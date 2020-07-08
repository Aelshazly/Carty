package okhttp3.internal.http1;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy.Type;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RequestLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import p008cz.msebera.android.httpclient.protocol.HTTP;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 =2\u00020\u0001:\u0007:;<=>?@B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0015H\u0016J\b\u0010 \u001a\u00020\u0015H\u0016J\b\u0010!\u001a\u00020\u0018H\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020\fH\u0002J\b\u0010(\u001a\u00020\u0018H\u0002J\b\u0010)\u001a\u00020#H\u0002J\u0010\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020\u0013H\u0002J\u0012\u00100\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u00020\u000eH\u0016J\u0010\u00103\u001a\u00020\f2\u0006\u0010+\u001a\u00020,H\u0016J\u000e\u00104\u001a\u00020\u00152\u0006\u0010+\u001a\u00020,J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0016\u00105\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u00132\u0006\u00107\u001a\u00020.J\u0010\u00108\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\f\u00109\u001a\u00020\u000e*\u00020\u001aH\u0002J\f\u00109\u001a\u00020\u000e*\u00020,H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000¨\u0006A"}, mo24952d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "realConnection", "Lokhttp3/internal/connection/RealConnection;", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/connection/RealConnection;Lokio/BufferedSource;Lokio/BufferedSink;)V", "headerLimit", "", "isClosed", "", "()Z", "state", "", "trailers", "Lokhttp3/Headers;", "cancel", "", "connection", "createRequestBody", "Lokio/Sink;", "request", "Lokhttp3/Request;", "contentLength", "detachTimeout", "timeout", "Lokio/ForwardingTimeout;", "finishRequest", "flushRequest", "newChunkedSink", "newChunkedSource", "Lokio/Source;", "url", "Lokhttp3/HttpUrl;", "newFixedLengthSource", "length", "newKnownLengthSink", "newUnknownLengthSource", "openResponseBodySource", "response", "Lokhttp3/Response;", "readHeaderLine", "", "readHeaders", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "reportedContentLength", "skipConnectBody", "writeRequest", "headers", "requestLine", "writeRequestHeaders", "isChunked", "AbstractSource", "ChunkedSink", "ChunkedSource", "Companion", "FixedLengthSource", "KnownLengthSink", "UnknownLengthSource", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Http1ExchangeCodec.kt */
public final class Http1ExchangeCodec implements ExchangeCodec {
    public static final Companion Companion = new Companion(null);
    private static final int HEADER_LIMIT = 262144;
    private static final long NO_CHUNK_YET = -1;
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    /* access modifiers changed from: private */
    public final OkHttpClient client;
    private long headerLimit = ((long) 262144);
    /* access modifiers changed from: private */
    public final RealConnection realConnection;
    /* access modifiers changed from: private */
    public final BufferedSink sink;
    /* access modifiers changed from: private */
    public final BufferedSource source;
    /* access modifiers changed from: private */
    public int state;
    /* access modifiers changed from: private */
    public Headers trailers;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b¢\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\r\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0014J\b\u0010\t\u001a\u00020\u0015H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, mo24952d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokio/Source;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "timeout", "Lokio/ForwardingTimeout;", "getTimeout", "()Lokio/ForwardingTimeout;", "read", "", "sink", "Lokio/Buffer;", "byteCount", "responseBodyComplete", "", "responseBodyComplete$okhttp", "Lokio/Timeout;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http1ExchangeCodec.kt */
    private abstract class AbstractSource implements Source {
        private boolean closed;
        private final ForwardingTimeout timeout;

        public AbstractSource() {
            this.timeout = new ForwardingTimeout(Http1ExchangeCodec.this.source.timeout());
        }

        /* access modifiers changed from: protected */
        public final ForwardingTimeout getTimeout() {
            return this.timeout;
        }

        /* access modifiers changed from: protected */
        public final boolean getClosed() {
            return this.closed;
        }

        /* access modifiers changed from: protected */
        public final void setClosed(boolean z) {
            this.closed = z;
        }

        public Timeout timeout() {
            return this.timeout;
        }

        public long read(Buffer sink, long byteCount) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            try {
                return Http1ExchangeCodec.this.source.read(sink, byteCount);
            } catch (IOException e) {
                RealConnection access$getRealConnection$p = Http1ExchangeCodec.this.realConnection;
                if (access$getRealConnection$p == null) {
                    Intrinsics.throwNpe();
                }
                access$getRealConnection$p.noNewExchanges();
                responseBodyComplete$okhttp();
                throw e;
            }
        }

        public final void responseBodyComplete$okhttp() {
            if (Http1ExchangeCodec.this.state != 6) {
                if (Http1ExchangeCodec.this.state == 5) {
                    Http1ExchangeCodec.this.detachTimeout(this.timeout);
                    Http1ExchangeCodec.this.state = 6;
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("state: ");
                sb.append(Http1ExchangeCodec.this.state);
                throw new IllegalStateException(sb.toString());
            }
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u0005\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo24952d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSink;", "Lokio/Sink;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "timeout", "Lokio/ForwardingTimeout;", "close", "", "flush", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", "byteCount", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http1ExchangeCodec.kt */
    private final class ChunkedSink implements Sink {
        private boolean closed;
        private final ForwardingTimeout timeout;

        public ChunkedSink() {
            this.timeout = new ForwardingTimeout(Http1ExchangeCodec.this.sink.timeout());
        }

        public Timeout timeout() {
            return this.timeout;
        }

        public void write(Buffer source, long byteCount) {
            Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
            if (!(!this.closed)) {
                throw new IllegalStateException("closed".toString());
            } else if (byteCount != 0) {
                Http1ExchangeCodec.this.sink.writeHexadecimalUnsignedLong(byteCount);
                String str = "\r\n";
                Http1ExchangeCodec.this.sink.writeUtf8(str);
                Http1ExchangeCodec.this.sink.write(source, byteCount);
                Http1ExchangeCodec.this.sink.writeUtf8(str);
            }
        }

        public synchronized void flush() {
            if (!this.closed) {
                Http1ExchangeCodec.this.sink.flush();
            }
        }

        public synchronized void close() {
            if (!this.closed) {
                this.closed = true;
                Http1ExchangeCodec.this.sink.writeUtf8("0\r\n\r\n");
                Http1ExchangeCodec.this.detachTimeout(this.timeout);
                Http1ExchangeCodec.this.state = 3;
            }
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, mo24952d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "url", "Lokhttp3/HttpUrl;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;Lokhttp3/HttpUrl;)V", "bytesRemainingInChunk", "", "hasMoreChunks", "", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "readChunkSize", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http1ExchangeCodec.kt */
    private final class ChunkedSource extends AbstractSource {
        private long bytesRemainingInChunk = -1;
        private boolean hasMoreChunks = true;
        final /* synthetic */ Http1ExchangeCodec this$0;
        private final HttpUrl url;

        public ChunkedSource(Http1ExchangeCodec $outer, HttpUrl url2) {
            Intrinsics.checkParameterIsNotNull(url2, ImagesContract.URL);
            this.this$0 = $outer;
            super();
            this.url = url2;
        }

        public long read(Buffer sink, long byteCount) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            if (!(byteCount >= 0)) {
                StringBuilder sb = new StringBuilder();
                sb.append("byteCount < 0: ");
                sb.append(byteCount);
                throw new IllegalArgumentException(sb.toString().toString());
            } else if (!(true ^ getClosed())) {
                throw new IllegalStateException("closed".toString());
            } else if (!this.hasMoreChunks) {
                return -1;
            } else {
                long j = this.bytesRemainingInChunk;
                if (j == 0 || j == -1) {
                    readChunkSize();
                    if (!this.hasMoreChunks) {
                        return -1;
                    }
                }
                long read = super.read(sink, Math.min(byteCount, this.bytesRemainingInChunk));
                if (read == -1) {
                    RealConnection access$getRealConnection$p = this.this$0.realConnection;
                    if (access$getRealConnection$p == null) {
                        Intrinsics.throwNpe();
                    }
                    access$getRealConnection$p.noNewExchanges();
                    ProtocolException e = new ProtocolException("unexpected end of stream");
                    responseBodyComplete$okhttp();
                    throw e;
                }
                this.bytesRemainingInChunk -= read;
                return read;
            }
        }

        private final void readChunkSize() {
            if (this.bytesRemainingInChunk != -1) {
                this.this$0.source.readUtf8LineStrict();
            }
            try {
                this.bytesRemainingInChunk = this.this$0.source.readHexadecimalUnsignedLong();
                String readUtf8LineStrict = this.this$0.source.readUtf8LineStrict();
                if (readUtf8LineStrict != null) {
                    String extensions = StringsKt.trim((CharSequence) readUtf8LineStrict).toString();
                    if (this.bytesRemainingInChunk >= 0) {
                        if (!(extensions.length() > 0) || StringsKt.startsWith$default(extensions, ";", false, 2, null)) {
                            if (this.bytesRemainingInChunk == 0) {
                                this.hasMoreChunks = false;
                                Http1ExchangeCodec http1ExchangeCodec = this.this$0;
                                http1ExchangeCodec.trailers = http1ExchangeCodec.readHeaders();
                                OkHttpClient access$getClient$p = this.this$0.client;
                                if (access$getClient$p == null) {
                                    Intrinsics.throwNpe();
                                }
                                CookieJar cookieJar = access$getClient$p.cookieJar();
                                HttpUrl httpUrl = this.url;
                                Headers access$getTrailers$p = this.this$0.trailers;
                                if (access$getTrailers$p == null) {
                                    Intrinsics.throwNpe();
                                }
                                HttpHeaders.receiveHeaders(cookieJar, httpUrl, access$getTrailers$p);
                                responseBodyComplete$okhttp();
                                return;
                            }
                            return;
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("expected chunk size and optional extensions");
                    sb.append(" but was \"");
                    sb.append(this.bytesRemainingInChunk);
                    sb.append(extensions);
                    sb.append(Typography.quote);
                    throw new ProtocolException(sb.toString());
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        public void close() {
            if (!getClosed()) {
                if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    RealConnection access$getRealConnection$p = this.this$0.realConnection;
                    if (access$getRealConnection$p == null) {
                        Intrinsics.throwNpe();
                    }
                    access$getRealConnection$p.noNewExchanges();
                    responseBodyComplete$okhttp();
                }
                setClosed(true);
            }
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, mo24952d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$Companion;", "", "()V", "HEADER_LIMIT", "", "NO_CHUNK_YET", "", "STATE_CLOSED", "STATE_IDLE", "STATE_OPEN_REQUEST_BODY", "STATE_OPEN_RESPONSE_BODY", "STATE_READING_RESPONSE_BODY", "STATE_READ_RESPONSE_HEADERS", "STATE_WRITING_REQUEST_BODY", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http1ExchangeCodec.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, mo24952d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$FixedLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "bytesRemaining", "", "(Lokhttp3/internal/http1/Http1ExchangeCodec;J)V", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http1ExchangeCodec.kt */
    private final class FixedLengthSource extends AbstractSource {
        private long bytesRemaining;

        public FixedLengthSource(long bytesRemaining2) {
            super();
            this.bytesRemaining = bytesRemaining2;
            if (this.bytesRemaining == 0) {
                responseBodyComplete$okhttp();
            }
        }

        public long read(Buffer sink, long byteCount) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            if (!(byteCount >= 0)) {
                StringBuilder sb = new StringBuilder();
                sb.append("byteCount < 0: ");
                sb.append(byteCount);
                throw new IllegalArgumentException(sb.toString().toString());
            } else if (true ^ getClosed()) {
                long j = this.bytesRemaining;
                if (j == 0) {
                    return -1;
                }
                long read = super.read(sink, Math.min(j, byteCount));
                if (read == -1) {
                    RealConnection access$getRealConnection$p = Http1ExchangeCodec.this.realConnection;
                    if (access$getRealConnection$p == null) {
                        Intrinsics.throwNpe();
                    }
                    access$getRealConnection$p.noNewExchanges();
                    ProtocolException e = new ProtocolException("unexpected end of stream");
                    responseBodyComplete$okhttp();
                    throw e;
                }
                this.bytesRemaining -= read;
                if (this.bytesRemaining == 0) {
                    responseBodyComplete$okhttp();
                }
                return read;
            } else {
                throw new IllegalStateException("closed".toString());
            }
        }

        public void close() {
            if (!getClosed()) {
                if (this.bytesRemaining != 0 && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    RealConnection access$getRealConnection$p = Http1ExchangeCodec.this.realConnection;
                    if (access$getRealConnection$p == null) {
                        Intrinsics.throwNpe();
                    }
                    access$getRealConnection$p.noNewExchanges();
                    responseBodyComplete$okhttp();
                }
                setClosed(true);
            }
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u0005\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo24952d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$KnownLengthSink;", "Lokio/Sink;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "timeout", "Lokio/ForwardingTimeout;", "close", "", "flush", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", "byteCount", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http1ExchangeCodec.kt */
    private final class KnownLengthSink implements Sink {
        private boolean closed;
        private final ForwardingTimeout timeout;

        public KnownLengthSink() {
            this.timeout = new ForwardingTimeout(Http1ExchangeCodec.this.sink.timeout());
        }

        public Timeout timeout() {
            return this.timeout;
        }

        public void write(Buffer source, long byteCount) {
            Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
            if (!this.closed) {
                Util.checkOffsetAndCount(source.size(), 0, byteCount);
                Http1ExchangeCodec.this.sink.write(source, byteCount);
                return;
            }
            throw new IllegalStateException("closed".toString());
        }

        public void flush() {
            if (!this.closed) {
                Http1ExchangeCodec.this.sink.flush();
            }
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                Http1ExchangeCodec.this.detachTimeout(this.timeout);
                Http1ExchangeCodec.this.state = 3;
            }
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, mo24952d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$UnknownLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "inputExhausted", "", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http1ExchangeCodec.kt */
    private final class UnknownLengthSource extends AbstractSource {
        private boolean inputExhausted;

        public UnknownLengthSource() {
            super();
        }

        public long read(Buffer sink, long byteCount) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            if (!(byteCount >= 0)) {
                StringBuilder sb = new StringBuilder();
                sb.append("byteCount < 0: ");
                sb.append(byteCount);
                throw new IllegalArgumentException(sb.toString().toString());
            } else if (!(!getClosed())) {
                throw new IllegalStateException("closed".toString());
            } else if (this.inputExhausted) {
                return -1;
            } else {
                long read = super.read(sink, byteCount);
                if (read != -1) {
                    return read;
                }
                this.inputExhausted = true;
                responseBodyComplete$okhttp();
                return -1;
            }
        }

        public void close() {
            if (!getClosed()) {
                if (!this.inputExhausted) {
                    responseBodyComplete$okhttp();
                }
                setClosed(true);
            }
        }
    }

    public Http1ExchangeCodec(OkHttpClient client2, RealConnection realConnection2, BufferedSource source2, BufferedSink sink2) {
        Intrinsics.checkParameterIsNotNull(source2, Param.SOURCE);
        Intrinsics.checkParameterIsNotNull(sink2, "sink");
        this.client = client2;
        this.realConnection = realConnection2;
        this.source = source2;
        this.sink = sink2;
    }

    private final boolean isChunked(Response $this$isChunked) {
        return StringsKt.equals(HTTP.CHUNK_CODING, Response.header$default($this$isChunked, "Transfer-Encoding", null, 2, null), true);
    }

    private final boolean isChunked(Request $this$isChunked) {
        return StringsKt.equals(HTTP.CHUNK_CODING, $this$isChunked.header("Transfer-Encoding"), true);
    }

    public final boolean isClosed() {
        return this.state == 6;
    }

    public RealConnection connection() {
        return this.realConnection;
    }

    public Sink createRequestBody(Request request, long contentLength) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        if (request.body() != null && request.body().isDuplex()) {
            throw new ProtocolException("Duplex connections are not supported for HTTP/1");
        } else if (isChunked(request)) {
            return newChunkedSink();
        } else {
            if (contentLength != -1) {
                return newKnownLengthSink();
            }
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
    }

    public void cancel() {
        RealConnection realConnection2 = this.realConnection;
        if (realConnection2 != null) {
            realConnection2.cancel();
        }
    }

    public void writeRequestHeaders(Request request) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        RequestLine requestLine = RequestLine.INSTANCE;
        RealConnection realConnection2 = this.realConnection;
        if (realConnection2 == null) {
            Intrinsics.throwNpe();
        }
        Type type = realConnection2.route().proxy().type();
        Intrinsics.checkExpressionValueIsNotNull(type, "realConnection!!.route().proxy.type()");
        writeRequest(request.headers(), requestLine.get(request, type));
    }

    public long reportedContentLength(Response response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        if (!HttpHeaders.promisesBody(response)) {
            return 0;
        }
        if (isChunked(response)) {
            return -1;
        }
        return Util.headersContentLength(response);
    }

    public Source openResponseBodySource(Response response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        if (!HttpHeaders.promisesBody(response)) {
            return newFixedLengthSource(0);
        }
        if (isChunked(response)) {
            return newChunkedSource(response.request().url());
        }
        long contentLength = Util.headersContentLength(response);
        if (contentLength != -1) {
            return newFixedLengthSource(contentLength);
        }
        return newUnknownLengthSource();
    }

    public Headers trailers() {
        if (this.state == 6) {
            Headers headers = this.trailers;
            return headers != null ? headers : Util.EMPTY_HEADERS;
        }
        throw new IllegalStateException("too early; can't read the trailers yet".toString());
    }

    public void flushRequest() {
        this.sink.flush();
    }

    public void finishRequest() {
        this.sink.flush();
    }

    public final void writeRequest(Headers headers, String requestLine) {
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(requestLine, "requestLine");
        if (this.state == 0) {
            String str = "\r\n";
            this.sink.writeUtf8(requestLine).writeUtf8(str);
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                this.sink.writeUtf8(headers.name(i)).writeUtf8(": ").writeUtf8(headers.value(i)).writeUtf8(str);
            }
            this.sink.writeUtf8(str);
            this.state = 1;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("state: ");
        sb.append(this.state);
        throw new IllegalStateException(sb.toString().toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0072, code lost:
        if (r1 != null) goto L_0x0077;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Response.Builder readResponseHeaders(boolean r6) {
        /*
            r5 = this;
            int r0 = r5.state
            r1 = 3
            r2 = 1
            if (r0 == r2) goto L_0x000a
            if (r0 != r1) goto L_0x0009
            goto L_0x000a
        L_0x0009:
            r2 = 0
        L_0x000a:
            if (r2 == 0) goto L_0x0093
            okhttp3.internal.http.StatusLine$Companion r0 = okhttp3.internal.http.StatusLine.Companion     // Catch:{ EOFException -> 0x0057 }
            java.lang.String r2 = r5.readHeaderLine()     // Catch:{ EOFException -> 0x0057 }
            okhttp3.internal.http.StatusLine r0 = r0.parse(r2)     // Catch:{ EOFException -> 0x0057 }
            okhttp3.Response$Builder r2 = new okhttp3.Response$Builder     // Catch:{ EOFException -> 0x0057 }
            r2.<init>()     // Catch:{ EOFException -> 0x0057 }
            okhttp3.Protocol r3 = r0.protocol     // Catch:{ EOFException -> 0x0057 }
            okhttp3.Response$Builder r2 = r2.protocol(r3)     // Catch:{ EOFException -> 0x0057 }
            int r3 = r0.code     // Catch:{ EOFException -> 0x0057 }
            okhttp3.Response$Builder r2 = r2.code(r3)     // Catch:{ EOFException -> 0x0057 }
            java.lang.String r3 = r0.message     // Catch:{ EOFException -> 0x0057 }
            okhttp3.Response$Builder r2 = r2.message(r3)     // Catch:{ EOFException -> 0x0057 }
            okhttp3.Headers r3 = r5.readHeaders()     // Catch:{ EOFException -> 0x0057 }
            okhttp3.Response$Builder r2 = r2.headers(r3)     // Catch:{ EOFException -> 0x0057 }
            r3 = 100
            if (r6 == 0) goto L_0x004a
            int r4 = r0.code     // Catch:{ EOFException -> 0x0057 }
            if (r4 != r3) goto L_0x004a
            r1 = 0
            goto L_0x0056
        L_0x004a:
            int r4 = r0.code     // Catch:{ EOFException -> 0x0057 }
            if (r4 != r3) goto L_0x0051
            r5.state = r1     // Catch:{ EOFException -> 0x0057 }
            goto L_0x0055
        L_0x0051:
            r1 = 4
            r5.state = r1     // Catch:{ EOFException -> 0x0057 }
        L_0x0055:
            r1 = r2
        L_0x0056:
            return r1
        L_0x0057:
            r0 = move-exception
            okhttp3.internal.connection.RealConnection r1 = r5.realConnection
            if (r1 == 0) goto L_0x0075
            okhttp3.Route r1 = r1.route()
            if (r1 == 0) goto L_0x0075
            okhttp3.Address r1 = r1.address()
            if (r1 == 0) goto L_0x0075
            okhttp3.HttpUrl r1 = r1.url()
            if (r1 == 0) goto L_0x0075
            java.lang.String r1 = r1.redact()
            if (r1 == 0) goto L_0x0075
            goto L_0x0077
        L_0x0075:
            java.lang.String r1 = "unknown"
        L_0x0077:
            java.io.IOException r2 = new java.io.IOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "unexpected end of stream on "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            r4 = r0
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            r2.<init>(r3, r4)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x0093:
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "state: "
            r1.append(r2)
            int r2 = r5.state
            r1.append(r2)
            java.lang.String r0 = r1.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(boolean):okhttp3.Response$Builder");
    }

    private final String readHeaderLine() {
        String line = this.source.readUtf8LineStrict(this.headerLimit);
        this.headerLimit -= (long) line.length();
        return line;
    }

    /* access modifiers changed from: private */
    public final Headers readHeaders() {
        Builder headers = new Builder();
        String line = readHeaderLine();
        while (true) {
            if (!(line.length() > 0)) {
                return headers.build();
            }
            headers.addLenient$okhttp(line);
            line = readHeaderLine();
        }
    }

    private final Sink newChunkedSink() {
        boolean z = true;
        if (this.state != 1) {
            z = false;
        }
        if (z) {
            this.state = 2;
            return new ChunkedSink();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("state: ");
        sb.append(this.state);
        throw new IllegalStateException(sb.toString().toString());
    }

    private final Sink newKnownLengthSink() {
        boolean z = true;
        if (this.state != 1) {
            z = false;
        }
        if (z) {
            this.state = 2;
            return new KnownLengthSink();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("state: ");
        sb.append(this.state);
        throw new IllegalStateException(sb.toString().toString());
    }

    private final Source newFixedLengthSource(long length) {
        if (this.state == 4) {
            this.state = 5;
            return new FixedLengthSource(length);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("state: ");
        sb.append(this.state);
        throw new IllegalStateException(sb.toString().toString());
    }

    private final Source newChunkedSource(HttpUrl url) {
        if (this.state == 4) {
            this.state = 5;
            return new ChunkedSource(this, url);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("state: ");
        sb.append(this.state);
        throw new IllegalStateException(sb.toString().toString());
    }

    private final Source newUnknownLengthSource() {
        if (this.state == 4) {
            this.state = 5;
            RealConnection realConnection2 = this.realConnection;
            if (realConnection2 == null) {
                Intrinsics.throwNpe();
            }
            realConnection2.noNewExchanges();
            return new UnknownLengthSource();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("state: ");
        sb.append(this.state);
        throw new IllegalStateException(sb.toString().toString());
    }

    /* access modifiers changed from: private */
    public final void detachTimeout(ForwardingTimeout timeout) {
        Timeout oldDelegate = timeout.delegate();
        timeout.setDelegate(Timeout.NONE);
        oldDelegate.clearDeadline();
        oldDelegate.clearTimeout();
    }

    public final void skipConnectBody(Response response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        long contentLength = Util.headersContentLength(response);
        if (contentLength != -1) {
            Source body = newFixedLengthSource(contentLength);
            Util.skipAll(body, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            body.close();
        }
    }
}
