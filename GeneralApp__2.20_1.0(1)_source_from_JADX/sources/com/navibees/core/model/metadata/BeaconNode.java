package com.navibees.core.model.metadata;

import com.navibees.core.model.metadata.json.IndoorLocation;
import java.util.ArrayList;
import java.util.List;

public class BeaconNode {
    public String UUID;
    public int batteryStatus;
    public String buildingId;
    public IndoorLocation location;
    public int major;
    private double meanAccuracy;
    public double meanEstimatedDistance;
    private double meanRSSI;
    public int minor;
    public List<C1666a> states = new ArrayList();
    public int txPower;

    public BeaconNode(int i, int i2) {
        this.major = i;
        this.minor = i2;
    }

    public String getUniqueId() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.buildingId);
        sb.append(":");
        sb.append(this.location.floor);
        return sb.toString();
    }

    public double meanAccuracy() {
        if (this.states.size() == 0) {
            this.meanAccuracy = Double.NEGATIVE_INFINITY;
            return this.meanAccuracy;
        }
        int i = 0;
        for (C1666a aVar : this.states) {
            i = (int) (((double) i) + aVar.f1323c);
        }
        this.meanAccuracy = ((double) i) / ((double) this.states.size());
        return this.meanAccuracy;
    }

    public double meanEstimatedDistance() {
        if (this.states.size() == 0) {
            this.meanEstimatedDistance = Double.NEGATIVE_INFINITY;
            return this.meanEstimatedDistance;
        }
        int i = 0;
        for (C1666a aVar : this.states) {
            i = (int) (((double) i) + aVar.f1324d);
        }
        this.meanEstimatedDistance = ((double) i) / ((double) this.states.size());
        return this.meanEstimatedDistance;
    }

    public double meanRSSI() {
        if (this.states.size() == 0) {
            this.meanRSSI = Double.NEGATIVE_INFINITY;
            return this.meanRSSI;
        }
        int i = 0;
        for (C1666a aVar : this.states) {
            i += aVar.f1322b;
        }
        this.meanRSSI = ((double) i) / ((double) this.states.size());
        return this.meanRSSI;
    }

    public String toString() {
        String str;
        String str2 = "";
        String str3 = "null";
        if (this.location != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(this.location.f1332x);
            str = sb.toString();
        } else {
            str = str3;
        }
        if (this.location != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append(this.location.f1333y);
            str3 = sb2.toString();
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("[major , minor]:[ ");
        sb3.append(this.major);
        String str4 = " , ";
        sb3.append(str4);
        sb3.append(this.minor);
        sb3.append(" ] ,*, (location.X , location.Y) : ( ");
        sb3.append(str);
        sb3.append(str4);
        sb3.append(str3);
        sb3.append(" )[betteryStatus]:[ ");
        sb3.append(this.batteryStatus);
        sb3.append("] (meanRSSI): ( ");
        sb3.append(this.meanRSSI);
        sb3.append(" ) ,*, ( meanAccuracy ) : (");
        sb3.append(this.meanAccuracy);
        sb3.append(" )  ,*,  ( txPower @ 1m ) : ( ");
        sb3.append(this.txPower);
        sb3.append(" )");
        return sb3.toString();
    }
}
