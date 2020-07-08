package okhttp3;

import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0011\u0018\u0000 !2\u00020\u0001:\u0002 !Bq\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\r\u0010\u000f\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0015J\r\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u0016J\r\u0010\u000b\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u0017J\r\u0010\f\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u0018J\r\u0010\n\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0019J\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u001aJ\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u001bJ\r\u0010\u000e\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u001cJ\r\u0010\r\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u001dJ\r\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u001eJ\b\u0010\u001f\u001a\u00020\u0011H\u0016R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u000f\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0013R\u0013\u0010\u0005\u001a\u00020\u00068\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0014R\u0013\u0010\u000b\u001a\u00020\u00068\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0014R\u0013\u0010\f\u001a\u00020\u00068\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0014R\u0013\u0010\n\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0013R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0013R\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0013R\u0013\u0010\u000e\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0013R\u0013\u0010\r\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0013R\u0013\u0010\u0007\u001a\u00020\u00068\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0014¨\u0006\""}, mo24952d2 = {"Lokhttp3/CacheControl;", "", "noCache", "", "noStore", "maxAgeSeconds", "", "sMaxAgeSeconds", "isPrivate", "isPublic", "mustRevalidate", "maxStaleSeconds", "minFreshSeconds", "onlyIfCached", "noTransform", "immutable", "headerValue", "", "(ZZIIZZZIIZZZLjava/lang/String;)V", "()Z", "()I", "-deprecated_immutable", "-deprecated_maxAgeSeconds", "-deprecated_maxStaleSeconds", "-deprecated_minFreshSeconds", "-deprecated_mustRevalidate", "-deprecated_noCache", "-deprecated_noStore", "-deprecated_noTransform", "-deprecated_onlyIfCached", "-deprecated_sMaxAgeSeconds", "toString", "Builder", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: CacheControl.kt */
public final class CacheControl {
    public static final Companion Companion = new Companion(null);
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    private String headerValue;
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u0003\u001a\u00020\u0000J\u0016\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\t\u001a\u00020\u0000J\u0006\u0010\n\u001a\u00020\u0000J\u0006\u0010\u000b\u001a\u00020\u0000J\u0006\u0010\f\u001a\u00020\u0000J\f\u0010\u0014\u001a\u00020\u0006*\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, mo24952d2 = {"Lokhttp3/CacheControl$Builder;", "", "()V", "immutable", "", "maxAgeSeconds", "", "maxStaleSeconds", "minFreshSeconds", "noCache", "noStore", "noTransform", "onlyIfCached", "build", "Lokhttp3/CacheControl;", "maxAge", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "maxStale", "minFresh", "clampToInt", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: CacheControl.kt */
    public static final class Builder {
        private boolean immutable;
        private int maxAgeSeconds = -1;
        private int maxStaleSeconds = -1;
        private int minFreshSeconds = -1;
        private boolean noCache;
        private boolean noStore;
        private boolean noTransform;
        private boolean onlyIfCached;

        public final Builder noCache() {
            this.noCache = true;
            return this;
        }

        public final Builder noStore() {
            this.noStore = true;
            return this;
        }

        public final Builder maxAge(int maxAge, TimeUnit timeUnit) {
            Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
            Builder $this$apply = this;
            if (maxAge >= 0) {
                $this$apply.maxAgeSeconds = $this$apply.clampToInt(timeUnit.toSeconds((long) maxAge));
                return this;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("maxAge < 0: ");
            sb.append(maxAge);
            throw new IllegalArgumentException(sb.toString().toString());
        }

        public final Builder maxStale(int maxStale, TimeUnit timeUnit) {
            Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
            Builder $this$apply = this;
            if (maxStale >= 0) {
                $this$apply.maxStaleSeconds = $this$apply.clampToInt(timeUnit.toSeconds((long) maxStale));
                return this;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("maxStale < 0: ");
            sb.append(maxStale);
            throw new IllegalArgumentException(sb.toString().toString());
        }

        public final Builder minFresh(int minFresh, TimeUnit timeUnit) {
            Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
            Builder $this$apply = this;
            if (minFresh >= 0) {
                $this$apply.minFreshSeconds = $this$apply.clampToInt(timeUnit.toSeconds((long) minFresh));
                return this;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("minFresh < 0: ");
            sb.append(minFresh);
            throw new IllegalArgumentException(sb.toString().toString());
        }

        public final Builder onlyIfCached() {
            this.onlyIfCached = true;
            return this;
        }

        public final Builder noTransform() {
            this.noTransform = true;
            return this;
        }

        public final Builder immutable() {
            this.immutable = true;
            return this;
        }

        private final int clampToInt(long $this$clampToInt) {
            if ($this$clampToInt > ((long) Integer.MAX_VALUE)) {
                return Integer.MAX_VALUE;
            }
            return (int) $this$clampToInt;
        }

        public final CacheControl build() {
            CacheControl cacheControl = new CacheControl(this.noCache, this.noStore, this.maxAgeSeconds, -1, false, false, false, this.maxStaleSeconds, this.minFreshSeconds, this.onlyIfCached, this.noTransform, this.immutable, null, null);
            return cacheControl;
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001e\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\nH\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, mo24952d2 = {"Lokhttp3/CacheControl$Companion;", "", "()V", "FORCE_CACHE", "Lokhttp3/CacheControl;", "FORCE_NETWORK", "parse", "headers", "Lokhttp3/Headers;", "indexOfElement", "", "", "characters", "startIndex", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: CacheControl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX WARNING: Removed duplicated region for block: B:47:0x012a  */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x0137  */
        @kotlin.jvm.JvmStatic
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final okhttp3.CacheControl parse(okhttp3.Headers r35) {
            /*
                r34 = this;
                r0 = r35
                java.lang.String r1 = "headers"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r1)
                r1 = 0
                r2 = 0
                r3 = -1
                r4 = -1
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = -1
                r9 = -1
                r10 = 0
                r11 = 0
                r12 = 0
                r13 = 1
                r14 = 0
                r15 = r14
                java.lang.String r15 = (java.lang.String) r15
                int r14 = r35.size()
                r16 = 0
                r31 = r3
                r3 = r2
                r2 = r1
                r1 = 0
            L_0x0023:
                if (r1 >= r14) goto L_0x0257
                r16 = r14
                java.lang.String r14 = r0.name(r1)
                r32 = r12
                java.lang.String r12 = r0.value(r1)
                r0 = 1
                r33 = r11
                java.lang.String r11 = "Cache-Control"
                boolean r11 = kotlin.text.StringsKt.equals(r14, r11, r0)
                if (r11 == 0) goto L_0x0043
                if (r15 == 0) goto L_0x0041
                r13 = 0
                goto L_0x004c
            L_0x0041:
                r15 = r12
                goto L_0x004c
            L_0x0043:
                java.lang.String r11 = "Pragma"
                boolean r11 = kotlin.text.StringsKt.equals(r14, r11, r0)
                if (r11 == 0) goto L_0x024a
                r13 = 0
            L_0x004c:
                r11 = 0
            L_0x004e:
                int r0 = r12.length()
                if (r11 >= r0) goto L_0x0239
                r0 = r11
                r18 = r2
                r2 = r34
                okhttp3.CacheControl$Companion r2 = (okhttp3.CacheControl.Companion) r2
                r19 = r3
                java.lang.String r3 = "=,;"
                int r2 = r2.indexOfElement(r12, r3, r11)
                java.lang.String r3 = "null cannot be cast to non-null type java.lang.String"
                if (r12 == 0) goto L_0x0231
                java.lang.String r11 = r12.substring(r0, r2)
                r20 = r0
                java.lang.String r0 = "(this as java.lang.Strin…ing(startIndex, endIndex)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r0)
                r21 = r4
                java.lang.String r4 = "null cannot be cast to non-null type kotlin.CharSequence"
                if (r11 == 0) goto L_0x022b
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11
                java.lang.CharSequence r11 = kotlin.text.StringsKt.trim(r11)
                java.lang.String r11 = r11.toString()
                r22 = 0
                r23 = r5
                int r5 = r12.length()
                if (r2 == r5) goto L_0x0113
                char r5 = r12.charAt(r2)
                r24 = r6
                r6 = 44
                if (r5 == r6) goto L_0x0110
                char r5 = r12.charAt(r2)
                r6 = 59
                if (r5 != r6) goto L_0x00a2
                r25 = r7
                goto L_0x0117
            L_0x00a2:
                int r2 = r2 + 1
                int r2 = okhttp3.internal.Util.indexOfNonWhitespace(r12, r2)
                int r5 = r12.length()
                if (r2 >= r5) goto L_0x00e1
                char r5 = r12.charAt(r2)
                r6 = 34
                if (r5 != r6) goto L_0x00e1
                int r2 = r2 + 1
                r4 = r2
                r25 = r12
                java.lang.CharSequence r25 = (java.lang.CharSequence) r25
                r26 = 34
                r28 = 0
                r29 = 4
                r30 = 0
                r27 = r2
                int r2 = kotlin.text.StringsKt.indexOf$default(r25, r26, r27, r28, r29, r30)
                if (r12 == 0) goto L_0x00db
                java.lang.String r3 = r12.substring(r4, r2)
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r0)
                r0 = r3
                int r2 = r2 + 1
                r25 = r7
                goto L_0x011e
            L_0x00db:
                kotlin.TypeCastException r0 = new kotlin.TypeCastException
                r0.<init>(r3)
                throw r0
            L_0x00e1:
                r5 = r2
                r6 = r34
                okhttp3.CacheControl$Companion r6 = (okhttp3.CacheControl.Companion) r6
                r25 = r7
                java.lang.String r7 = ",;"
                int r2 = r6.indexOfElement(r12, r7, r2)
                if (r12 == 0) goto L_0x010a
                java.lang.String r3 = r12.substring(r5, r2)
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r0)
                if (r3 == 0) goto L_0x0104
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                java.lang.CharSequence r0 = kotlin.text.StringsKt.trim(r3)
                java.lang.String r0 = r0.toString()
                goto L_0x011e
            L_0x0104:
                kotlin.TypeCastException r0 = new kotlin.TypeCastException
                r0.<init>(r4)
                throw r0
            L_0x010a:
                kotlin.TypeCastException r0 = new kotlin.TypeCastException
                r0.<init>(r3)
                throw r0
            L_0x0110:
                r25 = r7
                goto L_0x0117
            L_0x0113:
                r24 = r6
                r25 = r7
            L_0x0117:
                int r2 = r2 + 1
                r0 = 0
                r3 = r0
                java.lang.String r3 = (java.lang.String) r3
                r0 = r3
            L_0x011e:
                java.lang.String r3 = "no-cache"
                r5 = 1
                boolean r3 = kotlin.text.StringsKt.equals(r3, r11, r5)
                if (r3 == 0) goto L_0x0137
                r3 = 1
                r18 = r3
                r3 = r19
                r4 = r21
                r6 = r24
                r7 = r25
                goto L_0x0222
            L_0x0137:
                java.lang.String r3 = "no-store"
                boolean r3 = kotlin.text.StringsKt.equals(r3, r11, r5)
                if (r3 == 0) goto L_0x0148
                r3 = 1
                r4 = r21
                r6 = r24
                r7 = r25
                goto L_0x0222
            L_0x0148:
                java.lang.String r3 = "max-age"
                boolean r3 = kotlin.text.StringsKt.equals(r3, r11, r5)
                r4 = -1
                if (r3 == 0) goto L_0x0161
                int r3 = okhttp3.internal.Util.toNonNegativeInt(r0, r4)
                r31 = r3
                r3 = r19
                r4 = r21
                r6 = r24
                r7 = r25
                goto L_0x0222
            L_0x0161:
                java.lang.String r3 = "s-maxage"
                boolean r3 = kotlin.text.StringsKt.equals(r3, r11, r5)
                if (r3 == 0) goto L_0x0176
                int r3 = okhttp3.internal.Util.toNonNegativeInt(r0, r4)
                r4 = r3
                r3 = r19
                r6 = r24
                r7 = r25
                goto L_0x0222
            L_0x0176:
                java.lang.String r3 = "private"
                boolean r3 = kotlin.text.StringsKt.equals(r3, r11, r5)
                if (r3 == 0) goto L_0x018b
                r3 = 1
                r23 = r3
                r3 = r19
                r4 = r21
                r6 = r24
                r7 = r25
                goto L_0x0222
            L_0x018b:
                java.lang.String r3 = "public"
                boolean r3 = kotlin.text.StringsKt.equals(r3, r11, r5)
                if (r3 == 0) goto L_0x019d
                r3 = 1
                r6 = r3
                r3 = r19
                r4 = r21
                r7 = r25
                goto L_0x0222
            L_0x019d:
                java.lang.String r3 = "must-revalidate"
                boolean r3 = kotlin.text.StringsKt.equals(r3, r11, r5)
                if (r3 == 0) goto L_0x01af
                r3 = 1
                r7 = r3
                r3 = r19
                r4 = r21
                r6 = r24
                goto L_0x0222
            L_0x01af:
                java.lang.String r3 = "max-stale"
                boolean r3 = kotlin.text.StringsKt.equals(r3, r11, r5)
                if (r3 == 0) goto L_0x01c9
                r3 = 2147483647(0x7fffffff, float:NaN)
                int r3 = okhttp3.internal.Util.toNonNegativeInt(r0, r3)
                r8 = r3
                r3 = r19
                r4 = r21
                r6 = r24
                r7 = r25
                goto L_0x0222
            L_0x01c9:
                java.lang.String r3 = "min-fresh"
                boolean r3 = kotlin.text.StringsKt.equals(r3, r11, r5)
                if (r3 == 0) goto L_0x01df
                int r3 = okhttp3.internal.Util.toNonNegativeInt(r0, r4)
                r9 = r3
                r3 = r19
                r4 = r21
                r6 = r24
                r7 = r25
                goto L_0x0222
            L_0x01df:
                java.lang.String r3 = "only-if-cached"
                boolean r3 = kotlin.text.StringsKt.equals(r3, r11, r5)
                if (r3 == 0) goto L_0x01f2
                r3 = 1
                r10 = r3
                r3 = r19
                r4 = r21
                r6 = r24
                r7 = r25
                goto L_0x0222
            L_0x01f2:
                java.lang.String r3 = "no-transform"
                boolean r3 = kotlin.text.StringsKt.equals(r3, r11, r5)
                if (r3 == 0) goto L_0x0206
                r3 = 1
                r33 = r3
                r3 = r19
                r4 = r21
                r6 = r24
                r7 = r25
                goto L_0x0222
            L_0x0206:
                java.lang.String r3 = "immutable"
                boolean r3 = kotlin.text.StringsKt.equals(r3, r11, r5)
                if (r3 == 0) goto L_0x021a
                r3 = 1
                r32 = r3
                r3 = r19
                r4 = r21
                r6 = r24
                r7 = r25
                goto L_0x0222
            L_0x021a:
                r3 = r19
                r4 = r21
                r6 = r24
                r7 = r25
            L_0x0222:
                r11 = r2
                r2 = r18
                r5 = r23
                r0 = 1
                goto L_0x004e
            L_0x022b:
                kotlin.TypeCastException r0 = new kotlin.TypeCastException
                r0.<init>(r4)
                throw r0
            L_0x0231:
                r20 = r0
                kotlin.TypeCastException r0 = new kotlin.TypeCastException
                r0.<init>(r3)
                throw r0
            L_0x0239:
                r18 = r2
                r19 = r3
                r21 = r4
                r23 = r5
                r24 = r6
                r25 = r7
                r12 = r32
                r11 = r33
                goto L_0x024e
            L_0x024a:
                r12 = r32
                r11 = r33
            L_0x024e:
                int r1 = r1 + 1
                r0 = r35
                r14 = r16
                goto L_0x0023
            L_0x0257:
                r33 = r11
                r32 = r12
                if (r13 != 0) goto L_0x0261
                r0 = 0
                r15 = r0
                java.lang.String r15 = (java.lang.String) r15
            L_0x0261:
                okhttp3.CacheControl r0 = new okhttp3.CacheControl
                r30 = 0
                r16 = r0
                r17 = r2
                r18 = r3
                r19 = r31
                r20 = r4
                r21 = r5
                r22 = r6
                r23 = r7
                r24 = r8
                r25 = r9
                r26 = r10
                r27 = r33
                r28 = r32
                r29 = r15
                r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.CacheControl.Companion.parse(okhttp3.Headers):okhttp3.CacheControl");
        }

        static /* synthetic */ int indexOfElement$default(Companion companion, String str, String str2, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return companion.indexOfElement(str, str2, i);
        }

        private final int indexOfElement(String $this$indexOfElement, String characters, int startIndex) {
            int length = $this$indexOfElement.length();
            for (int i = startIndex; i < length; i++) {
                if (StringsKt.contains$default((CharSequence) characters, $this$indexOfElement.charAt(i), false, 2, (Object) null)) {
                    return i;
                }
            }
            return $this$indexOfElement.length();
        }
    }

    @JvmStatic
    public static final CacheControl parse(Headers headers) {
        return Companion.parse(headers);
    }

    private CacheControl(boolean noCache2, boolean noStore2, int maxAgeSeconds2, int sMaxAgeSeconds2, boolean isPrivate2, boolean isPublic2, boolean mustRevalidate2, int maxStaleSeconds2, int minFreshSeconds2, boolean onlyIfCached2, boolean noTransform2, boolean immutable2, String headerValue2) {
        this.noCache = noCache2;
        this.noStore = noStore2;
        this.maxAgeSeconds = maxAgeSeconds2;
        this.sMaxAgeSeconds = sMaxAgeSeconds2;
        this.isPrivate = isPrivate2;
        this.isPublic = isPublic2;
        this.mustRevalidate = mustRevalidate2;
        this.maxStaleSeconds = maxStaleSeconds2;
        this.minFreshSeconds = minFreshSeconds2;
        this.onlyIfCached = onlyIfCached2;
        this.noTransform = noTransform2;
        this.immutable = immutable2;
        this.headerValue = headerValue2;
    }

    public /* synthetic */ CacheControl(boolean noCache2, boolean noStore2, int maxAgeSeconds2, int sMaxAgeSeconds2, boolean isPrivate2, boolean isPublic2, boolean mustRevalidate2, int maxStaleSeconds2, int minFreshSeconds2, boolean onlyIfCached2, boolean noTransform2, boolean immutable2, String headerValue2, DefaultConstructorMarker $constructor_marker) {
        this(noCache2, noStore2, maxAgeSeconds2, sMaxAgeSeconds2, isPrivate2, isPublic2, mustRevalidate2, maxStaleSeconds2, minFreshSeconds2, onlyIfCached2, noTransform2, immutable2, headerValue2);
    }

    public final boolean noCache() {
        return this.noCache;
    }

    public final boolean noStore() {
        return this.noStore;
    }

    public final int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public final int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public final boolean isPrivate() {
        return this.isPrivate;
    }

    public final boolean isPublic() {
        return this.isPublic;
    }

    public final boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public final int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public final int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public final boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public final boolean noTransform() {
        return this.noTransform;
    }

    public final boolean immutable() {
        return this.immutable;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "noCache", imports = {}))
    /* renamed from: -deprecated_noCache reason: not valid java name */
    public final boolean m2258deprecated_noCache() {
        return this.noCache;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "noStore", imports = {}))
    /* renamed from: -deprecated_noStore reason: not valid java name */
    public final boolean m2259deprecated_noStore() {
        return this.noStore;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "maxAgeSeconds", imports = {}))
    /* renamed from: -deprecated_maxAgeSeconds reason: not valid java name */
    public final int m2254deprecated_maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sMaxAgeSeconds", imports = {}))
    /* renamed from: -deprecated_sMaxAgeSeconds reason: not valid java name */
    public final int m2262deprecated_sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "mustRevalidate", imports = {}))
    /* renamed from: -deprecated_mustRevalidate reason: not valid java name */
    public final boolean m2257deprecated_mustRevalidate() {
        return this.mustRevalidate;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "maxStaleSeconds", imports = {}))
    /* renamed from: -deprecated_maxStaleSeconds reason: not valid java name */
    public final int m2255deprecated_maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "minFreshSeconds", imports = {}))
    /* renamed from: -deprecated_minFreshSeconds reason: not valid java name */
    public final int m2256deprecated_minFreshSeconds() {
        return this.minFreshSeconds;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "onlyIfCached", imports = {}))
    /* renamed from: -deprecated_onlyIfCached reason: not valid java name */
    public final boolean m2261deprecated_onlyIfCached() {
        return this.onlyIfCached;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "noTransform", imports = {}))
    /* renamed from: -deprecated_noTransform reason: not valid java name */
    public final boolean m2260deprecated_noTransform() {
        return this.noTransform;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "immutable", imports = {}))
    /* renamed from: -deprecated_immutable reason: not valid java name */
    public final boolean m2253deprecated_immutable() {
        return this.immutable;
    }

    public String toString() {
        String result = this.headerValue;
        if (result == null) {
            StringBuilder sb = new StringBuilder();
            StringBuilder $this$buildString = sb;
            if (this.noCache) {
                $this$buildString.append("no-cache, ");
            }
            if (this.noStore) {
                $this$buildString.append("no-store, ");
            }
            String str = ", ";
            if (this.maxAgeSeconds != -1) {
                $this$buildString.append("max-age=");
                $this$buildString.append(this.maxAgeSeconds);
                $this$buildString.append(str);
            }
            if (this.sMaxAgeSeconds != -1) {
                $this$buildString.append("s-maxage=");
                $this$buildString.append(this.sMaxAgeSeconds);
                $this$buildString.append(str);
            }
            if (this.isPrivate) {
                $this$buildString.append("private, ");
            }
            if (this.isPublic) {
                $this$buildString.append("public, ");
            }
            if (this.mustRevalidate) {
                $this$buildString.append("must-revalidate, ");
            }
            if (this.maxStaleSeconds != -1) {
                $this$buildString.append("max-stale=");
                $this$buildString.append(this.maxStaleSeconds);
                $this$buildString.append(str);
            }
            if (this.minFreshSeconds != -1) {
                $this$buildString.append("min-fresh=");
                $this$buildString.append(this.minFreshSeconds);
                $this$buildString.append(str);
            }
            if (this.onlyIfCached) {
                $this$buildString.append("only-if-cached, ");
            }
            if (this.noTransform) {
                $this$buildString.append("no-transform, ");
            }
            if (this.immutable) {
                $this$buildString.append("immutable, ");
            }
            if ($this$buildString.length() == 0) {
                return "";
            }
            $this$buildString.delete($this$buildString.length() - 2, $this$buildString.length());
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
            result = sb2;
            this.headerValue = result;
        }
        return result;
    }
}
