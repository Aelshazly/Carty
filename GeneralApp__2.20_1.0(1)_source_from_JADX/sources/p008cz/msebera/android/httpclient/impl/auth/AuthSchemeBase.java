package p008cz.msebera.android.httpclient.impl.auth;

import java.util.Locale;
import p008cz.msebera.android.httpclient.FormattedHeader;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.auth.AuthenticationException;
import p008cz.msebera.android.httpclient.auth.ChallengeState;
import p008cz.msebera.android.httpclient.auth.ContextAwareAuthScheme;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.auth.MalformedChallengeException;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

/* renamed from: cz.msebera.android.httpclient.impl.auth.AuthSchemeBase */
public abstract class AuthSchemeBase implements ContextAwareAuthScheme {
    private ChallengeState challengeState;

    /* access modifiers changed from: protected */
    public abstract void parseChallenge(CharArrayBuffer charArrayBuffer, int i, int i2) throws MalformedChallengeException;

    @Deprecated
    public AuthSchemeBase(ChallengeState challengeState2) {
        this.challengeState = challengeState2;
    }

    public AuthSchemeBase() {
    }

    public void processChallenge(Header header) throws MalformedChallengeException {
        CharArrayBuffer buffer;
        int pos;
        Args.notNull(header, "Header");
        String authheader = header.getName();
        if (authheader.equalsIgnoreCase("WWW-Authenticate")) {
            this.challengeState = ChallengeState.TARGET;
        } else if (authheader.equalsIgnoreCase("Proxy-Authenticate")) {
            this.challengeState = ChallengeState.PROXY;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected header name: ");
            sb.append(authheader);
            throw new MalformedChallengeException(sb.toString());
        }
        if (header instanceof FormattedHeader) {
            CharArrayBuffer buffer2 = ((FormattedHeader) header).getBuffer();
            buffer = buffer2;
            pos = ((FormattedHeader) header).getValuePos();
        } else {
            String s = header.getValue();
            if (s != null) {
                buffer = new CharArrayBuffer(s.length());
                buffer.append(s);
                pos = 0;
            } else {
                throw new MalformedChallengeException("Header value is null");
            }
        }
        while (pos < buffer.length() && HTTP.isWhitespace(buffer.charAt(pos))) {
            pos++;
        }
        int beginIndex = pos;
        while (pos < buffer.length() && !HTTP.isWhitespace(buffer.charAt(pos))) {
            pos++;
        }
        String s2 = buffer.substring(beginIndex, pos);
        if (s2.equalsIgnoreCase(getSchemeName())) {
            parseChallenge(buffer, pos, buffer.length());
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Invalid scheme identifier: ");
        sb2.append(s2);
        throw new MalformedChallengeException(sb2.toString());
    }

    public Header authenticate(Credentials credentials, HttpRequest request, HttpContext context) throws AuthenticationException {
        return authenticate(credentials, request);
    }

    public boolean isProxy() {
        ChallengeState challengeState2 = this.challengeState;
        return challengeState2 != null && challengeState2 == ChallengeState.PROXY;
    }

    public ChallengeState getChallengeState() {
        return this.challengeState;
    }

    public String toString() {
        String name = getSchemeName();
        if (name != null) {
            return name.toUpperCase(Locale.ENGLISH);
        }
        return super.toString();
    }
}
