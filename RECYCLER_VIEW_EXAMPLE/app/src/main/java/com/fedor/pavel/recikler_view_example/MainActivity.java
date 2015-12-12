package com.fedor.pavel.recikler_view_example;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.fedor.pavel.recikler_view_example.adapters.RecyclerViewTestAdapter;
import com.fedor.pavel.recikler_view_example.model.PlaceModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG = "myTag";
    private RecyclerView rvTest;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewTestAdapter adapter;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createView();

        setSupportActionBar(toolbar);

        Log.d(LOG_TAG, "onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    public void createView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /*Находим recycler view*/
        rvTest = (RecyclerView) findViewById(R.id.rv_test);
        /*Создам адаптер*/
        adapter = new RecyclerViewTestAdapter(getData());
        rvTest.setAdapter(adapter);
        /*Создаем layoutManager*/
        layoutManager = new LinearLayoutManager(this);
        // присваиваем layoutManager
        rvTest.setLayoutManager(layoutManager);
        //если размер списка не изменится в будущем то этот метод оптимизирует его работу
        /*rvTest.setHasFixedSize(true);*/



    }
    private ArrayList<PlaceModel> getData(){

        ArrayList<PlaceModel> models = new ArrayList<>();

        models.add(new PlaceModel("test1","http://cs543101.vk.me/v543101752/101e6/mZcPSoTcv9Q.jpg",44.555,45.66,"address1",50));
        models.add(new PlaceModel("test2","http://cs543101.vk.me/v543101752/101f8/WnCFbHPuhts.jpg",44.555,45.66,"address2",14));
        models.add(new PlaceModel("test3","http://cs625418.vk.me/v625418854/435f2/bnoW6ZzpTnU.jpg",44.555,45.66,"address3",15));

        return models;

    }
}
