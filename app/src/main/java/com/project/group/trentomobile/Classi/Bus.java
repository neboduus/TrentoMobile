package com.project.group.trentomobile.Classi;

import android.graphics.Color;

import com.project.group.trentomobile.Repository.GeneriRepo;

/**
 * Created by neboduus on 10/06/2017.
 */

public class Bus extends Genere {
    private Integer idBus;
    private String short_name;
    private String long_name;
    private Integer color;

    public Bus(String tipo, Integer id, String short_name, String long_name, Integer color) {
        super(tipo);
        this.setIdBus(id != null ? id : -1);
        this.setShort_name(short_name != null ? short_name : "none");
        this.setLong_name(long_name != null ? long_name : "none");
        this.setColor(color != null ? color*-1 : Color.BLUE);
    }


    public Integer getIdBus() {
        return idBus;
    }

    public void setIdBus(Integer id) {
        this.idBus = id;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}
