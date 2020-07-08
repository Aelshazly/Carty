package androidx.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

final class MultiDexExtractor implements Closeable {
    private static final int BUFFER_SIZE = 16384;
    private static final String DEX_PREFIX = "classes";
    static final String DEX_SUFFIX = ".dex";
    private static final String EXTRACTED_NAME_EXT = ".classes";
    static final String EXTRACTED_SUFFIX = ".zip";
    private static final String KEY_CRC = "crc";
    private static final String KEY_DEX_CRC = "dex.crc.";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String KEY_DEX_TIME = "dex.time.";
    private static final String KEY_TIME_STAMP = "timestamp";
    private static final String LOCK_FILENAME = "MultiDex.lock";
    private static final int MAX_EXTRACT_ATTEMPTS = 3;
    private static final long NO_VALUE = -1;
    private static final String PREFS_FILE = "multidex.version";
    private static final String TAG = "MultiDex";
    private final FileLock cacheLock;
    private final File dexDir;
    private final FileChannel lockChannel;
    private final RandomAccessFile lockRaf;
    private final File sourceApk;
    private final long sourceCrc;

    private static class ExtractedDex extends File {
        public long crc = -1;

        public ExtractedDex(File dexDir, String fileName) {
            super(dexDir, fileName);
        }
    }

    MultiDexExtractor(File sourceApk2, File dexDir2) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("MultiDexExtractor(");
        sb.append(sourceApk2.getPath());
        sb.append(", ");
        sb.append(dexDir2.getPath());
        sb.append(")");
        String sb2 = sb.toString();
        String str = TAG;
        Log.i(str, sb2);
        this.sourceApk = sourceApk2;
        this.dexDir = dexDir2;
        this.sourceCrc = getZipCrc(sourceApk2);
        File lockFile = new File(dexDir2, LOCK_FILENAME);
        this.lockRaf = new RandomAccessFile(lockFile, "rw");
        try {
            this.lockChannel = this.lockRaf.getChannel();
            try {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Blocking on lock ");
                sb3.append(lockFile.getPath());
                Log.i(str, sb3.toString());
                this.cacheLock = this.lockChannel.lock();
                StringBuilder sb4 = new StringBuilder();
                sb4.append(lockFile.getPath());
                sb4.append(" locked");
                Log.i(str, sb4.toString());
                return;
            } catch (IOException e) {
                e = e;
            } catch (RuntimeException e2) {
                e = e2;
            } catch (Error e3) {
                e = e3;
            }
            closeQuietly(this.lockChannel);
            throw e;
        } catch (IOException | Error | RuntimeException e4) {
            closeQuietly(this.lockRaf);
            throw e4;
        }
    }

    /* access modifiers changed from: 0000 */
    public List<? extends File> load(Context context, String prefsKeyPrefix, boolean forceReload) throws IOException {
        List<ExtractedDex> files;
        StringBuilder sb = new StringBuilder();
        sb.append("MultiDexExtractor.load(");
        sb.append(this.sourceApk.getPath());
        String str = ", ";
        sb.append(str);
        sb.append(forceReload);
        sb.append(str);
        sb.append(prefsKeyPrefix);
        sb.append(")");
        String sb2 = sb.toString();
        String str2 = TAG;
        Log.i(str2, sb2);
        if (this.cacheLock.isValid()) {
            if (forceReload || isModified(context, this.sourceApk, this.sourceCrc, prefsKeyPrefix)) {
                if (forceReload) {
                    Log.i(str2, "Forced extraction must be performed.");
                } else {
                    Log.i(str2, "Detected that extraction must be performed.");
                }
                files = performExtractions();
                putStoredApkInfo(context, prefsKeyPrefix, getTimeStamp(this.sourceApk), this.sourceCrc, files);
            } else {
                try {
                    files = loadExistingExtractions(context, prefsKeyPrefix);
                } catch (IOException ioe) {
                    Log.w(str2, "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", ioe);
                    List<ExtractedDex> files2 = performExtractions();
                    putStoredApkInfo(context, prefsKeyPrefix, getTimeStamp(this.sourceApk), this.sourceCrc, files2);
                    files = files2;
                }
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("load found ");
            sb3.append(files.size());
            sb3.append(" secondary dex files");
            Log.i(str2, sb3.toString());
            return files;
        }
        throw new IllegalStateException("MultiDexExtractor was closed");
    }

    public void close() throws IOException {
        this.cacheLock.release();
        this.lockChannel.close();
        this.lockRaf.close();
    }

    private List<ExtractedDex> loadExistingExtractions(Context context, String prefsKeyPrefix) throws IOException {
        String str = prefsKeyPrefix;
        Log.i(TAG, "loading existing secondary dex files");
        StringBuilder sb = new StringBuilder();
        sb.append(this.sourceApk.getName());
        sb.append(EXTRACTED_NAME_EXT);
        String extractedFilePrefix = sb.toString();
        SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(KEY_DEX_NUMBER);
        int totalDexNumber = multiDexPreferences.getInt(sb2.toString(), 1);
        List<ExtractedDex> files = new ArrayList<>(totalDexNumber - 1);
        int secondaryNumber = 2;
        while (secondaryNumber <= totalDexNumber) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(extractedFilePrefix);
            sb3.append(secondaryNumber);
            sb3.append(EXTRACTED_SUFFIX);
            ExtractedDex extractedFile = new ExtractedDex(this.dexDir, sb3.toString());
            if (extractedFile.isFile()) {
                extractedFile.crc = getZipCrc(extractedFile);
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(KEY_DEX_CRC);
                sb4.append(secondaryNumber);
                long expectedCrc = multiDexPreferences.getLong(sb4.toString(), -1);
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str);
                sb5.append(KEY_DEX_TIME);
                sb5.append(secondaryNumber);
                long expectedModTime = multiDexPreferences.getLong(sb5.toString(), -1);
                long lastModified = extractedFile.lastModified();
                if (expectedModTime == lastModified) {
                    String extractedFilePrefix2 = extractedFilePrefix;
                    SharedPreferences multiDexPreferences2 = multiDexPreferences;
                    if (expectedCrc == extractedFile.crc) {
                        files.add(extractedFile);
                        secondaryNumber++;
                        extractedFilePrefix = extractedFilePrefix2;
                        multiDexPreferences = multiDexPreferences2;
                    }
                } else {
                    SharedPreferences sharedPreferences = multiDexPreferences;
                }
                StringBuilder sb6 = new StringBuilder();
                sb6.append("Invalid extracted dex: ");
                sb6.append(extractedFile);
                sb6.append(" (key \"");
                sb6.append(str);
                sb6.append("\"), expected modification time: ");
                sb6.append(expectedModTime);
                sb6.append(", modification time: ");
                sb6.append(lastModified);
                sb6.append(", expected crc: ");
                sb6.append(expectedCrc);
                sb6.append(", file crc: ");
                sb6.append(extractedFile.crc);
                throw new IOException(sb6.toString());
            }
            StringBuilder sb7 = new StringBuilder();
            sb7.append("Missing extracted secondary dex file '");
            sb7.append(extractedFile.getPath());
            sb7.append("'");
            throw new IOException(sb7.toString());
        }
        return files;
    }

    private static boolean isModified(Context context, File archive, long currentCrc, String prefsKeyPrefix) {
        SharedPreferences prefs = getMultiDexPreferences(context);
        StringBuilder sb = new StringBuilder();
        sb.append(prefsKeyPrefix);
        sb.append("timestamp");
        if (prefs.getLong(sb.toString(), -1) == getTimeStamp(archive)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(prefsKeyPrefix);
            sb2.append(KEY_CRC);
            if (prefs.getLong(sb2.toString(), -1) == currentCrc) {
                return false;
            }
        }
        return true;
    }

    private static long getTimeStamp(File archive) {
        long timeStamp = archive.lastModified();
        if (timeStamp == -1) {
            return timeStamp - 1;
        }
        return timeStamp;
    }

    private static long getZipCrc(File archive) throws IOException {
        long computedValue = ZipUtil.getZipCrc(archive);
        if (computedValue == -1) {
            return computedValue - 1;
        }
        return computedValue;
    }

    private List<ExtractedDex> performExtractions() throws IOException {
        boolean isExtractionSuccessful;
        ExtractedDex extractedFile;
        MultiDexExtractor multiDexExtractor = this;
        String str = DEX_SUFFIX;
        String str2 = "Failed to close resource";
        String str3 = DEX_PREFIX;
        String str4 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append(multiDexExtractor.sourceApk.getName());
        sb.append(EXTRACTED_NAME_EXT);
        String extractedFilePrefix = sb.toString();
        clearDexDir();
        List<ExtractedDex> files = new ArrayList<>();
        ZipFile apk = new ZipFile(multiDexExtractor.sourceApk);
        try {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str3);
            sb2.append(2);
            sb2.append(str);
            ZipEntry dexFile = apk.getEntry(sb2.toString());
            int secondaryNumber = 2;
            while (dexFile != null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(extractedFilePrefix);
                sb3.append(secondaryNumber);
                sb3.append(EXTRACTED_SUFFIX);
                extractedFile = new ExtractedDex(multiDexExtractor.dexDir, sb3.toString());
                files.add(extractedFile);
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Extraction is needed for file ");
                sb4.append(extractedFile);
                Log.i(str4, sb4.toString());
                int numAttempts = 0;
                boolean isExtractionSuccessful2 = false;
                while (numAttempts < 3 && !isExtractionSuccessful2) {
                    int numAttempts2 = numAttempts + 1;
                    extract(apk, dexFile, extractedFile, extractedFilePrefix);
                    extractedFile.crc = getZipCrc(extractedFile);
                    isExtractionSuccessful = true;
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("Extraction ");
                    sb5.append(isExtractionSuccessful2 ? "succeeded" : "failed");
                    sb5.append(" '");
                    sb5.append(extractedFile.getAbsolutePath());
                    sb5.append("': length ");
                    int numAttempts3 = numAttempts2;
                    sb5.append(extractedFile.length());
                    sb5.append(" - crc: ");
                    sb5.append(extractedFile.crc);
                    Log.i(str4, sb5.toString());
                    if (!isExtractionSuccessful2) {
                        extractedFile.delete();
                        if (extractedFile.exists()) {
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append("Failed to delete corrupted secondary dex '");
                            sb6.append(extractedFile.getPath());
                            sb6.append("'");
                            Log.w(str4, sb6.toString());
                        }
                    }
                    numAttempts = numAttempts3;
                }
                if (isExtractionSuccessful2) {
                    secondaryNumber++;
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(str3);
                    sb7.append(secondaryNumber);
                    sb7.append(str);
                    dexFile = apk.getEntry(sb7.toString());
                    multiDexExtractor = this;
                } else {
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append("Could not create zip file ");
                    sb8.append(extractedFile.getAbsolutePath());
                    sb8.append(" for secondary dex (");
                    sb8.append(secondaryNumber);
                    sb8.append(")");
                    throw new IOException(sb8.toString());
                }
            }
            try {
                apk.close();
            } catch (IOException e) {
                Log.w(str4, str2, e);
            }
            return files;
        } catch (IOException e2) {
            isExtractionSuccessful = false;
            StringBuilder sb9 = new StringBuilder();
            sb9.append("Failed to read crc from ");
            sb9.append(extractedFile.getAbsolutePath());
            Log.w(str4, sb9.toString(), e2);
        } finally {
            isExtractionSuccessful = isExtractionSuccessful;
            try {
                apk.close();
            } catch (IOException e3) {
                Log.w(str4, str2, e3);
            }
        }
    }

    private static void putStoredApkInfo(Context context, String keyPrefix, long timeStamp, long crc, List<ExtractedDex> extractedDexes) {
        Editor edit = getMultiDexPreferences(context).edit();
        StringBuilder sb = new StringBuilder();
        sb.append(keyPrefix);
        sb.append("timestamp");
        edit.putLong(sb.toString(), timeStamp);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(keyPrefix);
        sb2.append(KEY_CRC);
        edit.putLong(sb2.toString(), crc);
        StringBuilder sb3 = new StringBuilder();
        sb3.append(keyPrefix);
        sb3.append(KEY_DEX_NUMBER);
        edit.putInt(sb3.toString(), extractedDexes.size() + 1);
        int extractedDexId = 2;
        for (ExtractedDex dex : extractedDexes) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(keyPrefix);
            sb4.append(KEY_DEX_CRC);
            sb4.append(extractedDexId);
            edit.putLong(sb4.toString(), dex.crc);
            StringBuilder sb5 = new StringBuilder();
            sb5.append(keyPrefix);
            sb5.append(KEY_DEX_TIME);
            sb5.append(extractedDexId);
            edit.putLong(sb5.toString(), dex.lastModified());
            extractedDexId++;
        }
        edit.commit();
    }

    private static SharedPreferences getMultiDexPreferences(Context context) {
        return context.getSharedPreferences(PREFS_FILE, VERSION.SDK_INT < 11 ? 0 : 4);
    }

    private void clearDexDir() {
        File[] files = this.dexDir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return !pathname.getName().equals(MultiDexExtractor.LOCK_FILENAME);
            }
        });
        String str = TAG;
        if (files == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to list secondary dex dir content (");
            sb.append(this.dexDir.getPath());
            sb.append(").");
            Log.w(str, sb.toString());
            return;
        }
        for (File oldFile : files) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Trying to delete old file ");
            sb2.append(oldFile.getPath());
            sb2.append(" of size ");
            sb2.append(oldFile.length());
            Log.i(str, sb2.toString());
            if (!oldFile.delete()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Failed to delete old file ");
                sb3.append(oldFile.getPath());
                Log.w(str, sb3.toString());
            } else {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Deleted old file ");
                sb4.append(oldFile.getPath());
                Log.i(str, sb4.toString());
            }
        }
    }

    private static void extract(ZipFile apk, ZipEntry dexFile, File extractTo, String extractedFilePrefix) throws IOException, FileNotFoundException {
        ZipOutputStream out;
        InputStream in = apk.getInputStream(dexFile);
        StringBuilder sb = new StringBuilder();
        sb.append("tmp-");
        sb.append(extractedFilePrefix);
        File tmp = File.createTempFile(sb.toString(), EXTRACTED_SUFFIX, extractTo.getParentFile());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Extracting ");
        sb2.append(tmp.getPath());
        String sb3 = sb2.toString();
        String str = TAG;
        Log.i(str, sb3);
        try {
            out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tmp)));
            ZipEntry classesDex = new ZipEntry("classes.dex");
            classesDex.setTime(dexFile.getTime());
            out.putNextEntry(classesDex);
            byte[] buffer = new byte[16384];
            for (int length = in.read(buffer); length != -1; length = in.read(buffer)) {
                out.write(buffer, 0, length);
            }
            out.closeEntry();
            out.close();
            if (tmp.setReadOnly()) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Renaming to ");
                sb4.append(extractTo.getPath());
                Log.i(str, sb4.toString());
                if (tmp.renameTo(extractTo)) {
                    closeQuietly(in);
                    tmp.delete();
                    return;
                }
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Failed to rename \"");
                sb5.append(tmp.getAbsolutePath());
                sb5.append("\" to \"");
                sb5.append(extractTo.getAbsolutePath());
                sb5.append("\"");
                throw new IOException(sb5.toString());
            }
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Failed to mark readonly \"");
            sb6.append(tmp.getAbsolutePath());
            sb6.append("\" (tmp of \"");
            sb6.append(extractTo.getAbsolutePath());
            sb6.append("\")");
            throw new IOException(sb6.toString());
        } catch (Throwable th) {
            closeQuietly(in);
            tmp.delete();
            throw th;
        }
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w(TAG, "Failed to close resource", e);
        }
    }
}
