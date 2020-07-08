package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import p008cz.msebera.android.httpclient.client.cache.Resource;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.FileResource */
public class FileResource implements Resource {
    private static final long serialVersionUID = 4132244415919043397L;
    private volatile boolean disposed = false;
    private final File file;

    public FileResource(File file2) {
        this.file = file2;
    }

    /* access modifiers changed from: 0000 */
    public synchronized File getFile() {
        return this.file;
    }

    public synchronized InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public synchronized long length() {
        return this.file.length();
    }

    public synchronized void dispose() {
        if (!this.disposed) {
            this.disposed = true;
            this.file.delete();
        }
    }
}
