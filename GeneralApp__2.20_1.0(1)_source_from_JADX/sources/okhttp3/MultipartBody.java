package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0003\"#$B%\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\r\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0002\b\u0015J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0005H\u0016J\u000e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0012J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\b\u0018J\r\u0010\u0011\u001a\u00020\u0012H\u0007¢\u0006\u0002\b\u0019J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\b\u001aJ\u001a\u0010\u001b\u001a\u00020\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u0011\u0010\n\u001a\u00020\u000b8G¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013R\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0014¨\u0006%"}, mo24952d2 = {"Lokhttp3/MultipartBody;", "Lokhttp3/RequestBody;", "boundaryByteString", "Lokio/ByteString;", "type", "Lokhttp3/MediaType;", "parts", "", "Lokhttp3/MultipartBody$Part;", "(Lokio/ByteString;Lokhttp3/MediaType;Ljava/util/List;)V", "boundary", "", "()Ljava/lang/String;", "contentLength", "", "contentType", "()Ljava/util/List;", "size", "", "()I", "()Lokhttp3/MediaType;", "-deprecated_boundary", "part", "index", "-deprecated_parts", "-deprecated_size", "-deprecated_type", "writeOrCountBytes", "sink", "Lokio/BufferedSink;", "countBytes", "", "writeTo", "", "Builder", "Companion", "Part", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: MultipartBody.kt */
public final class MultipartBody extends RequestBody {
    public static final MediaType ALTERNATIVE = MediaType.Companion.get("multipart/alternative");
    private static final byte[] COLONSPACE = {(byte) 58, (byte) 32};
    private static final byte[] CRLF = {(byte) 13, (byte) 10};
    public static final Companion Companion = new Companion(null);
    private static final byte[] DASHDASH;
    public static final MediaType DIGEST = MediaType.Companion.get("multipart/digest");
    public static final MediaType FORM = MediaType.Companion.get("multipart/form-data");
    public static final MediaType MIXED = MediaType.Companion.get("multipart/mixed");
    public static final MediaType PARALLEL = MediaType.Companion.get("multipart/parallel");
    private final ByteString boundaryByteString;
    private long contentLength = -1;
    private final MediaType contentType;
    private final List<Part> parts;
    private final MediaType type;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003J \u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\u0011\u001a\u00020\u00002\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, mo24952d2 = {"Lokhttp3/MultipartBody$Builder;", "", "boundary", "", "(Ljava/lang/String;)V", "Lokio/ByteString;", "parts", "", "Lokhttp3/MultipartBody$Part;", "type", "Lokhttp3/MediaType;", "addFormDataPart", "name", "value", "filename", "body", "Lokhttp3/RequestBody;", "addPart", "headers", "Lokhttp3/Headers;", "part", "build", "Lokhttp3/MultipartBody;", "setType", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: MultipartBody.kt */
    public static final class Builder {
        private final ByteString boundary;
        private final List<Part> parts;
        private MediaType type;

        public Builder() {
            this(null, 1, null);
        }

        public Builder(String boundary2) {
            Intrinsics.checkParameterIsNotNull(boundary2, "boundary");
            this.boundary = ByteString.Companion.encodeUtf8(boundary2);
            this.type = MultipartBody.MIXED;
            this.parts = new ArrayList();
        }

        public /* synthetic */ Builder(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                str = UUID.randomUUID().toString();
                Intrinsics.checkExpressionValueIsNotNull(str, "UUID.randomUUID().toString()");
            }
            this(str);
        }

        public final Builder setType(MediaType type2) {
            Intrinsics.checkParameterIsNotNull(type2, "type");
            Builder $this$apply = this;
            if (Intrinsics.areEqual((Object) type2.type(), (Object) "multipart")) {
                $this$apply.type = type2;
                return this;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("multipart != ");
            sb.append(type2);
            throw new IllegalArgumentException(sb.toString().toString());
        }

        public final Builder addPart(RequestBody body) {
            Intrinsics.checkParameterIsNotNull(body, "body");
            addPart(Part.Companion.create(body));
            return this;
        }

        public final Builder addPart(Headers headers, RequestBody body) {
            Intrinsics.checkParameterIsNotNull(body, "body");
            addPart(Part.Companion.create(headers, body));
            return this;
        }

        public final Builder addFormDataPart(String name, String value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            addPart(Part.Companion.createFormData(name, value));
            return this;
        }

        public final Builder addFormDataPart(String name, String filename, RequestBody body) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(body, "body");
            addPart(Part.Companion.createFormData(name, filename, body));
            return this;
        }

        public final Builder addPart(Part part) {
            Intrinsics.checkParameterIsNotNull(part, "part");
            this.parts.add(part);
            return this;
        }

        public final MultipartBody build() {
            if (!this.parts.isEmpty()) {
                return new MultipartBody(this.boundary, this.type, Util.toImmutableList(this.parts));
            }
            throw new IllegalStateException("Multipart body must have at least one part.".toString());
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\r\u001a\u00020\u000e*\u00060\u000fj\u0002`\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo24952d2 = {"Lokhttp3/MultipartBody$Companion;", "", "()V", "ALTERNATIVE", "Lokhttp3/MediaType;", "COLONSPACE", "", "CRLF", "DASHDASH", "DIGEST", "FORM", "MIXED", "PARALLEL", "appendQuotedString", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "key", "", "appendQuotedString$okhttp", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: MultipartBody.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final void appendQuotedString$okhttp(StringBuilder $this$appendQuotedString, String key) {
            Intrinsics.checkParameterIsNotNull($this$appendQuotedString, "$this$appendQuotedString");
            Intrinsics.checkParameterIsNotNull(key, "key");
            $this$appendQuotedString.append(Typography.quote);
            int length = key.length();
            for (int i = 0; i < length; i++) {
                char ch = key.charAt(i);
                if (ch == 10) {
                    $this$appendQuotedString.append("%0A");
                } else if (ch == 13) {
                    $this$appendQuotedString.append("%0D");
                } else if (ch == '\"') {
                    $this$appendQuotedString.append("%22");
                } else {
                    $this$appendQuotedString.append(ch);
                }
            }
            $this$appendQuotedString.append(Typography.quote);
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0019\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\b\tJ\u000f\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b\nR\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\b¨\u0006\f"}, mo24952d2 = {"Lokhttp3/MultipartBody$Part;", "", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/RequestBody;", "(Lokhttp3/Headers;Lokhttp3/RequestBody;)V", "()Lokhttp3/RequestBody;", "()Lokhttp3/Headers;", "-deprecated_body", "-deprecated_headers", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: MultipartBody.kt */
    public static final class Part {
        public static final Companion Companion = new Companion(null);
        private final RequestBody body;
        private final Headers headers;

        @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0007J\"\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\u000e"}, mo24952d2 = {"Lokhttp3/MultipartBody$Part$Companion;", "", "()V", "create", "Lokhttp3/MultipartBody$Part;", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/RequestBody;", "createFormData", "name", "", "value", "filename", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
        /* compiled from: MultipartBody.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            @JvmStatic
            public final Part create(RequestBody body) {
                Intrinsics.checkParameterIsNotNull(body, "body");
                return create(null, body);
            }

            @JvmStatic
            public final Part create(Headers headers, RequestBody body) {
                Intrinsics.checkParameterIsNotNull(body, "body");
                boolean z = true;
                if ((headers != null ? headers.get("Content-Type") : null) == null) {
                    if ((headers != null ? headers.get("Content-Length") : null) != null) {
                        z = false;
                    }
                    if (z) {
                        return new Part(headers, body, null);
                    }
                    throw new IllegalArgumentException("Unexpected header: Content-Length".toString());
                }
                throw new IllegalArgumentException("Unexpected header: Content-Type".toString());
            }

            @JvmStatic
            public final Part createFormData(String name, String value) {
                Intrinsics.checkParameterIsNotNull(name, "name");
                Intrinsics.checkParameterIsNotNull(value, "value");
                return createFormData(name, null, okhttp3.RequestBody.Companion.create$default(RequestBody.Companion, value, (MediaType) null, 1, (Object) null));
            }

            @JvmStatic
            public final Part createFormData(String name, String filename, RequestBody body) {
                Intrinsics.checkParameterIsNotNull(name, "name");
                Intrinsics.checkParameterIsNotNull(body, "body");
                StringBuilder sb = new StringBuilder();
                StringBuilder $this$buildString = sb;
                $this$buildString.append("form-data; name=");
                MultipartBody.Companion.appendQuotedString$okhttp($this$buildString, name);
                if (filename != null) {
                    $this$buildString.append("; filename=");
                    MultipartBody.Companion.appendQuotedString$okhttp($this$buildString, filename);
                }
                String disposition = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(disposition, "StringBuilder().apply(builderAction).toString()");
                return create(new okhttp3.Headers.Builder().addUnsafeNonAscii("Content-Disposition", disposition).build(), body);
            }
        }

        @JvmStatic
        public static final Part create(Headers headers2, RequestBody requestBody) {
            return Companion.create(headers2, requestBody);
        }

        @JvmStatic
        public static final Part create(RequestBody requestBody) {
            return Companion.create(requestBody);
        }

        @JvmStatic
        public static final Part createFormData(String str, String str2) {
            return Companion.createFormData(str, str2);
        }

        @JvmStatic
        public static final Part createFormData(String str, String str2, RequestBody requestBody) {
            return Companion.createFormData(str, str2, requestBody);
        }

        private Part(Headers headers2, RequestBody body2) {
            this.headers = headers2;
            this.body = body2;
        }

        public /* synthetic */ Part(Headers headers2, RequestBody body2, DefaultConstructorMarker $constructor_marker) {
            this(headers2, body2);
        }

        public final Headers headers() {
            return this.headers;
        }

        public final RequestBody body() {
            return this.body;
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "headers", imports = {}))
        /* renamed from: -deprecated_headers reason: not valid java name */
        public final Headers m2324deprecated_headers() {
            return this.headers;
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "body", imports = {}))
        /* renamed from: -deprecated_body reason: not valid java name */
        public final RequestBody m2323deprecated_body() {
            return this.body;
        }
    }

    public MultipartBody(ByteString boundaryByteString2, MediaType type2, List<Part> parts2) {
        Intrinsics.checkParameterIsNotNull(boundaryByteString2, "boundaryByteString");
        Intrinsics.checkParameterIsNotNull(type2, "type");
        Intrinsics.checkParameterIsNotNull(parts2, "parts");
        this.boundaryByteString = boundaryByteString2;
        this.type = type2;
        this.parts = parts2;
        okhttp3.MediaType.Companion companion = MediaType.Companion;
        StringBuilder sb = new StringBuilder();
        sb.append(this.type);
        sb.append("; boundary=");
        sb.append(boundary());
        this.contentType = companion.get(sb.toString());
    }

    public final MediaType type() {
        return this.type;
    }

    public final List<Part> parts() {
        return this.parts;
    }

    public final String boundary() {
        return this.boundaryByteString.utf8();
    }

    public final int size() {
        return this.parts.size();
    }

    public final Part part(int index) {
        return (Part) this.parts.get(index);
    }

    public MediaType contentType() {
        return this.contentType;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "type", imports = {}))
    /* renamed from: -deprecated_type reason: not valid java name */
    public final MediaType m2322deprecated_type() {
        return this.type;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "boundary", imports = {}))
    /* renamed from: -deprecated_boundary reason: not valid java name */
    public final String m2319deprecated_boundary() {
        return boundary();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    /* renamed from: -deprecated_size reason: not valid java name */
    public final int m2321deprecated_size() {
        return size();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "parts", imports = {}))
    /* renamed from: -deprecated_parts reason: not valid java name */
    public final List<Part> m2320deprecated_parts() {
        return this.parts;
    }

    public long contentLength() throws IOException {
        long result = this.contentLength;
        if (result != -1) {
            return result;
        }
        long result2 = writeOrCountBytes(null, true);
        this.contentLength = result2;
        return result2;
    }

    public void writeTo(BufferedSink sink) throws IOException {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        writeOrCountBytes(sink, false);
    }

    private final long writeOrCountBytes(BufferedSink sink, boolean countBytes) throws IOException {
        BufferedSink sink2 = sink;
        Buffer byteCountBuffer = null;
        if (countBytes) {
            byteCountBuffer = new Buffer();
            sink2 = byteCountBuffer;
        }
        int size = this.parts.size();
        long byteCount = 0;
        for (int p = 0; p < size; p++) {
            Part part = (Part) this.parts.get(p);
            Headers headers = part.headers();
            RequestBody body = part.body();
            if (sink2 == null) {
                Intrinsics.throwNpe();
            }
            sink2.write(DASHDASH);
            sink2.write(this.boundaryByteString);
            sink2.write(CRLF);
            if (headers != null) {
                int size2 = headers.size();
                for (int h = 0; h < size2; h++) {
                    sink2.writeUtf8(headers.name(h)).write(COLONSPACE).writeUtf8(headers.value(h)).write(CRLF);
                }
            }
            MediaType contentType2 = body.contentType();
            if (contentType2 != null) {
                sink2.writeUtf8("Content-Type: ").writeUtf8(contentType2.toString()).write(CRLF);
            }
            long contentLength2 = body.contentLength();
            if (contentLength2 != -1) {
                sink2.writeUtf8("Content-Length: ").writeDecimalLong(contentLength2).write(CRLF);
            } else if (countBytes) {
                if (byteCountBuffer == null) {
                    Intrinsics.throwNpe();
                }
                byteCountBuffer.clear();
                return -1;
            }
            sink2.write(CRLF);
            if (countBytes) {
                byteCount += contentLength2;
            } else {
                body.writeTo(sink2);
            }
            sink2.write(CRLF);
        }
        if (sink2 == null) {
            Intrinsics.throwNpe();
        }
        sink2.write(DASHDASH);
        sink2.write(this.boundaryByteString);
        sink2.write(DASHDASH);
        sink2.write(CRLF);
        if (countBytes) {
            if (byteCountBuffer == null) {
                Intrinsics.throwNpe();
            }
            byteCount += byteCountBuffer.size();
            byteCountBuffer.clear();
        }
        return byteCount;
    }

    static {
        byte b = (byte) 45;
        DASHDASH = new byte[]{b, b};
    }
}
