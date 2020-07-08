package com.google.maps.android.data.kml;

import okhttp3.internal.cache.DiskLruCache;

public class KmlBoolean {
    public static boolean parseBoolean(String text) {
        if (DiskLruCache.VERSION_1.equals(text) || "true".equals(text)) {
            return true;
        }
        return false;
    }
}
