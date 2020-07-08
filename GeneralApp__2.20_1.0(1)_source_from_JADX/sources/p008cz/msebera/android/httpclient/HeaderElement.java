package p008cz.msebera.android.httpclient;

/* renamed from: cz.msebera.android.httpclient.HeaderElement */
public interface HeaderElement {
    String getName();

    NameValuePair getParameter(int i);

    NameValuePair getParameterByName(String str);

    int getParameterCount();

    NameValuePair[] getParameters();

    String getValue();
}
