package com.google.maps.android.data.kml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class KmlContainerParser {
    private static final String CONTAINER_REGEX = "Folder|Document";
    private static final String EXTENDED_DATA = "ExtendedData";
    private static final String GROUND_OVERLAY = "GroundOverlay";
    private static final String PLACEMARK = "Placemark";
    private static final String PROPERTY_REGEX = "name|description|visibility|open|address|phoneNumber";
    private static final String STYLE = "Style";
    private static final String STYLE_MAP = "StyleMap";
    private static final String UNSUPPORTED_REGEX = "altitude|altitudeModeGroup|altitudeMode|begin|bottomFov|cookie|displayName|displayMode|displayMode|end|expires|extrude|flyToView|gridOrigin|httpQuery|leftFov|linkDescription|linkName|linkSnippet|listItemType|maxSnippetLines|maxSessionLength|message|minAltitude|minFadeExtent|minLodPixels|minRefreshPeriod|maxAltitude|maxFadeExtent|maxLodPixels|maxHeight|maxWidth|near|overlayXY|range|refreshMode|refreshInterval|refreshVisibility|rightFov|roll|rotationXY|screenXY|shape|sourceHref|state|targetHref|tessellate|tileSize|topFov|viewBoundScale|viewFormat|viewRefreshMode|viewRefreshTime|when";

    KmlContainerParser() {
    }

    static KmlContainer createContainer(XmlPullParser parser) throws XmlPullParserException, IOException {
        return assignPropertiesToContainer(parser);
    }

    private static KmlContainer assignPropertiesToContainer(XmlPullParser parser) throws XmlPullParserException, IOException {
        HashMap hashMap;
        String startTag;
        int eventType;
        HashMap hashMap2;
        XmlPullParser xmlPullParser = parser;
        String startTag2 = parser.getName();
        String containerId = null;
        HashMap<String, String> containerProperties = new HashMap<>();
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        ArrayList arrayList = new ArrayList();
        HashMap hashMap5 = new HashMap();
        HashMap hashMap6 = new HashMap();
        String str = "id";
        if (xmlPullParser.getAttributeValue(null, str) != null) {
            containerId = xmlPullParser.getAttributeValue(null, str);
        }
        parser.next();
        int eventType2 = parser.getEventType();
        while (true) {
            if (eventType2 != 3) {
                startTag = startTag2;
                eventType = eventType2;
                hashMap = hashMap6;
            } else if (!parser.getName().equals(startTag2)) {
                startTag = startTag2;
                eventType = eventType2;
                hashMap = hashMap6;
            } else {
                String str2 = startTag2;
                int i = eventType2;
                HashMap hashMap7 = hashMap6;
                KmlContainer kmlContainer = new KmlContainer(containerProperties, hashMap3, hashMap4, hashMap5, arrayList, hashMap6, containerId);
                return kmlContainer;
            }
            if (eventType != 2) {
                hashMap2 = hashMap;
            } else if (parser.getName().matches(UNSUPPORTED_REGEX)) {
                KmlParser.skip(parser);
                hashMap2 = hashMap;
            } else if (parser.getName().matches(CONTAINER_REGEX)) {
                arrayList.add(assignPropertiesToContainer(parser));
                hashMap2 = hashMap;
            } else if (parser.getName().matches(PROPERTY_REGEX)) {
                containerProperties.put(parser.getName(), parser.nextText());
                hashMap2 = hashMap;
            } else if (parser.getName().equals(STYLE_MAP)) {
                setContainerStyleMap(xmlPullParser, hashMap5);
                hashMap2 = hashMap;
            } else if (parser.getName().equals(STYLE)) {
                setContainerStyle(xmlPullParser, hashMap3);
                hashMap2 = hashMap;
            } else if (parser.getName().equals(PLACEMARK)) {
                setContainerPlacemark(xmlPullParser, hashMap4);
                hashMap2 = hashMap;
            } else if (parser.getName().equals(EXTENDED_DATA)) {
                setExtendedDataProperties(xmlPullParser, containerProperties);
                hashMap2 = hashMap;
            } else if (parser.getName().equals(GROUND_OVERLAY)) {
                hashMap2 = hashMap;
                hashMap2.put(KmlFeatureParser.createGroundOverlay(parser), null);
            } else {
                hashMap2 = hashMap;
            }
            eventType2 = parser.next();
            hashMap6 = hashMap2;
            startTag2 = startTag;
        }
    }

    private static void setContainerStyleMap(XmlPullParser parser, HashMap<String, String> containerStyleMap) throws XmlPullParserException, IOException {
        containerStyleMap.putAll(KmlStyleParser.createStyleMap(parser));
    }

    private static void setExtendedDataProperties(XmlPullParser parser, HashMap<String, String> mContainerProperties) throws XmlPullParserException, IOException {
        String propertyKey = null;
        int eventType = parser.getEventType();
        while (true) {
            if (eventType != 3 || !parser.getName().equals(EXTENDED_DATA)) {
                if (eventType == 2) {
                    if (parser.getName().equals("Data")) {
                        propertyKey = parser.getAttributeValue(null, "name");
                    } else if (parser.getName().equals("value") && propertyKey != null) {
                        mContainerProperties.put(propertyKey, parser.nextText());
                        propertyKey = null;
                    }
                }
                eventType = parser.next();
            } else {
                return;
            }
        }
    }

    private static void setContainerStyle(XmlPullParser parser, HashMap<String, KmlStyle> containerStyles) throws XmlPullParserException, IOException {
        if (parser.getAttributeValue(null, "id") != null) {
            KmlStyle style = KmlStyleParser.createStyle(parser);
            containerStyles.put(style.getStyleId(), style);
        }
    }

    private static void setContainerPlacemark(XmlPullParser parser, HashMap<KmlPlacemark, Object> containerPlacemarks) throws XmlPullParserException, IOException {
        containerPlacemarks.put(KmlFeatureParser.createPlacemark(parser), null);
    }
}
