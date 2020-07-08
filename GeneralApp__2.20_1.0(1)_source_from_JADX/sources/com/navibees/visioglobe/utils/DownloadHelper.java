package com.navibees.visioglobe.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class DownloadHelper extends Activity {

    /* renamed from: a */
    boolean f875a = false;

    /* renamed from: b */
    boolean f876b = false;

    /* renamed from: c */
    public String f877c;

    /* renamed from: d */
    public String f878d;

    /* renamed from: e */
    public String f879e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public int f880f;

    /* renamed from: g */
    private String f881g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public ProgressDialog f882h;

    /* renamed from: i */
    private C1529a f883i = null;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public String f884j;

    /* renamed from: com.navibees.visioglobe.utils.DownloadHelper$a */
    class C1529a extends AsyncTask<String, Integer, Integer> {
        C1529a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Integer doInBackground(String... strArr) {
            HttpURLConnection httpURLConnection;
            String str = "The url to download is malformed: ";
            String str2 = "(Vg)DownloadHelper";
            Integer valueOf = Integer.valueOf(-1);
            String str3 = strArr[0];
            try {
                URL url = new URL(str3);
                URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                URL url2 = uri.toURL();
                DownloadHelper.this.f884j = url2.toString();
                StringBuilder sb = new StringBuilder();
                sb.append("Downloading file ");
                sb.append(url2.toString());
                sb.append(" ...");
                Log.i(str2, sb.toString());
                try {
                    if (url2.getProtocol().contentEquals("https")) {
                        httpURLConnection = (HttpsURLConnection) url2.openConnection();
                    } else {
                        httpURLConnection = (HttpURLConnection) url2.openConnection();
                    }
                    httpURLConnection.setConnectTimeout(30000);
                    httpURLConnection.setReadTimeout(60000);
                    httpURLConnection.connect();
                    int contentLength = httpURLConnection.getContentLength();
                    String file = url2.getFile();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(" - Length of file: ");
                    sb2.append(contentLength);
                    Log.i(str2, sb2.toString());
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(" - File name: ");
                    sb3.append(file);
                    Log.i(str2, sb3.toString());
                    try {
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                        try {
                            new File(DownloadHelper.this.f879e).mkdirs();
                            new File(DownloadHelper.this.f877c).createNewFile();
                            FileOutputStream fileOutputStream = new FileOutputStream(DownloadHelper.this.f877c);
                            byte[] bArr = new byte[8192];
                            long j = 0;
                            while (true) {
                                try {
                                    int read = bufferedInputStream.read(bArr);
                                    if (read != -1) {
                                        j += (long) read;
                                        publishProgress(new Integer[]{Integer.valueOf((int) ((((float) j) / ((float) contentLength)) * 100.0f))});
                                        fileOutputStream.write(bArr, 0, read);
                                    } else {
                                        fileOutputStream.flush();
                                        fileOutputStream.close();
                                        bufferedInputStream.close();
                                        httpURLConnection.disconnect();
                                        return valueOf;
                                    }
                                } catch (IOException e) {
                                    Log.i(str2, "A stream error occured whilst writing to file");
                                    return Integer.valueOf(7);
                                }
                            }
                        } catch (FileNotFoundException e2) {
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append("File download stream doesn't exist and couldn't be created: ");
                            sb4.append(DownloadHelper.this.f877c);
                            Log.i(str2, sb4.toString());
                            return Integer.valueOf(4);
                        } catch (IOException e3) {
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("The file could not be created: ");
                            sb5.append(DownloadHelper.this.f877c);
                            Log.i(str2, sb5.toString());
                            return Integer.valueOf(4);
                        }
                    } catch (IOException e4) {
                        Log.i(str2, "The username or password are invalid.");
                        return Integer.valueOf(6);
                    }
                } catch (SocketTimeoutException e5) {
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("Timeout occuring on connection: ");
                    sb6.append(url2.toString());
                    Log.i(str2, sb6.toString());
                    return Integer.valueOf(3);
                } catch (IOException e6) {
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("An error occurred connecting to resource: ");
                    sb7.append(url2.toString());
                    Log.i(str2, sb7.toString());
                    return Integer.valueOf(2);
                }
            } catch (MalformedURLException e7) {
                StringBuilder sb8 = new StringBuilder();
                sb8.append(str);
                sb8.append(str3);
                Log.i(str2, sb8.toString());
                return Integer.valueOf(5);
            } catch (URISyntaxException e8) {
                StringBuilder sb9 = new StringBuilder();
                sb9.append(str);
                sb9.append(str3);
                Log.i(str2, sb9.toString());
                return Integer.valueOf(5);
            }
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            if (DownloadHelper.this.f880f != 0) {
                DownloadHelper downloadHelper = DownloadHelper.this;
                downloadHelper.showDialog(downloadHelper.f880f);
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onProgressUpdate(Integer... numArr) {
            if (DownloadHelper.this.f882h != null) {
                DownloadHelper.this.f882h.setProgress(numArr[0].intValue());
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Integer num) {
            if (DownloadHelper.this.f880f != 0) {
                try {
                    DownloadHelper.this.dismissDialog(DownloadHelper.this.f880f);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
            Intent intent = null;
            if (num.intValue() == -1) {
                intent = new Intent();
                intent.putExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.downloadDir", DownloadHelper.this.f879e);
                intent.putExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.downloadFilename", DownloadHelper.this.f878d);
                intent.putExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.downloadPath", DownloadHelper.this.f877c);
                intent.putExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.URL", DownloadHelper.this.f884j);
            }
            DownloadHelper.this.setResult(num.intValue(), intent);
            DownloadHelper.this.finish();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState)) {
            this.f876b = true;
            this.f875a = true;
        } else if ("mounted_ro".equals(externalStorageState)) {
            this.f875a = true;
            this.f876b = false;
        } else {
            this.f876b = false;
            this.f875a = false;
        }
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i) {
        if (i == 0) {
            return null;
        }
        if (i == 1) {
            this.f882h = new ProgressDialog(this);
            this.f882h.setTitle(this.f881g);
            this.f882h.setProgressStyle(1);
            this.f882h.setCancelable(false);
            this.f882h.show();
            return this.f882h;
        } else if (i != 2) {
            return null;
        } else {
            this.f882h = new ProgressDialog(this);
            this.f882h.setProgressStyle(0);
            this.f882h.setTitle(this.f881g);
            this.f882h.setCancelable(false);
            this.f882h.show();
            return this.f882h;
        }
    }

    public void onResume() {
        super.onResume();
        if (this.f883i != null) {
            return;
        }
        if (!this.f875a || !this.f876b) {
            setResult(7);
            finish();
            return;
        }
        String string = getIntent().getExtras().getString("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.URL");
        File externalCacheDir = getExternalCacheDir();
        String str = "";
        if (externalCacheDir != null) {
            this.f879e = externalCacheDir.toString();
        } else {
            this.f879e = str;
        }
        this.f878d = getIntent().getExtras().getString("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.downloadFilename");
        StringBuilder sb = new StringBuilder();
        sb.append(this.f879e);
        sb.append("/");
        sb.append(this.f878d);
        this.f877c = sb.toString();
        this.f881g = str;
        String str2 = "com.visioglobe.VisioSample.Utils.DownloadHelper.extra.dialogType";
        if (getIntent().hasExtra(str2)) {
            this.f880f = getIntent().getExtras().getInt(str2);
            String str3 = "com.visioglobe.VisioSample.Utils.DownloadHelper.extra.dialogMessage";
            if (getIntent().hasExtra(str3)) {
                str = getIntent().getExtras().getString(str3);
            }
            this.f881g = str;
        } else {
            this.f880f = 0;
        }
        m588a(string);
    }

    /* renamed from: a */
    private void m588a(String str) {
        this.f883i = new C1529a();
        this.f883i.execute(new String[]{str});
    }
}
