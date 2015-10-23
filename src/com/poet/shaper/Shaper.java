package com.poet.shaper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.View;

/**
 * 自定义View时，onDraw的时候，显示区域的控制者
 * 
 * @author poet
 * @date 2015-10-11 下午1:48:35
 */
public abstract class Shaper {

    private Paint mPaint;
    private Path mPath;

    public Shaper(View view) {
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.TRANSPARENT);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mPath = new Path();
    }

    protected abstract boolean isSquare();

    protected abstract void onShape(float left, float top, float right, float bottom, Path path);

    public void shape(Canvas canvas) {
        float left = 0, top = 0, right = canvas.getWidth(), bottom = canvas.getHeight();
        if (isSquare() && canvas.getWidth() != canvas.getHeight()) {
            if (canvas.getWidth() > canvas.getHeight()) {
                float d = canvas.getWidth() - canvas.getHeight();
                left = d / 2;
                right = canvas.getWidth() - d / 2;
            } else {
                float d = canvas.getHeight() - canvas.getWidth();
                top = d / 2;
                bottom = canvas.getHeight() - d / 2;
            }
        }
        mPath.reset();
        onShape(left, top, right, bottom, mPath);
        mPath.setFillType(FillType.INVERSE_EVEN_ODD);
        canvas.drawPath(mPath, mPaint);
    }

    public void setBlankColor(int color) {
        mPaint.setColor(color);
    }
}
