package p008cz.msebera.android.httpclient;

/* renamed from: cz.msebera.android.httpclient.StatusLine */
public interface StatusLine {
    ProtocolVersion getProtocolVersion();

    String getReasonPhrase();

    int getStatusCode();
}
