package okhttp3.internal.http2;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.IntRef;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Http2Reader.Handler;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 \u00012\u00020\u0001:\b\u0001\u0001\u0001\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010H\u001a\u00020IJ\b\u0010J\u001a\u00020IH\u0016J'\u0010J\u001a\u00020I2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020L2\b\u0010N\u001a\u0004\u0018\u00010OH\u0000¢\u0006\u0002\bPJ\u0012\u0010Q\u001a\u00020I2\b\u0010R\u001a\u0004\u0018\u00010OH\u0002J\u0006\u0010S\u001a\u00020IJ\u0010\u0010T\u001a\u0004\u0018\u00010;2\u0006\u0010U\u001a\u00020\u0010J\u0006\u0010V\u001a\u00020\u0010J&\u0010W\u001a\u00020;2\u0006\u0010X\u001a\u00020\u00102\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020[0Z2\u0006\u0010\\\u001a\u00020\u0006H\u0002J\u001c\u0010W\u001a\u00020;2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020[0Z2\u0006\u0010\\\u001a\u00020\u0006J\u0006\u0010]\u001a\u00020\u0010J-\u0010^\u001a\u00020I2\u0006\u0010_\u001a\u00020\u00102\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020\u00102\u0006\u0010c\u001a\u00020\u0006H\u0000¢\u0006\u0002\bdJ+\u0010e\u001a\u00020I2\u0006\u0010_\u001a\u00020\u00102\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020[0Z2\u0006\u0010c\u001a\u00020\u0006H\u0000¢\u0006\u0002\bfJ#\u0010g\u001a\u00020I2\u0006\u0010_\u001a\u00020\u00102\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020[0ZH\u0000¢\u0006\u0002\bhJ\u001d\u0010i\u001a\u00020I2\u0006\u0010_\u001a\u00020\u00102\u0006\u0010j\u001a\u00020LH\u0000¢\u0006\u0002\bkJ$\u0010l\u001a\u00020;2\u0006\u0010X\u001a\u00020\u00102\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020[0Z2\u0006\u0010\\\u001a\u00020\u0006J\u0015\u0010m\u001a\u00020\u00062\u0006\u0010_\u001a\u00020\u0010H\u0000¢\u0006\u0002\bnJ\u0017\u0010o\u001a\u0004\u0018\u00010;2\u0006\u0010_\u001a\u00020\u0010H\u0000¢\u0006\u0002\bpJ\u000e\u0010q\u001a\u00020I2\u0006\u0010r\u001a\u00020\"J\u000e\u0010s\u001a\u00020I2\u0006\u0010t\u001a\u00020LJ\u0012\u0010u\u001a\u00020I2\b\b\u0002\u0010v\u001a\u00020\u0006H\u0007J\u0015\u0010w\u001a\u00020I2\u0006\u0010x\u001a\u00020+H\u0000¢\u0006\u0002\byJ(\u0010z\u001a\u00020I2\u0006\u0010_\u001a\u00020\u00102\u0006\u0010{\u001a\u00020\u00062\b\u0010|\u001a\u0004\u0018\u00010}2\u0006\u0010b\u001a\u00020+J,\u0010~\u001a\u00020I2\u0006\u0010_\u001a\u00020\u00102\u0006\u0010{\u001a\u00020\u00062\f\u0010\u001a\b\u0012\u0004\u0012\u00020[0ZH\u0000¢\u0006\u0003\b\u0001J\"\u0010\u0001\u001a\u00020I2\u0007\u0010\u0001\u001a\u00020\u00062\u0007\u0010\u0001\u001a\u00020\u00102\u0007\u0010\u0001\u001a\u00020\u0010J\u0007\u0010\u0001\u001a\u00020IJ\u001f\u0010\u0001\u001a\u00020I2\u0006\u0010_\u001a\u00020\u00102\u0006\u0010t\u001a\u00020LH\u0000¢\u0006\u0003\b\u0001J\u001f\u0010\u0001\u001a\u00020I2\u0006\u0010_\u001a\u00020\u00102\u0006\u0010j\u001a\u00020LH\u0000¢\u0006\u0003\b\u0001J \u0010\u0001\u001a\u00020I2\u0006\u0010_\u001a\u00020\u00102\u0007\u0010\u0001\u001a\u00020+H\u0000¢\u0006\u0003\b\u0001R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R&\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00068F@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\t\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0017\"\u0004\b \u0010\u0019R\u0011\u0010!\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u000e\u0010'\u001a\u00020(X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010,\u001a\u00020+2\u0006\u0010\u0011\u001a\u00020+@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u001e\u0010/\u001a\u00020+2\u0006\u0010\u0011\u001a\u00020+@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b0\u0010.R\u0015\u00101\u001a\u000602R\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0014\u00105\u001a\u000206X\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R \u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020;0:X\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u001e\u0010>\u001a\u00020+2\u0006\u0010\u0011\u001a\u00020+@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b?\u0010.R\u001e\u0010@\u001a\u00020+2\u0006\u0010\u0011\u001a\u00020+@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\bA\u0010.R\u0011\u0010B\u001a\u00020C¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u000e\u0010F\u001a\u00020GX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Connection;", "Ljava/io/Closeable;", "builder", "Lokhttp3/internal/http2/Http2Connection$Builder;", "(Lokhttp3/internal/http2/Http2Connection$Builder;)V", "awaitingPong", "", "client", "getClient$okhttp", "()Z", "connectionName", "", "getConnectionName$okhttp", "()Ljava/lang/String;", "currentPushRequests", "", "", "<set-?>", "isShutdown", "setShutdown$okhttp", "(Z)V", "lastGoodStreamId", "getLastGoodStreamId$okhttp", "()I", "setLastGoodStreamId$okhttp", "(I)V", "listener", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "nextStreamId", "getNextStreamId$okhttp", "setNextStreamId$okhttp", "okHttpSettings", "Lokhttp3/internal/http2/Settings;", "getOkHttpSettings", "()Lokhttp3/internal/http2/Settings;", "peerSettings", "getPeerSettings", "pushExecutor", "Ljava/util/concurrent/ThreadPoolExecutor;", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "", "readBytesAcknowledged", "getReadBytesAcknowledged", "()J", "readBytesTotal", "getReadBytesTotal", "readerRunnable", "Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "getReaderRunnable", "()Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "socket", "Ljava/net/Socket;", "getSocket$okhttp", "()Ljava/net/Socket;", "streams", "", "Lokhttp3/internal/http2/Http2Stream;", "getStreams$okhttp", "()Ljava/util/Map;", "writeBytesMaximum", "getWriteBytesMaximum", "writeBytesTotal", "getWriteBytesTotal", "writer", "Lokhttp3/internal/http2/Http2Writer;", "getWriter", "()Lokhttp3/internal/http2/Http2Writer;", "writerExecutor", "Ljava/util/concurrent/ScheduledThreadPoolExecutor;", "awaitPong", "", "close", "connectionCode", "Lokhttp3/internal/http2/ErrorCode;", "streamCode", "cause", "Ljava/io/IOException;", "close$okhttp", "failConnection", "e", "flush", "getStream", "id", "maxConcurrentStreams", "newStream", "associatedStreamId", "requestHeaders", "", "Lokhttp3/internal/http2/Header;", "out", "openStreamCount", "pushDataLater", "streamId", "source", "Lokio/BufferedSource;", "byteCount", "inFinished", "pushDataLater$okhttp", "pushHeadersLater", "pushHeadersLater$okhttp", "pushRequestLater", "pushRequestLater$okhttp", "pushResetLater", "errorCode", "pushResetLater$okhttp", "pushStream", "pushedStream", "pushedStream$okhttp", "removeStream", "removeStream$okhttp", "setSettings", "settings", "shutdown", "statusCode", "start", "sendConnectionPreface", "updateConnectionFlowControl", "read", "updateConnectionFlowControl$okhttp", "writeData", "outFinished", "buffer", "Lokio/Buffer;", "writeHeaders", "alternating", "writeHeaders$okhttp", "writePing", "reply", "payload1", "payload2", "writePingAndAwaitPong", "writeSynReset", "writeSynReset$okhttp", "writeSynResetLater", "writeSynResetLater$okhttp", "writeWindowUpdateLater", "unacknowledgedBytesRead", "writeWindowUpdateLater$okhttp", "Builder", "Companion", "Listener", "ReaderRunnable", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Http2Connection.kt */
public final class Http2Connection implements Closeable {
    public static final Companion Companion = new Companion(null);
    public static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    /* access modifiers changed from: private */
    public static final ThreadPoolExecutor listenerExecutor;
    /* access modifiers changed from: private */
    public boolean awaitingPong;
    private final boolean client;
    private final String connectionName;
    /* access modifiers changed from: private */
    public final Set<Integer> currentPushRequests;
    private boolean isShutdown;
    private int lastGoodStreamId;
    private final Listener listener;
    private int nextStreamId;
    private final Settings okHttpSettings;
    private final Settings peerSettings;
    private final ThreadPoolExecutor pushExecutor;
    /* access modifiers changed from: private */
    public final PushObserver pushObserver;
    private long readBytesAcknowledged;
    private long readBytesTotal;
    private final ReaderRunnable readerRunnable;
    private final Socket socket;
    private final Map<Integer, Http2Stream> streams = new LinkedHashMap();
    /* access modifiers changed from: private */
    public long writeBytesMaximum;
    private long writeBytesTotal;
    private final Http2Writer writer;
    /* access modifiers changed from: private */
    public final ScheduledThreadPoolExecutor writerExecutor;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u00102\u001a\u000203J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001bJ.\u0010&\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010,\u001a\u00020-2\b\b\u0002\u0010 \u001a\u00020!H\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001a\u0010\b\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020'X.¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020-X.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u00064"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Connection$Builder;", "", "client", "", "(Z)V", "getClient$okhttp", "()Z", "setClient$okhttp", "connectionName", "", "getConnectionName$okhttp", "()Ljava/lang/String;", "setConnectionName$okhttp", "(Ljava/lang/String;)V", "listener", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "setListener$okhttp", "(Lokhttp3/internal/http2/Http2Connection$Listener;)V", "pingIntervalMillis", "", "getPingIntervalMillis$okhttp", "()I", "setPingIntervalMillis$okhttp", "(I)V", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "getPushObserver$okhttp", "()Lokhttp3/internal/http2/PushObserver;", "setPushObserver$okhttp", "(Lokhttp3/internal/http2/PushObserver;)V", "sink", "Lokio/BufferedSink;", "getSink$okhttp", "()Lokio/BufferedSink;", "setSink$okhttp", "(Lokio/BufferedSink;)V", "socket", "Ljava/net/Socket;", "getSocket$okhttp", "()Ljava/net/Socket;", "setSocket$okhttp", "(Ljava/net/Socket;)V", "source", "Lokio/BufferedSource;", "getSource$okhttp", "()Lokio/BufferedSource;", "setSource$okhttp", "(Lokio/BufferedSource;)V", "build", "Lokhttp3/internal/http2/Http2Connection;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http2Connection.kt */
    public static final class Builder {
        private boolean client;
        public String connectionName;
        private Listener listener = Listener.REFUSE_INCOMING_STREAMS;
        private int pingIntervalMillis;
        private PushObserver pushObserver = PushObserver.CANCEL;
        public BufferedSink sink;
        public Socket socket;
        public BufferedSource source;

        public final Builder socket(Socket socket2) throws IOException {
            return socket$default(this, socket2, null, null, null, 14, null);
        }

        public final Builder socket(Socket socket2, String str) throws IOException {
            return socket$default(this, socket2, str, null, null, 12, null);
        }

        public final Builder socket(Socket socket2, String str, BufferedSource bufferedSource) throws IOException {
            return socket$default(this, socket2, str, bufferedSource, null, 8, null);
        }

        public Builder(boolean client2) {
            this.client = client2;
        }

        public final boolean getClient$okhttp() {
            return this.client;
        }

        public final void setClient$okhttp(boolean z) {
            this.client = z;
        }

        public final Socket getSocket$okhttp() {
            Socket socket2 = this.socket;
            if (socket2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("socket");
            }
            return socket2;
        }

        public final void setSocket$okhttp(Socket socket2) {
            Intrinsics.checkParameterIsNotNull(socket2, "<set-?>");
            this.socket = socket2;
        }

        public final String getConnectionName$okhttp() {
            String str = this.connectionName;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connectionName");
            }
            return str;
        }

        public final void setConnectionName$okhttp(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.connectionName = str;
        }

        public final BufferedSource getSource$okhttp() {
            BufferedSource bufferedSource = this.source;
            if (bufferedSource == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Param.SOURCE);
            }
            return bufferedSource;
        }

        public final void setSource$okhttp(BufferedSource bufferedSource) {
            Intrinsics.checkParameterIsNotNull(bufferedSource, "<set-?>");
            this.source = bufferedSource;
        }

        public final BufferedSink getSink$okhttp() {
            BufferedSink bufferedSink = this.sink;
            if (bufferedSink == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sink");
            }
            return bufferedSink;
        }

        public final void setSink$okhttp(BufferedSink bufferedSink) {
            Intrinsics.checkParameterIsNotNull(bufferedSink, "<set-?>");
            this.sink = bufferedSink;
        }

        public final Listener getListener$okhttp() {
            return this.listener;
        }

        public final void setListener$okhttp(Listener listener2) {
            Intrinsics.checkParameterIsNotNull(listener2, "<set-?>");
            this.listener = listener2;
        }

        public final PushObserver getPushObserver$okhttp() {
            return this.pushObserver;
        }

        public final void setPushObserver$okhttp(PushObserver pushObserver2) {
            Intrinsics.checkParameterIsNotNull(pushObserver2, "<set-?>");
            this.pushObserver = pushObserver2;
        }

        public final int getPingIntervalMillis$okhttp() {
            return this.pingIntervalMillis;
        }

        public final void setPingIntervalMillis$okhttp(int i) {
            this.pingIntervalMillis = i;
        }

        public static /* synthetic */ Builder socket$default(Builder builder, Socket socket2, String str, BufferedSource bufferedSource, BufferedSink bufferedSink, int i, Object obj) throws IOException {
            if ((i & 2) != 0) {
                str = Util.connectionName(socket2);
            }
            if ((i & 4) != 0) {
                bufferedSource = Okio.buffer(Okio.source(socket2));
            }
            if ((i & 8) != 0) {
                bufferedSink = Okio.buffer(Okio.sink(socket2));
            }
            return builder.socket(socket2, str, bufferedSource, bufferedSink);
        }

        public final Builder socket(Socket socket2, String connectionName2, BufferedSource source2, BufferedSink sink2) throws IOException {
            Intrinsics.checkParameterIsNotNull(socket2, "socket");
            Intrinsics.checkParameterIsNotNull(connectionName2, "connectionName");
            Intrinsics.checkParameterIsNotNull(source2, Param.SOURCE);
            Intrinsics.checkParameterIsNotNull(sink2, "sink");
            Builder $this$apply = this;
            $this$apply.socket = socket2;
            $this$apply.connectionName = connectionName2;
            $this$apply.source = source2;
            $this$apply.sink = sink2;
            return this;
        }

        public final Builder listener(Listener listener2) {
            Intrinsics.checkParameterIsNotNull(listener2, CastExtraArgs.LISTENER);
            this.listener = listener2;
            return this;
        }

        public final Builder pushObserver(PushObserver pushObserver2) {
            Intrinsics.checkParameterIsNotNull(pushObserver2, "pushObserver");
            this.pushObserver = pushObserver2;
            return this;
        }

        public final Builder pingIntervalMillis(int pingIntervalMillis2) {
            this.pingIntervalMillis = pingIntervalMillis2;
            return this;
        }

        public final Http2Connection build() {
            return new Http2Connection(this);
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Connection$Companion;", "", "()V", "OKHTTP_CLIENT_WINDOW_SIZE", "", "listenerExecutor", "Ljava/util/concurrent/ThreadPoolExecutor;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http2Connection.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u000b"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Connection$Listener;", "", "()V", "onSettings", "", "connection", "Lokhttp3/internal/http2/Http2Connection;", "onStream", "stream", "Lokhttp3/internal/http2/Http2Stream;", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http2Connection.kt */
    public static abstract class Listener {
        public static final Companion Companion = new Companion(null);
        public static final Listener REFUSE_INCOMING_STREAMS = new Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1();

        @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Connection$Listener$Companion;", "", "()V", "REFUSE_INCOMING_STREAMS", "Lokhttp3/internal/http2/Http2Connection$Listener;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
        /* compiled from: Http2Connection.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }

        public abstract void onStream(Http2Stream http2Stream) throws IOException;

        public void onSettings(Http2Connection connection) {
            Intrinsics.checkParameterIsNotNull(connection, "connection");
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J8\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J(\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\fH\u0016J \u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0010H\u0016J.\u0010$\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010%\u001a\u00020\f2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'H\u0016J \u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\f2\u0006\u0010,\u001a\u00020\fH\u0016J(\u0010-\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\u0017H\u0016J&\u00101\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00102\u001a\u00020\f2\f\u00103\u001a\b\u0012\u0004\u0012\u00020(0'H\u0016J\u0018\u00104\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u00105\u001a\u00020\tH\u0016J\u0018\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u00106\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00107\u001a\u00020\u0014H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u00068"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "Ljava/lang/Runnable;", "Lokhttp3/internal/http2/Http2Reader$Handler;", "reader", "Lokhttp3/internal/http2/Http2Reader;", "(Lokhttp3/internal/http2/Http2Connection;Lokhttp3/internal/http2/Http2Reader;)V", "getReader$okhttp", "()Lokhttp3/internal/http2/Http2Reader;", "ackSettings", "", "alternateService", "streamId", "", "origin", "", "protocol", "Lokio/ByteString;", "host", "port", "maxAge", "", "applyAndAckSettings", "clearPrevious", "", "settings", "Lokhttp3/internal/http2/Settings;", "data", "inFinished", "source", "Lokio/BufferedSource;", "length", "goAway", "lastGoodStreamId", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "debugData", "headers", "associatedStreamId", "headerBlock", "", "Lokhttp3/internal/http2/Header;", "ping", "ack", "payload1", "payload2", "priority", "streamDependency", "weight", "exclusive", "pushPromise", "promisedStreamId", "requestHeaders", "rstStream", "run", "windowUpdate", "windowSizeIncrement", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http2Connection.kt */
    public final class ReaderRunnable implements Runnable, Handler {
        private final Http2Reader reader;
        final /* synthetic */ Http2Connection this$0;

        public ReaderRunnable(Http2Connection $outer, Http2Reader reader2) {
            Intrinsics.checkParameterIsNotNull(reader2, "reader");
            this.this$0 = $outer;
            this.reader = reader2;
        }

        public final Http2Reader getReader$okhttp() {
            return this.reader;
        }

        public void run() {
            ErrorCode streamErrorCode;
            ErrorCode connectionErrorCode = ErrorCode.INTERNAL_ERROR;
            ErrorCode streamErrorCode2 = ErrorCode.INTERNAL_ERROR;
            IOException errorException = null;
            try {
                this.reader.readConnectionPreface(this);
                while (this.reader.nextFrame(false, this)) {
                }
                connectionErrorCode = ErrorCode.NO_ERROR;
                streamErrorCode = ErrorCode.CANCEL;
            } catch (IOException e) {
                errorException = e;
                connectionErrorCode = ErrorCode.PROTOCOL_ERROR;
                streamErrorCode = ErrorCode.PROTOCOL_ERROR;
            } catch (Throwable th) {
                this.this$0.close$okhttp(connectionErrorCode, streamErrorCode2, errorException);
                Util.closeQuietly((Closeable) this.reader);
                throw th;
            }
            this.this$0.close$okhttp(connectionErrorCode, streamErrorCode, errorException);
            Util.closeQuietly((Closeable) this.reader);
        }

        public void data(boolean inFinished, int streamId, BufferedSource source, int length) throws IOException {
            Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
            if (this.this$0.pushedStream$okhttp(streamId)) {
                this.this$0.pushDataLater$okhttp(streamId, source, length, inFinished);
                return;
            }
            Http2Stream dataStream = this.this$0.getStream(streamId);
            if (dataStream == null) {
                this.this$0.writeSynResetLater$okhttp(streamId, ErrorCode.PROTOCOL_ERROR);
                this.this$0.updateConnectionFlowControl$okhttp((long) length);
                source.skip((long) length);
                return;
            }
            dataStream.receiveData(source, length);
            if (inFinished) {
                dataStream.receiveHeaders(Util.EMPTY_HEADERS, true);
            }
        }

        public void headers(boolean inFinished, int streamId, int associatedStreamId, List<Header> headerBlock) {
            Http2Connection http2Connection;
            boolean z = inFinished;
            int i = streamId;
            List<Header> list = headerBlock;
            Intrinsics.checkParameterIsNotNull(list, "headerBlock");
            if (this.this$0.pushedStream$okhttp(i)) {
                this.this$0.pushHeadersLater$okhttp(i, list, z);
                return;
            }
            Http2Connection http2Connection2 = this.this$0;
            synchronized (http2Connection2) {
                try {
                    Http2Stream stream = this.this$0.getStream(i);
                    if (stream == null) {
                        try {
                            if (!this.this$0.isShutdown()) {
                                if (i > this.this$0.getLastGoodStreamId$okhttp()) {
                                    if (i % 2 != this.this$0.getNextStreamId$okhttp() % 2) {
                                        int i2 = streamId;
                                        Http2Stream http2Stream = new Http2Stream(i2, this.this$0, false, inFinished, Util.toHeaders(headerBlock));
                                        Http2Stream newStream = http2Stream;
                                        this.this$0.setLastGoodStreamId$okhttp(i);
                                        this.this$0.getStreams$okhttp().put(Integer.valueOf(streamId), newStream);
                                        Executor access$getListenerExecutor$cp = Http2Connection.listenerExecutor;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("OkHttp ");
                                        sb.append(this.this$0.getConnectionName$okhttp());
                                        sb.append(" stream ");
                                        sb.append(i);
                                        Executor $this$execute$iv = access$getListenerExecutor$cp;
                                        r1 = r1;
                                        http2Connection = http2Connection2;
                                        boolean z2 = z;
                                        try {
                                            C1325xe89ff7cd http2Connection$ReaderRunnable$headers$$inlined$synchronized$lambda$1 = new C1325xe89ff7cd(sb.toString(), newStream, this, stream, streamId, headerBlock, inFinished);
                                            $this$execute$iv.execute(http2Connection$ReaderRunnable$headers$$inlined$synchronized$lambda$1);
                                        } catch (Throwable th) {
                                            th = th;
                                        }
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            Http2Stream http2Stream2 = stream;
                            http2Connection = http2Connection2;
                            boolean z3 = z;
                            throw th;
                        }
                    } else {
                        Http2Stream stream2 = stream;
                        http2Connection = http2Connection2;
                        boolean z4 = z;
                        Unit unit = Unit.INSTANCE;
                        stream2.receiveHeaders(Util.toHeaders(headerBlock), z4);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    http2Connection = http2Connection2;
                    boolean z5 = z;
                    throw th;
                }
            }
        }

        public void rstStream(int streamId, ErrorCode errorCode) {
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            if (this.this$0.pushedStream$okhttp(streamId)) {
                this.this$0.pushResetLater$okhttp(streamId, errorCode);
                return;
            }
            Http2Stream rstStream = this.this$0.removeStream$okhttp(streamId);
            if (rstStream != null) {
                rstStream.receiveRstStream(errorCode);
            }
        }

        public void settings(boolean clearPrevious, Settings settings) {
            Intrinsics.checkParameterIsNotNull(settings, "settings");
            Executor $this$tryExecute$iv = this.this$0.writerExecutor;
            StringBuilder sb = new StringBuilder();
            sb.append("OkHttp ");
            sb.append(this.this$0.getConnectionName$okhttp());
            sb.append(" ACK Settings");
            try {
                $this$tryExecute$iv.execute(new Http2Connection$ReaderRunnable$settings$$inlined$tryExecute$1(sb.toString(), this, clearPrevious, settings));
            } catch (RejectedExecutionException e) {
            }
        }

        public final void applyAndAckSettings(boolean clearPrevious, Settings settings) {
            int i;
            Settings settings2 = settings;
            Intrinsics.checkParameterIsNotNull(settings2, "settings");
            long delta = 0;
            Http2Stream[] http2StreamArr = null;
            Http2Stream[] http2StreamArr2 = null;
            synchronized (this.this$0.getWriter()) {
                synchronized (this.this$0) {
                    int priorWriteWindowSize = this.this$0.getPeerSettings().getInitialWindowSize();
                    if (clearPrevious) {
                        this.this$0.getPeerSettings().clear();
                    }
                    this.this$0.getPeerSettings().merge(settings2);
                    int peerInitialWindowSize = this.this$0.getPeerSettings().getInitialWindowSize();
                    if (!(peerInitialWindowSize == -1 || peerInitialWindowSize == priorWriteWindowSize)) {
                        delta = (long) (peerInitialWindowSize - priorWriteWindowSize);
                        if (!this.this$0.getStreams$okhttp().isEmpty()) {
                            Object[] array = this.this$0.getStreams$okhttp().values().toArray(new Http2Stream[0]);
                            if (array != null) {
                                http2StreamArr = (Http2Stream[]) array;
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                        }
                        http2StreamArr2 = http2StreamArr;
                    }
                    Unit unit = Unit.INSTANCE;
                }
                try {
                    this.this$0.getWriter().applyAndAckSettings(this.this$0.getPeerSettings());
                } catch (IOException e) {
                    this.this$0.failConnection(e);
                }
                Unit unit2 = Unit.INSTANCE;
            }
            if (http2StreamArr2 != null) {
                if (http2StreamArr2 == null) {
                    Intrinsics.throwNpe();
                }
                for (Http2Stream stream : http2StreamArr2) {
                    synchronized (stream) {
                        stream.addBytesToWriteWindow(delta);
                        Unit unit3 = Unit.INSTANCE;
                    }
                }
            }
            Executor $this$execute$iv = Http2Connection.listenerExecutor;
            StringBuilder sb = new StringBuilder();
            sb.append("OkHttp ");
            sb.append(this.this$0.getConnectionName$okhttp());
            sb.append(" settings");
            $this$execute$iv.execute(new C1324x5de897ce(sb.toString(), this));
        }

        public void ackSettings() {
        }

        public void ping(boolean ack, int payload1, int payload2) {
            if (ack) {
                synchronized (this.this$0) {
                    this.this$0.awaitingPong = false;
                    Http2Connection http2Connection = this.this$0;
                    if (http2Connection != null) {
                        http2Connection.notifyAll();
                        Unit unit = Unit.INSTANCE;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                    }
                }
                return;
            }
            Executor $this$tryExecute$iv = this.this$0.writerExecutor;
            StringBuilder sb = new StringBuilder();
            sb.append("OkHttp ");
            sb.append(this.this$0.getConnectionName$okhttp());
            sb.append(" ping");
            try {
                $this$tryExecute$iv.execute(new Http2Connection$ReaderRunnable$ping$$inlined$tryExecute$1(sb.toString(), this, payload1, payload2));
            } catch (RejectedExecutionException e) {
            }
        }

        public void goAway(int lastGoodStreamId, ErrorCode errorCode, ByteString debugData) {
            int i;
            Http2Stream[] streamsCopy;
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            Intrinsics.checkParameterIsNotNull(debugData, "debugData");
            int size = debugData.size();
            synchronized (this.this$0) {
                Object[] array = this.this$0.getStreams$okhttp().values().toArray(new Http2Stream[0]);
                if (array != null) {
                    streamsCopy = (Http2Stream[]) array;
                    this.this$0.setShutdown$okhttp(true);
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
            for (Http2Stream http2Stream : streamsCopy) {
                if (http2Stream.getId() > lastGoodStreamId && http2Stream.isLocallyInitiated()) {
                    http2Stream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    this.this$0.removeStream$okhttp(http2Stream.getId());
                }
            }
        }

        public void windowUpdate(int streamId, long windowSizeIncrement) {
            if (streamId == 0) {
                synchronized (this.this$0) {
                    Http2Connection http2Connection = this.this$0;
                    http2Connection.writeBytesMaximum = http2Connection.getWriteBytesMaximum() + windowSizeIncrement;
                    Http2Connection http2Connection2 = this.this$0;
                    if (http2Connection2 != null) {
                        http2Connection2.notifyAll();
                        Unit unit = Unit.INSTANCE;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                    }
                }
                return;
            }
            Http2Stream stream = this.this$0.getStream(streamId);
            if (stream != null) {
                synchronized (stream) {
                    stream.addBytesToWriteWindow(windowSizeIncrement);
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }

        public void priority(int streamId, int streamDependency, int weight, boolean exclusive) {
        }

        public void pushPromise(int streamId, int promisedStreamId, List<Header> requestHeaders) {
            Intrinsics.checkParameterIsNotNull(requestHeaders, "requestHeaders");
            this.this$0.pushRequestLater$okhttp(promisedStreamId, requestHeaders);
        }

        public void alternateService(int streamId, String origin, ByteString protocol, String host, int port, long maxAge) {
            Intrinsics.checkParameterIsNotNull(origin, "origin");
            Intrinsics.checkParameterIsNotNull(protocol, "protocol");
            Intrinsics.checkParameterIsNotNull(host, "host");
        }
    }

    public final void start() throws IOException {
        start$default(this, false, 1, null);
    }

    public Http2Connection(Builder builder) {
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        this.client = builder.getClient$okhttp();
        this.listener = builder.getListener$okhttp();
        this.connectionName = builder.getConnectionName$okhttp();
        this.nextStreamId = builder.getClient$okhttp() ? 3 : 2;
        this.writerExecutor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(Util.format("OkHttp %s Writer", this.connectionName), false));
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory(Util.format("OkHttp %s Push Observer", this.connectionName), true));
        this.pushExecutor = threadPoolExecutor;
        this.pushObserver = builder.getPushObserver$okhttp();
        Settings settings = new Settings();
        Settings $this$apply = settings;
        if (builder.getClient$okhttp()) {
            $this$apply.set(7, 16777216);
        }
        this.okHttpSettings = settings;
        Settings settings2 = new Settings();
        Settings $this$apply2 = settings2;
        $this$apply2.set(7, 65535);
        $this$apply2.set(5, 16384);
        this.peerSettings = settings2;
        this.writeBytesMaximum = (long) this.peerSettings.getInitialWindowSize();
        this.socket = builder.getSocket$okhttp();
        this.writer = new Http2Writer(builder.getSink$okhttp(), this.client);
        this.readerRunnable = new ReaderRunnable(this, new Http2Reader(builder.getSource$okhttp(), this.client));
        this.currentPushRequests = new LinkedHashSet();
        if (builder.getPingIntervalMillis$okhttp() != 0) {
            this.writerExecutor.scheduleAtFixedRate(new Runnable(this) {
                final /* synthetic */ Http2Connection this$0;

                {
                    this.this$0 = r1;
                }

                public final void run() {
                    StringBuilder sb = new StringBuilder();
                    sb.append("OkHttp ");
                    sb.append(this.this$0.getConnectionName$okhttp());
                    sb.append(" ping");
                    String name$iv = sb.toString();
                    Thread currentThread$iv = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread$iv, "currentThread");
                    String oldName$iv = currentThread$iv.getName();
                    currentThread$iv.setName(name$iv);
                    try {
                        this.this$0.writePing(false, 0, 0);
                    } finally {
                        currentThread$iv.setName(oldName$iv);
                    }
                }
            }, (long) builder.getPingIntervalMillis$okhttp(), (long) builder.getPingIntervalMillis$okhttp(), TimeUnit.MILLISECONDS);
        }
    }

    public final boolean getClient$okhttp() {
        return this.client;
    }

    public final Listener getListener$okhttp() {
        return this.listener;
    }

    public final Map<Integer, Http2Stream> getStreams$okhttp() {
        return this.streams;
    }

    public final String getConnectionName$okhttp() {
        return this.connectionName;
    }

    public final int getLastGoodStreamId$okhttp() {
        return this.lastGoodStreamId;
    }

    public final void setLastGoodStreamId$okhttp(int i) {
        this.lastGoodStreamId = i;
    }

    public final int getNextStreamId$okhttp() {
        return this.nextStreamId;
    }

    public final void setNextStreamId$okhttp(int i) {
        this.nextStreamId = i;
    }

    public final synchronized boolean isShutdown() {
        return this.isShutdown;
    }

    public final void setShutdown$okhttp(boolean z) {
        this.isShutdown = z;
    }

    public final Settings getOkHttpSettings() {
        return this.okHttpSettings;
    }

    public final Settings getPeerSettings() {
        return this.peerSettings;
    }

    public final long getReadBytesTotal() {
        return this.readBytesTotal;
    }

    public final long getReadBytesAcknowledged() {
        return this.readBytesAcknowledged;
    }

    public final long getWriteBytesTotal() {
        return this.writeBytesTotal;
    }

    public final long getWriteBytesMaximum() {
        return this.writeBytesMaximum;
    }

    public final Socket getSocket$okhttp() {
        return this.socket;
    }

    public final Http2Writer getWriter() {
        return this.writer;
    }

    public final ReaderRunnable getReaderRunnable() {
        return this.readerRunnable;
    }

    public final synchronized int openStreamCount() {
        return this.streams.size();
    }

    public final synchronized Http2Stream getStream(int id) {
        return (Http2Stream) this.streams.get(Integer.valueOf(id));
    }

    public final synchronized Http2Stream removeStream$okhttp(int streamId) {
        Http2Stream stream;
        stream = (Http2Stream) this.streams.remove(Integer.valueOf(streamId));
        notifyAll();
        return stream;
    }

    public final synchronized int maxConcurrentStreams() {
        return this.peerSettings.getMaxConcurrentStreams(Integer.MAX_VALUE);
    }

    public final synchronized void updateConnectionFlowControl$okhttp(long read) {
        this.readBytesTotal += read;
        long readBytesToAcknowledge = this.readBytesTotal - this.readBytesAcknowledged;
        if (readBytesToAcknowledge >= ((long) (this.okHttpSettings.getInitialWindowSize() / 2))) {
            writeWindowUpdateLater$okhttp(0, readBytesToAcknowledge);
            this.readBytesAcknowledged += readBytesToAcknowledge;
        }
    }

    public final Http2Stream pushStream(int associatedStreamId, List<Header> requestHeaders, boolean out) throws IOException {
        Intrinsics.checkParameterIsNotNull(requestHeaders, "requestHeaders");
        if (!this.client) {
            return newStream(associatedStreamId, requestHeaders, out);
        }
        throw new IllegalStateException("Client cannot push requests.".toString());
    }

    public final Http2Stream newStream(List<Header> requestHeaders, boolean out) throws IOException {
        Intrinsics.checkParameterIsNotNull(requestHeaders, "requestHeaders");
        return newStream(0, requestHeaders, out);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0074, code lost:
        if (r8 != 0) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r7.writer.headers(r10, r18, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x007e, code lost:
        r2 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0083, code lost:
        if ((!r7.client) == false) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0085, code lost:
        r7.writer.pushPromise(r8, r2, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x008a, code lost:
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x008e, code lost:
        if (r12 == false) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0090, code lost:
        r7.writer.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0095, code lost:
        return r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00a4, code lost:
        throw new java.lang.IllegalArgumentException("client streams shouldn't have associated stream IDs".toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00a5, code lost:
        r0 = th;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0068 A[SYNTHETIC, Splitter:B:35:0x0068] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final okhttp3.internal.http2.Http2Stream newStream(int r20, java.util.List<okhttp3.internal.http2.Header> r21, boolean r22) throws java.io.IOException {
        /*
            r19 = this;
            r7 = r19
            r8 = r20
            r9 = r21
            r0 = r22 ^ 1
            r10 = r0
            r11 = 0
            r12 = 0
            r13 = 0
            r1 = 0
            okhttp3.internal.http2.Http2Writer r14 = r7.writer
            monitor-enter(r14)
            r15 = 0
            monitor-enter(r19)     // Catch:{ all -> 0x00bf }
            r0 = 0
            int r2 = r7.nextStreamId     // Catch:{ all -> 0x00bc }
            r3 = 1073741823(0x3fffffff, float:1.9999999)
            if (r2 <= r3) goto L_0x001f
            okhttp3.internal.http2.ErrorCode r2 = okhttp3.internal.http2.ErrorCode.REFUSED_STREAM     // Catch:{ all -> 0x00bc }
            r7.shutdown(r2)     // Catch:{ all -> 0x00bc }
        L_0x001f:
            boolean r2 = r7.isShutdown     // Catch:{ all -> 0x00bc }
            if (r2 != 0) goto L_0x00b4
            int r2 = r7.nextStreamId     // Catch:{ all -> 0x00bc }
            r6 = r2
            int r1 = r7.nextStreamId     // Catch:{ all -> 0x00b0 }
            int r1 = r1 + 2
            r7.nextStreamId = r1     // Catch:{ all -> 0x00b0 }
            okhttp3.internal.http2.Http2Stream r16 = new okhttp3.internal.http2.Http2Stream     // Catch:{ all -> 0x00b0 }
            r17 = 0
            r1 = r16
            r2 = r6
            r3 = r19
            r4 = r10
            r5 = r11
            r18 = r6
            r6 = r17
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00ab }
            r13 = r16
            r1 = 1
            if (r22 == 0) goto L_0x0060
            long r2 = r7.writeBytesTotal     // Catch:{ all -> 0x005c }
            long r4 = r7.writeBytesMaximum     // Catch:{ all -> 0x005c }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0060
            long r2 = r13.getWriteBytesTotal()     // Catch:{ all -> 0x005c }
            long r4 = r13.getWriteBytesMaximum()     // Catch:{ all -> 0x005c }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x005a
            goto L_0x0060
        L_0x005a:
            r2 = 0
            goto L_0x0061
        L_0x005c:
            r0 = move-exception
            r1 = r18
            goto L_0x00bd
        L_0x0060:
            r2 = 1
        L_0x0061:
            r12 = r2
            boolean r2 = r13.isOpen()     // Catch:{ all -> 0x00ab }
            if (r2 == 0) goto L_0x0071
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.Http2Stream> r2 = r7.streams     // Catch:{ all -> 0x005c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r18)     // Catch:{ all -> 0x005c }
            r2.put(r3, r13)     // Catch:{ all -> 0x005c }
        L_0x0071:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00ab }
            monitor-exit(r19)     // Catch:{ all -> 0x00a7 }
            if (r8 != 0) goto L_0x007e
            okhttp3.internal.http2.Http2Writer r0 = r7.writer     // Catch:{ all -> 0x00a7 }
            r2 = r18
            r0.headers(r10, r2, r9)     // Catch:{ all -> 0x00a5 }
            goto L_0x008a
        L_0x007e:
            r2 = r18
            boolean r0 = r7.client     // Catch:{ all -> 0x00a5 }
            r0 = r0 ^ r1
            if (r0 == 0) goto L_0x0096
            okhttp3.internal.http2.Http2Writer r0 = r7.writer     // Catch:{ all -> 0x00a5 }
            r0.pushPromise(r8, r2, r9)     // Catch:{ all -> 0x00a5 }
        L_0x008a:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00a5 }
            monitor-exit(r14)
            if (r12 == 0) goto L_0x0095
            okhttp3.internal.http2.Http2Writer r0 = r7.writer
            r0.flush()
        L_0x0095:
            return r13
        L_0x0096:
            r0 = 0
            java.lang.String r1 = "client streams shouldn't have associated stream IDs"
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00a5 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00a5 }
            r0.<init>(r1)     // Catch:{ all -> 0x00a5 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x00a5 }
            throw r0     // Catch:{ all -> 0x00a5 }
        L_0x00a5:
            r0 = move-exception
            goto L_0x00c1
        L_0x00a7:
            r0 = move-exception
            r2 = r18
            goto L_0x00c1
        L_0x00ab:
            r0 = move-exception
            r2 = r18
            r1 = r2
            goto L_0x00bd
        L_0x00b0:
            r0 = move-exception
            r2 = r6
            r1 = r2
            goto L_0x00bd
        L_0x00b4:
            okhttp3.internal.http2.ConnectionShutdownException r2 = new okhttp3.internal.http2.ConnectionShutdownException     // Catch:{ all -> 0x00bc }
            r2.<init>()     // Catch:{ all -> 0x00bc }
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch:{ all -> 0x00bc }
            throw r2     // Catch:{ all -> 0x00bc }
        L_0x00bc:
            r0 = move-exception
        L_0x00bd:
            monitor-exit(r19)     // Catch:{ all -> 0x00bf }
            throw r0     // Catch:{ all -> 0x00bf }
        L_0x00bf:
            r0 = move-exception
            r2 = r1
        L_0x00c1:
            monitor-exit(r14)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.newStream(int, java.util.List, boolean):okhttp3.internal.http2.Http2Stream");
    }

    public final void writeHeaders$okhttp(int streamId, boolean outFinished, List<Header> alternating) throws IOException {
        Intrinsics.checkParameterIsNotNull(alternating, "alternating");
        this.writer.headers(outFinished, streamId, alternating);
    }

    public final void writeData(int streamId, boolean outFinished, Buffer buffer, long byteCount) throws IOException {
        int i = streamId;
        boolean z = outFinished;
        Buffer buffer2 = buffer;
        if (byteCount == 0) {
            this.writer.data(z, i, buffer2, 0);
            return;
        }
        long byteCount2 = byteCount;
        while (byteCount2 > 0) {
            IntRef toWrite = new IntRef();
            synchronized (this) {
                while (this.writeBytesTotal >= this.writeBytesMaximum) {
                    try {
                        if (this.streams.containsKey(Integer.valueOf(streamId))) {
                            wait();
                        } else {
                            throw new IOException("stream closed");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                }
                toWrite.element = (int) Math.min(byteCount2, this.writeBytesMaximum - this.writeBytesTotal);
                toWrite.element = Math.min(toWrite.element, this.writer.maxDataLength());
                this.writeBytesTotal += (long) toWrite.element;
                Unit unit = Unit.INSTANCE;
            }
            byteCount2 -= (long) toWrite.element;
            this.writer.data(z && byteCount2 == 0, i, buffer2, toWrite.element);
        }
    }

    public final void writeSynResetLater$okhttp(int streamId, ErrorCode errorCode) {
        Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
        Executor $this$tryExecute$iv = this.writerExecutor;
        StringBuilder sb = new StringBuilder();
        sb.append("OkHttp ");
        sb.append(this.connectionName);
        sb.append(" stream ");
        sb.append(streamId);
        try {
            $this$tryExecute$iv.execute(new Http2Connection$writeSynResetLater$$inlined$tryExecute$1(sb.toString(), this, streamId, errorCode));
        } catch (RejectedExecutionException e) {
        }
    }

    public final void writeSynReset$okhttp(int streamId, ErrorCode statusCode) throws IOException {
        Intrinsics.checkParameterIsNotNull(statusCode, "statusCode");
        this.writer.rstStream(streamId, statusCode);
    }

    public final void writeWindowUpdateLater$okhttp(int streamId, long unacknowledgedBytesRead) {
        Executor $this$tryExecute$iv = this.writerExecutor;
        StringBuilder sb = new StringBuilder();
        sb.append("OkHttp Window Update ");
        sb.append(this.connectionName);
        sb.append(" stream ");
        sb.append(streamId);
        Executor $this$execute$iv$iv = $this$tryExecute$iv;
        try {
            Http2Connection$writeWindowUpdateLater$$inlined$tryExecute$1 http2Connection$writeWindowUpdateLater$$inlined$tryExecute$1 = new Http2Connection$writeWindowUpdateLater$$inlined$tryExecute$1(sb.toString(), this, streamId, unacknowledgedBytesRead);
            $this$execute$iv$iv.execute(http2Connection$writeWindowUpdateLater$$inlined$tryExecute$1);
        } catch (RejectedExecutionException e) {
        }
    }

    public final void writePing(boolean reply, int payload1, int payload2) {
        boolean failedDueToMissingPong;
        if (!reply) {
            synchronized (this) {
                failedDueToMissingPong = this.awaitingPong;
                this.awaitingPong = true;
                Unit unit = Unit.INSTANCE;
            }
            if (failedDueToMissingPong) {
                failConnection(null);
                return;
            }
        }
        try {
            this.writer.ping(reply, payload1, payload2);
        } catch (IOException e) {
            failConnection(e);
        }
    }

    public final void writePingAndAwaitPong() throws InterruptedException {
        writePing(false, 1330343787, -257978967);
        awaitPong();
    }

    public final synchronized void awaitPong() throws InterruptedException {
        while (this.awaitingPong) {
            wait();
        }
    }

    public final void flush() throws IOException {
        this.writer.flush();
    }

    public final void shutdown(ErrorCode statusCode) throws IOException {
        Intrinsics.checkParameterIsNotNull(statusCode, "statusCode");
        synchronized (this.writer) {
            synchronized (this) {
                if (!this.isShutdown) {
                    this.isShutdown = true;
                    int lastGoodStreamId2 = this.lastGoodStreamId;
                    Unit unit = Unit.INSTANCE;
                    this.writer.goAway(lastGoodStreamId2, statusCode, Util.EMPTY_BYTE_ARRAY);
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
    }

    public void close() {
        close$okhttp(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
    }

    public final void close$okhttp(ErrorCode connectionCode, ErrorCode streamCode, IOException cause) {
        int i;
        Intrinsics.checkParameterIsNotNull(connectionCode, "connectionCode");
        Intrinsics.checkParameterIsNotNull(streamCode, "streamCode");
        boolean z = !Thread.holdsLock(this);
        if (!_Assertions.ENABLED || z) {
            try {
                shutdown(connectionCode);
            } catch (IOException e) {
            }
            Http2Stream[] http2StreamArr = null;
            synchronized (this) {
                if (!this.streams.isEmpty()) {
                    Object[] array = this.streams.values().toArray(new Http2Stream[0]);
                    if (array != null) {
                        http2StreamArr = (Http2Stream[]) array;
                        this.streams.clear();
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
            if (http2StreamArr != null) {
                for (Http2Stream stream : http2StreamArr) {
                    try {
                        stream.close(streamCode, cause);
                    } catch (IOException e2) {
                    }
                }
            }
            try {
                this.writer.close();
            } catch (IOException e3) {
            }
            try {
                this.socket.close();
            } catch (IOException e4) {
            }
            this.writerExecutor.shutdown();
            this.pushExecutor.shutdown();
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    /* access modifiers changed from: private */
    public final void failConnection(IOException e) {
        close$okhttp(ErrorCode.PROTOCOL_ERROR, ErrorCode.PROTOCOL_ERROR, e);
    }

    public static /* synthetic */ void start$default(Http2Connection http2Connection, boolean z, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            z = true;
        }
        http2Connection.start(z);
    }

    public final void start(boolean sendConnectionPreface) throws IOException {
        if (sendConnectionPreface) {
            this.writer.connectionPreface();
            this.writer.settings(this.okHttpSettings);
            int windowSize = this.okHttpSettings.getInitialWindowSize();
            if (windowSize != 65535) {
                this.writer.windowUpdate(0, (long) (windowSize - 65535));
            }
        }
        Runnable runnable = this.readerRunnable;
        StringBuilder sb = new StringBuilder();
        sb.append("OkHttp ");
        sb.append(this.connectionName);
        new Thread(runnable, sb.toString()).start();
    }

    public final void setSettings(Settings settings) throws IOException {
        Intrinsics.checkParameterIsNotNull(settings, "settings");
        synchronized (this.writer) {
            synchronized (this) {
                if (!this.isShutdown) {
                    this.okHttpSettings.merge(settings);
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new ConnectionShutdownException();
                }
            }
            this.writer.settings(settings);
            Unit unit2 = Unit.INSTANCE;
        }
    }

    public final boolean pushedStream$okhttp(int streamId) {
        return streamId != 0 && (streamId & 1) == 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        if (r6.isShutdown != false) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        r0 = r6.pushExecutor;
        r1 = new java.lang.StringBuilder();
        r1.append("OkHttp ");
        r1.append(r6.connectionName);
        r1.append(" Push Request[");
        r1.append(r7);
        r1.append(']');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.execute(new okhttp3.internal.http2.Http2Connection$pushRequestLater$$inlined$tryExecute$1(r1.toString(), r6, r7, r8));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void pushRequestLater$okhttp(int r7, java.util.List<okhttp3.internal.http2.Header> r8) {
        /*
            r6 = this;
            java.lang.String r0 = "requestHeaders"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            monitor-enter(r6)
            r0 = 0
            java.util.Set<java.lang.Integer> r1 = r6.currentPushRequests     // Catch:{ all -> 0x005e }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x005e }
            boolean r1 = r1.contains(r2)     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x001a
            okhttp3.internal.http2.ErrorCode r1 = okhttp3.internal.http2.ErrorCode.PROTOCOL_ERROR     // Catch:{ all -> 0x005e }
            r6.writeSynResetLater$okhttp(r7, r1)     // Catch:{ all -> 0x005e }
            monitor-exit(r6)
            return
        L_0x001a:
            java.util.Set<java.lang.Integer> r1 = r6.currentPushRequests     // Catch:{ all -> 0x005e }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x005e }
            r1.add(r2)     // Catch:{ all -> 0x005e }
            monitor-exit(r6)
            boolean r0 = r6.isShutdown
            if (r0 != 0) goto L_0x005d
            java.util.concurrent.ThreadPoolExecutor r0 = r6.pushExecutor
            java.util.concurrent.Executor r0 = (java.util.concurrent.Executor) r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "OkHttp "
            r1.append(r2)
            java.lang.String r2 = r6.connectionName
            r1.append(r2)
            java.lang.String r2 = " Push Request["
            r1.append(r2)
            r1.append(r7)
            r2 = 93
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 0
            r3 = r0
            r4 = 0
            okhttp3.internal.http2.Http2Connection$pushRequestLater$$inlined$tryExecute$1 r5 = new okhttp3.internal.http2.Http2Connection$pushRequestLater$$inlined$tryExecute$1     // Catch:{ RejectedExecutionException -> 0x005b }
            r5.<init>(r1, r6, r7, r8)     // Catch:{ RejectedExecutionException -> 0x005b }
            java.lang.Runnable r5 = (java.lang.Runnable) r5     // Catch:{ RejectedExecutionException -> 0x005b }
            r3.execute(r5)     // Catch:{ RejectedExecutionException -> 0x005b }
            goto L_0x005c
        L_0x005b:
            r3 = move-exception
        L_0x005c:
        L_0x005d:
            return
        L_0x005e:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.pushRequestLater$okhttp(int, java.util.List):void");
    }

    public final void pushHeadersLater$okhttp(int streamId, List<Header> requestHeaders, boolean inFinished) {
        Intrinsics.checkParameterIsNotNull(requestHeaders, "requestHeaders");
        if (!this.isShutdown) {
            Executor $this$tryExecute$iv = this.pushExecutor;
            StringBuilder sb = new StringBuilder();
            sb.append("OkHttp ");
            sb.append(this.connectionName);
            sb.append(" Push Headers[");
            sb.append(streamId);
            sb.append(']');
            Executor $this$execute$iv$iv = $this$tryExecute$iv;
            try {
                Http2Connection$pushHeadersLater$$inlined$tryExecute$1 http2Connection$pushHeadersLater$$inlined$tryExecute$1 = new Http2Connection$pushHeadersLater$$inlined$tryExecute$1(sb.toString(), this, streamId, requestHeaders, inFinished);
                $this$execute$iv$iv.execute(http2Connection$pushHeadersLater$$inlined$tryExecute$1);
            } catch (RejectedExecutionException e) {
            }
        }
    }

    public final void pushDataLater$okhttp(int streamId, BufferedSource source, int byteCount, boolean inFinished) throws IOException {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        Buffer buffer = new Buffer();
        source.require((long) byteCount);
        source.read(buffer, (long) byteCount);
        if (!this.isShutdown) {
            Executor $this$execute$iv = this.pushExecutor;
            StringBuilder sb = new StringBuilder();
            sb.append("OkHttp ");
            sb.append(this.connectionName);
            sb.append(" Push Data[");
            sb.append(streamId);
            sb.append(']');
            Http2Connection$pushDataLater$$inlined$execute$1 http2Connection$pushDataLater$$inlined$execute$1 = new Http2Connection$pushDataLater$$inlined$execute$1(sb.toString(), this, streamId, buffer, byteCount, inFinished);
            $this$execute$iv.execute(http2Connection$pushDataLater$$inlined$execute$1);
        }
    }

    public final void pushResetLater$okhttp(int streamId, ErrorCode errorCode) {
        Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
        if (!this.isShutdown) {
            Executor $this$execute$iv = this.pushExecutor;
            StringBuilder sb = new StringBuilder();
            sb.append("OkHttp ");
            sb.append(this.connectionName);
            sb.append(" Push Reset[");
            sb.append(streamId);
            sb.append(']');
            $this$execute$iv.execute(new Http2Connection$pushResetLater$$inlined$execute$1(sb.toString(), this, streamId, errorCode));
        }
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Http2Connection", true));
        listenerExecutor = threadPoolExecutor;
    }
}
