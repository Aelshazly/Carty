package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.LineString;
import java.util.List;

public class GeoJsonLineString extends LineString {
    public GeoJsonLineString(List<LatLng> coordinates) {
        super(coordinates);
    }

    public String getType() {
        return getGeometryType();
    }

    public List<LatLng> getCoordinates() {
        return getGeometryObject();
    }
}
