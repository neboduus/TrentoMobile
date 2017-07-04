package com.project.group.trentomobile.Util;

/**
 * Created by postal on 15/06/17.
 */

import java.lang.Math;

public class CoordinateToMetri {

    static public Integer disgeod (double latA, double lonA,
                           double latB, double lonB)
    {
      /* Definisce le costanti e le variabili */
      final double R = 6371;
      final double pigreco = 3.1415927;
        double lat_alfa, lat_beta;
        double lon_alfa, lon_beta;
        double fi;
        Double p, d;
      /* Converte i gradi in radianti */
        lat_alfa = pigreco * latA / 180;
        lat_beta = pigreco * latB / 180;
        lon_alfa = pigreco * lonA / 180;
        lon_beta = pigreco * lonB / 180;
      /* Calcola l'angolo compreso fi */
        fi = java.lang.Math.abs(lon_alfa - lon_beta);
      /* Calcola il terzo lato del triangolo sferico */
        p = java.lang.Math.acos(java.lang.Math.sin(lat_beta) * java.lang.Math.sin(lat_alfa) +
                java.lang.Math.cos(lat_beta) * java.lang.Math.cos(lat_alfa) * java.lang.Math.cos(fi));
      /* Calcola la distanza sulla superficie
      terrestre R = ~6371 km */
        d = p * R *1000;


        return d.intValue();
    }


}
