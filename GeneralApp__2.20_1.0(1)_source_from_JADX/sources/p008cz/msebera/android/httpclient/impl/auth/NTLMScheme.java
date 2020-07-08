package p008cz.msebera.android.httpclient.impl.auth;

import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.auth.AuthenticationException;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.auth.InvalidCredentialsException;
import p008cz.msebera.android.httpclient.auth.MalformedChallengeException;
import p008cz.msebera.android.httpclient.auth.NTCredentials;
import p008cz.msebera.android.httpclient.message.BufferedHeader;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

/* renamed from: cz.msebera.android.httpclient.impl.auth.NTLMScheme */
public class NTLMScheme extends AuthSchemeBase {
    private String challenge;
    private final NTLMEngine engine;
    private State state;

    /* renamed from: cz.msebera.android.httpclient.impl.auth.NTLMScheme$State */
    enum State {
        UNINITIATED,
        CHALLENGE_RECEIVED,
        MSG_TYPE1_GENERATED,
        MSG_TYPE2_RECEVIED,
        MSG_TYPE3_GENERATED,
        FAILED
    }

    public NTLMScheme(NTLMEngine engine2) {
        Args.notNull(engine2, "NTLM engine");
        this.engine = engine2;
        this.state = State.UNINITIATED;
        this.challenge = null;
    }

    public NTLMScheme() {
        this(new NTLMEngineImpl());
    }

    public String getSchemeName() {
        return "ntlm";
    }

    public String getParameter(String name) {
        return null;
    }

    public String getRealm() {
        return null;
    }

    public boolean isConnectionBased() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void parseChallenge(CharArrayBuffer buffer, int beginIndex, int endIndex) throws MalformedChallengeException {
        this.challenge = buffer.substringTrimmed(beginIndex, endIndex);
        if (this.challenge.length() == 0) {
            if (this.state == State.UNINITIATED) {
                this.state = State.CHALLENGE_RECEIVED;
            } else {
                this.state = State.FAILED;
            }
        } else if (this.state.compareTo(State.MSG_TYPE1_GENERATED) < 0) {
            this.state = State.FAILED;
            throw new MalformedChallengeException("Out of sequence NTLM response message");
        } else if (this.state == State.MSG_TYPE1_GENERATED) {
            this.state = State.MSG_TYPE2_RECEVIED;
        }
    }

    public Header authenticate(Credentials credentials, HttpRequest request) throws AuthenticationException {
        String response;
        try {
            NTCredentials ntcredentials = (NTCredentials) credentials;
            if (this.state != State.FAILED) {
                if (this.state == State.CHALLENGE_RECEIVED) {
                    response = this.engine.generateType1Msg(ntcredentials.getDomain(), ntcredentials.getWorkstation());
                    this.state = State.MSG_TYPE1_GENERATED;
                } else if (this.state == State.MSG_TYPE2_RECEVIED) {
                    response = this.engine.generateType3Msg(ntcredentials.getUserName(), ntcredentials.getPassword(), ntcredentials.getDomain(), ntcredentials.getWorkstation(), this.challenge);
                    this.state = State.MSG_TYPE3_GENERATED;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unexpected state: ");
                    sb.append(this.state);
                    throw new AuthenticationException(sb.toString());
                }
                CharArrayBuffer buffer = new CharArrayBuffer(32);
                if (isProxy()) {
                    buffer.append("Proxy-Authorization");
                } else {
                    buffer.append("Authorization");
                }
                buffer.append(": NTLM ");
                buffer.append(response);
                return new BufferedHeader(buffer);
            }
            throw new AuthenticationException("NTLM authentication failed");
        } catch (ClassCastException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Credentials cannot be used for NTLM authentication: ");
            sb2.append(credentials.getClass().getName());
            throw new InvalidCredentialsException(sb2.toString());
        }
    }

    public boolean isComplete() {
        return this.state == State.MSG_TYPE3_GENERATED || this.state == State.FAILED;
    }
}
