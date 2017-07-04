package com.project.group.trentomobile.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.project.group.trentomobile.context.MyApplication;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

/**
 * Created by postal on 15/04/17.
 */

public class ScaricaImmagine extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    static Bitmap busImg = null;

    public ScaricaImmagine(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {


        //CONTROLLO IMMAGINE GIA' SCARICATA
        //SE UEGLI URLS E? PRESENTE UN NOME PER SALVARE L'IMMAGINE

        SaveLoadImg sli = SaveLoadImg.getIstance();

        Bitmap mIcon11 = null;



        if(urls.length == 2) {

            Log.d("name img", urls[1]);

            if(urls[1].equals("bus3")) {
                if(busImg != null) {
                    Log.d("immagine bus", "in ram");
                    return busImg;
                }
                Log.d("immagine bus", "e lei");
            }

            Bitmap b = sli.loadImageFromStorage(urls[1]);
            if (b != null) {
                Log.d("immagine", "in memoria");
                if(urls[1].equals("bus3")) {
                    busImg = b;
                    Log.d("setta", "bus img");
                }
                return b;
            }
        }



        String urldisplay = urls[0];
        URL url;
        try {


            url = new URL(urldisplay);
            mIcon11 = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            if(urls.length==2){

                if(urls[1].equals("bus3")) {
                    busImg = mIcon11;
                    Log.d("setta", "bus img");
                }

                Log.d("salva", urls[1]);
                sli.saveToInternalStorage(mIcon11,urls[1]);
            }

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
