package p008cz.msebera.android.httpclient.impl.cookie;

import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import p008cz.msebera.android.httpclient.cookie.CookieOrigin;
import p008cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.cookie.SetCookie;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.TextUtils;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.BasicPathHandler */
public class BasicPathHandler implements CookieAttributeHandler {
    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        cookie.setPath(!TextUtils.isBlank(value) ? value : "/");
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        if (!match(cookie, origin)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Illegal path attribute \"");
            sb.append(cookie.getPath());
            sb.append("\". Path of origin: \"");
            sb.append(origin.getPath());
            sb.append("\"");
            throw new CookieRestrictionViolationException(sb.toString());
        }
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull(cookie, C0971SM.COOKIE);
        Args.notNull(origin, "Cookie origin");
        String targetpath = origin.getPath();
        String topmostPath = cookie.getPath();
        if (topmostPath == null) {
            topmostPath = "/";
        }
        boolean z = false;
        String str = "/";
        if (topmostPath.length() > 1 && topmostPath.endsWith(str)) {
            topmostPath = topmostPath.substring(0, topmostPath.length() - 1);
        }
        boolean match = targetpath.startsWith(topmostPath);
        if (!match || targetpath.length() == topmostPath.length() || topmostPath.endsWith(str)) {
            return match;
        }
        if (targetpath.charAt(topmostPath.length()) == '/') {
            z = true;
        }
        return z;
    }
}
