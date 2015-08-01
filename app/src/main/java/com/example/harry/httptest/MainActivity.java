package com.example.harry.httptest;

import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ListActivity {

    List<Flower> flowerlist;
    public static final String PHOTOS_BASE_URL="http://services.hanselandpetal.com/photos/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if (isOnline()) {
                requestData("http://services.hanselandpetal.com/feeds/flowers.json");
            } else {
                Toast.makeText(this, "no network", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
     protected void update(){
         FlowerAdapter adapter= new FlowerAdapter(this,R.layout.item_layout,flowerlist);
         setListAdapter(adapter);
     }
    private void requestData(String uri) {
        Mytask mytask = new Mytask();
        mytask.execute(uri);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }

    }

    private class Mytask extends AsyncTask<String, String, List<Flower>> {


        protected void onPreExecute() {

        }

        protected List<Flower> doInBackground(String... params) {
            String response =HttpManager.getData(params[0]);
            flowerlist = FlowerJsonParser.parseFeed(response);


            return  flowerlist;
        }

        protected void onPostExecute(List<Flower> result) {





            if(result != null){
                 update();}
        }
    }
}