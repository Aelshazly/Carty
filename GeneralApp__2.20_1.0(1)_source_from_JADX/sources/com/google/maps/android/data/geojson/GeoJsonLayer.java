package com.google.maps.android.data.geojson;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Layer;
import com.google.maps.android.data.Layer.OnFeatureClickListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class GeoJsonLayer extends Layer {
    private LatLngBounds mBoundingBox;

    public interface GeoJsonOnFeatureClickListener extends OnFeatureClickListener {
    }

    public GeoJsonLayer(GoogleMap map, JSONObject geoJsonFile) {
        if (geoJsonFile != null) {
            this.mBoundingBox = null;
            GeoJsonParser parser = new GeoJsonParser(geoJsonFile);
            this.mBoundingBox = parser.getBoundingBox();
            HashMap<GeoJsonFeature, Object> geoJsonFeatures = new HashMap<>();
            Iterator it = parser.getFeatures().iterator();
            while (it.hasNext()) {
                geoJsonFeatures.put((GeoJsonFeature) it.next(), null);
            }
            storeRenderer(new GeoJsonRenderer(map, geoJsonFeatures));
            return;
        }
        throw new IllegalArgumentException("GeoJSON file cannot be null");
    }

    public GeoJsonLayer(GoogleMap map, int resourceId, Context context) throws IOException, JSONException {
        this(map, createJsonFileObject(context.getResources().openRawResource(resourceId)));
    }

    private static JSONObject createJsonFileObject(InputStream stream) throws IOException, JSONException {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        while (true) {
            String readLine = reader.readLine();
            String line = readLine;
            if (readLine != null) {
                result.append(line);
            } else {
                reader.close();
                return new JSONObject(result.toString());
            }
        }
    }

    public void addLayerToMap() {
        super.addGeoJsonToMap();
    }

    public Iterable<GeoJsonFeature> getFeatures() {
        return super.getFeatures();
    }

    public void addFeature(GeoJsonFeature feature) {
        if (feature != null) {
            super.addFeature(feature);
            return;
        }
        throw new IllegalArgumentException("Feature cannot be null");
    }

    public void removeFeature(GeoJsonFeature feature) {
        if (feature != null) {
            super.removeFeature(feature);
            return;
        }
        throw new IllegalArgumentException("Feature cannot be null");
    }

    public LatLngBounds getBoundingBox() {
        return this.mBoundingBox;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Collection{");
        sb.append("\n Bounding box=");
        sb.append(this.mBoundingBox);
        sb.append("\n}\n");
        return sb.toString();
    }
}
