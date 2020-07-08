package p008cz.msebera.android.httpclient.conn.ssl;

/* renamed from: cz.msebera.android.httpclient.conn.ssl.AllowAllHostnameVerifier */
public class AllowAllHostnameVerifier extends AbstractVerifier {
    public final void verify(String host, String[] cns, String[] subjectAlts) {
    }

    public final String toString() {
        return "ALLOW_ALL";
    }
}
