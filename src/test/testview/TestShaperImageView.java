package test.testview;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.poet.shaper.CircleShaper;
import com.poet.shaper.FiveStarShaper;
import com.poet.shaper.R;
import com.poet.shaper.RhombShaper;
import com.poet.shaper.RoundRectShaper;
import com.poet.shaper.view.ShaperImageView;

public class TestShaperImageView extends FrameLayout {

    int index = -1;

    public TestShaperImageView(final Context context) {
        super(context);
        final ShaperImageView siv = new ShaperImageView(context);
        siv.setImageResource(R.drawable.profile);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if (index > 3) {
                    index = 0;
                }
                switch (index) {
                case 0:
                    siv.setShaper(new CircleShaper(siv));
                    break;
                case 1:
                    siv.setShaper(new FiveStarShaper(siv));
                    break;
                case 2:
                    siv.setShaper(new RoundRectShaper(siv, context.getResources().getDimensionPixelSize(R.dimen.dp10)));
                    break;
                default:
                    siv.setShaper(new RhombShaper(siv, 0));
                    break;
                }
                siv.invalidate();
            }
        });
        this.setBackgroundColor(Color.BLACK);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2, Gravity.CENTER);
        this.addView(siv, params);
        Toast.makeText(context, "点击屏幕切换\nclick screen to switch effect", Toast.LENGTH_SHORT).show();
    }

}
