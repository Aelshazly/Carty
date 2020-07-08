package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import p008cz.msebera.android.httpclient.HttpEntity;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.IOUtils */
class IOUtils {
    IOUtils() {
    }

    static void consume(HttpEntity entity) throws IOException {
        if (entity != null && entity.isStreaming()) {
            InputStream instream = entity.getContent();
            if (instream != null) {
                instream.close();
            }
        }
    }

    static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[2048];
        while (true) {
            int read = in.read(buf);
            int len = read;
            if (read != -1) {
                out.write(buf, 0, len);
            } else {
                return;
            }
        }
    }

    static void closeSilently(Closeable closable) {
        try {
            closable.close();
        } catch (IOException e) {
        }
    }

    static void copyAndClose(InputStream in, OutputStream out) throws IOException {
        try {
            copy(in, out);
            in.close();
            out.close();
        } catch (IOException ex) {
            closeSilently(in);
            closeSilently(out);
            throw ex;
        }
    }

    static void copyFile(File in, File out) throws IOException {
        FileChannel c1;
        FileChannel c2;
        RandomAccessFile f1 = new RandomAccessFile(in, "r");
        RandomAccessFile f2 = new RandomAccessFile(out, "rw");
        try {
            c1 = f1.getChannel();
            c2 = f2.getChannel();
            c1.transferTo(0, f1.length(), c2);
            c1.close();
            c2.close();
            f1.close();
            f2.close();
        } catch (IOException ex) {
            closeSilently(c1);
            closeSilently(c2);
            throw ex;
        } catch (IOException ex2) {
            closeSilently(f1);
            closeSilently(f2);
            throw ex2;
        }
    }
}
