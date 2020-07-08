package com.bumptech.glide.load.data;

import com.bumptech.glide.load.data.DataRewinder.Factory;
import com.bumptech.glide.util.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataRewinderRegistry {
    private static final Factory<?> DEFAULT_FACTORY = new Factory<Object>() {
        public DataRewinder<Object> build(Object data) {
            return new DefaultRewinder(data);
        }

        public Class<Object> getDataClass() {
            throw new UnsupportedOperationException("Not implemented");
        }
    };
    private final Map<Class<?>, Factory<?>> rewinders = new HashMap();

    private static final class DefaultRewinder implements DataRewinder<Object> {
        private final Object data;

        DefaultRewinder(Object data2) {
            this.data = data2;
        }

        public Object rewindAndGet() {
            return this.data;
        }

        public void cleanup() {
        }
    }

    public synchronized void register(Factory<?> factory) {
        this.rewinders.put(factory.getDataClass(), factory);
    }

    public synchronized <T> DataRewinder<T> build(T data) {
        Factory factory;
        Preconditions.checkNotNull(data);
        factory = (Factory) this.rewinders.get(data.getClass());
        if (factory == null) {
            Iterator it = this.rewinders.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Factory<?> registeredFactory = (Factory) it.next();
                if (registeredFactory.getDataClass().isAssignableFrom(data.getClass())) {
                    factory = registeredFactory;
                    break;
                }
            }
        }
        if (factory == null) {
            factory = DEFAULT_FACTORY;
        }
        return factory.build(data);
    }
}
