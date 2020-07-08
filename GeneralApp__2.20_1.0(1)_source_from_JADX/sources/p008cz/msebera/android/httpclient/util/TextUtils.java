package p008cz.msebera.android.httpclient.util;

/* renamed from: cz.msebera.android.httpclient.util.TextUtils */
public final class TextUtils {
    public static boolean isEmpty(CharSequence s) {
        boolean z = true;
        if (s == null) {
            return true;
        }
        if (s.length() != 0) {
            z = false;
        }
        return z;
    }

    public static boolean isBlank(CharSequence s) {
        if (s == null) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
