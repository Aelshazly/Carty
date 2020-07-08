package okhttp3.internal.http;

import com.loopj.android.http.AsyncHttpClient;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okio.GzipSource;
import okio.Okio;
import okio.Source;
import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.protocol.HTTP;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, mo24952d2 = {"Lokhttp3/internal/http/BridgeInterceptor;", "Lokhttp3/Interceptor;", "cookieJar", "Lokhttp3/CookieJar;", "(Lokhttp3/CookieJar;)V", "cookieHeader", "", "cookies", "", "Lokhttp3/Cookie;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: BridgeInterceptor.kt */
public final class BridgeInterceptor implements Interceptor {
    private final CookieJar cookieJar;

    public BridgeInterceptor(CookieJar cookieJar2) {
        Intrinsics.checkParameterIsNotNull(cookieJar2, "cookieJar");
        this.cookieJar = cookieJar2;
    }

    public Response intercept(Chain chain) throws IOException {
        Chain chain2 = chain;
        Intrinsics.checkParameterIsNotNull(chain2, "chain");
        Request userRequest = chain.request();
        Builder requestBuilder = userRequest.newBuilder();
        RequestBody body = userRequest.body();
        String str = "Content-Type";
        String str2 = "Content-Length";
        if (body != null) {
            MediaType contentType = body.contentType();
            if (contentType != null) {
                requestBuilder.header(str, contentType.toString());
            }
            long contentLength = body.contentLength();
            String str3 = "Transfer-Encoding";
            if (contentLength != -1) {
                requestBuilder.header(str2, String.valueOf(contentLength));
                requestBuilder.removeHeader(str3);
            } else {
                requestBuilder.header(str3, HTTP.CHUNK_CODING);
                requestBuilder.removeHeader(str2);
            }
        }
        String str4 = "Host";
        if (userRequest.header(str4) == null) {
            requestBuilder.header(str4, Util.toHostHeader$default(userRequest.url(), false, 1, null));
        }
        String str5 = "Connection";
        if (userRequest.header(str5) == null) {
            requestBuilder.header(str5, HTTP.CONN_KEEP_ALIVE);
        }
        boolean transparentGzip = false;
        String str6 = "Accept-Encoding";
        String header = userRequest.header(str6);
        String str7 = AsyncHttpClient.ENCODING_GZIP;
        if (header == null && userRequest.header("Range") == null) {
            transparentGzip = true;
            requestBuilder.header(str6, str7);
        }
        List cookies = this.cookieJar.loadForRequest(userRequest.url());
        if (!cookies.isEmpty()) {
            requestBuilder.header(C0971SM.COOKIE, cookieHeader(cookies));
        }
        String str8 = "User-Agent";
        if (userRequest.header(str8) == null) {
            requestBuilder.header(str8, Version.userAgent);
        }
        Response networkResponse = chain2.proceed(requestBuilder.build());
        HttpHeaders.receiveHeaders(this.cookieJar, userRequest.url(), networkResponse.headers());
        Response.Builder responseBuilder = networkResponse.newBuilder().request(userRequest);
        if (transparentGzip) {
            String str9 = "Content-Encoding";
            if (StringsKt.equals(str7, Response.header$default(networkResponse, str9, null, 2, null), true) && HttpHeaders.promisesBody(networkResponse)) {
                ResponseBody responseBody = networkResponse.body();
                if (responseBody != null) {
                    GzipSource gzipSource = new GzipSource(responseBody.source());
                    responseBuilder.headers(networkResponse.headers().newBuilder().removeAll(str9).removeAll(str2).build());
                    responseBuilder.body(new RealResponseBody(Response.header$default(networkResponse, str, null, 2, null), -1, Okio.buffer((Source) gzipSource)));
                }
            }
        }
        return responseBuilder.build();
    }

    private final String cookieHeader(List<Cookie> cookies) {
        StringBuilder sb = new StringBuilder();
        StringBuilder $this$buildString = sb;
        int index = 0;
        for (Object item$iv : cookies) {
            int index$iv = index + 1;
            if (index < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Cookie cookie = (Cookie) item$iv;
            if (index > 0) {
                $this$buildString.append("; ");
            }
            $this$buildString.append(cookie.name());
            $this$buildString.append('=');
            $this$buildString.append(cookie.value());
            index = index$iv;
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
