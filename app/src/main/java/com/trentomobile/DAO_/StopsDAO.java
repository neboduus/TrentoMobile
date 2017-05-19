package com.trentomobile.DAO_;

import com.trentomobile.transport.Stop;
import java.util.List;

public interface StopsDAO {
    void open();

    void close();

    Stop getStopByName(String var1);

    Stop getNearestStops(Float var1, Float var2);

    List<Stop> getAllStops();
}
