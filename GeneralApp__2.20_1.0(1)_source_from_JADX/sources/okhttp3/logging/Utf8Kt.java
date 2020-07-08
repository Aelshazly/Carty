package okhttp3.logging;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import okio.Buffer;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, mo24952d2 = {"isProbablyUtf8", "", "Lokio/Buffer;", "okhttp-logging-interceptor"}, mo24953k = 2, mo24954mv = {1, 1, 15})
/* compiled from: utf8.kt */
public final class Utf8Kt {
    public static final boolean isProbablyUtf8(Buffer $this$isProbablyUtf8) {
        Intrinsics.checkParameterIsNotNull($this$isProbablyUtf8, "$this$isProbablyUtf8");
        try {
            Buffer prefix = new Buffer();
            $this$isProbablyUtf8.copyTo(prefix, 0, RangesKt.coerceAtMost($this$isProbablyUtf8.size(), 64));
            int i = 0;
            while (true) {
                if (i >= 16) {
                    break;
                } else if (prefix.exhausted()) {
                    break;
                } else {
                    int codePoint = prefix.readUtf8CodePoint();
                    if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                        return false;
                    }
                    i++;
                }
            }
            return true;
        } catch (EOFException e) {
            return false;
        }
    }
}
