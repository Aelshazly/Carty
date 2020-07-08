package p008cz.msebera.android.httpclient;

/* renamed from: cz.msebera.android.httpclient.HttpRequestFactory */
public interface HttpRequestFactory {
    HttpRequest newHttpRequest(RequestLine requestLine) throws MethodNotSupportedException;

    HttpRequest newHttpRequest(String str, String str2) throws MethodNotSupportedException;
}
