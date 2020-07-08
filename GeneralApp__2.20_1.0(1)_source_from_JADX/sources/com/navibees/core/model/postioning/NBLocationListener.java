package com.navibees.core.model.postioning;

import com.navibees.core.model.metadata.json.IndoorLocation;

public interface NBLocationListener {
    boolean isBackground();

    void locationCallback(IndoorLocation indoorLocation, IndoorLocation indoorLocation2, int i, boolean z, boolean z2);
}
