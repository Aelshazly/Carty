package p008cz.msebera.android.httpclient.impl.client.cache;

import java.util.Map;
import p008cz.msebera.android.httpclient.client.cache.HeaderConstants;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.ConditionalRequestBuilder */
class ConditionalRequestBuilder {
    ConditionalRequestBuilder() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0067, code lost:
        r9 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper buildConditionalRequest(p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper r17, p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry r18) throws p008cz.msebera.android.httpclient.ProtocolException {
        /*
            r16 = this;
            r0 = r18
            cz.msebera.android.httpclient.HttpRequest r1 = r17.getOriginal()
            cz.msebera.android.httpclient.client.methods.HttpRequestWrapper r1 = p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper.wrap(r1)
            cz.msebera.android.httpclient.Header[] r2 = r17.getAllHeaders()
            r1.setHeaders(r2)
            java.lang.String r2 = "ETag"
            cz.msebera.android.httpclient.Header r2 = r0.getFirstHeader(r2)
            if (r2 == 0) goto L_0x0022
            java.lang.String r3 = r2.getValue()
            java.lang.String r4 = "If-None-Match"
            r1.setHeader(r4, r3)
        L_0x0022:
            java.lang.String r3 = "Last-Modified"
            cz.msebera.android.httpclient.Header r3 = r0.getFirstHeader(r3)
            if (r3 == 0) goto L_0x0033
            java.lang.String r4 = r3.getValue()
            java.lang.String r5 = "If-Modified-Since"
            r1.setHeader(r5, r4)
        L_0x0033:
            r4 = 0
            java.lang.String r5 = "Cache-Control"
            cz.msebera.android.httpclient.Header[] r6 = r0.getHeaders(r5)
            int r7 = r6.length
            r9 = r4
            r4 = 0
        L_0x003d:
            if (r4 >= r7) goto L_0x006c
            r10 = r6[r4]
            cz.msebera.android.httpclient.HeaderElement[] r11 = r10.getElements()
            int r12 = r11.length
            r13 = 0
        L_0x0047:
            if (r13 >= r12) goto L_0x0069
            r14 = r11[r13]
            java.lang.String r15 = r14.getName()
            java.lang.String r8 = "must-revalidate"
            boolean r8 = r8.equalsIgnoreCase(r15)
            if (r8 != 0) goto L_0x0067
            java.lang.String r8 = r14.getName()
            java.lang.String r15 = "proxy-revalidate"
            boolean r8 = r15.equalsIgnoreCase(r8)
            if (r8 == 0) goto L_0x0064
            goto L_0x0067
        L_0x0064:
            int r13 = r13 + 1
            goto L_0x0047
        L_0x0067:
            r8 = 1
            r9 = r8
        L_0x0069:
            int r4 = r4 + 1
            goto L_0x003d
        L_0x006c:
            if (r9 == 0) goto L_0x0073
            java.lang.String r4 = "max-age=0"
            r1.addHeader(r5, r4)
        L_0x0073:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.client.cache.ConditionalRequestBuilder.buildConditionalRequest(cz.msebera.android.httpclient.client.methods.HttpRequestWrapper, cz.msebera.android.httpclient.client.cache.HttpCacheEntry):cz.msebera.android.httpclient.client.methods.HttpRequestWrapper");
    }

    public HttpRequestWrapper buildConditionalRequestFromVariants(HttpRequestWrapper request, Map<String, Variant> variants) {
        HttpRequestWrapper newRequest = HttpRequestWrapper.wrap(request.getOriginal());
        newRequest.setHeaders(request.getAllHeaders());
        StringBuilder etags = new StringBuilder();
        boolean first = true;
        for (String etag : variants.keySet()) {
            if (!first) {
                etags.append(",");
            }
            first = false;
            etags.append(etag);
        }
        newRequest.setHeader("If-None-Match", etags.toString());
        return newRequest;
    }

    public HttpRequestWrapper buildUnconditionalRequest(HttpRequestWrapper request, HttpCacheEntry entry) {
        HttpRequestWrapper newRequest = HttpRequestWrapper.wrap(request.getOriginal());
        newRequest.setHeaders(request.getAllHeaders());
        String str = HeaderConstants.CACHE_CONTROL_NO_CACHE;
        newRequest.addHeader("Cache-Control", str);
        newRequest.addHeader("Pragma", str);
        newRequest.removeHeaders("If-Range");
        newRequest.removeHeaders("If-Match");
        newRequest.removeHeaders("If-None-Match");
        newRequest.removeHeaders("If-Unmodified-Since");
        newRequest.removeHeaders("If-Modified-Since");
        return newRequest;
    }
}
