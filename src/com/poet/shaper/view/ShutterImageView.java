package com.poet.shaper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.poet.shaper.Shaper;

/**
 * 百叶窗
 * @author poet
 * 2015-10-22下午3:32:03
 */
public class ShutterImageView extends ImageView {

    private Runnable mOpenRunnable = new Runnable() {
        @Override
        public void run() {
            if (mShaper.open()) {
                postDelayed(this, mShaper.rate);
            } else {
                if (mListener != null) {
                    mListener.onShutterOpeded(ShutterImageView.this);
                }
            }
            invalidate();
        }
    };
    private Runnable mCloseRunnable = new Runnable() {
        @Override
        public void run() {
            if (mShaper.close()) {
                postDelayed(this, mShaper.rate);
            } else {
                if (mListener != null) {
                    mListener.onShutterClosed(ShutterImageView.this);
                }
            }
            invalidate();
        }
    };

    private ShutterShaper mShaper;
    private OnShutterListener mListener;

    public ShutterImageView(Context context) {
        super(context);
    }

    public ShutterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mShaper != null) {
            mShaper.shape(canvas);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mShaper != null) {
            removeCallbacks(mOpenRunnable);
            removeCallbacks(mCloseRunnable);
        }
    }

    public Shaper getShaper() {
        return mShaper;
    }

    public void setShaper(ShutterShaper shaper) {
        this.mShaper = shaper;
    }

    public void openOrClose() {
        if (mShaper == null) {
            throw new IllegalStateException("This ShutterImageView has not a ShutterShaper!");
        }
        if (mShaper.itemCurSize == ShutterShaper.SHUTTER_MIN_SIZE) {
            openOrClose(true);
        } else {
            openOrClose(false);
        }
    }

    public void openOrClose(boolean open) {
        if (mShaper == null) {
            throw new IllegalStateException("This ShutterImageView has not a ShutterShaper!");
        }
        removeCallbacks(mOpenRunnable);
        removeCallbacks(mCloseRunnable);
        if (open) {
            mShaper.itemCurSize = 0;
            post(mOpenRunnable);
        } else {
            mShaper.itemCurSize = mShaper.itemMaxSize;
            post(mCloseRunnable);
        }
    }

    public void setOnShutterListener(OnShutterListener listener) {
        mListener = listener;
    }

    public static abstract class ShutterShaper extends Shaper {

        // 如果是0，不会显示遮盖效果
        protected static final float SHUTTER_MIN_SIZE = 0.01f;

        // 列数
        private int columnCount = 10;
        // 变化速度
        private float speed = 2;
        // 变化频率
        private int rate = 50;

        private float itemMaxSize;
        private float itemCurSize;

        public ShutterShaper(View view) {
            super(view);
        }

        @Override
        protected final boolean isSquare() {
            return false;
        }

        @Override
        protected final void onShape(float left, float top, float right, float bottom, Path path) {
            if (itemMaxSize == 0) {
                itemMaxSize = (right - left) / columnCount;
            }
            onShape(left, top, right, bottom, path, itemCurSize, itemMaxSize, columnCount);
        }

        protected abstract void onShape(float left, float top, float right, float bottom, Path path, float itemCurSize,
                float itemMaxSize, int columnCount);

        protected boolean open() {
            if ((itemCurSize += speed) >= itemMaxSize) {
                itemCurSize = itemMaxSize;
                return false;
            }
            return true;
        }

        protected boolean close() {
            if ((itemCurSize -= speed) <= SHUTTER_MIN_SIZE) {
                itemCurSize = SHUTTER_MIN_SIZE;
                return false;
            }
            return true;
        }

        public void setColumnCount(int count) {
            this.columnCount = count;
            this.itemMaxSize = 0;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }
    }

    public interface OnShutterListener {
        void onShutterOpeded(ShutterImageView shutterImageView);

        void onShutterClosed(ShutterImageView shutterImageView);
    }
}
