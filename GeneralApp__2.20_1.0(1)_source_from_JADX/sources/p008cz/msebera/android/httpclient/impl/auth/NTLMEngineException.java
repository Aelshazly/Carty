package p008cz.msebera.android.httpclient.impl.auth;

import p008cz.msebera.android.httpclient.auth.AuthenticationException;

/* renamed from: cz.msebera.android.httpclient.impl.auth.NTLMEngineException */
public class NTLMEngineException extends AuthenticationException {
    private static final long serialVersionUID = 6027981323731768824L;

    public NTLMEngineException() {
    }

    public NTLMEngineException(String message) {
        super(message);
    }

    public NTLMEngineException(String message, Throwable cause) {
        super(message, cause);
    }
}
