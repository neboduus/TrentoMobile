package com.trentomobile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.trentomobile.assetsHelper.SQLAssetHelper_DB;
import com.trentomobile.context.MyApplication;
import com.trentomobile.transport.Linea;
import com.trentomobile.transport.Orario;
import com.trentomobile.transport.Stop;
import com.trentomobile.transport.Trip;
import com.visuality.f32.temperature.TemperatureUnit;
import com.visuality.f32.weather.data.entity.Weather;
import com.visuality.f32.weather.manager.WeatherManager;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication context = new MyApplication();
        SQLAssetHelper_DB dbHelper = new SQLAssetHelper_DB(MyApplication.getAppContext());

        List<Stop> nearestStops = dbHelper.getNearestStops(5, 46.1421242f,11.1006433f);
        Stop nearestStop = nearestStops.get(4);

        List<Orario> orariNearest = dbHelper.getNearestOrarioFromStop(nearestStop, "16:37:00");
        Orario nearestOrario = orariNearest.get(0);

        Trip trip = dbHelper.getTripById(nearestOrario.getTrip_id());

        Linea linea = dbHelper.getLineaById(trip.getRoute_id());

        String msg = "Stazione: "+nearestStop.getName()+ "\n"+
                "Ora partenza prossimo Bus: "+nearestOrario.getDeparture_time()+ "\n"+
                "Headsign corsa: "+trip.getTrip_headsign() + "\n"+
                "Sigla Bus: "+linea.getShort_name()+ "\n"+
                "Nome della Corsa: "+linea.getLong_name()+ "\n"+
                "Direzione: "+(trip.getDirection_id()==true ? "Andata" : "Ritorno");
        Log.d("NEAREST BUS STATION:", msg);

        List<Linea> allLinee = dbHelper.getAllLinee();
        for (Linea l: allLinee){
            Log.d("Tutti i BUS:", l.getShort_name()+" - "+l.getLong_name());
        }


        Integer fermata = allLinee.size()-5;
        //prendo il bus 17
        Linea _17 = allLinee.get(fermata);

        List<List<Stop>> stops = dbHelper.getStopsByLineaId(_17.getId());
        List<Stop> stops_andata = stops.get(0);
        List<Stop> stops_ritorno = stops.get(1);


        Log.d("SEPARATOR", "-----FERMATE--ANDATA-----------"+_17.getShort_name()+" - "+_17.getLong_name()+"--------id="+_17.getId()+"------------------------");

        for (Stop s: stops_andata){
            Log.d("Stops", s.getName());
        }

        Log.d("SEPARATOR", "-----FERMATE--RITORNO-----------"+_17.getShort_name()+" - "+_17.getLong_name()+"--------id="+_17.getId()+"------------------------");

        for (Stop s: stops_ritorno){
            Log.d("Stops", s.getName());
        }

        //fermate Lavis Stazione FTM
        Stop stop_lavis_FTM_andata = stops_andata.get(stops_andata.size()-2);

        Log.d("SEPARATOR", "--------ORARI----------"+_17.getShort_name()+" - "+_17.getLong_name()+"--------id="+_17.getId()+"------------------------");
        List<Orario> orari = dbHelper.getOrariByStopLinea(stop_lavis_FTM_andata.getId(), _17.getId(), stop_lavis_FTM_andata.getDirection_id() /*direzione*/);


        //orari
        for (Orario o: orari){
            Log.d("Orario", "arrivo: "+o.getArrival_time()+" - partenza: "+o.getDeparture_time());
        }

        new WeatherManager("97afef6a27b88c8138c824865619ff56").getCurrentWeatherByCoordinates(
                46.1421242, // latitude
                11.1006433, // longitude
                new WeatherManager.CurrentWeatherHandler() {
                    @Override
                    public void onReceivedCurrentWeather(WeatherManager manager, Weather weather) {
                        // Handle current weather information
                        Log.d("METEO", weather.getNavigation().getLocationName()+" " + weather.getTemperature().getCurrent()
                                .getValue(TemperatureUnit.CELCIUS)+" gradi C");
                        Log.d("METEO", "Percentuale Nuvole: "+weather.getCloudiness().getPercentage()+"%");
                        Log.d("METEO", "Pioggia nelle ultime 3 h: "+weather.getRain().getThreeHoursVolume());
                        Log.d("METEO", "Velocità del vento: "+weather.getWind().getSpeed());
                        Log.d("METEO", "Direzione del vento: "+weather.getWind().getDirection()+" gradi");

                    }

                    @Override
                    public void onFailedToReceiveCurrentWeather(WeatherManager manager) {
                        // Handle error
                    }
                }
        );


        ListView listStopsView = (ListView) findViewById(R.id.listStops);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
