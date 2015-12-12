package com.fedor.pavel.sql_example.model;

/**
 * Created by Pavel on 21.10.2015.
 */
public class BuyModel {

    private long id;
    private String name;
    private double count;

    public BuyModel(){}

    public BuyModel(String name, double count) {
        this.name = name;
        this.count = count;
    }

    public BuyModel(long id, String name, double count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
