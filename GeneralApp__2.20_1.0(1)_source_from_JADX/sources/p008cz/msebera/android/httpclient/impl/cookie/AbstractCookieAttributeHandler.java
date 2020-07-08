package p008cz.msebera.android.httpclient.impl.cookie;

import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import p008cz.msebera.android.httpclient.cookie.CookieOrigin;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.AbstractCookieAttributeHandler */
public abstract class AbstractCookieAttributeHandler implements CookieAttributeHandler {
    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        return true;
    }
}
