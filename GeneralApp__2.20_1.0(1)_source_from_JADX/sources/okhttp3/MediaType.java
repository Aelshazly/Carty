package okhttp3;

import java.nio.charset.Charset;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B)\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0016\u0010\u0006\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tH\u0007J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\r\u0010\u0005\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0012R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\bR\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\b¨\u0006\u0014"}, mo24952d2 = {"Lokhttp3/MediaType;", "", "mediaType", "", "type", "subtype", "charset", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "()Ljava/lang/String;", "Ljava/nio/charset/Charset;", "defaultValue", "equals", "", "other", "hashCode", "", "-deprecated_subtype", "toString", "-deprecated_type", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: MediaType.kt */
public final class MediaType {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final Pattern PARAMETER = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    private static final String QUOTED = "\"([^\"]*)\"";
    private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    /* access modifiers changed from: private */
    public static final Pattern TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    private final String charset;
    private final String mediaType;
    private final String subtype;
    private final String type;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0007¢\u0006\u0002\b\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0007¢\u0006\u0002\b\u000fJ\u0011\u0010\u0010\u001a\u00020\u000b*\u00020\u0007H\u0007¢\u0006\u0002\b\nJ\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u000b*\u00020\u0007H\u0007¢\u0006\u0002\b\u000eR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo24952d2 = {"Lokhttp3/MediaType$Companion;", "", "()V", "PARAMETER", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "QUOTED", "", "TOKEN", "TYPE_SUBTYPE", "get", "Lokhttp3/MediaType;", "mediaType", "-deprecated_get", "parse", "-deprecated_parse", "toMediaType", "toMediaTypeOrNull", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: MediaType.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x00cb, code lost:
            if (kotlin.text.StringsKt.equals(r0, r11, true) != false) goto L_0x00cf;
         */
        @kotlin.jvm.JvmStatic
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final okhttp3.MediaType get(java.lang.String r17) {
            /*
                r16 = this;
                r6 = r17
                java.lang.String r0 = "$this$toMediaType"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
                java.util.regex.Pattern r0 = okhttp3.MediaType.TYPE_SUBTYPE
                r1 = r6
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                java.util.regex.Matcher r7 = r0.matcher(r1)
                boolean r0 = r7.lookingAt()
                if (r0 == 0) goto L_0x015b
                r0 = 1
                java.lang.String r2 = r7.group(r0)
                java.lang.String r3 = "typeSubtype.group(1)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
                java.util.Locale r3 = java.util.Locale.US
                java.lang.String r4 = "Locale.US"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
                java.lang.String r5 = "null cannot be cast to non-null type java.lang.String"
                if (r2 == 0) goto L_0x0155
                java.lang.String r2 = r2.toLowerCase(r3)
                java.lang.String r3 = "(this as java.lang.String).toLowerCase(locale)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
                r8 = 2
                java.lang.String r9 = r7.group(r8)
                java.lang.String r10 = "typeSubtype.group(2)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r10)
                java.util.Locale r10 = java.util.Locale.US
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r4)
                if (r9 == 0) goto L_0x014f
                java.lang.String r4 = r9.toLowerCase(r10)
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r3)
                r3 = r4
                r4 = 0
                r5 = r4
                java.lang.String r5 = (java.lang.String) r5
                java.util.regex.Pattern r9 = okhttp3.MediaType.PARAMETER
                r10 = r6
                java.lang.CharSequence r10 = (java.lang.CharSequence) r10
                java.util.regex.Matcher r9 = r9.matcher(r10)
                int r10 = r7.end()
                r11 = r5
            L_0x0063:
                int r5 = r17.length()
                if (r10 >= r5) goto L_0x0144
                int r5 = r17.length()
                r9.region(r10, r5)
                boolean r5 = r9.lookingAt()
                java.lang.String r12 = "\" for: \""
                if (r5 == 0) goto L_0x0112
                java.lang.String r5 = r9.group(r0)
                if (r5 == 0) goto L_0x010a
                java.lang.String r13 = "charset"
                boolean r13 = kotlin.text.StringsKt.equals(r5, r13, r0)
                if (r13 != 0) goto L_0x0089
                r1 = 1
                goto L_0x010b
            L_0x0089:
                r13 = 0
                java.lang.String r14 = r9.group(r8)
                r15 = 0
                if (r14 != 0) goto L_0x009e
                r1 = 3
                java.lang.String r1 = r9.group(r1)
                java.lang.String r0 = "parameter.group(3)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r0)
                r0 = r1
                goto L_0x00c3
            L_0x009e:
                java.lang.String r0 = "'"
                boolean r1 = kotlin.text.StringsKt.startsWith$default(r14, r0, r15, r8, r4)
                if (r1 == 0) goto L_0x00c2
                boolean r0 = kotlin.text.StringsKt.endsWith$default(r14, r0, r15, r8, r4)
                if (r0 == 0) goto L_0x00c2
                int r0 = r14.length()
                if (r0 <= r8) goto L_0x00c2
                int r0 = r14.length()
                r1 = 1
                int r0 = r0 - r1
                java.lang.String r0 = r14.substring(r1, r0)
                java.lang.String r1 = "(this as java.lang.Strin…ing(startIndex, endIndex)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
                goto L_0x00c3
            L_0x00c2:
                r0 = r14
            L_0x00c3:
                if (r11 == 0) goto L_0x00ce
                r1 = 1
                boolean r13 = kotlin.text.StringsKt.equals(r0, r11, r1)
                if (r13 == 0) goto L_0x00d0
                goto L_0x00cf
            L_0x00ce:
                r1 = 1
            L_0x00cf:
                r15 = 1
            L_0x00d0:
                if (r15 == 0) goto L_0x00d9
                r11 = r0
                int r10 = r9.end()
                r0 = 1
                goto L_0x0063
            L_0x00d9:
                r1 = 0
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r8 = "Multiple charsets defined: \""
                r4.append(r8)
                r4.append(r11)
                java.lang.String r8 = "\" and: \""
                r4.append(r8)
                r4.append(r0)
                r4.append(r12)
                r4.append(r6)
                r8 = 34
                r4.append(r8)
                java.lang.String r1 = r4.toString()
                java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
                java.lang.String r1 = r1.toString()
                r4.<init>(r1)
                java.lang.Throwable r4 = (java.lang.Throwable) r4
                throw r4
            L_0x010a:
                r1 = 1
            L_0x010b:
                int r10 = r9.end()
                r0 = 1
                goto L_0x0063
            L_0x0112:
                r0 = 0
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r4 = "Parameter is not formatted correctly: \""
                r1.append(r4)
                java.lang.String r4 = r6.substring(r10)
                java.lang.String r5 = "(this as java.lang.String).substring(startIndex)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
                r1.append(r4)
                r1.append(r12)
                r1.append(r6)
                r4 = 34
                r1.append(r4)
                java.lang.String r0 = r1.toString()
                java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                java.lang.Throwable r1 = (java.lang.Throwable) r1
                throw r1
            L_0x0144:
                okhttp3.MediaType r8 = new okhttp3.MediaType
                r5 = 0
                r0 = r8
                r1 = r17
                r4 = r11
                r0.<init>(r1, r2, r3, r4, r5)
                return r8
            L_0x014f:
                kotlin.TypeCastException r0 = new kotlin.TypeCastException
                r0.<init>(r5)
                throw r0
            L_0x0155:
                kotlin.TypeCastException r0 = new kotlin.TypeCastException
                r0.<init>(r5)
                throw r0
            L_0x015b:
                r0 = 0
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "No subtype found for: \""
                r1.append(r2)
                r1.append(r6)
                r2 = 34
                r1.append(r2)
                java.lang.String r0 = r1.toString()
                java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                java.lang.Throwable r1 = (java.lang.Throwable) r1
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.MediaType.Companion.get(java.lang.String):okhttp3.MediaType");
        }

        @JvmStatic
        public final MediaType parse(String $this$toMediaTypeOrNull) {
            Intrinsics.checkParameterIsNotNull($this$toMediaTypeOrNull, "$this$toMediaTypeOrNull");
            try {
                return get($this$toMediaTypeOrNull);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "mediaType.toMediaType()", imports = {"okhttp3.MediaType.Companion.toMediaType"}))
        /* renamed from: -deprecated_get reason: not valid java name */
        public final MediaType m2317deprecated_get(String mediaType) {
            Intrinsics.checkParameterIsNotNull(mediaType, "mediaType");
            return get(mediaType);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "mediaType.toMediaTypeOrNull()", imports = {"okhttp3.MediaType.Companion.toMediaTypeOrNull"}))
        /* renamed from: -deprecated_parse reason: not valid java name */
        public final MediaType m2318deprecated_parse(String mediaType) {
            Intrinsics.checkParameterIsNotNull(mediaType, "mediaType");
            return parse(mediaType);
        }
    }

    @JvmStatic
    public static final MediaType get(String str) {
        return Companion.get(str);
    }

    @JvmStatic
    public static final MediaType parse(String str) {
        return Companion.parse(str);
    }

    public final Charset charset() {
        return charset$default(this, null, 1, null);
    }

    private MediaType(String mediaType2, String type2, String subtype2, String charset2) {
        this.mediaType = mediaType2;
        this.type = type2;
        this.subtype = subtype2;
        this.charset = charset2;
    }

    public /* synthetic */ MediaType(String mediaType2, String type2, String subtype2, String charset2, DefaultConstructorMarker $constructor_marker) {
        this(mediaType2, type2, subtype2, charset2);
    }

    public final String type() {
        return this.type;
    }

    public final String subtype() {
        return this.subtype;
    }

    public static /* synthetic */ Charset charset$default(MediaType mediaType2, Charset charset2, int i, Object obj) {
        if ((i & 1) != 0) {
            charset2 = null;
        }
        return mediaType2.charset(charset2);
    }

    public final Charset charset(Charset defaultValue) {
        try {
            return this.charset != null ? Charset.forName(this.charset) : defaultValue;
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "type", imports = {}))
    /* renamed from: -deprecated_type reason: not valid java name */
    public final String m2316deprecated_type() {
        return this.type;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "subtype", imports = {}))
    /* renamed from: -deprecated_subtype reason: not valid java name */
    public final String m2315deprecated_subtype() {
        return this.subtype;
    }

    public String toString() {
        return this.mediaType;
    }

    public boolean equals(Object other) {
        return (other instanceof MediaType) && Intrinsics.areEqual((Object) ((MediaType) other).mediaType, (Object) this.mediaType);
    }

    public int hashCode() {
        return this.mediaType.hashCode();
    }
}
