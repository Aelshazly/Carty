package p008cz.msebera.android.httpclient.impl;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import p008cz.msebera.android.httpclient.config.ConnectionConfig;

/* renamed from: cz.msebera.android.httpclient.impl.ConnSupport */
public final class ConnSupport {
    public static CharsetDecoder createDecoder(ConnectionConfig cconfig) {
        if (cconfig == null) {
            return null;
        }
        Charset charset = cconfig.getCharset();
        CodingErrorAction malformed = cconfig.getMalformedInputAction();
        CodingErrorAction unmappable = cconfig.getUnmappableInputAction();
        if (charset == null) {
            return null;
        }
        return charset.newDecoder().onMalformedInput(malformed != null ? malformed : CodingErrorAction.REPORT).onUnmappableCharacter(unmappable != null ? unmappable : CodingErrorAction.REPORT);
    }

    public static CharsetEncoder createEncoder(ConnectionConfig cconfig) {
        if (cconfig == null) {
            return null;
        }
        Charset charset = cconfig.getCharset();
        if (charset == null) {
            return null;
        }
        CodingErrorAction malformed = cconfig.getMalformedInputAction();
        CodingErrorAction unmappable = cconfig.getUnmappableInputAction();
        return charset.newEncoder().onMalformedInput(malformed != null ? malformed : CodingErrorAction.REPORT).onUnmappableCharacter(unmappable != null ? unmappable : CodingErrorAction.REPORT);
    }
}
