package com.trentomobile.transport;

import java.util.List;

public class Stop {
    private Integer id;
    private String code;
    private String name;
    private String desc;
    private Float lat;
    private Float lon;
    private Double trip_id;
    private Integer direction_id;

    private List<Linea> nextBusses;

    public Stop() {
    }

    public Stop(Integer id, String code, String name, String desc, Float lat, Float lon) {
        this.setCode(code);
        this.setId(id);
        this.setDesc(desc);
        this.setLat(lat);
        this.setLon(lon);
        this.setName(name);
    }

    public Stop(String code, String name, String desc, Float lat, Float lon) {
        this.setCode(code != null?code:"");
        this.setId(-1);
        this.setDesc(desc != null?desc:"");
        this.setLat(Float.valueOf(lat != null?lat.floatValue():-1.0F));
        this.setLon(Float.valueOf(lon != null?lon.floatValue():-1.0F));
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Float getLat() {
        return this.lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return this.lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public List<Linea> getNextBusses() {
        return nextBusses;
    }

    public void setNextBusses(List<Linea> nextBusses) {
        this.nextBusses = nextBusses;
    }

    public Double getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(Double trip_id) {
        this.trip_id = trip_id;
    }

    public Integer getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(Integer direction_id) {
        this.direction_id = direction_id;
    }
}
