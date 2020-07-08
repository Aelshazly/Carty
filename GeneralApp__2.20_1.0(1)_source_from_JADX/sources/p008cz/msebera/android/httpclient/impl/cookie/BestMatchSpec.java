package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.List;
import p008cz.msebera.android.httpclient.FormattedHeader;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.cookie.C0971SM;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;
import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieOrigin;
import p008cz.msebera.android.httpclient.cookie.CookieSpec;
import p008cz.msebera.android.httpclient.cookie.MalformedCookieException;
import p008cz.msebera.android.httpclient.cookie.SetCookie2;
import p008cz.msebera.android.httpclient.message.ParserCursor;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.BestMatchSpec */
public class BestMatchSpec implements CookieSpec {
    private BrowserCompatSpec compat;
    private final String[] datepatterns;
    private RFC2109Spec obsoleteStrict;
    private final boolean oneHeader;
    private RFC2965Spec strict;

    public BestMatchSpec(String[] datepatterns2, boolean oneHeader2) {
        this.datepatterns = datepatterns2 == null ? null : (String[]) datepatterns2.clone();
        this.oneHeader = oneHeader2;
    }

    public BestMatchSpec() {
        this(null, false);
    }

    private RFC2965Spec getStrict() {
        if (this.strict == null) {
            this.strict = new RFC2965Spec(this.datepatterns, this.oneHeader);
        }
        return this.strict;
    }

    private RFC2109Spec getObsoleteStrict() {
        if (this.obsoleteStrict == null) {
            this.obsoleteStrict = new RFC2109Spec(this.datepatterns, this.oneHeader);
        }
        return this.obsoleteStrict;
    }

    private BrowserCompatSpec getCompat() {
        if (this.compat == null) {
            this.compat = new BrowserCompatSpec(this.datepatterns);
        }
        return this.compat;
    }

    public List<Cookie> parse(Header header, CookieOrigin origin) throws MalformedCookieException {
        ParserCursor cursor;
        CharArrayBuffer buffer;
        Args.notNull(header, "Header");
        Args.notNull(origin, "Cookie origin");
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
        if (netscape || !versioned) {
            NetscapeDraftHeaderParser parser = NetscapeDraftHeaderParser.DEFAULT;
            if (header instanceof FormattedHeader) {
                buffer = ((FormattedHeader) header).getBuffer();
                cursor = new ParserCursor(((FormattedHeader) header).getValuePos(), buffer.length());
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
            return getCompat().parse(new HeaderElement[]{parser.parseHeader(buffer, cursor)}, origin);
        }
        if (C0971SM.SET_COOKIE2.equals(header.getName())) {
            return getStrict().parse(helems, origin);
        }
        return getObsoleteStrict().parse(helems, origin);
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        Args.notNull(cookie, C0971SM.COOKIE);
        Args.notNull(origin, "Cookie origin");
        if (cookie.getVersion() <= 0) {
            getCompat().validate(cookie, origin);
        } else if (cookie instanceof SetCookie2) {
            getStrict().validate(cookie, origin);
        } else {
            getObsoleteStrict().validate(cookie, origin);
        }
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull(cookie, C0971SM.COOKIE);
        Args.notNull(origin, "Cookie origin");
        if (cookie.getVersion() <= 0) {
            return getCompat().match(cookie, origin);
        }
        if (cookie instanceof SetCookie2) {
            return getStrict().match(cookie, origin);
        }
        return getObsoleteStrict().match(cookie, origin);
    }

    public List<Header> formatCookies(List<Cookie> cookies) {
        Args.notNull(cookies, "List of cookies");
        int version = Integer.MAX_VALUE;
        boolean isSetCookie2 = true;
        for (Cookie cookie : cookies) {
            if (!(cookie instanceof SetCookie2)) {
                isSetCookie2 = false;
            }
            if (cookie.getVersion() < version) {
                version = cookie.getVersion();
            }
        }
        if (version <= 0) {
            return getCompat().formatCookies(cookies);
        }
        if (isSetCookie2) {
            return getStrict().formatCookies(cookies);
        }
        return getObsoleteStrict().formatCookies(cookies);
    }

    public int getVersion() {
        return getStrict().getVersion();
    }

    public Header getVersionHeader() {
        return getStrict().getVersionHeader();
    }

    public String toString() {
        return "best-match";
    }
}
