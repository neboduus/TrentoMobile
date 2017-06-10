package com.project.group.trentomobile.assetsHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.project.group.trentomobile.transport.Linea;
import com.project.group.trentomobile.transport.Orario;
import com.project.group.trentomobile.transport.Stop;
import com.project.group.trentomobile.transport.Trip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neboduus on 27/05/2017.
 */

public class SQLAssetHelper_DB extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "trasportiTrento_new.db";
    private static final int DATABASE_VERSION = 1;

    public SQLAssetHelper_DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    public List<Orario> getOrariByStopLinea(Integer stop_id, Integer route_id, Integer direction){
        Log.d("getOrariByStopLinea", "getOrariByStopLinea( stop_id="+stop_id+" route_id="+route_id+" direction_id="+direction+" )");
        List<Orario> orari_andata = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String selectStatement = "select stop_times._id, stop_times.trip_id, stop_times.stop_id," +
                " stop_times.stop_sequence, stop_times.arrival_time, stop_times.departure_time"+
                " from stop_times, trips, routes " +
                " where stop_times.stop_id="+stop_id+
                " and stop_times.trip_id=trips.trip_id " +
                " and trips.route_id=routes._id " +
                " and routes._id="+route_id+
                " and trips.direction_id="+direction+
                " group by stop_times._id " +
                " order by stop_times.departure_time asc";
        Cursor cursor = db.rawQuery(selectStatement, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Integer id = cursor.getInt(0);
            Double trip__id = cursor.getDouble(1);
            Integer stop__id = cursor.getInt(2);
            Integer stop_sequence = cursor.getInt(3);
            String arrival_time = cursor.getString(4);
            String departure_time = cursor.getString(5);
            Orario orario = new Orario(id, trip__id, stop__id, stop_sequence, arrival_time, departure_time);


            orari_andata.add(orario);
            cursor.moveToNext();
        }
        cursor.close();

        return orari_andata;
    }


    public List<List<Stop>> getStopsByLineaId(Integer linea_id){
        Log.d("getStopsByLineaID", "getStopsByLineaID( linea_id="+linea_id+" )");

        List<Stop> stops_andata = new ArrayList<>();
        List<Stop> stops_ritorno = new ArrayList<>();

        List<List<Stop>> liste = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String selectStatement = "select S._id, S.stop_code, S.stop_name, S.stop_desc, S.stop_lat, S.stop_lon, T.trip_id, T.direction_id " +
                "from trips as T, stop_times as ST, stop as S " +
                "where ST.stop_id=S._id"+
                " and ST.trip_id=T.trip_id"+
                " and T.route_id="+linea_id+
                " and T.direction_id=0 group by S._id order by ST.stop_sequence ASC";
        Cursor cursor = db.rawQuery(selectStatement, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Stop stop = new Stop(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getFloat(4), cursor.getFloat(5));
            stop.setDirection_id(cursor.getInt(6));
            stop.setTrip_id(cursor.getDouble(6));
            stops_andata.add(stop);
            cursor.moveToNext();
        }

        String selectStatement2 = "select S._id, S.stop_code, S.stop_name, S.stop_desc, S.stop_lat, S.stop_lon, T.trip_id " +
                "from trips as T, stop_times as ST, stop as S " +
                "where ST.stop_id=S._id"+
                " and ST.trip_id=T.trip_id"+
                " and T.route_id="+linea_id+
                " and T.direction_id=0 group by S._id order by ST.stop_sequence ASC";
        cursor = db.rawQuery(selectStatement, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Stop stop = new Stop(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getFloat(4), cursor.getFloat(5));
            stop.setTrip_id(cursor.getDouble(6));
            stops_ritorno.add(stop);
            cursor.moveToNext();
        }

        cursor.close();

        liste.add(stops_andata);
        liste.add(stops_ritorno);
        return liste;
    }

    public List<Linea> getAllLinee(){
        Log.d("getAllLinee", "getAllLinee( )");

        List<Linea> linee = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("routes", new String[]{"_id", "route_short_name", "route_long_name", "route_color"}, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Integer _id = cursor.getInt(0);
            String route_short_name = cursor.getString(1);
            String route_long_name = cursor.getString(2);
            String route_color = cursor.getString(3);
            Linea linea = new Linea(_id, route_short_name, route_long_name, route_color);
            linee.add(linea);
            cursor.moveToNext();
        }
        cursor.close();

        return linee;
    }

    public Linea getLineaById(Integer id){
        Log.d("getLineaById", "getLineaById( id="+id+" )");
        Linea linea = new Linea(null, null, null, null);

        SQLiteDatabase db = getReadableDatabase();
        String selectStatement = "select _id, route_short_name, route_long_name, route_color " +
                "from routes where _id="+id;
        Cursor cursor = db.rawQuery(selectStatement, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Integer _id = cursor.getInt(0);
            String route_short_name = cursor.getString(1);
            String route_long_name = cursor.getString(2);
            String route_color = cursor.getString(3);
            linea = new Linea(_id, route_short_name, route_long_name, route_color);
            cursor.moveToNext();
        }
        cursor.close();
        return linea;
    }

    public Trip getTripById(Double id){
        Log.d("getTripById", "in getTripById( id="+id+" )");
        Trip trip = new Trip(null, null, null, null, null);

        SQLiteDatabase db = getReadableDatabase();
        String selectStatement = "select _id, trip_id, route_id, direction_id," +
                "trip_headsign from trips where trip_id="+id;
        Cursor cursor = db.rawQuery(selectStatement, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Integer _id = cursor.getInt(0);
            Double trip_id = cursor.getDouble(1);
            Integer route_id = cursor.getInt(2);
            Boolean direction_id = cursor.getInt(3) == 0 ? true : false;
            String trip_headsign = cursor.getString(4);

            trip = new Trip(_id, route_id, trip_id, trip_headsign, direction_id);
            cursor.moveToNext();
        }
        cursor.close();

        return trip;
    }


    public List<Orario> getNearestOrarioFromStop(Stop stop, String time){
        Log.d("getNearestOrarioFrStop", "getNearestOrarioFromStop( stop_id="+stop.getId()+" )");

        List<Orario> lOrari = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String selectStatement = "SELECT _id, trip_id, arrival_time, departure_time, stop_id, stop_sequence" +
                " from stop_times where time(departure_time)>time('"+time+"') " +
                "and stop_id ='"+stop.getId()+"' order by time(departure_time) ASC LIMIT 3";
        //selectStatement = "Select * FROM stop_times";
        Cursor cursor = db.rawQuery(selectStatement, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Integer id = cursor.getInt(0);
            Double trip_id = cursor.getDouble(1);
            Integer stop__id = cursor.getInt(4);
            Integer stop_sequence = cursor.getInt(5);
            String arrival_time = cursor.getString(2);
            String departure_time = cursor.getString(3);


            Orario orario = new Orario(id, trip_id, stop__id, stop_sequence, arrival_time, departure_time);
            lOrari.add(orario);
            cursor.moveToNext();
        }
        cursor.close();
        return lOrari;

    }

    public List<Stop> getNearestStops(int many, Float userLat, Float userLon){
        Log.d("DB", "getting nearest stops");
        List<Stop> stops = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String selectStatement = "SELECT _id, stop_code, stop_name, stop_desc, stop_lat, stop_lon, ( (stop_lat-"+userLat+")*(stop_lat-"+userLat+
                ")+(stop_lon-"+userLon+")*(stop_lon-"+userLon+")) as distance FROM Stop ORDER BY distance ASC LIMIT "+many;
        Cursor cursor = db.rawQuery(selectStatement, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Stop stop = new Stop(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getFloat(4), cursor.getFloat(5));
            stops.add(stop);
            cursor.moveToNext();
        }

        cursor.close();
        return stops;
    }

    public List<Stop> getAllStops() {
        Log.d("DB", "getting all stops");
        List<Stop> stops = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"_id", "stop_code", "stop_name", "stop_desc", "stop_lat", "stop_lon"};
        String sqlTables = "Stop";

        qb.setTables(sqlTables);
        Cursor cursor = qb.query(db, sqlSelect, null, null,
                null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Stop stop = this.cursorToStop(cursor);
            stops.add(stop);
            cursor.moveToNext();
        }

        cursor.close();
        return stops;
    }


    private Stop cursorToStop(Cursor cursor) {
        Integer id = cursor.getInt(0);
        String code = cursor.getString(1);
        String name = cursor.getString(2);
        String desc = cursor.getString(3);
        Float lat = cursor.getFloat(4);
        Float lon = cursor.getFloat(5);

        Stop s = new Stop(id, code, name, desc, lat, lon);

        return s;
    }

    private ContentValues stopToValues(Stop stop) {
        ContentValues values = new ContentValues();
        values.put("stop_code", stop.getCode());
        values.put("stop_desc", stop.getDesc());
        values.put("stop_id", stop.getId());
        values.put("stop_lat", stop.getLat());
        values.put("stop_lon", stop.getLon());
        values.put("stop_name", stop.getName());
        return values;
    }
}
