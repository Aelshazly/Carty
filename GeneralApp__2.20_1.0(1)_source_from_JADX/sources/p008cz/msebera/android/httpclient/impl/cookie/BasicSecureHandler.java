package p008cz.msebera.android.httpclient.impl.cookie;

import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieOrigin;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.cookie.SetCookie;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.BasicSecureHandler */
public class BasicSecureHandler extends AbstractCookieAttributeHandler {
    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        cookie.setSecure(true);
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull(cookie, C0971SM.COOKIE);
        Args.notNull(origin, "Cookie origin");
        return !cookie.isSecure() || origin.isSecure();
    }
}
