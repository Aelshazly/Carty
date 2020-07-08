package p008cz.msebera.android.httpclient.conn;

import java.io.IOException;

/* renamed from: cz.msebera.android.httpclient.conn.ConnectionReleaseTrigger */
public interface ConnectionReleaseTrigger {
    void abortConnection() throws IOException;

    void releaseConnection() throws IOException;
}
