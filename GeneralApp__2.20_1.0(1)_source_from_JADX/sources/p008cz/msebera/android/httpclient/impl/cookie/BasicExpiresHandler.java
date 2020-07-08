package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.Date;
import p008cz.msebera.android.httpclient.client.utils.DateUtils;
import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.cookie.SetCookie;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.BasicExpiresHandler */
public class BasicExpiresHandler extends AbstractCookieAttributeHandler {
    private final String[] datepatterns;

    public BasicExpiresHandler(String[] datepatterns2) {
        Args.notNull(datepatterns2, "Array of date patterns");
        this.datepatterns = datepatterns2;
    }

    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        if (value != null) {
            Date expiry = DateUtils.parseDate(value, this.datepatterns);
            if (expiry != null) {
                cookie.setExpiryDate(expiry);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to parse expires attribute: ");
            sb.append(value);
            throw new MalformedCookieException(sb.toString());
        }
        throw new MalformedCookieException("Missing value for expires attribute");
    }
}
