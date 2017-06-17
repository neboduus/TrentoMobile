package com.project.group.trentomobile.Classi;

import com.project.group.trentomobile.Repository.GeneriRepo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 17/06/2017.
 */

public class Settings implements Serializable{

    private int eta;
    private String occupazione;
    private Preferenze myPreference;

    public Settings (int eta,String occupazione)
    {
        this.eta=eta;
        this.occupazione=occupazione;
        setta_preferenze();
    }

    private void setta_preferenze()
    {
        myPreference = new Preferenze();


        for (Genere_Luogo g: GeneriRepo.getIstance().GeneriLuoghi) {
            myPreference.addPref_Luoghi_Ture(g.getTipo());
        }
        for (Genere_Notizia g: GeneriRepo.getIstance().GeneriNotizie) {
            myPreference.addPref_Notizie_Ture(g.getTipo());
        }
        for (Genere_Evento g: GeneriRepo.getIstance().GeneriEventi) {
            myPreference.addPref_Eventi_Ture(g.getTipo());
        }

        myPreference.setPref_Trasporti_Ture();

        if(eta<18)
        {
            if(occupazione=="lavoratore")
            {
                HashMap mappa=new HashMap();
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Cronaca",5));
            }else
            {
                HashMap mappa=new HashMap();
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Cronaca",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Culturali",5));
            }
        }
        if(eta<30 && eta>=18){
            if(occupazione=="disoccupato")
            {
                HashMap mappa=new HashMap();
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Sport",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Culturali",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Musicale",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Economiche",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Traffico",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Mezzi di trasporto",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Orario edifici",5));
            }
            if(occupazione=="lavoratore")
            {
                HashMap mappa=new HashMap();
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Sport",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Culturali",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Musicale",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Economiche",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Traffico",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Mezzi di trasporto",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Orario edifici",5));
            }
            if(occupazione=="studente")
            {
                HashMap mappa=new HashMap();
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Sport",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Culturali",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Musicale",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Economiche",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Traffico",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Mezzi di trasporto",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Orario edifici",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Opportunità provinciali/comunal",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Politica",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Università",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Ludico",5));

            }
        }
        if (eta>30 && eta<=50){
            if("studente"==occupazione)
            {
                HashMap mappa=new HashMap();
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Universita",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Culturali",5));
                myPreference.setPref_Notizie((Map<String, Integer>) mappa.put("Mezzi di Trasporto",5));
            }
        }
    }
}