package com.navibees.core.interfaces;

import com.navibees.core.model.metadata.json.IndoorLocation;

public interface OutdoorLocationListener {
    void onLocationUpdate(IndoorLocation indoorLocation, int i);
}
