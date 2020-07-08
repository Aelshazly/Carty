package p008cz.msebera.android.httpclient.impl.p012io;

import java.io.IOException;
import java.io.InputStream;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.MalformedChunkCodingException;
import p008cz.msebera.android.httpclient.TruncatedChunkException;
import p008cz.msebera.android.httpclient.p013io.BufferInfo;
import p008cz.msebera.android.httpclient.p013io.SessionInputBuffer;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

/* renamed from: cz.msebera.android.httpclient.impl.io.ChunkedInputStream */
public class ChunkedInputStream extends InputStream {
    private static final int BUFFER_SIZE = 2048;
    private static final int CHUNK_CRLF = 3;
    private static final int CHUNK_DATA = 2;
    private static final int CHUNK_LEN = 1;
    private final CharArrayBuffer buffer;
    private int chunkSize;
    private boolean closed = false;
    private boolean eof = false;
    private Header[] footers = new Header[0];

    /* renamed from: in */
    private final SessionInputBuffer f241in;
    private int pos;
    private int state;

    public ChunkedInputStream(SessionInputBuffer in) {
        this.f241in = (SessionInputBuffer) Args.notNull(in, "Session input buffer");
        this.pos = 0;
        this.buffer = new CharArrayBuffer(16);
        this.state = 1;
    }

    public int available() throws IOException {
        SessionInputBuffer sessionInputBuffer = this.f241in;
        if (sessionInputBuffer instanceof BufferInfo) {
            return Math.min(((BufferInfo) sessionInputBuffer).length(), this.chunkSize - this.pos);
        }
        return 0;
    }

    public int read() throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.eof) {
            return -1;
        } else {
            if (this.state != 2) {
                nextChunk();
                if (this.eof) {
                    return -1;
                }
            }
            int b = this.f241in.read();
            if (b != -1) {
                this.pos++;
                if (this.pos >= this.chunkSize) {
                    this.state = 3;
                }
            }
            return b;
        }
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.eof) {
            return -1;
        } else {
            if (this.state != 2) {
                nextChunk();
                if (this.eof) {
                    return -1;
                }
            }
            int bytesRead = this.f241in.read(b, off, Math.min(len, this.chunkSize - this.pos));
            if (bytesRead != -1) {
                this.pos += bytesRead;
                if (this.pos >= this.chunkSize) {
                    this.state = 3;
                }
                return bytesRead;
            }
            this.eof = true;
            StringBuilder sb = new StringBuilder();
            sb.append("Truncated chunk ( expected size: ");
            sb.append(this.chunkSize);
            sb.append("; actual size: ");
            sb.append(this.pos);
            sb.append(")");
            throw new TruncatedChunkException(sb.toString());
        }
    }

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    private void nextChunk() throws IOException {
        this.chunkSize = getChunkSize();
        int i = this.chunkSize;
        if (i >= 0) {
            this.state = 2;
            this.pos = 0;
            if (i == 0) {
                this.eof = true;
                parseTrailerHeaders();
                return;
            }
            return;
        }
        throw new MalformedChunkCodingException("Negative chunk size");
    }

    private int getChunkSize() throws IOException {
        int st = this.state;
        if (st != 1) {
            if (st == 3) {
                this.buffer.clear();
                if (this.f241in.readLine(this.buffer) == -1) {
                    return 0;
                }
                if (this.buffer.isEmpty()) {
                    this.state = 1;
                } else {
                    throw new MalformedChunkCodingException("Unexpected content at the end of chunk");
                }
            } else {
                throw new IllegalStateException("Inconsistent codec state");
            }
        }
        this.buffer.clear();
        if (this.f241in.readLine(this.buffer) == -1) {
            return 0;
        }
        int separator = this.buffer.indexOf(59);
        if (separator < 0) {
            separator = this.buffer.length();
        }
        try {
            return Integer.parseInt(this.buffer.substringTrimmed(0, separator), 16);
        } catch (NumberFormatException e) {
            throw new MalformedChunkCodingException("Bad chunk header");
        }
    }

    private void parseTrailerHeaders() throws IOException {
        try {
            this.footers = AbstractMessageParser.parseHeaders(this.f241in, -1, -1, null);
        } catch (HttpException ex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid footer: ");
            sb.append(ex.getMessage());
            IOException ioe = new MalformedChunkCodingException(sb.toString());
            ioe.initCause(ex);
            throw ioe;
        }
    }

    public void close() throws IOException {
        if (!this.closed) {
            try {
                if (!this.eof) {
                    do {
                    } while (read(new byte[2048]) >= 0);
                }
            } finally {
                this.eof = true;
                this.closed = true;
            }
        }
    }

    public Header[] getFooters() {
        return (Header[]) this.footers.clone();
    }
}
