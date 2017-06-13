package com.project.group.trentomobile.transport;

import java.util.List;

public class Stop {
    private Integer id;
    private String code;
    private String name;
    private String desc;
    private Double lat;
    private Double lon;
    private Double trip_id;
    private Integer direction_id;

    private List<Linea> nextBusses;

    public Stop() {
    }

    public Stop(Integer id, String code, String name, String desc, Double lat, Double lon) {
        this.setCode(code);
        this.setId(id);
        this.setDesc(desc);
        this.setLat(lat);
        this.setLon(lon);
        this.setName(name);
    }

    public Stop(String code, String name, String desc, Double lat, Double lon) {
        this.setCode(code != null?code:"");
        this.setId(-1);
        this.setDesc(desc != null?desc:"");
        this.setLat(Double.valueOf(lat != null?lat.doubleValue():-1.0D));
        this.setLon(Double.valueOf(lon != null?lon.doubleValue():-1.0D));
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

    public Double getLat() {
        return this.lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return this.lon;
    }

    public void setLon(Double lon) {
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
