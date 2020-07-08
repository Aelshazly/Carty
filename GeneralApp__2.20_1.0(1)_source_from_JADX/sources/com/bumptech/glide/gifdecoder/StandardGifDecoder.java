package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.UByte;

public class StandardGifDecoder implements GifDecoder {
    private static final int BYTES_PER_INTEGER = 4;
    private static final int COLOR_TRANSPARENT_BLACK = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MASK_INT_LOWEST_BYTE = 255;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    private static final String TAG = StandardGifDecoder.class.getSimpleName();
    private int[] act;
    private Config bitmapConfig;
    private final BitmapProvider bitmapProvider;
    private byte[] block;
    private int downsampledHeight;
    private int downsampledWidth;
    private int framePointer;
    private GifHeader header;
    private Boolean isFirstFrameTransparent;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private final int[] pct;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private int sampleSize;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public StandardGifDecoder(BitmapProvider provider, GifHeader gifHeader, ByteBuffer rawData2) {
        this(provider, gifHeader, rawData2, 1);
    }

    public StandardGifDecoder(BitmapProvider provider, GifHeader gifHeader, ByteBuffer rawData2, int sampleSize2) {
        this(provider);
        setData(gifHeader, rawData2, sampleSize2);
    }

    public StandardGifDecoder(BitmapProvider provider) {
        this.pct = new int[256];
        this.bitmapConfig = Config.ARGB_8888;
        this.bitmapProvider = provider;
        this.header = new GifHeader();
    }

    public int getWidth() {
        return this.header.width;
    }

    public int getHeight() {
        return this.header.height;
    }

    public ByteBuffer getData() {
        return this.rawData;
    }

    public int getStatus() {
        return this.status;
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public int getDelay(int n) {
        if (n < 0 || n >= this.header.frameCount) {
            return -1;
        }
        return ((GifFrame) this.header.frames.get(n)).delay;
    }

    public int getNextDelay() {
        if (this.header.frameCount > 0) {
            int i = this.framePointer;
            if (i >= 0) {
                return getDelay(i);
            }
        }
        return 0;
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Deprecated
    public int getLoopCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        return this.header.loopCount;
    }

    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    public int getTotalIterationCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        if (this.header.loopCount == 0) {
            return 0;
        }
        return this.header.loopCount + 1;
    }

    public int getByteSize() {
        return this.rawData.limit() + this.mainPixels.length + (this.mainScratch.length * 4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e9, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Bitmap getNextFrame() {
        /*
            r8 = this;
            monitor-enter(r8)
            com.bumptech.glide.gifdecoder.GifHeader r0 = r8.header     // Catch:{ all -> 0x00ea }
            int r0 = r0.frameCount     // Catch:{ all -> 0x00ea }
            r1 = 3
            r2 = 1
            if (r0 <= 0) goto L_0x000d
            int r0 = r8.framePointer     // Catch:{ all -> 0x00ea }
            if (r0 >= 0) goto L_0x003b
        L_0x000d:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ea }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ea }
            if (r0 == 0) goto L_0x0039
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ea }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
            r3.<init>()     // Catch:{ all -> 0x00ea }
            java.lang.String r4 = "Unable to decode frame, frameCount="
            r3.append(r4)     // Catch:{ all -> 0x00ea }
            com.bumptech.glide.gifdecoder.GifHeader r4 = r8.header     // Catch:{ all -> 0x00ea }
            int r4 = r4.frameCount     // Catch:{ all -> 0x00ea }
            r3.append(r4)     // Catch:{ all -> 0x00ea }
            java.lang.String r4 = ", framePointer="
            r3.append(r4)     // Catch:{ all -> 0x00ea }
            int r4 = r8.framePointer     // Catch:{ all -> 0x00ea }
            r3.append(r4)     // Catch:{ all -> 0x00ea }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00ea }
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x00ea }
        L_0x0039:
            r8.status = r2     // Catch:{ all -> 0x00ea }
        L_0x003b:
            int r0 = r8.status     // Catch:{ all -> 0x00ea }
            r3 = 0
            if (r0 == r2) goto L_0x00c8
            int r0 = r8.status     // Catch:{ all -> 0x00ea }
            r4 = 2
            if (r0 != r4) goto L_0x0047
            goto L_0x00c8
        L_0x0047:
            r0 = 0
            r8.status = r0     // Catch:{ all -> 0x00ea }
            byte[] r4 = r8.block     // Catch:{ all -> 0x00ea }
            if (r4 != 0) goto L_0x0058
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r4 = r8.bitmapProvider     // Catch:{ all -> 0x00ea }
            r5 = 255(0xff, float:3.57E-43)
            byte[] r4 = r4.obtainByteArray(r5)     // Catch:{ all -> 0x00ea }
            r8.block = r4     // Catch:{ all -> 0x00ea }
        L_0x0058:
            com.bumptech.glide.gifdecoder.GifHeader r4 = r8.header     // Catch:{ all -> 0x00ea }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r4 = r4.frames     // Catch:{ all -> 0x00ea }
            int r5 = r8.framePointer     // Catch:{ all -> 0x00ea }
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x00ea }
            com.bumptech.glide.gifdecoder.GifFrame r4 = (com.bumptech.glide.gifdecoder.GifFrame) r4     // Catch:{ all -> 0x00ea }
            r5 = 0
            int r6 = r8.framePointer     // Catch:{ all -> 0x00ea }
            int r6 = r6 - r2
            if (r6 < 0) goto L_0x0075
            com.bumptech.glide.gifdecoder.GifHeader r7 = r8.header     // Catch:{ all -> 0x00ea }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r7 = r7.frames     // Catch:{ all -> 0x00ea }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ all -> 0x00ea }
            com.bumptech.glide.gifdecoder.GifFrame r7 = (com.bumptech.glide.gifdecoder.GifFrame) r7     // Catch:{ all -> 0x00ea }
            r5 = r7
        L_0x0075:
            int[] r7 = r4.lct     // Catch:{ all -> 0x00ea }
            if (r7 == 0) goto L_0x007c
            int[] r7 = r4.lct     // Catch:{ all -> 0x00ea }
            goto L_0x0080
        L_0x007c:
            com.bumptech.glide.gifdecoder.GifHeader r7 = r8.header     // Catch:{ all -> 0x00ea }
            int[] r7 = r7.gct     // Catch:{ all -> 0x00ea }
        L_0x0080:
            r8.act = r7     // Catch:{ all -> 0x00ea }
            int[] r7 = r8.act     // Catch:{ all -> 0x00ea }
            if (r7 != 0) goto L_0x00aa
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ea }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ea }
            if (r0 == 0) goto L_0x00a6
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ea }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
            r1.<init>()     // Catch:{ all -> 0x00ea }
            java.lang.String r7 = "No valid color table found for frame #"
            r1.append(r7)     // Catch:{ all -> 0x00ea }
            int r7 = r8.framePointer     // Catch:{ all -> 0x00ea }
            r1.append(r7)     // Catch:{ all -> 0x00ea }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ea }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00ea }
        L_0x00a6:
            r8.status = r2     // Catch:{ all -> 0x00ea }
            monitor-exit(r8)
            return r3
        L_0x00aa:
            boolean r1 = r4.transparency     // Catch:{ all -> 0x00ea }
            if (r1 == 0) goto L_0x00c2
            int[] r1 = r8.act     // Catch:{ all -> 0x00ea }
            int[] r2 = r8.pct     // Catch:{ all -> 0x00ea }
            int[] r3 = r8.act     // Catch:{ all -> 0x00ea }
            int r3 = r3.length     // Catch:{ all -> 0x00ea }
            java.lang.System.arraycopy(r1, r0, r2, r0, r3)     // Catch:{ all -> 0x00ea }
            int[] r1 = r8.pct     // Catch:{ all -> 0x00ea }
            r8.act = r1     // Catch:{ all -> 0x00ea }
            int[] r1 = r8.act     // Catch:{ all -> 0x00ea }
            int r2 = r4.transIndex     // Catch:{ all -> 0x00ea }
            r1[r2] = r0     // Catch:{ all -> 0x00ea }
        L_0x00c2:
            android.graphics.Bitmap r0 = r8.setPixels(r4, r5)     // Catch:{ all -> 0x00ea }
            monitor-exit(r8)
            return r0
        L_0x00c8:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ea }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ea }
            if (r0 == 0) goto L_0x00e8
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ea }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
            r1.<init>()     // Catch:{ all -> 0x00ea }
            java.lang.String r2 = "Unable to decode frame, status="
            r1.append(r2)     // Catch:{ all -> 0x00ea }
            int r2 = r8.status     // Catch:{ all -> 0x00ea }
            r1.append(r2)     // Catch:{ all -> 0x00ea }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ea }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00ea }
        L_0x00e8:
            monitor-exit(r8)
            return r3
        L_0x00ea:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.getNextFrame():android.graphics.Bitmap");
    }

    public int read(InputStream is, int contentLength) {
        if (is != null) {
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream(contentLength > 0 ? contentLength + 4096 : 16384);
                byte[] data = new byte[16384];
                while (true) {
                    int read = is.read(data, 0, data.length);
                    int nRead = read;
                    if (read == -1) {
                        break;
                    }
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                read(buffer.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    public void clear() {
        this.header = null;
        byte[] bArr = this.mainPixels;
        if (bArr != null) {
            this.bitmapProvider.release(bArr);
        }
        int[] iArr = this.mainScratch;
        if (iArr != null) {
            this.bitmapProvider.release(iArr);
        }
        Bitmap bitmap = this.previousImage;
        if (bitmap != null) {
            this.bitmapProvider.release(bitmap);
        }
        this.previousImage = null;
        this.rawData = null;
        this.isFirstFrameTransparent = null;
        byte[] bArr2 = this.block;
        if (bArr2 != null) {
            this.bitmapProvider.release(bArr2);
        }
    }

    public synchronized void setData(GifHeader header2, byte[] data) {
        setData(header2, ByteBuffer.wrap(data));
    }

    public synchronized void setData(GifHeader header2, ByteBuffer buffer) {
        setData(header2, buffer, 1);
    }

    public synchronized void setData(GifHeader header2, ByteBuffer buffer, int sampleSize2) {
        if (sampleSize2 > 0) {
            int sampleSize3 = Integer.highestOneBit(sampleSize2);
            this.status = 0;
            this.header = header2;
            this.framePointer = -1;
            this.rawData = buffer.asReadOnlyBuffer();
            this.rawData.position(0);
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.savePrevious = false;
            Iterator it = header2.frames.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((GifFrame) it.next()).dispose == 3) {
                    this.savePrevious = true;
                    break;
                }
            }
            this.sampleSize = sampleSize3;
            this.downsampledWidth = header2.width / sampleSize3;
            this.downsampledHeight = header2.height / sampleSize3;
            this.mainPixels = this.bitmapProvider.obtainByteArray(header2.width * header2.height);
            this.mainScratch = this.bitmapProvider.obtainIntArray(this.downsampledWidth * this.downsampledHeight);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Sample size must be >=0, not: ");
            sb.append(sampleSize2);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    public synchronized int read(byte[] data) {
        this.header = getHeaderParser().setData(data).parseHeader();
        if (data != null) {
            setData(this.header, data);
        }
        return this.status;
    }

    public void setDefaultBitmapConfig(Config config) {
        if (config == Config.ARGB_8888 || config == Config.RGB_565) {
            this.bitmapConfig = config;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unsupported format: ");
        sb.append(config);
        sb.append(", must be one of ");
        sb.append(Config.ARGB_8888);
        sb.append(" or ");
        sb.append(Config.RGB_565);
        throw new IllegalArgumentException(sb.toString());
    }

    private Bitmap setPixels(GifFrame currentFrame, GifFrame previousFrame) {
        int[] dest = this.mainScratch;
        if (previousFrame == null) {
            Bitmap bitmap = this.previousImage;
            if (bitmap != null) {
                this.bitmapProvider.release(bitmap);
            }
            this.previousImage = null;
            Arrays.fill(dest, 0);
        }
        if (previousFrame != null && previousFrame.dispose == 3 && this.previousImage == null) {
            Arrays.fill(dest, 0);
        }
        if (previousFrame != null && previousFrame.dispose > 0) {
            if (previousFrame.dispose == 2) {
                int c = 0;
                if (!currentFrame.transparency) {
                    c = this.header.bgColor;
                    if (currentFrame.lct != null && this.header.bgIndex == currentFrame.transIndex) {
                        c = 0;
                    }
                } else if (this.framePointer == 0) {
                    this.isFirstFrameTransparent = Boolean.valueOf(true);
                }
                int downsampledIH = previousFrame.f43ih / this.sampleSize;
                int downsampledIY = previousFrame.f46iy / this.sampleSize;
                int downsampledIW = previousFrame.f44iw / this.sampleSize;
                int downsampledIX = previousFrame.f45ix / this.sampleSize;
                int i = this.downsampledWidth;
                int topLeft = (downsampledIY * i) + downsampledIX;
                int bottomLeft = (i * downsampledIH) + topLeft;
                int left = topLeft;
                while (left < bottomLeft) {
                    int right = left + downsampledIW;
                    for (int pointer = left; pointer < right; pointer++) {
                        dest[pointer] = c;
                    }
                    left += this.downsampledWidth;
                }
            } else if (previousFrame.dispose == 3) {
                Bitmap bitmap2 = this.previousImage;
                if (bitmap2 != null) {
                    int i2 = this.downsampledWidth;
                    bitmap2.getPixels(dest, 0, i2, 0, 0, i2, this.downsampledHeight);
                }
            }
        }
        decodeBitmapData(currentFrame);
        if (currentFrame.interlace || this.sampleSize != 1) {
            copyCopyIntoScratchRobust(currentFrame);
        } else {
            copyIntoScratchFast(currentFrame);
        }
        if (this.savePrevious && (currentFrame.dispose == 0 || currentFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            Bitmap bitmap3 = this.previousImage;
            int i3 = this.downsampledWidth;
            bitmap3.setPixels(dest, 0, i3, 0, 0, i3, this.downsampledHeight);
        }
        Bitmap result = getNextBitmap();
        int i4 = this.downsampledWidth;
        result.setPixels(dest, 0, i4, 0, 0, i4, this.downsampledHeight);
        return result;
    }

    private void copyIntoScratchFast(GifFrame currentFrame) {
        GifFrame gifFrame = currentFrame;
        int[] dest = this.mainScratch;
        int downsampledIH = gifFrame.f43ih;
        int downsampledIY = gifFrame.f46iy;
        int downsampledIW = gifFrame.f44iw;
        int downsampledIX = gifFrame.f45ix;
        boolean isFirstFrame = this.framePointer == 0;
        int width = this.downsampledWidth;
        byte[] mainPixels2 = this.mainPixels;
        int[] act2 = this.act;
        int transparentColorIndex = -1;
        int i = 0;
        while (i < downsampledIH) {
            int k = (i + downsampledIY) * width;
            int dx = k + downsampledIX;
            int dlim = dx + downsampledIW;
            if (k + width < dlim) {
                dlim = k + width;
            }
            int transparentColorIndex2 = transparentColorIndex;
            int sx = gifFrame.f44iw * i;
            int dx2 = dx;
            while (dx2 < dlim) {
                int downsampledIH2 = downsampledIH;
                byte downsampledIH3 = mainPixels2[sx];
                int downsampledIY2 = downsampledIY;
                int downsampledIY3 = downsampledIH3 & 255;
                if (downsampledIY3 != transparentColorIndex2) {
                    int color = act2[downsampledIY3];
                    if (color != 0) {
                        dest[dx2] = color;
                    } else {
                        transparentColorIndex2 = downsampledIH3;
                    }
                }
                sx++;
                dx2++;
                downsampledIH = downsampledIH2;
                downsampledIY = downsampledIY2;
            }
            int i2 = downsampledIY;
            i++;
            transparentColorIndex = transparentColorIndex2;
            gifFrame = currentFrame;
        }
        int i3 = downsampledIY;
        this.isFirstFrameTransparent = Boolean.valueOf(this.isFirstFrameTransparent == null && isFirstFrame && transparentColorIndex != -1);
    }

    private void copyCopyIntoScratchRobust(GifFrame currentFrame) {
        boolean z;
        int downsampledIH;
        int downsampledIX;
        int downsampledIW;
        int downsampledIY;
        int pass;
        GifFrame gifFrame = currentFrame;
        int[] dest = this.mainScratch;
        int downsampledIH2 = gifFrame.f43ih / this.sampleSize;
        int downsampledIY2 = gifFrame.f46iy / this.sampleSize;
        int downsampledIW2 = gifFrame.f44iw / this.sampleSize;
        int downsampledIX2 = gifFrame.f45ix / this.sampleSize;
        int iline = 0;
        boolean isFirstFrame = this.framePointer == 0;
        int sampleSize2 = this.sampleSize;
        int downsampledWidth2 = this.downsampledWidth;
        int downsampledHeight2 = this.downsampledHeight;
        byte[] mainPixels2 = this.mainPixels;
        int[] act2 = this.act;
        int pass2 = 1;
        Boolean isFirstFrameTransparent2 = this.isFirstFrameTransparent;
        int i = 0;
        int inc = 8;
        while (i < downsampledIH2) {
            int line = i;
            Boolean isFirstFrameTransparent3 = isFirstFrameTransparent2;
            if (gifFrame.interlace) {
                if (iline >= downsampledIH2) {
                    pass = pass2 + 1;
                    downsampledIH = downsampledIH2;
                    if (pass == 2) {
                        iline = 4;
                    } else if (pass == 3) {
                        iline = 2;
                        inc = 4;
                    } else if (pass == 4) {
                        iline = 1;
                        inc = 2;
                    }
                } else {
                    downsampledIH = downsampledIH2;
                    pass = pass2;
                }
                line = iline;
                iline += inc;
                pass2 = pass;
            } else {
                downsampledIH = downsampledIH2;
            }
            int downsampledIH3 = line + downsampledIY2;
            boolean isNotDownsampling = sampleSize2 == 1;
            if (downsampledIH3 < downsampledHeight2) {
                int k = downsampledIH3 * downsampledWidth2;
                int dx = k + downsampledIX2;
                int i2 = downsampledIH3;
                int dlim = dx + downsampledIW2;
                downsampledIY = downsampledIY2;
                if (k + downsampledWidth2 < dlim) {
                    dlim = k + downsampledWidth2;
                }
                downsampledIW = downsampledIW2;
                int sx = i * sampleSize2 * gifFrame.f44iw;
                if (isNotDownsampling) {
                    int sx2 = sx;
                    int dx2 = dx;
                    while (dx2 < dlim) {
                        int downsampledIX3 = downsampledIX2;
                        int averageColor = act2[mainPixels2[sx2] & 255];
                        if (averageColor != 0) {
                            dest[dx2] = averageColor;
                        } else if (isFirstFrame && isFirstFrameTransparent3 == null) {
                            isFirstFrameTransparent3 = Boolean.valueOf(true);
                        }
                        sx2 += sampleSize2;
                        dx2++;
                        downsampledIX2 = downsampledIX3;
                    }
                    downsampledIX = downsampledIX2;
                    isFirstFrameTransparent2 = isFirstFrameTransparent3;
                } else {
                    downsampledIX = downsampledIX2;
                    int maxPositionInSource = ((dlim - dx) * sampleSize2) + sx;
                    int sx3 = sx;
                    int dx3 = dx;
                    while (dx3 < dlim) {
                        int dlim2 = dlim;
                        int averageColor2 = averageColorsNear(sx3, maxPositionInSource, gifFrame.f44iw);
                        if (averageColor2 != 0) {
                            dest[dx3] = averageColor2;
                        } else if (isFirstFrame && isFirstFrameTransparent3 == null) {
                            isFirstFrameTransparent3 = Boolean.valueOf(true);
                        }
                        sx3 += sampleSize2;
                        dx3++;
                        dlim = dlim2;
                    }
                    isFirstFrameTransparent2 = isFirstFrameTransparent3;
                }
            } else {
                int line2 = downsampledIH3;
                downsampledIY = downsampledIY2;
                downsampledIW = downsampledIW2;
                downsampledIX = downsampledIX2;
                isFirstFrameTransparent2 = isFirstFrameTransparent3;
            }
            i++;
            downsampledIH2 = downsampledIH;
            downsampledIY2 = downsampledIY;
            downsampledIW2 = downsampledIW;
            downsampledIX2 = downsampledIX;
        }
        int i3 = downsampledIY2;
        int i4 = downsampledIW2;
        int i5 = downsampledIX2;
        Boolean isFirstFrameTransparent4 = isFirstFrameTransparent2;
        if (this.isFirstFrameTransparent == null) {
            if (isFirstFrameTransparent4 == null) {
                z = false;
            } else {
                z = isFirstFrameTransparent4.booleanValue();
            }
            this.isFirstFrameTransparent = Boolean.valueOf(z);
        }
    }

    private int averageColorsNear(int positionInMainPixels, int maxPositionInMainPixels, int currentFrameIw) {
        int alphaSum = 0;
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
        int totalAdded = 0;
        for (int i = positionInMainPixels; i < this.sampleSize + positionInMainPixels; i++) {
            byte[] bArr = this.mainPixels;
            if (i >= bArr.length || i >= maxPositionInMainPixels) {
                break;
            }
            int currentColor = this.act[bArr[i] & 255];
            if (currentColor != 0) {
                alphaSum += (currentColor >> 24) & 255;
                redSum += (currentColor >> 16) & 255;
                greenSum += (currentColor >> 8) & 255;
                blueSum += currentColor & 255;
                totalAdded++;
            }
        }
        for (int i2 = positionInMainPixels + currentFrameIw; i2 < positionInMainPixels + currentFrameIw + this.sampleSize; i2++) {
            byte[] bArr2 = this.mainPixels;
            if (i2 >= bArr2.length || i2 >= maxPositionInMainPixels) {
                break;
            }
            int currentColor2 = this.act[bArr2[i2] & 255];
            if (currentColor2 != 0) {
                alphaSum += (currentColor2 >> 24) & 255;
                redSum += (currentColor2 >> 16) & 255;
                greenSum += (currentColor2 >> 8) & 255;
                blueSum += currentColor2 & 255;
                totalAdded++;
            }
        }
        if (totalAdded == 0) {
            return 0;
        }
        return ((alphaSum / totalAdded) << 24) | ((redSum / totalAdded) << 16) | ((greenSum / totalAdded) << 8) | (blueSum / totalAdded);
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [short[]] */
    /* JADX WARNING: type inference failed for: r22v6 */
    /* JADX WARNING: type inference failed for: r14v7 */
    /* JADX WARNING: type inference failed for: r13v7 */
    /* JADX WARNING: type inference failed for: r24v6 */
    /* JADX WARNING: type inference failed for: r13v9 */
    /* JADX WARNING: type inference failed for: r13v10 */
    /* JADX WARNING: type inference failed for: r14v9 */
    /* JADX WARNING: type inference failed for: r13v11, types: [short] */
    /* JADX WARNING: type inference failed for: r13v12 */
    /* JADX WARNING: type inference failed for: r14v10 */
    /* JADX WARNING: type inference failed for: r14v11 */
    /* JADX WARNING: type inference failed for: r22v13 */
    /* JADX WARNING: type inference failed for: r14v12 */
    /* JADX WARNING: type inference failed for: r13v13 */
    /* JADX WARNING: type inference failed for: r13v14 */
    /* JADX WARNING: type inference failed for: r13v15 */
    /* JADX WARNING: type inference failed for: r13v16 */
    /* JADX WARNING: type inference failed for: r14v13 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=null, for r13v11, types: [short] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short[], code=null, for r4v1, types: [short[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r22v6
      assigns: []
      uses: []
      mth insns count: 173
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 9 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame r28) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            if (r1 == 0) goto L_0x000d
            java.nio.ByteBuffer r2 = r0.rawData
            int r3 = r1.bufferFrameStart
            r2.position(r3)
        L_0x000d:
            if (r1 != 0) goto L_0x0018
            com.bumptech.glide.gifdecoder.GifHeader r2 = r0.header
            int r2 = r2.width
            com.bumptech.glide.gifdecoder.GifHeader r3 = r0.header
            int r3 = r3.height
            goto L_0x001c
        L_0x0018:
            int r2 = r1.f44iw
            int r3 = r1.f43ih
        L_0x001c:
            int r2 = r2 * r3
            byte[] r3 = r0.mainPixels
            if (r3 == 0) goto L_0x0025
            int r3 = r3.length
            if (r3 >= r2) goto L_0x002d
        L_0x0025:
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r3 = r0.bitmapProvider
            byte[] r3 = r3.obtainByteArray(r2)
            r0.mainPixels = r3
        L_0x002d:
            byte[] r3 = r0.mainPixels
            short[] r4 = r0.prefix
            r5 = 4096(0x1000, float:5.74E-42)
            if (r4 != 0) goto L_0x0039
            short[] r4 = new short[r5]
            r0.prefix = r4
        L_0x0039:
            short[] r4 = r0.prefix
            byte[] r6 = r0.suffix
            if (r6 != 0) goto L_0x0043
            byte[] r6 = new byte[r5]
            r0.suffix = r6
        L_0x0043:
            byte[] r6 = r0.suffix
            byte[] r7 = r0.pixelStack
            if (r7 != 0) goto L_0x004f
            r7 = 4097(0x1001, float:5.741E-42)
            byte[] r7 = new byte[r7]
            r0.pixelStack = r7
        L_0x004f:
            byte[] r7 = r0.pixelStack
            int r8 = r27.readByte()
            r9 = 1
            int r10 = r9 << r8
            int r11 = r10 + 1
            int r12 = r10 + 2
            r13 = -1
            int r14 = r8 + 1
            int r15 = r9 << r14
            int r15 = r15 - r9
            r16 = 0
            r5 = r16
        L_0x0066:
            r9 = 0
            if (r5 >= r10) goto L_0x0072
            r4[r5] = r9
            byte r9 = (byte) r5
            r6[r5] = r9
            int r5 = r5 + 1
            r9 = 1
            goto L_0x0066
        L_0x0072:
            byte[] r1 = r0.block
            r17 = r9
            r18 = r9
            r19 = r9
            r20 = r9
            r21 = r9
            r22 = r9
            r23 = r9
            r24 = r9
            r26 = r12
            r12 = r5
            r5 = r24
            r24 = r14
            r14 = r18
            r18 = r15
            r15 = r26
        L_0x0091:
            if (r5 >= r2) goto L_0x0167
            if (r21 != 0) goto L_0x00a2
            int r21 = r27.readBlock()
            if (r21 > 0) goto L_0x00a0
            r9 = 3
            r0.status = r9
            goto L_0x0167
        L_0x00a0:
            r17 = 0
        L_0x00a2:
            byte r9 = r1[r17]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << r22
            int r23 = r23 + r9
            int r22 = r22 + 8
            r9 = 1
            int r17 = r17 + 1
            r9 = -1
            int r21 = r21 + -1
            r25 = r20
            r20 = r19
            r19 = r5
            r5 = r22
            r22 = r14
            r14 = r13
            r13 = r12
            r12 = r24
        L_0x00c0:
            if (r5 < r12) goto L_0x014e
            r13 = r23 & r18
            int r23 = r23 >> r12
            int r5 = r5 - r12
            if (r13 != r10) goto L_0x00d5
            int r12 = r8 + 1
            r16 = 1
            int r24 = r16 << r12
            int r18 = r24 + -1
            int r15 = r10 + 2
            r14 = -1
            goto L_0x00c0
        L_0x00d5:
            r16 = 1
            if (r13 != r11) goto L_0x00e9
            r24 = r12
            r12 = r13
            r13 = r14
            r14 = r22
            r9 = 0
            r22 = r5
            r5 = r19
            r19 = r20
            r20 = r25
            goto L_0x0091
        L_0x00e9:
            if (r14 != r9) goto L_0x00f7
            byte r24 = r6[r13]
            r3[r22] = r24
            int r22 = r22 + 1
            int r19 = r19 + 1
            r14 = r13
            r25 = r13
            goto L_0x00c0
        L_0x00f7:
            r24 = r13
            if (r13 < r15) goto L_0x0105
            r9 = r25
            byte r0 = (byte) r9
            r7[r20] = r0
            int r20 = r20 + 1
            r0 = r14
            r13 = r0
            goto L_0x0107
        L_0x0105:
            r9 = r25
        L_0x0107:
            if (r13 < r10) goto L_0x0112
            byte r0 = r6[r13]
            r7[r20] = r0
            int r20 = r20 + 1
            short r13 = r4[r13]
            goto L_0x0107
        L_0x0112:
            byte r0 = r6[r13]
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r9 = (byte) r0
            r3[r22] = r9
            int r22 = r22 + 1
            int r19 = r19 + 1
        L_0x011d:
            if (r20 <= 0) goto L_0x012a
            int r20 = r20 + -1
            byte r9 = r7[r20]
            r3[r22] = r9
            int r22 = r22 + 1
            int r19 = r19 + 1
            goto L_0x011d
        L_0x012a:
            r9 = 4096(0x1000, float:5.74E-42)
            if (r15 >= r9) goto L_0x0145
            short r9 = (short) r14
            r4[r15] = r9
            byte r9 = (byte) r0
            r6[r15] = r9
            int r15 = r15 + 1
            r9 = r15 & r18
            if (r9 != 0) goto L_0x0143
            r9 = 4096(0x1000, float:5.74E-42)
            if (r15 >= r9) goto L_0x0145
            int r12 = r12 + 1
            int r18 = r18 + r15
            goto L_0x0145
        L_0x0143:
            r9 = 4096(0x1000, float:5.74E-42)
        L_0x0145:
            r14 = r24
            r9 = -1
            r25 = r0
            r0 = r27
            goto L_0x00c0
        L_0x014e:
            r9 = r25
            r0 = 4096(0x1000, float:5.74E-42)
            r16 = 1
            r0 = r27
            r24 = r12
            r12 = r13
            r13 = r14
            r14 = r22
            r22 = r5
            r5 = r19
            r19 = r20
            r20 = r9
            r9 = 0
            goto L_0x0091
        L_0x0167:
            r0 = 0
            java.util.Arrays.fill(r3, r14, r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame):void");
    }

    private int readByte() {
        return this.rawData.get() & UByte.MAX_VALUE;
    }

    private int readBlock() {
        int blockSize = readByte();
        if (blockSize <= 0) {
            return blockSize;
        }
        ByteBuffer byteBuffer = this.rawData;
        byteBuffer.get(this.block, 0, Math.min(blockSize, byteBuffer.remaining()));
        return blockSize;
    }

    private Bitmap getNextBitmap() {
        Boolean bool = this.isFirstFrameTransparent;
        Bitmap result = this.bitmapProvider.obtain(this.downsampledWidth, this.downsampledHeight, (bool == null || bool.booleanValue()) ? Config.ARGB_8888 : this.bitmapConfig);
        result.setHasAlpha(true);
        return result;
    }
}
