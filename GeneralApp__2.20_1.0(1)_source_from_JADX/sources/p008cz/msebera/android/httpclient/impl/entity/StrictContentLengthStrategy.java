package p008cz.msebera.android.httpclient.impl.entity;

import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpMessage;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.entity.StrictContentLengthStrategy */
public class StrictContentLengthStrategy implements ContentLengthStrategy {
    public static final StrictContentLengthStrategy INSTANCE = new StrictContentLengthStrategy();
    private final int implicitLen;

    public StrictContentLengthStrategy(int implicitLen2) {
        this.implicitLen = implicitLen2;
    }

    public StrictContentLengthStrategy() {
        this(-1);
    }

    public long determineLength(HttpMessage message) throws HttpException {
        Args.notNull(message, "HTTP message");
        Header transferEncodingHeader = message.getFirstHeader("Transfer-Encoding");
        if (transferEncodingHeader != null) {
            String s = transferEncodingHeader.getValue();
            if (HTTP.CHUNK_CODING.equalsIgnoreCase(s)) {
                if (!message.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0)) {
                    return -2;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Chunked transfer encoding not allowed for ");
                sb.append(message.getProtocolVersion());
                throw new ProtocolException(sb.toString());
            } else if (HTTP.IDENTITY_CODING.equalsIgnoreCase(s)) {
                return -1;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unsupported transfer encoding: ");
                sb2.append(s);
                throw new ProtocolException(sb2.toString());
            }
        } else {
            Header contentLengthHeader = message.getFirstHeader("Content-Length");
            if (contentLengthHeader == null) {
                return (long) this.implicitLen;
            }
            String s2 = contentLengthHeader.getValue();
            try {
                long len = Long.parseLong(s2);
                if (len >= 0) {
                    return len;
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Negative content length: ");
                sb3.append(s2);
                throw new ProtocolException(sb3.toString());
            } catch (NumberFormatException e) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Invalid content length: ");
                sb4.append(s2);
                throw new ProtocolException(sb4.toString());
            }
        }
    }
}
