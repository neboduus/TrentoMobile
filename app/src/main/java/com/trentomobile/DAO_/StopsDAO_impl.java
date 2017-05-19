//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.trentomobile.DAO_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trentomobile.context.MyApplication;
import com.trentomobile.transport.Stop;
import java.util.ArrayList;
import java.util.List;

public class StopsDAO_impl implements StopsDAO {
    private DataBaseTransportHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allColumns = new String[]{"stop_id", "stop_code", "stop_desc", "stop_lat", "stop_lon", "stop_name"};

    public StopsDAO_impl() {
    }

    public void open() {
        if(this.dbHelper == null) {
            this.dbHelper = new DataBaseTransportHelper(MyApplication.getAppContext());
        }

        this.database = this.dbHelper.getWritableDatabase();
    }

    public void close() {
        this.dbHelper.close();
    }

    public Stop getStopByName(String name) {
        return null;
    }

    public Stop getNearestStops(Float lat, Float lon) {
        return null;
    }

    public List<Stop> getAllStops() {
        ArrayList stops = new ArrayList();
        DataBaseTransportHelper var10001 = this.dbHelper;
        Cursor cursor = this.database.query("Stop", this.allColumns, (String)null, (String[])null, (String)null, (String)null, (String)null);
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
        Float id = cursor.getFloat(0);
        String code = cursor.getString(1);
        String name = cursor.getString(2);
        String desc = cursor.getString(3);
        Float lat = cursor.getFloat(4);
        Float lon = cursor.getFloat(5);
        return new Stop(id, code, name, desc, lat, lon);
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
