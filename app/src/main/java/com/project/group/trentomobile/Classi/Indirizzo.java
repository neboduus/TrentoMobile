/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.group.trentomobile.Classi;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Indirizzo implements Serializable {
    private Integer id;    
    private Float lat;
    private Float lng;
    private String via;

    public Indirizzo( Float lat, Float lng, String via) {
        this.id = -1;
        this.lat = lat;
        this.lng = lng;
        this.via = via;
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the lat
     */
    public Float getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(Float lat) {
        this.lat = lat;
    }

    /**
     * @return the lng
     */
    public Float getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(Float lng) {
        this.lng = lng;
    }

    /**
     * @return the via
     */
    public String getVia() {
        return via;
    }

    /**
     * @param via the via to set
     */
    public void setVia(String via) {
        this.via = via;
    }
}
