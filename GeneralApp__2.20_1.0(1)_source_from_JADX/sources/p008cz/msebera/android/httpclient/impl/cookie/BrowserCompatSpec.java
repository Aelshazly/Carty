package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import p008cz.msebera.android.httpclient.FormattedHeader;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.NameValuePair;
import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;
import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import p008cz.msebera.android.httpclient.cookie.CookieOrigin;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpecFactory.SecurityLevel;
import p008cz.msebera.android.httpclient.message.BasicHeaderElement;
import p008cz.msebera.android.httpclient.message.BasicHeaderValueFormatter;
import p008cz.msebera.android.httpclient.message.BufferedHeader;
import p008cz.msebera.android.httpclient.message.ParserCursor;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;
import p008cz.msebera.android.httpclient.util.TextUtils;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpec */
public class BrowserCompatSpec extends CookieSpecBase {
    private static final String[] DEFAULT_DATE_PATTERNS = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z"};
    private final String[] datepatterns;

    /* renamed from: cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpec$2 */
    static /* synthetic */ class C12982 {

        /* renamed from: $SwitchMap$cz$msebera$android$httpclient$impl$cookie$BrowserCompatSpecFactory$SecurityLevel */
        static final /* synthetic */ int[] f240xa779815d = new int[SecurityLevel.values().length];

        static {
            try {
                f240xa779815d[SecurityLevel.SECURITYLEVEL_DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f240xa779815d[SecurityLevel.SECURITYLEVEL_IE_MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public BrowserCompatSpec(String[] datepatterns2, SecurityLevel securityLevel) {
        if (datepatterns2 != null) {
            this.datepatterns = (String[]) datepatterns2.clone();
        } else {
            this.datepatterns = DEFAULT_DATE_PATTERNS;
        }
        int i = C12982.f240xa779815d[securityLevel.ordinal()];
        String str = ClientCookie.PATH_ATTR;
        if (i == 1) {
            registerAttribHandler(str, new BasicPathHandler());
        } else if (i == 2) {
            registerAttribHandler(str, new BasicPathHandler() {
                public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
                }
            });
        } else {
            throw new RuntimeException("Unknown security level");
        }
        registerAttribHandler(ClientCookie.DOMAIN_ATTR, new BasicDomainHandler());
        registerAttribHandler("max-age", new BasicMaxAgeHandler());
        registerAttribHandler(ClientCookie.SECURE_ATTR, new BasicSecureHandler());
        registerAttribHandler(ClientCookie.COMMENT_ATTR, new BasicCommentHandler());
        registerAttribHandler(ClientCookie.EXPIRES_ATTR, new BasicExpiresHandler(this.datepatterns));
        registerAttribHandler(ClientCookie.VERSION_ATTR, new BrowserCompatVersionAttributeHandler());
    }

    public BrowserCompatSpec(String[] datepatterns2) {
        this(datepatterns2, SecurityLevel.SECURITYLEVEL_DEFAULT);
    }

    public BrowserCompatSpec() {
        this(null, SecurityLevel.SECURITYLEVEL_DEFAULT);
    }

    public List<Cookie> parse(Header header, CookieOrigin origin) throws MalformedCookieException {
        ParserCursor cursor;
        CharArrayBuffer buffer;
        BrowserCompatSpec browserCompatSpec = this;
        Header header2 = header;
        CookieOrigin cookieOrigin = origin;
        Args.notNull(header2, "Header");
        Args.notNull(cookieOrigin, "Cookie origin");
        if (header.getName().equalsIgnoreCase(C0971SM.SET_COOKIE)) {
            HeaderElement[] helems = header.getElements();
            boolean netscape = false;
            boolean versioned = false;
            for (HeaderElement helem : helems) {
                if (helem.getParameterByName(ClientCookie.VERSION_ATTR) != null) {
                    versioned = true;
                }
                if (helem.getParameterByName(ClientCookie.EXPIRES_ATTR) != null) {
                    netscape = true;
                }
            }
            if (!netscape && versioned) {
                return browserCompatSpec.parse(helems, cookieOrigin);
            }
            NetscapeDraftHeaderParser parser = NetscapeDraftHeaderParser.DEFAULT;
            if (header2 instanceof FormattedHeader) {
                buffer = ((FormattedHeader) header2).getBuffer();
                cursor = new ParserCursor(((FormattedHeader) header2).getValuePos(), buffer.length());
            } else {
                String s = header.getValue();
                if (s != null) {
                    CharArrayBuffer buffer2 = new CharArrayBuffer(s.length());
                    buffer2.append(s);
                    CharArrayBuffer charArrayBuffer = buffer2;
                    cursor = new ParserCursor(0, buffer2.length());
                    buffer = charArrayBuffer;
                } else {
                    throw new MalformedCookieException("Header value is null");
                }
            }
            HeaderElement elem = parser.parseHeader(buffer, cursor);
            String name = elem.getName();
            String value = elem.getValue();
            if (name == null || TextUtils.isBlank(name)) {
                throw new MalformedCookieException("Cookie name may not be empty");
            }
            BasicClientCookie cookie = new BasicClientCookie(name, value);
            cookie.setPath(getDefaultPath(origin));
            cookie.setDomain(getDefaultDomain(origin));
            NameValuePair[] attribs = elem.getParameters();
            int j = attribs.length - 1;
            while (j >= 0) {
                NameValuePair attrib = attribs[j];
                String s2 = attrib.getName().toLowerCase(Locale.ENGLISH);
                cookie.setAttribute(s2, attrib.getValue());
                CookieAttributeHandler handler = browserCompatSpec.findAttribHandler(s2);
                if (handler != null) {
                    handler.parse(cookie, attrib.getValue());
                }
                j--;
                browserCompatSpec = this;
                Header header3 = header;
                CookieOrigin cookieOrigin2 = origin;
            }
            if (netscape) {
                cookie.setVersion(0);
            }
            return Collections.singletonList(cookie);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unrecognized cookie header '");
        sb.append(header.toString());
        sb.append("'");
        throw new MalformedCookieException(sb.toString());
    }

    private static boolean isQuoteEnclosed(String s) {
        if (s != null) {
            String str = "\"";
            if (s.startsWith(str) && s.endsWith(str)) {
                return true;
            }
        }
        return false;
    }

    public List<Header> formatCookies(List<Cookie> cookies) {
        Args.notEmpty(cookies, "List of cookies");
        CharArrayBuffer buffer = new CharArrayBuffer(cookies.size() * 20);
        buffer.append(C0971SM.COOKIE);
        buffer.append(": ");
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = (Cookie) cookies.get(i);
            if (i > 0) {
                buffer.append("; ");
            }
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            if (cookie.getVersion() <= 0 || isQuoteEnclosed(cookieValue)) {
                buffer.append(cookieName);
                buffer.append("=");
                if (cookieValue != null) {
                    buffer.append(cookieValue);
                }
            } else {
                BasicHeaderValueFormatter.INSTANCE.formatHeaderElement(buffer, (HeaderElement) new BasicHeaderElement(cookieName, cookieValue), false);
            }
        }
        List<Header> headers = new ArrayList<>(1);
        headers.add(new BufferedHeader(buffer));
        return headers;
    }

    public int getVersion() {
        return 0;
    }

    public Header getVersionHeader() {
        return null;
    }

    public String toString() {
        return "compatibility";
    }
}
