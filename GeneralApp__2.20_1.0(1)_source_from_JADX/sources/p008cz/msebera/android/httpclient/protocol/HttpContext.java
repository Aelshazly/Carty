package p008cz.msebera.android.httpclient.protocol;

/* renamed from: cz.msebera.android.httpclient.protocol.HttpContext */
public interface HttpContext {
    public static final String RESERVED_PREFIX = "http.";

    Object getAttribute(String str);

    Object removeAttribute(String str);

    void setAttribute(String str, Object obj);
}
