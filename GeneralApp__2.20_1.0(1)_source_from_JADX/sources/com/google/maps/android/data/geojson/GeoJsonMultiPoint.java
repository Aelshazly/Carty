package com.google.maps.android.data.geojson;

import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.MultiGeometry;
import java.util.ArrayList;
import java.util.List;

public class GeoJsonMultiPoint extends MultiGeometry {
    public GeoJsonMultiPoint(List<GeoJsonPoint> geoJsonPoints) {
        super(geoJsonPoints);
        setGeometryType("MultiPoint");
    }

    public String getType() {
        return getGeometryType();
    }

    public List<GeoJsonPoint> getPoints() {
        List<Geometry> geometryList = getGeometryObject();
        ArrayList<GeoJsonPoint> geoJsonPoints = new ArrayList<>();
        for (Geometry geometry : geometryList) {
            geoJsonPoints.add((GeoJsonPoint) geometry);
        }
        return geoJsonPoints;
    }
}
