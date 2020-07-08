package p008cz.msebera.android.httpclient.client.methods;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HeaderIterator;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.client.methods.HttpOptions */
public class HttpOptions extends HttpRequestBase {
    public static final String METHOD_NAME = "OPTIONS";

    public HttpOptions() {
    }

    public HttpOptions(URI uri) {
        setURI(uri);
    }

    public HttpOptions(String uri) {
        setURI(URI.create(uri));
    }

    public String getMethod() {
        return "OPTIONS";
    }

    public Set<String> getAllowedMethods(HttpResponse response) {
        Args.notNull(response, "HTTP response");
        HeaderIterator it = response.headerIterator("Allow");
        Set<String> methods = new HashSet<>();
        while (it.hasNext()) {
            for (HeaderElement element : it.nextHeader().getElements()) {
                methods.add(element.getName());
            }
        }
        return methods;
    }
}
