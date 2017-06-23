package com.project.group.trentomobile.CategoriePK;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.group.trentomobile.Util.ScaricaImmagine;

/**
 * Created by postal on 24/04/17.
 */

public class Categoria extends RelativeLayout {

    String nome;
    SquareImageView immagine;
    public String img = "";
    TextView txtNome;
    RelativeLayout.LayoutParams params;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Categoria(Context context) {

        super(context);

        params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);



        immagine = new SquareImageView(context);
        txtNome = new TextView(context);



        this.setBackgroundColor(Color.RED);
        this.setLayoutParams(params);
        this.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        immagine.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        immagine.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        txtNome.setLayoutParams(rp);
        txtNome.setTextSize(20);
        txtNome.setTextColor(Color.WHITE);
        txtNome.setGravity(Gravity.CENTER | Gravity.CENTER);

//        GradientDrawable d = new GradientDrawable();
  //      d.setGradientType(GradientDrawable.LINEAR_GRADIENT);
    //    d.setShape(GradientDrawable.RECTANGLE);


        txtNome.setShadowLayer(2.1f, 1.5f, 1.3f, Color.BLACK);
        //d.setColor(Color.WHITE);
        //d.setStroke(3, Color.RED);

        //txtNome.setBackground(d);


        this.addView(immagine);
        this.addView(txtNome);

    }

    public String getNome(){
        return nome;
    }

    public  void setNome(String nome){
        this.nome = nome;
        txtNome.setText(nome);
    }

    public void setImmagine(String img){
        if(!img.isEmpty()) {
            new ScaricaImmagine(immagine).execute(img, nome);
            this.img = img;
        }
    }

    public void setImmagineColor(Integer color){
        Log.d("colore", String.valueOf(color));
        this.setBackgroundColor(color);
    }


}





