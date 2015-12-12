package com.fedor.pavel.googlemap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/*  Провайдер - источник получения данных местоположения
    * 1 - не точное опр местопложения (примерно 2 квартала) (Вышки сотовой связи, WIFI);
    *    (разрешение в манифесте ACCESS_COARSE_LOCATION)
    * 2 - точное местоположения (5 - 10 м) ( GPS, ГЛОННАС );
    *    (разрешение в манифесте ACCESS_FINE_LOCATION)
    *
    * */

public class CurrentLocationActivity extends AppCompatActivity implements LocationListener {

    private TextView tvInfo;
    private LocationManager locationManager;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000; // 1 sec

    private boolean isGPSEnabled;

    private boolean isNetworkEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_current_location);
        tvInfo = (TextView) findViewById(R.id.tv_result);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

     /*   Location location12 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);*/

        if (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if (location != null) {
            tvInfo.setText("lat = " + location.getLatitude() + ", long = " + location.getLongitude());
        }

        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        /* Задаем слушатель местоположеия */
       /* locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);*/

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        /*Всегда закрывать*/
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        if (location != null) {
            tvInfo.setText("lat = " + location.getLatitude() + ", long = " + location.getLongitude());
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {


    }

    public void showSettingsAlert() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

// Setting Dialog Title

        alertDialog.setTitle("GPS is settings");

// Setting Dialog Message

        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

// On pressing Settings button

        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

// on pressing cancel button

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

// Showing Alert Message

        alertDialog.show();
    }
}
