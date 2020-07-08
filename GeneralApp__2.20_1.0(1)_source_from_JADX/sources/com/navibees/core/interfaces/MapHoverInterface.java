package com.navibees.core.interfaces;

import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Floor;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.RouteSegment;
import java.util.ArrayList;

public interface MapHoverInterface {
    void compassTrackingChanged(boolean z);

    void locationTrackingChanged(boolean z);

    void notifyMapIsLoaded();

    void onEnterOrExitCoverageArea(boolean z);

    void onNavigationInstructionComputed(double d, double d2, ArrayList<RouteSegment> arrayList);

    void onPOISelected(POI poi);

    void onRouteFindCallback(POI poi, double d, double d2, Floor floor, boolean z);

    void onRouteFindCallbackForPosition(double d, double d2, boolean z);

    void onSaveLocationClicked(IndoorLocation indoorLocation);

    void presentedBuildingChanged(Building building);

    void presentedFloorChanged(Floor floor);

    void trackingLocationAndCompassChanged(boolean z);

    void updateBuildingPickerState(boolean z);

    void updateCompass(float f);

    void updateDistanceToRoute(double d);

    void updateFloorPickerState(boolean z);

    void updateNavigationMessage(String str, String str2);

    void updateNavigationMessage(String str, String str2, String str3);

    void updateNavigationProgress(double d, double d2);
}
