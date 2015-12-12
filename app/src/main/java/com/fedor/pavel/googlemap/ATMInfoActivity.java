package com.fedor.pavel.googlemap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fedor.pavel.googlemap.model.WasherModel;

import org.json.JSONException;
import org.json.JSONObject;


public class ATMInfoActivity extends AppCompatActivity {


    private TextView tvPlace, tvAddress;
    private ListView lvWorkTime;
    private ArrayAdapter<String> adapter;
    private WasherModel washerModel = new WasherModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm_info);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        try {
            washerModel.fromJSONObject(new JSONObject(getIntent().getStringExtra(MapsActivity.ATM_KEY)));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        for (int i = 0 ;i< washerModel.getDateModels().size();i++){
            adapter.add(washerModel.getDate(i).getName()+": "+ washerModel.getDate(i).getWorkTime());
        }




        tvPlace = (TextView) findViewById(R.id.tv_atm_place);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        lvWorkTime = (ListView) findViewById(R.id.lv_work_time);
        lvWorkTime.setAdapter(adapter);

        tvPlace.setText(washerModel.getPlace());
        tvAddress.setText(washerModel.getAddress());

    }
}
