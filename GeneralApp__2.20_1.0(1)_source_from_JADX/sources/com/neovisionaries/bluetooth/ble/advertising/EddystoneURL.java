package com.neovisionaries.bluetooth.ble.advertising;

import com.neovisionaries.bluetooth.ble.advertising.Eddystone.FrameType;
import java.net.MalformedURLException;
import java.net.URL;

public class EddystoneURL extends Eddystone {
    private static final String[] EXPANSION_CODES = {".com/", ".org/", ".edu/", ".net/", ".info/", ".biz/", ".gov/", ".com", ".org", ".edu", ".net", ".info", ".biz", ".gov"};
    private static final String[] SCHEME_PREFIXES = {"http://www.", "https://www.", "http://", "https://"};
    private static final String STRING_FORMAT = "EddystoneURL(TxPower=%d,URL=%s)";
    private static final long serialVersionUID = 1;
    private final int mTxPower;
    private final URL mURL;

    public EddystoneURL() {
        this(5, 22, new byte[]{-86, -2, 16, 0});
    }

    public EddystoneURL(int length, int type, byte[] data) {
        super(length, type, data, FrameType.URL);
        this.mTxPower = extractTxPower(data);
        this.mURL = extractURL(data);
    }

    private int extractTxPower(byte[] data) {
        if (4 <= data.length) {
            return data[3];
        }
        return 0;
    }

    private URL extractURL(byte[] data) {
        StringBuilder builder = new StringBuilder();
        String prefix = extractSchemePrefix(data);
        if (prefix != null) {
            builder.append(prefix);
        }
        for (int i = 5; i < data.length; i++) {
            byte ch = data[i];
            if (ch >= 0) {
                String[] strArr = EXPANSION_CODES;
                if (ch < strArr.length) {
                    builder.append(strArr[ch]);
                }
            }
            if (32 < ch && ch < Byte.MAX_VALUE) {
                builder.append((char) ch);
            }
        }
        if (builder.length() == 0) {
            return null;
        }
        try {
            return new URL(builder.toString());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private String extractSchemePrefix(byte[] data) {
        if (data.length < 5) {
            return null;
        }
        byte code = data[4];
        if (code >= 0) {
            String[] strArr = SCHEME_PREFIXES;
            if (strArr.length > code) {
                return strArr[code];
            }
        }
        return null;
    }

    public int getTxPower() {
        return this.mTxPower;
    }

    public URL getURL() {
        return this.mURL;
    }

    public String toString() {
        return String.format(STRING_FORMAT, new Object[]{Integer.valueOf(this.mTxPower), this.mURL});
    }
}
