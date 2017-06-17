package com.project.group.trentomobile.Classi;

/**
 * Created by postal on 10/06/17.
 */

public class Fermata extends Tile implements Indirizzabile{

    private Indirizzo indirizzo;

    //ALTRE COSE
    public Fermata(Integer id,String titolo, String descrizione, String patterImmagine, String Url, Indirizzo indirizzo) {
        super(titolo, descrizione, patterImmagine, Url);
        super.setId(id);
        this.indirizzo = indirizzo;
    }

    @Override
    public Indirizzo getIndirizzo() {
        return indirizzo;
    }
}
