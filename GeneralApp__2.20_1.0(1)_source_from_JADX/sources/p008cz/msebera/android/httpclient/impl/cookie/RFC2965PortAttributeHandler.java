package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.StringTokenizer;
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

/* renamed from: cz.msebera.android.httpclient.impl.cookie.RFC2965PortAttributeHandler */
public class RFC2965PortAttributeHandler implements CookieAttributeHandler {
    private static int[] parsePortAttribute(String portValue) throws MalformedCookieException {
        StringTokenizer st = new StringTokenizer(portValue, ",");
        int[] ports = new int[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            try {
                ports[i] = Integer.parseInt(st.nextToken().trim());
                if (ports[i] >= 0) {
                    i++;
                } else {
                    throw new MalformedCookieException("Invalid Port attribute.");
                }
            } catch (NumberFormatException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid Port attribute: ");
                sb.append(e.getMessage());
                throw new MalformedCookieException(sb.toString());
            }
        }
        return ports;
    }

    private static boolean portMatch(int port, int[] ports) {
        for (int port2 : ports) {
            if (port == port2) {
                return true;
            }
        }
        return false;
    }

    public void parse(SetCookie cookie, String portValue) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        if (cookie instanceof SetCookie2) {
            SetCookie2 cookie2 = (SetCookie2) cookie;
            if (portValue != null && portValue.trim().length() > 0) {
                cookie2.setPorts(parsePortAttribute(portValue));
            }
        }
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        Args.notNull(origin, "Cookie origin");
        int port = origin.getPort();
        if ((cookie instanceof ClientCookie) && ((ClientCookie) cookie).containsAttribute(ClientCookie.PORT_ATTR) && !portMatch(port, cookie.getPorts())) {
            throw new CookieRestrictionViolationException("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
        }
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull(cookie, C0971SM.COOKIE);
        Args.notNull(origin, "Cookie origin");
        int port = origin.getPort();
        if (!(cookie instanceof ClientCookie) || !((ClientCookie) cookie).containsAttribute(ClientCookie.PORT_ATTR) || (cookie.getPorts() != null && portMatch(port, cookie.getPorts()))) {
            return true;
        }
        return false;
    }
}
