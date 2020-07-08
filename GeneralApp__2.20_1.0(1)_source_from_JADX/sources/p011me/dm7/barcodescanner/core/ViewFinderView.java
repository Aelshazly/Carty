package p011me.dm7.barcodescanner.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/* renamed from: me.dm7.barcodescanner.core.ViewFinderView */
public class ViewFinderView extends View implements IViewFinder {
    private static final long ANIMATION_DELAY = 80;
    private static final float DEFAULT_SQUARE_DIMENSION_RATIO = 0.625f;
    private static final float LANDSCAPE_HEIGHT_RATIO = 0.625f;
    private static final float LANDSCAPE_WIDTH_HEIGHT_RATIO = 1.4f;
    private static final int MIN_DIMENSION_DIFF = 50;
    private static final int POINT_SIZE = 10;
    private static final float PORTRAIT_WIDTH_HEIGHT_RATIO = 0.75f;
    private static final float PORTRAIT_WIDTH_RATIO = 0.75f;
    private static final int[] SCANNER_ALPHA = {0, 64, 128, 192, 255, 192, 128, 64};
    private static final String TAG = "ViewFinderView";
    protected int mBorderLineLength;
    protected Paint mBorderPaint;
    private float mBordersAlpha;
    private final int mDefaultBorderColor = getResources().getColor(C1279R.C1280color.viewfinder_border);
    private final int mDefaultBorderLineLength = getResources().getInteger(C1279R.integer.viewfinder_border_length);
    private final int mDefaultBorderStrokeWidth = getResources().getInteger(C1279R.integer.viewfinder_border_width);
    private final int mDefaultLaserColor = getResources().getColor(C1279R.C1280color.viewfinder_laser);
    private final int mDefaultMaskColor = getResources().getColor(C1279R.C1280color.viewfinder_mask);
    protected Paint mFinderMaskPaint;
    private Rect mFramingRect;
    private boolean mIsLaserEnabled;
    protected Paint mLaserPaint;
    protected boolean mSquareViewFinder;
    private int mViewFinderOffset = 0;
    private int scannerAlpha;

    public ViewFinderView(Context context) {
        super(context);
        init();
    }

    public ViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.mLaserPaint = new Paint();
        this.mLaserPaint.setColor(this.mDefaultLaserColor);
        this.mLaserPaint.setStyle(Style.FILL);
        this.mFinderMaskPaint = new Paint();
        this.mFinderMaskPaint.setColor(this.mDefaultMaskColor);
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setColor(this.mDefaultBorderColor);
        this.mBorderPaint.setStyle(Style.STROKE);
        this.mBorderPaint.setStrokeWidth((float) this.mDefaultBorderStrokeWidth);
        this.mBorderPaint.setAntiAlias(true);
        this.mBorderLineLength = this.mDefaultBorderLineLength;
    }

    public void setLaserColor(int laserColor) {
        this.mLaserPaint.setColor(laserColor);
    }

    public void setMaskColor(int maskColor) {
        this.mFinderMaskPaint.setColor(maskColor);
    }

    public void setBorderColor(int borderColor) {
        this.mBorderPaint.setColor(borderColor);
    }

    public void setBorderStrokeWidth(int borderStrokeWidth) {
        this.mBorderPaint.setStrokeWidth((float) borderStrokeWidth);
    }

    public void setBorderLineLength(int borderLineLength) {
        this.mBorderLineLength = borderLineLength;
    }

    public void setLaserEnabled(boolean isLaserEnabled) {
        this.mIsLaserEnabled = isLaserEnabled;
    }

    public void setBorderCornerRounded(boolean isBorderCornersRounded) {
        if (isBorderCornersRounded) {
            this.mBorderPaint.setStrokeJoin(Join.ROUND);
        } else {
            this.mBorderPaint.setStrokeJoin(Join.BEVEL);
        }
    }

    public void setBorderAlpha(float alpha) {
        int colorAlpha = (int) (255.0f * alpha);
        this.mBordersAlpha = alpha;
        this.mBorderPaint.setAlpha(colorAlpha);
    }

    public void setBorderCornerRadius(int borderCornersRadius) {
        this.mBorderPaint.setPathEffect(new CornerPathEffect((float) borderCornersRadius));
    }

    public void setViewFinderOffset(int offset) {
        this.mViewFinderOffset = offset;
    }

    public void setSquareViewFinder(boolean set) {
        this.mSquareViewFinder = set;
    }

    public void setupViewFinder() {
        updateFramingRect();
        invalidate();
    }

    public Rect getFramingRect() {
        return this.mFramingRect;
    }

    public void onDraw(Canvas canvas) {
        if (getFramingRect() != null) {
            drawViewFinderMask(canvas);
            drawViewFinderBorder(canvas);
            if (this.mIsLaserEnabled) {
                drawLaser(canvas);
            }
        }
    }

    public void drawViewFinderMask(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Rect framingRect = getFramingRect();
        canvas.drawRect(0.0f, 0.0f, (float) width, (float) framingRect.top, this.mFinderMaskPaint);
        Canvas canvas2 = canvas;
        canvas2.drawRect(0.0f, (float) framingRect.top, (float) framingRect.left, (float) (framingRect.bottom + 1), this.mFinderMaskPaint);
        canvas2.drawRect((float) (framingRect.right + 1), (float) framingRect.top, (float) width, (float) (framingRect.bottom + 1), this.mFinderMaskPaint);
        canvas2.drawRect(0.0f, (float) (framingRect.bottom + 1), (float) width, (float) height, this.mFinderMaskPaint);
    }

    public void drawViewFinderBorder(Canvas canvas) {
        Rect framingRect = getFramingRect();
        Path path = new Path();
        path.moveTo((float) framingRect.left, (float) (framingRect.top + this.mBorderLineLength));
        path.lineTo((float) framingRect.left, (float) framingRect.top);
        path.lineTo((float) (framingRect.left + this.mBorderLineLength), (float) framingRect.top);
        canvas.drawPath(path, this.mBorderPaint);
        path.moveTo((float) framingRect.right, (float) (framingRect.top + this.mBorderLineLength));
        path.lineTo((float) framingRect.right, (float) framingRect.top);
        path.lineTo((float) (framingRect.right - this.mBorderLineLength), (float) framingRect.top);
        canvas.drawPath(path, this.mBorderPaint);
        path.moveTo((float) framingRect.right, (float) (framingRect.bottom - this.mBorderLineLength));
        path.lineTo((float) framingRect.right, (float) framingRect.bottom);
        path.lineTo((float) (framingRect.right - this.mBorderLineLength), (float) framingRect.bottom);
        canvas.drawPath(path, this.mBorderPaint);
        path.moveTo((float) framingRect.left, (float) (framingRect.bottom - this.mBorderLineLength));
        path.lineTo((float) framingRect.left, (float) framingRect.bottom);
        path.lineTo((float) (framingRect.left + this.mBorderLineLength), (float) framingRect.bottom);
        canvas.drawPath(path, this.mBorderPaint);
    }

    public void drawLaser(Canvas canvas) {
        Rect framingRect = getFramingRect();
        this.mLaserPaint.setAlpha(SCANNER_ALPHA[this.scannerAlpha]);
        this.scannerAlpha = (this.scannerAlpha + 1) % SCANNER_ALPHA.length;
        int middle = (framingRect.height() / 2) + framingRect.top;
        canvas.drawRect((float) (framingRect.left + 2), (float) (middle - 1), (float) (framingRect.right - 1), (float) (middle + 2), this.mLaserPaint);
        postInvalidateDelayed(ANIMATION_DELAY, framingRect.left - 10, framingRect.top - 10, framingRect.right + 10, framingRect.bottom + 10);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
        updateFramingRect();
    }

    public synchronized void updateFramingRect() {
        int width;
        int height;
        Point viewResolution = new Point(getWidth(), getHeight());
        int orientation = DisplayUtils.getScreenOrientation(getContext());
        if (this.mSquareViewFinder) {
            if (orientation != 1) {
                height = (int) (((float) getHeight()) * 0.625f);
                width = height;
            } else {
                width = (int) (((float) getWidth()) * 0.625f);
                height = width;
            }
        } else if (orientation != 1) {
            height = (int) (((float) getHeight()) * 0.625f);
            width = (int) (((float) height) * LANDSCAPE_WIDTH_HEIGHT_RATIO);
        } else {
            int width2 = (int) (((float) getWidth()) * 0.75f);
            width = width2;
            height = (int) (((float) width2) * 0.75f);
        }
        if (width > getWidth()) {
            width = getWidth() - 50;
        }
        if (height > getHeight()) {
            height = getHeight() - 50;
        }
        int leftOffset = (viewResolution.x - width) / 2;
        int topOffset = (viewResolution.y - height) / 2;
        this.mFramingRect = new Rect(this.mViewFinderOffset + leftOffset, this.mViewFinderOffset + topOffset, (leftOffset + width) - this.mViewFinderOffset, (topOffset + height) - this.mViewFinderOffset);
    }
}
