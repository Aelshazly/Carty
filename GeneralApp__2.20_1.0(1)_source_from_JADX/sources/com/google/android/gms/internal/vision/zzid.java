package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs.zze;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzid {
    static String zza(zzic zzic, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zza(zzic, sb, 0);
        return sb.toString();
    }

    private static void zza(zzic zzic, StringBuilder sb, int i) {
        String str;
        boolean z;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet<>();
        Method[] declaredMethods = zzic.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i2 = 0;
        while (true) {
            str = "get";
            if (i2 >= length) {
                break;
            }
            Method method = declaredMethods[i2];
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith(str)) {
                    treeSet.add(method.getName());
                }
            }
            i2++;
        }
        for (String str2 : treeSet) {
            String substring = str2.startsWith(str) ? str2.substring(3) : str2;
            String str3 = "List";
            if (substring.endsWith(str3) && !substring.endsWith("OrBuilderList") && !substring.equals(str3)) {
                String valueOf = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf2 = String.valueOf(substring.substring(1, substring.length() - 4));
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                Method method2 = (Method) hashMap.get(str2);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zza(sb, i, zzz(concat), zzgs.zza(method2, (Object) zzic, new Object[0]));
                }
            }
            String str4 = "Map";
            if (substring.endsWith(str4) && !substring.equals(str4)) {
                String valueOf3 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(substring.substring(1, substring.length() - 3));
                String concat2 = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                Method method3 = (Method) hashMap.get(str2);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zza(sb, i, zzz(concat2), zzgs.zza(method3, (Object) zzic, new Object[0]));
                }
            }
            String str5 = "set";
            String valueOf5 = String.valueOf(substring);
            if (((Method) hashMap2.get(valueOf5.length() != 0 ? str5.concat(valueOf5) : new String(str5))) != null) {
                if (substring.endsWith("Bytes")) {
                    String valueOf6 = String.valueOf(substring.substring(0, substring.length() - 5));
                    if (hashMap.containsKey(valueOf6.length() != 0 ? str.concat(valueOf6) : new String(str))) {
                    }
                }
                String valueOf7 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf8 = String.valueOf(substring.substring(1));
                String concat3 = valueOf8.length() != 0 ? valueOf7.concat(valueOf8) : new String(valueOf7);
                String valueOf9 = String.valueOf(substring);
                Method method4 = (Method) hashMap.get(valueOf9.length() != 0 ? str.concat(valueOf9) : new String(str));
                String str6 = "has";
                String valueOf10 = String.valueOf(substring);
                Method method5 = (Method) hashMap.get(valueOf10.length() != 0 ? str6.concat(valueOf10) : new String(str6));
                if (method4 != null) {
                    Object zza = zzgs.zza(method4, (Object) zzic, new Object[0]);
                    if (method5 == null) {
                        boolean z2 = zza instanceof Boolean ? !((Boolean) zza).booleanValue() : zza instanceof Integer ? ((Integer) zza).intValue() == 0 : zza instanceof Float ? ((Float) zza).floatValue() == 0.0f : zza instanceof Double ? ((Double) zza).doubleValue() == 0.0d : zza instanceof String ? zza.equals("") : zza instanceof zzfh ? zza.equals(zzfh.zzsd) : zza instanceof zzic ? zza == ((zzic) zza).zzgd() : zza instanceof Enum ? ((Enum) zza).ordinal() == 0 : false;
                        z = !z2;
                    } else {
                        z = ((Boolean) zzgs.zza(method5, (Object) zzic, new Object[0])).booleanValue();
                    }
                    if (z) {
                        zza(sb, i, zzz(concat3), zza);
                    }
                }
            }
        }
        if (zzic instanceof zze) {
            Iterator it = ((zze) zzic).zzwq.iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                int i3 = ((zzd) entry.getKey()).number;
                StringBuilder sb2 = new StringBuilder(13);
                sb2.append("[");
                sb2.append(i3);
                sb2.append("]");
                zza(sb, i, sb2.toString(), entry.getValue());
            }
        }
        zzgs zzgs = (zzgs) zzic;
        if (zzgs.zzwj != null) {
            zzgs.zzwj.zza(sb, i);
        }
    }

    static final void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object zza : (List) obj) {
                zza(sb, i, str, zza);
            }
        } else if (obj instanceof Map) {
            for (Entry zza2 : ((Map) obj).entrySet()) {
                zza(sb, i, str, zza2);
            }
        } else {
            sb.append(10);
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append(str);
            String str2 = ": \"";
            if (obj instanceof String) {
                sb.append(str2);
                sb.append(zzjf.zzd(zzfh.zzw((String) obj)));
                sb.append(Typography.quote);
            } else if (obj instanceof zzfh) {
                sb.append(str2);
                sb.append(zzjf.zzd((zzfh) obj));
                sb.append(Typography.quote);
            } else {
                String str3 = "}";
                String str4 = "\n";
                String str5 = " {";
                if (obj instanceof zzgs) {
                    sb.append(str5);
                    zza((zzgs) obj, sb, i + 2);
                    sb.append(str4);
                    while (i2 < i) {
                        sb.append(' ');
                        i2++;
                    }
                    sb.append(str3);
                } else if (obj instanceof Entry) {
                    sb.append(str5);
                    Entry entry = (Entry) obj;
                    int i4 = i + 2;
                    zza(sb, i4, "key", entry.getKey());
                    zza(sb, i4, "value", entry.getValue());
                    sb.append(str4);
                    while (i2 < i) {
                        sb.append(' ');
                        i2++;
                    }
                    sb.append(str3);
                } else {
                    sb.append(": ");
                    sb.append(obj.toString());
                }
            }
        }
    }

    private static final String zzz(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
