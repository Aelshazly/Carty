package p008cz.msebera.android.httpclient.conn.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import kotlin.text.Typography;
import p008cz.msebera.android.httpclient.conn.util.InetAddressUtils;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;

/* renamed from: cz.msebera.android.httpclient.conn.ssl.AbstractVerifier */
public abstract class AbstractVerifier implements X509HostnameVerifier {
    private static final String[] BAD_COUNTRY_2LDS = {"ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org"};
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    static {
        Arrays.sort(BAD_COUNTRY_2LDS);
    }

    public final void verify(String host, SSLSocket ssl) throws IOException {
        if (host != null) {
            SSLSession session = ssl.getSession();
            if (session == null) {
                ssl.getInputStream().available();
                session = ssl.getSession();
                if (session == null) {
                    ssl.startHandshake();
                    session = ssl.getSession();
                }
            }
            verify(host, (X509Certificate) session.getPeerCertificates()[0]);
            return;
        }
        throw new NullPointerException("host to verify is null");
    }

    public final boolean verify(String host, SSLSession session) {
        try {
            verify(host, (X509Certificate) session.getPeerCertificates()[0]);
            return true;
        } catch (SSLException e) {
            return false;
        }
    }

    public final void verify(String host, X509Certificate cert) throws SSLException {
        verify(host, getCNs(cert), getSubjectAlts(cert, host));
    }

    public final void verify(String host, String[] cns, String[] subjectAlts, boolean strictWithSubDomains) throws SSLException {
        boolean match;
        String str = host;
        String[] strArr = cns;
        String[] strArr2 = subjectAlts;
        LinkedList<String> names = new LinkedList<>();
        int i = 0;
        if (!(strArr == null || strArr.length <= 0 || strArr[0] == null)) {
            names.add(strArr[0]);
        }
        if (strArr2 != null) {
            for (String subjectAlt : strArr2) {
                if (subjectAlt != null) {
                    names.add(subjectAlt);
                }
            }
        }
        if (!names.isEmpty()) {
            StringBuilder buf = new StringBuilder();
            String hostName = normaliseIPv6Address(host.trim().toLowerCase(Locale.ENGLISH));
            boolean match2 = false;
            Iterator<String> it = names.iterator();
            while (it.hasNext()) {
                String cn = ((String) it.next()).toLowerCase(Locale.ENGLISH);
                buf.append(" <");
                buf.append(cn);
                buf.append(Typography.greater);
                if (it.hasNext()) {
                    buf.append(" OR");
                }
                String[] parts = cn.split("\\.");
                if (parts.length >= 3 && parts[i].endsWith("*") && validCountryWildcard(cn) && !isIPAddress(host)) {
                    String firstpart = parts[i];
                    if (firstpart.length() > 1) {
                        String prefix = firstpart.substring(i, firstpart.length() - 1);
                        match2 = hostName.startsWith(prefix) && hostName.substring(prefix.length()).endsWith(cn.substring(firstpart.length()));
                        match = true;
                    } else {
                        match = true;
                        match2 = hostName.endsWith(cn.substring(1));
                    }
                    if (match2 && strictWithSubDomains) {
                        if (countDots(hostName) != countDots(cn)) {
                            match = false;
                        }
                        match2 = match;
                    }
                } else {
                    match2 = hostName.equals(normaliseIPv6Address(cn));
                }
                if (match2) {
                    break;
                }
                i = 0;
            }
            if (!match2) {
                StringBuilder sb = new StringBuilder();
                sb.append("hostname in certificate didn't match: <");
                sb.append(str);
                sb.append("> !=");
                sb.append(buf);
                throw new SSLException(sb.toString());
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Certificate for <");
        sb2.append(str);
        sb2.append("> doesn't contain CN or DNS subjectAlt");
        throw new SSLException(sb2.toString());
    }

    @Deprecated
    public static boolean acceptableCountryWildcard(String cn) {
        String[] parts = cn.split("\\.");
        boolean z = true;
        if (parts.length != 3 || parts[2].length() != 2) {
            return true;
        }
        if (Arrays.binarySearch(BAD_COUNTRY_2LDS, parts[1]) >= 0) {
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public boolean validCountryWildcard(String cn) {
        String[] parts = cn.split("\\.");
        boolean z = true;
        if (parts.length != 3 || parts[2].length() != 2) {
            return true;
        }
        if (Arrays.binarySearch(BAD_COUNTRY_2LDS, parts[1]) >= 0) {
            z = false;
        }
        return z;
    }

    public static String[] getCNs(X509Certificate cert) {
        try {
            return extractCNs(cert.getSubjectX500Principal().toString());
        } catch (SSLException e) {
            return null;
        }
    }

    static String[] extractCNs(String subjectPrincipal) throws SSLException {
        if (subjectPrincipal == null) {
            return null;
        }
        LinkedList<String> cnList = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(subjectPrincipal, ",");
        while (st.hasMoreTokens()) {
            String tok = st.nextToken();
            int x = tok.indexOf("CN=");
            if (x >= 0) {
                cnList.add(tok.substring(x + 3));
            }
        }
        if (cnList.isEmpty()) {
            return null;
        }
        String[] cns = new String[cnList.size()];
        cnList.toArray(cns);
        return cns;
    }

    private static String[] getSubjectAlts(X509Certificate cert, String hostname) {
        int subjectType;
        if (isIPAddress(hostname)) {
            subjectType = 7;
        } else {
            subjectType = 2;
        }
        LinkedList<String> subjectAltList = new LinkedList<>();
        Collection<List<?>> collection = null;
        try {
            collection = cert.getSubjectAlternativeNames();
        } catch (CertificateParsingException e) {
        }
        if (collection != null) {
            for (List<?> list : collection) {
                if (((Integer) list.get(0)).intValue() == subjectType) {
                    subjectAltList.add((String) list.get(1));
                }
            }
        }
        if (subjectAltList.isEmpty()) {
            return null;
        }
        String[] subjectAlts = new String[subjectAltList.size()];
        subjectAltList.toArray(subjectAlts);
        return subjectAlts;
    }

    public static String[] getDNSSubjectAlts(X509Certificate cert) {
        return getSubjectAlts(cert, null);
    }

    public static int countDots(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                count++;
            }
        }
        return count;
    }

    private static boolean isIPAddress(String hostname) {
        return hostname != null && (InetAddressUtils.isIPv4Address(hostname) || InetAddressUtils.isIPv6Address(hostname));
    }

    private String normaliseIPv6Address(String hostname) {
        if (hostname == null || !InetAddressUtils.isIPv6Address(hostname)) {
            return hostname;
        }
        try {
            return InetAddress.getByName(hostname).getHostAddress();
        } catch (UnknownHostException uhe) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected error converting ");
            sb.append(hostname);
            httpClientAndroidLog.error(sb.toString(), uhe);
            return hostname;
        }
    }
}
