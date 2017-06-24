/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.group.trentomobile.Classi;

import java.io.Serializable;
import android.text.format.Time;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Luogo extends Tile implements Serializable,Indirizzabile {
    
    private Indirizzo indirizzo;
    private ArrayList<Luogo> vicini=new ArrayList<Luogo>();
    private Genere_Luogo genere;
    private AperturaChiusura orario;

    public Luogo(Indirizzo indirizzo, Genere_Luogo genere, String titolo, String descrizione, String patterImmagine,String Url) {
        super(titolo, descrizione, patterImmagine,Url);
        this.indirizzo = indirizzo;
        this.genere = genere;
        this.orario = null;
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
     * @return the vicini
     */
    public ArrayList<Luogo> getVicini() {
        return vicini;
    }

    /**
     * @param vicini the vicini to set
     */
    public void setVicini(ArrayList<Luogo> vicini) {
        this.vicini = vicini;
    }

    /**
     * @return the genere
     */
    public Genere_Luogo getGenere() {
        return genere;
    }

    /**
     * @param genere the genere to set
     */
    public void setGenere(Genere_Luogo genere) {
        this.genere = genere;
    }


    public AperturaChiusura getOrario() {
        return orario;
    }

    public void setOrario(String a, String c) {
        this.orario = new AperturaChiusura(a,c);
    }
}
