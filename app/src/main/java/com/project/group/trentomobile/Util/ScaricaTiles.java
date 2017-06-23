package com.project.group.trentomobile.Util;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.project.group.trentomobile.Classi.*;
import com.project.group.trentomobile.R;
import com.project.group.trentomobile.Repository.*;
import com.project.group.trentomobile.TilePK.TileFragment;
import com.project.group.trentomobile.assetsHelper.SQLAssetHelper_DB;
import com.project.group.trentomobile.context.MyApplication;
import com.project.group.trentomobile.transport.Linea;
import com.project.group.trentomobile.transport.Orario;
import com.project.group.trentomobile.transport.Stop;
import com.project.group.trentomobile.transport.Trip;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.visuality.f32.temperature.TemperatureUnit;
import com.visuality.f32.weather.data.entity.Weather;
import com.visuality.f32.weather.manager.WeatherManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by postal on 13/05/17.
 */

public class ScaricaTiles extends AsyncTask<Preferenze,Void,TileMemoryRep> {

    Activity myActivity;

    public ScaricaTiles(Activity activity){
        myActivity = activity;
    }

    @Override
    protected TileMemoryRep doInBackground(Preferenze... params) {

        //PRENDI LA TUA POSIZIONE
        GetMyPosition pos = GetMyPosition.getIstanceAndUpdate(myActivity);


        TileMemoryRep tiles = TileMemoryRep.getInstance();

        if(tiles.getNotizie().isEmpty()) {

            //AGGIUNGI COSE
            Genere_Notizia gn_ludico = new Genere_Notizia("Ludico");
            Genere_Notizia gn_cronaca = new Genere_Notizia("Cronaca");
            gn_cronaca.setFoto("http://www.ansa.it/webimages/img_457x260/2017/5/26/80836b578212ee55f47e08a2454ff481.jpg");
            Genere_Notizia gn_culturale = new Genere_Notizia("Culturale");
            gn_culturale.setFoto("https://www.cultura.trentino.it/var/001/storage/images/media/images/la-facciata-sud-occidentale-del-palazzo-della-provincia-gia-imperial-hotel-trento-con-il-loggiato-eretto-tra-il-1898-e-il-1900-dall-architetto-em_opengraph/22254052-1-ita-IT/La-facciata-sud-occidentale-del-Palazzo-della-Provincia-gia-Imperial-Hotel-Trento-con-il-loggiato-eretto-tra-il-1898-e-il-1900-dall-architetto-Em_o_imagefullwide.jpg");
            Genere_Notizia gn_uni = new Genere_Notizia("Universitari");
            gn_uni.setFoto("http://3.citynews-trentotoday.stgy.ovh/~media/original-hi/44531476459805/cerimonia_lauree-2-2.jpg");
            Genere_Notizia gn_traffico = new Genere_Notizia("Traffico");
            gn_traffico.setFoto("http://notizie.tiscali.it/export/shared/agencies/media/15/12/26/b5648afd12e8624c9c375e48dd8da3cc.jpg_997313609.jpg");
            Genere_Notizia gn_politico = new Genere_Notizia("Politico");
            gn_politico.setFoto("http://www.lavocedeltrentino.it/wp-content/uploads/2017/04/comune_Tn.jpg");
            Genere_Notizia gn_sport = new Genere_Notizia("Sport");
            gn_sport.setFoto("http://www.pu24.it/wp/wp-content/uploads/2017/02/16649036_1370588699671463_2148889984841226295_n.jpg");

            Genere_Notizia gn_meteo = new Genere_Notizia("Meteo");

            Genere_Luogo gl_Piazze = new Genere_Luogo("Piazze");
            gl_Piazze.setFoto("http://radionbc.it/wp-content/uploads/2015/09/DANTE.jpg");
            Genere_Luogo gl_Musei = new Genere_Luogo("Musei");
            gl_Musei.setFoto("http://www.svegliamuseo.com/wp-content/uploads/2014/01/Muse_%C2%AEHufton+Crow_027.jpg");
            Genere_Luogo gl_Monumenti = new Genere_Luogo("Monumenti");
            gl_Monumenti.setFoto("https://www.lindipendenza.com/wp-content/uploads/TRENTO-FOTO-20-Il-Monumento-di-Dante-02.jpg");
            Genere_Luogo gl_Parchi = new Genere_Luogo("Parchi");
            gl_Parchi.setFoto("http://www.adnkronos.com/rf/image_size_1280x960/Pub/AdnKronos/Assets/Immagini/molveno2.jpg");
            Genere_Luogo gl_Religioso = new Genere_Luogo("Religioso");
            gl_Religioso.setFoto("http://www.filmcommission.provincia.tn.it/filesroot/Images/1405_Trento_-_Chiesa_di_S__Pietro__3__jpeg/$650$0$/Trento_-_Chiesa_di_S._Pietro__3_.jpeg");
            Genere_Luogo gl_SempreUtili = new Genere_Luogo("Sempre Utili");
            gl_SempreUtili.setFoto("http://besport.org/sportmedicina/wp-content/uploads/2010/09/1440267109puertas-de-farmacia.jpg");
            Genere_Luogo gl_Universitari = new Genere_Luogo("Universitari");
            gl_Universitari.setFoto("http://static-cdn.unitn.it/fileswww/styles/photogallery_full/public/images/2/0012-povo12013collection1.jpg?itok=v41xyZ1l");
            Genere_Luogo gl_Commerciali = new Genere_Luogo("Commerciali");
            gl_Commerciali.setFoto("http://trentinocorrierealpi.gelocal.it/polopoly_fs/1.13397033!/httpImage/image.jpg_gen/derivatives/gallery_978/image.jpg");

            Genere_Evento ge_Sagra = new Genere_Evento("Sagra");
            Genere_Evento ge_Provincia = new Genere_Evento("Provinciali");
            Genere_Evento ge_Ludico = new Genere_Evento("Ludico");
            Genere_Evento ge_Discoteche = new Genere_Evento("Discoteche");
            Genere_Evento ge_Cinema = new Genere_Evento("Cinema");
            Genere_Evento ge_Musicale = new Genere_Evento("Musicale");
            Genere_Evento ge_Teatro = new Genere_Evento("Teatro");
            Genere_Evento ge_Uni = new Genere_Evento("Universitario");


            //tiles.addNotizia(new Notizia("Caricamento meteo","","","",new Autore("OpenWeatherMap", null, null),gn_meteo,new GregorianCalendar()));


            Autore a = new Autore("L'Adige", "adige", "adige");
            GregorianCalendar d = new GregorianCalendar(2017, 2, 1);

            Autore a1 = new Autore("Provincia di Trento", "tn", "tn");
            tiles.addNotizia(new Notizia("Dai rifiuti organici combustibile per 64 autobus di Trentino Trasporti",
                    "L'ambizioso progetto è stato presentato ieri dall'assessore Gilmozzi, l'obiettivo è arrivare a trattare il 100% dell'umido trentino per coprire il fabbisogno dei mezzi pubblici a metano, che passeranno da 42 a 64 entro il 201",
                    "http://3.citynews-trentotoday.stgy.ovh/~media/original-hi/35778078353010/img-20170504-wa0028-1-2.jpg",
                    "http://www.trentotoday.it/green/cadino-metano-organico-autobus-trentino.html",
                    a1, gn_cronaca, d));

            Autore a2 = new Autore("People 4 Soil", "tn", "tn");
            tiles.addNotizia(new Notizia("SALVA IL SUOLO",
                    "In Europa, non esiste ancora una legge comune che difenda il suolo.\n" +
                            "\n" +
                            "Tutelare il suolo con delle leggi è il primo modo di proteggere uomini, piante, animali. Senza un suolo sano e vivo non c'è futuro. Un suolo sano e vivo ci protegge dai disastri ambientali, dai cambiamenti climatici, dai veleni nel piatto.",
                    "http://imagestc.trovacasa.net/annunci/LIS/A_24204_28512512_264051876/terreno_agricolo-in-vendita-a-mezzocorona.jpg",
                    "https://www.people4soil.eu/it",
                    a2, gn_cronaca, d));

            Autore a3 = new Autore("Consiglio Europeo", "eu", "eu");
            tiles.addNotizia(new Notizia("Tutto pronto per il roaming gratuito nell'UE a partire da giugno",
                    "Dal 15 giugno gli utenti di telefonia mobile in viaggio in altri paesi dell'UE potranno effettuare telefonate, inviare messaggi di testo o navigare in " +
                            "rete senza pagare sovrapprezzi. L'ultimo requisito per l'abolizione delle tariffe di roaming sui dispositivi mobili è stato soddisfatto oggi con l'" +
                            "adozione, da parte del Consiglio, " +
                            "dell'atto giuridico che limita l'importo che gli operatori possono addebitarsi l'un l'altro per consentire il roaming in tutta l'Europa.",
                    "http://www.lentepubblica.it/wp-content/uploads/2014/07/europa.png",
                    "http://www.consilium.europa.eu/it/press/press-releases/2017/04/25-free-roaming-from-june/",
                    a3, gn_cronaca, d));

            Autore a4 = new Autore("Provincia di Trento", "tn", "tn");
            tiles.addNotizia(new Notizia("Spazio Alpino 2014-2020",
                    "Il 13aprile é stato pubblicato il terzo bando del programma transnazionale di \"Cooperazione Territoriale Europea\" che promuove uno sviluppo " +
                            "regionale sostenibile nella regione alpina, contribuisce alla Strategia Europa 2020 per una crescita intelligente, " +
                            "sostenibile e inclusiva e fornisce alle parti interessate un contesto operativo per sviluppare nuove idee.",
                    "http://www.alpine-space.eu/projects/thefourbees/logos/logo_the4bees_h.png",
                    "http://www.alpine-space.eu/project-application/project-submission/open-calls-for-project-proposals",
                    a4, gn_cronaca, d));

            Autore a5 = new Autore("Università di Trento", "tn", "tn");
            tiles.addNotizia(new Notizia("Borse di studio e assegni",
                    "L'Università degli Studi di Trento ha mantenuto negli anni una politica di sostegno degli studenti più meritevoli i quali possono garantirsi," +
                            " con diverse modalità, la necessaria tranquillità finanziaria per conseguire il titolo di studio.",
                    "https://www.tutored.me/it/wp-content/uploads/sites/13/2016/07/600sociologia-trento-fonte-unitn.jpg",
                    "http://www.unitn.it/servizi/230/borse-di-studio-e-assegni",
                    a5, gn_uni, d));

            tiles.addNotizia(new Notizia("Dall'Università di Trento la possibilità di tirocini presso le rappresentanze diplomatiche del ministero degli affari esteri",
                    "Il bando è aperto per la selezioni di 11 studenti. Il tirocinio si potrà svolgere presso le sedi diplomatico-consolari e le Rappresentanze Permanenti " +
                            "d’Italia a Barcellona, Bruxelles, Dar-Es-Saalam, Ginevra, Pechino, Sofia, Tel Aviv e Vienna",
                    "http://www.toscanaoggi.it/var/ezdemo_site/storage/images/mondo/ue-e-giovani-concorso-per-le-scuole-diventare-cittadini-europei/2648516-1-ita-IT/Ue-e-giovani-concorso-per-le-scuole-Diventare-cittadini-europei_articleimage.jpg",
                    "http://www.ildolomiti.it/ricerca-e-universita/dalluniversita-di-trento-la-possibilita-di-tirocini-presso-le-rappresentanze",
                    new Autore("Il Dolomiti", "tn", "tn"), gn_uni, d));


            tiles.addNotizia(new Notizia("Trovato gatto a Zambana",
                    "Alla fine ho trovato un gatto incima alla mia casa con del pelo molto bello che non mi dispiace anche perchè è bello morbido al tatto.",
                    "http://www.solegemello.net/gatto.jpg", "http://www.google.com", a, gn_ludico, d));

            Indirizzo ind = new Indirizzo(46.071537d, 11.120346d, "Piazza Dante");
            tiles.addLuogo(new Luogo(ind, gl_Monumenti, "Monumento di dante", "sdjilhcu hudshad uashdjh asjkdhsj hdjsf hfjkhsd jkfhjksd hjksd hjksdfhjk hadjksh jksdh jkhdk fhjksdf",
                    "http://www.meteotrentino.it/images/hp/img-32.gif?27052017", "http://www.google.com"));


            GregorianCalendar d2 = new GregorianCalendar(2017, 5, 12);

            tiles.addNotizia(new Notizia("Bonus per le future mamme. Già mille domande in Trentino",
                    "Sono già circa 1.000 le domande arrivate dal Trentino per ottenere il premio di 800 euro per la nascita o l’adozione di un minore, previsto dalla legge di bilancio per il 2017.",
                    "http://www.ladige.it/sites/www.ladige.it/files/styles/798x457/public/beb%C3%A8_0.jpg?itok=sSmZV6GK",
                    "http://www.ladige.it/news/cronaca/2017/05/13/bonus-future-mamme-gi-mille-domande-trentino",
                    a, gn_cronaca, d2));


            //FILTRAGGIO FERMATE BUS

            GetMyPosition myPosition = GetMyPosition.getIstanceAndUpdate(myActivity);

            while(myPosition.lat == null); //ASPETTA FINCHE NON HA LA POSIZIONE

            SQLAssetHelper_DB  sqlDB = new SQLAssetHelper_DB(MyApplication.getAppContext());

            Log.d("aAAAAAAAAAAAAA", String.valueOf(myPosition.lat));

            List<Stop> ls = sqlDB.getNearestStops(3, myPosition.lat, myPosition.lng);

            for(Stop s : ls){

                List<Orario> lo = sqlDB.getNearestOrarioFromStop(s,new SimpleDateFormat("HH:mm:ss").format(new Date()));
                String corpo="";
                Indirizzo indirizzo = new Indirizzo(s.getLat(), s.getLon(), null);

                int count = 1;

                for(Orario o : lo){
                    o.getArrival_time();
                    Trip t = sqlDB.getTripById(o.getTrip_id());
                    Linea l = sqlDB.getLineaById(t.getRoute_id());

                    corpo+="Bus:"+l.getShort_name()+"\n\tpartenza:"+o.getArrival_time()+"\n\t"+"direzione:"+(t.getDirection_id() ? "andata" :"ritorno")+"\n";

                    if(count==0) break;
                    count--;
                }

                tiles.addFermata(new Fermata(s.getId(),s.getName(),corpo,"https://png.icons8.com/bus/color/50","https://png.icons8.com/bus/color/50",indirizzo));
            }


            //TEST WEATHER
            /*
            new WeatherManager("97afef6a27b88c8138c824865619ff56").getCurrentWeatherByCoordinates(
                    46.1421242, // latitude
                    11.1006433, // longitude
                    new WeatherManager.CurrentWeatherHandler() {
                        @Override
                        public void onReceivedCurrentWeather(WeatherManager manager, Weather weather) {
                            // Handle current weather information
                            Log.d("METEO", weather.getNavigation().getLocationName()+" " + weather.getTemperature().getCurrent()
                                    .getValue(TemperatureUnit.CELCIUS)+" gradi C");
                            Log.d("METEO", "Percentuale Nuvole: "+weather.getCloudiness().getPercentage()+"%");
                            Log.d("METEO", "Pioggia nelle ultime 3 h: "+weather.getRain().getThreeHoursVolume());
                            Log.d("METEO", "Velocità del vento: "+weather.getWind().getSpeed());
                            Log.d("METEO", "Direzione del vento: "+weather.getWind().getDirection()+" gradi");

                        }

                        @Override
                        public void onFailedToReceiveCurrentWeather(WeatherManager manager) {
                            // Handle error
                        }
                    }


            );

  */



            //AGGIUNGO GENERI
            GeneriRepo gr = GeneriRepo.getIstance();

            gr.GeneriNotizie.add(gn_cronaca);
            gr.GeneriNotizie.add(gn_culturale);
            gr.GeneriNotizie.add(gn_ludico);
            gr.GeneriNotizie.add(gn_politico);
            gr.GeneriNotizie.add(gn_meteo);
            gr.GeneriNotizie.add(gn_sport);
            gr.GeneriNotizie.add(gn_traffico);
            gr.GeneriNotizie.add(gn_uni);


            gr.GeneriLuoghi.add(gl_Commerciali);
            gr.GeneriLuoghi.add(gl_Monumenti);
            gr.GeneriLuoghi.add(gl_Musei);
            gr.GeneriLuoghi.add(gl_Parchi);
            gr.GeneriLuoghi.add(gl_Piazze);
            gr.GeneriLuoghi.add(gl_Religioso);
            gr.GeneriLuoghi.add(gl_SempreUtili);
            gr.GeneriLuoghi.add(gl_Universitari);

            gr.GeneriEventi.add(ge_Cinema);
            gr.GeneriEventi.add(ge_Discoteche);
            gr.GeneriEventi.add(ge_Ludico);
            gr.GeneriEventi.add(ge_Musicale);
            gr.GeneriEventi.add(ge_Provincia);
            gr.GeneriEventi.add(ge_Sagra);
            gr.GeneriEventi.add(ge_Teatro);
            gr.GeneriEventi.add(ge_Uni);

            SQLAssetHelper_DB dbHelperTransport = new SQLAssetHelper_DB(MyApplication.getAppContext());
            List<Linea> autobus = dbHelperTransport.getAllLinee();
            for (Linea l:autobus){
                Log.e("bus", l.getShort_name()+" ");

                if(l.getColor()!="none"){
                    Bus b = new Bus( l.getShort_name(), l.getId(), l.getShort_name(), l.getLong_name(),
                            l.getColor()=="none" ? null : Integer.parseInt(l.getColor(), 16) );
                    gr.Autobus.add(b);
                }
            }

            /*
            String test = "brennero nord";
            List<Stop> tester = dbHelperTransport.getStopsByName(test);
            for (Stop t: tester){
                Log.e("aa", "AAAAAAAAAAA "+t.getName());
            }
            */

        }else{
            Log.d("ce","jdsdfhdfhjhdfh");
        }

        //SETTA PREFERENZE

        Preferenze myPreference = params[0];

        try {
            //InternalStorage.writeObject(this,"lolk","lolv");
            myPreference = (Preferenze) InternalStorage.readObject(myActivity);
            Log.d("lol", String.valueOf(myPreference.getPref_Notizie().size()));

        } catch (IOException e) {
            Log.d("poing", "creato2");
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

            Log.d("lol", String.valueOf(myPreference.getPref_Notizie().size()));

            try {
                InternalStorage.writeObject(myActivity,myPreference);
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        GetMyPosition myPosition = GetMyPosition.getIstanceAndUpdate(myActivity);

        myPreference.setMylat(myPosition.lat);
        myPreference.setMyLng(myPosition.lng);

        tiles.Filtra(myPreference);

        //tiles.TuttiTiles();
        return tiles;
    }



    protected void onPostExecute(TileMemoryRep result) {



        //   METEO TEST
        GetMyPosition myPos = GetMyPosition.getIstanceAndUpdate(myActivity);
        while(myPos.lat == null);
        Double lat = myPos.lat;
        Double lon = myPos.lng;

        String api_key = "97afef6a27b88c8138c824865619ff56";
        new WeatherManager(api_key).getCurrentWeatherByCoordinates(
                lat, // latitude
                lon, // longitude
                new WeatherManager.CurrentWeatherHandler() {
                    @Override
                    public void onReceivedCurrentWeather(WeatherManager manager, Weather weather) {
                        // Handle current weather information
                        Log.d("METEO", weather.getNavigation().getLocationName()+" " + weather.getTemperature().getCurrent()
                                .getValue(TemperatureUnit.CELCIUS)+" °C");
                        Log.d("METEO", "Percentuale Nuvole: "+weather.getCloudiness().getPercentage()+"%");
                        Log.d("METEO", "Pioggia nelle ultime 3 h: "+weather.getRain().getThreeHoursVolume());
                        Log.d("METEO", "Velocità del vento: "+weather.getWind().getSpeed());
                        Log.d("METEO", "Direzione del vento: "+weather.getWind().getDirection()+" gradi");

                        String place_temp = weather.getNavigation().getLocationName()+" " + String.format("%.2f", weather.getTemperature().getCurrent()
                                .getValue(TemperatureUnit.CELCIUS))+" °C";
                        String clouds = "Percentuale nuvole: "+weather.getCloudiness().getPercentage()+"%";
                        String rain = "Pioggia ultime 3h: "+weather.getRain().getThreeHoursVolume();

                        FragmentManager fragmentManager = myActivity.getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        GregorianCalendar d = new GregorianCalendar();
                        String prevision = place_temp+"\n"+clouds+"\n"+rain;

                        String imgUrl = decideWeatherImg(weather.getCloudiness().getPercentage(),weather.getRain().getThreeHoursVolume() );
                        Log.d("METEO", imgUrl);
                        Notizia fictionaryTile = new Notizia("Meteo vicino a te", prevision, imgUrl , imgUrl, new Autore("OpenWeatherMap", null, null),new Genere_Notizia("Meteo") , d);

                        //TileMemoryRep.getInstance().addNotizia(fictionaryTile);

                        fragmentTransaction.add(R.id.MeteoPosition, TileFragment.newInstance(fictionaryTile), String.valueOf(fictionaryTile.getId()));
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void onFailedToReceiveCurrentWeather(WeatherManager manager) {
                        FragmentManager fragmentManager = myActivity.getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        GregorianCalendar d = new GregorianCalendar(2017, 2, 1);
                        Tile fictionaryTile = new Notizia("Meteo vicino a te", "Informazioni sul meteo non disponibili \nControlla i tuoi permessi per la geolocalizzazione", "http://www.solegemello.net/gatto.jpg" , "http://www.solegemello.net/gatto.jpg", new Autore("OpenWeatherMap", null, null),null , d);

                        fragmentTransaction.add(R.id.MeteoPosition, TileFragment.newInstance(fictionaryTile), String.valueOf(fictionaryTile.getId()));
                        //fragmentTransaction.replace(, TileFragment.newInstance(fictionaryTile));

                        fragmentTransaction.commit();
                    }
                }

        );



        FragmentManager fragmentManager = myActivity.getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

     //   fragmentTransaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);



        for(Tile t : result.getTiles()){
            Log.d("id->", String.valueOf(t.getId())+ "  "+t.getTitolo());

            fragmentTransaction.add(R.id.linearMain, TileFragment.newInstance(t), String.valueOf(t.getId()));
        }
        fragmentTransaction.commit();

        View namebar = myActivity.findViewById(R.id.loadingPanel);

        ((ViewGroup) namebar.getParent()).removeView(namebar);

    }



    private String decideWeatherImg(Integer cloud_percentage, Double rain){
        String imgUrl="";

        if ((cloud_percentage >= 0) && (cloud_percentage <= 35) && rain < 50.0) {
            imgUrl = "http://skigd.com/wp-content/themes/wp-theme/images/Sunny.png";
        } else if ((cloud_percentage >= 0) && (cloud_percentage <= 35) && rain > 50.0) {
            imgUrl = "http://indywx.com/wp-content/uploads/2014/04/Status-weather-showers-day-icon.png";
        } else if (cloud_percentage > 35 && cloud_percentage < 70 && rain < 50.0) {
            imgUrl = "http://indywx.com/wp-content/uploads/2014/04/Status-weather-showers-day-icon.png";
        } else if (cloud_percentage > 35 && cloud_percentage < 70 && rain > 50.0) {
            imgUrl = "http://icons.iconseeker.com/ico/weather/heavy-rain.ico";
        } else if ((cloud_percentage > 70 && rain < 50.0)){
            imgUrl = "http://icons.iconseeker.com/ico/weather/heavy-rain.ico";
        }else{
            imgUrl = "https://cdn0.iconfinder.com/data/icons/weather-colored-liner/512/lightning-cloud-rain-2-128.png";
        }

        return  imgUrl;
    }

}
