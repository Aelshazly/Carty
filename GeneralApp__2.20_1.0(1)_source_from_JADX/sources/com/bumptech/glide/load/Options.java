package com.bumptech.glide.load;

import androidx.collection.ArrayMap;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import java.security.MessageDigest;

public final class Options implements Key {
    private final ArrayMap<Option<?>, Object> values = new CachedHashCodeArrayMap();

    public void putAll(Options other) {
        this.values.putAll(other.values);
    }

    public <T> Options set(Option<T> option, T value) {
        this.values.put(option, value);
        return this;
    }

    public <T> T get(Option<T> option) {
        return this.values.containsKey(option) ? this.values.get(option) : option.getDefaultValue();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Options)) {
            return false;
        }
        return this.values.equals(((Options) o).values);
    }

    public int hashCode() {
        return this.values.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        for (int i = 0; i < this.values.size(); i++) {
            updateDiskCacheKey((Option) this.values.keyAt(i), this.values.valueAt(i), messageDigest);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Options{values=");
        sb.append(this.values);
        sb.append('}');
        return sb.toString();
    }

    private static <T> void updateDiskCacheKey(Option<T> option, Object value, MessageDigest md) {
        option.update(value, md);
    }
}
