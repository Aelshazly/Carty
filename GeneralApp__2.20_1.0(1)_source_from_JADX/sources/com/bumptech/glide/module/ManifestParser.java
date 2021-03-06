package com.bumptech.glide.module;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public final class ManifestParser {
    private static final String GLIDE_MODULE_VALUE = "GlideModule";
    private static final String TAG = "ManifestParser";
    private final Context context;

    public ManifestParser(Context context2) {
        this.context = context2;
    }

    public List<GlideModule> parse() {
        String str = TAG;
        if (Log.isLoggable(str, 3)) {
            Log.d(str, "Loading Glide modules");
        }
        List<GlideModule> modules = new ArrayList<>();
        try {
            ApplicationInfo appInfo = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
            if (appInfo.metaData == null) {
                if (Log.isLoggable(str, 3)) {
                    Log.d(str, "Got null app info metadata");
                }
                return modules;
            }
            if (Log.isLoggable(str, 2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Got app info metadata: ");
                sb.append(appInfo.metaData);
                Log.v(str, sb.toString());
            }
            for (String key : appInfo.metaData.keySet()) {
                if (GLIDE_MODULE_VALUE.equals(appInfo.metaData.get(key))) {
                    modules.add(parseModule(key));
                    if (Log.isLoggable(str, 3)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Loaded Glide module: ");
                        sb2.append(key);
                        Log.d(str, sb2.toString());
                    }
                }
            }
            if (Log.isLoggable(str, 3)) {
                Log.d(str, "Finished loading Glide modules");
            }
            return modules;
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e);
        }
    }

    private static GlideModule parseModule(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Object module = null;
            try {
                module = clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (InstantiationException e) {
                throwInstantiateGlideModuleException(clazz, e);
            } catch (IllegalAccessException e2) {
                throwInstantiateGlideModuleException(clazz, e2);
            } catch (NoSuchMethodException e3) {
                throwInstantiateGlideModuleException(clazz, e3);
            } catch (InvocationTargetException e4) {
                throwInstantiateGlideModuleException(clazz, e4);
            }
            if (module instanceof GlideModule) {
                return (GlideModule) module;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Expected instanceof GlideModule, but found: ");
            sb.append(module);
            throw new RuntimeException(sb.toString());
        } catch (ClassNotFoundException e5) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e5);
        }
    }

    private static void throwInstantiateGlideModuleException(Class<?> clazz, Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to instantiate GlideModule implementation for ");
        sb.append(clazz);
        throw new RuntimeException(sb.toString(), e);
    }
}
