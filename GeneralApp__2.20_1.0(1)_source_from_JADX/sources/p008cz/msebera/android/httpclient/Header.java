package p008cz.msebera.android.httpclient;

/* renamed from: cz.msebera.android.httpclient.Header */
public interface Header {
    HeaderElement[] getElements() throws ParseException;

    String getName();

    String getValue();
}
