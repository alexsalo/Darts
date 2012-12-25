package ru.saloasdev.darts.view;

import ru.saloasdev.darts.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Foresight extends View {

	private static final int INVALID_POINTER_ID = -1;

	private Drawable spotImage;
	private float posX;
	private float posY;
	private int imageHeight;

	private float lastTouchX;
	private float lastTouchY;
	private int activePointerId = INVALID_POINTER_ID;

	public Foresight(Context context) {
		this(context, null, 0);
	}

	public Foresight(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Foresight(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		spotImage = getResources().getDrawable(R.drawable.ic_dart_spot);
		spotImage.setBounds(0, 0, spotImage.getIntrinsicWidth(), spotImage.getIntrinsicHeight());
		imageHeight = spotImage.getIntrinsicHeight();
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		final int action = ev.getAction();
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN: {
			final float x = ev.getX();
			final float y = ev.getY();

			lastTouchX = x;
			lastTouchY = y;
			activePointerId = ev.getPointerId(0);
			posX = x;
			posY = y;
			invalidate();
			break;
		}

		case MotionEvent.ACTION_MOVE: {
			final int pointerIndex = ev.findPointerIndex(activePointerId);
			final float x = ev.getX(pointerIndex);
			final float y = ev.getY(pointerIndex);

			final float dx = x - lastTouchX;
			final float dy = y - lastTouchY;

			posX += dx;
			posY += dy;

			invalidate();

			lastTouchX = x;
			lastTouchY = y;

			break;
		}

		case MotionEvent.ACTION_UP: {
			activePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_CANCEL: {
			activePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_POINTER_UP: {
			final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			final int pointerId = ev.getPointerId(pointerIndex);
			if (pointerId == activePointerId) {
				final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
				lastTouchX = ev.getX(newPointerIndex);
				lastTouchY = ev.getY(newPointerIndex);
				activePointerId = ev.getPointerId(newPointerIndex);
			}
			break;
		}
		}

		return true;
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		canvas.translate(posX, posY-imageHeight);
		spotImage.draw(canvas);
		canvas.restore();
	}
}