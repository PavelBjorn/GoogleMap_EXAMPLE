package com.fedor.pavel.googlemap.model;


import org.json.JSONException;
import org.json.JSONObject;

public class DateModel {

    private String name;
    private String workTime;
    private int dayId;


    public DateModel() {

    }
    public DateModel(String name, String workTime) {
        setName(name);
        this.workTime = workTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        switch (name){

            case "sun":
                dayId = 1;
                this.name = "Воскресение";
                break;

            case "mon":
                dayId = 2;
                this.name = "Понедельник";
                break;

            case "tue":
                dayId = 3;
                this.name = "Вторник";
                break;

            case "wed":
                dayId = 4;
                this.name = "Среда";
                break;

            case "thu":
                dayId = 5;
                this.name = "Четверг";
                break;

            case "fri":
                dayId = 6;
                this.name = "Пятница";
                break;

            case "sat":
                dayId = 7;
                this.name = "Суббота";
                break;

            case "hol":
                dayId = 8;
                this.name = "Праздники";
                break;

        }
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject object = new JSONObject();
        object.put("name",name);
        object.put("workTime",workTime);
        object.put("id",dayId);

        return object;

    }

    public void fromJSONObject(JSONObject object) throws JSONException {

        name = object.getString("name");
        workTime = object.getString("workTime");
        dayId = object.getInt("id");

    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }
}
