/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.group.trentomobile.Classi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author User
 */
public class Preferenze implements Serializable {
    
    private Map<String,Integer> Pref_Notizie;
    private Map<String,Integer> Pref_Eventi;
    private Map<String,Integer> Pref_Luoghi;
    private ArrayList<Keyword> Keywords;
    private Set<Integer> IdsPreferiti;


    public Preferenze(){
    
        Pref_Eventi=new HashMap<String, Integer>();
        Pref_Notizie=new HashMap<String, Integer>();
        Pref_Luoghi=new HashMap<String, Integer>();
        IdsPreferiti = new HashSet<>();
    }
    /**
     * @return the Pref_Notizie
     */
    public Map<String,Integer> getPref_Notizie() {
        return Pref_Notizie;
    }

    /**
     * @param Pref_Notizie the Pref_Notizie to set
     */
    public void setPref_Notizie(Map<String,Integer> Pref_Notizie) {
        this.Pref_Notizie = Pref_Notizie;
    }

    /**
     * @return the Pref_Luoghi
     */
    public Map<String,Integer> getPref_Luoghi() {
        return Pref_Luoghi;
    }

    /**
     * @param Pref_Luoghi the Pref_Luoghi to set
     */
    public void setPref_Luoghi(Map<String,Integer> Pref_Luoghi) {
        this.Pref_Luoghi = Pref_Luoghi;
    }

    public void addPref_Luoghi_Ture(String Pref_Luogho) {
        this.Pref_Luoghi.put(Pref_Luogho,10);
    }

    public void addPref_Eventi_Ture(String Pref_Evento) {
        this.Pref_Eventi.put(Pref_Evento,10);
    }

    public void addPref_Notizie_Ture(String Pref_Notizie) {
        this.Pref_Notizie.put(Pref_Notizie,10);
    }

    /**
     * @return the Pref_Eventi
     */
    public Map<String,Integer> getPref_Eventi() {
        return Pref_Eventi;
    }

    /**
     * @param Pref_Eventi the Pref_Eventi to set
     */
    public void setPref_Eventi(Map<String,Integer> Pref_Eventi) {
        this.Pref_Eventi = Pref_Eventi;
    }

    /**
     * @return the Keywords
     */
    public ArrayList<Keyword> getKeywords() {
        return Keywords;
    }

    /**
     * @param Keywords the Keywords to set
     */
    public void setKeywords(ArrayList<Keyword> Keywords) {
        this.Keywords = Keywords;
    }


    public void addPreferiti(int id){
        IdsPreferiti.add(id);
    }

    public HashSet<Integer> getIdsPreferiti(){
        return (HashSet<Integer>) IdsPreferiti;
    }


}
