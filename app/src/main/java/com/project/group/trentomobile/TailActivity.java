package com.project.group.trentomobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.group.trentomobile.Classi.*;
import com.project.group.trentomobile.Util.ScaricaImmagine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TailActivity extends AppCompatActivity {

    private TextView titolo;
    private TextView descrizione;
    private ImageView immagine;
    private TextView URL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tail);
        


        titolo = (TextView) findViewById(R.id.txtTitolo);
        descrizione = (TextView) findViewById(R.id.txtDescrizione);
        immagine = (ImageView) findViewById(R.id.imgT);
        URL = (TextView) findViewById(R.id.txtURL);

        Bundle bundle = getIntent().getExtras();
        Tile data = (Tile) bundle.getSerializable("data");

        titolo.setText(data.getTitolo());
        descrizione.setText(data.getDescrizione());
        URL.setText("SITO WEB");
        final String stringURL = data.getURL();

        URL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(stringURL));
                startActivity(browserIntent);
                return false;
            }
        });


        DateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");


        new ScaricaImmagine((ImageView) immagine).execute(data.getPatterImmagine());

        if(data instanceof Luogo){
            ((TextView) findViewById(R.id.txtIndirizzo)).setText("Indirizzo: "+((Luogo)data).getIndirizzo().getVia());
            ((TextView) findViewById(R.id.txtGenere)).setText("Genere Luogo: "+((Luogo) data).getGenere().getTipo());
        }

        if(data instanceof Evento){
            ((TextView) findViewById(R.id.txtIndirizzo)).setText("Indirizzo: "+((Evento)data).getIndirizzo().getVia());
            ((TextView) findViewById(R.id.txtData)).setText("Data: "+((Evento)data).getData().toString());
            ((TextView) findViewById(R.id.txtGenere)).setText("Genere Evento: "+((Evento) data).getGenere().getTipo());
        }

        if(data instanceof Notizia){
            ((TextView) findViewById(R.id.txtGenere)).setText("Genere Notizzia: "+((Notizia) data).getGenere().getTipo());
            ((TextView) findViewById(R.id.txtData)).setText("Data: "+formatter.format(((Notizia)data).getData().getTimeInMillis()));
            ((TextView) findViewById(R.id.txtAutore)).setText("Autore: "+((Notizia) data).getAutore().getNome());
        }




       // BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tile, menu);
        return true;
    }


}
