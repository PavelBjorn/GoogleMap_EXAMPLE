package com.fedor.pavel.viewpager_example.adapters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.fedor.pavel.viewpager_example.MainActivity;
import com.fedor.pavel.viewpager_example.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;

/**
 * Created by Pavel on 14.10.2015.
 */
public class ImagesGalleryAdapter extends PagerAdapter {

    private Activity activity;
    private ArrayList<String> imagePaths;
    private LayoutInflater inflater;

    public ImagesGalleryAdapter(Activity activity,
                                ArrayList<String> imagePaths) {
        this.activity = activity;
        this.imagePaths = imagePaths;
        this.inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View item = inflater.inflate(R.layout.item_gallery_image, container, false);
        ((ViewPager) container).addView(item);

        final ImageView imgCurrent = (ImageView) item.findViewById(R.id.item_gallery_image_imv_photo);

        imgCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)activity).viewPanels();
/*
                ObjectAnimator animator = ObjectAnimator.ofFloat(imgCurrent, "rotation", 0f, 360f);
                animator.start();*/// Анимирует поворот изображения

                /*ObjectAnimator animX = ObjectAnimator.ofFloat(imgCurrent, "x", 50f);
                ObjectAnimator animY = ObjectAnimator.ofFloat(imgCurrent, "y", 100f);
                AnimatorSet animSetXY = new AnimatorSet();
                animSetXY.playTogether(animX, animY);
                animSetXY.start();
                imgCurrent.animate().x(50f).y(100f);*/ // Анимируе смещение по оси х и  у

                YoYo.with(Techniques.FadeIn)
                        .duration(700)
                        .playOn(imgCurrent);
            }
        });
        ImageLoader.getInstance().displayImage(imagePaths.get(position), imgCurrent, getImageOptions());

        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((FrameLayout) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 1f;
    }


    public static DisplayImageOptions getImageOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }
}
