package com.google.android.gms.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzac;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzah;
import com.google.android.gms.internal.vision.zzai;
import com.google.android.gms.internal.vision.zzp;
import com.google.android.gms.internal.vision.zzq;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.Frame.Metadata;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class TextRecognizer extends Detector<TextBlock> {
    private final zzai zzef;

    /* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
    public static class Builder {
        private Context zze;
        private zzah zzeg = new zzah();

        public Builder(Context context) {
            this.zze = context;
        }

        public TextRecognizer build() {
            return new TextRecognizer(new zzai(this.zze, this.zzeg));
        }
    }

    private TextRecognizer() {
        throw new IllegalStateException("Default constructor called");
    }

    private TextRecognizer(zzai zzai) {
        this.zzef = zzai;
    }

    public final SparseArray<TextBlock> detect(Frame frame) {
        Bitmap bitmap;
        Rect rect;
        byte[] bArr;
        zzae zzae = new zzae(new Rect());
        if (frame != null) {
            zzp zzc = zzp.zzc(frame);
            if (frame.getBitmap() != null) {
                bitmap = frame.getBitmap();
            } else {
                Metadata metadata = frame.getMetadata();
                ByteBuffer grayscaleImageData = frame.getGrayscaleImageData();
                int format = metadata.getFormat();
                int i = zzc.width;
                int i2 = zzc.height;
                if (!grayscaleImageData.hasArray() || grayscaleImageData.arrayOffset() != 0) {
                    byte[] bArr2 = new byte[grayscaleImageData.capacity()];
                    grayscaleImageData.get(bArr2);
                    bArr = bArr2;
                } else {
                    bArr = grayscaleImageData.array();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                YuvImage yuvImage = new YuvImage(bArr, format, i, i2, null);
                yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
            Bitmap zzb = zzq.zzb(bitmap, zzc);
            if (!zzae.zzer.isEmpty()) {
                Rect rect2 = zzae.zzer;
                int width = frame.getMetadata().getWidth();
                int height = frame.getMetadata().getHeight();
                int i3 = zzc.rotation;
                if (i3 == 1) {
                    rect = new Rect(height - rect2.bottom, rect2.left, height - rect2.top, rect2.right);
                } else if (i3 == 2) {
                    rect = new Rect(width - rect2.right, height - rect2.bottom, width - rect2.left, height - rect2.top);
                } else if (i3 != 3) {
                    rect = rect2;
                } else {
                    rect = new Rect(rect2.top, width - rect2.right, rect2.bottom, width - rect2.left);
                }
                zzae.zzer.set(rect);
            }
            zzc.rotation = 0;
            zzac[] zza = this.zzef.zza(zzb, zzc, zzae);
            SparseArray sparseArray = new SparseArray();
            for (zzac zzac : zza) {
                SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzac.zzep);
                if (sparseArray2 == null) {
                    sparseArray2 = new SparseArray();
                    sparseArray.append(zzac.zzep, sparseArray2);
                }
                sparseArray2.append(zzac.zzeq, zzac);
            }
            SparseArray<TextBlock> sparseArray3 = new SparseArray<>(sparseArray.size());
            for (int i4 = 0; i4 < sparseArray.size(); i4++) {
                sparseArray3.append(sparseArray.keyAt(i4), new TextBlock((SparseArray) sparseArray.valueAt(i4)));
            }
            return sparseArray3;
        }
        throw new IllegalArgumentException("No frame supplied.");
    }

    public final boolean isOperational() {
        return this.zzef.isOperational();
    }

    public final void release() {
        super.release();
        this.zzef.zzo();
    }
}
