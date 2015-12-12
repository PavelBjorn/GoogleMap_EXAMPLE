package com.fedor.pavel.googlemap.net;


import com.fedor.pavel.googlemap.listener.OnATMGetListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class APIProvider {

    public static void getAllATM(OnATMGetListener listener){

        RequestTask requestTask = new RequestTask(listener);
        requestTask.execute("https://api.privatbank.ua/p24api/infrastructure?json&atm");



    }
    public static void getAllATM(OnATMGetListener listener, String city) {

        RequestTask requestTask = new RequestTask(listener);

        try {

            requestTask.execute("https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city="
                                + URLEncoder.encode(city,"UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }



}
