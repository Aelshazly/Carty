package p008cz.msebera.android.httpclient.auth;

import java.security.Principal;

/* renamed from: cz.msebera.android.httpclient.auth.Credentials */
public interface Credentials {
    String getPassword();

    Principal getUserPrincipal();
}
