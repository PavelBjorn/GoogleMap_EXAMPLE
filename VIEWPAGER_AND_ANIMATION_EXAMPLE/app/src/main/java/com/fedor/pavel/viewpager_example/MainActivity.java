package com.fedor.pavel.viewpager_example;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.daimajia.androidanimations.library.YoYo;
import com.fedor.pavel.viewpager_example.adapters.ImagesGalleryAdapter;
import com.fedor.pavel.viewpager_example.animation.ZoomOutPageTransformer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ViewPager vpTest;
    private ImagesGalleryAdapter adapter;
    private FrameLayout flTopPanel;

    /*Data*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); // делает активити на весь экран
        setContentView(R.layout.activity_main);

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        createViews();


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


    }


    public void createViews() {
        fillAdapter();
        adapter = new ImagesGalleryAdapter(this, fillAdapter());
        vpTest = (ViewPager) findViewById(R.id.vp_test);
        vpTest.setPageTransformer(false, new ZoomOutPageTransformer());// добавляем анимацию листания страниц
        vpTest.setAdapter(adapter);

        flTopPanel = (FrameLayout) findViewById(R.id.top_panel);
    }

    public ArrayList<String> fillAdapter() {

        ArrayList<String> photosUrl = new ArrayList<>();
        photosUrl.add("http://cs622222.vk.me/v622222854/42be0/NGNSGniKypg.jpg");
        photosUrl.add("http://cs622222.vk.me/v622222854/42be8/zFuiJCONQtc.jpg");
        photosUrl.add("http://cs622222.vk.me/v622222854/42bf9/JGW_Wx80F1s.jpg");
        return photosUrl;

    }

    /*При вызове метода убирает панель*/
    public void viewPanels() {

        if (flTopPanel.getAlpha() == 1f) {
            ObjectAnimator anim = ObjectAnimator.ofFloat(flTopPanel, "alpha", 1f, 0f);// изменяет прозрачность от 0 до 1
            anim.setDuration(350); // длительность анимации
            anim.start();
        } else {
            ObjectAnimator anim = ObjectAnimator.ofFloat(flTopPanel, "alpha", 0f, 1f);// изменяет прозрачность от 0 до 1
            anim.setDuration(350);// длительность анимации
            anim.start();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
