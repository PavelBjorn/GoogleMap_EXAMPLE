package ua.com.oncreate.tools.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import ua.com.oncreate.tools.http.Net;
import ua.com.oncreate.tools.http.NetSettings;
import ua.com.oncreate.tools.interfaces.ConnectionListener;
import ua.com.oncreate.tools.models.ConnectInfo;
import ua.com.oncreate.tools.models.ResponseModel;
import ua.com.oncreate.tools.utils.RequestStringBuilders;


public class NetMultipartTask extends AsyncTask<String, Void, ResponseModel> {

    // General
    private Net net;
    private Context context;
    private HttpPost httpPost;
    private ConnectionListener taskListener;

    // Data
    private boolean showDialog;
    private ProgressDialog progressDialog;

    private long timeStartRequest;


    public NetMultipartTask(Net net, HttpPost httpPost, ConnectionListener connectionListener) {
        this.net = net;
        this.context = net.getContext();
        this.showDialog = net.isProgressDialogEnable();
        this.taskListener = connectionListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (taskListener != null) {
            taskListener.onStartConnection(net.getURL());
        }

        if (showDialog) {
            try {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage(net.getDialogMessage().equals(RequestStringBuilders.EMPTY_STRING) ? RequestStringBuilders.DEFAULT_DIALOG_LOADING_MESSAGE : net.getDialogMessage());
                progressDialog.show();
            } catch (Exception e) {

            }
        }
    }

    @Override
    protected ResponseModel doInBackground(String... params) {
        try {
            timeStartRequest = System.currentTimeMillis();

            NetSettings netSettings = NetSettings.getInstance(net);
            HttpResponse response = null;
            if (httpPost != null)
                response = netSettings.getResponse(httpPost, new PhotoUploadResponseHandler());

            String entityString = null;
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null)
                    entityString = EntityUtils.toString(entity);
            } catch (Exception e) {
            }

            return new ResponseModel(response, netSettings.getHttpClient(), entityString, net.getURL());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(ResponseModel responseModel) {
        super.onPostExecute(responseModel);

        try {
            if (progressDialog != null) {
                progressDialog.cancel();
            }
        } catch (Exception e) {
        }

        if (taskListener != null) {
            ConnectInfo info = new ConnectInfo(net.getURL(), (int) ((System.currentTimeMillis() - timeStartRequest) / 1000), responseModel != null, responseModel.getDefaultHttpClient());
            taskListener.onFinishConnection(info,
                    responseModel.getReponse(), responseModel.getEntity());
        }
    }

    public class PhotoUploadResponseHandler implements ResponseHandler<Object> {

        @Override
        public Object handleResponse(HttpResponse response)
                throws ClientProtocolException, IOException {

            HttpEntity r_entity = response.getEntity();
            String responseString = EntityUtils.toString(r_entity);
            Log.d("UPLOAD", responseString);

            return null;
        }

    }
}

