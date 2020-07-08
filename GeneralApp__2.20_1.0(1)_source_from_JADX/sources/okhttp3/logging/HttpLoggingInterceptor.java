package okhttp3.logging;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.loopj.android.http.AsyncHttpClient;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.Interceptor;
import p008cz.msebera.android.httpclient.protocol.HTTP;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002\u001e\u001fB\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\r\u0010\u000b\u001a\u00020\tH\u0007¢\u0006\u0002\b\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0007J\u0010\u0010\u001d\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\tH\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u000e¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@GX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\n\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0007\n\u0005\bF0\u0001¨\u0006 "}, mo24952d2 = {"Lokhttp3/logging/HttpLoggingInterceptor;", "Lokhttp3/Interceptor;", "logger", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "(Lokhttp3/logging/HttpLoggingInterceptor$Logger;)V", "headersToRedact", "", "", "<set-?>", "Lokhttp3/logging/HttpLoggingInterceptor$Level;", "level", "getLevel", "()Lokhttp3/logging/HttpLoggingInterceptor$Level;", "(Lokhttp3/logging/HttpLoggingInterceptor$Level;)V", "bodyHasUnknownEncoding", "", "headers", "Lokhttp3/Headers;", "-deprecated_level", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "logHeader", "", "i", "", "redactHeader", "name", "setLevel", "Level", "Logger", "okhttp-logging-interceptor"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: HttpLoggingInterceptor.kt */
public final class HttpLoggingInterceptor implements Interceptor {
    private volatile Set<String> headersToRedact;
    private volatile Level level;
    private final Logger logger;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, mo24952d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Level;", "", "(Ljava/lang/String;I)V", "NONE", "BASIC", "HEADERS", "BODY", "okhttp-logging-interceptor"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: HttpLoggingInterceptor.kt */
    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u0002\u0007\n\u0005\bF0\u0001¨\u0006\u0007"}, mo24952d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "", "log", "", "message", "", "Companion", "okhttp-logging-interceptor"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: HttpLoggingInterceptor.kt */
    public interface Logger {
        public static final Companion Companion = new Companion(null);
        public static final Logger DEFAULT = new HttpLoggingInterceptor$Logger$Companion$DEFAULT$1();

        @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000¨\u0006\u0001\u0002\u0007\n\u0005\bF0\u0001¨\u0006\u0005"}, mo24952d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Logger$Companion;", "", "()V", "DEFAULT", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "okhttp-logging-interceptor"}, mo24953k = 1, mo24954mv = {1, 1, 15})
        /* compiled from: HttpLoggingInterceptor.kt */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = null;

            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }

        void log(String str);
    }

    public HttpLoggingInterceptor() {
        this(null, 1, null);
    }

    public HttpLoggingInterceptor(Logger logger2) {
        Intrinsics.checkParameterIsNotNull(logger2, "logger");
        this.logger = logger2;
        this.headersToRedact = SetsKt.emptySet();
        this.level = Level.NONE;
    }

    public /* synthetic */ HttpLoggingInterceptor(Logger logger2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            logger2 = Logger.DEFAULT;
        }
        this(logger2);
    }

    public final Level getLevel() {
        return this.level;
    }

    public final void level(Level level2) {
        Intrinsics.checkParameterIsNotNull(level2, "<set-?>");
        this.level = level2;
    }

    public final void redactHeader(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        TreeSet newHeadersToRedact = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        CollectionsKt.addAll((Collection<? super T>) newHeadersToRedact, (Iterable<? extends T>) this.headersToRedact);
        newHeadersToRedact.add(name);
        this.headersToRedact = newHeadersToRedact;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to var. Replace setLevel(...) with level(...) to fix Java", replaceWith = @ReplaceWith(expression = "apply { this.level = level }", imports = {}))
    public final HttpLoggingInterceptor setLevel(Level level2) {
        Intrinsics.checkParameterIsNotNull(level2, Param.LEVEL);
        this.level = level2;
        return this;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "level", imports = {}))
    /* renamed from: -deprecated_level reason: not valid java name */
    public final Level m2375deprecated_level() {
        return this.level;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:122:0x03cd, code lost:
        if (r9 != null) goto L_0x03d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01aa, code lost:
        if (r11 != null) goto L_0x01b2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r32) throws java.io.IOException {
        /*
            r31 = this;
            r1 = r31
            r2 = r32
            java.lang.String r0 = "chain"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            okhttp3.logging.HttpLoggingInterceptor$Level r3 = r1.level
            okhttp3.Request r4 = r32.request()
            okhttp3.logging.HttpLoggingInterceptor$Level r0 = okhttp3.logging.HttpLoggingInterceptor.Level.NONE
            if (r3 != r0) goto L_0x0018
            okhttp3.Response r0 = r2.proceed(r4)
            return r0
        L_0x0018:
            okhttp3.logging.HttpLoggingInterceptor$Level r0 = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
            if (r3 != r0) goto L_0x001e
            r0 = 1
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            r7 = r0
            if (r7 != 0) goto L_0x0029
            okhttp3.logging.HttpLoggingInterceptor$Level r0 = okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS
            if (r3 != r0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r0 = 0
            goto L_0x002a
        L_0x0029:
            r0 = 1
        L_0x002a:
            r8 = r0
            okhttp3.RequestBody r9 = r4.body()
            okhttp3.Connection r10 = r32.connection()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r11 = "--> "
            r0.append(r11)
            java.lang.String r11 = r4.method()
            r0.append(r11)
            r11 = 32
            r0.append(r11)
            okhttp3.HttpUrl r12 = r4.url()
            r0.append(r12)
            java.lang.String r12 = ""
            if (r10 == 0) goto L_0x006b
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = " "
            r13.append(r14)
            okhttp3.Protocol r14 = r10.protocol()
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            goto L_0x006c
        L_0x006b:
            r13 = r12
        L_0x006c:
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            java.lang.String r13 = "-byte body)"
            java.lang.String r14 = " ("
            if (r8 != 0) goto L_0x0099
            if (r9 == 0) goto L_0x0099
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r0)
            r15.append(r14)
            r17 = r12
            long r11 = r9.contentLength()
            r15.append(r11)
            r15.append(r13)
            java.lang.String r0 = r15.toString()
            r11 = r0
            goto L_0x009c
        L_0x0099:
            r17 = r12
            r11 = r0
        L_0x009c:
            okhttp3.logging.HttpLoggingInterceptor$Logger r0 = r1.logger
            r0.log(r11)
            java.lang.String r0 = "-byte body omitted)"
            java.lang.String r12 = "UTF_8"
            r18 = -1
            if (r8 == 0) goto L_0x0248
            okhttp3.Headers r15 = r4.headers()
            if (r9 == 0) goto L_0x011a
            okhttp3.MediaType r20 = r9.contentType()
            if (r20 == 0) goto L_0x00e1
            r21 = r20
            r20 = 0
            java.lang.String r5 = "Content-Type"
            java.lang.String r5 = r15.get(r5)
            if (r5 != 0) goto L_0x00dc
            okhttp3.logging.HttpLoggingInterceptor$Logger r5 = r1.logger
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r23 = r3
            java.lang.String r3 = "Content-Type: "
            r6.append(r3)
            r3 = r21
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            r5.log(r6)
            goto L_0x00e0
        L_0x00dc:
            r23 = r3
            r3 = r21
        L_0x00e0:
            goto L_0x00e3
        L_0x00e1:
            r23 = r3
        L_0x00e3:
            long r5 = r9.contentLength()
            int r3 = (r5 > r18 ? 1 : (r5 == r18 ? 0 : -1))
            if (r3 == 0) goto L_0x0116
            java.lang.String r3 = "Content-Length"
            java.lang.String r3 = r15.get(r3)
            if (r3 != 0) goto L_0x0112
            okhttp3.logging.HttpLoggingInterceptor$Logger r3 = r1.logger
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Content-Length: "
            r5.append(r6)
            r6 = r10
            r20 = r11
            long r10 = r9.contentLength()
            r5.append(r10)
            java.lang.String r5 = r5.toString()
            r3.log(r5)
            goto L_0x011f
        L_0x0112:
            r6 = r10
            r20 = r11
            goto L_0x011f
        L_0x0116:
            r6 = r10
            r20 = r11
            goto L_0x011f
        L_0x011a:
            r23 = r3
            r6 = r10
            r20 = r11
        L_0x011f:
            int r3 = r15.size()
            r5 = 0
        L_0x0124:
            if (r5 >= r3) goto L_0x012c
            r1.logHeader(r15, r5)
            r10 = 1
            int r5 = r5 + r10
            goto L_0x0124
        L_0x012c:
            java.lang.String r3 = "--> END "
            if (r7 == 0) goto L_0x0228
            if (r9 != 0) goto L_0x013b
            r21 = r6
            r26 = r17
            r17 = r15
            r15 = r12
            goto L_0x022f
        L_0x013b:
            okhttp3.Headers r5 = r4.headers()
            boolean r5 = r1.bodyHasUnknownEncoding(r5)
            if (r5 == 0) goto L_0x0169
            okhttp3.logging.HttpLoggingInterceptor$Logger r5 = r1.logger
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r3)
            java.lang.String r3 = r4.method()
            r10.append(r3)
            java.lang.String r3 = " (encoded body omitted)"
            r10.append(r3)
            java.lang.String r3 = r10.toString()
            r5.log(r3)
            r21 = r6
            r15 = r12
            r26 = r17
            goto L_0x0251
        L_0x0169:
            boolean r5 = r9.isDuplex()
            if (r5 == 0) goto L_0x0193
            okhttp3.logging.HttpLoggingInterceptor$Logger r5 = r1.logger
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r3)
            java.lang.String r3 = r4.method()
            r10.append(r3)
            java.lang.String r3 = " (duplex request body omitted)"
            r10.append(r3)
            java.lang.String r3 = r10.toString()
            r5.log(r3)
            r21 = r6
            r15 = r12
            r26 = r17
            goto L_0x0251
        L_0x0193:
            okio.Buffer r5 = new okio.Buffer
            r5.<init>()
            r10 = r5
            okio.BufferedSink r10 = (okio.BufferedSink) r10
            r9.writeTo(r10)
            okhttp3.MediaType r10 = r9.contentType()
            if (r10 == 0) goto L_0x01ad
            java.nio.charset.Charset r11 = java.nio.charset.StandardCharsets.UTF_8
            java.nio.charset.Charset r11 = r10.charset(r11)
            if (r11 == 0) goto L_0x01ad
            goto L_0x01b2
        L_0x01ad:
            java.nio.charset.Charset r11 = java.nio.charset.StandardCharsets.UTF_8
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r12)
        L_0x01b2:
            r21 = r6
            okhttp3.logging.HttpLoggingInterceptor$Logger r6 = r1.logger
            r24 = r10
            r10 = r17
            r6.log(r10)
            boolean r6 = okhttp3.logging.Utf8Kt.isProbablyUtf8(r5)
            if (r6 == 0) goto L_0x01f9
            okhttp3.logging.HttpLoggingInterceptor$Logger r6 = r1.logger
            r17 = r15
            java.lang.String r15 = r5.readString(r11)
            r6.log(r15)
            okhttp3.logging.HttpLoggingInterceptor$Logger r6 = r1.logger
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r3)
            java.lang.String r3 = r4.method()
            r15.append(r3)
            r15.append(r14)
            r26 = r10
            r25 = r11
            long r10 = r9.contentLength()
            r15.append(r10)
            r15.append(r13)
            java.lang.String r3 = r15.toString()
            r6.log(r3)
            r15 = r12
            goto L_0x0251
        L_0x01f9:
            r26 = r10
            r25 = r11
            r17 = r15
            okhttp3.logging.HttpLoggingInterceptor$Logger r6 = r1.logger
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r3)
            java.lang.String r3 = r4.method()
            r10.append(r3)
            java.lang.String r3 = " (binary "
            r10.append(r3)
            r15 = r12
            long r11 = r9.contentLength()
            r10.append(r11)
            r10.append(r0)
            java.lang.String r3 = r10.toString()
            r6.log(r3)
            goto L_0x0251
        L_0x0228:
            r21 = r6
            r26 = r17
            r17 = r15
            r15 = r12
        L_0x022f:
            okhttp3.logging.HttpLoggingInterceptor$Logger r5 = r1.logger
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r3)
            java.lang.String r3 = r4.method()
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            r5.log(r3)
            goto L_0x0251
        L_0x0248:
            r23 = r3
            r21 = r10
            r20 = r11
            r15 = r12
            r26 = r17
        L_0x0251:
            long r5 = java.lang.System.nanoTime()
            r3 = 0
            okhttp3.Response r10 = r2.proceed(r4)     // Catch:{ Exception -> 0x047e }
            r3 = r10
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r11 = java.lang.System.nanoTime()
            long r11 = r11 - r5
            long r10 = r10.toMillis(r11)
            okhttp3.ResponseBody r12 = r3.body()
            if (r12 != 0) goto L_0x0273
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0273:
            r17 = r4
            r24 = r5
            long r4 = r12.contentLength()
            int r6 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r6 == 0) goto L_0x0291
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r4)
            java.lang.String r2 = "-byte"
            r6.append(r2)
            java.lang.String r2 = r6.toString()
            goto L_0x0293
        L_0x0291:
            java.lang.String r2 = "unknown-length"
        L_0x0293:
            okhttp3.logging.HttpLoggingInterceptor$Logger r6 = r1.logger
            r18 = r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r19 = r15
            java.lang.String r15 = "<-- "
            r9.append(r15)
            int r15 = r3.code()
            r9.append(r15)
            java.lang.String r15 = r3.message()
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15
            int r15 = r15.length()
            if (r15 != 0) goto L_0x02b8
            r15 = 1
            goto L_0x02b9
        L_0x02b8:
            r15 = 0
        L_0x02b9:
            if (r15 == 0) goto L_0x02c4
            r29 = r4
            r27 = r13
            r4 = r26
            r28 = 32
            goto L_0x02e1
        L_0x02c4:
            java.lang.String r15 = r3.message()
            r27 = r13
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r29 = r4
            r28 = 32
            java.lang.String r4 = java.lang.String.valueOf(r28)
            r13.append(r4)
            r13.append(r15)
            java.lang.String r4 = r13.toString()
        L_0x02e1:
            r9.append(r4)
            r4 = 32
            r9.append(r4)
            okhttp3.Request r4 = r3.request()
            okhttp3.HttpUrl r4 = r4.url()
            r9.append(r4)
            r9.append(r14)
            r9.append(r10)
            java.lang.String r4 = "ms"
            r9.append(r4)
            if (r8 != 0) goto L_0x0318
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = ", "
            r4.append(r5)
            r4.append(r2)
            java.lang.String r5 = " body"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            goto L_0x031a
        L_0x0318:
            r4 = r26
        L_0x031a:
            r9.append(r4)
            r4 = 41
            r9.append(r4)
            java.lang.String r4 = r9.toString()
            r6.log(r4)
            if (r8 == 0) goto L_0x0479
            okhttp3.Headers r4 = r3.headers()
            int r5 = r4.size()
            r6 = 0
        L_0x0334:
            if (r6 >= r5) goto L_0x033c
            r1.logHeader(r4, r6)
            r9 = 1
            int r6 = r6 + r9
            goto L_0x0334
        L_0x033c:
            if (r7 == 0) goto L_0x046e
            boolean r5 = okhttp3.internal.http.HttpHeaders.promisesBody(r3)
            if (r5 != 0) goto L_0x0349
            r22 = r2
            r2 = r4
            goto L_0x0471
        L_0x0349:
            okhttp3.Headers r5 = r3.headers()
            boolean r5 = r1.bodyHasUnknownEncoding(r5)
            if (r5 == 0) goto L_0x035e
            okhttp3.logging.HttpLoggingInterceptor$Logger r0 = r1.logger
            java.lang.String r5 = "<-- END HTTP (encoded body omitted)"
            r0.log(r5)
            r22 = r2
            goto L_0x047b
        L_0x035e:
            okio.BufferedSource r5 = r12.source()
            r13 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r5.request(r13)
            okio.Buffer r6 = r5.getBuffer()
            r9 = 0
            r13 = r9
            java.lang.Long r13 = (java.lang.Long) r13
            java.lang.String r14 = "Content-Encoding"
            java.lang.String r14 = r4.get(r14)
            java.lang.String r15 = "gzip"
            r9 = 1
            boolean r9 = kotlin.text.StringsKt.equals(r15, r14, r9)
            if (r9 == 0) goto L_0x03bf
            long r14 = r6.size()
            java.lang.Long r13 = java.lang.Long.valueOf(r14)
            okio.GzipSource r9 = new okio.GzipSource
            okio.Buffer r14 = r6.clone()
            okio.Source r14 = (okio.Source) r14
            r9.<init>(r14)
            java.io.Closeable r9 = (java.io.Closeable) r9
            r14 = 0
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            r15 = r9
            okio.GzipSource r15 = (okio.GzipSource) r15     // Catch:{ all -> 0x03b4 }
            r16 = 0
            okio.Buffer r22 = new okio.Buffer     // Catch:{ all -> 0x03b4 }
            r22.<init>()     // Catch:{ all -> 0x03b4 }
            r6 = r22
            r22 = r2
            r2 = r15
            okio.Source r2 = (okio.Source) r2     // Catch:{ all -> 0x03b1 }
            r6.writeAll(r2)     // Catch:{ all -> 0x03b1 }
            kotlin.p014io.CloseableKt.closeFinally(r9, r14)
            goto L_0x03c1
        L_0x03b1:
            r0 = move-exception
            r2 = r0
            goto L_0x03b8
        L_0x03b4:
            r0 = move-exception
            r22 = r2
            r2 = r0
        L_0x03b8:
            throw r2     // Catch:{ all -> 0x03b9 }
        L_0x03b9:
            r0 = move-exception
            r14 = r0
            kotlin.p014io.CloseableKt.closeFinally(r9, r2)
            throw r14
        L_0x03bf:
            r22 = r2
        L_0x03c1:
            okhttp3.MediaType r2 = r12.contentType()
            if (r2 == 0) goto L_0x03d0
            java.nio.charset.Charset r9 = java.nio.charset.StandardCharsets.UTF_8
            java.nio.charset.Charset r9 = r2.charset(r9)
            if (r9 == 0) goto L_0x03d0
            goto L_0x03d7
        L_0x03d0:
            java.nio.charset.Charset r9 = java.nio.charset.StandardCharsets.UTF_8
            r14 = r19
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r14)
        L_0x03d7:
            boolean r14 = okhttp3.logging.Utf8Kt.isProbablyUtf8(r6)
            if (r14 != 0) goto L_0x0407
            okhttp3.logging.HttpLoggingInterceptor$Logger r14 = r1.logger
            r15 = r26
            r14.log(r15)
            okhttp3.logging.HttpLoggingInterceptor$Logger r14 = r1.logger
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r16 = r2
            java.lang.String r2 = "<-- END HTTP (binary "
            r15.append(r2)
            r2 = r4
            r19 = r5
            long r4 = r6.size()
            r15.append(r4)
            r15.append(r0)
            java.lang.String r0 = r15.toString()
            r14.log(r0)
            return r3
        L_0x0407:
            r16 = r2
            r2 = r4
            r19 = r5
            r15 = r26
            r4 = 0
            int r0 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0426
            okhttp3.logging.HttpLoggingInterceptor$Logger r0 = r1.logger
            r0.log(r15)
            okhttp3.logging.HttpLoggingInterceptor$Logger r0 = r1.logger
            okio.Buffer r4 = r6.clone()
            java.lang.String r4 = r4.readString(r9)
            r0.log(r4)
        L_0x0426:
            java.lang.String r0 = "<-- END HTTP ("
            if (r13 == 0) goto L_0x0450
            okhttp3.logging.HttpLoggingInterceptor$Logger r4 = r1.logger
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            long r14 = r6.size()
            r5.append(r14)
            java.lang.String r0 = "-byte, "
            r5.append(r0)
            r5.append(r13)
            java.lang.String r0 = "-gzipped-byte body)"
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r4.log(r0)
            goto L_0x047b
        L_0x0450:
            okhttp3.logging.HttpLoggingInterceptor$Logger r4 = r1.logger
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            long r14 = r6.size()
            r5.append(r14)
            r0 = r27
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r4.log(r0)
            goto L_0x047b
        L_0x046e:
            r22 = r2
            r2 = r4
        L_0x0471:
            okhttp3.logging.HttpLoggingInterceptor$Logger r0 = r1.logger
            java.lang.String r4 = "<-- END HTTP"
            r0.log(r4)
            goto L_0x047b
        L_0x0479:
            r22 = r2
        L_0x047b:
            return r3
        L_0x047e:
            r0 = move-exception
            r17 = r4
            r24 = r5
            r18 = r9
            r2 = r0
            r0 = r2
            okhttp3.logging.HttpLoggingInterceptor$Logger r2 = r1.logger
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "<-- HTTP FAILED: "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            r2.log(r4)
            r2 = r0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.logging.HttpLoggingInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    private final void logHeader(Headers headers, int i) {
        String value = this.headersToRedact.contains(headers.name(i)) ? "██" : headers.value(i);
        Logger logger2 = this.logger;
        StringBuilder sb = new StringBuilder();
        sb.append(headers.name(i));
        sb.append(": ");
        sb.append(value);
        logger2.log(sb.toString());
    }

    private final boolean bodyHasUnknownEncoding(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        boolean z = false;
        if (contentEncoding == null) {
            return false;
        }
        if (!StringsKt.equals(contentEncoding, HTTP.IDENTITY_CODING, true) && !StringsKt.equals(contentEncoding, AsyncHttpClient.ENCODING_GZIP, true)) {
            z = true;
        }
        return z;
    }
}
