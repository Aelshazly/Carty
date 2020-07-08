package p008cz.msebera.android.httpclient.conn.ssl;

import javax.net.ssl.SSLException;

/* renamed from: cz.msebera.android.httpclient.conn.ssl.BrowserCompatHostnameVerifier */
public class BrowserCompatHostnameVerifier extends AbstractVerifier {
    public final void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
        verify(host, cns, subjectAlts, false);
    }

    /* access modifiers changed from: 0000 */
    public boolean validCountryWildcard(String cn) {
        return true;
    }

    public final String toString() {
        return "BROWSER_COMPATIBLE";
    }
}
