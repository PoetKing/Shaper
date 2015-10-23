package com.poet.shaper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.poet.shaper.Shaper;

/**
 * 
 * @author poet
 * 2015-8-24下午5:33:14
 */
public class ShaperImageView extends ImageView {

    private Shaper mShaper;

    public ShaperImageView(Context context) {
        super(context);
    }

    public ShaperImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mShaper != null) {
            mShaper.shape(canvas);
        }
    }

    public void setShaper(Shaper shaper) {
        mShaper = shaper;
    }
}
