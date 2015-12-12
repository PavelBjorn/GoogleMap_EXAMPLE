package com.fedor.pavel.googlemap.listener;


import com.fedor.pavel.googlemap.model.WasherModel;

import java.util.ArrayList;

/**
 * Created by konovalenko on 16.09.2015.
 */
public interface OnATMGetListener {

    void getAllATM(ArrayList<WasherModel> models);
}
