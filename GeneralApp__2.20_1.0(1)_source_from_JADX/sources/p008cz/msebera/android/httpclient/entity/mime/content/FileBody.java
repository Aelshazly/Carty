package p008cz.msebera.android.httpclient.entity.mime.content;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import p008cz.msebera.android.httpclient.entity.ContentType;
import p008cz.msebera.android.httpclient.entity.mime.MIME;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.entity.mime.content.FileBody */
public class FileBody extends AbstractContentBody {
    private final File file;
    private final String filename;

    @Deprecated
    public FileBody(File file2, String filename2, String mimeType, String charset) {
        this(file2, ContentType.create(mimeType, charset), filename2);
    }

    @Deprecated
    public FileBody(File file2, String mimeType, String charset) {
        this(file2, null, mimeType, charset);
    }

    @Deprecated
    public FileBody(File file2, String mimeType) {
        this(file2, ContentType.create(mimeType), (String) null);
    }

    public FileBody(File file2) {
        this(file2, ContentType.DEFAULT_BINARY, file2 != null ? file2.getName() : null);
    }

    public FileBody(File file2, ContentType contentType, String filename2) {
        super(contentType);
        Args.notNull(file2, "File");
        this.file = file2;
        this.filename = filename2;
    }

    public FileBody(File file2, ContentType contentType) {
        this(file2, contentType, (String) null);
    }

    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public void writeTo(OutputStream out) throws IOException {
        Args.notNull(out, "Output stream");
        InputStream in = new FileInputStream(this.file);
        try {
            byte[] tmp = new byte[4096];
            while (true) {
                int read = in.read(tmp);
                int l = read;
                if (read != -1) {
                    out.write(tmp, 0, l);
                } else {
                    out.flush();
                    return;
                }
            }
        } finally {
            in.close();
        }
    }

    public String getTransferEncoding() {
        return MIME.ENC_BINARY;
    }

    public long getContentLength() {
        return this.file.length();
    }

    public String getFilename() {
        return this.filename;
    }

    public File getFile() {
        return this.file;
    }
}
