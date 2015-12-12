package com.fedor.pavel.recikler_view_example.adapters;


import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fedor.pavel.recikler_view_example.R;
import com.fedor.pavel.recikler_view_example.model.PlaceModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;

public class RecyclerViewTestAdapter extends RecyclerView.Adapter<RecyclerViewTestAdapter.TestViewHolder> {


    private ArrayList <PlaceModel> data = new ArrayList<>();


    public RecyclerViewTestAdapter(ArrayList <PlaceModel> data) {

        this.data.addAll(data);

    }


    @Override
    public RecyclerViewTestAdapter.TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_test, parent, false);

        TestViewHolder testViewHolder = new TestViewHolder(v);

        return testViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewTestAdapter.TestViewHolder holder, int position) {
        PlaceModel model = data.get(position);
        holder.tvName.setText(model.getName());
        holder.tvAddress.setText(model.getAddress());
        holder.tvDistance.setText(""+model.getDistance()+" км");

        ImageLoader.getInstance().displayImage(model.getUrl(),holder.imvPhoto,getImageOptions());


    }

    @Override
    public int getItemCount() {
        return data.size();
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
    class TestViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvAddress, tvDistance;
        public ImageView imvPhoto;

        public TestViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            tvDistance = (TextView) itemView.findViewById(R.id.tv_distance);
            imvPhoto = (ImageView) itemView.findViewById(R.id.imv_image);
        }
    }
}
