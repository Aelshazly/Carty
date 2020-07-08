package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Style;
import java.util.Arrays;

public class GeoJsonLineStringStyle extends Style implements GeoJsonStyle {
    private static final String[] GEOMETRY_TYPE = {"LineString", "MultiLineString", "GeometryCollection"};

    public GeoJsonLineStringStyle() {
        this.mPolylineOptions = new PolylineOptions();
    }

    public String[] getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public int getColor() {
        return this.mPolylineOptions.getColor();
    }

    public void setColor(int color) {
        this.mPolylineOptions.color(color);
        styleChanged();
    }

    public boolean isClickable() {
        return this.mPolylineOptions.isClickable();
    }

    public void setClickable(boolean clickable) {
        this.mPolylineOptions.clickable(clickable);
        styleChanged();
    }

    public boolean isGeodesic() {
        return this.mPolylineOptions.isGeodesic();
    }

    public void setGeodesic(boolean geodesic) {
        this.mPolylineOptions.geodesic(geodesic);
        styleChanged();
    }

    public float getWidth() {
        return this.mPolylineOptions.getWidth();
    }

    public void setWidth(float width) {
        setLineStringWidth(width);
        styleChanged();
    }

    public float getZIndex() {
        return this.mPolylineOptions.getZIndex();
    }

    public void setZIndex(float zIndex) {
        this.mPolylineOptions.zIndex(zIndex);
        styleChanged();
    }

    public boolean isVisible() {
        return this.mPolylineOptions.isVisible();
    }

    public void setVisible(boolean visible) {
        this.mPolylineOptions.visible(visible);
        styleChanged();
    }

    private void styleChanged() {
        setChanged();
        notifyObservers();
    }

    public PolylineOptions toPolylineOptions() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(this.mPolylineOptions.getColor());
        polylineOptions.clickable(this.mPolylineOptions.isClickable());
        polylineOptions.geodesic(this.mPolylineOptions.isGeodesic());
        polylineOptions.visible(this.mPolylineOptions.isVisible());
        polylineOptions.width(this.mPolylineOptions.getWidth());
        polylineOptions.zIndex(this.mPolylineOptions.getZIndex());
        return polylineOptions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LineStringStyle{");
        sb.append("\n geometry type=");
        sb.append(Arrays.toString(GEOMETRY_TYPE));
        sb.append(",\n color=");
        sb.append(getColor());
        sb.append(",\n clickable=");
        sb.append(isClickable());
        sb.append(",\n geodesic=");
        sb.append(isGeodesic());
        sb.append(",\n visible=");
        sb.append(isVisible());
        sb.append(",\n width=");
        sb.append(getWidth());
        sb.append(",\n z index=");
        sb.append(getZIndex());
        sb.append("\n}\n");
        return sb.toString();
    }
}
