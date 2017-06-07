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
public class Evento extends Tile{
    private Indirizzo indirizzo;
    private GregorianCalendar data;
    private Genere_Evento genere;

    public Evento(String titolo,Genere_Evento genere, String descrizione, String patterImmagine,String Url) {
        super(titolo, descrizione, patterImmagine,Url);
        this.genere=genere;
    }

    /**
     * @return the indirizzo
     */
    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    /**
     * @param indirizzo the indirizzo to set
     */
    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
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
     * @return the genere
     */
    public Genere_Evento getGenere() {
        return genere;
    }

    /**
     * @param genere the genere to set
     */
    public void setGenere(Genere_Evento genere) {
        this.genere = genere;
    }
}
