package com.project.group.trentomobile.Classi;

import android.icu.util.GregorianCalendar;
import android.provider.ContactsContract;

import java.util.Date;

/**
 * Created by postal on 24/06/17.
 */

public class PosizioneData {

    private Double lat;
    private Double lng;
    private Date data;
    private Integer peso =0;

    public PosizioneData(Double lat, Double lng, Date date) {
        this.data = data;
        this.lat = lat;
        this.lng = lng;
    }


    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public Date getData() {
        return data;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getPeso() {
        return peso;
    }
}
