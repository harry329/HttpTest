package com.example.harry.httptest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by Harry on 3/21/2015.
 */
public class FlowerAdapter extends ArrayAdapter<Flower> {

    private Context context;
    private List<Flower> flowerlist;

    public FlowerAdapter(Context context,int resource, List<Flower> object){
        super(context,resource,object);
        this.context= context;
        this.flowerlist= object;

    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_layout,parent,false);
        Flower flower =flowerlist.get(position);
        TextView tv= (TextView)view.findViewById(R.id.textView);
        tv.setText(flower.getName());
        if(flower.getImage()!=null){
        ImageView im = (ImageView)view.findViewById(R.id.imageView);
        im.setImageBitmap(flower.getImage());}
        else{
            FlowerAndView container = new FlowerAndView();
            container.flower=flower;
            container.view= view;
            ImageLoader loader= new ImageLoader();
            loader.execute(container);
        }
        return view;
    }

    class FlowerAndView {
        public Flower flower;
        public View view;
        public Bitmap bitmap;

    }

    public class ImageLoader extends AsyncTask<FlowerAndView,Void,FlowerAndView>{
        protected FlowerAndView doInBackground(FlowerAndView...params) {
            FlowerAndView container = params[0];
            Flower flower = container.flower;
            try {

                String url = MainActivity.PHOTOS_BASE_URL + flower.getName();
                InputStream is = (InputStream) new URL(url).getContent();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                flower.setImage(bitmap);
                is.close();
                container.bitmap = bitmap;
                return container;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }



        }
         protected void onPostExecute(FlowerAndView result) {
             ImageView im = (ImageView) result.view.findViewById(R.id.imageView);
             im.setImageBitmap(result.bitmap);
             result.flower.setImage(result.bitmap);
         }
    }

}
