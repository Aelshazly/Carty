package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpHeaders;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.client.ClientProtocolException;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.utils.DateUtils;
import p008cz.msebera.android.httpclient.message.BasicHeader;
import p008cz.msebera.android.httpclient.protocol.HTTP;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.ResponseProtocolCompliance */
class ResponseProtocolCompliance {
    private static final String UNEXPECTED_100_CONTINUE = "The incoming request did not contain a 100-continue header, but the response was a Status 100, continue.";
    private static final String UNEXPECTED_PARTIAL_CONTENT = "partial content was returned for a request that did not ask for it";

    ResponseProtocolCompliance() {
    }

    public void ensureProtocolCompliance(HttpRequestWrapper request, HttpResponse response) throws IOException {
        if (backendResponseMustNotHaveBody(request, response)) {
            consumeBody(response);
            response.setEntity(null);
        }
        requestDidNotExpect100ContinueButResponseIsOne(request, response);
        transferEncodingIsNotReturnedTo1_0Client(request, response);
        ensurePartialContentIsNotSentToAClientThatDidNotRequestIt(request, response);
        ensure200ForOPTIONSRequestWithNoBodyHasContentLengthZero(request, response);
        ensure206ContainsDateHeader(response);
        ensure304DoesNotContainExtraEntityHeaders(response);
        identityIsNotUsedInContentEncoding(response);
        warningsWithNonMatchingWarnDatesAreRemoved(response);
    }

    private void consumeBody(HttpResponse response) throws IOException {
        HttpEntity body = response.getEntity();
        if (body != null) {
            IOUtils.consume(body);
        }
    }

    private void warningsWithNonMatchingWarnDatesAreRemoved(HttpResponse response) {
        WarningValue[] warningValues;
        HttpResponse httpResponse = response;
        Date responseDate = DateUtils.parseDate(httpResponse.getFirstHeader("Date").getValue());
        if (responseDate != null) {
            String str = "Warning";
            Header[] warningHeaders = httpResponse.getHeaders(str);
            if (warningHeaders != null && warningHeaders.length != 0) {
                List<Header> newWarningHeaders = new ArrayList<>();
                int length = warningHeaders.length;
                boolean modified = false;
                int i = 0;
                while (i < length) {
                    boolean modified2 = modified;
                    for (WarningValue wv : WarningValue.getWarningValues(warningHeaders[i])) {
                        Date warnDate = wv.getWarnDate();
                        if (warnDate == null || warnDate.equals(responseDate)) {
                            newWarningHeaders.add(new BasicHeader(str, wv.toString()));
                        } else {
                            modified2 = true;
                        }
                    }
                    i++;
                    modified = modified2;
                }
                if (modified) {
                    httpResponse.removeHeaders(str);
                    for (Header h : newWarningHeaders) {
                        httpResponse.addHeader(h);
                    }
                }
            }
        }
    }

    private void identityIsNotUsedInContentEncoding(HttpResponse response) {
        HeaderElement[] elements;
        HttpResponse httpResponse = response;
        String str = "Content-Encoding";
        Header[] hdrs = httpResponse.getHeaders(str);
        if (hdrs != null && hdrs.length != 0) {
            List<Header> newHeaders = new ArrayList<>();
            int length = hdrs.length;
            boolean modified = false;
            int i = 0;
            while (i < length) {
                Header h = hdrs[i];
                StringBuilder buf = new StringBuilder();
                boolean first = true;
                boolean modified2 = modified;
                for (HeaderElement elt : h.getElements()) {
                    if (HTTP.IDENTITY_CODING.equalsIgnoreCase(elt.getName())) {
                        modified2 = true;
                    } else {
                        if (!first) {
                            buf.append(",");
                        }
                        buf.append(elt.toString());
                        first = false;
                    }
                }
                String newHeaderValue = buf.toString();
                if (!"".equals(newHeaderValue)) {
                    newHeaders.add(new BasicHeader(str, newHeaderValue));
                }
                i++;
                modified = modified2;
            }
            if (modified) {
                httpResponse.removeHeaders(str);
                for (Header h2 : newHeaders) {
                    httpResponse.addHeader(h2);
                }
            }
        }
    }

    private void ensure206ContainsDateHeader(HttpResponse response) {
        String str = "Date";
        if (response.getFirstHeader(str) == null) {
            response.addHeader(str, DateUtils.formatDate(new Date()));
        }
    }

    private void ensurePartialContentIsNotSentToAClientThatDidNotRequestIt(HttpRequest request, HttpResponse response) throws IOException {
        if (request.getFirstHeader("Range") == null && response.getStatusLine().getStatusCode() == 206) {
            consumeBody(response);
            throw new ClientProtocolException(UNEXPECTED_PARTIAL_CONTENT);
        }
    }

    private void ensure200ForOPTIONSRequestWithNoBodyHasContentLengthZero(HttpRequest request, HttpResponse response) {
        if (request.getRequestLine().getMethod().equalsIgnoreCase("OPTIONS") && response.getStatusLine().getStatusCode() == 200) {
            String str = "Content-Length";
            if (response.getFirstHeader(str) == null) {
                response.addHeader(str, "0");
            }
        }
    }

    private void ensure304DoesNotContainExtraEntityHeaders(HttpResponse response) {
        String[] disallowedEntityHeaders = {"Allow", "Content-Encoding", HttpHeaders.CONTENT_LANGUAGE, "Content-Length", HttpHeaders.CONTENT_MD5, "Content-Range", "Content-Type", "Last-Modified"};
        if (response.getStatusLine().getStatusCode() == 304) {
            for (String hdr : disallowedEntityHeaders) {
                response.removeHeaders(hdr);
            }
        }
    }

    private boolean backendResponseMustNotHaveBody(HttpRequest request, HttpResponse backendResponse) {
        return "HEAD".equals(request.getRequestLine().getMethod()) || backendResponse.getStatusLine().getStatusCode() == 204 || backendResponse.getStatusLine().getStatusCode() == 205 || backendResponse.getStatusLine().getStatusCode() == 304;
    }

    private void requestDidNotExpect100ContinueButResponseIsOne(HttpRequestWrapper request, HttpResponse response) throws IOException {
        if (response.getStatusLine().getStatusCode() == 100) {
            HttpRequest originalRequest = request.getOriginal();
            if (!(originalRequest instanceof HttpEntityEnclosingRequest) || !((HttpEntityEnclosingRequest) originalRequest).expectContinue()) {
                consumeBody(response);
                throw new ClientProtocolException(UNEXPECTED_100_CONTINUE);
            }
        }
    }

    private void transferEncodingIsNotReturnedTo1_0Client(HttpRequestWrapper request, HttpResponse response) {
        if (request.getOriginal().getProtocolVersion().compareToVersion(HttpVersion.HTTP_1_1) < 0) {
            removeResponseTransferEncoding(response);
        }
    }

    private void removeResponseTransferEncoding(HttpResponse response) {
        response.removeHeaders(HttpHeaders.f124TE);
        response.removeHeaders("Transfer-Encoding");
    }
}
