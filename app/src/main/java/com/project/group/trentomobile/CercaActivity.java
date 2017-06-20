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

import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Classi.Tile;
import com.project.group.trentomobile.Repository.TileMemoryRep;
import com.project.group.trentomobile.TilePK.TileFragment;
import com.project.group.trentomobile.Util.InternalStorage;

import java.io.IOException;
import java.util.ArrayList;


public class CercaActivity extends AppCompatActivity {

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


        tiles = new ArrayList<>();


        Bundle bundle = getIntent().getExtras();
        String cerca =  (String) bundle.getString("cerca");


        for(Tile tile : TileMemoryRep.getInstance().getTiles()){
            //Cerca nel titolo e in base alla categoria
            if(tile.getTitolo().contains(cerca))
            {
                tiles.add(tile);
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
