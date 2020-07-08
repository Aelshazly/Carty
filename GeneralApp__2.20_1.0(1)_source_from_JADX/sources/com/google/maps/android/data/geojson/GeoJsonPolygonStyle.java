package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.data.Style;
import com.google.maps.android.data.kml.KmlPolygon;
import java.util.Arrays;

public class GeoJsonPolygonStyle extends Style implements GeoJsonStyle {
    private static final String[] GEOMETRY_TYPE = {KmlPolygon.GEOMETRY_TYPE, "MultiPolygon", "GeometryCollection"};

    public GeoJsonPolygonStyle() {
        this.mPolygonOptions = new PolygonOptions();
    }

    public String[] getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public int getFillColor() {
        return this.mPolygonOptions.getFillColor();
    }

    public void setFillColor(int fillColor) {
        setPolygonFillColor(fillColor);
        styleChanged();
    }

    public boolean isGeodesic() {
        return this.mPolygonOptions.isGeodesic();
    }

    public void setGeodesic(boolean geodesic) {
        this.mPolygonOptions.geodesic(geodesic);
        styleChanged();
    }

    public int getStrokeColor() {
        return this.mPolygonOptions.getStrokeColor();
    }

    public void setStrokeColor(int strokeColor) {
        this.mPolygonOptions.strokeColor(strokeColor);
        styleChanged();
    }

    public float getStrokeWidth() {
        return this.mPolygonOptions.getStrokeWidth();
    }

    public void setStrokeWidth(float strokeWidth) {
        setPolygonStrokeWidth(strokeWidth);
        styleChanged();
    }

    public float getZIndex() {
        return this.mPolygonOptions.getZIndex();
    }

    public void setZIndex(float zIndex) {
        this.mPolygonOptions.zIndex(zIndex);
        styleChanged();
    }

    public boolean isVisible() {
        return this.mPolygonOptions.isVisible();
    }

    public void setVisible(boolean visible) {
        this.mPolygonOptions.visible(visible);
        styleChanged();
    }

    private void styleChanged() {
        setChanged();
        notifyObservers();
    }

    public PolygonOptions toPolygonOptions() {
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.fillColor(this.mPolygonOptions.getFillColor());
        polygonOptions.geodesic(this.mPolygonOptions.isGeodesic());
        polygonOptions.strokeColor(this.mPolygonOptions.getStrokeColor());
        polygonOptions.strokeWidth(this.mPolygonOptions.getStrokeWidth());
        polygonOptions.visible(this.mPolygonOptions.isVisible());
        polygonOptions.zIndex(this.mPolygonOptions.getZIndex());
        return polygonOptions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PolygonStyle{");
        sb.append("\n geometry type=");
        sb.append(Arrays.toString(GEOMETRY_TYPE));
        sb.append(",\n fill color=");
        sb.append(getFillColor());
        sb.append(",\n geodesic=");
        sb.append(isGeodesic());
        sb.append(",\n stroke color=");
        sb.append(getStrokeColor());
        sb.append(",\n stroke width=");
        sb.append(getStrokeWidth());
        sb.append(",\n visible=");
        sb.append(isVisible());
        sb.append(",\n z index=");
        sb.append(getZIndex());
        sb.append("\n}\n");
        return sb.toString();
    }
}
