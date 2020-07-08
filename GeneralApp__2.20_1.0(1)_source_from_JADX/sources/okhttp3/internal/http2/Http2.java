package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okio.ByteString;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u000bJ.\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u000bR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0014\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000¨\u0006'"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2;", "", "()V", "BINARY", "", "", "[Ljava/lang/String;", "CONNECTION_PREFACE", "Lokio/ByteString;", "FLAGS", "FLAG_ACK", "", "FLAG_COMPRESSED", "FLAG_END_HEADERS", "FLAG_END_PUSH_PROMISE", "FLAG_END_STREAM", "FLAG_NONE", "FLAG_PADDED", "FLAG_PRIORITY", "FRAME_NAMES", "INITIAL_MAX_FRAME_SIZE", "TYPE_CONTINUATION", "TYPE_DATA", "TYPE_GOAWAY", "TYPE_HEADERS", "TYPE_PING", "TYPE_PRIORITY", "TYPE_PUSH_PROMISE", "TYPE_RST_STREAM", "TYPE_SETTINGS", "TYPE_WINDOW_UPDATE", "formatFlags", "type", "flags", "frameLog", "inbound", "", "streamId", "length", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Http2.kt */
public final class Http2 {
    private static final String[] BINARY;
    public static final ByteString CONNECTION_PREFACE = ByteString.Companion.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    private static final String[] FLAGS = new String[64];
    public static final int FLAG_ACK = 1;
    public static final int FLAG_COMPRESSED = 32;
    public static final int FLAG_END_HEADERS = 4;
    public static final int FLAG_END_PUSH_PROMISE = 4;
    public static final int FLAG_END_STREAM = 1;
    public static final int FLAG_NONE = 0;
    public static final int FLAG_PADDED = 8;
    public static final int FLAG_PRIORITY = 32;
    private static final String[] FRAME_NAMES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
    public static final int INITIAL_MAX_FRAME_SIZE = 16384;
    public static final Http2 INSTANCE = new Http2();
    public static final int TYPE_CONTINUATION = 9;
    public static final int TYPE_DATA = 0;
    public static final int TYPE_GOAWAY = 7;
    public static final int TYPE_HEADERS = 1;
    public static final int TYPE_PING = 6;
    public static final int TYPE_PRIORITY = 2;
    public static final int TYPE_PUSH_PROMISE = 5;
    public static final int TYPE_RST_STREAM = 3;
    public static final int TYPE_SETTINGS = 4;
    public static final int TYPE_WINDOW_UPDATE = 8;

    static {
        String str;
        int[] frameFlags;
        String[] strArr = new String[256];
        for (int i = 0; i < 256; i++) {
            String binaryString = Integer.toBinaryString(i);
            Intrinsics.checkExpressionValueIsNotNull(binaryString, "Integer.toBinaryString(it)");
            strArr[i] = StringsKt.replace$default(Util.format("%8s", binaryString), ' ', '0', false, 4, (Object) null);
        }
        BINARY = strArr;
        String[] strArr2 = FLAGS;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] prefixFlags = {1};
        strArr2[8] = "PADDED";
        int length = prefixFlags.length;
        int i2 = 0;
        while (true) {
            str = "|PADDED";
            if (i2 >= length) {
                break;
            }
            int prefixFlag = prefixFlags[i2];
            String[] strArr3 = FLAGS;
            strArr3[prefixFlag | 8] = Intrinsics.stringPlus(strArr3[prefixFlag], str);
            i2++;
        }
        String[] strArr4 = FLAGS;
        strArr4[4] = "END_HEADERS";
        strArr4[32] = "PRIORITY";
        strArr4[36] = "END_HEADERS|PRIORITY";
        for (int frameFlag : new int[]{4, 32, 36}) {
            for (int prefixFlag2 : prefixFlags) {
                String[] strArr5 = FLAGS;
                int i3 = prefixFlag2 | frameFlag;
                StringBuilder sb = new StringBuilder();
                sb.append(FLAGS[prefixFlag2]);
                String str2 = "|";
                sb.append(str2);
                sb.append(FLAGS[frameFlag]);
                strArr5[i3] = sb.toString();
                String[] strArr6 = FLAGS;
                int i4 = prefixFlag2 | frameFlag | 8;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(FLAGS[prefixFlag2]);
                sb2.append(str2);
                sb2.append(FLAGS[frameFlag]);
                sb2.append(str);
                strArr6[i4] = sb2.toString();
            }
        }
        int length2 = FLAGS.length;
        for (int i5 = 0; i5 < length2; i5++) {
            String[] strArr7 = FLAGS;
            if (strArr7[i5] == null) {
                strArr7[i5] = BINARY[i5];
            }
        }
    }

    private Http2() {
    }

    public final String frameLog(boolean inbound, int streamId, int length, int type, int flags) {
        String[] strArr = FRAME_NAMES;
        return Util.format("%s 0x%08x %5d %-13s %s", inbound ? "<<" : ">>", Integer.valueOf(streamId), Integer.valueOf(length), type < strArr.length ? strArr[type] : Util.format("0x%02x", Integer.valueOf(type)), formatFlags(type, flags));
    }

    public final String formatFlags(int type, int flags) {
        String str;
        String str2;
        if (flags == 0) {
            return "";
        }
        if (!(type == 2 || type == 3)) {
            if (type == 4 || type == 6) {
                return flags == 1 ? "ACK" : BINARY[flags];
            } else if (!(type == 7 || type == 8)) {
                String[] strArr = FLAGS;
                if (flags < strArr.length) {
                    str = strArr[flags];
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                } else {
                    str = BINARY[flags];
                }
                String result = str;
                if (type == 5 && (flags & 4) != 0) {
                    str2 = StringsKt.replace$default(result, "HEADERS", "PUSH_PROMISE", false, 4, (Object) null);
                } else if (type != 0 || (flags & 32) == 0) {
                    str2 = result;
                } else {
                    str2 = StringsKt.replace$default(result, "PRIORITY", "COMPRESSED", false, 4, (Object) null);
                }
                return str2;
            }
        }
        return BINARY[flags];
    }
}