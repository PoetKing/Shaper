package com.poet.shaper;

import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.view.View;

/**
 * 圆角矩形
 * @author poet
 *
 */
public class RoundRectShaper extends Shaper {
	
	private float radius;

	public RoundRectShaper(View view, float radius) {
		super(view);
		this.radius = radius;
	}

	@Override
	public boolean isSquare() {
		return false;
	}

	@Override
	public void onShape(float left, float top, float right, float bottom,
			Path path) {
		float width = right - left;
		float height = bottom - top;
		path.addRoundRect(new RectF(0, 0, width, height), radius, radius,
				Direction.CCW);
	}
}