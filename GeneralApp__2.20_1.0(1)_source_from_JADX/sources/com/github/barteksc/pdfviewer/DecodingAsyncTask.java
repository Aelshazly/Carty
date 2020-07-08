package com.github.barteksc.pdfviewer;

import android.os.AsyncTask;
import com.github.barteksc.pdfviewer.source.DocumentSource;
import com.shockwave.pdfium.PdfiumCore;
import com.shockwave.pdfium.util.Size;

class DecodingAsyncTask extends AsyncTask<Void, Void, Throwable> {
    private boolean cancelled = false;
    private DocumentSource docSource;
    private String password;
    private PdfFile pdfFile;
    private PDFView pdfView;
    private PdfiumCore pdfiumCore;
    private int[] userPages;

    DecodingAsyncTask(DocumentSource docSource2, String password2, int[] userPages2, PDFView pdfView2, PdfiumCore pdfiumCore2) {
        this.docSource = docSource2;
        this.userPages = userPages2;
        this.pdfView = pdfView2;
        this.password = password2;
        this.pdfiumCore = pdfiumCore2;
    }

    /* access modifiers changed from: protected */
    public Throwable doInBackground(Void... params) {
        try {
            PdfFile pdfFile2 = new PdfFile(this.pdfiumCore, this.docSource.createDocument(this.pdfView.getContext(), this.pdfiumCore, this.password), this.pdfView.getPageFitPolicy(), getViewSize(), this.userPages, this.pdfView.isSwipeVertical(), this.pdfView.getSpacingPx(), this.pdfView.doAutoSpacing());
            this.pdfFile = pdfFile2;
            return null;
        } catch (Throwable t) {
            return t;
        }
    }

    private Size getViewSize() {
        return new Size(this.pdfView.getWidth(), this.pdfView.getHeight());
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Throwable t) {
        if (t != null) {
            this.pdfView.loadError(t);
            return;
        }
        if (!this.cancelled) {
            this.pdfView.loadComplete(this.pdfFile);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        this.cancelled = true;
    }
}
