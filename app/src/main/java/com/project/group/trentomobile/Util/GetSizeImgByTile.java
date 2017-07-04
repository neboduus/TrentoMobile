package com.project.group.trentomobile.Util;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.project.group.trentomobile.Classi.Notizia;
import com.project.group.trentomobile.Classi.Tile;
import com.project.group.trentomobile.context.MyApplication;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by postal on 26/06/17.
 */

public class GetSizeImgByTile {

    private static GetSizeImgByTile instance = null;

    public static GetSizeImgByTile getInstance(){
        if(instance==null) instance = new GetSizeImgByTile();
        return instance;
    }

    public void setSizeImg(Tile t){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

       // if(!((t instanceof Notizia) && (((Notizia)t).getGenere().getTipo().equals("Meteo")))) {

            ContextWrapper cw = new ContextWrapper(MyApplication.getAppContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File f = new File(directory, "tileid" + t.getId());
            BitmapFactory.decodeFile(f.getAbsolutePath(), options);


            t.dinYImg = options.outHeight;
            t.dinXImg = options.outWidth;
            Log.d("size img " + t.getId() + " ->>>", String.valueOf(t.dinYImg) + "  -  " + String.valueOf(t.dinYImg));
        //}

        if((t.dinYImg == 0) || (t.dinYImg == -1)) {
            try {
                URL url = new URL(t.getPatterImmagine());
                BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options);

                t.dinYImg = options.outHeight;
                t.dinXImg = options.outWidth;
                Log.d("size img internet" + t.getId() + " ->>>", String.valueOf(t.dinYImg) + "  -  " + String.valueOf(t.dinYImg));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
