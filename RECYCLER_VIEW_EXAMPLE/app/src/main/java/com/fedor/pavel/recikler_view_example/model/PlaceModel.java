package com.fedor.pavel.recikler_view_example.model;


public class PlaceModel {

    private String name;
    private String url;
    private String address;
    private float distance;
    private double lan;
    private double lng;

    public PlaceModel(String name, String url, double lan, double lng,String address, float distance) {
        this.name = name;
        this.url = url;
        this.lan = lan;
        this.lng = lng;
        this.address = address;
        this.distance = distance;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getLan() {
        return lan;
    }

    public void setLan(double lan) {
        this.lan = lan;
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

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
