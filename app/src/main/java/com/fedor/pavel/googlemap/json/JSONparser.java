package com.fedor.pavel.googlemap.json;

import com.fedor.pavel.googlemap.model.WasherModel;
import com.fedor.pavel.googlemap.model.DateModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;



public class JSONparser {


    public static ArrayList<WasherModel> parseATMList(String entity) throws JSONException {

        ArrayList<WasherModel> dataArrayList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(entity);
        JSONArray jsonArray = jsonObject.getJSONArray("devices");

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonATM = jsonArray.getJSONObject(i);
            String place = jsonATM.optString("placeRu", "no name");
            String city = jsonATM.optString("cityRU","-");
            String address = jsonATM.optString("fullAddressRu","-");
            double lat = jsonATM.optDouble("latitude", 0);
            double lng = jsonATM.optDouble("longitude", 0);

            JSONObject date = jsonATM.getJSONObject("tw");
            Iterator <String> iterator =date.keys();
            ArrayList<DateModel> dateModels = new ArrayList<>();

            while (iterator.hasNext()){
                String day = iterator.next();
                dateModels.add(new DateModel(day,date.getString(day)));
            }

            dataArrayList.add(new WasherModel(city,address,place, lat, lng,dateModels));
        }

        return dataArrayList;

    }


}
