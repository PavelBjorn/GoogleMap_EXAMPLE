package com.fedor.pavel.googlemap.location;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;



public class CalcDistanceBetweenPoints {

    public static double calcDistanceBetweenPoints(LatLng one, LatLng two){

        try{

            Location loc1 = new Location("");
            loc1.setLatitude(one.latitude);
            loc1.setLongitude(one.longitude);

            Location loc2 = new Location("");
            loc2.setLatitude(two.latitude);
            loc2.setLongitude(two.longitude);

            double distanceInKM = loc1.distanceTo(loc2);
            return distanceInKM;
        }
        catch(Exception e){
            return 0;
        }
    }






}
