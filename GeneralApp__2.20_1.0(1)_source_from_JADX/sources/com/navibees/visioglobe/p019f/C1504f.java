package com.navibees.visioglobe.p019f;

import android.content.Intent;
import android.util.Log;
import androidx.fragment.app.Fragment;
import com.navibees.C1164R;
import com.navibees.navigatorapp.network.RestApi;
import com.navibees.visioglobe.p019f.C1501d.C1502a;
import com.navibees.visioglobe.p020g.C1523l;
import com.navibees.visioglobe.p020g.C1523l.C1524a.C1525a;
import com.navibees.visioglobe.p020g.C1523l.C1526b;
import com.navibees.visioglobe.utils.DownloadHelper;
import com.navibees.visioglobe.utils.UnzipHelper;
import com.visioglobe.libVisioMove.VgIApplication;
import java.io.File;
import java.net.URL;
import p008cz.msebera.android.httpclient.HttpStatus;

/* renamed from: com.navibees.visioglobe.f.f */
/* compiled from: VgMyRemoteMapManagerImpl */
public class C1504f implements C1523l {

    /* renamed from: a */
    Fragment f825a;

    /* renamed from: b */
    boolean f826b = false;

    /* renamed from: c */
    String f827c;

    /* renamed from: d */
    VgIApplication f828d;

    /* renamed from: e */
    C1526b f829e;

    /* renamed from: f */
    String f830f;

    public C1504f(Fragment fragment, VgIApplication vgIApplication, C1526b bVar, String str, String str2) {
        this.f825a = fragment;
        this.f829e = bVar;
        this.f828d = vgIApplication;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("/DownloadedMaps");
        this.f827c = sb.toString();
    }

    /* renamed from: a */
    public void mo28610a(int i, int i2, Intent intent) {
        String str = "com.visioglobe.VisioSample.Utils.DownloadHelper.extra.downloadPath";
        switch (i) {
            case 200:
                if (i2 != -1) {
                    this.f829e.f874f.mo28292a((C1523l) this, C1525a.eFailed, this.f830f);
                    return;
                } else if (intent.hasExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.downloadFilename") && intent.hasExtra(str)) {
                    String str2 = "VgMyRemoteMapManager";
                    if (!mo28615c(this.f830f)) {
                        Log.i(str2, "Error removing previous map");
                    }
                    String string = intent.getExtras().getString(str);
                    String b = mo28613b(this.f830f);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unzipping bundle: ");
                    sb.append(string);
                    sb.append(" -> ");
                    sb.append(b);
                    Log.i(str2, sb.toString());
                    Intent intent2 = new Intent(this.f825a.getActivity(), UnzipHelper.class);
                    intent2.putExtra("com.visioglobe.VisioSample.Utils.UnzipHelper.extra.unzipPath", string);
                    intent2.putExtra("com.visioglobe.VisioSample.Utils.UnzipHelper.extra.unzipLocation", b);
                    intent2.putExtra("com.visioglobe.VisioSample.Utils.UnzipHelper.extra.unzipMessage", this.f825a.getActivity().getString(C1164R.string.titleUnzipping));
                    this.f825a.startActivityForResult(intent2, HttpStatus.SC_ACCEPTED);
                    return;
                } else {
                    return;
                }
            case HttpStatus.SC_CREATED /*201*/:
                boolean z = false;
                if (i2 == -1) {
                    String str3 = "com.visioglobe.VisioSample.Utils.DownloadHelper.extra.URL";
                    if (intent.hasExtra(str3) && intent.hasExtra(str)) {
                        String string2 = intent.getExtras().getString(str);
                        try {
                            URL url = new URL(intent.getExtras().getString(str3));
                            C1501d dVar = new C1501d();
                            if (dVar.mo28604a(C1501d.m518a(new File(string2)), url)) {
                                this.f829e.f874f.mo28291a((C1523l) this, C1525a.eSuccess, dVar);
                                z = true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (!z) {
                    this.f829e.f874f.mo28291a((C1523l) this, C1525a.eFailed, (C1501d) null);
                    return;
                }
                return;
            case HttpStatus.SC_ACCEPTED /*202*/:
                if (i2 == -1) {
                    this.f829e.f874f.mo28292a((C1523l) this, C1525a.eSuccess, this.f830f);
                    return;
                } else {
                    this.f829e.f874f.mo28292a((C1523l) this, C1525a.eFailed, this.f830f);
                    return;
                }
            default:
                return;
        }
    }

    /* renamed from: b */
    public String mo28613b(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f827c);
        sb.append("/");
        sb.append(str);
        return sb.toString();
    }

    /* renamed from: c */
    public boolean mo28615c(String str) {
        String a = mo28609a(str);
        if (a.length() > 0) {
            String[] strArr = new String[2];
            VgIApplication vgIApplication = this.f828d;
            if (vgIApplication != null && vgIApplication.editEngine().editDatabase().getCachedLicenseFilenameForConfiguration(a, strArr)) {
                File file = new File(strArr[0]);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        File file2 = new File(mo28613b(str));
        if (file2.exists()) {
            m526a(file2);
        }
        return true;
    }

    /* renamed from: b */
    public void mo28614b() {
        this.f825a = null;
        this.f828d = null;
        this.f829e = null;
    }

    /* renamed from: a */
    public boolean mo28611a() {
        String str;
        if (this.f826b) {
            return false;
        }
        if (this.f829e.f872d != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f829e.f869a);
            sb.append(this.f829e.f872d);
            sb.append("/");
            sb.append(this.f829e.f873e);
            str = sb.toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.f829e.f869a);
            sb2.append("list?login=");
            sb2.append(this.f829e.f870b);
            sb2.append("&password=");
            sb2.append(this.f829e.f871c);
            str = sb2.toString();
        }
        Intent intent = new Intent(this.f825a.getActivity(), DownloadHelper.class);
        intent.putExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.URL", str);
        intent.putExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.downloadFilename", RestApi.LIST_JSON);
        intent.putExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.dialogType", 2);
        intent.putExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.dialogMessage", this.f825a.getActivity().getString(C1164R.string.titleListDownload));
        this.f825a.startActivityForResult(intent, HttpStatus.SC_CREATED);
        return true;
    }

    /* renamed from: a */
    public boolean mo28612a(C1502a aVar) {
        if (this.f826b) {
            return false;
        }
        this.f830f = aVar.f815b;
        String url = aVar.f820g.toString();
        Intent intent = new Intent(this.f825a.getActivity(), DownloadHelper.class);
        intent.putExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.URL", url);
        intent.putExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.downloadFilename", "bundle.zip");
        intent.putExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.dialogType", 1);
        intent.putExtra("com.visioglobe.VisioSample.Utils.DownloadHelper.extra.dialogMessage", this.f825a.getString(C1164R.string.titleMapDownload));
        this.f825a.startActivityForResult(intent, 200);
        return true;
    }

    /* renamed from: a */
    public String mo28609a(String str) {
        File file = new File(mo28613b(str));
        File a = m525a(file, "vg_config.xml");
        if (a != null && a.exists()) {
            return a.getAbsolutePath();
        }
        File a2 = m525a(file, "vg_config.vge");
        return (a2 == null || !a2.exists()) ? "" : a2.getAbsolutePath();
    }

    /* renamed from: a */
    private File m525a(File file, String str) {
        if (file.isFile() && file.getName().contains(str)) {
            return file;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File a : listFiles) {
                    File a2 = m525a(a, str);
                    if (a2 != null) {
                        return a2;
                    }
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    private void m526a(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File a : listFiles) {
                    m526a(a);
                }
            }
        }
        file.delete();
    }
}
