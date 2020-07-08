package com.navibees.visioglobe.utils;

import android.app.Application;
import android.os.Environment;
import androidx.collection.SimpleArrayMap;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.visioglobe.C1472a;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* renamed from: com.navibees.visioglobe.utils.b */
/* compiled from: MapDataExtractor */
public class C1534b {
    /* renamed from: a */
    public boolean mo28669a(Application application) {
        if (!m611a()) {
            return false;
        }
        File externalFilesDir = application.getExternalFilesDir(null);
        StringBuilder sb = new StringBuilder();
        sb.append(externalFilesDir.toString());
        String str = "/";
        sb.append(str);
        sb.append(InstallActivity.f886e);
        String sb2 = sb.toString();
        if (new File(sb2).exists()) {
            return true;
        }
        try {
            String[] list = application.getAssets().list("MapData");
            if (list != null) {
                if (list.length != 0) {
                    for (String str2 : list) {
                        try {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("MapData/");
                            sb3.append(str2);
                            try {
                                mo28668a(new ZipInputStream(application.getAssets().open(sb3.toString(), 2)), sb2);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    SimpleArrayMap venues = NaviBeesManager.getInstance(application).getMetaDataManager().getVenues();
                    int i = 0;
                    while (venues != null && i < venues.size()) {
                        String str3 = (String) venues.keyAt(i);
                        Venue venue = (Venue) venues.get(str3);
                        String format = String.format("venue_%s", new Object[]{str3});
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(sb2);
                        sb4.append(str);
                        sb4.append(format);
                        sb4.append("/Data.mapeditor/PVRTC/vg_config.xml");
                        String sb5 = sb4.toString();
                        if (new File(sb5).exists()) {
                            C1472a aVar = new C1472a();
                            aVar.f722b = Long.valueOf(291268198);
                            aVar.f721a = sb5;
                            aVar.f724d = venue.mapProvider.version;
                            aVar.mo28551a(application.getApplicationContext(), str3);
                        }
                        i++;
                    }
                    return true;
                }
            }
            return false;
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    private boolean m611a() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28668a(ZipInputStream zipInputStream, String str) {
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        while (nextEntry != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
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
