package com.project.group.trentomobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.group.trentomobile.Classi.Luogo;
import com.project.group.trentomobile.Classi.Tile;
import com.project.group.trentomobile.Repository.TileMemoryRep;

import java.util.ArrayList;

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
            if(t instanceof Luogo) {
                LatLng Trento = new LatLng(((Luogo) t).getIndirizzo().getLat(), ((Luogo) t).getIndirizzo().getLng());
                Marker m = mMap.addMarker(new MarkerOptions().position(Trento).title(t.getTitolo()));
                m.setTag(t.getId());
            }
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(46.067197, 11.121376), 14));

        mMap.setOnInfoWindowClickListener(this);

    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        Tile t = TileMemoryRep.getInstance().getTileById((Integer) marker.getTag());

        Intent myIntent = new Intent(this, TailActivity.class);

        //the name of optional parameters must include a package prefix (Ex. 'com.project.group.trentomobile.TilePK.TileData')
        myIntent.putExtra("data", t); //Optional parameters
        this.startActivity(myIntent);
    }
}
