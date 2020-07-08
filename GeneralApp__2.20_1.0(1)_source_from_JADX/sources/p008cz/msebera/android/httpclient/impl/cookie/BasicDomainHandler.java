package p008cz.msebera.android.httpclient.impl.cookie;

import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import p008cz.msebera.android.httpclient.cookie.CookieOrigin;
import p008cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.cookie.SetCookie;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.BasicDomainHandler */
public class BasicDomainHandler implements CookieAttributeHandler {
    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        if (value == null) {
            throw new MalformedCookieException("Missing value for domain attribute");
        } else if (value.trim().length() != 0) {
            cookie.setDomain(value);
        } else {
            throw new MalformedCookieException("Blank value for domain attribute");
        }
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        Args.notNull(origin, "Cookie origin");
        String host = origin.getHost();
        String domain = cookie.getDomain();
        if (domain != null) {
            String str = ".";
            String str2 = "\"";
            String str3 = "\". Domain of origin: \"";
            String str4 = "Illegal domain attribute \"";
            if (host.contains(str)) {
                if (!host.endsWith(domain)) {
                    if (domain.startsWith(str)) {
                        domain = domain.substring(1, domain.length());
                    }
                    if (!host.equals(domain)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str4);
                        sb.append(domain);
                        sb.append(str3);
                        sb.append(host);
                        sb.append(str2);
                        throw new CookieRestrictionViolationException(sb.toString());
                    }
                }
            } else if (!host.equals(domain)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str4);
                sb2.append(domain);
                sb2.append(str3);
                sb2.append(host);
                sb2.append(str2);
                throw new CookieRestrictionViolationException(sb2.toString());
            }
        } else {
            throw new CookieRestrictionViolationException("Cookie domain may not be null");
        }
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull(cookie, C0971SM.COOKIE);
        Args.notNull(origin, "Cookie origin");
        String host = origin.getHost();
        String domain = cookie.getDomain();
        boolean z = false;
        if (domain == null) {
            return false;
        }
        if (host.equals(domain)) {
            return true;
        }
        if (!domain.startsWith(".")) {
            StringBuilder sb = new StringBuilder();
            sb.append('.');
            sb.append(domain);
            domain = sb.toString();
        }
        if (host.endsWith(domain) || host.equals(domain.substring(1))) {
            z = true;
        }
        return z;
    }
}
