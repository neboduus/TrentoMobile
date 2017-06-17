package com.project.group.trentomobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.project.group.trentomobile.CategoriePK.Categoria;
import com.project.group.trentomobile.CategoriePK.CategoriaAdapter;
import com.project.group.trentomobile.Classi.Genere;
import com.project.group.trentomobile.Repository.GeneriRepo;

import java.util.ArrayList;

public class TipoActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private String tipo;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo);

        mTextMessage = (TextView) findViewById(R.id.message);

        Bundle bundle = getIntent().getExtras();
        tipo =  (String) bundle.getSerializable("tipo");

        GridView gridView = (GridView)findViewById(R.id.grigliaTipi);

        ArrayList<? extends Genere> generi = null;

        if(tipo.equals("notizia")){
            generi = GeneriRepo.getIstance().GeneriNotizie;
            setTitle("Notizie");
        }
        if(tipo.equals("evento")){
            generi = GeneriRepo.getIstance().GeneriEventi;
            setTitle("Eventi");
        }
        if(tipo.equals("luogo")){
            generi = GeneriRepo.getIstance().GeneriLuoghi;
            setTitle("Luoghi");
        }
        if(tipo.equals("trasporti")){
            generi = GeneriRepo.getIstance().Autobus;
            setTitle("Trasporti");
        }


        CategoriaAdapter booksAdapter = new CategoriaAdapter(this, generi);
        gridView.setAdapter(booksAdapter);

        final Activity mActivity = this;

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent myIntent = new Intent(mActivity, CategorieActivity.class);
                myIntent.putExtra("categoria", ((Categoria)v).getNome()); //Optional parameters
                myIntent.putExtra("tipo", tipo); //Optional parameters
                myIntent.putExtra("img", ((Categoria)v).img);
                mActivity.startActivity(myIntent);


            }
        });



    }

}
