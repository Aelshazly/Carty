package androidx.transition;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.widget.ImageView;
import java.lang.reflect.Field;

class ImageViewUtils {
    private static Field sDrawMatrixField;
    private static boolean sDrawMatrixFieldFetched;
    private static boolean sTryHiddenAnimateTransform = true;

    static void animateTransform(ImageView view, Matrix matrix) {
        if (VERSION.SDK_INT >= 29) {
            view.animateTransform(matrix);
        } else if (matrix == null) {
            Drawable drawable = view.getDrawable();
            if (drawable != null) {
                drawable.setBounds(0, 0, (view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight(), (view.getHeight() - view.getPaddingTop()) - view.getPaddingBottom());
                view.invalidate();
            }
        } else if (VERSION.SDK_INT >= 21) {
            hiddenAnimateTransform(view, matrix);
        } else {
            Drawable drawable2 = view.getDrawable();
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
                Matrix drawMatrix = null;
                fetchDrawMatrixField();
                Field field = sDrawMatrixField;
                if (field != null) {
                    try {
                        drawMatrix = (Matrix) field.get(view);
                        if (drawMatrix == null) {
                            drawMatrix = new Matrix();
                            sDrawMatrixField.set(view, drawMatrix);
                        }
                    } catch (IllegalAccessException e) {
                    }
                }
                if (drawMatrix != null) {
                    drawMatrix.set(matrix);
                }
                view.invalidate();
            }
        }
    }

    private static void hiddenAnimateTransform(ImageView view, Matrix matrix) {
        if (sTryHiddenAnimateTransform) {
            try {
                view.animateTransform(matrix);
            } catch (NoSuchMethodError e) {
                sTryHiddenAnimateTransform = false;
            }
        }
    }

    private static void fetchDrawMatrixField() {
        if (!sDrawMatrixFieldFetched) {
            try {
                sDrawMatrixField = ImageView.class.getDeclaredField("mDrawMatrix");
                sDrawMatrixField.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            sDrawMatrixFieldFetched = true;
        }
    }

    private ImageViewUtils() {
    }
}
