package com.project.group.trentomobile;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.group.trentomobile.CategoriePK.Categoria;
import com.project.group.trentomobile.Classi.Bus;
import com.project.group.trentomobile.Classi.Fermata;
import com.project.group.trentomobile.Classi.Indirizzo;
import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Classi.Tile;
import com.project.group.trentomobile.Repository.GeneriRepo;
import com.project.group.trentomobile.Repository.TileMemoryRep;
import com.project.group.trentomobile.TilePK.TileFragment;
import com.project.group.trentomobile.Util.ScaricaImmagine;
import com.project.group.trentomobile.assetsHelper.SQLAssetHelper_DB;
import com.project.group.trentomobile.context.MyApplication;
import com.project.group.trentomobile.transport.Linea;
import com.project.group.trentomobile.transport.Orario;
import com.project.group.trentomobile.transport.Stop;
import com.project.group.trentomobile.transport.Trip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        setTitle(categoria);


        String img = (String) bundle.getSerializable("img");
        if(!img.isEmpty())
            new ScaricaImmagine((ImageView) findViewById(R.id.imageView)).execute(img, categoria);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Preferenze p = new Preferenze();
        Map<String,Integer> m =new HashMap<>();

        if(tipo.equals("notizia")){
            m.put(categoria,10);
            p.setPref_Notizie(m);
            fab.hide();
        }
        if(tipo.equals("evento")){
            m.put(categoria,10);
            p.setPref_Eventi(m);
        }
        if(tipo.equals("luogo")){
            m.put(categoria,10);
            p.setPref_Luoghi(m);
        }





        if(tipo.equals("trasporti")){


            new AsyncTask<String, Tile, ArrayList<Tile>>() {

                @Override
                protected ArrayList<Tile> doInBackground(String... params) {
                    tiles = new ArrayList<>();

                    SQLAssetHelper_DB sqlDB = new SQLAssetHelper_DB(MyApplication.getAppContext());

                    List<Stop> stops = null;
                    Bus selectedBus = null;

                    for(Bus b: GeneriRepo.getIstance().Autobus) {
                        Log.e ("aaa", b.getShort_name()+"AAAAAAAAAAAAAAAAAAAAAAAAAAAA"+b.getIdBus());
                        if(b.getShort_name().equals(params[0])) {
                            stops = sqlDB.getStopsByLineaId(b.getIdBus()).get(0);
                            selectedBus = b;
                            Log.e ("aaa", b.getShort_name()+"BBBBBBBBBBBBBBBBBBBBBBBB"+b.getIdBus()+" "+stops.size());
                        }
                    }


                    for(Stop s : stops){

                        List<Orario> lo = sqlDB.getOrariByStopLinea(s.getId(), selectedBus.getIdBus(), 0);
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

                        publishProgress(f);
                    }



                    return tiles;
                }

                protected void onProgressUpdate(Tile... progress) {
                    Log.d("update", progress[0].getTitolo());
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentTransaction.add(R.id.linearMain, TileFragment.newInstance(progress[0]));

                    fragmentTransaction.commit();
                }

                protected void onPostExecute(ArrayList<Tile> result) {

                }


            }.execute(categoria);


        }else {
            tiles = TileMemoryRep.getInstance().getTilesFiltrati(p);

            for(Tile t : tiles){
                fragmentTransaction.add(R.id.linearMain, TileFragment.newInstance(t));
            }
            fragmentTransaction.commit();
        }





    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }

    }

}
