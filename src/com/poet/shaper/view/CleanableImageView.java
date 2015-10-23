package com.poet.shaper.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.poet.shaper.Shaper;

public class CleanableImageView extends ImageView {

    private Shaper mShaper = new Shaper(this) {

        @Override
        protected void onShape(float left, float top, float right, float bottom, Path path) {
            for (Point p : mList) {
                mShaper.getPath().addCircle(p.x, p.y, 10, Direction.CCW);
            }
        }

        @Override
        protected boolean isSquare() {
            return false;
        }
    };
    private List<Point> mList = new ArrayList<Point>();

    public CleanableImageView(Context context) {
        super(context);
        mShaper.setInverse(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mShaper.shape(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mList.add(new Point((int) event.getX(), (int) event.getY()));
        invalidate();
        return true;
    }

    public void setCleanColor(int color) {
        mShaper.setBlankColor(color);
    }
}
