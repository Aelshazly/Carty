package p011me.dm7.barcodescanner.core;

import android.hardware.Camera;

/* renamed from: me.dm7.barcodescanner.core.CameraWrapper */
public class CameraWrapper {
    public final Camera mCamera;
    public final int mCameraId;

    private CameraWrapper(Camera camera, int cameraId) {
        if (camera != null) {
            this.mCamera = camera;
            this.mCameraId = cameraId;
            return;
        }
        throw new NullPointerException("Camera cannot be null");
    }

    public static CameraWrapper getWrapper(Camera camera, int cameraId) {
        if (camera == null) {
            return null;
        }
        return new CameraWrapper(camera, cameraId);
    }
}
