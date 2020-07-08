package com.bumptech.glide.disklrucache;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class DiskLruCache implements Closeable {
    static final long ANY_SEQUENCE_NUMBER = -1;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    static final String VERSION_1 = "1";
    private final int appVersion;
    private final Callable<Void> cleanupCallable;
    /* access modifiers changed from: private */
    public final File directory;
    final ThreadPoolExecutor executorService;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    /* access modifiers changed from: private */
    public Writer journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private long maxSize;
    private long nextSequenceNumber = 0;
    /* access modifiers changed from: private */
    public int redundantOpCount;
    private long size = 0;
    /* access modifiers changed from: private */
    public final int valueCount;

    private static final class DiskLruCacheThreadFactory implements ThreadFactory {
        private DiskLruCacheThreadFactory() {
        }

        public synchronized Thread newThread(Runnable runnable) {
            Thread result;
            result = new Thread(runnable, "glide-disk-lru-cache-thread");
            result.setPriority(1);
            return result;
        }
    }

    public final class Editor {
        private boolean committed;
        /* access modifiers changed from: private */
        public final Entry entry;
        /* access modifiers changed from: private */
        public final boolean[] written;

        private Editor(Entry entry2) {
            this.entry = entry2;
            this.written = entry2.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }

        private InputStream newInputStream(int index) throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                } else if (!this.entry.readable) {
                    return null;
                } else {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(this.entry.getCleanFile(index));
                        return fileInputStream;
                    } catch (FileNotFoundException e) {
                        return null;
                    }
                }
            }
        }

        public String getString(int index) throws IOException {
            InputStream in = newInputStream(index);
            if (in != null) {
                return DiskLruCache.inputStreamToString(in);
            }
            return null;
        }

        public File getFile(int index) throws IOException {
            File dirtyFile;
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor == this) {
                    if (!this.entry.readable) {
                        this.written[index] = true;
                    }
                    dirtyFile = this.entry.getDirtyFile(index);
                    if (!DiskLruCache.this.directory.exists()) {
                        DiskLruCache.this.directory.mkdirs();
                    }
                } else {
                    throw new IllegalStateException();
                }
            }
            return dirtyFile;
        }

        public void set(int index, String value) throws IOException {
            Writer writer = null;
            try {
                writer = new OutputStreamWriter(new FileOutputStream(getFile(index)), Util.UTF_8);
                writer.write(value);
            } finally {
                Util.closeQuietly(writer);
            }
        }

        public void commit() throws IOException {
            DiskLruCache.this.completeEdit(this, true);
            this.committed = true;
        }

        public void abort() throws IOException {
            DiskLruCache.this.completeEdit(this, false);
        }

        public void abortUnlessCommitted() {
            if (!this.committed) {
                try {
                    abort();
                } catch (IOException e) {
                }
            }
        }
    }

    private final class Entry {
        File[] cleanFiles;
        /* access modifiers changed from: private */
        public Editor currentEditor;
        File[] dirtyFiles;
        /* access modifiers changed from: private */
        public final String key;
        /* access modifiers changed from: private */
        public final long[] lengths;
        /* access modifiers changed from: private */
        public boolean readable;
        /* access modifiers changed from: private */
        public long sequenceNumber;

        private Entry(String key2) {
            this.key = key2;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new File[DiskLruCache.this.valueCount];
            this.dirtyFiles = new File[DiskLruCache.this.valueCount];
            StringBuilder fileBuilder = new StringBuilder(key2).append('.');
            int truncateTo = fileBuilder.length();
            for (int i = 0; i < DiskLruCache.this.valueCount; i++) {
                fileBuilder.append(i);
                this.cleanFiles[i] = new File(DiskLruCache.this.directory, fileBuilder.toString());
                fileBuilder.append(".tmp");
                this.dirtyFiles[i] = new File(DiskLruCache.this.directory, fileBuilder.toString());
                fileBuilder.setLength(truncateTo);
            }
        }

        public String getLengths() throws IOException {
            long[] jArr;
            StringBuilder result = new StringBuilder();
            for (long size : this.lengths) {
                result.append(' ');
                result.append(size);
            }
            return result.toString();
        }

        /* access modifiers changed from: private */
        public void setLengths(String[] strings) throws IOException {
            if (strings.length == DiskLruCache.this.valueCount) {
                int i = 0;
                while (i < strings.length) {
                    try {
                        this.lengths[i] = Long.parseLong(strings[i]);
                        i++;
                    } catch (NumberFormatException e) {
                        throw invalidLengths(strings);
                    }
                }
                return;
            }
            throw invalidLengths(strings);
        }

        private IOException invalidLengths(String[] strings) throws IOException {
            StringBuilder sb = new StringBuilder();
            sb.append("unexpected journal line: ");
            sb.append(Arrays.toString(strings));
            throw new IOException(sb.toString());
        }

        public File getCleanFile(int i) {
            return this.cleanFiles[i];
        }

        public File getDirtyFile(int i) {
            return this.dirtyFiles[i];
        }
    }

    public final class Value {
        private final File[] files;
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;

        private Value(String key2, long sequenceNumber2, File[] files2, long[] lengths2) {
            this.key = key2;
            this.sequenceNumber = sequenceNumber2;
            this.files = files2;
            this.lengths = lengths2;
        }

        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public File getFile(int index) {
            return this.files[index];
        }

        public String getString(int index) throws IOException {
            return DiskLruCache.inputStreamToString(new FileInputStream(this.files[index]));
        }

        public long getLength(int index) {
            return this.lengths[index];
        }
    }

    private DiskLruCache(File directory2, int appVersion2, int valueCount2, long maxSize2) {
        File file = directory2;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DiskLruCacheThreadFactory());
        this.executorService = threadPoolExecutor;
        this.cleanupCallable = new Callable<Void>() {
            /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
                return null;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void call() throws java.lang.Exception {
                /*
                    r4 = this;
                    com.bumptech.glide.disklrucache.DiskLruCache r0 = com.bumptech.glide.disklrucache.DiskLruCache.this
                    monitor-enter(r0)
                    com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    java.io.Writer r1 = r1.journalWriter     // Catch:{ all -> 0x0028 }
                    r2 = 0
                    if (r1 != 0) goto L_0x000e
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    return r2
                L_0x000e:
                    com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    r1.trimToSize()     // Catch:{ all -> 0x0028 }
                    com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    boolean r1 = r1.journalRebuildRequired()     // Catch:{ all -> 0x0028 }
                    if (r1 == 0) goto L_0x0026
                    com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    r1.rebuildJournal()     // Catch:{ all -> 0x0028 }
                    com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    r3 = 0
                    r1.redundantOpCount = r3     // Catch:{ all -> 0x0028 }
                L_0x0026:
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    return r2
                L_0x0028:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.C04821.call():java.lang.Void");
            }
        };
        this.directory = file;
        this.appVersion = appVersion2;
        this.journalFile = new File(file, "journal");
        this.journalFileTmp = new File(file, "journal.tmp");
        this.journalFileBackup = new File(file, "journal.bkp");
        this.valueCount = valueCount2;
        this.maxSize = maxSize2;
    }

    public static DiskLruCache open(File directory2, int appVersion2, int valueCount2, long maxSize2) throws IOException {
        if (maxSize2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (valueCount2 > 0) {
            File backupFile = new File(directory2, "journal.bkp");
            if (backupFile.exists()) {
                File journalFile2 = new File(directory2, "journal");
                if (journalFile2.exists()) {
                    backupFile.delete();
                } else {
                    renameTo(backupFile, journalFile2, false);
                }
            }
            DiskLruCache diskLruCache = new DiskLruCache(directory2, appVersion2, valueCount2, maxSize2);
            if (diskLruCache.journalFile.exists()) {
                try {
                    diskLruCache.readJournal();
                    diskLruCache.processJournal();
                    return diskLruCache;
                } catch (IOException journalIsCorrupt) {
                    PrintStream printStream = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("DiskLruCache ");
                    sb.append(directory2);
                    sb.append(" is corrupt: ");
                    sb.append(journalIsCorrupt.getMessage());
                    sb.append(", removing");
                    printStream.println(sb.toString());
                    diskLruCache.delete();
                }
            }
            directory2.mkdirs();
            DiskLruCache diskLruCache2 = new DiskLruCache(directory2, appVersion2, valueCount2, maxSize2);
            DiskLruCache cache = diskLruCache2;
            cache.rebuildJournal();
            return cache;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    private void readJournal() throws IOException {
        int lineCount;
        String str = ", ";
        StrictLineReader reader = new StrictLineReader(new FileInputStream(this.journalFile), Util.US_ASCII);
        try {
            String magic = reader.readLine();
            String version = reader.readLine();
            String appVersionString = reader.readLine();
            String valueCountString = reader.readLine();
            String blank = reader.readLine();
            if (!"libcore.io.DiskLruCache".equals(magic) || !"1".equals(version) || !Integer.toString(this.appVersion).equals(appVersionString) || !Integer.toString(this.valueCount).equals(valueCountString) || !"".equals(blank)) {
                StringBuilder sb = new StringBuilder();
                sb.append("unexpected journal header: [");
                sb.append(magic);
                sb.append(str);
                sb.append(version);
                sb.append(str);
                sb.append(valueCountString);
                sb.append(str);
                sb.append(blank);
                sb.append("]");
                throw new IOException(sb.toString());
            }
            lineCount = 0;
            while (true) {
                readJournalLine(reader.readLine());
                lineCount++;
            }
        } catch (EOFException e) {
            this.redundantOpCount = lineCount - this.lruEntries.size();
            if (reader.hasUnterminatedLine()) {
                rebuildJournal();
            } else {
                this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), Util.US_ASCII));
            }
            Util.closeQuietly(reader);
        } catch (Throwable th) {
            Util.closeQuietly(reader);
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0093, code lost:
        if (r11.startsWith(r3) != false) goto L_0x0095;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readJournalLine(java.lang.String r11) throws java.io.IOException {
        /*
            r10 = this;
            r0 = 32
            int r1 = r11.indexOf(r0)
            java.lang.String r2 = "unexpected journal line: "
            r3 = -1
            if (r1 == r3) goto L_0x00ab
            int r4 = r1 + 1
            int r0 = r11.indexOf(r0, r4)
            if (r0 != r3) goto L_0x002b
            java.lang.String r5 = r11.substring(r4)
            java.lang.String r6 = "REMOVE"
            int r7 = r6.length()
            if (r1 != r7) goto L_0x002f
            boolean r6 = r11.startsWith(r6)
            if (r6 == 0) goto L_0x002f
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r2 = r10.lruEntries
            r2.remove(r5)
            return
        L_0x002b:
            java.lang.String r5 = r11.substring(r4, r0)
        L_0x002f:
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r6 = r10.lruEntries
            java.lang.Object r6 = r6.get(r5)
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r6 = (com.bumptech.glide.disklrucache.DiskLruCache.Entry) r6
            r7 = 0
            if (r6 != 0) goto L_0x0045
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r8 = new com.bumptech.glide.disklrucache.DiskLruCache$Entry
            r8.<init>(r5)
            r6 = r8
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r8 = r10.lruEntries
            r8.put(r5, r6)
        L_0x0045:
            if (r0 == r3) goto L_0x006c
            java.lang.String r8 = "CLEAN"
            int r9 = r8.length()
            if (r1 != r9) goto L_0x006c
            boolean r8 = r11.startsWith(r8)
            if (r8 == 0) goto L_0x006c
            int r2 = r0 + 1
            java.lang.String r2 = r11.substring(r2)
            java.lang.String r3 = " "
            java.lang.String[] r2 = r2.split(r3)
            r3 = 1
            r6.readable = r3
            r6.currentEditor = r7
            r6.setLengths(r2)
            goto L_0x0095
        L_0x006c:
            if (r0 != r3) goto L_0x0085
            java.lang.String r8 = "DIRTY"
            int r9 = r8.length()
            if (r1 != r9) goto L_0x0085
            boolean r8 = r11.startsWith(r8)
            if (r8 == 0) goto L_0x0085
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r2 = new com.bumptech.glide.disklrucache.DiskLruCache$Editor
            r2.<init>(r6)
            r6.currentEditor = r2
            goto L_0x0095
        L_0x0085:
            if (r0 != r3) goto L_0x0096
            java.lang.String r3 = "READ"
            int r7 = r3.length()
            if (r1 != r7) goto L_0x0096
            boolean r3 = r11.startsWith(r3)
            if (r3 == 0) goto L_0x0096
        L_0x0095:
            return
        L_0x0096:
            java.io.IOException r3 = new java.io.IOException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r2)
            r7.append(r11)
            java.lang.String r2 = r7.toString()
            r3.<init>(r2)
            throw r3
        L_0x00ab:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            r3.append(r11)
            java.lang.String r2 = r3.toString()
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.readJournalLine(java.lang.String):void");
    }

    private void processJournal() throws IOException {
        deleteIfExists(this.journalFileTmp);
        Iterator<Entry> i = this.lruEntries.values().iterator();
        while (i.hasNext()) {
            Entry entry = (Entry) i.next();
            if (entry.currentEditor == null) {
                for (int t = 0; t < this.valueCount; t++) {
                    this.size += entry.lengths[t];
                }
            } else {
                entry.currentEditor = null;
                for (int t2 = 0; t2 < this.valueCount; t2++) {
                    deleteIfExists(entry.getCleanFile(t2));
                    deleteIfExists(entry.getDirtyFile(t2));
                }
                i.remove();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public synchronized void rebuildJournal() throws IOException {
        if (this.journalWriter != null) {
            this.journalWriter.close();
        }
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFileTmp), Util.US_ASCII));
        try {
            writer.write("libcore.io.DiskLruCache");
            writer.write("\n");
            writer.write("1");
            writer.write("\n");
            writer.write(Integer.toString(this.appVersion));
            writer.write("\n");
            writer.write(Integer.toString(this.valueCount));
            writer.write("\n");
            writer.write("\n");
            for (Entry entry : this.lruEntries.values()) {
                if (entry.currentEditor != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("DIRTY ");
                    sb.append(entry.key);
                    sb.append(10);
                    writer.write(sb.toString());
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("CLEAN ");
                    sb2.append(entry.key);
                    sb2.append(entry.getLengths());
                    sb2.append(10);
                    writer.write(sb2.toString());
                }
            }
            writer.close();
            if (this.journalFile.exists()) {
                renameTo(this.journalFile, this.journalFileBackup, true);
            }
            renameTo(this.journalFileTmp, this.journalFile, false);
            this.journalFileBackup.delete();
            this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), Util.US_ASCII));
        } catch (Throwable th) {
            writer.close();
            throw th;
        }
    }

    private static void deleteIfExists(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void renameTo(File from, File to, boolean deleteDestination) throws IOException {
        if (deleteDestination) {
            deleteIfExists(to);
        }
        if (!from.renameTo(to)) {
            throw new IOException();
        }
    }

    public synchronized Value get(String key) throws IOException {
        checkNotClosed();
        Entry entry = (Entry) this.lruEntries.get(key);
        if (entry == null) {
            return null;
        }
        if (!entry.readable) {
            return null;
        }
        for (File file : entry.cleanFiles) {
            if (!file.exists()) {
                return null;
            }
        }
        this.redundantOpCount++;
        this.journalWriter.append("READ");
        this.journalWriter.append(' ');
        this.journalWriter.append(key);
        this.journalWriter.append(10);
        if (journalRebuildRequired()) {
            this.executorService.submit(this.cleanupCallable);
        }
        Value value = new Value(key, entry.sequenceNumber, entry.cleanFiles, entry.lengths);
        return value;
    }

    public Editor edit(String key) throws IOException {
        return edit(key, -1);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.bumptech.glide.disklrucache.DiskLruCache.Editor edit(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.checkNotClosed()     // Catch:{ all -> 0x005e }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r0 = r5.lruEntries     // Catch:{ all -> 0x005e }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x005e }
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = (com.bumptech.glide.disklrucache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x005e }
            r1 = -1
            r3 = 0
            int r4 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r4 == 0) goto L_0x001f
            if (r0 == 0) goto L_0x001d
            long r1 = r0.sequenceNumber     // Catch:{ all -> 0x005e }
            int r4 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x001f
        L_0x001d:
            monitor-exit(r5)
            return r3
        L_0x001f:
            if (r0 != 0) goto L_0x002d
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r1 = new com.bumptech.glide.disklrucache.DiskLruCache$Entry     // Catch:{ all -> 0x005e }
            r1.<init>(r6)     // Catch:{ all -> 0x005e }
            r0 = r1
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r1 = r5.lruEntries     // Catch:{ all -> 0x005e }
            r1.put(r6, r0)     // Catch:{ all -> 0x005e }
            goto L_0x0035
        L_0x002d:
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r1 = r0.currentEditor     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0035
            monitor-exit(r5)
            return r3
        L_0x0035:
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r1 = new com.bumptech.glide.disklrucache.DiskLruCache$Editor     // Catch:{ all -> 0x005e }
            r1.<init>(r0)     // Catch:{ all -> 0x005e }
            r0.currentEditor = r1     // Catch:{ all -> 0x005e }
            java.io.Writer r2 = r5.journalWriter     // Catch:{ all -> 0x005e }
            java.lang.String r3 = "DIRTY"
            r2.append(r3)     // Catch:{ all -> 0x005e }
            java.io.Writer r2 = r5.journalWriter     // Catch:{ all -> 0x005e }
            r3 = 32
            r2.append(r3)     // Catch:{ all -> 0x005e }
            java.io.Writer r2 = r5.journalWriter     // Catch:{ all -> 0x005e }
            r2.append(r6)     // Catch:{ all -> 0x005e }
            java.io.Writer r2 = r5.journalWriter     // Catch:{ all -> 0x005e }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x005e }
            java.io.Writer r2 = r5.journalWriter     // Catch:{ all -> 0x005e }
            r2.flush()     // Catch:{ all -> 0x005e }
            monitor-exit(r5)
            return r1
        L_0x005e:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.edit(java.lang.String, long):com.bumptech.glide.disklrucache.DiskLruCache$Editor");
    }

    public File getDirectory() {
        return this.directory;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized void setMaxSize(long maxSize2) {
        this.maxSize = maxSize2;
        this.executorService.submit(this.cleanupCallable);
    }

    public synchronized long size() {
        return this.size;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0109, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void completeEdit(com.bumptech.glide.disklrucache.DiskLruCache.Editor r11, boolean r12) throws java.io.IOException {
        /*
            r10 = this;
            monitor-enter(r10)
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = r11.entry     // Catch:{ all -> 0x0110 }
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r1 = r0.currentEditor     // Catch:{ all -> 0x0110 }
            if (r1 != r11) goto L_0x010a
            r1 = 0
            if (r12 == 0) goto L_0x004d
            boolean r2 = r0.readable     // Catch:{ all -> 0x0110 }
            if (r2 != 0) goto L_0x004d
            r2 = r1
        L_0x0015:
            int r3 = r10.valueCount     // Catch:{ all -> 0x0110 }
            if (r2 >= r3) goto L_0x004d
            boolean[] r3 = r11.written     // Catch:{ all -> 0x0110 }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x0110 }
            if (r3 == 0) goto L_0x0033
            java.io.File r3 = r0.getDirtyFile(r2)     // Catch:{ all -> 0x0110 }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x0110 }
            if (r3 != 0) goto L_0x0030
            r11.abort()     // Catch:{ all -> 0x0110 }
            monitor-exit(r10)
            return
        L_0x0030:
            int r2 = r2 + 1
            goto L_0x0015
        L_0x0033:
            r11.abort()     // Catch:{ all -> 0x0110 }
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0110 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0110 }
            r3.<init>()     // Catch:{ all -> 0x0110 }
            java.lang.String r4 = "Newly created entry didn't create value for index "
            r3.append(r4)     // Catch:{ all -> 0x0110 }
            r3.append(r2)     // Catch:{ all -> 0x0110 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0110 }
            r1.<init>(r3)     // Catch:{ all -> 0x0110 }
            throw r1     // Catch:{ all -> 0x0110 }
        L_0x004d:
        L_0x004e:
            int r2 = r10.valueCount     // Catch:{ all -> 0x0110 }
            if (r1 >= r2) goto L_0x0083
            java.io.File r2 = r0.getDirtyFile(r1)     // Catch:{ all -> 0x0110 }
            if (r12 == 0) goto L_0x007d
            boolean r3 = r2.exists()     // Catch:{ all -> 0x0110 }
            if (r3 == 0) goto L_0x0080
            java.io.File r3 = r0.getCleanFile(r1)     // Catch:{ all -> 0x0110 }
            r2.renameTo(r3)     // Catch:{ all -> 0x0110 }
            long[] r4 = r0.lengths     // Catch:{ all -> 0x0110 }
            r5 = r4[r1]     // Catch:{ all -> 0x0110 }
            r4 = r5
            long r6 = r3.length()     // Catch:{ all -> 0x0110 }
            long[] r8 = r0.lengths     // Catch:{ all -> 0x0110 }
            r8[r1] = r6     // Catch:{ all -> 0x0110 }
            long r8 = r10.size     // Catch:{ all -> 0x0110 }
            long r8 = r8 - r4
            long r8 = r8 + r6
            r10.size = r8     // Catch:{ all -> 0x0110 }
            goto L_0x0080
        L_0x007d:
            deleteIfExists(r2)     // Catch:{ all -> 0x0110 }
        L_0x0080:
            int r1 = r1 + 1
            goto L_0x004e
        L_0x0083:
            int r1 = r10.redundantOpCount     // Catch:{ all -> 0x0110 }
            r2 = 1
            int r1 = r1 + r2
            r10.redundantOpCount = r1     // Catch:{ all -> 0x0110 }
            r1 = 0
            r0.currentEditor = r1     // Catch:{ all -> 0x0110 }
            boolean r1 = r0.readable     // Catch:{ all -> 0x0110 }
            r1 = r1 | r12
            r3 = 10
            r4 = 32
            if (r1 == 0) goto L_0x00cb
            r0.readable = r2     // Catch:{ all -> 0x0110 }
            java.io.Writer r1 = r10.journalWriter     // Catch:{ all -> 0x0110 }
            java.lang.String r2 = "CLEAN"
            r1.append(r2)     // Catch:{ all -> 0x0110 }
            java.io.Writer r1 = r10.journalWriter     // Catch:{ all -> 0x0110 }
            r1.append(r4)     // Catch:{ all -> 0x0110 }
            java.io.Writer r1 = r10.journalWriter     // Catch:{ all -> 0x0110 }
            java.lang.String r2 = r0.key     // Catch:{ all -> 0x0110 }
            r1.append(r2)     // Catch:{ all -> 0x0110 }
            java.io.Writer r1 = r10.journalWriter     // Catch:{ all -> 0x0110 }
            java.lang.String r2 = r0.getLengths()     // Catch:{ all -> 0x0110 }
            r1.append(r2)     // Catch:{ all -> 0x0110 }
            java.io.Writer r1 = r10.journalWriter     // Catch:{ all -> 0x0110 }
            r1.append(r3)     // Catch:{ all -> 0x0110 }
            if (r12 == 0) goto L_0x00ee
            long r1 = r10.nextSequenceNumber     // Catch:{ all -> 0x0110 }
            r3 = 1
            long r3 = r3 + r1
            r10.nextSequenceNumber = r3     // Catch:{ all -> 0x0110 }
            r0.sequenceNumber = r1     // Catch:{ all -> 0x0110 }
            goto L_0x00ee
        L_0x00cb:
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r1 = r10.lruEntries     // Catch:{ all -> 0x0110 }
            java.lang.String r2 = r0.key     // Catch:{ all -> 0x0110 }
            r1.remove(r2)     // Catch:{ all -> 0x0110 }
            java.io.Writer r1 = r10.journalWriter     // Catch:{ all -> 0x0110 }
            java.lang.String r2 = "REMOVE"
            r1.append(r2)     // Catch:{ all -> 0x0110 }
            java.io.Writer r1 = r10.journalWriter     // Catch:{ all -> 0x0110 }
            r1.append(r4)     // Catch:{ all -> 0x0110 }
            java.io.Writer r1 = r10.journalWriter     // Catch:{ all -> 0x0110 }
            java.lang.String r2 = r0.key     // Catch:{ all -> 0x0110 }
            r1.append(r2)     // Catch:{ all -> 0x0110 }
            java.io.Writer r1 = r10.journalWriter     // Catch:{ all -> 0x0110 }
            r1.append(r3)     // Catch:{ all -> 0x0110 }
        L_0x00ee:
            java.io.Writer r1 = r10.journalWriter     // Catch:{ all -> 0x0110 }
            r1.flush()     // Catch:{ all -> 0x0110 }
            long r1 = r10.size     // Catch:{ all -> 0x0110 }
            long r3 = r10.maxSize     // Catch:{ all -> 0x0110 }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x0101
            boolean r1 = r10.journalRebuildRequired()     // Catch:{ all -> 0x0110 }
            if (r1 == 0) goto L_0x0108
        L_0x0101:
            java.util.concurrent.ThreadPoolExecutor r1 = r10.executorService     // Catch:{ all -> 0x0110 }
            java.util.concurrent.Callable<java.lang.Void> r2 = r10.cleanupCallable     // Catch:{ all -> 0x0110 }
            r1.submit(r2)     // Catch:{ all -> 0x0110 }
        L_0x0108:
            monitor-exit(r10)
            return
        L_0x010a:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0110 }
            r1.<init>()     // Catch:{ all -> 0x0110 }
            throw r1     // Catch:{ all -> 0x0110 }
        L_0x0110:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.completeEdit(com.bumptech.glide.disklrucache.DiskLruCache$Editor, boolean):void");
    }

    /* access modifiers changed from: private */
    public boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x008d, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008f, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean remove(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            monitor-enter(r8)
            r8.checkNotClosed()     // Catch:{ all -> 0x0090 }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r0 = r8.lruEntries     // Catch:{ all -> 0x0090 }
            java.lang.Object r0 = r0.get(r9)     // Catch:{ all -> 0x0090 }
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = (com.bumptech.glide.disklrucache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0090 }
            r1 = 0
            if (r0 == 0) goto L_0x008e
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r2 = r0.currentEditor     // Catch:{ all -> 0x0090 }
            if (r2 == 0) goto L_0x0017
            goto L_0x008e
        L_0x0017:
        L_0x0018:
            int r2 = r8.valueCount     // Catch:{ all -> 0x0090 }
            if (r1 >= r2) goto L_0x005a
            java.io.File r2 = r0.getCleanFile(r1)     // Catch:{ all -> 0x0090 }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x0090 }
            if (r3 == 0) goto L_0x0044
            boolean r3 = r2.delete()     // Catch:{ all -> 0x0090 }
            if (r3 == 0) goto L_0x002d
            goto L_0x0044
        L_0x002d:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x0090 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0090 }
            r4.<init>()     // Catch:{ all -> 0x0090 }
            java.lang.String r5 = "failed to delete "
            r4.append(r5)     // Catch:{ all -> 0x0090 }
            r4.append(r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0090 }
            r3.<init>(r4)     // Catch:{ all -> 0x0090 }
            throw r3     // Catch:{ all -> 0x0090 }
        L_0x0044:
            long r3 = r8.size     // Catch:{ all -> 0x0090 }
            long[] r5 = r0.lengths     // Catch:{ all -> 0x0090 }
            r6 = r5[r1]     // Catch:{ all -> 0x0090 }
            long r3 = r3 - r6
            r8.size = r3     // Catch:{ all -> 0x0090 }
            long[] r3 = r0.lengths     // Catch:{ all -> 0x0090 }
            r4 = 0
            r3[r1] = r4     // Catch:{ all -> 0x0090 }
            int r1 = r1 + 1
            goto L_0x0018
        L_0x005a:
            int r1 = r8.redundantOpCount     // Catch:{ all -> 0x0090 }
            r2 = 1
            int r1 = r1 + r2
            r8.redundantOpCount = r1     // Catch:{ all -> 0x0090 }
            java.io.Writer r1 = r8.journalWriter     // Catch:{ all -> 0x0090 }
            java.lang.String r3 = "REMOVE"
            r1.append(r3)     // Catch:{ all -> 0x0090 }
            java.io.Writer r1 = r8.journalWriter     // Catch:{ all -> 0x0090 }
            r3 = 32
            r1.append(r3)     // Catch:{ all -> 0x0090 }
            java.io.Writer r1 = r8.journalWriter     // Catch:{ all -> 0x0090 }
            r1.append(r9)     // Catch:{ all -> 0x0090 }
            java.io.Writer r1 = r8.journalWriter     // Catch:{ all -> 0x0090 }
            r3 = 10
            r1.append(r3)     // Catch:{ all -> 0x0090 }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r1 = r8.lruEntries     // Catch:{ all -> 0x0090 }
            r1.remove(r9)     // Catch:{ all -> 0x0090 }
            boolean r1 = r8.journalRebuildRequired()     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x008c
            java.util.concurrent.ThreadPoolExecutor r1 = r8.executorService     // Catch:{ all -> 0x0090 }
            java.util.concurrent.Callable<java.lang.Void> r3 = r8.cleanupCallable     // Catch:{ all -> 0x0090 }
            r1.submit(r3)     // Catch:{ all -> 0x0090 }
        L_0x008c:
            monitor-exit(r8)
            return r2
        L_0x008e:
            monitor-exit(r8)
            return r1
        L_0x0090:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.remove(java.lang.String):boolean");
    }

    public synchronized boolean isClosed() {
        return this.journalWriter == null;
    }

    private void checkNotClosed() {
        if (this.journalWriter == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        checkNotClosed();
        trimToSize();
        this.journalWriter.flush();
    }

    public synchronized void close() throws IOException {
        if (this.journalWriter != null) {
            Iterator it = new ArrayList(this.lruEntries.values()).iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
        }
    }

    /* access modifiers changed from: private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            remove((String) ((java.util.Map.Entry) this.lruEntries.entrySet().iterator().next()).getKey());
        }
    }

    public void delete() throws IOException {
        close();
        Util.deleteContents(this.directory);
    }

    /* access modifiers changed from: private */
    public static String inputStreamToString(InputStream in) throws IOException {
        return Util.readFully(new InputStreamReader(in, Util.UTF_8));
    }
}