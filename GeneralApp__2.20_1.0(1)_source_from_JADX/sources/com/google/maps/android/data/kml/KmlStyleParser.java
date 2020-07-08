package com.google.maps.android.data.kml;

import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class KmlStyleParser {
    private static final String COLOR_STYLE_COLOR = "color";
    private static final String COLOR_STYLE_MODE = "colorMode";
    private static final String ICON_STYLE_HEADING = "heading";
    private static final String ICON_STYLE_HOTSPOT = "hotSpot";
    private static final String ICON_STYLE_SCALE = "scale";
    private static final String ICON_STYLE_URL = "Icon";
    private static final String LINE_STYLE_WIDTH = "width";
    private static final String POLY_STYLE_FILL = "fill";
    private static final String POLY_STYLE_OUTLINE = "outline";
    private static final String STYLE_MAP_KEY = "key";
    private static final String STYLE_MAP_NORMAL_STYLE = "normal";
    private static final String STYLE_TAG = "styleUrl";

    KmlStyleParser() {
    }

    static KmlStyle createStyle(XmlPullParser parser) throws IOException, XmlPullParserException {
        KmlStyle styleProperties = new KmlStyle();
        setStyleId(parser.getAttributeValue(null, "id"), styleProperties);
        int eventType = parser.getEventType();
        while (true) {
            if (eventType == 3 && parser.getName().equals("Style")) {
                return styleProperties;
            }
            if (eventType == 2) {
                if (parser.getName().equals("IconStyle")) {
                    createIconStyle(parser, styleProperties);
                } else if (parser.getName().equals("LineStyle")) {
                    createLineStyle(parser, styleProperties);
                } else if (parser.getName().equals("PolyStyle")) {
                    createPolyStyle(parser, styleProperties);
                } else if (parser.getName().equals("BalloonStyle")) {
                    createBalloonStyle(parser, styleProperties);
                }
            }
            eventType = parser.next();
        }
    }

    private static void setStyleId(String id, KmlStyle styleProperties) {
        if (id != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("#");
            sb.append(id);
            styleProperties.setStyleId(sb.toString());
        }
    }

    private static void createIconStyle(XmlPullParser parser, KmlStyle style) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        while (true) {
            if (eventType != 3 || !parser.getName().equals("IconStyle")) {
                if (eventType == 2) {
                    if (parser.getName().equals(ICON_STYLE_HEADING)) {
                        style.setHeading(Float.parseFloat(parser.nextText()));
                    } else if (parser.getName().equals(ICON_STYLE_URL)) {
                        setIconUrl(parser, style);
                    } else if (parser.getName().equals(ICON_STYLE_HOTSPOT)) {
                        setIconHotSpot(parser, style);
                    } else if (parser.getName().equals(ICON_STYLE_SCALE)) {
                        style.setIconScale(Double.parseDouble(parser.nextText()));
                    } else if (parser.getName().equals(COLOR_STYLE_COLOR)) {
                        style.setMarkerColor(parser.nextText());
                    } else if (parser.getName().equals(COLOR_STYLE_MODE)) {
                        style.setIconColorMode(parser.nextText());
                    }
                }
                eventType = parser.next();
            } else {
                return;
            }
        }
    }

    static HashMap<String, String> createStyleMap(XmlPullParser parser) throws XmlPullParserException, IOException {
        HashMap<String, String> styleMaps = new HashMap<>();
        Boolean isNormalStyleMapValue = Boolean.valueOf(false);
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(parser.getAttributeValue(null, "id"));
        String styleId = sb.toString();
        int eventType = parser.getEventType();
        while (true) {
            if (eventType == 3 && parser.getName().equals("StyleMap")) {
                return styleMaps;
            }
            if (eventType == 2) {
                if (parser.getName().equals(STYLE_MAP_KEY) && parser.nextText().equals(STYLE_MAP_NORMAL_STYLE)) {
                    isNormalStyleMapValue = Boolean.valueOf(true);
                } else if (parser.getName().equals(STYLE_TAG) && isNormalStyleMapValue.booleanValue()) {
                    styleMaps.put(styleId, parser.nextText());
                    isNormalStyleMapValue = Boolean.valueOf(false);
                }
            }
            eventType = parser.next();
        }
    }

    private static void createBalloonStyle(XmlPullParser parser, KmlStyle style) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        while (true) {
            if (eventType != 3 || !parser.getName().equals("BalloonStyle")) {
                if (eventType == 2 && parser.getName().equals("text")) {
                    style.setInfoWindowText(parser.nextText());
                }
                eventType = parser.next();
            } else {
                return;
            }
        }
    }

    private static void setIconUrl(XmlPullParser parser, KmlStyle style) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        while (true) {
            if (eventType != 3 || !parser.getName().equals(ICON_STYLE_URL)) {
                if (eventType == 2 && parser.getName().equals("href")) {
                    style.setIconUrl(parser.nextText());
                }
                eventType = parser.next();
            } else {
                return;
            }
        }
    }

    private static void setIconHotSpot(XmlPullParser parser, KmlStyle style) {
        Float xValue = Float.valueOf(Float.parseFloat(parser.getAttributeValue(null, "x")));
        Float yValue = Float.valueOf(Float.parseFloat(parser.getAttributeValue(null, "y")));
        style.setHotSpot(xValue.floatValue(), yValue.floatValue(), parser.getAttributeValue(null, "xunits"), parser.getAttributeValue(null, "yunits"));
    }

    private static void createLineStyle(XmlPullParser parser, KmlStyle style) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        while (true) {
            if (eventType != 3 || !parser.getName().equals("LineStyle")) {
                if (eventType == 2) {
                    if (parser.getName().equals(COLOR_STYLE_COLOR)) {
                        style.setOutlineColor(parser.nextText());
                    } else if (parser.getName().equals(LINE_STYLE_WIDTH)) {
                        style.setWidth(Float.valueOf(parser.nextText()));
                    } else if (parser.getName().equals(COLOR_STYLE_MODE)) {
                        style.setLineColorMode(parser.nextText());
                    }
                }
                eventType = parser.next();
            } else {
                return;
            }
        }
    }

    private static void createPolyStyle(XmlPullParser parser, KmlStyle style) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        while (true) {
            if (eventType != 3 || !parser.getName().equals("PolyStyle")) {
                if (eventType == 2) {
                    if (parser.getName().equals(COLOR_STYLE_COLOR)) {
                        style.setFillColor(parser.nextText());
                    } else if (parser.getName().equals(POLY_STYLE_OUTLINE)) {
                        style.setOutline(KmlBoolean.parseBoolean(parser.nextText()));
                    } else if (parser.getName().equals(POLY_STYLE_FILL)) {
                        style.setFill(KmlBoolean.parseBoolean(parser.nextText()));
                    } else if (parser.getName().equals(COLOR_STYLE_MODE)) {
                        style.setPolyColorMode(parser.nextText());
                    }
                }
                eventType = parser.next();
            } else {
                return;
            }
        }
    }
}
