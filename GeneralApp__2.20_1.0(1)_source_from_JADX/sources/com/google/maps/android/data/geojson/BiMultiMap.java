package com.google.maps.android.data.geojson;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BiMultiMap<K> extends HashMap<K, Object> {
    private final Map<Object, K> mValuesToKeys = new HashMap();

    public void putAll(Map<? extends K, ?> map) {
        for (Entry<? extends K, ?> entry : map.entrySet()) {
            put((K) entry.getKey(), entry.getValue());
        }
    }

    public Object put(K key, Object value) {
        this.mValuesToKeys.put(value, key);
        return super.put(key, value);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Collection, code=java.util.Collection<java.lang.Object>, for r5v0, types: [java.util.Collection<java.lang.Object>, java.util.Collection, java.lang.Object] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object put(K r4, java.util.Collection<java.lang.Object> r5) {
        /*
            r3 = this;
            java.util.Iterator r0 = r5.iterator()
        L_0x0004:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0014
            java.lang.Object r1 = r0.next()
            java.util.Map<java.lang.Object, K> r2 = r3.mValuesToKeys
            r2.put(r1, r4)
            goto L_0x0004
        L_0x0014:
            java.lang.Object r0 = super.put(r4, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.geojson.BiMultiMap.put(java.lang.Object, java.util.Collection):java.lang.Object");
    }

    public Object remove(Object key) {
        Object value = super.remove(key);
        if (value instanceof Collection) {
            for (Object valueItem : (Collection) value) {
                this.mValuesToKeys.remove(valueItem);
            }
        } else {
            this.mValuesToKeys.remove(value);
        }
        return value;
    }

    public void clear() {
        super.clear();
        this.mValuesToKeys.clear();
    }

    public BiMultiMap<K> clone() {
        BiMultiMap<K> cloned = new BiMultiMap<>();
        cloned.putAll((Map) super.clone());
        return cloned;
    }

    public K getKey(Object value) {
        return this.mValuesToKeys.get(value);
    }
}
