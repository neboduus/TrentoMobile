package com.project.group.trentomobile.Util;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.project.group.trentomobile.context.MyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by postal on 17/06/17.
 */

public class SaveLoadImg {


    private static SaveLoadImg istance;

    public static SaveLoadImg getIstance(){
        if(istance == null) istance = new SaveLoadImg();
        return istance;
    }


    public String saveToInternalStorage(Bitmap bitmapImage, String name){
        ContextWrapper cw = new ContextWrapper(MyApplication.getAppContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,name);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }


    public Bitmap loadImageFromStorage(String name)
    {
        Bitmap b = null;

        try {
            ContextWrapper cw = new ContextWrapper(MyApplication.getAppContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File f=new File(directory, name);
            b = BitmapFactory.decodeStream(new FileInputStream(f));

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return b;

    }

    public void deleatImageFromStorage(String name)
    {
        Bitmap b = null;

            ContextWrapper cw = new ContextWrapper(MyApplication.getAppContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File f=new File(directory, name);
            boolean r = f.delete();

        Log.d("deleate",name+"   "+r);

    }
}
