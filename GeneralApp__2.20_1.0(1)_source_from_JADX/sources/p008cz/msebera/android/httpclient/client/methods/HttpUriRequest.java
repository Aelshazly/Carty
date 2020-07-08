package p008cz.msebera.android.httpclient.client.methods;

import java.net.URI;
import p008cz.msebera.android.httpclient.HttpRequest;

/* renamed from: cz.msebera.android.httpclient.client.methods.HttpUriRequest */
public interface HttpUriRequest extends HttpRequest {
    void abort() throws UnsupportedOperationException;

    String getMethod();

    URI getURI();

    boolean isAborted();
}
