package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.Date;
import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.cookie.SetCookie;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.BasicMaxAgeHandler */
public class BasicMaxAgeHandler extends AbstractCookieAttributeHandler {
    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        if (value != null) {
            try {
                int age = Integer.parseInt(value);
                if (age >= 0) {
                    cookie.setExpiryDate(new Date(System.currentTimeMillis() + (((long) age) * 1000)));
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Negative max-age attribute: ");
                sb.append(value);
                throw new MalformedCookieException(sb.toString());
            } catch (NumberFormatException e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Invalid max-age attribute: ");
                sb2.append(value);
                throw new MalformedCookieException(sb2.toString());
            }
        } else {
            throw new MalformedCookieException("Missing value for max-age attribute");
        }
    }
}
