package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public final class RoundedCorners extends BitmapTransformation {

    /* renamed from: ID */
    private static final String f61ID = "com.bumptech.glide.load.resource.bitmap.RoundedCorners";
    private static final byte[] ID_BYTES = f61ID.getBytes(CHARSET);
    private final int roundingRadius;

    public RoundedCorners(int roundingRadius2) {
        Preconditions.checkArgument(roundingRadius2 > 0, "roundingRadius must be greater than 0.");
        this.roundingRadius = roundingRadius2;
    }

    /* access modifiers changed from: protected */
    public Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.roundedCorners(pool, toTransform, this.roundingRadius);
    }

    public boolean equals(Object o) {
        boolean z = false;
        if (!(o instanceof RoundedCorners)) {
            return false;
        }
        if (this.roundingRadius == ((RoundedCorners) o).roundingRadius) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Util.hashCode(f61ID.hashCode(), Util.hashCode(this.roundingRadius));
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.roundingRadius).array());
    }
}
