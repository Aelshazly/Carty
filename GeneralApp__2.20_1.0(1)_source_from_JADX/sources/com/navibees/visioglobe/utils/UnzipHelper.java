package com.navibees.visioglobe.utils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import com.navibees.C1164R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipHelper extends Activity {

    /* renamed from: a */
    boolean f892a = false;

    /* renamed from: b */
    boolean f893b = false;

    /* renamed from: c */
    private C1532b f894c = null;

    /* renamed from: d */
    private String f895d;

    /* renamed from: e */
    public File f896e;

    /* renamed from: f */
    public File f897f;

    /* renamed from: g */
    private ProgressDialog f898g;

    /* renamed from: com.navibees.visioglobe.utils.UnzipHelper$a */
    class C1531a implements OnClickListener {
        C1531a() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            UnzipHelper.this.setResult(0);
            UnzipHelper.this.finish();
        }
    }

    /* renamed from: com.navibees.visioglobe.utils.UnzipHelper$b */
    class C1532b extends AsyncTask<File, String, Integer> {

        /* renamed from: a */
        File f900a = null;

        /* renamed from: b */
        File f901b = null;

        C1532b(File file) {
            this.f900a = file;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Integer doInBackground(File... fileArr) {
            ZipInputStream zipInputStream;
            File file = fileArr[0];
            try {
                if (!file.exists() || !file.toString().endsWith("zip")) {
                    zipInputStream = null;
                } else {
                    zipInputStream = new ZipInputStream(new FileInputStream(file));
                }
                if (this.f901b == null) {
                    this.f901b = new File(this.f900a.getPath());
                    this.f901b.mkdirs();
                }
                for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.f900a.getPath());
                    sb.append("/");
                    sb.append(nextEntry.getName());
                    File file2 = new File(sb.toString());
                    if (nextEntry.isDirectory()) {
                        file2.mkdirs();
                    } else {
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Integer.valueOf(-1);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onProgressUpdate(String... strArr) {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            UnzipHelper.this.showDialog(0);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Integer num) {
            UnzipHelper.this.dismissDialog(0);
            Intent intent = new Intent();
            intent.putExtra("com.visioglobe.VisioSample.Utils.UnzipHelper.extra.unzipLocationRoot", this.f901b.toString());
            UnzipHelper.this.setResult(num.intValue(), intent);
            UnzipHelper.this.finish();
        }
    }

    /* renamed from: a */
    private void m603a(File file, File file2) {
        this.f894c = new C1532b(file2);
        this.f894c.execute(new File[]{file});
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState)) {
            this.f893b = true;
            this.f892a = true;
        } else if ("mounted_ro".equals(externalStorageState)) {
            this.f892a = true;
            this.f893b = false;
        } else {
            this.f893b = false;
            this.f892a = false;
        }
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i) {
        if (i != 0) {
            return null;
        }
        this.f898g = new ProgressDialog(this);
        this.f898g.setMessage(this.f895d);
        this.f898g.setProgressStyle(0);
        this.f898g.setCancelable(false);
        this.f898g.show();
        return this.f898g;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.f898g.cancel();
        this.f898g = null;
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        if (this.f894c != null) {
            return;
        }
        if (!this.f892a || !this.f893b) {
            Builder builder = new Builder(this);
            builder.setTitle(C1164R.string.app_name);
            builder.setIcon(17301543);
            builder.setMessage(C1164R.string.externalStorageError);
            builder.setNeutralButton(getString(17039370), new C1531a());
            builder.create().show();
            return;
        }
        this.f896e = new File(getIntent().getExtras().getString("com.visioglobe.VisioSample.Utils.UnzipHelper.extra.unzipPath"));
        this.f897f = new File(getIntent().getExtras().getString("com.visioglobe.VisioSample.Utils.UnzipHelper.extra.unzipLocation"));
        String str = "com.visioglobe.VisioSample.Utils.UnzipHelper.extra.unzipMessage";
        this.f895d = getIntent().hasExtra(str) ? getIntent().getExtras().getString(str) : "";
        m603a(this.f896e, this.f897f);
    }
}
