package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class TransformationUtils {
    private static final Lock BITMAP_DRAWABLE_LOCK = (MODELS_REQUIRING_BITMAP_LOCK.contains(Build.MODEL) ? new ReentrantLock() : new NoLock());
    private static final Paint CIRCLE_CROP_BITMAP_PAINT = new Paint(7);
    private static final int CIRCLE_CROP_PAINT_FLAGS = 7;
    private static final Paint CIRCLE_CROP_SHAPE_PAINT = new Paint(7);
    private static final Paint DEFAULT_PAINT = new Paint(6);
    private static final Set<String> MODELS_REQUIRING_BITMAP_LOCK = new HashSet(Arrays.asList(new String[]{"XT1085", "XT1092", "XT1093", "XT1094", "XT1095", "XT1096", "XT1097", "XT1098", "XT1031", "XT1028", "XT937C", "XT1032", "XT1008", "XT1033", "XT1035", "XT1034", "XT939G", "XT1039", "XT1040", "XT1042", "XT1045", "XT1063", "XT1064", "XT1068", "XT1069", "XT1072", "XT1077", "XT1078", "XT1079"}));
    public static final int PAINT_FLAGS = 6;
    private static final String TAG = "TransformationUtils";

    private static final class NoLock implements Lock {
        NoLock() {
        }

        public void lock() {
        }

        public void lockInterruptibly() throws InterruptedException {
        }

        public boolean tryLock() {
            return true;
        }

        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return true;
        }

        public void unlock() {
        }

        public Condition newCondition() {
            throw new UnsupportedOperationException("Should not be called");
        }
    }

    static {
        CIRCLE_CROP_BITMAP_PAINT.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
    }

    private TransformationUtils() {
    }

    public static Lock getBitmapDrawableLock() {
        return BITMAP_DRAWABLE_LOCK;
    }

    public static Bitmap centerCrop(BitmapPool pool, Bitmap inBitmap, int width, int height) {
        float dy;
        float dx;
        float scale;
        if (inBitmap.getWidth() == width && inBitmap.getHeight() == height) {
            return inBitmap;
        }
        Matrix m = new Matrix();
        if (inBitmap.getWidth() * height > inBitmap.getHeight() * width) {
            scale = ((float) height) / ((float) inBitmap.getHeight());
            dx = (((float) width) - (((float) inBitmap.getWidth()) * scale)) * 0.5f;
            dy = 0.0f;
        } else {
            scale = ((float) width) / ((float) inBitmap.getWidth());
            dx = 0.0f;
            dy = (((float) height) - (((float) inBitmap.getHeight()) * scale)) * 0.5f;
        }
        m.setScale(scale, scale);
        m.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (0.5f + dy)));
        Bitmap result = pool.get(width, height, getNonNullConfig(inBitmap));
        setAlpha(inBitmap, result);
        applyMatrix(inBitmap, result, m);
        return result;
    }

    public static Bitmap fitCenter(BitmapPool pool, Bitmap inBitmap, int width, int height) {
        int width2 = inBitmap.getWidth();
        String str = TAG;
        if (width2 == width && inBitmap.getHeight() == height) {
            if (Log.isLoggable(str, 2)) {
                Log.v(str, "requested target size matches input, returning input");
            }
            return inBitmap;
        }
        float minPercentage = Math.min(((float) width) / ((float) inBitmap.getWidth()), ((float) height) / ((float) inBitmap.getHeight()));
        int targetWidth = Math.round(((float) inBitmap.getWidth()) * minPercentage);
        int targetHeight = Math.round(((float) inBitmap.getHeight()) * minPercentage);
        if (inBitmap.getWidth() == targetWidth && inBitmap.getHeight() == targetHeight) {
            if (Log.isLoggable(str, 2)) {
                Log.v(str, "adjusted target size matches input, returning input");
            }
            return inBitmap;
        }
        Bitmap toReuse = pool.get((int) (((float) inBitmap.getWidth()) * minPercentage), (int) (((float) inBitmap.getHeight()) * minPercentage), getNonNullConfig(inBitmap));
        setAlpha(inBitmap, toReuse);
        if (Log.isLoggable(str, 2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("request: ");
            sb.append(width);
            String str2 = "x";
            sb.append(str2);
            sb.append(height);
            Log.v(str, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("toFit:   ");
            sb2.append(inBitmap.getWidth());
            sb2.append(str2);
            sb2.append(inBitmap.getHeight());
            Log.v(str, sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("toReuse: ");
            sb3.append(toReuse.getWidth());
            sb3.append(str2);
            sb3.append(toReuse.getHeight());
            Log.v(str, sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            sb4.append("minPct:   ");
            sb4.append(minPercentage);
            Log.v(str, sb4.toString());
        }
        Matrix matrix = new Matrix();
        matrix.setScale(minPercentage, minPercentage);
        applyMatrix(inBitmap, toReuse, matrix);
        return toReuse;
    }

    public static Bitmap centerInside(BitmapPool pool, Bitmap inBitmap, int width, int height) {
        int width2 = inBitmap.getWidth();
        String str = TAG;
        if (width2 > width || inBitmap.getHeight() > height) {
            if (Log.isLoggable(str, 2)) {
                Log.v(str, "requested target size too big for input, fit centering instead");
            }
            return fitCenter(pool, inBitmap, width, height);
        }
        if (Log.isLoggable(str, 2)) {
            Log.v(str, "requested target size larger or equal to input, returning input");
        }
        return inBitmap;
    }

    public static void setAlpha(Bitmap inBitmap, Bitmap outBitmap) {
        outBitmap.setHasAlpha(inBitmap.hasAlpha());
    }

    public static Bitmap rotateImage(Bitmap imageToOrient, int degreesToRotate) {
        Bitmap result = imageToOrient;
        if (degreesToRotate == 0) {
            return result;
        }
        try {
            Matrix matrix = new Matrix();
            matrix.setRotate((float) degreesToRotate);
            return Bitmap.createBitmap(imageToOrient, 0, 0, imageToOrient.getWidth(), imageToOrient.getHeight(), matrix, true);
        } catch (Exception e) {
            String str = TAG;
            if (!Log.isLoggable(str, 6)) {
                return result;
            }
            Log.e(str, "Exception when trying to orient image", e);
            return result;
        }
    }

    public static int getExifOrientationDegrees(int exifOrientation) {
        switch (exifOrientation) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public static Bitmap rotateImageExif(BitmapPool pool, Bitmap inBitmap, int exifOrientation) {
        if (!isExifOrientationRequired(exifOrientation)) {
            return inBitmap;
        }
        Matrix matrix = new Matrix();
        initializeMatrixForRotation(exifOrientation, matrix);
        RectF newRect = new RectF(0.0f, 0.0f, (float) inBitmap.getWidth(), (float) inBitmap.getHeight());
        matrix.mapRect(newRect);
        Bitmap result = pool.get(Math.round(newRect.width()), Math.round(newRect.height()), getNonNullConfig(inBitmap));
        matrix.postTranslate(-newRect.left, -newRect.top);
        result.setHasAlpha(inBitmap.hasAlpha());
        applyMatrix(inBitmap, result, matrix);
        return result;
    }

    public static boolean isExifOrientationRequired(int exifOrientation) {
        switch (exifOrientation) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    public static Bitmap circleCrop(BitmapPool pool, Bitmap inBitmap, int destWidth, int destHeight) {
        BitmapPool bitmapPool = pool;
        int destMinEdge = Math.min(destWidth, destHeight);
        float radius = ((float) destMinEdge) / 2.0f;
        int srcWidth = inBitmap.getWidth();
        int srcHeight = inBitmap.getHeight();
        float maxScale = Math.max(((float) destMinEdge) / ((float) srcWidth), ((float) destMinEdge) / ((float) srcHeight));
        float scaledWidth = maxScale * ((float) srcWidth);
        float scaledHeight = maxScale * ((float) srcHeight);
        float left = (((float) destMinEdge) - scaledWidth) / 2.0f;
        float top = (((float) destMinEdge) - scaledHeight) / 2.0f;
        RectF destRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);
        Bitmap toTransform = getAlphaSafeBitmap(pool, inBitmap);
        float f = top;
        Bitmap result = bitmapPool.get(destMinEdge, destMinEdge, getAlphaSafeConfig(inBitmap));
        result.setHasAlpha(true);
        BITMAP_DRAWABLE_LOCK.lock();
        try {
            Canvas canvas = new Canvas(result);
            int i = destMinEdge;
            try {
                canvas.drawCircle(radius, radius, radius, CIRCLE_CROP_SHAPE_PAINT);
                float f2 = radius;
                try {
                    canvas.drawBitmap(toTransform, null, destRect, CIRCLE_CROP_BITMAP_PAINT);
                    clear(canvas);
                    BITMAP_DRAWABLE_LOCK.unlock();
                    if (!toTransform.equals(inBitmap)) {
                        bitmapPool.put(toTransform);
                    }
                    return result;
                } catch (Throwable th) {
                    th = th;
                    Bitmap bitmap = inBitmap;
                    BITMAP_DRAWABLE_LOCK.unlock();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                Bitmap bitmap2 = inBitmap;
                float f3 = radius;
                BITMAP_DRAWABLE_LOCK.unlock();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            int i2 = destMinEdge;
            float f4 = radius;
            Bitmap bitmap3 = inBitmap;
            BITMAP_DRAWABLE_LOCK.unlock();
            throw th;
        }
    }

    private static Bitmap getAlphaSafeBitmap(BitmapPool pool, Bitmap maybeAlphaSafe) {
        Config safeConfig = getAlphaSafeConfig(maybeAlphaSafe);
        if (safeConfig.equals(maybeAlphaSafe.getConfig())) {
            return maybeAlphaSafe;
        }
        Bitmap argbBitmap = pool.get(maybeAlphaSafe.getWidth(), maybeAlphaSafe.getHeight(), safeConfig);
        new Canvas(argbBitmap).drawBitmap(maybeAlphaSafe, 0.0f, 0.0f, null);
        return argbBitmap;
    }

    private static Config getAlphaSafeConfig(Bitmap inBitmap) {
        if (VERSION.SDK_INT < 26 || !Config.RGBA_F16.equals(inBitmap.getConfig())) {
            return Config.ARGB_8888;
        }
        return Config.RGBA_F16;
    }

    @Deprecated
    public static Bitmap roundedCorners(BitmapPool pool, Bitmap inBitmap, int width, int height, int roundingRadius) {
        return roundedCorners(pool, inBitmap, roundingRadius);
    }

    /* JADX INFO: finally extract failed */
    public static Bitmap roundedCorners(BitmapPool pool, Bitmap inBitmap, int roundingRadius) {
        Preconditions.checkArgument(roundingRadius > 0, "roundingRadius must be greater than 0.");
        Config safeConfig = getAlphaSafeConfig(inBitmap);
        Bitmap toTransform = getAlphaSafeBitmap(pool, inBitmap);
        Bitmap result = pool.get(toTransform.getWidth(), toTransform.getHeight(), safeConfig);
        result.setHasAlpha(true);
        BitmapShader shader = new BitmapShader(toTransform, TileMode.CLAMP, TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        RectF rect = new RectF(0.0f, 0.0f, (float) result.getWidth(), (float) result.getHeight());
        BITMAP_DRAWABLE_LOCK.lock();
        try {
            Canvas canvas = new Canvas(result);
            canvas.drawColor(0, Mode.CLEAR);
            canvas.drawRoundRect(rect, (float) roundingRadius, (float) roundingRadius, paint);
            clear(canvas);
            BITMAP_DRAWABLE_LOCK.unlock();
            if (!toTransform.equals(inBitmap)) {
                pool.put(toTransform);
            }
            return result;
        } catch (Throwable th) {
            BITMAP_DRAWABLE_LOCK.unlock();
            throw th;
        }
    }

    private static void clear(Canvas canvas) {
        canvas.setBitmap(null);
    }

    private static Config getNonNullConfig(Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Config.ARGB_8888;
    }

    private static void applyMatrix(Bitmap inBitmap, Bitmap targetBitmap, Matrix matrix) {
        BITMAP_DRAWABLE_LOCK.lock();
        try {
            Canvas canvas = new Canvas(targetBitmap);
            canvas.drawBitmap(inBitmap, matrix, DEFAULT_PAINT);
            clear(canvas);
        } finally {
            BITMAP_DRAWABLE_LOCK.unlock();
        }
    }

    static void initializeMatrixForRotation(int exifOrientation, Matrix matrix) {
        switch (exifOrientation) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                return;
            case 3:
                matrix.setRotate(180.0f);
                return;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 6:
                matrix.setRotate(90.0f);
                return;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 8:
                matrix.setRotate(-90.0f);
                return;
            default:
                return;
        }
    }
}
