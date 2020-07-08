package com.navibees.visioglobe.utils;

import android.os.AsyncTask;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.navibees.visioglobe.utils.a */
/* compiled from: CopyAssetThread */
class C1533a extends AsyncTask<String, String, Integer> {

    /* renamed from: a */
    private Integer f903a = Integer.valueOf(0);

    /* renamed from: b */
    private Integer f904b = Integer.valueOf(1);

    /* renamed from: c */
    private String f905c = null;

    /* renamed from: d */
    private InstallActivity f906d;

    public C1533a(InstallActivity installActivity, String str) {
        this.f906d = installActivity;
        this.f905c = str;
    }

    /* renamed from: b */
    private void m608b(String str, String str2) {
        String str3 = "/";
        try {
            String[] list = this.f906d.getAssets().list(str);
            if (list.length == 0) {
                m607a(str, str2);
                return;
            }
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            for (String str4 : list) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(str3);
                sb.append(str4);
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str2);
                sb3.append(str3);
                sb3.append(str4);
                m608b(sb2, sb3.toString());
            }
        } catch (IOException e) {
            Log.e("(Vg)CopyAssetThread", e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Integer doInBackground(String... strArr) {
        String str = "(Vg)CopyAssetThread";
        String str2 = strArr[0];
        try {
            if (this.f906d.getAssets().list(str2).length == 0) {
                Log.e(str, "Error: The map bundle identified within \"DataManifest.xml\" was not found");
                return this.f904b;
            }
            m608b(str2, this.f905c);
            return this.f903a;
        } catch (IOException e) {
            Log.e(str, e.getMessage());
            return this.f904b;
        }
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        this.f906d.showDialog(0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Integer num) {
        this.f906d.dismissDialog(0);
        if (this.f903a.equals(num)) {
            this.f906d.mo28645d();
        } else {
            this.f906d.mo28641a();
        }
    }

    /* renamed from: a */
    private void m607a(String str, String str2) {
        try {
            InputStream open = this.f906d.getAssets().open(str);
            new File(str2).createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[65536];
            while (true) {
                int read = open.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    open.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            Log.e("(Vg)CopyAssetThread", e.getMessage());
        }
    }
}
