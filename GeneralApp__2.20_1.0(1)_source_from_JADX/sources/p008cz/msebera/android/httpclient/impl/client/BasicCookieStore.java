package p008cz.msebera.android.httpclient.impl.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import p008cz.msebera.android.httpclient.client.CookieStore;
import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieIdentityComparator;

/* renamed from: cz.msebera.android.httpclient.impl.client.BasicCookieStore */
public class BasicCookieStore implements CookieStore, Serializable {
    private static final long serialVersionUID = -7581093305228232025L;
    private final TreeSet<Cookie> cookies = new TreeSet<>(new CookieIdentityComparator());

    public synchronized void addCookie(Cookie cookie) {
        if (cookie != null) {
            this.cookies.remove(cookie);
            if (!cookie.isExpired(new Date())) {
                this.cookies.add(cookie);
            }
        }
    }

    public synchronized void addCookies(Cookie[] cookies2) {
        if (cookies2 != null) {
            for (Cookie cooky : cookies2) {
                addCookie(cooky);
            }
        }
    }

    public synchronized List<Cookie> getCookies() {
        return new ArrayList(this.cookies);
    }

    public synchronized boolean clearExpired(Date date) {
        if (date == null) {
            return false;
        }
        boolean removed = false;
        Iterator<Cookie> it = this.cookies.iterator();
        while (it.hasNext()) {
            if (((Cookie) it.next()).isExpired(date)) {
                it.remove();
                removed = true;
            }
        }
        return removed;
    }

    public synchronized void clear() {
        this.cookies.clear();
    }

    public synchronized String toString() {
        return this.cookies.toString();
    }
}
