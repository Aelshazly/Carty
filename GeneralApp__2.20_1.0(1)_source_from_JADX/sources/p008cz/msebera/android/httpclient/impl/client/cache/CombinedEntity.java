package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import p008cz.msebera.android.httpclient.client.cache.Resource;
import p008cz.msebera.android.httpclient.entity.AbstractHttpEntity;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.CombinedEntity */
class CombinedEntity extends AbstractHttpEntity {
    private final InputStream combinedStream;
    private final Resource resource;

    /* renamed from: cz.msebera.android.httpclient.impl.client.cache.CombinedEntity$ResourceStream */
    class ResourceStream extends FilterInputStream {
        protected ResourceStream(InputStream in) {
            super(in);
        }

        public void close() throws IOException {
            try {
                super.close();
            } finally {
                CombinedEntity.this.dispose();
            }
        }
    }

    CombinedEntity(Resource resource2, InputStream instream) throws IOException {
        this.resource = resource2;
        this.combinedStream = new SequenceInputStream(new ResourceStream(resource2.getInputStream()), instream);
    }

    public long getContentLength() {
        return -1;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        return true;
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        return this.combinedStream;
    }

    public void writeTo(OutputStream outstream) throws IOException {
        Args.notNull(outstream, "Output stream");
        InputStream instream = getContent();
        try {
            byte[] tmp = new byte[2048];
            while (true) {
                int read = instream.read(tmp);
                int l = read;
                if (read != -1) {
                    outstream.write(tmp, 0, l);
                } else {
                    return;
                }
            }
        } finally {
            instream.close();
        }
    }

    /* access modifiers changed from: private */
    public void dispose() {
        this.resource.dispose();
    }
}
