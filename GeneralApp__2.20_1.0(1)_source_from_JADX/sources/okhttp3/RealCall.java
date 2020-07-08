package okhttp3;

import androidx.core.app.NotificationCompat;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.connection.Transmitter;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;
import okio.Timeout;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 '2\u00020\u0001:\u0002&'B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0000H\u0016J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0006\u0010\u001d\u001a\u00020\u001cJ\b\u0010\u001e\u001a\u00020\u0007H\u0016J\b\u0010\u001f\u001a\u00020\u0007H\u0016J\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020\u0005H\u0016J\b\u0010#\u001a\u00020$H\u0016J\u0006\u0010%\u001a\u00020!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u0002\n\u0000¨\u0006("}, mo24952d2 = {"Lokhttp3/RealCall;", "Lokhttp3/Call;", "client", "Lokhttp3/OkHttpClient;", "originalRequest", "Lokhttp3/Request;", "forWebSocket", "", "(Lokhttp3/OkHttpClient;Lokhttp3/Request;Z)V", "getClient", "()Lokhttp3/OkHttpClient;", "executed", "getExecuted", "()Z", "setExecuted", "(Z)V", "getForWebSocket", "getOriginalRequest", "()Lokhttp3/Request;", "transmitter", "Lokhttp3/internal/connection/Transmitter;", "cancel", "", "clone", "enqueue", "responseCallback", "Lokhttp3/Callback;", "execute", "Lokhttp3/Response;", "getResponseWithInterceptorChain", "isCanceled", "isExecuted", "redactedUrl", "", "request", "timeout", "Lokio/Timeout;", "toLoggableString", "AsyncCall", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: RealCall.kt */
public final class RealCall implements Call {
    public static final Companion Companion = new Companion(null);
    private final OkHttpClient client;
    private boolean executed;
    private final boolean forWebSocket;
    private final Request originalRequest;
    /* access modifiers changed from: private */
    public Transmitter transmitter;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0011\u001a\u00020\b2\n\u0010\u0012\u001a\u00060\u0000R\u00020\fJ\b\u0010\u0013\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo24952d2 = {"Lokhttp3/RealCall$AsyncCall;", "Ljava/lang/Runnable;", "responseCallback", "Lokhttp3/Callback;", "(Lokhttp3/RealCall;Lokhttp3/Callback;)V", "callsPerHost", "Ljava/util/concurrent/atomic/AtomicInteger;", "executeOn", "", "executorService", "Ljava/util/concurrent/ExecutorService;", "get", "Lokhttp3/RealCall;", "host", "", "request", "Lokhttp3/Request;", "reuseCallsPerHostFrom", "other", "run", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: RealCall.kt */
    public final class AsyncCall implements Runnable {
        private volatile AtomicInteger callsPerHost = new AtomicInteger(0);
        private final Callback responseCallback;
        final /* synthetic */ RealCall this$0;

        public AsyncCall(RealCall $outer, Callback responseCallback2) {
            Intrinsics.checkParameterIsNotNull(responseCallback2, "responseCallback");
            this.this$0 = $outer;
            this.responseCallback = responseCallback2;
        }

        public final AtomicInteger callsPerHost() {
            return this.callsPerHost;
        }

        public final void reuseCallsPerHostFrom(AsyncCall other) {
            Intrinsics.checkParameterIsNotNull(other, "other");
            this.callsPerHost = other.callsPerHost;
        }

        public final String host() {
            return this.this$0.getOriginalRequest().url().host();
        }

        public final Request request() {
            return this.this$0.getOriginalRequest();
        }

        public final RealCall get() {
            return this.this$0;
        }

        public final void executeOn(ExecutorService executorService) {
            Intrinsics.checkParameterIsNotNull(executorService, "executorService");
            boolean z = !Thread.holdsLock(this.this$0.getClient().dispatcher());
            if (!_Assertions.ENABLED || z) {
                try {
                    executorService.execute(this);
                } catch (RejectedExecutionException e) {
                    InterruptedIOException ioException = new InterruptedIOException("executor rejected");
                    ioException.initCause(e);
                    RealCall.access$getTransmitter$p(this.this$0).noMoreExchanges(ioException);
                    this.responseCallback.onFailure(this.this$0, ioException);
                    this.this$0.getClient().dispatcher().finished$okhttp(this);
                } catch (Throwable th) {
                    this.this$0.getClient().dispatcher().finished$okhttp(this);
                    throw th;
                }
            } else {
                throw new AssertionError("Assertion failed");
            }
        }

        public void run() {
            Dispatcher dispatcher;
            StringBuilder sb = new StringBuilder();
            sb.append("OkHttp ");
            sb.append(this.this$0.redactedUrl());
            String name$iv = sb.toString();
            Thread currentThread$iv = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread$iv, "currentThread");
            String oldName$iv = currentThread$iv.getName();
            currentThread$iv.setName(name$iv);
            boolean signalledCallback = false;
            try {
                RealCall.access$getTransmitter$p(this.this$0).timeoutEnter();
                try {
                    signalledCallback = true;
                    this.responseCallback.onResponse(this.this$0, this.this$0.getResponseWithInterceptorChain());
                    dispatcher = this.this$0.getClient().dispatcher();
                } catch (IOException e) {
                    if (signalledCallback) {
                        Platform platform = Platform.Companion.get();
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Callback failure for ");
                        sb2.append(this.this$0.toLoggableString());
                        platform.log(4, sb2.toString(), e);
                    } else {
                        this.responseCallback.onFailure(this.this$0, e);
                    }
                    dispatcher = this.this$0.getClient().dispatcher();
                }
                dispatcher.finished$okhttp(this);
                currentThread$iv.setName(oldName$iv);
            } catch (Throwable th) {
                currentThread$iv.setName(oldName$iv);
                throw th;
            }
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, mo24952d2 = {"Lokhttp3/RealCall$Companion;", "", "()V", "newRealCall", "Lokhttp3/RealCall;", "client", "Lokhttp3/OkHttpClient;", "originalRequest", "Lokhttp3/Request;", "forWebSocket", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: RealCall.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final RealCall newRealCall(OkHttpClient client, Request originalRequest, boolean forWebSocket) {
            Intrinsics.checkParameterIsNotNull(client, "client");
            Intrinsics.checkParameterIsNotNull(originalRequest, "originalRequest");
            RealCall realCall = new RealCall(client, originalRequest, forWebSocket, null);
            RealCall $this$apply = realCall;
            $this$apply.transmitter = new Transmitter(client, $this$apply);
            return realCall;
        }
    }

    private RealCall(OkHttpClient client2, Request originalRequest2, boolean forWebSocket2) {
        this.client = client2;
        this.originalRequest = originalRequest2;
        this.forWebSocket = forWebSocket2;
    }

    public /* synthetic */ RealCall(OkHttpClient client2, Request originalRequest2, boolean forWebSocket2, DefaultConstructorMarker $constructor_marker) {
        this(client2, originalRequest2, forWebSocket2);
    }

    public static final /* synthetic */ Transmitter access$getTransmitter$p(RealCall $this) {
        Transmitter transmitter2 = $this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        return transmitter2;
    }

    public final OkHttpClient getClient() {
        return this.client;
    }

    public final Request getOriginalRequest() {
        return this.originalRequest;
    }

    public final boolean getForWebSocket() {
        return this.forWebSocket;
    }

    public final boolean getExecuted() {
        return this.executed;
    }

    public final void setExecuted(boolean z) {
        this.executed = z;
    }

    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public boolean isCanceled() {
        Transmitter transmitter2 = this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        return transmitter2.isCanceled();
    }

    public Request request() {
        return this.originalRequest;
    }

    public Response execute() {
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("Already Executed".toString());
            }
        }
        Transmitter transmitter2 = this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        transmitter2.timeoutEnter();
        Transmitter transmitter3 = this.transmitter;
        if (transmitter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        transmitter3.callStart();
        try {
            this.client.dispatcher().executed$okhttp(this);
            return getResponseWithInterceptorChain();
        } finally {
            this.client.dispatcher().finished$okhttp(this);
        }
    }

    public void enqueue(Callback responseCallback) {
        Intrinsics.checkParameterIsNotNull(responseCallback, "responseCallback");
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("Already Executed".toString());
            }
        }
        Transmitter transmitter2 = this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        transmitter2.callStart();
        this.client.dispatcher().enqueue$okhttp(new AsyncCall(this, responseCallback));
    }

    public void cancel() {
        Transmitter transmitter2 = this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        transmitter2.cancel();
    }

    public Timeout timeout() {
        Transmitter transmitter2 = this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        return transmitter2.timeout();
    }

    public RealCall clone() {
        return Companion.newRealCall(this.client, this.originalRequest, this.forWebSocket);
    }

    public final String toLoggableString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "canceled " : "");
        sb.append(this.forWebSocket ? "web socket" : NotificationCompat.CATEGORY_CALL);
        sb.append(" to ");
        sb.append(redactedUrl());
        return sb.toString();
    }

    public final String redactedUrl() {
        return this.originalRequest.url().redact();
    }

    public final Response getResponseWithInterceptorChain() throws IOException {
        List interceptors = new ArrayList();
        CollectionsKt.addAll((Collection<? super T>) interceptors, (Iterable<? extends T>) this.client.interceptors());
        interceptors.add(new RetryAndFollowUpInterceptor(this.client));
        interceptors.add(new BridgeInterceptor(this.client.cookieJar()));
        interceptors.add(new CacheInterceptor(this.client.cache()));
        interceptors.add(ConnectInterceptor.INSTANCE);
        if (!this.forWebSocket) {
            CollectionsKt.addAll((Collection<? super T>) interceptors, (Iterable<? extends T>) this.client.networkInterceptors());
        }
        interceptors.add(new CallServerInterceptor(this.forWebSocket));
        Transmitter transmitter2 = this.transmitter;
        String str = "transmitter";
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        RealInterceptorChain chain = new RealInterceptorChain(interceptors, transmitter2, null, 0, this.originalRequest, this, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis());
        try {
            Response response = chain.proceed(this.originalRequest);
            Transmitter transmitter3 = this.transmitter;
            if (transmitter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str);
            }
            if (!transmitter3.isCanceled()) {
                Transmitter transmitter4 = this.transmitter;
                if (transmitter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                }
                transmitter4.noMoreExchanges(null);
                return response;
            }
            Util.closeQuietly((Closeable) response);
            throw new IOException("Canceled");
        } catch (IOException e) {
            Transmitter transmitter5 = this.transmitter;
            if (transmitter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str);
            }
            IOException noMoreExchanges = transmitter5.noMoreExchanges(e);
            throw (noMoreExchanges == null ? new TypeCastException("null cannot be cast to non-null type kotlin.Throwable") : noMoreExchanges);
        } catch (Throwable th) {
            if (1 == 0) {
                Transmitter transmitter6 = this.transmitter;
                if (transmitter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                }
                transmitter6.noMoreExchanges(null);
            }
            throw th;
        }
    }
}
