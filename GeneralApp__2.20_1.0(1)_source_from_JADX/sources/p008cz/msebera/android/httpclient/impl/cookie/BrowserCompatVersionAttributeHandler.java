package p008cz.msebera.android.httpclient.impl.cookie;

import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.cookie.SetCookie;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.BrowserCompatVersionAttributeHandler */
public class BrowserCompatVersionAttributeHandler extends AbstractCookieAttributeHandler {
    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        if (value != null) {
            int version = 0;
            try {
                version = Integer.parseInt(value);
            } catch (NumberFormatException e) {
            }
            cookie.setVersion(version);
            return;
        }
        throw new MalformedCookieException("Missing value for version attribute");
    }
}
