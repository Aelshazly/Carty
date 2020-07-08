package com.bumptech.glide.load.data;

import android.content.res.AssetManager;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher.DataCallback;
import java.io.IOException;

public abstract class AssetPathFetcher<T> implements DataFetcher<T> {
    private static final String TAG = "AssetPathFetcher";
    private final AssetManager assetManager;
    private final String assetPath;
    private T data;

    /* access modifiers changed from: protected */
    public abstract void close(T t) throws IOException;

    /* access modifiers changed from: protected */
    public abstract T loadResource(AssetManager assetManager2, String str) throws IOException;

    public AssetPathFetcher(AssetManager assetManager2, String assetPath2) {
        this.assetManager = assetManager2;
        this.assetPath = assetPath2;
    }

    public void loadData(Priority priority, DataCallback<? super T> callback) {
        try {
            this.data = loadResource(this.assetManager, this.assetPath);
            callback.onDataReady(this.data);
        } catch (IOException e) {
            String str = TAG;
            if (Log.isLoggable(str, 3)) {
                Log.d(str, "Failed to load data from asset manager", e);
            }
            callback.onLoadFailed(e);
        }
    }

    public void cleanup() {
        T t = this.data;
        if (t != null) {
            try {
                close(t);
            } catch (IOException e) {
            }
        }
    }

    public void cancel() {
    }

    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}
