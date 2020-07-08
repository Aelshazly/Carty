package com.google.android.datatransport.cct;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.cct.p005a.zzaa;
import com.google.android.datatransport.cct.p005a.zzo;
import com.google.android.datatransport.cct.p005a.zzq;
import com.google.android.datatransport.cct.p005a.zzs;
import com.google.android.datatransport.cct.p005a.zzt;
import com.google.android.datatransport.cct.p005a.zzv;
import com.google.android.datatransport.cct.p005a.zzx;
import com.google.android.datatransport.cct.p005a.zzy;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.EventInternal.Builder;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.retries.Retries;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.loopj.android.http.AsyncHttpClient;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import p008cz.msebera.android.httpclient.HttpHeaders;
import p008cz.msebera.android.httpclient.HttpStatus;
import p008cz.msebera.android.httpclient.client.methods.HttpPost;

/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
final class zzc implements TransportBackend {
    private final DataEncoder zza = zzs.zza();
    private final ConnectivityManager zzb;
    final URL zzc;
    private final Clock zzd;
    private final Clock zze;
    private final int zzf;

    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    static final class zza {
        final URL zza;
        final zzo zzb;
        final String zzc;

        zza(URL url, zzo zzo, String str) {
            this.zza = url;
            this.zzb = zzo;
            this.zzc = str;
        }

        /* access modifiers changed from: 0000 */
        public zza zza(URL url) {
            return new zza(url, this.zzb, this.zzc);
        }
    }

    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    static final class zzb {
        final int zza;
        final URL zzb;
        final long zzc;

        zzb(int i, URL url, long j) {
            this.zza = i;
            this.zzb = url;
            this.zzc = j;
        }
    }

    zzc(Context context, Clock clock, Clock clock2) {
        this.zzb = (ConnectivityManager) context.getSystemService("connectivity");
        this.zzc = zza(CCTDestination.zza);
        this.zzd = clock2;
        this.zze = clock;
        this.zzf = 40000;
    }

    private static URL zza(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid url: ");
            sb.append(str);
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    public EventInternal decorate(EventInternal eventInternal) {
        int i;
        int i2;
        NetworkInfo activeNetworkInfo = this.zzb.getActiveNetworkInfo();
        String str = "model";
        String str2 = "hardware";
        String str3 = "device";
        String str4 = "product";
        String str5 = "os-uild";
        String str6 = "manufacturer";
        String str7 = "fingerprint";
        Builder addMetadata = eventInternal.toBuilder().addMetadata("sdk-version", VERSION.SDK_INT).addMetadata(str, Build.MODEL).addMetadata(str2, Build.HARDWARE).addMetadata(str3, Build.DEVICE).addMetadata(str4, Build.PRODUCT).addMetadata(str5, Build.ID).addMetadata(str6, Build.MANUFACTURER).addMetadata(str7, Build.FINGERPRINT);
        Calendar.getInstance();
        Builder addMetadata2 = addMetadata.addMetadata("tz-offset", (long) (TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis()) / 1000));
        if (activeNetworkInfo == null) {
            i = com.google.android.datatransport.cct.p005a.zzy.zzc.NONE.zza();
        } else {
            i = activeNetworkInfo.getType();
        }
        Builder addMetadata3 = addMetadata2.addMetadata("net-type", i);
        if (activeNetworkInfo == null) {
            i2 = com.google.android.datatransport.cct.p005a.zzy.zzb.UNKNOWN_MOBILE_SUBTYPE.zza();
        } else {
            i2 = activeNetworkInfo.getSubtype();
            if (i2 == -1) {
                i2 = com.google.android.datatransport.cct.p005a.zzy.zzb.COMBINED.zza();
            } else if (com.google.android.datatransport.cct.p005a.zzy.zzb.zza(i2) == null) {
                i2 = 0;
            }
        }
        return addMetadata3.addMetadata("mobile-subtype", i2).build();
    }

    public BackendResponse send(BackendRequest backendRequest) {
        String str;
        com.google.android.datatransport.cct.p005a.zzt.zza zza2;
        HashMap hashMap = new HashMap();
        for (EventInternal eventInternal : backendRequest.getEvents()) {
            String transportName = eventInternal.getTransportName();
            if (!hashMap.containsKey(transportName)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(eventInternal);
                hashMap.put(transportName, arrayList);
            } else {
                ((List) hashMap.get(transportName)).add(eventInternal);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = hashMap.entrySet().iterator();
        while (true) {
            str = "CctTransportBackend";
            if (!it.hasNext()) {
                break;
            }
            Entry entry = (Entry) it.next();
            EventInternal eventInternal2 = (EventInternal) ((List) entry.getValue()).get(0);
            com.google.android.datatransport.cct.p005a.zzv.zza zza3 = zzv.zza().zza(zzaa.DEFAULT).zza(this.zze.getTime()).zzb(this.zzd.getTime()).zza(zzq.zza().zza(com.google.android.datatransport.cct.p005a.zzq.zzb.ANDROID).zza(com.google.android.datatransport.cct.p005a.zza.zza().zza(eventInternal2.getInteger("sdk-version")).zze(eventInternal2.get("model")).zzc(eventInternal2.get("hardware")).zza(eventInternal2.get("device")).zzg(eventInternal2.get("product")).zzf(eventInternal2.get("os-uild")).zzd(eventInternal2.get("manufacturer")).zzb(eventInternal2.get("fingerprint")).zza()).zza());
            try {
                zza3.zzb(Integer.valueOf((String) entry.getKey()).intValue());
            } catch (NumberFormatException e) {
                zza3.zzb((String) entry.getKey());
            }
            ArrayList arrayList3 = new ArrayList();
            for (EventInternal eventInternal3 : (List) entry.getValue()) {
                EncodedPayload encodedPayload = eventInternal3.getEncodedPayload();
                Encoding encoding = encodedPayload.getEncoding();
                if (encoding.equals(Encoding.m11of("proto"))) {
                    zza2 = zzt.zza(encodedPayload.getBytes());
                } else if (encoding.equals(Encoding.m11of("json"))) {
                    zza2 = zzt.zza(new String(encodedPayload.getBytes(), Charset.forName("UTF-8")));
                } else {
                    Logging.m19w(str, "Received event of unsupported encoding %s. Skipping...", encoding);
                }
                zza2.zza(eventInternal3.getEventMillis()).zzb(eventInternal3.getUptimeMillis()).zzc(eventInternal3.getLong("tz-offset")).zza(zzy.zza().zza(com.google.android.datatransport.cct.p005a.zzy.zzc.zza(eventInternal3.getInteger("net-type"))).zza(com.google.android.datatransport.cct.p005a.zzy.zzb.zza(eventInternal3.getInteger("mobile-subtype"))).zza());
                if (eventInternal3.getCode() != null) {
                    zza2.zza(eventInternal3.getCode().intValue());
                }
                arrayList3.add(zza2.zza());
            }
            zza3.zza((List<zzt>) arrayList3);
            arrayList2.add(zza3.zza());
        }
        zzo zza4 = zzo.zza(arrayList2);
        String str2 = null;
        URL url = this.zzc;
        if (backendRequest.getExtras() != null) {
            try {
                CCTDestination fromByteArray = CCTDestination.fromByteArray(backendRequest.getExtras());
                if (fromByteArray.getAPIKey() != null) {
                    str2 = fromByteArray.getAPIKey();
                }
                if (fromByteArray.getEndPoint() != null) {
                    url = zza(fromByteArray.getEndPoint());
                }
            } catch (IllegalArgumentException e2) {
                return BackendResponse.fatalError();
            }
        }
        try {
            zzb zzb2 = (zzb) Retries.retry(5, new zza(url, zza4, str2), zza.zza(this), zzb.zza());
            if (zzb2.zza == 200) {
                return BackendResponse.m12ok(zzb2.zzc);
            }
            int i = zzb2.zza;
            if (i < 500) {
                if (i != 404) {
                    return BackendResponse.fatalError();
                }
            }
            return BackendResponse.transientError();
        } catch (IOException e3) {
            Logging.m17e(str, "Could not make request to the backend", e3);
            return BackendResponse.transientError();
        }
    }

    /* access modifiers changed from: private */
    public zzb zza(zza zza2) throws IOException {
        GZIPOutputStream gZIPOutputStream;
        InputStream inputStream;
        String str = "CctTransportBackend";
        Logging.m14d(str, "Making request to: %s", (Object) zza2.zza);
        HttpURLConnection httpURLConnection = (HttpURLConnection) zza2.zza.openConnection();
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(this.zzf);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
        httpURLConnection.setRequestProperty("User-Agent", String.format("datatransport/%s android/", new Object[]{"2.2.0"}));
        String str2 = AsyncHttpClient.ENCODING_GZIP;
        String str3 = "Content-Encoding";
        httpURLConnection.setRequestProperty(str3, str2);
        String str4 = "Content-Type";
        httpURLConnection.setRequestProperty(str4, "application/json");
        httpURLConnection.setRequestProperty("Accept-Encoding", str2);
        String str5 = zza2.zzc;
        if (str5 != null) {
            httpURLConnection.setRequestProperty("X-Goog-Api-Key", str5);
        }
        WritableByteChannel newChannel = Channels.newChannel(httpURLConnection.getOutputStream());
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                this.zza.encode(zza2.zzb, new OutputStreamWriter(gZIPOutputStream));
                gZIPOutputStream.close();
                newChannel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
                int responseCode = httpURLConnection.getResponseCode();
                StringBuilder sb = new StringBuilder();
                sb.append("Status Code: ");
                sb.append(responseCode);
                Logging.m18i(str, sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Content-Type: ");
                sb2.append(httpURLConnection.getHeaderField(str4));
                Logging.m18i(str, sb2.toString());
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Content-Encoding: ");
                sb3.append(httpURLConnection.getHeaderField(str3));
                Logging.m18i(str, sb3.toString());
                if (!(responseCode == 302 || responseCode == 301)) {
                    if (responseCode != 307) {
                        if (responseCode != 200) {
                            zzb zzb2 = new zzb(responseCode, null, 0);
                            newChannel.close();
                            return zzb2;
                        }
                        String headerField = httpURLConnection.getHeaderField(str3);
                        if (headerField == null || !headerField.equals(str2)) {
                            inputStream = httpURLConnection.getInputStream();
                        } else {
                            inputStream = new GZIPInputStream(httpURLConnection.getInputStream());
                        }
                        zzb zzb3 = new zzb(responseCode, null, zzx.zza(new InputStreamReader(inputStream)).zza());
                        inputStream.close();
                        newChannel.close();
                        return zzb3;
                    }
                }
                zzb zzb4 = new zzb(responseCode, new URL(httpURLConnection.getHeaderField(HttpHeaders.LOCATION)), 0);
                newChannel.close();
                return zzb4;
            } catch (EncodingException | IOException e) {
                Logging.m17e(str, "Couldn't encode request, returning with 400", e);
                zzb zzb5 = new zzb(HttpStatus.SC_BAD_REQUEST, null, 0);
                gZIPOutputStream.close();
                newChannel.close();
                return zzb5;
            }
        } catch (Throwable th) {
            newChannel.close();
            throw th;
        }
    }

    static /* synthetic */ zza zza(zza zza2, zzb zzb2) {
        URL url = zzb2.zzb;
        if (url == null) {
            return null;
        }
        Logging.m14d("CctTransportBackend", "Following redirect to: %s", (Object) url);
        return zza2.zza(zzb2.zzb);
    }
}
