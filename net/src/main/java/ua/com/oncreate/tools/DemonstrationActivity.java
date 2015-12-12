package ua.com.oncreate.tools;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;

import ua.com.oncreate.tools.http.Net;
import ua.com.oncreate.tools.interfaces.ConnectionListener;
import ua.com.oncreate.tools.models.ConnectInfo;

public class DemonstrationActivity /*extends Activity implements View.OnClickListener, ConnectionListener */{

//    // View`s declaration
//    private Button btnSend;
//    private EditText edtOutput;
//
//    private Net net;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_priview);
//
//        btnSend = (Button) findViewById(R.id.btn_get);
//        edtOutput = (EditText) findViewById(R.id.edt_responce);
//
//        btnSend.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//
//            case R.id.btn_get:
//                net = new Net(this, Net.POST, "http://192.168.0.116", false);
//                net.setUserAgent("my agent");
//                net.connect(this);
//                break;
//        }
//    }
//
//    @Override
//    public void onStartConnection(String url) {
//        edtOutput.setText("Start connect... to " + url);
//    }
//
//    @Override
//    public void onFinishConnection(ConnectInfo info, HttpResponse response, String entity) {
//        edtOutput.setText("Response from " + info.getUrl() + "\n"
//                + "Connection time " + info.getConnectTime() + "\n"
//                + "Connection result " + info.isConnectSuccessful() + "\n"
//                + entity);
//    }
}