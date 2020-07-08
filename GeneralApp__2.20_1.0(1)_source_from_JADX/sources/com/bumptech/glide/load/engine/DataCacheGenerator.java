package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher.DataCallback;
import com.bumptech.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoader.LoadData;
import java.io.File;
import java.util.List;

class DataCacheGenerator implements DataFetcherGenerator, DataCallback<Object> {
    private File cacheFile;
    private final List<Key> cacheKeys;

    /* renamed from: cb */
    private final FetcherReadyCallback f48cb;
    private final DecodeHelper<?> helper;
    private volatile LoadData<?> loadData;
    private int modelLoaderIndex;
    private List<ModelLoader<File, ?>> modelLoaders;
    private int sourceIdIndex;
    private Key sourceKey;

    DataCacheGenerator(DecodeHelper<?> helper2, FetcherReadyCallback cb) {
        this(helper2.getCacheKeys(), helper2, cb);
    }

    DataCacheGenerator(List<Key> cacheKeys2, DecodeHelper<?> helper2, FetcherReadyCallback cb) {
        this.sourceIdIndex = -1;
        this.cacheKeys = cacheKeys2;
        this.helper = helper2;
        this.f48cb = cb;
    }

    public boolean startNext() {
        while (true) {
            if (this.modelLoaders == null || !hasNextModelLoader()) {
                this.sourceIdIndex++;
                if (this.sourceIdIndex >= this.cacheKeys.size()) {
                    return false;
                }
                Key sourceId = (Key) this.cacheKeys.get(this.sourceIdIndex);
                this.cacheFile = this.helper.getDiskCache().get(new DataCacheKey(sourceId, this.helper.getSignature()));
                File file = this.cacheFile;
                if (file != null) {
                    this.sourceKey = sourceId;
                    this.modelLoaders = this.helper.getModelLoaders(file);
                    this.modelLoaderIndex = 0;
                }
            } else {
                this.loadData = null;
                boolean started = false;
                while (!started && hasNextModelLoader()) {
                    List<ModelLoader<File, ?>> list = this.modelLoaders;
                    int i = this.modelLoaderIndex;
                    this.modelLoaderIndex = i + 1;
                    this.loadData = ((ModelLoader) list.get(i)).buildLoadData(this.cacheFile, this.helper.getWidth(), this.helper.getHeight(), this.helper.getOptions());
                    if (this.loadData != null && this.helper.hasLoadPath(this.loadData.fetcher.getDataClass())) {
                        started = true;
                        this.loadData.fetcher.loadData(this.helper.getPriority(), this);
                    }
                }
                return started;
            }
        }
    }

    private boolean hasNextModelLoader() {
        return this.modelLoaderIndex < this.modelLoaders.size();
    }

    public void cancel() {
        LoadData<?> local = this.loadData;
        if (local != null) {
            local.fetcher.cancel();
        }
    }

    public void onDataReady(Object data) {
        this.f48cb.onDataFetcherReady(this.sourceKey, data, this.loadData.fetcher, DataSource.DATA_DISK_CACHE, this.sourceKey);
    }

    public void onLoadFailed(Exception e) {
        this.f48cb.onDataFetcherFailed(this.sourceKey, e, this.loadData.fetcher, DataSource.DATA_DISK_CACHE);
    }
}
