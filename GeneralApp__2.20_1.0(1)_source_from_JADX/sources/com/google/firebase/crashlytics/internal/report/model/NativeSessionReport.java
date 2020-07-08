package com.google.firebase.crashlytics.internal.report.model;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.report.model.Report.Type;
import java.io.File;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class NativeSessionReport implements Report {
    private final File reportDirectory;

    public NativeSessionReport(File reportDirectory2) {
        this.reportDirectory = reportDirectory2;
    }

    public void remove() {
        File[] files;
        for (File file : getFiles()) {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Removing native report file at ");
            sb.append(file.getPath());
            logger.mo19679d(sb.toString());
            file.delete();
        }
        Logger logger2 = Logger.getLogger();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Removing native report directory at ");
        sb2.append(this.reportDirectory);
        logger2.mo19679d(sb2.toString());
        this.reportDirectory.delete();
    }

    public String getFileName() {
        return null;
    }

    public String getIdentifier() {
        return this.reportDirectory.getName();
    }

    public File getFile() {
        return null;
    }

    public File[] getFiles() {
        return this.reportDirectory.listFiles();
    }

    public Map<String, String> getCustomHeaders() {
        return null;
    }

    public Type getType() {
        return Type.NATIVE;
    }
}
