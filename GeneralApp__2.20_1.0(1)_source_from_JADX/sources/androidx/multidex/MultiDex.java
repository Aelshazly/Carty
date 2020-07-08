package androidx.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.zip.ZipFile;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;

public final class MultiDex {
    private static final String CODE_CACHE_NAME = "code_cache";
    private static final String CODE_CACHE_SECONDARY_FOLDER_NAME = "secondary-dexes";
    private static final boolean IS_VM_MULTIDEX_CAPABLE = isVMMultidexCapable(System.getProperty("java.vm.version"));
    private static final int MAX_SUPPORTED_SDK_VERSION = 20;
    private static final int MIN_SDK_VERSION = 4;
    private static final String NO_KEY_PREFIX = "";
    private static final String OLD_SECONDARY_FOLDER_NAME = "secondary-dexes";
    static final String TAG = "MultiDex";
    private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
    private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;
    private static final Set<File> installedApk = new HashSet();

    private static final class V14 {
        private static final int EXTRACTED_SUFFIX_LENGTH = ".zip".length();
        private final ElementConstructor elementConstructor;

        private interface ElementConstructor {
            Object newInstance(File file, DexFile dexFile) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException;
        }

        private static class ICSElementConstructor implements ElementConstructor {
            private final Constructor<?> elementConstructor;

            ICSElementConstructor(Class<?> elementClass) throws SecurityException, NoSuchMethodException {
                this.elementConstructor = elementClass.getConstructor(new Class[]{File.class, ZipFile.class, DexFile.class});
                this.elementConstructor.setAccessible(true);
            }

            public Object newInstance(File file, DexFile dex) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
                return this.elementConstructor.newInstance(new Object[]{file, new ZipFile(file), dex});
            }
        }

        private static class JBMR11ElementConstructor implements ElementConstructor {
            private final Constructor<?> elementConstructor;

            JBMR11ElementConstructor(Class<?> elementClass) throws SecurityException, NoSuchMethodException {
                this.elementConstructor = elementClass.getConstructor(new Class[]{File.class, File.class, DexFile.class});
                this.elementConstructor.setAccessible(true);
            }

            public Object newInstance(File file, DexFile dex) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
                return this.elementConstructor.newInstance(new Object[]{file, file, dex});
            }
        }

        private static class JBMR2ElementConstructor implements ElementConstructor {
            private final Constructor<?> elementConstructor;

            JBMR2ElementConstructor(Class<?> elementClass) throws SecurityException, NoSuchMethodException {
                this.elementConstructor = elementClass.getConstructor(new Class[]{File.class, Boolean.TYPE, File.class, DexFile.class});
                this.elementConstructor.setAccessible(true);
            }

            public Object newInstance(File file, DexFile dex) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
                return this.elementConstructor.newInstance(new Object[]{file, Boolean.FALSE, file, dex});
            }
        }

        static void install(ClassLoader loader, List<? extends File> additionalClassPathEntries) throws IOException, SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
            Object dexPathList = MultiDex.findField(loader, "pathList").get(loader);
            Object[] elements = new V14().makeDexElements(additionalClassPathEntries);
            try {
                MultiDex.expandFieldArray(dexPathList, "dexElements", elements);
            } catch (NoSuchFieldException e) {
                Log.w(MultiDex.TAG, "Failed find field 'dexElements' attempting 'pathElements'", e);
                MultiDex.expandFieldArray(dexPathList, "pathElements", elements);
            }
        }

        private V14() throws ClassNotFoundException, SecurityException, NoSuchMethodException {
            ElementConstructor constructor;
            Class<?> elementClass = Class.forName("dalvik.system.DexPathList$Element");
            try {
                constructor = new ICSElementConstructor(elementClass);
            } catch (NoSuchMethodException e) {
                try {
                    constructor = new JBMR11ElementConstructor(elementClass);
                } catch (NoSuchMethodException e2) {
                    constructor = new JBMR2ElementConstructor(elementClass);
                }
            }
            this.elementConstructor = constructor;
        }

        private Object[] makeDexElements(List<? extends File> files) throws IOException, SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
            Object[] elements = new Object[files.size()];
            for (int i = 0; i < elements.length; i++) {
                File file = (File) files.get(i);
                elements[i] = this.elementConstructor.newInstance(file, DexFile.loadDex(file.getPath(), optimizedPathFor(file), 0));
            }
            return elements;
        }

        private static String optimizedPathFor(File path) {
            File optimizedDirectory = path.getParentFile();
            String fileName = path.getName();
            StringBuilder sb = new StringBuilder();
            sb.append(fileName.substring(0, fileName.length() - EXTRACTED_SUFFIX_LENGTH));
            sb.append(".dex");
            return new File(optimizedDirectory, sb.toString()).getPath();
        }
    }

    private static final class V19 {
        private V19() {
        }

        static void install(ClassLoader loader, List<? extends File> additionalClassPathEntries, File optimizedDirectory) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
            IOException[] dexElementsSuppressedExceptions;
            Object dexPathList = MultiDex.findField(loader, "pathList").get(loader);
            ArrayList<IOException> suppressedExceptions = new ArrayList<>();
            MultiDex.expandFieldArray(dexPathList, "dexElements", makeDexElements(dexPathList, new ArrayList(additionalClassPathEntries), optimizedDirectory, suppressedExceptions));
            if (suppressedExceptions.size() > 0) {
                Iterator it = suppressedExceptions.iterator();
                while (it.hasNext()) {
                    Log.w(MultiDex.TAG, "Exception in makeDexElement", (IOException) it.next());
                }
                Field suppressedExceptionsField = MultiDex.findField(dexPathList, "dexElementsSuppressedExceptions");
                IOException[] dexElementsSuppressedExceptions2 = (IOException[]) suppressedExceptionsField.get(dexPathList);
                if (dexElementsSuppressedExceptions2 == null) {
                    dexElementsSuppressedExceptions = (IOException[]) suppressedExceptions.toArray(new IOException[suppressedExceptions.size()]);
                } else {
                    IOException[] combined = new IOException[(suppressedExceptions.size() + dexElementsSuppressedExceptions2.length)];
                    suppressedExceptions.toArray(combined);
                    System.arraycopy(dexElementsSuppressedExceptions2, 0, combined, suppressedExceptions.size(), dexElementsSuppressedExceptions2.length);
                    dexElementsSuppressedExceptions = combined;
                }
                suppressedExceptionsField.set(dexPathList, dexElementsSuppressedExceptions);
                IOException exception = new IOException("I/O exception during makeDexElement");
                exception.initCause((Throwable) suppressedExceptions.get(0));
                throw exception;
            }
        }

        private static Object[] makeDexElements(Object dexPathList, ArrayList<File> files, File optimizedDirectory, ArrayList<IOException> suppressedExceptions) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            return (Object[]) MultiDex.findMethod(dexPathList, "makeDexElements", ArrayList.class, File.class, ArrayList.class).invoke(dexPathList, new Object[]{files, optimizedDirectory, suppressedExceptions});
        }
    }

    /* renamed from: androidx.multidex.MultiDex$V4 */
    private static final class C0275V4 {
        private C0275V4() {
        }

        static void install(ClassLoader loader, List<? extends File> additionalClassPathEntries) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
            int extraSize = additionalClassPathEntries.size();
            Field pathField = MultiDex.findField(loader, ClientCookie.PATH_ATTR);
            StringBuilder path = new StringBuilder((String) pathField.get(loader));
            String[] extraPaths = new String[extraSize];
            File[] extraFiles = new File[extraSize];
            ZipFile[] extraZips = new ZipFile[extraSize];
            DexFile[] extraDexs = new DexFile[extraSize];
            ListIterator<? extends File> iterator = additionalClassPathEntries.listIterator();
            while (iterator.hasNext()) {
                File additionalEntry = (File) iterator.next();
                String entryPath = additionalEntry.getAbsolutePath();
                path.append(':');
                path.append(entryPath);
                int index = iterator.previousIndex();
                extraPaths[index] = entryPath;
                extraFiles[index] = additionalEntry;
                extraZips[index] = new ZipFile(additionalEntry);
                StringBuilder sb = new StringBuilder();
                sb.append(entryPath);
                sb.append(".dex");
                extraDexs[index] = DexFile.loadDex(entryPath, sb.toString(), 0);
            }
            pathField.set(loader, path.toString());
            MultiDex.expandFieldArray(loader, "mPaths", extraPaths);
            MultiDex.expandFieldArray(loader, "mFiles", extraFiles);
            MultiDex.expandFieldArray(loader, "mZips", extraZips);
            MultiDex.expandFieldArray(loader, "mDexs", extraDexs);
        }
    }

    private MultiDex() {
    }

    public static void install(Context context) {
        String str = TAG;
        Log.i(str, "Installing application");
        if (IS_VM_MULTIDEX_CAPABLE) {
            Log.i(str, "VM has multidex support, MultiDex support library is disabled.");
        } else if (VERSION.SDK_INT >= 4) {
            try {
                ApplicationInfo applicationInfo = getApplicationInfo(context);
                if (applicationInfo == null) {
                    Log.i(str, "No ApplicationInfo available, i.e. running on a test Context: MultiDex support library is disabled.");
                    return;
                }
                doInstallation(context, new File(applicationInfo.sourceDir), new File(applicationInfo.dataDir), "secondary-dexes", "", true);
                Log.i(str, "install done");
            } catch (Exception e) {
                Log.e(str, "MultiDex installation failure", e);
                StringBuilder sb = new StringBuilder();
                sb.append("MultiDex installation failed (");
                sb.append(e.getMessage());
                sb.append(").");
                throw new RuntimeException(sb.toString());
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("MultiDex installation failed. SDK ");
            sb2.append(VERSION.SDK_INT);
            sb2.append(" is unsupported. Min SDK version is ");
            sb2.append(4);
            sb2.append(".");
            throw new RuntimeException(sb2.toString());
        }
    }

    public static void installInstrumentation(Context instrumentationContext, Context targetContext) {
        String str = TAG;
        Log.i(str, "Installing instrumentation");
        if (IS_VM_MULTIDEX_CAPABLE) {
            Log.i(str, "VM has multidex support, MultiDex support library is disabled.");
            return;
        }
        String str2 = ".";
        if (VERSION.SDK_INT >= 4) {
            try {
                ApplicationInfo instrumentationInfo = getApplicationInfo(instrumentationContext);
                if (instrumentationInfo == null) {
                    Log.i(str, "No ApplicationInfo available for instrumentation, i.e. running on a test Context: MultiDex support library is disabled.");
                    return;
                }
                ApplicationInfo applicationInfo = getApplicationInfo(targetContext);
                if (applicationInfo == null) {
                    Log.i(str, "No ApplicationInfo available, i.e. running on a test Context: MultiDex support library is disabled.");
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(instrumentationContext.getPackageName());
                sb.append(str2);
                String instrumentationPrefix = sb.toString();
                File dataDir = new File(applicationInfo.dataDir);
                File file = new File(instrumentationInfo.sourceDir);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(instrumentationPrefix);
                sb2.append("secondary-dexes");
                doInstallation(targetContext, file, dataDir, sb2.toString(), instrumentationPrefix, false);
                doInstallation(targetContext, new File(applicationInfo.sourceDir), dataDir, "secondary-dexes", "", false);
                Log.i(str, "Installation done");
            } catch (Exception e) {
                Log.e(str, "MultiDex installation failure", e);
                StringBuilder sb3 = new StringBuilder();
                sb3.append("MultiDex installation failed (");
                sb3.append(e.getMessage());
                sb3.append(").");
                throw new RuntimeException(sb3.toString());
            }
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("MultiDex installation failed. SDK ");
            sb4.append(VERSION.SDK_INT);
            sb4.append(" is unsupported. Min SDK version is ");
            sb4.append(4);
            sb4.append(str2);
            throw new RuntimeException(sb4.toString());
        }
    }

    private static void doInstallation(Context mainContext, File sourceApk, File dataDir, String secondaryFolderName, String prefsKeyPrefix, boolean reinstallOnPatchRecoverableException) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
        synchronized (installedApk) {
            if (!installedApk.contains(sourceApk)) {
                installedApk.add(sourceApk);
                if (VERSION.SDK_INT > 20) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("MultiDex is not guaranteed to work in SDK version ");
                    sb.append(VERSION.SDK_INT);
                    sb.append(": SDK version higher than ");
                    sb.append(20);
                    sb.append(" should be backed by ");
                    sb.append("runtime with built-in multidex capabilty but it's not the ");
                    sb.append("case here: java.vm.version=\"");
                    sb.append(System.getProperty("java.vm.version"));
                    sb.append("\"");
                    Log.w(str, sb.toString());
                }
                ClassLoader loader = getDexClassloader(mainContext);
                if (loader != null) {
                    try {
                        clearOldDexDir(mainContext);
                    } catch (Throwable t) {
                        Log.w(TAG, "Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning.", t);
                    }
                    File dexDir = getDexDir(mainContext, dataDir, secondaryFolderName);
                    MultiDexExtractor extractor = new MultiDexExtractor(sourceApk, dexDir);
                    IOException closeException = null;
                    try {
                        installSecondaryDexes(loader, dexDir, extractor.load(mainContext, prefsKeyPrefix, false));
                    } catch (IOException e) {
                        if (reinstallOnPatchRecoverableException) {
                            Log.w(TAG, "Failed to install extracted secondary dex files, retrying with forced extraction", e);
                            installSecondaryDexes(loader, dexDir, extractor.load(mainContext, prefsKeyPrefix, true));
                        } else {
                            throw e;
                        }
                    } catch (Throwable th) {
                        try {
                            extractor.close();
                        } catch (IOException e2) {
                            IOException closeException2 = e2;
                        }
                        throw th;
                    }
                    try {
                        extractor.close();
                    } catch (IOException e3) {
                        closeException = e3;
                    }
                    if (closeException != null) {
                        throw closeException;
                    }
                }
            }
        }
    }

    private static ClassLoader getDexClassloader(Context context) {
        String str = TAG;
        try {
            ClassLoader loader = context.getClassLoader();
            if (VERSION.SDK_INT >= 14) {
                if (loader instanceof BaseDexClassLoader) {
                    return loader;
                }
            } else if ((loader instanceof DexClassLoader) || (loader instanceof PathClassLoader)) {
                return loader;
            }
            Log.e(str, "Context class loader is null or not dex-capable. Must be running in test mode. Skip patching.");
            return null;
        } catch (RuntimeException e) {
            Log.w(str, "Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching.", e);
            return null;
        }
    }

    private static ApplicationInfo getApplicationInfo(Context context) {
        try {
            return context.getApplicationInfo();
        } catch (RuntimeException e) {
            Log.w(TAG, "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", e);
            return null;
        }
    }

    static boolean isVMMultidexCapable(String versionString) {
        boolean isMultidexCapable = false;
        if (versionString != null) {
            StringTokenizer tokenizer = new StringTokenizer(versionString, ".");
            String minorToken = null;
            String majorToken = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
            if (tokenizer.hasMoreTokens()) {
                minorToken = tokenizer.nextToken();
            }
            if (!(majorToken == null || minorToken == null)) {
                try {
                    int major = Integer.parseInt(majorToken);
                    int minor = Integer.parseInt(minorToken);
                    boolean z = true;
                    if (major <= 2 && (major != 2 || minor < 1)) {
                        z = false;
                    }
                    isMultidexCapable = z;
                } catch (NumberFormatException e) {
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("VM with version ");
        sb.append(versionString);
        sb.append(isMultidexCapable ? " has multidex support" : " does not have multidex support");
        Log.i(TAG, sb.toString());
        return isMultidexCapable;
    }

    private static void installSecondaryDexes(ClassLoader loader, File dexDir, List<? extends File> files) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException, SecurityException, ClassNotFoundException, InstantiationException {
        if (files.isEmpty()) {
            return;
        }
        if (VERSION.SDK_INT >= 19) {
            V19.install(loader, files, dexDir);
        } else if (VERSION.SDK_INT >= 14) {
            V14.install(loader, files);
        } else {
            C0275V4.install(loader, files);
        }
    }

    /* access modifiers changed from: private */
    public static Field findField(Object instance, String name) throws NoSuchFieldException {
        Class<?> clazz = instance.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(name);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Field ");
        sb.append(name);
        sb.append(" not found in ");
        sb.append(instance.getClass());
        throw new NoSuchFieldException(sb.toString());
    }

    /* access modifiers changed from: private */
    public static Method findMethod(Object instance, String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        Class<?> clazz = instance.getClass();
        while (clazz != null) {
            try {
                Method method = clazz.getDeclaredMethod(name, parameterTypes);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                return method;
            } catch (NoSuchMethodException e) {
                clazz = clazz.getSuperclass();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Method ");
        sb.append(name);
        sb.append(" with parameters ");
        sb.append(Arrays.asList(parameterTypes));
        sb.append(" not found in ");
        sb.append(instance.getClass());
        throw new NoSuchMethodException(sb.toString());
    }

    /* access modifiers changed from: private */
    public static void expandFieldArray(Object instance, String fieldName, Object[] extraElements) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field jlrField = findField(instance, fieldName);
        Object[] original = (Object[]) jlrField.get(instance);
        Object[] combined = (Object[]) Array.newInstance(original.getClass().getComponentType(), original.length + extraElements.length);
        System.arraycopy(original, 0, combined, 0, original.length);
        System.arraycopy(extraElements, 0, combined, original.length, extraElements.length);
        jlrField.set(instance, combined);
    }

    private static void clearOldDexDir(Context context) throws Exception {
        File dexDir = new File(context.getFilesDir(), "secondary-dexes");
        if (dexDir.isDirectory()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Clearing old secondary dex dir (");
            sb.append(dexDir.getPath());
            String str = ").";
            sb.append(str);
            String sb2 = sb.toString();
            String str2 = TAG;
            Log.i(str2, sb2);
            File[] files = dexDir.listFiles();
            if (files == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Failed to list secondary dex dir content (");
                sb3.append(dexDir.getPath());
                sb3.append(str);
                Log.w(str2, sb3.toString());
                return;
            }
            for (File oldFile : files) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Trying to delete old file ");
                sb4.append(oldFile.getPath());
                sb4.append(" of size ");
                sb4.append(oldFile.length());
                Log.i(str2, sb4.toString());
                if (!oldFile.delete()) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("Failed to delete old file ");
                    sb5.append(oldFile.getPath());
                    Log.w(str2, sb5.toString());
                } else {
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("Deleted old file ");
                    sb6.append(oldFile.getPath());
                    Log.i(str2, sb6.toString());
                }
            }
            if (!dexDir.delete()) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append("Failed to delete secondary dex dir ");
                sb7.append(dexDir.getPath());
                Log.w(str2, sb7.toString());
            } else {
                StringBuilder sb8 = new StringBuilder();
                sb8.append("Deleted old secondary dex dir ");
                sb8.append(dexDir.getPath());
                Log.i(str2, sb8.toString());
            }
        }
    }

    private static File getDexDir(Context context, File dataDir, String secondaryFolderName) throws IOException {
        String str = CODE_CACHE_NAME;
        File cache = new File(dataDir, str);
        try {
            mkdirChecked(cache);
        } catch (IOException e) {
            cache = new File(context.getFilesDir(), str);
            mkdirChecked(cache);
        }
        File dexDir = new File(cache, secondaryFolderName);
        mkdirChecked(dexDir);
        return dexDir;
    }

    private static void mkdirChecked(File dir) throws IOException {
        dir.mkdir();
        if (!dir.isDirectory()) {
            File parent = dir.getParentFile();
            String str = "Failed to create dir ";
            String str2 = TAG;
            if (parent == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(dir.getPath());
                sb.append(". Parent file is null.");
                Log.e(str2, sb.toString());
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(dir.getPath());
                sb2.append(". parent file is a dir ");
                sb2.append(parent.isDirectory());
                sb2.append(", a file ");
                sb2.append(parent.isFile());
                sb2.append(", exists ");
                sb2.append(parent.exists());
                sb2.append(", readable ");
                sb2.append(parent.canRead());
                sb2.append(", writable ");
                sb2.append(parent.canWrite());
                Log.e(str2, sb2.toString());
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Failed to create directory ");
            sb3.append(dir.getPath());
            throw new IOException(sb3.toString());
        }
    }
}
