package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class Rotate extends BitmapTransformation {

    /* renamed from: ID */
    private static final String f60ID = "com.bumptech.glide.load.resource.bitmap.Rotate";
    private static final byte[] ID_BYTES = f60ID.getBytes(CHARSET);
    private final int degreesToRotate;

    public Rotate(int degreesToRotate2) {
        this.degreesToRotate = degreesToRotate2;
    }

    /* access modifiers changed from: protected */
    public Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.rotateImage(toTransform, this.degreesToRotate);
    }

    public boolean equals(Object o) {
        boolean z = false;
        if (!(o instanceof Rotate)) {
            return false;
        }
        if (this.degreesToRotate == ((Rotate) o).degreesToRotate) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Util.hashCode(f60ID.hashCode(), Util.hashCode(this.degreesToRotate));
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.degreesToRotate).array());
    }
}
