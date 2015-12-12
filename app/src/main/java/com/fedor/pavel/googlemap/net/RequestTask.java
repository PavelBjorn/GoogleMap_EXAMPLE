package com.fedor.pavel.googlemap.net;


import android.os.AsyncTask;

import com.fedor.pavel.googlemap.json.JSONparser;
import com.fedor.pavel.googlemap.listener.OnATMGetListener;
import com.fedor.pavel.googlemap.model.WasherModel;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by konovalenko on 16.09.2015.
 */
public class RequestTask extends AsyncTask<String, Void, ArrayList<WasherModel>> {

    private OnATMGetListener onATMGetListener;

    public RequestTask(OnATMGetListener onATMGetListener) {
        this.onATMGetListener = onATMGetListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected ArrayList<WasherModel> doInBackground(String... params) {

        String response = "";
        ArrayList<WasherModel> dataArrayList = new ArrayList<>();

        try {

            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));



                while ((line = reader.readLine()) != null) {
                    response += line;
                }

                dataArrayList.addAll(JSONparser.parseATMList(response));


        } else {


        }

        } catch (IOException e) {



        } catch (JSONException e) {



        }

        return dataArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<WasherModel> models) {
        super.onPostExecute(models);
        if (onATMGetListener != null){
            onATMGetListener.getAllATM(models);
        }
    }
}