package com.project.group.trentomobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.group.trentomobile.Classi.Fermata;
import com.project.group.trentomobile.Classi.Indirizzabile;
import com.project.group.trentomobile.Classi.Indirizzo;
import com.project.group.trentomobile.Classi.Luogo;
import com.project.group.trentomobile.Classi.Tile;
import com.project.group.trentomobile.Repository.TileMemoryRep;
import com.project.group.trentomobile.assetsHelper.SQLAssetHelper_DB;
import com.project.group.trentomobile.context.MyApplication;
import com.project.group.trentomobile.transport.Linea;
import com.project.group.trentomobile.transport.Orario;
import com.project.group.trentomobile.transport.Stop;
import com.project.group.trentomobile.transport.Trip;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private ArrayList<Tile> tiles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Bundle bundle = getIntent().getExtras();
        tiles = (ArrayList<Tile>) bundle.getSerializable("tiles");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        for(Tile t : tiles) {
            if(t instanceof Indirizzabile) {
                LatLng Trento = new LatLng(((Indirizzabile) t).getIndirizzo().getLat(), ((Indirizzabile) t).getIndirizzo().getLng());
                Marker m = mMap.addMarker(new MarkerOptions().position(Trento).title(t.getTitolo()));
                Log.d("iddddd", String.valueOf(t.getId()));
                if(t instanceof Fermata)
                    m.setTag(t.getId());
                else
                    m.setTag(t.getId());
            }
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(46.067197, 11.121376), 14));

        mMap.setOnInfoWindowClickListener(this);

    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        int tag = (Integer) marker.getTag();
        Tile t = null;
        if(tag>=0)
             t = TileMemoryRep.getInstance().getTileById(tag);
        else{
            SQLAssetHelper_DB sqlDB = new SQLAssetHelper_DB(MyApplication.getAppContext());
            Stop s = sqlDB.getStopsById(tag*-1);

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


            t = new Fermata(s.getId(),s.getName(),corpo,"http://www.homemade-preschool.com/images/school-bus-racing-front.png","http://images.clipartpanda.com/clipart-bus-17816-simple-bus-clip-art.png",indirizzo);


        }

        Intent myIntent = new Intent(this, TailActivity.class);

        //the name of optional parameters must include a package prefix (Ex. 'com.project.group.trentomobile.TilePK.TileData')
        myIntent.putExtra("data", t); //Optional parameters
        this.startActivity(myIntent);
    }
}
