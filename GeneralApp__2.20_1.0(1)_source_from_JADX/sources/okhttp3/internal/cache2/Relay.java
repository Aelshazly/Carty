package okhttp3.internal.cache2;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 :2\u00020\u0001:\u0002:;B3\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\u000e\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\tJ\b\u00105\u001a\u0004\u0018\u00010\u0005J \u00106\u001a\u0002032\u0006\u00107\u001a\u00020\t2\u0006\u00104\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u0007H\u0002J\u0010\u00109\u001a\u0002032\u0006\u00104\u001a\u00020\u0007H\u0002R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0015R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0011\"\u0004\b*\u0010+R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u0006<"}, mo24952d2 = {"Lokhttp3/internal/cache2/Relay;", "", "file", "Ljava/io/RandomAccessFile;", "upstream", "Lokio/Source;", "upstreamPos", "", "metadata", "Lokio/ByteString;", "bufferMaxSize", "(Ljava/io/RandomAccessFile;Lokio/Source;JLokio/ByteString;J)V", "buffer", "Lokio/Buffer;", "getBuffer", "()Lokio/Buffer;", "getBufferMaxSize", "()J", "complete", "", "getComplete", "()Z", "setComplete", "(Z)V", "getFile", "()Ljava/io/RandomAccessFile;", "setFile", "(Ljava/io/RandomAccessFile;)V", "isClosed", "sourceCount", "", "getSourceCount", "()I", "setSourceCount", "(I)V", "getUpstream", "()Lokio/Source;", "setUpstream", "(Lokio/Source;)V", "upstreamBuffer", "getUpstreamBuffer", "getUpstreamPos", "setUpstreamPos", "(J)V", "upstreamReader", "Ljava/lang/Thread;", "getUpstreamReader", "()Ljava/lang/Thread;", "setUpstreamReader", "(Ljava/lang/Thread;)V", "commit", "", "upstreamSize", "newSource", "writeHeader", "prefix", "metadataSize", "writeMetadata", "Companion", "RelaySource", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Relay.kt */
public final class Relay {
    public static final Companion Companion = new Companion(null);
    private static final long FILE_HEADER_SIZE = 32;
    public static final ByteString PREFIX_CLEAN = ByteString.Companion.encodeUtf8("OkHttp cache v1\n");
    public static final ByteString PREFIX_DIRTY = ByteString.Companion.encodeUtf8("OkHttp DIRTY :(\n");
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    private final Buffer buffer;
    private final long bufferMaxSize;
    private boolean complete;
    private RandomAccessFile file;
    private final ByteString metadata;
    private int sourceCount;
    private Source upstream;
    private final Buffer upstreamBuffer;
    private long upstreamPos;
    private Thread upstreamReader;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo24952d2 = {"Lokhttp3/internal/cache2/Relay$Companion;", "", "()V", "FILE_HEADER_SIZE", "", "PREFIX_CLEAN", "Lokio/ByteString;", "PREFIX_DIRTY", "SOURCE_FILE", "", "SOURCE_UPSTREAM", "edit", "Lokhttp3/internal/cache2/Relay;", "file", "Ljava/io/File;", "upstream", "Lokio/Source;", "metadata", "bufferMaxSize", "read", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Relay.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final Relay edit(File file, Source upstream, ByteString metadata, long bufferMaxSize) throws IOException {
            File file2 = file;
            Intrinsics.checkParameterIsNotNull(file2, "file");
            Intrinsics.checkParameterIsNotNull(upstream, "upstream");
            Intrinsics.checkParameterIsNotNull(metadata, "metadata");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
            Relay result = new Relay(randomAccessFile, upstream, 0, metadata, bufferMaxSize, null);
            randomAccessFile.setLength(0);
            result.writeHeader(Relay.PREFIX_DIRTY, -1, -1);
            return result;
        }

        public final Relay read(File file) throws IOException {
            File file2 = file;
            Intrinsics.checkParameterIsNotNull(file2, "file");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
            FileChannel channel = randomAccessFile.getChannel();
            Intrinsics.checkExpressionValueIsNotNull(channel, "randomAccessFile.channel");
            FileOperator fileOperator = new FileOperator(channel);
            Buffer header = new Buffer();
            fileOperator.read(0, header, 32);
            if (!(!Intrinsics.areEqual((Object) header.readByteString((long) Relay.PREFIX_CLEAN.size()), (Object) Relay.PREFIX_CLEAN))) {
                long upstreamSize = header.readLong();
                long metadataSize = header.readLong();
                Buffer metadataBuffer = new Buffer();
                fileOperator.read(upstreamSize + 32, metadataBuffer, metadataSize);
                Relay relay = new Relay(randomAccessFile, null, upstreamSize, metadataBuffer.readByteString(), 0, null);
                return relay;
            }
            throw new IOException("unreadable cache file");
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo24952d2 = {"Lokhttp3/internal/cache2/Relay$RelaySource;", "Lokio/Source;", "(Lokhttp3/internal/cache2/Relay;)V", "fileOperator", "Lokhttp3/internal/cache2/FileOperator;", "sourcePos", "", "timeout", "Lokio/Timeout;", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Relay.kt */
    public final class RelaySource implements Source {
        private FileOperator fileOperator;
        private long sourcePos;
        private final Timeout timeout = new Timeout();

        public RelaySource() {
            RandomAccessFile file = Relay.this.getFile();
            if (file == null) {
                Intrinsics.throwNpe();
            }
            FileChannel channel = file.getChannel();
            Intrinsics.checkExpressionValueIsNotNull(channel, "file!!.channel");
            this.fileOperator = new FileOperator(channel);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:101:?, code lost:
            r1.this$0.setUpstreamReader(null);
            r6 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:102:0x01d7, code lost:
            if (r6 == null) goto L_0x01d9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:104:0x01e0, code lost:
            throw new kotlin.TypeCastException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:105:0x01e1, code lost:
            r6.notifyAll();
            r5 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:107:0x01ea, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0091, code lost:
            if (r4 != 2) goto L_0x00b6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0093, code lost:
            r12 = java.lang.Math.min(r2, r1.this$0.getUpstreamPos() - r1.sourcePos);
            r4 = r1.fileOperator;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a2, code lost:
            if (r4 != null) goto L_0x00a7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a4, code lost:
            kotlin.jvm.internal.Intrinsics.throwNpe();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a7, code lost:
            r4.read(r1.sourcePos + 32, r26, r12);
            r1.sourcePos += r12;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b5, code lost:
            return r12;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            r0 = r1.this$0.getUpstream();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x00bf, code lost:
            if (r0 != null) goto L_0x00c4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c1, code lost:
            kotlin.jvm.internal.Intrinsics.throwNpe();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c4, code lost:
            r8 = r0.read(r1.this$0.getUpstreamBuffer(), r1.this$0.getBufferMaxSize());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d7, code lost:
            if (r8 != -1) goto L_0x010c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d9, code lost:
            r1.this$0.commit(r1.this$0.getUpstreamPos());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e4, code lost:
            r4 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e7, code lost:
            monitor-enter(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
            r1.this$0.setUpstreamReader(null);
            r5 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f5, code lost:
            if (r5 == null) goto L_0x0101;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f7, code lost:
            r5.notifyAll();
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ff, code lost:
            monitor-exit(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x0100, code lost:
            return -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0108, code lost:
            throw new kotlin.TypeCastException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
            r12 = java.lang.Math.min(r8, r2);
            r23 = r8;
            r1.this$0.getUpstreamBuffer().copyTo(r26, 0, r12);
            r1.sourcePos += r12;
            r0 = r1.fileOperator;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x0128, code lost:
            if (r0 != null) goto L_0x012d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x012a, code lost:
            kotlin.jvm.internal.Intrinsics.throwNpe();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x012d, code lost:
            r0.write(r1.this$0.getUpstreamPos() + 32, r1.this$0.getUpstreamBuffer().clone(), r23);
            r4 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x0148, code lost:
            monitor-enter(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x0156, code lost:
            r7 = r23;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
            r1.this$0.getBuffer().write(r1.this$0.getUpstreamBuffer(), r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:0x016d, code lost:
            if (r1.this$0.getBuffer().size() <= r1.this$0.getBufferMaxSize()) goto L_0x018a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x016f, code lost:
            r1.this$0.getBuffer().skip(r1.this$0.getBuffer().size() - r1.this$0.getBufferMaxSize());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:72:0x018a, code lost:
            r5 = r1.this$0;
            r5.setUpstreamPos(r5.getUpstreamPos() + r7);
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
            monitor-exit(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x0197, code lost:
            r4 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:76:0x019a, code lost:
            monitor-enter(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
            r1.this$0.setUpstreamReader(null);
            r5 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:0x01a8, code lost:
            if (r5 == null) goto L_0x01b4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:0x01aa, code lost:
            r5.notifyAll();
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:0x01b2, code lost:
            monitor-exit(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:83:0x01b3, code lost:
            return r12;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:86:0x01bb, code lost:
            throw new kotlin.TypeCastException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:90:0x01bf, code lost:
            r0 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:91:0x01c1, code lost:
            r0 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:92:0x01c2, code lost:
            r7 = r23;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
            monitor-exit(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:95:0x01c5, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:96:0x01c6, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:98:0x01c9, code lost:
            monitor-enter(r1.this$0);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(okio.Buffer r26, long r27) throws java.io.IOException {
            /*
                r25 = this;
                r1 = r25
                r2 = r27
                java.lang.String r0 = "sink"
                r10 = r26
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
                okhttp3.internal.cache2.FileOperator r0 = r1.fileOperator
                r4 = 1
                r5 = 0
                if (r0 == 0) goto L_0x0013
                r0 = 1
                goto L_0x0014
            L_0x0013:
                r0 = 0
            L_0x0014:
                if (r0 == 0) goto L_0x01f1
                okhttp3.internal.cache2.Relay r11 = okhttp3.internal.cache2.Relay.this
                monitor-enter(r11)
                r0 = r5
            L_0x001a:
                okhttp3.internal.cache2.Relay r5 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01ee }
                long r5 = r5.getUpstreamPos()     // Catch:{ all -> 0x01ee }
                long r7 = r1.sourcePos     // Catch:{ all -> 0x01ee }
                r9 = 2
                r12 = -1
                int r14 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r14 == 0) goto L_0x0069
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01ee }
                long r4 = r4.getUpstreamPos()     // Catch:{ all -> 0x01ee }
                okhttp3.internal.cache2.Relay r6 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01ee }
                okio.Buffer r6 = r6.getBuffer()     // Catch:{ all -> 0x01ee }
                long r6 = r6.size()     // Catch:{ all -> 0x01ee }
                long r14 = r4 - r6
                long r4 = r1.sourcePos     // Catch:{ all -> 0x01ee }
                int r6 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
                if (r6 >= 0) goto L_0x0044
                r4 = 2
                goto L_0x008d
            L_0x0044:
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01ee }
                long r4 = r4.getUpstreamPos()     // Catch:{ all -> 0x01ee }
                long r6 = r1.sourcePos     // Catch:{ all -> 0x01ee }
                long r4 = r4 - r6
                long r4 = java.lang.Math.min(r2, r4)     // Catch:{ all -> 0x01ee }
                r12 = r4
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01ee }
                okio.Buffer r4 = r4.getBuffer()     // Catch:{ all -> 0x01ee }
                long r5 = r1.sourcePos     // Catch:{ all -> 0x01ee }
                long r6 = r5 - r14
                r5 = r26
                r8 = r12
                r4.copyTo(r5, r6, r8)     // Catch:{ all -> 0x01ee }
                long r4 = r1.sourcePos     // Catch:{ all -> 0x01ee }
                long r4 = r4 + r12
                r1.sourcePos = r4     // Catch:{ all -> 0x01ee }
                monitor-exit(r11)
                return r12
            L_0x0069:
                okhttp3.internal.cache2.Relay r7 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01ee }
                boolean r7 = r7.getComplete()     // Catch:{ all -> 0x01ee }
                if (r7 == 0) goto L_0x0073
                monitor-exit(r11)
                return r12
            L_0x0073:
                okhttp3.internal.cache2.Relay r7 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01ee }
                java.lang.Thread r7 = r7.getUpstreamReader()     // Catch:{ all -> 0x01ee }
                if (r7 == 0) goto L_0x0083
                okio.Timeout r7 = r1.timeout     // Catch:{ all -> 0x01ee }
                okhttp3.internal.cache2.Relay r8 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01ee }
                r7.waitUntilNotified(r8)     // Catch:{ all -> 0x01ee }
                goto L_0x001a
            L_0x0083:
                okhttp3.internal.cache2.Relay r7 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01ee }
                java.lang.Thread r8 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x01ee }
                r7.setUpstreamReader(r8)     // Catch:{ all -> 0x01ee }
            L_0x008d:
                monitor-exit(r11)
                r11 = r4
                r14 = 32
                if (r11 != r9) goto L_0x00b6
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this
                long r4 = r0.getUpstreamPos()
                long r6 = r1.sourcePos
                long r4 = r4 - r6
                long r12 = java.lang.Math.min(r2, r4)
                okhttp3.internal.cache2.FileOperator r4 = r1.fileOperator
                if (r4 != 0) goto L_0x00a7
                kotlin.jvm.internal.Intrinsics.throwNpe()
            L_0x00a7:
                long r5 = r1.sourcePos
                long r5 = r5 + r14
                r7 = r26
                r8 = r12
                r4.read(r5, r7, r8)
                long r4 = r1.sourcePos
                long r4 = r4 + r12
                r1.sourcePos = r4
                return r12
            L_0x00b6:
                r16 = 0
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c6 }
                okio.Source r0 = r0.getUpstream()     // Catch:{ all -> 0x01c6 }
                if (r0 != 0) goto L_0x00c4
                kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01c6 }
            L_0x00c4:
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c6 }
                okio.Buffer r4 = r4.getUpstreamBuffer()     // Catch:{ all -> 0x01c6 }
                okhttp3.internal.cache2.Relay r5 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c6 }
                long r5 = r5.getBufferMaxSize()     // Catch:{ all -> 0x01c6 }
                long r4 = r0.read(r4, r5)     // Catch:{ all -> 0x01c6 }
                r8 = r4
                int r0 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
                if (r0 != 0) goto L_0x010c
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c6 }
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c6 }
                long r4 = r4.getUpstreamPos()     // Catch:{ all -> 0x01c6 }
                r0.commit(r4)     // Catch:{ all -> 0x01c6 }
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this
                monitor-enter(r4)
                r0 = 0
                okhttp3.internal.cache2.Relay r5 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0109 }
                r6 = r16
                java.lang.Thread r6 = (java.lang.Thread) r6     // Catch:{ all -> 0x0109 }
                r5.setUpstreamReader(r6)     // Catch:{ all -> 0x0109 }
                okhttp3.internal.cache2.Relay r5 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0109 }
                r6 = 0
                if (r5 == 0) goto L_0x0101
                r7 = r5
                java.lang.Object r7 = (java.lang.Object) r7     // Catch:{ all -> 0x0109 }
                r7.notifyAll()     // Catch:{ all -> 0x0109 }
                kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0109 }
                monitor-exit(r4)
                return r12
            L_0x0101:
                kotlin.TypeCastException r7 = new kotlin.TypeCastException     // Catch:{ all -> 0x0109 }
                java.lang.String r12 = "null cannot be cast to non-null type java.lang.Object"
                r7.<init>(r12)     // Catch:{ all -> 0x0109 }
                throw r7     // Catch:{ all -> 0x0109 }
            L_0x0109:
                r0 = move-exception
                monitor-exit(r4)
                throw r0
            L_0x010c:
                long r4 = java.lang.Math.min(r8, r2)     // Catch:{ all -> 0x01c6 }
                r12 = r4
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c6 }
                okio.Buffer r4 = r0.getUpstreamBuffer()     // Catch:{ all -> 0x01c6 }
                r6 = 0
                r5 = r26
                r23 = r8
                r8 = r12
                r4.copyTo(r5, r6, r8)     // Catch:{ all -> 0x01c6 }
                long r4 = r1.sourcePos     // Catch:{ all -> 0x01c6 }
                long r4 = r4 + r12
                r1.sourcePos = r4     // Catch:{ all -> 0x01c6 }
                okhttp3.internal.cache2.FileOperator r0 = r1.fileOperator     // Catch:{ all -> 0x01c6 }
                if (r0 != 0) goto L_0x012d
                kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01c6 }
            L_0x012d:
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c6 }
                long r4 = r4.getUpstreamPos()     // Catch:{ all -> 0x01c6 }
                long r18 = r4 + r14
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c6 }
                okio.Buffer r4 = r4.getUpstreamBuffer()     // Catch:{ all -> 0x01c6 }
                okio.Buffer r20 = r4.clone()     // Catch:{ all -> 0x01c6 }
                r17 = r0
                r21 = r23
                r17.write(r18, r20, r21)     // Catch:{ all -> 0x01c6 }
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c6 }
                monitor-enter(r4)     // Catch:{ all -> 0x01c6 }
                r0 = 0
                okhttp3.internal.cache2.Relay r5 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c1 }
                okio.Buffer r5 = r5.getBuffer()     // Catch:{ all -> 0x01c1 }
                okhttp3.internal.cache2.Relay r6 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01c1 }
                okio.Buffer r6 = r6.getUpstreamBuffer()     // Catch:{ all -> 0x01c1 }
                r7 = r23
                r5.write(r6, r7)     // Catch:{ all -> 0x01bf }
                okhttp3.internal.cache2.Relay r5 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01bf }
                okio.Buffer r5 = r5.getBuffer()     // Catch:{ all -> 0x01bf }
                long r5 = r5.size()     // Catch:{ all -> 0x01bf }
                okhttp3.internal.cache2.Relay r9 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01bf }
                long r14 = r9.getBufferMaxSize()     // Catch:{ all -> 0x01bf }
                int r9 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
                if (r9 <= 0) goto L_0x018a
                okhttp3.internal.cache2.Relay r5 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01bf }
                okio.Buffer r5 = r5.getBuffer()     // Catch:{ all -> 0x01bf }
                okhttp3.internal.cache2.Relay r6 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01bf }
                okio.Buffer r6 = r6.getBuffer()     // Catch:{ all -> 0x01bf }
                long r14 = r6.size()     // Catch:{ all -> 0x01bf }
                okhttp3.internal.cache2.Relay r6 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01bf }
                long r17 = r6.getBufferMaxSize()     // Catch:{ all -> 0x01bf }
                long r14 = r14 - r17
                r5.skip(r14)     // Catch:{ all -> 0x01bf }
            L_0x018a:
                okhttp3.internal.cache2.Relay r5 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01bf }
                long r14 = r5.getUpstreamPos()     // Catch:{ all -> 0x01bf }
                long r14 = r14 + r7
                r5.setUpstreamPos(r14)     // Catch:{ all -> 0x01bf }
                kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01bf }
                monitor-exit(r4)     // Catch:{ all -> 0x01c6 }
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this
                monitor-enter(r4)
                r0 = 0
                okhttp3.internal.cache2.Relay r5 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01bc }
                r6 = r16
                java.lang.Thread r6 = (java.lang.Thread) r6     // Catch:{ all -> 0x01bc }
                r5.setUpstreamReader(r6)     // Catch:{ all -> 0x01bc }
                okhttp3.internal.cache2.Relay r5 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01bc }
                r6 = 0
                if (r5 == 0) goto L_0x01b4
                r9 = r5
                java.lang.Object r9 = (java.lang.Object) r9     // Catch:{ all -> 0x01bc }
                r9.notifyAll()     // Catch:{ all -> 0x01bc }
                kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01bc }
                monitor-exit(r4)
                return r12
            L_0x01b4:
                kotlin.TypeCastException r9 = new kotlin.TypeCastException     // Catch:{ all -> 0x01bc }
                java.lang.String r14 = "null cannot be cast to non-null type java.lang.Object"
                r9.<init>(r14)     // Catch:{ all -> 0x01bc }
                throw r9     // Catch:{ all -> 0x01bc }
            L_0x01bc:
                r0 = move-exception
                monitor-exit(r4)
                throw r0
            L_0x01bf:
                r0 = move-exception
                goto L_0x01c4
            L_0x01c1:
                r0 = move-exception
                r7 = r23
            L_0x01c4:
                monitor-exit(r4)     // Catch:{ all -> 0x01c6 }
                throw r0     // Catch:{ all -> 0x01c6 }
            L_0x01c6:
                r0 = move-exception
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this
                monitor-enter(r4)
                r5 = 0
                okhttp3.internal.cache2.Relay r6 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01eb }
                r7 = r16
                java.lang.Thread r7 = (java.lang.Thread) r7     // Catch:{ all -> 0x01eb }
                r6.setUpstreamReader(r7)     // Catch:{ all -> 0x01eb }
                okhttp3.internal.cache2.Relay r6 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01eb }
                r7 = 0
                if (r6 != 0) goto L_0x01e1
                kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch:{ all -> 0x01eb }
                java.lang.String r8 = "null cannot be cast to non-null type java.lang.Object"
                r0.<init>(r8)     // Catch:{ all -> 0x01eb }
                throw r0     // Catch:{ all -> 0x01eb }
            L_0x01e1:
                r8 = r6
                java.lang.Object r8 = (java.lang.Object) r8     // Catch:{ all -> 0x01eb }
                r8.notifyAll()     // Catch:{ all -> 0x01eb }
                kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01eb }
                monitor-exit(r4)
                throw r0
            L_0x01eb:
                r0 = move-exception
                monitor-exit(r4)
                throw r0
            L_0x01ee:
                r0 = move-exception
                monitor-exit(r11)
                throw r0
            L_0x01f1:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r4 = "Check failed."
                java.lang.String r4 = r4.toString()
                r0.<init>(r4)
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache2.Relay.RelaySource.read(okio.Buffer, long):long");
        }

        public Timeout timeout() {
            return this.timeout;
        }

        public void close() throws IOException {
            if (this.fileOperator != null) {
                this.fileOperator = null;
                RandomAccessFile randomAccessFile = null;
                synchronized (Relay.this) {
                    Relay relay = Relay.this;
                    relay.setSourceCount(relay.getSourceCount() - 1);
                    if (Relay.this.getSourceCount() == 0) {
                        randomAccessFile = Relay.this.getFile();
                        Relay.this.setFile(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
                if (randomAccessFile != null) {
                    Util.closeQuietly((Closeable) randomAccessFile);
                }
            }
        }
    }

    private Relay(RandomAccessFile file2, Source upstream2, long upstreamPos2, ByteString metadata2, long bufferMaxSize2) {
        this.file = file2;
        this.upstream = upstream2;
        this.upstreamPos = upstreamPos2;
        this.metadata = metadata2;
        this.bufferMaxSize = bufferMaxSize2;
        this.upstreamBuffer = new Buffer();
        this.complete = this.upstream == null;
        this.buffer = new Buffer();
    }

    public /* synthetic */ Relay(RandomAccessFile file2, Source upstream2, long upstreamPos2, ByteString metadata2, long bufferMaxSize2, DefaultConstructorMarker $constructor_marker) {
        this(file2, upstream2, upstreamPos2, metadata2, bufferMaxSize2);
    }

    public final RandomAccessFile getFile() {
        return this.file;
    }

    public final void setFile(RandomAccessFile randomAccessFile) {
        this.file = randomAccessFile;
    }

    public final Source getUpstream() {
        return this.upstream;
    }

    public final void setUpstream(Source source) {
        this.upstream = source;
    }

    public final long getUpstreamPos() {
        return this.upstreamPos;
    }

    public final void setUpstreamPos(long j) {
        this.upstreamPos = j;
    }

    public final long getBufferMaxSize() {
        return this.bufferMaxSize;
    }

    public final Thread getUpstreamReader() {
        return this.upstreamReader;
    }

    public final void setUpstreamReader(Thread thread) {
        this.upstreamReader = thread;
    }

    public final Buffer getUpstreamBuffer() {
        return this.upstreamBuffer;
    }

    public final boolean getComplete() {
        return this.complete;
    }

    public final void setComplete(boolean z) {
        this.complete = z;
    }

    public final Buffer getBuffer() {
        return this.buffer;
    }

    public final int getSourceCount() {
        return this.sourceCount;
    }

    public final void setSourceCount(int i) {
        this.sourceCount = i;
    }

    public final boolean isClosed() {
        return this.file == null;
    }

    /* access modifiers changed from: private */
    public final void writeHeader(ByteString prefix, long upstreamSize, long metadataSize) throws IOException {
        Buffer header = new Buffer();
        Buffer $this$apply = header;
        $this$apply.write(prefix);
        $this$apply.writeLong(upstreamSize);
        $this$apply.writeLong(metadataSize);
        if ($this$apply.size() == 32) {
            RandomAccessFile randomAccessFile = this.file;
            if (randomAccessFile == null) {
                Intrinsics.throwNpe();
            }
            FileChannel channel = randomAccessFile.getChannel();
            Intrinsics.checkExpressionValueIsNotNull(channel, "file!!.channel");
            new FileOperator(channel).write(0, header, 32);
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private final void writeMetadata(long upstreamSize) throws IOException {
        Buffer metadataBuffer = new Buffer();
        metadataBuffer.write(this.metadata);
        RandomAccessFile randomAccessFile = this.file;
        if (randomAccessFile == null) {
            Intrinsics.throwNpe();
        }
        FileChannel channel = randomAccessFile.getChannel();
        Intrinsics.checkExpressionValueIsNotNull(channel, "file!!.channel");
        new FileOperator(channel).write(32 + upstreamSize, metadataBuffer, (long) this.metadata.size());
    }

    public final void commit(long upstreamSize) throws IOException {
        writeMetadata(upstreamSize);
        RandomAccessFile randomAccessFile = this.file;
        if (randomAccessFile == null) {
            Intrinsics.throwNpe();
        }
        randomAccessFile.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, upstreamSize, (long) this.metadata.size());
        RandomAccessFile randomAccessFile2 = this.file;
        if (randomAccessFile2 == null) {
            Intrinsics.throwNpe();
        }
        randomAccessFile2.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
            Unit unit = Unit.INSTANCE;
        }
        Source source = this.upstream;
        if (source != null) {
            Util.closeQuietly((Closeable) source);
        }
        this.upstream = null;
    }

    public final ByteString metadata() {
        return this.metadata;
    }

    public final Source newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }
}
