package com.navibees.visioglobe.utils;

import android.os.AsyncTask;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* renamed from: com.navibees.visioglobe.utils.c */
/* compiled from: UnzipAssetThread */
class C1535c extends AsyncTask<String, String, Integer> {

    /* renamed from: a */
    Integer f907a = Integer.valueOf(0);

    /* renamed from: b */
    Integer f908b = Integer.valueOf(1);

    /* renamed from: c */
    String f909c = null;

    /* renamed from: d */
    InstallActivity f910d;

    public C1535c(InstallActivity installActivity, String str) {
        this.f910d = installActivity;
        this.f909c = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Integer doInBackground(String... strArr) {
        String str = "Vg (UnzipAssetThread)";
        try {
            try {
                mo28672a(new ZipInputStream(this.f910d.getAssets().open(strArr[0], 2)));
                return this.f907a;
            } catch (Exception e) {
                e.printStackTrace();
                StringBuilder sb = new StringBuilder();
                sb.append("Problems with zip file: ");
                sb.append(e.getMessage());
                Log.e(str, sb.toString());
                return this.f908b;
            }
        } catch (Exception e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Could not open: (maybe missing from assets directory?) ");
            sb2.append(e2.getMessage());
            Log.e(str, sb2.toString());
            return this.f908b;
        }
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        this.f910d.showDialog(0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Integer num) {
        this.f910d.dismissDialog(0);
        if (this.f907a == num) {
            this.f910d.mo28645d();
        } else {
            this.f910d.mo28641a();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28672a(ZipInputStream zipInputStream) {
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        while (nextEntry != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f909c);
            sb.append("/");
            sb.append(nextEntry.getName());
            File file = new File(sb.toString());
            if (nextEntry.isDirectory()) {
                file.mkdirs();
            } else {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = zipInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            zipInputStream.closeEntry();
            nextEntry = zipInputStream.getNextEntry();
        }
    }
}
