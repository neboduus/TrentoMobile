package com.project.group.trentomobile.TilePK;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;

/**
 * Created by postal on 13/04/17.
 */

public class TileLayout extends LinearLayout {


    private LinearLayout.LayoutParams params;
    public PiediLayout piediLayout;
    public CorpoLayout corpoLayout;
    public IntestazioneLayout intestazioneLayout;


    public TileLayout(Context context, String intestazione, String corpo, String urlFoto, String piedi) {

        super(context);

        this.setOrientation(LinearLayout.VERTICAL);

        params = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(20,20,20,60);

        this.setLayoutParams(params);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(2, Color.GRAY);
        drawable.setColor(Color.WHITE);
        drawable.setCornerRadius(10);
        this.setBackgroundDrawable(drawable);

        this.setPadding(50,50,50,50);



        intestazioneLayout = new IntestazioneLayout(context, intestazione);
        corpoLayout = new CorpoLayout(context, corpo, urlFoto);
        piediLayout = new PiediLayout(context, piedi);





        this.addView(intestazioneLayout);
        this.addView(corpoLayout);
        this.addView(piediLayout);




    }



}
