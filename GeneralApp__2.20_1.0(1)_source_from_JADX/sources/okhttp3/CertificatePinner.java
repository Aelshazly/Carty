package okhttp3;

import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 !2\u00020\u0001:\u0003 !\"B\u001f\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J)\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fH\u0000¢\u0006\u0002\b\u0012J)\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0016J\u001c\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0002J\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0017\u0010\u001e\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\b\u001fR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, mo24952d2 = {"Lokhttp3/CertificatePinner;", "", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "(Ljava/util/Set;Lokhttp3/internal/tls/CertificateChainCleaner;)V", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "check", "", "hostname", "", "cleanedPeerCertificatesFn", "Lkotlin/Function0;", "", "Ljava/security/cert/X509Certificate;", "check$okhttp", "peerCertificates", "", "Ljava/security/cert/Certificate;", "(Ljava/lang/String;[Ljava/security/cert/Certificate;)V", "equals", "", "other", "findMatchingPins", "findMatchingPins$okhttp", "hashCode", "", "withCertificateChainCleaner", "withCertificateChainCleaner$okhttp", "Builder", "Companion", "Pin", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: CertificatePinner.kt */
public final class CertificatePinner {
    public static final Companion Companion = new Companion(null);
    public static final CertificatePinner DEFAULT = new Builder().build();
    public static final String WILDCARD = "*.";
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J'\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\t\"\u00020\b¢\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo24952d2 = {"Lokhttp3/CertificatePinner$Builder;", "", "()V", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "add", "pattern", "", "", "(Ljava/lang/String;[Ljava/lang/String;)Lokhttp3/CertificatePinner$Builder;", "build", "Lokhttp3/CertificatePinner;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: CertificatePinner.kt */
    public static final class Builder {
        private final List<Pin> pins = new ArrayList();

        public final Builder add(String pattern, String... pins2) {
            Intrinsics.checkParameterIsNotNull(pattern, "pattern");
            Intrinsics.checkParameterIsNotNull(pins2, "pins");
            Builder $this$apply = this;
            for (String pin : pins2) {
                $this$apply.pins.add(CertificatePinner.Companion.newPin$okhttp(pattern, pin));
            }
            return this;
        }

        public final CertificatePinner build() {
            return new CertificatePinner(CollectionsKt.toSet(this.pins), null);
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u000bJ\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0007J\u0011\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0000¢\u0006\u0002\b\u0011J\u0011\u0010\u0012\u001a\u00020\u000f*\u00020\u0010H\u0000¢\u0006\u0002\b\u0013R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo24952d2 = {"Lokhttp3/CertificatePinner$Companion;", "", "()V", "DEFAULT", "Lokhttp3/CertificatePinner;", "WILDCARD", "", "newPin", "Lokhttp3/CertificatePinner$Pin;", "pattern", "pin", "newPin$okhttp", "certificate", "Ljava/security/cert/Certificate;", "toSha1ByteString", "Lokio/ByteString;", "Ljava/security/cert/X509Certificate;", "toSha1ByteString$okhttp", "toSha256ByteString", "toSha256ByteString$okhttp", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: CertificatePinner.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @JvmStatic
        public final String pin(Certificate certificate) {
            Intrinsics.checkParameterIsNotNull(certificate, "certificate");
            if (certificate instanceof X509Certificate) {
                StringBuilder sb = new StringBuilder();
                sb.append("sha256/");
                sb.append(toSha256ByteString$okhttp((X509Certificate) certificate).base64());
                return sb.toString();
            }
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates".toString());
        }

        public final ByteString toSha1ByteString$okhttp(X509Certificate $this$toSha1ByteString) {
            Intrinsics.checkParameterIsNotNull($this$toSha1ByteString, "$this$toSha1ByteString");
            okio.ByteString.Companion companion = ByteString.Companion;
            PublicKey publicKey = $this$toSha1ByteString.getPublicKey();
            Intrinsics.checkExpressionValueIsNotNull(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            Intrinsics.checkExpressionValueIsNotNull(encoded, "publicKey.encoded");
            return okio.ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null).sha1();
        }

        public final ByteString toSha256ByteString$okhttp(X509Certificate $this$toSha256ByteString) {
            Intrinsics.checkParameterIsNotNull($this$toSha256ByteString, "$this$toSha256ByteString");
            okio.ByteString.Companion companion = ByteString.Companion;
            PublicKey publicKey = $this$toSha256ByteString.getPublicKey();
            Intrinsics.checkExpressionValueIsNotNull(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            Intrinsics.checkExpressionValueIsNotNull(encoded, "publicKey.encoded");
            return okio.ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null).sha256();
        }

        public final Pin newPin$okhttp(String pattern, String pin) {
            String canonicalHostname;
            Intrinsics.checkParameterIsNotNull(pattern, "pattern");
            Intrinsics.checkParameterIsNotNull(pin, "pin");
            String str = CertificatePinner.WILDCARD;
            String str2 = "http://";
            String str3 = "(this as java.lang.String).substring(startIndex)";
            if (StringsKt.startsWith$default(pattern, str, false, 2, null)) {
                okhttp3.HttpUrl.Companion companion = HttpUrl.Companion;
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                String substring = pattern.substring(str.length());
                Intrinsics.checkExpressionValueIsNotNull(substring, str3);
                sb.append(substring);
                canonicalHostname = companion.get(sb.toString()).host();
            } else {
                okhttp3.HttpUrl.Companion companion2 = HttpUrl.Companion;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append(pattern);
                canonicalHostname = companion2.get(sb2.toString()).host();
            }
            String str4 = "sha1/";
            if (StringsKt.startsWith$default(pin, str4, false, 2, null)) {
                okio.ByteString.Companion companion3 = ByteString.Companion;
                String substring2 = pin.substring(str4.length());
                Intrinsics.checkExpressionValueIsNotNull(substring2, str3);
                ByteString hash = companion3.decodeBase64(substring2);
                if (hash == null) {
                    Intrinsics.throwNpe();
                }
                return new Pin(pattern, canonicalHostname, str4, hash);
            }
            String str5 = "sha256/";
            if (StringsKt.startsWith$default(pin, str5, false, 2, null)) {
                okio.ByteString.Companion companion4 = ByteString.Companion;
                String substring3 = pin.substring(str5.length());
                Intrinsics.checkExpressionValueIsNotNull(substring3, str3);
                ByteString hash2 = companion4.decodeBase64(substring3);
                if (hash2 == null) {
                    Intrinsics.throwNpe();
                }
                return new Pin(pattern, canonicalHostname, str5, hash2);
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("pins must start with 'sha256/' or 'sha1/': ");
            sb3.append(pin);
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\u000e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0003J\b\u0010\u001a\u001a\u00020\u0003H\u0016R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u001b"}, mo24952d2 = {"Lokhttp3/CertificatePinner$Pin;", "", "pattern", "", "canonicalHostname", "hashAlgorithm", "hash", "Lokio/ByteString;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lokio/ByteString;)V", "getHash", "()Lokio/ByteString;", "getHashAlgorithm", "()Ljava/lang/String;", "getPattern", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "matches", "hostname", "toString", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: CertificatePinner.kt */
    public static final class Pin {
        private final String canonicalHostname;
        private final ByteString hash;
        private final String hashAlgorithm;
        private final String pattern;

        private final String component2() {
            return this.canonicalHostname;
        }

        public static /* synthetic */ Pin copy$default(Pin pin, String str, String str2, String str3, ByteString byteString, int i, Object obj) {
            if ((i & 1) != 0) {
                str = pin.pattern;
            }
            if ((i & 2) != 0) {
                str2 = pin.canonicalHostname;
            }
            if ((i & 4) != 0) {
                str3 = pin.hashAlgorithm;
            }
            if ((i & 8) != 0) {
                byteString = pin.hash;
            }
            return pin.copy(str, str2, str3, byteString);
        }

        public final String component1() {
            return this.pattern;
        }

        public final String component3() {
            return this.hashAlgorithm;
        }

        public final ByteString component4() {
            return this.hash;
        }

        public final Pin copy(String str, String str2, String str3, ByteString byteString) {
            Intrinsics.checkParameterIsNotNull(str, "pattern");
            Intrinsics.checkParameterIsNotNull(str2, "canonicalHostname");
            Intrinsics.checkParameterIsNotNull(str3, "hashAlgorithm");
            Intrinsics.checkParameterIsNotNull(byteString, "hash");
            return new Pin(str, str2, str3, byteString);
        }

        /* JADX INFO: used method not loaded: kotlin.jvm.internal.Intrinsics.areEqual(java.lang.Object, java.lang.Object):null, types can be incorrect */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.hash, (java.lang.Object) r3.hash) != false) goto L_0x0033;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x0033
                boolean r0 = r3 instanceof okhttp3.CertificatePinner.Pin
                if (r0 == 0) goto L_0x0031
                okhttp3.CertificatePinner$Pin r3 = (okhttp3.CertificatePinner.Pin) r3
                java.lang.String r0 = r2.pattern
                java.lang.String r1 = r3.pattern
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0031
                java.lang.String r0 = r2.canonicalHostname
                java.lang.String r1 = r3.canonicalHostname
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0031
                java.lang.String r0 = r2.hashAlgorithm
                java.lang.String r1 = r3.hashAlgorithm
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0031
                okio.ByteString r0 = r2.hash
                okio.ByteString r3 = r3.hash
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
                if (r3 == 0) goto L_0x0031
                goto L_0x0033
            L_0x0031:
                r3 = 0
                return r3
            L_0x0033:
                r3 = 1
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.CertificatePinner.Pin.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            String str = this.pattern;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.canonicalHostname;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.hashAlgorithm;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            ByteString byteString = this.hash;
            if (byteString != null) {
                i = byteString.hashCode();
            }
            return hashCode3 + i;
        }

        public Pin(String pattern2, String canonicalHostname2, String hashAlgorithm2, ByteString hash2) {
            Intrinsics.checkParameterIsNotNull(pattern2, "pattern");
            Intrinsics.checkParameterIsNotNull(canonicalHostname2, "canonicalHostname");
            Intrinsics.checkParameterIsNotNull(hashAlgorithm2, "hashAlgorithm");
            Intrinsics.checkParameterIsNotNull(hash2, "hash");
            this.pattern = pattern2;
            this.canonicalHostname = canonicalHostname2;
            this.hashAlgorithm = hashAlgorithm2;
            this.hash = hash2;
        }

        public final String getPattern() {
            return this.pattern;
        }

        public final String getHashAlgorithm() {
            return this.hashAlgorithm;
        }

        public final ByteString getHash() {
            return this.hash;
        }

        public final boolean matches(String hostname) {
            Intrinsics.checkParameterIsNotNull(hostname, "hostname");
            boolean z = false;
            if (!StringsKt.startsWith$default(this.pattern, CertificatePinner.WILDCARD, false, 2, null)) {
                return Intrinsics.areEqual((Object) hostname, (Object) this.canonicalHostname);
            }
            int firstDot = StringsKt.indexOf$default((CharSequence) hostname, '.', 0, false, 6, (Object) null);
            if ((hostname.length() - firstDot) - 1 == this.canonicalHostname.length()) {
                if (StringsKt.startsWith$default(hostname, this.canonicalHostname, firstDot + 1, false, 4, null)) {
                    z = true;
                }
            }
            return z;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.hashAlgorithm);
            sb.append(this.hash.base64());
            return sb.toString();
        }
    }

    @JvmStatic
    public static final String pin(Certificate certificate) {
        return Companion.pin(certificate);
    }

    public CertificatePinner(Set<Pin> pins2, CertificateChainCleaner certificateChainCleaner2) {
        Intrinsics.checkParameterIsNotNull(pins2, "pins");
        this.pins = pins2;
        this.certificateChainCleaner = certificateChainCleaner2;
    }

    public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
        return this.certificateChainCleaner;
    }

    public final void check(String hostname, List<? extends Certificate> peerCertificates) throws SSLPeerUnverifiedException {
        Intrinsics.checkParameterIsNotNull(hostname, "hostname");
        Intrinsics.checkParameterIsNotNull(peerCertificates, "peerCertificates");
        check$okhttp(hostname, new CertificatePinner$check$1(this, peerCertificates, hostname));
    }

    public final void check$okhttp(String hostname, Function0<? extends List<? extends X509Certificate>> cleanedPeerCertificatesFn) {
        Pin pin;
        Intrinsics.checkParameterIsNotNull(hostname, "hostname");
        Intrinsics.checkParameterIsNotNull(cleanedPeerCertificatesFn, "cleanedPeerCertificatesFn");
        List<Pin> pins2 = findMatchingPins$okhttp(hostname);
        if (!pins2.isEmpty()) {
            List<X509Certificate> peerCertificates = (List) cleanedPeerCertificatesFn.invoke();
            loop0:
            for (X509Certificate peerCertificate : peerCertificates) {
                ByteString sha1 = null;
                ByteString sha256 = null;
                Iterator it = pins2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        pin = (Pin) it.next();
                        String hashAlgorithm = pin.getHashAlgorithm();
                        int hashCode = hashAlgorithm.hashCode();
                        if (hashCode == 109397962) {
                            if (!hashAlgorithm.equals("sha1/")) {
                                break loop0;
                            }
                            if (sha1 == null) {
                                sha1 = Companion.toSha1ByteString$okhttp(peerCertificate);
                            }
                            if (Intrinsics.areEqual((Object) pin.getHash(), (Object) sha1)) {
                                return;
                            }
                        } else if (hashCode != 2052263656 || !hashAlgorithm.equals("sha256/")) {
                            break loop0;
                        } else {
                            if (sha256 == null) {
                                sha256 = Companion.toSha256ByteString$okhttp(peerCertificate);
                            }
                            if (Intrinsics.areEqual((Object) pin.getHash(), (Object) sha256)) {
                                return;
                            }
                        }
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append("unsupported hashAlgorithm: ");
                sb.append(pin.getHashAlgorithm());
                throw new AssertionError(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder();
            StringBuilder $this$buildString = sb2;
            $this$buildString.append("Certificate pinning failure!");
            $this$buildString.append("\n  Peer certificate chain:");
            Iterator it2 = peerCertificates.iterator();
            while (true) {
                String str = "\n    ";
                if (it2.hasNext()) {
                    X509Certificate element = (X509Certificate) it2.next();
                    if (element != null) {
                        X509Certificate x509Certificate = element;
                        $this$buildString.append(str);
                        $this$buildString.append(Companion.pin(x509Certificate));
                        $this$buildString.append(": ");
                        Principal subjectDN = x509Certificate.getSubjectDN();
                        Intrinsics.checkExpressionValueIsNotNull(subjectDN, "x509Certificate.subjectDN");
                        $this$buildString.append(subjectDN.getName());
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                    }
                } else {
                    $this$buildString.append("\n  Pinned certificates for ");
                    $this$buildString.append(hostname);
                    $this$buildString.append(":");
                    for (Pin pin2 : pins2) {
                        $this$buildString.append(str);
                        $this$buildString.append(pin2);
                    }
                    String message = sb2.toString();
                    Intrinsics.checkExpressionValueIsNotNull(message, "StringBuilder().apply(builderAction).toString()");
                    throw new SSLPeerUnverifiedException(message);
                }
            }
        }
    }

    @Deprecated(message = "replaced with {@link #check(String, List)}.", replaceWith = @ReplaceWith(expression = "check(hostname, peerCertificates.toList())", imports = {}))
    public final void check(String hostname, Certificate... peerCertificates) throws SSLPeerUnverifiedException {
        Intrinsics.checkParameterIsNotNull(hostname, "hostname");
        Intrinsics.checkParameterIsNotNull(peerCertificates, "peerCertificates");
        check(hostname, ArraysKt.toList((T[]) peerCertificates));
    }

    public final List<Pin> findMatchingPins$okhttp(String hostname) {
        Intrinsics.checkParameterIsNotNull(hostname, "hostname");
        List result = CollectionsKt.emptyList();
        for (Pin pin : this.pins) {
            if (pin.matches(hostname)) {
                if (result.isEmpty()) {
                    result = new ArrayList();
                }
                if (result != null) {
                    TypeIntrinsics.asMutableList(result).add(pin);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableList<okhttp3.CertificatePinner.Pin>");
                }
            }
        }
        return result;
    }

    public final CertificatePinner withCertificateChainCleaner$okhttp(CertificateChainCleaner certificateChainCleaner2) {
        if (Intrinsics.areEqual((Object) this.certificateChainCleaner, (Object) certificateChainCleaner2)) {
            return this;
        }
        return new CertificatePinner(this.pins, certificateChainCleaner2);
    }

    public boolean equals(Object other) {
        return (other instanceof CertificatePinner) && Intrinsics.areEqual((Object) ((CertificatePinner) other).pins, (Object) this.pins) && Intrinsics.areEqual((Object) ((CertificatePinner) other).certificateChainCleaner, (Object) this.certificateChainCleaner);
    }

    public int hashCode() {
        int result = ((37 * 41) + this.pins.hashCode()) * 41;
        CertificateChainCleaner certificateChainCleaner2 = this.certificateChainCleaner;
        return result + (certificateChainCleaner2 != null ? certificateChainCleaner2.hashCode() : 0);
    }
}
