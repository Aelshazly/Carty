package com.google.firebase.crashlytics.internal.report.model;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.report.model.Report.Type;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class SessionReport implements Report {
    private final Map<String, String> customHeaders;
    private final File file;
    private final File[] files;

    public SessionReport(File file2) {
        this(file2, Collections.emptyMap());
    }

    public SessionReport(File file2, Map<String, String> customHeaders2) {
        this.file = file2;
        this.files = new File[]{file2};
        this.customHeaders = new HashMap(customHeaders2);
    }

    public File getFile() {
        return this.file;
    }

    public File[] getFiles() {
        return this.files;
    }

    public String getFileName() {
        return getFile().getName();
    }

    public String getIdentifier() {
        String fileName = getFileName();
        return fileName.substring(0, fileName.lastIndexOf(46));
    }

    public Map<String, String> getCustomHeaders() {
        return Collections.unmodifiableMap(this.customHeaders);
    }

    public void remove() {
        Logger logger = Logger.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append("Removing report at ");
        sb.append(this.file.getPath());
        logger.mo19679d(sb.toString());
        this.file.delete();
    }

    public Type getType() {
        return Type.JAVA;
    }
}
