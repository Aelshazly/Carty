package okhttp3.internal.http;

import androidx.core.app.NotificationCompat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.Transmitter;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001BU\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\n\u0012\u0006\u0010\u0011\u001a\u00020\n¢\u0006\u0002\u0010\u0012J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\b\u0010\u0019\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010 \u001a\u00020\nH\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, mo24952d2 = {"Lokhttp3/internal/http/RealInterceptorChain;", "Lokhttp3/Interceptor$Chain;", "interceptors", "", "Lokhttp3/Interceptor;", "transmitter", "Lokhttp3/internal/connection/Transmitter;", "exchange", "Lokhttp3/internal/connection/Exchange;", "index", "", "request", "Lokhttp3/Request;", "call", "Lokhttp3/Call;", "connectTimeout", "readTimeout", "writeTimeout", "(Ljava/util/List;Lokhttp3/internal/connection/Transmitter;Lokhttp3/internal/connection/Exchange;ILokhttp3/Request;Lokhttp3/Call;III)V", "calls", "connectTimeoutMillis", "connection", "Lokhttp3/Connection;", "proceed", "Lokhttp3/Response;", "readTimeoutMillis", "withConnectTimeout", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "withReadTimeout", "withWriteTimeout", "writeTimeoutMillis", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: RealInterceptorChain.kt */
public final class RealInterceptorChain implements Chain {
    private final Call call;
    private int calls;
    private final int connectTimeout;
    private final Exchange exchange;
    private final int index;
    private final List<Interceptor> interceptors;
    private final int readTimeout;
    private final Request request;
    private final Transmitter transmitter;
    private final int writeTimeout;

    public RealInterceptorChain(List<? extends Interceptor> interceptors2, Transmitter transmitter2, Exchange exchange2, int index2, Request request2, Call call2, int connectTimeout2, int readTimeout2, int writeTimeout2) {
        Intrinsics.checkParameterIsNotNull(interceptors2, "interceptors");
        Intrinsics.checkParameterIsNotNull(transmitter2, "transmitter");
        Intrinsics.checkParameterIsNotNull(request2, "request");
        Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
        this.interceptors = interceptors2;
        this.transmitter = transmitter2;
        this.exchange = exchange2;
        this.index = index2;
        this.request = request2;
        this.call = call2;
        this.connectTimeout = connectTimeout2;
        this.readTimeout = readTimeout2;
        this.writeTimeout = writeTimeout2;
    }

    public Connection connection() {
        Exchange exchange2 = this.exchange;
        return exchange2 != null ? exchange2.connection() : null;
    }

    public int connectTimeoutMillis() {
        return this.connectTimeout;
    }

    public Chain withConnectTimeout(int timeout, TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        RealInterceptorChain realInterceptorChain = new RealInterceptorChain(this.interceptors, this.transmitter, this.exchange, this.index, this.request, this.call, Util.checkDuration("timeout", (long) timeout, unit), this.readTimeout, this.writeTimeout);
        return realInterceptorChain;
    }

    public int readTimeoutMillis() {
        return this.readTimeout;
    }

    public Chain withReadTimeout(int timeout, TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        RealInterceptorChain realInterceptorChain = new RealInterceptorChain(this.interceptors, this.transmitter, this.exchange, this.index, this.request, this.call, this.connectTimeout, Util.checkDuration("timeout", (long) timeout, unit), this.writeTimeout);
        return realInterceptorChain;
    }

    public int writeTimeoutMillis() {
        return this.writeTimeout;
    }

    public Chain withWriteTimeout(int timeout, TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        RealInterceptorChain realInterceptorChain = new RealInterceptorChain(this.interceptors, this.transmitter, this.exchange, this.index, this.request, this.call, this.connectTimeout, this.readTimeout, Util.checkDuration("timeout", (long) timeout, unit));
        return realInterceptorChain;
    }

    public final Transmitter transmitter() {
        return this.transmitter;
    }

    public final Exchange exchange() {
        Exchange exchange2 = this.exchange;
        if (exchange2 == null) {
            Intrinsics.throwNpe();
        }
        return exchange2;
    }

    public Call call() {
        return this.call;
    }

    public Request request() {
        return this.request;
    }

    public Response proceed(Request request2) {
        Intrinsics.checkParameterIsNotNull(request2, "request");
        return proceed(request2, this.transmitter, this.exchange);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0135  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final okhttp3.Response proceed(okhttp3.Request r20, okhttp3.internal.connection.Transmitter r21, okhttp3.internal.connection.Exchange r22) throws java.io.IOException {
        /*
            r19 = this;
            r0 = r19
            java.lang.String r1 = "request"
            r12 = r20
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r1)
            java.lang.String r1 = "transmitter"
            r13 = r21
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r13, r1)
            int r1 = r0.index
            java.util.List<okhttp3.Interceptor> r2 = r0.interceptors
            int r2 = r2.size()
            if (r1 >= r2) goto L_0x0162
            int r1 = r0.calls
            r14 = 1
            int r1 = r1 + r14
            r0.calls = r1
            okhttp3.internal.connection.Exchange r1 = r0.exchange
            if (r1 == 0) goto L_0x003a
            okhttp3.internal.connection.RealConnection r1 = r1.connection()
            if (r1 != 0) goto L_0x002d
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x002d:
            okhttp3.HttpUrl r2 = r20.url()
            boolean r1 = r1.supportsUrl(r2)
            if (r1 == 0) goto L_0x0038
            goto L_0x003a
        L_0x0038:
            r1 = 0
            goto L_0x003b
        L_0x003a:
            r1 = 1
        L_0x003b:
            java.lang.String r11 = "network interceptor "
            if (r1 == 0) goto L_0x0135
            okhttp3.internal.connection.Exchange r1 = r0.exchange
            if (r1 == 0) goto L_0x004a
            int r1 = r0.calls
            if (r1 > r14) goto L_0x0048
            goto L_0x004a
        L_0x0048:
            r1 = 0
            goto L_0x004b
        L_0x004a:
            r1 = 1
        L_0x004b:
            java.lang.String r10 = " must call proceed() exactly once"
            if (r1 == 0) goto L_0x0109
            okhttp3.internal.http.RealInterceptorChain r1 = new okhttp3.internal.http.RealInterceptorChain
            java.util.List<okhttp3.Interceptor> r3 = r0.interceptors
            int r2 = r0.index
            int r6 = r2 + 1
            okhttp3.Call r8 = r0.call
            int r9 = r0.connectTimeout
            int r7 = r0.readTimeout
            int r5 = r0.writeTimeout
            r2 = r1
            r4 = r21
            r16 = r5
            r5 = r22
            r17 = r7
            r7 = r20
            r15 = r10
            r10 = r17
            r18 = r11
            r11 = r16
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            java.util.List<okhttp3.Interceptor> r2 = r0.interceptors
            int r3 = r0.index
            java.lang.Object r2 = r2.get(r3)
            okhttp3.Interceptor r2 = (okhttp3.Interceptor) r2
            r3 = r1
            okhttp3.Interceptor$Chain r3 = (okhttp3.Interceptor.Chain) r3
            okhttp3.Response r3 = r2.intercept(r3)
            java.lang.String r4 = "interceptor "
            if (r3 == 0) goto L_0x00ed
            if (r22 == 0) goto L_0x009d
            int r5 = r0.index
            int r5 = r5 + r14
            java.util.List<okhttp3.Interceptor> r6 = r0.interceptors
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x009d
            int r5 = r1.calls
            if (r5 != r14) goto L_0x009b
            goto L_0x009d
        L_0x009b:
            r5 = 0
            goto L_0x009e
        L_0x009d:
            r5 = 1
        L_0x009e:
            if (r5 == 0) goto L_0x00cc
            okhttp3.ResponseBody r5 = r3.body()
            if (r5 == 0) goto L_0x00a7
            goto L_0x00a8
        L_0x00a7:
            r14 = 0
        L_0x00a8:
            if (r14 == 0) goto L_0x00ab
            return r3
        L_0x00ab:
            r5 = 0
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r4)
            r6.append(r2)
            java.lang.String r4 = " returned a response with no body"
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r4 = r4.toString()
            r5.<init>(r4)
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            throw r5
        L_0x00cc:
            r4 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r6 = r18
            r5.append(r6)
            r5.append(r2)
            r5.append(r15)
            java.lang.String r4 = r5.toString()
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r4 = r4.toString()
            r5.<init>(r4)
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            throw r5
        L_0x00ed:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r4)
            r5.append(r2)
            java.lang.String r4 = " returned null"
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            r3.<init>(r4)
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            throw r3
        L_0x0109:
            r15 = r10
            r6 = r11
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            java.util.List<okhttp3.Interceptor> r3 = r0.interceptors
            int r4 = r0.index
            int r4 = r4 - r14
            java.lang.Object r3 = r3.get(r4)
            okhttp3.Interceptor r3 = (okhttp3.Interceptor) r3
            r2.append(r3)
            r2.append(r15)
            java.lang.String r1 = r2.toString()
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x0135:
            r6 = r11
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            java.util.List<okhttp3.Interceptor> r3 = r0.interceptors
            int r4 = r0.index
            int r4 = r4 - r14
            java.lang.Object r3 = r3.get(r4)
            okhttp3.Interceptor r3 = (okhttp3.Interceptor) r3
            r2.append(r3)
            java.lang.String r3 = " must retain the same host and port"
            r2.append(r3)
            java.lang.String r1 = r2.toString()
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x0162:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.RealInterceptorChain.proceed(okhttp3.Request, okhttp3.internal.connection.Transmitter, okhttp3.internal.connection.Exchange):okhttp3.Response");
    }
}
