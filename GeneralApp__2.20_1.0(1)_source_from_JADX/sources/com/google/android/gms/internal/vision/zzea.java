package com.google.android.gms.internal.vision;

import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzea {

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zza extends zzgs<zza, C1744zza> implements zzie {
        private static volatile zzil<zza> zzbd;
        /* access modifiers changed from: private */
        public static final zza zzmp;
        private int zzbf;
        private String zzmn;
        private String zzmo;

        /* renamed from: com.google.android.gms.internal.vision.zzea$zza$zza reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class C1744zza extends com.google.android.gms.internal.vision.zzgs.zza<zza, C1744zza> implements zzie {
            private C1744zza() {
                super(zza.zzmp);
            }

            public final C1744zza zzl(String str) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zza) this.zzwh).zzn(str);
                return this;
            }

            public final C1744zza zzm(String str) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zza) this.zzwh).zzo(str);
                return this;
            }

            /* synthetic */ C1744zza(zzdz zzdz) {
                this();
            }
        }

        private zza() {
            String str = "";
            this.zzmn = str;
            this.zzmo = str;
        }

        /* access modifiers changed from: private */
        public final void zzn(String str) {
            str.getClass();
            this.zzbf |= 1;
            this.zzmn = str;
        }

        /* access modifiers changed from: private */
        public final void zzo(String str) {
            str.getClass();
            this.zzbf |= 2;
            this.zzmo = str;
        }

        public static C1744zza zzcj() {
            return (C1744zza) zzmp.zzge();
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zza>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zza>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zza>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 42
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0057;
                    case 2: goto L_0x0051;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zza> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zza> r3 = com.google.android.gms.internal.vision.zzea.zza.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zza> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zza r4 = zzmp     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zza r2 = zzmp
                return r2
            L_0x0033:
                r2 = 3
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zzmn"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzmo"
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zza r3 = zzmp
                java.lang.String r4 = "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x0051:
                com.google.android.gms.internal.vision.zzea$zza$zza r2 = new com.google.android.gms.internal.vision.zzea$zza$zza
                r2.<init>(r3)
                return r2
            L_0x0057:
                com.google.android.gms.internal.vision.zzea$zza r2 = new com.google.android.gms.internal.vision.zzea$zza
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zza.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zza zza = new zza();
            zzmp = zza;
            zzgs.zza(zza.class, zza);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzb extends zzgs<zzb, zza> implements zzie {
        private static volatile zzil<zzb> zzbd;
        private static final zzha<Integer, zzeo> zzmr = new zzeb();
        /* access modifiers changed from: private */
        public static final zzb zzms;
        private zzgx zzmq = zzgg();

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zza extends com.google.android.gms.internal.vision.zzgs.zza<zzb, zza> implements zzie {
            private zza() {
                super(zzb.zzms);
            }

            /* synthetic */ zza(zzdz zzdz) {
                this();
            }
        }

        private zzb() {
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzb>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzb>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzb>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 40
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0052;
                    case 2: goto L_0x004c;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzb> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzb> r3 = com.google.android.gms.internal.vision.zzea.zzb.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzb> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzb r4 = zzms     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzb r2 = zzms
                return r2
            L_0x0033:
                r2 = 2
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzmq"
                r2[r3] = r0
                com.google.android.gms.internal.vision.zzgy r3 = com.google.android.gms.internal.vision.zzeo.zzah()
                r2[r4] = r3
                com.google.android.gms.internal.vision.zzea$zzb r3 = zzms
                java.lang.String r4 = "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001e"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x004c:
                com.google.android.gms.internal.vision.zzea$zzb$zza r2 = new com.google.android.gms.internal.vision.zzea$zzb$zza
                r2.<init>(r3)
                return r2
            L_0x0052:
                com.google.android.gms.internal.vision.zzea$zzb r2 = new com.google.android.gms.internal.vision.zzea$zzb
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzb.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.vision.zzeb, com.google.android.gms.internal.vision.zzha<java.lang.Integer, com.google.android.gms.internal.vision.zzeo>] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.vision.zzeb, com.google.android.gms.internal.vision.zzha<java.lang.Integer, com.google.android.gms.internal.vision.zzeo>]
          assigns: [com.google.android.gms.internal.vision.zzeb]
          uses: [com.google.android.gms.internal.vision.zzha<java.lang.Integer, com.google.android.gms.internal.vision.zzeo>]
          mth insns count: 7
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 1 */
        static {
            /*
                com.google.android.gms.internal.vision.zzeb r0 = new com.google.android.gms.internal.vision.zzeb
                r0.<init>()
                zzmr = r0
                com.google.android.gms.internal.vision.zzea$zzb r0 = new com.google.android.gms.internal.vision.zzea$zzb
                r0.<init>()
                zzms = r0
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzb> r1 = com.google.android.gms.internal.vision.zzea.zzb.class
                com.google.android.gms.internal.vision.zzgs.zza(r1, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzb.<clinit>():void");
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzc extends zzgs<zzc, zza> implements zzie {
        private static volatile zzil<zzc> zzbd;
        /* access modifiers changed from: private */
        public static final zzc zzmw;
        private int zzbf;
        private int zzmt;
        private int zzmu;
        private String zzmv = "";

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zza extends com.google.android.gms.internal.vision.zzgs.zza<zzc, zza> implements zzie {
            private zza() {
                super(zzc.zzmw);
            }

            /* synthetic */ zza(zzdz zzdz) {
                this();
            }
        }

        private zzc() {
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzc>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzc>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzc>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 48
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x006b;
                    case 2: goto L_0x0065;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzc> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzc> r3 = com.google.android.gms.internal.vision.zzea.zzc.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzc> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzc r4 = zzmw     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzc r2 = zzmw
                return r2
            L_0x0033:
                r2 = 6
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zzmt"
                r2[r4] = r3
                r3 = 2
                com.google.android.gms.internal.vision.zzgy r4 = com.google.android.gms.internal.vision.zzeo.zzah()
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzmu"
                r2[r3] = r4
                r3 = 4
                com.google.android.gms.internal.vision.zzgy r4 = com.google.android.gms.internal.vision.zzes.zzah()
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zzmv"
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zzc r3 = zzmw
                java.lang.String r4 = "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဈ\u0002"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x0065:
                com.google.android.gms.internal.vision.zzea$zzc$zza r2 = new com.google.android.gms.internal.vision.zzea$zzc$zza
                r2.<init>(r3)
                return r2
            L_0x006b:
                com.google.android.gms.internal.vision.zzea$zzc r2 = new com.google.android.gms.internal.vision.zzea$zzc
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzc.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzc zzc = new zzc();
            zzmw = zzc;
            zzgs.zza(zzc.class, zzc);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzd extends zzgs<zzd, zza> implements zzie {
        private static volatile zzil<zzd> zzbd;
        /* access modifiers changed from: private */
        public static final zzd zzmy;
        private zzgz<zzm> zzmx = zzgh();

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zza extends com.google.android.gms.internal.vision.zzgs.zza<zzd, zza> implements zzie {
            private zza() {
                super(zzd.zzmy);
            }

            public final zza zzb(zzm zzm) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzd) this.zzwh).zza(zzm);
                return this;
            }

            /* synthetic */ zza(zzdz zzdz) {
                this();
            }
        }

        private zzd() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzm zzm) {
            zzm.getClass();
            if (!this.zzmx.zzdo()) {
                this.zzmx = zzgs.zza(this.zzmx);
            }
            this.zzmx.add(zzm);
        }

        public static zza zzcn() {
            return (zza) zzmy.zzge();
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzd>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzd>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzd>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 40
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0050;
                    case 2: goto L_0x004a;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzd> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzd> r3 = com.google.android.gms.internal.vision.zzea.zzd.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzd> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzd r4 = zzmy     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzd r2 = zzmy
                return r2
            L_0x0033:
                r2 = 2
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzmx"
                r2[r3] = r0
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzm> r3 = com.google.android.gms.internal.vision.zzea.zzm.class
                r2[r4] = r3
                com.google.android.gms.internal.vision.zzea$zzd r3 = zzmy
                java.lang.String r4 = "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x004a:
                com.google.android.gms.internal.vision.zzea$zzd$zza r2 = new com.google.android.gms.internal.vision.zzea$zzd$zza
                r2.<init>(r3)
                return r2
            L_0x0050:
                com.google.android.gms.internal.vision.zzea$zzd r2 = new com.google.android.gms.internal.vision.zzea$zzd
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzd.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzd zzd = new zzd();
            zzmy = zzd;
            zzgs.zza(zzd.class, zzd);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zze extends zzgs<zze, zzb> implements zzie {
        private static volatile zzil<zze> zzbd;
        /* access modifiers changed from: private */
        public static final zze zznh;
        private int zzbf;
        private String zzmz;
        private boolean zzna;
        private int zznb;
        private long zznc;
        private long zznd;
        private long zzne;
        private String zznf;
        private boolean zzng;

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public enum zza implements zzgw {
            REASON_UNKNOWN(0),
            REASON_MISSING(1),
            REASON_UPGRADE(2),
            REASON_INVALID(3);
            
            private static final zzgv<zza> zzhc = null;
            private final int value;

            public final int zzag() {
                return this.value;
            }

            public static zza zzt(int i) {
                if (i == 0) {
                    return REASON_UNKNOWN;
                }
                if (i == 1) {
                    return REASON_MISSING;
                }
                if (i == 2) {
                    return REASON_UPGRADE;
                }
                if (i != 3) {
                    return null;
                }
                return REASON_INVALID;
            }

            public static zzgy zzah() {
                return zzec.zzhf;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName());
                sb.append('@');
                sb.append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=");
                sb.append(this.value);
                sb.append(" name=");
                sb.append(name());
                sb.append(Typography.greater);
                return sb.toString();
            }

            private zza(int i) {
                this.value = i;
            }

            static {
                zzhc = new zzed();
            }
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zzb extends com.google.android.gms.internal.vision.zzgs.zza<zze, zzb> implements zzie {
            private zzb() {
                super(zze.zznh);
            }

            /* synthetic */ zzb(zzdz zzdz) {
                this();
            }
        }

        private zze() {
            String str = "";
            this.zzmz = str;
            this.zznf = str;
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zze>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zze>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zze>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 56
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0085;
                    case 2: goto L_0x007f;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zze> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zze> r3 = com.google.android.gms.internal.vision.zzea.zze.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zze> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zze r4 = zznh     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zze r2 = zznh
                return r2
            L_0x0033:
                r2 = 10
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zzmz"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzna"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zznb"
                r2[r3] = r4
                r3 = 4
                com.google.android.gms.internal.vision.zzgy r4 = com.google.android.gms.internal.vision.zzea.zze.zza.zzah()
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zznc"
                r2[r3] = r4
                r3 = 6
                java.lang.String r4 = "zznd"
                r2[r3] = r4
                r3 = 7
                java.lang.String r4 = "zzne"
                r2[r3] = r4
                r3 = 8
                java.lang.String r4 = "zznf"
                r2[r3] = r4
                r3 = 9
                java.lang.String r4 = "zzng"
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zze r3 = zznh
                java.lang.String r4 = "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဌ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဈ\u0006\bဇ\u0007"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x007f:
                com.google.android.gms.internal.vision.zzea$zze$zzb r2 = new com.google.android.gms.internal.vision.zzea$zze$zzb
                r2.<init>(r3)
                return r2
            L_0x0085:
                com.google.android.gms.internal.vision.zzea$zze r2 = new com.google.android.gms.internal.vision.zzea$zze
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zze.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zze zze = new zze();
            zznh = zze;
            zzgs.zza(zze.class, zze);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzf extends zzgs<zzf, zza> implements zzie {
        private static volatile zzil<zzf> zzbd;
        /* access modifiers changed from: private */
        public static final zzf zznv;
        private int zzbf;
        private String zznn;
        private String zzno;
        private zzgz<String> zznp = zzgs.zzgh();
        private int zznq;
        private String zznr;
        private long zzns;
        private long zznt;
        private zzgz<zzn> zznu;

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zza extends com.google.android.gms.internal.vision.zzgs.zza<zzf, zza> implements zzie {
            private zza() {
                super(zzf.zznv);
            }

            public final zza zzp(String str) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzf) this.zzwh).setName(str);
                return this;
            }

            public final zza zzq(String str) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzf) this.zzwh).zzr(str);
                return this;
            }

            public final zza zzd(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzf) this.zzwh).zzf(j);
                return this;
            }

            public final zza zze(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzf) this.zzwh).zzg(j);
                return this;
            }

            public final zza zzc(Iterable<? extends zzn> iterable) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzf) this.zzwh).zzd(iterable);
                return this;
            }

            /* synthetic */ zza(zzdz zzdz) {
                this();
            }
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public enum zzb implements zzgw {
            RESULT_UNKNOWN(0),
            RESULT_SUCCESS(1),
            RESULT_FAIL(2),
            RESULT_SKIPPED(3);
            
            private static final zzgv<zzb> zzhc = null;
            private final int value;

            public final int zzag() {
                return this.value;
            }

            public static zzb zzu(int i) {
                if (i == 0) {
                    return RESULT_UNKNOWN;
                }
                if (i == 1) {
                    return RESULT_SUCCESS;
                }
                if (i == 2) {
                    return RESULT_FAIL;
                }
                if (i != 3) {
                    return null;
                }
                return RESULT_SKIPPED;
            }

            public static zzgy zzah() {
                return zzef.zzhf;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName());
                sb.append('@');
                sb.append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=");
                sb.append(this.value);
                sb.append(" name=");
                sb.append(name());
                sb.append(Typography.greater);
                return sb.toString();
            }

            private zzb(int i) {
                this.value = i;
            }

            static {
                zzhc = new zzee();
            }
        }

        private zzf() {
            String str = "";
            this.zznn = str;
            this.zzno = str;
            this.zznr = str;
            this.zznu = zzgh();
        }

        /* access modifiers changed from: private */
        public final void setName(String str) {
            str.getClass();
            this.zzbf |= 1;
            this.zznn = str;
        }

        /* access modifiers changed from: private */
        public final void zzr(String str) {
            str.getClass();
            this.zzbf |= 8;
            this.zznr = str;
        }

        /* access modifiers changed from: private */
        public final void zzf(long j) {
            this.zzbf |= 16;
            this.zzns = j;
        }

        /* access modifiers changed from: private */
        public final void zzg(long j) {
            this.zzbf |= 32;
            this.zznt = j;
        }

        /* access modifiers changed from: private */
        public final void zzd(Iterable<? extends zzn> iterable) {
            if (!this.zznu.zzdo()) {
                this.zznu = zzgs.zza(this.zznu);
            }
            zzet.zza(iterable, this.zznu);
        }

        public static zza zzcq() {
            return (zza) zznv.zzge();
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzf>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzf>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzf>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 58
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x008b;
                    case 2: goto L_0x0085;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzf> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzf> r3 = com.google.android.gms.internal.vision.zzea.zzf.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzf> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzf r4 = zznv     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzf r2 = zznv
                return r2
            L_0x0033:
                r2 = 11
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zznn"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzno"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zznp"
                r2[r3] = r4
                r3 = 4
                java.lang.String r4 = "zznq"
                r2[r3] = r4
                r3 = 5
                com.google.android.gms.internal.vision.zzgy r4 = com.google.android.gms.internal.vision.zzea.zzf.zzb.zzah()
                r2[r3] = r4
                r3 = 6
                java.lang.String r4 = "zznr"
                r2[r3] = r4
                r3 = 7
                java.lang.String r4 = "zzns"
                r2[r3] = r4
                r3 = 8
                java.lang.String r4 = "zznt"
                r2[r3] = r4
                r3 = 9
                java.lang.String r4 = "zznu"
                r2[r3] = r4
                r3 = 10
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzn> r4 = com.google.android.gms.internal.vision.zzea.zzn.class
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zzf r3 = zznv
                java.lang.String r4 = "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0002\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003\u001a\u0004ဌ\u0002\u0005ဈ\u0003\u0006ဂ\u0004\u0007ဂ\u0005\b\u001b"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x0085:
                com.google.android.gms.internal.vision.zzea$zzf$zza r2 = new com.google.android.gms.internal.vision.zzea$zzf$zza
                r2.<init>(r3)
                return r2
            L_0x008b:
                com.google.android.gms.internal.vision.zzea$zzf r2 = new com.google.android.gms.internal.vision.zzea$zzf
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzf.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzf zzf = new zzf();
            zznv = zzf;
            zzgs.zza(zzf.class, zzf);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzg extends zzgs<zzg, zzb> implements zzie {
        private static volatile zzil<zzg> zzbd;
        /* access modifiers changed from: private */
        public static final zzg zzof;
        private int zzbf;
        private float zzka;
        private boolean zzke;
        private int zzob;
        private int zzoc;
        private int zzod;
        private boolean zzoe;

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public enum zza implements zzgw {
            CLASSIFICATION_UNKNOWN(0),
            CLASSIFICATION_NONE(1),
            CLASSIFICATION_ALL(2);
            
            private static final zzgv<zza> zzhc = null;
            private final int value;

            public final int zzag() {
                return this.value;
            }

            public static zza zzv(int i) {
                if (i == 0) {
                    return CLASSIFICATION_UNKNOWN;
                }
                if (i == 1) {
                    return CLASSIFICATION_NONE;
                }
                if (i != 2) {
                    return null;
                }
                return CLASSIFICATION_ALL;
            }

            public static zzgy zzah() {
                return zzeg.zzhf;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName());
                sb.append('@');
                sb.append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=");
                sb.append(this.value);
                sb.append(" name=");
                sb.append(name());
                sb.append(Typography.greater);
                return sb.toString();
            }

            private zza(int i) {
                this.value = i;
            }

            static {
                zzhc = new zzeh();
            }
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zzb extends com.google.android.gms.internal.vision.zzgs.zza<zzg, zzb> implements zzie {
            private zzb() {
                super(zzg.zzof);
            }

            public final zzb zzb(zzd zzd) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzg) this.zzwh).zza(zzd);
                return this;
            }

            public final zzb zzb(zzc zzc) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzg) this.zzwh).zza(zzc);
                return this;
            }

            public final zzb zzb(zza zza) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzg) this.zzwh).zza(zza);
                return this;
            }

            public final zzb zzh(boolean z) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzg) this.zzwh).zza(z);
                return this;
            }

            public final zzb zzi(boolean z) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzg) this.zzwh).zzg(z);
                return this;
            }

            public final zzb zzf(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzg) this.zzwh).zzd(f);
                return this;
            }

            /* synthetic */ zzb(zzdz zzdz) {
                this();
            }
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public enum zzc implements zzgw {
            LANDMARK_UNKNOWN(0),
            LANDMARK_NONE(1),
            LANDMARK_ALL(2),
            LANDMARK_CONTOUR(3);
            
            private static final zzgv<zzc> zzhc = null;
            private final int value;

            public final int zzag() {
                return this.value;
            }

            public static zzc zzw(int i) {
                if (i == 0) {
                    return LANDMARK_UNKNOWN;
                }
                if (i == 1) {
                    return LANDMARK_NONE;
                }
                if (i == 2) {
                    return LANDMARK_ALL;
                }
                if (i != 3) {
                    return null;
                }
                return LANDMARK_CONTOUR;
            }

            public static zzgy zzah() {
                return zzej.zzhf;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName());
                sb.append('@');
                sb.append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=");
                sb.append(this.value);
                sb.append(" name=");
                sb.append(name());
                sb.append(Typography.greater);
                return sb.toString();
            }

            private zzc(int i) {
                this.value = i;
            }

            static {
                zzhc = new zzei();
            }
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public enum zzd implements zzgw {
            MODE_UNKNOWN(0),
            MODE_ACCURATE(1),
            MODE_FAST(2),
            MODE_SELFIE(3);
            
            private static final zzgv<zzd> zzhc = null;
            private final int value;

            public final int zzag() {
                return this.value;
            }

            public static zzd zzx(int i) {
                if (i == 0) {
                    return MODE_UNKNOWN;
                }
                if (i == 1) {
                    return MODE_ACCURATE;
                }
                if (i == 2) {
                    return MODE_FAST;
                }
                if (i != 3) {
                    return null;
                }
                return MODE_SELFIE;
            }

            public static zzgy zzah() {
                return zzek.zzhf;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName());
                sb.append('@');
                sb.append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=");
                sb.append(this.value);
                sb.append(" name=");
                sb.append(name());
                sb.append(Typography.greater);
                return sb.toString();
            }

            private zzd(int i) {
                this.value = i;
            }

            static {
                zzhc = new zzel();
            }
        }

        private zzg() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzd zzd2) {
            this.zzob = zzd2.zzag();
            this.zzbf |= 1;
        }

        /* access modifiers changed from: private */
        public final void zza(zzc zzc2) {
            this.zzoc = zzc2.zzag();
            this.zzbf |= 2;
        }

        /* access modifiers changed from: private */
        public final void zza(zza zza2) {
            this.zzod = zza2.zzag();
            this.zzbf |= 4;
        }

        /* access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzbf |= 8;
            this.zzke = z;
        }

        /* access modifiers changed from: private */
        public final void zzg(boolean z) {
            this.zzbf |= 16;
            this.zzoe = z;
        }

        /* access modifiers changed from: private */
        public final void zzd(float f) {
            this.zzbf |= 32;
            this.zzka = f;
        }

        public static zzb zzcs() {
            return (zzb) zzof.zzge();
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzg>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzg>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzg>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 56
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0087;
                    case 2: goto L_0x0081;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzg> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzg> r3 = com.google.android.gms.internal.vision.zzea.zzg.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzg> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzg r4 = zzof     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzg r2 = zzof
                return r2
            L_0x0033:
                r2 = 10
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zzob"
                r2[r4] = r3
                r3 = 2
                com.google.android.gms.internal.vision.zzgy r4 = com.google.android.gms.internal.vision.zzea.zzg.zzd.zzah()
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzoc"
                r2[r3] = r4
                r3 = 4
                com.google.android.gms.internal.vision.zzgy r4 = com.google.android.gms.internal.vision.zzea.zzg.zzc.zzah()
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zzod"
                r2[r3] = r4
                r3 = 6
                com.google.android.gms.internal.vision.zzgy r4 = com.google.android.gms.internal.vision.zzea.zzg.zza.zzah()
                r2[r3] = r4
                r3 = 7
                java.lang.String r4 = "zzke"
                r2[r3] = r4
                r3 = 8
                java.lang.String r4 = "zzoe"
                r2[r3] = r4
                r3 = 9
                java.lang.String r4 = "zzka"
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zzg r3 = zzof
                java.lang.String r4 = "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဌ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ခ\u0005"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x0081:
                com.google.android.gms.internal.vision.zzea$zzg$zzb r2 = new com.google.android.gms.internal.vision.zzea$zzg$zzb
                r2.<init>(r3)
                return r2
            L_0x0087:
                com.google.android.gms.internal.vision.zzea$zzg r2 = new com.google.android.gms.internal.vision.zzea$zzg
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzg.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzg zzg = new zzg();
            zzof = zzg;
            zzgs.zza(zzg.class, zzg);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzh extends zzgs<zzh, zza> implements zzie {
        private static volatile zzil<zzh> zzbd;
        /* access modifiers changed from: private */
        public static final zzh zzpa;
        private int zzbf;
        private float zzou;
        private float zzov;
        private float zzow;
        private float zzox;
        private float zzoy;
        private float zzoz;

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zza extends com.google.android.gms.internal.vision.zzgs.zza<zzh, zza> implements zzie {
            private zza() {
                super(zzh.zzpa);
            }

            public final zza zzg(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzh) this.zzwh).zzm(f);
                return this;
            }

            public final zza zzh(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzh) this.zzwh).zzn(f);
                return this;
            }

            public final zza zzi(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzh) this.zzwh).zzo(f);
                return this;
            }

            public final zza zzj(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzh) this.zzwh).zzp(f);
                return this;
            }

            public final zza zzk(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzh) this.zzwh).zzq(f);
                return this;
            }

            public final zza zzl(float f) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzh) this.zzwh).zzr(f);
                return this;
            }

            /* synthetic */ zza(zzdz zzdz) {
                this();
            }
        }

        private zzh() {
        }

        /* access modifiers changed from: private */
        public final void zzm(float f) {
            this.zzbf |= 1;
            this.zzou = f;
        }

        /* access modifiers changed from: private */
        public final void zzn(float f) {
            this.zzbf |= 2;
            this.zzov = f;
        }

        /* access modifiers changed from: private */
        public final void zzo(float f) {
            this.zzbf |= 4;
            this.zzow = f;
        }

        /* access modifiers changed from: private */
        public final void zzp(float f) {
            this.zzbf |= 8;
            this.zzox = f;
        }

        /* access modifiers changed from: private */
        public final void zzq(float f) {
            this.zzbf |= 16;
            this.zzoy = f;
        }

        /* access modifiers changed from: private */
        public final void zzr(float f) {
            this.zzbf |= 32;
            this.zzoz = f;
        }

        public static zza zzcu() {
            return (zza) zzpa.zzge();
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzh>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzh>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzh>, com.google.android.gms.internal.vision.zzgs$zzc] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 50
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x006f;
                    case 2: goto L_0x0069;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzh> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzh> r3 = com.google.android.gms.internal.vision.zzea.zzh.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzh> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzh r4 = zzpa     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzh r2 = zzpa
                return r2
            L_0x0033:
                r2 = 7
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zzou"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzov"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzow"
                r2[r3] = r4
                r3 = 4
                java.lang.String r4 = "zzox"
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zzoy"
                r2[r3] = r4
                r3 = 6
                java.lang.String r4 = "zzoz"
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zzh r3 = zzpa
                java.lang.String r4 = "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ခ\u0005"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x0069:
                com.google.android.gms.internal.vision.zzea$zzh$zza r2 = new com.google.android.gms.internal.vision.zzea$zzh$zza
                r2.<init>(r3)
                return r2
            L_0x006f:
                com.google.android.gms.internal.vision.zzea$zzh r2 = new com.google.android.gms.internal.vision.zzea$zzh
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzh.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzh zzh = new zzh();
            zzpa = zzh;
            zzgs.zza(zzh.class, zzh);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzi extends zzgs<zzi, zza> implements zzie {
        private static volatile zzil<zzi> zzbd;
        /* access modifiers changed from: private */
        public static final zzi zzpe;
        private int zzbf;
        private zzj zzpb;
        private zzl zzpc;
        private zzgz<zzf> zzpd = zzgh();

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zza extends com.google.android.gms.internal.vision.zzgs.zza<zzi, zza> implements zzie {
            private zza() {
                super(zzi.zzpe);
            }

            public final zza zza(zzj zzj) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzi) this.zzwh).zzb(zzj);
                return this;
            }

            public final zza zza(zza zza) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzi) this.zzwh).zza((zzf) ((zzgs) zza.zzgc()));
                return this;
            }

            public final zza zze(Iterable<? extends zzf> iterable) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzi) this.zzwh).zzf(iterable);
                return this;
            }

            /* synthetic */ zza(zzdz zzdz) {
                this();
            }
        }

        private zzi() {
        }

        /* access modifiers changed from: private */
        public final void zzb(zzj zzj) {
            zzj.getClass();
            this.zzpb = zzj;
            this.zzbf |= 1;
        }

        private final void zzcw() {
            if (!this.zzpd.zzdo()) {
                this.zzpd = zzgs.zza(this.zzpd);
            }
        }

        /* access modifiers changed from: private */
        public final void zza(zzf zzf) {
            zzf.getClass();
            zzcw();
            this.zzpd.add(zzf);
        }

        /* access modifiers changed from: private */
        public final void zzf(Iterable<? extends zzf> iterable) {
            zzcw();
            zzet.zza(iterable, this.zzpd);
        }

        public static zza zzcx() {
            return (zza) zzpe.zzge();
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzi>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzi>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzi>, com.google.android.gms.internal.vision.zzgs$zzc] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 46
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0062;
                    case 2: goto L_0x005c;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzi> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzi> r3 = com.google.android.gms.internal.vision.zzea.zzi.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzi> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzi r4 = zzpe     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzi r2 = zzpe
                return r2
            L_0x0033:
                r2 = 5
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zzpb"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzpc"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzpd"
                r2[r3] = r4
                r3 = 4
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzf> r4 = com.google.android.gms.internal.vision.zzea.zzf.class
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zzi r3 = zzpe
                java.lang.String r4 = "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\u001b"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x005c:
                com.google.android.gms.internal.vision.zzea$zzi$zza r2 = new com.google.android.gms.internal.vision.zzea$zzi$zza
                r2.<init>(r3)
                return r2
            L_0x0062:
                com.google.android.gms.internal.vision.zzea$zzi r2 = new com.google.android.gms.internal.vision.zzea$zzi
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzi.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzi zzi = new zzi();
            zzpe = zzi;
            zzgs.zza(zzi.class, zzi);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzj extends zzgs<zzj, zza> implements zzie {
        private static volatile zzil<zzj> zzbd;
        /* access modifiers changed from: private */
        public static final zzj zzpj;
        private int zzbf;
        private int zzmt;
        private long zzpf;
        private long zzpg;
        private long zzph;
        private long zzpi;

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zza extends com.google.android.gms.internal.vision.zzgs.zza<zzj, zza> implements zzie {
            private zza() {
                super(zzj.zzpj);
            }

            public final zza zzh(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzj) this.zzwh).zzl(j);
                return this;
            }

            public final zza zzi(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzj) this.zzwh).zzm(j);
                return this;
            }

            public final zza zzj(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzj) this.zzwh).zzn(j);
                return this;
            }

            public final zza zzk(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzj) this.zzwh).zzo(j);
                return this;
            }

            /* synthetic */ zza(zzdz zzdz) {
                this();
            }
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public enum zzb implements zzgw {
            FORMAT_UNKNOWN(0),
            FORMAT_LUMINANCE(1),
            FORMAT_RGB8(2),
            FORMAT_MONOCHROME(3);
            
            private static final zzgv<zzb> zzhc = null;
            private final int value;

            public final int zzag() {
                return this.value;
            }

            public static zzb zzy(int i) {
                if (i == 0) {
                    return FORMAT_UNKNOWN;
                }
                if (i == 1) {
                    return FORMAT_LUMINANCE;
                }
                if (i == 2) {
                    return FORMAT_RGB8;
                }
                if (i != 3) {
                    return null;
                }
                return FORMAT_MONOCHROME;
            }

            public static zzgy zzah() {
                return zzen.zzhf;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName());
                sb.append('@');
                sb.append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=");
                sb.append(this.value);
                sb.append(" name=");
                sb.append(name());
                sb.append(Typography.greater);
                return sb.toString();
            }

            private zzb(int i) {
                this.value = i;
            }

            static {
                zzhc = new zzem();
            }
        }

        private zzj() {
        }

        /* access modifiers changed from: private */
        public final void zzl(long j) {
            this.zzbf |= 2;
            this.zzpf = j;
        }

        /* access modifiers changed from: private */
        public final void zzm(long j) {
            this.zzbf |= 4;
            this.zzpg = j;
        }

        /* access modifiers changed from: private */
        public final void zzn(long j) {
            this.zzbf |= 8;
            this.zzph = j;
        }

        /* access modifiers changed from: private */
        public final void zzo(long j) {
            this.zzbf |= 16;
            this.zzpi = j;
        }

        public static zza zzcz() {
            return (zza) zzpj.zzge();
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzj>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzj>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzj>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 50
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0070;
                    case 2: goto L_0x006a;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzj> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzj> r3 = com.google.android.gms.internal.vision.zzea.zzj.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzj> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzj r4 = zzpj     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzj r2 = zzpj
                return r2
            L_0x0033:
                r2 = 7
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zzmt"
                r2[r4] = r3
                r3 = 2
                com.google.android.gms.internal.vision.zzgy r4 = com.google.android.gms.internal.vision.zzea.zzj.zzb.zzah()
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzpf"
                r2[r3] = r4
                r3 = 4
                java.lang.String r4 = "zzpg"
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zzpi"
                r2[r3] = r4
                r3 = 6
                java.lang.String r4 = "zzph"
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zzj r3 = zzpj
                java.lang.String r4 = "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0004\u0005ဂ\u0003"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x006a:
                com.google.android.gms.internal.vision.zzea$zzj$zza r2 = new com.google.android.gms.internal.vision.zzea$zzj$zza
                r2.<init>(r3)
                return r2
            L_0x0070:
                com.google.android.gms.internal.vision.zzea$zzj r2 = new com.google.android.gms.internal.vision.zzea$zzj
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzj.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzj zzj = new zzj();
            zzpj = zzj;
            zzgs.zza(zzj.class, zzj);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzk extends zzgs<zzk, zza> implements zzie {
        private static volatile zzil<zzk> zzbd;
        /* access modifiers changed from: private */
        public static final zzk zzpt;
        private int zzbf;
        private String zznf;
        private String zznn;
        private long zzpp;
        private zza zzpq;
        private zzg zzpr;
        private zzb zzps;

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zza extends com.google.android.gms.internal.vision.zzgs.zza<zzk, zza> implements zzie {
            private zza() {
                super(zzk.zzpt);
            }

            public final zza zzt(String str) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzk) this.zzwh).setName(str);
                return this;
            }

            public final zza zzq(long j) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzk) this.zzwh).zzp(j);
                return this;
            }

            public final zza zzb(zza zza) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzk) this.zzwh).zza(zza);
                return this;
            }

            public final zza zzu(String str) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzk) this.zzwh).zzs(str);
                return this;
            }

            public final zza zza(zzb zzb) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzk) this.zzwh).zza((zzg) ((zzgs) zzb.zzgc()));
                return this;
            }

            /* synthetic */ zza(zzdz zzdz) {
                this();
            }
        }

        private zzk() {
            String str = "";
            this.zznn = str;
            this.zznf = str;
        }

        /* access modifiers changed from: private */
        public final void setName(String str) {
            str.getClass();
            this.zzbf |= 1;
            this.zznn = str;
        }

        /* access modifiers changed from: private */
        public final void zzp(long j) {
            this.zzbf |= 2;
            this.zzpp = j;
        }

        /* access modifiers changed from: private */
        public final void zza(zza zza2) {
            zza2.getClass();
            this.zzpq = zza2;
            this.zzbf |= 4;
        }

        /* access modifiers changed from: private */
        public final void zzs(String str) {
            str.getClass();
            this.zzbf |= 8;
            this.zznf = str;
        }

        /* access modifiers changed from: private */
        public final void zza(zzg zzg) {
            zzg.getClass();
            this.zzpr = zzg;
            this.zzbf |= 16;
        }

        public static zza zzdb() {
            return (zza) zzpt.zzge();
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzk>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzk>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzk>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 50
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x006f;
                    case 2: goto L_0x0069;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzk> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzk> r3 = com.google.android.gms.internal.vision.zzea.zzk.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzk> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzk r4 = zzpt     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzk r2 = zzpt
                return r2
            L_0x0033:
                r2 = 7
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zznn"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzpp"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzpq"
                r2[r3] = r4
                r3 = 4
                java.lang.String r4 = "zznf"
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zzpr"
                r2[r3] = r4
                r3 = 6
                java.lang.String r4 = "zzps"
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zzk r3 = zzpt
                java.lang.String r4 = "\u0001\u0006\u0000\u0001\u0001\u0011\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဉ\u0002\u0006ဈ\u0003\u0010ဉ\u0004\u0011ဉ\u0005"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x0069:
                com.google.android.gms.internal.vision.zzea$zzk$zza r2 = new com.google.android.gms.internal.vision.zzea$zzk$zza
                r2.<init>(r3)
                return r2
            L_0x006f:
                com.google.android.gms.internal.vision.zzea$zzk r2 = new com.google.android.gms.internal.vision.zzea$zzk
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzk.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzk zzk = new zzk();
            zzpt = zzk;
            zzgs.zza(zzk.class, zzk);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzl extends zzgs<zzl, zza> implements zzie {
        private static volatile zzil<zzl> zzbd;
        /* access modifiers changed from: private */
        public static final zzl zzpu;
        private int zzbf;
        private long zzns;
        private long zznt;

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zza extends com.google.android.gms.internal.vision.zzgs.zza<zzl, zza> implements zzie {
            private zza() {
                super(zzl.zzpu);
            }

            /* synthetic */ zza(zzdz zzdz) {
                this();
            }
        }

        private zzl() {
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzl>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzl>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzl>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 42
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0057;
                    case 2: goto L_0x0051;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzl> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzl> r3 = com.google.android.gms.internal.vision.zzea.zzl.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzl> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzl r4 = zzpu     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzl r2 = zzpu
                return r2
            L_0x0033:
                r2 = 3
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zzns"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zznt"
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zzl r3 = zzpu
                java.lang.String r4 = "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x0051:
                com.google.android.gms.internal.vision.zzea$zzl$zza r2 = new com.google.android.gms.internal.vision.zzea$zzl$zza
                r2.<init>(r3)
                return r2
            L_0x0057:
                com.google.android.gms.internal.vision.zzea$zzl r2 = new com.google.android.gms.internal.vision.zzea$zzl
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzl.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzl zzl = new zzl();
            zzpu = zzl;
            zzgs.zza(zzl.class, zzl);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzm extends zzgs<zzm, zza> implements zzie {
        private static volatile zzil<zzm> zzbd;
        /* access modifiers changed from: private */
        public static final zzm zzpx;
        private int zzbf;
        private int zzpv;
        private int zzpw;

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zza extends com.google.android.gms.internal.vision.zzgs.zza<zzm, zza> implements zzie {
            private zza() {
                super(zzm.zzpx);
            }

            public final zza zzz(int i) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzm) this.zzwh).setX(i);
                return this;
            }

            public final zza zzaa(int i) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzm) this.zzwh).setY(i);
                return this;
            }

            /* synthetic */ zza(zzdz zzdz) {
                this();
            }
        }

        private zzm() {
        }

        /* access modifiers changed from: private */
        public final void setX(int i) {
            this.zzbf |= 1;
            this.zzpv = i;
        }

        /* access modifiers changed from: private */
        public final void setY(int i) {
            this.zzbf |= 2;
            this.zzpw = i;
        }

        public static zza zzde() {
            return (zza) zzpx.zzge();
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzm>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzm>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzm>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 42
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0057;
                    case 2: goto L_0x0051;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzm> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzm> r3 = com.google.android.gms.internal.vision.zzea.zzm.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzm> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzm r4 = zzpx     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzm r2 = zzpx
                return r2
            L_0x0033:
                r2 = 3
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zzpv"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzpw"
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zzm r3 = zzpx
                java.lang.String r4 = "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x0051:
                com.google.android.gms.internal.vision.zzea$zzm$zza r2 = new com.google.android.gms.internal.vision.zzea$zzm$zza
                r2.<init>(r3)
                return r2
            L_0x0057:
                com.google.android.gms.internal.vision.zzea$zzm r2 = new com.google.android.gms.internal.vision.zzea$zzm
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzm.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzm zzm = new zzm();
            zzpx = zzm;
            zzgs.zza(zzm.class, zzm);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzn extends zzgs<zzn, zza> implements zzie {
        private static volatile zzil<zzn> zzbd;
        /* access modifiers changed from: private */
        public static final zzn zzqc;
        private int zzbf;
        private zzd zzpy;
        private int zzpz;
        private zzh zzqa;
        private zzc zzqb;

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zza extends com.google.android.gms.internal.vision.zzgs.zza<zzn, zza> implements zzie {
            private zza() {
                super(zzn.zzqc);
            }

            public final zza zza(zza zza) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzn) this.zzwh).zza((zzd) ((zzgs) zza.zzgc()));
                return this;
            }

            public final zza zzab(int i) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzn) this.zzwh).setId(i);
                return this;
            }

            public final zza zzb(zzh zzh) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzn) this.zzwh).zza(zzh);
                return this;
            }

            /* synthetic */ zza(zzdz zzdz) {
                this();
            }
        }

        private zzn() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzd zzd) {
            zzd.getClass();
            this.zzpy = zzd;
            this.zzbf |= 1;
        }

        /* access modifiers changed from: private */
        public final void setId(int i) {
            this.zzbf |= 2;
            this.zzpz = i;
        }

        /* access modifiers changed from: private */
        public final void zza(zzh zzh) {
            zzh.getClass();
            this.zzqa = zzh;
            this.zzbf |= 4;
        }

        public static zza zzdg() {
            return (zza) zzqc.zzge();
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzn>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzn>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzn>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 46
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0063;
                    case 2: goto L_0x005d;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzn> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzn> r3 = com.google.android.gms.internal.vision.zzea.zzn.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzn> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzn r4 = zzqc     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzn r2 = zzqc
                return r2
            L_0x0033:
                r2 = 5
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zzpy"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzpz"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzqa"
                r2[r3] = r4
                r3 = 4
                java.lang.String r4 = "zzqb"
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zzn r3 = zzqc
                java.lang.String r4 = "\u0001\u0004\u0000\u0001\u0001\u0011\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002င\u0001\u0010ဉ\u0002\u0011ဉ\u0003"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x005d:
                com.google.android.gms.internal.vision.zzea$zzn$zza r2 = new com.google.android.gms.internal.vision.zzea$zzn$zza
                r2.<init>(r3)
                return r2
            L_0x0063:
                com.google.android.gms.internal.vision.zzea$zzn r2 = new com.google.android.gms.internal.vision.zzea$zzn
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzn.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzn zzn = new zzn();
            zzqc = zzn;
            zzgs.zza(zzn.class, zzn);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzo extends zzgs<zzo, zza> implements zzie {
        private static volatile zzil<zzo> zzbd;
        /* access modifiers changed from: private */
        public static final zzo zzqh;
        private int zzbf;
        private zze zzqd;
        private zzk zzqe;
        private zzi zzqf;
        private int zzqg;

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
        public static final class zza extends com.google.android.gms.internal.vision.zzgs.zza<zzo, zza> implements zzie {
            private zza() {
                super(zzo.zzqh);
            }

            public final zza zza(zza zza) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzo) this.zzwh).zza((zzk) ((zzgs) zza.zzgc()));
                return this;
            }

            public final zza zzb(zzi zzi) {
                if (this.zzwi) {
                    zzfy();
                    this.zzwi = false;
                }
                ((zzo) this.zzwh).zza(zzi);
                return this;
            }

            /* synthetic */ zza(zzdz zzdz) {
                this();
            }
        }

        private zzo() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzk zzk) {
            zzk.getClass();
            this.zzqe = zzk;
            this.zzbf |= 2;
        }

        /* access modifiers changed from: private */
        public final void zza(zzi zzi) {
            zzi.getClass();
            this.zzqf = zzi;
            this.zzbf |= 4;
        }

        public static zza zzdi() {
            return (zza) zzqh.zzge();
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzo>] */
        /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzo>] */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzo>] */
        /* JADX WARNING: type inference failed for: r2v17 */
        /* JADX WARNING: type inference failed for: r2v18 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13
          assigns: []
          uses: []
          mth insns count: 46
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.vision.zzdz.zzbe
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0063;
                    case 2: goto L_0x005d;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
                r2.<init>()
                throw r2
            L_0x0010:
                return r3
            L_0x0011:
                java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
                return r2
            L_0x0016:
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzo> r2 = zzbd
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.vision.zzea$zzo> r3 = com.google.android.gms.internal.vision.zzea.zzo.class
                monitor-enter(r3)
                com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzo> r2 = zzbd     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.vision.zzgs$zzc r2 = new com.google.android.gms.internal.vision.zzgs$zzc     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.vision.zzea$zzo r4 = zzqh     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbd = r2     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                goto L_0x002f
            L_0x002c:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x002c }
                throw r2
            L_0x002f:
                return r2
            L_0x0030:
                com.google.android.gms.internal.vision.zzea$zzo r2 = zzqh
                return r2
            L_0x0033:
                r2 = 5
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbf"
                r2[r3] = r0
                java.lang.String r3 = "zzqd"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzqe"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzqf"
                r2[r3] = r4
                r3 = 4
                java.lang.String r4 = "zzqg"
                r2[r3] = r4
                com.google.android.gms.internal.vision.zzea$zzo r3 = zzqh
                java.lang.String r4 = "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004င\u0003"
                java.lang.Object r2 = zza(r3, r4, r2)
                return r2
            L_0x005d:
                com.google.android.gms.internal.vision.zzea$zzo$zza r2 = new com.google.android.gms.internal.vision.zzea$zzo$zza
                r2.<init>(r3)
                return r2
            L_0x0063:
                com.google.android.gms.internal.vision.zzea$zzo r2 = new com.google.android.gms.internal.vision.zzea$zzo
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzea.zzo.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        static {
            zzo zzo = new zzo();
            zzqh = zzo;
            zzgs.zza(zzo.class, zzo);
        }
    }
}
