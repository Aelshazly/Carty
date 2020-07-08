package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.HashMap;
import java.util.Map;

public class KmlGroundOverlay {
    private final GroundOverlayOptions mGroundOverlayOptions = new GroundOverlayOptions();
    private String mImageUrl;
    private LatLngBounds mLatLngBox;
    private final Map<String, String> mProperties;

    KmlGroundOverlay(String imageUrl, LatLngBounds latLonBox, float drawOrder, int visibility, HashMap<String, String> properties, float rotation) {
        this.mImageUrl = imageUrl;
        this.mProperties = properties;
        if (latLonBox != null) {
            this.mLatLngBox = latLonBox;
            this.mGroundOverlayOptions.positionFromBounds(latLonBox);
            this.mGroundOverlayOptions.bearing(rotation);
            this.mGroundOverlayOptions.zIndex(drawOrder);
            this.mGroundOverlayOptions.visible(visibility != 0);
            return;
        }
        throw new IllegalArgumentException("No LatLonBox given");
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public LatLngBounds getLatLngBox() {
        return this.mLatLngBox;
    }

    public Iterable<String> getProperties() {
        return this.mProperties.keySet();
    }

    public String getProperty(String keyValue) {
        return (String) this.mProperties.get(keyValue);
    }

    public boolean hasProperty(String keyValue) {
        return this.mProperties.get(keyValue) != null;
    }

    /* access modifiers changed from: 0000 */
    public GroundOverlayOptions getGroundOverlayOptions() {
        return this.mGroundOverlayOptions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroundOverlay").append("{");
        sb.append("\n properties=");
        sb.append(this.mProperties);
        sb.append(",\n image url=");
        sb.append(this.mImageUrl);
        sb.append(",\n LatLngBox=");
        sb.append(this.mLatLngBox);
        sb.append("\n}\n");
        return sb.toString();
    }
}
