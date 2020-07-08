package com.loopj.android.http;

import android.text.TextUtils;
import com.loopj.android.http.RequestParams.FileWrapper;
import com.loopj.android.http.RequestParams.StreamWrapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;
import kotlin.text.Typography;
import org.json.JSONArray;
import org.json.JSONObject;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.message.BasicHeader;

public class JsonStreamerEntity implements HttpEntity {
    private static final int BUFFER_SIZE = 4096;
    private static final UnsupportedOperationException ERR_UNSUPPORTED = new UnsupportedOperationException("Unsupported operation in this implementation.");
    private static final Header HEADER_GZIP_ENCODING = new BasicHeader("Content-Encoding", AsyncHttpClient.ENCODING_GZIP);
    private static final Header HEADER_JSON_CONTENT = new BasicHeader("Content-Type", "application/json");
    private static final byte[] JSON_FALSE = "false".getBytes();
    private static final byte[] JSON_NULL = "null".getBytes();
    private static final byte[] JSON_TRUE = "true".getBytes();
    private static final String LOG_TAG = "JsonStreamerEntity";
    private static final byte[] STREAM_CONTENTS = escape("contents");
    private static final byte[] STREAM_NAME = escape("name");
    private static final byte[] STREAM_TYPE = escape("type");
    private final byte[] buffer = new byte[4096];
    private final Header contentEncoding;
    private final byte[] elapsedField;
    private final Map<String, Object> jsonParams = new HashMap();
    private final ResponseHandlerInterface progressHandler;

    public JsonStreamerEntity(ResponseHandlerInterface progressHandler2, boolean useGZipCompression, String elapsedField2) {
        this.progressHandler = progressHandler2;
        byte[] bArr = null;
        this.contentEncoding = useGZipCompression ? HEADER_GZIP_ENCODING : null;
        if (!TextUtils.isEmpty(elapsedField2)) {
            bArr = escape(elapsedField2);
        }
        this.elapsedField = bArr;
    }

    static byte[] escape(String string) {
        if (string == null) {
            return JSON_NULL;
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append(Typography.quote);
        int length = string.length();
        int pos = -1;
        while (true) {
            pos++;
            if (pos < length) {
                char ch = string.charAt(pos);
                if (ch == 12) {
                    sb.append("\\f");
                } else if (ch == 13) {
                    sb.append("\\r");
                } else if (ch == '\"') {
                    sb.append("\\\"");
                } else if (ch != '\\') {
                    switch (ch) {
                        case 8:
                            sb.append("\\b");
                            break;
                        case 9:
                            sb.append("\\t");
                            break;
                        case 10:
                            sb.append("\\n");
                            break;
                        default:
                            if (ch > 31 && ((ch < 127 || ch > 159) && (ch < 8192 || ch > 8447))) {
                                sb.append(ch);
                                break;
                            } else {
                                String intString = Integer.toHexString(ch);
                                sb.append("\\u");
                                int intLength = 4 - intString.length();
                                for (int zero = 0; zero < intLength; zero++) {
                                    sb.append('0');
                                }
                                sb.append(intString.toUpperCase(Locale.US));
                                break;
                            }
                            break;
                    }
                } else {
                    sb.append("\\\\");
                }
            } else {
                sb.append(Typography.quote);
                return sb.toString().getBytes();
            }
        }
    }

    public void addPart(String key, Object value) {
        this.jsonParams.put(key, value);
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isChunked() {
        return false;
    }

    public boolean isStreaming() {
        return false;
    }

    public long getContentLength() {
        return -1;
    }

    public Header getContentEncoding() {
        return this.contentEncoding;
    }

    public Header getContentType() {
        return HEADER_JSON_CONTENT;
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
    }

    public InputStream getContent() throws IOException, UnsupportedOperationException {
        throw ERR_UNSUPPORTED;
    }

    public void writeTo(OutputStream out) throws IOException {
        String str;
        JsonStreamerEntity jsonStreamerEntity;
        int keysProcessed;
        JsonStreamerEntity jsonStreamerEntity2 = this;
        OutputStream outputStream = out;
        if (outputStream != null) {
            long now = System.currentTimeMillis();
            OutputStream os = jsonStreamerEntity2.contentEncoding != null ? new GZIPOutputStream(outputStream, 4096) : outputStream;
            os.write(123);
            Set<String> keys = jsonStreamerEntity2.jsonParams.keySet();
            int keysCount = keys.size();
            if (keysCount > 0) {
                int keysProcessed2 = 0;
                Iterator it = keys.iterator();
                while (true) {
                    str = "";
                    if (!it.hasNext()) {
                        break;
                    }
                    String key = (String) it.next();
                    int keysProcessed3 = keysProcessed2 + 1;
                    try {
                        Object value = jsonStreamerEntity2.jsonParams.get(key);
                        os.write(escape(key));
                        os.write(58);
                        if (value == null) {
                            os.write(JSON_NULL);
                            jsonStreamerEntity = jsonStreamerEntity2;
                            keysProcessed = keysProcessed3;
                        } else {
                            boolean isFileWrapper = value instanceof FileWrapper;
                            if (!isFileWrapper) {
                                try {
                                    if (value instanceof StreamWrapper) {
                                        keysProcessed = keysProcessed3;
                                    } else if (value instanceof JsonValueInterface) {
                                        os.write(((JsonValueInterface) value).getEscapedJsonValue());
                                        jsonStreamerEntity = jsonStreamerEntity2;
                                        keysProcessed = keysProcessed3;
                                    } else if (value instanceof JSONObject) {
                                        os.write(value.toString().getBytes());
                                        jsonStreamerEntity = jsonStreamerEntity2;
                                        keysProcessed = keysProcessed3;
                                    } else if (value instanceof JSONArray) {
                                        os.write(value.toString().getBytes());
                                        jsonStreamerEntity = jsonStreamerEntity2;
                                        keysProcessed = keysProcessed3;
                                    } else if (value instanceof Boolean) {
                                        os.write(((Boolean) value).booleanValue() ? JSON_TRUE : JSON_FALSE);
                                        jsonStreamerEntity = jsonStreamerEntity2;
                                        keysProcessed = keysProcessed3;
                                    } else if (value instanceof Long) {
                                        try {
                                            StringBuilder sb = new StringBuilder();
                                            keysProcessed = keysProcessed3;
                                            try {
                                                sb.append(((Number) value).longValue());
                                                sb.append(str);
                                                os.write(sb.toString().getBytes());
                                                jsonStreamerEntity = jsonStreamerEntity2;
                                            } catch (Throwable th) {
                                                th = th;
                                                jsonStreamerEntity = jsonStreamerEntity2;
                                                keysProcessed3 = keysProcessed;
                                                os.write(44);
                                                throw th;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            jsonStreamerEntity = jsonStreamerEntity2;
                                            os.write(44);
                                            throw th;
                                        }
                                    } else {
                                        keysProcessed = keysProcessed3;
                                        try {
                                            if (value instanceof Double) {
                                                StringBuilder sb2 = new StringBuilder();
                                                sb2.append(((Number) value).doubleValue());
                                                sb2.append(str);
                                                os.write(sb2.toString().getBytes());
                                                jsonStreamerEntity = this;
                                            } else if (value instanceof Float) {
                                                StringBuilder sb3 = new StringBuilder();
                                                sb3.append(((Number) value).floatValue());
                                                sb3.append(str);
                                                os.write(sb3.toString().getBytes());
                                                jsonStreamerEntity = this;
                                            } else if (value instanceof Integer) {
                                                StringBuilder sb4 = new StringBuilder();
                                                sb4.append(((Number) value).intValue());
                                                sb4.append(str);
                                                os.write(sb4.toString().getBytes());
                                                jsonStreamerEntity = this;
                                            } else {
                                                os.write(escape(value.toString()));
                                                jsonStreamerEntity = this;
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            jsonStreamerEntity = this;
                                            keysProcessed3 = keysProcessed;
                                            os.write(44);
                                            throw th;
                                        }
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    jsonStreamerEntity = this;
                                    if (jsonStreamerEntity.elapsedField != null || keysProcessed3 < keysCount) {
                                        os.write(44);
                                    }
                                    throw th;
                                }
                            } else {
                                keysProcessed = keysProcessed3;
                            }
                            try {
                                os.write(123);
                                if (isFileWrapper) {
                                    jsonStreamerEntity = this;
                                    try {
                                        jsonStreamerEntity.writeToFromFile(os, (FileWrapper) value);
                                    } catch (Throwable th5) {
                                        th = th5;
                                    }
                                } else {
                                    jsonStreamerEntity = this;
                                    try {
                                        jsonStreamerEntity.writeToFromStream(os, (StreamWrapper) value);
                                    } catch (Throwable th6) {
                                        th = th6;
                                        keysProcessed3 = keysProcessed;
                                        os.write(44);
                                        throw th;
                                    }
                                }
                                os.write(125);
                            } catch (Throwable th7) {
                                th = th7;
                                jsonStreamerEntity = this;
                                keysProcessed3 = keysProcessed;
                                os.write(44);
                                throw th;
                            }
                        }
                        if (jsonStreamerEntity.elapsedField == null) {
                            keysProcessed2 = keysProcessed;
                            if (keysProcessed2 >= keysCount) {
                                jsonStreamerEntity2 = jsonStreamerEntity;
                            }
                        } else {
                            keysProcessed2 = keysProcessed;
                        }
                        os.write(44);
                        jsonStreamerEntity2 = jsonStreamerEntity;
                    } catch (Throwable th8) {
                        th = th8;
                        jsonStreamerEntity = jsonStreamerEntity2;
                        os.write(44);
                        throw th;
                    }
                }
                long elapsedTime = System.currentTimeMillis() - now;
                byte[] bArr = jsonStreamerEntity2.elapsedField;
                if (bArr != null) {
                    os.write(bArr);
                    os.write(58);
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(elapsedTime);
                    sb5.append(str);
                    os.write(sb5.toString().getBytes());
                }
                LogInterface logInterface = AsyncHttpClient.log;
                StringBuilder sb6 = new StringBuilder();
                sb6.append("Uploaded JSON in ");
                sb6.append(Math.floor((double) (elapsedTime / 1000)));
                sb6.append(" seconds");
                logInterface.mo22452i(LOG_TAG, sb6.toString());
            } else {
                JsonStreamerEntity jsonStreamerEntity3 = jsonStreamerEntity2;
            }
            os.write(125);
            os.flush();
            AsyncHttpClient.silentCloseOutputStream(os);
            return;
        }
        JsonStreamerEntity jsonStreamerEntity4 = jsonStreamerEntity2;
        throw new IllegalStateException("Output stream cannot be null.");
    }

    private void writeToFromStream(OutputStream os, StreamWrapper entry) throws IOException {
        writeMetaData(os, entry.name, entry.contentType);
        Base64OutputStream bos = new Base64OutputStream(os, 18);
        while (true) {
            int read = entry.inputStream.read(this.buffer);
            int bytesRead = read;
            if (read == -1) {
                break;
            }
            bos.write(this.buffer, 0, bytesRead);
        }
        AsyncHttpClient.silentCloseOutputStream(bos);
        endMetaData(os);
        if (entry.autoClose) {
            AsyncHttpClient.silentCloseInputStream(entry.inputStream);
        }
    }

    private void writeToFromFile(OutputStream os, FileWrapper wrapper) throws IOException {
        writeMetaData(os, wrapper.file.getName(), wrapper.contentType);
        long bytesWritten = 0;
        long totalSize = wrapper.file.length();
        FileInputStream in = new FileInputStream(wrapper.file);
        Base64OutputStream bos = new Base64OutputStream(os, 18);
        while (true) {
            int read = in.read(this.buffer);
            int bytesRead = read;
            if (read != -1) {
                bos.write(this.buffer, 0, bytesRead);
                bytesWritten += (long) bytesRead;
                this.progressHandler.sendProgressMessage(bytesWritten, totalSize);
            } else {
                AsyncHttpClient.silentCloseOutputStream(bos);
                endMetaData(os);
                AsyncHttpClient.silentCloseInputStream(in);
                return;
            }
        }
    }

    private void writeMetaData(OutputStream os, String name, String contentType) throws IOException {
        os.write(STREAM_NAME);
        os.write(58);
        os.write(escape(name));
        os.write(44);
        os.write(STREAM_TYPE);
        os.write(58);
        os.write(escape(contentType));
        os.write(44);
        os.write(STREAM_CONTENTS);
        os.write(58);
        os.write(34);
    }

    private void endMetaData(OutputStream os) throws IOException {
        os.write(34);
    }
}
