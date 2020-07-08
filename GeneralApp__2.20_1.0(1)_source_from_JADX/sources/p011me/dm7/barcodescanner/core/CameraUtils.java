package p011me.dm7.barcodescanner.core;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import java.util.List;

/* renamed from: me.dm7.barcodescanner.core.CameraUtils */
public class CameraUtils {
    public static Camera getCameraInstance() {
        return getCameraInstance(getDefaultCameraId());
    }

    public static int getDefaultCameraId() {
        int numberOfCameras = Camera.getNumberOfCameras();
        CameraInfo cameraInfo = new CameraInfo();
        int defaultCameraId = -1;
        for (int i = 0; i < numberOfCameras; i++) {
            defaultCameraId = i;
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 0) {
                return i;
            }
        }
        return defaultCameraId;
    }

    public static Camera getCameraInstance(int cameraId) {
        if (cameraId != -1) {
            return Camera.open(cameraId);
        }
        try {
            return Camera.open();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isFlashSupported(Camera camera) {
        if (camera == null) {
            return false;
        }
        Parameters parameters = camera.getParameters();
        if (parameters.getFlashMode() == null) {
            return false;
        }
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (supportedFlashModes == null || supportedFlashModes.isEmpty() || (supportedFlashModes.size() == 1 && ((String) supportedFlashModes.get(0)).equals("off"))) {
            return false;
        }
        return true;
    }
}
