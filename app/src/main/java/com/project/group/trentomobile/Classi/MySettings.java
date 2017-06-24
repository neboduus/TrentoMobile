package com.project.group.trentomobile.Classi;

import com.project.group.trentomobile.Repository.GeneriRepo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 17/06/2017.
 */

public class MySettings implements Serializable{

    private int eta;
    private String occupazione;
    private Preferenze myPreference;

    public MySettings(int eta, String occupazione)
    {
        this.eta=eta;
        this.occupazione=occupazione;
        setta_preferenze();
    }

    public Preferenze getMyPreference() {
        return myPreference;
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

                myPreference.getPref_Notizie().put("Cronaca",5);
            }else
            {

                myPreference.getPref_Notizie().put("Cronaca",5);
                myPreference.getPref_Notizie().put("Culturali",5);
            }
        }
        if(eta<30 && eta>=18){
            if(occupazione=="altro")
            {

                myPreference.getPref_Notizie().put("Sport",5);
                myPreference.getPref_Notizie().put("Culturali",5);
                myPreference.getPref_Notizie().put("Musicale",5);
                myPreference.getPref_Notizie().put("Economiche",5);
                myPreference.getPref_Notizie().put("Traffico",5);
                myPreference.getPref_Notizie().put("Mezzi di trasporto",5);
                myPreference.getPref_Notizie().put("Orario edifici",5);
            }
            if(occupazione=="lavoratore")
            {

                myPreference.getPref_Notizie().put("Sport",5);
                myPreference.getPref_Notizie().put("Culturali",5);
                myPreference.getPref_Notizie().put("Musicale",5);
                myPreference.getPref_Notizie().put("Economiche",5);
                myPreference.getPref_Notizie().put("Traffico",5);
                myPreference.getPref_Notizie().put("Mezzi di trasporto",5);
                myPreference.getPref_Notizie().put("Orario edifici",5);
            }
            if(occupazione=="studente")
            {

                myPreference.getPref_Notizie().put("Sport",5);
                myPreference.getPref_Notizie().put("Culturali",5);
                myPreference.getPref_Notizie().put("Musicale",5);
                myPreference.getPref_Notizie().put("Economiche",5);
                myPreference.getPref_Notizie().put("Traffico",5);
                myPreference.getPref_Notizie().put("Mezzi di trasporto",5);
                myPreference.getPref_Notizie().put("Orario edifici",5);
                myPreference.getPref_Notizie().put("Opportunità provinciali/comunal",5);
                myPreference.getPref_Notizie().put("Politica",5);
                myPreference.getPref_Notizie().put("Ludico",5);

            }
        }
        if (eta>30 && eta<=50){
            if("studente"==occupazione)
            {
                HashMap mappa=new HashMap();
                myPreference.getPref_Notizie().put("Universita",5);
                myPreference.getPref_Notizie().put("Culturali",5);
                myPreference.getPref_Notizie().put("Mezzi di Trasporto",5);
            }
        }

        if(occupazione=="turista")
        {

            myPreference.getPref_Notizie().put("Sport",5);
            myPreference.getPref_Notizie().put("Musicale",5);
            myPreference.getPref_Notizie().put("Economiche",5);
            myPreference.getPref_Notizie().put("Traffico",5);
            myPreference.getPref_Notizie().put("Mezzi di trasporto",5);
            myPreference.getPref_Notizie().put("Opportunità provinciali/comunal",5);
            myPreference.getPref_Notizie().put("Politica",5);
            myPreference.getPref_Notizie().put("Ludico",5);

        }
    }
}