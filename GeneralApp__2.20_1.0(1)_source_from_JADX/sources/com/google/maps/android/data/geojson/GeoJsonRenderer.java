package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Renderer;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class GeoJsonRenderer extends Renderer implements Observer {
    private static final Object FEATURE_NOT_ON_MAP = null;

    public GeoJsonRenderer(GoogleMap map, HashMap<GeoJsonFeature, Object> features) {
        super(map, features);
    }

    public void setMap(GoogleMap map) {
        super.setMap(map);
        for (Feature feature : super.getFeatures()) {
            redrawFeatureToMap((GeoJsonFeature) feature, map);
        }
    }

    public void addLayerToMap() {
        if (!isLayerOnMap()) {
            setLayerVisibility(true);
            for (Feature feature : super.getFeatures()) {
                addFeature((GeoJsonFeature) feature);
            }
        }
    }

    public void addFeature(GeoJsonFeature feature) {
        super.addFeature(feature);
        if (isLayerOnMap()) {
            feature.addObserver(this);
        }
    }

    public void removeLayerFromMap() {
        if (isLayerOnMap()) {
            for (Feature feature : super.getFeatures()) {
                removeFromMap(super.getAllFeatures().get(feature));
                feature.deleteObserver(this);
            }
            setLayerVisibility(false);
        }
    }

    public void removeFeature(GeoJsonFeature feature) {
        super.removeFeature(feature);
        if (super.getFeatures().contains(feature)) {
            feature.deleteObserver(this);
        }
    }

    private void redrawFeatureToMap(GeoJsonFeature feature) {
        redrawFeatureToMap(feature, getMap());
    }

    private void redrawFeatureToMap(GeoJsonFeature feature, GoogleMap map) {
        removeFromMap(getAllFeatures().get(feature));
        putFeatures(feature, FEATURE_NOT_ON_MAP);
        if (map != null && feature.hasGeometry()) {
            putFeatures(feature, addGeoJsonFeatureToMap(feature, feature.getGeometry()));
        }
    }

    public void update(Observable observable, Object data) {
        if (observable instanceof GeoJsonFeature) {
            GeoJsonFeature feature = (GeoJsonFeature) observable;
            boolean featureIsOnMap = getAllFeatures().get(feature) != FEATURE_NOT_ON_MAP;
            if (featureIsOnMap && feature.hasGeometry()) {
                redrawFeatureToMap(feature);
            } else if (featureIsOnMap && !feature.hasGeometry()) {
                removeFromMap(getAllFeatures().get(feature));
                putFeatures(feature, FEATURE_NOT_ON_MAP);
            } else if (!featureIsOnMap && feature.hasGeometry()) {
                addFeature(feature);
            }
        }
    }
}
