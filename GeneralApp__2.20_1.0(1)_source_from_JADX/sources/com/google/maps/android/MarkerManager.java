package com.google.maps.android;

import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MarkerManager implements OnInfoWindowClickListener, OnMarkerClickListener, OnMarkerDragListener, InfoWindowAdapter {
    /* access modifiers changed from: private */
    public final Map<Marker, Collection> mAllMarkers = new HashMap();
    /* access modifiers changed from: private */
    public final GoogleMap mMap;
    private final Map<String, Collection> mNamedCollections = new HashMap();

    public class Collection {
        /* access modifiers changed from: private */
        public InfoWindowAdapter mInfoWindowAdapter;
        /* access modifiers changed from: private */
        public OnInfoWindowClickListener mInfoWindowClickListener;
        /* access modifiers changed from: private */
        public OnMarkerClickListener mMarkerClickListener;
        /* access modifiers changed from: private */
        public OnMarkerDragListener mMarkerDragListener;
        private final Set<Marker> mMarkers = new HashSet();

        public Collection() {
        }

        public Marker addMarker(MarkerOptions opts) {
            Marker marker = MarkerManager.this.mMap.addMarker(opts);
            this.mMarkers.add(marker);
            MarkerManager.this.mAllMarkers.put(marker, this);
            return marker;
        }

        public boolean remove(Marker marker) {
            if (!this.mMarkers.remove(marker)) {
                return false;
            }
            MarkerManager.this.mAllMarkers.remove(marker);
            marker.remove();
            return true;
        }

        public void clear() {
            for (Marker marker : this.mMarkers) {
                marker.remove();
                MarkerManager.this.mAllMarkers.remove(marker);
            }
            this.mMarkers.clear();
        }

        public java.util.Collection<Marker> getMarkers() {
            return Collections.unmodifiableCollection(this.mMarkers);
        }

        public void setOnInfoWindowClickListener(OnInfoWindowClickListener infoWindowClickListener) {
            this.mInfoWindowClickListener = infoWindowClickListener;
        }

        public void setOnMarkerClickListener(OnMarkerClickListener markerClickListener) {
            this.mMarkerClickListener = markerClickListener;
        }

        public void setOnMarkerDragListener(OnMarkerDragListener markerDragListener) {
            this.mMarkerDragListener = markerDragListener;
        }

        public void setOnInfoWindowAdapter(InfoWindowAdapter infoWindowAdapter) {
            this.mInfoWindowAdapter = infoWindowAdapter;
        }
    }

    public MarkerManager(GoogleMap map) {
        this.mMap = map;
    }

    public Collection newCollection() {
        return new Collection();
    }

    public Collection newCollection(String id) {
        if (this.mNamedCollections.get(id) == null) {
            Collection collection = new Collection();
            this.mNamedCollections.put(id, collection);
            return collection;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("collection id is not unique: ");
        sb.append(id);
        throw new IllegalArgumentException(sb.toString());
    }

    public Collection getCollection(String id) {
        return (Collection) this.mNamedCollections.get(id);
    }

    public View getInfoWindow(Marker marker) {
        Collection collection = (Collection) this.mAllMarkers.get(marker);
        if (collection == null || collection.mInfoWindowAdapter == null) {
            return null;
        }
        return collection.mInfoWindowAdapter.getInfoWindow(marker);
    }

    public View getInfoContents(Marker marker) {
        Collection collection = (Collection) this.mAllMarkers.get(marker);
        if (collection == null || collection.mInfoWindowAdapter == null) {
            return null;
        }
        return collection.mInfoWindowAdapter.getInfoContents(marker);
    }

    public void onInfoWindowClick(Marker marker) {
        Collection collection = (Collection) this.mAllMarkers.get(marker);
        if (collection != null && collection.mInfoWindowClickListener != null) {
            collection.mInfoWindowClickListener.onInfoWindowClick(marker);
        }
    }

    public boolean onMarkerClick(Marker marker) {
        Collection collection = (Collection) this.mAllMarkers.get(marker);
        if (collection == null || collection.mMarkerClickListener == null) {
            return false;
        }
        return collection.mMarkerClickListener.onMarkerClick(marker);
    }

    public void onMarkerDragStart(Marker marker) {
        Collection collection = (Collection) this.mAllMarkers.get(marker);
        if (collection != null && collection.mMarkerDragListener != null) {
            collection.mMarkerDragListener.onMarkerDragStart(marker);
        }
    }

    public void onMarkerDrag(Marker marker) {
        Collection collection = (Collection) this.mAllMarkers.get(marker);
        if (collection != null && collection.mMarkerDragListener != null) {
            collection.mMarkerDragListener.onMarkerDrag(marker);
        }
    }

    public void onMarkerDragEnd(Marker marker) {
        Collection collection = (Collection) this.mAllMarkers.get(marker);
        if (collection != null && collection.mMarkerDragListener != null) {
            collection.mMarkerDragListener.onMarkerDragEnd(marker);
        }
    }

    public boolean remove(Marker marker) {
        Collection collection = (Collection) this.mAllMarkers.get(marker);
        return collection != null && collection.remove(marker);
    }
}
