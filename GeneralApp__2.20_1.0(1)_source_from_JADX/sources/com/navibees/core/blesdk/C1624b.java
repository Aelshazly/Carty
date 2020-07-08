package com.navibees.core.blesdk;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothManager;
import android.content.Context;

/* renamed from: com.navibees.core.blesdk.b */
/* compiled from: BleClient */
public class C1624b {

    /* renamed from: c */
    private static C1624b f1218c;

    /* renamed from: a */
    private BluetoothManager f1219a;

    /* renamed from: b */
    private BluetoothAdapter f1220b = this.f1219a.getAdapter();

    private C1624b(Context context) {
        this.f1219a = (BluetoothManager) context.getApplicationContext().getSystemService("bluetooth");
    }

    /* renamed from: a */
    public static C1624b m877a(Context context) {
        if (f1218c == null) {
            f1218c = new C1624b(context);
        }
        return f1218c;
    }

    /* renamed from: b */
    public void mo28957b(LeScanCallback leScanCallback) {
        this.f1220b.stopLeScan(leScanCallback);
    }

    /* renamed from: a */
    public void mo28956a(LeScanCallback leScanCallback) {
        this.f1220b.startLeScan(leScanCallback);
    }
}
