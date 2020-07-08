package com.loopj.android.http;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;

public abstract class FileAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "FileAsyncHttpRH";
    protected final boolean append;
    protected final File file;
    protected File frontendFile;
    protected final boolean renameIfExists;

    public abstract void onFailure(int i, Header[] headerArr, Throwable th, File file2);

    public abstract void onSuccess(int i, Header[] headerArr, File file2);

    public FileAsyncHttpResponseHandler(File file2) {
        this(file2, false);
    }

    public FileAsyncHttpResponseHandler(File file2, boolean append2) {
        this(file2, append2, false);
    }

    public FileAsyncHttpResponseHandler(File file2, boolean append2, boolean renameTargetFileIfExists) {
        this(file2, append2, renameTargetFileIfExists, false);
    }

    public FileAsyncHttpResponseHandler(File file2, boolean append2, boolean renameTargetFileIfExists, boolean usePoolThread) {
        super(usePoolThread);
        Utils.asserts(file2 != null, "File passed into FileAsyncHttpResponseHandler constructor must not be null");
        if (!file2.isDirectory() && !file2.getParentFile().isDirectory()) {
            Utils.asserts(file2.getParentFile().mkdirs(), "Cannot create parent directories for requested File location");
        }
        if (file2.isDirectory() && !file2.mkdirs()) {
            AsyncHttpClient.log.mo22447d(LOG_TAG, "Cannot create directories for requested Directory location, might not be a problem");
        }
        this.file = file2;
        this.append = append2;
        this.renameIfExists = renameTargetFileIfExists;
    }

    public FileAsyncHttpResponseHandler(Context context) {
        this.file = getTemporaryFile(context);
        this.append = false;
        this.renameIfExists = false;
    }

    public boolean deleteTargetFile() {
        return getTargetFile() != null && getTargetFile().delete();
    }

    /* access modifiers changed from: protected */
    public File getTemporaryFile(Context context) {
        Utils.asserts(context != null, "Tried creating temporary file without having Context");
        try {
            return File.createTempFile("temp_", "_handled", context.getCacheDir());
        } catch (IOException e) {
            AsyncHttpClient.log.mo22450e(LOG_TAG, "Cannot create temporary file", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public File getOriginalFile() {
        Utils.asserts(this.file != null, "Target file is null, fatal!");
        return this.file;
    }

    public File getTargetFile() {
        if (this.frontendFile == null) {
            this.frontendFile = getOriginalFile().isDirectory() ? getTargetFileByParsingURL() : getOriginalFile();
        }
        return this.frontendFile;
    }

    /* access modifiers changed from: protected */
    public File getTargetFileByParsingURL() {
        String format;
        Utils.asserts(getOriginalFile().isDirectory(), "Target file is not a directory, cannot proceed");
        Utils.asserts(getRequestURI() != null, "RequestURI is null, cannot proceed");
        String requestURL = getRequestURI().toString();
        String filename = requestURL.substring(requestURL.lastIndexOf(47) + 1, requestURL.length());
        File targetFileRtn = new File(getOriginalFile(), filename);
        if (!targetFileRtn.exists() || !this.renameIfExists) {
            return targetFileRtn;
        }
        String str = " (%d)";
        if (!filename.contains(".")) {
            StringBuilder sb = new StringBuilder();
            sb.append(filename);
            sb.append(str);
            format = sb.toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(filename.substring(0, filename.lastIndexOf(46)));
            sb2.append(str);
            sb2.append(filename.substring(filename.lastIndexOf(46), filename.length()));
            format = sb2.toString();
        }
        int index = 0;
        while (true) {
            File targetFileRtn2 = new File(getOriginalFile(), String.format(format, new Object[]{Integer.valueOf(index)}));
            if (!targetFileRtn2.exists()) {
                return targetFileRtn2;
            }
            index++;
        }
    }

    public final void onFailure(int statusCode, Header[] headers, byte[] responseBytes, Throwable throwable) {
        onFailure(statusCode, headers, throwable, getTargetFile());
    }

    public final void onSuccess(int statusCode, Header[] headers, byte[] responseBytes) {
        onSuccess(statusCode, headers, getTargetFile());
    }

    /* access modifiers changed from: protected */
    public byte[] getResponseData(HttpEntity entity) throws IOException {
        if (entity != null) {
            InputStream instream = entity.getContent();
            long contentLength = entity.getContentLength();
            FileOutputStream buffer = new FileOutputStream(getTargetFile(), this.append);
            if (instream != null) {
                try {
                    byte[] tmp = new byte[4096];
                    int count = 0;
                    while (true) {
                        int read = instream.read(tmp);
                        int l = read;
                        if (read != -1 && !Thread.currentThread().isInterrupted()) {
                            count += l;
                            buffer.write(tmp, 0, l);
                            sendProgressMessage((long) count, contentLength);
                        }
                    }
                } finally {
                    AsyncHttpClient.silentCloseInputStream(instream);
                    buffer.flush();
                    AsyncHttpClient.silentCloseOutputStream(buffer);
                }
            }
        }
        return null;
    }
}
