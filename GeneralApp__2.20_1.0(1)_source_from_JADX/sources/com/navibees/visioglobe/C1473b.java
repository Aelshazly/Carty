package com.navibees.visioglobe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.navibees.visioglobe.p020g.C1515e.C1516a;
import com.navibees.visioglobe.utils.C1536d;
import com.navibees.visioglobe.utils.C1546f;
import com.visioglobe.libVisioMove.VgIMapModule;
import java.util.Map.Entry;

/* renamed from: com.navibees.visioglobe.b */
/* compiled from: VgMyExploreSolver */
public class C1473b {

    /* renamed from: a */
    protected C1546f f725a;

    /* renamed from: b */
    protected C1536d f726b;

    /* renamed from: c */
    protected C1516a f727c;

    /* renamed from: d */
    protected String f728d;

    /* renamed from: e */
    protected String f729e;

    /* renamed from: f */
    protected boolean f730f;

    /* renamed from: g */
    protected LocalBroadcastManager f731g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public VgIMapModule f732h;

    /* renamed from: i */
    private BroadcastReceiver f733i = new C1474a();

    /* renamed from: j */
    private BroadcastReceiver f734j = new C1475b();

    /* renamed from: com.navibees.visioglobe.b$a */
    /* compiled from: VgMyExploreSolver */
    class C1474a extends BroadcastReceiver {
        C1474a() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:9:0x003b, code lost:
            if (r3 != 3) goto L_0x0064;
         */
        /* JADX WARNING: Removed duplicated region for block: B:74:0x018d  */
        /* JADX WARNING: Removed duplicated region for block: B:93:0x0208  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceive(android.content.Context r9, android.content.Intent r10) {
            /*
                r8 = this;
                java.lang.String r9 = "view"
                java.io.Serializable r9 = r10.getSerializableExtra(r9)
                com.navibees.visioglobe.g.e$a r9 = (com.navibees.visioglobe.p020g.C1515e.C1516a) r9
                java.lang.String r0 = "focusedBuilding"
                java.lang.String r0 = r10.getStringExtra(r0)
                java.lang.String r1 = "focusedFloor"
                java.lang.String r1 = r10.getStringExtra(r1)
                java.lang.String r2 = "focusedPlace"
                java.lang.String r2 = r10.getStringExtra(r2)
                com.navibees.visioglobe.b r3 = com.navibees.visioglobe.C1473b.this
                r4 = 1
                java.lang.String r5 = "focusAnimation"
                boolean r5 = r10.getBooleanExtra(r5, r4)
                r3.f730f = r5
                if (r9 == 0) goto L_0x0064
                com.navibees.visioglobe.b r3 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.g.e$a r3 = r3.f727c
                if (r3 == r9) goto L_0x0064
                int[] r3 = com.navibees.visioglobe.C1473b.C1476c.f737a
                int r5 = r9.ordinal()
                r3 = r3[r5]
                if (r3 == r4) goto L_0x003e
                r5 = 2
                if (r3 == r5) goto L_0x004a
                r5 = 3
                if (r3 == r5) goto L_0x0056
                goto L_0x0064
            L_0x003e:
                com.navibees.visioglobe.b r3 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.utils.d r5 = r3.f726b
                com.navibees.visioglobe.utils.d$d r5 = r5.f911a
                boolean r5 = r5.f922a
                if (r5 == 0) goto L_0x0063
                r3.f727c = r9
            L_0x004a:
                com.navibees.visioglobe.b r3 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.utils.d r5 = r3.f726b
                com.navibees.visioglobe.utils.d$b r5 = r5.f912b
                boolean r5 = r5.f914a
                if (r5 == 0) goto L_0x0063
                r3.f727c = r9
            L_0x0056:
                com.navibees.visioglobe.b r3 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.utils.d r5 = r3.f726b
                com.navibees.visioglobe.utils.d$c r5 = r5.f913c
                boolean r5 = r5.f920a
                if (r5 == 0) goto L_0x0063
                r3.f727c = r9
                goto L_0x0064
            L_0x0063:
                return
            L_0x0064:
                r9 = 0
                if (r0 == 0) goto L_0x0081
                boolean r3 = r0.isEmpty()
                if (r3 != 0) goto L_0x0081
                com.navibees.visioglobe.b r2 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.utils.f r2 = r2.f725a
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$b> r2 = r2.f929e
                java.lang.Object r2 = r2.get(r0)
                com.navibees.visioglobe.utils.f$b r2 = (com.navibees.visioglobe.utils.C1546f.C1548b) r2
                if (r2 == 0) goto L_0x00de
                com.navibees.visioglobe.b r2 = com.navibees.visioglobe.C1473b.this
                r2.f728d = r0
                goto L_0x0189
            L_0x0081:
                r0 = 0
                if (r1 == 0) goto L_0x00e1
                boolean r3 = r1.isEmpty()
                if (r3 != 0) goto L_0x00e1
                com.navibees.visioglobe.b r2 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.utils.f r2 = r2.f725a
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$b> r2 = r2.f929e
                java.util.Set r2 = r2.entrySet()
                java.util.Iterator r2 = r2.iterator()
            L_0x0098:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x00d6
                java.lang.Object r3 = r2.next()
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r5 = r3.getValue()
                com.navibees.visioglobe.utils.f$b r5 = (com.navibees.visioglobe.utils.C1546f.C1548b) r5
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$c> r5 = r5.f937h
                java.util.Set r5 = r5.entrySet()
                java.util.Iterator r5 = r5.iterator()
            L_0x00b4:
                boolean r6 = r5.hasNext()
                if (r6 == 0) goto L_0x00d4
                java.lang.Object r6 = r5.next()
                java.util.Map$Entry r6 = (java.util.Map.Entry) r6
                java.lang.Object r6 = r6.getKey()
                java.lang.String r6 = (java.lang.String) r6
                boolean r6 = r6.equals(r1)
                if (r6 == 0) goto L_0x00b4
                java.lang.Object r0 = r3.getValue()
                com.navibees.visioglobe.utils.f$b r0 = (com.navibees.visioglobe.utils.C1546f.C1548b) r0
                java.lang.String r0 = r0.f930a
            L_0x00d4:
                if (r0 == 0) goto L_0x0098
            L_0x00d6:
                if (r0 == 0) goto L_0x00de
                com.navibees.visioglobe.b r2 = com.navibees.visioglobe.C1473b.this
                r2.f728d = r0
                goto L_0x0189
            L_0x00de:
                r0 = 0
                goto L_0x018a
            L_0x00e1:
                com.navibees.visioglobe.b r3 = com.navibees.visioglobe.C1473b.this
                com.visioglobe.libVisioMove.VgIMapModule r3 = r3.f732h
                if (r3 == 0) goto L_0x0176
                if (r2 == 0) goto L_0x0176
                boolean r3 = r2.isEmpty()
                if (r3 != 0) goto L_0x0176
                com.visioglobe.libVisioMove.VgPOIDescriptor r3 = new com.visioglobe.libVisioMove.VgPOIDescriptor
                r3.<init>()
                com.navibees.visioglobe.b r5 = com.navibees.visioglobe.C1473b.this
                com.visioglobe.libVisioMove.VgIMapModule r5 = r5.f732h
                boolean r2 = r5.queryPOIDescriptor(r2, r3)
                if (r2 == 0) goto L_0x0189
                java.lang.String r2 = r3.getMLayerName()
                com.navibees.visioglobe.b r3 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.utils.f r3 = r3.f725a
                java.lang.String r3 = r3.f927c
                boolean r3 = r2.equals(r3)
                if (r3 == 0) goto L_0x011d
                com.navibees.visioglobe.b r2 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.g.e$a r3 = com.navibees.visioglobe.p020g.C1515e.C1516a.eVgViewModeGlobal
                r2.f727c = r3
                r2.f728d = r0
                r2.f729e = r0
                goto L_0x0189
            L_0x011d:
                com.navibees.visioglobe.b r0 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.utils.f r0 = r0.f725a
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$b> r0 = r0.f929e
                java.util.Set r0 = r0.entrySet()
                java.util.Iterator r0 = r0.iterator()
                r3 = 0
            L_0x012c:
                boolean r5 = r0.hasNext()
                if (r5 == 0) goto L_0x018b
                java.lang.Object r5 = r0.next()
                java.util.Map$Entry r5 = (java.util.Map.Entry) r5
                java.lang.Object r6 = r5.getValue()
                com.navibees.visioglobe.utils.f$b r6 = (com.navibees.visioglobe.utils.C1546f.C1548b) r6
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$c> r6 = r6.f937h
                java.util.Set r6 = r6.entrySet()
                java.util.Iterator r6 = r6.iterator()
            L_0x0148:
                boolean r7 = r6.hasNext()
                if (r7 == 0) goto L_0x0173
                java.lang.Object r7 = r6.next()
                java.util.Map$Entry r7 = (java.util.Map.Entry) r7
                java.lang.Object r7 = r7.getValue()
                com.navibees.visioglobe.utils.f$c r7 = (com.navibees.visioglobe.utils.C1546f.C1551c) r7
                java.lang.String r7 = r7.f939b
                boolean r7 = r7.equals(r2)
                if (r7 == 0) goto L_0x0148
                com.navibees.visioglobe.b r3 = com.navibees.visioglobe.C1473b.this
                java.lang.Object r5 = r5.getValue()
                com.navibees.visioglobe.utils.f$b r5 = (com.navibees.visioglobe.utils.C1546f.C1548b) r5
                java.lang.String r5 = r5.f930a
                r3.f728d = r5
                com.navibees.visioglobe.b r3 = com.navibees.visioglobe.C1473b.this
                r3.f729e = r2
                r3 = 1
            L_0x0173:
                if (r3 == 0) goto L_0x012c
                goto L_0x018b
            L_0x0176:
                com.navibees.visioglobe.b r0 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.g.e$a r2 = r0.f727c
                com.navibees.visioglobe.g.e$a r3 = com.navibees.visioglobe.p020g.C1515e.C1516a.eVgViewModeBuilding
                if (r2 != r3) goto L_0x0189
                java.lang.String r0 = r0.f728d
                if (r0 == 0) goto L_0x0188
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto L_0x0189
            L_0x0188:
                return
            L_0x0189:
                r0 = 1
            L_0x018a:
                r3 = r0
            L_0x018b:
                if (r1 == 0) goto L_0x01b2
                boolean r0 = r1.isEmpty()
                if (r0 != 0) goto L_0x01b2
                com.navibees.visioglobe.b r0 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.utils.f r2 = r0.f725a
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$b> r2 = r2.f929e
                java.lang.String r0 = r0.f728d
                java.lang.Object r0 = r2.get(r0)
                com.navibees.visioglobe.utils.f$b r0 = (com.navibees.visioglobe.utils.C1546f.C1548b) r0
                if (r0 == 0) goto L_0x01b0
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$c> r0 = r0.f937h
                boolean r0 = r0.containsKey(r1)
                if (r0 == 0) goto L_0x01b0
                com.navibees.visioglobe.b r0 = com.navibees.visioglobe.C1473b.this
                r0.f729e = r1
                goto L_0x01d4
            L_0x01b0:
                r3 = 0
                goto L_0x01d4
            L_0x01b2:
                com.navibees.visioglobe.b r0 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.utils.f r1 = r0.f725a
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$b> r1 = r1.f929e
                java.lang.String r0 = r0.f728d
                java.lang.Object r0 = r1.get(r0)
                com.navibees.visioglobe.utils.f$b r0 = (com.navibees.visioglobe.utils.C1546f.C1548b) r0
                if (r0 == 0) goto L_0x01d4
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$c> r1 = r0.f937h
                com.navibees.visioglobe.b r2 = com.navibees.visioglobe.C1473b.this
                java.lang.String r2 = r2.f729e
                boolean r1 = r1.containsKey(r2)
                if (r1 != 0) goto L_0x01d4
                com.navibees.visioglobe.b r1 = com.navibees.visioglobe.C1473b.this
                java.lang.String r0 = r0.f935f
                r1.f729e = r0
            L_0x01d4:
                com.navibees.visioglobe.b r0 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.utils.f r1 = r0.f725a
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$b> r1 = r1.f929e
                java.lang.String r0 = r0.f728d
                java.lang.Object r0 = r1.get(r0)
                com.navibees.visioglobe.utils.f$b r0 = (com.navibees.visioglobe.utils.C1546f.C1548b) r0
                com.navibees.visioglobe.b r1 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.g.e$a r1 = r1.f727c
                com.navibees.visioglobe.g.e$a r2 = com.navibees.visioglobe.p020g.C1515e.C1516a.eVgViewModeBuilding
                if (r1 != r2) goto L_0x0206
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$c> r1 = r0.f937h
                int r1 = r1.size()
                if (r1 != r4) goto L_0x0206
                com.navibees.visioglobe.b r1 = com.navibees.visioglobe.C1473b.this
                com.navibees.visioglobe.g.e$a r2 = com.navibees.visioglobe.p020g.C1515e.C1516a.eVgViewModeFloor
                r1.f727c = r2
                java.util.List r0 = r0.mo28700a()
                java.lang.Object r9 = r0.get(r9)
                com.navibees.visioglobe.utils.f$c r9 = (com.navibees.visioglobe.utils.C1546f.C1551c) r9
                java.lang.String r9 = r9.f938a
                r1.f729e = r9
            L_0x0206:
                if (r3 == 0) goto L_0x020d
                com.navibees.visioglobe.b r9 = com.navibees.visioglobe.C1473b.this
                r9.mo28553a(r10)
            L_0x020d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.navibees.visioglobe.C1473b.C1474a.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    /* renamed from: com.navibees.visioglobe.b$b */
    /* compiled from: VgMyExploreSolver */
    class C1475b extends BroadcastReceiver {
        C1475b() {
        }

        public void onReceive(Context context, Intent intent) {
            C1473b.this.f726b = (C1536d) intent.getParcelableExtra("parameters");
            C1473b.this.f725a = (C1546f) intent.getParcelableExtra("venueLayout");
            String str = C1473b.this.f725a.f928d;
            if (str == null || str.isEmpty()) {
                C1473b bVar = C1473b.this;
                bVar.f728d = (String) ((Entry) bVar.f725a.f929e.entrySet().iterator().next()).getKey();
            } else {
                C1473b bVar2 = C1473b.this;
                bVar2.f728d = bVar2.f725a.f928d;
            }
            C1473b bVar3 = C1473b.this;
            C1536d dVar = bVar3.f726b;
            if (dVar.f911a.f922a) {
                bVar3.f727c = C1516a.eVgViewModeGlobal;
            } else if (dVar.f912b.f914a) {
                bVar3.f727c = C1516a.eVgViewModeBuilding;
            } else {
                bVar3.f727c = C1516a.eVgViewModeFloor;
            }
            C1473b.this.mo28553a(intent);
        }
    }

    /* renamed from: com.navibees.visioglobe.b$c */
    /* compiled from: VgMyExploreSolver */
    static /* synthetic */ class C1476c {

        /* renamed from: a */
        static final /* synthetic */ int[] f737a = new int[C1516a.values().length];

        static {
            try {
                f737a[C1516a.eVgViewModeGlobal.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f737a[C1516a.eVgViewModeBuilding.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f737a[C1516a.eVgViewModeFloor.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public C1473b(Context context) {
        this.f731g = LocalBroadcastManager.getInstance(context);
        this.f731g.registerReceiver(this.f734j, new IntentFilter("parametersLoaded"));
        this.f731g.registerReceiver(this.f733i, new IntentFilter("exploreRequest"));
    }

    /* renamed from: a */
    public void mo28552a() {
        this.f731g.unregisterReceiver(this.f734j);
        this.f731g.unregisterReceiver(this.f733i);
        this.f732h = null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28553a(Intent intent) {
        Intent intent2 = new Intent("explore");
        intent2.putExtra("view", this.f727c);
        intent2.putExtra("focusedBuilding", this.f728d);
        intent2.putExtra("focusedFloor", this.f729e);
        intent2.putExtra("focusAnimation", this.f730f);
        String str = "focusedLatitude";
        if (intent.hasExtra(str)) {
            String str2 = "focusedLongitude";
            if (intent.hasExtra(str2)) {
                String str3 = "focusedAltitude";
                if (intent.hasExtra(str3)) {
                    String str4 = "focusedHeading";
                    if (intent.hasExtra(str4)) {
                        String str5 = "focusedPitch";
                        if (intent.hasExtra(str5)) {
                            intent2.putExtra(str, intent.getFloatExtra(str, 0.0f));
                            intent2.putExtra(str2, intent.getFloatExtra(str2, 0.0f));
                            intent2.putExtra(str3, intent.getFloatExtra(str3, 0.0f));
                            intent2.putExtra(str4, intent.getFloatExtra(str4, 0.0f));
                            intent2.putExtra(str5, intent.getFloatExtra(str5, 0.0f));
                            this.f731g.sendBroadcast(intent2);
                        }
                    }
                }
            }
        }
        String str6 = "focusedPlace";
        if (intent.hasExtra(str6)) {
            intent2.putExtra(str6, intent.getStringExtra(str6));
        }
        this.f731g.sendBroadcast(intent2);
    }

    /* renamed from: a */
    public void mo28554a(VgIMapModule vgIMapModule) {
        this.f732h = vgIMapModule;
    }
}
