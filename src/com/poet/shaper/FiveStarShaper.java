package com.poet.shaper;

import android.graphics.Path;
import android.view.View;

/**
 * 五角星
 * @author poet
 *
 */
public class FiveStarShaper extends Shaper {

	public FiveStarShaper(View view) {
		super(view);
	}

	@Override
	public boolean isSquare() {
		return true;
	}

	@Override
	public void onShape(float left, float top, float right, float bottom,
			Path path) {
		float width = right - left;
		float radius = width / 2;
		// 顶点A
		path.lineTo(width / 2, 0);
		float bY = radius - getSin(18, radius);
		float side = getTan(18, bY) * 2;
		float center2Point = (float) (side / 2 / Math.sin(Math.toRadians(36)));
		// --内点1
		path.lineTo(radius + side / 2, bY);
		// 顶点B
		path.lineTo(radius + getSin(72, radius), bY);
		// --内点2
		float p2X4Center = getSin(72, center2Point);
		float p2Y = radius + getSin(18, center2Point);
		path.lineTo(radius + p2X4Center, p2Y);
		// 顶点C
		path.lineTo(radius + getSin(36, radius), radius + getSin(54, radius));
		// -- 内点3
		path.lineTo(radius, radius + center2Point);
		// 顶点D
		path.lineTo(radius - getSin(36, radius), radius + getSin(54, radius));
		// -- 内点4
		path.lineTo(radius - p2X4Center, p2Y);
		// 顶点E
		path.lineTo(radius - getSin(72, radius), radius - getSin(18, radius));
		// -- 内点5
		path.lineTo(radius - side / 2, bY);
		// 顶点A
		path.lineTo(width / 2, 0);
	}

	float getSin(int angle, float scale) {
		return (float) (Math.sin(Math.toRadians(angle)) * scale);
	}

	float getTan(int angle, float scale) {
		return (float) (Math.tan(Math.toRadians(angle)) * scale);
	}
}