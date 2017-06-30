package com.project.group.trentomobile.Classi;

import java.io.Serializable;

/**
 * Created by postal on 23/05/17.
 */

public class Genere implements Serializable {
    private Integer id;
    private String tipo;
    private String foto;

    public Genere(String tipo) {
        this.id = -1;
        this.tipo = tipo;
        foto = "";
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}