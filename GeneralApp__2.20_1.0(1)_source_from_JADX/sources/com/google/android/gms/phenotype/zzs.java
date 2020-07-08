package com.google.android.gms.phenotype;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.phenotype.PhenotypeFlag.Factory;

final class zzs extends PhenotypeFlag<String> {
    zzs(Factory factory, String str, String str2) {
        super(factory, str, str2, null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final String zza(SharedPreferences sharedPreferences) {
        try {
            return sharedPreferences.getString(this.zzap, null);
        } catch (ClassCastException e) {
            String str = "Invalid string value in SharedPreferences for ";
            String valueOf = String.valueOf(this.zzap);
            Log.e("PhenotypeFlag", valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e);
            return null;
        }
    }

    public final /* synthetic */ Object zza(String str) {
        return str;
    }
}
