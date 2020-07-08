package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Geometry;
import java.util.HashMap;

public class KmlPlacemark extends Feature {
    private final KmlStyle mInlineStyle;
    private final String mStyle;

    public KmlPlacemark(Geometry geometry, String style, KmlStyle inlineStyle, HashMap<String, String> properties) {
        super(geometry, style, properties);
        this.mStyle = style;
        this.mInlineStyle = inlineStyle;
    }

    public String getStyleId() {
        return super.getId();
    }

    public KmlStyle getInlineStyle() {
        return this.mInlineStyle;
    }

    public PolygonOptions getPolygonOptions() {
        return this.mInlineStyle.getPolygonOptions();
    }

    public MarkerOptions getMarkerOptions() {
        return this.mInlineStyle.getMarkerOptions();
    }

    public PolylineOptions getPolylineOptions() {
        return this.mInlineStyle.getPolylineOptions();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Placemark").append("{");
        sb.append("\n style id=");
        sb.append(this.mStyle);
        sb.append(",\n inline style=");
        sb.append(this.mInlineStyle);
        sb.append("\n}\n");
        return sb.toString();
    }
}
