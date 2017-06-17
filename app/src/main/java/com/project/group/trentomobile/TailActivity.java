package com.project.group.trentomobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.project.group.trentomobile.Classi.*;
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

public class TailActivity extends FragmentActivity implements OnMapReadyCallback{

    private TextToSpeech t1;
    private TextView titolo;
    private TextView descrizione;
    private ImageView immagine;
    private TextView URL;
    private GoogleMap mMap;
    private Button btn_speacher;
    Tile data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tail);

        titolo = (TextView) findViewById(R.id.txtTitolo);
        descrizione = (TextView) findViewById(R.id.txtDescrizione);
        immagine = (ImageView) findViewById(R.id.imgT);
        URL = (TextView) findViewById(R.id.txtURL);
        btn_speacher=(Button)findViewById(R.id.btn_speacher);
        Bundle bundle = getIntent().getExtras();
        data = (Tile) bundle.getSerializable("data");

        titolo.setText(data.getTitolo());
        descrizione.setText(data.getDescrizione());
        URL.setText("SITO WEB");
        final String stringURL = data.getURL();


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


        DateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");


        new ScaricaImmagine((ImageView) immagine).execute(data.getPatterImmagine(),"tileid"+data.getId());

        if(data instanceof Luogo){
            ((TextView) findViewById(R.id.txtIndirizzo)).setText("Indirizzo: "+((Luogo)data).getIndirizzo().getVia());
            ((TextView) findViewById(R.id.txtGenere)).setText("Genere Luogo: "+((Luogo) data).getGenere().getTipo());
        }

        if(data instanceof Evento){
            ((TextView) findViewById(R.id.txtIndirizzo)).setText("Indirizzo: "+((Evento)data).getIndirizzo().getVia());
            ((TextView) findViewById(R.id.txtData)).setText("Data: "+formatter.format(((Evento)data).getData().getTimeInMillis()));
            ((TextView) findViewById(R.id.txtGenere)).setText("Genere Evento: "+((Evento) data).getGenere().getTipo());
        }

        if(data instanceof Notizia){
            ((TextView) findViewById(R.id.txtGenere)).setText("Genere Notizzia: "+((Notizia) data).getGenere().getTipo());
            ((TextView) findViewById(R.id.txtData)).setText("Data: "+formatter.format(((Notizia)data).getData().getTimeInMillis()));
            ((TextView) findViewById(R.id.txtAutore)).setText("Autore: "+((Notizia) data).getAutore().getNome());
        }

        if (data instanceof Fermata){
            ((TextView) findViewById(R.id.txtGenere)).setText(" "+data.getTitolo());
        }


        ((TextView) findViewById(R.id.txtGenere)).setText("lllllllll"+data.peso);

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
        }

        // BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tile, menu);
        return true;
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

}