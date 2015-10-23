package test.testview;

import com.poet.shaper.R;
import com.poet.shaper.view.CleanableImageView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TestCleanableImageView extends LinearLayout {

    public TestCleanableImageView(Context context) {
        super(context);
        this.setGravity(Gravity.CENTER);
        this.setBackgroundColor(Color.BLACK);
        CleanableImageView civ = new CleanableImageView(context);
        civ.setImageResource(R.drawable.profile);
        civ.setCleanColor(Color.GRAY);
        this.addView(civ);

        FrameLayout fl = new FrameLayout(context);
        ImageView iv = new ImageView(context);
        iv.setImageResource(R.drawable.profile);
        fl.addView(iv);
        CleanableImageView civ2 = new CleanableImageView(context);
        civ2.setImageDrawable(new ColorDrawable(Color.RED));
        fl.addView(civ2);
        
        this.addView(fl);
    }

}
