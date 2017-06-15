package com.project.group.trentomobile;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.project.group.trentomobile.CategoriePK.Categoria;
import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Classi.Tile;
import com.project.group.trentomobile.Repository.TileMemoryRep;
import com.project.group.trentomobile.TilePK.TileFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class CategorieActivity extends AppCompatActivity {

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


        Bundle bundle = getIntent().getExtras();
        String categoria =  (String) bundle.getSerializable("categoria");
        String tipo =  (String) bundle.getSerializable("tipo");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Preferenze p = new Preferenze();
        Map<String,Integer> m =new HashMap<>();

        if(tipo.equals("notizia")){
            m.put(categoria,10);
            p.setPref_Notizie(m);
        }
        if(tipo.equals("evento")){
            m.put(categoria,10);
            p.setPref_Eventi(m);
        }
        if(tipo.equals("luogo")){
            m.put(categoria,10);
            p.setPref_Luoghi(m);
        }

        tiles = TileMemoryRep.getInstance().getTilesFiltrati(p);


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
