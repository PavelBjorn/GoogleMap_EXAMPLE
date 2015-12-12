package com.fedor.pavel.googlemap.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.fedor.pavel.googlemap.MapsActivity;



public class GeocoderHandler extends Handler {

    private MapsActivity activity;

    public GeocoderHandler(MapsActivity activity){
        this.activity = activity;
    }

    @Override
    public void handleMessage(Message message) {

        String locationAddress;

        switch (message.what) {
            case 1:
                Bundle bundle = message.getData();
                locationAddress = bundle.getString("address");
                break;
            default:
                locationAddress = null;
        }

        Toast.makeText(activity,locationAddress,Toast.LENGTH_LONG).show();

    }
}


