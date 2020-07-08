package p008cz.msebera.android.httpclient.conn.ssl;

import javax.net.ssl.SSLException;

/* renamed from: cz.msebera.android.httpclient.conn.ssl.StrictHostnameVerifier */
public class StrictHostnameVerifier extends AbstractVerifier {
    public final void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
        verify(host, cns, subjectAlts, true);
    }

    public final String toString() {
        return "STRICT";
    }
}
