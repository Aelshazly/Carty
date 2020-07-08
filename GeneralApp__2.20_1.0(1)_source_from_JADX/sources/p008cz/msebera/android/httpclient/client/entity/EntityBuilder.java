package p008cz.msebera.android.httpclient.client.entity;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.NameValuePair;
import p008cz.msebera.android.httpclient.entity.AbstractHttpEntity;
import p008cz.msebera.android.httpclient.entity.BasicHttpEntity;
import p008cz.msebera.android.httpclient.entity.ByteArrayEntity;
import p008cz.msebera.android.httpclient.entity.ContentType;
import p008cz.msebera.android.httpclient.entity.FileEntity;
import p008cz.msebera.android.httpclient.entity.InputStreamEntity;
import p008cz.msebera.android.httpclient.entity.SerializableEntity;
import p008cz.msebera.android.httpclient.entity.StringEntity;

/* renamed from: cz.msebera.android.httpclient.client.entity.EntityBuilder */
public class EntityBuilder {
    private byte[] binary;
    private boolean chunked;
    private String contentEncoding;
    private ContentType contentType;
    private File file;
    private boolean gzipCompress;
    private List<NameValuePair> parameters;
    private Serializable serializable;
    private InputStream stream;
    private String text;

    EntityBuilder() {
    }

    public static EntityBuilder create() {
        return new EntityBuilder();
    }

    private void clearContent() {
        this.text = null;
        this.binary = null;
        this.stream = null;
        this.parameters = null;
        this.serializable = null;
        this.file = null;
    }

    public String getText() {
        return this.text;
    }

    public EntityBuilder setText(String text2) {
        clearContent();
        this.text = text2;
        return this;
    }

    public byte[] getBinary() {
        return this.binary;
    }

    public EntityBuilder setBinary(byte[] binary2) {
        clearContent();
        this.binary = binary2;
        return this;
    }

    public InputStream getStream() {
        return this.stream;
    }

    public EntityBuilder setStream(InputStream stream2) {
        clearContent();
        this.stream = stream2;
        return this;
    }

    public List<NameValuePair> getParameters() {
        return this.parameters;
    }

    public EntityBuilder setParameters(List<NameValuePair> parameters2) {
        clearContent();
        this.parameters = parameters2;
        return this;
    }

    public EntityBuilder setParameters(NameValuePair... parameters2) {
        return setParameters(Arrays.asList(parameters2));
    }

    public Serializable getSerializable() {
        return this.serializable;
    }

    public EntityBuilder setSerializable(Serializable serializable2) {
        clearContent();
        this.serializable = serializable2;
        return this;
    }

    public File getFile() {
        return this.file;
    }

    public EntityBuilder setFile(File file2) {
        clearContent();
        this.file = file2;
        return this;
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    public EntityBuilder setContentType(ContentType contentType2) {
        this.contentType = contentType2;
        return this;
    }

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public EntityBuilder setContentEncoding(String contentEncoding2) {
        this.contentEncoding = contentEncoding2;
        return this;
    }

    public boolean isChunked() {
        return this.chunked;
    }

    public EntityBuilder chunked() {
        this.chunked = true;
        return this;
    }

    public boolean isGzipCompress() {
        return this.gzipCompress;
    }

    public EntityBuilder gzipCompress() {
        this.gzipCompress = true;
        return this;
    }

    private ContentType getContentOrDefault(ContentType def) {
        ContentType contentType2 = this.contentType;
        return contentType2 != null ? contentType2 : def;
    }

    public HttpEntity build() {
        AbstractHttpEntity e;
        String str = this.text;
        if (str != null) {
            e = new StringEntity(str, getContentOrDefault(ContentType.DEFAULT_TEXT));
        } else {
            byte[] bArr = this.binary;
            if (bArr != null) {
                e = new ByteArrayEntity(bArr, getContentOrDefault(ContentType.DEFAULT_BINARY));
            } else {
                InputStream inputStream = this.stream;
                if (inputStream != null) {
                    e = new InputStreamEntity(inputStream, 1, getContentOrDefault(ContentType.DEFAULT_BINARY));
                } else {
                    List<NameValuePair> list = this.parameters;
                    if (list != null) {
                        ContentType contentType2 = this.contentType;
                        e = new UrlEncodedFormEntity((Iterable<? extends NameValuePair>) list, contentType2 != null ? contentType2.getCharset() : null);
                    } else {
                        Serializable serializable2 = this.serializable;
                        if (serializable2 != null) {
                            e = new SerializableEntity(serializable2);
                            e.setContentType(ContentType.DEFAULT_BINARY.toString());
                        } else {
                            File file2 = this.file;
                            if (file2 != null) {
                                e = new FileEntity(file2, getContentOrDefault(ContentType.DEFAULT_BINARY));
                            } else {
                                e = new BasicHttpEntity();
                            }
                        }
                    }
                }
            }
        }
        if (e.getContentType() != null) {
            ContentType contentType3 = this.contentType;
            if (contentType3 != null) {
                e.setContentType(contentType3.toString());
            }
        }
        e.setContentEncoding(this.contentEncoding);
        e.setChunked(this.chunked);
        if (this.gzipCompress) {
            return new GzipCompressingEntity(e);
        }
        return e;
    }
}
