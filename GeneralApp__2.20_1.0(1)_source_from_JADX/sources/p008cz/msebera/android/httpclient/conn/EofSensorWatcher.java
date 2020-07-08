package p008cz.msebera.android.httpclient.conn;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: cz.msebera.android.httpclient.conn.EofSensorWatcher */
public interface EofSensorWatcher {
    boolean eofDetected(InputStream inputStream) throws IOException;

    boolean streamAbort(InputStream inputStream) throws IOException;

    boolean streamClosed(InputStream inputStream) throws IOException;
}
