package com.fedor.pavel.googlemap.model;


import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WasherModel {

    private String place;
    private double lat, lng;
    private String address;
    private String city;
    private ArrayList<DateModel> dateModels = new ArrayList<>();
    private Marker marker;


    public WasherModel(String place, double lat, double lng) {
        this.place = place;
        this.lat = lat;
        this.lng = lng;
    }

    public WasherModel(String city, String address, String place, double lat, double lng) {
        this.city = city;
        this.address = address;
        this.place = place;
        this.lat = lat;
        this.lng = lng;
    }

    public WasherModel(String city, String address, String place, double lat, double lng, ArrayList<DateModel> dateModels) {
        this.city = city;
        this.address = address;
        this.place = place;
        this.lat = lat;
        this.lng = lng;
        this.dateModels.addAll(dateModels);
    }

    public WasherModel() {


    }

    public void addDate(DateModel dateModel) {
        dateModels.add(dateModel);
    }

    public void addAllDate(ArrayList<DateModel> dates) {
        this.dateModels.addAll(dates);
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public DateModel getDate(int position) throws ArrayIndexOutOfBoundsException {
        return dateModels.get(position);
    }

    public ArrayList<DateModel> getDateModels() {
        return dateModels;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Marker getMarker() {
        return marker;
    }

    public boolean isWorkTime() {

        Calendar calendar = new GregorianCalendar();

        int currentDayId = calendar.get(Calendar.DAY_OF_WEEK);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMin = calendar.get(Calendar.MINUTE);

        int workHourFrom = 0;
        int workMinFrom = 0;
        int workHourTo = 0;
        int workMinTo = 0;

        for (int i = 0; i < dateModels.size(); i++) {

            if (dateModels.get(i).getDayId() == currentDayId) {

                String time = dateModels.get(i).getWorkTime();

                workHourFrom = Integer.valueOf(time.substring(0, 2));
                workMinFrom = Integer.valueOf(time.substring(3, 5));
                workHourTo = Integer.valueOf(time.substring(time.length() - 5, time.length() - 3));
                workMinTo = Integer.valueOf(time.substring(time.length() - 2, time.length()));

                break;
            }
        }


        if (currentHour > workHourFrom && currentHour < workHourTo) {
            return true;
        } else if (currentHour == workMinFrom) {

            if (currentMin >= workMinFrom) {
                return true;
            }

        } else if (currentHour == workHourTo) {
            if (currentMin <= workMinTo) {
                return true;
            }
        } else if (workHourFrom == workHourTo) {
            return true;
        }


        return false;

    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject object = new JSONObject();

        object.put("lng", lng);
        object.put("lat", lat);
        object.put("place", place);
        object.put("address", address);
        object.put("city", city);

        JSONArray array = new JSONArray();

        for (int i = 0; i < dateModels.size(); i++) {
            array.put(dateModels.get(i).toJSONObject());
        }

        object.put("workDate", array);

        return object;

    }

    public void fromJSONObject(JSONObject object) throws JSONException {

        lat = object.getDouble("lat");
        lng = object.getDouble("lng");
        place = object.getString("place");
        address = object.getString("address");
        city = object.getString("city");

        JSONArray array = object.getJSONArray("workDate");

        for (int i = 0; i < array.length(); i++) {

            DateModel dateModel = new DateModel();
            dateModel.fromJSONObject(array.getJSONObject(i));
            dateModels.add(dateModel);
        }

    }


}
