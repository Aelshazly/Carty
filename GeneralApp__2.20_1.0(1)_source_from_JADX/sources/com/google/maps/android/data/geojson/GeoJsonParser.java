package com.google.maps.android.data.geojson;

import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Geometry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class GeoJsonParser {
    private static final String BOUNDING_BOX = "bbox";
    private static final String FEATURE = "Feature";
    private static final String FEATURE_COLLECTION = "FeatureCollection";
    private static final String FEATURE_COLLECTION_ARRAY = "features";
    private static final String FEATURE_GEOMETRY = "geometry";
    private static final String FEATURE_ID = "id";
    private static final String GEOMETRY_COLLECTION = "GeometryCollection";
    private static final String GEOMETRY_COLLECTION_ARRAY = "geometries";
    private static final String GEOMETRY_COORDINATES_ARRAY = "coordinates";
    private static final String LINESTRING = "LineString";
    private static final String LOG_TAG = "GeoJsonParser";
    private static final String MULTILINESTRING = "MultiLineString";
    private static final String MULTIPOINT = "MultiPoint";
    private static final String MULTIPOLYGON = "MultiPolygon";
    private static final String POINT = "Point";
    private static final String POLYGON = "Polygon";
    private static final String PROPERTIES = "properties";
    private LatLngBounds mBoundingBox = null;
    private final ArrayList<GeoJsonFeature> mGeoJsonFeatures = new ArrayList<>();
    private final JSONObject mGeoJsonFile;

    GeoJsonParser(JSONObject geoJsonFile) {
        this.mGeoJsonFile = geoJsonFile;
        parseGeoJson();
    }

    private static boolean isGeometry(String type) {
        return type.matches("Point|MultiPoint|LineString|MultiLineString|Polygon|MultiPolygon|GeometryCollection");
    }

    private static GeoJsonFeature parseFeature(JSONObject geoJsonFeature) {
        String str = BOUNDING_BOX;
        String str2 = FEATURE_ID;
        String str3 = PROPERTIES;
        String str4 = FEATURE_GEOMETRY;
        String id = null;
        LatLngBounds boundingBox = null;
        Geometry geometry = null;
        HashMap hashMap = new HashMap();
        try {
            if (geoJsonFeature.has(str2)) {
                id = geoJsonFeature.getString(str2);
            }
            if (geoJsonFeature.has(str)) {
                boundingBox = parseBoundingBox(geoJsonFeature.getJSONArray(str));
            }
            if (geoJsonFeature.has(str4) && !geoJsonFeature.isNull(str4)) {
                geometry = parseGeometry(geoJsonFeature.getJSONObject(str4));
            }
            if (geoJsonFeature.has(str3) && !geoJsonFeature.isNull(str3)) {
                hashMap = parseProperties(geoJsonFeature.getJSONObject(str3));
            }
            return new GeoJsonFeature(geometry, id, hashMap, boundingBox);
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Feature could not be successfully parsed ");
            sb.append(geoJsonFeature.toString());
            Log.w(LOG_TAG, sb.toString());
            return null;
        }
    }

    private static LatLngBounds parseBoundingBox(JSONArray coordinates) throws JSONException {
        return new LatLngBounds(new LatLng(coordinates.getDouble(1), coordinates.getDouble(0)), new LatLng(coordinates.getDouble(3), coordinates.getDouble(2)));
    }

    private static Geometry parseGeometry(JSONObject geoJsonGeometry) {
        JSONArray geometryArray;
        try {
            String geometryType = geoJsonGeometry.getString("type");
            if (geometryType.equals(GEOMETRY_COLLECTION)) {
                geometryArray = geoJsonGeometry.getJSONArray(GEOMETRY_COLLECTION_ARRAY);
            } else if (!isGeometry(geometryType)) {
                return null;
            } else {
                geometryArray = geoJsonGeometry.getJSONArray(GEOMETRY_COORDINATES_ARRAY);
            }
            return createGeometry(geometryType, geometryArray);
        } catch (JSONException e) {
            return null;
        }
    }

    private static GeoJsonFeature parseGeometryToFeature(JSONObject geoJsonGeometry) {
        Geometry geometry = parseGeometry(geoJsonGeometry);
        if (geometry != null) {
            return new GeoJsonFeature(geometry, null, new HashMap(), null);
        }
        Log.w(LOG_TAG, "Geometry could not be parsed");
        return null;
    }

    private static HashMap<String, String> parseProperties(JSONObject properties) throws JSONException {
        HashMap<String, String> propertiesMap = new HashMap<>();
        Iterator propertyKeys = properties.keys();
        while (propertyKeys.hasNext()) {
            String key = (String) propertyKeys.next();
            propertiesMap.put(key, properties.isNull(key) ? null : properties.getString(key));
        }
        return propertiesMap;
    }

    private static Geometry createGeometry(String geometryType, JSONArray geometryArray) throws JSONException {
        if (geometryType.equals(POINT)) {
            return createPoint(geometryArray);
        }
        if (geometryType.equals(MULTIPOINT)) {
            return createMultiPoint(geometryArray);
        }
        if (geometryType.equals(LINESTRING)) {
            return createLineString(geometryArray);
        }
        if (geometryType.equals(MULTILINESTRING)) {
            return createMultiLineString(geometryArray);
        }
        if (geometryType.equals("Polygon")) {
            return createPolygon(geometryArray);
        }
        if (geometryType.equals(MULTIPOLYGON)) {
            return createMultiPolygon(geometryArray);
        }
        if (geometryType.equals(GEOMETRY_COLLECTION)) {
            return createGeometryCollection(geometryArray);
        }
        return null;
    }

    private static GeoJsonPoint createPoint(JSONArray coordinates) throws JSONException {
        return new GeoJsonPoint(parseCoordinate(coordinates));
    }

    private static GeoJsonMultiPoint createMultiPoint(JSONArray coordinates) throws JSONException {
        ArrayList<GeoJsonPoint> geoJsonPoints = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            geoJsonPoints.add(createPoint(coordinates.getJSONArray(i)));
        }
        return new GeoJsonMultiPoint(geoJsonPoints);
    }

    private static GeoJsonLineString createLineString(JSONArray coordinates) throws JSONException {
        return new GeoJsonLineString(parseCoordinatesArray(coordinates));
    }

    private static GeoJsonMultiLineString createMultiLineString(JSONArray coordinates) throws JSONException {
        ArrayList<GeoJsonLineString> geoJsonLineStrings = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            geoJsonLineStrings.add(createLineString(coordinates.getJSONArray(i)));
        }
        return new GeoJsonMultiLineString(geoJsonLineStrings);
    }

    private static GeoJsonPolygon createPolygon(JSONArray coordinates) throws JSONException {
        return new GeoJsonPolygon(parseCoordinatesArrays(coordinates));
    }

    private static GeoJsonMultiPolygon createMultiPolygon(JSONArray coordinates) throws JSONException {
        ArrayList<GeoJsonPolygon> geoJsonPolygons = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            geoJsonPolygons.add(createPolygon(coordinates.getJSONArray(i)));
        }
        return new GeoJsonMultiPolygon(geoJsonPolygons);
    }

    private static GeoJsonGeometryCollection createGeometryCollection(JSONArray geometries) throws JSONException {
        ArrayList<Geometry> geometryCollectionElements = new ArrayList<>();
        for (int i = 0; i < geometries.length(); i++) {
            Geometry geometry = parseGeometry(geometries.getJSONObject(i));
            if (geometry != null) {
                geometryCollectionElements.add(geometry);
            }
        }
        return new GeoJsonGeometryCollection(geometryCollectionElements);
    }

    private static LatLng parseCoordinate(JSONArray coordinates) throws JSONException {
        return new LatLng(coordinates.getDouble(1), coordinates.getDouble(0));
    }

    private static ArrayList<LatLng> parseCoordinatesArray(JSONArray coordinates) throws JSONException {
        ArrayList<LatLng> coordinatesArray = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            coordinatesArray.add(parseCoordinate(coordinates.getJSONArray(i)));
        }
        return coordinatesArray;
    }

    private static ArrayList<ArrayList<LatLng>> parseCoordinatesArrays(JSONArray coordinates) throws JSONException {
        ArrayList<ArrayList<LatLng>> coordinatesArray = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            coordinatesArray.add(parseCoordinatesArray(coordinates.getJSONArray(i)));
        }
        return coordinatesArray;
    }

    private void parseGeoJson() {
        String str = "GeoJSON file could not be parsed.";
        String str2 = LOG_TAG;
        try {
            String type = this.mGeoJsonFile.getString("type");
            if (type.equals(FEATURE)) {
                GeoJsonFeature feature = parseFeature(this.mGeoJsonFile);
                if (feature != null) {
                    this.mGeoJsonFeatures.add(feature);
                }
            } else if (type.equals(FEATURE_COLLECTION)) {
                this.mGeoJsonFeatures.addAll(parseFeatureCollection(this.mGeoJsonFile));
            } else if (isGeometry(type)) {
                GeoJsonFeature feature2 = parseGeometryToFeature(this.mGeoJsonFile);
                if (feature2 != null) {
                    this.mGeoJsonFeatures.add(feature2);
                }
            } else {
                Log.w(str2, str);
            }
        } catch (JSONException e) {
            Log.w(str2, str);
        }
    }

    private ArrayList<GeoJsonFeature> parseFeatureCollection(JSONObject geoJsonFeatureCollection) {
        String str = "Index of Feature in Feature Collection that could not be created: ";
        String str2 = BOUNDING_BOX;
        String str3 = LOG_TAG;
        ArrayList<GeoJsonFeature> features = new ArrayList<>();
        try {
            JSONArray geoJsonFeatures = geoJsonFeatureCollection.getJSONArray(FEATURE_COLLECTION_ARRAY);
            if (geoJsonFeatureCollection.has(str2)) {
                this.mBoundingBox = parseBoundingBox(geoJsonFeatureCollection.getJSONArray(str2));
            }
            for (int i = 0; i < geoJsonFeatures.length(); i++) {
                try {
                    JSONObject feature = geoJsonFeatures.getJSONObject(i);
                    if (feature.getString("type").equals(FEATURE)) {
                        GeoJsonFeature parsedFeature = parseFeature(feature);
                        if (parsedFeature != null) {
                            features.add(parsedFeature);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append(str);
                            sb.append(i);
                            Log.w(str3, sb.toString());
                        }
                    }
                } catch (JSONException e) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append(i);
                    Log.w(str3, sb2.toString());
                }
            }
            return features;
        } catch (JSONException e2) {
            Log.w(str3, "Feature Collection could not be created.");
            return features;
        }
    }

    /* access modifiers changed from: 0000 */
    public ArrayList<GeoJsonFeature> getFeatures() {
        return this.mGeoJsonFeatures;
    }

    /* access modifiers changed from: 0000 */
    public LatLngBounds getBoundingBox() {
        return this.mBoundingBox;
    }
}
