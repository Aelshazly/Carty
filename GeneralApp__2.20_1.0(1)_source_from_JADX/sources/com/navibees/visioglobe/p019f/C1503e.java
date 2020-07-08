package com.navibees.visioglobe.p019f;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.navibees.visioglobe.utils.C1536d;
import com.navibees.visioglobe.utils.C1536d.C1538b.C1540b;
import com.navibees.visioglobe.utils.C1546f;
import com.navibees.visioglobe.utils.C1546f.C1548b;
import com.navibees.visioglobe.utils.C1546f.C1551c;
import com.visioglobe.libVisioMove.VgIApplication;
import com.visioglobe.libVisioMove.VgLayerVector;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.navibees.visioglobe.f.e */
/* compiled from: VgMyParametersLoader */
public class C1503e {

    /* renamed from: a */
    Context f821a;

    /* renamed from: b */
    VgIApplication f822b;

    /* renamed from: c */
    LocalBroadcastManager f823c = LocalBroadcastManager.getInstance(this.f821a);

    /* renamed from: d */
    String f824d;

    public C1503e(Context context, VgIApplication vgIApplication, String str) {
        this.f821a = context;
        this.f822b = vgIApplication;
        this.f824d = str;
    }

    /* renamed from: a */
    public void mo28608a(C1546f fVar, String str, String str2) {
        FileInputStream fileInputStream;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f824d);
            sb.append(str);
            fileInputStream = new FileInputStream(new File(sb.toString()));
            FileChannel channel = fileInputStream.getChannel();
            String charBuffer = Charset.defaultCharset().decode(channel.map(MapMode.READ_ONLY, 0, channel.size())).toString();
            fileInputStream.close();
            fVar.mo28694a(new JSONObject(charBuffer).getJSONObject("locale").getJSONObject(str2).getJSONObject("venueLayout"));
        } catch (IOException | JSONException e) {
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    /* renamed from: a */
    public void mo28607a() {
        FileInputStream fileInputStream;
        String str = "venueLayout";
        String str2 = "parameters";
        String str3 = "parametersLoaded";
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f824d);
            sb.append("/config/vg_venue_layout.json");
            fileInputStream = new FileInputStream(new File(sb.toString()));
            FileChannel channel = fileInputStream.getChannel();
            String charBuffer = Charset.defaultCharset().decode(channel.map(MapMode.READ_ONLY, 0, channel.size())).toString();
            fileInputStream.close();
            C1546f fVar = new C1546f(new JSONObject(charBuffer));
            Locale locale = this.f821a.getResources().getConfiguration().locale;
            mo28608a(fVar, "/resources/default/vg_localized.json", "default");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("/resources/");
            sb2.append(locale.getLanguage());
            sb2.append("/vg_localized.json");
            mo28608a(fVar, sb2.toString(), locale.getLanguage());
            C1536d dVar = new C1536d();
            if (fVar.f927c == null || fVar.f927c.isEmpty()) {
                dVar.f911a.f922a = false;
            }
            Intent intent = new Intent(str3);
            intent.putExtra(str2, dVar);
            intent.putExtra(str, fVar);
            this.f823c.sendBroadcast(intent);
        } catch (IOException | JSONException e) {
            C1546f fVar2 = new C1546f();
            String str4 = "<Building>";
            C1548b bVar = new C1548b(str4);
            VgLayerVector layers = this.f822b.editEngine().editLayerManager().getLayers();
            long size = layers.size();
            for (long j = 0; j < size; j++) {
                int i = (int) j;
                String name = layers.get(i).getName();
                C1551c cVar = new C1551c(name);
                cVar.f943f = i;
                cVar.f939b = name;
                bVar.mo28701a(cVar);
            }
            fVar2.f929e.put(str4, bVar);
            C1536d dVar2 = new C1536d();
            dVar2.f911a.f922a = false;
            dVar2.f912b.f916c = C1540b.eSpaced;
            Intent intent2 = new Intent(str3);
            intent2.putExtra(str2, dVar2);
            intent2.putExtra(str, fVar2);
            this.f823c.sendBroadcast(intent2);
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }
}
