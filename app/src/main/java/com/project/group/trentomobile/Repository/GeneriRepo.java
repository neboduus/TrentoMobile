package com.project.group.trentomobile.Repository;

import com.project.group.trentomobile.Classi.Bus;
import com.project.group.trentomobile.Classi.Genere;
import com.project.group.trentomobile.Classi.Genere_Evento;
import com.project.group.trentomobile.Classi.Genere_Luogo;
import com.project.group.trentomobile.Classi.Genere_Notizia;

import java.util.ArrayList;

/**
 * Created by postal on 23/05/17.
 */

public class GeneriRepo {

    public ArrayList<Genere_Notizia> GeneriNotizie;
    public ArrayList<Genere_Luogo> GeneriLuoghi;
    public ArrayList<Genere_Evento> GeneriEventi;
    public ArrayList<Bus> Autobus;

    static private GeneriRepo istance;

    private GeneriRepo(){
        GeneriNotizie = new ArrayList<>();
        GeneriLuoghi = new ArrayList<>();
        GeneriEventi = new ArrayList<>();
        Autobus = new ArrayList<>();
    }

    public void addGenere_Notizia(Genere_Notizia g){
        GeneriNotizie.add(g);
    }

    static public GeneriRepo getIstance(){
        if(istance==null)
            istance = new GeneriRepo();
        return istance;
    }





}
