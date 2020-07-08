package com.navibees.maps;

import android.app.Activity;
import com.navibees.C1164R;
import com.navibees.visioglobe.VgMySurfaceView;
import com.visioglobe.libVisioMove.VgAnchorMode;
import com.visioglobe.libVisioMove.VgBinaryBuffer;
import com.visioglobe.libVisioMove.VgBinaryBufferConstRefPtr;
import com.visioglobe.libVisioMove.VgIApplication;
import com.visioglobe.libVisioMove.VgIGeometryCallback;
import com.visioglobe.libVisioMove.VgIGeometryCallbackRefPtr;
import com.visioglobe.libVisioMove.VgITextureRefPtr;
import com.visioglobe.libVisioMove.VgIconMarkerDescriptor;
import com.visioglobe.libVisioMove.VgIconMarkerDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgLayerRefPtr;
import com.visioglobe.libVisioMove.VgMarkerDescriptor;
import com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgOrientation;
import com.visioglobe.libVisioMove.VgPointDescriptor;
import com.visioglobe.libVisioMove.VgPointDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgPointRefPtr;
import com.visioglobe.libVisioMove.VgPosition;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.navibees.maps.k */
/* compiled from: VgMyPoiMarker */
public class C1453k {

    /* renamed from: a */
    VgPointRefPtr f614a;

    /* renamed from: b */
    Activity f615b;

    public C1453k(Activity activity, VgMySurfaceView vgMySurfaceView) {
        this.f615b = activity;
    }

    /* renamed from: a */
    public void mo28478a(String str, VgMySurfaceView vgMySurfaceView, VgPointDescriptorRefPtr vgPointDescriptorRefPtr) {
        VgIApplication application = vgMySurfaceView.getApplication();
        if (this.f614a == null) {
            this.f614a = application.editEngine().editInstanceFactory().instantiate(vgPointDescriptorRefPtr);
        } else {
            mo28474a();
        }
        VgLayerRefPtr vgLayerRefPtr = null;
        int i = 0;
        while (true) {
            if (((long) i) >= application.editEngine().editLayerManager().getLayers().size()) {
                i = Integer.MAX_VALUE;
                break;
            }
            VgLayerRefPtr vgLayerRefPtr2 = application.editEngine().editLayerManager().getLayers().get(i);
            if (vgLayerRefPtr2.getName().equalsIgnoreCase(str)) {
                vgLayerRefPtr = vgLayerRefPtr2;
                break;
            }
            i++;
        }
        if (i != Integer.MAX_VALUE && vgLayerRefPtr.isValid()) {
            this.f614a.setLayer(vgLayerRefPtr);
        }
    }

    /* renamed from: a */
    public void mo28474a() {
        this.f614a.setLayer(VgLayerRefPtr.getNull());
    }

    /* renamed from: a */
    public VgPointDescriptorRefPtr mo28473a(VgPosition vgPosition, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr) {
        VgPosition vgPosition2 = new VgPosition(vgPosition);
        VgPointDescriptorRefPtr create = VgPointDescriptor.create();
        create.setMPosition(vgPosition2);
        create.setMAnchorPosition(VgAnchorMode.eVgBottomCenter);
        create.setMGeometryConstantSizeDistance(0.0f);
        create.getMMarkerDescriptors().add(new VgMarkerDescriptorRefPtr((VgMarkerDescriptor) vgIconMarkerDescriptorRefPtr.get()));
        create.setMDrawOnTop(true);
        return create;
    }

    /* renamed from: a */
    private int m355a(int i) {
        if (i == 1) {
            return C1164R.C1166drawable.f160wc;
        }
        if (i == 2) {
            return C1164R.C1166drawable.tri;
        }
        if (i == 3) {
            return C1164R.C1166drawable.red;
        }
        if (i == 4) {
            return C1164R.C1166drawable.green;
        }
        switch (i) {
            case 11:
                return C1164R.C1166drawable.green1;
            case 12:
                return C1164R.C1166drawable.green2;
            case 13:
                return C1164R.C1166drawable.green3;
            case 14:
                return C1164R.C1166drawable.green4;
            default:
                switch (i) {
                    case 21:
                        return C1164R.C1166drawable.red1;
                    case 22:
                        return C1164R.C1166drawable.red2;
                    case 23:
                        return C1164R.C1166drawable.red3;
                    case 24:
                        return C1164R.C1166drawable.red4;
                    default:
                        return C1164R.C1166drawable.gray;
                }
        }
    }

    /* renamed from: a */
    public VgIconMarkerDescriptorRefPtr mo28472a(VgMySurfaceView vgMySurfaceView, int i) {
        VgIApplication application = vgMySurfaceView.getApplication();
        VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr = new VgBinaryBufferConstRefPtr();
        InputStream openRawResource = this.f615b.getResources().openRawResource(m355a(i));
        try {
            byte[] bArr = new byte[((int) this.f615b.getResources().openRawResourceFd(m355a(i)).getLength())];
            new DataInputStream(openRawResource).readFully(bArr);
            vgBinaryBufferConstRefPtr.set(new VgBinaryBuffer(bArr, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        VgITextureRefPtr createTexture = application.editEngine().editTextureManager().createTexture(vgBinaryBufferConstRefPtr);
        VgIconMarkerDescriptorRefPtr create = VgIconMarkerDescriptor.create();
        create.setMScale(2.0f);
        create.setMIcon(createTexture);
        return create;
    }

    /* renamed from: a */
    public void mo28477a(String str) {
        if (this.f614a != null) {
            this.f614a.addListener(new VgIGeometryCallbackRefPtr((VgIGeometryCallback) new C1450j(this.f615b, str)));
        }
    }

    /* renamed from: a */
    public void mo28476a(VgPosition vgPosition, VgLayerRefPtr vgLayerRefPtr, VgOrientation vgOrientation, float f) {
        this.f614a.setPosition(vgPosition);
        this.f614a.setLayer(vgLayerRefPtr);
        this.f614a.setOrientation(vgOrientation);
        this.f614a.setScale(f);
    }

    /* renamed from: a */
    public void mo28475a(VgPosition vgPosition) {
        mo28476a(new VgPosition(vgPosition), this.f614a.getLayer(), this.f614a.getOrientation(), this.f614a.getScale());
    }
}
