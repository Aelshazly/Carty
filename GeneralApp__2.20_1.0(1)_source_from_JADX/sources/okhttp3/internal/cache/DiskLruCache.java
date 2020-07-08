package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import okhttp3.internal.Util;
import okhttp3.internal.p016io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import okio.Source;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010)\n\u0002\b\u0007\u0018\u0000 V2\u00020\u00012\u00020\u0002:\u0004VWXYB7\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u00104\u001a\u000205H\u0002J\b\u00106\u001a\u000205H\u0016J!\u00107\u001a\u0002052\n\u00108\u001a\u000609R\u00020\u00002\u0006\u0010:\u001a\u00020\u0012H\u0000¢\u0006\u0002\b;J\u0006\u0010<\u001a\u000205J \u0010=\u001a\b\u0018\u000109R\u00020\u00002\u0006\u0010>\u001a\u00020$2\b\b\u0002\u0010?\u001a\u00020\u000bH\u0007J\u0006\u0010@\u001a\u000205J\b\u0010A\u001a\u000205H\u0016J\u0017\u0010B\u001a\b\u0018\u00010CR\u00020\u00002\u0006\u0010>\u001a\u00020$H\u0002J\u0006\u0010D\u001a\u000205J\u0006\u0010E\u001a\u00020\u0012J\b\u0010F\u001a\u00020\u0012H\u0002J\b\u0010G\u001a\u00020!H\u0002J\b\u0010H\u001a\u000205H\u0002J\b\u0010I\u001a\u000205H\u0002J\u0010\u0010J\u001a\u0002052\u0006\u0010K\u001a\u00020$H\u0002J\r\u0010L\u001a\u000205H\u0000¢\u0006\u0002\bMJ\u000e\u0010N\u001a\u00020\u00122\u0006\u0010>\u001a\u00020$J\u0019\u0010O\u001a\u00020\u00122\n\u0010P\u001a\u00060%R\u00020\u0000H\u0000¢\u0006\u0002\bQJ\u0006\u00101\u001a\u00020\u000bJ\u0010\u0010R\u001a\f\u0012\b\u0012\u00060CR\u00020\u00000SJ\u0006\u0010T\u001a\u000205J\u0010\u0010U\u001a\u0002052\u0006\u0010>\u001a\u00020$H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u000e¢\u0006\u0002\n\u0000R$\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020$\u0012\b\u0012\u00060%R\u00020\u00000#X\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R&\u0010\n\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u000b8F@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00103¨\u0006Z"}, mo24952d2 = {"Lokhttp3/internal/cache/DiskLruCache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "fileSystem", "Lokhttp3/internal/io/FileSystem;", "directory", "Ljava/io/File;", "appVersion", "", "valueCount", "maxSize", "", "executor", "Ljava/util/concurrent/Executor;", "(Lokhttp3/internal/io/FileSystem;Ljava/io/File;IIJLjava/util/concurrent/Executor;)V", "cleanupRunnable", "Ljava/lang/Runnable;", "closed", "", "getClosed$okhttp", "()Z", "setClosed$okhttp", "(Z)V", "getDirectory", "()Ljava/io/File;", "getFileSystem$okhttp", "()Lokhttp3/internal/io/FileSystem;", "hasJournalErrors", "initialized", "journalFile", "journalFileBackup", "journalFileTmp", "journalWriter", "Lokio/BufferedSink;", "lruEntries", "Ljava/util/LinkedHashMap;", "", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "getLruEntries$okhttp", "()Ljava/util/LinkedHashMap;", "value", "getMaxSize", "()J", "setMaxSize", "(J)V", "mostRecentRebuildFailed", "mostRecentTrimFailed", "nextSequenceNumber", "redundantOpCount", "size", "getValueCount$okhttp", "()I", "checkNotClosed", "", "close", "completeEdit", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "success", "completeEdit$okhttp", "delete", "edit", "key", "expectedSequenceNumber", "evictAll", "flush", "get", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "initialize", "isClosed", "journalRebuildRequired", "newJournalWriter", "processJournal", "readJournal", "readJournalLine", "line", "rebuildJournal", "rebuildJournal$okhttp", "remove", "removeEntry", "entry", "removeEntry$okhttp", "snapshots", "", "trimToSize", "validateKey", "Companion", "Editor", "Entry", "Snapshot", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: DiskLruCache.kt */
public final class DiskLruCache implements Closeable, Flushable {
    public static final long ANY_SEQUENCE_NUMBER = -1;
    public static final String CLEAN = CLEAN;
    public static final Companion Companion = new Companion(null);
    public static final String DIRTY = DIRTY;
    public static final String JOURNAL_FILE = JOURNAL_FILE;
    public static final String JOURNAL_FILE_BACKUP = JOURNAL_FILE_BACKUP;
    public static final String JOURNAL_FILE_TEMP = JOURNAL_FILE_TEMP;
    public static final Regex LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");
    public static final String MAGIC = MAGIC;
    public static final String READ = READ;
    public static final String REMOVE = REMOVE;
    public static final String VERSION_1 = VERSION_1;
    private final int appVersion;
    private final Runnable cleanupRunnable = new DiskLruCache$cleanupRunnable$1(this);
    private boolean closed;
    private final File directory;
    private final Executor executor;
    private final FileSystem fileSystem;
    /* access modifiers changed from: private */
    public boolean hasJournalErrors;
    /* access modifiers changed from: private */
    public boolean initialized;
    private final File journalFile = new File(this.directory, JOURNAL_FILE);
    private final File journalFileBackup = new File(this.directory, JOURNAL_FILE_BACKUP);
    private final File journalFileTmp = new File(this.directory, JOURNAL_FILE_TEMP);
    /* access modifiers changed from: private */
    public BufferedSink journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private long maxSize;
    /* access modifiers changed from: private */
    public boolean mostRecentRebuildFailed;
    /* access modifiers changed from: private */
    public boolean mostRecentTrimFailed;
    private long nextSequenceNumber;
    /* access modifiers changed from: private */
    public int redundantOpCount;
    private long size;
    private final int valueCount;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u00020\u00048\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000¨\u0006\u001b"}, mo24952d2 = {"Lokhttp3/internal/cache/DiskLruCache$Companion;", "", "()V", "ANY_SEQUENCE_NUMBER", "", "CLEAN", "", "DIRTY", "JOURNAL_FILE", "JOURNAL_FILE_BACKUP", "JOURNAL_FILE_TEMP", "LEGAL_KEY_PATTERN", "Lkotlin/text/Regex;", "MAGIC", "READ", "REMOVE", "VERSION_1", "create", "Lokhttp3/internal/cache/DiskLruCache;", "fileSystem", "Lokhttp3/internal/io/FileSystem;", "directory", "Ljava/io/File;", "appVersion", "", "valueCount", "maxSize", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: DiskLruCache.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final DiskLruCache create(FileSystem fileSystem, File directory, int appVersion, int valueCount, long maxSize) {
            Intrinsics.checkParameterIsNotNull(fileSystem, "fileSystem");
            Intrinsics.checkParameterIsNotNull(directory, "directory");
            boolean z = false;
            if (maxSize > 0) {
                if (valueCount > 0) {
                    z = true;
                }
                if (z) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true));
                    DiskLruCache diskLruCache = new DiskLruCache(fileSystem, directory, appVersion, valueCount, maxSize, threadPoolExecutor);
                    return diskLruCache;
                }
                throw new IllegalArgumentException("valueCount <= 0".toString());
            }
            throw new IllegalArgumentException("maxSize <= 0".toString());
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0013\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\r\u0010\u0011\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0002\u001a\u00060\u0003R\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, mo24952d2 = {"Lokhttp3/internal/cache/DiskLruCache$Editor;", "", "entry", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "Lokhttp3/internal/cache/DiskLruCache;", "(Lokhttp3/internal/cache/DiskLruCache;Lokhttp3/internal/cache/DiskLruCache$Entry;)V", "done", "", "getEntry$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Entry;", "written", "", "getWritten$okhttp", "()[Z", "abort", "", "commit", "detach", "detach$okhttp", "newSink", "Lokio/Sink;", "index", "", "newSource", "Lokio/Source;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: DiskLruCache.kt */
    public final class Editor {
        private boolean done;
        private final Entry entry;
        final /* synthetic */ DiskLruCache this$0;
        private final boolean[] written;

        public Editor(DiskLruCache $outer, Entry entry2) {
            Intrinsics.checkParameterIsNotNull(entry2, "entry");
            this.this$0 = $outer;
            this.entry = entry2;
            this.written = this.entry.getReadable$okhttp() ? null : new boolean[$outer.getValueCount$okhttp()];
        }

        public final Entry getEntry$okhttp() {
            return this.entry;
        }

        public final boolean[] getWritten$okhttp() {
            return this.written;
        }

        public final void detach$okhttp() {
            if (Intrinsics.areEqual((Object) this.entry.getCurrentEditor$okhttp(), (Object) this)) {
                int valueCount$okhttp = this.this$0.getValueCount$okhttp();
                for (int i = 0; i < valueCount$okhttp; i++) {
                    try {
                        this.this$0.getFileSystem$okhttp().delete((File) this.entry.getDirtyFiles$okhttp().get(i));
                    } catch (IOException e) {
                    }
                }
                this.entry.setCurrentEditor$okhttp(null);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final okio.Source newSource(int r6) {
            /*
                r5 = this;
                okhttp3.internal.cache.DiskLruCache r0 = r5.this$0
                monitor-enter(r0)
                r1 = 0
                boolean r2 = r5.done     // Catch:{ all -> 0x0052 }
                r2 = r2 ^ 1
                if (r2 == 0) goto L_0x0044
                okhttp3.internal.cache.DiskLruCache$Entry r2 = r5.entry     // Catch:{ all -> 0x0052 }
                boolean r2 = r2.getReadable$okhttp()     // Catch:{ all -> 0x0052 }
                r3 = 0
                if (r2 == 0) goto L_0x0041
                okhttp3.internal.cache.DiskLruCache$Entry r2 = r5.entry     // Catch:{ all -> 0x0052 }
                okhttp3.internal.cache.DiskLruCache$Editor r2 = r2.getCurrentEditor$okhttp()     // Catch:{ all -> 0x0052 }
                r4 = r5
                okhttp3.internal.cache.DiskLruCache$Editor r4 = (okhttp3.internal.cache.DiskLruCache.Editor) r4     // Catch:{ all -> 0x0052 }
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)     // Catch:{ all -> 0x0052 }
                r2 = r2 ^ 1
                if (r2 == 0) goto L_0x0025
                goto L_0x0041
            L_0x0025:
                okhttp3.internal.cache.DiskLruCache r2 = r5.this$0     // Catch:{ FileNotFoundException -> 0x003d }
                okhttp3.internal.io.FileSystem r2 = r2.getFileSystem$okhttp()     // Catch:{ FileNotFoundException -> 0x003d }
                okhttp3.internal.cache.DiskLruCache$Entry r4 = r5.entry     // Catch:{ FileNotFoundException -> 0x003d }
                java.util.List r4 = r4.getCleanFiles$okhttp()     // Catch:{ FileNotFoundException -> 0x003d }
                java.lang.Object r4 = r4.get(r6)     // Catch:{ FileNotFoundException -> 0x003d }
                java.io.File r4 = (java.io.File) r4     // Catch:{ FileNotFoundException -> 0x003d }
                okio.Source r3 = r2.source(r4)     // Catch:{ FileNotFoundException -> 0x003d }
                goto L_0x003f
            L_0x003d:
                r2 = move-exception
            L_0x003f:
                monitor-exit(r0)
                return r3
            L_0x0041:
                monitor-exit(r0)
                return r3
            L_0x0044:
                java.lang.String r2 = "Check failed."
                java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0052 }
                java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0052 }
                r3.<init>(r2)     // Catch:{ all -> 0x0052 }
                java.lang.Throwable r3 = (java.lang.Throwable) r3     // Catch:{ all -> 0x0052 }
                throw r3     // Catch:{ all -> 0x0052 }
            L_0x0052:
                r1 = move-exception
                monitor-exit(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.Editor.newSource(int):okio.Source");
        }

        public final Sink newSink(int index) {
            synchronized (this.this$0) {
                if (!(!this.done)) {
                    throw new IllegalStateException("Check failed.".toString());
                } else if (!Intrinsics.areEqual((Object) this.entry.getCurrentEditor$okhttp(), (Object) this)) {
                    Sink blackhole = Okio.blackhole();
                    return blackhole;
                } else {
                    if (!this.entry.getReadable$okhttp()) {
                        boolean[] zArr = this.written;
                        if (zArr == null) {
                            Intrinsics.throwNpe();
                        }
                        zArr[index] = true;
                    }
                    try {
                        Sink faultHidingSink = new FaultHidingSink(this.this$0.getFileSystem$okhttp().sink((File) this.entry.getDirtyFiles$okhttp().get(index)), new DiskLruCache$Editor$newSink$$inlined$synchronized$lambda$1(this, index));
                        return faultHidingSink;
                    } catch (FileNotFoundException e) {
                        return Okio.blackhole();
                    }
                }
            }
        }

        public final void commit() throws IOException {
            synchronized (this.this$0) {
                if (!this.done) {
                    if (Intrinsics.areEqual((Object) this.entry.getCurrentEditor$okhttp(), (Object) this)) {
                        this.this$0.completeEdit$okhttp(this, true);
                    }
                    this.done = true;
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }

        public final void abort() throws IOException {
            synchronized (this.this$0) {
                if (!this.done) {
                    if (Intrinsics.areEqual((Object) this.entry.getCurrentEditor$okhttp(), (Object) this)) {
                        this.this$0.completeEdit$okhttp(this, false);
                    }
                    this.done = true;
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030(H\u0002J\u001b\u0010)\u001a\u00020*2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030(H\u0000¢\u0006\u0002\b+J\u0013\u0010,\u001a\b\u0018\u00010-R\u00020\fH\u0000¢\u0006\u0002\b.J\u0015\u0010/\u001a\u00020*2\u0006\u00100\u001a\u000201H\u0000¢\u0006\u0002\b2R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0018\u00010\u000bR\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u00063"}, mo24952d2 = {"Lokhttp3/internal/cache/DiskLruCache$Entry;", "", "key", "", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;)V", "cleanFiles", "", "Ljava/io/File;", "getCleanFiles$okhttp", "()Ljava/util/List;", "currentEditor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getCurrentEditor$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Editor;", "setCurrentEditor$okhttp", "(Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "dirtyFiles", "getDirtyFiles$okhttp", "getKey$okhttp", "()Ljava/lang/String;", "lengths", "", "getLengths$okhttp", "()[J", "readable", "", "getReadable$okhttp", "()Z", "setReadable$okhttp", "(Z)V", "sequenceNumber", "", "getSequenceNumber$okhttp", "()J", "setSequenceNumber$okhttp", "(J)V", "invalidLengths", "Ljava/io/IOException;", "strings", "", "setLengths", "", "setLengths$okhttp", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "snapshot$okhttp", "writeLengths", "writer", "Lokio/BufferedSink;", "writeLengths$okhttp", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: DiskLruCache.kt */
    public final class Entry {
        private final List<File> cleanFiles = new ArrayList();
        private Editor currentEditor;
        private final List<File> dirtyFiles = new ArrayList();
        private final String key;
        private final long[] lengths;
        private boolean readable;
        private long sequenceNumber;
        final /* synthetic */ DiskLruCache this$0;

        public Entry(DiskLruCache $outer, String key2) {
            Intrinsics.checkParameterIsNotNull(key2, "key");
            this.this$0 = $outer;
            this.key = key2;
            this.lengths = new long[$outer.getValueCount$okhttp()];
            StringBuilder fileBuilder = new StringBuilder(this.key).append('.');
            int truncateTo = fileBuilder.length();
            int valueCount$okhttp = $outer.getValueCount$okhttp();
            for (int i = 0; i < valueCount$okhttp; i++) {
                fileBuilder.append(i);
                this.cleanFiles.add(new File($outer.getDirectory(), fileBuilder.toString()));
                fileBuilder.append(".tmp");
                this.dirtyFiles.add(new File($outer.getDirectory(), fileBuilder.toString()));
                fileBuilder.setLength(truncateTo);
            }
        }

        public final String getKey$okhttp() {
            return this.key;
        }

        public final long[] getLengths$okhttp() {
            return this.lengths;
        }

        public final List<File> getCleanFiles$okhttp() {
            return this.cleanFiles;
        }

        public final List<File> getDirtyFiles$okhttp() {
            return this.dirtyFiles;
        }

        public final boolean getReadable$okhttp() {
            return this.readable;
        }

        public final void setReadable$okhttp(boolean z) {
            this.readable = z;
        }

        public final Editor getCurrentEditor$okhttp() {
            return this.currentEditor;
        }

        public final void setCurrentEditor$okhttp(Editor editor) {
            this.currentEditor = editor;
        }

        public final long getSequenceNumber$okhttp() {
            return this.sequenceNumber;
        }

        public final void setSequenceNumber$okhttp(long j) {
            this.sequenceNumber = j;
        }

        public final void setLengths$okhttp(List<String> strings) throws IOException {
            Intrinsics.checkParameterIsNotNull(strings, "strings");
            if (strings.size() == this.this$0.getValueCount$okhttp()) {
                try {
                    int size = strings.size();
                    for (int i = 0; i < size; i++) {
                        this.lengths[i] = Long.parseLong((String) strings.get(i));
                    }
                } catch (NumberFormatException e) {
                    throw invalidLengths(strings);
                }
            } else {
                throw invalidLengths(strings);
            }
        }

        public final void writeLengths$okhttp(BufferedSink writer) throws IOException {
            Intrinsics.checkParameterIsNotNull(writer, "writer");
            for (long length : this.lengths) {
                writer.writeByte(32).writeDecimalLong(length);
            }
        }

        private final IOException invalidLengths(List<String> strings) throws IOException {
            StringBuilder sb = new StringBuilder();
            sb.append("unexpected journal line: ");
            sb.append(strings);
            throw new IOException(sb.toString());
        }

        public final Snapshot snapshot$okhttp() {
            boolean holdsLock = Thread.holdsLock(this.this$0);
            if (!_Assertions.ENABLED || holdsLock) {
                List<Source> sources = new ArrayList<>();
                long[] lengths2 = (long[]) this.lengths.clone();
                try {
                    int valueCount$okhttp = this.this$0.getValueCount$okhttp();
                    for (int i = 0; i < valueCount$okhttp; i++) {
                        sources.add(this.this$0.getFileSystem$okhttp().source((File) this.cleanFiles.get(i)));
                    }
                    Snapshot snapshot = new Snapshot(this.this$0, this.key, this.sequenceNumber, sources, lengths2);
                    return snapshot;
                } catch (FileNotFoundException e) {
                    for (Source source : sources) {
                        Util.closeQuietly((Closeable) source);
                    }
                    try {
                        this.this$0.removeEntry$okhttp(this);
                    } catch (IOException e2) {
                    }
                    return null;
                }
            } else {
                throw new AssertionError("Assertion failed");
            }
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B-\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\f\u0010\u000e\u001a\b\u0018\u00010\u000fR\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0002\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, mo24952d2 = {"Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Ljava/io/Closeable;", "key", "", "sequenceNumber", "", "sources", "", "Lokio/Source;", "lengths", "", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;JLjava/util/List;[J)V", "close", "", "edit", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getLength", "index", "", "getSource", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: DiskLruCache.kt */
    public final class Snapshot implements Closeable {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final List<Source> sources;
        final /* synthetic */ DiskLruCache this$0;

        public Snapshot(DiskLruCache $outer, String key2, long sequenceNumber2, List<? extends Source> sources2, long[] lengths2) {
            Intrinsics.checkParameterIsNotNull(key2, "key");
            Intrinsics.checkParameterIsNotNull(sources2, "sources");
            Intrinsics.checkParameterIsNotNull(lengths2, "lengths");
            this.this$0 = $outer;
            this.key = key2;
            this.sequenceNumber = sequenceNumber2;
            this.sources = sources2;
            this.lengths = lengths2;
        }

        public final String key() {
            return this.key;
        }

        public final Editor edit() throws IOException {
            return this.this$0.edit(this.key, this.sequenceNumber);
        }

        public final Source getSource(int index) {
            return (Source) this.sources.get(index);
        }

        public final long getLength(int index) {
            return this.lengths[index];
        }

        public void close() {
            for (Source source : this.sources) {
                Util.closeQuietly((Closeable) source);
            }
        }
    }

    public final Editor edit(String str) throws IOException {
        return edit$default(this, str, 0, 2, null);
    }

    public DiskLruCache(FileSystem fileSystem2, File directory2, int appVersion2, int valueCount2, long maxSize2, Executor executor2) {
        Intrinsics.checkParameterIsNotNull(fileSystem2, "fileSystem");
        Intrinsics.checkParameterIsNotNull(directory2, "directory");
        Intrinsics.checkParameterIsNotNull(executor2, "executor");
        this.fileSystem = fileSystem2;
        this.directory = directory2;
        this.appVersion = appVersion2;
        this.valueCount = valueCount2;
        this.executor = executor2;
        this.maxSize = maxSize2;
    }

    public final FileSystem getFileSystem$okhttp() {
        return this.fileSystem;
    }

    public final File getDirectory() {
        return this.directory;
    }

    public final int getValueCount$okhttp() {
        return this.valueCount;
    }

    public final synchronized long getMaxSize() {
        return this.maxSize;
    }

    public final synchronized void setMaxSize(long value) {
        this.maxSize = value;
        if (this.initialized) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    public final LinkedHashMap<String, Entry> getLruEntries$okhttp() {
        return this.lruEntries;
    }

    public final boolean getClosed$okhttp() {
        return this.closed;
    }

    public final void setClosed$okhttp(boolean z) {
        this.closed = z;
    }

    public final synchronized void initialize() throws IOException {
        boolean holdsLock = Thread.holdsLock(this);
        if (_Assertions.ENABLED) {
            if (!holdsLock) {
                throw new AssertionError("Assertion failed");
            }
        }
        if (!this.initialized) {
            if (this.fileSystem.exists(this.journalFileBackup)) {
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.delete(this.journalFileBackup);
                } else {
                    this.fileSystem.rename(this.journalFileBackup, this.journalFile);
                }
            }
            if (this.fileSystem.exists(this.journalFile)) {
                try {
                    readJournal();
                    processJournal();
                    this.initialized = true;
                    return;
                } catch (IOException journalIsCorrupt) {
                    Platform platform = Platform.Companion.get();
                    StringBuilder sb = new StringBuilder();
                    sb.append("DiskLruCache ");
                    sb.append(this.directory);
                    sb.append(" is corrupt: ");
                    sb.append(journalIsCorrupt.getMessage());
                    sb.append(", removing");
                    platform.log(5, sb.toString(), journalIsCorrupt);
                    delete();
                    this.closed = false;
                } catch (Throwable th) {
                    this.closed = false;
                    throw th;
                }
            }
            rebuildJournal$okhttp();
            this.initialized = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00cb, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00cc, code lost:
        kotlin.p014io.CloseableKt.closeFinally(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00cf, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void readJournal() throws java.io.IOException {
        /*
            r12 = this;
            java.lang.String r0 = ", "
            okhttp3.internal.io.FileSystem r1 = r12.fileSystem
            java.io.File r2 = r12.journalFile
            okio.Source r1 = r1.source(r2)
            okio.BufferedSource r1 = okio.Okio.buffer(r1)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = r1
            okio.BufferedSource r3 = (okio.BufferedSource) r3     // Catch:{ all -> 0x00c9 }
            r4 = 0
            java.lang.String r5 = r3.readUtf8LineStrict()     // Catch:{ all -> 0x00c9 }
            java.lang.String r6 = r3.readUtf8LineStrict()     // Catch:{ all -> 0x00c9 }
            java.lang.String r7 = r3.readUtf8LineStrict()     // Catch:{ all -> 0x00c9 }
            java.lang.String r8 = r3.readUtf8LineStrict()     // Catch:{ all -> 0x00c9 }
            java.lang.String r9 = r3.readUtf8LineStrict()     // Catch:{ all -> 0x00c9 }
            java.lang.String r10 = MAGIC     // Catch:{ all -> 0x00c9 }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r5)     // Catch:{ all -> 0x00c9 }
            r11 = 1
            r10 = r10 ^ r11
            if (r10 != 0) goto L_0x0099
            java.lang.String r10 = VERSION_1     // Catch:{ all -> 0x00c9 }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r6)     // Catch:{ all -> 0x00c9 }
            r10 = r10 ^ r11
            if (r10 != 0) goto L_0x0099
            int r10 = r12.appVersion     // Catch:{ all -> 0x00c9 }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x00c9 }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r7)     // Catch:{ all -> 0x00c9 }
            r10 = r10 ^ r11
            if (r10 != 0) goto L_0x0099
            int r10 = r12.valueCount     // Catch:{ all -> 0x00c9 }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x00c9 }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r8)     // Catch:{ all -> 0x00c9 }
            r10 = r10 ^ r11
            if (r10 != 0) goto L_0x0099
            r10 = r9
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ all -> 0x00c9 }
            int r10 = r10.length()     // Catch:{ all -> 0x00c9 }
            if (r10 <= 0) goto L_0x0066
            goto L_0x0067
        L_0x0066:
            r11 = 0
        L_0x0067:
            if (r11 != 0) goto L_0x0099
            r0 = 0
        L_0x006a:
            java.lang.String r10 = r3.readUtf8LineStrict()     // Catch:{ EOFException -> 0x0076 }
            r12.readJournalLine(r10)     // Catch:{ EOFException -> 0x0076 }
            int r0 = r0 + 1
            goto L_0x006a
        L_0x0076:
            r10 = move-exception
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r10 = r12.lruEntries     // Catch:{ all -> 0x00c9 }
            int r10 = r10.size()     // Catch:{ all -> 0x00c9 }
            int r10 = r0 - r10
            r12.redundantOpCount = r10     // Catch:{ all -> 0x00c9 }
            boolean r10 = r3.exhausted()     // Catch:{ all -> 0x00c9 }
            if (r10 != 0) goto L_0x008c
            r12.rebuildJournal$okhttp()     // Catch:{ all -> 0x00c9 }
            goto L_0x0092
        L_0x008c:
            okio.BufferedSink r10 = r12.newJournalWriter()     // Catch:{ all -> 0x00c9 }
            r12.journalWriter = r10     // Catch:{ all -> 0x00c9 }
        L_0x0092:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00c9 }
            kotlin.p014io.CloseableKt.closeFinally(r1, r2)
            return
        L_0x0099:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x00c9 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c9 }
            r10.<init>()     // Catch:{ all -> 0x00c9 }
            java.lang.String r11 = "unexpected journal header: ["
            r10.append(r11)     // Catch:{ all -> 0x00c9 }
            r10.append(r5)     // Catch:{ all -> 0x00c9 }
            r10.append(r0)     // Catch:{ all -> 0x00c9 }
            r10.append(r6)     // Catch:{ all -> 0x00c9 }
            r10.append(r0)     // Catch:{ all -> 0x00c9 }
            r10.append(r8)     // Catch:{ all -> 0x00c9 }
            r10.append(r0)     // Catch:{ all -> 0x00c9 }
            r10.append(r9)     // Catch:{ all -> 0x00c9 }
            r0 = 93
            r10.append(r0)     // Catch:{ all -> 0x00c9 }
            java.lang.String r0 = r10.toString()     // Catch:{ all -> 0x00c9 }
            r2.<init>(r0)     // Catch:{ all -> 0x00c9 }
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch:{ all -> 0x00c9 }
            throw r2     // Catch:{ all -> 0x00c9 }
        L_0x00c9:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00cb }
        L_0x00cb:
            r2 = move-exception
            kotlin.p014io.CloseableKt.closeFinally(r1, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.readJournal():void");
    }

    private final BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer((Sink) new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile), new DiskLruCache$newJournalWriter$faultHidingSink$1(this)));
    }

    private final void readJournalLine(String line) throws IOException {
        String key;
        String str = line;
        int firstSpace = StringsKt.indexOf$default((CharSequence) str, ' ', 0, false, 6, (Object) null);
        String str2 = "unexpected journal line: ";
        if (firstSpace != -1) {
            int keyBegin = firstSpace + 1;
            int secondSpace = StringsKt.indexOf$default((CharSequence) str, ' ', keyBegin, false, 4, (Object) null);
            String str3 = "(this as java.lang.String).substring(startIndex)";
            String str4 = "null cannot be cast to non-null type java.lang.String";
            if (secondSpace == -1) {
                if (str != null) {
                    String substring = str.substring(keyBegin);
                    Intrinsics.checkExpressionValueIsNotNull(substring, str3);
                    key = substring;
                    if (firstSpace == REMOVE.length() && StringsKt.startsWith$default(str, REMOVE, false, 2, null)) {
                        this.lruEntries.remove(key);
                        return;
                    }
                } else {
                    throw new TypeCastException(str4);
                }
            } else if (str != null) {
                String substring2 = str.substring(keyBegin, secondSpace);
                Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                key = substring2;
            } else {
                throw new TypeCastException(str4);
            }
            Entry entry = (Entry) this.lruEntries.get(key);
            if (entry == null) {
                entry = new Entry(this, key);
                this.lruEntries.put(key, entry);
            }
            if (secondSpace != -1 && firstSpace == CLEAN.length() && StringsKt.startsWith$default(str, CLEAN, false, 2, null)) {
                int i = secondSpace + 1;
                if (str != null) {
                    String substring3 = str.substring(i);
                    Intrinsics.checkExpressionValueIsNotNull(substring3, str3);
                    List parts = StringsKt.split$default((CharSequence) substring3, new char[]{' '}, false, 0, 6, (Object) null);
                    entry.setReadable$okhttp(true);
                    entry.setCurrentEditor$okhttp(null);
                    entry.setLengths$okhttp(parts);
                } else {
                    throw new TypeCastException(str4);
                }
            } else if (secondSpace == -1 && firstSpace == DIRTY.length() && StringsKt.startsWith$default(str, DIRTY, false, 2, null)) {
                entry.setCurrentEditor$okhttp(new Editor(this, entry));
            } else if (!(secondSpace == -1 && firstSpace == READ.length() && StringsKt.startsWith$default(str, READ, false, 2, null))) {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(str);
                throw new IOException(sb.toString());
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append(str);
        throw new IOException(sb2.toString());
    }

    private final void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator i = this.lruEntries.values().iterator();
        while (i.hasNext()) {
            Object next = i.next();
            Intrinsics.checkExpressionValueIsNotNull(next, "i.next()");
            Entry entry = (Entry) next;
            int t = 0;
            if (entry.getCurrentEditor$okhttp() == null) {
                int i2 = this.valueCount;
                while (t < i2) {
                    this.size += entry.getLengths$okhttp()[t];
                    t++;
                }
            } else {
                entry.setCurrentEditor$okhttp(null);
                int i3 = this.valueCount;
                while (t < i3) {
                    this.fileSystem.delete((File) entry.getCleanFiles$okhttp().get(t));
                    this.fileSystem.delete((File) entry.getDirtyFiles$okhttp().get(t));
                    t++;
                }
                i.remove();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c8, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        kotlin.p014io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00cc, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void rebuildJournal$okhttp() throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            okio.BufferedSink r0 = r9.journalWriter     // Catch:{ all -> 0x00cd }
            if (r0 == 0) goto L_0x0008
            r0.close()     // Catch:{ all -> 0x00cd }
        L_0x0008:
            okhttp3.internal.io.FileSystem r0 = r9.fileSystem     // Catch:{ all -> 0x00cd }
            java.io.File r1 = r9.journalFileTmp     // Catch:{ all -> 0x00cd }
            okio.Sink r0 = r0.sink(r1)     // Catch:{ all -> 0x00cd }
            okio.BufferedSink r0 = okio.Okio.buffer(r0)     // Catch:{ all -> 0x00cd }
            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch:{ all -> 0x00cd }
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x00cd }
            r2 = r0
            okio.BufferedSink r2 = (okio.BufferedSink) r2     // Catch:{ all -> 0x00c6 }
            r3 = 0
            java.lang.String r4 = MAGIC     // Catch:{ all -> 0x00c6 }
            okio.BufferedSink r4 = r2.writeUtf8(r4)     // Catch:{ all -> 0x00c6 }
            r5 = 10
            r4.writeByte(r5)     // Catch:{ all -> 0x00c6 }
            java.lang.String r4 = VERSION_1     // Catch:{ all -> 0x00c6 }
            okio.BufferedSink r4 = r2.writeUtf8(r4)     // Catch:{ all -> 0x00c6 }
            r4.writeByte(r5)     // Catch:{ all -> 0x00c6 }
            int r4 = r9.appVersion     // Catch:{ all -> 0x00c6 }
            long r6 = (long) r4     // Catch:{ all -> 0x00c6 }
            okio.BufferedSink r4 = r2.writeDecimalLong(r6)     // Catch:{ all -> 0x00c6 }
            r4.writeByte(r5)     // Catch:{ all -> 0x00c6 }
            int r4 = r9.valueCount     // Catch:{ all -> 0x00c6 }
            long r6 = (long) r4     // Catch:{ all -> 0x00c6 }
            okio.BufferedSink r4 = r2.writeDecimalLong(r6)     // Catch:{ all -> 0x00c6 }
            r4.writeByte(r5)     // Catch:{ all -> 0x00c6 }
            r2.writeByte(r5)     // Catch:{ all -> 0x00c6 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r4 = r9.lruEntries     // Catch:{ all -> 0x00c6 }
            java.util.Collection r4 = r4.values()     // Catch:{ all -> 0x00c6 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x00c6 }
        L_0x0052:
            boolean r6 = r4.hasNext()     // Catch:{ all -> 0x00c6 }
            if (r6 == 0) goto L_0x0091
            java.lang.Object r6 = r4.next()     // Catch:{ all -> 0x00c6 }
            okhttp3.internal.cache.DiskLruCache$Entry r6 = (okhttp3.internal.cache.DiskLruCache.Entry) r6     // Catch:{ all -> 0x00c6 }
            okhttp3.internal.cache.DiskLruCache$Editor r7 = r6.getCurrentEditor$okhttp()     // Catch:{ all -> 0x00c6 }
            r8 = 32
            if (r7 == 0) goto L_0x007a
            java.lang.String r7 = DIRTY     // Catch:{ all -> 0x00c6 }
            okio.BufferedSink r7 = r2.writeUtf8(r7)     // Catch:{ all -> 0x00c6 }
            r7.writeByte(r8)     // Catch:{ all -> 0x00c6 }
            java.lang.String r7 = r6.getKey$okhttp()     // Catch:{ all -> 0x00c6 }
            r2.writeUtf8(r7)     // Catch:{ all -> 0x00c6 }
            r2.writeByte(r5)     // Catch:{ all -> 0x00c6 }
            goto L_0x0090
        L_0x007a:
            java.lang.String r7 = CLEAN     // Catch:{ all -> 0x00c6 }
            okio.BufferedSink r7 = r2.writeUtf8(r7)     // Catch:{ all -> 0x00c6 }
            r7.writeByte(r8)     // Catch:{ all -> 0x00c6 }
            java.lang.String r7 = r6.getKey$okhttp()     // Catch:{ all -> 0x00c6 }
            r2.writeUtf8(r7)     // Catch:{ all -> 0x00c6 }
            r6.writeLengths$okhttp(r2)     // Catch:{ all -> 0x00c6 }
            r2.writeByte(r5)     // Catch:{ all -> 0x00c6 }
        L_0x0090:
            goto L_0x0052
        L_0x0091:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00c6 }
            kotlin.p014io.CloseableKt.closeFinally(r0, r1)     // Catch:{ all -> 0x00cd }
            okhttp3.internal.io.FileSystem r0 = r9.fileSystem     // Catch:{ all -> 0x00cd }
            java.io.File r1 = r9.journalFile     // Catch:{ all -> 0x00cd }
            boolean r0 = r0.exists(r1)     // Catch:{ all -> 0x00cd }
            if (r0 == 0) goto L_0x00a9
            okhttp3.internal.io.FileSystem r0 = r9.fileSystem     // Catch:{ all -> 0x00cd }
            java.io.File r1 = r9.journalFile     // Catch:{ all -> 0x00cd }
            java.io.File r2 = r9.journalFileBackup     // Catch:{ all -> 0x00cd }
            r0.rename(r1, r2)     // Catch:{ all -> 0x00cd }
        L_0x00a9:
            okhttp3.internal.io.FileSystem r0 = r9.fileSystem     // Catch:{ all -> 0x00cd }
            java.io.File r1 = r9.journalFileTmp     // Catch:{ all -> 0x00cd }
            java.io.File r2 = r9.journalFile     // Catch:{ all -> 0x00cd }
            r0.rename(r1, r2)     // Catch:{ all -> 0x00cd }
            okhttp3.internal.io.FileSystem r0 = r9.fileSystem     // Catch:{ all -> 0x00cd }
            java.io.File r1 = r9.journalFileBackup     // Catch:{ all -> 0x00cd }
            r0.delete(r1)     // Catch:{ all -> 0x00cd }
            okio.BufferedSink r0 = r9.newJournalWriter()     // Catch:{ all -> 0x00cd }
            r9.journalWriter = r0     // Catch:{ all -> 0x00cd }
            r0 = 0
            r9.hasJournalErrors = r0     // Catch:{ all -> 0x00cd }
            r9.mostRecentRebuildFailed = r0     // Catch:{ all -> 0x00cd }
            monitor-exit(r9)
            return
        L_0x00c6:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x00c8 }
        L_0x00c8:
            r2 = move-exception
            kotlin.p014io.CloseableKt.closeFinally(r0, r1)     // Catch:{ all -> 0x00cd }
            throw r2     // Catch:{ all -> 0x00cd }
        L_0x00cd:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.rebuildJournal$okhttp():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0064, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized okhttp3.internal.cache.DiskLruCache.Snapshot get(java.lang.String r5) throws java.io.IOException {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)     // Catch:{ all -> 0x0069 }
            r4.initialize()     // Catch:{ all -> 0x0069 }
            r4.checkNotClosed()     // Catch:{ all -> 0x0069 }
            r4.validateKey(r5)     // Catch:{ all -> 0x0069 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r4.lruEntries     // Catch:{ all -> 0x0069 }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x0069 }
            okhttp3.internal.cache.DiskLruCache$Entry r0 = (okhttp3.internal.cache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0069 }
            r1 = 0
            if (r0 == 0) goto L_0x0067
            java.lang.String r2 = "lruEntries[key] ?: return null"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)     // Catch:{ all -> 0x0069 }
            boolean r2 = r0.getReadable$okhttp()     // Catch:{ all -> 0x0069 }
            if (r2 != 0) goto L_0x0027
            monitor-exit(r4)
            return r1
        L_0x0027:
            okhttp3.internal.cache.DiskLruCache$Snapshot r2 = r0.snapshot$okhttp()     // Catch:{ all -> 0x0069 }
            if (r2 == 0) goto L_0x0065
            r1 = r2
            int r2 = r4.redundantOpCount     // Catch:{ all -> 0x0069 }
            int r2 = r2 + 1
            r4.redundantOpCount = r2     // Catch:{ all -> 0x0069 }
            okio.BufferedSink r2 = r4.journalWriter     // Catch:{ all -> 0x0069 }
            if (r2 != 0) goto L_0x0041
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x0069 }
        L_0x0041:
            java.lang.String r3 = READ     // Catch:{ all -> 0x0069 }
            okio.BufferedSink r2 = r2.writeUtf8(r3)     // Catch:{ all -> 0x0069 }
            r3 = 32
            okio.BufferedSink r2 = r2.writeByte(r3)     // Catch:{ all -> 0x0069 }
            okio.BufferedSink r2 = r2.writeUtf8(r5)     // Catch:{ all -> 0x0069 }
            r3 = 10
            r2.writeByte(r3)     // Catch:{ all -> 0x0069 }
            boolean r2 = r4.journalRebuildRequired()     // Catch:{ all -> 0x0069 }
            if (r2 == 0) goto L_0x0063
            java.util.concurrent.Executor r2 = r4.executor     // Catch:{ all -> 0x0069 }
            java.lang.Runnable r3 = r4.cleanupRunnable     // Catch:{ all -> 0x0069 }
            r2.execute(r3)     // Catch:{ all -> 0x0069 }
        L_0x0063:
            monitor-exit(r4)
            return r1
        L_0x0065:
            monitor-exit(r4)
            return r1
        L_0x0067:
            monitor-exit(r4)
            return r1
        L_0x0069:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.get(java.lang.String):okhttp3.internal.cache.DiskLruCache$Snapshot");
    }

    public static /* synthetic */ Editor edit$default(DiskLruCache diskLruCache, String str, long j, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = ANY_SEQUENCE_NUMBER;
        }
        return diskLruCache.edit(str, j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized okhttp3.internal.cache.DiskLruCache.Editor edit(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)     // Catch:{ all -> 0x008d }
            r5.initialize()     // Catch:{ all -> 0x008d }
            r5.checkNotClosed()     // Catch:{ all -> 0x008d }
            r5.validateKey(r6)     // Catch:{ all -> 0x008d }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r5.lruEntries     // Catch:{ all -> 0x008d }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x008d }
            okhttp3.internal.cache.DiskLruCache$Entry r0 = (okhttp3.internal.cache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x008d }
            long r1 = ANY_SEQUENCE_NUMBER     // Catch:{ all -> 0x008d }
            r3 = 0
            int r4 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r4 == 0) goto L_0x002b
            if (r0 == 0) goto L_0x0029
            long r1 = r0.getSequenceNumber$okhttp()     // Catch:{ all -> 0x008d }
            int r4 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x002b
        L_0x0029:
            monitor-exit(r5)
            return r3
        L_0x002b:
            if (r0 == 0) goto L_0x0032
            okhttp3.internal.cache.DiskLruCache$Editor r1 = r0.getCurrentEditor$okhttp()     // Catch:{ all -> 0x008d }
            goto L_0x0033
        L_0x0032:
            r1 = r3
        L_0x0033:
            if (r1 == 0) goto L_0x0037
            monitor-exit(r5)
            return r3
        L_0x0037:
            boolean r1 = r5.mostRecentTrimFailed     // Catch:{ all -> 0x008d }
            if (r1 != 0) goto L_0x0084
            boolean r1 = r5.mostRecentRebuildFailed     // Catch:{ all -> 0x008d }
            if (r1 == 0) goto L_0x0040
            goto L_0x0084
        L_0x0040:
            okio.BufferedSink r1 = r5.journalWriter     // Catch:{ all -> 0x008d }
            if (r1 != 0) goto L_0x0047
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x008d }
        L_0x0047:
            java.lang.String r2 = DIRTY     // Catch:{ all -> 0x008d }
            okio.BufferedSink r2 = r1.writeUtf8(r2)     // Catch:{ all -> 0x008d }
            r4 = 32
            okio.BufferedSink r2 = r2.writeByte(r4)     // Catch:{ all -> 0x008d }
            okio.BufferedSink r2 = r2.writeUtf8(r6)     // Catch:{ all -> 0x008d }
            r4 = 10
            r2.writeByte(r4)     // Catch:{ all -> 0x008d }
            r1.flush()     // Catch:{ all -> 0x008d }
            boolean r2 = r5.hasJournalErrors     // Catch:{ all -> 0x008d }
            if (r2 == 0) goto L_0x006b
            monitor-exit(r5)
            return r3
        L_0x006b:
            if (r0 != 0) goto L_0x007a
            okhttp3.internal.cache.DiskLruCache$Entry r2 = new okhttp3.internal.cache.DiskLruCache$Entry     // Catch:{ all -> 0x008d }
            r2.<init>(r5, r6)     // Catch:{ all -> 0x008d }
            r0 = r2
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r2 = r5.lruEntries     // Catch:{ all -> 0x008d }
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ all -> 0x008d }
            r2.put(r6, r0)     // Catch:{ all -> 0x008d }
        L_0x007a:
            okhttp3.internal.cache.DiskLruCache$Editor r2 = new okhttp3.internal.cache.DiskLruCache$Editor     // Catch:{ all -> 0x008d }
            r2.<init>(r5, r0)     // Catch:{ all -> 0x008d }
            r0.setCurrentEditor$okhttp(r2)     // Catch:{ all -> 0x008d }
            monitor-exit(r5)
            return r2
        L_0x0084:
            java.util.concurrent.Executor r1 = r5.executor     // Catch:{ all -> 0x008d }
            java.lang.Runnable r2 = r5.cleanupRunnable     // Catch:{ all -> 0x008d }
            r1.execute(r2)     // Catch:{ all -> 0x008d }
            monitor-exit(r5)
            return r3
        L_0x008d:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.edit(java.lang.String, long):okhttp3.internal.cache.DiskLruCache$Editor");
    }

    public final synchronized long size() throws IOException {
        initialize();
        return this.size;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0130, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void completeEdit$okhttp(okhttp3.internal.cache.DiskLruCache.Editor r12, boolean r13) throws java.io.IOException {
        /*
            r11 = this;
            monitor-enter(r11)
            java.lang.String r0 = "editor"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)     // Catch:{ all -> 0x013f }
            okhttp3.internal.cache.DiskLruCache$Entry r0 = r12.getEntry$okhttp()     // Catch:{ all -> 0x013f }
            okhttp3.internal.cache.DiskLruCache$Editor r1 = r0.getCurrentEditor$okhttp()     // Catch:{ all -> 0x013f }
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r12)     // Catch:{ all -> 0x013f }
            if (r1 == 0) goto L_0x0131
            r1 = 0
            if (r13 == 0) goto L_0x0066
            boolean r2 = r0.getReadable$okhttp()     // Catch:{ all -> 0x013f }
            if (r2 != 0) goto L_0x0066
            int r2 = r11.valueCount     // Catch:{ all -> 0x013f }
            r3 = 0
        L_0x0020:
            if (r3 >= r2) goto L_0x0066
            boolean[] r4 = r12.getWritten$okhttp()     // Catch:{ all -> 0x013f }
            if (r4 != 0) goto L_0x002b
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x013f }
        L_0x002b:
            boolean r4 = r4[r3]     // Catch:{ all -> 0x013f }
            if (r4 == 0) goto L_0x004a
            okhttp3.internal.io.FileSystem r4 = r11.fileSystem     // Catch:{ all -> 0x013f }
            java.util.List r5 = r0.getDirtyFiles$okhttp()     // Catch:{ all -> 0x013f }
            java.lang.Object r5 = r5.get(r3)     // Catch:{ all -> 0x013f }
            java.io.File r5 = (java.io.File) r5     // Catch:{ all -> 0x013f }
            boolean r4 = r4.exists(r5)     // Catch:{ all -> 0x013f }
            if (r4 != 0) goto L_0x0046
            r12.abort()     // Catch:{ all -> 0x013f }
            monitor-exit(r11)
            return
        L_0x0046:
            int r3 = r3 + 1
            goto L_0x0020
        L_0x004a:
            r12.abort()     // Catch:{ all -> 0x013f }
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x013f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x013f }
            r2.<init>()     // Catch:{ all -> 0x013f }
            java.lang.String r4 = "Newly created entry didn't create value for index "
            r2.append(r4)     // Catch:{ all -> 0x013f }
            r2.append(r3)     // Catch:{ all -> 0x013f }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x013f }
            r1.<init>(r2)     // Catch:{ all -> 0x013f }
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x013f }
            throw r1     // Catch:{ all -> 0x013f }
        L_0x0066:
            int r2 = r11.valueCount     // Catch:{ all -> 0x013f }
        L_0x0068:
            if (r1 >= r2) goto L_0x00b1
            java.util.List r3 = r0.getDirtyFiles$okhttp()     // Catch:{ all -> 0x013f }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x013f }
            java.io.File r3 = (java.io.File) r3     // Catch:{ all -> 0x013f }
            if (r13 == 0) goto L_0x00a7
            okhttp3.internal.io.FileSystem r4 = r11.fileSystem     // Catch:{ all -> 0x013f }
            boolean r4 = r4.exists(r3)     // Catch:{ all -> 0x013f }
            if (r4 == 0) goto L_0x00ac
            java.util.List r4 = r0.getCleanFiles$okhttp()     // Catch:{ all -> 0x013f }
            java.lang.Object r4 = r4.get(r1)     // Catch:{ all -> 0x013f }
            java.io.File r4 = (java.io.File) r4     // Catch:{ all -> 0x013f }
            okhttp3.internal.io.FileSystem r5 = r11.fileSystem     // Catch:{ all -> 0x013f }
            r5.rename(r3, r4)     // Catch:{ all -> 0x013f }
            long[] r5 = r0.getLengths$okhttp()     // Catch:{ all -> 0x013f }
            r6 = r5[r1]     // Catch:{ all -> 0x013f }
            r5 = r6
            okhttp3.internal.io.FileSystem r7 = r11.fileSystem     // Catch:{ all -> 0x013f }
            long r7 = r7.size(r4)     // Catch:{ all -> 0x013f }
            long[] r9 = r0.getLengths$okhttp()     // Catch:{ all -> 0x013f }
            r9[r1] = r7     // Catch:{ all -> 0x013f }
            long r9 = r11.size     // Catch:{ all -> 0x013f }
            long r9 = r9 - r5
            long r9 = r9 + r7
            r11.size = r9     // Catch:{ all -> 0x013f }
            goto L_0x00ac
        L_0x00a7:
            okhttp3.internal.io.FileSystem r4 = r11.fileSystem     // Catch:{ all -> 0x013f }
            r4.delete(r3)     // Catch:{ all -> 0x013f }
        L_0x00ac:
            int r1 = r1 + 1
            goto L_0x0068
        L_0x00b1:
            int r1 = r11.redundantOpCount     // Catch:{ all -> 0x013f }
            r2 = 1
            int r1 = r1 + r2
            r11.redundantOpCount = r1     // Catch:{ all -> 0x013f }
            r1 = 0
            okhttp3.internal.cache.DiskLruCache$Editor r1 = (okhttp3.internal.cache.DiskLruCache.Editor) r1     // Catch:{ all -> 0x013f }
            r0.setCurrentEditor$okhttp(r1)     // Catch:{ all -> 0x013f }
            okio.BufferedSink r1 = r11.journalWriter     // Catch:{ all -> 0x013f }
            if (r1 != 0) goto L_0x00c4
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x013f }
        L_0x00c4:
            r3 = 0
            boolean r4 = r0.getReadable$okhttp()     // Catch:{ all -> 0x013f }
            r5 = 10
            r6 = 32
            if (r4 != 0) goto L_0x00ef
            if (r13 == 0) goto L_0x00d2
            goto L_0x00ef
        L_0x00d2:
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r2 = r11.lruEntries     // Catch:{ all -> 0x013f }
            java.lang.String r4 = r0.getKey$okhttp()     // Catch:{ all -> 0x013f }
            r2.remove(r4)     // Catch:{ all -> 0x013f }
            java.lang.String r2 = REMOVE     // Catch:{ all -> 0x013f }
            okio.BufferedSink r2 = r1.writeUtf8(r2)     // Catch:{ all -> 0x013f }
            r2.writeByte(r6)     // Catch:{ all -> 0x013f }
            java.lang.String r2 = r0.getKey$okhttp()     // Catch:{ all -> 0x013f }
            r1.writeUtf8(r2)     // Catch:{ all -> 0x013f }
            r1.writeByte(r5)     // Catch:{ all -> 0x013f }
            goto L_0x0114
        L_0x00ef:
            r0.setReadable$okhttp(r2)     // Catch:{ all -> 0x013f }
            java.lang.String r2 = CLEAN     // Catch:{ all -> 0x013f }
            okio.BufferedSink r2 = r1.writeUtf8(r2)     // Catch:{ all -> 0x013f }
            r2.writeByte(r6)     // Catch:{ all -> 0x013f }
            java.lang.String r2 = r0.getKey$okhttp()     // Catch:{ all -> 0x013f }
            r1.writeUtf8(r2)     // Catch:{ all -> 0x013f }
            r0.writeLengths$okhttp(r1)     // Catch:{ all -> 0x013f }
            r1.writeByte(r5)     // Catch:{ all -> 0x013f }
            if (r13 == 0) goto L_0x0114
            long r4 = r11.nextSequenceNumber     // Catch:{ all -> 0x013f }
            r6 = 1
            long r6 = r6 + r4
            r11.nextSequenceNumber = r6     // Catch:{ all -> 0x013f }
            r0.setSequenceNumber$okhttp(r4)     // Catch:{ all -> 0x013f }
        L_0x0114:
            r1.flush()     // Catch:{ all -> 0x013f }
            long r1 = r11.size     // Catch:{ all -> 0x013f }
            long r3 = r11.maxSize     // Catch:{ all -> 0x013f }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x0128
            boolean r1 = r11.journalRebuildRequired()     // Catch:{ all -> 0x013f }
            if (r1 == 0) goto L_0x012f
        L_0x0128:
            java.util.concurrent.Executor r1 = r11.executor     // Catch:{ all -> 0x013f }
            java.lang.Runnable r2 = r11.cleanupRunnable     // Catch:{ all -> 0x013f }
            r1.execute(r2)     // Catch:{ all -> 0x013f }
        L_0x012f:
            monitor-exit(r11)
            return
        L_0x0131:
            java.lang.String r1 = "Check failed."
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ all -> 0x013f }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x013f }
            r2.<init>(r1)     // Catch:{ all -> 0x013f }
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch:{ all -> 0x013f }
            throw r2     // Catch:{ all -> 0x013f }
        L_0x013f:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.completeEdit$okhttp(okhttp3.internal.cache.DiskLruCache$Editor, boolean):void");
    }

    /* access modifiers changed from: private */
    public final boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean remove(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)     // Catch:{ all -> 0x0033 }
            r8.initialize()     // Catch:{ all -> 0x0033 }
            r8.checkNotClosed()     // Catch:{ all -> 0x0033 }
            r8.validateKey(r9)     // Catch:{ all -> 0x0033 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r8.lruEntries     // Catch:{ all -> 0x0033 }
            java.lang.Object r0 = r0.get(r9)     // Catch:{ all -> 0x0033 }
            okhttp3.internal.cache.DiskLruCache$Entry r0 = (okhttp3.internal.cache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0033 }
            r1 = 0
            if (r0 == 0) goto L_0x0031
            java.lang.String r2 = "lruEntries[key] ?: return false"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)     // Catch:{ all -> 0x0033 }
            boolean r2 = r8.removeEntry$okhttp(r0)     // Catch:{ all -> 0x0033 }
            if (r2 == 0) goto L_0x002f
            long r3 = r8.size     // Catch:{ all -> 0x0033 }
            long r5 = r8.maxSize     // Catch:{ all -> 0x0033 }
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 > 0) goto L_0x002f
            r8.mostRecentTrimFailed = r1     // Catch:{ all -> 0x0033 }
        L_0x002f:
            monitor-exit(r8)
            return r2
        L_0x0031:
            monitor-exit(r8)
            return r1
        L_0x0033:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.remove(java.lang.String):boolean");
    }

    public final boolean removeEntry$okhttp(Entry entry) throws IOException {
        Intrinsics.checkParameterIsNotNull(entry, "entry");
        Editor currentEditor$okhttp = entry.getCurrentEditor$okhttp();
        if (currentEditor$okhttp != null) {
            currentEditor$okhttp.detach$okhttp();
        }
        int i = this.valueCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.fileSystem.delete((File) entry.getCleanFiles$okhttp().get(i2));
            this.size -= entry.getLengths$okhttp()[i2];
            entry.getLengths$okhttp()[i2] = 0;
        }
        this.redundantOpCount++;
        BufferedSink bufferedSink = this.journalWriter;
        if (bufferedSink == null) {
            Intrinsics.throwNpe();
        }
        bufferedSink.writeUtf8(REMOVE).writeByte(32).writeUtf8(entry.getKey$okhttp()).writeByte(10);
        this.lruEntries.remove(entry.getKey$okhttp());
        if (journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return true;
    }

    private final synchronized void checkNotClosed() {
        if (!(!this.closed)) {
            throw new IllegalStateException("cache is closed".toString());
        }
    }

    public synchronized void flush() throws IOException {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            BufferedSink bufferedSink = this.journalWriter;
            if (bufferedSink == null) {
                Intrinsics.throwNpe();
            }
            bufferedSink.flush();
        }
    }

    public final synchronized boolean isClosed() {
        return this.closed;
    }

    public synchronized void close() throws IOException {
        Entry[] entryArr;
        if (this.initialized) {
            if (!this.closed) {
                Collection $this$toTypedArray$iv = this.lruEntries.values();
                Intrinsics.checkExpressionValueIsNotNull($this$toTypedArray$iv, "lruEntries.values");
                Object[] array = $this$toTypedArray$iv.toArray(new Entry[0]);
                if (array != null) {
                    for (Entry entry : (Entry[]) array) {
                        if (entry.getCurrentEditor$okhttp() != null) {
                            Editor currentEditor$okhttp = entry.getCurrentEditor$okhttp();
                            if (currentEditor$okhttp == null) {
                                Intrinsics.throwNpe();
                            }
                            currentEditor$okhttp.abort();
                        }
                    }
                    trimToSize();
                    BufferedSink bufferedSink = this.journalWriter;
                    if (bufferedSink == null) {
                        Intrinsics.throwNpe();
                    }
                    bufferedSink.close();
                    this.journalWriter = null;
                    this.closed = true;
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        this.closed = true;
    }

    public final void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            Object next = this.lruEntries.values().iterator().next();
            Intrinsics.checkExpressionValueIsNotNull(next, "lruEntries.values.iterator().next()");
            removeEntry$okhttp((Entry) next);
        }
        this.mostRecentTrimFailed = false;
    }

    public final void delete() throws IOException {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    public final synchronized void evictAll() throws IOException {
        Entry[] entryArr;
        initialize();
        Collection $this$toTypedArray$iv = this.lruEntries.values();
        Intrinsics.checkExpressionValueIsNotNull($this$toTypedArray$iv, "lruEntries.values");
        Object[] array = $this$toTypedArray$iv.toArray(new Entry[0]);
        if (array != null) {
            for (Entry entry : (Entry[]) array) {
                Intrinsics.checkExpressionValueIsNotNull(entry, "entry");
                removeEntry$okhttp(entry);
            }
            this.mostRecentTrimFailed = false;
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    private final void validateKey(String key) {
        if (!LEGAL_KEY_PATTERN.matches(key)) {
            StringBuilder sb = new StringBuilder();
            sb.append("keys must match regex [a-z0-9_-]{1,120}: \"");
            sb.append(key);
            sb.append(Typography.quote);
            throw new IllegalArgumentException(sb.toString().toString());
        }
    }

    public final synchronized Iterator<Snapshot> snapshots() throws IOException {
        initialize();
        return new DiskLruCache$snapshots$1<>(this);
    }
}
