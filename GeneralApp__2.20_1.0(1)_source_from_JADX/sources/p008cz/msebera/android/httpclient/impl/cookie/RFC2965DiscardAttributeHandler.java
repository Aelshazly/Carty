package p008cz.msebera.android.httpclient.impl.cookie;

import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import p008cz.msebera.android.httpclient.cookie.CookieOrigin;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.cookie.SetCookie;
import p008cz.msebera.android.httpclient.cookie.SetCookie2;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.RFC2965DiscardAttributeHandler */
public class RFC2965DiscardAttributeHandler implements CookieAttributeHandler {
    public void parse(SetCookie cookie, String commenturl) throws MalformedCookieException {
        if (cookie instanceof SetCookie2) {
            ((SetCookie2) cookie).setDiscard(true);
        }
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        return true;
    }
}
