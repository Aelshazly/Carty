package p008cz.msebera.android.httpclient.cookie;

/* renamed from: cz.msebera.android.httpclient.cookie.CookieAttributeHandler */
public interface CookieAttributeHandler {
    boolean match(Cookie cookie, CookieOrigin cookieOrigin);

    void parse(SetCookie setCookie, String str) throws MalformedCookieException;

    void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException;
}
