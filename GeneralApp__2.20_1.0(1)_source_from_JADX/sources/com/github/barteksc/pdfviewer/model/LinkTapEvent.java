package com.github.barteksc.pdfviewer.model;

import android.graphics.RectF;
import com.shockwave.pdfium.PdfDocument.Link;

public class LinkTapEvent {
    private float documentX;
    private float documentY;
    private Link link;
    private RectF mappedLinkRect;
    private float originalX;
    private float originalY;

    public LinkTapEvent(float originalX2, float originalY2, float documentX2, float documentY2, RectF mappedLinkRect2, Link link2) {
        this.originalX = originalX2;
        this.originalY = originalY2;
        this.documentX = documentX2;
        this.documentY = documentY2;
        this.mappedLinkRect = mappedLinkRect2;
        this.link = link2;
    }

    public float getOriginalX() {
        return this.originalX;
    }

    public float getOriginalY() {
        return this.originalY;
    }

    public float getDocumentX() {
        return this.documentX;
    }

    public float getDocumentY() {
        return this.documentY;
    }

    public RectF getMappedLinkRect() {
        return this.mappedLinkRect;
    }

    public Link getLink() {
        return this.link;
    }
}
