package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import p008cz.msebera.android.httpclient.client.cache.InputLimit;
import p008cz.msebera.android.httpclient.client.cache.Resource;
import p008cz.msebera.android.httpclient.client.cache.ResourceFactory;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.FileResourceFactory */
public class FileResourceFactory implements ResourceFactory {
    private final File cacheDir;
    private final BasicIdGenerator idgen = new BasicIdGenerator();

    public FileResourceFactory(File cacheDir2) {
        this.cacheDir = cacheDir2;
    }

    private File generateUniqueCacheFile(String requestId) {
        StringBuilder buffer = new StringBuilder();
        this.idgen.generate(buffer);
        buffer.append('.');
        int len = Math.min(requestId.length(), 100);
        for (int i = 0; i < len; i++) {
            char ch = requestId.charAt(i);
            if (Character.isLetterOrDigit(ch) || ch == '.') {
                buffer.append(ch);
            } else {
                buffer.append('-');
            }
        }
        return new File(this.cacheDir, buffer.toString());
    }

    /* JADX INFO: finally extract failed */
    public Resource generate(String requestId, InputStream instream, InputLimit limit) throws IOException {
        File file = generateUniqueCacheFile(requestId);
        FileOutputStream outstream = new FileOutputStream(file);
        try {
            byte[] buf = new byte[2048];
            long total = 0;
            while (true) {
                int read = instream.read(buf);
                int l = read;
                if (read == -1) {
                    break;
                }
                outstream.write(buf, 0, l);
                total += (long) l;
                if (limit != null && total > limit.getValue()) {
                    limit.reached();
                    break;
                }
            }
            outstream.close();
            return new FileResource(file);
        } catch (Throwable th) {
            outstream.close();
            throw th;
        }
    }

    public Resource copy(String requestId, Resource resource) throws IOException {
        File file = generateUniqueCacheFile(requestId);
        if (resource instanceof FileResource) {
            IOUtils.copyFile(((FileResource) resource).getFile(), file);
        } else {
            IOUtils.copyAndClose(resource.getInputStream(), new FileOutputStream(file));
        }
        return new FileResource(file);
    }
}
