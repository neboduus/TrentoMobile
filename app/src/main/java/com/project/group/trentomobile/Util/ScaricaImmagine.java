package com.project.group.trentomobile.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.net.URL;

/**
 * Created by postal on 15/04/17.
 */

public class ScaricaImmagine extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public ScaricaImmagine(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        URL url;
        try {


            url = new URL(urldisplay);
            mIcon11 = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return mIcon11;

        } catch (Exception e) {
            Log.e("Error", "_____"+e.getMessage());
            e.printStackTrace();
            return null;
        }


    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);

    }
}
