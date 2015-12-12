package com.fedor.pavel.googlemap;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import com.fedor.pavel.googlemap.file.FileManager;
import com.fedor.pavel.googlemap.listener.OnATMGetListener;

import com.fedor.pavel.googlemap.location.CurrentLocation;
import com.fedor.pavel.googlemap.location.GetDirectionsAsyncTask;
import com.fedor.pavel.googlemap.model.WasherModel;
import com.fedor.pavel.googlemap.net.APIProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnATMGetListener, GoogleMap.OnInfoWindowClickListener, View.OnClickListener {


    private GoogleMap mMap;

    public static final String ATM_KEY = "place";

    private ArrayList<WasherModel> models = new ArrayList<>();
    private ArrayAdapter<String> citiesAdapter;
    private AutoCompleteTextView avtSearch;
    private ImageButton ibSearch;
    private Polyline newPolyline;
    private Marker locationMarker;
    private CurrentLocation currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            citiesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, FileManager.getCities(this));
        } catch (IOException e) {
            e.printStackTrace();
        }


        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        /*GeocoderHandler handler = new GeocoderHandler(this);

        LocationAddress.getAddressFromLocation(48.4471166,35.0574361,this,handler);*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

        /*loadAllATM();*/

    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {

        mMap.setMyLocationEnabled(false);
        mMap.setOnInfoWindowClickListener(this);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.4622985, 35.0003565), 11));
        avtSearch = (AutoCompleteTextView) this.findViewById(R.id.atv_search);
        avtSearch.setAdapter(citiesAdapter);
        ibSearch = (ImageButton) findViewById(R.id.ib_search);
        ibSearch.setOnClickListener(this);

        addLocationMarker();

    }

    public void loadAllATM() {

        APIProvider.getAllATM(this);
    }

    public void loadAllATM(String city) {
        APIProvider.getAllATM(this, city);
    }

    @Override
    public void getAllATM(ArrayList<WasherModel> models) {


        for (int i = 0; i < models.size(); i++) {

            int markerIconID = R.drawable.ic_atm_deactiv;

            if(models.get(i).isWorkTime()){
                markerIconID = R.drawable.ic_atm_activ;
            }

            MarkerOptions options = new MarkerOptions()
                    .position(new LatLng(models.get(i).getLat(), models.get(i).getLng()))
                    .title(models.get(i).getPlace())
                    .snippet(models.get(i).getAddress())
                    .icon(BitmapDescriptorFactory.fromResource(markerIconID));

            models.get(i).setMarker(mMap.addMarker(options));

        }

        this.models.addAll(models);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        WasherModel model = findATMbyMarker(marker);

        if (model != null) {
            Intent intent = new Intent(this, ATMInfoActivity.class);
            try {
                intent.putExtra(ATM_KEY, model.toJSONObject().toString());
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public WasherModel findATMbyMarker(Marker marker) {
        for (int i = 0; i < models.size(); i++) {
            if (marker.equals(models.get(i).getMarker())) {
                return models.get(i);
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ib_search:

                mMap.clear();

                loadAllATM(avtSearch.getText().toString().trim());

                break;

        }
    }

    public void startToShowRout(LatLng from, LatLng to, String str) {

        HashMap<String, String> map = new HashMap<>();

        map.put(GetDirectionsAsyncTask.USER_CURRENT_LAT, String.valueOf(from.latitude));
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LONG, String.valueOf(from.longitude));
        map.put(GetDirectionsAsyncTask.DESTINATION_LAT, String.valueOf(to.latitude));
        map.put(GetDirectionsAsyncTask.DESTINATION_LONG, String.valueOf(to.longitude));
        map.put(GetDirectionsAsyncTask.DIRECTIONS_MODE, str);

        GetDirectionsAsyncTask asyncTask = new GetDirectionsAsyncTask(this);
        asyncTask.execute(map);

    }

    public void showRout(ArrayList<LatLng> result) {

        if (newPolyline != null)
            newPolyline.setPoints(result);
        else {
            PolylineOptions rectLine = new PolylineOptions().width(5).color(Color.RED);
            for (int i = 0; i < result.size(); i++)
                rectLine.add(result.get(i));
            newPolyline = mMap.addPolyline(rectLine);

        }
    }

    private void addLocationMarker(){

      currentLocation = new CurrentLocation(this);

        MarkerOptions options = new MarkerOptions()
                .position(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()))
                .title("You");

        locationMarker = mMap.addMarker(options);

    }

    public Marker getLocationMarker(){
        return locationMarker;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        currentLocation.stopUsingGPS();
    }
}
