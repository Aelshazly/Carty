package com.navibees.core.model.metadata.json;

import com.navibees.core.NaviBeesConstants.InstructionsIconsNames;
import java.util.HashMap;

public class RouteSegment {
    public double distance = -1.0d;
    public double duration = -1.0d;
    public Location endLoc;
    public String maneuverType = InstructionsIconsNames.maneuverTypeUnknown;
    public Location startLoc;
    public double time = -1.0d;
    public double totalTime = -1.0d;

    public String description() {
        HashMap hashMap = new HashMap();
        hashMap.put("startLoc", this.startLoc.toString());
        hashMap.put("endLoc", this.endLoc.toString());
        hashMap.put("duration", String.valueOf(this.duration));
        hashMap.put("distance", String.valueOf(this.distance));
        hashMap.put("maneuverType", this.maneuverType);
        hashMap.put("time", String.valueOf(this.time));
        hashMap.put("totalTime", String.valueOf(this.totalTime));
        return hashMap.toString();
    }
}
