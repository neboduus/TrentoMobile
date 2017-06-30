/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.group.trentomobile.Classi;

import java.util.GregorianCalendar;

/**
 *
 * @author User
 */
public class Notizia extends Tile {
    private GregorianCalendar data;
    private Autore autore;
    private Genere_Notizia genere;
    

    public Notizia(String titolo, String descrizione, String patterImmagine,String Url, Autore autore, Genere_Notizia genere, GregorianCalendar data) {
        super(titolo, descrizione, patterImmagine,Url);
        this.autore = autore;
        this.genere = genere;
        this.data = data;
    }

    /**
     * @return the data
     */
    public GregorianCalendar getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    /**
     * @return the autore
     */
    public Autore getAutore() {
        return autore;
    }

    /**
     * @param autore the autore to set
     */
    public void setAutore(Autore autore) {
        this.autore = autore;
    }

    /**
     * @return the genere
     */
    public Genere_Notizia getGenere() {
        return genere;
    }

    /**
     * @param genere the genere to set
     */
    public void setGenere(Genere_Notizia genere) {
        this.genere = genere;
    }
}
