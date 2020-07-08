package com.google.firebase.installations.remote;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.JsonReader;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.firebase.FirebaseException;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.heartbeatinfo.HeartBeatInfo.HeartBeat;
import com.google.firebase.installations.FirebaseInstallationsException;
import com.google.firebase.installations.FirebaseInstallationsException.Status;
import com.google.firebase.installations.remote.InstallationResponse.ResponseCode;
import com.google.firebase.installations.remote.TokenResult.Builder;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;
import p008cz.msebera.android.httpclient.client.methods.HttpPost;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
public class FirebaseInstallationServiceClient {
    private static final String ACCEPT_HEADER_KEY = "Accept";
    private static final String API_KEY_HEADER = "x-goog-api-key";
    private static final String CONTENT_ENCODING_HEADER_KEY = "Content-Encoding";
    private static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
    private static final String CREATE_REQUEST_RESOURCE_NAME_FORMAT = "projects/%s/installations";
    private static final String DELETE_REQUEST_RESOURCE_NAME_FORMAT = "projects/%s/installations/%s";
    private static final Pattern EXPIRATION_TIMESTAMP_PATTERN = Pattern.compile("[0-9]+s");
    private static final String FIREBASE_INSTALLATIONS_API_DOMAIN = "firebaseinstallations.googleapis.com";
    private static final String FIREBASE_INSTALLATIONS_API_VERSION = "v1";
    private static final String FIREBASE_INSTALLATIONS_ID_HEARTBEAT_TAG = "fire-installations-id";
    private static final String FIREBASE_INSTALLATION_AUTH_VERSION = "FIS_v2";
    private static final String GENERATE_AUTH_TOKEN_REQUEST_RESOURCE_NAME_FORMAT = "projects/%s/installations/%s/authTokens:generate";
    private static final String GZIP_CONTENT_ENCODING = "gzip";
    private static final String HEART_BEAT_HEADER = "x-firebase-client-log-type";
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final int MAX_RETRIES = 1;
    private static final int NETWORK_TIMEOUT_MILLIS = 10000;
    static final String PARSING_EXPIRATION_TIME_ERROR_MESSAGE = "Invalid Expiration Timestamp.";
    private static final String SDK_VERSION_PREFIX = "a:";
    private static final String USER_AGENT_HEADER = "x-firebase-client";
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final String X_ANDROID_CERT_HEADER_KEY = "X-Android-Cert";
    private static final String X_ANDROID_IID_MIGRATION_KEY = "x-goog-fis-android-iid-migration-auth";
    private static final String X_ANDROID_PACKAGE_HEADER_KEY = "X-Android-Package";
    private final Context context;
    private final HeartBeatInfo heartbeatInfo;
    private final UserAgentPublisher userAgentPublisher;

    public FirebaseInstallationServiceClient(Context context2, UserAgentPublisher publisher, HeartBeatInfo heartbeatInfo2) {
        this.context = context2;
        this.userAgentPublisher = publisher;
        this.heartbeatInfo = heartbeatInfo2;
    }

    public InstallationResponse createFirebaseInstallation(String apiKey, String fid, String projectID, String appId, String iidToken) throws IOException {
        int retryCount = 0;
        URL url = new URL(String.format("https://%s/%s/%s", new Object[]{FIREBASE_INSTALLATIONS_API_DOMAIN, FIREBASE_INSTALLATIONS_API_VERSION, String.format(CREATE_REQUEST_RESOURCE_NAME_FORMAT, new Object[]{projectID})}));
        while (retryCount <= 1) {
            HttpURLConnection httpURLConnection = openHttpURLConnection(url, apiKey);
            try {
                httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
                httpURLConnection.setDoOutput(true);
                if (iidToken != null) {
                    httpURLConnection.addRequestProperty(X_ANDROID_IID_MIGRATION_KEY, iidToken);
                }
                writeFIDCreateRequestBodyToOutputStream(httpURLConnection, fid, appId);
                int httpResponseCode = httpURLConnection.getResponseCode();
                if (httpResponseCode == 200) {
                    InstallationResponse readCreateResponse = readCreateResponse(httpURLConnection);
                    httpURLConnection.disconnect();
                    return readCreateResponse;
                } else if (httpResponseCode != 429 && (httpResponseCode < 500 || httpResponseCode >= 600)) {
                    return InstallationResponse.builder().setResponseCode(ResponseCode.BAD_CONFIG).build();
                } else {
                    retryCount++;
                    httpURLConnection.disconnect();
                }
            } finally {
                httpURLConnection.disconnect();
            }
        }
        throw new IOException();
    }

    private void writeFIDCreateRequestBodyToOutputStream(HttpURLConnection httpURLConnection, String fid, String appId) throws IOException {
        OutputStream outputStream = httpURLConnection.getOutputStream();
        if (outputStream != null) {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            try {
                gzipOutputStream.write(buildCreateFirebaseInstallationRequestBody(fid, appId).toString().getBytes("UTF-8"));
                gzipOutputStream.close();
            } catch (JSONException e) {
                throw new IllegalStateException(e);
            } catch (Throwable th) {
                gzipOutputStream.close();
                throw th;
            }
        } else {
            throw new IOException("Cannot send CreateInstallation request to FIS. No OutputStream available.");
        }
    }

    private static JSONObject buildCreateFirebaseInstallationRequestBody(String fid, String appId) throws JSONException {
        JSONObject firebaseInstallationData = new JSONObject();
        firebaseInstallationData.put("fid", fid);
        firebaseInstallationData.put("appId", appId);
        firebaseInstallationData.put("authVersion", FIREBASE_INSTALLATION_AUTH_VERSION);
        firebaseInstallationData.put("sdkVersion", "a:16.2.1");
        return firebaseInstallationData;
    }

    private void writeGenerateAuthTokenRequestBodyToOutputStream(HttpURLConnection httpURLConnection) throws IOException {
        OutputStream outputStream = httpURLConnection.getOutputStream();
        if (outputStream != null) {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            try {
                gzipOutputStream.write(buildGenerateAuthTokenRequestBody().toString().getBytes("UTF-8"));
                gzipOutputStream.close();
            } catch (JSONException e) {
                throw new IllegalStateException(e);
            } catch (Throwable th) {
                gzipOutputStream.close();
                throw th;
            }
        } else {
            throw new IOException("Cannot send GenerateAuthToken request to FIS. No OutputStream available.");
        }
    }

    private static JSONObject buildGenerateAuthTokenRequestBody() throws JSONException {
        JSONObject sdkVersionData = new JSONObject();
        sdkVersionData.put("sdkVersion", "a:16.2.1");
        JSONObject firebaseInstallationData = new JSONObject();
        firebaseInstallationData.put("installation", sdkVersionData);
        return firebaseInstallationData;
    }

    public void deleteFirebaseInstallation(String apiKey, String fid, String projectID, String refreshToken) throws FirebaseException, IOException {
        URL url = new URL(String.format("https://%s/%s/%s", new Object[]{FIREBASE_INSTALLATIONS_API_DOMAIN, FIREBASE_INSTALLATIONS_API_VERSION, String.format(DELETE_REQUEST_RESOURCE_NAME_FORMAT, new Object[]{projectID, fid})}));
        int retryCount = 0;
        while (retryCount <= 1) {
            HttpURLConnection httpURLConnection = openHttpURLConnection(url, apiKey);
            httpURLConnection.setRequestMethod("DELETE");
            StringBuilder sb = new StringBuilder();
            sb.append("FIS_v2 ");
            sb.append(refreshToken);
            httpURLConnection.addRequestProperty("Authorization", sb.toString());
            int httpResponseCode = httpURLConnection.getResponseCode();
            httpURLConnection.disconnect();
            if (httpResponseCode != 200 && httpResponseCode != 401 && httpResponseCode != 404) {
                if (httpResponseCode == 429 || (httpResponseCode >= 500 && httpResponseCode < 600)) {
                    retryCount++;
                } else {
                    throw new FirebaseInstallationsException("bad config while trying to delete FID", Status.BAD_CONFIG);
                }
            } else {
                return;
            }
        }
        throw new IOException();
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:19:0x0073=Splitter:B:19:0x0073, B:24:0x008b=Splitter:B:24:0x008b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.installations.remote.TokenResult generateAuthToken(java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) throws java.io.IOException {
        /*
            r8 = this;
            r0 = 2
            java.lang.Object[] r1 = new java.lang.Object[r0]
            r2 = 0
            r1[r2] = r11
            r3 = 1
            r1[r3] = r10
            java.lang.String r4 = "projects/%s/installations/%s/authTokens:generate"
            java.lang.String r1 = java.lang.String.format(r4, r1)
            r4 = 0
            java.net.URL r5 = new java.net.URL
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.String r7 = "firebaseinstallations.googleapis.com"
            r6[r2] = r7
            java.lang.String r2 = "v1"
            r6[r3] = r2
            r6[r0] = r1
            java.lang.String r0 = "https://%s/%s/%s"
            java.lang.String r0 = java.lang.String.format(r0, r6)
            r5.<init>(r0)
            r0 = r5
        L_0x0029:
            if (r4 > r3) goto L_0x00a2
            java.net.HttpURLConnection r2 = r8.openHttpURLConnection(r0, r9)
            java.lang.String r5 = "POST"
            r2.setRequestMethod(r5)     // Catch:{ all -> 0x009d }
            java.lang.String r5 = "Authorization"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x009d }
            r6.<init>()     // Catch:{ all -> 0x009d }
            java.lang.String r7 = "FIS_v2 "
            r6.append(r7)     // Catch:{ all -> 0x009d }
            r6.append(r12)     // Catch:{ all -> 0x009d }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x009d }
            r2.addRequestProperty(r5, r6)     // Catch:{ all -> 0x009d }
            r8.writeGenerateAuthTokenRequestBodyToOutputStream(r2)     // Catch:{ all -> 0x009d }
            int r5 = r2.getResponseCode()     // Catch:{ all -> 0x009d }
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 != r6) goto L_0x005d
            com.google.firebase.installations.remote.TokenResult r3 = r8.readGenerateAuthTokenResponse(r2)     // Catch:{ all -> 0x009d }
            r2.disconnect()
            return r3
        L_0x005d:
            r6 = 401(0x191, float:5.62E-43)
            if (r5 == r6) goto L_0x008b
            r6 = 404(0x194, float:5.66E-43)
            if (r5 != r6) goto L_0x0066
            goto L_0x008b
        L_0x0066:
            r6 = 429(0x1ad, float:6.01E-43)
            if (r5 == r6) goto L_0x0085
            r6 = 500(0x1f4, float:7.0E-43)
            if (r5 < r6) goto L_0x0073
            r6 = 600(0x258, float:8.41E-43)
            if (r5 >= r6) goto L_0x0073
            goto L_0x0085
        L_0x0073:
            com.google.firebase.installations.remote.TokenResult$Builder r3 = com.google.firebase.installations.remote.TokenResult.builder()     // Catch:{ all -> 0x009d }
            com.google.firebase.installations.remote.TokenResult$ResponseCode r6 = com.google.firebase.installations.remote.TokenResult.ResponseCode.BAD_CONFIG     // Catch:{ all -> 0x009d }
            com.google.firebase.installations.remote.TokenResult$Builder r3 = r3.setResponseCode(r6)     // Catch:{ all -> 0x009d }
            com.google.firebase.installations.remote.TokenResult r3 = r3.build()     // Catch:{ all -> 0x009d }
            r2.disconnect()
            return r3
        L_0x0085:
            int r4 = r4 + 1
            r2.disconnect()
            goto L_0x0029
        L_0x008b:
            com.google.firebase.installations.remote.TokenResult$Builder r3 = com.google.firebase.installations.remote.TokenResult.builder()     // Catch:{ all -> 0x009d }
            com.google.firebase.installations.remote.TokenResult$ResponseCode r6 = com.google.firebase.installations.remote.TokenResult.ResponseCode.AUTH_ERROR     // Catch:{ all -> 0x009d }
            com.google.firebase.installations.remote.TokenResult$Builder r3 = r3.setResponseCode(r6)     // Catch:{ all -> 0x009d }
            com.google.firebase.installations.remote.TokenResult r3 = r3.build()     // Catch:{ all -> 0x009d }
            r2.disconnect()
            return r3
        L_0x009d:
            r3 = move-exception
            r2.disconnect()
            throw r3
        L_0x00a2:
            java.io.IOException r2 = new java.io.IOException
            r2.<init>()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.installations.remote.FirebaseInstallationServiceClient.generateAuthToken(java.lang.String, java.lang.String, java.lang.String, java.lang.String):com.google.firebase.installations.remote.TokenResult");
    }

    private HttpURLConnection openHttpURLConnection(URL url, String apiKey) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        String str = "application/json";
        httpURLConnection.addRequestProperty("Content-Type", str);
        httpURLConnection.addRequestProperty("Accept", str);
        httpURLConnection.addRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.addRequestProperty(X_ANDROID_PACKAGE_HEADER_KEY, this.context.getPackageName());
        HeartBeatInfo heartBeatInfo = this.heartbeatInfo;
        if (!(heartBeatInfo == null || this.userAgentPublisher == null)) {
            HeartBeat heartbeat = heartBeatInfo.getHeartBeatCode(FIREBASE_INSTALLATIONS_ID_HEARTBEAT_TAG);
            if (heartbeat != HeartBeat.NONE) {
                httpURLConnection.addRequestProperty(USER_AGENT_HEADER, this.userAgentPublisher.getUserAgent());
                httpURLConnection.addRequestProperty(HEART_BEAT_HEADER, Integer.toString(heartbeat.getCode()));
            }
        }
        httpURLConnection.addRequestProperty(X_ANDROID_CERT_HEADER_KEY, getFingerprintHashForPackage());
        httpURLConnection.addRequestProperty(API_KEY_HEADER, apiKey);
        return httpURLConnection;
    }

    private InstallationResponse readCreateResponse(HttpURLConnection conn) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(conn.getInputStream(), UTF_8));
        Builder tokenResult = TokenResult.builder();
        InstallationResponse.Builder builder = InstallationResponse.builder();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                builder.setUri(reader.nextString());
            } else if (name.equals("fid")) {
                builder.setFid(reader.nextString());
            } else if (name.equals("refreshToken")) {
                builder.setRefreshToken(reader.nextString());
            } else if (name.equals("authToken")) {
                reader.beginObject();
                while (reader.hasNext()) {
                    String key = reader.nextName();
                    if (key.equals("token")) {
                        tokenResult.setToken(reader.nextString());
                    } else if (key.equals("expiresIn")) {
                        tokenResult.setTokenExpirationTimestamp(parseTokenExpirationTimestamp(reader.nextString()));
                    } else {
                        reader.skipValue();
                    }
                }
                builder.setAuthToken(tokenResult.build());
                reader.endObject();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        reader.close();
        return builder.setResponseCode(ResponseCode.OK).build();
    }

    private TokenResult readGenerateAuthTokenResponse(HttpURLConnection conn) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(conn.getInputStream(), UTF_8));
        Builder builder = TokenResult.builder();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("token")) {
                builder.setToken(reader.nextString());
            } else if (name.equals("expiresIn")) {
                builder.setTokenExpirationTimestamp(parseTokenExpirationTimestamp(reader.nextString()));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        reader.close();
        return builder.setResponseCode(TokenResult.ResponseCode.OK).build();
    }

    private String getFingerprintHashForPackage() {
        String str = "ContentValues";
        try {
            byte[] hash = AndroidUtilsLight.getPackageCertificateHashBytes(this.context, this.context.getPackageName());
            if (hash != null) {
                return Hex.bytesToStringUppercase(hash, false);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Could not get fingerprint hash for package: ");
            sb.append(this.context.getPackageName());
            Log.e(str, sb.toString());
            return null;
        } catch (NameNotFoundException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("No such package: ");
            sb2.append(this.context.getPackageName());
            Log.e(str, sb2.toString(), e);
            return null;
        }
    }

    static long parseTokenExpirationTimestamp(String expiresIn) {
        Preconditions.checkArgument(EXPIRATION_TIMESTAMP_PATTERN.matcher(expiresIn).matches(), PARSING_EXPIRATION_TIME_ERROR_MESSAGE);
        if (expiresIn == null || expiresIn.length() == 0) {
            return 0;
        }
        return Long.parseLong(expiresIn.substring(0, expiresIn.length() - 1));
    }
}
