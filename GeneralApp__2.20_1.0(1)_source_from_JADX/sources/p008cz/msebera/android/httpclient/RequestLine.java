package p008cz.msebera.android.httpclient;

/* renamed from: cz.msebera.android.httpclient.RequestLine */
public interface RequestLine {
    String getMethod();

    ProtocolVersion getProtocolVersion();

    String getUri();
}
