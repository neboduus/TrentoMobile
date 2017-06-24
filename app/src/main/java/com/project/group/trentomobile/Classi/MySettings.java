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


        if(occupazione.equals("turista"))
        {

            myPreference.getPref_Notizie().put("Sport",5);
            myPreference.getPref_Notizie().put("Musicale",5);
            myPreference.getPref_Notizie().put("Economiche",5);
            myPreference.getPref_Notizie().put("Traffico",5);
            myPreference.getPref_Notizie().put("Mezzi di trasporto",5);
            myPreference.getPref_Notizie().put("Opportunità provinciali/comunal",5);
            myPreference.getPref_Notizie().put("Politica",5);
            myPreference.getPref_Notizie().put("Ludico",5);
            myPreference.getPref_Notizie().put("Cronaca",5);
            myPreference.getPref_Notizie().put("Culturale",5);
            myPreference.getPref_Notizie().put("Universitari",5);
            myPreference.getPref_Notizie().put("Politico",5);


            myPreference.getPref_Luoghi().put("Sempre Utili",5);
            myPreference.getPref_Luoghi().put("Universitari",5);
            myPreference.getPref_Luoghi().put("Commerciali",5);

            myPreference.getPref_Eventi().put("Sagra",5);
            myPreference.getPref_Eventi().put("Ludico",5);
            myPreference.getPref_Eventi().put("Discoteche",5);
            myPreference.getPref_Eventi().put("Cinema",5);
            myPreference.getPref_Eventi().put("Musicale",5);
            myPreference.getPref_Eventi().put("Teatro",5);
            myPreference.getPref_Eventi().put("Universitario",5);




            myPreference.setPref_Trasporti_False();



        }else if(eta<18)
        {
            if(occupazione.equals("lavoratore"))
            {


                myPreference.getPref_Notizie().put("Culturali",5);
                myPreference.getPref_Notizie().put("Musicale",5);
                myPreference.getPref_Notizie().put("Economiche",5);
                myPreference.getPref_Notizie().put("Traffico",5);
                myPreference.getPref_Notizie().put("Politica",5);
                myPreference.getPref_Notizie().put("Ludico",5);
                myPreference.getPref_Notizie().put("Universitari",5);

                myPreference.getPref_Luoghi().put("Piazze",5);
                myPreference.getPref_Luoghi().put("Musei",5);
                myPreference.getPref_Luoghi().put("Monumenti",5);
                myPreference.getPref_Luoghi().put("Religioso",5);
                myPreference.getPref_Luoghi().put("Universitari",5);

                myPreference.getPref_Eventi().put("Sagra",5);
                myPreference.getPref_Eventi().put("Provinciali",5);
                myPreference.getPref_Eventi().put("Ludico",5);
                myPreference.getPref_Eventi().put("Musicale",5);
                myPreference.getPref_Eventi().put("Teatro",5);
                myPreference.getPref_Eventi().put("Universitario",5);
            }else
            {

                myPreference.getPref_Notizie().put("Cronaca",5);
                myPreference.getPref_Notizie().put("Culturali",5);
                myPreference.getPref_Notizie().put("Economiche",5);
                myPreference.getPref_Notizie().put("Traffico",5);
                myPreference.getPref_Notizie().put("Politica",5);
                myPreference.getPref_Notizie().put("Ludico",5);
                myPreference.getPref_Notizie().put("Universitari",5);

                myPreference.getPref_Luoghi().put("Piazze",5);
                myPreference.getPref_Luoghi().put("Musei",5);
                myPreference.getPref_Luoghi().put("Monumenti",5);
                myPreference.getPref_Luoghi().put("Religioso",5);
                myPreference.getPref_Luoghi().put("Sempre Utili",5);
                myPreference.getPref_Luoghi().put("Commerciali",5);
                myPreference.getPref_Luoghi().put("Universitari",5);

                myPreference.getPref_Eventi().put("Sagra",5);
                myPreference.getPref_Eventi().put("Provinciali",5);
                myPreference.getPref_Eventi().put("Ludico",5);
                myPreference.getPref_Eventi().put("Universitario",5);
                myPreference.getPref_Eventi().put("Teatro",5);
            }
        }else
        if(eta<30 && eta>=18){
            if(occupazione.equals("altro"))
            {

                myPreference.getPref_Notizie().put("Sport",5);
                myPreference.getPref_Notizie().put("Culturali",5);
                myPreference.getPref_Notizie().put("Musicale",5);
                myPreference.getPref_Notizie().put("Economiche",5);
                myPreference.getPref_Notizie().put("Traffico",5);
                myPreference.getPref_Notizie().put("Mezzi di trasporto",5);
                myPreference.getPref_Notizie().put("Orario edifici",5);
                myPreference.getPref_Notizie().put("Universitari",5);

                myPreference.getPref_Luoghi().put("Piazze",5);
                myPreference.getPref_Luoghi().put("Musei",5);
                myPreference.getPref_Luoghi().put("Monumenti",5);
                myPreference.getPref_Luoghi().put("Religioso",5);
                myPreference.getPref_Luoghi().put("Parchi",5);
                myPreference.getPref_Luoghi().put("Universitari",5);
                myPreference.getPref_Luoghi().put("Commerciali",5);

                myPreference.getPref_Eventi().put("Sagra",5);
                myPreference.getPref_Eventi().put("Provinciali",5);
                myPreference.getPref_Eventi().put("Ludico",5);
                myPreference.getPref_Eventi().put("Teatro",5);
                myPreference.getPref_Eventi().put("Universitario",5);
            }else
            if(occupazione.equals("lavoratore"))
            {


                myPreference.getPref_Luoghi().put("Piazze",5);
                myPreference.getPref_Luoghi().put("Musei",5);
                myPreference.getPref_Luoghi().put("Monumenti",5);
                myPreference.getPref_Luoghi().put("Religioso",5);
                myPreference.getPref_Luoghi().put("Parchi",5);
                myPreference.getPref_Luoghi().put("Sempre Utili",5);
                myPreference.getPref_Luoghi().put("Universitari",5);
                myPreference.getPref_Luoghi().put("Commerciali",5);

                myPreference.getPref_Notizie().put("Ludico",5);
                myPreference.getPref_Notizie().put("Discoteche",5);
                myPreference.getPref_Notizie().put("Universitari",5);

                myPreference.getPref_Eventi().put("Sagra",5);
                myPreference.getPref_Eventi().put("Provinciali",5);
                myPreference.getPref_Eventi().put("Ludico",5);
                myPreference.getPref_Eventi().put("Universitario",5);
                myPreference.getPref_Eventi().put("Teatro",5);

                myPreference.setPref_Trasporti_False();
            }else
            if(occupazione.equals("studente"))
            {


                myPreference.getPref_Notizie().put("Musicale",5);
                myPreference.getPref_Notizie().put("Economiche",5);
                myPreference.getPref_Notizie().put("Traffico",5);
                myPreference.getPref_Notizie().put("Politica",5);
                myPreference.getPref_Notizie().put("Ludico",5);

                myPreference.getPref_Luoghi().put("Piazze",5);
                myPreference.getPref_Luoghi().put("Musei",5);
                myPreference.getPref_Luoghi().put("Monumenti",5);
                myPreference.getPref_Luoghi().put("Religioso",5);


                myPreference.getPref_Eventi().put("Sagra",5);
                myPreference.getPref_Eventi().put("Provinciali",5);
                myPreference.getPref_Eventi().put("Discoteche",5);
                myPreference.getPref_Eventi().put("Teatro",5);

            }
        }else
        if (eta>30 && eta<=50){
            if("studente".equals(occupazione))
            {
                HashMap mappa=new HashMap();
                myPreference.getPref_Notizie().put("Universita",5);
                myPreference.getPref_Notizie().put("Culturali",5);
                myPreference.getPref_Notizie().put("Mezzi di Trasporto",5);
                myPreference.getPref_Notizie().put("Universitari",5);
                myPreference.getPref_Notizie().put("Politico",5);
                myPreference.getPref_Notizie().put("Cronaca",5);


                myPreference.getPref_Luoghi().put("Piazze",5);
                myPreference.getPref_Luoghi().put("Musei",5);
                myPreference.getPref_Luoghi().put("Monumenti",5);
                myPreference.getPref_Luoghi().put("Religioso",5);
                myPreference.getPref_Luoghi().put("Parchi",5);

                myPreference.getPref_Eventi().put("Sagra",5);
                myPreference.getPref_Eventi().put("Provinciali",5);
                myPreference.getPref_Eventi().put("Ludico",5);
                myPreference.getPref_Eventi().put("Discoteche",5);
                myPreference.getPref_Eventi().put("Cinema",5);

            }
        }

    }
}