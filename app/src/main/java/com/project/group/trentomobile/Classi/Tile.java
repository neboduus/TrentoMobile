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
public class Tile implements Serializable{
    private Integer id;
    private String titolo;
    private String descrizione;
    private String patterImmagine;
    private String URL;
    public Integer peso;

    public Tile( String titolo, String descrizione, String patterImmagine, String Url) {
        this.id = -1;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.patterImmagine = patterImmagine;
        this.URL=Url;
    }
    
    
    
    public Integer getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getPatterImmagine() {
        return patterImmagine;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param titolo the titolo to set
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * @param patterImmagine the patterImmagine to set
     */
    public void setPatterImmagine(String patterImmagine) {
        this.patterImmagine = patterImmagine;
    }

    /**
     * @return the URL
     */
    public String getURL() {
        return URL;
    }

    /**
     * @param URL the URL to set
     */
    public void setURL(String URL) {
        this.URL = URL;
    }
    
}
