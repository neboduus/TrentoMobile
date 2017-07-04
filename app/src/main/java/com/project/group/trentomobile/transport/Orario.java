package com.project.group.trentomobile.transport;

/**
 * Created by neboduus on 28/05/2017.
 */

public class Orario {

    private Integer id;
    private Double trip_id;
    private Integer stop_id;
    private Integer stop_sequence;
    private String arrival_time;
    private String departure_time;

    public Orario(Integer id, Double trip_id, Integer stop_id, Integer stop_sequence,
                  String arrival_time, String departure_time){
        this.id = id  != null ? id : -1;
        this.trip_id = trip_id != null ? trip_id : -1;
        this.stop_id = stop_id != null ? stop_id : -1;
        this.stop_sequence = stop_sequence != null ? stop_sequence : -1;
        this.arrival_time = arrival_time != null ? arrival_time : "none";
        this.departure_time = departure_time != null ? departure_time : "none";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(Double trip_id) {
        this.trip_id = trip_id;
    }

    public Integer getStop_id() {
        return stop_id;
    }

    public void setStop_id(Integer stop_id) {
        this.stop_id = stop_id;
    }

    public Integer getStop_sequence() {
        return stop_sequence;
    }

    public void setStop_sequence(Integer stop_sequence) {
        this.stop_sequence = stop_sequence;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }
}
