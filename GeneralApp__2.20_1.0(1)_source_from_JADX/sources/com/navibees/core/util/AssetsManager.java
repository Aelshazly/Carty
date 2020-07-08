package com.navibees.core.util;

import android.content.Context;
import android.content.res.AssetManager;
import com.navibees.core.C1556a;
import com.navibees.core.NaviBeesConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class AssetsManager {

    /* renamed from: b */
    static final String f1607b = "AssetsManager";

    /* renamed from: a */
    private Context f1608a;

    public AssetsManager(Context context) {
        this.f1608a = context;
    }

    /* renamed from: a */
    private void m1166a(String str) throws IOException {
        try {
            InputStream open = this.f1608a.getAssets().open(str);
            StringBuilder sb = new StringBuilder();
            sb.append(this.f1608a.getFilesDir().getAbsolutePath());
            sb.append("/");
            sb.append(str);
            FileOutputStream fileOutputStream = new FileOutputStream(sb.toString());
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    open.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            Log.m1172e(f1607b, e.getMessage());
            e.printStackTrace();
            throw new IOException(e);
        }
    }

    /* renamed from: a */
    private void m1167a(ZipEntry zipEntry, String str, ZipFile zipFile) {
    }

    /* renamed from: b */
    private Boolean m1169b(String str) {
        AssetManager assets = this.f1608a.getAssets();
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1608a.getFilesDir().getAbsolutePath());
        String str2 = "/";
        sb.append(str2);
        sb.append(str);
        String sb2 = sb.toString();
        try {
            String[] list = assets.list(str);
            if (list.length != 0) {
                File file = new File(sb2);
                if (!file.exists()) {
                    file.mkdir();
                }
                for (String append : list) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str);
                    sb3.append(str2);
                    sb3.append(append);
                    m1169b(sb3.toString());
                }
            } else if (!new File(sb2).exists()) {
                m1166a(str);
            }
            return Boolean.valueOf(true);
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: c */
    private void m1170c(String str) {
        File file = new File(str);
        String[] list = file.list();
        if (list != null) {
            if (list.length == 0) {
                file.delete();
            } else {
                for (String append : list) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append("/");
                    sb.append(append);
                    m1170c(sb.toString());
                }
            }
        }
        if (file.exists()) {
            file.delete();
        }
    }

    public static String getMapResourcesAppPath(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir().getAbsolutePath());
        String str = "/";
        sb.append(str);
        sb.append(NaviBeesConstants.MAP_RESOURCES_FOLDER);
        sb.append(str);
        sb.append(NaviBeesConstants.NAVIBEES_APP_FILE);
        return sb.toString();
    }

    public static String getMapResourcesImagesPath(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir().getAbsolutePath());
        String str = "/";
        sb.append(str);
        sb.append(NaviBeesConstants.MAP_RESOURCES_FOLDER);
        sb.append(str);
        String str2 = NaviBeesConstants.MAP_RESOURCES_FOLDER_IMAGE;
        sb.append(str2);
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String getMapResourcesMetadataPath(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir().getAbsolutePath());
        String str = "/";
        sb.append(str);
        sb.append(NaviBeesConstants.MAP_RESOURCES_FOLDER);
        sb.append(str);
        String str2 = NaviBeesConstants.MAP_RESOURCES_FOLDER_META_DATA;
        sb.append(str2);
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String getMapResourcesNetworkDatasetsPath(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir().getAbsolutePath());
        String str = "/";
        sb.append(str);
        sb.append(NaviBeesConstants.MAP_RESOURCES_FOLDER);
        sb.append(str);
        String str2 = NaviBeesConstants.MAP_RESOURCES_FOLDER_NETWORK_DATASETS;
        sb.append(str2);
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String getMapResourcesTiledLayerPath(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir().getAbsolutePath());
        String str = "/";
        sb.append(str);
        sb.append(NaviBeesConstants.MAP_RESOURCES_FOLDER);
        sb.append(str);
        String str2 = NaviBeesConstants.MAP_RESOURCES_FOLDER_TILED_LAYERS;
        sb.append(str2);
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public boolean copyAssetsFolder() {
        if (!C1556a.m636e(this.f1608a)) {
            return true;
        }
        deleteMapResources();
        String str = NaviBeesConstants.MAP_RESOURCES_FOLDER;
        if (!m1169b(str).booleanValue()) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1608a.getFilesDir().getAbsolutePath());
        String str2 = "/";
        sb.append(str2);
        sb.append(str);
        sb.append(str2);
        sb.append(NaviBeesConstants.MAP_RESOURCES_FOLDER_IMAGE);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.f1608a.getFilesDir().getAbsolutePath());
        sb3.append(str2);
        sb3.append(str);
        sb3.append(str2);
        sb3.append(NaviBeesConstants.MAP_RESOURCES_FOLDER_META_DATA);
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(this.f1608a.getFilesDir().getAbsolutePath());
        sb5.append(str2);
        sb5.append(str);
        sb5.append(str2);
        sb5.append(NaviBeesConstants.MAP_RESOURCES_FOLDER_NETWORK_DATASETS);
        String sb6 = sb5.toString();
        StringBuilder sb7 = new StringBuilder();
        sb7.append(this.f1608a.getFilesDir().getAbsolutePath());
        sb7.append(str2);
        sb7.append(str);
        sb7.append(str2);
        sb7.append(NaviBeesConstants.MAP_RESOURCES_FOLDER_TILED_LAYERS);
        String sb8 = sb7.toString();
        StringBuilder sb9 = new StringBuilder();
        sb9.append(sb2);
        String str3 = ".zip";
        sb9.append(str3);
        File file = new File(sb9.toString());
        StringBuilder sb10 = new StringBuilder();
        sb10.append(sb4);
        sb10.append(str3);
        File file2 = new File(sb10.toString());
        StringBuilder sb11 = new StringBuilder();
        sb11.append(sb6);
        sb11.append(str3);
        File file3 = new File(sb11.toString());
        StringBuilder sb12 = new StringBuilder();
        sb12.append(sb8);
        sb12.append(str3);
        File file4 = new File(sb12.toString());
        boolean a = m1168a(file2, sb4);
        m1168a(file3, sb6);
        m1168a(file, sb2);
        m1168a(file4, sb8);
        return a;
    }

    public void deleteMapResources() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1608a.getFilesDir().getAbsolutePath());
        sb.append("/");
        sb.append(NaviBeesConstants.MAP_RESOURCES_FOLDER);
        m1170c(sb.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0151, code lost:
        if (r6 == null) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x017a, code lost:
        if (r6 == null) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x017c, code lost:
        r6.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x014e A[SYNTHETIC, Splitter:B:64:0x014e] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0174 A[SYNTHETIC, Splitter:B:72:0x0174] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0183 A[SYNTHETIC, Splitter:B:81:0x0183] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x018b A[Catch:{ IOException -> 0x0187 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:61:0x0131=Splitter:B:61:0x0131, B:69:0x0157=Splitter:B:69:0x0157} */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m1168a(java.io.File r14, java.lang.String r15) {
        /*
            r13 = this;
            java.lang.String r0 = "Zip Path Traversal Vulnerability Detected"
            java.lang.String r1 = "AssetsManager"
            r2 = 0
            r3 = 0
            java.util.zip.ZipFile r4 = new java.util.zip.ZipFile     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            r4.<init>(r14)     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            java.io.File r5 = new java.io.File     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            r5.<init>(r15)     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            boolean r6 = r5.isDirectory()     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            if (r6 == 0) goto L_0x0037
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            r0.<init>()     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            java.lang.String r4 = "Destination directory already exists for the MapResources:folder:"
            r0.append(r4)     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            java.lang.String r4 = r14.getName()     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            r0.append(r4)     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            java.lang.String r4 = " , destination_dir = "
            r0.append(r4)     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            r0.append(r15)     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            java.lang.String r15 = r0.toString()     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            com.navibees.core.util.Log.m1171d(r1, r15)     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            return r3
        L_0x0037:
            boolean r5 = r5.mkdirs()     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            if (r5 != 0) goto L_0x0056
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            r15.<init>()     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            java.lang.String r0 = "Failed to create destination directory for the MapResources:folder:"
            r15.append(r0)     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            java.lang.String r0 = r14.getName()     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            r15.append(r0)     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            java.lang.String r15 = r15.toString()     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            com.navibees.core.util.Log.m1171d(r1, r15)     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            return r3
        L_0x0056:
            java.util.Enumeration r5 = r4.entries()     // Catch:{ IOException -> 0x0154, SecurityException -> 0x012e, all -> 0x012a }
            r6 = r2
        L_0x005b:
            boolean r7 = r5.hasMoreElements()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            if (r7 == 0) goto L_0x0111
            java.lang.Object r7 = r5.nextElement()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.util.zip.ZipEntry r7 = (java.util.zip.ZipEntry) r7     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.lang.String r8 = r14.getCanonicalPath()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            r9.<init>()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.io.File r10 = new java.io.File     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            r11.<init>()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            android.content.Context r12 = r13.f1608a     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.io.File r12 = r12.getFilesDir()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.lang.String r12 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            r11.append(r12)     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.lang.String r12 = "/"
            r11.append(r12)     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.lang.String r12 = "MapResources"
            r11.append(r12)     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.lang.String r11 = r11.toString()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            r10.<init>(r11)     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.lang.String r10 = r10.getCanonicalPath()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            r9.append(r10)     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.lang.String r10 = java.io.File.separator     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            r9.append(r10)     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            boolean r8 = r8.startsWith(r9)     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            if (r8 == 0) goto L_0x0106
            java.io.File r8 = new java.io.File     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.lang.String r9 = r7.getName()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            r8.<init>(r15, r9)     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.io.File r9 = r8.getParentFile()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            r9.mkdirs()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            boolean r9 = r7.isDirectory()     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            if (r9 != 0) goto L_0x0103
            java.io.BufferedInputStream r9 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.io.InputStream r7 = r4.getInputStream(r7)     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            r9.<init>(r7)     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0101, SecurityException -> 0x00ff, all -> 0x00fc }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0101, SecurityException -> 0x00ff, all -> 0x00fc }
            r7.<init>(r8)     // Catch:{ IOException -> 0x0101, SecurityException -> 0x00ff, all -> 0x00fc }
            r2.<init>(r7)     // Catch:{ IOException -> 0x0101, SecurityException -> 0x00ff, all -> 0x00fc }
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r6]     // Catch:{ IOException -> 0x00f8, SecurityException -> 0x00f5, all -> 0x00f1 }
        L_0x00d8:
            int r8 = r9.read(r7, r3, r6)     // Catch:{ IOException -> 0x00f8, SecurityException -> 0x00f5, all -> 0x00f1 }
            r10 = -1
            if (r8 == r10) goto L_0x00e4
            r2.write(r7, r3, r8)     // Catch:{ IOException -> 0x00f8, SecurityException -> 0x00f5, all -> 0x00f1 }
            goto L_0x00d8
        L_0x00e4:
            r2.flush()     // Catch:{ IOException -> 0x00f8, SecurityException -> 0x00f5, all -> 0x00f1 }
            r2.close()     // Catch:{ IOException -> 0x00f8, SecurityException -> 0x00f5, all -> 0x00f1 }
            r9.close()     // Catch:{ IOException -> 0x00f8, SecurityException -> 0x00f5, all -> 0x00f1 }
            r6 = r2
            r2 = r9
            goto L_0x005b
        L_0x00f1:
            r14 = move-exception
            r6 = r2
            goto L_0x0181
        L_0x00f5:
            r15 = move-exception
            r6 = r2
            goto L_0x0131
        L_0x00f8:
            r15 = move-exception
            r6 = r2
            goto L_0x0157
        L_0x00fc:
            r14 = move-exception
            goto L_0x0181
        L_0x00ff:
            r15 = move-exception
            goto L_0x0131
        L_0x0101:
            r15 = move-exception
            goto L_0x0157
        L_0x0103:
            goto L_0x005b
        L_0x0106:
            java.io.PrintStream r15 = java.lang.System.out     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            r15.println(r0)     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            java.lang.SecurityException r15 = new java.lang.SecurityException     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            r15.<init>(r0)     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
            throw r15     // Catch:{ IOException -> 0x0127, SecurityException -> 0x0124, all -> 0x0120 }
        L_0x0111:
            r14 = 1
            if (r2 == 0) goto L_0x011a
            r2.close()     // Catch:{ IOException -> 0x0118 }
            goto L_0x011a
        L_0x0118:
            r15 = move-exception
            goto L_0x011f
        L_0x011a:
            if (r6 == 0) goto L_0x011f
            r6.close()     // Catch:{ IOException -> 0x0118 }
        L_0x011f:
            return r14
        L_0x0120:
            r14 = move-exception
            r9 = r2
            goto L_0x0181
        L_0x0124:
            r15 = move-exception
            r9 = r2
            goto L_0x0131
        L_0x0127:
            r15 = move-exception
            r9 = r2
            goto L_0x0157
        L_0x012a:
            r14 = move-exception
            r6 = r2
            r9 = r6
            goto L_0x0181
        L_0x012e:
            r15 = move-exception
            r6 = r2
            r9 = r6
        L_0x0131:
            r15.printStackTrace()     // Catch:{ all -> 0x0180 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x0180 }
            r15.<init>()     // Catch:{ all -> 0x0180 }
            java.lang.String r0 = "Zip Path Traversal Vulnerability Detected in: "
            r15.append(r0)     // Catch:{ all -> 0x0180 }
            java.lang.String r14 = r14.getName()     // Catch:{ all -> 0x0180 }
            r15.append(r14)     // Catch:{ all -> 0x0180 }
            java.lang.String r14 = r15.toString()     // Catch:{ all -> 0x0180 }
            com.navibees.core.util.Log.m1171d(r1, r14)     // Catch:{ all -> 0x0180 }
            if (r9 == 0) goto L_0x0151
            r9.close()     // Catch:{ IOException -> 0x0178 }
        L_0x0151:
            if (r6 == 0) goto L_0x017f
            goto L_0x017c
        L_0x0154:
            r15 = move-exception
            r6 = r2
            r9 = r6
        L_0x0157:
            r15.printStackTrace()     // Catch:{ all -> 0x0180 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x0180 }
            r15.<init>()     // Catch:{ all -> 0x0180 }
            java.lang.String r0 = "Failed to unpack the MapResources:folder:"
            r15.append(r0)     // Catch:{ all -> 0x0180 }
            java.lang.String r14 = r14.getName()     // Catch:{ all -> 0x0180 }
            r15.append(r14)     // Catch:{ all -> 0x0180 }
            java.lang.String r14 = r15.toString()     // Catch:{ all -> 0x0180 }
            com.navibees.core.util.Log.m1171d(r1, r14)     // Catch:{ all -> 0x0180 }
            if (r9 == 0) goto L_0x017a
            r9.close()     // Catch:{ IOException -> 0x0178 }
            goto L_0x017a
        L_0x0178:
            r14 = move-exception
            goto L_0x017f
        L_0x017a:
            if (r6 == 0) goto L_0x017f
        L_0x017c:
            r6.close()     // Catch:{ IOException -> 0x0178 }
        L_0x017f:
            return r3
        L_0x0180:
            r14 = move-exception
        L_0x0181:
            if (r9 == 0) goto L_0x0189
            r9.close()     // Catch:{ IOException -> 0x0187 }
            goto L_0x0189
        L_0x0187:
            r15 = move-exception
            goto L_0x018e
        L_0x0189:
            if (r6 == 0) goto L_0x018e
            r6.close()     // Catch:{ IOException -> 0x0187 }
        L_0x018e:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.core.util.AssetsManager.m1168a(java.io.File, java.lang.String):boolean");
    }
}
