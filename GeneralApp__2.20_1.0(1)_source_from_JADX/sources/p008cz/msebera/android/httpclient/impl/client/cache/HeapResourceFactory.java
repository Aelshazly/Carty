package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import p008cz.msebera.android.httpclient.client.cache.InputLimit;
import p008cz.msebera.android.httpclient.client.cache.Resource;
import p008cz.msebera.android.httpclient.client.cache.ResourceFactory;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.HeapResourceFactory */
public class HeapResourceFactory implements ResourceFactory {
    public Resource generate(String requestId, InputStream instream, InputLimit limit) throws IOException {
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
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
        return createResource(outstream.toByteArray());
    }

    public Resource copy(String requestId, Resource resource) throws IOException {
        byte[] body;
        if (resource instanceof HeapResource) {
            body = ((HeapResource) resource).getByteArray();
        } else {
            ByteArrayOutputStream outstream = new ByteArrayOutputStream();
            IOUtils.copyAndClose(resource.getInputStream(), outstream);
            body = outstream.toByteArray();
        }
        return createResource(body);
    }

    /* access modifiers changed from: 0000 */
    public Resource createResource(byte[] buf) {
        return new HeapResource(buf);
    }
}
