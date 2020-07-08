package p008cz.msebera.android.httpclient.util;

import java.util.Collection;

/* renamed from: cz.msebera.android.httpclient.util.Args */
public class Args {
    public static void check(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void check(boolean expression, String message, Object... args) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, args));
        }
    }

    public static <T> T notNull(T argument, String name) {
        if (argument != null) {
            return argument;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" may not be null");
        throw new IllegalArgumentException(sb.toString());
    }

    public static <T extends CharSequence> T notEmpty(T argument, String name) {
        if (argument == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(" may not be null");
            throw new IllegalArgumentException(sb.toString());
        } else if (!TextUtils.isEmpty(argument)) {
            return argument;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(name);
            sb2.append(" may not be empty");
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public static <T extends CharSequence> T notBlank(T argument, String name) {
        if (argument == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(" may not be null");
            throw new IllegalArgumentException(sb.toString());
        } else if (!TextUtils.isBlank(argument)) {
            return argument;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(name);
            sb2.append(" may not be blank");
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public static <E, T extends Collection<E>> T notEmpty(T argument, String name) {
        if (argument == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(" may not be null");
            throw new IllegalArgumentException(sb.toString());
        } else if (!argument.isEmpty()) {
            return argument;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(name);
            sb2.append(" may not be empty");
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public static int positive(int n, String name) {
        if (n > 0) {
            return n;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" may not be negative or zero");
        throw new IllegalArgumentException(sb.toString());
    }

    public static long positive(long n, String name) {
        if (n > 0) {
            return n;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" may not be negative or zero");
        throw new IllegalArgumentException(sb.toString());
    }

    public static int notNegative(int n, String name) {
        if (n >= 0) {
            return n;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" may not be negative");
        throw new IllegalArgumentException(sb.toString());
    }

    public static long notNegative(long n, String name) {
        if (n >= 0) {
            return n;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" may not be negative");
        throw new IllegalArgumentException(sb.toString());
    }
}
