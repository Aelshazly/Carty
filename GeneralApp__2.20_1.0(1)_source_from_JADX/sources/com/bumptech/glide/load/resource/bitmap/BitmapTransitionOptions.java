package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.BitmapTransitionFactory;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory.Builder;
import com.bumptech.glide.request.transition.TransitionFactory;

public final class BitmapTransitionOptions extends TransitionOptions<BitmapTransitionOptions, Bitmap> {
    public static BitmapTransitionOptions withCrossFade() {
        return new BitmapTransitionOptions().crossFade();
    }

    public static BitmapTransitionOptions withCrossFade(int duration) {
        return new BitmapTransitionOptions().crossFade(duration);
    }

    public static BitmapTransitionOptions withCrossFade(DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return new BitmapTransitionOptions().crossFade(drawableCrossFadeFactory);
    }

    public static BitmapTransitionOptions withCrossFade(Builder builder) {
        return new BitmapTransitionOptions().crossFade(builder);
    }

    public static BitmapTransitionOptions withWrapped(TransitionFactory<Drawable> drawableCrossFadeFactory) {
        return new BitmapTransitionOptions().transitionUsing(drawableCrossFadeFactory);
    }

    public static BitmapTransitionOptions with(TransitionFactory<Bitmap> transitionFactory) {
        return (BitmapTransitionOptions) new BitmapTransitionOptions().transition(transitionFactory);
    }

    public BitmapTransitionOptions crossFade() {
        return crossFade(new Builder());
    }

    public BitmapTransitionOptions crossFade(int duration) {
        return crossFade(new Builder(duration));
    }

    public BitmapTransitionOptions crossFade(DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return transitionUsing(drawableCrossFadeFactory);
    }

    public BitmapTransitionOptions transitionUsing(TransitionFactory<Drawable> drawableCrossFadeFactory) {
        return (BitmapTransitionOptions) transition((TransitionFactory<? super TranscodeType>) new BitmapTransitionFactory<Object>(drawableCrossFadeFactory));
    }

    public BitmapTransitionOptions crossFade(Builder builder) {
        return transitionUsing(builder.build());
    }
}