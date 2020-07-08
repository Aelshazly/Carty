package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.Locale;
import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;
import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import p008cz.msebera.android.httpclient.cookie.CookieOrigin;
import p008cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.cookie.SetCookie;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.RFC2965DomainAttributeHandler */
public class RFC2965DomainAttributeHandler implements CookieAttributeHandler {
    public void parse(SetCookie cookie, String domain) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        if (domain == null) {
            throw new MalformedCookieException("Missing value for domain attribute");
        } else if (domain.trim().length() != 0) {
            String s = domain.toLowerCase(Locale.ENGLISH);
            if (!domain.startsWith(".")) {
                StringBuilder sb = new StringBuilder();
                sb.append('.');
                sb.append(s);
                s = sb.toString();
            }
            cookie.setDomain(s);
        } else {
            throw new MalformedCookieException("Blank value for domain attribute");
        }
    }

    public boolean domainMatch(String host, String domain) {
        return host.equals(domain) || (domain.startsWith(".") && host.endsWith(domain));
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        Args.notNull(origin, "Cookie origin");
        String host = origin.getHost().toLowerCase(Locale.ENGLISH);
        if (cookie.getDomain() != null) {
            String cookieDomain = cookie.getDomain().toLowerCase(Locale.ENGLISH);
            if ((cookie instanceof ClientCookie) && ((ClientCookie) cookie).containsAttribute(ClientCookie.DOMAIN_ATTR)) {
                String str = "Domain attribute \"";
                if (cookieDomain.startsWith(".")) {
                    int dotIndex = cookieDomain.indexOf(46, 1);
                    if ((dotIndex < 0 || dotIndex == cookieDomain.length() - 1) && !cookieDomain.equals(".local")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append(cookie.getDomain());
                        sb.append("\" violates RFC 2965: the value contains no embedded dots ");
                        sb.append("and the value is not .local");
                        throw new CookieRestrictionViolationException(sb.toString());
                    } else if (!domainMatch(host, cookieDomain)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(cookie.getDomain());
                        sb2.append("\" violates RFC 2965: effective host name does not ");
                        sb2.append("domain-match domain attribute.");
                        throw new CookieRestrictionViolationException(sb2.toString());
                    } else if (host.substring(0, host.length() - cookieDomain.length()).indexOf(46) != -1) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str);
                        sb3.append(cookie.getDomain());
                        sb3.append("\" violates RFC 2965: ");
                        sb3.append("effective host minus domain may not contain any dots");
                        throw new CookieRestrictionViolationException(sb3.toString());
                    }
                } else {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(str);
                    sb4.append(cookie.getDomain());
                    sb4.append("\" violates RFC 2109: domain must start with a dot");
                    throw new CookieRestrictionViolationException(sb4.toString());
                }
            } else if (!cookie.getDomain().equals(host)) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Illegal domain attribute: \"");
                sb5.append(cookie.getDomain());
                sb5.append("\".");
                sb5.append("Domain of origin: \"");
                sb5.append(host);
                sb5.append("\"");
                throw new CookieRestrictionViolationException(sb5.toString());
            }
        } else {
            throw new CookieRestrictionViolationException("Invalid cookie state: domain not specified");
        }
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull(cookie, C0971SM.COOKIE);
        Args.notNull(origin, "Cookie origin");
        String host = origin.getHost().toLowerCase(Locale.ENGLISH);
        String cookieDomain = cookie.getDomain();
        boolean z = false;
        if (!domainMatch(host, cookieDomain)) {
            return false;
        }
        if (host.substring(0, host.length() - cookieDomain.length()).indexOf(46) == -1) {
            z = true;
        }
        return z;
    }
}
