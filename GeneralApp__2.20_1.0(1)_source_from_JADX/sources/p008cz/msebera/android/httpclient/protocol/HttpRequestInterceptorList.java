package p008cz.msebera.android.httpclient.protocol;

import java.util.List;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.protocol.HttpRequestInterceptorList */
public interface HttpRequestInterceptorList {
    void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor);

    void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor, int i);

    void clearRequestInterceptors();

    HttpRequestInterceptor getRequestInterceptor(int i);

    int getRequestInterceptorCount();

    void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> cls);

    void setInterceptors(List<?> list);
}
