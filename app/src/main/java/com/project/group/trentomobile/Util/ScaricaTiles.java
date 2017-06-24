package com.project.group.trentomobile.Util;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.project.group.trentomobile.Classi.Autore;
import com.project.group.trentomobile.Classi.Bus;
import com.project.group.trentomobile.Classi.Evento;
import com.project.group.trentomobile.Classi.Fermata;
import com.project.group.trentomobile.Classi.Genere_Evento;
import com.project.group.trentomobile.Classi.Genere_Luogo;
import com.project.group.trentomobile.Classi.Genere_Notizia;
import com.project.group.trentomobile.Classi.Indirizzo;
import com.project.group.trentomobile.Classi.Luogo;
import com.project.group.trentomobile.Classi.Notizia;
import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Classi.Tile;
import com.project.group.trentomobile.R;
import com.project.group.trentomobile.Repository.GeneriRepo;
import com.project.group.trentomobile.Repository.TileMemoryRep;
import com.project.group.trentomobile.SettingPreference;
import com.project.group.trentomobile.TilePK.TileFragment;
import com.project.group.trentomobile.assetsHelper.SQLAssetHelper_DB;
import com.project.group.trentomobile.context.MyApplication;
import com.project.group.trentomobile.transport.Linea;
import com.project.group.trentomobile.transport.Orario;
import com.project.group.trentomobile.transport.Stop;
import com.project.group.trentomobile.transport.Trip;
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
            Genere_Notizia gn_cronaca = new Genere_Notizia("Cronaca");
            gn_cronaca.setFoto("http://www.guidavalencia.com/images/categorie/guida_valencia/categoria_feed_giornali.jpg");
            Genere_Notizia gn_culturale = new Genere_Notizia("Culturale");
            gn_culturale.setFoto("http://c2.vgtstatic.com/thumbll/3/1/31057-v3/mausoleo-di-cesare-battisti-1.jpg");
            Genere_Notizia gn_uni = new Genere_Notizia("Universitari");
            gn_uni.setFoto("http://www.corriere.it/Media/Foto/2009/07/24/1972608--180x140.jpg");
            Genere_Notizia gn_traffico = new Genere_Notizia("Traffico");
            gn_traffico.setFoto("http://www.estense.com/wp-content/uploads/2012/05/traffico1.jpg");
            Genere_Notizia gn_politico = new Genere_Notizia("Politico");
            gn_politico.setFoto("http://www.datagiovani.it/newsite/wp-content/uploads/2013/01/parlamento-italiano.jpg");
            Genere_Notizia gn_sport = new Genere_Notizia("Sport");
            gn_sport.setFoto("http://www.alltrainer.it/articoli/guarire-con-lo-sport.jpg");
            Genere_Notizia gn_ludico = new Genere_Notizia("Ludico");
            gn_ludico.setFoto("http://images.nymag.com/listings/bar/dakota-bar-main.jpg");

            Genere_Notizia gn_meteo = new Genere_Notizia("Meteo");
            gn_meteo.setFoto("http://www.verdeazzurronotizie.it/wp-content/uploads/2016/06/meteo-icon.png");

            Genere_Luogo gl_Piazze = new Genere_Luogo("Piazze");
            gl_Piazze.setFoto("http://radionbc.it/wp-content/uploads/2015/09/DANTE.jpg");
            Genere_Luogo gl_Musei = new Genere_Luogo("Musei");
            gl_Musei.setFoto("https://images.placesonline.com/photos/67251_trento_muse_museo_di_scienze_naturali_di_trento.jpg");
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
            ge_Sagra.setFoto("http://image.afcdn.com/dossiers/D20130801/festa-della-castagna-alto-adige-103725_L.jpg");
            Genere_Evento ge_Provincia = new Genere_Evento("Provinciali");
            ge_Provincia.setFoto("http://eventi.fmach.it/var/ezflow_site/storage/images/future-ipm/organizers/patronage/provincia-autonoma-di-trento/5895-1-eng-GB/Provincia-autonoma-di-Trento_popup.jpg");
            Genere_Evento ge_Ludico = new Genere_Evento("Ludico");
            ge_Ludico.setFoto("http://25bb7f8ea083f526b4cb-34dd027d44c74b053dd2b880a7326734.r32.cf1.rackcdn.com/responsive/575/25bb7f8ea083f526b4cb-34dd027d44c74b053dd2b880a7326734.r32.cf1.rackcdn.com/lps/assets/u/B-Bar-four-points-by-sheraton-bolzano.jpg");
            Genere_Evento ge_Discoteche = new Genere_Evento("Discoteche");
            ge_Discoteche.setFoto("https://image.freepik.com/free-vector/disco-background-design_1314-96.jpg");
            Genere_Evento ge_Cinema = new Genere_Evento("Cinema");
            ge_Cinema.setFoto("http://ilgiornaleoff.ilgiornale.it/wp-content/uploads/2017/03/cinema.jpg");
            Genere_Evento ge_Musicale = new Genere_Evento("Musicale");
            ge_Musicale.setFoto("https://thumbs.dreamstime.com/x/fondo-musicale-per-progettazione-di-evento-di-musica-42859418.jpg");
            Genere_Evento ge_Teatro = new Genere_Evento("Teatro");
            ge_Teatro.setFoto("http://giornaledelladanza.com/home/wp-content/uploads/Teatro-alla-Scala-di-Milano-480x263.jpg");
            Genere_Evento ge_Uni = new Genere_Evento("Universitario");
            ge_Uni.setFoto("https://www.unocero.com/wp-content/uploads/2013/10/Tour-universitario-telcel-2013.jpg");




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


            GregorianCalendar d2 = new GregorianCalendar(2017, 5, 12);

            //Aggiunto luogo Piazza Dante
            Indirizzo PiazzaDuomo_indirizzo=new Indirizzo(46.0671354,11.1188445,"Piazza Duomo, 38122 Trento");
            Luogo PiazzaDuomo=new Luogo(PiazzaDuomo_indirizzo,gl_Piazze,"Piazza Duomo","Nonostante in origine si trovasse urbanisticamente decentrata rispetto a quella che era la città delimitata dalle mura, con lespandersi della zona urbana Piazza del Duomo si è trovata fisicamente, politicamente e religiosamente nel cuore del centro storico di Trento ed è stata definita una delle piazze urbane più belle e caratteristiche presenti sul territorio italiano.Su Piazza del Duomo, che ha assunto la struttura attuale nel corso del VIII secolo, si affacciano la cattedrale di San Vigilio -duomo della città- e Palazzo Pretorio; ad abbellire ulteriormente la piazza è la settecentesca fontana del Nettuno.","https://www.cultura.trentino.it/var/001/storage/images/media/images/museo-diocesano-tridentino3/16383714-1-ita-IT/Museo-diocesano-tridentino_imagefullwide.jpg","");
            tiles.addLuogo(PiazzaDuomo);

            //Aggiunto monumento Cattedrale San Vigilio
            Indirizzo CattedraleSanVigilio_indirizzo=new Indirizzo(46.0671354,11.1188445,"Cattedrale di San Vigilio, Piazza del Duomo, 38122 Trento");
            Luogo CattedraleSanVigilio=new Luogo(CattedraleSanVigilio_indirizzo,gl_Religioso,"Cattedrale San Vigilio","La cattedrale di San Vigilio è il duomo di Trento, e Piazza del Duomo deve il suo nome proprio alla presenza di questo splendido edificio religioso. Edificata nel XIII secolo e dedicata al santo patrono della città, la cattedrale fungeva anticamente come chiesa cimiteriale: qui sono infatti conservate le spoglie di San Vigilio; col passare dei secoli, comunque, il duomo ha mantenuto questa caratteristica, ed è infatti qui che è sepolta la maggior parte dei vescovi della città. Sotto la cattedrale, inoltre, si trova la basilica paleocristiana, alla quale si accede dallinterno del duomo stesso.","http://www.medioevo.org/artemedievale/Images/TrentinoAltoAdige/Trento/DuomodiTrento/IMG_4673s.jpg","");
            tiles.addLuogo(CattedraleSanVigilio);

            //Aggiunto monumento Fontana del Nettuno
            Indirizzo FontanadelNettuno_indirizzo=new Indirizzo(46.0673885,11.120655,"Piazza Duomo, 38122 Trento");
            Luogo FontanadelNettuno=new Luogo(FontanadelNettuno_indirizzo,gl_Monumenti,"Fontana del Nettuno","Testimonianza artistica del XVIII secolo è invece la fontana del Nettuno, eseguita tra il 1767 e il 1769  dall'architetto Francesco Antonio Giongo di Lavarone. L'originaria statua del Nettuno, in realtà, si trova ora nel cortile del Palazzo Thun, ed è stata sostituita nel 1945 da una copia in bronzo realizzata da Davide Rigatti; ai piedi del Nettuno tritoni, cavalli marini e altre figure -tutte copie bronzee delle sculture originali- decorano la bella fontana di Piazza del Duomo.","http://www.trentoarte.it/wp-content/uploads/2014/02/fontana-nettuno-Trento-2.jpg","");
            tiles.addLuogo(FontanadelNettuno);

            //Aggiunto MOnumento Case
            Indirizzo CaseCasuffiRella_indirizzo=new Indirizzo(46.0677408,11.1194669,"Piazza Duomo, 38122 Trento");
            Luogo CaseCasuffiRella=new Luogo(CaseCasuffiRella_indirizzo,gl_Monumenti,"Case Cazuffi-Rella","Entrambi palazzi del XVI secolo, le due case sorgono una accanto all'altra e regalano al visitatore che si reca in Piazza del Duomo meravigliosi affreschi dipinti sui muri che affacciano sulla piazza. Probabilmente eseguiti da Marcello Fogolino, pittore italiano vissuto a cavallo tra il XV e il XVI secolo -, gli affreschi che decorano le facciate delle due case raffigurano personaggi e figure pagane e leggendarie, caratteristica pressoché unica se si mettono a confronto con le altre opere pittoriche dipinte a decorazione degli esterni di abitazioni e palazzi presenti nelle valli attorno a Trento, quasi tutte di carattere religioso","https://upload.wikimedia.org/wikipedia/commons/c/cd/Trento-case_Cazuffi-Rella.jpg","");
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

            //Aggiunto Biblioteca UNiversitaria Centrale
            Indirizzo Buc_indirizzo=new Indirizzo(46.0596899,11.1132797,"Via Adalberto Libera, 38121 Trento");
            Luogo Buc=new Luogo(Buc_indirizzo,gl_Universitari,"B.U.C.","La Biblioteca Universitaria Centrale (BUC) è al servizio dell'attività didattica e di ricerca dei dipartimenti di area socio-economica, giuridica e umanistica. L'edificio, progettato da Renzo Piano, è di recentissima costruzione ed è articolato in sette piani distribuiti in quattro corpi di fabbrica. La maggior parte dei libri è collocata a scaffale aperto (è cioè accessibile direttamente dagli utenti) in quindici sale, ordinata per disciplina secondo la Classificazione decimale Dewey. ","http://www.luniversitario.it/wp-content/uploads/2016/11/buc.jpg","http://www.biblioteca.unitn.it/biblioteca-universitaria-centrale-buc");
            Buc.setOrario("8.00","23.45");
            tiles.addLuogo(Buc);


            //Aggiungo Notizie
            Notizia notiza_traffico=new Notizia("Incidente all'altezza di Levico, un 38enne è rimasto ferito",
                    "Traffico deviato inseguito a un incidente, questa mattina, sulla Valsugana, in direzione Trento, all'altezza di Levico.",
                    "http://www.ladige.it/sites/www.ladige.it/files/styles/798x457/public/incidente%20levico.jpg?itok=1kpUSj1J",
                    "http://www.ladige.it/territori/valsugana-primiero/2017/06/22/incidente-allaltezza-levico-38enne-rimasto-ferito",a,gn_traffico,new GregorianCalendar());
            tiles.addNotizia(notiza_traffico);

            //Aggiunto evento universitario
            Evento Convegno=new Evento("Industrial problem solving with physics",ge_Uni,"Industrial Problem Solving with Physics (IPSP) è un evento della durata di una settimana, organizzato dal Dipartimento di Fisica, dalla Scuola di Dottorato in Fisica e dalla Divisione Supporto Ricerca Scientifica e Trasferimento Tecnologico dellUniversità di Trento, in collaborazione con Confindustria Trento e Polo Meccatronica - Trentino Sviluppo. Scopo dell'evento è quello di promuovere la connessione tra il mondo della ricerca in fisica e il mondo delle imprese. I giovani ricercatori avranno lopportunità di mettere alla prova le proprie conoscenze e capacità, mentre le aziende sperimenteranno lopportunità unica di collaborare con cervelli di talento.","https://d2rhekw5qr4gcj.cloudfront.net/img/400sqf/from/uploads/course_photos/3146044000150629230132.jpg","http://events.unitn.it/ipsp2017");
            Convegno.setData(new GregorianCalendar(2017, 8, 1, 10, 00));
            Convegno.setIndirizzo(new Indirizzo(46.067783d,11.1147857d,"Via Tommaso Gar, 14, 38122 Trento" ));
            tiles.addEvento(Convegno);

            //Aggiunto evento Teatro
            Evento teatro=new Evento("Casa de Tabau",ge_Teatro,"Chi avrebbe mai detto che quella casa potesse andare cosi lontano. In una giornata iniziata come quella di ieri, non ci si poteva aspettare tanto. La cosa certa è che unincontro importante arriva dopo un lungo viaggio e così è stato.\n" +
                    "Due Mondi.\n" +
                    "Lui con la sua casa viaggiante, lei con la sua casa sonora.\n" +
                    "Lui con il suo silenzio impacciato e morbido, lei con le sue abitudini precise e ritmiche.\n" +
                    "Il loro incontro davanti allo sguardo del pubblico li rende ancora più assurdi, più fragili a tal punto che i martelli ballano per loro. Vedersi dalla terrazza ma non riuscire a trovarsi davanti alla porta, pulire casa, sognare, far colazione, darsi appuntamento sotto un lampione sopra una sedia e salutarsi.\n" +
                    "Casa de Tábua è un incontro inaspettato tra suono e gesto che rivela il lato comico delluomo nel quotidiano.\n" +
                    "Casa de Tábua è un luogo immaginario ma per noi così reale, che se un giorno riprendessimo quel viaggio lo vorremo portare appresso proprio come le nostre case.",
                    "http://teatrocart.com/wp-content/uploads/2013/04/casa-ita.jpg","http://teatrocart.com/casa-de-tabua/");
            teatro.setIndirizzo(new Indirizzo(46.0677826d,11.1082196d,"Via Oss Mazzurana, 19, 38122 Trento"));
            teatro.setData(new GregorianCalendar(2017, 9, 8, 16, 30));
            tiles.addEvento(teatro);

            //Aggiunto evento Musicale
            Evento Concerto=new Evento("Concerto di Zucchero",ge_Musicale,"Il grande cantante e cantautore italiano Zucchero arriverà per la prima volta nella città di Trento. L evento si terrà al quartiere delle Albere Trento città. Affrettatevi a comprare i biglietti perché il ricavato andra tutto in beneficenza.","https://www.campioniomaggiogratuiti.it/wp-content/uploads/2016/10/Vinci-gratis-concerto-di-Zucchero-e-viaggio-300x157.jpg","");
            Concerto.setData(new GregorianCalendar(2017,7,25,21,00));
            Concerto.setIndirizzo(new Indirizzo(46.067782d,11.1082196d,"38121 Trento"));
            tiles.addEvento(Concerto);

            //Aggiunto evento Cinema
            Evento Cinema=new Evento("1° Mercoledì del mese, cinema a 2 euro",ge_Cinema,"Vieni a vedere un film ogni primo Mercoledì del mese e ti costerà solo 2 euro. In tutte le sale dei cinema di Trento!","http://www.trentoblog.it/wp-content/uploads/logo-mediaworld300.jpg","http://www.mymovies.it/cinema/trento/");
            Cinema.setIndirizzo(new Indirizzo(46.0614078d,11.1227692d,"Corso 3 Novembre 1918, 35, 38122 Trento"));
            Cinema.setData(new GregorianCalendar(2017, 7, 29));
            tiles.addEvento(Cinema);


//EVENTO SAGRA
            Evento notteBianca = new Evento("Notte Bianca - MAGICA NOTTE - Feste Vigiliane", ge_Sagra,
                    "Dedicate a San Vigilio, patrono di Trento, le Feste Vigiliane sono giunte alla 34ª " +
                            "edizione e propongono come ogni anno rievocazioni storiche in costume, eventi " +
                            "musicali, animazione per bambini e proposte enogastronomiche. Cinque giorni di " +
                            "festa destinati, ancora una volta, a riversare un fiume di persone lungo le vie cittadine e " +
                            "ad offrire a residenti e turisti un fitto calendario di spettacoli musicali e teatrali, disfide " +
                            "in costume, eventi espositivi, golose proposte per il palato. Ecco le \"Feste Vigiliane\" di Trento " +
                            "che andranno in scena, quest'anno per la 34ª edizione, da giovedì 22 a lunedì 26 giugno.",
                    "http://trentinocorrierealpi.gelocal.it/polopoly_fs/1.11643835.1434745349!/httpImage/image.jpg_gen/derivatives/detail_558/image.jpg",
                    "http://festevigiliane.it/");
            notteBianca.setIndirizzo(new Indirizzo(46.0804401d,11.050316d,"Trento - Centro Storico"));
            notteBianca.setData(new GregorianCalendar(2017, 6, 24, 0, 0));
            tiles.addEvento(notteBianca);

//EVENTO PROVINCIA
            Evento rinascimento = new Evento("Ordine e bizzarria. Il Rinascimento di Marcello Fogolino", ge_Provincia,
                    "La rassegna sarà inaugurata venerdì 7 luglio al Castello del Buonconsiglio,vuole far conoscere " +
                            "al grande pubblico un pittore che fu costretto ad una forzata permanenza in Trentino, in " +
                            "quanto esiliato dalla Repubblica di Venezia con la pesante accusa di assassinio, ma che " +
                            "riuscì a guadagnarsi, con la sua opera, la fiducia del Principe Vescovo Bernardo Cles fino " +
                            "a divenirne il pittore di corte.",
                    "http://www.welcometorome.net/publicimages/full/rinascimento-a-roma(1).jpg",
                    "https://www.buonconsiglio.it/index.php/Castello-del-Buonconsiglio/mostre/Calendario-mostre/ORDINE-E-BIZZARRIA.-Il-Rinascimento-di-Marcello-Fogolino");
            notteBianca.setIndirizzo(new Indirizzo(46.070312d,11.1247753d,"Castello del Buonconsiglio"));
            notteBianca.setData(new GregorianCalendar(2017, 7, 7, 12, 0));
            tiles.addEvento(rinascimento);


//EVENTO LUDICO
            Evento mercolediUniversitario = new Evento("UNI.MADNESS -IL MERCOLEDÌ UNIVERSITARIO", ge_Ludico,
                    "A Trento è tornata la festa degli universitari che ha richiamato " +
                            "tantissimi giovani  per una serata all'insegna del divertimento e della musica.",
                    "http://www.cosafareintrentino.it/_files/f_media/2017/03/10890.jpg",
                    "http://www.cosafareintrentino.it/it/evento/uni-madness-il-mercoledi-universitario-5");
            notteBianca.setIndirizzo(new Indirizzo(46.0701256d,11.1227607d,"La Cantinotta"));
            notteBianca.setData(new GregorianCalendar(2017, 7, 10, 23, 00));
            tiles.addEvento(mercolediUniversitario);

//EVENTO DISCO
            Evento festaElysee = new Evento("The Last Night - Elysee", ge_Discoteche,
                    "Il grande evento di chiusura di uno dei punti di riferimento della movida trentina.\n" +
                            "\n" +
                            "⚫ Special guest ⭐ANDREA DAMANTE⭐\n" +
                            "⚫ Support dj LA ROCCA BROTHERS\n" +
                            "⚫ Support dj EMILIANO MARIGNONI from AfterSide\n" +
                            "⚫ Resident voice SILVER",
                    "http://www.radioetv.it/newsnet/thumbs/thumb_95964.jpg",
                    "http://www.discoteca.it/trento/locali/elysee");
            notteBianca.setIndirizzo(new Indirizzo(46.0641523d,11.0858639d,"Elysee Disco"));
            notteBianca.setData(new GregorianCalendar(2017, 7, 10, 23, 00));
            tiles.addEvento(festaElysee);

            // Luogo Sempre Utile
            Luogo luogoProvincia = new Luogo(new Indirizzo(46.0715306d,11.120544d, "Piazza Dante, 15, 38122 Trento"), gl_SempreUtili,
                    "Provincia Autonoma di Trento - Sede",
                    "La provincia autonoma di Trento , meglio nota come Trentino, è una provincia italiana " +
                            "del Trentino-Alto Adige di 538 223 abitanti[1], con capoluogo Trento. Confina a " +
                            "nord con la provincia autonoma di Bolzano, a est e a sud con le province venete " +
                            "di Belluno, Vicenza e Verona, e a ovest con le province lombarde di Brescia e Sondrio.",
                    "http://www.autonomia.provincia.tn.it/binary/pat_autonomia/stemma_ufficiale/aq_color.1159948281.1236767128.jpg",
                    "http://www.provincia.tn.it/");
            luogoProvincia.setOrario("9.00","13.30");
            tiles.addLuogo(luogoProvincia);

            //LuogoCommerciale
            Luogo luogoTopCenter = new Luogo(new Indirizzo(46.0715306d,11.120544d, "Via del Brennero, 320, 38121 Trento"), gl_Commerciali,
                    "Centro Commerciale Top Center",
                    "Grande centro commerciale a Trento nord. Trovi bellissimi negozi ed è il luogo ideale per abbracciare nuovi trendi scoprire i nuovi marchi italiani ma anche prodotti alimentari.",
                    "http://radionbc.it/wp-content/uploads/2016/01/9758809.jpg",
                    "http://topcenter.eu/negozi/");
            luogoProvincia.setOrario("8.00","20.00");
            tiles.addLuogo(luogoTopCenter);

            //NOTIZIA LUDICO
            tiles.addNotizia(new Notizia("Caccia alle onde gravitazionali nello spazio: c'è anche l'Università di Trento",
                    "Tre sonde saranno lanciate a circa 50 milioni di chilometri dalla Terra. Soddisfazione anche a Trento " +
                            "dove lavora Stefano Vitale, tra i protagonisti del progetto",
                    "http://images2.corriereobjects.it/methode_image/2016/02/09/Scienze/Foto%20Scienze%20-%20Trattate/20sci01f2-kciG-U43150714147506vzC-593x443@Corriere-Web-Sezioni.JPG",
                    "http://www.trentotoday.it/cronaca/onde-gravitazionali-lisa-unitn.html",
                    new Autore("Trento Today","tn","tn"), gn_ludico, new GregorianCalendar(2017, 7, 24)));

            //NOTIZIA CULTURALE
            tiles.addNotizia(new Notizia("Festival di Trento. Curare i migranti fa bene all'economia",
                    "Investire uno per risparmiare 100 fra qualche tempo. È decisamente un buon investimento " +
                            "garantire cure adeguate agli immigrati che arrivano nel nostro Paese. Le motivazioni " +
                            "morali dovrebbero essere scontate. Ma in tempi di demagogia imperante, sono utili anche " +
                            "i calcoli economici. Se n'è parlato in uno degli oltre 100 incontri organizzati nell'ambito del " +
                            "Festival dell'Economia di Trento, curato da Tito Boeri e promosso da Editori Laterza e Provincia " +
                            "autonoma di Trento.“Non curare adeguatamente chi arriva nel nostro Paese fuggendo da guerre, " +
                            "cambiamenti climatici e miseria, è, oltre che un tradimento dei principi della nostra Costituzione, " +
                            "un pessimo investimento” ha spiegato Loris De Filippi, presidente di Medici Senza Frontiere. " +
                            "“Infatti si pensa di risparmiare ma in realtà si rinvia il costo a quando patologie di facile " +
                            "cura peggiorano, richiedendo in un secondo momento interventi ben più complessi e più ingenti”.",
                    "http://www.educational.rai.it/materiali/immagini_articoli/23733.png",
                    "https://www.avvenire.it/attualita/pagine/curare-i-migranti-fa-bene-all-economia?utm_content=buffer42433&utm_medium=social&utm_source=facebook.com&utm_campaign=buffer",
                    new Autore("Trento Today","tn","tn"), gn_culturale, new GregorianCalendar(2017, 7, 7)));


            //notizia POLITICA
            tiles.addNotizia(new Notizia("Mobilità sostenibile, DDL popolare affossato. L'amarezza del Comitato: \"Manca volontà politica\"",
                    "L'operazione di by-pass compiuta tra ieri e oggi dal Consiglio provinciale, che ha approvato il " +
                            "DDL sulla mobilità dell'assessore Gilmozzi per poi annullare la discussione su quello di " +
                            "iniziativa popolare (nonostante la cronologia inversa: il DDL popolare era il  numero 58, " +
                            "quello giuntale il 177) non va giù al Comitato Mobilità Sostenibile in Trentino.\n" +
                            "Il Comitato aveva espressamente richiesto che la votazione sul ddl popolare si facesse - " +
                            "spiegano i rpromotori -. La scelta di evitare la votazione dimostra mancanza di assunzione " +
                            "di responsabilità da parte dei rappresentanti istituzionali nei confronti dell’azione popolare. " +
                            "La volontà politica di approvare la linea proposta dall’Assessore è stata nettamente condivisa. " +
                            "Bisognava quindi avere il coraggio di bocciare in modo esplicito e trasparente il disegno di legge " +
                            "di iniziativa popolare sottoscritto da circa 3.600 cittadine e cittadini che nel suo complesso " +
                            "intendeva non solo prefigurare uno scenario di mobilità sostenibile per il Trentino, ma renderlo " +
                            "effettivo.",
                    "https://media-cdn.tripadvisor.com/media/photo-s/07/52/0a/71/funivia-trento-sardagna.jpg",
                    "http://www.trentotoday.it/politica/mobilita-sostenibile-ddl-popolare-gilmozzi.html",
                    new Autore("Trento Today","tn","tn"), gn_culturale, new GregorianCalendar(2017, 7, 7)));

            // notizia sport
            Notizia notizia_sport=new Notizia("Partita  importantissima questa sera per l'acquila",
                    "Possiamo giocarci tutte le nostre carte, perché nulla è già deciso. Quando ieri pomeriggio i " +
                            "giocatori della Dolomiti Energia si " +
                            "sono ritrovati al PalaTrento per la consueta sessione video prima di un leggero allenamento " +
                            "(tiri liberi compresi), Maurizio Buscaglia li ha guardati in volto uno ad uno. E li ha catechizzati: " +
                            "Trento ha ancora le proprie chance di vincere il titolo. Di questo devono convincersi tutti: squadra, " +
                            "staff e pubblico.",
                    "http://www.ladige.it/sites/www.ladige.it/files/styles/798x457/public/gara6bis.png?itok=0cs00DEN",
                    "http://www.ladige.it/sport/basket/2017/06/20/stasera-tutti-laquila-o-si-vince-o-finita" ,a,gn_sport,new GregorianCalendar(2017,07,5,21,15));
            tiles.addNotizia(notizia_sport);



            //FILTRAGGIO FERMATE BUS

            GetMyPosition myPosition = GetMyPosition.getIstanceAndUpdate(myActivity);

            while(myPosition.lat == null); //ASPETTA FINCHE NON HA LA POSIZIONE

            SQLAssetHelper_DB  sqlDB = new SQLAssetHelper_DB(MyApplication.getAppContext());


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




            //AGGIUNGO GENERI
            GeneriRepo gr = GeneriRepo.getIstance();

            gr.GeneriNotizie.add(gn_cronaca);
            gr.GeneriNotizie.add(gn_culturale);
            gr.GeneriNotizie.add(gn_politico);
            //gr.GeneriNotizie.add(gn_meteo);
            gr.GeneriNotizie.add(gn_sport);
            gr.GeneriNotizie.add(gn_traffico);
            gr.GeneriNotizie.add(gn_uni);
            gr.GeneriNotizie.add(gn_ludico);


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
            gr.GeneriEventi.add(ge_Musicale);
            gr.GeneriEventi.add(ge_Provincia);
            gr.GeneriEventi.add(ge_Sagra);
            gr.GeneriEventi.add(ge_Teatro);
            gr.GeneriEventi.add(ge_Uni);
            gr.GeneriEventi.add(ge_Ludico);

            SQLAssetHelper_DB dbHelperTransport = new SQLAssetHelper_DB(MyApplication.getAppContext());
            List<Linea> autobus = dbHelperTransport.getAllLinee();
            for (Linea l:autobus){
                Log.e("bus", l.getShort_name()+" ");

                if(l.getColor()!="none"){
                    Bus b = new Bus( l.getShort_name(), l.getId(), l.getShort_name(), l.getLong_name(),
                            l.getColor());
                    gr.Autobus.add(b);
                }
            }


        }else{
            Log.d("ce","jdsdfhdfhjhdfh");
        }


        Preferenze myPreference = params[0];

        try {
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
                        Log.d("METEO", "Pioggia nelle ultime 3 ore: "+weather.getRain().getThreeHoursVolume());
                        Log.d("METEO", "Velocità del vento: "+weather.getWind().getSpeed());
                        Log.d("METEO", "Direzione del vento: "+weather.getWind().getDirection()+" gradi");

                        String place_temp = weather.getNavigation().getLocationName()+" " + String.format("%.2f", weather.getTemperature().getCurrent()
                                .getValue(TemperatureUnit.CELCIUS))+" °C";
                        String clouds = "Percentuale nuvole: "+weather.getCloudiness().getPercentage()+"%";
                        String rain = "Pioggia ultime 3 ore: "+weather.getRain().getThreeHoursVolume();

                        FragmentManager fragmentManager = myActivity.getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        GregorianCalendar d = new GregorianCalendar();
                        String prevision = place_temp+"\n"+clouds+"\n"+rain;

                        String imgUrl = decideWeatherImg(weather.getCloudiness().getPercentage(),weather.getRain().getThreeHoursVolume() );
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