package p008cz.msebera.android.httpclient.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.entity.SerializableEntity */
public class SerializableEntity extends AbstractHttpEntity {
    private Serializable objRef;
    private byte[] objSer;

    public SerializableEntity(Serializable ser, boolean bufferize) throws IOException {
        Args.notNull(ser, "Source object");
        if (bufferize) {
            createBytes(ser);
        } else {
            this.objRef = ser;
        }
    }

    public SerializableEntity(Serializable ser) {
        Args.notNull(ser, "Source object");
        this.objRef = ser;
    }

    private void createBytes(Serializable ser) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(ser);
        out.flush();
        this.objSer = baos.toByteArray();
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        if (this.objSer == null) {
            createBytes(this.objRef);
        }
        return new ByteArrayInputStream(this.objSer);
    }

    public long getContentLength() {
        byte[] bArr = this.objSer;
        if (bArr == null) {
            return -1;
        }
        return (long) bArr.length;
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return this.objSer == null;
    }

    public void writeTo(OutputStream outstream) throws IOException {
        Args.notNull(outstream, "Output stream");
        byte[] bArr = this.objSer;
        if (bArr == null) {
            ObjectOutputStream out = new ObjectOutputStream(outstream);
            out.writeObject(this.objRef);
            out.flush();
            return;
        }
        outstream.write(bArr);
        outstream.flush();
    }
}
