package com.project.group.trentomobile.transport;

/**
 * Created by neboduus on 27/05/2017.
 */

public class Linea {
    private Integer id;
    private String short_name;
    private String long_name;
    private String color;

    public Linea(Integer id, String short_name, String long_name,  String color){
        this.setId(id != null ? id : -1);
        this.setShort_name(short_name != null ? short_name : "none");
        this.setLong_name(long_name != null ? long_name : "none");
        this.setColor(color != null ? color : "none");

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
