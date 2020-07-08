package com.google.firebase.crashlytics.internal.proto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class ClsFileOutputStream extends FileOutputStream {
    public static final String IN_PROGRESS_SESSION_FILE_EXTENSION = ".cls_temp";
    public static final String SESSION_FILE_EXTENSION = ".cls";
    public static final FilenameFilter TEMP_FILENAME_FILTER = new FilenameFilter() {
        public boolean accept(File dir, String filename) {
            return filename.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION);
        }
    };
    private boolean closed;
    private File complete;
    private File inProgress;
    private final String root;

    public ClsFileOutputStream(String dir, String fileRoot) throws FileNotFoundException {
        this(new File(dir), fileRoot);
    }

    public ClsFileOutputStream(File dir, String fileRoot) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(fileRoot);
        String str = IN_PROGRESS_SESSION_FILE_EXTENSION;
        sb.append(str);
        super(new File(dir, sb.toString()));
        this.closed = false;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(dir);
        sb2.append(File.separator);
        sb2.append(fileRoot);
        this.root = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.root);
        sb3.append(str);
        this.inProgress = new File(sb3.toString());
    }

    public synchronized void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            super.flush();
            super.close();
            StringBuilder sb = new StringBuilder();
            sb.append(this.root);
            sb.append(SESSION_FILE_EXTENSION);
            File complete2 = new File(sb.toString());
            if (this.inProgress.renameTo(complete2)) {
                this.inProgress = null;
                this.complete = complete2;
                return;
            }
            String reason = "";
            if (complete2.exists()) {
                reason = " (target already exists)";
            } else if (!this.inProgress.exists()) {
                reason = " (source does not exist)";
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Could not rename temp file: ");
            sb2.append(this.inProgress);
            sb2.append(" -> ");
            sb2.append(complete2);
            sb2.append(reason);
            throw new IOException(sb2.toString());
        }
    }

    public void closeInProgressStream() throws IOException {
        if (!this.closed) {
            this.closed = true;
            super.flush();
            super.close();
        }
    }

    public File getCompleteFile() {
        return this.complete;
    }

    public File getInProgressFile() {
        return this.inProgress;
    }
}
