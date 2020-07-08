package com.bumptech.glide;

import androidx.core.util.Pools.Pool;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.data.DataRewinder.Factory;
import com.bumptech.glide.load.data.DataRewinderRegistry;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.load.engine.LoadPath;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.ModelLoaderRegistry;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.load.resource.transcode.TranscoderRegistry;
import com.bumptech.glide.provider.EncoderRegistry;
import com.bumptech.glide.provider.ImageHeaderParserRegistry;
import com.bumptech.glide.provider.LoadPathCache;
import com.bumptech.glide.provider.ModelToResourceClassCache;
import com.bumptech.glide.provider.ResourceDecoderRegistry;
import com.bumptech.glide.provider.ResourceEncoderRegistry;
import com.bumptech.glide.util.pool.FactoryPools;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Registry {
    private static final String BUCKET_APPEND_ALL = "legacy_append";
    public static final String BUCKET_BITMAP = "Bitmap";
    public static final String BUCKET_BITMAP_DRAWABLE = "BitmapDrawable";
    public static final String BUCKET_GIF = "Gif";
    private static final String BUCKET_PREPEND_ALL = "legacy_prepend_all";
    private final DataRewinderRegistry dataRewinderRegistry = new DataRewinderRegistry();
    private final ResourceDecoderRegistry decoderRegistry = new ResourceDecoderRegistry();
    private final EncoderRegistry encoderRegistry = new EncoderRegistry();
    private final ImageHeaderParserRegistry imageHeaderParserRegistry = new ImageHeaderParserRegistry();
    private final LoadPathCache loadPathCache = new LoadPathCache();
    private final ModelLoaderRegistry modelLoaderRegistry = new ModelLoaderRegistry(this.throwableListPool);
    private final ModelToResourceClassCache modelToResourceClassCache = new ModelToResourceClassCache();
    private final ResourceEncoderRegistry resourceEncoderRegistry = new ResourceEncoderRegistry();
    private final Pool<List<Throwable>> throwableListPool = FactoryPools.threadSafeList();
    private final TranscoderRegistry transcoderRegistry = new TranscoderRegistry();

    public static class MissingComponentException extends RuntimeException {
        public MissingComponentException(String message) {
            super(message);
        }
    }

    public static final class NoImageHeaderParserException extends MissingComponentException {
        public NoImageHeaderParserException() {
            super("Failed to find image header parser.");
        }
    }

    public static class NoModelLoaderAvailableException extends MissingComponentException {
        public NoModelLoaderAvailableException(Object model) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to find any ModelLoaders for model: ");
            sb.append(model);
            super(sb.toString());
        }

        public NoModelLoaderAvailableException(Class<?> modelClass, Class<?> dataClass) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to find any ModelLoaders for model: ");
            sb.append(modelClass);
            sb.append(" and data: ");
            sb.append(dataClass);
            super(sb.toString());
        }
    }

    public static class NoResultEncoderAvailableException extends MissingComponentException {
        public NoResultEncoderAvailableException(Class<?> resourceClass) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to find result encoder for resource class: ");
            sb.append(resourceClass);
            sb.append(", you may need to consider registering a new Encoder for the requested type or DiskCacheStrategy.DATA/DiskCacheStrategy.NONE if caching your transformed resource is unnecessary.");
            super(sb.toString());
        }
    }

    public static class NoSourceEncoderAvailableException extends MissingComponentException {
        public NoSourceEncoderAvailableException(Class<?> dataClass) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to find source encoder for data class: ");
            sb.append(dataClass);
            super(sb.toString());
        }
    }

    public Registry() {
        setResourceDecoderBucketPriorityList(Arrays.asList(new String[]{BUCKET_GIF, BUCKET_BITMAP, BUCKET_BITMAP_DRAWABLE}));
    }

    @Deprecated
    public <Data> Registry register(Class<Data> dataClass, Encoder<Data> encoder) {
        return append(dataClass, encoder);
    }

    public <Data> Registry append(Class<Data> dataClass, Encoder<Data> encoder) {
        this.encoderRegistry.append(dataClass, encoder);
        return this;
    }

    public <Data> Registry prepend(Class<Data> dataClass, Encoder<Data> encoder) {
        this.encoderRegistry.prepend(dataClass, encoder);
        return this;
    }

    public <Data, TResource> Registry append(Class<Data> dataClass, Class<TResource> resourceClass, ResourceDecoder<Data, TResource> decoder) {
        append(BUCKET_APPEND_ALL, dataClass, resourceClass, decoder);
        return this;
    }

    public <Data, TResource> Registry append(String bucket, Class<Data> dataClass, Class<TResource> resourceClass, ResourceDecoder<Data, TResource> decoder) {
        this.decoderRegistry.append(bucket, decoder, dataClass, resourceClass);
        return this;
    }

    public <Data, TResource> Registry prepend(Class<Data> dataClass, Class<TResource> resourceClass, ResourceDecoder<Data, TResource> decoder) {
        prepend(BUCKET_PREPEND_ALL, dataClass, resourceClass, decoder);
        return this;
    }

    public <Data, TResource> Registry prepend(String bucket, Class<Data> dataClass, Class<TResource> resourceClass, ResourceDecoder<Data, TResource> decoder) {
        this.decoderRegistry.prepend(bucket, decoder, dataClass, resourceClass);
        return this;
    }

    public final Registry setResourceDecoderBucketPriorityList(List<String> buckets) {
        List<String> modifiedBuckets = new ArrayList<>(buckets.size());
        modifiedBuckets.addAll(buckets);
        modifiedBuckets.add(0, BUCKET_PREPEND_ALL);
        modifiedBuckets.add(BUCKET_APPEND_ALL);
        this.decoderRegistry.setBucketPriorityList(modifiedBuckets);
        return this;
    }

    @Deprecated
    public <TResource> Registry register(Class<TResource> resourceClass, ResourceEncoder<TResource> encoder) {
        return append(resourceClass, encoder);
    }

    public <TResource> Registry append(Class<TResource> resourceClass, ResourceEncoder<TResource> encoder) {
        this.resourceEncoderRegistry.append(resourceClass, encoder);
        return this;
    }

    public <TResource> Registry prepend(Class<TResource> resourceClass, ResourceEncoder<TResource> encoder) {
        this.resourceEncoderRegistry.prepend(resourceClass, encoder);
        return this;
    }

    public Registry register(Factory<?> factory) {
        this.dataRewinderRegistry.register(factory);
        return this;
    }

    public <TResource, Transcode> Registry register(Class<TResource> resourceClass, Class<Transcode> transcodeClass, ResourceTranscoder<TResource, Transcode> transcoder) {
        this.transcoderRegistry.register(resourceClass, transcodeClass, transcoder);
        return this;
    }

    public Registry register(ImageHeaderParser parser) {
        this.imageHeaderParserRegistry.add(parser);
        return this;
    }

    public <Model, Data> Registry append(Class<Model> modelClass, Class<Data> dataClass, ModelLoaderFactory<Model, Data> factory) {
        this.modelLoaderRegistry.append(modelClass, dataClass, factory);
        return this;
    }

    public <Model, Data> Registry prepend(Class<Model> modelClass, Class<Data> dataClass, ModelLoaderFactory<Model, Data> factory) {
        this.modelLoaderRegistry.prepend(modelClass, dataClass, factory);
        return this;
    }

    public <Model, Data> Registry replace(Class<Model> modelClass, Class<Data> dataClass, ModelLoaderFactory<? extends Model, ? extends Data> factory) {
        this.modelLoaderRegistry.replace(modelClass, dataClass, factory);
        return this;
    }

    public <Data, TResource, Transcode> LoadPath<Data, TResource, Transcode> getLoadPath(Class<Data> dataClass, Class<TResource> resourceClass, Class<Transcode> transcodeClass) {
        LoadPath<Data, TResource, Transcode> result = this.loadPathCache.get(dataClass, resourceClass, transcodeClass);
        if (this.loadPathCache.isEmptyLoadPath(result)) {
            return null;
        }
        if (result == null) {
            List<DecodePath<Data, TResource, Transcode>> decodePaths = getDecodePaths(dataClass, resourceClass, transcodeClass);
            if (decodePaths.isEmpty()) {
                result = null;
            } else {
                LoadPath<Data, TResource, Transcode> loadPath = new LoadPath<>(dataClass, resourceClass, transcodeClass, decodePaths, this.throwableListPool);
                result = loadPath;
            }
            this.loadPathCache.put(dataClass, resourceClass, transcodeClass, result);
        }
        return result;
    }

    private <Data, TResource, Transcode> List<DecodePath<Data, TResource, Transcode>> getDecodePaths(Class<Data> dataClass, Class<TResource> resourceClass, Class<Transcode> transcodeClass) {
        Class<Data> cls = dataClass;
        List<DecodePath<Data, TResource, Transcode>> decodePaths = new ArrayList<>();
        for (Class<TResource> registeredResourceClass : this.decoderRegistry.getResourceClasses(cls, resourceClass)) {
            for (Class<Transcode> registeredTranscodeClass : this.transcoderRegistry.getTranscodeClasses(registeredResourceClass, transcodeClass)) {
                Class cls2 = registeredTranscodeClass;
                DecodePath<Data, TResource, Transcode> path = new DecodePath<>(dataClass, registeredResourceClass, registeredTranscodeClass, this.decoderRegistry.getDecoders(cls, registeredResourceClass), this.transcoderRegistry.get(registeredResourceClass, registeredTranscodeClass), this.throwableListPool);
                decodePaths.add(path);
            }
        }
        Class<Transcode> cls3 = transcodeClass;
        return decodePaths;
    }

    public <Model, TResource, Transcode> List<Class<?>> getRegisteredResourceClasses(Class<Model> modelClass, Class<TResource> resourceClass, Class<Transcode> transcodeClass) {
        List<Class<?>> result = this.modelToResourceClassCache.get(modelClass, resourceClass, transcodeClass);
        if (result == null) {
            result = new ArrayList<>();
            for (Class<?> dataClass : this.modelLoaderRegistry.getDataClasses(modelClass)) {
                for (Class<?> registeredResourceClass : this.decoderRegistry.getResourceClasses(dataClass, resourceClass)) {
                    if (!this.transcoderRegistry.getTranscodeClasses(registeredResourceClass, transcodeClass).isEmpty() && !result.contains(registeredResourceClass)) {
                        result.add(registeredResourceClass);
                    }
                }
            }
            this.modelToResourceClassCache.put(modelClass, resourceClass, transcodeClass, Collections.unmodifiableList(result));
        }
        return result;
    }

    public boolean isResourceEncoderAvailable(Resource<?> resource) {
        return this.resourceEncoderRegistry.get(resource.getResourceClass()) != null;
    }

    public <X> ResourceEncoder<X> getResultEncoder(Resource<X> resource) throws NoResultEncoderAvailableException {
        ResourceEncoder<X> resourceEncoder = this.resourceEncoderRegistry.get(resource.getResourceClass());
        if (resourceEncoder != null) {
            return resourceEncoder;
        }
        throw new NoResultEncoderAvailableException(resource.getResourceClass());
    }

    public <X> Encoder<X> getSourceEncoder(X data) throws NoSourceEncoderAvailableException {
        Encoder<X> encoder = this.encoderRegistry.getEncoder(data.getClass());
        if (encoder != null) {
            return encoder;
        }
        throw new NoSourceEncoderAvailableException(data.getClass());
    }

    public <X> DataRewinder<X> getRewinder(X data) {
        return this.dataRewinderRegistry.build(data);
    }

    public <Model> List<ModelLoader<Model, ?>> getModelLoaders(Model model) {
        List<ModelLoader<Model, ?>> result = this.modelLoaderRegistry.getModelLoaders(model);
        if (!result.isEmpty()) {
            return result;
        }
        throw new NoModelLoaderAvailableException(model);
    }

    public List<ImageHeaderParser> getImageHeaderParsers() {
        List<ImageHeaderParser> result = this.imageHeaderParserRegistry.getParsers();
        if (!result.isEmpty()) {
            return result;
        }
        throw new NoImageHeaderParserException();
    }
}
