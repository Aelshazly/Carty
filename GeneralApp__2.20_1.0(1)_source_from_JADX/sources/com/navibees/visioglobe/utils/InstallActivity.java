package com.navibees.visioglobe.utils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.navibees.C1164R;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class InstallActivity extends Activity {

    /* renamed from: e */
    protected static String f886e = "EmbeddedMaps";

    /* renamed from: a */
    protected String f887a = null;

    /* renamed from: b */
    public ProgressDialog f888b;

    /* renamed from: c */
    protected String f889c;

    /* renamed from: d */
    protected String f890d;

    /* renamed from: com.navibees.visioglobe.utils.InstallActivity$a */
    class C1530a implements OnClickListener {
        C1530a() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            InstallActivity.this.mo28641a();
        }
    }

    /* renamed from: a */
    private void m595a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("/");
        sb.append(str);
        new C1533a(this, sb.toString()).execute(new String[]{str});
    }

    /* renamed from: b */
    public String mo28643b() {
        Document document;
        String str = "(Vg)InstallActivity";
        try {
            try {
                document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(getAssets().open("DataManifest.xml"));
            } catch (Exception e) {
                Log.e(str, e.getMessage());
                document = null;
            }
            Element documentElement = document.getDocumentElement();
            String nodeName = documentElement.getNodeName();
            if (!nodeName.contentEquals("archive") && !nodeName.contentEquals("archives")) {
                return null;
            }
            try {
                URI uri = new URI(documentElement.getAttribute(Param.SOURCE));
                if (uri.getScheme().contentEquals("asset")) {
                    return uri.getPath().replaceAll("^/", "");
                }
                Log.e(str, "Error: The \"asset\" protocol doesn't exist within the \"DataManifest.xml\" file");
                return null;
            } catch (URISyntaxException e2) {
                Log.e(str, "Error: The source attribute within the \"DataManifest.xml\" contains an invalid path");
                return null;
            }
        } catch (IOException e3) {
            Log.e(str, "Error: \"DataManifest.xml\" is missing from the \"assets\" directory");
            return null;
        }
    }

    /* renamed from: c */
    public boolean mo28644c() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* renamed from: d */
    public void mo28645d() {
        setResult(-1);
        getSharedPreferences(this.f887a, 0).edit().putString("vg_config_xml", mo28640a(this.f890d)).commit();
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f887a = getIntent().getExtras().getString("preferenceID");
        String str = "installFolder";
        if (getIntent().hasExtra(str)) {
            this.f889c = getIntent().getExtras().getString(str);
        } else {
            this.f889c = f886e;
        }
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i) {
        if (i != 0) {
            return null;
        }
        this.f888b = new ProgressDialog(this);
        this.f888b.setProgressStyle(0);
        this.f888b.setTitle(C1164R.string.titleExtractingMapBundle);
        this.f888b.setCancelable(false);
        this.f888b.show();
        return this.f888b;
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        if (mo28644c()) {
            String b = mo28643b();
            File externalFilesDir = getExternalFilesDir(null);
            StringBuilder sb = new StringBuilder();
            sb.append(externalFilesDir.toString());
            sb.append("/");
            sb.append(this.f889c);
            this.f890d = sb.toString();
            File file = new File(this.f890d);
            if (file.exists()) {
                mo28642a(file);
            }
            if (b == null) {
                mo28641a();
            } else if (b.endsWith(".zip")) {
                m596b(b, this.f890d);
            } else {
                m595a(b, this.f890d);
            }
        } else {
            Builder builder = new Builder(this);
            builder.setTitle(C1164R.string.app_name);
            builder.setIcon(17301543);
            builder.setMessage(C1164R.string.externalStorageError);
            builder.setNeutralButton(getString(17039370), new C1530a());
            builder.create().show();
        }
    }

    /* renamed from: a */
    public String mo28640a(String str) {
        File file = new File(str);
        File a = m594a(file, "vg_config.xml");
        if (a != null && a.exists()) {
            return a.getAbsolutePath();
        }
        File a2 = m594a(file, "vg_config.vge");
        return (a2 == null || !a2.exists()) ? "" : a2.getAbsolutePath();
    }

    /* renamed from: a */
    private File m594a(File file, String str) {
        if (file.isFile() && file.getName().contains(str)) {
            return file;
        }
        if (file.isDirectory()) {
            for (File a : file.listFiles()) {
                File a2 = m594a(a, str);
                if (a2 != null) {
                    return a2;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28642a(File file) {
        if (file.isDirectory()) {
            for (File a : file.listFiles()) {
                mo28642a(a);
            }
        }
        file.delete();
    }

    /* renamed from: a */
    public void mo28641a() {
        setResult(0);
        finish();
    }

    /* renamed from: b */
    private void m596b(String str, String str2) {
        new C1535c(this, str2).execute(new String[]{str});
    }
}
