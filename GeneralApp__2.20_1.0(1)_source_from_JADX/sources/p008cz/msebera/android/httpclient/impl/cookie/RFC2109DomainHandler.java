package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.Locale;
import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import p008cz.msebera.android.httpclient.cookie.CookieOrigin;
import p008cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.cookie.SetCookie;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.RFC2109DomainHandler */
public class RFC2109DomainHandler implements CookieAttributeHandler {
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
        if (domain == null) {
            throw new CookieRestrictionViolationException("Cookie domain may not be null");
        } else if (!domain.equals(host)) {
            String str = "\"";
            String str2 = "Domain attribute \"";
            if (domain.indexOf(46) == -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(domain);
                sb.append("\" does not match the host \"");
                sb.append(host);
                sb.append(str);
                throw new CookieRestrictionViolationException(sb.toString());
            } else if (domain.startsWith(".")) {
                int dotIndex = domain.indexOf(46, 1);
                if (dotIndex < 0 || dotIndex == domain.length() - 1) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str2);
                    sb2.append(domain);
                    sb2.append("\" violates RFC 2109: domain must contain an embedded dot");
                    throw new CookieRestrictionViolationException(sb2.toString());
                }
                String host2 = host.toLowerCase(Locale.ENGLISH);
                if (!host2.endsWith(domain)) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Illegal domain attribute \"");
                    sb3.append(domain);
                    sb3.append("\". Domain of origin: \"");
                    sb3.append(host2);
                    sb3.append(str);
                    throw new CookieRestrictionViolationException(sb3.toString());
                } else if (host2.substring(0, host2.length() - domain.length()).indexOf(46) != -1) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(str2);
                    sb4.append(domain);
                    sb4.append("\" violates RFC 2109: host minus domain may not contain any dots");
                    throw new CookieRestrictionViolationException(sb4.toString());
                }
            } else {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str2);
                sb5.append(domain);
                sb5.append("\" violates RFC 2109: domain must start with a dot");
                throw new CookieRestrictionViolationException(sb5.toString());
            }
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
        if (host.equals(domain) || (domain.startsWith(".") && host.endsWith(domain))) {
            z = true;
        }
        return z;
    }
}
