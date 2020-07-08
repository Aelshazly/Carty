package com.navibees.navigatorapp.p010ui.fragments;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.navibees.core.NaviBeesManager;
import com.navibees.navigatorapp.C1170R;
import com.navibees.navigatorapp.p010ui.activities.SettingActivity;
import com.navibees.navigatorapp.utils.LocaleUtils;
import java.util.Locale;

/* renamed from: com.navibees.navigatorapp.ui.fragments.SettingFragment */
public class SettingFragment extends PreferenceFragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(C1170R.xml.preferences);
        handleStateOfTTS();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ListPreference) getPreferenceManager().findPreference(getActivity().getResources().getString(C1170R.string.preference_preferred_language_key))).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (!preference.getKey().equals(SettingFragment.this.getResources().getString(C1170R.string.preference_preferred_language_key))) {
                    return false;
                }
                String selectedLang = (String) newValue;
                NaviBeesManager.changeLanguage(SettingFragment.this.getActivity(), selectedLang);
                Locale locale = new Locale(selectedLang);
                Locale.setDefault(locale);
                Resources resources = SettingFragment.this.getActivity().getResources();
                Configuration configuration = resources.getConfiguration();
                configuration.locale = locale;
                resources.updateConfiguration(configuration, resources.getDisplayMetrics());
                SettingFragment.this.getActivity().recreate();
                return true;
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void showAlert(String title, final String selectedLang) {
        Builder alertDialog = new Builder(getActivity());
        alertDialog.setTitle(title);
        alertDialog.setMessage(getString(C1170R.string.change_language));
        alertDialog.setPositiveButton("OK", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ProgressDialog progressDialog = ProgressDialog.show(SettingFragment.this.getActivity(), null, "Please Wait", true);
                LocaleUtils.setLocale(new Locale(selectedLang));
                LocaleUtils.updateConfig(SettingFragment.this.getActivity().getApplication(), SettingFragment.this.getActivity().getBaseContext().getResources().getConfiguration());
                progressDialog.dismiss();
                SettingFragment.this.startActivity(new Intent(SettingFragment.this.getActivity(), SettingActivity.class));
                SettingFragment.this.getActivity().finish();
            }
        });
        alertDialog.setNegativeButton("NO", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.create().show();
    }

    private void handleStateOfTTS() {
        SwitchPreference isTTSEnabled = (SwitchPreference) getPreferenceManager().findPreference(getResources().getString(C1170R.string.com_navibees_sdk_preference_user_enabled_tts_key));
        String selectedLang = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString(NaviBeesManager.LANGUAGE_KEY, Locale.getDefault().getDisplayLanguage());
        if (isTTSEnabled != null) {
            String[] languages = getResources().getStringArray(C1170R.array.language_list_locale);
            if (selectedLang != null && languages != null) {
                if (selectedLang.equals(languages[0]) || selectedLang.equals(languages[4])) {
                    isTTSEnabled.setEnabled(false);
                } else {
                    isTTSEnabled.setEnabled(true);
                }
            }
        }
    }
}
