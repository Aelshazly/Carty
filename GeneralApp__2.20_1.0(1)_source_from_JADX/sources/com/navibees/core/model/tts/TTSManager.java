package com.navibees.core.model.tts;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.UtteranceProgressListener;
import androidx.preference.PreferenceManager;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.interfaces.C1643b;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;
import com.navibees.core.util.Log;
import com.navibees.core.util.NaviBeesUtils;
import com.navibees.sdk.C1266R;
import java.util.HashMap;
import java.util.Locale;

public final class TTSManager extends UtteranceProgressListener implements OnInitListener {

    /* renamed from: e */
    static final String f1598e = "TTSManager";

    /* renamed from: a */
    private TextToSpeech f1599a = null;

    /* renamed from: b */
    private C1643b f1600b;

    /* renamed from: c */
    private boolean f1601c = false;

    /* renamed from: d */
    private Activity f1602d;

    public TTSManager(Activity activity) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        this.f1602d = activity;
        NaviBeesManager.getInstance(activity.getApplication()).getLicenseManager().mo29048a(NaviBeesFeature.Navigation_TTS);
        try {
            this.f1599a = new TextToSpeech(this.f1602d.getApplicationContext(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m1163a(String str) {
        if (this.f1601c) {
            HashMap hashMap = new HashMap();
            hashMap.put("streamType", String.valueOf(3));
            hashMap.put("utteranceId", str);
            if (VERSION.SDK_INT >= 21) {
                this.f1599a.speak(str, 0, null, str);
            } else {
                this.f1599a.speak(str, 0, hashMap);
            }
        } else {
            Log.m1172e(f1598e, "TTS Not Initialized");
        }
    }

    public void onDone(String str) {
        C1643b bVar = this.f1600b;
        if (bVar != null) {
            bVar.onDone(str);
        }
    }

    public void onError(String str) {
        C1643b bVar = this.f1600b;
        if (bVar != null) {
            bVar.onError(str);
        }
    }

    public void onInit(int i) {
        String str = f1598e;
        if (i == 0) {
            int language = this.f1599a.setLanguage(Locale.getDefault());
            if (language == -1) {
                Log.m1172e(str, "TTS LANG_MISSING_DATA");
                this.f1601c = false;
                Intent intent = new Intent();
                intent.setAction("android.speech.tts.engine.INSTALL_TTS_DATA");
                intent.setFlags(268435456);
                this.f1602d.startActivityForResult(intent, 5);
                NaviBeesUtils.disableTTS(this.f1602d.getApplicationContext());
            } else if (language == -2) {
                if (!"ar".equals(NaviBeesManager.getAppLanguage())) {
                    if (!"en".equals(NaviBeesManager.getAppLanguage())) {
                        Log.m1172e(str, "LANG_NOT_SUPPORTED");
                        this.f1601c = false;
                        NaviBeesUtils.disableTTS(this.f1602d.getApplicationContext());
                        shutDown();
                        return;
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append("LANG_NOT_SUPPORTED but don't disable for ");
                sb.append(NaviBeesManager.getAppLanguage());
                Log.m1172e(str, sb.toString());
                this.f1601c = true;
                this.f1599a.setOnUtteranceProgressListener(this);
            } else {
                this.f1601c = true;
                this.f1599a.setOnUtteranceProgressListener(this);
            }
        } else {
            Log.m1172e(str, "TextToSpeech.ERROR");
            this.f1601c = false;
            NaviBeesUtils.disableTTS(this.f1602d.getApplicationContext());
            shutDown();
        }
    }

    public void onStart(String str) {
        C1643b bVar = this.f1600b;
        if (bVar != null) {
            bVar.onStart(str);
        }
    }

    public void resetTTS() {
        TextToSpeech textToSpeech = this.f1599a;
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    public void setTtsListener(C1643b bVar) {
        this.f1600b = bVar;
    }

    public void shutDown() {
        TextToSpeech textToSpeech = this.f1599a;
        if (textToSpeech != null) {
            textToSpeech.stop();
            this.f1599a.shutdown();
        }
        this.f1601c = false;
    }

    public void speak(String str) {
        if (PreferenceManager.getDefaultSharedPreferences(this.f1602d).getBoolean(this.f1602d.getString(C1266R.string.com_navibees_sdk_preference_user_enabled_tts_key), true) && str != null) {
            m1163a(str);
        }
    }
}
