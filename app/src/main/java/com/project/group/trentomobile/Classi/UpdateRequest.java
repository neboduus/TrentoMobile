package com.project.group.trentomobile.Classi;

import android.text.format.Time;

/**
 * Created by postal on 17/06/17.
 */

public class UpdateRequest {

    private boolean hasRequest = true;
    public Time lastUpdate = new Time();

    private static UpdateRequest instance;

    UpdateRequest(){
        lastUpdate.setToNow();
    }

    static public UpdateRequest getInstance(){
        if (instance==null) instance = new UpdateRequest();
        return instance;
    }

    public boolean isRequestUpadte(){

        Time actualTime = new Time();
        actualTime.setToNow();

        if((lastUpdate.toMillis(false)-actualTime.toMillis(false))>100000){
            hasRequest = false;
            lastUpdate = actualTime;
            return true;
        }

        if(hasRequest){
            hasRequest = false;
            return true;
        }
            return false;
    }

    public void requestUpdate(){
        hasRequest = true;
    }

}
