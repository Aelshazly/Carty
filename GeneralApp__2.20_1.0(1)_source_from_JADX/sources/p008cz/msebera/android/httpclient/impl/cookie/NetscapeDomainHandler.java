package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.Locale;
import java.util.StringTokenizer;
import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieOrigin;
import p008cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.NetscapeDomainHandler */
public class NetscapeDomainHandler extends BasicDomainHandler {
    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        super.validate(cookie, origin);
        String host = origin.getHost();
        String domain = cookie.getDomain();
        String str = ".";
        if (host.contains(str)) {
            int domainParts = new StringTokenizer(domain, str).countTokens();
            String str2 = "Domain attribute \"";
            if (isSpecialDomain(domain)) {
                if (domainParts < 2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append(domain);
                    sb.append("\" violates the Netscape cookie specification for ");
                    sb.append("special domains");
                    throw new CookieRestrictionViolationException(sb.toString());
                }
            } else if (domainParts < 3) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append(domain);
                sb2.append("\" violates the Netscape cookie specification");
                throw new CookieRestrictionViolationException(sb2.toString());
            }
        }
    }

    private static boolean isSpecialDomain(String domain) {
        String ucDomain = domain.toUpperCase(Locale.ENGLISH);
        return ucDomain.endsWith(".COM") || ucDomain.endsWith(".EDU") || ucDomain.endsWith(".NET") || ucDomain.endsWith(".GOV") || ucDomain.endsWith(".MIL") || ucDomain.endsWith(".ORG") || ucDomain.endsWith(".INT");
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull(cookie, C0971SM.COOKIE);
        Args.notNull(origin, "Cookie origin");
        String host = origin.getHost();
        String domain = cookie.getDomain();
        if (domain == null) {
            return false;
        }
        return host.endsWith(domain);
    }
}
