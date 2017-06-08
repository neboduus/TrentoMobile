package com.project.group.trentomobile;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Classi.Tile;
import com.project.group.trentomobile.Repository.TileMemoryRep;
import com.project.group.trentomobile.TilePK.TileFragment;

import java.util.HashMap;
import java.util.Map;

public class CategorieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Bundle bundle = getIntent().getExtras();
        String categoria =  (String) bundle.getSerializable("categoria");
        String tipo =  (String) bundle.getSerializable("tipo");

        TileMemoryRep tiles = TileMemoryRep.getInstance();
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

        tiles.Filtra(p);


        for(Tile t : tiles.getTiles()){
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
