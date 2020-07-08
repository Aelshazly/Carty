package p008cz.msebera.android.httpclient.entity;

import android.support.p000v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.entity.InputStreamEntity */
public class InputStreamEntity extends AbstractHttpEntity {
    private final InputStream content;
    private final long length;

    public InputStreamEntity(InputStream instream) {
        this(instream, -1);
    }

    public InputStreamEntity(InputStream instream, long length2) {
        this(instream, length2, null);
    }

    public InputStreamEntity(InputStream instream, ContentType contentType) {
        this(instream, -1, contentType);
    }

    public InputStreamEntity(InputStream instream, long length2, ContentType contentType) {
        this.content = (InputStream) Args.notNull(instream, "Source input stream");
        this.length = length2;
        if (contentType != null) {
            setContentType(contentType.toString());
        }
    }

    public boolean isRepeatable() {
        return false;
    }

    public long getContentLength() {
        return this.length;
    }

    public InputStream getContent() throws IOException {
        return this.content;
    }

    public void writeTo(OutputStream outstream) throws IOException {
        Args.notNull(outstream, "Output stream");
        InputStream instream = this.content;
        try {
            byte[] buffer = new byte[4096];
            if (this.length >= 0) {
                long remaining = this.length;
                while (true) {
                    if (remaining <= 0) {
                        break;
                    }
                    int l = instream.read(buffer, 0, (int) Math.min(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, remaining));
                    if (l == -1) {
                        break;
                    }
                    outstream.write(buffer, 0, l);
                    remaining -= (long) l;
                }
            } else {
                while (true) {
                    int read = instream.read(buffer);
                    int l2 = read;
                    if (read == -1) {
                        break;
                    }
                    outstream.write(buffer, 0, l2);
                }
            }
        } finally {
            instream.close();
        }
    }

    public boolean isStreaming() {
        return true;
    }
}
