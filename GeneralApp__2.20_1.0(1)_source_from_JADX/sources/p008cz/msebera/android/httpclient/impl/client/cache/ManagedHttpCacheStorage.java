package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheUpdateCallback;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.ManagedHttpCacheStorage */
public class ManagedHttpCacheStorage implements HttpCacheStorage, Closeable {
    private final AtomicBoolean active = new AtomicBoolean(true);
    private final CacheMap entries;
    private final ReferenceQueue<HttpCacheEntry> morque = new ReferenceQueue<>();
    private final Set<ResourceReference> resources = new HashSet();

    public ManagedHttpCacheStorage(CacheConfig config) {
        this.entries = new CacheMap(config.getMaxCacheEntries());
    }

    private void ensureValidState() throws IllegalStateException {
        if (!this.active.get()) {
            throw new IllegalStateException("Cache has been shut down");
        }
    }

    private void keepResourceReference(HttpCacheEntry entry) {
        if (entry.getResource() != null) {
            this.resources.add(new ResourceReference(entry, this.morque));
        }
    }

    public void putEntry(String url, HttpCacheEntry entry) throws IOException {
        Args.notNull(url, "URL");
        Args.notNull(entry, "Cache entry");
        ensureValidState();
        synchronized (this) {
            this.entries.put(url, entry);
            keepResourceReference(entry);
        }
    }

    public HttpCacheEntry getEntry(String url) throws IOException {
        HttpCacheEntry httpCacheEntry;
        Args.notNull(url, "URL");
        ensureValidState();
        synchronized (this) {
            httpCacheEntry = (HttpCacheEntry) this.entries.get(url);
        }
        return httpCacheEntry;
    }

    public void removeEntry(String url) throws IOException {
        Args.notNull(url, "URL");
        ensureValidState();
        synchronized (this) {
            this.entries.remove(url);
        }
    }

    public void updateEntry(String url, HttpCacheUpdateCallback callback) throws IOException {
        Args.notNull(url, "URL");
        Args.notNull(callback, "Callback");
        ensureValidState();
        synchronized (this) {
            HttpCacheEntry existing = (HttpCacheEntry) this.entries.get(url);
            HttpCacheEntry updated = callback.update(existing);
            this.entries.put(url, updated);
            if (existing != updated) {
                keepResourceReference(updated);
            }
        }
    }

    public void cleanResources() {
        if (this.active.get()) {
            while (true) {
                ResourceReference resourceReference = (ResourceReference) this.morque.poll();
                ResourceReference ref = resourceReference;
                if (resourceReference != null) {
                    synchronized (this) {
                        this.resources.remove(ref);
                    }
                    ref.getResource().dispose();
                } else {
                    return;
                }
            }
            while (true) {
            }
        }
    }

    public void shutdown() {
        if (this.active.compareAndSet(true, false)) {
            synchronized (this) {
                this.entries.clear();
                for (ResourceReference ref : this.resources) {
                    ref.getResource().dispose();
                }
                this.resources.clear();
                while (this.morque.poll() != null) {
                }
            }
        }
    }

    public void close() {
        shutdown();
    }
}
