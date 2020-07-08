package p008cz.msebera.android.httpclient.protocol;

import java.util.List;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.protocol.HttpResponseInterceptorList */
public interface HttpResponseInterceptorList {
    void addResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor);

    void addResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor, int i);

    void clearResponseInterceptors();

    HttpResponseInterceptor getResponseInterceptor(int i);

    int getResponseInterceptorCount();

    void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> cls);

    void setInterceptors(List<?> list);
}
