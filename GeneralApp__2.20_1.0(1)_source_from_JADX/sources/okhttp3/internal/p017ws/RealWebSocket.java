package okhttp3.internal.p017ws;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.RealCall;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.p017ws.WebSocketReader.FrameCallback;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import p008cz.msebera.android.httpclient.HttpHeaders;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\u0018\u0000 ]2\u00020\u00012\u00020\u0002:\u0006[\\]^_`B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0016\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020#2\u0006\u00101\u001a\u000202J\b\u00103\u001a\u00020/H\u0016J\u001f\u00104\u001a\u00020/2\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108H\u0000¢\u0006\u0002\b9J\u001a\u0010:\u001a\u00020\r2\u0006\u0010;\u001a\u00020#2\b\u0010<\u001a\u0004\u0018\u00010\u0017H\u0016J \u0010:\u001a\u00020\r2\u0006\u0010;\u001a\u00020#2\b\u0010<\u001a\u0004\u0018\u00010\u00172\u0006\u0010=\u001a\u00020\nJ\u000e\u0010>\u001a\u00020/2\u0006\u0010?\u001a\u00020@J\u001c\u0010A\u001a\u00020/2\n\u0010B\u001a\u00060Cj\u0002`D2\b\u00105\u001a\u0004\u0018\u000106J\u0016\u0010E\u001a\u00020/2\u0006\u0010F\u001a\u00020\u00172\u0006\u0010(\u001a\u00020)J\u0006\u0010G\u001a\u00020/J\u0018\u0010H\u001a\u00020/2\u0006\u0010;\u001a\u00020#2\u0006\u0010<\u001a\u00020\u0017H\u0016J\u0010\u0010I\u001a\u00020/2\u0006\u0010J\u001a\u00020\u0017H\u0016J\u0010\u0010I\u001a\u00020/2\u0006\u0010K\u001a\u00020\u001eH\u0016J\u0010\u0010L\u001a\u00020/2\u0006\u0010M\u001a\u00020\u001eH\u0016J\u0010\u0010N\u001a\u00020/2\u0006\u0010M\u001a\u00020\u001eH\u0016J\u000e\u0010O\u001a\u00020\r2\u0006\u0010M\u001a\u00020\u001eJ\u0006\u0010P\u001a\u00020\rJ\b\u0010\u001f\u001a\u00020\nH\u0016J\u0006\u0010%\u001a\u00020#J\u0006\u0010&\u001a\u00020#J\b\u0010Q\u001a\u00020\u0004H\u0016J\b\u0010R\u001a\u00020/H\u0002J\u0010\u0010S\u001a\u00020\r2\u0006\u0010J\u001a\u00020\u0017H\u0016J\u0010\u0010S\u001a\u00020\r2\u0006\u0010K\u001a\u00020\u001eH\u0016J\u0018\u0010S\u001a\u00020\r2\u0006\u0010T\u001a\u00020\u001e2\u0006\u0010U\u001a\u00020#H\u0002J\u0006\u0010'\u001a\u00020#J\u0006\u0010V\u001a\u00020/J\r\u0010W\u001a\u00020\rH\u0000¢\u0006\u0002\bXJ\r\u0010Y\u001a\u00020/H\u0000¢\u0006\u0002\bZR\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0004¢\u0006\u0002\n\u0000¨\u0006a"}, mo24952d2 = {"Lokhttp3/internal/ws/RealWebSocket;", "Lokhttp3/WebSocket;", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "originalRequest", "Lokhttp3/Request;", "listener", "Lokhttp3/WebSocketListener;", "random", "Ljava/util/Random;", "pingIntervalMillis", "", "(Lokhttp3/Request;Lokhttp3/WebSocketListener;Ljava/util/Random;J)V", "awaitingPong", "", "call", "Lokhttp3/Call;", "cancelFuture", "Ljava/util/concurrent/ScheduledFuture;", "enqueuedClose", "executor", "Ljava/util/concurrent/ScheduledExecutorService;", "failed", "key", "", "getListener$okhttp", "()Lokhttp3/WebSocketListener;", "messageAndCloseQueue", "Ljava/util/ArrayDeque;", "", "pongQueue", "Lokio/ByteString;", "queueSize", "reader", "Lokhttp3/internal/ws/WebSocketReader;", "receivedCloseCode", "", "receivedCloseReason", "receivedPingCount", "receivedPongCount", "sentPingCount", "streams", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "writer", "Lokhttp3/internal/ws/WebSocketWriter;", "writerRunnable", "Ljava/lang/Runnable;", "awaitTermination", "", "timeout", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "cancel", "checkUpgradeSuccess", "response", "Lokhttp3/Response;", "exchange", "Lokhttp3/internal/connection/Exchange;", "checkUpgradeSuccess$okhttp", "close", "code", "reason", "cancelAfterCloseMillis", "connect", "client", "Lokhttp3/OkHttpClient;", "failWebSocket", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "initReaderAndWriter", "name", "loopReader", "onReadClose", "onReadMessage", "text", "bytes", "onReadPing", "payload", "onReadPong", "pong", "processNextFrame", "request", "runWriter", "send", "data", "formatOpcode", "tearDown", "writeOneFrame", "writeOneFrame$okhttp", "writePingFrame", "writePingFrame$okhttp", "CancelRunnable", "Close", "Companion", "Message", "PingRunnable", "Streams", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* renamed from: okhttp3.internal.ws.RealWebSocket */
/* compiled from: RealWebSocket.kt */
public final class RealWebSocket implements WebSocket, FrameCallback {
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    public static final Companion Companion = new Companion(null);
    private static final long MAX_QUEUE_SIZE = 16777216;
    private static final List<Protocol> ONLY_HTTP1 = CollectionsKt.listOf(Protocol.HTTP_1_1);
    private boolean awaitingPong;
    private Call call;
    private ScheduledFuture<?> cancelFuture;
    private boolean enqueuedClose;
    private ScheduledExecutorService executor;
    private boolean failed;
    private final String key;
    private final WebSocketListener listener;
    private final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque<>();
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private final ArrayDeque<ByteString> pongQueue = new ArrayDeque<>();
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode = -1;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private WebSocketWriter writer;
    private final Runnable writerRunnable;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, mo24952d2 = {"Lokhttp3/internal/ws/RealWebSocket$CancelRunnable;", "Ljava/lang/Runnable;", "(Lokhttp3/internal/ws/RealWebSocket;)V", "run", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* renamed from: okhttp3.internal.ws.RealWebSocket$CancelRunnable */
    /* compiled from: RealWebSocket.kt */
    public final class CancelRunnable implements Runnable {
        public CancelRunnable() {
        }

        public void run() {
            RealWebSocket.this.cancel();
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, mo24952d2 = {"Lokhttp3/internal/ws/RealWebSocket$Close;", "", "code", "", "reason", "Lokio/ByteString;", "cancelAfterCloseMillis", "", "(ILokio/ByteString;J)V", "getCancelAfterCloseMillis", "()J", "getCode", "()I", "getReason", "()Lokio/ByteString;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* renamed from: okhttp3.internal.ws.RealWebSocket$Close */
    /* compiled from: RealWebSocket.kt */
    public static final class Close {
        private final long cancelAfterCloseMillis;
        private final int code;
        private final ByteString reason;

        public Close(int code2, ByteString reason2, long cancelAfterCloseMillis2) {
            this.code = code2;
            this.reason = reason2;
            this.cancelAfterCloseMillis = cancelAfterCloseMillis2;
        }

        public final int getCode() {
            return this.code;
        }

        public final ByteString getReason() {
            return this.reason;
        }

        public final long getCancelAfterCloseMillis() {
            return this.cancelAfterCloseMillis;
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, mo24952d2 = {"Lokhttp3/internal/ws/RealWebSocket$Companion;", "", "()V", "CANCEL_AFTER_CLOSE_MILLIS", "", "MAX_QUEUE_SIZE", "ONLY_HTTP1", "", "Lokhttp3/Protocol;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* renamed from: okhttp3.internal.ws.RealWebSocket$Companion */
    /* compiled from: RealWebSocket.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, mo24952d2 = {"Lokhttp3/internal/ws/RealWebSocket$Message;", "", "formatOpcode", "", "data", "Lokio/ByteString;", "(ILokio/ByteString;)V", "getData", "()Lokio/ByteString;", "getFormatOpcode", "()I", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* renamed from: okhttp3.internal.ws.RealWebSocket$Message */
    /* compiled from: RealWebSocket.kt */
    public static final class Message {
        private final ByteString data;
        private final int formatOpcode;

        public Message(int formatOpcode2, ByteString data2) {
            Intrinsics.checkParameterIsNotNull(data2, "data");
            this.formatOpcode = formatOpcode2;
            this.data = data2;
        }

        public final int getFormatOpcode() {
            return this.formatOpcode;
        }

        public final ByteString getData() {
            return this.data;
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, mo24952d2 = {"Lokhttp3/internal/ws/RealWebSocket$PingRunnable;", "Ljava/lang/Runnable;", "(Lokhttp3/internal/ws/RealWebSocket;)V", "run", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* renamed from: okhttp3.internal.ws.RealWebSocket$PingRunnable */
    /* compiled from: RealWebSocket.kt */
    private final class PingRunnable implements Runnable {
        public PingRunnable() {
        }

        public void run() {
            RealWebSocket.this.writePingFrame$okhttp();
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, mo24952d2 = {"Lokhttp3/internal/ws/RealWebSocket$Streams;", "Ljava/io/Closeable;", "client", "", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "(ZLokio/BufferedSource;Lokio/BufferedSink;)V", "getClient", "()Z", "getSink", "()Lokio/BufferedSink;", "getSource", "()Lokio/BufferedSource;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* renamed from: okhttp3.internal.ws.RealWebSocket$Streams */
    /* compiled from: RealWebSocket.kt */
    public static abstract class Streams implements Closeable {
        private final boolean client;
        private final BufferedSink sink;
        private final BufferedSource source;

        public Streams(boolean client2, BufferedSource source2, BufferedSink sink2) {
            Intrinsics.checkParameterIsNotNull(source2, Param.SOURCE);
            Intrinsics.checkParameterIsNotNull(sink2, "sink");
            this.client = client2;
            this.source = source2;
            this.sink = sink2;
        }

        public final boolean getClient() {
            return this.client;
        }

        public final BufferedSource getSource() {
            return this.source;
        }

        public final BufferedSink getSink() {
            return this.sink;
        }
    }

    public RealWebSocket(Request originalRequest2, WebSocketListener listener2, Random random2, long pingIntervalMillis2) {
        Intrinsics.checkParameterIsNotNull(originalRequest2, "originalRequest");
        Intrinsics.checkParameterIsNotNull(listener2, CastExtraArgs.LISTENER);
        Intrinsics.checkParameterIsNotNull(random2, "random");
        this.originalRequest = originalRequest2;
        this.listener = listener2;
        this.random = random2;
        this.pingIntervalMillis = pingIntervalMillis2;
        if (Intrinsics.areEqual((Object) "GET", (Object) this.originalRequest.method())) {
            okio.ByteString.Companion companion = ByteString.Companion;
            byte[] bArr = new byte[16];
            this.random.nextBytes(bArr);
            this.key = okio.ByteString.Companion.of$default(companion, bArr, 0, 0, 3, null).base64();
            this.writerRunnable = new Runnable(this) {
                final /* synthetic */ RealWebSocket this$0;

                {
                    this.this$0 = r1;
                }

                public final void run() {
                    do {
                        try {
                        } catch (IOException e) {
                            this.this$0.failWebSocket(e, null);
                            return;
                        }
                    } while (this.this$0.writeOneFrame$okhttp());
                }
            };
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Request must be GET: ");
        sb.append(this.originalRequest.method());
        throw new IllegalArgumentException(sb.toString().toString());
    }

    public final WebSocketListener getListener$okhttp() {
        return this.listener;
    }

    public Request request() {
        return this.originalRequest;
    }

    public synchronized long queueSize() {
        return this.queueSize;
    }

    public void cancel() {
        Call call2 = this.call;
        if (call2 == null) {
            Intrinsics.throwNpe();
        }
        call2.cancel();
    }

    public final void connect(OkHttpClient client) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        OkHttpClient webSocketClient = client.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        Builder newBuilder = this.originalRequest.newBuilder();
        String str = HttpHeaders.UPGRADE;
        Request request = newBuilder.header(str, "websocket").header("Connection", str).header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").build();
        this.call = RealCall.Companion.newRealCall(webSocketClient, request, true);
        Call call2 = this.call;
        if (call2 == null) {
            Intrinsics.throwNpe();
        }
        call2.enqueue(new RealWebSocket$connect$1(this, request));
    }

    public final void checkUpgradeSuccess$okhttp(Response response, Exchange exchange) throws IOException {
        Intrinsics.checkParameterIsNotNull(response, "response");
        if (response.code() == 101) {
            String headerConnection = Response.header$default(response, "Connection", null, 2, null);
            String str = HttpHeaders.UPGRADE;
            if (StringsKt.equals(str, headerConnection, true)) {
                String headerUpgrade = Response.header$default(response, str, null, 2, null);
                if (StringsKt.equals("websocket", headerUpgrade, true)) {
                    String headerAccept = Response.header$default(response, "Sec-WebSocket-Accept", null, 2, null);
                    okio.ByteString.Companion companion = ByteString.Companion;
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.key);
                    sb.append(WebSocketProtocol.ACCEPT_MAGIC);
                    String acceptExpected = companion.encodeUtf8(sb.toString()).sha1().base64();
                    if (true ^ Intrinsics.areEqual((Object) acceptExpected, (Object) headerAccept)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Expected 'Sec-WebSocket-Accept' header value '");
                        sb2.append(acceptExpected);
                        sb2.append("' but was '");
                        sb2.append(headerAccept);
                        sb2.append('\'');
                        throw new ProtocolException(sb2.toString());
                    } else if (exchange == null) {
                        throw new ProtocolException("Web Socket exchange missing: bad interceptor?");
                    }
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Expected 'Upgrade' header value 'websocket' but was '");
                    sb3.append(headerUpgrade);
                    sb3.append('\'');
                    throw new ProtocolException(sb3.toString());
                }
            } else {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Expected 'Connection' header value 'Upgrade' but was '");
                sb4.append(headerConnection);
                sb4.append('\'');
                throw new ProtocolException(sb4.toString());
            }
        } else {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Expected HTTP 101 response but was '");
            sb5.append(response.code());
            sb5.append(' ');
            sb5.append(response.message());
            sb5.append('\'');
            throw new ProtocolException(sb5.toString());
        }
    }

    public final void initReaderAndWriter(String name, Streams streams2) throws IOException {
        String str = name;
        Streams streams3 = streams2;
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(streams3, "streams");
        synchronized (this) {
            this.streams = streams3;
            this.writer = new WebSocketWriter(streams2.getClient(), streams2.getSink(), this.random);
            this.executor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(str, false));
            if (this.pingIntervalMillis != 0) {
                ScheduledExecutorService scheduledExecutorService = this.executor;
                if (scheduledExecutorService == null) {
                    Intrinsics.throwNpe();
                }
                scheduledExecutorService.scheduleAtFixedRate(new PingRunnable(), this.pingIntervalMillis, this.pingIntervalMillis, TimeUnit.MILLISECONDS);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
            Unit unit = Unit.INSTANCE;
        }
        this.reader = new WebSocketReader(streams2.getClient(), streams2.getSource(), this);
    }

    public final void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            WebSocketReader webSocketReader = this.reader;
            if (webSocketReader == null) {
                Intrinsics.throwNpe();
            }
            webSocketReader.processNextFrame();
        }
    }

    public final boolean processNextFrame() throws IOException {
        try {
            WebSocketReader webSocketReader = this.reader;
            if (webSocketReader == null) {
                Intrinsics.throwNpe();
            }
            webSocketReader.processNextFrame();
            if (this.receivedCloseCode == -1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            failWebSocket(e, null);
            return false;
        }
    }

    public final void awaitTermination(int timeout, TimeUnit timeUnit) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService == null) {
            Intrinsics.throwNpe();
        }
        scheduledExecutorService.awaitTermination((long) timeout, timeUnit);
    }

    public final void tearDown() throws InterruptedException {
        ScheduledFuture<?> scheduledFuture = this.cancelFuture;
        if (scheduledFuture != null) {
            if (scheduledFuture == null) {
                Intrinsics.throwNpe();
            }
            scheduledFuture.cancel(false);
        }
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService == null) {
            Intrinsics.throwNpe();
        }
        scheduledExecutorService.shutdown();
        ScheduledExecutorService scheduledExecutorService2 = this.executor;
        if (scheduledExecutorService2 == null) {
            Intrinsics.throwNpe();
        }
        scheduledExecutorService2.awaitTermination(10, TimeUnit.SECONDS);
    }

    public final synchronized int sentPingCount() {
        return this.sentPingCount;
    }

    public final synchronized int receivedPingCount() {
        return this.receivedPingCount;
    }

    public final synchronized int receivedPongCount() {
        return this.receivedPongCount;
    }

    public void onReadMessage(String text) throws IOException {
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.listener.onMessage((WebSocket) this, text);
    }

    public void onReadMessage(ByteString bytes) throws IOException {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        this.listener.onMessage((WebSocket) this, bytes);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onReadPing(okio.ByteString r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.lang.String r0 = "payload"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)     // Catch:{ all -> 0x0029 }
            boolean r0 = r1.failed     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x0027
            boolean r0 = r1.enqueuedClose     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0017
            java.util.ArrayDeque<java.lang.Object> r0 = r1.messageAndCloseQueue     // Catch:{ all -> 0x0029 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0017
            goto L_0x0027
        L_0x0017:
            java.util.ArrayDeque<okio.ByteString> r0 = r1.pongQueue     // Catch:{ all -> 0x0029 }
            r0.add(r2)     // Catch:{ all -> 0x0029 }
            r1.runWriter()     // Catch:{ all -> 0x0029 }
            int r0 = r1.receivedPingCount     // Catch:{ all -> 0x0029 }
            int r0 = r0 + 1
            r1.receivedPingCount = r0     // Catch:{ all -> 0x0029 }
            monitor-exit(r1)
            return
        L_0x0027:
            monitor-exit(r1)
            return
        L_0x0029:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.p017ws.RealWebSocket.onReadPing(okio.ByteString):void");
    }

    public synchronized void onReadPong(ByteString payload) {
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        this.receivedPongCount++;
        this.awaitingPong = false;
    }

    /* JADX INFO: finally extract failed */
    public void onReadClose(int code, String reason) {
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        boolean z = true;
        if (code != -1) {
            Streams streams2 = null;
            synchronized (this) {
                if (this.receivedCloseCode != -1) {
                    z = false;
                }
                if (z) {
                    this.receivedCloseCode = code;
                    this.receivedCloseReason = reason;
                    if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                        streams2 = this.streams;
                        this.streams = null;
                        if (this.cancelFuture != null) {
                            ScheduledFuture<?> scheduledFuture = this.cancelFuture;
                            if (scheduledFuture == null) {
                                Intrinsics.throwNpe();
                            }
                            scheduledFuture.cancel(false);
                        }
                        ScheduledExecutorService scheduledExecutorService = this.executor;
                        if (scheduledExecutorService == null) {
                            Intrinsics.throwNpe();
                        }
                        scheduledExecutorService.shutdown();
                    }
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("already closed".toString());
                }
            }
            try {
                this.listener.onClosing(this, code, reason);
                if (streams2 != null) {
                    this.listener.onClosed(this, code, reason);
                }
                if (streams2 != null) {
                    Util.closeQuietly((Closeable) streams2);
                }
            } catch (Throwable th) {
                if (streams2 != null) {
                    Util.closeQuietly((Closeable) streams2);
                }
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public boolean send(String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        return send(ByteString.Companion.encodeUtf8(text), 1);
    }

    public boolean send(ByteString bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        return send(bytes, 2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean send(okio.ByteString r7, int r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.failed     // Catch:{ all -> 0x003e }
            r1 = 0
            if (r0 != 0) goto L_0x003c
            boolean r0 = r6.enqueuedClose     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x000b
            goto L_0x003c
        L_0x000b:
            long r2 = r6.queueSize     // Catch:{ all -> 0x003e }
            int r0 = r7.size()     // Catch:{ all -> 0x003e }
            long r4 = (long) r0     // Catch:{ all -> 0x003e }
            long r2 = r2 + r4
            r4 = 16777216(0x1000000, double:8.289046E-317)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0022
            r0 = 1001(0x3e9, float:1.403E-42)
            r2 = 0
            r6.close(r0, r2)     // Catch:{ all -> 0x003e }
            monitor-exit(r6)
            return r1
        L_0x0022:
            long r0 = r6.queueSize     // Catch:{ all -> 0x003e }
            int r2 = r7.size()     // Catch:{ all -> 0x003e }
            long r2 = (long) r2     // Catch:{ all -> 0x003e }
            long r0 = r0 + r2
            r6.queueSize = r0     // Catch:{ all -> 0x003e }
            java.util.ArrayDeque<java.lang.Object> r0 = r6.messageAndCloseQueue     // Catch:{ all -> 0x003e }
            okhttp3.internal.ws.RealWebSocket$Message r1 = new okhttp3.internal.ws.RealWebSocket$Message     // Catch:{ all -> 0x003e }
            r1.<init>(r8, r7)     // Catch:{ all -> 0x003e }
            r0.add(r1)     // Catch:{ all -> 0x003e }
            r6.runWriter()     // Catch:{ all -> 0x003e }
            r0 = 1
            monitor-exit(r6)
            return r0
        L_0x003c:
            monitor-exit(r6)
            return r1
        L_0x003e:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.p017ws.RealWebSocket.send(okio.ByteString, int):boolean");
    }

    public final synchronized boolean pong(ByteString payload) {
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        if (!this.failed) {
            if (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty()) {
                this.pongQueue.add(payload);
                runWriter();
                return true;
            }
        }
        return false;
    }

    public boolean close(int code, String reason) {
        return close(code, reason, CANCEL_AFTER_CLOSE_MILLIS);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005e, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean close(int r9, java.lang.String r10, long r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            okhttp3.internal.ws.WebSocketProtocol r0 = okhttp3.internal.p017ws.WebSocketProtocol.INSTANCE     // Catch:{ all -> 0x005f }
            r0.validateCloseCode(r9)     // Catch:{ all -> 0x005f }
            r0 = 0
            okio.ByteString r0 = (okio.ByteString) r0     // Catch:{ all -> 0x005f }
            r1 = 0
            r2 = 1
            if (r10 == 0) goto L_0x0043
            okio.ByteString$Companion r3 = okio.ByteString.Companion     // Catch:{ all -> 0x005f }
            okio.ByteString r3 = r3.encodeUtf8(r10)     // Catch:{ all -> 0x005f }
            r0 = r3
            int r3 = r0.size()     // Catch:{ all -> 0x005f }
            long r3 = (long) r3     // Catch:{ all -> 0x005f }
            r5 = 123(0x7b, double:6.1E-322)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 > 0) goto L_0x0021
            r3 = 1
            goto L_0x0022
        L_0x0021:
            r3 = 0
        L_0x0022:
            if (r3 == 0) goto L_0x0025
            goto L_0x0043
        L_0x0025:
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x005f }
            r2.<init>()     // Catch:{ all -> 0x005f }
            java.lang.String r3 = "reason.size() > 123: "
            r2.append(r3)     // Catch:{ all -> 0x005f }
            r2.append(r10)     // Catch:{ all -> 0x005f }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x005f }
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x005f }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x005f }
            r1.<init>(r2)     // Catch:{ all -> 0x005f }
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x005f }
            throw r1     // Catch:{ all -> 0x005f }
        L_0x0043:
            boolean r3 = r8.failed     // Catch:{ all -> 0x005f }
            if (r3 != 0) goto L_0x005d
            boolean r3 = r8.enqueuedClose     // Catch:{ all -> 0x005f }
            if (r3 == 0) goto L_0x004c
            goto L_0x005d
        L_0x004c:
            r8.enqueuedClose = r2     // Catch:{ all -> 0x005f }
            java.util.ArrayDeque<java.lang.Object> r1 = r8.messageAndCloseQueue     // Catch:{ all -> 0x005f }
            okhttp3.internal.ws.RealWebSocket$Close r3 = new okhttp3.internal.ws.RealWebSocket$Close     // Catch:{ all -> 0x005f }
            r3.<init>(r9, r0, r11)     // Catch:{ all -> 0x005f }
            r1.add(r3)     // Catch:{ all -> 0x005f }
            r8.runWriter()     // Catch:{ all -> 0x005f }
            monitor-exit(r8)
            return r2
        L_0x005d:
            monitor-exit(r8)
            return r1
        L_0x005f:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.p017ws.RealWebSocket.close(int, java.lang.String, long):boolean");
    }

    private final void runWriter() {
        boolean holdsLock = Thread.holdsLock(this);
        if (!_Assertions.ENABLED || holdsLock) {
            ScheduledExecutorService scheduledExecutorService = this.executor;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.execute(this.writerRunnable);
                return;
            }
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r9v3 */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0072, code lost:
        if (r1 == null) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0074, code lost:
        if (r0 != null) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0079, code lost:
        r0.writePong(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x007f, code lost:
        if ((r2 instanceof okhttp3.internal.p017ws.RealWebSocket.Message) == false) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0081, code lost:
        r4 = ((okhttp3.internal.p017ws.RealWebSocket.Message) r2).getData();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008a, code lost:
        if (r0 != null) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008c, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x008f, code lost:
        r7 = okio.Okio.buffer(r0.newMessageSink(((okhttp3.internal.p017ws.RealWebSocket.Message) r2).getFormatOpcode(), (long) r4.size()));
        r7.write(r4);
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00aa, code lost:
        monitor-enter(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r13.queueSize -= (long) r4.size();
        r8 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        monitor-exit(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00bf, code lost:
        if ((r2 instanceof okhttp3.internal.p017ws.RealWebSocket.Close) == false) goto L_0x00ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00c1, code lost:
        r4 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c4, code lost:
        if (r0 != null) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00c6, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00c9, code lost:
        r0.writeClose(r4.getCode(), r4.getReason());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d4, code lost:
        if (r6 == null) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00d6, code lost:
        r7 = r13.listener;
        r8 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00db, code lost:
        if (r5 != null) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00dd, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00e0, code lost:
        r7.onClosed(r8, r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e5, code lost:
        if (r6 == null) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00e7, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00ed, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00f5, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00f6, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00f7, code lost:
        if (r6 != null) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00f9, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00ff, code lost:
        throw r4;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean writeOneFrame$okhttp() throws java.io.IOException {
        /*
            r13 = this;
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = -1
            r4 = 0
            r5 = r4
            java.lang.String r5 = (java.lang.String) r5
            r6 = r4
            okhttp3.internal.ws.RealWebSocket$Streams r6 = (okhttp3.internal.p017ws.RealWebSocket.Streams) r6
            monitor-enter(r13)
            r7 = 0
            boolean r8 = r13.failed     // Catch:{ all -> 0x0100 }
            r9 = 0
            if (r8 == 0) goto L_0x0015
            monitor-exit(r13)
            return r9
        L_0x0015:
            okhttp3.internal.ws.WebSocketWriter r8 = r13.writer     // Catch:{ all -> 0x0100 }
            r0 = r8
            java.util.ArrayDeque<okio.ByteString> r8 = r13.pongQueue     // Catch:{ all -> 0x0100 }
            java.lang.Object r8 = r8.poll()     // Catch:{ all -> 0x0100 }
            okio.ByteString r8 = (okio.ByteString) r8     // Catch:{ all -> 0x0100 }
            r1 = r8
            if (r1 != 0) goto L_0x006d
            java.util.ArrayDeque<java.lang.Object> r8 = r13.messageAndCloseQueue     // Catch:{ all -> 0x0100 }
            java.lang.Object r8 = r8.poll()     // Catch:{ all -> 0x0100 }
            r2 = r8
            boolean r8 = r2 instanceof okhttp3.internal.p017ws.RealWebSocket.Close     // Catch:{ all -> 0x0100 }
            if (r8 == 0) goto L_0x0068
            int r8 = r13.receivedCloseCode     // Catch:{ all -> 0x0100 }
            r3 = r8
            java.lang.String r8 = r13.receivedCloseReason     // Catch:{ all -> 0x0100 }
            r5 = r8
            r8 = -1
            if (r3 == r8) goto L_0x004a
            okhttp3.internal.ws.RealWebSocket$Streams r8 = r13.streams     // Catch:{ all -> 0x0100 }
            r6 = r8
            okhttp3.internal.ws.RealWebSocket$Streams r4 = (okhttp3.internal.p017ws.RealWebSocket.Streams) r4     // Catch:{ all -> 0x0100 }
            r13.streams = r4     // Catch:{ all -> 0x0100 }
            java.util.concurrent.ScheduledExecutorService r4 = r13.executor     // Catch:{ all -> 0x0100 }
            if (r4 != 0) goto L_0x0046
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x0100 }
        L_0x0046:
            r4.shutdown()     // Catch:{ all -> 0x0100 }
            goto L_0x006d
        L_0x004a:
            java.util.concurrent.ScheduledExecutorService r4 = r13.executor     // Catch:{ all -> 0x0100 }
            if (r4 != 0) goto L_0x0051
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x0100 }
        L_0x0051:
            okhttp3.internal.ws.RealWebSocket$CancelRunnable r8 = new okhttp3.internal.ws.RealWebSocket$CancelRunnable     // Catch:{ all -> 0x0100 }
            r8.<init>()     // Catch:{ all -> 0x0100 }
            java.lang.Runnable r8 = (java.lang.Runnable) r8     // Catch:{ all -> 0x0100 }
            r9 = r2
            okhttp3.internal.ws.RealWebSocket$Close r9 = (okhttp3.internal.p017ws.RealWebSocket.Close) r9     // Catch:{ all -> 0x0100 }
            long r9 = r9.getCancelAfterCloseMillis()     // Catch:{ all -> 0x0100 }
            java.util.concurrent.TimeUnit r11 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0100 }
            java.util.concurrent.ScheduledFuture r4 = r4.schedule(r8, r9, r11)     // Catch:{ all -> 0x0100 }
            r13.cancelFuture = r4     // Catch:{ all -> 0x0100 }
            goto L_0x006d
        L_0x0068:
            if (r2 != 0) goto L_0x006d
            monitor-exit(r13)
            return r9
        L_0x006d:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0100 }
            monitor-exit(r13)
            if (r1 == 0) goto L_0x007d
            if (r0 != 0) goto L_0x0079
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x00f6 }
        L_0x0079:
            r0.writePong(r1)     // Catch:{ all -> 0x00f6 }
            goto L_0x00e3
        L_0x007d:
            boolean r4 = r2 instanceof okhttp3.internal.p017ws.RealWebSocket.Message     // Catch:{ all -> 0x00f6 }
            if (r4 == 0) goto L_0x00bd
            r4 = r2
            okhttp3.internal.ws.RealWebSocket$Message r4 = (okhttp3.internal.p017ws.RealWebSocket.Message) r4     // Catch:{ all -> 0x00f6 }
            okio.ByteString r4 = r4.getData()     // Catch:{ all -> 0x00f6 }
            if (r0 != 0) goto L_0x008f
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x00f6 }
        L_0x008f:
            r7 = r2
            okhttp3.internal.ws.RealWebSocket$Message r7 = (okhttp3.internal.p017ws.RealWebSocket.Message) r7     // Catch:{ all -> 0x00f6 }
            int r7 = r7.getFormatOpcode()     // Catch:{ all -> 0x00f6 }
            int r8 = r4.size()     // Catch:{ all -> 0x00f6 }
            long r8 = (long) r8     // Catch:{ all -> 0x00f6 }
            okio.Sink r7 = r0.newMessageSink(r7, r8)     // Catch:{ all -> 0x00f6 }
            okio.BufferedSink r7 = okio.Okio.buffer(r7)     // Catch:{ all -> 0x00f6 }
            r7.write(r4)     // Catch:{ all -> 0x00f6 }
            r7.close()     // Catch:{ all -> 0x00f6 }
            monitor-enter(r13)     // Catch:{ all -> 0x00f6 }
            r8 = 0
            long r9 = r13.queueSize     // Catch:{ all -> 0x00ba }
            int r11 = r4.size()     // Catch:{ all -> 0x00ba }
            long r11 = (long) r11     // Catch:{ all -> 0x00ba }
            long r9 = r9 - r11
            r13.queueSize = r9     // Catch:{ all -> 0x00ba }
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00ba }
            monitor-exit(r13)     // Catch:{ all -> 0x00f6 }
            goto L_0x00e3
        L_0x00ba:
            r8 = move-exception
            monitor-exit(r13)     // Catch:{ all -> 0x00f6 }
            throw r8     // Catch:{ all -> 0x00f6 }
        L_0x00bd:
            boolean r4 = r2 instanceof okhttp3.internal.p017ws.RealWebSocket.Close     // Catch:{ all -> 0x00f6 }
            if (r4 == 0) goto L_0x00ee
            r4 = r2
            okhttp3.internal.ws.RealWebSocket$Close r4 = (okhttp3.internal.p017ws.RealWebSocket.Close) r4     // Catch:{ all -> 0x00f6 }
            if (r0 != 0) goto L_0x00c9
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x00f6 }
        L_0x00c9:
            int r7 = r4.getCode()     // Catch:{ all -> 0x00f6 }
            okio.ByteString r8 = r4.getReason()     // Catch:{ all -> 0x00f6 }
            r0.writeClose(r7, r8)     // Catch:{ all -> 0x00f6 }
            if (r6 == 0) goto L_0x00e3
            okhttp3.WebSocketListener r7 = r13.listener     // Catch:{ all -> 0x00f6 }
            r8 = r13
            okhttp3.WebSocket r8 = (okhttp3.WebSocket) r8     // Catch:{ all -> 0x00f6 }
            if (r5 != 0) goto L_0x00e0
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x00f6 }
        L_0x00e0:
            r7.onClosed(r8, r3, r5)     // Catch:{ all -> 0x00f6 }
        L_0x00e3:
            r4 = 1
            if (r6 == 0) goto L_0x00ed
            r7 = r6
            java.io.Closeable r7 = (java.io.Closeable) r7
            okhttp3.internal.Util.closeQuietly(r7)
        L_0x00ed:
            return r4
        L_0x00ee:
            java.lang.AssertionError r4 = new java.lang.AssertionError     // Catch:{ all -> 0x00f6 }
            r4.<init>()     // Catch:{ all -> 0x00f6 }
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch:{ all -> 0x00f6 }
            throw r4     // Catch:{ all -> 0x00f6 }
        L_0x00f6:
            r4 = move-exception
            if (r6 == 0) goto L_0x00ff
            r7 = r6
            java.io.Closeable r7 = (java.io.Closeable) r7
            okhttp3.internal.Util.closeQuietly(r7)
        L_0x00ff:
            throw r4
        L_0x0100:
            r4 = move-exception
            monitor-exit(r13)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.p017ws.RealWebSocket.writeOneFrame$okhttp():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
        if (r1 == -1) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        r4 = new java.lang.StringBuilder();
        r4.append("sent ping but didn't receive pong within ");
        r4.append(r7.pingIntervalMillis);
        r4.append("ms (after ");
        r4.append(r1 - 1);
        r4.append(" successful ping/pongs)");
        failWebSocket(new java.net.SocketTimeoutException(r4.toString()), null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0054, code lost:
        if (r0 != null) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0059, code lost:
        r0.writePing(okio.ByteString.EMPTY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0060, code lost:
        failWebSocket(r3, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0067, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void writePingFrame$okhttp() {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
            monitor-enter(r7)
            r2 = 0
            boolean r3 = r7.failed     // Catch:{ all -> 0x0068 }
            if (r3 == 0) goto L_0x000a
            monitor-exit(r7)
            return
        L_0x000a:
            okhttp3.internal.ws.WebSocketWriter r3 = r7.writer     // Catch:{ all -> 0x0068 }
            r0 = r3
            boolean r3 = r7.awaitingPong     // Catch:{ all -> 0x0068 }
            r4 = -1
            if (r3 == 0) goto L_0x0015
            int r3 = r7.sentPingCount     // Catch:{ all -> 0x0068 }
            goto L_0x0016
        L_0x0015:
            r3 = -1
        L_0x0016:
            r1 = r3
            int r3 = r7.sentPingCount     // Catch:{ all -> 0x0068 }
            r5 = 1
            int r3 = r3 + r5
            r7.sentPingCount = r3     // Catch:{ all -> 0x0068 }
            r7.awaitingPong = r5     // Catch:{ all -> 0x0068 }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0068 }
            monitor-exit(r7)
            r2 = 0
            if (r1 == r4) goto L_0x0053
            java.net.SocketTimeoutException r3 = new java.net.SocketTimeoutException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "sent ping but didn't receive pong within "
            r4.append(r5)
            long r5 = r7.pingIntervalMillis
            r4.append(r5)
            java.lang.String r5 = "ms (after "
            r4.append(r5)
            int r5 = r1 + -1
            r4.append(r5)
            java.lang.String r5 = " successful ping/pongs)"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            java.lang.Exception r3 = (java.lang.Exception) r3
            r7.failWebSocket(r3, r2)
            return
        L_0x0053:
            if (r0 != 0) goto L_0x0059
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ IOException -> 0x005f }
        L_0x0059:
            okio.ByteString r3 = okio.ByteString.EMPTY     // Catch:{ IOException -> 0x005f }
            r0.writePing(r3)     // Catch:{ IOException -> 0x005f }
            goto L_0x0066
        L_0x005f:
            r3 = move-exception
            r4 = r3
            java.lang.Exception r4 = (java.lang.Exception) r4
            r7.failWebSocket(r4, r2)
        L_0x0066:
            return
        L_0x0068:
            r2 = move-exception
            monitor-exit(r7)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.p017ws.RealWebSocket.writePingFrame$okhttp():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r4.listener.onFailure(r4, r5, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0040, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0041, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0042, code lost:
        if (r0 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0044, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004a, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void failWebSocket(java.lang.Exception r5, okhttp3.Response r6) {
        /*
            r4 = this;
            java.lang.String r0 = "e"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            r0 = 0
            monitor-enter(r4)
            r1 = 0
            boolean r2 = r4.failed     // Catch:{ all -> 0x004b }
            if (r2 == 0) goto L_0x000e
            monitor-exit(r4)
            return
        L_0x000e:
            r2 = 1
            r4.failed = r2     // Catch:{ all -> 0x004b }
            okhttp3.internal.ws.RealWebSocket$Streams r2 = r4.streams     // Catch:{ all -> 0x004b }
            r0 = r2
            r2 = 0
            okhttp3.internal.ws.RealWebSocket$Streams r2 = (okhttp3.internal.p017ws.RealWebSocket.Streams) r2     // Catch:{ all -> 0x004b }
            r4.streams = r2     // Catch:{ all -> 0x004b }
            java.util.concurrent.ScheduledFuture<?> r2 = r4.cancelFuture     // Catch:{ all -> 0x004b }
            if (r2 == 0) goto L_0x0021
            r3 = 0
            r2.cancel(r3)     // Catch:{ all -> 0x004b }
        L_0x0021:
            java.util.concurrent.ScheduledExecutorService r2 = r4.executor     // Catch:{ all -> 0x004b }
            if (r2 == 0) goto L_0x002a
            r2.shutdown()     // Catch:{ all -> 0x004b }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004b }
        L_0x002a:
            monitor-exit(r4)
            okhttp3.WebSocketListener r1 = r4.listener     // Catch:{ all -> 0x0041 }
            r2 = r4
            okhttp3.WebSocket r2 = (okhttp3.WebSocket) r2     // Catch:{ all -> 0x0041 }
            r3 = r5
            java.lang.Throwable r3 = (java.lang.Throwable) r3     // Catch:{ all -> 0x0041 }
            r1.onFailure(r2, r3, r6)     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x003f
            r1 = r0
            java.io.Closeable r1 = (java.io.Closeable) r1
            okhttp3.internal.Util.closeQuietly(r1)
        L_0x003f:
            return
        L_0x0041:
            r1 = move-exception
            if (r0 == 0) goto L_0x004a
            r2 = r0
            java.io.Closeable r2 = (java.io.Closeable) r2
            okhttp3.internal.Util.closeQuietly(r2)
        L_0x004a:
            throw r1
        L_0x004b:
            r1 = move-exception
            monitor-exit(r4)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.p017ws.RealWebSocket.failWebSocket(java.lang.Exception, okhttp3.Response):void");
    }
}
