package com.project.group.trentomobile;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.project.group.trentomobile.Classi.Fermata;
import com.project.group.trentomobile.Classi.Indirizzo;
import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Classi.Tile;
import com.project.group.trentomobile.Repository.TileMemoryRep;
import com.project.group.trentomobile.TilePK.TileFragment;
import com.project.group.trentomobile.Util.InternalStorage;
import com.project.group.trentomobile.assetsHelper.SQLAssetHelper_DB;
import com.project.group.trentomobile.context.MyApplication;
import com.project.group.trentomobile.transport.Linea;
import com.project.group.trentomobile.transport.Orario;
import com.project.group.trentomobile.transport.Stop;
import com.project.group.trentomobile.transport.Trip;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PreferitiActivity extends AppCompatActivity {

    ArrayList<Tile> tiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Activity mActivity = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(mActivity, MapActivity.class);
                myIntent.putExtra("tiles", tiles); //Optional parameters
                mActivity.startActivity(myIntent);
            }
        });


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Preferenze myPreference = null;
        try {
            myPreference = (Preferenze) InternalStorage.readObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tiles = new ArrayList<>();

        for(int idP : myPreference.getIdsPreferiti()){

            if(idP>-1) {
                Log.d("id preferito", String.valueOf(idP));
                Tile t = TileMemoryRep.getInstance().getTileById(idP);
                tiles.add(t);
            }else{

                SQLAssetHelper_DB sqlDB = new SQLAssetHelper_DB(MyApplication.getAppContext());
                Stop s = sqlDB.getStopsById(idP*-1);

                List<Orario> lo = sqlDB.getNearestOrarioFromStop(s,new SimpleDateFormat("HH:mm:ss").format(new Date()));
                String corpo="";
                Indirizzo indirizzo = new Indirizzo(s.getLat(), s.getLon(), null);

                int count = 1;

                for(Orario o : lo){
                    o.getArrival_time();
                    Trip tr = sqlDB.getTripById(o.getTrip_id());
                    Linea l = sqlDB.getLineaById(tr.getRoute_id());

                    corpo+="Bus:"+l.getShort_name()+"\n\tpartenza:"+o.getArrival_time()+"\n\t"+"direzione:"+(tr.getDirection_id() ? "andata" :"ritorno")+"\n";

                    if(count==0) break;
                    count--;
                }

                Fermata f = new Fermata(s.getId(),s.getName(),corpo,"https://png.icons8.com/bus/color/50","https://png.icons8.com/bus/color/50",indirizzo);
                tiles.add(f);

            }
        }


        for(Tile t : tiles){
            fragmentTransaction.add(R.id.linearMain, TileFragment.newInstance(t));
        }
        fragmentTransaction.commit();



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
