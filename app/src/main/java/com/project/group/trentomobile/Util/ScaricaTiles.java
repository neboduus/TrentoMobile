package com.project.group.trentomobile.Util;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.project.group.trentomobile.Classi.*;
import com.project.group.trentomobile.MainActivity;
import com.project.group.trentomobile.R;
import com.project.group.trentomobile.Repository.*;
import com.project.group.trentomobile.SettingPreference;
import com.project.group.trentomobile.TilePK.TileFragment;
import com.project.group.trentomobile.TipoActivity;
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


            //Aggiunto luogo Piazza Dante
            Indirizzo PiazzaDuomo_indirizzo=new Indirizzo(46.0671354,11.1188445,"Piazza Duomo, 38122 Trento");
            Luogo PiazzaDuomo=new Luogo(PiazzaDuomo_indirizzo,gl_Piazze,"Piazza Duomo","Nonostante in origine si trovasse urbanisticamente decentrata rispetto a quella che era la città delimitata dalle mura, con lespandersi della zona urbana Piazza del Duomo si è trovata fisicamente, politicamente e religiosamente nel cuore del centro storico di Trento ed è stata definita una delle piazze urbane più belle e caratteristiche presenti sul territorio italiano.Su Piazza del Duomo, che ha assunto la struttura attuale nel corso del VIII secolo, si affacciano la cattedrale di San Vigilio -duomo della città- e Palazzo Pretorio; ad abbellire ulteriormente la piazza è la settecentesca fontana del Nettuno.","https://www.cultura.trentino.it/var/001/storage/images/media/images/museo-diocesano-tridentino3/16383714-1-ita-IT/Museo-diocesano-tridentino_imagefullwide.jpg","");
            tiles.addLuogo(PiazzaDuomo);

            //Aggiunto monumento Cattedrale San Vigilio
            Indirizzo CattedraleSanVigilio_indirizzo=new Indirizzo(46.0671354,11.1188445,"Cattedrale di San Vigilio, Piazza del Duomo, 38122 Trento");
            Luogo CattedraleSanVigilio=new Luogo(CattedraleSanVigilio_indirizzo,gl_Monumenti,"Cattedrale San Vigilio","La cattedrale di San Vigilio è il duomo di Trento, e Piazza del Duomo deve il suo nome proprio alla presenza di questo splendido edificio religioso. Edificata nel XIII secolo e dedicata al santo patrono della città, la cattedrale fungeva anticamente come chiesa cimiteriale: qui sono infatti conservate le spoglie di San Vigilio; col passare dei secoli, comunque, il duomo ha mantenuto questa caratteristica, ed è infatti qui che è sepolta la maggior parte dei vescovi della città. Sotto la cattedrale, inoltre, si trova la basilica paleocristiana, alla quale si accede dallinterno del duomo stesso.","http://www.medioevo.org/artemedievale/Images/TrentinoAltoAdige/Trento/DuomodiTrento/IMG_4673s.jpg","");
            tiles.addLuogo(CattedraleSanVigilio);

            //Aggiunto monumento Fontana del Nettuno
            Indirizzo FontanadelNettuno_indirizzo=new Indirizzo(46.0673885,11.120655,"Piazza Duomo, 38122 Trento");
            Luogo FontanadelNettuno=new Luogo(FontanadelNettuno_indirizzo,gl_Monumenti,"Fontana del Nettuno","Testimonianza artistica del XVIII secolo è invece la fontana del Nettuno, eseguita tra il 1767 e il 1769  dallarchitetto Francesco Antonio Giongo di Lavarone. Loriginaria statua del Nettuno, in realtà, si trova ora nel cortile del Palazzo Thun, ed è stata sostituita nel 1945 da una copia in bronzo realizzata da Davide Rigatti; ai piedi del Nettuno tritoni, cavalli marini e altre figure -tutte copie bronzee delle sculture originali- decorano la bella fontana di Piazza del Duomo.","http://www.trentoarte.it/wp-content/uploads/2014/02/fontana-nettuno-Trento-2.jpg","");
            tiles.addLuogo(FontanadelNettuno);

            //Aggiunto MOnumento Case
            Indirizzo CaseCasuffiRella_indirizzo=new Indirizzo(46.0677408,11.1194669,"Piazza Duomo, 38122 Trento");
            Luogo CaseCasuffiRella=new Luogo(CaseCasuffiRella_indirizzo,gl_Monumenti,"Case Cazuffi-Rella","Entrambi palazzi del XVI secolo, le due case sorgono una accanto allaltra e regalano al visitatore che si reca in Piazza del Duomo meravigliosi affreschi dipinti sui muri che affacciano sulla piazza. Probabilmente eseguiti da Marcello Fogolino  pittore italiano vissuto a cavallo tra il XV e il XVI secolo -, gli affreschi che decorano le facciate delle due case raffigurano personaggi e figure pagane e leggendarie, caratteristica pressoché unica se si mettono a confronto con le altre opere pittoriche dipinte a decorazione degli esterni di abitazioni e palazzi presenti nelle valli attorno a Trento, quasi tutte di carattere religioso","https://upload.wikimedia.org/wikipedia/commons/c/cd/Trento-case_Cazuffi-Rella.jpg","");
            tiles.addLuogo(CaseCasuffiRella);

            Luogo dossTrento = new Luogo(new Indirizzo(46.0732339d,11.1098908d, "Via Dòs Trento, 38121 Trento"), gl_Parchi,
                    "Parco Naturale Doss Trento",
                    "Il Doss Trento è una piccola collina che sorge sulla riva idrografica destra del fiume Adige nei pressi " +
                            "del capoluogo trentino.Si tratta di uno sperone che nel suo punto più elevato raggiunge i 309 m s.l.m., " +
                            "elevandosi di oltre cento metri rispetto al piano del fondovalle, ed è ricoperto da 8 ettari di foresta." +
                            "Assieme al Dosso di San Rocco e al Dosso Sant'Agata formano i \"tre denti\" dell'antica Tridentum romana.",
                    "http://www.catinabib.it/files/TIC511-0913.jpg",
                    "https://it.wikipedia.org/wiki/Doss_Trento");
            dossTrento.setOrario("9.00", "19.00");
            tiles.addLuogo(dossTrento);

            Luogo mausoleoCesareBattisti = new Luogo(new Indirizzo(46.0732031d,11.1112507d, "Via Dòs Trento, 38121 Trento"), gl_Monumenti,
                    "Mausoleo di Cesare Battisti",
                    "Solenne e di notevole impatto scenografico, il Mausoleo dedicato all'uomo che lottò per" +
                            " l'italianità di Trento è stato costruito nel 1935 da Ettore Fagioli, architetto veronese." +
                            "Il Mausoleo, dalla forma circolare mutuata dal mondo classico, si caratterizza per un sapiente" +
                            " gioco di contrasti: spazi pieni e spazi vuoti, giochi di luce e ombre e per la scelta dei materiali," +
                            " tutti provenienti dal Trentino. Colpisce il colonnato che si erge sul corpo di fabbrica principale: " +
                            "sedici colonne, alte più di dieci metri, formano una corona circolare che accoglie, al centro, " +
                            "l'altare sul quale poggia la grande area tombale commemorativa. Tre grandi aperture conducono " +
                            "all'interno del monumento, dove nell'ipogeo è posta la cella che custodisce l'arca con le spoglie " +
                            "di Battisti.",
                    "https://lh6.googleusercontent.com/-hcLg67M_LGc/V6b_UC7wLkI/AAAAAAAArrc/uGr1MHVRKdUslx-RCzYAjdpCNrY0vaBGACJkC/w408-h302-k-no/",
                    "http://www.comune.trento.it/Aree-tematiche/Turismo/Visitare/Altri-siti-di-interesse-storico-artistico/Mausoleo-di-Cesare-Battisti");
            mausoleoCesareBattisti.setOrario("9.00", "19.00");
            tiles.addLuogo(mausoleoCesareBattisti);

            Luogo museoAlpini = new Luogo(new Indirizzo(46.0727413d,11.1115806d, "Via Brescia, 1, 38100 Trento"), gl_Musei,
                    "Museo Storico Delle Truppe Alpine",
                    "L'idea di costruire un complesso in onore del Corpo degli Alpini fu della Legione Trentina, " +
                            "con il sostegno del Comando Superiore delle Truppe Alpine e dell'Associazione Nazionale " +
                            "Alpini. La proposta fu accolta nel 1938 dal Governo Italiano che istituì la \"Fondazione " +
                            "Acropoli Alpina\". La dislocazione era prevista sul Doss Trento. Una scalinata conduce all'entrata, " +
                            "vegliata da due cannoni da 47/32 e da un pezzo da 100/17. All'interno è raccolta la storia del Corpo degli Alpini, " +
                            "dalla sua fondazione ai giorni nostri, mentre trofei, armi e cimeli sono esposti in nicchie e vetrine. ",
                    "https://media-cdn.tripadvisor.com/media/photo-s/0b/56/07/e9/museo-degli-alpini.jpg",
                    "http://www.museonazionalealpini.it/");
            museoAlpini.setOrario("9.00","16.30");
            tiles.addLuogo(museoAlpini);

            Luogo galleriePiedicastello = new Luogo(new Indirizzo(46.0719687d,11.110892d, "Piazza di Piedicastello, 38122 Trento"), gl_Musei,
                    "Le Gallerie di Piedicastello",
                    "Le Gallerie facevano parte della tangenziale ovest fino all'ottobre del 2007, ma in seguito alla costruzione" +
                            " di altre due nuove gallerie, queste sono state convertite nell'agosto 2008 in uno spazio museale " +
                            "dedicato principalmente alla storia e alla memoria. Entrambe le gallerie offrono una superficie " +
                            "complessiva di oltre 6000 metri quadri suddivisi nella galleria nera e quella bianca",
                    "http://th.travelblog.it/X1W0WdWdHnu8MYw3Hg90k_pKClg=/fit-in/655x437/http://media.travelblog.it/t/tre/trento-centro-storico-e-gallerie-piedicastello/gallerietrento.jpg",
                    "http://www.centenario1914-1918.it/it/2014/06/18/le-gallerie-di-piedicastello");
            galleriePiedicastello.setOrario("9.00", "19.00");
            tiles.addLuogo(galleriePiedicastello);







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

                tiles.addFermata(new Fermata(s.getId(),s.getName(),corpo,"https://png.icons8.com/bus/color/50",null,indirizzo));
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

            Intent myIntent = new Intent(myActivity, SettingPreference.class);
            myActivity.startActivity(myIntent);


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
