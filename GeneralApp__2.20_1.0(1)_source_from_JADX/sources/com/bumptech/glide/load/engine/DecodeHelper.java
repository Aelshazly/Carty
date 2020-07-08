package com.bumptech.glide.load.engine;

import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry.NoModelLoaderAvailableException;
import com.bumptech.glide.Registry.NoSourceEncoderAvailableException;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoader.LoadData;
import com.bumptech.glide.load.resource.UnitTransformation;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class DecodeHelper<Transcode> {
    private final List<Key> cacheKeys = new ArrayList();
    private DiskCacheProvider diskCacheProvider;
    private DiskCacheStrategy diskCacheStrategy;
    private GlideContext glideContext;
    private int height;
    private boolean isCacheKeysSet;
    private boolean isLoadDataSet;
    private boolean isScaleOnlyOrNoTransform;
    private boolean isTransformationRequired;
    private final List<LoadData<?>> loadData = new ArrayList();
    private Object model;
    private Options options;
    private Priority priority;
    private Class<?> resourceClass;
    private Key signature;
    private Class<Transcode> transcodeClass;
    private Map<Class<?>, Transformation<?>> transformations;
    private int width;

    DecodeHelper() {
    }

    /* access modifiers changed from: 0000 */
    public <R> void init(GlideContext glideContext2, Object model2, Key signature2, int width2, int height2, DiskCacheStrategy diskCacheStrategy2, Class<?> resourceClass2, Class<R> transcodeClass2, Priority priority2, Options options2, Map<Class<?>, Transformation<?>> transformations2, boolean isTransformationRequired2, boolean isScaleOnlyOrNoTransform2, DiskCacheProvider diskCacheProvider2) {
        this.glideContext = glideContext2;
        this.model = model2;
        this.signature = signature2;
        this.width = width2;
        this.height = height2;
        this.diskCacheStrategy = diskCacheStrategy2;
        this.resourceClass = resourceClass2;
        this.diskCacheProvider = diskCacheProvider2;
        this.transcodeClass = transcodeClass2;
        this.priority = priority2;
        this.options = options2;
        this.transformations = transformations2;
        this.isTransformationRequired = isTransformationRequired2;
        this.isScaleOnlyOrNoTransform = isScaleOnlyOrNoTransform2;
    }

    /* access modifiers changed from: 0000 */
    public void clear() {
        this.glideContext = null;
        this.model = null;
        this.signature = null;
        this.resourceClass = null;
        this.transcodeClass = null;
        this.options = null;
        this.priority = null;
        this.transformations = null;
        this.diskCacheStrategy = null;
        this.loadData.clear();
        this.isLoadDataSet = false;
        this.cacheKeys.clear();
        this.isCacheKeysSet = false;
    }

    /* access modifiers changed from: 0000 */
    public DiskCache getDiskCache() {
        return this.diskCacheProvider.getDiskCache();
    }

    /* access modifiers changed from: 0000 */
    public DiskCacheStrategy getDiskCacheStrategy() {
        return this.diskCacheStrategy;
    }

    /* access modifiers changed from: 0000 */
    public Priority getPriority() {
        return this.priority;
    }

    /* access modifiers changed from: 0000 */
    public Options getOptions() {
        return this.options;
    }

    /* access modifiers changed from: 0000 */
    public Key getSignature() {
        return this.signature;
    }

    /* access modifiers changed from: 0000 */
    public int getWidth() {
        return this.width;
    }

    /* access modifiers changed from: 0000 */
    public int getHeight() {
        return this.height;
    }

    /* access modifiers changed from: 0000 */
    public ArrayPool getArrayPool() {
        return this.glideContext.getArrayPool();
    }

    /* access modifiers changed from: 0000 */
    public Class<?> getTranscodeClass() {
        return this.transcodeClass;
    }

    /* access modifiers changed from: 0000 */
    public Class<?> getModelClass() {
        return this.model.getClass();
    }

    /* access modifiers changed from: 0000 */
    public List<Class<?>> getRegisteredResourceClasses() {
        return this.glideContext.getRegistry().getRegisteredResourceClasses(this.model.getClass(), this.resourceClass, this.transcodeClass);
    }

    /* access modifiers changed from: 0000 */
    public boolean hasLoadPath(Class<?> dataClass) {
        return getLoadPath(dataClass) != null;
    }

    /* access modifiers changed from: 0000 */
    public <Data> LoadPath<Data, ?, Transcode> getLoadPath(Class<Data> dataClass) {
        return this.glideContext.getRegistry().getLoadPath(dataClass, this.resourceClass, this.transcodeClass);
    }

    /* access modifiers changed from: 0000 */
    public boolean isScaleOnlyOrNoTransform() {
        return this.isScaleOnlyOrNoTransform;
    }

    /* access modifiers changed from: 0000 */
    public <Z> Transformation<Z> getTransformation(Class<Z> resourceClass2) {
        Transformation<Z> result = (Transformation) this.transformations.get(resourceClass2);
        if (result == null) {
            Iterator it = this.transformations.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Entry<Class<?>, Transformation<?>> entry = (Entry) it.next();
                if (((Class) entry.getKey()).isAssignableFrom(resourceClass2)) {
                    result = (Transformation) entry.getValue();
                    break;
                }
            }
        }
        if (result != null) {
            return result;
        }
        if (!this.transformations.isEmpty() || !this.isTransformationRequired) {
            return UnitTransformation.get();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Missing transformation for ");
        sb.append(resourceClass2);
        sb.append(". If you wish to ignore unknown resource types, use the optional transformation methods.");
        throw new IllegalArgumentException(sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public boolean isResourceEncoderAvailable(Resource<?> resource) {
        return this.glideContext.getRegistry().isResourceEncoderAvailable(resource);
    }

    /* access modifiers changed from: 0000 */
    public <Z> ResourceEncoder<Z> getResultEncoder(Resource<Z> resource) {
        return this.glideContext.getRegistry().getResultEncoder(resource);
    }

    /* access modifiers changed from: 0000 */
    public List<ModelLoader<File, ?>> getModelLoaders(File file) throws NoModelLoaderAvailableException {
        return this.glideContext.getRegistry().getModelLoaders(file);
    }

    /* access modifiers changed from: 0000 */
    public boolean isSourceKey(Key key) {
        List<LoadData<?>> loadData2 = getLoadData();
        int size = loadData2.size();
        for (int i = 0; i < size; i++) {
            if (((LoadData) loadData2.get(i)).sourceKey.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public List<LoadData<?>> getLoadData() {
        if (!this.isLoadDataSet) {
            this.isLoadDataSet = true;
            this.loadData.clear();
            List<ModelLoader<Object, ?>> modelLoaders = this.glideContext.getRegistry().getModelLoaders(this.model);
            int size = modelLoaders.size();
            for (int i = 0; i < size; i++) {
                LoadData<?> current = ((ModelLoader) modelLoaders.get(i)).buildLoadData(this.model, this.width, this.height, this.options);
                if (current != null) {
                    this.loadData.add(current);
                }
            }
        }
        return this.loadData;
    }

    /* access modifiers changed from: 0000 */
    public List<Key> getCacheKeys() {
        if (!this.isCacheKeysSet) {
            this.isCacheKeysSet = true;
            this.cacheKeys.clear();
            List<LoadData<?>> loadData2 = getLoadData();
            int size = loadData2.size();
            for (int i = 0; i < size; i++) {
                LoadData<?> data = (LoadData) loadData2.get(i);
                if (!this.cacheKeys.contains(data.sourceKey)) {
                    this.cacheKeys.add(data.sourceKey);
                }
                for (int j = 0; j < data.alternateKeys.size(); j++) {
                    if (!this.cacheKeys.contains(data.alternateKeys.get(j))) {
                        this.cacheKeys.add(data.alternateKeys.get(j));
                    }
                }
            }
        }
        return this.cacheKeys;
    }

    /* access modifiers changed from: 0000 */
    public <X> Encoder<X> getSourceEncoder(X data) throws NoSourceEncoderAvailableException {
        return this.glideContext.getRegistry().getSourceEncoder(data);
    }
}
