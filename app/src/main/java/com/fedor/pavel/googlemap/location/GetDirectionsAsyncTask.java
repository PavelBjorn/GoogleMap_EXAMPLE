package com.fedor.pavel.googlemap.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.w3c.dom.Document;

import com.fedor.pavel.googlemap.MapsActivity;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

/**Класс показывает путь от точки до точки*/
public class GetDirectionsAsyncTask extends AsyncTask<HashMap<String, String>, Object, ArrayList<LatLng>>
{
    public static final String USER_CURRENT_LAT = "user_current_lat";
    public static final String USER_CURRENT_LONG = "user_current_long";
    public static final String DESTINATION_LAT = "destination_lat";
    public static final String DESTINATION_LONG = "destination_long";
    public static final String DIRECTIONS_MODE = "directions_mode";
    private Activity activity;
    private Exception exception;
    private ProgressDialog progressDialog;
 
    public GetDirectionsAsyncTask(Activity activity){
        super();
        this.activity = activity;
    }
 
    public void onPreExecute(){
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Wait please");
        //progressDialog.setCancelable(false);
        progressDialog.show();
    }
 
    @Override
    public void onPostExecute(ArrayList<LatLng> result){
        progressDialog.cancel();
        if (exception == null) {
            ((MapsActivity) activity).showRout(result);
        }
    }
 
    @Override
    protected ArrayList<LatLng> doInBackground(HashMap<String, String>... params){

        Map<String, String> paramMap = params[0];

        try{
            LatLng fromPosition = new LatLng(Double.valueOf(paramMap.get(USER_CURRENT_LAT)) , Double.valueOf(paramMap.get(USER_CURRENT_LONG)));
            LatLng toPosition = new LatLng(Double.valueOf(paramMap.get(DESTINATION_LAT)) , Double.valueOf(paramMap.get(DESTINATION_LONG)));
            GMapV2Direction md = new GMapV2Direction();
            Document doc = md.getDocument(fromPosition, toPosition, paramMap.get(DIRECTIONS_MODE));
            ArrayList<LatLng> directionPoints = md.getDirection(doc);

            return directionPoints;

        }
        catch (Exception e){
            exception = e;
            return null;
        }
    }
}