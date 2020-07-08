package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.3.0 */
final class zzfu<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zza;

    public zzfu(Iterator<Entry<K, Object>> it) {
        this.zza = it;
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final void remove() {
        this.zza.remove();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.zza.next();
        if (entry.getValue() instanceof zzfp) {
            return new zzfr(entry);
        }
        return entry;
    }
}
