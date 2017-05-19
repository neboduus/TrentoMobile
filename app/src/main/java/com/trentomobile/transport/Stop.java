package com.trentomobile.transport;

public class Stop {
    private Float id;
    private String code;
    private String name;
    private String desc;
    private Float lat;
    private Float lon;

    public Stop() {
    }

    public Stop(Float id, String code, String name, String desc, Float lat, Float lon) {
        this.setCode(code);
        this.setId(id);
        this.setDesc(desc);
        this.setLat(lat);
        this.setLon(lon);
    }

    public Stop(String code, String name, String desc, Float lat, Float lon) {
        this.setCode(code != null?code:"");
        this.setId(Float.valueOf(-1.0F));
        this.setDesc(desc != null?desc:"");
        this.setLat(Float.valueOf(lat != null?lat.floatValue():-1.0F));
        this.setLon(Float.valueOf(lon != null?lon.floatValue():-1.0F));
    }

    public Float getId() {
        return this.id;
    }

    public void setId(Float id) {
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
}
