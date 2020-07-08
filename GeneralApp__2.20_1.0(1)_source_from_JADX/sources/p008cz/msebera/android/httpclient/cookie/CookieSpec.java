package p008cz.msebera.android.httpclient.cookie;

import java.util.List;
import p008cz.msebera.android.httpclient.Header;

/* renamed from: cz.msebera.android.httpclient.cookie.CookieSpec */
public interface CookieSpec {
    List<Header> formatCookies(List<Cookie> list);

    int getVersion();

    Header getVersionHeader();

    boolean match(Cookie cookie, CookieOrigin cookieOrigin);

    List<Cookie> parse(Header header, CookieOrigin cookieOrigin) throws MalformedCookieException;

    void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException;
}
