package test.testview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.poet.shaper.R;
import com.poet.shaper.Shaper;
import com.poet.shaper.view.ShutterImageView;
import com.poet.shaper.view.ShutterImageView.ShutterShaper;

public class TestShutterImageView extends LinearLayout {

    ShutterImageView shutterImageView;

    public TestShutterImageView(Context context) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
        this.setGravity(Gravity.CENTER_HORIZONTAL);
        this.setBackgroundColor(Color.BLACK);
        shutterImageView = new ShutterImageView(context);
        shutterImageView.setImageResource(R.drawable.profile);
        int size = context.getResources().getDimensionPixelSize(R.dimen.dp10) * 20;
        this.addView(shutterImageView, size, size);
        Button btn1 = new Button(context);
        btn1.setTextColor(Color.WHITE);
        btn1.setText("垂直");
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Shaper shaper = shutterImageView.getShaper();
                if (shaper == null || !(shaper instanceof Shaper1)) {
                    shutterImageView.setShaper(new Shaper1(shutterImageView));
                }
                shutterImageView.openOrClose();
            }
        });
        this.addView(btn1, -2, -2);

        Button btn2 = new Button(context);
        btn2.setTextColor(Color.WHITE);
        btn2.setText("水平");
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Shaper shaper = shutterImageView.getShaper();
                if (shaper == null || !(shaper instanceof Shaper2)) {
                    shutterImageView.setShaper(new Shaper2(shutterImageView));
                }
                shutterImageView.openOrClose();
            }
        });
        this.addView(btn2, -2, -2);

        Button btn3 = new Button(context);
        btn3.setTextColor(Color.WHITE);
        btn3.setText("圆形");
        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Shaper shaper = shutterImageView.getShaper();
                if (shaper == null || !(shaper instanceof Shaper3)) {
                    shutterImageView.setShaper(new Shaper3(shutterImageView));
                }
                shutterImageView.openOrClose();
            }
        });
        this.addView(btn3, -2, -2);
    }

    class Shaper1 extends ShutterShaper {

        public Shaper1(View view) {
            super(view);
        }

        @Override
        protected void onShape(float left, float top, float right, float bottom, Path path, float itemCurSize,
                float itemMaxSize, int columnCount) {
            for (int i = 0; i < columnCount; i++) {
                float itemTop = top + i * itemMaxSize;
                path.addRect(left, itemTop, right, itemTop + itemCurSize, Direction.CCW);
            }
        }
    }

    class Shaper2 extends ShutterShaper {

        public Shaper2(View view) {
            super(view);
        }

        @Override
        protected void onShape(float left, float top, float right, float bottom, Path path, float itemCurSize,
                float itemMaxSize, int columnCount) {
            for (int i = 0; i < columnCount; i++) {
                float itemLeft = left + i * itemMaxSize;
                path.addRect(itemLeft, top, itemLeft + itemCurSize, bottom, Direction.CCW);
            }
        }
    }

    class Shaper3 extends ShutterShaper {

        float maxCorner;
        float curCorner;
        RectF rectF;

        public Shaper3(View view) {
            super(view);
        }

        @Override
        protected void onShape(float left, float top, float right, float bottom, Path path, float itemCurSize,
                float itemMaxSize, int columnCount) {
            if (maxCorner == 0) {
                maxCorner = curCorner = itemMaxSize / 2;
            }
            int xCount = columnCount;
            int yCount = (int) ((bottom - top) / itemMaxSize);
            for (int i = 0; i < xCount; i++) {
                for (int j = 0; j < yCount; j++) {
                    float x = i * itemMaxSize;
                    float y = j * itemMaxSize;
                    if (itemCurSize < itemMaxSize) {
                        path.addCircle(x + itemMaxSize / 2, y + itemMaxSize / 2, itemCurSize / 2, Direction.CCW);
                    } else {
                        if (rectF == null) {
                            rectF = new RectF();
                        }
                        rectF.set(x, y, x + itemMaxSize, y + itemMaxSize);
                        path.addRoundRect(rectF, curCorner, curCorner, Direction.CCW);
                    }
                }
            }
        }

        @Override
        protected boolean close() {
            curCorner = maxCorner;
            return super.close();
        }

        @Override
        protected boolean open() {
            if (!super.open()) {
                if ((curCorner -= maxCorner / 8) <= 0) {
                    curCorner = 0;
                    return false;
                }
            }
            return true;
        }
    }
}
