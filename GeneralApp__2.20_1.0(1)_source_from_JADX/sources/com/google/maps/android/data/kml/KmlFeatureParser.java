package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Geometry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class KmlFeatureParser {
    private static final String BOUNDARY_REGEX = "outerBoundaryIs|innerBoundaryIs";
    private static final String COMPASS_REGEX = "north|south|east|west";
    private static final String EXTENDED_DATA = "ExtendedData";
    private static final String GEOMETRY_REGEX = "Point|LineString|Polygon|MultiGeometry";
    private static final int LATITUDE_INDEX = 1;
    private static final int LONGITUDE_INDEX = 0;
    private static final String PROPERTY_REGEX = "name|description|drawOrder|visibility|open|address|phoneNumber";
    private static final String STYLE_TAG = "Style";
    private static final String STYLE_URL_TAG = "styleUrl";

    KmlFeatureParser() {
    }

    static KmlPlacemark createPlacemark(XmlPullParser parser) throws IOException, XmlPullParserException {
        String styleId = null;
        KmlStyle inlineStyle = null;
        HashMap<String, String> properties = new HashMap<>();
        Geometry geometry = null;
        int eventType = parser.getEventType();
        while (true) {
            if (eventType == 3 && parser.getName().equals("Placemark")) {
                return new KmlPlacemark(geometry, styleId, inlineStyle, properties);
            }
            if (eventType == 2) {
                if (parser.getName().equals(STYLE_URL_TAG)) {
                    styleId = parser.nextText();
                } else if (parser.getName().matches(GEOMETRY_REGEX)) {
                    geometry = createGeometry(parser, parser.getName());
                } else if (parser.getName().matches(PROPERTY_REGEX)) {
                    properties.put(parser.getName(), parser.nextText());
                } else if (parser.getName().equals(EXTENDED_DATA)) {
                    properties.putAll(setExtendedDataProperties(parser));
                } else if (parser.getName().equals(STYLE_TAG)) {
                    inlineStyle = KmlStyleParser.createStyle(parser);
                }
            }
            eventType = parser.next();
        }
    }

    static KmlGroundOverlay createGroundOverlay(XmlPullParser parser) throws IOException, XmlPullParserException {
        int visibility = 1;
        String imageUrl = null;
        HashMap<String, String> properties = new HashMap<>();
        HashMap hashMap = new HashMap();
        float rotation = 0.0f;
        float drawOrder = 0.0f;
        int eventType = parser.getEventType();
        while (true) {
            if (eventType != 3 || !parser.getName().equals("GroundOverlay")) {
                if (eventType == 2) {
                    if (parser.getName().equals("Icon")) {
                        imageUrl = getImageUrl(parser);
                    } else if (parser.getName().equals("drawOrder")) {
                        drawOrder = Float.parseFloat(parser.nextText());
                    } else if (parser.getName().equals("visibility")) {
                        visibility = Integer.parseInt(parser.nextText());
                    } else if (parser.getName().equals(EXTENDED_DATA)) {
                        properties.putAll(setExtendedDataProperties(parser));
                    } else if (parser.getName().equals("rotation")) {
                        rotation = getRotation(parser);
                    } else if (parser.getName().matches(PROPERTY_REGEX) || parser.getName().equals("color")) {
                        properties.put(parser.getName(), parser.nextText());
                    } else if (parser.getName().matches(COMPASS_REGEX)) {
                        hashMap.put(parser.getName(), Double.valueOf(Double.parseDouble(parser.nextText())));
                    }
                }
                eventType = parser.next();
            } else {
                KmlGroundOverlay kmlGroundOverlay = new KmlGroundOverlay(imageUrl, createLatLngBounds((Double) hashMap.get("north"), (Double) hashMap.get("south"), (Double) hashMap.get("east"), (Double) hashMap.get("west")), drawOrder, visibility, properties, rotation);
                return kmlGroundOverlay;
            }
        }
    }

    private static float getRotation(XmlPullParser parser) throws IOException, XmlPullParserException {
        return -Float.parseFloat(parser.nextText());
    }

    private static String getImageUrl(XmlPullParser parser) throws IOException, XmlPullParserException {
        int eventType = parser.getEventType();
        while (true) {
            if (eventType == 3 && parser.getName().equals("Icon")) {
                return null;
            }
            if (eventType == 2 && parser.getName().equals("href")) {
                return parser.nextText();
            }
            eventType = parser.next();
        }
    }

    private static Geometry createGeometry(XmlPullParser parser, String geometryType) throws IOException, XmlPullParserException {
        int eventType = parser.getEventType();
        while (true) {
            if (eventType == 3 && parser.getName().equals(geometryType)) {
                return null;
            }
            if (eventType == 2) {
                if (parser.getName().equals("Point")) {
                    return createPoint(parser);
                }
                if (parser.getName().equals("LineString")) {
                    return createLineString(parser);
                }
                if (parser.getName().equals(KmlPolygon.GEOMETRY_TYPE)) {
                    return createPolygon(parser);
                }
                if (parser.getName().equals("MultiGeometry")) {
                    return createMultiGeometry(parser);
                }
            }
            eventType = parser.next();
        }
    }

    private static HashMap<String, String> setExtendedDataProperties(XmlPullParser parser) throws XmlPullParserException, IOException {
        HashMap<String, String> properties = new HashMap<>();
        String propertyKey = null;
        int eventType = parser.getEventType();
        while (true) {
            if (eventType == 3 && parser.getName().equals(EXTENDED_DATA)) {
                return properties;
            }
            if (eventType == 2) {
                if (parser.getName().equals("Data")) {
                    propertyKey = parser.getAttributeValue(null, "name");
                } else if (parser.getName().equals("value") && propertyKey != null) {
                    properties.put(propertyKey, parser.nextText());
                    propertyKey = null;
                }
            }
            eventType = parser.next();
        }
    }

    private static KmlPoint createPoint(XmlPullParser parser) throws XmlPullParserException, IOException {
        LatLng coordinate = null;
        int eventType = parser.getEventType();
        while (true) {
            if (eventType == 3 && parser.getName().equals("Point")) {
                return new KmlPoint(coordinate);
            }
            if (eventType == 2 && parser.getName().equals("coordinates")) {
                coordinate = convertToLatLng(parser.nextText());
            }
            eventType = parser.next();
        }
    }

    private static KmlLineString createLineString(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<LatLng> coordinates = new ArrayList<>();
        int eventType = parser.getEventType();
        while (true) {
            if (eventType == 3 && parser.getName().equals("LineString")) {
                return new KmlLineString(coordinates);
            }
            if (eventType == 2 && parser.getName().equals("coordinates")) {
                coordinates = convertToLatLngArray(parser.nextText());
            }
            eventType = parser.next();
        }
    }

    private static KmlPolygon createPolygon(XmlPullParser parser) throws XmlPullParserException, IOException {
        Boolean isOuterBoundary = Boolean.valueOf(false);
        List<LatLng> outerBoundary = new ArrayList<>();
        List<List<LatLng>> innerBoundaries = new ArrayList<>();
        int eventType = parser.getEventType();
        while (true) {
            if (eventType == 3 && parser.getName().equals(KmlPolygon.GEOMETRY_TYPE)) {
                return new KmlPolygon(outerBoundary, innerBoundaries);
            }
            if (eventType == 2) {
                if (parser.getName().matches(BOUNDARY_REGEX)) {
                    isOuterBoundary = Boolean.valueOf(parser.getName().equals("outerBoundaryIs"));
                } else if (parser.getName().equals("coordinates")) {
                    if (isOuterBoundary.booleanValue()) {
                        outerBoundary = convertToLatLngArray(parser.nextText());
                    } else {
                        innerBoundaries.add(convertToLatLngArray(parser.nextText()));
                    }
                }
            }
            eventType = parser.next();
        }
    }

    private static KmlMultiGeometry createMultiGeometry(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Geometry> geometries = new ArrayList<>();
        int eventType = parser.next();
        while (true) {
            if (eventType == 3 && parser.getName().equals("MultiGeometry")) {
                return new KmlMultiGeometry(geometries);
            }
            if (eventType == 2 && parser.getName().matches(GEOMETRY_REGEX)) {
                geometries.add(createGeometry(parser, parser.getName()));
            }
            eventType = parser.next();
        }
    }

    private static ArrayList<LatLng> convertToLatLngArray(String coordinatesString) {
        ArrayList<LatLng> coordinatesArray = new ArrayList<>();
        for (String coordinate : coordinatesString.trim().split("(\\s+)")) {
            coordinatesArray.add(convertToLatLng(coordinate));
        }
        return coordinatesArray;
    }

    private static LatLng convertToLatLng(String coordinateString) {
        String[] coordinate = coordinateString.split(",");
        return new LatLng(Double.valueOf(Double.parseDouble(coordinate[1])).doubleValue(), Double.valueOf(Double.parseDouble(coordinate[0])).doubleValue());
    }

    private static LatLngBounds createLatLngBounds(Double north, Double south, Double east, Double west) {
        return new LatLngBounds(new LatLng(south.doubleValue(), west.doubleValue()), new LatLng(north.doubleValue(), east.doubleValue()));
    }
}
