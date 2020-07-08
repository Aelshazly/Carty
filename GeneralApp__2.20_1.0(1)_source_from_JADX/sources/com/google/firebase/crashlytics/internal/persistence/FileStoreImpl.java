package com.google.firebase.crashlytics.internal.persistence;

import android.content.Context;
import android.os.Environment;
import com.google.firebase.crashlytics.internal.Logger;
import java.io.File;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class FileStoreImpl implements FileStore {
    public static final String FILES_PATH = ".com.google.firebase.crashlytics";
    private final Context context;

    public FileStoreImpl(Context context2) {
        this.context = context2;
    }

    public File getFilesDir() {
        return prepare(new File(this.context.getFilesDir(), FILES_PATH));
    }

    public String getFilesDirPath() {
        return new File(this.context.getFilesDir(), FILES_PATH).getPath();
    }

    /* access modifiers changed from: 0000 */
    public File prepare(File file) {
        if (file == null) {
            Logger.getLogger().mo19679d("Null File");
        } else if (file.exists() || file.mkdirs()) {
            return file;
        } else {
            Logger.getLogger().mo19689w("Couldn't create file");
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public boolean isExternalStorageAvailable() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return true;
        }
        Logger.getLogger().mo19689w("External Storage is not mounted and/or writable\nHave you declared android.permission.WRITE_EXTERNAL_STORAGE in the manifest?");
        return false;
    }
}
