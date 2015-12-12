package com.fedor.pavel.sql_example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fedor.pavel.sql_example.R;
import com.fedor.pavel.sql_example.model.BuyModel;

import java.util.ArrayList;


public class DBTestAdapter extends BaseAdapter {


    private ArrayList<BuyModel> models = new ArrayList<>();
    private LayoutInflater inflater;


    public DBTestAdapter(Context context) {

        inflater = LayoutInflater.from(context);

    }

    public DBTestAdapter(Context context, ArrayList<BuyModel> models) {

        inflater = LayoutInflater.from(context);
        this.models.addAll(models);

    }

    public void addModel(BuyModel model) {
        this.models.add(model);
    }

    public void addAllModels(ArrayList<BuyModel> models) {
        this.models.addAll(models);
        notifyDataSetChanged();
    }

    public void remove( int positon ){
        models.remove(positon);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public BuyModel getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v;

        if (convertView == null) {
            v = inflater.inflate(R.layout.item_list_view, null);
        } else {
            v = convertView;
        }

        TextView tvName = (TextView) v.findViewById(R.id.tv_name);
        TextView tvCount = (TextView) v.findViewById(R.id.tv_count);
        tvName.setText(models.get(position).getName());
        tvCount.setText("" + models.get(position).getCount());

        return v;
    }
}
