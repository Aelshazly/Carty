package p008cz.msebera.android.httpclient.conn.ssl;

import java.security.cert.X509Certificate;
import java.util.Arrays;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.conn.ssl.PrivateKeyDetails */
public final class PrivateKeyDetails {
    private final X509Certificate[] certChain;
    private final String type;

    public PrivateKeyDetails(String type2, X509Certificate[] certChain2) {
        this.type = (String) Args.notNull(type2, "Private key type");
        this.certChain = certChain2;
    }

    public String getType() {
        return this.type;
    }

    public X509Certificate[] getCertChain() {
        return this.certChain;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.type);
        sb.append(':');
        sb.append(Arrays.toString(this.certChain));
        return sb.toString();
    }
}
