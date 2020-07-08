package com.bumptech.glide.load.resource.drawable;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.util.List;

public class ResourceDrawableDecoder implements ResourceDecoder<Uri, Drawable> {
    private static final String ANDROID_PACKAGE_NAME = "android";
    private static final int ID_PATH_SEGMENTS = 1;
    private static final int MISSING_RESOURCE_ID = 0;
    private static final int NAME_PATH_SEGMENT_INDEX = 1;
    private static final int NAME_URI_PATH_SEGMENTS = 2;
    private static final int RESOURCE_ID_SEGMENT_INDEX = 0;
    private static final int TYPE_PATH_SEGMENT_INDEX = 0;
    private final Context context;

    public ResourceDrawableDecoder(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public boolean handles(Uri source, Options options) {
        return source.getScheme().equals("android.resource");
    }

    public Resource<Drawable> decode(Uri source, int width, int height, Options options) {
        Context targetContext = findContextForPackage(source, source.getAuthority());
        return NonOwnedDrawableResource.newInstance(DrawableDecoderCompat.getDrawable(this.context, targetContext, findResourceIdFromUri(targetContext, source)));
    }

    private Context findContextForPackage(Uri source, String packageName) {
        if (packageName.equals(this.context.getPackageName())) {
            return this.context;
        }
        try {
            return this.context.createPackageContext(packageName, 0);
        } catch (NameNotFoundException e) {
            if (packageName.contains(this.context.getPackageName())) {
                return this.context;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to obtain context or unrecognized Uri format for: ");
            sb.append(source);
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    private int findResourceIdFromUri(Context context2, Uri source) {
        List<String> segments = source.getPathSegments();
        if (segments.size() == 2) {
            return findResourceIdFromTypeAndNameResourceUri(context2, source);
        }
        if (segments.size() == 1) {
            return findResourceIdFromResourceIdUri(source);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unrecognized Uri format: ");
        sb.append(source);
        throw new IllegalArgumentException(sb.toString());
    }

    private int findResourceIdFromTypeAndNameResourceUri(Context context2, Uri source) {
        List<String> segments = source.getPathSegments();
        String typeName = (String) segments.get(0);
        String resourceName = (String) segments.get(1);
        int result = context2.getResources().getIdentifier(resourceName, typeName, source.getAuthority());
        if (result == 0) {
            result = Resources.getSystem().getIdentifier(resourceName, typeName, "android");
        }
        if (result != 0) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Failed to find resource id for: ");
        sb.append(source);
        throw new IllegalArgumentException(sb.toString());
    }

    private int findResourceIdFromResourceIdUri(Uri source) {
        try {
            return Integer.parseInt((String) source.getPathSegments().get(0));
        } catch (NumberFormatException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unrecognized Uri format: ");
            sb.append(source);
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }
}
