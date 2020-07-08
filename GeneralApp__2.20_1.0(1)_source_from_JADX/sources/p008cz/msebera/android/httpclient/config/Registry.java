package p008cz.msebera.android.httpclient.config;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: cz.msebera.android.httpclient.config.Registry */
public final class Registry<I> implements Lookup<I> {
    private final Map<String, I> map;

    Registry(Map<String, I> map2) {
        this.map = new ConcurrentHashMap(map2);
    }

    public I lookup(String key) {
        if (key == null) {
            return null;
        }
        return this.map.get(key.toLowerCase(Locale.ENGLISH));
    }

    public String toString() {
        return this.map.toString();
    }
}
