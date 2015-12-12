package com.fedor.pavel.sql_example;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.fedor.pavel.sql_example.adapters.DBTestAdapter;
import com.fedor.pavel.sql_example.model.BuyModel;
import com.fedor.pavel.sql_example.sql.SQLiteManager;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private SQLiteManager manager;
    private FloatingActionButton fab;
    private DBTestAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new SQLiteManager(this);
        try {
            manager.open();
            adapter = new DBTestAdapter(this, manager.selectBuys());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finish();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        listView = (ListView) findViewById(R.id.lv_db_test);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.remove(position);

                Snackbar.make(fab, "" + manager.deleteBuy(adapter.getItem(position).getId()), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });


        }

        @Override
    protected void onDestroy() {
        manager.close();
        super.onDestroy();
    }

    public void showDialog() {

        View v = getLayoutInflater().inflate(R.layout.item_dalog, null);
        final EditText edtName = (EditText) v.findViewById(R.id.edt_name);
        final EditText edtCount = (EditText) v.findViewById(R.id.edt_count);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(v)
                .setTitle("Insert element")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edtName.getText().toString();
                        String count = edtCount.getText().toString();

                        Snackbar.make(fab, "" + manager.insertBuy(new BuyModel(name, Double.valueOf(count))), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                }).setNegativeButton("Cancel", null);

        dialog.show();
    }


}
