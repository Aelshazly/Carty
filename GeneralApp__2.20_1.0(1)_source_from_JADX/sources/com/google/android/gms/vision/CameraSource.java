package com.google.android.gms.vision;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.google.android.gms.common.images.Size;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public class CameraSource {
    public static final int CAMERA_FACING_BACK = 0;
    public static final int CAMERA_FACING_FRONT = 1;
    /* access modifiers changed from: private */
    public int facing;
    /* access modifiers changed from: private */
    public int rotation;
    /* access modifiers changed from: private */
    public Context zze;
    /* access modifiers changed from: private */
    public final Object zzf;
    /* access modifiers changed from: private */
    public Camera zzg;
    /* access modifiers changed from: private */
    public Size zzh;
    /* access modifiers changed from: private */
    public float zzi;
    /* access modifiers changed from: private */
    public int zzj;
    /* access modifiers changed from: private */
    public int zzk;
    /* access modifiers changed from: private */
    public boolean zzl;
    /* access modifiers changed from: private */
    public String zzm;
    private SurfaceTexture zzn;
    private boolean zzo;
    private Thread zzp;
    /* access modifiers changed from: private */
    public zzb zzq;
    /* access modifiers changed from: private */
    public Map<byte[], ByteBuffer> zzr;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static class Builder {
        private final Detector<?> zzt;
        private CameraSource zzu = new CameraSource();

        public Builder(Context context, Detector<?> detector) {
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            } else if (detector != null) {
                this.zzt = detector;
                this.zzu.zze = context;
            } else {
                throw new IllegalArgumentException("No detector supplied.");
            }
        }

        public Builder setRequestedFps(float f) {
            if (f > 0.0f) {
                this.zzu.zzi = f;
                return this;
            }
            StringBuilder sb = new StringBuilder(28);
            sb.append("Invalid fps: ");
            sb.append(f);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            if (i <= 0 || i > 1000000 || i2 <= 0 || i2 > 1000000) {
                StringBuilder sb = new StringBuilder(45);
                sb.append("Invalid preview size: ");
                sb.append(i);
                sb.append("x");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzu.zzj = i;
            this.zzu.zzk = i2;
            return this;
        }

        public Builder setFacing(int i) {
            if (i == 0 || i == 1) {
                this.zzu.facing = i;
                return this;
            }
            StringBuilder sb = new StringBuilder(27);
            sb.append("Invalid camera: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setAutoFocusEnabled(boolean z) {
            this.zzu.zzl = z;
            return this;
        }

        public Builder setFocusMode(String str) {
            if (!str.equals("continuous-video") && !str.equals("continuous-picture")) {
                Log.w("CameraSource", String.format("FocusMode %s is not supported for now.", new Object[]{str}));
                str = null;
            }
            this.zzu.zzm = str;
            return this;
        }

        public CameraSource build() {
            CameraSource cameraSource = this.zzu;
            cameraSource.getClass();
            cameraSource.zzq = new zzb(this.zzt);
            return this.zzu;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public interface ShutterCallback {
        void onShutter();
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    class zza implements PreviewCallback {
        private zza() {
        }

        public final void onPreviewFrame(byte[] bArr, Camera camera) {
            CameraSource.this.zzq.zza(bArr, camera);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    class zzb implements Runnable {
        private final Object lock = new Object();
        private Detector<?> zzt;
        private long zzv = SystemClock.elapsedRealtime();
        private boolean zzw = true;
        private long zzx;
        private int zzy = 0;
        private ByteBuffer zzz;

        zzb(Detector<?> detector) {
            this.zzt = detector;
        }

        /* access modifiers changed from: 0000 */
        public final void release() {
            this.zzt.release();
            this.zzt = null;
        }

        /* access modifiers changed from: 0000 */
        public final void setActive(boolean z) {
            synchronized (this.lock) {
                this.zzw = z;
                this.lock.notifyAll();
            }
        }

        /* access modifiers changed from: 0000 */
        public final void zza(byte[] bArr, Camera camera) {
            synchronized (this.lock) {
                if (this.zzz != null) {
                    camera.addCallbackBuffer(this.zzz.array());
                    this.zzz = null;
                }
                if (!CameraSource.this.zzr.containsKey(bArr)) {
                    Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
                    return;
                }
                this.zzx = SystemClock.elapsedRealtime() - this.zzv;
                this.zzy++;
                this.zzz = (ByteBuffer) CameraSource.this.zzr.get(bArr);
                this.lock.notifyAll();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
            r6.zzt.receiveFrame(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0075, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0077, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
            android.util.Log.e("CameraSource", "Exception thrown from receiver.", r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x008e, code lost:
            com.google.android.gms.vision.CameraSource.zzb(r6.zzs).addCallbackBuffer(r2.array());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x009b, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r6 = this;
            L_0x0000:
                java.lang.Object r0 = r6.lock
                monitor-enter(r0)
            L_0x0003:
                boolean r1 = r6.zzw     // Catch:{ all -> 0x009c }
                if (r1 == 0) goto L_0x001b
                java.nio.ByteBuffer r1 = r6.zzz     // Catch:{ all -> 0x009c }
                if (r1 != 0) goto L_0x001b
                java.lang.Object r1 = r6.lock     // Catch:{ InterruptedException -> 0x0011 }
                r1.wait()     // Catch:{ InterruptedException -> 0x0011 }
                goto L_0x0003
            L_0x0011:
                r1 = move-exception
                java.lang.String r2 = "CameraSource"
                java.lang.String r3 = "Frame processing loop terminated."
                android.util.Log.d(r2, r3, r1)     // Catch:{ all -> 0x009c }
                monitor-exit(r0)     // Catch:{ all -> 0x009c }
                return
            L_0x001b:
                boolean r1 = r6.zzw     // Catch:{ all -> 0x009c }
                if (r1 != 0) goto L_0x0021
                monitor-exit(r0)     // Catch:{ all -> 0x009c }
                return
            L_0x0021:
                com.google.android.gms.vision.Frame$Builder r1 = new com.google.android.gms.vision.Frame$Builder     // Catch:{ all -> 0x009c }
                r1.<init>()     // Catch:{ all -> 0x009c }
                java.nio.ByteBuffer r2 = r6.zzz     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.CameraSource r3 = com.google.android.gms.vision.CameraSource.this     // Catch:{ all -> 0x009c }
                com.google.android.gms.common.images.Size r3 = r3.zzh     // Catch:{ all -> 0x009c }
                int r3 = r3.getWidth()     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.CameraSource r4 = com.google.android.gms.vision.CameraSource.this     // Catch:{ all -> 0x009c }
                com.google.android.gms.common.images.Size r4 = r4.zzh     // Catch:{ all -> 0x009c }
                int r4 = r4.getHeight()     // Catch:{ all -> 0x009c }
                r5 = 17
                com.google.android.gms.vision.Frame$Builder r1 = r1.setImageData(r2, r3, r4, r5)     // Catch:{ all -> 0x009c }
                int r2 = r6.zzy     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Frame$Builder r1 = r1.setId(r2)     // Catch:{ all -> 0x009c }
                long r2 = r6.zzx     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Frame$Builder r1 = r1.setTimestampMillis(r2)     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.CameraSource r2 = com.google.android.gms.vision.CameraSource.this     // Catch:{ all -> 0x009c }
                int r2 = r2.rotation     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Frame$Builder r1 = r1.setRotation(r2)     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Frame r1 = r1.build()     // Catch:{ all -> 0x009c }
                java.nio.ByteBuffer r2 = r6.zzz     // Catch:{ all -> 0x009c }
                r3 = 0
                r6.zzz = r3     // Catch:{ all -> 0x009c }
                monitor-exit(r0)     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Detector<?> r0 = r6.zzt     // Catch:{ Exception -> 0x0077 }
                r0.receiveFrame(r1)     // Catch:{ Exception -> 0x0077 }
                com.google.android.gms.vision.CameraSource r0 = com.google.android.gms.vision.CameraSource.this
                android.hardware.Camera r0 = r0.zzg
                byte[] r1 = r2.array()
                r0.addCallbackBuffer(r1)
                goto L_0x0000
            L_0x0075:
                r0 = move-exception
                goto L_0x008e
            L_0x0077:
                r0 = move-exception
                java.lang.String r1 = "CameraSource"
                java.lang.String r3 = "Exception thrown from receiver."
                android.util.Log.e(r1, r3, r0)     // Catch:{ all -> 0x0075 }
                com.google.android.gms.vision.CameraSource r0 = com.google.android.gms.vision.CameraSource.this
                android.hardware.Camera r0 = r0.zzg
                byte[] r1 = r2.array()
                r0.addCallbackBuffer(r1)
                goto L_0x0000
            L_0x008e:
                com.google.android.gms.vision.CameraSource r1 = com.google.android.gms.vision.CameraSource.this
                android.hardware.Camera r1 = r1.zzg
                byte[] r2 = r2.array()
                r1.addCallbackBuffer(r2)
                throw r0
            L_0x009c:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x009c }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.CameraSource.zzb.run():void");
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    static class zzc implements android.hardware.Camera.ShutterCallback {
        /* access modifiers changed from: private */
        public ShutterCallback zzaa;

        private zzc() {
        }

        public final void onShutter() {
            ShutterCallback shutterCallback = this.zzaa;
            if (shutterCallback != null) {
                shutterCallback.onShutter();
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    class zzd implements android.hardware.Camera.PictureCallback {
        /* access modifiers changed from: private */
        public PictureCallback zzab;

        private zzd() {
        }

        public final void onPictureTaken(byte[] bArr, Camera camera) {
            PictureCallback pictureCallback = this.zzab;
            if (pictureCallback != null) {
                pictureCallback.onPictureTaken(bArr);
            }
            synchronized (CameraSource.this.zzf) {
                if (CameraSource.this.zzg != null) {
                    CameraSource.this.zzg.startPreview();
                }
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    static class zze {
        private Size zzac;
        private Size zzad;

        public zze(Camera.Size size, @Nullable Camera.Size size2) {
            this.zzac = new Size(size.width, size.height);
            if (size2 != null) {
                this.zzad = new Size(size2.width, size2.height);
            }
        }

        public final Size zzb() {
            return this.zzac;
        }

        @Nullable
        public final Size zzc() {
            return this.zzad;
        }
    }

    public void release() {
        synchronized (this.zzf) {
            stop();
            this.zzq.release();
        }
    }

    public CameraSource start() throws IOException {
        synchronized (this.zzf) {
            if (this.zzg != null) {
                return this;
            }
            this.zzg = zza();
            this.zzn = new SurfaceTexture(100);
            this.zzg.setPreviewTexture(this.zzn);
            this.zzo = true;
            this.zzg.startPreview();
            this.zzp = new Thread(this.zzq);
            this.zzp.setName("gms.vision.CameraSource");
            this.zzq.setActive(true);
            this.zzp.start();
            return this;
        }
    }

    public CameraSource start(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this.zzf) {
            if (this.zzg != null) {
                return this;
            }
            this.zzg = zza();
            this.zzg.setPreviewDisplay(surfaceHolder);
            this.zzg.startPreview();
            this.zzp = new Thread(this.zzq);
            this.zzq.setActive(true);
            this.zzp.start();
            this.zzo = false;
            return this;
        }
    }

    public void stop() {
        synchronized (this.zzf) {
            this.zzq.setActive(false);
            if (this.zzp != null) {
                try {
                    this.zzp.join();
                } catch (InterruptedException e) {
                    Log.d("CameraSource", "Frame processing thread interrupted on release.");
                }
                this.zzp = null;
            }
            if (this.zzg != null) {
                this.zzg.stopPreview();
                this.zzg.setPreviewCallbackWithBuffer(null);
                try {
                    if (this.zzo) {
                        this.zzg.setPreviewTexture(null);
                    } else {
                        this.zzg.setPreviewDisplay(null);
                    }
                } catch (Exception e2) {
                    String str = "CameraSource";
                    String valueOf = String.valueOf(e2);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 32);
                    sb.append("Failed to clear camera preview: ");
                    sb.append(valueOf);
                    Log.e(str, sb.toString());
                }
                this.zzg.release();
                this.zzg = null;
            }
            this.zzr.clear();
        }
    }

    public Size getPreviewSize() {
        return this.zzh;
    }

    public int getCameraFacing() {
        return this.facing;
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback) {
        synchronized (this.zzf) {
            if (this.zzg != null) {
                zzc zzc2 = new zzc();
                zzc2.zzaa = shutterCallback;
                zzd zzd2 = new zzd();
                zzd2.zzab = pictureCallback;
                this.zzg.takePicture(zzc2, null, null, zzd2);
            }
        }
    }

    private CameraSource() {
        this.zzf = new Object();
        this.facing = 0;
        this.zzi = 30.0f;
        this.zzj = 1024;
        this.zzk = 768;
        this.zzl = false;
        this.zzr = new HashMap();
    }

    private final Camera zza() throws IOException {
        int i;
        int i2;
        int i3;
        int i4 = this.facing;
        CameraInfo cameraInfo = new CameraInfo();
        int i5 = 0;
        while (true) {
            if (i5 >= Camera.getNumberOfCameras()) {
                i5 = -1;
                break;
            }
            Camera.getCameraInfo(i5, cameraInfo);
            if (cameraInfo.facing == i4) {
                break;
            }
            i5++;
        }
        if (i5 != -1) {
            Camera open = Camera.open(i5);
            int i6 = this.zzj;
            int i7 = this.zzk;
            Parameters parameters = open.getParameters();
            List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            List supportedPictureSizes = parameters.getSupportedPictureSizes();
            ArrayList arrayList = new ArrayList();
            for (Camera.Size size : supportedPreviewSizes) {
                float f = ((float) size.width) / ((float) size.height);
                Iterator it = supportedPictureSizes.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Camera.Size size2 = (Camera.Size) it.next();
                    if (Math.abs(f - (((float) size2.width) / ((float) size2.height))) < 0.01f) {
                        arrayList.add(new zze(size, size2));
                        break;
                    }
                }
            }
            String str = "CameraSource";
            if (arrayList.size() == 0) {
                Log.w(str, "No preview sizes have a corresponding same-aspect-ratio picture size");
                for (Camera.Size zze2 : supportedPreviewSizes) {
                    arrayList.add(new zze(zze2, null));
                }
            }
            ArrayList arrayList2 = arrayList;
            int size3 = arrayList2.size();
            zze zze3 = null;
            int i8 = 0;
            int i9 = Integer.MAX_VALUE;
            while (i8 < size3) {
                Object obj = arrayList2.get(i8);
                i8++;
                zze zze4 = (zze) obj;
                Size zzb2 = zze4.zzb();
                int abs = Math.abs(zzb2.getWidth() - i6) + Math.abs(zzb2.getHeight() - i7);
                if (abs < i9) {
                    zze3 = zze4;
                    i9 = abs;
                }
            }
            if (zze3 != null) {
                Size zzc2 = zze3.zzc();
                this.zzh = zze3.zzb();
                int i10 = (int) (this.zzi * 1000.0f);
                int[] iArr = null;
                int i11 = Integer.MAX_VALUE;
                for (int[] iArr2 : open.getParameters().getSupportedPreviewFpsRange()) {
                    int abs2 = Math.abs(i10 - iArr2[0]) + Math.abs(i10 - iArr2[1]);
                    if (abs2 < i11) {
                        iArr = iArr2;
                        i11 = abs2;
                    }
                }
                if (iArr != null) {
                    Parameters parameters2 = open.getParameters();
                    if (zzc2 != null) {
                        parameters2.setPictureSize(zzc2.getWidth(), zzc2.getHeight());
                    }
                    parameters2.setPreviewSize(this.zzh.getWidth(), this.zzh.getHeight());
                    parameters2.setPreviewFpsRange(iArr[0], iArr[1]);
                    parameters2.setPreviewFormat(17);
                    int rotation2 = ((WindowManager) this.zze.getSystemService("window")).getDefaultDisplay().getRotation();
                    if (rotation2 == 0) {
                        i = 0;
                    } else if (rotation2 == 1) {
                        i = 90;
                    } else if (rotation2 == 2) {
                        i = 180;
                    } else if (rotation2 != 3) {
                        StringBuilder sb = new StringBuilder(31);
                        sb.append("Bad rotation value: ");
                        sb.append(rotation2);
                        Log.e(str, sb.toString());
                        i = 0;
                    } else {
                        i = 270;
                    }
                    CameraInfo cameraInfo2 = new CameraInfo();
                    Camera.getCameraInfo(i5, cameraInfo2);
                    if (cameraInfo2.facing == 1) {
                        i2 = (cameraInfo2.orientation + i) % 360;
                        i3 = (360 - i2) % 360;
                    } else {
                        i2 = ((cameraInfo2.orientation - i) + 360) % 360;
                        i3 = i2;
                    }
                    this.rotation = i2 / 90;
                    open.setDisplayOrientation(i3);
                    parameters2.setRotation(i2);
                    if (this.zzm != null) {
                        if (parameters2.getSupportedFocusModes().contains(this.zzm)) {
                            parameters2.setFocusMode(this.zzm);
                        } else {
                            Log.w(str, String.format("FocusMode %s is not supported on this device.", new Object[]{this.zzm}));
                            this.zzm = null;
                        }
                    }
                    if (this.zzm == null && this.zzl) {
                        String str2 = "continuous-video";
                        if (parameters2.getSupportedFocusModes().contains(str2)) {
                            parameters2.setFocusMode(str2);
                            this.zzm = str2;
                        } else {
                            Log.i(str, "Camera auto focus is not supported on this device.");
                        }
                    }
                    open.setParameters(parameters2);
                    open.setPreviewCallbackWithBuffer(new zza());
                    open.addCallbackBuffer(zza(this.zzh));
                    open.addCallbackBuffer(zza(this.zzh));
                    open.addCallbackBuffer(zza(this.zzh));
                    open.addCallbackBuffer(zza(this.zzh));
                    return open;
                }
                throw new IOException("Could not find suitable preview frames per second range.");
            }
            throw new IOException("Could not find suitable preview size.");
        }
        throw new IOException("Could not find requested camera.");
    }

    private final byte[] zza(Size size) {
        byte[] bArr = new byte[(((int) Math.ceil(((double) ((long) ((size.getHeight() * size.getWidth()) * ImageFormat.getBitsPerPixel(17)))) / 8.0d)) + 1)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (!wrap.hasArray() || wrap.array() != bArr) {
            throw new IllegalStateException("Failed to create valid buffer for camera source.");
        }
        this.zzr.put(bArr, wrap);
        return bArr;
    }
}
