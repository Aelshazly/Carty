package com.bumptech.glide.util;

import com.bumptech.glide.ListPreloader.PreloadSizeProvider;

public class FixedPreloadSizeProvider<T> implements PreloadSizeProvider<T> {
    private final int[] size;

    public FixedPreloadSizeProvider(int width, int height) {
        this.size = new int[]{width, height};
    }

    public int[] getPreloadSize(T t, int adapterPosition, int itemPosition) {
        return this.size;
    }
}
