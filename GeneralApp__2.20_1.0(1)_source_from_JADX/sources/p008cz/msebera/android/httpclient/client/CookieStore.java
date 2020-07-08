package p008cz.msebera.android.httpclient.client;

import java.util.Date;
import java.util.List;
import p008cz.msebera.android.httpclient.cookie.Cookie;

/* renamed from: cz.msebera.android.httpclient.client.CookieStore */
public interface CookieStore {
    void addCookie(Cookie cookie);

    void clear();

    boolean clearExpired(Date date);

    List<Cookie> getCookies();
}
