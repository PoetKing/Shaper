package com.poet.shaper;

import android.graphics.Path;
import android.view.View;

/**
 * 菱形
 *
 * @author poet
 * @date 2015-10-11 下午2:12:57
 */
public class RhombShaper extends Shaper {
	
	private float bend;

	public RhombShaper(View view, float bend) {
		super(view);
		this.bend = bend;
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
		path.moveTo(width / 2, 0);
		if (bend > 0) {
			path.quadTo(width / 4 - bend, height / 4 - bend, 0, height / 2);
			path.quadTo(width / 4 - bend, height / 4 * 3 + bend, width / 2,
					height);
			path.quadTo(width / 4 * 3 + bend, height / 4 * 3 + bend, width,
					height / 2);
			path.quadTo(width / 4 * 3 + bend, height / 4 - bend, width / 2, 0);
		} else {
			path.lineTo(0, height / 2);
			path.lineTo(width / 2, height);
			path.lineTo(width, height / 2);
			path.lineTo(width / 2, 0);
		}
	}
}