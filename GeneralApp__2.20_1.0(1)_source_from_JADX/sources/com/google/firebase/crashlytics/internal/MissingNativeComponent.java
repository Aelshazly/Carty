package com.google.firebase.crashlytics.internal;

import java.io.File;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public final class MissingNativeComponent implements CrashlyticsNativeComponent {
    private static final NativeSessionFileProvider MISSING_NATIVE_SESSION_FILE_PROVIDER = new MissingNativeSessionFileProvider();

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class MissingNativeSessionFileProvider implements NativeSessionFileProvider {
        private MissingNativeSessionFileProvider() {
        }

        public File getMinidumpFile() {
            return null;
        }

        public File getBinaryImagesFile() {
            return null;
        }

        public File getMetadataFile() {
            return null;
        }

        public File getSessionFile() {
            return null;
        }

        public File getAppFile() {
            return null;
        }

        public File getDeviceFile() {
            return null;
        }

        public File getOsFile() {
            return null;
        }
    }

    public boolean hasCrashDataForSession(String sessionId) {
        return false;
    }

    public boolean openSession(String sessionId) {
        return true;
    }

    public boolean finalizeSession(String sessionId) {
        return true;
    }

    public NativeSessionFileProvider getSessionFileProvider(String sessionId) {
        return MISSING_NATIVE_SESSION_FILE_PROVIDER;
    }

    public void writeBeginSession(String sessionId, String generator, long startedAtSeconds) {
    }

    public void writeSessionApp(String sessionId, String appIdentifier, String versionCode, String versionName, String installUuid, int deliveryMechanism, String unityVersion) {
    }

    public void writeSessionOs(String sessionId, String osRelease, String osCodeName, boolean isRooted) {
    }

    public void writeSessionDevice(String sessionId, int arch, String model, int availableProcessors, long totalRam, long diskSpace, boolean isEmulator, int state, String manufacturer, String modelClass) {
    }
}
