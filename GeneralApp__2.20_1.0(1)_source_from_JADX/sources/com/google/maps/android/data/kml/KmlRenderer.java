package com.google.maps.android.data.kml;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Renderer;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class KmlRenderer extends Renderer {
    private static final String LOG_TAG = "KmlRenderer";
    /* access modifiers changed from: private */
    public ArrayList<KmlContainer> mContainers;
    private boolean mGroundOverlayImagesDownloaded = false;
    private final ArrayList<String> mGroundOverlayUrls = new ArrayList<>();
    /* access modifiers changed from: private */
    public HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlays;
    private boolean mMarkerIconsDownloaded = false;

    private class GroundOverlayImageDownload extends AsyncTask<String, Void, Bitmap> {
        private final String mGroundOverlayUrl;

        public GroundOverlayImageDownload(String groundOverlayUrl) {
            this.mGroundOverlayUrl = groundOverlayUrl;
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(String... params) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(this.mGroundOverlayUrl).getContent());
            } catch (MalformedURLException e) {
                return BitmapFactory.decodeFile(this.mGroundOverlayUrl);
            } catch (IOException e2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Image [");
                sb.append(this.mGroundOverlayUrl);
                sb.append("] download issue");
                Log.e(KmlRenderer.LOG_TAG, sb.toString(), e2);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Image at this URL could not be found ");
                sb.append(this.mGroundOverlayUrl);
                Log.e(KmlRenderer.LOG_TAG, sb.toString());
                return;
            }
            KmlRenderer.this.putImagesCache(this.mGroundOverlayUrl, bitmap);
            if (KmlRenderer.this.isLayerOnMap()) {
                KmlRenderer kmlRenderer = KmlRenderer.this;
                kmlRenderer.addGroundOverlayToMap(this.mGroundOverlayUrl, kmlRenderer.mGroundOverlays, true);
                KmlRenderer kmlRenderer2 = KmlRenderer.this;
                kmlRenderer2.addGroundOverlayInContainerGroups(this.mGroundOverlayUrl, kmlRenderer2.mContainers, true);
            }
        }
    }

    private class MarkerIconImageDownload extends AsyncTask<String, Void, Bitmap> {
        private final String mIconUrl;

        public MarkerIconImageDownload(String iconUrl) {
            this.mIconUrl = iconUrl;
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(String... params) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(this.mIconUrl).getContent());
            } catch (MalformedURLException e) {
                return BitmapFactory.decodeFile(this.mIconUrl);
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Image at this URL could not be found ");
                sb.append(this.mIconUrl);
                Log.e(KmlRenderer.LOG_TAG, sb.toString());
                return;
            }
            KmlRenderer.this.putImagesCache(this.mIconUrl, bitmap);
            if (KmlRenderer.this.isLayerOnMap()) {
                KmlRenderer kmlRenderer = KmlRenderer.this;
                kmlRenderer.addIconToMarkers(this.mIconUrl, kmlRenderer.getAllFeatures());
                KmlRenderer kmlRenderer2 = KmlRenderer.this;
                kmlRenderer2.addContainerGroupIconsToMarkers(this.mIconUrl, kmlRenderer2.mContainers);
            }
        }
    }

    KmlRenderer(GoogleMap map, Context context) {
        super(map, context);
    }

    private static BitmapDescriptor scaleIcon(Bitmap unscaledBitmap, Double scale) {
        return BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(unscaledBitmap, (int) (((double) unscaledBitmap.getWidth()) * scale.doubleValue()), (int) (((double) unscaledBitmap.getHeight()) * scale.doubleValue()), false));
    }

    private void removePlacemarks(HashMap<? extends Feature, Object> placemarks) {
        removeFeatures(placemarks);
    }

    static boolean getContainerVisibility(KmlContainer kmlContainer, boolean isParentContainerVisible) {
        boolean isChildContainerVisible = true;
        String str = "visibility";
        if (kmlContainer.hasProperty(str) && Integer.parseInt(kmlContainer.getProperty(str)) == 0) {
            isChildContainerVisible = false;
        }
        return isParentContainerVisible && isChildContainerVisible;
    }

    private void removeGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> groundOverlays) {
        for (GroundOverlay groundOverlay : groundOverlays.values()) {
            groundOverlay.remove();
        }
    }

    private void removeContainers(Iterable<KmlContainer> containers) {
        for (KmlContainer container : containers) {
            removePlacemarks(container.getPlacemarksHashMap());
            removeGroundOverlays(container.getGroundOverlayHashMap());
            removeContainers(container.getContainers());
        }
    }

    public void addLayerToMap() {
        setLayerVisibility(true);
        this.mGroundOverlays = getGroundOverlayMap();
        this.mContainers = getContainerList();
        putStyles();
        assignStyleMap(getStyleMaps(), getStylesRenderer());
        addGroundOverlays(this.mGroundOverlays, this.mContainers);
        addContainerGroupToMap(this.mContainers, true);
        addPlacemarksToMap(getAllFeatures());
        if (!this.mGroundOverlayImagesDownloaded) {
            downloadGroundOverlays();
        }
        if (!this.mMarkerIconsDownloaded) {
            downloadMarkerIcons();
        }
    }

    /* access modifiers changed from: 0000 */
    public void storeKmlData(HashMap<String, KmlStyle> styles, HashMap<String, String> styleMaps, HashMap<KmlPlacemark, Object> features, ArrayList<KmlContainer> folders, HashMap<KmlGroundOverlay, GroundOverlay> groundOverlays) {
        storeData(styles, styleMaps, features, folders, groundOverlays);
    }

    public void setMap(GoogleMap map) {
        removeLayerFromMap();
        super.setMap(map);
        addLayerToMap();
    }

    /* access modifiers changed from: 0000 */
    public boolean hasKmlPlacemarks() {
        return hasFeatures();
    }

    /* access modifiers changed from: 0000 */
    public Iterable<? extends Feature> getKmlPlacemarks() {
        return getFeatures();
    }

    public boolean hasNestedContainers() {
        return this.mContainers.size() > 0;
    }

    public Iterable<KmlContainer> getNestedContainers() {
        return this.mContainers;
    }

    public Iterable<KmlGroundOverlay> getGroundOverlays() {
        return this.mGroundOverlays.keySet();
    }

    public void removeLayerFromMap() {
        removePlacemarks(getAllFeatures());
        removeGroundOverlays(this.mGroundOverlays);
        if (hasNestedContainers()) {
            removeContainers(getNestedContainers());
        }
        setLayerVisibility(false);
        clearStylesRenderer();
    }

    private void addPlacemarksToMap(HashMap<? extends Feature, Object> placemarks) {
        for (Feature kmlPlacemark : placemarks.keySet()) {
            addFeature(kmlPlacemark);
        }
    }

    private void addContainerGroupToMap(Iterable<KmlContainer> kmlContainers, boolean containerVisibility) {
        for (KmlContainer container : kmlContainers) {
            boolean isContainerVisible = getContainerVisibility(container, containerVisibility);
            if (container.getStyles() != null) {
                putStyles(container.getStyles());
            }
            if (container.getStyleMap() != null) {
                super.assignStyleMap(container.getStyleMap(), getStylesRenderer());
            }
            addContainerObjectToMap(container, isContainerVisible);
            if (container.hasContainers()) {
                addContainerGroupToMap(container.getContainers(), isContainerVisible);
            }
        }
    }

    private void addContainerObjectToMap(KmlContainer kmlContainer, boolean isContainerVisible) {
        for (Feature placemark : kmlContainer.getPlacemarks()) {
            boolean isObjectVisible = isContainerVisible && getPlacemarkVisibility(placemark);
            if (placemark.getGeometry() != null) {
                Object mapObject = addKmlPlacemarkToMap((KmlPlacemark) placemark, placemark.getGeometry(), getPlacemarkStyle(placemark.getId()), ((KmlPlacemark) placemark).getInlineStyle(), isObjectVisible);
                kmlContainer.setPlacemark((KmlPlacemark) placemark, mapObject);
                putContainerFeature(mapObject, placemark);
            }
        }
    }

    private void downloadMarkerIcons() {
        this.mMarkerIconsDownloaded = true;
        Iterator<String> iterator = getMarkerIconUrls().iterator();
        while (iterator.hasNext()) {
            new MarkerIconImageDownload((String) iterator.next()).execute(new String[0]);
            iterator.remove();
        }
    }

    /* access modifiers changed from: private */
    public void addIconToMarkers(String iconUrl, HashMap<KmlPlacemark, Object> placemarks) {
        for (Feature placemark : placemarks.keySet()) {
            KmlStyle urlStyle = (KmlStyle) getStylesRenderer().get(placemark.getId());
            KmlStyle inlineStyle = ((KmlPlacemark) placemark).getInlineStyle();
            if ("Point".equals(placemark.getGeometry().getGeometryType())) {
                boolean isPlacemarkStyleIcon = true;
                boolean isInlineStyleIcon = inlineStyle != null && iconUrl.equals(inlineStyle.getIconUrl());
                if (urlStyle == null || !iconUrl.equals(urlStyle.getIconUrl())) {
                    isPlacemarkStyleIcon = false;
                }
                if (isInlineStyleIcon) {
                    scaleBitmap(inlineStyle, placemarks, (KmlPlacemark) placemark);
                } else if (isPlacemarkStyleIcon) {
                    scaleBitmap(urlStyle, placemarks, (KmlPlacemark) placemark);
                }
            }
        }
    }

    private void scaleBitmap(KmlStyle style, HashMap<KmlPlacemark, Object> placemarks, KmlPlacemark placemark) {
        double bitmapScale = style.getIconScale();
        ((Marker) placemarks.get(placemark)).setIcon(scaleIcon((Bitmap) getImagesCache().get(style.getIconUrl()), Double.valueOf(bitmapScale)));
    }

    /* access modifiers changed from: private */
    public void addContainerGroupIconsToMarkers(String iconUrl, Iterable<KmlContainer> kmlContainers) {
        for (KmlContainer container : kmlContainers) {
            addIconToMarkers(iconUrl, container.getPlacemarksHashMap());
            if (container.hasContainers()) {
                addContainerGroupIconsToMarkers(iconUrl, container.getContainers());
            }
        }
    }

    private void addGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> groundOverlays, Iterable<KmlContainer> kmlContainers) {
        addGroundOverlays(groundOverlays);
        for (KmlContainer container : kmlContainers) {
            addGroundOverlays(container.getGroundOverlayHashMap(), container.getContainers());
        }
    }

    private void addGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> groundOverlays) {
        for (KmlGroundOverlay groundOverlay : groundOverlays.keySet()) {
            String groundOverlayUrl = groundOverlay.getImageUrl();
            if (!(groundOverlayUrl == null || groundOverlay.getLatLngBox() == null)) {
                if (getImagesCache().get(groundOverlayUrl) != null) {
                    addGroundOverlayToMap(groundOverlayUrl, this.mGroundOverlays, true);
                } else if (!this.mGroundOverlayUrls.contains(groundOverlayUrl)) {
                    this.mGroundOverlayUrls.add(groundOverlayUrl);
                }
            }
        }
    }

    private void downloadGroundOverlays() {
        this.mGroundOverlayImagesDownloaded = true;
        Iterator<String> iterator = this.mGroundOverlayUrls.iterator();
        while (iterator.hasNext()) {
            new GroundOverlayImageDownload((String) iterator.next()).execute(new String[0]);
            iterator.remove();
        }
    }

    /* access modifiers changed from: private */
    public void addGroundOverlayToMap(String groundOverlayUrl, HashMap<KmlGroundOverlay, GroundOverlay> groundOverlays, boolean containerVisibility) {
        BitmapDescriptor groundOverlayBitmap = BitmapDescriptorFactory.fromBitmap((Bitmap) getImagesCache().get(groundOverlayUrl));
        for (KmlGroundOverlay kmlGroundOverlay : groundOverlays.keySet()) {
            if (kmlGroundOverlay.getImageUrl().equals(groundOverlayUrl)) {
                GroundOverlay mapGroundOverlay = attachGroundOverlay(kmlGroundOverlay.getGroundOverlayOptions().image(groundOverlayBitmap));
                if (!containerVisibility) {
                    mapGroundOverlay.setVisible(false);
                }
                groundOverlays.put(kmlGroundOverlay, mapGroundOverlay);
            }
        }
    }

    /* access modifiers changed from: private */
    public void addGroundOverlayInContainerGroups(String groundOverlayUrl, Iterable<KmlContainer> kmlContainers, boolean containerVisibility) {
        for (KmlContainer container : kmlContainers) {
            boolean isContainerVisible = getContainerVisibility(container, containerVisibility);
            addGroundOverlayToMap(groundOverlayUrl, container.getGroundOverlayHashMap(), isContainerVisible);
            if (container.hasContainers()) {
                addGroundOverlayInContainerGroups(groundOverlayUrl, container.getContainers(), isContainerVisible);
            }
        }
    }
}
