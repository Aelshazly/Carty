package com.navibees.visioglobe;

import android.app.Activity;
import com.visioglobe.libVisioMove.VgBinaryBuffer;
import com.visioglobe.libVisioMove.VgBinaryBufferConstRefPtr;
import com.visioglobe.libVisioMove.VgIEngine;
import com.visioglobe.libVisioMove.VgITextureRefPtr;
import com.visioglobe.libVisioMove.VgIconMarkerDescriptor;
import com.visioglobe.libVisioMove.VgIconMarkerDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgMarkerDescriptor;
import com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/* renamed from: com.navibees.visioglobe.e */
/* compiled from: VgMyTextureLoader */
public class C1482e {

    /* renamed from: a */
    private HashMap<String, C1484b> f752a = new HashMap<>();

    /* renamed from: b */
    private HashMap<String, C1483a> f753b = new HashMap<>();

    /* renamed from: c */
    private Activity f754c;

    /* renamed from: d */
    private VgMySurfaceView f755d;

    /* renamed from: com.navibees.visioglobe.e$a */
    /* compiled from: VgMyTextureLoader */
    protected class C1483a {

        /* renamed from: a */
        boolean f756a = false;

        /* renamed from: b */
        String f757b;

        /* renamed from: c */
        VgIconMarkerDescriptorRefPtr f758c;

        public C1483a(C1482e eVar, String str) {
            this.f757b = str;
        }
    }

    /* renamed from: com.navibees.visioglobe.e$b */
    /* compiled from: VgMyTextureLoader */
    protected class C1484b {

        /* renamed from: a */
        boolean f759a = false;

        /* renamed from: b */
        int f760b;

        /* renamed from: c */
        VgITextureRefPtr f761c = null;

        public C1484b(C1482e eVar, int i) {
            this.f760b = i;
        }
    }

    public C1482e(Activity activity, VgMySurfaceView vgMySurfaceView) {
        this.f754c = activity;
        this.f755d = vgMySurfaceView;
    }

    /* renamed from: a */
    public void mo28576a(String str, int i) {
        this.f752a.put(str, new C1484b(this, i));
    }

    /* renamed from: b */
    public VgITextureRefPtr mo28578b(String str) {
        C1484b bVar = (C1484b) this.f752a.get(str);
        if (bVar == null) {
            return null;
        }
        if (!bVar.f759a) {
            bVar.f759a = true;
            VgIEngine editEngine = this.f755d.getApplication().editEngine();
            if (editEngine == null || editEngine.editTextureManager() == null) {
                return null;
            }
            bVar.f761c = mo28573a(bVar.f760b);
        }
        return bVar.f761c;
    }

    /* renamed from: c */
    public boolean mo28579c(String str) {
        return this.f752a.get(str) != null;
    }

    /* renamed from: d */
    public boolean mo28580d(String str) {
        return this.f753b.get(str) != null;
    }

    /* renamed from: a */
    public void mo28577a(String str, String str2) {
        this.f753b.put(str, new C1483a(this, str2));
    }

    /* renamed from: a */
    public VgMarkerDescriptorRefPtr mo28574a(String str) {
        C1483a aVar = (C1483a) this.f753b.get(str);
        if (aVar == null) {
            return null;
        }
        if (!aVar.f756a) {
            aVar.f756a = true;
            aVar.f758c = VgIconMarkerDescriptor.create();
            aVar.f758c.setMScale(20.0f);
            aVar.f758c.setMIcon(mo28578b(aVar.f757b));
        }
        return new VgMarkerDescriptorRefPtr((VgMarkerDescriptor) aVar.f758c.get());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public VgITextureRefPtr mo28573a(int i) {
        VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr = new VgBinaryBufferConstRefPtr();
        InputStream openRawResource = this.f754c.getResources().openRawResource(i);
        try {
            byte[] bArr = new byte[((int) this.f754c.getResources().openRawResourceFd(i).getLength())];
            new DataInputStream(openRawResource).readFully(bArr);
            vgBinaryBufferConstRefPtr.set(new VgBinaryBuffer(bArr, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (vgBinaryBufferConstRefPtr.isValid()) {
            return this.f755d.getApplication().editEngine().editTextureManager().createTexture(vgBinaryBufferConstRefPtr);
        }
        return null;
    }

    /* renamed from: a */
    public void mo28575a() {
        this.f754c = null;
        this.f755d = null;
        HashMap<String, C1484b> hashMap = this.f752a;
        if (hashMap != null) {
            hashMap.clear();
            this.f752a = null;
        }
        HashMap<String, C1483a> hashMap2 = this.f753b;
        if (hashMap2 != null) {
            hashMap2.clear();
            this.f753b = null;
        }
    }
}
