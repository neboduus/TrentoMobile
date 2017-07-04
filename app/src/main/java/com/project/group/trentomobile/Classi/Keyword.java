/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.group.trentomobile.Classi;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Keyword implements Serializable {
    private String name; 
    
    public Keyword(String name){
        this.name=name;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
