package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzhi<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zzyg;

    public zzhi(Iterator<Entry<K, Object>> it) {
        this.zzyg = it;
    }

    public final boolean hasNext() {
        return this.zzyg.hasNext();
    }

    public final void remove() {
        this.zzyg.remove();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.zzyg.next();
        if (entry.getValue() instanceof zzhd) {
            return new zzhf(entry);
        }
        return entry;
    }
}
