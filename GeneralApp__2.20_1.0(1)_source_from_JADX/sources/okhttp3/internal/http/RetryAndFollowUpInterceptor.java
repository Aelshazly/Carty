package okhttp3.internal.http;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Transmitter;
import p008cz.msebera.android.httpclient.HttpHeaders;
import p008cz.msebera.android.httpclient.HttpStatus;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0002J(\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0006H\u0002J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0006H\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, mo24952d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor;", "Lokhttp3/Interceptor;", "client", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "buildRedirectRequest", "Lokhttp3/Request;", "userResponse", "Lokhttp3/Response;", "method", "", "followUpRequest", "route", "Lokhttp3/Route;", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "isRecoverable", "", "e", "Ljava/io/IOException;", "requestSendStarted", "recover", "transmitter", "Lokhttp3/internal/connection/Transmitter;", "userRequest", "requestIsOneShot", "retryAfter", "", "defaultDelay", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: RetryAndFollowUpInterceptor.kt */
public final class RetryAndFollowUpInterceptor implements Interceptor {
    public static final Companion Companion = new Companion(null);
    private static final int MAX_FOLLOW_UPS = 20;
    private final OkHttpClient client;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo24952d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor$Companion;", "", "()V", "MAX_FOLLOW_UPS", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: RetryAndFollowUpInterceptor.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    public RetryAndFollowUpInterceptor(OkHttpClient client2) {
        Intrinsics.checkParameterIsNotNull(client2, "client");
        this.client = client2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0065 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r15) throws java.io.IOException {
        /*
            r14 = this;
            java.lang.String r0 = "chain"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r15, r0)
            okhttp3.Request r0 = r15.request()
            r1 = r15
            okhttp3.internal.http.RealInterceptorChain r1 = (okhttp3.internal.http.RealInterceptorChain) r1
            okhttp3.internal.connection.Transmitter r2 = r1.transmitter()
            r3 = 0
            r4 = 0
            r5 = r4
            okhttp3.Response r5 = (okhttp3.Response) r5
        L_0x0015:
            r2.prepareToConnect(r0)
            boolean r6 = r2.isCanceled()
            if (r6 != 0) goto L_0x00ee
            r6 = 0
            r7 = 0
            r8 = 0
            okhttp3.Response r8 = r1.proceed(r0, r2, r4)     // Catch:{ RouteException -> 0x00d0, IOException -> 0x00b9 }
            r6 = r8
            r7 = 1
            if (r5 == 0) goto L_0x004d
            okhttp3.Response$Builder r8 = r6.newBuilder()
            okhttp3.Response$Builder r9 = r5.newBuilder()
            okhttp3.Response$Builder r9 = r9.body(r4)
            okhttp3.Response r9 = r9.build()
            okhttp3.Response$Builder r8 = r8.priorResponse(r9)
            okhttp3.Response r6 = r8.build()
        L_0x004d:
            okhttp3.internal.connection.Exchange r8 = r6.exchange()
            if (r8 == 0) goto L_0x005e
            okhttp3.internal.connection.RealConnection r9 = r8.connection()
            if (r9 == 0) goto L_0x005e
            okhttp3.Route r9 = r9.route()
            goto L_0x005f
        L_0x005e:
            r9 = r4
        L_0x005f:
            okhttp3.Request r10 = r14.followUpRequest(r6, r9)
            if (r10 != 0) goto L_0x0071
            if (r8 == 0) goto L_0x0070
            boolean r4 = r8.isDuplex()
            if (r4 == 0) goto L_0x0070
            r2.timeoutEarlyExit()
        L_0x0070:
            return r6
        L_0x0071:
            okhttp3.RequestBody r11 = r10.body()
            if (r11 == 0) goto L_0x007e
            boolean r12 = r11.isOneShot()
            if (r12 == 0) goto L_0x007e
            return r6
        L_0x007e:
            okhttp3.ResponseBody r12 = r6.body()
            if (r12 == 0) goto L_0x0089
            java.io.Closeable r12 = (java.io.Closeable) r12
            okhttp3.internal.Util.closeQuietly(r12)
        L_0x0089:
            boolean r12 = r2.hasExchange()
            if (r12 == 0) goto L_0x0094
            if (r8 == 0) goto L_0x0094
            r8.detachWithViolence()
        L_0x0094:
            int r3 = r3 + 1
            r12 = 20
            if (r3 > r12) goto L_0x009e
            r0 = r10
            r5 = r6
            goto L_0x0015
        L_0x009e:
            java.net.ProtocolException r4 = new java.net.ProtocolException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "Too many follow-up requests: "
            r12.append(r13)
            r12.append(r3)
            java.lang.String r12 = r12.toString()
            r4.<init>(r12)
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            throw r4
        L_0x00b7:
            r4 = move-exception
            goto L_0x00e9
        L_0x00b9:
            r9 = move-exception
            boolean r10 = r9 instanceof okhttp3.internal.http2.ConnectionShutdownException     // Catch:{ all -> 0x00b7 }
            if (r10 != 0) goto L_0x00bf
            r8 = 1
        L_0x00bf:
            boolean r10 = r14.recover(r9, r2, r8, r0)     // Catch:{ all -> 0x00b7 }
            if (r10 == 0) goto L_0x00cc
            r2.exchangeDoneDueToException()
            goto L_0x0015
        L_0x00cc:
            r4 = r9
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch:{ all -> 0x00b7 }
            throw r4     // Catch:{ all -> 0x00b7 }
        L_0x00d0:
            r9 = move-exception
            java.io.IOException r10 = r9.getLastConnectException()     // Catch:{ all -> 0x00b7 }
            boolean r8 = r14.recover(r10, r2, r8, r0)     // Catch:{ all -> 0x00b7 }
            if (r8 == 0) goto L_0x00e2
            r2.exchangeDoneDueToException()
            goto L_0x0015
        L_0x00e2:
            java.io.IOException r4 = r9.getFirstConnectException()     // Catch:{ all -> 0x00b7 }
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch:{ all -> 0x00b7 }
            throw r4     // Catch:{ all -> 0x00b7 }
        L_0x00e9:
            r2.exchangeDoneDueToException()
            throw r4
        L_0x00ee:
            java.io.IOException r4 = new java.io.IOException
            java.lang.String r6 = "Canceled"
            r4.<init>(r6)
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    private final boolean recover(IOException e, Transmitter transmitter, boolean requestSendStarted, Request userRequest) {
        if (!this.client.retryOnConnectionFailure()) {
            return false;
        }
        if ((!requestSendStarted || !requestIsOneShot(e, userRequest)) && isRecoverable(e, requestSendStarted) && transmitter.canRetry()) {
            return true;
        }
        return false;
    }

    private final boolean requestIsOneShot(IOException e, Request userRequest) {
        RequestBody requestBody = userRequest.body();
        return (requestBody != null && requestBody.isOneShot()) || (e instanceof FileNotFoundException);
    }

    private final boolean isRecoverable(IOException e, boolean requestSendStarted) {
        boolean z = false;
        if (e instanceof ProtocolException) {
            return false;
        }
        if (e instanceof InterruptedIOException) {
            if ((e instanceof SocketTimeoutException) && !requestSendStarted) {
                z = true;
            }
            return z;
        } else if ((!(e instanceof SSLHandshakeException) || !(e.getCause() instanceof CertificateException)) && !(e instanceof SSLPeerUnverifiedException)) {
            return true;
        } else {
            return false;
        }
    }

    private final Request followUpRequest(Response userResponse, Route route) throws IOException {
        int responseCode = userResponse.code();
        String method = userResponse.request().method();
        if (responseCode == 307 || responseCode == 308) {
            if (!(!Intrinsics.areEqual((Object) method, (Object) "GET")) || !(!Intrinsics.areEqual((Object) method, (Object) "HEAD"))) {
                return buildRedirectRequest(userResponse, method);
            }
            return null;
        } else if (responseCode == 401) {
            return this.client.authenticator().authenticate(route, userResponse);
        } else {
            if (responseCode == 503) {
                Response priorResponse = userResponse.priorResponse();
                if ((priorResponse == null || priorResponse.code() != 503) && retryAfter(userResponse, Integer.MAX_VALUE) == 0) {
                    return userResponse.request();
                }
                return null;
            } else if (responseCode == 407) {
                if (route == null) {
                    Intrinsics.throwNpe();
                }
                if (route.proxy().type() == Type.HTTP) {
                    return this.client.proxyAuthenticator().authenticate(route, userResponse);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            } else if (responseCode != 408) {
                switch (responseCode) {
                    case HttpStatus.SC_MULTIPLE_CHOICES /*300*/:
                    case HttpStatus.SC_MOVED_PERMANENTLY /*301*/:
                    case HttpStatus.SC_MOVED_TEMPORARILY /*302*/:
                    case HttpStatus.SC_SEE_OTHER /*303*/:
                        return buildRedirectRequest(userResponse, method);
                    default:
                        return null;
                }
            } else if (!this.client.retryOnConnectionFailure()) {
                return null;
            } else {
                RequestBody requestBody = userResponse.request().body();
                if (requestBody != null && requestBody.isOneShot()) {
                    return null;
                }
                Response priorResponse2 = userResponse.priorResponse();
                if ((priorResponse2 == null || priorResponse2.code() != 408) && retryAfter(userResponse, 0) <= 0) {
                    return userResponse.request();
                }
                return null;
            }
        }
    }

    private final Request buildRedirectRequest(Response userResponse, String method) {
        RequestBody requestBody = null;
        if (!this.client.followRedirects()) {
            return null;
        }
        String location = Response.header$default(userResponse, HttpHeaders.LOCATION, null, 2, null);
        if (location == null) {
            return null;
        }
        HttpUrl url = userResponse.request().url().resolve(location);
        if (url == null) {
            return null;
        }
        if (!Intrinsics.areEqual((Object) url.scheme(), (Object) userResponse.request().url().scheme()) && !this.client.followSslRedirects()) {
            return null;
        }
        Builder requestBuilder = userResponse.request().newBuilder();
        if (HttpMethod.permitsRequestBody(method)) {
            boolean maintainBody = HttpMethod.INSTANCE.redirectsWithBody(method);
            if (HttpMethod.INSTANCE.redirectsToGet(method)) {
                requestBuilder.method("GET", null);
            } else {
                if (maintainBody) {
                    requestBody = userResponse.request().body();
                }
                requestBuilder.method(method, requestBody);
            }
            if (!maintainBody) {
                requestBuilder.removeHeader("Transfer-Encoding");
                requestBuilder.removeHeader("Content-Length");
                requestBuilder.removeHeader("Content-Type");
            }
        }
        if (!Util.canReuseConnectionFor(userResponse.request().url(), url)) {
            requestBuilder.removeHeader("Authorization");
        }
        return requestBuilder.url(url).build();
    }

    private final int retryAfter(Response userResponse, int defaultDelay) {
        String header = Response.header$default(userResponse, HttpHeaders.RETRY_AFTER, null, 2, null);
        if (header == null) {
            return defaultDelay;
        }
        if (!new Regex("\\d+").matches(header)) {
            return Integer.MAX_VALUE;
        }
        Integer valueOf = Integer.valueOf(header);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "Integer.valueOf(header)");
        return valueOf.intValue();
    }
}
