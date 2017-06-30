package com.project.group.trentomobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Switch;

import com.project.group.trentomobile.Classi.MySettings;
import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Util.InternalStorage;

import java.io.IOException;

public class SettingPreference extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_preference);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Activity mActivity= this;

        findViewById(R.id.btn_carica_preferenze).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String occupazione = String.valueOf(((Spinner) findViewById(R.id.spinner_occupazioni)).getSelectedItem());
                String stringEta = String.valueOf(((Spinner) findViewById(R.id.spinner_eta)).getSelectedItem());

                Integer eta = 25;
                if (stringEta.equals("0-18 anni")) {
                    eta = 16;
                } else if (stringEta.equals("18-30 anni")) {
                    eta = 20;
                } else if (stringEta.equals("30-100 anni")) {
                    eta = 50;
                }

                MySettings settings = new MySettings(eta, occupazione);

                Preferenze p = settings.getMyPreference();


                try {
                    InternalStorage.writeObject(mActivity, p);
                    Log.d("finito","preferenze settate");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Intent myIntent = new Intent(mActivity, MainActivity.class);
                mActivity.startActivity(myIntent);
            }
        });

    }

}
