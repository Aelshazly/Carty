package p008cz.msebera.android.httpclient.impl.auth;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import p008cz.msebera.android.httpclient.Consts;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.auth.AuthenticationException;
import p008cz.msebera.android.httpclient.auth.ChallengeState;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.auth.MalformedChallengeException;
import p008cz.msebera.android.httpclient.protocol.BasicHttpContext;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.auth.DigestScheme */
public class DigestScheme extends RFC2617Scheme {
    private static final char[] HEXADECIMAL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final int QOP_AUTH = 2;
    private static final int QOP_AUTH_INT = 1;
    private static final int QOP_MISSING = 0;
    private static final int QOP_UNKNOWN = -1;

    /* renamed from: a1 */
    private String f130a1;

    /* renamed from: a2 */
    private String f131a2;
    private String cnonce;
    private boolean complete;
    private String lastNonce;
    private long nounceCount;

    public DigestScheme(Charset credentialsCharset) {
        super(credentialsCharset);
        this.complete = false;
    }

    @Deprecated
    public DigestScheme(ChallengeState challengeState) {
        super(challengeState);
    }

    public DigestScheme() {
        this(Consts.ASCII);
    }

    public void processChallenge(Header header) throws MalformedChallengeException {
        super.processChallenge(header);
        this.complete = true;
    }

    public boolean isComplete() {
        if ("true".equalsIgnoreCase(getParameter("stale"))) {
            return false;
        }
        return this.complete;
    }

    public String getSchemeName() {
        return "digest";
    }

    public boolean isConnectionBased() {
        return false;
    }

    public void overrideParamter(String name, String value) {
        getParameters().put(name, value);
    }

    @Deprecated
    public Header authenticate(Credentials credentials, HttpRequest request) throws AuthenticationException {
        return authenticate(credentials, request, new BasicHttpContext());
    }

    public Header authenticate(Credentials credentials, HttpRequest request, HttpContext context) throws AuthenticationException {
        Args.notNull(credentials, "Credentials");
        Args.notNull(request, "HTTP request");
        if (getParameter("realm") == null) {
            throw new AuthenticationException("missing realm in challenge");
        } else if (getParameter("nonce") != null) {
            getParameters().put("methodname", request.getRequestLine().getMethod());
            getParameters().put("uri", request.getRequestLine().getUri());
            String str = "charset";
            if (getParameter(str) == null) {
                getParameters().put(str, getCredentialsCharset(request));
            }
            return createDigestHeader(credentials, request);
        } else {
            throw new AuthenticationException("missing nonce in challenge");
        }
    }

    private static MessageDigest createMessageDigest(String digAlg) throws UnsupportedDigestAlgorithmException {
        try {
            return MessageDigest.getInstance(digAlg);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unsupported algorithm in HTTP Digest authentication: ");
            sb.append(digAlg);
            throw new UnsupportedDigestAlgorithmException(sb.toString());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:74:0x028a  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x02a4  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x02fe  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0304  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x034d  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0376  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0386  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0397  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x03a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private p008cz.msebera.android.httpclient.Header createDigestHeader(p008cz.msebera.android.httpclient.auth.Credentials r37, p008cz.msebera.android.httpclient.HttpRequest r38) throws p008cz.msebera.android.httpclient.auth.AuthenticationException {
        /*
            r36 = this;
            r1 = r36
            r2 = r38
            java.lang.String r0 = "uri"
            java.lang.String r3 = r1.getParameter(r0)
            java.lang.String r4 = "realm"
            java.lang.String r5 = r1.getParameter(r4)
            java.lang.String r6 = "nonce"
            java.lang.String r7 = r1.getParameter(r6)
            java.lang.String r8 = "opaque"
            java.lang.String r9 = r1.getParameter(r8)
            java.lang.String r10 = "methodname"
            java.lang.String r10 = r1.getParameter(r10)
            java.lang.String r11 = "algorithm"
            java.lang.String r12 = r1.getParameter(r11)
            if (r12 != 0) goto L_0x002c
            java.lang.String r12 = "MD5"
        L_0x002c:
            java.util.HashSet r13 = new java.util.HashSet
            r14 = 8
            r13.<init>(r14)
            r14 = -1
            java.lang.String r15 = "qop"
            r16 = r14
            java.lang.String r14 = r1.getParameter(r15)
            r17 = r8
            java.lang.String r8 = "auth-int"
            r18 = r9
            java.lang.String r9 = "auth"
            if (r14 == 0) goto L_0x0086
            r19 = r11
            java.util.StringTokenizer r11 = new java.util.StringTokenizer
            r20 = r15
            java.lang.String r15 = ","
            r11.<init>(r14, r15)
        L_0x0051:
            boolean r15 = r11.hasMoreTokens()
            if (r15 == 0) goto L_0x006d
            java.lang.String r15 = r11.nextToken()
            java.lang.String r15 = r15.trim()
            r21 = r11
            java.util.Locale r11 = java.util.Locale.ENGLISH
            java.lang.String r11 = r15.toLowerCase(r11)
            r13.add(r11)
            r11 = r21
            goto L_0x0051
        L_0x006d:
            r21 = r11
            boolean r11 = r2 instanceof p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest
            if (r11 == 0) goto L_0x007b
            boolean r11 = r13.contains(r8)
            if (r11 == 0) goto L_0x007b
            r11 = 1
            goto L_0x0085
        L_0x007b:
            boolean r11 = r13.contains(r9)
            if (r11 == 0) goto L_0x0083
            r11 = 2
            goto L_0x0085
        L_0x0083:
            r11 = r16
        L_0x0085:
            goto L_0x008b
        L_0x0086:
            r19 = r11
            r20 = r15
            r11 = 0
        L_0x008b:
            r15 = -1
            if (r11 == r15) goto L_0x0414
            java.lang.String r15 = "charset"
            java.lang.String r15 = r1.getParameter(r15)
            if (r15 != 0) goto L_0x0098
            java.lang.String r15 = "ISO-8859-1"
        L_0x0098:
            r16 = r12
            r21 = r8
            java.lang.String r8 = "MD5-sess"
            r22 = r14
            r14 = r16
            boolean r16 = r14.equalsIgnoreCase(r8)
            if (r16 == 0) goto L_0x00ac
            java.lang.String r16 = "MD5"
            r14 = r16
        L_0x00ac:
            java.security.MessageDigest r16 = createMessageDigest(r14)     // Catch:{ UnsupportedDigestAlgorithmException -> 0x03eb }
            r23 = r16
            java.security.Principal r16 = r37.getUserPrincipal()
            r24 = r14
            java.lang.String r14 = r16.getName()
            r16 = r6
            java.lang.String r6 = r37.getPassword()
            r25 = r0
            java.lang.String r0 = r1.lastNonce
            boolean r0 = r7.equals(r0)
            r26 = r3
            if (r0 == 0) goto L_0x00db
            long r2 = r1.nounceCount
            r29 = r9
            r28 = r10
            r9 = 1
            long r2 = r2 + r9
            r1.nounceCount = r2
            goto L_0x00e8
        L_0x00db:
            r29 = r9
            r28 = r10
            r9 = 1
            r1.nounceCount = r9
            r0 = 0
            r1.cnonce = r0
            r1.lastNonce = r7
        L_0x00e8:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = 256(0x100, float:3.59E-43)
            r2.<init>(r3)
            java.util.Formatter r3 = new java.util.Formatter
            java.util.Locale r9 = java.util.Locale.US
            r3.<init>(r2, r9)
            r9 = 1
            java.lang.Object[] r10 = new java.lang.Object[r9]
            r27 = r10
            long r9 = r1.nounceCount
            java.lang.Long r9 = java.lang.Long.valueOf(r9)
            r10 = 0
            r27[r10] = r9
            java.lang.String r9 = "%08x"
            r0 = r27
            r3.format(r9, r0)
            r3.close()
            java.lang.String r9 = r2.toString()
            java.lang.String r0 = r1.cnonce
            if (r0 != 0) goto L_0x011c
            java.lang.String r0 = createCnonce()
            r1.cnonce = r0
        L_0x011c:
            r0 = 0
            r1.f130a1 = r0
            r1.f131a2 = r0
            boolean r0 = r12.equalsIgnoreCase(r8)
            r8 = 58
            if (r0 == 0) goto L_0x016a
            r2.setLength(r10)
            r2.append(r14)
            r2.append(r8)
            r2.append(r5)
            r2.append(r8)
            r2.append(r6)
            java.lang.String r0 = r2.toString()
            byte[] r0 = p008cz.msebera.android.httpclient.util.EncodingUtils.getBytes(r0, r15)
            r8 = r23
            byte[] r0 = r8.digest(r0)
            java.lang.String r0 = encode(r0)
            r2.setLength(r10)
            r2.append(r0)
            r10 = 58
            r2.append(r10)
            r2.append(r7)
            r2.append(r10)
            java.lang.String r10 = r1.cnonce
            r2.append(r10)
            java.lang.String r10 = r2.toString()
            r1.f130a1 = r10
            goto L_0x0187
        L_0x016a:
            r8 = r23
            r0 = 0
            r2.setLength(r0)
            r2.append(r14)
            r0 = 58
            r2.append(r0)
            r2.append(r5)
            r2.append(r0)
            r2.append(r6)
            java.lang.String r0 = r2.toString()
            r1.f130a1 = r0
        L_0x0187:
            java.lang.String r0 = r1.f130a1
            byte[] r0 = p008cz.msebera.android.httpclient.util.EncodingUtils.getBytes(r0, r15)
            byte[] r0 = r8.digest(r0)
            java.lang.String r10 = encode(r0)
            r0 = 2
            if (r11 != r0) goto L_0x01bc
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r30 = r3
            r3 = r28
            r0.append(r3)
            r28 = r6
            r6 = 58
            r0.append(r6)
            r6 = r26
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            r1.f131a2 = r0
            r26 = r11
            r31 = r13
            goto L_0x0278
        L_0x01bc:
            r30 = r3
            r3 = r28
            r28 = r6
            r6 = r26
            r0 = 1
            if (r11 != r0) goto L_0x025e
            r0 = 0
            r31 = r0
            r26 = r11
            r11 = r38
            boolean r0 = r11 instanceof p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest
            if (r0 == 0) goto L_0x01db
            r0 = r11
            cz.msebera.android.httpclient.HttpEntityEnclosingRequest r0 = (p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest) r0
            cz.msebera.android.httpclient.HttpEntity r0 = r0.getEntity()
            r11 = r0
            goto L_0x01dd
        L_0x01db:
            r11 = r31
        L_0x01dd:
            if (r11 == 0) goto L_0x0214
            boolean r0 = r11.isRepeatable()
            if (r0 != 0) goto L_0x0214
            r0 = r29
            boolean r29 = r13.contains(r0)
            if (r29 == 0) goto L_0x020c
            r26 = 2
            r29 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            r31 = r13
            r13 = 58
            r0.append(r13)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            r1.f131a2 = r0
            r11 = r26
            goto L_0x0252
        L_0x020c:
            cz.msebera.android.httpclient.auth.AuthenticationException r0 = new cz.msebera.android.httpclient.auth.AuthenticationException
            java.lang.String r4 = "Qop auth-int cannot be used with a non-repeatable entity"
            r0.<init>(r4)
            throw r0
        L_0x0214:
            r31 = r13
            cz.msebera.android.httpclient.impl.auth.HttpEntityDigester r0 = new cz.msebera.android.httpclient.impl.auth.HttpEntityDigester
            r0.<init>(r8)
            r13 = r0
            if (r11 == 0) goto L_0x0226
            r11.writeTo(r13)     // Catch:{ IOException -> 0x0222 }
            goto L_0x0226
        L_0x0222:
            r0 = move-exception
            r32 = r11
            goto L_0x0256
        L_0x0226:
            r13.close()     // Catch:{ IOException -> 0x0253 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            r32 = r11
            r11 = 58
            r0.append(r11)
            r0.append(r6)
            r0.append(r11)
            byte[] r11 = r13.getDigest()
            java.lang.String r11 = encode(r11)
            r0.append(r11)
            java.lang.String r0 = r0.toString()
            r1.f131a2 = r0
            r11 = r26
        L_0x0252:
            goto L_0x027a
        L_0x0253:
            r0 = move-exception
            r32 = r11
        L_0x0256:
            cz.msebera.android.httpclient.auth.AuthenticationException r4 = new cz.msebera.android.httpclient.auth.AuthenticationException
            java.lang.String r11 = "I/O error reading entity content"
            r4.<init>(r11, r0)
            throw r4
        L_0x025e:
            r26 = r11
            r31 = r13
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            r11 = 58
            r0.append(r11)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            r1.f131a2 = r0
        L_0x0278:
            r11 = r26
        L_0x027a:
            java.lang.String r0 = r1.f131a2
            byte[] r0 = p008cz.msebera.android.httpclient.util.EncodingUtils.getBytes(r0, r15)
            byte[] r0 = r8.digest(r0)
            java.lang.String r0 = encode(r0)
            if (r11 != 0) goto L_0x02a4
            r13 = 0
            r2.setLength(r13)
            r2.append(r10)
            r13 = 58
            r2.append(r13)
            r2.append(r7)
            r2.append(r13)
            r2.append(r0)
            java.lang.String r13 = r2.toString()
            goto L_0x02df
        L_0x02a4:
            r13 = 58
            r13 = 0
            r2.setLength(r13)
            r2.append(r10)
            r13 = 58
            r2.append(r13)
            r2.append(r7)
            r2.append(r13)
            r2.append(r9)
            r2.append(r13)
            java.lang.String r13 = r1.cnonce
            r2.append(r13)
            r13 = 58
            r2.append(r13)
            r13 = 1
            if (r11 != r13) goto L_0x02ce
            r13 = r21
            goto L_0x02d0
        L_0x02ce:
            r13 = r29
        L_0x02d0:
            r2.append(r13)
            r13 = 58
            r2.append(r13)
            r2.append(r0)
            java.lang.String r13 = r2.toString()
        L_0x02df:
            r26 = r0
            byte[] r0 = p008cz.msebera.android.httpclient.util.EncodingUtils.getAsciiBytes(r13)
            byte[] r0 = r8.digest(r0)
            java.lang.String r0 = encode(r0)
            r27 = r2
            cz.msebera.android.httpclient.util.CharArrayBuffer r2 = new cz.msebera.android.httpclient.util.CharArrayBuffer
            r32 = r3
            r3 = 128(0x80, float:1.794E-43)
            r2.<init>(r3)
            boolean r3 = r36.isProxy()
            if (r3 == 0) goto L_0x0304
            java.lang.String r3 = "Proxy-Authorization"
            r2.append(r3)
            goto L_0x0309
        L_0x0304:
            java.lang.String r3 = "Authorization"
            r2.append(r3)
        L_0x0309:
            java.lang.String r3 = ": Digest "
            r2.append(r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r33 = r8
            r8 = 20
            r3.<init>(r8)
            cz.msebera.android.httpclient.message.BasicNameValuePair r8 = new cz.msebera.android.httpclient.message.BasicNameValuePair
            r34 = r10
            java.lang.String r10 = "username"
            r8.<init>(r10, r14)
            r3.add(r8)
            cz.msebera.android.httpclient.message.BasicNameValuePair r8 = new cz.msebera.android.httpclient.message.BasicNameValuePair
            r8.<init>(r4, r5)
            r3.add(r8)
            cz.msebera.android.httpclient.message.BasicNameValuePair r4 = new cz.msebera.android.httpclient.message.BasicNameValuePair
            r8 = r16
            r4.<init>(r8, r7)
            r3.add(r4)
            cz.msebera.android.httpclient.message.BasicNameValuePair r4 = new cz.msebera.android.httpclient.message.BasicNameValuePair
            r8 = r25
            r4.<init>(r8, r6)
            r3.add(r4)
            cz.msebera.android.httpclient.message.BasicNameValuePair r4 = new cz.msebera.android.httpclient.message.BasicNameValuePair
            java.lang.String r8 = "response"
            r4.<init>(r8, r0)
            r3.add(r4)
            java.lang.String r4 = "nc"
            if (r11 == 0) goto L_0x0376
            cz.msebera.android.httpclient.message.BasicNameValuePair r8 = new cz.msebera.android.httpclient.message.BasicNameValuePair
            r10 = 1
            if (r11 != r10) goto L_0x0355
            r10 = r21
            goto L_0x0357
        L_0x0355:
            r10 = r29
        L_0x0357:
            r16 = r0
            r0 = r20
            r8.<init>(r0, r10)
            r3.add(r8)
            cz.msebera.android.httpclient.message.BasicNameValuePair r8 = new cz.msebera.android.httpclient.message.BasicNameValuePair
            r8.<init>(r4, r9)
            r3.add(r8)
            cz.msebera.android.httpclient.message.BasicNameValuePair r8 = new cz.msebera.android.httpclient.message.BasicNameValuePair
            java.lang.String r10 = r1.cnonce
            java.lang.String r1 = "cnonce"
            r8.<init>(r1, r10)
            r3.add(r8)
            goto L_0x037a
        L_0x0376:
            r16 = r0
            r0 = r20
        L_0x037a:
            cz.msebera.android.httpclient.message.BasicNameValuePair r1 = new cz.msebera.android.httpclient.message.BasicNameValuePair
            r8 = r19
            r1.<init>(r8, r12)
            r3.add(r1)
            if (r18 == 0) goto L_0x0397
            cz.msebera.android.httpclient.message.BasicNameValuePair r1 = new cz.msebera.android.httpclient.message.BasicNameValuePair
            r10 = r18
            r35 = r17
            r17 = r5
            r5 = r35
            r1.<init>(r5, r10)
            r3.add(r1)
            goto L_0x039b
        L_0x0397:
            r17 = r5
            r10 = r18
        L_0x039b:
            r1 = 0
        L_0x039c:
            int r5 = r3.size()
            if (r1 >= r5) goto L_0x03e5
            java.lang.Object r5 = r3.get(r1)
            cz.msebera.android.httpclient.message.BasicNameValuePair r5 = (p008cz.msebera.android.httpclient.message.BasicNameValuePair) r5
            if (r1 <= 0) goto L_0x03b2
            r18 = r3
            java.lang.String r3 = ", "
            r2.append(r3)
            goto L_0x03b4
        L_0x03b2:
            r18 = r3
        L_0x03b4:
            java.lang.String r3 = r5.getName()
            boolean r19 = r4.equals(r3)
            if (r19 != 0) goto L_0x03ce
            boolean r19 = r0.equals(r3)
            if (r19 != 0) goto L_0x03ce
            boolean r19 = r8.equals(r3)
            if (r19 == 0) goto L_0x03cb
            goto L_0x03ce
        L_0x03cb:
            r19 = 0
            goto L_0x03d0
        L_0x03ce:
            r19 = 1
        L_0x03d0:
            r20 = r0
            cz.msebera.android.httpclient.message.BasicHeaderValueFormatter r0 = p008cz.msebera.android.httpclient.message.BasicHeaderValueFormatter.INSTANCE
            r21 = r3
            if (r19 != 0) goto L_0x03da
            r3 = 1
            goto L_0x03db
        L_0x03da:
            r3 = 0
        L_0x03db:
            r0.formatNameValuePair(r2, r5, r3)
            int r1 = r1 + 1
            r3 = r18
            r0 = r20
            goto L_0x039c
        L_0x03e5:
            cz.msebera.android.httpclient.message.BufferedHeader r0 = new cz.msebera.android.httpclient.message.BufferedHeader
            r0.<init>(r2)
            return r0
        L_0x03eb:
            r0 = move-exception
            r6 = r3
            r17 = r5
            r32 = r10
            r26 = r11
            r31 = r13
            r24 = r14
            r10 = r18
            r1 = r0
            r0 = r1
            cz.msebera.android.httpclient.auth.AuthenticationException r1 = new cz.msebera.android.httpclient.auth.AuthenticationException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unsuppported digest algorithm: "
            r2.append(r3)
            r14 = r24
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0414:
            r22 = r14
            cz.msebera.android.httpclient.auth.AuthenticationException r0 = new cz.msebera.android.httpclient.auth.AuthenticationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "None of the qop methods is supported: "
            r1.append(r2)
            r2 = r22
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.auth.DigestScheme.createDigestHeader(cz.msebera.android.httpclient.auth.Credentials, cz.msebera.android.httpclient.HttpRequest):cz.msebera.android.httpclient.Header");
    }

    /* access modifiers changed from: 0000 */
    public String getCnonce() {
        return this.cnonce;
    }

    /* access modifiers changed from: 0000 */
    public String getA1() {
        return this.f130a1;
    }

    /* access modifiers changed from: 0000 */
    public String getA2() {
        return this.f131a2;
    }

    static String encode(byte[] binaryData) {
        int n = binaryData.length;
        char[] buffer = new char[(n * 2)];
        for (int i = 0; i < n; i++) {
            int low = binaryData[i] & 15;
            int high = (binaryData[i] & 240) >> 4;
            int i2 = i * 2;
            char[] cArr = HEXADECIMAL;
            buffer[i2] = cArr[high];
            buffer[(i * 2) + 1] = cArr[low];
        }
        return new String(buffer);
    }

    public static String createCnonce() {
        byte[] tmp = new byte[8];
        new SecureRandom().nextBytes(tmp);
        return encode(tmp);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DIGEST [complete=");
        builder.append(this.complete);
        builder.append(", nonce=");
        builder.append(this.lastNonce);
        builder.append(", nc=");
        builder.append(this.nounceCount);
        builder.append("]");
        return builder.toString();
    }
}
