package p008cz.msebera.android.httpclient.impl.cookie;

import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;
import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import p008cz.msebera.android.httpclient.cookie.CookieOrigin;
import p008cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.cookie.SetCookie;
import p008cz.msebera.android.httpclient.cookie.SetCookie2;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.RFC2965VersionAttributeHandler */
public class RFC2965VersionAttributeHandler implements CookieAttributeHandler {
    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        int version;
        Args.notNull(cookie, C0971SM.COOKIE);
        if (value != null) {
            try {
                version = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                version = -1;
            }
            if (version >= 0) {
                cookie.setVersion(version);
                return;
            }
            throw new MalformedCookieException("Invalid cookie version.");
        }
        throw new MalformedCookieException("Missing value for version attribute");
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        if ((cookie instanceof SetCookie2) && (cookie instanceof ClientCookie) && !((ClientCookie) cookie).containsAttribute(ClientCookie.VERSION_ATTR)) {
            throw new CookieRestrictionViolationException("Violates RFC 2965. Version attribute is required.");
        }
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        return true;
    }
}
