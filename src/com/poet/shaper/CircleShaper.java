package com.poet.shaper;

import android.graphics.Path;
import android.graphics.Path.Direction;
import android.view.View;

/**
 * 圆形
 * @author poet
 *
 */
public class CircleShaper extends Shaper {

	public CircleShaper(View view) {
		super(view);
	}

	@Override
	protected boolean isSquare() {
		return true;
	}

	@Override
	protected void onShape(float left, float top, float right, float bottom,
			Path path) {
		float width = right - left;
		float height = bottom - top;
		float radius;
		if (width > height) {
			radius = height / 2;
		} else {
			radius = width / 2;
		}
		path.addCircle(width / 2, height / 2, radius, Direction.CCW);
	}

}
