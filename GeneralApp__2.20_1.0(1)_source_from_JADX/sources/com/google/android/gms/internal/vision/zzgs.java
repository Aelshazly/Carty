package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import com.google.android.gms.internal.vision.zzgs.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public abstract class zzgs<MessageType extends zzgs<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzet<MessageType, BuilderType> {
    private static Map<Object, zzgs<?, ?>> zzwl = new ConcurrentHashMap();
    protected zzjm zzwj = zzjm.zzig();
    private int zzwk = -1;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static abstract class zza<MessageType extends zzgs<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzew<MessageType, BuilderType> {
        private final MessageType zzwg;
        protected MessageType zzwh;
        protected boolean zzwi = false;

        protected zza(MessageType messagetype) {
            this.zzwg = messagetype;
            this.zzwh = (zzgs) messagetype.zza(zzf.zzwu, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public void zzfy() {
            MessageType messagetype = (zzgs) this.zzwh.zza(zzf.zzwu, (Object) null, (Object) null);
            zza(messagetype, this.zzwh);
            this.zzwh = messagetype;
        }

        public final boolean isInitialized() {
            return zzgs.zza(this.zzwh, false);
        }

        /* renamed from: zzfz */
        public MessageType zzgb() {
            if (this.zzwi) {
                return this.zzwh;
            }
            MessageType messagetype = this.zzwh;
            zzin.zzho().zzv(messagetype).zzh(messagetype);
            this.zzwi = true;
            return this.zzwh;
        }

        /* renamed from: zzga */
        public final MessageType zzgc() {
            MessageType messagetype = (zzgs) zzgb();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzjk(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzwi) {
                zzfy();
                this.zzwi = false;
            }
            zza(this.zzwh, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzin.zzho().zzv(messagetype).zzd(messagetype, messagetype2);
        }

        private final BuilderType zzb(byte[] bArr, int i, int i2, zzgd zzgd) throws zzhc {
            if (this.zzwi) {
                zzfy();
                this.zzwi = false;
            }
            try {
                zzin.zzho().zzv(this.zzwh).zza(this.zzwh, bArr, 0, i2 + 0, new zzfb(zzgd));
                return this;
            } catch (zzhc e) {
                throw e;
            } catch (IndexOutOfBoundsException e2) {
                throw zzhc.zzgm();
            } catch (IOException e3) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e3);
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: zzb */
        public final BuilderType zza(zzft zzft, zzgd zzgd) throws IOException {
            if (this.zzwi) {
                zzfy();
                this.zzwi = false;
            }
            try {
                zzin.zzho().zzv(this.zzwh).zza(this.zzwh, zzfy.zza(zzft), zzgd);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        public final /* synthetic */ zzew zza(byte[] bArr, int i, int i2, zzgd zzgd) throws zzhc {
            return zzb(bArr, 0, i2, zzgd);
        }

        public final /* synthetic */ zzew zzdn() {
            return (zza) clone();
        }

        public final /* synthetic */ zzic zzgd() {
            return this.zzwg;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zza = (zza) ((zzgs) this.zzwg).zza(zzf.zzwv, (Object) null, (Object) null);
            zza.zza((MessageType) (zzgs) zzgb());
            return zza;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static abstract class zzb<MessageType extends zze<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zza<MessageType, BuilderType> implements zzie {
        protected zzb(MessageType messagetype) {
            super(messagetype);
        }

        /* access modifiers changed from: protected */
        public void zzfy() {
            super.zzfy();
            ((zze) this.zzwh).zzwq = (zzgi) ((zze) this.zzwh).zzwq.clone();
        }

        public /* synthetic */ zzgs zzfz() {
            return (zze) zzgb();
        }

        public /* synthetic */ zzic zzgb() {
            if (this.zzwi) {
                return (zze) this.zzwh;
            }
            ((zze) this.zzwh).zzwq.zzdp();
            return (zze) super.zzgb();
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static class zzc<T extends zzgs<T, ?>> extends zzey<T> {
        private final T zzwg;

        public zzc(T t) {
            this.zzwg = t;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    static final class zzd implements zzgk<zzd> {
        final int number = 202056002;
        final zzgv<?> zzwm = null;
        final zzka zzwn;
        final boolean zzwo;
        final boolean zzwp;

        zzd(zzgv<?> zzgv, int i, zzka zzka, boolean z, boolean z2) {
            this.zzwn = zzka;
            this.zzwo = true;
            this.zzwp = false;
        }

        public final int zzag() {
            return this.number;
        }

        public final zzka zzfs() {
            return this.zzwn;
        }

        public final zzkd zzft() {
            return this.zzwn.zzip();
        }

        public final boolean zzfu() {
            return this.zzwo;
        }

        public final boolean zzfv() {
            return this.zzwp;
        }

        public final zzib zza(zzib zzib, zzic zzic) {
            return ((zza) zzib).zza((zzgs) zzic);
        }

        public final zzih zza(zzih zzih, zzih zzih2) {
            throw new UnsupportedOperationException();
        }

        public final /* synthetic */ int compareTo(Object obj) {
            return this.number - ((zzd) obj).number;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static abstract class zze<MessageType extends zze<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzgs<MessageType, BuilderType> implements zzie {
        protected zzgi<zzd> zzwq = zzgi.zzfn();

        /* access modifiers changed from: 0000 */
        public final zzgi<zzd> zzgk() {
            if (this.zzwq.isImmutable()) {
                this.zzwq = (zzgi) this.zzwq.clone();
            }
            return this.zzwq;
        }

        public final <Type> Type zzc(zzge<MessageType, Type> zzge) {
            zzg zzb = zzgs.zza(zzge);
            if (zzb.zzxf == ((zzgs) zzgd())) {
                Type zza = this.zzwq.zza(zzb.zzxh);
                if (zza == null) {
                    return zzb.zzgd;
                }
                if (!zzb.zzxh.zzwo) {
                    return zzb.zzj(zza);
                }
                if (zzb.zzxh.zzwn.zzip() != zzkd.ENUM) {
                    return zza;
                }
                Type arrayList = new ArrayList();
                for (Object zzj : (List) zza) {
                    arrayList.add(zzb.zzj(zzj));
                }
                return arrayList;
            }
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
    }

    /* 'enum' access flag removed */
    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static final class zzf {
        public static final int zzwr = 1;
        public static final int zzws = 2;
        public static final int zzwt = 3;
        public static final int zzwu = 4;
        public static final int zzwv = 5;
        public static final int zzww = 6;
        public static final int zzwx = 7;
        private static final /* synthetic */ int[] zzwy = {zzwr, zzws, zzwt, zzwu, zzwv, zzww, zzwx};
        public static final int zzwz = 1;
        public static final int zzxa = 2;
        private static final /* synthetic */ int[] zzxb = {zzwz, zzxa};
        public static final int zzxc = 1;
        public static final int zzxd = 2;
        private static final /* synthetic */ int[] zzxe = {zzxc, zzxd};

        /* renamed from: values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0 */
        public static int[] m49x126d66cb() {
            return (int[]) zzwy.clone();
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static class zzg<ContainingType extends zzic, Type> extends zzge<ContainingType, Type> {
        final Type zzgd;
        final ContainingType zzxf;
        final zzic zzxg;
        final zzd zzxh;

        zzg(ContainingType containingtype, Type type, zzic zzic, zzd zzd, Class cls) {
            if (containingtype == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            } else if (zzd.zzwn == zzka.MESSAGE && zzic == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            } else {
                this.zzxf = containingtype;
                this.zzgd = type;
                this.zzxg = zzic;
                this.zzxh = zzd;
            }
        }

        /* access modifiers changed from: 0000 */
        public final Object zzj(Object obj) {
            if (this.zzxh.zzwn.zzip() == zzkd.ENUM) {
                return this.zzxh.zzwm.zzh(((Integer) obj).intValue());
            }
            return obj;
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    public String toString() {
        return zzid.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzro != 0) {
            return this.zzro;
        }
        this.zzro = zzin.zzho().zzv(this).hashCode(this);
        return this.zzro;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzin.zzho().zzv(this).equals(this, (zzgs) obj);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzgs<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzge() {
        return (zza) zza(zzf.zzwv, (Object) null, (Object) null);
    }

    public final boolean isInitialized() {
        return zza((T) this, Boolean.TRUE.booleanValue());
    }

    /* access modifiers changed from: 0000 */
    public final int zzdl() {
        return this.zzwk;
    }

    /* access modifiers changed from: 0000 */
    public final void zzae(int i) {
        this.zzwk = i;
    }

    public final void zzb(zzga zzga) throws IOException {
        zzin.zzho().zzv(this).zza(this, zzgc.zza(zzga));
    }

    public final int zzgf() {
        if (this.zzwk == -1) {
            this.zzwk = zzin.zzho().zzv(this).zzs(this);
        }
        return this.zzwk;
    }

    static <T extends zzgs<?, ?>> T zzd(Class<T> cls) {
        T t = (zzgs) zzwl.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzgs) zzwl.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzgs) ((zzgs) zzjp.zzh(cls)).zza(zzf.zzww, (Object) null, (Object) null);
            if (t != null) {
                zzwl.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends zzgs<?, ?>> void zza(Class<T> cls, T t) {
        zzwl.put(cls, t);
    }

    protected static Object zza(zzic zzic, String str, Object[] objArr) {
        return new zzip(zzic, str, objArr);
    }

    public static <ContainingType extends zzic, Type> zzg<ContainingType, Type> zza(ContainingType containingtype, zzic zzic, zzgv<?> zzgv, int i, zzka zzka, boolean z, Class cls) {
        List emptyList = Collections.emptyList();
        zzd zzd2 = new zzd(null, 202056002, zzka, true, false);
        zzg zzg2 = new zzg(containingtype, emptyList, zzic, zzd2, cls);
        return zzg2;
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    /* access modifiers changed from: private */
    public static <MessageType extends zze<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>, T> zzg<MessageType, T> zza(zzge<MessageType, T> zzge) {
        return (zzg) zzge;
    }

    protected static final <T extends zzgs<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzf.zzwr, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzu = zzin.zzho().zzv(t).zzu(t);
        if (z) {
            t.zza(zzf.zzws, (Object) zzu ? t : null, (Object) null);
        }
        return zzu;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.vision.zzgx, com.google.android.gms.internal.vision.zzgu] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.vision.zzgx, com.google.android.gms.internal.vision.zzgu]
      assigns: [com.google.android.gms.internal.vision.zzgu]
      uses: [com.google.android.gms.internal.vision.zzgx]
      mth insns count: 2
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.google.android.gms.internal.vision.zzgx zzgg() {
        /*
            com.google.android.gms.internal.vision.zzgu r0 = com.google.android.gms.internal.vision.zzgu.zzgl()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzgs.zzgg():com.google.android.gms.internal.vision.zzgx");
    }

    protected static <E> zzgz<E> zzgh() {
        return zziq.zzhr();
    }

    protected static <E> zzgz<E> zza(zzgz<E> zzgz) {
        int size = zzgz.size();
        return zzgz.zzah(size == 0 ? 10 : size << 1);
    }

    private static <T extends zzgs<T, ?>> T zza(T t, byte[] bArr, int i, int i2, zzgd zzgd) throws zzhc {
        T t2 = (zzgs) t.zza(zzf.zzwu, (Object) null, (Object) null);
        try {
            zzir zzv = zzin.zzho().zzv(t2);
            zzv.zza(t2, bArr, 0, i2, new zzfb(zzgd));
            zzv.zzh(t2);
            if (t2.zzro == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzhc) {
                throw ((zzhc) e.getCause());
            }
            throw new zzhc(e.getMessage()).zzg(t2);
        } catch (IndexOutOfBoundsException e2) {
            throw zzhc.zzgm().zzg(t2);
        }
    }

    private static <T extends zzgs<T, ?>> T zzb(T t) throws zzhc {
        if (t == null || t.isInitialized()) {
            return t;
        }
        throw new zzhc(new zzjk(t).getMessage()).zzg(t);
    }

    protected static <T extends zzgs<T, ?>> T zza(T t, byte[] bArr) throws zzhc {
        return zzb((T) zza(t, bArr, 0, bArr.length, zzgd.zzfl()));
    }

    protected static <T extends zzgs<T, ?>> T zza(T t, byte[] bArr, zzgd zzgd) throws zzhc {
        return zzb((T) zza(t, bArr, 0, bArr.length, zzgd));
    }

    public final /* synthetic */ zzib zzgi() {
        zza zza2 = (zza) zza(zzf.zzwv, (Object) null, (Object) null);
        zza2.zza(this);
        return zza2;
    }

    public final /* synthetic */ zzib zzgj() {
        return (zza) zza(zzf.zzwv, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzic zzgd() {
        return (zzgs) zza(zzf.zzww, (Object) null, (Object) null);
    }
}
