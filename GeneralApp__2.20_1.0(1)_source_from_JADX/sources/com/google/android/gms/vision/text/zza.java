package com.google.android.gms.vision.text;

import java.util.Comparator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
final class zza implements Comparator<Entry<String, Integer>> {
    zza(TextBlock textBlock) {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return ((Integer) ((Entry) obj).getValue()).compareTo((Integer) ((Entry) obj2).getValue());
    }
}
