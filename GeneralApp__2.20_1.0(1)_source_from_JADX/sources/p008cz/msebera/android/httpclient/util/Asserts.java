package p008cz.msebera.android.httpclient.util;

/* renamed from: cz.msebera.android.httpclient.util.Asserts */
public class Asserts {
    public static void check(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void check(boolean expression, String message, Object... args) {
        if (!expression) {
            throw new IllegalStateException(String.format(message, args));
        }
    }

    public static void notNull(Object object, String name) {
        if (object == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(" is null");
            throw new IllegalStateException(sb.toString());
        }
    }

    public static void notEmpty(CharSequence s, String name) {
        if (TextUtils.isEmpty(s)) {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(" is empty");
            throw new IllegalStateException(sb.toString());
        }
    }

    public static void notBlank(CharSequence s, String name) {
        if (TextUtils.isBlank(s)) {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(" is blank");
            throw new IllegalStateException(sb.toString());
        }
    }
}
