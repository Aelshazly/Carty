package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;
import java.util.Map;

class EngineKey implements Key {
    private int hashCode;
    private final int height;
    private final Object model;
    private final Options options;
    private final Class<?> resourceClass;
    private final Key signature;
    private final Class<?> transcodeClass;
    private final Map<Class<?>, Transformation<?>> transformations;
    private final int width;

    EngineKey(Object model2, Key signature2, int width2, int height2, Map<Class<?>, Transformation<?>> transformations2, Class<?> resourceClass2, Class<?> transcodeClass2, Options options2) {
        this.model = Preconditions.checkNotNull(model2);
        this.signature = (Key) Preconditions.checkNotNull(signature2, "Signature must not be null");
        this.width = width2;
        this.height = height2;
        this.transformations = (Map) Preconditions.checkNotNull(transformations2);
        this.resourceClass = (Class) Preconditions.checkNotNull(resourceClass2, "Resource class must not be null");
        this.transcodeClass = (Class) Preconditions.checkNotNull(transcodeClass2, "Transcode class must not be null");
        this.options = (Options) Preconditions.checkNotNull(options2);
    }

    public boolean equals(Object o) {
        boolean z = false;
        if (!(o instanceof EngineKey)) {
            return false;
        }
        EngineKey other = (EngineKey) o;
        if (this.model.equals(other.model) && this.signature.equals(other.signature) && this.height == other.height && this.width == other.width && this.transformations.equals(other.transformations) && this.resourceClass.equals(other.resourceClass) && this.transcodeClass.equals(other.transcodeClass) && this.options.equals(other.options)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = this.model.hashCode();
            this.hashCode = (this.hashCode * 31) + this.signature.hashCode();
            this.hashCode = (this.hashCode * 31) + this.width;
            this.hashCode = (this.hashCode * 31) + this.height;
            this.hashCode = (this.hashCode * 31) + this.transformations.hashCode();
            this.hashCode = (this.hashCode * 31) + this.resourceClass.hashCode();
            this.hashCode = (this.hashCode * 31) + this.transcodeClass.hashCode();
            this.hashCode = (this.hashCode * 31) + this.options.hashCode();
        }
        return this.hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EngineKey{model=");
        sb.append(this.model);
        sb.append(", width=");
        sb.append(this.width);
        sb.append(", height=");
        sb.append(this.height);
        sb.append(", resourceClass=");
        sb.append(this.resourceClass);
        sb.append(", transcodeClass=");
        sb.append(this.transcodeClass);
        sb.append(", signature=");
        sb.append(this.signature);
        sb.append(", hashCode=");
        sb.append(this.hashCode);
        sb.append(", transformations=");
        sb.append(this.transformations);
        sb.append(", options=");
        sb.append(this.options);
        sb.append('}');
        return sb.toString();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }
}
