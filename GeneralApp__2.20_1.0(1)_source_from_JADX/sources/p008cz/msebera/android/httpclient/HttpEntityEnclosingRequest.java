package p008cz.msebera.android.httpclient;

/* renamed from: cz.msebera.android.httpclient.HttpEntityEnclosingRequest */
public interface HttpEntityEnclosingRequest extends HttpRequest {
    boolean expectContinue();

    HttpEntity getEntity();

    void setEntity(HttpEntity httpEntity);
}
