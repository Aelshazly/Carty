package okio;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.Typography;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u0000 \u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0004\u0001\u0001B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0000H\u0016J\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\u0000H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0016J\u0006\u0010\u0015\u001a\u00020\fJ$\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\f2\b\b\u0002\u0010\u001a\u001a\u00020\fH\u0007J\"\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0019\u001a\u00020\f2\b\b\u0002\u0010\u001a\u001a\u00020\fJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0000H\u0016J\b\u0010 \u001a\u00020\u0000H\u0016J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J\b\u0010%\u001a\u00020\"H\u0016J\b\u0010&\u001a\u00020\u0012H\u0016J\u0016\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\fH\u0002¢\u0006\u0002\b*J\u0015\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020\fH\u0007¢\u0006\u0002\b,J\b\u0010-\u001a\u00020.H\u0016J\u0018\u0010/\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u00100\u001a\u00020\u001cH\u0002J\u000e\u00101\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u001cJ\u000e\u00102\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u001cJ\u000e\u00103\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u001cJ\u0010\u00104\u001a\u00020\f2\u0006\u00105\u001a\u00020(H\u0016J\u0018\u00104\u001a\u00020\f2\u0006\u00105\u001a\u00020(2\u0006\u00106\u001a\u00020\fH\u0016J \u00104\u001a\u00020\f2\u0006\u00105\u001a\u00020(2\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0016J\u0010\u00104\u001a\u00020\f2\u0006\u00108\u001a\u00020\u001cH\u0016J\u0018\u00104\u001a\u00020\f2\u0006\u00108\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\fH\u0016J\u0010\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u00020\u001cH\u0016J\u0018\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\fH\u0016J\b\u0010;\u001a\u00020<H\u0016J\b\u0010=\u001a\u00020\"H\u0016J\u0006\u0010>\u001a\u00020\u001cJ\b\u0010?\u001a\u00020\u0018H\u0016J\b\u0010@\u001a\u00020\u0001H\u0016J\u0018\u0010A\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u00108\u001a\u00020\u001cH\u0016J(\u0010A\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u00108\u001a\u00020\u001c2\u0006\u0010B\u001a\u00020.2\u0006\u0010\u001a\u001a\u00020.H\u0016J0\u0010A\u001a\u00020\"2\u0006\u0010C\u001a\u00020\n2\u0006\u0010D\u001a\u00020.2\u0006\u00108\u001a\u00020E2\u0006\u0010B\u001a\u00020.2\u0006\u0010F\u001a\u00020.H\u0002J\u0010\u0010G\u001a\u00020.2\u0006\u0010H\u001a\u00020IH\u0016J\u0010\u0010G\u001a\u00020.2\u0006\u0010H\u001a\u00020EH\u0016J \u0010G\u001a\u00020.2\u0006\u0010H\u001a\u00020E2\u0006\u0010\u0019\u001a\u00020.2\u0006\u0010\u001a\u001a\u00020.H\u0016J\u0018\u0010G\u001a\u00020\f2\u0006\u0010H\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u0010\u0010J\u001a\u00020\f2\u0006\u0010H\u001a\u00020KH\u0016J\u0012\u0010L\u001a\u00020M2\b\b\u0002\u0010N\u001a\u00020MH\u0007J\b\u0010O\u001a\u00020(H\u0016J\b\u0010P\u001a\u00020EH\u0016J\u0010\u0010P\u001a\u00020E2\u0006\u0010\u001a\u001a\u00020\fH\u0016J\b\u0010Q\u001a\u00020\u001cH\u0016J\u0010\u0010Q\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\fH\u0016J\b\u0010R\u001a\u00020\fH\u0016J\u000e\u0010S\u001a\u00020\u00002\u0006\u0010T\u001a\u00020<J\u0016\u0010S\u001a\u00020\u00002\u0006\u0010T\u001a\u00020<2\u0006\u0010\u001a\u001a\u00020\fJ \u0010S\u001a\u00020\u00122\u0006\u0010T\u001a\u00020<2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010U\u001a\u00020\"H\u0002J\u0010\u0010V\u001a\u00020\u00122\u0006\u0010H\u001a\u00020EH\u0016J\u0018\u0010V\u001a\u00020\u00122\u0006\u0010H\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\fH\u0016J\b\u0010W\u001a\u00020\fH\u0016J\b\u0010X\u001a\u00020.H\u0016J\b\u0010Y\u001a\u00020.H\u0016J\b\u0010Z\u001a\u00020\fH\u0016J\b\u0010[\u001a\u00020\fH\u0016J\b\u0010\\\u001a\u00020]H\u0016J\b\u0010^\u001a\u00020]H\u0016J\u0010\u0010_\u001a\u00020\u001e2\u0006\u0010`\u001a\u00020aH\u0016J\u0018\u0010_\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010`\u001a\u00020aH\u0016J\u0012\u0010b\u001a\u00020M2\b\b\u0002\u0010N\u001a\u00020MH\u0007J\b\u0010c\u001a\u00020\u001eH\u0016J\u0010\u0010c\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\fH\u0016J\b\u0010d\u001a\u00020.H\u0016J\n\u0010e\u001a\u0004\u0018\u00010\u001eH\u0016J\u0015\u0010e\u001a\u00020\u001e2\u0006\u0010f\u001a\u00020\fH\u0000¢\u0006\u0002\bgJ\b\u0010h\u001a\u00020\u001eH\u0016J\u0010\u0010h\u001a\u00020\u001e2\u0006\u0010i\u001a\u00020\fH\u0016J\u0010\u0010j\u001a\u00020\"2\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u0010\u0010k\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\fH\u0016J8\u0010l\u001a\u0002Hm\"\u0004\b\u0000\u0010m2\u0006\u00106\u001a\u00020\f2\u001a\u0010n\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002Hm0oH\b¢\u0006\u0002\u0010pJ\u0010\u0010q\u001a\u00020.2\u0006\u0010r\u001a\u00020sH\u0016J\u001f\u0010t\u001a\u00020.2\u0006\u0010r\u001a\u00020s2\b\b\u0002\u0010u\u001a\u00020\"H\u0000¢\u0006\u0002\bvJ\u0006\u0010w\u001a\u00020\u001cJ\u0006\u0010x\u001a\u00020\u001cJ\u0006\u0010y\u001a\u00020\u001cJ\r\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0002\bzJ\u0010\u0010{\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u0006\u0010|\u001a\u00020\u001cJ\u000e\u0010|\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020.J\b\u0010}\u001a\u00020~H\u0016J\b\u0010\u001a\u00020\u001eH\u0016J\u0018\u0010\u0001\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020.H\u0000¢\u0006\u0003\b\u0001J\u0012\u0010\u0001\u001a\u00020.2\u0007\u0010\u0001\u001a\u00020IH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020EH\u0016J\"\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020E2\u0006\u0010\u0019\u001a\u00020.2\u0006\u0010\u001a\u001a\u00020.H\u0016J\u001a\u0010\u0001\u001a\u00020\u00122\u0007\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001cH\u0016J\u001b\u0010\u0001\u001a\u00020\u00022\b\u0010\u0001\u001a\u00030\u00012\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u0013\u0010\u0001\u001a\u00020\f2\b\u0010\u0001\u001a\u00030\u0001H\u0016J\u0011\u0010\u0001\u001a\u00020\u00002\u0006\u00105\u001a\u00020.H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\fH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\fH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020.H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020.H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\fH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\fH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020.H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020.H\u0016J\u001a\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001e2\u0006\u0010`\u001a\u00020aH\u0016J,\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001e2\u0007\u0010\u0001\u001a\u00020.2\u0007\u0010\u0001\u001a\u00020.2\u0006\u0010`\u001a\u00020aH\u0016J\u001b\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\fH\u0007J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001eH\u0016J$\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001e2\u0007\u0010\u0001\u001a\u00020.2\u0007\u0010\u0001\u001a\u00020.H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020.H\u0016R\u0014\u0010\u0006\u001a\u00020\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\n8\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R&\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8\u0007@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0001"}, mo24952d2 = {"Lokio/Buffer;", "Lokio/BufferedSource;", "Lokio/BufferedSink;", "", "Ljava/nio/channels/ByteChannel;", "()V", "buffer", "getBuffer", "()Lokio/Buffer;", "head", "Lokio/Segment;", "<set-?>", "", "size", "()J", "setSize$jvm", "(J)V", "clear", "", "clone", "close", "completeSegmentByteCount", "copyTo", "out", "Ljava/io/OutputStream;", "offset", "byteCount", "digest", "Lokio/ByteString;", "algorithm", "", "emit", "emitCompleteSegments", "equals", "", "other", "", "exhausted", "flush", "get", "", "pos", "getByte", "index", "-deprecated_getByte", "hashCode", "", "hmac", "key", "hmacSha1", "hmacSha256", "hmacSha512", "indexOf", "b", "fromIndex", "toIndex", "bytes", "indexOfElement", "targetBytes", "inputStream", "Ljava/io/InputStream;", "isOpen", "md5", "outputStream", "peek", "rangeEquals", "bytesOffset", "segment", "segmentPos", "", "bytesLimit", "read", "sink", "Ljava/nio/ByteBuffer;", "readAll", "Lokio/Sink;", "readAndWriteUnsafe", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "readByte", "readByteArray", "readByteString", "readDecimalLong", "readFrom", "input", "forever", "readFully", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "readShort", "", "readShortLe", "readString", "charset", "Ljava/nio/charset/Charset;", "readUnsafe", "readUtf8", "readUtf8CodePoint", "readUtf8Line", "newline", "readUtf8Line$jvm", "readUtf8LineStrict", "limit", "request", "require", "seek", "T", "lambda", "Lkotlin/Function2;", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "select", "options", "Lokio/Options;", "selectPrefix", "selectTruncated", "selectPrefix$jvm", "sha1", "sha256", "sha512", "-deprecated_size", "skip", "snapshot", "timeout", "Lokio/Timeout;", "toString", "writableSegment", "minimumCapacity", "writableSegment$jvm", "write", "source", "byteString", "Lokio/Source;", "writeAll", "writeByte", "writeDecimalLong", "v", "writeHexadecimalUnsignedLong", "writeInt", "i", "writeIntLe", "writeLong", "writeLongLe", "writeShort", "s", "writeShortLe", "writeString", "string", "beginIndex", "endIndex", "writeTo", "writeUtf8", "writeUtf8CodePoint", "codePoint", "Companion", "UnsafeCursor", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: Buffer.kt */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {
    public static final Companion Companion = new Companion(null);
    private static final byte[] DIGITS;
    public Segment head;
    private long size;

    @Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo24952d2 = {"Lokio/Buffer$Companion;", "", "()V", "DIGITS", "", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
    /* compiled from: Buffer.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\bJ\u0006\u0010\u0014\u001a\u00020\bJ\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nJ\u000e\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, mo24952d2 = {"Lokio/Buffer$UnsafeCursor;", "Ljava/io/Closeable;", "()V", "buffer", "Lokio/Buffer;", "data", "", "end", "", "offset", "", "readWrite", "", "segment", "Lokio/Segment;", "start", "close", "", "expandBuffer", "minByteCount", "next", "resizeBuffer", "newSize", "seek", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
    /* compiled from: Buffer.kt */
    public static final class UnsafeCursor implements Closeable {
        public Buffer buffer;
        public byte[] data;
        public int end = -1;
        public long offset = -1;
        public boolean readWrite;
        private Segment segment;
        public int start = -1;

        public final int next() {
            long j = this.offset;
            Buffer buffer2 = this.buffer;
            if (buffer2 == null) {
                Intrinsics.throwNpe();
            }
            if (j != buffer2.size()) {
                long j2 = this.offset;
                return seek(j2 == -1 ? 0 : j2 + ((long) (this.end - this.start)));
            }
            throw new IllegalStateException("no more bytes".toString());
        }

        public final int seek(long offset2) {
            long nextOffset;
            Segment next;
            long j = offset2;
            Buffer buffer2 = this.buffer;
            if (buffer2 == null) {
                throw new IllegalStateException("not attached to a buffer".toString());
            } else if (j < ((long) -1) || j > buffer2.size()) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Long.valueOf(offset2), Long.valueOf(buffer2.size())};
                String format = String.format("offset=%s > size=%s", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                throw new ArrayIndexOutOfBoundsException(format);
            } else if (j == -1 || j == buffer2.size()) {
                this.segment = null;
                this.offset = j;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return -1;
            } else {
                long min = 0;
                long max = buffer2.size();
                Segment head = buffer2.head;
                Segment tail = buffer2.head;
                Segment segment2 = this.segment;
                if (segment2 != null) {
                    long j2 = this.offset;
                    int i = this.start;
                    if (segment2 == null) {
                        Intrinsics.throwNpe();
                    }
                    long segmentOffset = j2 - ((long) (i - segment2.pos));
                    if (segmentOffset > j) {
                        max = segmentOffset;
                        tail = this.segment;
                    } else {
                        min = segmentOffset;
                        head = this.segment;
                    }
                }
                if (max - j > j - min) {
                    next = head;
                    nextOffset = min;
                    while (true) {
                        if (next == null) {
                            Intrinsics.throwNpe();
                        }
                        if (j < ((long) (next.limit - next.pos)) + nextOffset) {
                            break;
                        }
                        nextOffset += (long) (next.limit - next.pos);
                        next = next.next;
                    }
                } else {
                    Segment next2 = tail;
                    long nextOffset2 = max;
                    while (nextOffset > j) {
                        if (next == null) {
                            Intrinsics.throwNpe();
                        }
                        next2 = next.prev;
                        if (next2 == null) {
                            Intrinsics.throwNpe();
                        }
                        nextOffset2 = nextOffset - ((long) (next2.limit - next2.pos));
                    }
                }
                if (this.readWrite) {
                    if (next == null) {
                        Intrinsics.throwNpe();
                    }
                    if (next.shared) {
                        Segment unsharedNext = next.unsharedCopy();
                        if (buffer2.head == next) {
                            buffer2.head = unsharedNext;
                        }
                        next = next.push(unsharedNext);
                        Segment segment3 = next.prev;
                        if (segment3 == null) {
                            Intrinsics.throwNpe();
                        }
                        segment3.pop();
                    }
                }
                this.segment = next;
                this.offset = j;
                if (next == null) {
                    Intrinsics.throwNpe();
                }
                this.data = next.data;
                this.start = next.pos + ((int) (j - nextOffset));
                this.end = next.limit;
                return this.end - this.start;
            }
        }

        public final long resizeBuffer(long newSize) {
            long j = newSize;
            Buffer buffer2 = this.buffer;
            if (buffer2 == null) {
                throw new IllegalStateException("not attached to a buffer".toString());
            } else if (this.readWrite) {
                long oldSize = buffer2.size();
                int i = 1;
                if (j <= oldSize) {
                    if (j < 0) {
                        i = 0;
                    }
                    if (i != 0) {
                        long bytesToSubtract = oldSize - j;
                        while (true) {
                            if (bytesToSubtract <= 0) {
                                break;
                            }
                            Segment segment2 = buffer2.head;
                            if (segment2 == null) {
                                Intrinsics.throwNpe();
                            }
                            Segment tail = segment2.prev;
                            if (tail == null) {
                                Intrinsics.throwNpe();
                            }
                            int tailSize = tail.limit - tail.pos;
                            if (((long) tailSize) > bytesToSubtract) {
                                tail.limit -= (int) bytesToSubtract;
                                break;
                            }
                            buffer2.head = tail.pop();
                            SegmentPool.recycle(tail);
                            bytesToSubtract -= (long) tailSize;
                        }
                        this.segment = null;
                        this.offset = j;
                        this.data = null;
                        this.start = -1;
                        this.end = -1;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("newSize < 0: ");
                        sb.append(j);
                        throw new IllegalArgumentException(sb.toString().toString());
                    }
                } else if (j > oldSize) {
                    boolean needsToSeek = true;
                    long bytesToAdd = j - oldSize;
                    for (long j2 = 0; bytesToAdd > j2; j2 = 0) {
                        Segment tail2 = buffer2.writableSegment$jvm(i);
                        int segmentBytesToAdd = (int) Math.min(bytesToAdd, (long) (8192 - tail2.limit));
                        tail2.limit += segmentBytesToAdd;
                        bytesToAdd -= (long) segmentBytesToAdd;
                        if (needsToSeek) {
                            this.segment = tail2;
                            this.offset = oldSize;
                            this.data = tail2.data;
                            this.start = tail2.limit - segmentBytesToAdd;
                            this.end = tail2.limit;
                            needsToSeek = false;
                        }
                        i = 1;
                    }
                }
                buffer2.setSize$jvm(j);
                return oldSize;
            } else {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers".toString());
            }
        }

        public final long expandBuffer(int minByteCount) {
            boolean z = true;
            if (minByteCount > 0) {
                if (minByteCount > 8192) {
                    z = false;
                }
                if (z) {
                    Buffer buffer2 = this.buffer;
                    if (buffer2 == null) {
                        throw new IllegalStateException("not attached to a buffer".toString());
                    } else if (this.readWrite) {
                        long oldSize = buffer2.size();
                        Segment tail = buffer2.writableSegment$jvm(minByteCount);
                        int result = 8192 - tail.limit;
                        tail.limit = 8192;
                        buffer2.setSize$jvm(((long) result) + oldSize);
                        this.segment = tail;
                        this.offset = oldSize;
                        this.data = tail.data;
                        this.start = 8192 - result;
                        this.end = 8192;
                        return (long) result;
                    } else {
                        throw new IllegalStateException("expandBuffer() only permitted for read/write buffers".toString());
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("minByteCount > Segment.SIZE: ");
                    sb.append(minByteCount);
                    throw new IllegalArgumentException(sb.toString().toString());
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("minByteCount <= 0: ");
                sb2.append(minByteCount);
                throw new IllegalArgumentException(sb2.toString().toString());
            }
        }

        public void close() {
            if (this.buffer != null) {
                this.buffer = null;
                this.segment = null;
                this.offset = -1;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return;
            }
            throw new IllegalStateException("not attached to a buffer".toString());
        }
    }

    public final Buffer copyTo(OutputStream outputStream) throws IOException {
        return copyTo$default(this, outputStream, 0, 0, 6, (Object) null);
    }

    public final Buffer copyTo(OutputStream outputStream, long j) throws IOException {
        return copyTo$default(this, outputStream, j, 0, 4, (Object) null);
    }

    public final UnsafeCursor readAndWriteUnsafe() {
        return readAndWriteUnsafe$default(this, null, 1, null);
    }

    public final UnsafeCursor readUnsafe() {
        return readUnsafe$default(this, null, 1, null);
    }

    public final Buffer writeTo(OutputStream outputStream) throws IOException {
        return writeTo$default(this, outputStream, 0, 2, null);
    }

    public final void setSize$jvm(long j) {
        this.size = j;
    }

    public final long size() {
        return this.size;
    }

    public Buffer buffer() {
        return this;
    }

    public Buffer getBuffer() {
        return this;
    }

    public OutputStream outputStream() {
        return new Buffer$outputStream$1(this);
    }

    public Buffer emitCompleteSegments() {
        return this;
    }

    public Buffer emit() {
        return this;
    }

    public boolean exhausted() {
        return this.size == 0;
    }

    public void require(long byteCount) throws EOFException {
        if (this.size < byteCount) {
            throw new EOFException();
        }
    }

    public boolean request(long byteCount) {
        return this.size >= byteCount;
    }

    public BufferedSource peek() {
        return Okio.buffer((Source) new PeekSource(this));
    }

    public InputStream inputStream() {
        return new Buffer$inputStream$1(this);
    }

    public static /* bridge */ /* synthetic */ Buffer copyTo$default(Buffer buffer, OutputStream outputStream, long j, long j2, int i, Object obj) throws IOException {
        long j3;
        long j4 = (i & 2) != 0 ? 0 : j;
        if ((i & 4) != 0) {
            j3 = buffer.size - j4;
        } else {
            j3 = j2;
        }
        return buffer.copyTo(outputStream, j4, j3);
    }

    public final Buffer copyTo(OutputStream out, long offset, long byteCount) throws IOException {
        OutputStream outputStream = out;
        Intrinsics.checkParameterIsNotNull(out, "out");
        long offset2 = offset;
        long byteCount2 = byteCount;
        Util.checkOffsetAndCount(this.size, offset2, byteCount2);
        if (byteCount2 == 0) {
            return this;
        }
        Segment s = this.head;
        while (true) {
            if (s == null) {
                Intrinsics.throwNpe();
            }
            if (offset2 < ((long) (s.limit - s.pos))) {
                break;
            }
            offset2 -= (long) (s.limit - s.pos);
            s = s.next;
        }
        while (byteCount2 > 0) {
            if (s == null) {
                Intrinsics.throwNpe();
            }
            int pos = (int) (((long) s.pos) + offset2);
            int toCopy = (int) Math.min((long) (s.limit - pos), byteCount2);
            out.write(s.data, pos, toCopy);
            byteCount2 -= (long) toCopy;
            offset2 = 0;
            s = s.next;
        }
        return this;
    }

    public static /* bridge */ /* synthetic */ Buffer copyTo$default(Buffer buffer, Buffer buffer2, long j, long j2, int i, Object obj) {
        long j3;
        long j4 = (i & 2) != 0 ? 0 : j;
        if ((i & 4) != 0) {
            j3 = buffer.size - j4;
        } else {
            j3 = j2;
        }
        return buffer.copyTo(buffer2, j4, j3);
    }

    public final Buffer copyTo(Buffer out, long offset, long byteCount) {
        Intrinsics.checkParameterIsNotNull(out, "out");
        long offset2 = offset;
        long byteCount2 = byteCount;
        Util.checkOffsetAndCount(this.size, offset2, byteCount2);
        if (byteCount2 == 0) {
            return this;
        }
        out.size += byteCount2;
        Segment s = this.head;
        while (true) {
            if (s == null) {
                Intrinsics.throwNpe();
            }
            if (offset2 < ((long) (s.limit - s.pos))) {
                break;
            }
            offset2 -= (long) (s.limit - s.pos);
            s = s.next;
        }
        while (byteCount2 > 0) {
            if (s == null) {
                Intrinsics.throwNpe();
            }
            Segment copy = s.sharedCopy();
            copy.pos += (int) offset2;
            copy.limit = Math.min(copy.pos + ((int) byteCount2), copy.limit);
            Segment segment = out.head;
            if (segment == null) {
                copy.prev = copy;
                copy.next = copy.prev;
                out.head = copy.next;
            } else {
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                Segment segment2 = segment.prev;
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
                segment2.push(copy);
            }
            byteCount2 -= (long) (copy.limit - copy.pos);
            offset2 = 0;
            s = s.next;
        }
        return this;
    }

    public static /* bridge */ /* synthetic */ Buffer writeTo$default(Buffer buffer, OutputStream outputStream, long j, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = buffer.size;
        }
        return buffer.writeTo(outputStream, j);
    }

    public final Buffer writeTo(OutputStream out, long byteCount) throws IOException {
        Intrinsics.checkParameterIsNotNull(out, "out");
        long byteCount2 = byteCount;
        Util.checkOffsetAndCount(this.size, 0, byteCount2);
        Segment s = this.head;
        while (byteCount2 > 0) {
            if (s == null) {
                Intrinsics.throwNpe();
            }
            int b$iv = (int) Math.min(byteCount2, (long) (s.limit - s.pos));
            out.write(s.data, s.pos, b$iv);
            s.pos += b$iv;
            this.size -= (long) b$iv;
            byteCount2 -= (long) b$iv;
            if (s.pos == s.limit) {
                Segment toRecycle = s;
                s = toRecycle.pop();
                this.head = s;
                SegmentPool.recycle(toRecycle);
            }
        }
        return this;
    }

    public final Buffer readFrom(InputStream input) throws IOException {
        Intrinsics.checkParameterIsNotNull(input, "input");
        readFrom(input, LongCompanionObject.MAX_VALUE, true);
        return this;
    }

    public final Buffer readFrom(InputStream input, long byteCount) throws IOException {
        Intrinsics.checkParameterIsNotNull(input, "input");
        if (byteCount >= 0) {
            readFrom(input, byteCount, false);
            return this;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("byteCount < 0: ");
        sb.append(byteCount);
        throw new IllegalArgumentException(sb.toString().toString());
    }

    private final void readFrom(InputStream input, long byteCount, boolean forever) throws IOException {
        long byteCount2 = byteCount;
        while (true) {
            if (byteCount2 > 0 || forever) {
                Segment tail = writableSegment$jvm(1);
                int bytesRead = input.read(tail.data, tail.limit, (int) Math.min(byteCount2, (long) (8192 - tail.limit)));
                if (bytesRead != -1) {
                    tail.limit += bytesRead;
                    this.size += (long) bytesRead;
                    byteCount2 -= (long) bytesRead;
                } else if (!forever) {
                    throw new EOFException();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public final long completeSegmentByteCount() {
        long result = this.size;
        if (result == 0) {
            return 0;
        }
        Segment segment = this.head;
        if (segment == null) {
            Intrinsics.throwNpe();
        }
        Segment tail = segment.prev;
        if (tail == null) {
            Intrinsics.throwNpe();
        }
        if (tail.limit < 8192 && tail.owner) {
            result -= (long) (tail.limit - tail.pos);
        }
        return result;
    }

    public byte readByte() throws EOFException {
        if (this.size != 0) {
            Segment segment = this.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int pos = segment.pos;
            int limit = segment.limit;
            int pos2 = pos + 1;
            byte b = segment.data[pos];
            this.size--;
            if (pos2 == limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = pos2;
            }
            return b;
        }
        throw new EOFException();
    }

    public final byte getByte(long pos) {
        Util.checkOffsetAndCount(this.size, pos, 1);
        Segment s$iv = this.head;
        if (s$iv == null) {
            Segment s = null;
            Intrinsics.throwNpe();
            return s.data[(int) ((((long) s.pos) + pos) - -1)];
        } else if (size() - pos < pos) {
            long offset$iv = size();
            while (offset$iv > pos) {
                Segment segment = s$iv.prev;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                s$iv = segment;
                offset$iv -= (long) (s$iv.limit - s$iv.pos);
            }
            Segment s2 = s$iv;
            long offset = offset$iv;
            if (s2 == null) {
                Intrinsics.throwNpe();
            }
            return s2.data[(int) ((((long) s2.pos) + pos) - offset)];
        } else {
            long offset$iv2 = 0;
            while (true) {
                long nextOffset$iv = ((long) (s$iv.limit - s$iv.pos)) + offset$iv2;
                if (nextOffset$iv > pos) {
                    break;
                }
                Segment segment2 = s$iv.next;
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
                s$iv = segment2;
                offset$iv2 = nextOffset$iv;
            }
            Segment s3 = s$iv;
            long offset2 = offset$iv2;
            if (s3 == null) {
                Intrinsics.throwNpe();
            }
            return s3.data[(int) ((((long) s3.pos) + pos) - offset2)];
        }
    }

    public short readShort() throws EOFException {
        if (this.size >= 2) {
            Segment segment = this.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int pos = segment.pos;
            int limit = segment.limit;
            if (limit - pos < 2) {
                return (short) (((readByte() & UByte.MAX_VALUE) << 8) | (readByte() & 255));
            }
            byte[] data = segment.data;
            int pos2 = pos + 1;
            int pos3 = pos2 + 1;
            int s = ((data[pos] & UByte.MAX_VALUE) << 8) | (data[pos2] & 255);
            this.size -= 2;
            if (pos3 == limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = pos3;
            }
            return (short) s;
        }
        throw new EOFException();
    }

    public int readInt() throws EOFException {
        if (this.size >= 4) {
            Segment segment = this.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int pos = segment.pos;
            int limit = segment.limit;
            if (((long) (limit - pos)) < 4) {
                return ((readByte() & UByte.MAX_VALUE) << 24) | ((readByte() & UByte.MAX_VALUE) << 16) | ((readByte() & UByte.MAX_VALUE) << 8) | (readByte() & UByte.MAX_VALUE);
            }
            byte[] data = segment.data;
            int pos2 = pos + 1;
            int pos3 = pos2 + 1;
            int i = ((data[pos] & UByte.MAX_VALUE) << 24) | ((data[pos2] & UByte.MAX_VALUE) << 16);
            int pos4 = pos3 + 1;
            int i2 = i | ((data[pos3] & UByte.MAX_VALUE) << 8);
            int pos5 = pos4 + 1;
            int i3 = i2 | (data[pos4] & 255);
            this.size -= 4;
            if (pos5 == limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = pos5;
            }
            return i3;
        }
        throw new EOFException();
    }

    public long readLong() throws EOFException {
        if (this.size >= 8) {
            Segment segment = this.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int pos = segment.pos;
            int limit = segment.limit;
            if (((long) (limit - pos)) < 8) {
                return (((long) readInt()) & 4294967295L) | ((4294967295L & ((long) readInt())) << 32);
            }
            byte[] data = segment.data;
            int pos2 = pos + 1;
            int pos3 = pos2 + 1;
            int pos4 = pos3 + 1;
            long j = ((255 & ((long) data[pos])) << 56) | ((255 & ((long) data[pos2])) << 48) | ((255 & ((long) data[pos3])) << 40);
            int pos5 = pos4 + 1;
            long j2 = ((255 & ((long) data[pos4])) << 32) | j;
            int pos6 = pos5 + 1;
            int pos7 = pos6 + 1;
            long j3 = j2 | ((255 & ((long) data[pos5])) << 24) | ((((long) data[pos6]) & 255) << 16);
            int pos8 = pos7 + 1;
            int pos9 = pos8 + 1;
            long v = j3 | ((255 & ((long) data[pos7])) << 8) | (((long) data[pos8]) & 255);
            this.size -= 8;
            if (pos9 == limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = pos9;
            }
            return v;
        }
        throw new EOFException();
    }

    public short readShortLe() throws EOFException {
        return Util.reverseBytes(readShort());
    }

    public int readIntLe() throws EOFException {
        return Util.reverseBytes(readInt());
    }

    public long readLongLe() throws EOFException {
        return Util.reverseBytes(readLong());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00cf, code lost:
        if (r12 != r13) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d1, code lost:
        r0.head = r10.pop();
        okio.SegmentPool.recycle(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00db, code lost:
        r10.pos = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00de, code lost:
        if (r5 != false) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e2, code lost:
        if (r0.head != null) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e9, code lost:
        r0.size -= (long) r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ef, code lost:
        if (r4 == false) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        return -r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readDecimalLong() throws java.io.EOFException {
        /*
            r20 = this;
            r0 = r20
            long r1 = r0.size
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x00f5
            r1 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            r8 = -7
        L_0x0016:
            okio.Segment r10 = r0.head
            if (r10 != 0) goto L_0x001e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x001e:
            byte[] r11 = r10.data
            int r12 = r10.pos
            int r13 = r10.limit
        L_0x0024:
            if (r12 >= r13) goto L_0x00c9
            byte r14 = r11[r12]
            r15 = 48
            byte r15 = (byte) r15
            if (r14 < r15) goto L_0x0088
            r16 = r5
            r5 = 57
            byte r5 = (byte) r5
            if (r14 > r5) goto L_0x0083
            int r15 = r15 - r14
            int r5 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r5 < 0) goto L_0x0050
            int r5 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r5 != 0) goto L_0x0045
            r17 = r6
            long r5 = (long) r15
            int r7 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r7 >= 0) goto L_0x0047
            goto L_0x0052
        L_0x0045:
            r17 = r6
        L_0x0047:
            r5 = 10
            long r1 = r1 * r5
            long r5 = (long) r15
            long r1 = r1 + r5
            r19 = r11
            goto L_0x0099
        L_0x0050:
            r17 = r6
        L_0x0052:
            okio.Buffer r5 = new okio.Buffer
            r5.<init>()
            okio.Buffer r5 = r5.writeDecimalLong(r1)
            okio.Buffer r5 = r5.writeByte(r14)
            if (r4 != 0) goto L_0x0064
            r5.readByte()
        L_0x0064:
            java.lang.NumberFormatException r6 = new java.lang.NumberFormatException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r19 = r11
            java.lang.String r11 = "Number too large: "
            r7.append(r11)
            java.lang.String r11 = r5.readUtf8()
            r7.append(r11)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            throw r6
        L_0x0083:
            r17 = r6
            r19 = r11
            goto L_0x008e
        L_0x0088:
            r16 = r5
            r17 = r6
            r19 = r11
        L_0x008e:
            r5 = 45
            byte r5 = (byte) r5
            if (r14 != r5) goto L_0x00a7
            if (r3 != 0) goto L_0x00a7
            r4 = 1
            r5 = 1
            long r8 = r8 - r5
        L_0x0099:
            int r12 = r12 + 1
            int r3 = r3 + 1
            r5 = r16
            r6 = r17
            r11 = r19
            goto L_0x0024
        L_0x00a7:
            if (r3 == 0) goto L_0x00ab
            r5 = 1
            goto L_0x00cf
        L_0x00ab:
            java.lang.NumberFormatException r5 = new java.lang.NumberFormatException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Expected leading [0-9] or '-' character but was 0x"
            r6.append(r7)
            java.lang.String r7 = java.lang.Integer.toHexString(r14)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            throw r5
        L_0x00c9:
            r16 = r5
            r17 = r6
            r19 = r11
        L_0x00cf:
            if (r12 != r13) goto L_0x00db
            okio.Segment r6 = r10.pop()
            r0.head = r6
            okio.SegmentPool.recycle(r10)
            goto L_0x00dd
        L_0x00db:
            r10.pos = r12
        L_0x00dd:
            if (r5 != 0) goto L_0x00e9
            okio.Segment r6 = r0.head
            if (r6 != 0) goto L_0x00e5
            goto L_0x00e9
        L_0x00e5:
            r6 = r17
            goto L_0x0016
        L_0x00e9:
            long r6 = r0.size
            long r10 = (long) r3
            long r6 = r6 - r10
            r0.size = r6
            if (r4 == 0) goto L_0x00f3
            r6 = r1
            goto L_0x00f4
        L_0x00f3:
            long r6 = -r1
        L_0x00f4:
            return r6
        L_0x00f5:
            java.io.EOFException r1 = new java.io.EOFException
            r1.<init>()
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readDecimalLong():long");
    }

    public long readHexadecimalUnsignedLong() throws EOFException {
        int digit;
        if (this.size != 0) {
            long value = 0;
            int seen = 0;
            boolean done = false;
            do {
                Segment segment = this.head;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                byte[] data = segment.data;
                int pos = segment.pos;
                int limit = segment.limit;
                while (true) {
                    if (pos >= limit) {
                        break;
                    }
                    byte b = data[pos];
                    byte b2 = (byte) 48;
                    if (b < b2 || b > ((byte) 57)) {
                        byte b3 = (byte) 97;
                        if (b < b3 || b > ((byte) 102)) {
                            byte b4 = (byte) 65;
                            if (b >= b4 && b <= ((byte) 70)) {
                                digit = (b - b4) + 10;
                            } else if (seen != 0) {
                                done = true;
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Expected leading [0-9a-fA-F] character but was 0x");
                                sb.append(Integer.toHexString(b));
                                throw new NumberFormatException(sb.toString());
                            }
                        } else {
                            digit = (b - b3) + 10;
                        }
                    } else {
                        digit = b - b2;
                    }
                    if ((-1152921504606846976L & value) == 0) {
                        value = (value << 4) | ((long) digit);
                        pos++;
                        seen++;
                    } else {
                        Buffer buffer = new Buffer().writeHexadecimalUnsignedLong(value).writeByte((int) b);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Number too large: ");
                        sb2.append(buffer.readUtf8());
                        throw new NumberFormatException(sb2.toString());
                    }
                }
                if (pos == limit) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                } else {
                    segment.pos = pos;
                }
                if (done) {
                    break;
                }
            } while (this.head != null);
            this.size -= (long) seen;
            return value;
        }
        throw new EOFException();
    }

    public ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    public ByteString readByteString(long byteCount) throws EOFException {
        return new ByteString(readByteArray(byteCount));
    }

    public int select(Options options) {
        Intrinsics.checkParameterIsNotNull(options, "options");
        int index = selectPrefix$jvm$default(this, options, false, 2, null);
        if (index == -1) {
            return -1;
        }
        skip((long) options.getByteStrings$jvm()[index].size());
        return index;
    }

    public static /* bridge */ /* synthetic */ int selectPrefix$jvm$default(Buffer buffer, Options options, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return buffer.selectPrefix$jvm(options, z);
    }

    public final int selectPrefix$jvm(Options options, boolean selectTruncated) {
        boolean z;
        int nextStep;
        int pos;
        Intrinsics.checkParameterIsNotNull(options, "options");
        Segment head2 = this.head;
        int i = -1;
        if (head2 != null) {
            Segment s = head2;
            byte[] data = head2.data;
            int pos2 = head2.pos;
            int limit = head2.limit;
            int[] trie = options.getTrie$jvm();
            int scanOrSelect = 0;
            int prefixIndex = -1;
            int limit2 = limit;
            boolean z2 = false;
            int pos3 = pos2;
            byte[] data2 = data;
            Segment s2 = s;
            loop0:
            while (true) {
                int triePos = scanOrSelect + 1;
                int scanOrSelect2 = trie[scanOrSelect];
                int triePos2 = triePos + 1;
                int possiblePrefixIndex = trie[triePos];
                if (possiblePrefixIndex != i) {
                    prefixIndex = possiblePrefixIndex;
                }
                if (s2 == null) {
                    int i2 = pos3;
                    break;
                }
                if (scanOrSelect2 < 0) {
                    int trieLimit = triePos2 + (scanOrSelect2 * -1);
                    while (true) {
                        int pos4 = pos3 + 1;
                        int triePos3 = triePos2 + 1;
                        if ((data2[pos3] & 255) != trie[triePos2]) {
                            return prefixIndex;
                        }
                        boolean scanComplete = triePos3 == trieLimit;
                        if (pos4 == limit2) {
                            if (s2 == null) {
                                Intrinsics.throwNpe();
                            }
                            Segment segment = s2.next;
                            if (segment == null) {
                                Intrinsics.throwNpe();
                            }
                            s2 = segment;
                            pos = s2.pos;
                            data2 = s2.data;
                            limit2 = s2.limit;
                            if (s2 == head2) {
                                if (!scanComplete) {
                                    int i3 = triePos3;
                                    break loop0;
                                }
                                s2 = null;
                            }
                        } else {
                            pos = pos4;
                        }
                        if (scanComplete) {
                            int trieLimit2 = triePos3;
                            nextStep = trie[triePos3];
                            z = true;
                            break;
                        }
                        pos3 = pos;
                        triePos2 = triePos3;
                        Options options2 = options;
                    }
                } else {
                    int selectChoiceCount = scanOrSelect2;
                    pos = pos3 + 1;
                    byte $receiver$iv = data2[pos3];
                    z = z2;
                    int $receiver$iv2 = $receiver$iv & 255;
                    int selectLimit = triePos2 + selectChoiceCount;
                    int triePos4 = triePos2;
                    while (triePos4 != selectLimit) {
                        int selectLimit2 = selectLimit;
                        if ($receiver$iv2 == trie[triePos4]) {
                            int nextStep2 = trie[triePos4 + selectChoiceCount];
                            if (pos == limit2) {
                                Segment s3 = s2.next;
                                if (s3 == null) {
                                    Intrinsics.throwNpe();
                                }
                                pos = s3.pos;
                                byte[] data3 = s3.data;
                                int limit3 = s3.limit;
                                if (s3 == head2) {
                                    limit2 = limit3;
                                    data2 = data3;
                                    s2 = null;
                                    nextStep = nextStep2;
                                } else {
                                    limit2 = limit3;
                                    data2 = data3;
                                    s2 = s3;
                                    nextStep = nextStep2;
                                }
                            } else {
                                nextStep = nextStep2;
                            }
                        } else {
                            triePos4++;
                            selectLimit = selectLimit2;
                        }
                    }
                    return prefixIndex;
                }
                if (nextStep >= 0) {
                    return nextStep;
                }
                scanOrSelect = -nextStep;
                i = -1;
                z2 = z;
                pos3 = pos;
                Options options3 = options;
            }
            if (selectTruncated) {
                return -2;
            }
            return prefixIndex;
        }
        return selectTruncated ? -2 : -1;
    }

    public void readFully(Buffer sink, long byteCount) throws EOFException {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        long j = this.size;
        if (j >= byteCount) {
            sink.write(this, byteCount);
        } else {
            sink.write(this, j);
            throw new EOFException();
        }
    }

    public long readAll(Sink sink) throws IOException {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        long byteCount = this.size;
        if (byteCount > 0) {
            sink.write(this, byteCount);
        }
        return byteCount;
    }

    public String readUtf8() {
        return readString(this.size, Charsets.UTF_8);
    }

    public String readUtf8(long byteCount) throws EOFException {
        return readString(byteCount, Charsets.UTF_8);
    }

    public String readString(Charset charset) {
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return readString(this.size, charset);
    }

    public String readString(long byteCount, Charset charset) throws EOFException {
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        if (!(byteCount >= 0 && byteCount <= ((long) Integer.MAX_VALUE))) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount: ");
            sb.append(byteCount);
            throw new IllegalArgumentException(sb.toString().toString());
        } else if (this.size < byteCount) {
            throw new EOFException();
        } else if (byteCount == 0) {
            return "";
        } else {
            Segment s = this.head;
            if (s == null) {
                Intrinsics.throwNpe();
            }
            if (((long) s.pos) + byteCount > ((long) s.limit)) {
                return new String(readByteArray(byteCount), charset);
            }
            String result = new String(s.data, s.pos, (int) byteCount, charset);
            s.pos += (int) byteCount;
            this.size -= byteCount;
            if (s.pos == s.limit) {
                this.head = s.pop();
                SegmentPool.recycle(s);
            }
            return result;
        }
    }

    public String readUtf8Line() throws EOFException {
        long newline = indexOf((byte) 10);
        if (newline != -1) {
            return readUtf8Line$jvm(newline);
        }
        long j = this.size;
        if (j != 0) {
            return readUtf8(j);
        }
        return null;
    }

    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(LongCompanionObject.MAX_VALUE);
    }

    public String readUtf8LineStrict(long limit) throws EOFException {
        long j = limit;
        if (j >= 0) {
            long j2 = LongCompanionObject.MAX_VALUE;
            if (j != LongCompanionObject.MAX_VALUE) {
                j2 = j + 1;
            }
            long scanLength = j2;
            byte b = (byte) 10;
            long newline = indexOf(b, 0, scanLength);
            if (newline != -1) {
                return readUtf8Line$jvm(newline);
            }
            if (scanLength < this.size && getByte(scanLength - 1) == ((byte) 13) && getByte(scanLength) == b) {
                return readUtf8Line$jvm(scanLength);
            }
            Buffer data = new Buffer();
            long j3 = newline;
            copyTo(data, 0, Math.min((long) 32, this.size));
            StringBuilder sb = new StringBuilder();
            sb.append("\\n not found: limit=");
            sb.append(Math.min(this.size, j));
            sb.append(" content=");
            sb.append(data.readByteString().hex());
            sb.append(Typography.ellipsis);
            throw new EOFException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("limit < 0: ");
        sb2.append(j);
        throw new IllegalArgumentException(sb2.toString().toString());
    }

    public final String readUtf8Line$jvm(long newline) throws EOFException {
        if (newline <= 0 || getByte(newline - 1) != ((byte) 13)) {
            String result = readUtf8(newline);
            skip(1);
            return result;
        }
        String readUtf8 = readUtf8(newline - 1);
        skip(2);
        return readUtf8;
    }

    public int readUtf8CodePoint() throws EOFException {
        int codePoint;
        int byteCount;
        int min;
        if (this.size != 0) {
            int b0 = getByte(0);
            byte $receiver$iv = 65533;
            if ((128 & b0) == 0) {
                codePoint = b0 & 127;
                byteCount = 1;
                min = 0;
            } else if ((224 & b0) == 192) {
                codePoint = b0 & 31;
                byteCount = 2;
                min = 128;
            } else if ((240 & b0) == 224) {
                codePoint = b0 & 15;
                byteCount = 3;
                min = 2048;
            } else if ((248 & b0) == 240) {
                codePoint = b0 & 7;
                byteCount = 4;
                min = 65536;
            } else {
                skip(1);
                return Utf8.REPLACEMENT_CODE_POINT;
            }
            if (this.size >= ((long) byteCount)) {
                int i = 1;
                while (i < byteCount) {
                    int b = getByte((long) i);
                    if ((192 & b) == 128) {
                        codePoint = (codePoint << 6) | (63 & b);
                        i++;
                    } else {
                        skip((long) i);
                        return Utf8.REPLACEMENT_CODE_POINT;
                    }
                }
                skip((long) byteCount);
                if (codePoint <= 1114111 && ((55296 > codePoint || 57343 < codePoint) && codePoint >= min)) {
                    $receiver$iv = codePoint;
                }
                return $receiver$iv;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("size < ");
            sb.append(byteCount);
            sb.append(": ");
            sb.append(this.size);
            sb.append(" (to read code point prefixed 0x");
            sb.append(Integer.toHexString(b0));
            sb.append(")");
            throw new EOFException(sb.toString());
        }
        throw new EOFException();
    }

    public byte[] readByteArray() {
        return readByteArray(this.size);
    }

    public byte[] readByteArray(long byteCount) throws EOFException {
        if (!(byteCount >= 0 && byteCount <= ((long) Integer.MAX_VALUE))) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount: ");
            sb.append(byteCount);
            throw new IllegalArgumentException(sb.toString().toString());
        } else if (this.size >= byteCount) {
            byte[] result = new byte[((int) byteCount)];
            readFully(result);
            return result;
        } else {
            throw new EOFException();
        }
    }

    public int read(byte[] sink) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        return read(sink, 0, sink.length);
    }

    public void readFully(byte[] sink) throws EOFException {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        int offset = 0;
        while (offset < sink.length) {
            int read = read(sink, offset, sink.length - offset);
            if (read != -1) {
                offset += read;
            } else {
                throw new EOFException();
            }
        }
    }

    public int read(byte[] sink, int offset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        Util.checkOffsetAndCount((long) sink.length, (long) offset, (long) byteCount);
        Segment s = this.head;
        if (s == null) {
            return -1;
        }
        int toCopy = Math.min(byteCount, s.limit - s.pos);
        System.arraycopy(s.data, s.pos, sink, offset, toCopy);
        s.pos += toCopy;
        this.size -= (long) toCopy;
        if (s.pos == s.limit) {
            this.head = s.pop();
            SegmentPool.recycle(s);
        }
        return toCopy;
    }

    public int read(ByteBuffer sink) throws IOException {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        Segment s = this.head;
        if (s == null) {
            return -1;
        }
        int toCopy = Math.min(sink.remaining(), s.limit - s.pos);
        sink.put(s.data, s.pos, toCopy);
        s.pos += toCopy;
        this.size -= (long) toCopy;
        if (s.pos == s.limit) {
            this.head = s.pop();
            SegmentPool.recycle(s);
        }
        return toCopy;
    }

    public final void clear() {
        skip(this.size);
    }

    public void skip(long byteCount) throws EOFException {
        long byteCount2 = byteCount;
        while (byteCount2 > 0) {
            Segment head2 = this.head;
            if (head2 != null) {
                int toSkip = (int) Math.min(byteCount2, (long) (head2.limit - head2.pos));
                this.size -= (long) toSkip;
                byteCount2 -= (long) toSkip;
                head2.pos += toSkip;
                if (head2.pos == head2.limit) {
                    this.head = head2.pop();
                    SegmentPool.recycle(head2);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    public Buffer write(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "byteString");
        byteString.write$jvm(this);
        return this;
    }

    public Buffer writeUtf8(String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        return writeUtf8(string, 0, string.length());
    }

    public Buffer writeUtf8(String string, int beginIndex, int endIndex) {
        String str = string;
        int i = beginIndex;
        int i2 = endIndex;
        Intrinsics.checkParameterIsNotNull(str, "string");
        int i3 = 1;
        if (i >= 0) {
            if (i2 >= i) {
                if (i2 <= string.length()) {
                    int runSize = beginIndex;
                    while (runSize < i2) {
                        int c = str.charAt(runSize);
                        if (c < 128) {
                            Segment tail = writableSegment$jvm(i3);
                            byte[] data = tail.data;
                            int segmentOffset = tail.limit - runSize;
                            int runLimit = Math.min(i2, 8192 - segmentOffset);
                            int i4 = runSize + 1;
                            data[runSize + segmentOffset] = (byte) c;
                            while (i4 < runLimit) {
                                int c2 = str.charAt(i4);
                                if (c2 >= 128) {
                                    break;
                                }
                                int i5 = i4 + 1;
                                data[i4 + segmentOffset] = (byte) c2;
                                i4 = i5;
                            }
                            int runSize2 = (i4 + segmentOffset) - tail.limit;
                            tail.limit += runSize2;
                            this.size += (long) runSize2;
                            runSize = i4;
                        } else if (c < 2048) {
                            Segment tail2 = writableSegment$jvm(2);
                            tail2.data[tail2.limit] = (byte) ((c >> 6) | 192);
                            tail2.data[tail2.limit + 1] = (byte) (128 | (c & 63));
                            tail2.limit += 2;
                            this.size += 2;
                            runSize++;
                        } else if (c < 55296 || c > 57343) {
                            Segment tail3 = writableSegment$jvm(3);
                            tail3.data[tail3.limit] = (byte) ((c >> 12) | 224);
                            tail3.data[tail3.limit + 1] = (byte) ((63 & (c >> 6)) | 128);
                            tail3.data[tail3.limit + 2] = (byte) ((c & 63) | 128);
                            tail3.limit += 3;
                            this.size += 3;
                            runSize++;
                        } else {
                            int low = runSize + 1 < i2 ? str.charAt(runSize + 1) : 0;
                            if (c > 56319 || 56320 > low || 57343 < low) {
                                writeByte(63);
                                runSize++;
                            } else {
                                int codePoint = (((c & 1023) << 10) | (low & 1023)) + 65536;
                                Segment tail4 = writableSegment$jvm(4);
                                tail4.data[tail4.limit] = (byte) ((codePoint >> 18) | 240);
                                tail4.data[tail4.limit + 1] = (byte) (((codePoint >> 12) & 63) | 128);
                                tail4.data[tail4.limit + 2] = (byte) (((codePoint >> 6) & 63) | 128);
                                tail4.data[tail4.limit + 3] = (byte) (128 | (codePoint & 63));
                                tail4.limit += 4;
                                this.size += 4;
                                runSize += 2;
                            }
                        }
                        i3 = 1;
                    }
                    return this;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("endIndex > string.length: ");
                sb.append(i2);
                sb.append(" > ");
                sb.append(string.length());
                throw new IllegalArgumentException(sb.toString().toString());
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("endIndex < beginIndex: ");
            sb2.append(i2);
            sb2.append(" < ");
            sb2.append(i);
            throw new IllegalArgumentException(sb2.toString().toString());
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("beginIndex < 0: ");
        sb3.append(i);
        throw new IllegalArgumentException(sb3.toString().toString());
    }

    public Buffer writeUtf8CodePoint(int codePoint) {
        if (codePoint < 128) {
            writeByte(codePoint);
        } else if (codePoint < 2048) {
            Segment tail = writableSegment$jvm(2);
            tail.data[tail.limit] = (byte) ((codePoint >> 6) | 192);
            tail.data[tail.limit + 1] = (byte) (128 | (codePoint & 63));
            tail.limit += 2;
            this.size += 2;
        } else if (55296 <= codePoint && 57343 >= codePoint) {
            writeByte(63);
        } else if (codePoint < 65536) {
            Segment tail2 = writableSegment$jvm(3);
            tail2.data[tail2.limit] = (byte) ((codePoint >> 12) | 224);
            tail2.data[tail2.limit + 1] = (byte) ((63 & (codePoint >> 6)) | 128);
            tail2.data[tail2.limit + 2] = (byte) (128 | (codePoint & 63));
            tail2.limit += 3;
            this.size += 3;
        } else if (codePoint <= 1114111) {
            Segment tail3 = writableSegment$jvm(4);
            tail3.data[tail3.limit] = (byte) ((codePoint >> 18) | 240);
            tail3.data[tail3.limit + 1] = (byte) (((codePoint >> 12) & 63) | 128);
            tail3.data[tail3.limit + 2] = (byte) (((codePoint >> 6) & 63) | 128);
            tail3.data[tail3.limit + 3] = (byte) (128 | (codePoint & 63));
            tail3.limit += 4;
            this.size += 4;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected code point: ");
            sb.append(Integer.toHexString(codePoint));
            throw new IllegalArgumentException(sb.toString());
        }
        return this;
    }

    public Buffer writeString(String string, Charset charset) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return writeString(string, 0, string.length(), charset);
    }

    public Buffer writeString(String string, int beginIndex, int endIndex, Charset charset) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        boolean z = true;
        if (beginIndex >= 0) {
            if (endIndex >= beginIndex) {
                if (endIndex > string.length()) {
                    z = false;
                }
                if (!z) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("endIndex > string.length: ");
                    sb.append(endIndex);
                    sb.append(" > ");
                    sb.append(string.length());
                    throw new IllegalArgumentException(sb.toString().toString());
                } else if (Intrinsics.areEqual((Object) charset, (Object) Charsets.UTF_8)) {
                    return writeUtf8(string, beginIndex, endIndex);
                } else {
                    String substring = string.substring(beginIndex, endIndex);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    if (substring != null) {
                        byte[] data = substring.getBytes(charset);
                        Intrinsics.checkExpressionValueIsNotNull(data, "(this as java.lang.String).getBytes(charset)");
                        return write(data, 0, data.length);
                    }
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("endIndex < beginIndex: ");
                sb2.append(endIndex);
                sb2.append(" < ");
                sb2.append(beginIndex);
                throw new IllegalArgumentException(sb2.toString().toString());
            }
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("beginIndex < 0: ");
            sb3.append(beginIndex);
            throw new IllegalArgumentException(sb3.toString().toString());
        }
    }

    public Buffer write(byte[] source) {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        return write(source, 0, source.length);
    }

    public Buffer write(byte[] source, int offset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        int offset2 = offset;
        Util.checkOffsetAndCount((long) source.length, (long) offset2, (long) byteCount);
        int limit = offset2 + byteCount;
        while (offset2 < limit) {
            Segment tail = writableSegment$jvm(1);
            int toCopy = Math.min(limit - offset2, 8192 - tail.limit);
            System.arraycopy(source, offset2, tail.data, tail.limit, toCopy);
            offset2 += toCopy;
            tail.limit += toCopy;
        }
        this.size += (long) byteCount;
        return this;
    }

    public int write(ByteBuffer source) throws IOException {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        int byteCount = source.remaining();
        int remaining = byteCount;
        while (remaining > 0) {
            Segment tail = writableSegment$jvm(1);
            int toCopy = Math.min(remaining, 8192 - tail.limit);
            source.get(tail.data, tail.limit, toCopy);
            remaining -= toCopy;
            tail.limit += toCopy;
        }
        this.size += (long) byteCount;
        return byteCount;
    }

    public long writeAll(Source source) throws IOException {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        long totalBytesRead = 0;
        while (true) {
            long readCount = source.read(this, (long) 8192);
            if (readCount == -1) {
                return totalBytesRead;
            }
            totalBytesRead += readCount;
        }
    }

    public BufferedSink write(Source source, long byteCount) throws IOException {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        long byteCount2 = byteCount;
        while (byteCount2 > 0) {
            long read = source.read(this, byteCount2);
            if (read != -1) {
                byteCount2 -= read;
            } else {
                throw new EOFException();
            }
        }
        return this;
    }

    public Buffer writeByte(int b) {
        Segment tail = writableSegment$jvm(1);
        byte[] bArr = tail.data;
        int i = tail.limit;
        tail.limit = i + 1;
        bArr[i] = (byte) b;
        this.size++;
        return this;
    }

    public Buffer writeShort(int s) {
        Segment tail = writableSegment$jvm(2);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((s >>> 8) & 255);
        int limit3 = limit2 + 1;
        data[limit2] = (byte) (s & 255);
        tail.limit = limit3;
        this.size += 2;
        return this;
    }

    public Buffer writeShortLe(int s) {
        return writeShort((int) Util.reverseBytes((short) s));
    }

    public Buffer writeInt(int i) {
        Segment tail = writableSegment$jvm(4);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((i >>> 24) & 255);
        int limit3 = limit2 + 1;
        data[limit2] = (byte) ((i >>> 16) & 255);
        int limit4 = limit3 + 1;
        data[limit3] = (byte) ((i >>> 8) & 255);
        int limit5 = limit4 + 1;
        data[limit4] = (byte) (i & 255);
        tail.limit = limit5;
        this.size += 4;
        return this;
    }

    public Buffer writeIntLe(int i) {
        return writeInt(Util.reverseBytes(i));
    }

    public Buffer writeLong(long v) {
        Segment tail = writableSegment$jvm(8);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((int) ((v >>> 56) & 255));
        int limit3 = limit2 + 1;
        data[limit2] = (byte) ((int) ((v >>> 48) & 255));
        int limit4 = limit3 + 1;
        data[limit3] = (byte) ((int) ((v >>> 40) & 255));
        int limit5 = limit4 + 1;
        data[limit4] = (byte) ((int) ((v >>> 32) & 255));
        int limit6 = limit5 + 1;
        data[limit5] = (byte) ((int) ((v >>> 24) & 255));
        int limit7 = limit6 + 1;
        data[limit6] = (byte) ((int) ((v >>> 16) & 255));
        int limit8 = limit7 + 1;
        data[limit7] = (byte) ((int) ((v >>> 8) & 255));
        int limit9 = limit8 + 1;
        data[limit8] = (byte) ((int) (v & 255));
        tail.limit = limit9;
        this.size += 8;
        return this;
    }

    public Buffer writeLongLe(long v) {
        return writeLong(Util.reverseBytes(v));
    }

    public Buffer writeDecimalLong(long v) {
        long v2 = v;
        if (v2 == 0) {
            return writeByte(48);
        }
        boolean negative = false;
        if (v2 < 0) {
            v2 = -v2;
            if (v2 < 0) {
                return writeUtf8("-9223372036854775808");
            }
            negative = true;
        }
        int width = v2 < 100000000 ? v2 < 10000 ? v2 < 100 ? v2 < 10 ? 1 : 2 : v2 < 1000 ? 3 : 4 : v2 < 1000000 ? v2 < 100000 ? 5 : 6 : v2 < 10000000 ? 7 : 8 : v2 < 1000000000000L ? v2 < 10000000000L ? v2 < 1000000000 ? 9 : 10 : v2 < 100000000000L ? 11 : 12 : v2 < 1000000000000000L ? v2 < 10000000000000L ? 13 : v2 < 100000000000000L ? 14 : 15 : v2 < 100000000000000000L ? v2 < 10000000000000000L ? 16 : 17 : v2 < 1000000000000000000L ? 18 : 19;
        if (negative) {
            width++;
        }
        Segment tail = writableSegment$jvm(width);
        byte[] data = tail.data;
        int pos = tail.limit + width;
        while (v2 != 0) {
            long j = (long) 10;
            pos--;
            data[pos] = DIGITS[(int) (v2 % j)];
            v2 /= j;
        }
        if (negative) {
            data[pos - 1] = (byte) 45;
        }
        tail.limit += width;
        this.size += (long) width;
        return this;
    }

    public Buffer writeHexadecimalUnsignedLong(long v) {
        long v2 = v;
        if (v2 == 0) {
            return writeByte(48);
        }
        int width = (Long.numberOfTrailingZeros(Long.highestOneBit(v2)) / 4) + 1;
        Segment tail = writableSegment$jvm(width);
        byte[] data = tail.data;
        int start = tail.limit;
        for (int pos = (tail.limit + width) - 1; pos >= start; pos--) {
            data[pos] = DIGITS[(int) (15 & v2)];
            v2 >>>= 4;
        }
        tail.limit += width;
        this.size += (long) width;
        return this;
    }

    public final Segment writableSegment$jvm(int minimumCapacity) {
        boolean z = true;
        if (minimumCapacity < 1 || minimumCapacity > 8192) {
            z = false;
        }
        if (z) {
            Segment segment = this.head;
            if (segment == null) {
                Segment result = SegmentPool.take();
                this.head = result;
                result.prev = result;
                result.next = result;
                return result;
            }
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            Segment tail = segment.prev;
            if (tail == null) {
                Intrinsics.throwNpe();
            }
            if (tail.limit + minimumCapacity > 8192 || !tail.owner) {
                tail = tail.push(SegmentPool.take());
            }
            return tail;
        }
        throw new IllegalArgumentException("unexpected capacity".toString());
    }

    public void write(Buffer source, long byteCount) {
        Segment tail;
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        long byteCount2 = byteCount;
        if (source != this) {
            Util.checkOffsetAndCount(source.size, 0, byteCount2);
            while (byteCount2 > 0) {
                Segment segment = source.head;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                int i = segment.limit;
                Segment segment2 = source.head;
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
                if (byteCount2 < ((long) (i - segment2.pos))) {
                    Segment segment3 = this.head;
                    if (segment3 != null) {
                        if (segment3 == null) {
                            Intrinsics.throwNpe();
                        }
                        tail = segment3.prev;
                    } else {
                        tail = null;
                    }
                    if (tail != null && tail.owner) {
                        if ((((long) tail.limit) + byteCount2) - ((long) (tail.shared ? 0 : tail.pos)) <= ((long) 8192)) {
                            Segment segment4 = source.head;
                            if (segment4 == null) {
                                Intrinsics.throwNpe();
                            }
                            segment4.writeTo(tail, (int) byteCount2);
                            source.size -= byteCount2;
                            this.size += byteCount2;
                            return;
                        }
                    }
                    Segment segment5 = source.head;
                    if (segment5 == null) {
                        Intrinsics.throwNpe();
                    }
                    source.head = segment5.split((int) byteCount2);
                }
                Segment segmentToMove = source.head;
                if (segmentToMove == null) {
                    Intrinsics.throwNpe();
                }
                long movedByteCount = (long) (segmentToMove.limit - segmentToMove.pos);
                source.head = segmentToMove.pop();
                Segment segment6 = this.head;
                if (segment6 == null) {
                    this.head = segmentToMove;
                    segmentToMove.prev = segmentToMove;
                    segmentToMove.next = segmentToMove.prev;
                } else {
                    if (segment6 == null) {
                        Intrinsics.throwNpe();
                    }
                    Segment tail2 = segment6.prev;
                    if (tail2 == null) {
                        Intrinsics.throwNpe();
                    }
                    tail2.push(segmentToMove).compact();
                }
                source.size -= movedByteCount;
                this.size += movedByteCount;
                byteCount2 -= movedByteCount;
            }
            return;
        }
        throw new IllegalArgumentException("source == this".toString());
    }

    public long read(Buffer sink, long byteCount) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        long byteCount2 = byteCount;
        if (byteCount2 >= 0) {
            long j = this.size;
            if (j == 0) {
                return -1;
            }
            if (byteCount2 > j) {
                byteCount2 = this.size;
            }
            sink.write(this, byteCount2);
            return byteCount2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("byteCount < 0: ");
        sb.append(byteCount2);
        throw new IllegalArgumentException(sb.toString().toString());
    }

    public long indexOf(byte b) {
        return indexOf(b, 0, LongCompanionObject.MAX_VALUE);
    }

    private final <T> T seek(long fromIndex, Function2<? super Segment, ? super Long, ? extends T> lambda) {
        Segment s = this.head;
        if (s == null) {
            return lambda.invoke(null, Long.valueOf(-1));
        }
        if (size() - fromIndex < fromIndex) {
            long offset = size();
            while (offset > fromIndex) {
                Segment segment = s.prev;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                s = segment;
                offset -= (long) (s.limit - s.pos);
            }
            return lambda.invoke(s, Long.valueOf(offset));
        }
        long offset2 = 0;
        while (true) {
            long nextOffset = ((long) (s.limit - s.pos)) + offset2;
            if (nextOffset > fromIndex) {
                return lambda.invoke(s, Long.valueOf(offset2));
            }
            Segment segment2 = s.next;
            if (segment2 == null) {
                Intrinsics.throwNpe();
            }
            s = segment2;
            offset2 = nextOffset;
        }
    }

    public long indexOf(byte b, long fromIndex) {
        return indexOf(b, fromIndex, LongCompanionObject.MAX_VALUE);
    }

    public long indexOf(byte b, long fromIndex, long toIndex) {
        byte b2 = b;
        long fromIndex2 = fromIndex;
        long toIndex2 = toIndex;
        boolean z = false;
        if (0 <= fromIndex2 && toIndex2 >= fromIndex2) {
            if (toIndex2 > this.size) {
                toIndex2 = this.size;
            }
            if (fromIndex2 == toIndex2) {
                return -1;
            }
            long fromIndex$iv = fromIndex2;
            Buffer this_$iv = this;
            boolean z2 = false;
            Segment s$iv = this_$iv.head;
            if (s$iv == null) {
                Segment segment = null;
                return -1;
            } else if (this_$iv.size() - fromIndex$iv < fromIndex$iv) {
                long offset$iv = this_$iv.size();
                while (offset$iv > fromIndex$iv) {
                    Segment segment2 = s$iv.prev;
                    if (segment2 == null) {
                        Intrinsics.throwNpe();
                    }
                    s$iv = segment2;
                    offset$iv -= (long) (s$iv.limit - s$iv.pos);
                }
                Segment s = s$iv;
                long offset = offset$iv;
                if (s == null) {
                    return -1;
                }
                Segment s2 = s;
                long offset2 = offset;
                while (offset2 < toIndex2) {
                    boolean z3 = z;
                    byte[] data = s2.data;
                    Segment s3 = s;
                    Buffer this_$iv2 = this_$iv;
                    boolean z4 = z2;
                    Segment s$iv2 = s$iv;
                    long offset$iv2 = offset$iv;
                    int limit = (int) Math.min((long) s2.limit, (((long) s2.pos) + toIndex2) - offset2);
                    for (int pos = (int) ((((long) s2.pos) + fromIndex2) - offset2); pos < limit; pos++) {
                        if (data[pos] == b2) {
                            return ((long) (pos - s2.pos)) + offset2;
                        }
                    }
                    offset2 += (long) (s2.limit - s2.pos);
                    fromIndex2 = offset2;
                    Segment segment3 = s2.next;
                    if (segment3 == null) {
                        Intrinsics.throwNpe();
                    }
                    s2 = segment3;
                    z = z3;
                    s = s3;
                    this_$iv = this_$iv2;
                    z2 = z4;
                    s$iv = s$iv2;
                    offset$iv = offset$iv2;
                }
                int $i$a$2$seek = z;
                return -1;
            } else {
                Buffer buffer = this_$iv;
                long offset$iv3 = 0;
                while (true) {
                    long nextOffset$iv = ((long) (s$iv.limit - s$iv.pos)) + offset$iv3;
                    if (nextOffset$iv > fromIndex$iv) {
                        break;
                    }
                    long j = offset$iv3;
                    long fromIndex$iv2 = fromIndex$iv;
                    Segment segment4 = s$iv.next;
                    if (segment4 == null) {
                        Intrinsics.throwNpe();
                    }
                    s$iv = segment4;
                    offset$iv3 = nextOffset$iv;
                    fromIndex$iv = fromIndex$iv2;
                }
                Segment s4 = s$iv;
                long offset3 = offset$iv3;
                if (s4 == null) {
                    return -1;
                }
                Segment s5 = s4;
                long offset4 = offset3;
                while (offset4 < toIndex2) {
                    boolean z5 = z;
                    byte[] data2 = s5.data;
                    long offset$iv4 = offset$iv3;
                    long fromIndex$iv3 = fromIndex$iv;
                    int limit2 = (int) Math.min((long) s5.limit, (((long) s5.pos) + toIndex2) - offset4);
                    for (int pos2 = (int) ((((long) s5.pos) + fromIndex2) - offset4); pos2 < limit2; pos2++) {
                        if (data2[pos2] == b2) {
                            return ((long) (pos2 - s5.pos)) + offset4;
                        }
                    }
                    offset4 += (long) (s5.limit - s5.pos);
                    fromIndex2 = offset4;
                    Segment segment5 = s5.next;
                    if (segment5 == null) {
                        Intrinsics.throwNpe();
                    }
                    s5 = segment5;
                    z = z5;
                    offset$iv3 = offset$iv4;
                    fromIndex$iv = fromIndex$iv3;
                }
                int $i$a$2$seek2 = z;
                return -1;
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("size=");
            sb.append(this.size);
            sb.append(" fromIndex=");
            sb.append(fromIndex2);
            sb.append(" toIndex=");
            sb.append(toIndex2);
            throw new IllegalArgumentException(sb.toString().toString());
        }
    }

    public long indexOf(ByteString bytes) throws IOException {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        return indexOf(bytes, 0);
    }

    public long indexOf(ByteString bytes, long fromIndex) throws IOException {
        byte[] data;
        byte b0;
        byte[] data2;
        Segment s$iv;
        byte b02;
        Buffer buffer = this;
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        long fromIndex2 = fromIndex;
        boolean z = true;
        if (bytes.size() > 0) {
            if (fromIndex2 < 0) {
                z = false;
            }
            if (z) {
                Buffer this_$iv = this;
                long fromIndex$iv = fromIndex2;
                Segment s$iv2 = this_$iv.head;
                if (s$iv2 == null) {
                    Segment segment = null;
                    return -1;
                } else if (this_$iv.size() - fromIndex$iv < fromIndex$iv) {
                    long offset$iv = this_$iv.size();
                    Segment s$iv3 = s$iv2;
                    while (offset$iv > fromIndex$iv) {
                        Segment segment2 = s$iv3.prev;
                        if (segment2 == null) {
                            Intrinsics.throwNpe();
                        }
                        s$iv3 = segment2;
                        offset$iv -= (long) (s$iv3.limit - s$iv3.pos);
                    }
                    Segment s = s$iv3;
                    long offset = offset$iv;
                    if (s == null) {
                        return -1;
                    }
                    Segment s2 = s;
                    long offset2 = offset;
                    byte[] targetByteArray = bytes.internalArray$jvm();
                    byte b03 = targetByteArray[0];
                    int bytesSize = bytes.size();
                    Segment s$iv4 = s$iv3;
                    long j = offset$iv;
                    long resultLimit = (buffer.size - ((long) bytesSize)) + 1;
                    long fromIndex3 = fromIndex2;
                    Segment s3 = s2;
                    boolean z2 = false;
                    while (offset2 < resultLimit) {
                        byte[] data3 = s3.data;
                        Buffer this_$iv2 = this_$iv;
                        boolean z3 = z2;
                        long resultLimit2 = resultLimit;
                        int segmentLimit = (int) Math.min((long) s3.limit, (((long) s3.pos) + resultLimit) - offset2);
                        int i = (int) ((((long) s3.pos) + fromIndex3) - offset2);
                        while (i < segmentLimit) {
                            int pos = i;
                            if (data3[pos] == b03) {
                                b02 = b03;
                                s$iv = s$iv4;
                                data2 = data3;
                                if (rangeEquals(s3, pos + 1, targetByteArray, 1, bytesSize)) {
                                    return ((long) (pos - s3.pos)) + offset2;
                                }
                            } else {
                                b02 = b03;
                                s$iv = s$iv4;
                                data2 = data3;
                            }
                            i = pos + 1;
                            b03 = b02;
                            data3 = data2;
                            s$iv4 = s$iv;
                        }
                        byte b04 = b03;
                        Segment s$iv5 = s$iv4;
                        byte[] bArr = data3;
                        offset2 += (long) (s3.limit - s3.pos);
                        fromIndex3 = offset2;
                        Segment segment3 = s3.next;
                        if (segment3 == null) {
                            Intrinsics.throwNpe();
                        }
                        s3 = segment3;
                        ByteString byteString = bytes;
                        b03 = b04;
                        s$iv4 = s$iv5;
                        this_$iv = this_$iv2;
                        z2 = z3;
                        resultLimit = resultLimit2;
                    }
                    return -1;
                } else {
                    Buffer buffer2 = this_$iv;
                    Segment s$iv6 = s$iv2;
                    long offset$iv2 = 0;
                    while (true) {
                        long nextOffset$iv = ((long) (s$iv6.limit - s$iv6.pos)) + offset$iv2;
                        if (nextOffset$iv > fromIndex$iv) {
                            break;
                        }
                        long j2 = offset$iv2;
                        Segment segment4 = s$iv6.next;
                        if (segment4 == null) {
                            Intrinsics.throwNpe();
                        }
                        s$iv6 = segment4;
                        offset$iv2 = nextOffset$iv;
                        buffer = this;
                    }
                    Segment s4 = s$iv6;
                    long offset3 = offset$iv2;
                    if (s4 == null) {
                        return -1;
                    }
                    Segment s5 = s4;
                    long offset4 = offset3;
                    byte[] targetByteArray2 = bytes.internalArray$jvm();
                    byte b05 = targetByteArray2[0];
                    int bytesSize2 = bytes.size();
                    long offset5 = offset4;
                    long j3 = offset$iv2;
                    long resultLimit3 = (buffer.size - ((long) bytesSize2)) + 1;
                    long fromIndex4 = fromIndex2;
                    Segment s6 = s5;
                    while (offset5 < resultLimit3) {
                        byte[] data4 = s6.data;
                        long resultLimit4 = resultLimit3;
                        int segmentLimit2 = (int) Math.min((long) s6.limit, (((long) s6.pos) + resultLimit3) - offset5);
                        int i2 = (int) ((((long) s6.pos) + fromIndex4) - offset5);
                        while (i2 < segmentLimit2) {
                            int pos2 = i2;
                            if (data4[pos2] == b05) {
                                data = data4;
                                b0 = b05;
                                if (rangeEquals(s6, pos2 + 1, targetByteArray2, 1, bytesSize2)) {
                                    return ((long) (pos2 - s6.pos)) + offset5;
                                }
                            } else {
                                data = data4;
                                b0 = b05;
                            }
                            i2 = pos2 + 1;
                            b05 = b0;
                            data4 = data;
                        }
                        byte b06 = b05;
                        offset5 += (long) (s6.limit - s6.pos);
                        fromIndex4 = offset5;
                        Segment segment5 = s6.next;
                        if (segment5 == null) {
                            Intrinsics.throwNpe();
                        }
                        s6 = segment5;
                        b05 = b06;
                        resultLimit3 = resultLimit4;
                    }
                    return -1;
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("fromIndex < 0: ");
                sb.append(fromIndex2);
                throw new IllegalArgumentException(sb.toString().toString());
            }
        } else {
            throw new IllegalArgumentException("bytes is empty".toString());
        }
    }

    public long indexOfElement(ByteString targetBytes) {
        Intrinsics.checkParameterIsNotNull(targetBytes, "targetBytes");
        return indexOfElement(targetBytes, 0);
    }

    public long indexOfElement(ByteString targetBytes, long fromIndex) {
        int pos;
        byte[] data;
        int pos2;
        Buffer buffer = this;
        ByteString byteString = targetBytes;
        Intrinsics.checkParameterIsNotNull(byteString, "targetBytes");
        long fromIndex2 = fromIndex;
        if (fromIndex2 >= 0) {
            long fromIndex$iv = fromIndex2;
            Buffer this_$iv = this;
            boolean z = false;
            Segment s$iv = this_$iv.head;
            if (s$iv == null) {
                Segment segment = null;
                return -1;
            } else if (this_$iv.size() - fromIndex$iv < fromIndex$iv) {
                long offset$iv = this_$iv.size();
                while (offset$iv > fromIndex$iv) {
                    Segment segment2 = s$iv.prev;
                    if (segment2 == null) {
                        Intrinsics.throwNpe();
                    }
                    s$iv = segment2;
                    offset$iv -= (long) (s$iv.limit - s$iv.pos);
                }
                Segment s = s$iv;
                long offset = offset$iv;
                if (s == null) {
                    return -1;
                }
                Segment s2 = s;
                long offset2 = offset;
                if (targetBytes.size() == 2) {
                    byte b0 = byteString.getByte(0);
                    byte b1 = byteString.getByte(1);
                    long fromIndex3 = fromIndex2;
                    Segment s3 = s2;
                    loop1:
                    while (true) {
                        Buffer this_$iv2 = this_$iv;
                        boolean z2 = z;
                        if (offset2 >= buffer.size) {
                            break;
                        }
                        data = s3.data;
                        pos2 = (int) ((((long) s3.pos) + fromIndex3) - offset2);
                        int limit = s3.limit;
                        while (pos2 < limit) {
                            byte b = data[pos2];
                            if (b == b0 || b == b1) {
                                byte[] bArr = data;
                                byte b2 = b0;
                            } else {
                                pos2++;
                            }
                        }
                        byte b02 = b0;
                        offset2 += (long) (s3.limit - s3.pos);
                        fromIndex3 = offset2;
                        Segment segment3 = s3.next;
                        if (segment3 == null) {
                            Intrinsics.throwNpe();
                        }
                        s3 = segment3;
                        this_$iv = this_$iv2;
                        z = z2;
                        b0 = b02;
                    }
                    byte[] bArr2 = data;
                    byte b22 = b0;
                    return ((long) (pos2 - s3.pos)) + offset2;
                }
                byte[] targetByteArray = targetBytes.internalArray$jvm();
                long fromIndex4 = fromIndex2;
                Segment s4 = s2;
                while (offset2 < buffer.size) {
                    byte[] data2 = s4.data;
                    int pos3 = (int) ((((long) s4.pos) + fromIndex4) - offset2);
                    int limit2 = s4.limit;
                    while (pos3 < limit2) {
                        byte b3 = data2[pos3];
                        int length = targetByteArray.length;
                        byte[] data3 = data2;
                        int i = 0;
                        while (i < length) {
                            int i2 = length;
                            if (b3 == targetByteArray[i]) {
                                byte[] bArr3 = targetByteArray;
                                return ((long) (pos3 - s4.pos)) + offset2;
                            }
                            i++;
                            length = i2;
                        }
                        pos3++;
                        data2 = data3;
                    }
                    byte[] targetByteArray2 = targetByteArray;
                    offset2 += (long) (s4.limit - s4.pos);
                    fromIndex4 = offset2;
                    Segment segment4 = s4.next;
                    if (segment4 == null) {
                        Intrinsics.throwNpe();
                    }
                    s4 = segment4;
                    targetByteArray = targetByteArray2;
                }
                return -1;
            } else {
                Buffer buffer2 = this_$iv;
                long offset$iv2 = 0;
                while (true) {
                    long nextOffset$iv = ((long) (s$iv.limit - s$iv.pos)) + offset$iv2;
                    if (nextOffset$iv > fromIndex$iv) {
                        break;
                    }
                    long j = offset$iv2;
                    long fromIndex$iv2 = fromIndex$iv;
                    Segment segment5 = s$iv.next;
                    if (segment5 == null) {
                        Intrinsics.throwNpe();
                    }
                    s$iv = segment5;
                    offset$iv2 = nextOffset$iv;
                    buffer = this;
                    byteString = targetBytes;
                    fromIndex$iv = fromIndex$iv2;
                }
                Segment s5 = s$iv;
                long offset3 = offset$iv2;
                if (s5 == null) {
                    return -1;
                }
                Segment s6 = s5;
                long offset4 = offset3;
                if (targetBytes.size() == 2) {
                    byte b03 = byteString.getByte(0);
                    byte b12 = byteString.getByte(1);
                    loop7:
                    while (true) {
                        long offset$iv3 = offset$iv2;
                        if (offset4 >= buffer.size) {
                            long j2 = fromIndex2;
                            long j3 = fromIndex$iv;
                            break;
                        }
                        byte[] data4 = s6.data;
                        long fromIndex$iv3 = fromIndex$iv;
                        pos = (int) ((((long) s6.pos) + fromIndex2) - offset4);
                        int limit3 = s6.limit;
                        while (pos < limit3) {
                            byte b4 = data4[pos];
                            if (b4 == b03 || b4 == b12) {
                                long j4 = fromIndex2;
                            } else {
                                pos++;
                            }
                        }
                        offset4 += (long) (s6.limit - s6.pos);
                        fromIndex2 = offset4;
                        Segment segment6 = s6.next;
                        if (segment6 == null) {
                            Intrinsics.throwNpe();
                        }
                        s6 = segment6;
                        ByteString byteString2 = targetBytes;
                        offset$iv2 = offset$iv3;
                        fromIndex$iv = fromIndex$iv3;
                    }
                    long j42 = fromIndex2;
                    return ((long) (pos - s6.pos)) + offset4;
                }
                long j5 = fromIndex$iv;
                byte[] targetByteArray3 = targetBytes.internalArray$jvm();
                while (offset4 < buffer.size) {
                    byte[] data5 = s6.data;
                    int pos4 = (int) ((((long) s6.pos) + fromIndex2) - offset4);
                    int limit4 = s6.limit;
                    while (pos4 < limit4) {
                        byte b5 = data5[pos4];
                        int length2 = targetByteArray3.length;
                        int i3 = 0;
                        while (i3 < length2) {
                            if (b5 == targetByteArray3[i3]) {
                                return ((long) (pos4 - s6.pos)) + offset4;
                            }
                            i3++;
                        }
                        pos4++;
                    }
                    offset4 += (long) (s6.limit - s6.pos);
                    fromIndex2 = offset4;
                    Segment segment7 = s6.next;
                    if (segment7 == null) {
                        Intrinsics.throwNpe();
                    }
                    s6 = segment7;
                    buffer = this;
                }
                return -1;
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("fromIndex < 0: ");
            sb.append(fromIndex2);
            throw new IllegalArgumentException(sb.toString().toString());
        }
    }

    public boolean rangeEquals(long offset, ByteString bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        return rangeEquals(offset, bytes, 0, bytes.size());
    }

    public boolean rangeEquals(long offset, ByteString bytes, int bytesOffset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        if (offset < 0 || bytesOffset < 0 || byteCount < 0 || this.size - offset < ((long) byteCount) || bytes.size() - bytesOffset < byteCount) {
            return false;
        }
        for (int i = 0; i < byteCount; i++) {
            if (getByte(((long) i) + offset) != bytes.getByte(bytesOffset + i)) {
                return false;
            }
        }
        return true;
    }

    private final boolean rangeEquals(Segment segment, int segmentPos, byte[] bytes, int bytesOffset, int bytesLimit) {
        Segment segment2 = segment;
        int segmentPos2 = segmentPos;
        int segmentLimit = segment2.limit;
        byte[] data = segment2.data;
        for (int i = bytesOffset; i < bytesLimit; i++) {
            if (segmentPos2 == segmentLimit) {
                Segment segment3 = segment2.next;
                if (segment3 == null) {
                    Intrinsics.throwNpe();
                }
                segment2 = segment3;
                data = segment2.data;
                segmentPos2 = segment2.pos;
                segmentLimit = segment2.limit;
            }
            if (data[segmentPos2] != bytes[i]) {
                return false;
            }
            segmentPos2++;
        }
        return true;
    }

    public void flush() {
    }

    public boolean isOpen() {
        return true;
    }

    public void close() {
    }

    public Timeout timeout() {
        return Timeout.NONE;
    }

    public final ByteString md5() {
        return digest("MD5");
    }

    public final ByteString sha1() {
        return digest("SHA-1");
    }

    public final ByteString sha256() {
        return digest("SHA-256");
    }

    public final ByteString sha512() {
        return digest("SHA-512");
    }

    private final ByteString digest(String algorithm) {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        Segment head2 = this.head;
        if (head2 != null) {
            messageDigest.update(head2.data, head2.pos, head2.limit - head2.pos);
            Segment s = head2.next;
            if (s == null) {
                Intrinsics.throwNpe();
            }
            while (s != head2) {
                messageDigest.update(s.data, s.pos, s.limit - s.pos);
                Segment segment = s.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                s = segment;
            }
        }
        byte[] digest = messageDigest.digest();
        Intrinsics.checkExpressionValueIsNotNull(digest, "messageDigest.digest()");
        return new ByteString(digest);
    }

    public final ByteString hmacSha1(ByteString key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return hmac("HmacSHA1", key);
    }

    public final ByteString hmacSha256(ByteString key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return hmac("HmacSHA256", key);
    }

    public final ByteString hmacSha512(ByteString key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return hmac("HmacSHA512", key);
    }

    private final ByteString hmac(String algorithm, ByteString key) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.internalArray$jvm(), algorithm));
            Segment head2 = this.head;
            if (head2 != null) {
                mac.update(head2.data, head2.pos, head2.limit - head2.pos);
                Segment s = head2.next;
                if (s == null) {
                    Intrinsics.throwNpe();
                }
                while (s != head2) {
                    mac.update(s.data, s.pos, s.limit - s.pos);
                    Segment segment = s.next;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    s = segment;
                }
            }
            byte[] doFinal = mac.doFinal();
            Intrinsics.checkExpressionValueIsNotNull(doFinal, "mac.doFinal()");
            return new ByteString(doFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean equals(Object other) {
        Object obj = other;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Buffer)) {
            return false;
        }
        long j = this.size;
        if (j != ((Buffer) obj).size) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        Segment sa = this.head;
        if (sa == null) {
            Intrinsics.throwNpe();
        }
        Segment sb = ((Buffer) obj).head;
        if (sb == null) {
            Intrinsics.throwNpe();
        }
        int posA = sa.pos;
        int posB = sb.pos;
        long pos = 0;
        while (pos < this.size) {
            long count = (long) Math.min(sa.limit - posA, sb.limit - posB);
            long i = j2;
            while (i < count) {
                int posA2 = posA + 1;
                int posB2 = posB + 1;
                if (sa.data[posA] != sb.data[posB]) {
                    return false;
                }
                i = 1 + i;
                posA = posA2;
                posB = posB2;
            }
            if (posA == sa.limit) {
                Segment segment = sa.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                sa = segment;
                posA = sa.pos;
            }
            if (posB == sb.limit) {
                Segment segment2 = sb.next;
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
                sb = segment2;
                posB = sb.pos;
            }
            pos += count;
            j2 = 0;
        }
        return true;
    }

    public int hashCode() {
        Segment s = this.head;
        if (s == null) {
            return 0;
        }
        int result = 1;
        do {
            for (int pos = s.pos; pos < s.limit; pos++) {
                result = (result * 31) + s.data[pos];
            }
            Segment segment = s.next;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            s = segment;
        } while (s != this.head);
        return result;
    }

    public String toString() {
        return snapshot().toString();
    }

    public Buffer clone() {
        Buffer result = new Buffer();
        if (this.size == 0) {
            return result;
        }
        Segment segment = this.head;
        if (segment == null) {
            Intrinsics.throwNpe();
        }
        result.head = segment.sharedCopy();
        Segment segment2 = result.head;
        if (segment2 == null) {
            Intrinsics.throwNpe();
        }
        Segment segment3 = result.head;
        segment2.prev = segment3;
        if (segment3 == null) {
            Intrinsics.throwNpe();
        }
        Segment segment4 = result.head;
        if (segment4 == null) {
            Intrinsics.throwNpe();
        }
        segment3.next = segment4.prev;
        Segment segment5 = this.head;
        if (segment5 == null) {
            Intrinsics.throwNpe();
        }
        for (Segment s = segment5.next; s != this.head; s = s.next) {
            Segment segment6 = result.head;
            if (segment6 == null) {
                Intrinsics.throwNpe();
            }
            Segment segment7 = segment6.prev;
            if (segment7 == null) {
                Intrinsics.throwNpe();
            }
            if (s == null) {
                Intrinsics.throwNpe();
            }
            segment7.push(s.sharedCopy());
        }
        result.size = this.size;
        return result;
    }

    public final ByteString snapshot() {
        if (this.size <= ((long) Integer.MAX_VALUE)) {
            return snapshot((int) this.size);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("size > Integer.MAX_VALUE: ");
        sb.append(this.size);
        throw new IllegalStateException(sb.toString().toString());
    }

    public final ByteString snapshot(int byteCount) {
        return byteCount == 0 ? ByteString.EMPTY : SegmentedByteString.Companion.mo27989of(this, byteCount);
    }

    public static /* bridge */ /* synthetic */ UnsafeCursor readUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int i, Object obj) {
        if ((i & 1) != 0) {
            unsafeCursor = new UnsafeCursor();
        }
        return buffer.readUnsafe(unsafeCursor);
    }

    public final UnsafeCursor readUnsafe(UnsafeCursor unsafeCursor) {
        Intrinsics.checkParameterIsNotNull(unsafeCursor, "unsafeCursor");
        if (unsafeCursor.buffer == null) {
            unsafeCursor.buffer = this;
            unsafeCursor.readWrite = false;
            return unsafeCursor;
        }
        throw new IllegalStateException("already attached to a buffer".toString());
    }

    public static /* bridge */ /* synthetic */ UnsafeCursor readAndWriteUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int i, Object obj) {
        if ((i & 1) != 0) {
            unsafeCursor = new UnsafeCursor();
        }
        return buffer.readAndWriteUnsafe(unsafeCursor);
    }

    public final UnsafeCursor readAndWriteUnsafe(UnsafeCursor unsafeCursor) {
        Intrinsics.checkParameterIsNotNull(unsafeCursor, "unsafeCursor");
        if (unsafeCursor.buffer == null) {
            unsafeCursor.buffer = this;
            unsafeCursor.readWrite = true;
            return unsafeCursor;
        }
        throw new IllegalStateException("already attached to a buffer".toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to operator function", replaceWith = @ReplaceWith(expression = "this[index]", imports = {}))
    /* renamed from: -deprecated_getByte reason: not valid java name */
    public final byte m2376deprecated_getByte(long index) {
        return getByte(index);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    /* renamed from: -deprecated_size reason: not valid java name */
    public final long m2377deprecated_size() {
        return this.size;
    }

    static {
        byte[] bytes = "0123456789abcdef".getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        DIGITS = bytes;
    }
}
