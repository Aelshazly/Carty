package com.navibees.core.interfaces;

import com.navibees.core.model.metadata.json.RouteSegment;
import java.util.ArrayList;

public interface RouteInterface {
    void onHeadingUpdate(double d);

    void onNavigationInstructionComputed(String str, String str2);

    void onRouteComputed(double d, double d2, ArrayList<RouteSegment> arrayList);

    void onRouteProgress(double d, double d2);
}
