package com.project.group.trentomobile.Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.project.group.trentomobile.context.MyApplication;

/**
 * Created by postal on 10/06/17.
 */

public class GetMyPosition {

    public Double lat = null;
    public Double lng = null;
    private static FusedLocationProviderClient mFusedLocationClient;

    static private GetMyPosition istance = null;

    private GetMyPosition() {
    }

    public static GetMyPosition getIstanceAndUpdate(Activity acty) {
        if (istance == null)
            istance = new GetMyPosition();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(acty);

        if (ActivityCompat.checkSelfPermission(acty, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(acty, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.



        }

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(acty, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.d("lat", String.valueOf(location.getLatitude()));
                            Log.d("lng", String.valueOf(location.getLongitude()));
                            istance.lat = location.getLatitude();
                            istance.lng = location.getLongitude();
                        }
                    }
                });




        return istance;
    }





}
