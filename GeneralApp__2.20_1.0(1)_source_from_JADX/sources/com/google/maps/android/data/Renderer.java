package com.google.maps.android.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.collection.LruCache;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.C1160R;
import com.google.maps.android.data.geojson.BiMultiMap;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLineString;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonMultiLineString;
import com.google.maps.android.data.geojson.GeoJsonMultiPoint;
import com.google.maps.android.data.geojson.GeoJsonMultiPolygon;
import com.google.maps.android.data.geojson.GeoJsonPoint;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygon;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlMultiGeometry;
import com.google.maps.android.data.kml.KmlPlacemark;
import com.google.maps.android.data.kml.KmlPoint;
import com.google.maps.android.data.kml.KmlPolygon;
import com.google.maps.android.data.kml.KmlStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Renderer {
    private static final Object FEATURE_NOT_ON_MAP = null;
    private static final int LRU_CACHE_SIZE = 50;
    private BiMultiMap<Feature> mContainerFeatures;
    private ArrayList<KmlContainer> mContainers;
    /* access modifiers changed from: private */
    public Context mContext;
    private final GeoJsonLineStringStyle mDefaultLineStringStyle;
    private final GeoJsonPointStyle mDefaultPointStyle;
    private final GeoJsonPolygonStyle mDefaultPolygonStyle;
    private final BiMultiMap<Feature> mFeatures = new BiMultiMap<>();
    private HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlays;
    private final LruCache<String, Bitmap> mImagesCache;
    private boolean mLayerOnMap;
    private GoogleMap mMap;
    private final ArrayList<String> mMarkerIconUrls;
    private HashMap<String, String> mStyleMaps;
    private HashMap<String, KmlStyle> mStyles;
    private HashMap<String, KmlStyle> mStylesRenderer;

    public Renderer(GoogleMap map, Context context) {
        this.mMap = map;
        this.mContext = context;
        this.mLayerOnMap = false;
        this.mImagesCache = new LruCache<>(50);
        this.mMarkerIconUrls = new ArrayList<>();
        this.mStylesRenderer = new HashMap<>();
        this.mDefaultPointStyle = null;
        this.mDefaultLineStringStyle = null;
        this.mDefaultPolygonStyle = null;
        this.mContainerFeatures = new BiMultiMap<>();
    }

    public Renderer(GoogleMap map, HashMap<? extends Feature, Object> features) {
        this.mMap = map;
        this.mFeatures.putAll(features);
        this.mLayerOnMap = false;
        this.mMarkerIconUrls = null;
        this.mDefaultPointStyle = new GeoJsonPointStyle();
        this.mDefaultLineStringStyle = new GeoJsonLineStringStyle();
        this.mDefaultPolygonStyle = new GeoJsonPolygonStyle();
        this.mImagesCache = null;
        this.mContainerFeatures = null;
    }

    public boolean isLayerOnMap() {
        return this.mLayerOnMap;
    }

    /* access modifiers changed from: protected */
    public void setLayerVisibility(boolean layerOnMap) {
        this.mLayerOnMap = layerOnMap;
    }

    public GoogleMap getMap() {
        return this.mMap;
    }

    public void setMap(GoogleMap map) {
        this.mMap = map;
    }

    /* access modifiers changed from: protected */
    public void putContainerFeature(Object mapObject, Feature placemark) {
        this.mContainerFeatures.put(placemark, mapObject);
    }

    public Set<Feature> getFeatures() {
        return this.mFeatures.keySet();
    }

    public Feature getFeature(Object mapObject) {
        return (Feature) this.mFeatures.getKey(mapObject);
    }

    public Feature getContainerFeature(Object mapObject) {
        BiMultiMap<Feature> biMultiMap = this.mContainerFeatures;
        if (biMultiMap != null) {
            return (Feature) biMultiMap.getKey(mapObject);
        }
        return null;
    }

    public Collection<Object> getValues() {
        return this.mFeatures.values();
    }

    /* access modifiers changed from: protected */
    public HashMap<? extends Feature, Object> getAllFeatures() {
        return this.mFeatures;
    }

    public ArrayList<String> getMarkerIconUrls() {
        return this.mMarkerIconUrls;
    }

    public HashMap<String, KmlStyle> getStylesRenderer() {
        return this.mStylesRenderer;
    }

    public HashMap<String, String> getStyleMaps() {
        return this.mStyleMaps;
    }

    public LruCache<String, Bitmap> getImagesCache() {
        return this.mImagesCache;
    }

    public HashMap<KmlGroundOverlay, GroundOverlay> getGroundOverlayMap() {
        return this.mGroundOverlays;
    }

    public ArrayList<KmlContainer> getContainerList() {
        return this.mContainers;
    }

    /* access modifiers changed from: protected */
    public KmlStyle getPlacemarkStyle(String styleId) {
        KmlStyle style = (KmlStyle) this.mStylesRenderer.get(null);
        if (this.mStylesRenderer.get(styleId) != null) {
            return (KmlStyle) this.mStylesRenderer.get(styleId);
        }
        return style;
    }

    public GeoJsonPointStyle getDefaultPointStyle() {
        return this.mDefaultPointStyle;
    }

    public GeoJsonLineStringStyle getDefaultLineStringStyle() {
        return this.mDefaultLineStringStyle;
    }

    public GeoJsonPolygonStyle getDefaultPolygonStyle() {
        return this.mDefaultPolygonStyle;
    }

    public void putFeatures(Feature feature, Object object) {
        this.mFeatures.put(feature, object);
    }

    public void putStyles() {
        this.mStylesRenderer.putAll(this.mStyles);
    }

    public void putStyles(HashMap<String, KmlStyle> styles) {
        this.mStylesRenderer.putAll(styles);
    }

    public void putImagesCache(String groundOverlayUrl, Bitmap bitmap) {
        this.mImagesCache.put(groundOverlayUrl, bitmap);
    }

    public boolean hasFeatures() {
        return this.mFeatures.size() > 0;
    }

    protected static void removeFeatures(HashMap<Feature, Object> features) {
        for (Object mapObject : features.values()) {
            if (mapObject instanceof Marker) {
                ((Marker) mapObject).remove();
            } else if (mapObject instanceof Polyline) {
                ((Polyline) mapObject).remove();
            } else if (mapObject instanceof Polygon) {
                ((Polygon) mapObject).remove();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void removeFeature(Feature feature) {
        if (this.mFeatures.containsKey(feature)) {
            removeFromMap(this.mFeatures.remove(feature));
        }
    }

    private void setFeatureDefaultStyles(GeoJsonFeature feature) {
        if (feature.getPointStyle() == null) {
            feature.setPointStyle(this.mDefaultPointStyle);
        }
        if (feature.getLineStringStyle() == null) {
            feature.setLineStringStyle(this.mDefaultLineStringStyle);
        }
        if (feature.getPolygonStyle() == null) {
            feature.setPolygonStyle(this.mDefaultPolygonStyle);
        }
    }

    public void clearStylesRenderer() {
        this.mStylesRenderer.clear();
    }

    /* access modifiers changed from: protected */
    public void storeData(HashMap<String, KmlStyle> styles, HashMap<String, String> styleMaps, HashMap<KmlPlacemark, Object> features, ArrayList<KmlContainer> folders, HashMap<KmlGroundOverlay, GroundOverlay> groundOverlays) {
        this.mStyles = styles;
        this.mStyleMaps = styleMaps;
        this.mFeatures.putAll(features);
        this.mContainers = folders;
        this.mGroundOverlays = groundOverlays;
    }

    public void addFeature(Feature feature) {
        Object mapObject = FEATURE_NOT_ON_MAP;
        if (feature instanceof GeoJsonFeature) {
            setFeatureDefaultStyles((GeoJsonFeature) feature);
        }
        if (this.mLayerOnMap) {
            if (this.mFeatures.containsKey(feature)) {
                removeFromMap(this.mFeatures.get(feature));
            }
            if (feature.hasGeometry()) {
                if (feature instanceof KmlPlacemark) {
                    boolean isPlacemarkVisible = getPlacemarkVisibility(feature);
                    String placemarkId = feature.getId();
                    mapObject = addKmlPlacemarkToMap((KmlPlacemark) feature, feature.getGeometry(), getPlacemarkStyle(placemarkId), ((KmlPlacemark) feature).getInlineStyle(), isPlacemarkVisible);
                } else {
                    mapObject = addGeoJsonFeatureToMap(feature, feature.getGeometry());
                }
            }
        }
        this.mFeatures.put(feature, mapObject);
    }

    public static void removeFromMap(Object mapObject) {
        if (mapObject instanceof Marker) {
            ((Marker) mapObject).remove();
        } else if (mapObject instanceof Polyline) {
            ((Polyline) mapObject).remove();
        } else if (mapObject instanceof Polygon) {
            ((Polygon) mapObject).remove();
        } else if (mapObject instanceof ArrayList) {
            Iterator it = ((ArrayList) mapObject).iterator();
            while (it.hasNext()) {
                removeFromMap(it.next());
            }
        }
    }

    /* access modifiers changed from: protected */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object addGeoJsonFeatureToMap(com.google.maps.android.data.Feature r4, com.google.maps.android.data.Geometry r5) {
        /*
            r3 = this;
            java.lang.String r0 = r5.getGeometryType()
            int r1 = r0.hashCode()
            switch(r1) {
                case -2116761119: goto L_0x0048;
                case -1065891849: goto L_0x003e;
                case -627102946: goto L_0x0034;
                case 77292912: goto L_0x002a;
                case 1267133722: goto L_0x0020;
                case 1806700869: goto L_0x0016;
                case 1950410960: goto L_0x000c;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x0052
        L_0x000c:
            java.lang.String r1 = "GeometryCollection"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x000b
            r1 = 6
            goto L_0x0053
        L_0x0016:
            java.lang.String r1 = "LineString"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x000b
            r1 = 1
            goto L_0x0053
        L_0x0020:
            java.lang.String r1 = "Polygon"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x000b
            r1 = 2
            goto L_0x0053
        L_0x002a:
            java.lang.String r1 = "Point"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x000b
            r1 = 0
            goto L_0x0053
        L_0x0034:
            java.lang.String r1 = "MultiLineString"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x000b
            r1 = 4
            goto L_0x0053
        L_0x003e:
            java.lang.String r1 = "MultiPoint"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x000b
            r1 = 3
            goto L_0x0053
        L_0x0048:
            java.lang.String r1 = "MultiPolygon"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x000b
            r1 = 5
            goto L_0x0053
        L_0x0052:
            r1 = -1
        L_0x0053:
            switch(r1) {
                case 0: goto L_0x00d4;
                case 1: goto L_0x00b4;
                case 2: goto L_0x0094;
                case 3: goto L_0x0085;
                case 4: goto L_0x0076;
                case 5: goto L_0x0067;
                case 6: goto L_0x0058;
                default: goto L_0x0056;
            }
        L_0x0056:
            r1 = 0
            return r1
        L_0x0058:
            r1 = r4
            com.google.maps.android.data.geojson.GeoJsonFeature r1 = (com.google.maps.android.data.geojson.GeoJsonFeature) r1
            r2 = r5
            com.google.maps.android.data.geojson.GeoJsonGeometryCollection r2 = (com.google.maps.android.data.geojson.GeoJsonGeometryCollection) r2
            java.util.List r2 = r2.getGeometries()
            java.util.ArrayList r1 = r3.addGeometryCollectionToMap(r1, r2)
            return r1
        L_0x0067:
            r1 = r4
            com.google.maps.android.data.geojson.GeoJsonFeature r1 = (com.google.maps.android.data.geojson.GeoJsonFeature) r1
            com.google.maps.android.data.geojson.GeoJsonPolygonStyle r1 = r1.getPolygonStyle()
            r2 = r5
            com.google.maps.android.data.geojson.GeoJsonMultiPolygon r2 = (com.google.maps.android.data.geojson.GeoJsonMultiPolygon) r2
            java.util.ArrayList r1 = r3.addMultiPolygonToMap(r1, r2)
            return r1
        L_0x0076:
            r1 = r4
            com.google.maps.android.data.geojson.GeoJsonFeature r1 = (com.google.maps.android.data.geojson.GeoJsonFeature) r1
            com.google.maps.android.data.geojson.GeoJsonLineStringStyle r1 = r1.getLineStringStyle()
            r2 = r5
            com.google.maps.android.data.geojson.GeoJsonMultiLineString r2 = (com.google.maps.android.data.geojson.GeoJsonMultiLineString) r2
            java.util.ArrayList r1 = r3.addMultiLineStringToMap(r1, r2)
            return r1
        L_0x0085:
            r1 = r4
            com.google.maps.android.data.geojson.GeoJsonFeature r1 = (com.google.maps.android.data.geojson.GeoJsonFeature) r1
            com.google.maps.android.data.geojson.GeoJsonPointStyle r1 = r1.getPointStyle()
            r2 = r5
            com.google.maps.android.data.geojson.GeoJsonMultiPoint r2 = (com.google.maps.android.data.geojson.GeoJsonMultiPoint) r2
            java.util.ArrayList r1 = r3.addMultiPointToMap(r1, r2)
            return r1
        L_0x0094:
            r1 = 0
            boolean r2 = r4 instanceof com.google.maps.android.data.geojson.GeoJsonFeature
            if (r2 == 0) goto L_0x00a1
            r2 = r4
            com.google.maps.android.data.geojson.GeoJsonFeature r2 = (com.google.maps.android.data.geojson.GeoJsonFeature) r2
            com.google.android.gms.maps.model.PolygonOptions r1 = r2.getPolygonOptions()
            goto L_0x00ac
        L_0x00a1:
            boolean r2 = r4 instanceof com.google.maps.android.data.kml.KmlPlacemark
            if (r2 == 0) goto L_0x00ac
            r2 = r4
            com.google.maps.android.data.kml.KmlPlacemark r2 = (com.google.maps.android.data.kml.KmlPlacemark) r2
            com.google.android.gms.maps.model.PolygonOptions r1 = r2.getPolygonOptions()
        L_0x00ac:
            r2 = r5
            com.google.maps.android.data.DataPolygon r2 = (com.google.maps.android.data.DataPolygon) r2
            com.google.android.gms.maps.model.Polygon r2 = r3.addPolygonToMap(r1, r2)
            return r2
        L_0x00b4:
            r1 = 0
            boolean r2 = r4 instanceof com.google.maps.android.data.geojson.GeoJsonFeature
            if (r2 == 0) goto L_0x00c1
            r2 = r4
            com.google.maps.android.data.geojson.GeoJsonFeature r2 = (com.google.maps.android.data.geojson.GeoJsonFeature) r2
            com.google.android.gms.maps.model.PolylineOptions r1 = r2.getPolylineOptions()
            goto L_0x00cc
        L_0x00c1:
            boolean r2 = r4 instanceof com.google.maps.android.data.kml.KmlPlacemark
            if (r2 == 0) goto L_0x00cc
            r2 = r4
            com.google.maps.android.data.kml.KmlPlacemark r2 = (com.google.maps.android.data.kml.KmlPlacemark) r2
            com.google.android.gms.maps.model.PolylineOptions r1 = r2.getPolylineOptions()
        L_0x00cc:
            r2 = r5
            com.google.maps.android.data.geojson.GeoJsonLineString r2 = (com.google.maps.android.data.geojson.GeoJsonLineString) r2
            com.google.android.gms.maps.model.Polyline r2 = r3.addLineStringToMap(r1, r2)
            return r2
        L_0x00d4:
            r1 = 0
            boolean r2 = r4 instanceof com.google.maps.android.data.geojson.GeoJsonFeature
            if (r2 == 0) goto L_0x00e1
            r2 = r4
            com.google.maps.android.data.geojson.GeoJsonFeature r2 = (com.google.maps.android.data.geojson.GeoJsonFeature) r2
            com.google.android.gms.maps.model.MarkerOptions r1 = r2.getMarkerOptions()
            goto L_0x00ec
        L_0x00e1:
            boolean r2 = r4 instanceof com.google.maps.android.data.kml.KmlPlacemark
            if (r2 == 0) goto L_0x00ec
            r2 = r4
            com.google.maps.android.data.kml.KmlPlacemark r2 = (com.google.maps.android.data.kml.KmlPlacemark) r2
            com.google.android.gms.maps.model.MarkerOptions r1 = r2.getMarkerOptions()
        L_0x00ec:
            r2 = r5
            com.google.maps.android.data.geojson.GeoJsonPoint r2 = (com.google.maps.android.data.geojson.GeoJsonPoint) r2
            com.google.android.gms.maps.model.Marker r2 = r3.addPointToMap(r1, r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.Renderer.addGeoJsonFeatureToMap(com.google.maps.android.data.Feature, com.google.maps.android.data.Geometry):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public Object addKmlPlacemarkToMap(KmlPlacemark placemark, Geometry geometry, KmlStyle style, KmlStyle inlineStyle, boolean isVisible) {
        String geometryType = geometry.getGeometryType();
        String str = "drawOrder";
        boolean hasDrawOrder = placemark.hasProperty(str);
        float drawOrder = 0.0f;
        if (hasDrawOrder) {
            try {
                drawOrder = Float.parseFloat(placemark.getProperty(str));
            } catch (NumberFormatException e) {
                hasDrawOrder = false;
            }
        }
        char c = 65535;
        switch (geometryType.hashCode()) {
            case 77292912:
                if (geometryType.equals("Point")) {
                    c = 0;
                    break;
                }
                break;
            case 89139371:
                if (geometryType.equals("MultiGeometry")) {
                    c = 3;
                    break;
                }
                break;
            case 1267133722:
                if (geometryType.equals(KmlPolygon.GEOMETRY_TYPE)) {
                    c = 2;
                    break;
                }
                break;
            case 1806700869:
                if (geometryType.equals("LineString")) {
                    c = 1;
                    break;
                }
                break;
        }
        if (c == 0) {
            MarkerOptions markerOptions = style.getMarkerOptions();
            if (inlineStyle != null) {
                setInlinePointStyle(markerOptions, inlineStyle, style.getIconUrl());
            } else if (style.getIconUrl() != null) {
                addMarkerIcons(style.getIconUrl(), markerOptions);
            }
            Marker marker = addPointToMap(markerOptions, (KmlPoint) geometry);
            marker.setVisible(isVisible);
            setMarkerInfoWindow(style, marker, placemark);
            if (hasDrawOrder) {
                marker.setZIndex(drawOrder);
            }
            return marker;
        } else if (c == 1) {
            PolylineOptions polylineOptions = style.getPolylineOptions();
            if (inlineStyle != null) {
                setInlineLineStringStyle(polylineOptions, inlineStyle);
            } else if (style.isLineRandomColorMode()) {
                polylineOptions.color(KmlStyle.computeRandomColor(polylineOptions.getColor()));
            }
            Polyline polyline = addLineStringToMap(polylineOptions, (LineString) geometry);
            polyline.setVisible(isVisible);
            if (hasDrawOrder) {
                polyline.setZIndex(drawOrder);
            }
            return polyline;
        } else if (c == 2) {
            PolygonOptions polygonOptions = style.getPolygonOptions();
            if (inlineStyle != null) {
                setInlinePolygonStyle(polygonOptions, inlineStyle);
            } else if (style.isPolyRandomColorMode()) {
                polygonOptions.fillColor(KmlStyle.computeRandomColor(polygonOptions.getFillColor()));
            }
            Polygon polygon = addPolygonToMap(polygonOptions, (DataPolygon) geometry);
            polygon.setVisible(isVisible);
            if (hasDrawOrder) {
                polygon.setZIndex(drawOrder);
            }
            return polygon;
        } else if (c != 3) {
            return null;
        } else {
            return addMultiGeometryToMap(placemark, (KmlMultiGeometry) geometry, style, inlineStyle, isVisible);
        }
    }

    /* access modifiers changed from: protected */
    public Marker addPointToMap(MarkerOptions markerOptions, Point point) {
        markerOptions.position(point.getGeometryObject());
        return this.mMap.addMarker(markerOptions);
    }

    private void setInlinePointStyle(MarkerOptions markerOptions, KmlStyle inlineStyle, String markerUrlIconUrl) {
        MarkerOptions inlineMarkerOptions = inlineStyle.getMarkerOptions();
        if (inlineStyle.isStyleSet("heading")) {
            markerOptions.rotation(inlineMarkerOptions.getRotation());
        }
        if (inlineStyle.isStyleSet("hotSpot")) {
            markerOptions.anchor(inlineMarkerOptions.getAnchorU(), inlineMarkerOptions.getAnchorV());
        }
        if (inlineStyle.isStyleSet("markerColor")) {
            markerOptions.icon(inlineMarkerOptions.getIcon());
        }
        if (inlineStyle.isStyleSet("iconUrl")) {
            addMarkerIcons(inlineStyle.getIconUrl(), markerOptions);
        } else if (markerUrlIconUrl != null) {
            addMarkerIcons(markerUrlIconUrl, markerOptions);
        }
    }

    /* access modifiers changed from: protected */
    public Polyline addLineStringToMap(PolylineOptions polylineOptions, LineString lineString) {
        polylineOptions.addAll(lineString.getGeometryObject());
        Polyline addedPolyline = this.mMap.addPolyline(polylineOptions);
        addedPolyline.setClickable(true);
        return addedPolyline;
    }

    private void setInlineLineStringStyle(PolylineOptions polylineOptions, KmlStyle inlineStyle) {
        PolylineOptions inlinePolylineOptions = inlineStyle.getPolylineOptions();
        if (inlineStyle.isStyleSet("outlineColor")) {
            polylineOptions.color(inlinePolylineOptions.getColor());
        }
        if (inlineStyle.isStyleSet("width")) {
            polylineOptions.width(inlinePolylineOptions.getWidth());
        }
        if (inlineStyle.isLineRandomColorMode()) {
            polylineOptions.color(KmlStyle.computeRandomColor(inlinePolylineOptions.getColor()));
        }
    }

    /* access modifiers changed from: protected */
    public Polygon addPolygonToMap(PolygonOptions polygonOptions, DataPolygon polygon) {
        polygonOptions.addAll(polygon.getOuterBoundaryCoordinates());
        for (List<LatLng> innerBoundary : polygon.getInnerBoundaryCoordinates()) {
            polygonOptions.addHole(innerBoundary);
        }
        Polygon addedPolygon = this.mMap.addPolygon(polygonOptions);
        addedPolygon.setClickable(true);
        return addedPolygon;
    }

    private void setInlinePolygonStyle(PolygonOptions polygonOptions, KmlStyle inlineStyle) {
        PolygonOptions inlinePolygonOptions = inlineStyle.getPolygonOptions();
        if (inlineStyle.hasFill() && inlineStyle.isStyleSet("fillColor")) {
            polygonOptions.fillColor(inlinePolygonOptions.getFillColor());
        }
        if (inlineStyle.hasOutline()) {
            if (inlineStyle.isStyleSet("outlineColor")) {
                polygonOptions.strokeColor(inlinePolygonOptions.getStrokeColor());
            }
            if (inlineStyle.isStyleSet("width")) {
                polygonOptions.strokeWidth(inlinePolygonOptions.getStrokeWidth());
            }
        }
        if (inlineStyle.isPolyRandomColorMode()) {
            polygonOptions.fillColor(KmlStyle.computeRandomColor(inlinePolygonOptions.getFillColor()));
        }
    }

    private ArrayList<Object> addGeometryCollectionToMap(GeoJsonFeature feature, List<Geometry> geoJsonGeometries) {
        ArrayList<Object> geometries = new ArrayList<>();
        for (Geometry geometry : geoJsonGeometries) {
            geometries.add(addGeoJsonFeatureToMap(feature, geometry));
        }
        return geometries;
    }

    protected static boolean getPlacemarkVisibility(Feature feature) {
        String str = "visibility";
        if (!feature.hasProperty(str) || Integer.parseInt(feature.getProperty(str)) != 0) {
            return true;
        }
        return false;
    }

    public void assignStyleMap(HashMap<String, String> styleMap, HashMap<String, KmlStyle> styles) {
        for (String styleMapKey : styleMap.keySet()) {
            String styleMapValue = (String) styleMap.get(styleMapKey);
            if (styles.containsKey(styleMapValue)) {
                styles.put(styleMapKey, styles.get(styleMapValue));
            }
        }
    }

    private ArrayList<Object> addMultiGeometryToMap(KmlPlacemark placemark, KmlMultiGeometry multiGeometry, KmlStyle urlStyle, KmlStyle inlineStyle, boolean isContainerVisible) {
        ArrayList<Object> mapObjects = new ArrayList<>();
        Iterator it = multiGeometry.getGeometryObject().iterator();
        while (it.hasNext()) {
            mapObjects.add(addKmlPlacemarkToMap(placemark, (Geometry) it.next(), urlStyle, inlineStyle, isContainerVisible));
        }
        return mapObjects;
    }

    private ArrayList<Marker> addMultiPointToMap(GeoJsonPointStyle pointStyle, GeoJsonMultiPoint multiPoint) {
        ArrayList<Marker> markers = new ArrayList<>();
        for (GeoJsonPoint geoJsonPoint : multiPoint.getPoints()) {
            markers.add(addPointToMap(pointStyle.toMarkerOptions(), geoJsonPoint));
        }
        return markers;
    }

    private ArrayList<Polyline> addMultiLineStringToMap(GeoJsonLineStringStyle lineStringStyle, GeoJsonMultiLineString multiLineString) {
        ArrayList<Polyline> polylines = new ArrayList<>();
        for (GeoJsonLineString geoJsonLineString : multiLineString.getLineStrings()) {
            polylines.add(addLineStringToMap(lineStringStyle.toPolylineOptions(), geoJsonLineString));
        }
        return polylines;
    }

    private ArrayList<Polygon> addMultiPolygonToMap(GeoJsonPolygonStyle polygonStyle, GeoJsonMultiPolygon multiPolygon) {
        ArrayList<Polygon> polygons = new ArrayList<>();
        for (GeoJsonPolygon geoJsonPolygon : multiPolygon.getPolygons()) {
            polygons.add(addPolygonToMap(polygonStyle.toPolygonOptions(), geoJsonPolygon));
        }
        return polygons;
    }

    private void addMarkerIcons(String styleUrl, MarkerOptions markerOptions) {
        if (this.mImagesCache.get(styleUrl) != null) {
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap((Bitmap) this.mImagesCache.get(styleUrl)));
        } else if (!this.mMarkerIconUrls.contains(styleUrl)) {
            this.mMarkerIconUrls.add(styleUrl);
        }
    }

    public GroundOverlay attachGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        return this.mMap.addGroundOverlay(groundOverlayOptions);
    }

    private void setMarkerInfoWindow(KmlStyle style, Marker marker, KmlPlacemark placemark) {
        String str = "name";
        boolean hasName = placemark.hasProperty(str);
        String str2 = "description";
        boolean hasDescription = placemark.hasProperty(str2);
        boolean hasBalloonOptions = style.hasBalloonStyle();
        String str3 = "text";
        boolean hasBalloonText = style.getBalloonOptions().containsKey(str3);
        if (hasBalloonOptions && hasBalloonText) {
            marker.setTitle((String) style.getBalloonOptions().get(str3));
            createInfoWindow();
        } else if (hasBalloonOptions && hasName) {
            marker.setTitle(placemark.getProperty(str));
            createInfoWindow();
        } else if (hasName && hasDescription) {
            marker.setTitle(placemark.getProperty(str));
            marker.setSnippet(placemark.getProperty(str2));
            createInfoWindow();
        } else if (hasDescription) {
            marker.setTitle(placemark.getProperty(str2));
            createInfoWindow();
        } else if (hasName) {
            marker.setTitle(placemark.getProperty(str));
            createInfoWindow();
        }
    }

    private void createInfoWindow() {
        this.mMap.setInfoWindowAdapter(new InfoWindowAdapter() {
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            public View getInfoContents(Marker arg0) {
                View view = LayoutInflater.from(Renderer.this.mContext).inflate(C1160R.layout.amu_info_window, null);
                TextView infoWindowText = (TextView) view.findViewById(C1160R.C1163id.window);
                if (arg0.getSnippet() != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(arg0.getTitle());
                    sb.append("<br>");
                    sb.append(arg0.getSnippet());
                    infoWindowText.setText(Html.fromHtml(sb.toString()));
                } else {
                    infoWindowText.setText(Html.fromHtml(arg0.getTitle()));
                }
                return view;
            }
        });
    }
}
