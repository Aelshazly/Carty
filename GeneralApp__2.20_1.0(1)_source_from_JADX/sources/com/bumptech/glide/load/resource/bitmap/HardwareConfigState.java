package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import java.io.File;

final class HardwareConfigState {
    private static final File FD_SIZE_LIST = new File("/proc/self/fd");
    private static final int MAXIMUM_FDS_FOR_HARDWARE_CONFIGS = 700;
    private static final int MINIMUM_DECODES_BETWEEN_FD_CHECKS = 50;
    private static final int MIN_HARDWARE_DIMENSION = 128;
    private static volatile HardwareConfigState instance;
    private volatile int decodesSinceLastFdCheck;
    private volatile boolean isHardwareConfigAllowed = true;

    static HardwareConfigState getInstance() {
        if (instance == null) {
            synchronized (HardwareConfigState.class) {
                if (instance == null) {
                    instance = new HardwareConfigState();
                }
            }
        }
        return instance;
    }

    private HardwareConfigState() {
    }

    /* access modifiers changed from: 0000 */
    public boolean setHardwareConfigIfAllowed(int targetWidth, int targetHeight, Options optionsWithScaling, DecodeFormat decodeFormat, boolean isHardwareConfigAllowed2, boolean isExifOrientationRequired) {
        if (!isHardwareConfigAllowed2 || VERSION.SDK_INT < 26 || isExifOrientationRequired) {
            return false;
        }
        boolean result = targetWidth >= 128 && targetHeight >= 128 && isFdSizeBelowHardwareLimit();
        if (result) {
            optionsWithScaling.inPreferredConfig = Config.HARDWARE;
            optionsWithScaling.inMutable = false;
        }
        return result;
    }

    private synchronized boolean isFdSizeBelowHardwareLimit() {
        int i = this.decodesSinceLastFdCheck + 1;
        this.decodesSinceLastFdCheck = i;
        if (i >= 50) {
            boolean z = false;
            this.decodesSinceLastFdCheck = 0;
            int currentFds = FD_SIZE_LIST.list().length;
            if (currentFds < MAXIMUM_FDS_FOR_HARDWARE_CONFIGS) {
                z = true;
            }
            this.isHardwareConfigAllowed = z;
            if (!this.isHardwareConfigAllowed && Log.isLoggable("Downsampler", 5)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors ");
                sb.append(currentFds);
                sb.append(", limit ");
                sb.append(MAXIMUM_FDS_FOR_HARDWARE_CONFIGS);
                Log.w("Downsampler", sb.toString());
            }
        }
        return this.isHardwareConfigAllowed;
    }
}
