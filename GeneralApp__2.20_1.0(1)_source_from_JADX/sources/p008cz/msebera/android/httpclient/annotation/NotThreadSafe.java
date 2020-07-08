package p008cz.msebera.android.httpclient.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
/* renamed from: cz.msebera.android.httpclient.annotation.NotThreadSafe */
public @interface NotThreadSafe {
}
