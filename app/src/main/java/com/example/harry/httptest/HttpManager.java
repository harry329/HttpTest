package com.example.harry.httptest;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Harry on 3/20/2015.
 */
public class HttpManager {
    public static String getData(String uri){
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            bf = new BufferedReader( new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = bf.readLine()) != null){
                sb.append(line +'\n');
            }
            return sb.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (bf == null) {
            } else {
                try{
                    bf.close();
                }catch (Exception e){
                    e.printStackTrace();

                }}

        }


    }
    public static String getData(String uri, String username, String password){
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = null;
        byte[] login =(username + ":" + password).getBytes();
        StringBuilder str = new StringBuilder().append("Basic ").
                append(Base64.encodeToString(login,Base64.DEFAULT));
        try {
            URL url = new URL(uri);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("Authorizaton", login.toString());

            bf = new BufferedReader( new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = bf.readLine()) != null){
                sb.append(line +'\n');
            }
            return sb.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (bf == null) {
            } else {
                try{
                    bf.close();
                }catch (Exception e){
                    e.printStackTrace();

                }}

        }


    }
}
