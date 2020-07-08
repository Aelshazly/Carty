package com.navibees.navigatorapp.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.view.ContextThemeWrapper;
import com.navibees.core.NaviBeesManager;
import java.util.Locale;

public class LocaleUtils {
    private static Locale sLocale;

    public static void setLocale(Locale locale) {
        sLocale = locale;
        Locale locale2 = sLocale;
        if (locale2 != null) {
            Locale.setDefault(locale2);
        }
    }

    public static void updateConfig(ContextThemeWrapper wrapper) {
        if (sLocale != null && VERSION.SDK_INT >= 17) {
            Configuration configuration = new Configuration();
            configuration.setLocale(sLocale);
            wrapper.applyOverrideConfiguration(configuration);
        }
    }

    public static void updateConfig(Application app, Configuration configuration) {
        if (sLocale != null && VERSION.SDK_INT < 17) {
            Configuration config = new Configuration(configuration);
            config.locale = sLocale;
            Resources res = app.getBaseContext().getResources();
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
    }

    public static Resources getLocalizedResources(Context context) {
        String selectedLang = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getString(NaviBeesManager.LANGUAGE_KEY, Locale.getDefault().getDisplayLanguage());
        Configuration conf = new Configuration(context.getResources().getConfiguration());
        conf.setLocale(new Locale(selectedLang));
        return context.createConfigurationContext(conf).getResources();
    }
}
