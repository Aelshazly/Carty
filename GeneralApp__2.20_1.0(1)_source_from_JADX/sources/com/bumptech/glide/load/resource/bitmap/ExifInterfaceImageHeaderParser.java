package com.bumptech.glide.load.resource.bitmap;

import android.media.ExifInterface;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class ExifInterfaceImageHeaderParser implements ImageHeaderParser {
    public ImageType getType(InputStream is) throws IOException {
        return ImageType.UNKNOWN;
    }

    public ImageType getType(ByteBuffer byteBuffer) throws IOException {
        return ImageType.UNKNOWN;
    }

    public int getOrientation(InputStream is, ArrayPool byteArrayPool) throws IOException {
        int result = new ExifInterface(is).getAttributeInt("Orientation", 1);
        if (result == 0) {
            return -1;
        }
        return result;
    }

    public int getOrientation(ByteBuffer byteBuffer, ArrayPool byteArrayPool) throws IOException {
        return getOrientation(ByteBufferUtil.toStream(byteBuffer), byteArrayPool);
    }
}
