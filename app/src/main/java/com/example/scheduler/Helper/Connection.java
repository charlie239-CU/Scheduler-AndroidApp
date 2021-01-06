package com.example.scheduler.Helper;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Connection
{
    public static HttpURLConnection createConnection(String filename) {
        HttpURLConnection connection = null;
        String path = "http://192.168.43.136/scheduler/" + filename;
        try {
            URL url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            Log.d("Connection", "Connection established");
        } catch (Exception e) {
            Log.d("Connection", e.toString());
        }
        return connection;
    }
}
