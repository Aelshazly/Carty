package okhttp3.internal.http2;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Hpack.Reader;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 #2\u00020\u0001:\u0003#$%B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J(\u0010\u0012\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u0017\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J.\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001c\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001d\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J\u0018\u0010\u001e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001f\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010 \u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010!\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\"\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Reader;", "Ljava/io/Closeable;", "source", "Lokio/BufferedSource;", "client", "", "(Lokio/BufferedSource;Z)V", "continuation", "Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "hpackReader", "Lokhttp3/internal/http2/Hpack$Reader;", "close", "", "nextFrame", "requireSettings", "handler", "Lokhttp3/internal/http2/Http2Reader$Handler;", "readConnectionPreface", "readData", "length", "", "flags", "streamId", "readGoAway", "readHeaderBlock", "", "Lokhttp3/internal/http2/Header;", "padding", "readHeaders", "readPing", "readPriority", "readPushPromise", "readRstStream", "readSettings", "readWindowUpdate", "Companion", "ContinuationSource", "Handler", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Http2Reader.kt */
public final class Http2Reader implements Closeable {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final Logger logger;
    private final boolean client;
    private final ContinuationSource continuation = new ContinuationSource(this.source);
    private final Reader hpackReader;
    private final BufferedSource source;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Reader$Companion;", "", "()V", "logger", "Ljava/util/logging/Logger;", "getLogger", "()Ljava/util/logging/Logger;", "lengthWithoutPadding", "", "length", "flags", "padding", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http2Reader.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final Logger getLogger() {
            return Http2Reader.logger;
        }

        public final int lengthWithoutPadding(int length, int flags, int padding) throws IOException {
            int result = length;
            if ((flags & 8) != 0) {
                result--;
            }
            if (padding <= result) {
                return result - padding;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("PROTOCOL_ERROR padding ");
            sb.append(padding);
            sb.append(" > remaining length ");
            sb.append(result);
            throw new IOException(sb.toString());
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u0018H\u0002J\b\u0010\u001f\u001a\u00020 H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\u001a\u0010\u0011\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\b\"\u0004\b\u0016\u0010\n¨\u0006!"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "Lokio/Source;", "source", "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "flags", "", "getFlags", "()I", "setFlags", "(I)V", "left", "getLeft", "setLeft", "length", "getLength", "setLength", "padding", "getPadding", "setPadding", "streamId", "getStreamId", "setStreamId", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "readContinuationHeader", "timeout", "Lokio/Timeout;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http2Reader.kt */
    public static final class ContinuationSource implements Source {
        private int flags;
        private int left;
        private int length;
        private int padding;
        private final BufferedSource source;
        private int streamId;

        public ContinuationSource(BufferedSource source2) {
            Intrinsics.checkParameterIsNotNull(source2, Param.SOURCE);
            this.source = source2;
        }

        public final int getLength() {
            return this.length;
        }

        public final void setLength(int i) {
            this.length = i;
        }

        public final int getFlags() {
            return this.flags;
        }

        public final void setFlags(int i) {
            this.flags = i;
        }

        public final int getStreamId() {
            return this.streamId;
        }

        public final void setStreamId(int i) {
            this.streamId = i;
        }

        public final int getLeft() {
            return this.left;
        }

        public final void setLeft(int i) {
            this.left = i;
        }

        public final int getPadding() {
            return this.padding;
        }

        public final void setPadding(int i) {
            this.padding = i;
        }

        public long read(Buffer sink, long byteCount) throws IOException {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            while (true) {
                int i = this.left;
                if (i == 0) {
                    this.source.skip((long) this.padding);
                    this.padding = 0;
                    if ((this.flags & 4) != 0) {
                        return -1;
                    }
                    readContinuationHeader();
                } else {
                    long read = this.source.read(sink, Math.min(byteCount, (long) i));
                    if (read == -1) {
                        return -1;
                    }
                    this.left -= (int) read;
                    return read;
                }
            }
        }

        public Timeout timeout() {
            return this.source.timeout();
        }

        public void close() throws IOException {
        }

        private final void readContinuationHeader() throws IOException {
            int previousStreamId = this.streamId;
            this.left = Util.readMedium(this.source);
            this.length = this.left;
            int type = Util.and(this.source.readByte(), 255);
            this.flags = Util.and(this.source.readByte(), 255);
            if (Http2Reader.Companion.getLogger().isLoggable(Level.FINE)) {
                Http2Reader.Companion.getLogger().fine(Http2.INSTANCE.frameLog(true, this.streamId, this.length, type, this.flags));
            }
            this.streamId = this.source.readInt() & Integer.MAX_VALUE;
            if (type != 9) {
                StringBuilder sb = new StringBuilder();
                sb.append(type);
                sb.append(" != TYPE_CONTINUATION");
                throw new IOException(sb.toString());
            } else if (this.streamId != previousStreamId) {
                throw new IOException("TYPE_CONTINUATION streamId changed");
            }
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J8\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH&J(\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H&J \u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\nH&J.\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH&J \u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0006H&J(\u0010#\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0011H&J&\u0010'\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH&J\u0018\u0010*\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0018\u0010+\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u00112\u0006\u0010+\u001a\u00020-H&J\u0018\u0010.\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u000eH&¨\u00060"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2Reader$Handler;", "", "ackSettings", "", "alternateService", "streamId", "", "origin", "", "protocol", "Lokio/ByteString;", "host", "port", "maxAge", "", "data", "inFinished", "", "source", "Lokio/BufferedSource;", "length", "goAway", "lastGoodStreamId", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "debugData", "headers", "associatedStreamId", "headerBlock", "", "Lokhttp3/internal/http2/Header;", "ping", "ack", "payload1", "payload2", "priority", "streamDependency", "weight", "exclusive", "pushPromise", "promisedStreamId", "requestHeaders", "rstStream", "settings", "clearPrevious", "Lokhttp3/internal/http2/Settings;", "windowUpdate", "windowSizeIncrement", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http2Reader.kt */
    public interface Handler {
        void ackSettings();

        void alternateService(int i, String str, ByteString byteString, String str2, int i2, long j);

        void data(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException;

        void goAway(int i, ErrorCode errorCode, ByteString byteString);

        void headers(boolean z, int i, int i2, List<Header> list);

        void ping(boolean z, int i, int i2);

        void priority(int i, int i2, int i3, boolean z);

        void pushPromise(int i, int i2, List<Header> list) throws IOException;

        void rstStream(int i, ErrorCode errorCode);

        void settings(boolean z, Settings settings);

        void windowUpdate(int i, long j);
    }

    public Http2Reader(BufferedSource source2, boolean client2) {
        Intrinsics.checkParameterIsNotNull(source2, Param.SOURCE);
        this.source = source2;
        this.client = client2;
        Reader reader = new Reader(this.continuation, 4096, 0, 4, null);
        this.hpackReader = reader;
    }

    public final void readConnectionPreface(Handler handler) throws IOException {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        if (!this.client) {
            ByteString connectionPreface = this.source.readByteString((long) Http2.CONNECTION_PREFACE.size());
            if (logger.isLoggable(Level.FINE)) {
                Logger logger2 = logger;
                StringBuilder sb = new StringBuilder();
                sb.append("<< CONNECTION ");
                sb.append(connectionPreface.hex());
                logger2.fine(Util.format(sb.toString(), new Object[0]));
            }
            if (true ^ Intrinsics.areEqual((Object) Http2.CONNECTION_PREFACE, (Object) connectionPreface)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Expected a connection header but was ");
                sb2.append(connectionPreface.utf8());
                throw new IOException(sb2.toString());
            }
        } else if (!nextFrame(true, handler)) {
            throw new IOException("Required SETTINGS preface not received");
        }
    }

    public final boolean nextFrame(boolean requireSettings, Handler handler) throws IOException {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        try {
            this.source.require(9);
            int length = Util.readMedium(this.source);
            if (length <= 16384) {
                int type = Util.and(this.source.readByte(), 255);
                if (!requireSettings || type == 4) {
                    int flags = Util.and(this.source.readByte(), 255);
                    int streamId = this.source.readInt() & Integer.MAX_VALUE;
                    if (logger.isLoggable(Level.FINE)) {
                        logger.fine(Http2.INSTANCE.frameLog(true, streamId, length, type, flags));
                    }
                    switch (type) {
                        case 0:
                            readData(handler, length, flags, streamId);
                            break;
                        case 1:
                            readHeaders(handler, length, flags, streamId);
                            break;
                        case 2:
                            readPriority(handler, length, flags, streamId);
                            break;
                        case 3:
                            readRstStream(handler, length, flags, streamId);
                            break;
                        case 4:
                            readSettings(handler, length, flags, streamId);
                            break;
                        case 5:
                            readPushPromise(handler, length, flags, streamId);
                            break;
                        case 6:
                            readPing(handler, length, flags, streamId);
                            break;
                        case 7:
                            readGoAway(handler, length, flags, streamId);
                            break;
                        case 8:
                            readWindowUpdate(handler, length, flags, streamId);
                            break;
                        default:
                            this.source.skip((long) length);
                            break;
                    }
                    return true;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Expected a SETTINGS frame but was ");
                sb.append(type);
                throw new IOException(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("FRAME_SIZE_ERROR: ");
            sb2.append(length);
            throw new IOException(sb2.toString());
        } catch (EOFException e) {
            return false;
        }
    }

    private final void readHeaders(Handler handler, int length, int flags, int streamId) throws IOException {
        if (streamId != 0) {
            int padding = 0;
            boolean endStream = (flags & 1) != 0;
            if ((flags & 8) != 0) {
                padding = Util.and(this.source.readByte(), 255);
            }
            int headerBlockLength = length;
            if ((flags & 32) != 0) {
                readPriority(handler, streamId);
                headerBlockLength -= 5;
            }
            handler.headers(endStream, streamId, -1, readHeaderBlock(Companion.lengthWithoutPadding(headerBlockLength, flags, padding), padding, flags, streamId));
            return;
        }
        throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
    }

    private final List<Header> readHeaderBlock(int length, int padding, int flags, int streamId) throws IOException {
        this.continuation.setLeft(length);
        ContinuationSource continuationSource = this.continuation;
        continuationSource.setLength(continuationSource.getLeft());
        this.continuation.setPadding(padding);
        this.continuation.setFlags(flags);
        this.continuation.setStreamId(streamId);
        this.hpackReader.readHeaders();
        return this.hpackReader.getAndResetHeaderList();
    }

    private final void readData(Handler handler, int length, int flags, int streamId) throws IOException {
        if (streamId != 0) {
            int padding = 0;
            boolean gzipped = true;
            boolean inFinished = (flags & 1) != 0;
            if ((flags & 32) == 0) {
                gzipped = false;
            }
            if (!gzipped) {
                if ((flags & 8) != 0) {
                    padding = Util.and(this.source.readByte(), 255);
                }
                handler.data(inFinished, streamId, this.source, Companion.lengthWithoutPadding(length, flags, padding));
                this.source.skip((long) padding);
                return;
            }
            throw new IOException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA");
        }
        throw new IOException("PROTOCOL_ERROR: TYPE_DATA streamId == 0");
    }

    private final void readPriority(Handler handler, int length, int flags, int streamId) throws IOException {
        if (length != 5) {
            StringBuilder sb = new StringBuilder();
            sb.append("TYPE_PRIORITY length: ");
            sb.append(length);
            sb.append(" != 5");
            throw new IOException(sb.toString());
        } else if (streamId != 0) {
            readPriority(handler, streamId);
        } else {
            throw new IOException("TYPE_PRIORITY streamId == 0");
        }
    }

    private final void readPriority(Handler handler, int streamId) throws IOException {
        int w1 = this.source.readInt();
        handler.priority(streamId, Integer.MAX_VALUE & w1, Util.and(this.source.readByte(), 255) + 1, (w1 & ((int) CacheValidityPolicy.MAX_AGE)) != 0);
    }

    private final void readRstStream(Handler handler, int length, int flags, int streamId) throws IOException {
        if (length != 4) {
            StringBuilder sb = new StringBuilder();
            sb.append("TYPE_RST_STREAM length: ");
            sb.append(length);
            sb.append(" != 4");
            throw new IOException(sb.toString());
        } else if (streamId != 0) {
            int errorCodeInt = this.source.readInt();
            ErrorCode errorCode = ErrorCode.Companion.fromHttp2(errorCodeInt);
            if (errorCode != null) {
                handler.rstStream(streamId, errorCode);
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("TYPE_RST_STREAM unexpected error code: ");
            sb2.append(errorCodeInt);
            throw new IOException(sb2.toString());
        } else {
            throw new IOException("TYPE_RST_STREAM streamId == 0");
        }
    }

    private final void readSettings(Handler handler, int length, int flags, int streamId) throws IOException {
        int value;
        if (streamId != 0) {
            throw new IOException("TYPE_SETTINGS streamId != 0");
        } else if ((flags & 1) != 0) {
            if (length == 0) {
                handler.ackSettings();
                return;
            }
            throw new IOException("FRAME_SIZE_ERROR ack frame should be empty!");
        } else if (length % 6 == 0) {
            Settings settings = new Settings();
            IntProgression step = RangesKt.step((IntProgression) RangesKt.until(0, length), 6);
            int i = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if (step2 < 0 ? i >= last : i <= last) {
                while (true) {
                    int id = Util.and(this.source.readShort(), 65535);
                    value = this.source.readInt();
                    if (id != 1) {
                        if (id != 2) {
                            if (id == 3) {
                                id = 4;
                            } else if (id == 4) {
                                id = 7;
                                if (value < 0) {
                                    throw new IOException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1");
                                }
                            } else if (id == 5 && (value < 16384 || value > 16777215)) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: ");
                                sb.append(value);
                            }
                        } else if (!(value == 0 || value == 1)) {
                            throw new IOException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1");
                        }
                    }
                    settings.set(id, value);
                    if (i == last) {
                        break;
                    }
                    i += step2;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: ");
                sb2.append(value);
                throw new IOException(sb2.toString());
            }
            handler.settings(false, settings);
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("TYPE_SETTINGS length % 6 != 0: ");
            sb3.append(length);
            throw new IOException(sb3.toString());
        }
    }

    private final void readPushPromise(Handler handler, int length, int flags, int streamId) throws IOException {
        if (streamId != 0) {
            int padding = (flags & 8) != 0 ? Util.and(this.source.readByte(), 255) : 0;
            handler.pushPromise(streamId, this.source.readInt() & Integer.MAX_VALUE, readHeaderBlock(Companion.lengthWithoutPadding(length - 4, flags, padding), padding, flags, streamId));
            return;
        }
        throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
    }

    private final void readPing(Handler handler, int length, int flags, int streamId) throws IOException {
        if (length != 8) {
            StringBuilder sb = new StringBuilder();
            sb.append("TYPE_PING length != 8: ");
            sb.append(length);
            throw new IOException(sb.toString());
        } else if (streamId == 0) {
            handler.ping((flags & 1) != 0, this.source.readInt(), this.source.readInt());
        } else {
            throw new IOException("TYPE_PING streamId != 0");
        }
    }

    private final void readGoAway(Handler handler, int length, int flags, int streamId) throws IOException {
        if (length < 8) {
            StringBuilder sb = new StringBuilder();
            sb.append("TYPE_GOAWAY length < 8: ");
            sb.append(length);
            throw new IOException(sb.toString());
        } else if (streamId == 0) {
            int lastStreamId = this.source.readInt();
            int errorCodeInt = this.source.readInt();
            int opaqueDataLength = length - 8;
            ErrorCode errorCode = ErrorCode.Companion.fromHttp2(errorCodeInt);
            if (errorCode != null) {
                ByteString debugData = ByteString.EMPTY;
                if (opaqueDataLength > 0) {
                    debugData = this.source.readByteString((long) opaqueDataLength);
                }
                handler.goAway(lastStreamId, errorCode, debugData);
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("TYPE_GOAWAY unexpected error code: ");
            sb2.append(errorCodeInt);
            throw new IOException(sb2.toString());
        } else {
            throw new IOException("TYPE_GOAWAY streamId != 0");
        }
    }

    private final void readWindowUpdate(Handler handler, int length, int flags, int streamId) throws IOException {
        if (length == 4) {
            long increment = Util.and(this.source.readInt(), 2147483647L);
            if (increment != 0) {
                handler.windowUpdate(streamId, increment);
                return;
            }
            throw new IOException("windowSizeIncrement was 0");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("TYPE_WINDOW_UPDATE length !=4: ");
        sb.append(length);
        throw new IOException(sb.toString());
    }

    public void close() throws IOException {
        this.source.close();
    }

    static {
        Logger logger2 = Logger.getLogger(Http2.class.getName());
        Intrinsics.checkExpressionValueIsNotNull(logger2, "Logger.getLogger(Http2::class.java.name)");
        logger = logger2;
    }
}
