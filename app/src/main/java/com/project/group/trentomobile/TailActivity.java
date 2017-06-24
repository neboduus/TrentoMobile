package com.project.group.trentomobile;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.project.group.trentomobile.Classi.*;
import com.project.group.trentomobile.Repository.TileMemoryRep;
import com.project.group.trentomobile.Util.InternalStorage;
import com.project.group.trentomobile.Util.ScaricaImmagine;
import com.project.group.trentomobile.context.MyApplication;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TailActivity extends AppCompatActivity implements OnMapReadyCallback{

    private TextToSpeech t1;
    private TextView titolo;
    private TextView descrizione;
    private ImageView immagine;
    private TextView URL;
    private GoogleMap mMap;
    private Button btn_speacher;
    Tile data;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titolo = (TextView) findViewById(R.id.txtTitolo);
        descrizione = (TextView) findViewById(R.id.txtDescrizione);
        immagine = (ImageView) findViewById(R.id.imgT);
        URL = (TextView) findViewById(R.id.txtURL);
        btn_speacher=(Button)findViewById(R.id.btn_speacher);
        Bundle bundle = getIntent().getExtras();
        data = (Tile) bundle.getSerializable("data");

        titolo.setText(data.getTitolo());
        descrizione.setText(data.getShortDescription());

        if(data.getURL() != null)
            URL.setText("SITO WEB");
        else
            ((ViewGroup) URL.getParent()).removeView(URL);

        final String stringURL = data.getURL();


        LinearLayout main = (LinearLayout) findViewById(R.id.mainLinearTile);


        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ITALIAN);
                }
            }
        });

        btn_speacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = descrizione.getText().toString();

                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        URL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(stringURL));
                startActivity(browserIntent);
                return false;
            }
        });

        findViewById(R.id.btn_extend).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                descrizione.setText(data.getDescrizione());
                return false;
            }
        });


        DateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");

        TextView datatxt =  ((TextView) findViewById(R.id.txtData));
        TextView autoretxt = ((TextView) findViewById(R.id.txtAutore));
        TextView insirizzotxt = ((TextView) findViewById(R.id.txtIndirizzo));
        TextView generetxt =  ((TextView) findViewById(R.id.txtGenere));


        new ScaricaImmagine((ImageView) immagine).execute(data.getPatterImmagine(),"tileid"+data.getId());

        if(data instanceof Luogo){
            insirizzotxt.setText("Indirizzo: "+((Luogo)data).getIndirizzo().getVia());
            generetxt.setText("Genere Luogo: "+((Luogo) data).getGenere().getTipo());

            ((ViewGroup) datatxt.getParent()).removeView(datatxt);

            ((ViewGroup) autoretxt.getParent()).removeView(autoretxt);
        }

        if(data instanceof Evento){
            insirizzotxt.setText("Indirizzo: "+((Evento)data).getIndirizzo().getVia());
            datatxt.setText("Data: "+formatter.format(((Evento)data).getData().getTimeInMillis()));
            generetxt.setText("Genere Evento: "+((Evento) data).getGenere().getTipo());

            ((ViewGroup) autoretxt.getParent()).removeView(autoretxt);
        }

        if(data instanceof Notizia){
            generetxt.setText("Genere Notizzia: "+((Notizia) data).getGenere().getTipo());
            datatxt.setText("Data: "+formatter.format(((Notizia)data).getData().getTimeInMillis()));
            autoretxt.setText("Autore: "+((Notizia) data).getAutore().getNome());

            ((ViewGroup) insirizzotxt.getParent()).removeView(insirizzotxt);
        }

        if (data instanceof Fermata){
            generetxt.setText("Genere: Fermata");

            ((ViewGroup) insirizzotxt.getParent()).removeView(insirizzotxt);
            ((ViewGroup) autoretxt.getParent()).removeView(autoretxt);
            ((ViewGroup) datatxt.getParent()).removeView(datatxt);
        }


        //AGGIORNA PREFERENZE

        Preferenze myPreference = null;
        try {
            myPreference = (Preferenze) InternalStorage.readObject(MyApplication.getAppContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(data instanceof Notizia){
            Genere_Notizia gn = ((Notizia) data).getGenere();
            myPreference.getPref_Notizie().put(gn.getTipo(),myPreference.getPref_Notizie().get(gn.getTipo())+1);
        }else
        if(data instanceof Luogo){
            Genere_Luogo gn = ((Luogo) data).getGenere();
            myPreference.getPref_Luoghi().put(gn.getTipo(),myPreference.getPref_Luoghi().get(gn.getTipo())+1);
        }else
        if(data instanceof Evento){
            Genere_Evento gn = ((Evento) data).getGenere();
            myPreference.getPref_Eventi().put(gn.getTipo(),myPreference.getPref_Eventi().get(gn.getTipo())+1);
        }

        try {
            InternalStorage.writeObject(MyApplication.getAppContext(),myPreference);

        } catch (IOException e) {
            e.printStackTrace();
        }


        if(data instanceof Indirizzabile) {
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }else{
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.map);

            getSupportFragmentManager().beginTransaction().hide(f).remove(f).commit();

        }

        // BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(data.getId(),1,0,"Condividi");
        menu.add(data.getId(),2,0,"Non mi interessa");
        menu.add(data.getId(),3,0,"Aggiungi ai preferiti");
        menu.add(data.getId(),4,0,"Invia feed-back");
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idd = item.getGroupId();
        Preferenze myPreference = null;

        //now we are modifying the preferences with respect on the item menu selected in the tile

        switch (item.getItemId()) {
            case 1: //CONDIVIDI

                Tile t = TileMemoryRep.getInstance().getTileById(idd);

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, t.getTitolo()+"---"+t.getDescrizione()+"---"+t.getPatterImmagine());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;

            case 2: //NON MI INTERESSA

                try {
                    myPreference = (Preferenze) InternalStorage.readObject(MyApplication.getAppContext());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                Tile d = TileMemoryRep.getInstance().getTileById(idd);

                Log.d("ttitolooooo->", d.getTitolo());

                if(d instanceof Notizia){
                    Genere_Notizia gn = ((Notizia) d).getGenere();
                    myPreference.getPref_Notizie().put(gn.getTipo(),0);
                }else
                if(d instanceof Luogo){
                    Genere_Luogo gn = ((Luogo) d).getGenere();
                    myPreference.getPref_Luoghi().put(gn.getTipo(),0);
                }else
                if(d instanceof Evento){
                    Genere_Evento gn = ((Evento) d).getGenere();
                    myPreference.getPref_Eventi().put(gn.getTipo(),0);
                }else{
                    Log.d("ERRORACCIO","ritorna la superclasse");
                }

                try {
                    InternalStorage.writeObject(MyApplication.getAppContext(),myPreference);

                } catch (IOException e) {
                    e.printStackTrace();
                }


                Log.d("tagid->", String.valueOf(data.getId())+ "    "+data.getTitolo()+ "     "+ idd);

                FragmentTransaction ft = this.getFragmentManager().beginTransaction();

                ft.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);

                ft.hide(getFragmentManager().findFragmentByTag(String.valueOf(idd)))
                        .addToBackStack(null)
                        .commit();


                return true;

            case 3 : //AGGIUNGI A PREFERITI

                myPreference = null;
                try {
                    myPreference = (Preferenze) InternalStorage.readObject(MyApplication.getAppContext());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                myPreference.addPreferiti(idd);

                for(int i:myPreference.getIdsPreferiti()){
                    Log.d("pref", String.valueOf(i));
                }



                try {
                    InternalStorage.writeObject(MyApplication.getAppContext(),myPreference);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return true;

            case 4: //FEEDBACK

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"sptanco@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Feed-back for tile with id:"+ idd);
                i.putExtra(Intent.EXTRA_TEXT   , "Good morning, this is my feed-back about the tile whith id:" + idd +".\n");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MyApplication.getAppContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(data instanceof Indirizzabile) {
            mMap = googleMap;
            // Add a marker in Sydney and move the camera
            LatLng Trento = new LatLng(((Indirizzabile)data).getIndirizzo().getLat(), ((Indirizzabile)data).getIndirizzo().getLng());
            mMap.addMarker(new MarkerOptions().position(Trento).title(data.getTitolo()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(((Indirizzabile)data).getIndirizzo().getLat(), ((Indirizzabile)data).getIndirizzo().getLng()), 17));

        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}