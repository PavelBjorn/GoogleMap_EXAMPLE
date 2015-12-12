package com.fedor.pavel.googlemap.location;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.fedor.pavel.googlemap.MapsActivity;
import com.google.android.gms.maps.model.LatLng;

public class CurrentLocation implements LocationListener {

    private MapsActivity activity;

    private boolean isGPSEnable = false;
    private boolean isNetWorkEnable = false;
    private boolean canGetLocation = false;

    private final int MIN_UPDATE_DISTANCE = 1; // 5 - miters
    private final int MIN_UPDATE_TIME = 0; // 5 - minute

    private Location location;
    private double latitude;
    private double longitude;

    private LocationManager locationManager;

    private final String TAG = "CurrentLocationTag";


    public CurrentLocation(MapsActivity activity) {
        this.activity = activity;
        getLocation();
    }


    public Location getLocation() {

        try {


            locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

            isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetWorkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnable && !isNetWorkEnable) {

                showSettingsAlert();

            } else {

                this.canGetLocation = true;

                if (isNetWorkEnable) {

                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_UPDATE_TIME, MIN_UPDATE_DISTANCE, this);
                    if (locationManager != null) {

                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }

                    }
                }

                if (isGPSEnable) {

                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_UPDATE_TIME, MIN_UPDATE_DISTANCE, this);

                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }

                        }

                    }

                }


            }
        } catch (Exception e) {
            Log.d(TAG, "" + e);
        }

        return location;
    }

    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(CurrentLocation.this);
        }
    }

    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }

        return latitude;
    }

    /**
     * Function to get longitude
     */
    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }
        return longitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);

// Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

// Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

// On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new
                        Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                activity.startActivity(intent);
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

    @Override
    public void onLocationChanged(Location location) {

        if (location != null) {
            this.location = location;
            this.latitude = location.getLatitude();
            this.longitude = location.getLongitude();

            activity.getLocationMarker().setPosition(new LatLng(latitude, longitude));
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


}
