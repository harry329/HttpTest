package com.example.harry.httptest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 3/20/2015.
 */
public class FlowerJsonParser {
    public static List<Flower> parseFeed(String content){
        try{
        JSONArray ar = new JSONArray(content);
        List<Flower> list = new ArrayList<>();
        for(int i=0; i<ar.length(); i++){
            Flower flower = new Flower();
            JSONObject obj = ar.getJSONObject(i);
            flower.setProductId(obj.getInt("productId"));
            flower.setPhoto(obj.getString("photo"));
            flower.setPrice(obj.getDouble("price"));
            flower.setName(obj.getString("name"));
            flower.setInstructions(obj.getString("instructions"));
            flower.setCategory(obj.getString("category"));
            list.add(flower);
        }

        return list;}
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
