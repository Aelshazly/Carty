package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.CertificatePinner;
import okhttp3.internal.Util;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0017"}, mo24952d2 = {"Lokhttp3/internal/tls/OkHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "()V", "ALT_DNS_NAME", "", "ALT_IPA_NAME", "allSubjectAltNames", "", "", "certificate", "Ljava/security/cert/X509Certificate;", "getSubjectAltNames", "type", "verify", "", "host", "session", "Ljavax/net/ssl/SSLSession;", "verifyHostname", "hostname", "pattern", "verifyIpAddress", "ipAddress", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: OkHostnameVerifier.kt */
public final class OkHostnameVerifier implements HostnameVerifier {
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();

    private OkHostnameVerifier() {
    }

    public boolean verify(String host, SSLSession session) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(session, "session");
        try {
            Certificate certificate = session.getPeerCertificates()[0];
            if (certificate != null) {
                return verify(host, (X509Certificate) certificate);
            }
            throw new TypeCastException("null cannot be cast to non-null type java.security.cert.X509Certificate");
        } catch (SSLException e) {
            return false;
        }
    }

    public final boolean verify(String host, X509Certificate certificate) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(certificate, "certificate");
        if (Util.canParseAsIpAddress(host)) {
            return verifyIpAddress(host, certificate);
        }
        return verifyHostname(host, certificate);
    }

    private final boolean verifyIpAddress(String ipAddress, X509Certificate certificate) {
        Iterable<String> $this$any$iv = getSubjectAltNames(certificate, 7);
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (String it : $this$any$iv) {
            if (StringsKt.equals(ipAddress, it, true)) {
                return true;
            }
        }
        return false;
    }

    private final boolean verifyHostname(String hostname, X509Certificate certificate) {
        Locale locale = Locale.US;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
        if (hostname != null) {
            String hostname2 = hostname.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(hostname2, "(this as java.lang.String).toLowerCase(locale)");
            Iterable<String> $this$any$iv = getSubjectAltNames(certificate, 2);
            if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
                return false;
            }
            for (String it : $this$any$iv) {
                if (INSTANCE.verifyHostname(hostname2, it)) {
                    return true;
                }
            }
            return false;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final boolean verifyHostname(String hostname, String pattern) {
        String hostname2 = hostname;
        String pattern2 = pattern;
        CharSequence charSequence = hostname2;
        if (!(charSequence == null || charSequence.length() == 0)) {
            String str = ".";
            if (!StringsKt.startsWith$default(hostname2, str, false, 2, null)) {
                String str2 = "..";
                if (!StringsKt.endsWith$default(hostname2, str2, false, 2, null)) {
                    CharSequence charSequence2 = pattern2;
                    if ((charSequence2 == null || charSequence2.length() == 0) || StringsKt.startsWith$default(pattern2, str, false, 2, null) || StringsKt.endsWith$default(pattern2, str2, false, 2, null)) {
                        return false;
                    }
                    if (!StringsKt.endsWith$default(hostname2, str, false, 2, null)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(hostname2);
                        sb.append(str);
                        hostname2 = sb.toString();
                    }
                    if (!StringsKt.endsWith$default(pattern2, str, false, 2, null)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(pattern2);
                        sb2.append(str);
                        pattern2 = sb2.toString();
                    }
                    Locale locale = Locale.US;
                    Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
                    String str3 = "null cannot be cast to non-null type java.lang.String";
                    if (pattern2 != null) {
                        String lowerCase = pattern2.toLowerCase(locale);
                        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                        String pattern3 = lowerCase;
                        if (!StringsKt.contains$default((CharSequence) pattern3, (CharSequence) "*", false, 2, (Object) null)) {
                            return Intrinsics.areEqual((Object) hostname2, (Object) pattern3);
                        }
                        String str4 = CertificatePinner.WILDCARD;
                        if (!StringsKt.startsWith$default(pattern3, str4, false, 2, null) || StringsKt.indexOf$default((CharSequence) pattern3, '*', 1, false, 4, (Object) null) != -1 || hostname2.length() < pattern3.length() || Intrinsics.areEqual((Object) str4, (Object) pattern3)) {
                            return false;
                        }
                        if (pattern3 != null) {
                            String suffix = pattern3.substring(1);
                            Intrinsics.checkExpressionValueIsNotNull(suffix, "(this as java.lang.String).substring(startIndex)");
                            if (!StringsKt.endsWith$default(hostname2, suffix, false, 2, null)) {
                                return false;
                            }
                            int suffixStartIndexInHostname = hostname2.length() - suffix.length();
                            return suffixStartIndexInHostname <= 0 || StringsKt.lastIndexOf$default((CharSequence) hostname2, '.', suffixStartIndexInHostname + -1, false, 4, (Object) null) == -1;
                        }
                        throw new TypeCastException(str3);
                    }
                    throw new TypeCastException(str3);
                }
            }
        }
        return false;
    }

    public final List<String> allSubjectAltNames(X509Certificate certificate) {
        Intrinsics.checkParameterIsNotNull(certificate, "certificate");
        return CollectionsKt.plus((Collection<? extends T>) getSubjectAltNames(certificate, 7), (Iterable<? extends T>) getSubjectAltNames(certificate, 2));
    }

    private final List<String> getSubjectAltNames(X509Certificate certificate, int type) {
        try {
            Collection<List> subjectAltNames = certificate.getSubjectAlternativeNames();
            if (subjectAltNames == null) {
                return CollectionsKt.emptyList();
            }
            List result = new ArrayList();
            for (List subjectAltName : subjectAltNames) {
                if (subjectAltName != null) {
                    if (subjectAltName.size() >= 2) {
                        if (!(!Intrinsics.areEqual(subjectAltName.get(0), (Object) Integer.valueOf(type)))) {
                            Object altName = subjectAltName.get(1);
                            if (altName == null) {
                                continue;
                            } else if (altName != null) {
                                result.add((String) altName);
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                            }
                        }
                    }
                }
            }
            return result;
        } catch (CertificateParsingException e) {
            return CollectionsKt.emptyList();
        }
    }
}
