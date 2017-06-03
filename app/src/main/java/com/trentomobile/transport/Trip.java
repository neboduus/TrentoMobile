package com.trentomobile.transport;

/**
 * Created by neboduus on 28/05/2017.
 */

public class Trip {
    private Integer id;
    private Integer route_id;
    private Double trip_id;
    private String trip_headsign;
    private Boolean direction_id;

    public Trip(Integer id, Integer route_id, Double trip_id,
                String trip_headsign, Boolean direction_id){
        this.id = id  != null ? id : -1;
        this.trip_id = trip_id != null ? trip_id : -1;
        this.route_id = route_id != null ? route_id : -1;
        this.trip_headsign = trip_headsign != null ? trip_headsign : "none";
        this.direction_id = direction_id != null ? direction_id : false;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Integer route_id) {
        this.route_id = route_id;
    }

    public Double getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(Double trip_id) {
        this.trip_id = trip_id;
    }

    public String getTrip_headsign() {
        return trip_headsign;
    }

    public void setTrip_headsign(String trip_headsign) {
        this.trip_headsign = trip_headsign;
    }

    public Boolean getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(Boolean direction_id) {
        this.direction_id = direction_id;
    }
}
