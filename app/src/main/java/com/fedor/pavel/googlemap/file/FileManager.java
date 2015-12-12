package com.fedor.pavel.googlemap.file;


import android.app.Activity;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileManager   {


    public static ArrayList<String> getCities(Activity activity) throws IOException {

        AssetManager am = activity.getAssets();

        ArrayList <String> cities  = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(am.open("cities.txt")));

        while (reader.readLine()!=null){
            cities.add(reader.readLine());
        }

        return cities;

    }








}
