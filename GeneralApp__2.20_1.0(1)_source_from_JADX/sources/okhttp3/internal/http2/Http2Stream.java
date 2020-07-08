package okhttp3.internal.http2;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 _2\u00020\u0001:\u0004_`abB1\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u000e\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020#J\r\u0010C\u001a\u00020AH\u0000¢\u0006\u0002\bDJ\r\u0010E\u001a\u00020AH\u0000¢\u0006\u0002\bFJ\u0018\u0010G\u001a\u00020A2\u0006\u0010H\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u001a\u0010I\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J\u000e\u0010J\u001a\u00020A2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010K\u001a\u00020A2\u0006\u0010L\u001a\u00020\nJ\u0006\u0010M\u001a\u00020NJ\u0006\u0010O\u001a\u00020PJ\u0006\u0010,\u001a\u00020QJ\u0016\u0010R\u001a\u00020A2\u0006\u00104\u001a\u00020S2\u0006\u0010T\u001a\u00020\u0003J\u0016\u0010U\u001a\u00020A2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010V\u001a\u00020A2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010W\u001a\u00020\nJ\u0006\u0010L\u001a\u00020\nJ\r\u0010X\u001a\u00020AH\u0000¢\u0006\u0002\bYJ$\u0010Z\u001a\u00020A2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020]0\\2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010^\u001a\u00020\u0007J\u0006\u0010>\u001a\u00020QR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000f8@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u001cX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b!\u0010 R$\u0010$\u001a\u00020#2\u0006\u0010\"\u001a\u00020#@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R$\u0010)\u001a\u00020#2\u0006\u0010\"\u001a\u00020#@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010&\"\u0004\b+\u0010(R\u0018\u0010,\u001a\u00060-R\u00020\u0000X\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0018\u00100\u001a\u000601R\u00020\u0000X\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0018\u00104\u001a\u000605R\u00020\u0000X\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R$\u00108\u001a\u00020#2\u0006\u0010\"\u001a\u00020#@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010&\"\u0004\b:\u0010(R$\u0010;\u001a\u00020#2\u0006\u0010\"\u001a\u00020#@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010&\"\u0004\b=\u0010(R\u0018\u0010>\u001a\u00060-R\u00020\u0000X\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010/¨\u0006c"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Stream;", "", "id", "", "connection", "Lokhttp3/internal/http2/Http2Connection;", "outFinished", "", "inFinished", "headers", "Lokhttp3/Headers;", "(ILokhttp3/internal/http2/Http2Connection;ZZLokhttp3/Headers;)V", "getConnection", "()Lokhttp3/internal/http2/Http2Connection;", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "getErrorCode$okhttp", "()Lokhttp3/internal/http2/ErrorCode;", "setErrorCode$okhttp", "(Lokhttp3/internal/http2/ErrorCode;)V", "errorException", "Ljava/io/IOException;", "getErrorException$okhttp", "()Ljava/io/IOException;", "setErrorException$okhttp", "(Ljava/io/IOException;)V", "hasResponseHeaders", "headersQueue", "Ljava/util/ArrayDeque;", "getId", "()I", "isLocallyInitiated", "()Z", "isOpen", "<set-?>", "", "readBytesAcknowledged", "getReadBytesAcknowledged", "()J", "setReadBytesAcknowledged$okhttp", "(J)V", "readBytesTotal", "getReadBytesTotal", "setReadBytesTotal$okhttp", "readTimeout", "Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "getReadTimeout$okhttp", "()Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "sink", "Lokhttp3/internal/http2/Http2Stream$FramingSink;", "getSink$okhttp", "()Lokhttp3/internal/http2/Http2Stream$FramingSink;", "source", "Lokhttp3/internal/http2/Http2Stream$FramingSource;", "getSource$okhttp", "()Lokhttp3/internal/http2/Http2Stream$FramingSource;", "writeBytesMaximum", "getWriteBytesMaximum", "setWriteBytesMaximum$okhttp", "writeBytesTotal", "getWriteBytesTotal", "setWriteBytesTotal$okhttp", "writeTimeout", "getWriteTimeout$okhttp", "addBytesToWriteWindow", "", "delta", "cancelStreamIfNecessary", "cancelStreamIfNecessary$okhttp", "checkOutNotClosed", "checkOutNotClosed$okhttp", "close", "rstStatusCode", "closeInternal", "closeLater", "enqueueTrailers", "trailers", "getSink", "Lokio/Sink;", "getSource", "Lokio/Source;", "Lokio/Timeout;", "receiveData", "Lokio/BufferedSource;", "length", "receiveHeaders", "receiveRstStream", "takeHeaders", "waitForIo", "waitForIo$okhttp", "writeHeaders", "responseHeaders", "", "Lokhttp3/internal/http2/Header;", "flushHeaders", "Companion", "FramingSink", "FramingSource", "StreamTimeout", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Http2Stream.kt */
public final class Http2Stream {
    public static final Companion Companion = new Companion(null);
    public static final long EMIT_BUFFER_SIZE = 16384;
    private final Http2Connection connection;
    private ErrorCode errorCode;
    private IOException errorException;
    private boolean hasResponseHeaders;
    private final ArrayDeque<Headers> headersQueue = new ArrayDeque<>();

    /* renamed from: id */
    private final int f262id;
    private long readBytesAcknowledged;
    private long readBytesTotal;
    private final StreamTimeout readTimeout;
    private final FramingSink sink;
    private final FramingSource source;
    private long writeBytesMaximum = ((long) this.connection.getPeerSettings().getInitialWindowSize());
    private long writeBytesTotal;
    private final StreamTimeout writeTimeout;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Stream$Companion;", "", "()V", "EMIT_BUFFER_SIZE", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http2Stream.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0003H\u0002J\b\u0010\u0018\u001a\u00020\u0015H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016R\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Stream$FramingSink;", "Lokio/Sink;", "finished", "", "(Lokhttp3/internal/http2/Http2Stream;Z)V", "closed", "getClosed", "()Z", "setClosed", "(Z)V", "getFinished", "setFinished", "sendBuffer", "Lokio/Buffer;", "trailers", "Lokhttp3/Headers;", "getTrailers", "()Lokhttp3/Headers;", "setTrailers", "(Lokhttp3/Headers;)V", "close", "", "emitFrame", "outFinishedOnLastFrame", "flush", "timeout", "Lokio/Timeout;", "write", "source", "byteCount", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http2Stream.kt */
    public final class FramingSink implements Sink {
        private boolean closed;
        private boolean finished;
        private final Buffer sendBuffer;
        private Headers trailers;

        public FramingSink(boolean finished2) {
            this.finished = finished2;
            this.sendBuffer = new Buffer();
        }

        public /* synthetic */ FramingSink(Http2Stream http2Stream, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                z = false;
            }
            this(z);
        }

        public final boolean getFinished() {
            return this.finished;
        }

        public final void setFinished(boolean z) {
            this.finished = z;
        }

        public final Headers getTrailers() {
            return this.trailers;
        }

        public final void setTrailers(Headers headers) {
            this.trailers = headers;
        }

        public final boolean getClosed() {
            return this.closed;
        }

        public final void setClosed(boolean z) {
            this.closed = z;
        }

        public void write(Buffer source, long byteCount) throws IOException {
            Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
            boolean z = !Thread.holdsLock(Http2Stream.this);
            if (!_Assertions.ENABLED || z) {
                this.sendBuffer.write(source, byteCount);
                while (this.sendBuffer.size() >= 16384) {
                    emitFrame(false);
                }
                return;
            }
            throw new AssertionError("Assertion failed");
        }

        /* JADX INFO: finally extract failed */
        private final void emitFrame(boolean outFinishedOnLastFrame) throws IOException {
            long toWrite;
            boolean outFinished;
            synchronized (Http2Stream.this) {
                Http2Stream.this.getWriteTimeout$okhttp().enter();
                while (Http2Stream.this.getWriteBytesTotal() >= Http2Stream.this.getWriteBytesMaximum() && !this.finished && !this.closed && Http2Stream.this.getErrorCode$okhttp() == null) {
                    try {
                        Http2Stream.this.waitForIo$okhttp();
                    } catch (Throwable th) {
                        Http2Stream.this.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
                        throw th;
                    }
                }
                Http2Stream.this.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
                Http2Stream.this.checkOutNotClosed$okhttp();
                toWrite = Math.min(Http2Stream.this.getWriteBytesMaximum() - Http2Stream.this.getWriteBytesTotal(), this.sendBuffer.size());
                Http2Stream http2Stream = Http2Stream.this;
                http2Stream.setWriteBytesTotal$okhttp(http2Stream.getWriteBytesTotal() + toWrite);
                Unit unit = Unit.INSTANCE;
            }
            Http2Stream.this.getWriteTimeout$okhttp().enter();
            if (outFinishedOnLastFrame) {
                try {
                    if (toWrite == this.sendBuffer.size()) {
                        outFinished = true;
                        Http2Stream.this.getConnection().writeData(Http2Stream.this.getId(), outFinished, this.sendBuffer, toWrite);
                        Http2Stream.this.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
                    }
                } catch (Throwable th2) {
                    Http2Stream.this.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
                    throw th2;
                }
            }
            outFinished = false;
            Http2Stream.this.getConnection().writeData(Http2Stream.this.getId(), outFinished, this.sendBuffer, toWrite);
            Http2Stream.this.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
        }

        public void flush() throws IOException {
            boolean z = !Thread.holdsLock(Http2Stream.this);
            if (!_Assertions.ENABLED || z) {
                synchronized (Http2Stream.this) {
                    Http2Stream.this.checkOutNotClosed$okhttp();
                    Unit unit = Unit.INSTANCE;
                }
                while (this.sendBuffer.size() > 0) {
                    emitFrame(false);
                    Http2Stream.this.getConnection().flush();
                }
                return;
            }
            throw new AssertionError("Assertion failed");
        }

        public Timeout timeout() {
            return Http2Stream.this.getWriteTimeout$okhttp();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
            if (r10.this$0.getSink$okhttp().finished != false) goto L_0x0096;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x003b, code lost:
            if (r10.sendBuffer.size() <= 0) goto L_0x003f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
            r2 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x003f, code lost:
            r2 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0042, code lost:
            if (r10.trailers == null) goto L_0x0046;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0044, code lost:
            r3 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0046, code lost:
            r3 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0048, code lost:
            if (r3 == false) goto L_0x0073;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0052, code lost:
            if (r10.sendBuffer.size() <= 0) goto L_0x0058;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0054, code lost:
            emitFrame(false);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0058, code lost:
            r0 = r10.this$0.getConnection();
            r4 = r10.this$0.getId();
            r5 = r10.trailers;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0066, code lost:
            if (r5 != null) goto L_0x006b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0068, code lost:
            kotlin.jvm.internal.Intrinsics.throwNpe();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x006b, code lost:
            r0.writeHeaders$okhttp(r4, true, okhttp3.internal.Util.toHeaderList(r5));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0073, code lost:
            if (r2 == false) goto L_0x0083;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x007d, code lost:
            if (r10.sendBuffer.size() <= 0) goto L_0x0096;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x007f, code lost:
            emitFrame(true);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0083, code lost:
            r10.this$0.getConnection().writeData(r10.this$0.getId(), true, null, 0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0096, code lost:
            r0 = r10.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0099, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
            r10.closed = true;
            r1 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x009f, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a0, code lost:
            r10.this$0.getConnection().flush();
            r10.this$0.cancelStreamIfNecessary$okhttp();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ae, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r10 = this;
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                boolean r0 = java.lang.Thread.holdsLock(r0)
                r1 = 1
                r0 = r0 ^ r1
                boolean r2 = kotlin._Assertions.ENABLED
                if (r2 == 0) goto L_0x0019
                if (r0 == 0) goto L_0x000f
                goto L_0x0019
            L_0x000f:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                java.lang.String r1 = "Assertion failed"
                r0.<init>(r1)
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                throw r0
            L_0x0019:
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                monitor-enter(r0)
                r2 = 0
                boolean r3 = r10.closed     // Catch:{ all -> 0x00b2 }
                if (r3 == 0) goto L_0x0023
                monitor-exit(r0)
                return
            L_0x0023:
                kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00b2 }
                monitor-exit(r0)
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Stream$FramingSink r0 = r0.getSink$okhttp()
                boolean r0 = r0.finished
                if (r0 != 0) goto L_0x0096
                okio.Buffer r0 = r10.sendBuffer
                long r2 = r0.size()
                r0 = 0
                r4 = 0
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 <= 0) goto L_0x003f
                r2 = 1
                goto L_0x0040
            L_0x003f:
                r2 = 0
            L_0x0040:
                okhttp3.Headers r3 = r10.trailers
                if (r3 == 0) goto L_0x0046
                r3 = 1
                goto L_0x0047
            L_0x0046:
                r3 = 0
            L_0x0047:
                if (r3 == 0) goto L_0x0073
            L_0x004a:
                okio.Buffer r6 = r10.sendBuffer
                long r6 = r6.size()
                int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
                if (r8 <= 0) goto L_0x0058
                r10.emitFrame(r0)
                goto L_0x004a
            L_0x0058:
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Connection r0 = r0.getConnection()
                okhttp3.internal.http2.Http2Stream r4 = okhttp3.internal.http2.Http2Stream.this
                int r4 = r4.getId()
                okhttp3.Headers r5 = r10.trailers
                if (r5 != 0) goto L_0x006b
                kotlin.jvm.internal.Intrinsics.throwNpe()
            L_0x006b:
                java.util.List r5 = okhttp3.internal.Util.toHeaderList(r5)
                r0.writeHeaders$okhttp(r4, r1, r5)
                goto L_0x0096
            L_0x0073:
                if (r2 == 0) goto L_0x0083
            L_0x0075:
                okio.Buffer r0 = r10.sendBuffer
                long r6 = r0.size()
                int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
                if (r0 <= 0) goto L_0x0096
                r10.emitFrame(r1)
                goto L_0x0075
            L_0x0083:
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Connection r4 = r0.getConnection()
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                int r5 = r0.getId()
                r6 = 1
                r7 = 0
                r8 = 0
                r4.writeData(r5, r6, r7, r8)
            L_0x0096:
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                monitor-enter(r0)
                r2 = 0
                r10.closed = r1     // Catch:{ all -> 0x00af }
                kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00af }
                monitor-exit(r0)
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Connection r0 = r0.getConnection()
                r0.flush()
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                r0.cancelStreamIfNecessary$okhttp()
                return
            L_0x00af:
                r1 = move-exception
                monitor-exit(r0)
                throw r1
            L_0x00b2:
                r1 = move-exception
                monitor-exit(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSink.close():void");
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0003H\u0016J\u001d\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\"J\b\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0003H\u0002R\u001a\u0010\u0007\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006&"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Stream$FramingSource;", "Lokio/Source;", "maxByteCount", "", "finished", "", "(Lokhttp3/internal/http2/Http2Stream;JZ)V", "closed", "getClosed$okhttp", "()Z", "setClosed$okhttp", "(Z)V", "getFinished$okhttp", "setFinished$okhttp", "readBuffer", "Lokio/Buffer;", "getReadBuffer", "()Lokio/Buffer;", "receiveBuffer", "getReceiveBuffer", "trailers", "Lokhttp3/Headers;", "getTrailers", "()Lokhttp3/Headers;", "setTrailers", "(Lokhttp3/Headers;)V", "close", "", "read", "sink", "byteCount", "receive", "source", "Lokio/BufferedSource;", "receive$okhttp", "timeout", "Lokio/Timeout;", "updateConnectionFlowControl", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http2Stream.kt */
    public final class FramingSource implements Source {
        private boolean closed;
        private boolean finished;
        private final long maxByteCount;
        private final Buffer readBuffer = new Buffer();
        private final Buffer receiveBuffer = new Buffer();
        private Headers trailers;

        public FramingSource(long maxByteCount2, boolean finished2) {
            this.maxByteCount = maxByteCount2;
            this.finished = finished2;
        }

        public final boolean getFinished$okhttp() {
            return this.finished;
        }

        public final void setFinished$okhttp(boolean z) {
            this.finished = z;
        }

        public final Buffer getReceiveBuffer() {
            return this.receiveBuffer;
        }

        public final Buffer getReadBuffer() {
            return this.readBuffer;
        }

        public final Headers getTrailers() {
            return this.trailers;
        }

        public final void setTrailers(Headers headers) {
            this.trailers = headers;
        }

        public final boolean getClosed$okhttp() {
            return this.closed;
        }

        public final void setClosed$okhttp(boolean z) {
            this.closed = z;
        }

        /* JADX INFO: finally extract failed */
        public long read(Buffer sink, long byteCount) throws IOException {
            Buffer buffer = sink;
            long j = byteCount;
            Intrinsics.checkParameterIsNotNull(buffer, "sink");
            long j2 = 0;
            if (j >= 0) {
                while (true) {
                    boolean tryAgain = false;
                    long readBytesDelivered = -1;
                    IOException iOException = null;
                    synchronized (Http2Stream.this) {
                        Http2Stream.this.getReadTimeout$okhttp().enter();
                        try {
                            if (Http2Stream.this.getErrorCode$okhttp() != null) {
                                IOException errorException$okhttp = Http2Stream.this.getErrorException$okhttp();
                                if (errorException$okhttp == null) {
                                    ErrorCode errorCode$okhttp = Http2Stream.this.getErrorCode$okhttp();
                                    if (errorCode$okhttp == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    errorException$okhttp = new StreamResetException(errorCode$okhttp);
                                }
                                iOException = errorException$okhttp;
                            }
                            if (!this.closed) {
                                if (this.readBuffer.size() > j2) {
                                    readBytesDelivered = this.readBuffer.read(buffer, Math.min(j, this.readBuffer.size()));
                                    Http2Stream http2Stream = Http2Stream.this;
                                    http2Stream.setReadBytesTotal$okhttp(http2Stream.getReadBytesTotal() + readBytesDelivered);
                                    long unacknowledgedBytesRead = Http2Stream.this.getReadBytesTotal() - Http2Stream.this.getReadBytesAcknowledged();
                                    if (iOException == null && unacknowledgedBytesRead >= ((long) (Http2Stream.this.getConnection().getOkHttpSettings().getInitialWindowSize() / 2))) {
                                        Http2Stream.this.getConnection().writeWindowUpdateLater$okhttp(Http2Stream.this.getId(), unacknowledgedBytesRead);
                                        Http2Stream.this.setReadBytesAcknowledged$okhttp(Http2Stream.this.getReadBytesTotal());
                                    }
                                } else if (!this.finished && iOException == null) {
                                    Http2Stream.this.waitForIo$okhttp();
                                    tryAgain = true;
                                }
                                Http2Stream.this.getReadTimeout$okhttp().exitAndThrowIfTimedOut();
                                Unit unit = Unit.INSTANCE;
                            } else {
                                throw new IOException("stream closed");
                            }
                        } catch (Throwable th) {
                            Http2Stream.this.getReadTimeout$okhttp().exitAndThrowIfTimedOut();
                            throw th;
                        }
                    }
                    if (tryAgain) {
                        j2 = 0;
                    } else if (readBytesDelivered != -1) {
                        updateConnectionFlowControl(readBytesDelivered);
                        return readBytesDelivered;
                    } else if (iOException == null) {
                        return -1;
                    } else {
                        if (iOException == null) {
                            Intrinsics.throwNpe();
                        }
                        throw iOException;
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("byteCount < 0: ");
                sb.append(j);
                throw new IllegalArgumentException(sb.toString().toString());
            }
        }

        private final void updateConnectionFlowControl(long read) {
            boolean z = !Thread.holdsLock(Http2Stream.this);
            if (!_Assertions.ENABLED || z) {
                Http2Stream.this.getConnection().updateConnectionFlowControl$okhttp(read);
                return;
            }
            throw new AssertionError("Assertion failed");
        }

        public final void receive$okhttp(BufferedSource source, long byteCount) throws IOException {
            boolean finished2;
            boolean z;
            boolean flowControlError;
            BufferedSource bufferedSource = source;
            Intrinsics.checkParameterIsNotNull(bufferedSource, Param.SOURCE);
            long byteCount2 = byteCount;
            boolean z2 = !Thread.holdsLock(Http2Stream.this);
            if (!_Assertions.ENABLED || z2) {
                while (byteCount2 > 0) {
                    synchronized (Http2Stream.this) {
                        finished2 = this.finished;
                        z = false;
                        flowControlError = this.readBuffer.size() + byteCount2 > this.maxByteCount;
                        Unit unit = Unit.INSTANCE;
                    }
                    if (flowControlError) {
                        bufferedSource.skip(byteCount2);
                        Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                        return;
                    } else if (finished2) {
                        bufferedSource.skip(byteCount2);
                        return;
                    } else {
                        long read = bufferedSource.read(this.receiveBuffer, byteCount2);
                        if (read != -1) {
                            byteCount2 -= read;
                            long bytesDiscarded = 0;
                            synchronized (Http2Stream.this) {
                                if (this.closed) {
                                    bytesDiscarded = this.receiveBuffer.size();
                                    this.receiveBuffer.clear();
                                } else {
                                    if (this.readBuffer.size() == 0) {
                                        z = true;
                                    }
                                    boolean wasEmpty = z;
                                    this.readBuffer.writeAll(this.receiveBuffer);
                                    if (wasEmpty) {
                                        Http2Stream http2Stream = Http2Stream.this;
                                        if (http2Stream != null) {
                                            http2Stream.notifyAll();
                                        } else {
                                            throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                                        }
                                    }
                                }
                                Unit unit2 = Unit.INSTANCE;
                            }
                            if (bytesDiscarded > 0) {
                                updateConnectionFlowControl(bytesDiscarded);
                            }
                        } else {
                            throw new EOFException();
                        }
                    }
                }
                return;
            }
            throw new AssertionError("Assertion failed");
        }

        public Timeout timeout() {
            return Http2Stream.this.getReadTimeout$okhttp();
        }

        public void close() throws IOException {
            long bytesDiscarded;
            synchronized (Http2Stream.this) {
                this.closed = true;
                bytesDiscarded = this.readBuffer.size();
                this.readBuffer.clear();
                Http2Stream http2Stream = Http2Stream.this;
                if (http2Stream != null) {
                    http2Stream.notifyAll();
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                }
            }
            if (bytesDiscarded > 0) {
                updateConnectionFlowControl(bytesDiscarded);
            }
            Http2Stream.this.cancelStreamIfNecessary$okhttp();
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\b\u001a\u00020\u0004H\u0014¨\u0006\t"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "Lokio/AsyncTimeout;", "(Lokhttp3/internal/http2/Http2Stream;)V", "exitAndThrowIfTimedOut", "", "newTimeoutException", "Ljava/io/IOException;", "cause", "timedOut", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http2Stream.kt */
    public final class StreamTimeout extends AsyncTimeout {
        public StreamTimeout() {
        }

        /* access modifiers changed from: protected */
        public void timedOut() {
            Http2Stream.this.closeLater(ErrorCode.CANCEL);
        }

        /* access modifiers changed from: protected */
        public IOException newTimeoutException(IOException cause) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            SocketTimeoutException $this$apply = socketTimeoutException;
            if (cause != null) {
                $this$apply.initCause(cause);
            }
            return socketTimeoutException;
        }

        public final void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }
    }

    public Http2Stream(int id, Http2Connection connection2, boolean outFinished, boolean inFinished, Headers headers) {
        Intrinsics.checkParameterIsNotNull(connection2, "connection");
        this.f262id = id;
        this.connection = connection2;
        this.source = new FramingSource((long) this.connection.getOkHttpSettings().getInitialWindowSize(), inFinished);
        this.sink = new FramingSink(outFinished);
        this.readTimeout = new StreamTimeout();
        this.writeTimeout = new StreamTimeout();
        if (headers != null) {
            if (!isLocallyInitiated()) {
                this.headersQueue.add(headers);
                return;
            }
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet".toString());
        } else if (!isLocallyInitiated()) {
            throw new IllegalStateException("remotely-initiated streams should have headers".toString());
        }
    }

    public final int getId() {
        return this.f262id;
    }

    public final Http2Connection getConnection() {
        return this.connection;
    }

    public final long getReadBytesTotal() {
        return this.readBytesTotal;
    }

    public final void setReadBytesTotal$okhttp(long j) {
        this.readBytesTotal = j;
    }

    public final long getReadBytesAcknowledged() {
        return this.readBytesAcknowledged;
    }

    public final void setReadBytesAcknowledged$okhttp(long j) {
        this.readBytesAcknowledged = j;
    }

    public final long getWriteBytesTotal() {
        return this.writeBytesTotal;
    }

    public final void setWriteBytesTotal$okhttp(long j) {
        this.writeBytesTotal = j;
    }

    public final long getWriteBytesMaximum() {
        return this.writeBytesMaximum;
    }

    public final void setWriteBytesMaximum$okhttp(long j) {
        this.writeBytesMaximum = j;
    }

    public final FramingSource getSource$okhttp() {
        return this.source;
    }

    public final FramingSink getSink$okhttp() {
        return this.sink;
    }

    public final StreamTimeout getReadTimeout$okhttp() {
        return this.readTimeout;
    }

    public final StreamTimeout getWriteTimeout$okhttp() {
        return this.writeTimeout;
    }

    public final synchronized ErrorCode getErrorCode$okhttp() {
        return this.errorCode;
    }

    public final void setErrorCode$okhttp(ErrorCode errorCode2) {
        this.errorCode = errorCode2;
    }

    public final IOException getErrorException$okhttp() {
        return this.errorException;
    }

    public final void setErrorException$okhttp(IOException iOException) {
        this.errorException = iOException;
    }

    public final synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        if ((this.source.getFinished$okhttp() || this.source.getClosed$okhttp()) && ((this.sink.getFinished() || this.sink.getClosed()) && this.hasResponseHeaders)) {
            return false;
        }
        return true;
    }

    public final boolean isLocallyInitiated() {
        if (this.connection.getClient$okhttp() == ((this.f262id & 1) == 1)) {
            return true;
        }
        return false;
    }

    public final synchronized Headers takeHeaders() throws IOException {
        Object removeFirst;
        this.readTimeout.enter();
        while (this.headersQueue.isEmpty()) {
            try {
                try {
                    if (this.errorCode == null) {
                        waitForIo$okhttp();
                    }
                } catch (Throwable th) {
                    th = th;
                    this.readTimeout.exitAndThrowIfTimedOut();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                this.readTimeout.exitAndThrowIfTimedOut();
                throw th;
            }
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        if (!this.headersQueue.isEmpty()) {
            removeFirst = this.headersQueue.removeFirst();
            Intrinsics.checkExpressionValueIsNotNull(removeFirst, "headersQueue.removeFirst()");
        } else {
            Throwable th3 = this.errorException;
            if (th3 == null) {
                ErrorCode errorCode2 = this.errorCode;
                if (errorCode2 == null) {
                    Intrinsics.throwNpe();
                }
                th3 = new StreamResetException(errorCode2);
            }
            throw th3;
        }
        return (Headers) removeFirst;
    }

    public final synchronized Headers trailers() throws IOException {
        Headers trailers;
        if (this.errorCode != null) {
            Throwable th = this.errorException;
            if (th == null) {
                ErrorCode errorCode2 = this.errorCode;
                if (errorCode2 == null) {
                    Intrinsics.throwNpe();
                }
                th = new StreamResetException(errorCode2);
            }
            throw th;
        }
        if (this.source.getFinished$okhttp() && this.source.getReceiveBuffer().exhausted() && this.source.getReadBuffer().exhausted()) {
            trailers = this.source.getTrailers();
            if (trailers == null) {
                trailers = Util.EMPTY_HEADERS;
            }
        } else {
            throw new IllegalStateException("too early; can't read the trailers yet".toString());
        }
        return trailers;
    }

    public final void writeHeaders(List<Header> responseHeaders, boolean outFinished, boolean flushHeaders) throws IOException {
        Intrinsics.checkParameterIsNotNull(responseHeaders, "responseHeaders");
        boolean flushHeaders2 = flushHeaders;
        boolean z = true;
        boolean z2 = !Thread.holdsLock(this);
        if (!_Assertions.ENABLED || z2) {
            synchronized (this) {
                this.hasResponseHeaders = true;
                if (outFinished) {
                    this.sink.setFinished(true);
                }
                Unit unit = Unit.INSTANCE;
            }
            if (!flushHeaders2) {
                synchronized (this.connection) {
                    if (this.connection.getWriteBytesTotal() < this.connection.getWriteBytesMaximum()) {
                        z = false;
                    }
                    flushHeaders2 = z;
                    Unit unit2 = Unit.INSTANCE;
                }
            }
            this.connection.writeHeaders$okhttp(this.f262id, outFinished, responseHeaders);
            if (flushHeaders2) {
                this.connection.flush();
                return;
            }
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    public final void enqueueTrailers(Headers trailers) {
        Intrinsics.checkParameterIsNotNull(trailers, "trailers");
        synchronized (this) {
            boolean z = true;
            if (!this.sink.getFinished()) {
                if (trailers.size() == 0) {
                    z = false;
                }
                if (z) {
                    this.sink.setTrailers(trailers);
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalArgumentException("trailers.size() == 0".toString());
                }
            } else {
                throw new IllegalStateException("already finished".toString());
            }
        }
    }

    public final Timeout readTimeout() {
        return this.readTimeout;
    }

    public final Timeout writeTimeout() {
        return this.writeTimeout;
    }

    public final Source getSource() {
        return this.source;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0012  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final okio.Sink getSink() {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            boolean r1 = r3.hasResponseHeaders     // Catch:{ all -> 0x0029 }
            if (r1 != 0) goto L_0x000f
            boolean r1 = r3.isLocallyInitiated()     // Catch:{ all -> 0x0029 }
            if (r1 == 0) goto L_0x000d
            goto L_0x000f
        L_0x000d:
            r1 = 0
            goto L_0x0010
        L_0x000f:
            r1 = 1
        L_0x0010:
            if (r1 == 0) goto L_0x001a
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0029 }
            monitor-exit(r3)
            okhttp3.internal.http2.Http2Stream$FramingSink r0 = r3.sink
            okio.Sink r0 = (okio.Sink) r0
            return r0
        L_0x001a:
            r1 = 0
            java.lang.String r2 = "reply before requesting the sink"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0029 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0029 }
            r1.<init>(r2)     // Catch:{ all -> 0x0029 }
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x0029 }
            throw r1     // Catch:{ all -> 0x0029 }
        L_0x0029:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.getSink():okio.Sink");
    }

    public final void close(ErrorCode rstStatusCode, IOException errorException2) throws IOException {
        Intrinsics.checkParameterIsNotNull(rstStatusCode, "rstStatusCode");
        if (closeInternal(rstStatusCode, errorException2)) {
            this.connection.writeSynReset$okhttp(this.f262id, rstStatusCode);
        }
    }

    public final void closeLater(ErrorCode errorCode2) {
        Intrinsics.checkParameterIsNotNull(errorCode2, "errorCode");
        if (closeInternal(errorCode2, null)) {
            this.connection.writeSynResetLater$okhttp(this.f262id, errorCode2);
        }
    }

    private final boolean closeInternal(ErrorCode errorCode2, IOException errorException2) {
        boolean z = !Thread.holdsLock(this);
        if (!_Assertions.ENABLED || z) {
            synchronized (this) {
                if (this.errorCode != null) {
                    return false;
                }
                if (this.source.getFinished$okhttp() && this.sink.getFinished()) {
                    return false;
                }
                this.errorCode = errorCode2;
                this.errorException = errorException2;
                notifyAll();
                Unit unit = Unit.INSTANCE;
                this.connection.removeStream$okhttp(this.f262id);
                return true;
            }
        }
        throw new AssertionError("Assertion failed");
    }

    public final void receiveData(BufferedSource source2, int length) throws IOException {
        Intrinsics.checkParameterIsNotNull(source2, Param.SOURCE);
        boolean z = !Thread.holdsLock(this);
        if (!_Assertions.ENABLED || z) {
            this.source.receive$okhttp(source2, (long) length);
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void receiveHeaders(okhttp3.Headers r6, boolean r7) {
        /*
            r5 = this;
            java.lang.String r0 = "headers"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            boolean r0 = java.lang.Thread.holdsLock(r5)
            r1 = 1
            r0 = r0 ^ r1
            boolean r2 = kotlin._Assertions.ENABLED
            if (r2 == 0) goto L_0x001c
            if (r0 == 0) goto L_0x0012
            goto L_0x001c
        L_0x0012:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            java.lang.String r1 = "Assertion failed"
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x001c:
            r0 = 0
            monitor-enter(r5)
            r2 = 0
            boolean r3 = r5.hasResponseHeaders     // Catch:{ all -> 0x0057 }
            if (r3 == 0) goto L_0x002c
            if (r7 != 0) goto L_0x0026
            goto L_0x002c
        L_0x0026:
            okhttp3.internal.http2.Http2Stream$FramingSource r3 = r5.source     // Catch:{ all -> 0x0057 }
            r3.setTrailers(r6)     // Catch:{ all -> 0x0057 }
            goto L_0x0035
        L_0x002c:
            r5.hasResponseHeaders = r1     // Catch:{ all -> 0x0057 }
            java.util.ArrayDeque<okhttp3.Headers> r3 = r5.headersQueue     // Catch:{ all -> 0x0057 }
            java.util.Collection r3 = (java.util.Collection) r3     // Catch:{ all -> 0x0057 }
            r3.add(r6)     // Catch:{ all -> 0x0057 }
        L_0x0035:
            if (r7 == 0) goto L_0x003d
            okhttp3.internal.http2.Http2Stream$FramingSource r3 = r5.source     // Catch:{ all -> 0x0057 }
            r3.setFinished$okhttp(r1)     // Catch:{ all -> 0x0057 }
        L_0x003d:
            boolean r1 = r5.isOpen()     // Catch:{ all -> 0x0057 }
            r0 = r1
            r1 = r5
            r3 = 0
            r4 = r1
            java.lang.Object r4 = (java.lang.Object) r4     // Catch:{ all -> 0x0057 }
            r4.notifyAll()     // Catch:{ all -> 0x0057 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0057 }
            monitor-exit(r5)
            if (r0 != 0) goto L_0x0056
            okhttp3.internal.http2.Http2Connection r1 = r5.connection
            int r2 = r5.f262id
            r1.removeStream$okhttp(r2)
        L_0x0056:
            return
        L_0x0057:
            r1 = move-exception
            monitor-exit(r5)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.receiveHeaders(okhttp3.Headers, boolean):void");
    }

    public final synchronized void receiveRstStream(ErrorCode errorCode2) {
        Intrinsics.checkParameterIsNotNull(errorCode2, "errorCode");
        if (this.errorCode == null) {
            this.errorCode = errorCode2;
            notifyAll();
        }
    }

    public final void cancelStreamIfNecessary$okhttp() throws IOException {
        boolean cancel;
        boolean open;
        boolean z = true;
        boolean z2 = !Thread.holdsLock(this);
        if (!_Assertions.ENABLED || z2) {
            synchronized (this) {
                if (this.source.getFinished$okhttp() || !this.source.getClosed$okhttp() || (!this.sink.getFinished() && !this.sink.getClosed())) {
                    z = false;
                }
                cancel = z;
                open = isOpen();
                Unit unit = Unit.INSTANCE;
            }
            if (cancel) {
                close(ErrorCode.CANCEL, null);
            } else if (!open) {
                this.connection.removeStream$okhttp(this.f262id);
            }
        } else {
            throw new AssertionError("Assertion failed");
        }
    }

    public final void addBytesToWriteWindow(long delta) {
        this.writeBytesMaximum += delta;
        if (delta > 0) {
            notifyAll();
        }
    }

    public final void checkOutNotClosed$okhttp() throws IOException {
        if (this.sink.getClosed()) {
            throw new IOException("stream closed");
        } else if (!this.sink.getFinished()) {
            ErrorCode errorCode2 = this.errorCode;
            if (errorCode2 != null) {
                Throwable th = this.errorException;
                if (th == null) {
                    if (errorCode2 == null) {
                        Intrinsics.throwNpe();
                    }
                    th = new StreamResetException(errorCode2);
                }
                throw th;
            }
        } else {
            throw new IOException("stream finished");
        }
    }

    public final void waitForIo$okhttp() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }
}
