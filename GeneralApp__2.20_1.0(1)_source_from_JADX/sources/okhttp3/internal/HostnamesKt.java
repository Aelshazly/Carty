package okhttp3.internal;

import java.net.IDN;
import java.net.InetAddress;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Buffer;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0002\u001a\"\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\f\u0010\r\u001a\u00020\u0001*\u00020\u0003H\u0002\u001a\f\u0010\u000e\u001a\u0004\u0018\u00010\u0003*\u00020\u0003¨\u0006\u000f"}, mo24952d2 = {"decodeIpv4Suffix", "", "input", "", "pos", "", "limit", "address", "", "addressOffset", "decodeIpv6", "Ljava/net/InetAddress;", "inet6AddressToAscii", "containsInvalidHostnameAsciiCodes", "toCanonicalHost", "okhttp"}, mo24953k = 2, mo24954mv = {1, 1, 15})
/* compiled from: hostnames.kt */
public final class HostnamesKt {
    public static final String toCanonicalHost(String $this$toCanonicalHost) {
        InetAddress inetAddress;
        Intrinsics.checkParameterIsNotNull($this$toCanonicalHost, "$this$toCanonicalHost");
        String host = $this$toCanonicalHost;
        String str = null;
        boolean z = true;
        if (StringsKt.contains$default((CharSequence) host, (CharSequence) ":", false, 2, (Object) null)) {
            if (!StringsKt.startsWith$default(host, "[", false, 2, null) || !StringsKt.endsWith$default(host, "]", false, 2, null)) {
                inetAddress = decodeIpv6(host, 0, host.length());
            } else {
                inetAddress = decodeIpv6(host, 1, host.length() - 1);
            }
            if (inetAddress == null) {
                return null;
            }
            byte[] address = inetAddress.getAddress();
            if (address.length == 16) {
                Intrinsics.checkExpressionValueIsNotNull(address, "address");
                return inet6AddressToAscii(address);
            } else if (address.length == 4) {
                return inetAddress.getHostAddress();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid IPv6 address: '");
                sb.append(host);
                sb.append('\'');
                throw new AssertionError(sb.toString());
            }
        } else {
            try {
                String ascii = IDN.toASCII(host);
                Intrinsics.checkExpressionValueIsNotNull(ascii, "IDN.toASCII(host)");
                Locale locale = Locale.US;
                Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
                if (ascii != null) {
                    String result = ascii.toLowerCase(locale);
                    Intrinsics.checkExpressionValueIsNotNull(result, "(this as java.lang.String).toLowerCase(locale)");
                    if (result.length() != 0) {
                        z = false;
                    }
                    if (z) {
                        return null;
                    }
                    if (!containsInvalidHostnameAsciiCodes(result)) {
                        str = result;
                    }
                    return str;
                }
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    private static final boolean containsInvalidHostnameAsciiCodes(String $this$containsInvalidHostnameAsciiCodes) {
        int length = $this$containsInvalidHostnameAsciiCodes.length();
        for (int i = 0; i < length; i++) {
            char c = $this$containsInvalidHostnameAsciiCodes.charAt(i);
            if (c <= 31 || c >= 127 || StringsKt.indexOf$default((CharSequence) " #%/:?@[\\]", c, 0, false, 6, (Object) null) != -1) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.net.InetAddress decodeIpv6(java.lang.String r13, int r14, int r15) {
        /*
            r0 = 16
            byte[] r0 = new byte[r0]
            r1 = 0
            r2 = -1
            r3 = -1
            r4 = r14
        L_0x0008:
            r11 = -1
            r12 = 0
            if (r4 >= r15) goto L_0x008d
            int r5 = r0.length
            if (r1 != r5) goto L_0x0010
            return r12
        L_0x0010:
            int r5 = r4 + 2
            if (r5 > r15) goto L_0x002d
            r8 = 0
            r9 = 4
            r10 = 0
            java.lang.String r6 = "::"
            r5 = r13
            r7 = r4
            boolean r5 = kotlin.text.StringsKt.startsWith$default(r5, r6, r7, r8, r9, r10)
            if (r5 == 0) goto L_0x002d
            if (r2 == r11) goto L_0x0024
            return r12
        L_0x0024:
            int r4 = r4 + 2
            int r1 = r1 + 2
            r2 = r1
            if (r4 != r15) goto L_0x0059
            goto L_0x008d
        L_0x002d:
            if (r1 == 0) goto L_0x0059
            r8 = 0
            r9 = 4
            r10 = 0
            java.lang.String r6 = ":"
            r5 = r13
            r7 = r4
            boolean r5 = kotlin.text.StringsKt.startsWith$default(r5, r6, r7, r8, r9, r10)
            if (r5 == 0) goto L_0x003f
            int r4 = r4 + 1
            goto L_0x0059
        L_0x003f:
            r8 = 0
            r9 = 4
            r10 = 0
            java.lang.String r6 = "."
            r5 = r13
            r7 = r4
            boolean r5 = kotlin.text.StringsKt.startsWith$default(r5, r6, r7, r8, r9, r10)
            if (r5 == 0) goto L_0x0058
            int r5 = r1 + -2
            boolean r5 = decodeIpv4Suffix(r13, r3, r15, r0, r5)
            if (r5 != 0) goto L_0x0055
            return r12
        L_0x0055:
            int r1 = r1 + 2
            goto L_0x008d
        L_0x0058:
            return r12
        L_0x0059:
            r5 = 0
            r3 = r4
        L_0x005d:
            if (r4 >= r15) goto L_0x0072
            char r6 = r13.charAt(r4)
            int r6 = okhttp3.internal.Util.parseHexDigit(r6)
            if (r6 != r11) goto L_0x006a
            goto L_0x0072
        L_0x006a:
            int r7 = r5 << 4
            int r5 = r7 + r6
            int r4 = r4 + 1
            goto L_0x005d
        L_0x0072:
            int r6 = r4 - r3
            if (r6 == 0) goto L_0x008c
            r7 = 4
            if (r6 <= r7) goto L_0x007a
            goto L_0x008c
        L_0x007a:
            int r7 = r1 + 1
            int r8 = r5 >>> 8
            r8 = r8 & 255(0xff, float:3.57E-43)
            byte r8 = (byte) r8
            r0[r1] = r8
            int r1 = r7 + 1
            r8 = r5 & 255(0xff, float:3.57E-43)
            byte r8 = (byte) r8
            r0[r7] = r8
            goto L_0x0008
        L_0x008c:
            return r12
        L_0x008d:
            int r5 = r0.length
            if (r1 == r5) goto L_0x00a4
            if (r2 != r11) goto L_0x0093
            return r12
        L_0x0093:
            int r5 = r0.length
            int r6 = r1 - r2
            int r5 = r5 - r6
            int r6 = r1 - r2
            java.lang.System.arraycopy(r0, r2, r0, r5, r6)
            int r5 = r0.length
            int r5 = r5 - r1
            int r5 = r5 + r2
            r6 = 0
            byte r6 = (byte) r6
            java.util.Arrays.fill(r0, r2, r5, r6)
        L_0x00a4:
            java.net.InetAddress r5 = java.net.InetAddress.getByAddress(r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.HostnamesKt.decodeIpv6(java.lang.String, int, int):java.net.InetAddress");
    }

    private static final boolean decodeIpv4Suffix(String input, int pos, int limit, byte[] address, int addressOffset) {
        int b = addressOffset;
        int i = pos;
        while (true) {
            boolean z = false;
            if (i >= limit) {
                if (b == addressOffset + 4) {
                    z = true;
                }
                return z;
            } else if (b == address.length) {
                return false;
            } else {
                if (b != addressOffset) {
                    if (input.charAt(i) != '.') {
                        return false;
                    }
                    i++;
                }
                int value = 0;
                int groupOffset = i;
                while (i < limit) {
                    char c = input.charAt(i);
                    if (c < '0' || c > '9') {
                        break;
                    } else if (value == 0 && groupOffset != i) {
                        return false;
                    } else {
                        value = ((value * 10) + c) - 48;
                        if (value > 255) {
                            return false;
                        }
                        i++;
                    }
                }
                if (i - groupOffset == 0) {
                    return false;
                }
                int b2 = b + 1;
                address[b] = (byte) value;
                b = b2;
            }
        }
    }

    private static final String inet6AddressToAscii(byte[] address) {
        int longestRunOffset = -1;
        int longestRunLength = 0;
        int i = 0;
        while (i < address.length) {
            int currentRunOffset = i;
            while (i < 16 && address[i] == 0 && address[i + 1] == 0) {
                i += 2;
            }
            int currentRunLength = i - currentRunOffset;
            if (currentRunLength > longestRunLength && currentRunLength >= 4) {
                longestRunOffset = currentRunOffset;
                longestRunLength = currentRunLength;
            }
            i += 2;
        }
        Buffer result = new Buffer();
        int i2 = 0;
        while (i2 < address.length) {
            if (i2 == longestRunOffset) {
                result.writeByte(58);
                i2 += longestRunLength;
                if (i2 == 16) {
                    result.writeByte(58);
                }
            } else {
                if (i2 > 0) {
                    result.writeByte(58);
                }
                result.writeHexadecimalUnsignedLong((long) ((Util.and(address[i2], 255) << 8) | Util.and(address[i2 + 1], 255)));
                i2 += 2;
            }
        }
        return result.readUtf8();
    }
}
