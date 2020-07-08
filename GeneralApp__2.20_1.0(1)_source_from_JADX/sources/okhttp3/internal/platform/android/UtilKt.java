package okhttp3.internal.platform.android;

import android.util.Log;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\t"}, mo24952d2 = {"MAX_LOG_LENGTH", "", "androidLog", "", "level", "message", "", "t", "", "okhttp"}, mo24953k = 2, mo24954mv = {1, 1, 15})
/* compiled from: util.kt */
public final class UtilKt {
    private static final int MAX_LOG_LENGTH = 4000;

    public static final void androidLog(int level, String message, Throwable t) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        String logMessage = message;
        int logLevel = 5;
        if (level != 5) {
            logLevel = 3;
        }
        if (t != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(logMessage);
            sb.append("\n");
            sb.append(Log.getStackTraceString(t));
            logMessage = sb.toString();
        }
        int i = 0;
        int length = logMessage.length();
        while (i < length) {
            int newline = StringsKt.indexOf$default((CharSequence) logMessage, 10, i, false, 4, (Object) null);
            int newline2 = newline != -1 ? newline : length;
            do {
                int end = Math.min(newline2, i + MAX_LOG_LENGTH);
                if (logMessage != null) {
                    String substring = logMessage.substring(i, end);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    Log.println(logLevel, "OkHttp", substring);
                    i = end;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            } while (i < newline2);
            i++;
        }
    }
}
