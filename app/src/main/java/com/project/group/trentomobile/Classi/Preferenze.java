/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.group.trentomobile.Classi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class Preferenze implements Serializable {
    
    private Map<String,Boolean> Pref_Notizie;
    private Map<String,Boolean> Pref_Eventi;
    private Map<String,Boolean> Pref_Luoghi;
    private ArrayList<Keyword> Keywords;


    public Preferenze(){
    
        Pref_Eventi=new HashMap<String, Boolean>();
        Pref_Notizie=new HashMap<String, Boolean>();
        Pref_Luoghi=new HashMap<String, Boolean>();
    }
    /**
     * @return the Pref_Notizie
     */
    public Map<String,Boolean> getPref_Notizie() {
        return Pref_Notizie;
    }

    /**
     * @param Pref_Notizie the Pref_Notizie to set
     */
    public void setPref_Notizie(Map<String,Boolean> Pref_Notizie) {
        this.Pref_Notizie = Pref_Notizie;
    }

    /**
     * @return the Pref_Luoghi
     */
    public Map<String,Boolean> getPref_Luoghi() {
        return Pref_Luoghi;
    }

    /**
     * @param Pref_Luoghi the Pref_Luoghi to set
     */
    public void setPref_Luoghi(Map<String,Boolean> Pref_Luoghi) {
        this.Pref_Luoghi = Pref_Luoghi;
    }

    public void addPref_Luoghi_Ture(String Pref_Luogho) {
        this.Pref_Luoghi.put(Pref_Luogho,Boolean.TRUE);
    }

    public void addPref_Eventi_Ture(String Pref_Evento) {
        this.Pref_Eventi.put(Pref_Evento,Boolean.TRUE);
    }

    public void addPref_Notizie_Ture(String Pref_Notizie) {
        this.Pref_Notizie.put(Pref_Notizie,Boolean.TRUE);
    }

    /**
     * @return the Pref_Eventi
     */
    public Map<String,Boolean> getPref_Eventi() {
        return Pref_Eventi;
    }

    /**
     * @param Pref_Eventi the Pref_Eventi to set
     */
    public void setPref_Eventi(Map<String,Boolean> Pref_Eventi) {
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


}
