package p008cz.msebera.android.httpclient.impl.auth;

import java.nio.charset.Charset;
import p008cz.msebera.android.httpclient.Consts;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.auth.AuthenticationException;
import p008cz.msebera.android.httpclient.auth.ChallengeState;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.auth.MalformedChallengeException;
import p008cz.msebera.android.httpclient.extras.Base64;
import p008cz.msebera.android.httpclient.message.BufferedHeader;
import p008cz.msebera.android.httpclient.protocol.BasicHttpContext;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;
import p008cz.msebera.android.httpclient.util.EncodingUtils;

/* renamed from: cz.msebera.android.httpclient.impl.auth.BasicScheme */
public class BasicScheme extends RFC2617Scheme {
    private boolean complete;

    public BasicScheme(Charset credentialsCharset) {
        super(credentialsCharset);
        this.complete = false;
    }

    @Deprecated
    public BasicScheme(ChallengeState challengeState) {
        super(challengeState);
    }

    public BasicScheme() {
        this(Consts.ASCII);
    }

    public String getSchemeName() {
        return "basic";
    }

    public void processChallenge(Header header) throws MalformedChallengeException {
        super.processChallenge(header);
        this.complete = true;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public boolean isConnectionBased() {
        return false;
    }

    @Deprecated
    public Header authenticate(Credentials credentials, HttpRequest request) throws AuthenticationException {
        return authenticate(credentials, request, (HttpContext) new BasicHttpContext());
    }

    public Header authenticate(Credentials credentials, HttpRequest request, HttpContext context) throws AuthenticationException {
        Args.notNull(credentials, "Credentials");
        Args.notNull(request, "HTTP request");
        StringBuilder tmp = new StringBuilder();
        tmp.append(credentials.getUserPrincipal().getName());
        tmp.append(":");
        tmp.append(credentials.getPassword() == null ? "null" : credentials.getPassword());
        byte[] base64password = Base64.encode(EncodingUtils.getBytes(tmp.toString(), getCredentialsCharset(request)), 2);
        CharArrayBuffer buffer = new CharArrayBuffer(32);
        if (isProxy()) {
            buffer.append("Proxy-Authorization");
        } else {
            buffer.append("Authorization");
        }
        buffer.append(": Basic ");
        buffer.append(base64password, 0, base64password.length);
        return new BufferedHeader(buffer);
    }

    @Deprecated
    public static Header authenticate(Credentials credentials, String charset, boolean proxy) {
        Args.notNull(credentials, "Credentials");
        Args.notNull(charset, "charset");
        StringBuilder tmp = new StringBuilder();
        tmp.append(credentials.getUserPrincipal().getName());
        tmp.append(":");
        tmp.append(credentials.getPassword() == null ? "null" : credentials.getPassword());
        byte[] base64password = Base64.encode(EncodingUtils.getBytes(tmp.toString(), charset), 2);
        CharArrayBuffer buffer = new CharArrayBuffer(32);
        if (proxy) {
            buffer.append("Proxy-Authorization");
        } else {
            buffer.append("Authorization");
        }
        buffer.append(": Basic ");
        buffer.append(base64password, 0, base64password.length);
        return new BufferedHeader(buffer);
    }
}
