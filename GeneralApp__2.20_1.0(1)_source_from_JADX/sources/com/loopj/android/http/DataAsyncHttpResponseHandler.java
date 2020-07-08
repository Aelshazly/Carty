package com.loopj.android.http;

import android.os.Message;

public abstract class DataAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "DataAsyncHttpRH";
    protected static final int PROGRESS_DATA_MESSAGE = 7;

    public static byte[] copyOfRange(byte[] original, int start, int end) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NullPointerException {
        if (start <= end) {
            int originalLength = original.length;
            if (start < 0 || start > originalLength) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int resultLength = end - start;
            byte[] result = new byte[resultLength];
            System.arraycopy(original, start, result, 0, Math.min(resultLength, originalLength - start));
            return result;
        }
        throw new IllegalArgumentException();
    }

    public void onProgressData(byte[] responseBody) {
        AsyncHttpClient.log.mo22447d(LOG_TAG, "onProgressData(byte[]) was not overriden, but callback was received");
    }

    public final void sendProgressDataMessage(byte[] responseBytes) {
        sendMessage(obtainMessage(7, new Object[]{responseBytes}));
    }

    /* access modifiers changed from: protected */
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 7) {
            Object[] response = (Object[]) message.obj;
            String str = LOG_TAG;
            if (response == null || response.length < 1) {
                AsyncHttpClient.log.mo22449e(str, "PROGRESS_DATA_MESSAGE didn't got enough params");
                return;
            }
            try {
                onProgressData((byte[]) response[0]);
            } catch (Throwable t) {
                AsyncHttpClient.log.mo22450e(str, "custom onProgressData contains an error", t);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        com.loopj.android.http.AsyncHttpClient.silentCloseInputStream(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return r4.toByteArray();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getResponseData(p008cz.msebera.android.httpclient.HttpEntity r13) throws java.io.IOException {
        /*
            r12 = this;
            r0 = 0
            if (r13 == 0) goto L_0x006c
            java.io.InputStream r1 = r13.getContent()
            if (r1 == 0) goto L_0x006c
            long r2 = r13.getContentLength()
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 > 0) goto L_0x0064
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x001c
            r2 = 4096(0x1000, double:2.0237E-320)
        L_0x001c:
            cz.msebera.android.httpclient.util.ByteArrayBuffer r4 = new cz.msebera.android.httpclient.util.ByteArrayBuffer     // Catch:{ OutOfMemoryError -> 0x0058 }
            int r5 = (int) r2     // Catch:{ OutOfMemoryError -> 0x0058 }
            r4.<init>(r5)     // Catch:{ OutOfMemoryError -> 0x0058 }
            r5 = 4096(0x1000, float:5.74E-42)
            byte[] r5 = new byte[r5]     // Catch:{ all -> 0x0053 }
            r6 = 0
            r7 = r6
        L_0x0028:
            int r8 = r1.read(r5)     // Catch:{ all -> 0x0053 }
            r9 = r8
            r10 = -1
            if (r8 == r10) goto L_0x0049
            java.lang.Thread r8 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0053 }
            boolean r8 = r8.isInterrupted()     // Catch:{ all -> 0x0053 }
            if (r8 != 0) goto L_0x0049
            r4.append(r5, r6, r9)     // Catch:{ all -> 0x0053 }
            byte[] r8 = copyOfRange(r5, r6, r9)     // Catch:{ all -> 0x0053 }
            r12.sendProgressDataMessage(r8)     // Catch:{ all -> 0x0053 }
            long r10 = (long) r7     // Catch:{ all -> 0x0053 }
            r12.sendProgressMessage(r10, r2)     // Catch:{ all -> 0x0053 }
            goto L_0x0028
        L_0x0049:
            com.loopj.android.http.AsyncHttpClient.silentCloseInputStream(r1)     // Catch:{ OutOfMemoryError -> 0x0058 }
            byte[] r5 = r4.toByteArray()     // Catch:{ OutOfMemoryError -> 0x0058 }
            r0 = r5
            goto L_0x006c
        L_0x0053:
            r5 = move-exception
            com.loopj.android.http.AsyncHttpClient.silentCloseInputStream(r1)     // Catch:{ OutOfMemoryError -> 0x0058 }
            throw r5     // Catch:{ OutOfMemoryError -> 0x0058 }
        L_0x0058:
            r4 = move-exception
            java.lang.System.gc()
            java.io.IOException r5 = new java.io.IOException
            java.lang.String r6 = "File too large to fit into available memory"
            r5.<init>(r6)
            throw r5
        L_0x0064:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "HTTP entity too large to be buffered in memory"
            r4.<init>(r5)
            throw r4
        L_0x006c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loopj.android.http.DataAsyncHttpResponseHandler.getResponseData(cz.msebera.android.httpclient.HttpEntity):byte[]");
    }
}
