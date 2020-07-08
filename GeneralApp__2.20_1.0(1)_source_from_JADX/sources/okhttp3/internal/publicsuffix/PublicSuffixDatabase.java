package okhttp3.internal.publicsuffix;

import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\fJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\u0016\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, mo24952d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "", "()V", "listRead", "Ljava/util/concurrent/atomic/AtomicBoolean;", "publicSuffixExceptionListBytes", "", "publicSuffixListBytes", "readCompleteLatch", "Ljava/util/concurrent/CountDownLatch;", "findMatchingRule", "", "", "domainLabels", "getEffectiveTldPlusOne", "domain", "readTheList", "", "readTheListUninterruptibly", "setListBytes", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: PublicSuffixDatabase.kt */
public final class PublicSuffixDatabase {
    public static final Companion Companion = new Companion(null);
    private static final char EXCEPTION_MARKER = '!';
    private static final List<String> PREVAILING_RULE = CollectionsKt.listOf("*");
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    private static final byte[] WILDCARD_LABEL = {(byte) 42};
    /* access modifiers changed from: private */
    public static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private byte[] publicSuffixExceptionListBytes;
    /* access modifiers changed from: private */
    public byte[] publicSuffixListBytes;
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\fJ)\u0010\u000e\u001a\u0004\u0018\u00010\u0007*\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo24952d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;", "", "()V", "EXCEPTION_MARKER", "", "PREVAILING_RULE", "", "", "PUBLIC_SUFFIX_RESOURCE", "WILDCARD_LABEL", "", "instance", "Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "get", "binarySearch", "labels", "", "labelIndex", "", "([B[[BI)Ljava/lang/String;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: PublicSuffixDatabase.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final PublicSuffixDatabase get() {
            return PublicSuffixDatabase.instance;
        }

        /* access modifiers changed from: private */
        public final String binarySearch(byte[] $this$binarySearch, byte[][] labels, int labelIndex) {
            int byte0;
            int compareResult;
            int low;
            byte[] bArr = $this$binarySearch;
            int low2 = 0;
            int high = bArr.length;
            String match = null;
            while (low2 < high) {
                int mid = (low2 + high) / 2;
                while (mid > -1 && bArr[mid] != ((byte) 10)) {
                    mid--;
                }
                int mid2 = mid + 1;
                int end = 1;
                while (bArr[mid2 + end] != ((byte) 10)) {
                    end++;
                }
                int publicSuffixLength = (mid2 + end) - mid2;
                int currentLabelIndex = labelIndex;
                int currentLabelByteIndex = 0;
                int publicSuffixByteIndex = 0;
                boolean expectDot = false;
                while (true) {
                    if (expectDot) {
                        byte0 = 46;
                        expectDot = false;
                    } else {
                        byte0 = Util.and(labels[currentLabelIndex][currentLabelByteIndex], 255);
                    }
                    compareResult = byte0 - Util.and(bArr[mid2 + publicSuffixByteIndex], 255);
                    if (compareResult == 0) {
                        publicSuffixByteIndex++;
                        currentLabelByteIndex++;
                        if (publicSuffixByteIndex == publicSuffixLength) {
                            break;
                        }
                        if (labels[currentLabelIndex].length != currentLabelByteIndex) {
                            low = low2;
                        } else if (currentLabelIndex == ((Object[]) labels).length - 1) {
                            break;
                        } else {
                            low = low2;
                            currentLabelIndex++;
                            expectDot = true;
                            currentLabelByteIndex = -1;
                        }
                        low2 = low;
                    } else {
                        break;
                    }
                }
                if (compareResult < 0) {
                    high = mid2 - 1;
                } else if (compareResult > 0) {
                    low2 = mid2 + end + 1;
                } else {
                    int publicSuffixBytesLeft = publicSuffixLength - publicSuffixByteIndex;
                    int labelBytesLeft = labels[currentLabelIndex].length - currentLabelByteIndex;
                    int i = currentLabelIndex + 1;
                    while (i < ((Object[]) labels).length) {
                        labelBytesLeft += labels[i].length;
                        i++;
                        low2 = low2;
                    }
                    int low3 = low2;
                    if (labelBytesLeft < publicSuffixBytesLeft) {
                        high = mid2 - 1;
                        low2 = low3;
                    } else if (labelBytesLeft > publicSuffixBytesLeft) {
                        low2 = mid2 + end + 1;
                    } else {
                        Charset charset = StandardCharsets.UTF_8;
                        Intrinsics.checkExpressionValueIsNotNull(charset, "UTF_8");
                        return new String(bArr, mid2, publicSuffixLength, charset);
                    }
                }
            }
            return match;
        }
    }

    public static final /* synthetic */ byte[] access$getPublicSuffixListBytes$p(PublicSuffixDatabase $this) {
        byte[] bArr = $this.publicSuffixListBytes;
        if (bArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");
        }
        return bArr;
    }

    public final String getEffectiveTldPlusOne(String domain) {
        int firstLabelOffset;
        String str = domain;
        Intrinsics.checkParameterIsNotNull(str, ClientCookie.DOMAIN_ATTR);
        String unicodeDomain = IDN.toUnicode(domain);
        Intrinsics.checkExpressionValueIsNotNull(unicodeDomain, "unicodeDomain");
        List domainLabels = StringsKt.split$default((CharSequence) unicodeDomain, new char[]{'.'}, false, 0, 6, (Object) null);
        List rule = findMatchingRule(domainLabels);
        if (domainLabels.size() == rule.size() && ((String) rule.get(0)).charAt(0) != '!') {
            return null;
        }
        if (((String) rule.get(0)).charAt(0) == '!') {
            firstLabelOffset = domainLabels.size() - rule.size();
        } else {
            firstLabelOffset = domainLabels.size() - (rule.size() + 1);
        }
        return SequencesKt.joinToString$default(SequencesKt.drop(CollectionsKt.asSequence(StringsKt.split$default((CharSequence) str, new char[]{'.'}, false, 0, 6, (Object) null)), firstLabelOffset), ".", null, null, 0, null, null, 62, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x011a, code lost:
        if (r7 != null) goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0132, code lost:
        if (r1 != null) goto L_0x0139;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<java.lang.String> findMatchingRule(java.util.List<java.lang.String> r15) {
        /*
            r14 = this;
            java.util.concurrent.atomic.AtomicBoolean r0 = r14.listRead
            boolean r0 = r0.get()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0016
            java.util.concurrent.atomic.AtomicBoolean r0 = r14.listRead
            boolean r0 = r0.compareAndSet(r1, r2)
            if (r0 == 0) goto L_0x0016
            r14.readTheListUninterruptibly()
            goto L_0x0025
        L_0x0016:
            java.util.concurrent.CountDownLatch r0 = r14.readCompleteLatch     // Catch:{ InterruptedException -> 0x001d }
            r0.await()     // Catch:{ InterruptedException -> 0x001d }
            goto L_0x0025
        L_0x001d:
            r0 = move-exception
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            r3.interrupt()
        L_0x0025:
            r0 = r14
            okhttp3.internal.publicsuffix.PublicSuffixDatabase r0 = (okhttp3.internal.publicsuffix.PublicSuffixDatabase) r0
            byte[] r0 = r0.publicSuffixListBytes
            if (r0 == 0) goto L_0x0030
            r0 = 1
            goto L_0x0031
        L_0x0030:
            r0 = 0
        L_0x0031:
            if (r0 == 0) goto L_0x0147
            int r0 = r15.size()
            byte[][] r3 = new byte[r0][]
            r4 = 0
        L_0x003a:
            if (r4 >= r0) goto L_0x0063
            r5 = r4
            r6 = 0
            java.lang.Object r7 = r15.get(r5)
            java.lang.String r7 = (java.lang.String) r7
            java.nio.charset.Charset r8 = java.nio.charset.StandardCharsets.UTF_8
            java.lang.String r9 = "UTF_8"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r9)
            if (r7 == 0) goto L_0x005b
            byte[] r7 = r7.getBytes(r8)
            java.lang.String r8 = "(this as java.lang.String).getBytes(charset)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            r3[r4] = r7
            int r4 = r4 + 1
            goto L_0x003a
        L_0x005b:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type java.lang.String"
            r0.<init>(r1)
            throw r0
        L_0x0063:
            r0 = r3
            byte[][] r0 = (byte[][]) r0
            r3 = 0
            r4 = r3
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r0.length
            r6 = 0
        L_0x006c:
            java.lang.String r7 = "publicSuffixListBytes"
            if (r6 >= r5) goto L_0x0085
            okhttp3.internal.publicsuffix.PublicSuffixDatabase$Companion r8 = Companion
            byte[] r9 = r14.publicSuffixListBytes
            if (r9 != 0) goto L_0x0079
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
        L_0x0079:
            java.lang.String r8 = r8.binarySearch(r9, r0, r6)
            if (r8 == 0) goto L_0x0081
            r4 = r8
            goto L_0x0085
        L_0x0081:
            int r6 = r6 + 1
            goto L_0x006c
        L_0x0085:
            r5 = r3
            java.lang.String r5 = (java.lang.String) r5
            r6 = r0
            java.lang.Object[] r6 = (java.lang.Object[]) r6
            int r6 = r6.length
            if (r6 <= r2) goto L_0x00b8
            r6 = r0
            java.lang.Object[] r6 = (java.lang.Object[]) r6
            java.lang.Object r6 = r6.clone()
            byte[][] r6 = (byte[][]) r6
            r8 = r6
            java.lang.Object[] r8 = (java.lang.Object[]) r8
            int r8 = r8.length
            int r8 = r8 - r2
            r9 = 0
        L_0x009d:
            if (r9 >= r8) goto L_0x00b8
            byte[] r10 = WILDCARD_LABEL
            r6[r9] = r10
            okhttp3.internal.publicsuffix.PublicSuffixDatabase$Companion r10 = Companion
            byte[] r11 = r14.publicSuffixListBytes
            if (r11 != 0) goto L_0x00ac
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
        L_0x00ac:
            java.lang.String r10 = r10.binarySearch(r11, r6, r9)
            if (r10 == 0) goto L_0x00b4
            r5 = r10
            goto L_0x00b8
        L_0x00b4:
            int r9 = r9 + 1
            goto L_0x009d
        L_0x00b8:
            java.lang.String r3 = (java.lang.String) r3
            if (r5 == 0) goto L_0x00dc
            r6 = r0
            java.lang.Object[] r6 = (java.lang.Object[]) r6
            int r6 = r6.length
            int r6 = r6 - r2
            r7 = 0
        L_0x00c2:
            if (r7 >= r6) goto L_0x00dc
            okhttp3.internal.publicsuffix.PublicSuffixDatabase$Companion r8 = Companion
            byte[] r9 = r14.publicSuffixExceptionListBytes
            if (r9 != 0) goto L_0x00cf
            java.lang.String r10 = "publicSuffixExceptionListBytes"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
        L_0x00cf:
            java.lang.String r8 = r8.binarySearch(r9, r0, r7)
            if (r8 == 0) goto L_0x00d8
            r3 = r8
            goto L_0x00dc
        L_0x00d8:
            int r7 = r7 + 1
            goto L_0x00c2
        L_0x00dc:
            r6 = 46
            if (r3 == 0) goto L_0x0101
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r8 = 33
            r7.append(r8)
            r7.append(r3)
            java.lang.String r3 = r7.toString()
            r7 = r3
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            char[] r8 = new char[r2]
            r8[r1] = r6
            r9 = 0
            r10 = 0
            r11 = 6
            r12 = 0
            java.util.List r1 = kotlin.text.StringsKt.split$default(r7, r8, r9, r10, r11, r12)
            return r1
        L_0x0101:
            if (r4 != 0) goto L_0x0108
            if (r5 != 0) goto L_0x0108
            java.util.List<java.lang.String> r1 = PREVAILING_RULE
            return r1
        L_0x0108:
            if (r4 == 0) goto L_0x011d
            r7 = r4
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            char[] r8 = new char[r2]
            r8[r1] = r6
            r9 = 0
            r10 = 0
            r11 = 6
            r12 = 0
            java.util.List r7 = kotlin.text.StringsKt.split$default(r7, r8, r9, r10, r11, r12)
            if (r7 == 0) goto L_0x011d
            goto L_0x0121
        L_0x011d:
            java.util.List r7 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0121:
            if (r5 == 0) goto L_0x0135
            r8 = r5
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            char[] r9 = new char[r2]
            r9[r1] = r6
            r10 = 0
            r11 = 0
            r12 = 6
            r13 = 0
            java.util.List r1 = kotlin.text.StringsKt.split$default(r8, r9, r10, r11, r12, r13)
            if (r1 == 0) goto L_0x0135
            goto L_0x0139
        L_0x0135:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0139:
            int r2 = r7.size()
            int r6 = r1.size()
            if (r2 <= r6) goto L_0x0145
            r2 = r7
            goto L_0x0146
        L_0x0145:
            r2 = r1
        L_0x0146:
            return r2
        L_0x0147:
            r0 = 0
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Unable to load publicsuffixes.gz resource from the classpath."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.findMatchingRule(java.util.List):java.util.List");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void readTheListUninterruptibly() {
        /*
            r6 = this;
            r0 = 0
        L_0x0002:
            r6.readTheList()     // Catch:{  }
            if (r0 == 0) goto L_0x0011
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ InterruptedIOException -> 0x002f, IOException -> 0x0014 }
            r1.interrupt()     // Catch:{ InterruptedIOException -> 0x002f, IOException -> 0x0014 }
        L_0x0011:
            return
        L_0x0012:
            r1 = move-exception
            goto L_0x0036
        L_0x0014:
            r1 = move-exception
            okhttp3.internal.platform.Platform$Companion r2 = okhttp3.internal.platform.Platform.Companion     // Catch:{ all -> 0x0012 }
            okhttp3.internal.platform.Platform r2 = r2.get()     // Catch:{ all -> 0x0012 }
            r3 = 5
            java.lang.String r4 = "Failed to read public suffix list"
            r5 = r1
            java.lang.Throwable r5 = (java.lang.Throwable) r5     // Catch:{ all -> 0x0012 }
            r2.log(r3, r4, r5)     // Catch:{ all -> 0x0012 }
            if (r0 == 0) goto L_0x002e
            java.lang.Thread r2 = java.lang.Thread.currentThread()
            r2.interrupt()
        L_0x002e:
            return
        L_0x002f:
            r1 = move-exception
            java.lang.Thread.interrupted()     // Catch:{ all -> 0x0012 }
            r0 = 1
            goto L_0x0002
        L_0x0036:
            if (r0 == 0) goto L_0x003f
            java.lang.Thread r2 = java.lang.Thread.currentThread()
            r2.interrupt()
        L_0x003f:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.readTheListUninterruptibly():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0061, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0062, code lost:
        kotlin.p014io.CloseableKt.closeFinally(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0065, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void readTheList() throws java.io.IOException {
        /*
            r11 = this;
            r0 = 0
            r1 = r0
            byte[] r1 = (byte[]) r1
            r2 = r0
            byte[] r2 = (byte[]) r2
            java.lang.Class<okhttp3.internal.publicsuffix.PublicSuffixDatabase> r3 = okhttp3.internal.publicsuffix.PublicSuffixDatabase.class
            java.lang.String r4 = "publicsuffixes.gz"
            java.io.InputStream r3 = r3.getResourceAsStream(r4)
            if (r3 == 0) goto L_0x0066
            okio.GzipSource r4 = new okio.GzipSource
            okio.Source r5 = okio.Okio.source(r3)
            r4.<init>(r5)
            okio.Source r4 = (okio.Source) r4
            okio.BufferedSource r4 = okio.Okio.buffer(r4)
            java.io.Closeable r4 = (java.io.Closeable) r4
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r5 = r4
            okio.BufferedSource r5 = (okio.BufferedSource) r5     // Catch:{ all -> 0x005f }
            r6 = 0
            int r7 = r5.readInt()     // Catch:{ all -> 0x005f }
            long r8 = (long) r7     // Catch:{ all -> 0x005f }
            byte[] r8 = r5.readByteArray(r8)     // Catch:{ all -> 0x005f }
            r1 = r8
            int r8 = r5.readInt()     // Catch:{ all -> 0x005f }
            long r9 = (long) r8     // Catch:{ all -> 0x005f }
            byte[] r9 = r5.readByteArray(r9)     // Catch:{ all -> 0x005f }
            r2 = r9
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x005f }
            kotlin.p014io.CloseableKt.closeFinally(r4, r0)
            monitor-enter(r11)
            r0 = 0
            if (r1 != 0) goto L_0x004a
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x005c }
        L_0x004a:
            r11.publicSuffixListBytes = r1     // Catch:{ all -> 0x005c }
            if (r2 != 0) goto L_0x0051
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x005c }
        L_0x0051:
            r11.publicSuffixExceptionListBytes = r2     // Catch:{ all -> 0x005c }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x005c }
            monitor-exit(r11)
            java.util.concurrent.CountDownLatch r0 = r11.readCompleteLatch
            r0.countDown()
            return
        L_0x005c:
            r0 = move-exception
            monitor-exit(r11)
            throw r0
        L_0x005f:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0061 }
        L_0x0061:
            r5 = move-exception
            kotlin.p014io.CloseableKt.closeFinally(r4, r0)
            throw r5
        L_0x0066:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.readTheList():void");
    }

    public final void setListBytes(byte[] publicSuffixListBytes2, byte[] publicSuffixExceptionListBytes2) {
        Intrinsics.checkParameterIsNotNull(publicSuffixListBytes2, "publicSuffixListBytes");
        Intrinsics.checkParameterIsNotNull(publicSuffixExceptionListBytes2, "publicSuffixExceptionListBytes");
        this.publicSuffixListBytes = publicSuffixListBytes2;
        this.publicSuffixExceptionListBytes = publicSuffixExceptionListBytes2;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }
}
