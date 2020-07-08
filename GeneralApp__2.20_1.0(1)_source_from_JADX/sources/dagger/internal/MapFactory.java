package dagger.internal;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Provider;

public final class MapFactory<K, V> extends AbstractMapFactory<K, V, V> {
    private static final Provider<Map<Object, Object>> EMPTY = InstanceFactory.create(Collections.emptyMap());

    public static final class Builder<K, V> extends dagger.internal.AbstractMapFactory.Builder<K, V, V> {
        private Builder(int size) {
            super(size);
        }

        public Builder<K, V> put(K key, Provider<V> providerOfValue) {
            super.put(key, providerOfValue);
            return this;
        }

        public Builder<K, V> putAll(Provider<Map<K, V>> mapFactory) {
            super.putAll(mapFactory);
            return this;
        }

        public MapFactory<K, V> build() {
            return new MapFactory<>(this.map);
        }
    }

    public static <K, V> Builder<K, V> builder(int size) {
        return new Builder<>(size);
    }

    public static <K, V> Provider<Map<K, V>> emptyMapProvider() {
        return EMPTY;
    }

    private MapFactory(Map<K, Provider<V>> map) {
        super(map);
    }

    public Map<K, V> get() {
        Map<K, V> result = DaggerCollections.newLinkedHashMapWithExpectedSize(contributingMap().size());
        for (Entry<K, Provider<V>> entry : contributingMap().entrySet()) {
            result.put(entry.getKey(), ((Provider) entry.getValue()).get());
        }
        return Collections.unmodifiableMap(result);
    }
}
