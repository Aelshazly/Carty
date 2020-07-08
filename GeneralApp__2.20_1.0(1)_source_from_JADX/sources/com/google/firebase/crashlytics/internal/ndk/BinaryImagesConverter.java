package com.google.firebase.crashlytics.internal.ndk;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import com.google.firebase.crashlytics.internal.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class BinaryImagesConverter {
    private static final String DATA_DIR = "/data";
    private final Context context;
    private final FileIdStrategy fileIdStrategy;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    interface FileIdStrategy {
        String createId(File file) throws IOException;
    }

    BinaryImagesConverter(Context context2, FileIdStrategy fileIdStrategy2) {
        this.context = context2;
        this.fileIdStrategy = fileIdStrategy2;
    }

    /* access modifiers changed from: 0000 */
    public byte[] convert(String raw) throws IOException {
        return generateBinaryImagesJsonString(parseProcMapsJsonFromString(raw));
    }

    /* access modifiers changed from: 0000 */
    public byte[] convert(BufferedReader reader) throws IOException {
        return generateBinaryImagesJsonString(parseProcMapsJsonFromStream(reader));
    }

    private JSONArray parseProcMapsJsonFromStream(BufferedReader reader) throws IOException {
        JSONArray binaryImagesJson = new JSONArray();
        while (true) {
            String readLine = reader.readLine();
            String mapEntryString = readLine;
            if (readLine == null) {
                return binaryImagesJson;
            }
            JSONObject mapJson = jsonFromMapEntryString(mapEntryString);
            if (mapJson != null) {
                binaryImagesJson.put(mapJson);
            }
        }
    }

    private JSONArray parseProcMapsJsonFromString(String rawProcMapsString) {
        JSONArray binaryImagesJson = new JSONArray();
        try {
            String[] mapsEntries = joinMapsEntries(new JSONObject(rawProcMapsString).getJSONArray("maps")).split("\\|");
            for (String mapEntryString : mapsEntries) {
                JSONObject mapJson = jsonFromMapEntryString(mapEntryString);
                if (mapJson != null) {
                    binaryImagesJson.put(mapJson);
                }
            }
            return binaryImagesJson;
        } catch (JSONException e) {
            Logger.getLogger().mo19690w("Unable to parse proc maps string", e);
            return binaryImagesJson;
        }
    }

    private JSONObject jsonFromMapEntryString(String mapEntryString) {
        ProcMapEntry mapInfo = ProcMapEntryParser.parse(mapEntryString);
        if (mapInfo == null || !isRelevant(mapInfo)) {
            return null;
        }
        try {
            try {
                return createBinaryImageJson(this.fileIdStrategy.createId(getLibraryFile(mapInfo.path)), mapInfo);
            } catch (JSONException e) {
                Logger.getLogger().mo19680d("Could not create a binary image json string", e);
                return null;
            }
        } catch (IOException e2) {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Could not generate ID for file ");
            sb.append(mapInfo.path);
            logger.mo19680d(sb.toString(), e2);
            return null;
        }
    }

    private File getLibraryFile(String path) {
        File libFile = new File(path);
        if (!libFile.exists()) {
            return correctDataPath(libFile);
        }
        return libFile;
    }

    private File correctDataPath(File missingFile) {
        if (VERSION.SDK_INT < 9) {
            return missingFile;
        }
        if (missingFile.getAbsolutePath().startsWith(DATA_DIR)) {
            try {
                missingFile = new File(this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 0).nativeLibraryDir, missingFile.getName());
            } catch (NameNotFoundException e) {
                Logger.getLogger().mo19682e("Error getting ApplicationInfo", e);
            }
        }
        return missingFile;
    }

    private static byte[] generateBinaryImagesJsonString(JSONArray binaryImages) {
        JSONObject binaryImagesObject = new JSONObject();
        try {
            binaryImagesObject.put("binary_images", binaryImages);
            return binaryImagesObject.toString().getBytes(Charset.forName("UTF-8"));
        } catch (JSONException e) {
            Logger.getLogger().mo19690w("Binary images string is null", e);
            return new byte[0];
        }
    }

    private static JSONObject createBinaryImageJson(String uuid, ProcMapEntry mapEntry) throws JSONException {
        JSONObject binaryImage = new JSONObject();
        binaryImage.put("base_address", mapEntry.address);
        binaryImage.put("size", mapEntry.size);
        binaryImage.put("name", mapEntry.path);
        binaryImage.put("uuid", uuid);
        return binaryImage;
    }

    private static String joinMapsEntries(JSONArray array) throws JSONException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length(); i++) {
            sb.append(array.getString(i));
        }
        return sb.toString();
    }

    private static boolean isRelevant(ProcMapEntry mapEntry) {
        return (mapEntry.perms.indexOf(120) == -1 || mapEntry.path.indexOf(47) == -1) ? false : true;
    }
}
