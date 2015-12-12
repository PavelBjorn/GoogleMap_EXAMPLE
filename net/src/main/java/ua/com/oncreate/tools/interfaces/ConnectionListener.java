package ua.com.oncreate.tools.interfaces;

import org.apache.http.HttpResponse;

import ua.com.oncreate.tools.models.ConnectInfo;

public interface ConnectionListener {

    public void onStartConnection(String requestUrl);

    public void onFinishConnection(ConnectInfo info, HttpResponse response,
                                   String entity);
}