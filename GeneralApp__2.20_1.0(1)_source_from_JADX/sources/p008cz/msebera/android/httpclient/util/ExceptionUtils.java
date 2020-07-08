package p008cz.msebera.android.httpclient.util;

import java.lang.reflect.Method;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.util.ExceptionUtils */
public final class ExceptionUtils {
    private static final Method INIT_CAUSE_METHOD = getInitCauseMethod();

    private static Method getInitCauseMethod() {
        try {
            return Throwable.class.getMethod("initCause", new Class[]{Throwable.class});
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static void initCause(Throwable throwable, Throwable cause) {
        Method method = INIT_CAUSE_METHOD;
        if (method != null) {
            try {
                method.invoke(throwable, new Object[]{cause});
            } catch (Exception e) {
            }
        }
    }

    private ExceptionUtils() {
    }
}
