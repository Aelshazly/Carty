package com.loopj.android.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import p008cz.msebera.android.httpclient.client.utils.URLEncodedUtils;
import p008cz.msebera.android.httpclient.message.BasicNameValuePair;

public class RequestParams implements Serializable {
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    protected static final String LOG_TAG = "RequestParams";
    protected boolean autoCloseInputStreams;
    protected String contentEncoding;
    protected String elapsedFieldInJsonStreamer;
    protected final ConcurrentHashMap<String, List<FileWrapper>> fileArrayParams;
    protected final ConcurrentHashMap<String, FileWrapper> fileParams;
    protected boolean forceMultipartEntity;
    protected boolean isRepeatable;
    protected final ConcurrentHashMap<String, StreamWrapper> streamParams;
    protected final ConcurrentHashMap<String, String> urlParams;
    protected final ConcurrentHashMap<String, Object> urlParamsWithObjects;
    protected boolean useJsonStreamer;

    public static class FileWrapper implements Serializable {
        public final String contentType;
        public final String customFileName;
        public final File file;

        public FileWrapper(File file2, String contentType2, String customFileName2) {
            this.file = file2;
            this.contentType = contentType2;
            this.customFileName = customFileName2;
        }
    }

    public static class StreamWrapper {
        public final boolean autoClose;
        public final String contentType;
        public final InputStream inputStream;
        public final String name;

        public StreamWrapper(InputStream inputStream2, String name2, String contentType2, boolean autoClose2) {
            this.inputStream = inputStream2;
            this.name = name2;
            this.contentType = contentType2;
            this.autoClose = autoClose2;
        }

        static StreamWrapper newInstance(InputStream inputStream2, String name2, String contentType2, boolean autoClose2) {
            return new StreamWrapper(inputStream2, name2, contentType2 == null ? "application/octet-stream" : contentType2, autoClose2);
        }
    }

    public RequestParams() {
        this(null);
    }

    public RequestParams(Map<String, String> source) {
        this.urlParams = new ConcurrentHashMap<>();
        this.streamParams = new ConcurrentHashMap<>();
        this.fileParams = new ConcurrentHashMap<>();
        this.fileArrayParams = new ConcurrentHashMap<>();
        this.urlParamsWithObjects = new ConcurrentHashMap<>();
        this.forceMultipartEntity = false;
        this.elapsedFieldInJsonStreamer = "_elapsed";
        this.contentEncoding = "UTF-8";
        if (source != null) {
            for (Entry<String, String> entry : source.entrySet()) {
                put((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    public RequestParams(final String key, final String value) {
        this((Map<String, String>) new HashMap<String, String>() {
            {
                put(key, value);
            }
        });
    }

    public RequestParams(Object... keysAndValues) {
        this.urlParams = new ConcurrentHashMap<>();
        this.streamParams = new ConcurrentHashMap<>();
        this.fileParams = new ConcurrentHashMap<>();
        this.fileArrayParams = new ConcurrentHashMap<>();
        this.urlParamsWithObjects = new ConcurrentHashMap<>();
        this.forceMultipartEntity = false;
        this.elapsedFieldInJsonStreamer = "_elapsed";
        this.contentEncoding = "UTF-8";
        int len = keysAndValues.length;
        if (len % 2 == 0) {
            for (int i = 0; i < len; i += 2) {
                put(String.valueOf(keysAndValues[i]), String.valueOf(keysAndValues[i + 1]));
            }
            return;
        }
        throw new IllegalArgumentException("Supplied arguments must be even");
    }

    public void setContentEncoding(String encoding) {
        if (encoding != null) {
            this.contentEncoding = encoding;
        } else {
            AsyncHttpClient.log.mo22447d(LOG_TAG, "setContentEncoding called with null attribute");
        }
    }

    public void setForceMultipartEntityContentType(boolean force) {
        this.forceMultipartEntity = force;
    }

    public void put(String key, String value) {
        if (key != null && value != null) {
            this.urlParams.put(key, value);
        }
    }

    public void put(String key, File[] files) throws FileNotFoundException {
        put(key, files, (String) null, (String) null);
    }

    public void put(String key, File[] files, String contentType, String customFileName) throws FileNotFoundException {
        if (key != null) {
            List<FileWrapper> fileWrappers = new ArrayList<>();
            for (File file : files) {
                if (file == null || !file.exists()) {
                    throw new FileNotFoundException();
                }
                fileWrappers.add(new FileWrapper(file, contentType, customFileName));
            }
            this.fileArrayParams.put(key, fileWrappers);
        }
    }

    public void put(String key, File file) throws FileNotFoundException {
        put(key, file, (String) null, (String) null);
    }

    public void put(String key, String customFileName, File file) throws FileNotFoundException {
        put(key, file, (String) null, customFileName);
    }

    public void put(String key, File file, String contentType) throws FileNotFoundException {
        put(key, file, contentType, (String) null);
    }

    public void put(String key, File file, String contentType, String customFileName) throws FileNotFoundException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException();
        } else if (key != null) {
            this.fileParams.put(key, new FileWrapper(file, contentType, customFileName));
        }
    }

    public void put(String key, InputStream stream) {
        put(key, stream, (String) null);
    }

    public void put(String key, InputStream stream, String name) {
        put(key, stream, name, (String) null);
    }

    public void put(String key, InputStream stream, String name, String contentType) {
        put(key, stream, name, contentType, this.autoCloseInputStreams);
    }

    public void put(String key, InputStream stream, String name, String contentType, boolean autoClose) {
        if (key != null && stream != null) {
            this.streamParams.put(key, StreamWrapper.newInstance(stream, name, contentType, autoClose));
        }
    }

    public void put(String key, Object value) {
        if (key != null && value != null) {
            this.urlParamsWithObjects.put(key, value);
        }
    }

    public void put(String key, int value) {
        if (key != null) {
            this.urlParams.put(key, String.valueOf(value));
        }
    }

    public void put(String key, long value) {
        if (key != null) {
            this.urlParams.put(key, String.valueOf(value));
        }
    }

    public void add(String key, String value) {
        if (key != null && value != null) {
            Object params = this.urlParamsWithObjects.get(key);
            if (params == null) {
                params = new HashSet();
                put(key, params);
            }
            if (params instanceof List) {
                ((List) params).add(value);
            } else if (params instanceof Set) {
                ((Set) params).add(value);
            }
        }
    }

    public void remove(String key) {
        this.urlParams.remove(key);
        this.streamParams.remove(key);
        this.fileParams.remove(key);
        this.urlParamsWithObjects.remove(key);
        this.fileArrayParams.remove(key);
    }

    public boolean has(String key) {
        return (this.urlParams.get(key) == null && this.streamParams.get(key) == null && this.fileParams.get(key) == null && this.urlParamsWithObjects.get(key) == null && this.fileArrayParams.get(key) == null) ? false : true;
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder result = new StringBuilder();
        Iterator it = this.urlParams.entrySet().iterator();
        while (true) {
            str = "&";
            str2 = "=";
            if (!it.hasNext()) {
                break;
            }
            Entry<String, String> entry = (Entry) it.next();
            if (result.length() > 0) {
                result.append(str);
            }
            result.append((String) entry.getKey());
            result.append(str2);
            result.append((String) entry.getValue());
        }
        for (Entry<String, StreamWrapper> entry2 : this.streamParams.entrySet()) {
            if (result.length() > 0) {
                result.append(str);
            }
            result.append((String) entry2.getKey());
            result.append(str2);
            result.append("STREAM");
        }
        for (Entry<String, FileWrapper> entry3 : this.fileParams.entrySet()) {
            if (result.length() > 0) {
                result.append(str);
            }
            result.append((String) entry3.getKey());
            result.append(str2);
            result.append("FILE");
        }
        for (Entry<String, List<FileWrapper>> entry4 : this.fileArrayParams.entrySet()) {
            if (result.length() > 0) {
                result.append(str);
            }
            result.append((String) entry4.getKey());
            result.append(str2);
            result.append("FILES(SIZE=");
            result.append(((List) entry4.getValue()).size());
            result.append(")");
        }
        for (BasicNameValuePair kv : getParamsList(null, this.urlParamsWithObjects)) {
            if (result.length() > 0) {
                result.append(str);
            }
            result.append(kv.getName());
            result.append(str2);
            result.append(kv.getValue());
        }
        return result.toString();
    }

    public void setHttpEntityIsRepeatable(boolean flag) {
        this.isRepeatable = flag;
    }

    public void setUseJsonStreamer(boolean flag) {
        this.useJsonStreamer = flag;
    }

    public void setElapsedFieldInJsonStreamer(String value) {
        this.elapsedFieldInJsonStreamer = value;
    }

    public void setAutoCloseInputStreams(boolean flag) {
        this.autoCloseInputStreams = flag;
    }

    public HttpEntity getEntity(ResponseHandlerInterface progressHandler) throws IOException {
        if (this.useJsonStreamer) {
            return createJsonStreamerEntity(progressHandler);
        }
        if (this.forceMultipartEntity || !this.streamParams.isEmpty() || !this.fileParams.isEmpty() || !this.fileArrayParams.isEmpty()) {
            return createMultipartEntity(progressHandler);
        }
        return createFormEntity();
    }

    private HttpEntity createJsonStreamerEntity(ResponseHandlerInterface progressHandler) throws IOException {
        JsonStreamerEntity entity = new JsonStreamerEntity(progressHandler, !this.fileParams.isEmpty() || !this.streamParams.isEmpty(), this.elapsedFieldInJsonStreamer);
        for (Entry<String, String> entry : this.urlParams.entrySet()) {
            entity.addPart((String) entry.getKey(), entry.getValue());
        }
        for (Entry<String, Object> entry2 : this.urlParamsWithObjects.entrySet()) {
            entity.addPart((String) entry2.getKey(), entry2.getValue());
        }
        for (Entry<String, FileWrapper> entry3 : this.fileParams.entrySet()) {
            entity.addPart((String) entry3.getKey(), entry3.getValue());
        }
        for (Entry<String, StreamWrapper> entry4 : this.streamParams.entrySet()) {
            StreamWrapper stream = (StreamWrapper) entry4.getValue();
            if (stream.inputStream != null) {
                entity.addPart((String) entry4.getKey(), StreamWrapper.newInstance(stream.inputStream, stream.name, stream.contentType, stream.autoClose));
            }
        }
        return entity;
    }

    private HttpEntity createFormEntity() {
        try {
            return new UrlEncodedFormEntity(getParamsList(), this.contentEncoding);
        } catch (UnsupportedEncodingException e) {
            AsyncHttpClient.log.mo22450e(LOG_TAG, "createFormEntity failed", e);
            return null;
        }
    }

    private HttpEntity createMultipartEntity(ResponseHandlerInterface progressHandler) throws IOException {
        SimpleMultipartEntity entity = new SimpleMultipartEntity(progressHandler);
        entity.setIsRepeatable(this.isRepeatable);
        for (Entry<String, String> entry : this.urlParams.entrySet()) {
            entity.addPartWithCharset((String) entry.getKey(), (String) entry.getValue(), this.contentEncoding);
        }
        for (BasicNameValuePair kv : getParamsList(null, this.urlParamsWithObjects)) {
            entity.addPartWithCharset(kv.getName(), kv.getValue(), this.contentEncoding);
        }
        for (Entry<String, StreamWrapper> entry2 : this.streamParams.entrySet()) {
            StreamWrapper stream = (StreamWrapper) entry2.getValue();
            if (stream.inputStream != null) {
                entity.addPart((String) entry2.getKey(), stream.name, stream.inputStream, stream.contentType);
            }
        }
        for (Entry<String, FileWrapper> entry3 : this.fileParams.entrySet()) {
            FileWrapper fileWrapper = (FileWrapper) entry3.getValue();
            entity.addPart((String) entry3.getKey(), fileWrapper.file, fileWrapper.contentType, fileWrapper.customFileName);
        }
        for (Entry<String, List<FileWrapper>> entry4 : this.fileArrayParams.entrySet()) {
            for (FileWrapper fw : (List) entry4.getValue()) {
                entity.addPart((String) entry4.getKey(), fw.file, fw.contentType, fw.customFileName);
            }
        }
        return entity;
    }

    /* access modifiers changed from: protected */
    public List<BasicNameValuePair> getParamsList() {
        List<BasicNameValuePair> lparams = new LinkedList<>();
        for (Entry<String, String> entry : this.urlParams.entrySet()) {
            lparams.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        lparams.addAll(getParamsList(null, this.urlParamsWithObjects));
        return lparams;
    }

    private List<BasicNameValuePair> getParamsList(String key, Object value) {
        String str;
        List<BasicNameValuePair> params = new LinkedList<>();
        if (value instanceof Map) {
            Map map = (Map) value;
            List list = new ArrayList(map.keySet());
            if (list.size() > 0 && (list.get(0) instanceof Comparable)) {
                Collections.sort(list);
            }
            for (Object nestedKey : list) {
                if (nestedKey instanceof String) {
                    Object nestedValue = map.get(nestedKey);
                    if (nestedValue != null) {
                        if (key == null) {
                            str = (String) nestedKey;
                        } else {
                            str = String.format(Locale.US, "%s[%s]", new Object[]{key, nestedKey});
                        }
                        params.addAll(getParamsList(str, nestedValue));
                    }
                }
            }
        } else {
            String str2 = "%s[%d]";
            if (value instanceof List) {
                List list2 = (List) value;
                int listSize = list2.size();
                for (int nestedValueIndex = 0; nestedValueIndex < listSize; nestedValueIndex++) {
                    params.addAll(getParamsList(String.format(Locale.US, str2, new Object[]{key, Integer.valueOf(nestedValueIndex)}), list2.get(nestedValueIndex)));
                }
            } else if (value instanceof Object[]) {
                Object[] array = (Object[]) value;
                int arrayLength = array.length;
                for (int nestedValueIndex2 = 0; nestedValueIndex2 < arrayLength; nestedValueIndex2++) {
                    params.addAll(getParamsList(String.format(Locale.US, str2, new Object[]{key, Integer.valueOf(nestedValueIndex2)}), array[nestedValueIndex2]));
                }
            } else if (value instanceof Set) {
                for (Object nestedValue2 : (Set) value) {
                    params.addAll(getParamsList(key, nestedValue2));
                }
            } else {
                params.add(new BasicNameValuePair(key, value.toString()));
            }
        }
        return params;
    }

    /* access modifiers changed from: protected */
    public String getParamString() {
        return URLEncodedUtils.format(getParamsList(), this.contentEncoding);
    }
}
