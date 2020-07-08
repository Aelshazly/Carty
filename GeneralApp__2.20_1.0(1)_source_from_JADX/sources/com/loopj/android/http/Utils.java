package com.loopj.android.http;

class Utils {
    private Utils() {
    }

    public static void asserts(boolean expression, String failedMessage) {
        if (!expression) {
            throw new AssertionError(failedMessage);
        }
    }

    public static <T> T notNull(T argument, String name) {
        if (argument != null) {
            return argument;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" should not be null!");
        throw new IllegalArgumentException(sb.toString());
    }
}
