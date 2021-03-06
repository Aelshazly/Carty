package com.bumptech.glide.load;

import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class ImageHeaderParserUtils {
    private static final int MARK_POSITION = 5242880;

    private ImageHeaderParserUtils() {
    }

    /* JADX INFO: finally extract failed */
    public static ImageType getType(List<ImageHeaderParser> parsers, InputStream is, ArrayPool byteArrayPool) throws IOException {
        if (is == null) {
            return ImageType.UNKNOWN;
        }
        if (!is.markSupported()) {
            is = new RecyclableBufferedInputStream(is, byteArrayPool);
        }
        is.mark(MARK_POSITION);
        int i = 0;
        int size = parsers.size();
        while (i < size) {
            try {
                ImageType type = ((ImageHeaderParser) parsers.get(i)).getType(is);
                if (type != ImageType.UNKNOWN) {
                    is.reset();
                    return type;
                }
                is.reset();
                i++;
            } catch (Throwable th) {
                is.reset();
                throw th;
            }
        }
        return ImageType.UNKNOWN;
    }

    public static ImageType getType(List<ImageHeaderParser> parsers, ByteBuffer buffer) throws IOException {
        if (buffer == null) {
            return ImageType.UNKNOWN;
        }
        int size = parsers.size();
        for (int i = 0; i < size; i++) {
            ImageType type = ((ImageHeaderParser) parsers.get(i)).getType(buffer);
            if (type != ImageType.UNKNOWN) {
                return type;
            }
        }
        return ImageType.UNKNOWN;
    }

    /* JADX INFO: finally extract failed */
    public static int getOrientation(List<ImageHeaderParser> parsers, InputStream is, ArrayPool byteArrayPool) throws IOException {
        if (is == null) {
            return -1;
        }
        if (!is.markSupported()) {
            is = new RecyclableBufferedInputStream(is, byteArrayPool);
        }
        is.mark(MARK_POSITION);
        int i = 0;
        int size = parsers.size();
        while (i < size) {
            try {
                int orientation = ((ImageHeaderParser) parsers.get(i)).getOrientation(is, byteArrayPool);
                if (orientation != -1) {
                    is.reset();
                    return orientation;
                }
                is.reset();
                i++;
            } catch (Throwable th) {
                is.reset();
                throw th;
            }
        }
        return -1;
    }
}
