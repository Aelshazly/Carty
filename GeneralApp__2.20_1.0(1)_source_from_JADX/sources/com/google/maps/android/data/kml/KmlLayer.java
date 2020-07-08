package com.google.maps.android.data.kml;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.data.Layer;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class KmlLayer extends Layer {
    public KmlLayer(GoogleMap map, int resourceId, Context context) throws XmlPullParserException, IOException {
        this(map, context.getResources().openRawResource(resourceId), context);
    }

    public KmlLayer(GoogleMap map, InputStream stream, Context context) throws XmlPullParserException, IOException {
        if (stream != null) {
            KmlRenderer mRenderer = new KmlRenderer(map, context);
            KmlParser parser = new KmlParser(createXmlParser(stream));
            parser.parseKml();
            stream.close();
            mRenderer.storeKmlData(parser.getStyles(), parser.getStyleMaps(), parser.getPlacemarks(), parser.getContainers(), parser.getGroundOverlays());
            storeRenderer(mRenderer);
            return;
        }
        throw new IllegalArgumentException("KML InputStream cannot be null");
    }

    private static XmlPullParser createXmlParser(InputStream stream) throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(stream, null);
        return parser;
    }

    public void addLayerToMap() throws IOException, XmlPullParserException {
        super.addKMLToMap();
    }

    public boolean hasPlacemarks() {
        return hasFeatures();
    }

    public Iterable<KmlPlacemark> getPlacemarks() {
        return getFeatures();
    }

    public boolean hasContainers() {
        return super.hasContainers();
    }

    public Iterable<KmlContainer> getContainers() {
        return super.getContainers();
    }

    public Iterable<KmlGroundOverlay> getGroundOverlays() {
        return super.getGroundOverlays();
    }
}
