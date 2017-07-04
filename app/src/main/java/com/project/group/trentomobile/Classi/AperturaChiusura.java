package com.project.group.trentomobile.Classi;


import android.text.format.Time;
import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by postal on 17/06/17.
 */

public class AperturaChiusura implements Serializable{

    public Date apre;
    public Date chiude;

    public AperturaChiusura(String apre, String chiude){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");

        try {
            this.apre = df.parse(apre);
            this.chiude = df.parse(chiude);
        } catch (ParseException e) {
            Log.e("AperturaChiusura", e.toString());
            e.printStackTrace();
        }
    }
}
