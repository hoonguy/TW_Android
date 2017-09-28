package com.jh.app.twapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerConnect extends AsyncTask <String, Void, String> {
    private String sendMsg, rcvMsg;

    @Override
    protected String doInBackground(String... str_login) {
        HttpURLConnection conn = null;
        try {
            String tmp;
            sendMsg = "id=" + str_login[0] + "&pw=" + str_login[1] + "&type=" + str_login[2];

            // Connection to Server
//            URL url = new URL("http://203.224.130.141:8080/AndroidConnect.jsp");
            URL url = new URL("http://203.224.130.141:8080/dbconnect.jsp");
            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            conn.setRequestMethod("POST");
/*
            // Deliver the parameter from Android to Server
            OutputStreamWriter outs = new OutputStreamWriter(conn.getOutputStream());
            outs.write(sendMsg);
            outs.flush();
            outs.close();
*/
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.println(sendMsg);
            out.close();

            // Returns the values from Server to Android
            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                StringBuffer sb = new StringBuffer();

                while ((tmp = br.readLine()) != null) { sb.append(tmp); }
                rcvMsg = sb.toString();
            } else {
                Log.i("Result for Connection", conn.getResponseCode() + "Error");
                conn.disconnect();
            }
        } catch (MalformedURLException e_url) {
            e_url.printStackTrace();
            conn.disconnect();
        } catch (IOException e_url_open) {
            e_url_open.printStackTrace();
            e_url_open.getMessage();
        }
        return rcvMsg;
    }
}
