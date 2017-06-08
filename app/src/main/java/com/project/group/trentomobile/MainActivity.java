package com.project.group.trentomobile;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Util.ScaricaTiles;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.getBackground().setAlpha(0);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        final Button btnMenu = (Button) findViewById(R.id.btnMenu);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.START);
            }
        });


        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);
        lm.setOrientation(LinearLayout.VERTICAL);

        // create the layout params that will be used to define how your
        // button will be displayed
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        params.setMargins(10, 10, 10, 10);


        LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p2.setMargins(10, 10, 10, 10);


        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(3, Color.GRAY);
        drawable.setCornerRadius(8);
        drawable.setColor(Color.WHITE);


        /*
        for (int i = 0; i < 8; i++) {
            TileLayout tl = new TileLayout(this, "Trovato gatto a Zambana", "Alla fine ho trovato un gatto incima alla mia casa con del pelo molto bello che non mi dispiace anche perchè è bello morbido al tatto.", "http://www.solegemello.net/gatto.jpg", "Coriere di Postal - 18/04/2017");
            lm.addView(tl);

        }
        */

        Preferenze p = new Preferenze();
        //INIZZIALIZZAZIONE TILES
        new ScaricaTiles(this).execute(p);
        /*TileMemoryRep tiles = TileMemoryRep.getInstance();

        tiles.addNotizia(new Notizia("Trovato gatto a Zambana", "Alla fine ho trovato un gatto incima alla mia casa con del pelo molto bello che non mi dispiace anche perchè è bello morbido al tatto.", "http://www.solegemello.net/gatto.jpg", "Coriere di Postal - 18/04/2017"));
            Indirizzo ind = new Indirizzo(1.f, 1.f, "Piazza Dante");
        tiles.addLuogo(new Luogo(ind, new Genere_Luogo("monumento"), "Monumento di dante", "sdjilhcu hudshad uashdjh asjkdhsj hdjsf hfjkhsd jkfhjksd hjksd hjksdfhjk hadjksh jksdh jkhdk fhjksdf","http://www.solegemello.net/gatto.jpg","URL"));


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        for(Tile t : tiles.getLuoghi()) {
            //TileLayout tl = new TileLayout(this, "Trovato gatto a Zambana", "Alla fine ho trovato un gatto incima alla mia casa con del pelo molto bello che non mi dispiace anche perchè è bello morbido al tatto.", "http://www.solegemello.net/gatto.jpg", "Coriere di Postal - 18/04/2017");
            //lm.addView(tl);
            fragmentTransaction.add(R.id.linearMain, TileFragment.newInstance(t));

        }

        fragmentTransaction.commit();
        */




    }








    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_luoghi) {

            Intent myIntent = new Intent(MainActivity.this, TipoActivity.class);
            myIntent.putExtra("tipo", "luogo"); //Optional parameters
            MainActivity.this.startActivity(myIntent);

        }else if (id == R.id.nav_preferenze) {
            Intent myIntent = new Intent(MainActivity.this, MyPreferenceActivity.class);
            myIntent.putExtra("key", 1); //Optional parameters
            MainActivity.this.startActivity(myIntent);

        }

        else if (id == R.id.nav_eventi) {
            Intent myIntent = new Intent(MainActivity.this, TipoActivity.class);
            myIntent.putExtra("tipo", "evento");  //Optional parameters
            MainActivity.this.startActivity(myIntent);

        } else if (id == R.id.nav_notizzie) {
            Intent myIntent = new Intent(MainActivity.this, TipoActivity.class);
            myIntent.putExtra("tipo", "notizia");  //Optional parameters
            MainActivity.this.startActivity(myIntent);
        } /*else if (id == R.id.nav_manage) {


        } else if (id == R.id.nav_share) {



        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





}


