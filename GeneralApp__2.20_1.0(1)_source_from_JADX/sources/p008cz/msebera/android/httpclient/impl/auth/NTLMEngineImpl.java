package p008cz.msebera.android.httpclient.impl.auth;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import p008cz.msebera.android.httpclient.extras.Base64;
import p008cz.msebera.android.httpclient.util.EncodingUtils;

/* renamed from: cz.msebera.android.httpclient.impl.auth.NTLMEngineImpl */
final class NTLMEngineImpl implements NTLMEngine {
    static final String DEFAULT_CHARSET = "ASCII";
    protected static final int FLAG_DOMAIN_PRESENT = 4096;
    protected static final int FLAG_REQUEST_128BIT_KEY_EXCH = 536870912;
    protected static final int FLAG_REQUEST_56BIT_ENCRYPTION = Integer.MIN_VALUE;
    protected static final int FLAG_REQUEST_ALWAYS_SIGN = 32768;
    protected static final int FLAG_REQUEST_EXPLICIT_KEY_EXCH = 1073741824;
    protected static final int FLAG_REQUEST_LAN_MANAGER_KEY = 128;
    protected static final int FLAG_REQUEST_NTLM2_SESSION = 524288;
    protected static final int FLAG_REQUEST_NTLMv1 = 512;
    protected static final int FLAG_REQUEST_SEAL = 32;
    protected static final int FLAG_REQUEST_SIGN = 16;
    protected static final int FLAG_REQUEST_TARGET = 4;
    protected static final int FLAG_REQUEST_UNICODE_ENCODING = 1;
    protected static final int FLAG_REQUEST_VERSION = 33554432;
    protected static final int FLAG_TARGETINFO_PRESENT = 8388608;
    protected static final int FLAG_WORKSTATION_PRESENT = 8192;
    private static final SecureRandom RND_GEN;
    /* access modifiers changed from: private */
    public static final byte[] SIGNATURE;
    private String credentialCharset = "ASCII";

    /* renamed from: cz.msebera.android.httpclient.impl.auth.NTLMEngineImpl$CipherGen */
    protected static class CipherGen {
        protected final byte[] challenge;
        protected byte[] clientChallenge;
        protected byte[] clientChallenge2;
        protected final String domain;
        protected byte[] lanManagerSessionKey;
        protected byte[] lm2SessionResponse;
        protected byte[] lmHash;
        protected byte[] lmResponse;
        protected byte[] lmUserSessionKey;
        protected byte[] lmv2Hash;
        protected byte[] lmv2Response;
        protected byte[] ntlm2SessionResponse;
        protected byte[] ntlm2SessionResponseUserSessionKey;
        protected byte[] ntlmHash;
        protected byte[] ntlmResponse;
        protected byte[] ntlmUserSessionKey;
        protected byte[] ntlmv2Blob;
        protected byte[] ntlmv2Hash;
        protected byte[] ntlmv2Response;
        protected byte[] ntlmv2UserSessionKey;
        protected final String password;
        protected byte[] secondaryKey;
        protected final String target;
        protected final byte[] targetInformation;
        protected byte[] timestamp;
        protected final String user;

        public CipherGen(String domain2, String user2, String password2, byte[] challenge2, String target2, byte[] targetInformation2, byte[] clientChallenge3, byte[] clientChallenge22, byte[] secondaryKey2, byte[] timestamp2) {
            this.lmHash = null;
            this.lmResponse = null;
            this.ntlmHash = null;
            this.ntlmResponse = null;
            this.ntlmv2Hash = null;
            this.lmv2Hash = null;
            this.lmv2Response = null;
            this.ntlmv2Blob = null;
            this.ntlmv2Response = null;
            this.ntlm2SessionResponse = null;
            this.lm2SessionResponse = null;
            this.lmUserSessionKey = null;
            this.ntlmUserSessionKey = null;
            this.ntlmv2UserSessionKey = null;
            this.ntlm2SessionResponseUserSessionKey = null;
            this.lanManagerSessionKey = null;
            this.domain = domain2;
            this.target = target2;
            this.user = user2;
            this.password = password2;
            this.challenge = challenge2;
            this.targetInformation = targetInformation2;
            this.clientChallenge = clientChallenge3;
            this.clientChallenge2 = clientChallenge22;
            this.secondaryKey = secondaryKey2;
            this.timestamp = timestamp2;
        }

        public CipherGen(String domain2, String user2, String password2, byte[] challenge2, String target2, byte[] targetInformation2) {
            this(domain2, user2, password2, challenge2, target2, targetInformation2, null, null, null, null);
        }

        public byte[] getClientChallenge() throws NTLMEngineException {
            if (this.clientChallenge == null) {
                this.clientChallenge = NTLMEngineImpl.makeRandomChallenge();
            }
            return this.clientChallenge;
        }

        public byte[] getClientChallenge2() throws NTLMEngineException {
            if (this.clientChallenge2 == null) {
                this.clientChallenge2 = NTLMEngineImpl.makeRandomChallenge();
            }
            return this.clientChallenge2;
        }

        public byte[] getSecondaryKey() throws NTLMEngineException {
            if (this.secondaryKey == null) {
                this.secondaryKey = NTLMEngineImpl.makeSecondaryKey();
            }
            return this.secondaryKey;
        }

        public byte[] getLMHash() throws NTLMEngineException {
            if (this.lmHash == null) {
                this.lmHash = NTLMEngineImpl.lmHash(this.password);
            }
            return this.lmHash;
        }

        public byte[] getLMResponse() throws NTLMEngineException {
            if (this.lmResponse == null) {
                this.lmResponse = NTLMEngineImpl.lmResponse(getLMHash(), this.challenge);
            }
            return this.lmResponse;
        }

        public byte[] getNTLMHash() throws NTLMEngineException {
            if (this.ntlmHash == null) {
                this.ntlmHash = NTLMEngineImpl.ntlmHash(this.password);
            }
            return this.ntlmHash;
        }

        public byte[] getNTLMResponse() throws NTLMEngineException {
            if (this.ntlmResponse == null) {
                this.ntlmResponse = NTLMEngineImpl.lmResponse(getNTLMHash(), this.challenge);
            }
            return this.ntlmResponse;
        }

        public byte[] getLMv2Hash() throws NTLMEngineException {
            if (this.lmv2Hash == null) {
                this.lmv2Hash = NTLMEngineImpl.lmv2Hash(this.domain, this.user, getNTLMHash());
            }
            return this.lmv2Hash;
        }

        public byte[] getNTLMv2Hash() throws NTLMEngineException {
            if (this.ntlmv2Hash == null) {
                this.ntlmv2Hash = NTLMEngineImpl.ntlmv2Hash(this.domain, this.user, getNTLMHash());
            }
            return this.ntlmv2Hash;
        }

        public byte[] getTimestamp() {
            if (this.timestamp == null) {
                long time = (System.currentTimeMillis() + 11644473600000L) * 10000;
                this.timestamp = new byte[8];
                for (int i = 0; i < 8; i++) {
                    this.timestamp[i] = (byte) ((int) time);
                    time >>>= 8;
                }
            }
            return this.timestamp;
        }

        public byte[] getNTLMv2Blob() throws NTLMEngineException {
            if (this.ntlmv2Blob == null) {
                this.ntlmv2Blob = NTLMEngineImpl.createBlob(getClientChallenge2(), this.targetInformation, getTimestamp());
            }
            return this.ntlmv2Blob;
        }

        public byte[] getNTLMv2Response() throws NTLMEngineException {
            if (this.ntlmv2Response == null) {
                this.ntlmv2Response = NTLMEngineImpl.lmv2Response(getNTLMv2Hash(), this.challenge, getNTLMv2Blob());
            }
            return this.ntlmv2Response;
        }

        public byte[] getLMv2Response() throws NTLMEngineException {
            if (this.lmv2Response == null) {
                this.lmv2Response = NTLMEngineImpl.lmv2Response(getLMv2Hash(), this.challenge, getClientChallenge());
            }
            return this.lmv2Response;
        }

        public byte[] getNTLM2SessionResponse() throws NTLMEngineException {
            if (this.ntlm2SessionResponse == null) {
                this.ntlm2SessionResponse = NTLMEngineImpl.ntlm2SessionResponse(getNTLMHash(), this.challenge, getClientChallenge());
            }
            return this.ntlm2SessionResponse;
        }

        public byte[] getLM2SessionResponse() throws NTLMEngineException {
            if (this.lm2SessionResponse == null) {
                byte[] clChallenge = getClientChallenge();
                this.lm2SessionResponse = new byte[24];
                System.arraycopy(clChallenge, 0, this.lm2SessionResponse, 0, clChallenge.length);
                byte[] bArr = this.lm2SessionResponse;
                Arrays.fill(bArr, clChallenge.length, bArr.length, 0);
            }
            return this.lm2SessionResponse;
        }

        public byte[] getLMUserSessionKey() throws NTLMEngineException {
            if (this.lmUserSessionKey == null) {
                this.lmUserSessionKey = new byte[16];
                System.arraycopy(getLMHash(), 0, this.lmUserSessionKey, 0, 8);
                Arrays.fill(this.lmUserSessionKey, 8, 16, 0);
            }
            return this.lmUserSessionKey;
        }

        public byte[] getNTLMUserSessionKey() throws NTLMEngineException {
            if (this.ntlmUserSessionKey == null) {
                MD4 md4 = new MD4();
                md4.update(getNTLMHash());
                this.ntlmUserSessionKey = md4.getOutput();
            }
            return this.ntlmUserSessionKey;
        }

        public byte[] getNTLMv2UserSessionKey() throws NTLMEngineException {
            if (this.ntlmv2UserSessionKey == null) {
                byte[] ntlmv2hash = getNTLMv2Hash();
                byte[] truncatedResponse = new byte[16];
                System.arraycopy(getNTLMv2Response(), 0, truncatedResponse, 0, 16);
                this.ntlmv2UserSessionKey = NTLMEngineImpl.hmacMD5(truncatedResponse, ntlmv2hash);
            }
            return this.ntlmv2UserSessionKey;
        }

        public byte[] getNTLM2SessionResponseUserSessionKey() throws NTLMEngineException {
            if (this.ntlm2SessionResponseUserSessionKey == null) {
                byte[] ntlm2SessionResponseNonce = getLM2SessionResponse();
                byte[] bArr = this.challenge;
                byte[] sessionNonce = new byte[(bArr.length + ntlm2SessionResponseNonce.length)];
                System.arraycopy(bArr, 0, sessionNonce, 0, bArr.length);
                System.arraycopy(ntlm2SessionResponseNonce, 0, sessionNonce, this.challenge.length, ntlm2SessionResponseNonce.length);
                this.ntlm2SessionResponseUserSessionKey = NTLMEngineImpl.hmacMD5(sessionNonce, getNTLMUserSessionKey());
            }
            return this.ntlm2SessionResponseUserSessionKey;
        }

        public byte[] getLanManagerSessionKey() throws NTLMEngineException {
            String str = "DES/ECB/NoPadding";
            if (this.lanManagerSessionKey == null) {
                try {
                    byte[] keyBytes = new byte[14];
                    System.arraycopy(getLMHash(), 0, keyBytes, 0, 8);
                    Arrays.fill(keyBytes, 8, keyBytes.length, -67);
                    Key lowKey = NTLMEngineImpl.createDESKey(keyBytes, 0);
                    Key highKey = NTLMEngineImpl.createDESKey(keyBytes, 7);
                    byte[] truncatedResponse = new byte[8];
                    System.arraycopy(getLMResponse(), 0, truncatedResponse, 0, truncatedResponse.length);
                    Cipher des = Cipher.getInstance(str);
                    des.init(1, lowKey);
                    byte[] lowPart = des.doFinal(truncatedResponse);
                    Cipher des2 = Cipher.getInstance(str);
                    des2.init(1, highKey);
                    byte[] highPart = des2.doFinal(truncatedResponse);
                    this.lanManagerSessionKey = new byte[16];
                    System.arraycopy(lowPart, 0, this.lanManagerSessionKey, 0, lowPart.length);
                    System.arraycopy(highPart, 0, this.lanManagerSessionKey, lowPart.length, highPart.length);
                } catch (Exception e) {
                    throw new NTLMEngineException(e.getMessage(), e);
                }
            }
            return this.lanManagerSessionKey;
        }
    }

    /* renamed from: cz.msebera.android.httpclient.impl.auth.NTLMEngineImpl$HMACMD5 */
    static class HMACMD5 {
        protected byte[] ipad;
        protected MessageDigest md5;
        protected byte[] opad;

        HMACMD5(byte[] input) throws NTLMEngineException {
            byte[] key = input;
            try {
                this.md5 = MessageDigest.getInstance("MD5");
                this.ipad = new byte[64];
                this.opad = new byte[64];
                int keyLength = key.length;
                if (keyLength > 64) {
                    this.md5.update(key);
                    key = this.md5.digest();
                    keyLength = key.length;
                }
                int i = 0;
                while (i < keyLength) {
                    this.ipad[i] = (byte) (54 ^ key[i]);
                    this.opad[i] = (byte) (92 ^ key[i]);
                    i++;
                }
                while (i < 64) {
                    this.ipad[i] = 54;
                    this.opad[i] = 92;
                    i++;
                }
                this.md5.reset();
                this.md5.update(this.ipad);
            } catch (Exception ex) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error getting md5 message digest implementation: ");
                sb.append(ex.getMessage());
                throw new NTLMEngineException(sb.toString(), ex);
            }
        }

        /* access modifiers changed from: 0000 */
        public byte[] getOutput() {
            byte[] digest = this.md5.digest();
            this.md5.update(this.opad);
            return this.md5.digest(digest);
        }

        /* access modifiers changed from: 0000 */
        public void update(byte[] input) {
            this.md5.update(input);
        }

        /* access modifiers changed from: 0000 */
        public void update(byte[] input, int offset, int length) {
            this.md5.update(input, offset, length);
        }
    }

    /* renamed from: cz.msebera.android.httpclient.impl.auth.NTLMEngineImpl$MD4 */
    static class MD4 {

        /* renamed from: A */
        protected int f132A = 1732584193;

        /* renamed from: B */
        protected int f133B = -271733879;

        /* renamed from: C */
        protected int f134C = -1732584194;

        /* renamed from: D */
        protected int f135D = 271733878;
        protected long count = 0;
        protected byte[] dataBuffer = new byte[64];

        MD4() {
        }

        /* access modifiers changed from: 0000 */
        public void update(byte[] input) {
            byte[] bArr;
            int curBufferPos = (int) (this.count & 63);
            int inputIndex = 0;
            while (true) {
                int length = (input.length - inputIndex) + curBufferPos;
                bArr = this.dataBuffer;
                if (length < bArr.length) {
                    break;
                }
                int transferAmt = bArr.length - curBufferPos;
                System.arraycopy(input, inputIndex, bArr, curBufferPos, transferAmt);
                this.count += (long) transferAmt;
                curBufferPos = 0;
                inputIndex += transferAmt;
                processBuffer();
            }
            if (inputIndex < input.length) {
                int transferAmt2 = input.length - inputIndex;
                System.arraycopy(input, inputIndex, bArr, curBufferPos, transferAmt2);
                this.count += (long) transferAmt2;
                int curBufferPos2 = curBufferPos + transferAmt2;
            }
        }

        /* access modifiers changed from: 0000 */
        public byte[] getOutput() {
            int bufferIndex = (int) (this.count & 63);
            int padLen = bufferIndex < 56 ? 56 - bufferIndex : 120 - bufferIndex;
            byte[] postBytes = new byte[(padLen + 8)];
            postBytes[0] = ByteCompanionObject.MIN_VALUE;
            for (int i = 0; i < 8; i++) {
                postBytes[padLen + i] = (byte) ((int) ((this.count * 8) >>> (i * 8)));
            }
            update(postBytes);
            byte[] result = new byte[16];
            NTLMEngineImpl.writeULong(result, this.f132A, 0);
            NTLMEngineImpl.writeULong(result, this.f133B, 4);
            NTLMEngineImpl.writeULong(result, this.f134C, 8);
            NTLMEngineImpl.writeULong(result, this.f135D, 12);
            return result;
        }

        /* access modifiers changed from: protected */
        public void processBuffer() {
            int[] d = new int[16];
            for (int i = 0; i < 16; i++) {
                byte[] bArr = this.dataBuffer;
                d[i] = (bArr[i * 4] & UByte.MAX_VALUE) + ((bArr[(i * 4) + 1] & UByte.MAX_VALUE) << 8) + ((bArr[(i * 4) + 2] & UByte.MAX_VALUE) << 16) + ((bArr[(i * 4) + 3] & UByte.MAX_VALUE) << 24);
            }
            int AA = this.f132A;
            int BB = this.f133B;
            int CC = this.f134C;
            int DD = this.f135D;
            round1(d);
            round2(d);
            round3(d);
            this.f132A += AA;
            this.f133B += BB;
            this.f134C += CC;
            this.f135D += DD;
        }

        /* access modifiers changed from: protected */
        public void round1(int[] d) {
            this.f132A = NTLMEngineImpl.rotintlft(this.f132A + NTLMEngineImpl.m101F(this.f133B, this.f134C, this.f135D) + d[0], 3);
            this.f135D = NTLMEngineImpl.rotintlft(this.f135D + NTLMEngineImpl.m101F(this.f132A, this.f133B, this.f134C) + d[1], 7);
            this.f134C = NTLMEngineImpl.rotintlft(this.f134C + NTLMEngineImpl.m101F(this.f135D, this.f132A, this.f133B) + d[2], 11);
            this.f133B = NTLMEngineImpl.rotintlft(this.f133B + NTLMEngineImpl.m101F(this.f134C, this.f135D, this.f132A) + d[3], 19);
            this.f132A = NTLMEngineImpl.rotintlft(this.f132A + NTLMEngineImpl.m101F(this.f133B, this.f134C, this.f135D) + d[4], 3);
            this.f135D = NTLMEngineImpl.rotintlft(this.f135D + NTLMEngineImpl.m101F(this.f132A, this.f133B, this.f134C) + d[5], 7);
            this.f134C = NTLMEngineImpl.rotintlft(this.f134C + NTLMEngineImpl.m101F(this.f135D, this.f132A, this.f133B) + d[6], 11);
            this.f133B = NTLMEngineImpl.rotintlft(this.f133B + NTLMEngineImpl.m101F(this.f134C, this.f135D, this.f132A) + d[7], 19);
            this.f132A = NTLMEngineImpl.rotintlft(this.f132A + NTLMEngineImpl.m101F(this.f133B, this.f134C, this.f135D) + d[8], 3);
            this.f135D = NTLMEngineImpl.rotintlft(this.f135D + NTLMEngineImpl.m101F(this.f132A, this.f133B, this.f134C) + d[9], 7);
            this.f134C = NTLMEngineImpl.rotintlft(this.f134C + NTLMEngineImpl.m101F(this.f135D, this.f132A, this.f133B) + d[10], 11);
            this.f133B = NTLMEngineImpl.rotintlft(this.f133B + NTLMEngineImpl.m101F(this.f134C, this.f135D, this.f132A) + d[11], 19);
            this.f132A = NTLMEngineImpl.rotintlft(this.f132A + NTLMEngineImpl.m101F(this.f133B, this.f134C, this.f135D) + d[12], 3);
            this.f135D = NTLMEngineImpl.rotintlft(this.f135D + NTLMEngineImpl.m101F(this.f132A, this.f133B, this.f134C) + d[13], 7);
            this.f134C = NTLMEngineImpl.rotintlft(this.f134C + NTLMEngineImpl.m101F(this.f135D, this.f132A, this.f133B) + d[14], 11);
            this.f133B = NTLMEngineImpl.rotintlft(this.f133B + NTLMEngineImpl.m101F(this.f134C, this.f135D, this.f132A) + d[15], 19);
        }

        /* access modifiers changed from: protected */
        public void round2(int[] d) {
            this.f132A = NTLMEngineImpl.rotintlft(this.f132A + NTLMEngineImpl.m102G(this.f133B, this.f134C, this.f135D) + d[0] + 1518500249, 3);
            this.f135D = NTLMEngineImpl.rotintlft(this.f135D + NTLMEngineImpl.m102G(this.f132A, this.f133B, this.f134C) + d[4] + 1518500249, 5);
            this.f134C = NTLMEngineImpl.rotintlft(this.f134C + NTLMEngineImpl.m102G(this.f135D, this.f132A, this.f133B) + d[8] + 1518500249, 9);
            this.f133B = NTLMEngineImpl.rotintlft(this.f133B + NTLMEngineImpl.m102G(this.f134C, this.f135D, this.f132A) + d[12] + 1518500249, 13);
            this.f132A = NTLMEngineImpl.rotintlft(this.f132A + NTLMEngineImpl.m102G(this.f133B, this.f134C, this.f135D) + d[1] + 1518500249, 3);
            this.f135D = NTLMEngineImpl.rotintlft(this.f135D + NTLMEngineImpl.m102G(this.f132A, this.f133B, this.f134C) + d[5] + 1518500249, 5);
            this.f134C = NTLMEngineImpl.rotintlft(this.f134C + NTLMEngineImpl.m102G(this.f135D, this.f132A, this.f133B) + d[9] + 1518500249, 9);
            this.f133B = NTLMEngineImpl.rotintlft(this.f133B + NTLMEngineImpl.m102G(this.f134C, this.f135D, this.f132A) + d[13] + 1518500249, 13);
            this.f132A = NTLMEngineImpl.rotintlft(this.f132A + NTLMEngineImpl.m102G(this.f133B, this.f134C, this.f135D) + d[2] + 1518500249, 3);
            this.f135D = NTLMEngineImpl.rotintlft(this.f135D + NTLMEngineImpl.m102G(this.f132A, this.f133B, this.f134C) + d[6] + 1518500249, 5);
            this.f134C = NTLMEngineImpl.rotintlft(this.f134C + NTLMEngineImpl.m102G(this.f135D, this.f132A, this.f133B) + d[10] + 1518500249, 9);
            this.f133B = NTLMEngineImpl.rotintlft(this.f133B + NTLMEngineImpl.m102G(this.f134C, this.f135D, this.f132A) + d[14] + 1518500249, 13);
            this.f132A = NTLMEngineImpl.rotintlft(this.f132A + NTLMEngineImpl.m102G(this.f133B, this.f134C, this.f135D) + d[3] + 1518500249, 3);
            this.f135D = NTLMEngineImpl.rotintlft(this.f135D + NTLMEngineImpl.m102G(this.f132A, this.f133B, this.f134C) + d[7] + 1518500249, 5);
            this.f134C = NTLMEngineImpl.rotintlft(this.f134C + NTLMEngineImpl.m102G(this.f135D, this.f132A, this.f133B) + d[11] + 1518500249, 9);
            this.f133B = NTLMEngineImpl.rotintlft(this.f133B + NTLMEngineImpl.m102G(this.f134C, this.f135D, this.f132A) + d[15] + 1518500249, 13);
        }

        /* access modifiers changed from: protected */
        public void round3(int[] d) {
            this.f132A = NTLMEngineImpl.rotintlft(this.f132A + NTLMEngineImpl.m103H(this.f133B, this.f134C, this.f135D) + d[0] + 1859775393, 3);
            this.f135D = NTLMEngineImpl.rotintlft(this.f135D + NTLMEngineImpl.m103H(this.f132A, this.f133B, this.f134C) + d[8] + 1859775393, 9);
            this.f134C = NTLMEngineImpl.rotintlft(this.f134C + NTLMEngineImpl.m103H(this.f135D, this.f132A, this.f133B) + d[4] + 1859775393, 11);
            this.f133B = NTLMEngineImpl.rotintlft(this.f133B + NTLMEngineImpl.m103H(this.f134C, this.f135D, this.f132A) + d[12] + 1859775393, 15);
            this.f132A = NTLMEngineImpl.rotintlft(this.f132A + NTLMEngineImpl.m103H(this.f133B, this.f134C, this.f135D) + d[2] + 1859775393, 3);
            this.f135D = NTLMEngineImpl.rotintlft(this.f135D + NTLMEngineImpl.m103H(this.f132A, this.f133B, this.f134C) + d[10] + 1859775393, 9);
            this.f134C = NTLMEngineImpl.rotintlft(this.f134C + NTLMEngineImpl.m103H(this.f135D, this.f132A, this.f133B) + d[6] + 1859775393, 11);
            this.f133B = NTLMEngineImpl.rotintlft(this.f133B + NTLMEngineImpl.m103H(this.f134C, this.f135D, this.f132A) + d[14] + 1859775393, 15);
            this.f132A = NTLMEngineImpl.rotintlft(this.f132A + NTLMEngineImpl.m103H(this.f133B, this.f134C, this.f135D) + d[1] + 1859775393, 3);
            this.f135D = NTLMEngineImpl.rotintlft(this.f135D + NTLMEngineImpl.m103H(this.f132A, this.f133B, this.f134C) + d[9] + 1859775393, 9);
            this.f134C = NTLMEngineImpl.rotintlft(this.f134C + NTLMEngineImpl.m103H(this.f135D, this.f132A, this.f133B) + d[5] + 1859775393, 11);
            this.f133B = NTLMEngineImpl.rotintlft(this.f133B + NTLMEngineImpl.m103H(this.f134C, this.f135D, this.f132A) + d[13] + 1859775393, 15);
            this.f132A = NTLMEngineImpl.rotintlft(this.f132A + NTLMEngineImpl.m103H(this.f133B, this.f134C, this.f135D) + d[3] + 1859775393, 3);
            this.f135D = NTLMEngineImpl.rotintlft(this.f135D + NTLMEngineImpl.m103H(this.f132A, this.f133B, this.f134C) + d[11] + 1859775393, 9);
            this.f134C = NTLMEngineImpl.rotintlft(this.f134C + NTLMEngineImpl.m103H(this.f135D, this.f132A, this.f133B) + d[7] + 1859775393, 11);
            this.f133B = NTLMEngineImpl.rotintlft(this.f133B + NTLMEngineImpl.m103H(this.f134C, this.f135D, this.f132A) + d[15] + 1859775393, 15);
        }
    }

    /* renamed from: cz.msebera.android.httpclient.impl.auth.NTLMEngineImpl$NTLMMessage */
    static class NTLMMessage {
        private int currentOutputPosition = 0;
        private byte[] messageContents = null;

        NTLMMessage() {
        }

        NTLMMessage(String messageBody, int expectedType) throws NTLMEngineException {
            this.messageContents = Base64.decode(EncodingUtils.getBytes(messageBody, "ASCII"), 2);
            if (this.messageContents.length >= NTLMEngineImpl.SIGNATURE.length) {
                int i = 0;
                while (i < NTLMEngineImpl.SIGNATURE.length) {
                    if (this.messageContents[i] == NTLMEngineImpl.SIGNATURE[i]) {
                        i++;
                    } else {
                        throw new NTLMEngineException("NTLM message expected - instead got unrecognized bytes");
                    }
                }
                int type = readULong(NTLMEngineImpl.SIGNATURE.length);
                if (type == expectedType) {
                    this.currentOutputPosition = this.messageContents.length;
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("NTLM type ");
                sb.append(Integer.toString(expectedType));
                sb.append(" message expected - instead got type ");
                sb.append(Integer.toString(type));
                throw new NTLMEngineException(sb.toString());
            }
            throw new NTLMEngineException("NTLM message decoding error - packet too short");
        }

        /* access modifiers changed from: protected */
        public int getPreambleLength() {
            return NTLMEngineImpl.SIGNATURE.length + 4;
        }

        /* access modifiers changed from: protected */
        public int getMessageLength() {
            return this.currentOutputPosition;
        }

        /* access modifiers changed from: protected */
        public byte readByte(int position) throws NTLMEngineException {
            byte[] bArr = this.messageContents;
            if (bArr.length >= position + 1) {
                return bArr[position];
            }
            throw new NTLMEngineException("NTLM: Message too short");
        }

        /* access modifiers changed from: protected */
        public void readBytes(byte[] buffer, int position) throws NTLMEngineException {
            byte[] bArr = this.messageContents;
            if (bArr.length >= buffer.length + position) {
                System.arraycopy(bArr, position, buffer, 0, buffer.length);
                return;
            }
            throw new NTLMEngineException("NTLM: Message too short");
        }

        /* access modifiers changed from: protected */
        public int readUShort(int position) throws NTLMEngineException {
            return NTLMEngineImpl.readUShort(this.messageContents, position);
        }

        /* access modifiers changed from: protected */
        public int readULong(int position) throws NTLMEngineException {
            return NTLMEngineImpl.readULong(this.messageContents, position);
        }

        /* access modifiers changed from: protected */
        public byte[] readSecurityBuffer(int position) throws NTLMEngineException {
            return NTLMEngineImpl.readSecurityBuffer(this.messageContents, position);
        }

        /* access modifiers changed from: protected */
        public void prepareResponse(int maxlength, int messageType) {
            this.messageContents = new byte[maxlength];
            this.currentOutputPosition = 0;
            addBytes(NTLMEngineImpl.SIGNATURE);
            addULong(messageType);
        }

        /* access modifiers changed from: protected */
        public void addByte(byte b) {
            byte[] bArr = this.messageContents;
            int i = this.currentOutputPosition;
            bArr[i] = b;
            this.currentOutputPosition = i + 1;
        }

        /* access modifiers changed from: protected */
        public void addBytes(byte[] bytes) {
            if (bytes != null) {
                for (byte b : bytes) {
                    byte[] bArr = this.messageContents;
                    int i = this.currentOutputPosition;
                    bArr[i] = b;
                    this.currentOutputPosition = i + 1;
                }
            }
        }

        /* access modifiers changed from: protected */
        public void addUShort(int value) {
            addByte((byte) (value & 255));
            addByte((byte) ((value >> 8) & 255));
        }

        /* access modifiers changed from: protected */
        public void addULong(int value) {
            addByte((byte) (value & 255));
            addByte((byte) ((value >> 8) & 255));
            addByte((byte) ((value >> 16) & 255));
            addByte((byte) ((value >> 24) & 255));
        }

        /* access modifiers changed from: 0000 */
        public String getResponse() {
            byte[] resp;
            byte[] bArr = this.messageContents;
            int length = bArr.length;
            int i = this.currentOutputPosition;
            if (length > i) {
                byte[] tmp = new byte[i];
                System.arraycopy(bArr, 0, tmp, 0, i);
                resp = tmp;
            } else {
                resp = this.messageContents;
            }
            return EncodingUtils.getAsciiString(Base64.encode(resp, 2));
        }
    }

    /* renamed from: cz.msebera.android.httpclient.impl.auth.NTLMEngineImpl$Type1Message */
    static class Type1Message extends NTLMMessage {
        protected byte[] domainBytes;
        protected byte[] hostBytes;

        Type1Message(String domain, String host) throws NTLMEngineException {
            try {
                String unqualifiedHost = NTLMEngineImpl.convertHost(host);
                String unqualifiedDomain = NTLMEngineImpl.convertDomain(domain);
                String str = "ASCII";
                byte[] bArr = null;
                this.hostBytes = unqualifiedHost != null ? unqualifiedHost.getBytes(str) : null;
                if (unqualifiedDomain != null) {
                    bArr = unqualifiedDomain.toUpperCase(Locale.ENGLISH).getBytes(str);
                }
                this.domainBytes = bArr;
            } catch (UnsupportedEncodingException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unicode unsupported: ");
                sb.append(e.getMessage());
                throw new NTLMEngineException(sb.toString(), e);
            }
        }

        /* access modifiers changed from: 0000 */
        public String getResponse() {
            prepareResponse(40, 1);
            addULong(-1576500735);
            addUShort(0);
            addUShort(0);
            addULong(40);
            addUShort(0);
            addUShort(0);
            addULong(40);
            addUShort(261);
            addULong(2600);
            addUShort(3840);
            return super.getResponse();
        }
    }

    /* renamed from: cz.msebera.android.httpclient.impl.auth.NTLMEngineImpl$Type2Message */
    static class Type2Message extends NTLMMessage {
        protected byte[] challenge = new byte[8];
        protected int flags;
        protected String target;
        protected byte[] targetInfo;

        Type2Message(String message) throws NTLMEngineException {
            super(message, 2);
            readBytes(this.challenge, 24);
            this.flags = readULong(20);
            if ((this.flags & 1) != 0) {
                this.target = null;
                if (getMessageLength() >= 20) {
                    byte[] bytes = readSecurityBuffer(12);
                    if (bytes.length != 0) {
                        try {
                            this.target = new String(bytes, "UnicodeLittleUnmarked");
                        } catch (UnsupportedEncodingException e) {
                            throw new NTLMEngineException(e.getMessage(), e);
                        }
                    }
                }
                this.targetInfo = null;
                if (getMessageLength() >= 48) {
                    byte[] bytes2 = readSecurityBuffer(40);
                    if (bytes2.length != 0) {
                        this.targetInfo = bytes2;
                        return;
                    }
                    return;
                }
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("NTLM type 2 message indicates no support for Unicode. Flags are: ");
            sb.append(Integer.toString(this.flags));
            throw new NTLMEngineException(sb.toString());
        }

        /* access modifiers changed from: 0000 */
        public byte[] getChallenge() {
            return this.challenge;
        }

        /* access modifiers changed from: 0000 */
        public String getTarget() {
            return this.target;
        }

        /* access modifiers changed from: 0000 */
        public byte[] getTargetInfo() {
            return this.targetInfo;
        }

        /* access modifiers changed from: 0000 */
        public int getFlags() {
            return this.flags;
        }
    }

    /* renamed from: cz.msebera.android.httpclient.impl.auth.NTLMEngineImpl$Type3Message */
    static class Type3Message extends NTLMMessage {
        protected byte[] domainBytes;
        protected byte[] hostBytes;
        protected byte[] lmResp;
        protected byte[] ntResp;
        protected byte[] sessionKey;
        protected int type2Flags;
        protected byte[] userBytes;

        Type3Message(String domain, String host, String user, String password, byte[] nonce, int type2Flags2, String target, byte[] targetInformation) throws NTLMEngineException {
            byte[] userSessionKey;
            byte[] bArr;
            byte[] userSessionKey2;
            int i = type2Flags2;
            this.type2Flags = i;
            String unqualifiedHost = NTLMEngineImpl.convertHost(host);
            String unqualifiedDomain = NTLMEngineImpl.convertDomain(domain);
            CipherGen gen = new CipherGen(unqualifiedDomain, user, password, nonce, target, targetInformation);
            if ((8388608 & i) != 0 && targetInformation != null && target != null) {
                try {
                    this.ntResp = gen.getNTLMv2Response();
                    this.lmResp = gen.getLMv2Response();
                    if ((i & 128) != 0) {
                        userSessionKey2 = gen.getLanManagerSessionKey();
                    } else {
                        userSessionKey2 = gen.getNTLMv2UserSessionKey();
                    }
                } catch (NTLMEngineException e) {
                    this.ntResp = new byte[0];
                    this.lmResp = gen.getLMResponse();
                    if ((i & 128) != 0) {
                        userSessionKey = gen.getLanManagerSessionKey();
                    } else {
                        userSessionKey = gen.getLMUserSessionKey();
                    }
                }
            } else if ((524288 & i) != 0) {
                this.ntResp = gen.getNTLM2SessionResponse();
                this.lmResp = gen.getLM2SessionResponse();
                if ((i & 128) != 0) {
                    userSessionKey2 = gen.getLanManagerSessionKey();
                } else {
                    userSessionKey2 = gen.getNTLM2SessionResponseUserSessionKey();
                }
            } else {
                this.ntResp = gen.getNTLMResponse();
                this.lmResp = gen.getLMResponse();
                if ((i & 128) != 0) {
                    userSessionKey2 = gen.getLanManagerSessionKey();
                } else {
                    userSessionKey2 = gen.getNTLMUserSessionKey();
                }
            }
            userSessionKey = userSessionKey2;
            byte[] bArr2 = null;
            if ((i & 16) == 0) {
                this.sessionKey = null;
            } else if ((NTLMEngineImpl.FLAG_REQUEST_EXPLICIT_KEY_EXCH & i) != 0) {
                this.sessionKey = NTLMEngineImpl.RC4(gen.getSecondaryKey(), userSessionKey);
            } else {
                this.sessionKey = userSessionKey;
            }
            String str = "UnicodeLittleUnmarked";
            if (unqualifiedHost != null) {
                try {
                    bArr = unqualifiedHost.getBytes(str);
                } catch (UnsupportedEncodingException e2) {
                    e = e2;
                    String str2 = user;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unicode not supported: ");
                    sb.append(e.getMessage());
                    throw new NTLMEngineException(sb.toString(), e);
                }
            } else {
                bArr = null;
            }
            this.hostBytes = bArr;
            if (unqualifiedDomain != null) {
                bArr2 = unqualifiedDomain.toUpperCase(Locale.ENGLISH).getBytes(str);
            }
            this.domainBytes = bArr2;
            String str3 = user;
            try {
                this.userBytes = user.getBytes(str);
            } catch (UnsupportedEncodingException e3) {
                e = e3;
            }
        }

        /* access modifiers changed from: 0000 */
        public String getResponse() {
            int sessionKeyLen;
            int ntRespLen = this.ntResp.length;
            int lmRespLen = this.lmResp.length;
            byte[] bArr = this.domainBytes;
            int hostLen = 0;
            int domainLen = bArr != null ? bArr.length : 0;
            byte[] bArr2 = this.hostBytes;
            if (bArr2 != null) {
                hostLen = bArr2.length;
            }
            int userLen = this.userBytes.length;
            byte[] bArr3 = this.sessionKey;
            if (bArr3 != null) {
                sessionKeyLen = bArr3.length;
            } else {
                sessionKeyLen = 0;
            }
            int ntRespOffset = lmRespLen + 72;
            int domainOffset = ntRespOffset + ntRespLen;
            int userOffset = domainOffset + domainLen;
            int hostOffset = userOffset + userLen;
            int sessionKeyOffset = hostOffset + hostLen;
            prepareResponse(sessionKeyOffset + sessionKeyLen, 3);
            addUShort(lmRespLen);
            addUShort(lmRespLen);
            addULong(72);
            addUShort(ntRespLen);
            addUShort(ntRespLen);
            addULong(ntRespOffset);
            addUShort(domainLen);
            addUShort(domainLen);
            addULong(domainOffset);
            addUShort(userLen);
            addUShort(userLen);
            addULong(userOffset);
            addUShort(hostLen);
            addUShort(hostLen);
            addULong(hostOffset);
            addUShort(sessionKeyLen);
            addUShort(sessionKeyLen);
            addULong(sessionKeyOffset);
            int i = this.type2Flags;
            int i2 = ntRespLen;
            addULong((i & 512) | (i & 128) | (524288 & i) | NTLMEngineImpl.FLAG_REQUEST_VERSION | (32768 & i) | (i & 32) | (i & 16) | (NTLMEngineImpl.FLAG_REQUEST_128BIT_KEY_EXCH & i) | (Integer.MIN_VALUE & i) | (NTLMEngineImpl.FLAG_REQUEST_EXPLICIT_KEY_EXCH & i) | (8388608 & i) | (i & 1) | (i & 4));
            addUShort(261);
            addULong(2600);
            addUShort(3840);
            addBytes(this.lmResp);
            addBytes(this.ntResp);
            addBytes(this.domainBytes);
            addBytes(this.userBytes);
            addBytes(this.hostBytes);
            byte[] bArr4 = this.sessionKey;
            if (bArr4 != null) {
                addBytes(bArr4);
            }
            return super.getResponse();
        }
    }

    NTLMEngineImpl() {
    }

    static {
        SecureRandom rnd = null;
        try {
            rnd = SecureRandom.getInstance("SHA1PRNG");
        } catch (Exception e) {
        }
        RND_GEN = rnd;
        byte[] bytesWithoutNull = EncodingUtils.getBytes("NTLMSSP", "ASCII");
        SIGNATURE = new byte[(bytesWithoutNull.length + 1)];
        System.arraycopy(bytesWithoutNull, 0, SIGNATURE, 0, bytesWithoutNull.length);
        SIGNATURE[bytesWithoutNull.length] = 0;
    }

    /* access modifiers changed from: 0000 */
    public final String getResponseFor(String message, String username, String password, String host, String domain) throws NTLMEngineException {
        if (message == null || message.trim().equals("")) {
            return getType1Message(host, domain);
        }
        Type2Message t2m = new Type2Message(message);
        return getType3Message(username, password, host, domain, t2m.getChallenge(), t2m.getFlags(), t2m.getTarget(), t2m.getTargetInfo());
    }

    /* access modifiers changed from: 0000 */
    public String getType1Message(String host, String domain) throws NTLMEngineException {
        return new Type1Message(domain, host).getResponse();
    }

    /* access modifiers changed from: 0000 */
    public String getType3Message(String user, String password, String host, String domain, byte[] nonce, int type2Flags, String target, byte[] targetInformation) throws NTLMEngineException {
        Type3Message type3Message = new Type3Message(domain, host, user, password, nonce, type2Flags, target, targetInformation);
        return type3Message.getResponse();
    }

    /* access modifiers changed from: 0000 */
    public String getCredentialCharset() {
        return this.credentialCharset;
    }

    /* access modifiers changed from: 0000 */
    public void setCredentialCharset(String credentialCharset2) {
        this.credentialCharset = credentialCharset2;
    }

    private static String stripDotSuffix(String value) {
        if (value == null) {
            return null;
        }
        int index = value.indexOf(".");
        if (index != -1) {
            return value.substring(0, index);
        }
        return value;
    }

    /* access modifiers changed from: private */
    public static String convertHost(String host) {
        return stripDotSuffix(host);
    }

    /* access modifiers changed from: private */
    public static String convertDomain(String domain) {
        return stripDotSuffix(domain);
    }

    /* access modifiers changed from: private */
    public static int readULong(byte[] src, int index) throws NTLMEngineException {
        if (src.length >= index + 4) {
            return (src[index] & UByte.MAX_VALUE) | ((src[index + 1] & UByte.MAX_VALUE) << 8) | ((src[index + 2] & UByte.MAX_VALUE) << 16) | ((src[index + 3] & UByte.MAX_VALUE) << 24);
        }
        throw new NTLMEngineException("NTLM authentication - buffer too small for DWORD");
    }

    /* access modifiers changed from: private */
    public static int readUShort(byte[] src, int index) throws NTLMEngineException {
        if (src.length >= index + 2) {
            return (src[index] & UByte.MAX_VALUE) | ((src[index + 1] & UByte.MAX_VALUE) << 8);
        }
        throw new NTLMEngineException("NTLM authentication - buffer too small for WORD");
    }

    /* access modifiers changed from: private */
    public static byte[] readSecurityBuffer(byte[] src, int index) throws NTLMEngineException {
        int length = readUShort(src, index);
        int offset = readULong(src, index + 4);
        if (src.length >= offset + length) {
            byte[] buffer = new byte[length];
            System.arraycopy(src, offset, buffer, 0, length);
            return buffer;
        }
        throw new NTLMEngineException("NTLM authentication - buffer too small for data item");
    }

    /* access modifiers changed from: private */
    public static byte[] makeRandomChallenge() throws NTLMEngineException {
        SecureRandom secureRandom = RND_GEN;
        if (secureRandom != null) {
            byte[] rval = new byte[8];
            synchronized (secureRandom) {
                RND_GEN.nextBytes(rval);
            }
            return rval;
        }
        throw new NTLMEngineException("Random generator not available");
    }

    /* access modifiers changed from: private */
    public static byte[] makeSecondaryKey() throws NTLMEngineException {
        SecureRandom secureRandom = RND_GEN;
        if (secureRandom != null) {
            byte[] rval = new byte[16];
            synchronized (secureRandom) {
                RND_GEN.nextBytes(rval);
            }
            return rval;
        }
        throw new NTLMEngineException("Random generator not available");
    }

    static byte[] hmacMD5(byte[] value, byte[] key) throws NTLMEngineException {
        HMACMD5 hmacMD5 = new HMACMD5(key);
        hmacMD5.update(value);
        return hmacMD5.getOutput();
    }

    static byte[] RC4(byte[] value, byte[] key) throws NTLMEngineException {
        String str = "RC4";
        try {
            Cipher rc4 = Cipher.getInstance(str);
            rc4.init(1, new SecretKeySpec(key, str));
            return rc4.doFinal(value);
        } catch (Exception e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    static byte[] ntlm2SessionResponse(byte[] ntlmHash, byte[] challenge, byte[] clientChallenge) throws NTLMEngineException {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(challenge);
            md5.update(clientChallenge);
            byte[] sessionHash = new byte[8];
            System.arraycopy(md5.digest(), 0, sessionHash, 0, 8);
            return lmResponse(ntlmHash, sessionHash);
        } catch (Exception e) {
            if (e instanceof NTLMEngineException) {
                throw ((NTLMEngineException) e);
            }
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] lmHash(String password) throws NTLMEngineException {
        String str = "US-ASCII";
        try {
            byte[] oemPassword = password.toUpperCase(Locale.ENGLISH).getBytes(str);
            byte[] keyBytes = new byte[14];
            System.arraycopy(oemPassword, 0, keyBytes, 0, Math.min(oemPassword.length, 14));
            Key lowKey = createDESKey(keyBytes, 0);
            Key highKey = createDESKey(keyBytes, 7);
            byte[] magicConstant = "KGS!@#$%".getBytes(str);
            Cipher des = Cipher.getInstance("DES/ECB/NoPadding");
            des.init(1, lowKey);
            byte[] lowHash = des.doFinal(magicConstant);
            des.init(1, highKey);
            byte[] highHash = des.doFinal(magicConstant);
            byte[] lmHash = new byte[16];
            System.arraycopy(lowHash, 0, lmHash, 0, 8);
            System.arraycopy(highHash, 0, lmHash, 8, 8);
            return lmHash;
        } catch (Exception e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] ntlmHash(String password) throws NTLMEngineException {
        try {
            byte[] unicodePassword = password.getBytes("UnicodeLittleUnmarked");
            MD4 md4 = new MD4();
            md4.update(unicodePassword);
            return md4.getOutput();
        } catch (UnsupportedEncodingException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unicode not supported: ");
            sb.append(e.getMessage());
            throw new NTLMEngineException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] lmv2Hash(String domain, String user, byte[] ntlmHash) throws NTLMEngineException {
        String str = "UnicodeLittleUnmarked";
        try {
            HMACMD5 hmacMD5 = new HMACMD5(ntlmHash);
            hmacMD5.update(user.toUpperCase(Locale.ENGLISH).getBytes(str));
            if (domain != null) {
                hmacMD5.update(domain.toUpperCase(Locale.ENGLISH).getBytes(str));
            }
            return hmacMD5.getOutput();
        } catch (UnsupportedEncodingException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unicode not supported! ");
            sb.append(e.getMessage());
            throw new NTLMEngineException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] ntlmv2Hash(String domain, String user, byte[] ntlmHash) throws NTLMEngineException {
        String str = "UnicodeLittleUnmarked";
        try {
            HMACMD5 hmacMD5 = new HMACMD5(ntlmHash);
            hmacMD5.update(user.toUpperCase(Locale.ENGLISH).getBytes(str));
            if (domain != null) {
                hmacMD5.update(domain.getBytes(str));
            }
            return hmacMD5.getOutput();
        } catch (UnsupportedEncodingException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unicode not supported! ");
            sb.append(e.getMessage());
            throw new NTLMEngineException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] lmResponse(byte[] hash, byte[] challenge) throws NTLMEngineException {
        try {
            byte[] keyBytes = new byte[21];
            System.arraycopy(hash, 0, keyBytes, 0, 16);
            Key lowKey = createDESKey(keyBytes, 0);
            Key middleKey = createDESKey(keyBytes, 7);
            Key highKey = createDESKey(keyBytes, 14);
            Cipher des = Cipher.getInstance("DES/ECB/NoPadding");
            des.init(1, lowKey);
            byte[] lowResponse = des.doFinal(challenge);
            des.init(1, middleKey);
            byte[] middleResponse = des.doFinal(challenge);
            des.init(1, highKey);
            byte[] highResponse = des.doFinal(challenge);
            byte[] lmResponse = new byte[24];
            System.arraycopy(lowResponse, 0, lmResponse, 0, 8);
            System.arraycopy(middleResponse, 0, lmResponse, 8, 8);
            System.arraycopy(highResponse, 0, lmResponse, 16, 8);
            return lmResponse;
        } catch (Exception e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] lmv2Response(byte[] hash, byte[] challenge, byte[] clientData) throws NTLMEngineException {
        HMACMD5 hmacMD5 = new HMACMD5(hash);
        hmacMD5.update(challenge);
        hmacMD5.update(clientData);
        byte[] mac = hmacMD5.getOutput();
        byte[] lmv2Response = new byte[(mac.length + clientData.length)];
        System.arraycopy(mac, 0, lmv2Response, 0, mac.length);
        System.arraycopy(clientData, 0, lmv2Response, mac.length, clientData.length);
        return lmv2Response;
    }

    /* access modifiers changed from: private */
    public static byte[] createBlob(byte[] clientChallenge, byte[] targetInformation, byte[] timestamp) {
        byte[] blobSignature = {1, 1, 0, 0};
        byte[] reserved = {0, 0, 0, 0};
        byte[] unknown1 = {0, 0, 0, 0};
        byte[] unknown2 = {0, 0, 0, 0};
        byte[] blob = new byte[(blobSignature.length + reserved.length + timestamp.length + 8 + unknown1.length + targetInformation.length + unknown2.length)];
        System.arraycopy(blobSignature, 0, blob, 0, blobSignature.length);
        int offset = 0 + blobSignature.length;
        System.arraycopy(reserved, 0, blob, offset, reserved.length);
        int offset2 = offset + reserved.length;
        System.arraycopy(timestamp, 0, blob, offset2, timestamp.length);
        int offset3 = offset2 + timestamp.length;
        System.arraycopy(clientChallenge, 0, blob, offset3, 8);
        int offset4 = offset3 + 8;
        System.arraycopy(unknown1, 0, blob, offset4, unknown1.length);
        int offset5 = offset4 + unknown1.length;
        System.arraycopy(targetInformation, 0, blob, offset5, targetInformation.length);
        int offset6 = offset5 + targetInformation.length;
        System.arraycopy(unknown2, 0, blob, offset6, unknown2.length);
        int offset7 = offset6 + unknown2.length;
        return blob;
    }

    /* access modifiers changed from: private */
    public static Key createDESKey(byte[] bytes, int offset) {
        byte[] keyBytes = new byte[7];
        System.arraycopy(bytes, offset, keyBytes, 0, 7);
        byte[] material = {keyBytes[0], (byte) ((keyBytes[0] << 7) | ((keyBytes[1] & UByte.MAX_VALUE) >>> 1)), (byte) ((keyBytes[1] << 6) | ((keyBytes[2] & UByte.MAX_VALUE) >>> 2)), (byte) ((keyBytes[2] << 5) | ((keyBytes[3] & UByte.MAX_VALUE) >>> 3)), (byte) ((keyBytes[3] << 4) | ((keyBytes[4] & UByte.MAX_VALUE) >>> 4)), (byte) ((keyBytes[4] << 3) | ((keyBytes[5] & UByte.MAX_VALUE) >>> 5)), (byte) ((keyBytes[5] << 2) | ((keyBytes[6] & UByte.MAX_VALUE) >>> 6)), (byte) (keyBytes[6] << 1)};
        oddParity(material);
        return new SecretKeySpec(material, "DES");
    }

    private static void oddParity(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            if (((((((((b >>> 7) ^ (b >>> 6)) ^ (b >>> 5)) ^ (b >>> 4)) ^ (b >>> 3)) ^ (b >>> 2)) ^ (b >>> 1)) & 1) == 0) {
                bytes[i] = (byte) (1 | bytes[i]);
            } else {
                bytes[i] = (byte) (bytes[i] & -2);
            }
        }
    }

    static void writeULong(byte[] buffer, int value, int offset) {
        buffer[offset] = (byte) (value & 255);
        buffer[offset + 1] = (byte) ((value >> 8) & 255);
        buffer[offset + 2] = (byte) ((value >> 16) & 255);
        buffer[offset + 3] = (byte) ((value >> 24) & 255);
    }

    /* renamed from: F */
    static int m101F(int x, int y, int z) {
        return (x & y) | ((~x) & z);
    }

    /* renamed from: G */
    static int m102G(int x, int y, int z) {
        return (x & y) | (x & z) | (y & z);
    }

    /* renamed from: H */
    static int m103H(int x, int y, int z) {
        return (x ^ y) ^ z;
    }

    static int rotintlft(int val, int numbits) {
        return (val << numbits) | (val >>> (32 - numbits));
    }

    public String generateType1Msg(String domain, String workstation) throws NTLMEngineException {
        return getType1Message(workstation, domain);
    }

    public String generateType3Msg(String username, String password, String domain, String workstation, String challenge) throws NTLMEngineException {
        Type2Message t2m = new Type2Message(challenge);
        return getType3Message(username, password, workstation, domain, t2m.getChallenge(), t2m.getFlags(), t2m.getTarget(), t2m.getTargetInfo());
    }
}
