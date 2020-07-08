package p008cz.msebera.android.httpclient.impl.auth;

import java.io.IOException;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.auth.SpnegoTokenGenerator */
public interface SpnegoTokenGenerator {
    byte[] generateSpnegoDERObject(byte[] bArr) throws IOException;
}
