package com.project.group.trentomobile;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.project.group.trentomobile.Classi.Genere_Evento;
import com.project.group.trentomobile.Classi.Genere_Luogo;
import com.project.group.trentomobile.Classi.Genere_Notizia;
import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Classi.Tile;
import com.project.group.trentomobile.Classi.UpdateRequest;
import com.project.group.trentomobile.Repository.GeneriRepo;
import com.project.group.trentomobile.Repository.TileMemoryRep;
import com.project.group.trentomobile.TilePK.TileFragment;
import com.project.group.trentomobile.Util.CoordinateToMetri;
import com.project.group.trentomobile.Util.GetMyPosition;
import com.project.group.trentomobile.Util.InternalStorage;
import com.project.group.trentomobile.Util.MyLocationListener;
import com.project.group.trentomobile.Util.SaveLoadImg;
import com.project.group.trentomobile.Util.ScaricaTiles;

import java.io.IOException;

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


        Log.d("distanza prova---------------->", String.valueOf(CoordinateToMetri.disgeod(46.067197, 11.121376, 46.068518, 11.120078)));

        final Button btnMenu = (Button) findViewById(R.id.btnMenu);
        //it draws the sliding menu
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // tie together the functionality of DrawerLayout and the framework ActionBar
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // standard navigation menu
        // typically placed inside a DrawerLayout
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //Set a listener that will be notified when a menu item is selected.
        navigationView.setNavigationItemSelectedListener(this);

        /** Push object to x-axis position at the start of its container, not changing its size. */
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.START);
            }
        });

        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);
        lm.setOrientation(LinearLayout.VERTICAL);

        // create the layout params that will be used to define how your
        // button will be displayed (input params: WIDTH & HEIGHT)
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p2.setMargins(10, 10, 10, 10);

        //creates a drawable element to draw on the screen
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


        findViewById(R.id.btnCerca).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, CercaActivity.class);
                myIntent.putExtra("cerca",((AutoCompleteTextView)findViewById(R.id.txtCerca)).getText().toString());//Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });



    }


    @Override
    protected void onRestart() {
        super.onRestart();

        if(UpdateRequest.getInstance().isRequestUpadte()) {
            //recover all the content of the main activity which is all the Tiles
            TileMemoryRep tiles = TileMemoryRep.getInstance();
            //recover the fragment manager and remove all the fragments which are the Tiles
            FragmentTransaction ft = getFragmentManager().beginTransaction(); //had to begin a fragmentTransaction
            for (Tile t : TileMemoryRep.getInstance().getTiles()) {
                ft.remove(getFragmentManager().findFragmentByTag(String.valueOf(t.getId())));
            }
            ft.commit();    //commit changes of the MainActivity

            //recovery of possible new preferences
            Preferenze myPreference = null;
            try {
                //InternalStorage.writeObject(this,"lolk","lolv");
                myPreference = (Preferenze) InternalStorage.readObject(this);
            } catch (IOException e) {
                //manage the possibility of having not setted the preferences
                //create default preferences
                myPreference = new Preferenze();
                for (Genere_Luogo g : GeneriRepo.getIstance().GeneriLuoghi) {
                    myPreference.addPref_Luoghi_Ture(g.getTipo());
                }
                for (Genere_Notizia g : GeneriRepo.getIstance().GeneriNotizie) {
                    myPreference.addPref_Notizie_Ture(g.getTipo());
                }
                for (Genere_Evento g : GeneriRepo.getIstance().GeneriEventi) {
                    myPreference.addPref_Eventi_Ture(g.getTipo());
                }

                try {
                    InternalStorage.writeObject(this, myPreference);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            GetMyPosition myPosition = GetMyPosition.getIstanceAndUpdate(this);
            myPreference.setMylat(myPosition.lat);
            myPreference.setMyLng(myPosition.lng);

            //filter the content with respect to Preferences
            tiles.Filtra(myPreference);
            FragmentTransaction fragmentTransaction;
            FragmentManager fragmentManager = getFragmentManager();
            //   fragmentTransaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);
            //show the new content
            for (Tile t : tiles.getTiles()) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.linearMain, TileFragment.newInstance(t), String.valueOf(t.getId()));
                fragmentTransaction.commit();
            }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    //handling event ItemSelected on navigation items
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
        }else if (id == R.id.nav_eventi) {
            Intent myIntent = new Intent(MainActivity.this, TipoActivity.class);
            myIntent.putExtra("tipo", "evento");  //Optional parameters
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_notizzie) {
            Intent myIntent = new Intent(MainActivity.this, TipoActivity.class);
            myIntent.putExtra("tipo", "notizia");  //Optional parameters
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_trasporti) {
            Intent myIntent = new Intent(MainActivity.this, TipoActivity.class);
            myIntent.putExtra("tipo", "trasporti");  //Optional parameters
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_preferiti) {
            Intent myIntent = new Intent(MainActivity.this, PreferitiActivity.class);
            MainActivity.this.startActivity(myIntent);
        }else if (id == R.id.nav_proponi) {

            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"sptanco@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Proposal for tile");
            i.putExtra(Intent.EXTRA_TEXT   , "Good morning, I have a proposal for a tile:\n");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("destroy","cavolacci");

        for(Tile t : TileMemoryRep.getInstance().getTiles()) {
            SaveLoadImg.getIstance().deleatImageFromStorage("tileid" + t.getId());
            Log.d("fineeeee cancella", "tileid" + t.getId());
        }

    }
}


