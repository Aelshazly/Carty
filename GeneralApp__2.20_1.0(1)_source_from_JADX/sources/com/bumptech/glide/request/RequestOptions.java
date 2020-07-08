package com.bumptech.glide.request;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;

public class RequestOptions extends BaseRequestOptions<RequestOptions> {
    private static RequestOptions centerCropOptions;
    private static RequestOptions centerInsideOptions;
    private static RequestOptions circleCropOptions;
    private static RequestOptions fitCenterOptions;
    private static RequestOptions noAnimationOptions;
    private static RequestOptions noTransformOptions;
    private static RequestOptions skipMemoryCacheFalseOptions;
    private static RequestOptions skipMemoryCacheTrueOptions;

    public static RequestOptions sizeMultiplierOf(float sizeMultiplier) {
        return (RequestOptions) new RequestOptions().sizeMultiplier(sizeMultiplier);
    }

    public static RequestOptions diskCacheStrategyOf(DiskCacheStrategy diskCacheStrategy) {
        return (RequestOptions) new RequestOptions().diskCacheStrategy(diskCacheStrategy);
    }

    public static RequestOptions priorityOf(Priority priority) {
        return (RequestOptions) new RequestOptions().priority(priority);
    }

    public static RequestOptions placeholderOf(Drawable placeholder) {
        return (RequestOptions) new RequestOptions().placeholder(placeholder);
    }

    public static RequestOptions placeholderOf(int placeholderId) {
        return (RequestOptions) new RequestOptions().placeholder(placeholderId);
    }

    public static RequestOptions errorOf(Drawable errorDrawable) {
        return (RequestOptions) new RequestOptions().error(errorDrawable);
    }

    public static RequestOptions errorOf(int errorId) {
        return (RequestOptions) new RequestOptions().error(errorId);
    }

    public static RequestOptions skipMemoryCacheOf(boolean skipMemoryCache) {
        if (skipMemoryCache) {
            if (skipMemoryCacheTrueOptions == null) {
                skipMemoryCacheTrueOptions = (RequestOptions) ((RequestOptions) new RequestOptions().skipMemoryCache(true)).autoClone();
            }
            return skipMemoryCacheTrueOptions;
        }
        if (skipMemoryCacheFalseOptions == null) {
            skipMemoryCacheFalseOptions = (RequestOptions) ((RequestOptions) new RequestOptions().skipMemoryCache(false)).autoClone();
        }
        return skipMemoryCacheFalseOptions;
    }

    public static RequestOptions overrideOf(int width, int height) {
        return (RequestOptions) new RequestOptions().override(width, height);
    }

    public static RequestOptions overrideOf(int size) {
        return overrideOf(size, size);
    }

    public static RequestOptions signatureOf(Key signature) {
        return (RequestOptions) new RequestOptions().signature(signature);
    }

    public static RequestOptions fitCenterTransform() {
        if (fitCenterOptions == null) {
            fitCenterOptions = (RequestOptions) ((RequestOptions) new RequestOptions().fitCenter()).autoClone();
        }
        return fitCenterOptions;
    }

    public static RequestOptions centerInsideTransform() {
        if (centerInsideOptions == null) {
            centerInsideOptions = (RequestOptions) ((RequestOptions) new RequestOptions().centerInside()).autoClone();
        }
        return centerInsideOptions;
    }

    public static RequestOptions centerCropTransform() {
        if (centerCropOptions == null) {
            centerCropOptions = (RequestOptions) ((RequestOptions) new RequestOptions().centerCrop()).autoClone();
        }
        return centerCropOptions;
    }

    public static RequestOptions circleCropTransform() {
        if (circleCropOptions == null) {
            circleCropOptions = (RequestOptions) ((RequestOptions) new RequestOptions().circleCrop()).autoClone();
        }
        return circleCropOptions;
    }

    public static RequestOptions bitmapTransform(Transformation<Bitmap> transformation) {
        return (RequestOptions) new RequestOptions().transform(transformation);
    }

    public static RequestOptions noTransformation() {
        if (noTransformOptions == null) {
            noTransformOptions = (RequestOptions) ((RequestOptions) new RequestOptions().dontTransform()).autoClone();
        }
        return noTransformOptions;
    }

    public static <T> RequestOptions option(Option<T> option, T value) {
        return (RequestOptions) new RequestOptions().set(option, value);
    }

    public static RequestOptions decodeTypeOf(Class<?> resourceClass) {
        return (RequestOptions) new RequestOptions().decode(resourceClass);
    }

    public static RequestOptions formatOf(DecodeFormat format) {
        return (RequestOptions) new RequestOptions().format(format);
    }

    public static RequestOptions frameOf(long frameTimeMicros) {
        return (RequestOptions) new RequestOptions().frame(frameTimeMicros);
    }

    public static RequestOptions downsampleOf(DownsampleStrategy strategy) {
        return (RequestOptions) new RequestOptions().downsample(strategy);
    }

    public static RequestOptions timeoutOf(int timeout) {
        return (RequestOptions) new RequestOptions().timeout(timeout);
    }

    public static RequestOptions encodeQualityOf(int quality) {
        return (RequestOptions) new RequestOptions().encodeQuality(quality);
    }

    public static RequestOptions encodeFormatOf(CompressFormat format) {
        return (RequestOptions) new RequestOptions().encodeFormat(format);
    }

    public static RequestOptions noAnimation() {
        if (noAnimationOptions == null) {
            noAnimationOptions = (RequestOptions) ((RequestOptions) new RequestOptions().dontAnimate()).autoClone();
        }
        return noAnimationOptions;
    }
}
