/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.group.trentomobile.Repository;

import com.project.group.trentomobile.Classi.Evento;
import com.project.group.trentomobile.Classi.Fermata;
import com.project.group.trentomobile.Classi.Luogo;
import com.project.group.trentomobile.Classi.Notizia;
import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Classi.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author User
 */
public class TileMemoryRep implements Interface_Rep{

    private static TileMemoryRep istanza =null;
    
    private ArrayList<Tile> tiles;

    private ArrayList<Notizia> Notizie;
    private ArrayList<Luogo> Luoghi;
    private ArrayList<Evento> Eventi;
    private ArrayList<Fermata> Fermate;
    
    private TileMemoryRep(){
        Eventi=new ArrayList<Evento>();
        Notizie=new ArrayList<Notizia>();
        Luoghi=new ArrayList<Luogo>();
        Fermate = new ArrayList<>();
    }
    
    public static TileMemoryRep getInstance()
    {
      if (istanza == null)
      {
        istanza = new TileMemoryRep();
      }
      return istanza; 
    }
    
    @Override
    public boolean addNotizia(Notizia notizia) {
        Notizie.add(notizia);
        return true;
    }

    public ArrayList<Tile> TuttiTiles(){

        ArrayList<Tile> ts =new ArrayList<Tile>();
        for(Notizia t : getNotizie()) if(t!=null) ts.add(t);
        for(Evento t:getEventi()) if(t!=null) ts.add(t);
        for(Luogo t:getLuoghi()) if(t!=null) ts.add(t);
        for(Fermata t:getFermate()) if(t!=null) ts.add(t);

        return ts;
    }

    public Tile getTileById(int id){
        for(Tile t: TuttiTiles()){
            if(t.getId() == id) return t;
        }
        return null;
    }

    @Override
    public void Filtra(Preferenze p) {

        tiles = new ArrayList<Tile>();

        tiles =new ArrayList<Tile>();
        Map<String,Integer> pref_tipi_eventi=p.getPref_Eventi();
        Map<String,Integer> pref_tipi_luoghi=p.getPref_Luoghi();
        Map<String,Integer> pref_tipi_notizie=p.getPref_Notizie();
   
        for(Notizia t:getNotizie())
        {
            if(pref_tipi_notizie.containsKey(t.getGenere().getTipo()) && (pref_tipi_notizie.get(t.getGenere().getTipo()) > 9)) {
                t.peso = pref_tipi_notizie.get(t.getGenere().getTipo());
                getTiles().add(t);
            } 
        }

        for(Evento t:getEventi())
        {
            
               if(pref_tipi_eventi.containsKey(t.getGenere().getTipo()) && (pref_tipi_eventi.get(t.getGenere().getTipo()) > 9)) {
                   t.peso = pref_tipi_eventi.get(t.getGenere().getTipo());
                   getTiles().add(t);
                } 
            
        }
        
        for(Luogo t:getLuoghi())
        {
            if(pref_tipi_luoghi.containsKey(t.getGenere().getTipo()) && (pref_tipi_luoghi.get(t.getGenere().getTipo()) > 9)) {
                t.peso = pref_tipi_luoghi.get(t.getGenere().getTipo());
                getTiles().add(t);
            } 
        }

        class ComparatorTiles implements Comparator<Tile> {
            @Override
            public int compare(Tile o1, Tile o2) {
                return o2.peso.compareTo(o1.peso);
            }
        }

        Collections.sort(tiles, new ComparatorTiles());


    }


    public ArrayList<Tile> getTilesFiltrati(Preferenze p) {

        ArrayList<Tile> ts = new ArrayList<Tile>();

        ts =new ArrayList<Tile>();
        Map<String,Integer> pref_tipi_eventi=p.getPref_Eventi();
        Map<String,Integer> pref_tipi_luoghi=p.getPref_Luoghi();
        Map<String,Integer> pref_tipi_notizie=p.getPref_Notizie();

        for(Notizia t:getNotizie())
        {
            if(pref_tipi_notizie.containsKey(t.getGenere().getTipo()) && (pref_tipi_notizie.get(t.getGenere().getTipo()) > 9)) {
                t.peso = pref_tipi_notizie.get(t.getGenere().getTipo());
                ts.add(t);
            }
        }

        for(Evento t:getEventi())
        {

            if(pref_tipi_eventi.containsKey(t.getGenere().getTipo()) && (pref_tipi_eventi.get(t.getGenere().getTipo()) > 9)) {
                t.peso = pref_tipi_eventi.get(t.getGenere().getTipo());
                ts.add(t);
            }

        }

        for(Luogo t:getLuoghi())
        {
            if(pref_tipi_luoghi.containsKey(t.getGenere().getTipo()) && (pref_tipi_luoghi.get(t.getGenere().getTipo()) > 9)) {
                t.peso = pref_tipi_luoghi.get(t.getGenere().getTipo());
                ts.add(t);
            }
        }

        for(Fermata t:getFermate())
        {
            ts.add(t);
        }

        class ComparatorTiles implements Comparator<Tile> {
            @Override
            public int compare(Tile o1, Tile o2) {
                return o2.peso.compareTo(o1.peso);
            }
        }

        Collections.sort(tiles, new ComparatorTiles());

        return ts;

    }

    @Override
    public boolean addEvento(Evento evento) {
        Eventi.add(evento);
        return true;
    }

    @Override
    public boolean addLuogo(Luogo luogo) {
        Luoghi.add(luogo);
        return true;
    }

    public boolean addFermata(Fermata fermata){
        Fermate.add(fermata);
        return true;
    }

    /**
     * @return the Notizie
     */
    public ArrayList<Notizia> getNotizie() {
        return Notizie;
    }

    /**
     * @return the Luoghi
     */
    public ArrayList<Luogo> getLuoghi() {
        return Luoghi;
    }

    /**
     * @return the Eventi
     */
    public ArrayList<Evento> getEventi() {
        return Eventi;
    }

    /**
     * @return the tiles
     */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }


    public ArrayList<Fermata> getFermate() {
        return Fermate;
    }

}
