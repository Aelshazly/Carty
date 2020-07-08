package p008cz.msebera.android.httpclient.impl.auth;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import p008cz.msebera.android.httpclient.Consts;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.auth.ChallengeState;
import p008cz.msebera.android.httpclient.auth.MalformedChallengeException;
import p008cz.msebera.android.httpclient.auth.params.AuthPNames;
import p008cz.msebera.android.httpclient.message.BasicHeaderValueParser;
import p008cz.msebera.android.httpclient.message.ParserCursor;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

/* renamed from: cz.msebera.android.httpclient.impl.auth.RFC2617Scheme */
public abstract class RFC2617Scheme extends AuthSchemeBase {
    private final Charset credentialsCharset;
    private final Map<String, String> params;

    @Deprecated
    public RFC2617Scheme(ChallengeState challengeState) {
        super(challengeState);
        this.params = new HashMap();
        this.credentialsCharset = Consts.ASCII;
    }

    public RFC2617Scheme(Charset credentialsCharset2) {
        this.params = new HashMap();
        this.credentialsCharset = credentialsCharset2 != null ? credentialsCharset2 : Consts.ASCII;
    }

    public RFC2617Scheme() {
        this(Consts.ASCII);
    }

    public Charset getCredentialsCharset() {
        return this.credentialsCharset;
    }

    /* access modifiers changed from: 0000 */
    public String getCredentialsCharset(HttpRequest request) {
        String charset = (String) request.getParams().getParameter(AuthPNames.CREDENTIAL_CHARSET);
        if (charset == null) {
            return getCredentialsCharset().name();
        }
        return charset;
    }

    /* access modifiers changed from: protected */
    public void parseChallenge(CharArrayBuffer buffer, int pos, int len) throws MalformedChallengeException {
        HeaderElement[] elements = BasicHeaderValueParser.INSTANCE.parseElements(buffer, new ParserCursor(pos, buffer.length()));
        if (elements.length != 0) {
            this.params.clear();
            for (HeaderElement element : elements) {
                this.params.put(element.getName().toLowerCase(Locale.ENGLISH), element.getValue());
            }
            return;
        }
        throw new MalformedChallengeException("Authentication challenge is empty");
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getParameters() {
        return this.params;
    }

    public String getParameter(String name) {
        if (name == null) {
            return null;
        }
        return (String) this.params.get(name.toLowerCase(Locale.ENGLISH));
    }

    public String getRealm() {
        return getParameter("realm");
    }
}
