package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import java.security.MessageDigest;

public class DrawableTransformation implements Transformation<Drawable> {
    private final boolean isRequired;
    private final Transformation<Bitmap> wrapped;

    public DrawableTransformation(Transformation<Bitmap> wrapped2, boolean isRequired2) {
        this.wrapped = wrapped2;
        this.isRequired = isRequired2;
    }

    public Transformation<BitmapDrawable> asBitmapDrawable() {
        return this;
    }

    public Resource<Drawable> transform(Context context, Resource<Drawable> resource, int outWidth, int outHeight) {
        Drawable drawable = (Drawable) resource.get();
        Resource<Bitmap> bitmapResourceToTransform = DrawableToBitmapConverter.convert(Glide.get(context).getBitmapPool(), drawable, outWidth, outHeight);
        if (bitmapResourceToTransform != null) {
            Resource<Bitmap> transformedBitmapResource = this.wrapped.transform(context, bitmapResourceToTransform, outWidth, outHeight);
            if (!transformedBitmapResource.equals(bitmapResourceToTransform)) {
                return newDrawableResource(context, transformedBitmapResource);
            }
            transformedBitmapResource.recycle();
            return resource;
        } else if (!this.isRequired) {
            return resource;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to convert ");
            sb.append(drawable);
            sb.append(" to a Bitmap");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private Resource<Drawable> newDrawableResource(Context context, Resource<Bitmap> transformed) {
        return LazyBitmapDrawableResource.obtain(context.getResources(), transformed);
    }

    public boolean equals(Object o) {
        if (!(o instanceof DrawableTransformation)) {
            return false;
        }
        return this.wrapped.equals(((DrawableTransformation) o).wrapped);
    }

    public int hashCode() {
        return this.wrapped.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        this.wrapped.updateDiskCacheKey(messageDigest);
    }
}
