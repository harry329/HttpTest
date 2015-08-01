package com.example.harry.httptest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Harry on 3/22/2015.
 */
public class ParanClass {

    private String uri;
    private Map<String,String> params= new HashMap<>();
    private String method= "GET";
    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setParams(String key, String value){
        params.put(key,value);
    }

    public String getCodedString(){
        StringBuilder sb = new StringBuilder();
        String value = null;
        for(String key: params.keySet()){
            try{
             value= URLEncoder.encode(params.get(key),"UTF-8");}
            catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            if(sb.length()>0){
                sb.append("&");
            }
            sb.append(key +"="+value);

        }
       return sb.toString();
    }

}
