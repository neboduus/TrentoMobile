/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.group.trentomobile.Repository;

import com.project.group.trentomobile.Classi.Evento;
import com.project.group.trentomobile.Classi.Luogo;
import com.project.group.trentomobile.Classi.Notizia;
import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Classi.Tile;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface Interface_Rep {
    
    public boolean addEvento(Evento evento);
    public boolean addLuogo(Luogo luogo);
    public boolean addNotizia(Notizia notizia);    
    public void Filtra(Preferenze p);
    public ArrayList<Luogo> getLuoghi();
    public ArrayList<Evento> getEventi();
    public ArrayList<Notizia> getNotizie();
    public ArrayList<Tile> getTiles();
    
}
