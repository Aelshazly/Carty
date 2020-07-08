package com.navibees.core.model.postioning;

import com.navibees.core.model.postioning.PositionLocator.CalculatedLocation;

public interface AdvancedLocationListener {
    void locationCallback(CalculatedLocation calculatedLocation, CalculatedLocation calculatedLocation2, boolean z);
}
