package com.loopj.android.http;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.message.BasicHeader;

class SimpleMultipartEntity implements HttpEntity {
    /* access modifiers changed from: private */
    public static final byte[] CR_LF = STR_CR_LF.getBytes();
    private static final String LOG_TAG = "SimpleMultipartEntity";
    private static final char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final String STR_CR_LF = "\r\n";
    /* access modifiers changed from: private */
    public static final byte[] TRANSFER_ENCODING_BINARY = "Content-Transfer-Encoding: binary\r\n".getBytes();
    private final String boundary;
    private final byte[] boundaryEnd;
    /* access modifiers changed from: private */
    public final byte[] boundaryLine;
    private long bytesWritten;
    private final List<FilePart> fileParts = new ArrayList();
    private boolean isRepeatable;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ResponseHandlerInterface progressHandler;
    private long totalSize;

    private class FilePart {
        public final File file;
        public final byte[] header;

        public FilePart(String key, File file2, String type, String customFileName) {
            this.header = createHeader(key, TextUtils.isEmpty(customFileName) ? file2.getName() : customFileName, type);
            this.file = file2;
        }

        public FilePart(String key, File file2, String type) {
            this.header = createHeader(key, file2.getName(), type);
            this.file = file2;
        }

        private byte[] createHeader(String key, String filename, String type) {
            ByteArrayOutputStream headerStream = new ByteArrayOutputStream();
            try {
                headerStream.write(SimpleMultipartEntity.this.boundaryLine);
                headerStream.write(SimpleMultipartEntity.this.createContentDisposition(key, filename));
                headerStream.write(SimpleMultipartEntity.this.createContentType(type));
                headerStream.write(SimpleMultipartEntity.TRANSFER_ENCODING_BINARY);
                headerStream.write(SimpleMultipartEntity.CR_LF);
            } catch (IOException e) {
                AsyncHttpClient.log.mo22450e(SimpleMultipartEntity.LOG_TAG, "createHeader ByteArrayOutputStream exception", e);
            }
            return headerStream.toByteArray();
        }

        public long getTotalLength() {
            return ((long) this.header.length) + this.file.length() + ((long) SimpleMultipartEntity.CR_LF.length);
        }

        public void writeTo(OutputStream out) throws IOException {
            out.write(this.header);
            SimpleMultipartEntity.this.updateProgress((long) this.header.length);
            FileInputStream inputStream = new FileInputStream(this.file);
            byte[] tmp = new byte[4096];
            while (true) {
                int read = inputStream.read(tmp);
                int bytesRead = read;
                if (read != -1) {
                    out.write(tmp, 0, bytesRead);
                    SimpleMultipartEntity.this.updateProgress((long) bytesRead);
                } else {
                    out.write(SimpleMultipartEntity.CR_LF);
                    SimpleMultipartEntity.this.updateProgress((long) SimpleMultipartEntity.CR_LF.length);
                    out.flush();
                    AsyncHttpClient.silentCloseInputStream(inputStream);
                    return;
                }
            }
        }
    }

    public SimpleMultipartEntity(ResponseHandlerInterface progressHandler2) {
        StringBuilder buf = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            char[] cArr = MULTIPART_CHARS;
            buf.append(cArr[rand.nextInt(cArr.length)]);
        }
        this.boundary = buf.toString();
        StringBuilder sb = new StringBuilder();
        String str = "--";
        sb.append(str);
        sb.append(this.boundary);
        String str2 = STR_CR_LF;
        sb.append(str2);
        this.boundaryLine = sb.toString().getBytes();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(this.boundary);
        sb2.append(str);
        sb2.append(str2);
        this.boundaryEnd = sb2.toString().getBytes();
        this.progressHandler = progressHandler2;
    }

    public void addPart(String key, String value, String contentType) {
        try {
            this.out.write(this.boundaryLine);
            this.out.write(createContentDisposition(key));
            this.out.write(createContentType(contentType));
            this.out.write(CR_LF);
            this.out.write(value.getBytes());
            this.out.write(CR_LF);
        } catch (IOException e) {
            AsyncHttpClient.log.mo22450e(LOG_TAG, "addPart ByteArrayOutputStream exception", e);
        }
    }

    public void addPartWithCharset(String key, String value, String charset) {
        if (charset == null) {
            charset = "UTF-8";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("text/plain; charset=");
        sb.append(charset);
        addPart(key, value, sb.toString());
    }

    public void addPart(String key, String value) {
        addPartWithCharset(key, value, null);
    }

    public void addPart(String key, File file) {
        addPart(key, file, (String) null);
    }

    public void addPart(String key, File file, String type) {
        this.fileParts.add(new FilePart(key, file, normalizeContentType(type)));
    }

    public void addPart(String key, File file, String type, String customFileName) {
        List<FilePart> list = this.fileParts;
        FilePart filePart = new FilePart(key, file, normalizeContentType(type), customFileName);
        list.add(filePart);
    }

    public void addPart(String key, String streamName, InputStream inputStream, String type) throws IOException {
        this.out.write(this.boundaryLine);
        this.out.write(createContentDisposition(key, streamName));
        this.out.write(createContentType(type));
        this.out.write(TRANSFER_ENCODING_BINARY);
        this.out.write(CR_LF);
        byte[] tmp = new byte[4096];
        while (true) {
            int read = inputStream.read(tmp);
            int l = read;
            if (read != -1) {
                this.out.write(tmp, 0, l);
            } else {
                this.out.write(CR_LF);
                this.out.flush();
                return;
            }
        }
    }

    private String normalizeContentType(String type) {
        return type == null ? "application/octet-stream" : type;
    }

    /* access modifiers changed from: private */
    public byte[] createContentType(String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("Content-Type: ");
        sb.append(normalizeContentType(type));
        sb.append(STR_CR_LF);
        return sb.toString().getBytes();
    }

    private byte[] createContentDisposition(String key) {
        StringBuilder sb = new StringBuilder();
        sb.append("Content-Disposition: form-data; name=\"");
        sb.append(key);
        sb.append("\"");
        sb.append(STR_CR_LF);
        return sb.toString().getBytes();
    }

    /* access modifiers changed from: private */
    public byte[] createContentDisposition(String key, String fileName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Content-Disposition: form-data; name=\"");
        sb.append(key);
        String str = "\"";
        sb.append(str);
        sb.append("; filename=\"");
        sb.append(fileName);
        sb.append(str);
        sb.append(STR_CR_LF);
        return sb.toString().getBytes();
    }

    /* access modifiers changed from: private */
    public void updateProgress(long count) {
        this.bytesWritten += count;
        this.progressHandler.sendProgressMessage(this.bytesWritten, this.totalSize);
    }

    public long getContentLength() {
        long contentLen = (long) this.out.size();
        for (FilePart filePart : this.fileParts) {
            long len = filePart.getTotalLength();
            if (len < 0) {
                return -1;
            }
            contentLen += len;
        }
        return contentLen + ((long) this.boundaryEnd.length);
    }

    public Header getContentType() {
        StringBuilder sb = new StringBuilder();
        sb.append("multipart/form-data; boundary=");
        sb.append(this.boundary);
        return new BasicHeader("Content-Type", sb.toString());
    }

    public boolean isChunked() {
        return false;
    }

    public void setIsRepeatable(boolean isRepeatable2) {
        this.isRepeatable = isRepeatable2;
    }

    public boolean isRepeatable() {
        return this.isRepeatable;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outstream) throws IOException {
        this.bytesWritten = 0;
        this.totalSize = (long) ((int) getContentLength());
        this.out.writeTo(outstream);
        updateProgress((long) this.out.size());
        for (FilePart filePart : this.fileParts) {
            filePart.writeTo(outstream);
        }
        outstream.write(this.boundaryEnd);
        updateProgress((long) this.boundaryEnd.length);
    }

    public Header getContentEncoding() {
        return null;
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public InputStream getContent() throws IOException, UnsupportedOperationException {
        throw new UnsupportedOperationException("getContent() is not supported. Use writeTo() instead.");
    }
}
