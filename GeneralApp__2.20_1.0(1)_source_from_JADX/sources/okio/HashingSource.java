package okio;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0017\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\r\u0010\n\u001a\u00020\bH\u0007¢\u0006\u0002\b\u0010J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0016R\u0011\u0010\n\u001a\u00020\b8G¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, mo24952d2 = {"Lokio/HashingSource;", "Lokio/ForwardingSource;", "source", "Lokio/Source;", "algorithm", "", "(Lokio/Source;Ljava/lang/String;)V", "key", "Lokio/ByteString;", "(Lokio/Source;Lokio/ByteString;Ljava/lang/String;)V", "hash", "()Lokio/ByteString;", "mac", "Ljavax/crypto/Mac;", "messageDigest", "Ljava/security/MessageDigest;", "-deprecated_hash", "read", "", "sink", "Lokio/Buffer;", "byteCount", "Companion", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: HashingSource.kt */
public final class HashingSource extends ForwardingSource {
    public static final Companion Companion = new Companion(null);
    private final Mac mac;
    private final MessageDigest messageDigest;

    @Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u000f"}, mo24952d2 = {"Lokio/HashingSource$Companion;", "", "()V", "hmacSha1", "Lokio/HashingSource;", "source", "Lokio/Source;", "key", "Lokio/ByteString;", "hmacSha256", "hmacSha512", "md5", "sha1", "sha256", "sha512", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
    /* compiled from: HashingSource.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @JvmStatic
        public final HashingSource md5(Source source) {
            Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
            return new HashingSource(source, "MD5");
        }

        @JvmStatic
        public final HashingSource sha1(Source source) {
            Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
            return new HashingSource(source, "SHA-1");
        }

        @JvmStatic
        public final HashingSource sha256(Source source) {
            Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
            return new HashingSource(source, "SHA-256");
        }

        @JvmStatic
        public final HashingSource sha512(Source source) {
            Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
            return new HashingSource(source, "SHA-512");
        }

        @JvmStatic
        public final HashingSource hmacSha1(Source source, ByteString key) {
            Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
            Intrinsics.checkParameterIsNotNull(key, "key");
            return new HashingSource(source, key, "HmacSHA1");
        }

        @JvmStatic
        public final HashingSource hmacSha256(Source source, ByteString key) {
            Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
            Intrinsics.checkParameterIsNotNull(key, "key");
            return new HashingSource(source, key, "HmacSHA256");
        }

        @JvmStatic
        public final HashingSource hmacSha512(Source source, ByteString key) {
            Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
            Intrinsics.checkParameterIsNotNull(key, "key");
            return new HashingSource(source, key, "HmacSHA512");
        }
    }

    @JvmStatic
    public static final HashingSource hmacSha1(Source source, ByteString byteString) {
        return Companion.hmacSha1(source, byteString);
    }

    @JvmStatic
    public static final HashingSource hmacSha256(Source source, ByteString byteString) {
        return Companion.hmacSha256(source, byteString);
    }

    @JvmStatic
    public static final HashingSource hmacSha512(Source source, ByteString byteString) {
        return Companion.hmacSha512(source, byteString);
    }

    @JvmStatic
    public static final HashingSource md5(Source source) {
        return Companion.md5(source);
    }

    @JvmStatic
    public static final HashingSource sha1(Source source) {
        return Companion.sha1(source);
    }

    @JvmStatic
    public static final HashingSource sha256(Source source) {
        return Companion.sha256(source);
    }

    @JvmStatic
    public static final HashingSource sha512(Source source) {
        return Companion.sha512(source);
    }

    public HashingSource(Source source, String algorithm) {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        Intrinsics.checkParameterIsNotNull(algorithm, "algorithm");
        super(source);
        this.messageDigest = MessageDigest.getInstance(algorithm);
        this.mac = null;
    }

    public HashingSource(Source source, ByteString key, String algorithm) {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(algorithm, "algorithm");
        super(source);
        try {
            Mac instance = Mac.getInstance(algorithm);
            instance.init(new SecretKeySpec(key.toByteArray(), algorithm));
            this.mac = instance;
            this.messageDigest = null;
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public long read(Buffer sink, long byteCount) throws IOException {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        long result = super.read(sink, byteCount);
        if (result != -1) {
            long start = sink.size() - result;
            long offset = sink.size();
            Segment s = sink.head;
            if (s == null) {
                Intrinsics.throwNpe();
            }
            while (offset > start) {
                Segment segment = s.prev;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                s = segment;
                offset -= (long) (s.limit - s.pos);
            }
            while (offset < sink.size()) {
                int pos = (int) ((((long) s.pos) + start) - offset);
                MessageDigest messageDigest2 = this.messageDigest;
                if (messageDigest2 != null) {
                    messageDigest2.update(s.data, pos, s.limit - pos);
                } else {
                    Mac mac2 = this.mac;
                    if (mac2 == null) {
                        Intrinsics.throwNpe();
                    }
                    mac2.update(s.data, pos, s.limit - pos);
                }
                offset += (long) (s.limit - s.pos);
                start = offset;
                Segment segment2 = s.next;
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
                s = segment2;
            }
        }
        return result;
    }

    public final ByteString hash() {
        byte[] result;
        MessageDigest messageDigest2 = this.messageDigest;
        if (messageDigest2 != null) {
            result = messageDigest2.digest();
        } else {
            Mac mac2 = this.mac;
            if (mac2 == null) {
                Intrinsics.throwNpe();
            }
            result = mac2.doFinal();
        }
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return new ByteString(result);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "hash", imports = {}))
    /* renamed from: -deprecated_hash reason: not valid java name */
    public final ByteString m2391deprecated_hash() {
        return hash();
    }
}
