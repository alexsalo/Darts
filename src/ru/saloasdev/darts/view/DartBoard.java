package ru.saloasdev.darts.view;

import ru.saloasdev.darts.R;
import ru.saloasdev.darts.fragment.interfaces.OnScoreChangedListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DartBoard extends RelativeLayout {

	private final String TAG = DartBoard.class.getSimpleName();

	private ImageView dartboard;
	private View rootView;
	private Foresight foresight;
	private OnScoreChangedListener onTouchListener;

	public DartBoard(Context context, AttributeSet attrs) {
		super(context, attrs);
		initLayout();
	}
	
	public DartBoard(Context context) {
		super(context);
		initLayout();
	}
	
	public void setOnScoreChangedListener(OnScoreChangedListener listener) {
		onTouchListener = listener;
	}
	
	public void setScore(int score){
		int result;
		switch (score) {
		case 11:
			result = 0;
			break;
		case 8:
			result = 1;
			break;
		case 16:
			result = 2;
			break;
		case 7:
			result = 3;
			break;
		case 19:
			result = 4;
			break;
		case 3:
			result = 5;
			break;
		case 17:
			result = 6;
			break;
		case 2:
			result = 7;
			break;
		case 15:
			result = 8;
			break;
		case 10:
			result = 9;
			break;
		case 6:
			result = 10;
			break;
		case 13:
			result = 11;
			break;
		case 4:
			result = 12;
			break;
		case 18:
			result = 13;
			break;
		case 1:
			result = 14;
			break;
		case 20:
			result = 15;
			break;
		case 5:
			result = 16;
			break;
		case 12:
			result = 17;
			break;
		case 9:
			result = 18;
			break;
		case 14:
			result = 19;
			break;
		default:
			result = 0;
		}
		//TODO showscore on dartboard
	}

	private void initLayout() {
		LayoutInflater.from(this.getContext()).inflate(R.layout.dartboard_graphical, this, true);
		rootView = this;
		foresight = (Foresight) rootView.findViewById(R.id.dartboard_foresight);
		dartboard = (ImageView) rootView.findViewById(R.id.dartboard_graphicalboard);
		ViewTreeObserver vto = dartboard.getViewTreeObserver();
		vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				dartboard.getLayoutParams().height = dartboard.getMeasuredWidth();
				foresight.getLayoutParams().height = dartboard.getMeasuredWidth();
				foresight.getLayoutParams().width = dartboard.getMeasuredWidth();
				return true;
			}
		});
		foresight.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				double angleDeg;
				double lenght;
				float x = event.getX();
				float y = event.getY();
				int height = getWidth();
				int width = getWidth();
				float convertedX = 0 - (width / 2f - x);
				float convertedY = height / 2f - y;
				double angleRad = Math.atan2(convertedY, convertedX);
				angleDeg = angleRad * 180d / Math.PI;
				lenght = (Math.sqrt(convertedY * convertedY + convertedX * convertedX)) * 100d / (width / 2d);
				if(onTouchListener!=null){
					onTouchListener.onScoreChanged(determineSector(angleDeg, lenght));
				}
				return false;
			}
		});
	}

	private int determineSector(double angleDeg, Double lenght) {
		int result = 0;
		Long longConvertedAngle = Math.round(((angleDeg + 180) / 18d));
		// 18 градусов приходится на один сектор, учитывает смещение на пол
		// сектора
		int convertedAngle = longConvertedAngle.intValue();
		int multiplier = 0;
//		if (lenght > 6 && lenght < 46)
//			multiplier = 2;
//		else if (lenght > 45 && lenght < 101)
			multiplier = 1;
//		else
//			return 50;

		switch (convertedAngle) {
		case 0:
			result = 11;
			break;
		case 20:
			result = 11;
		case 1:
			result = 8;
			break;
		case 2:
			result = 16;
			break;
		case 3:
			result = 7;
			break;
		case 4:
			result = 19;
			break;
		case 5:
			result = 3;
			break;
		case 6:
			result = 17;
			break;
		case 7:
			result = 2;
			break;
		case 8:
			result = 15;
			break;
		case 9:
			result = 10;
			break;
		case 10:
			result = 6;
			break;
		case 11:
			result = 13;
			break;
		case 12:
			result = 4;
			break;
		case 13:
			result = 18;
			break;
		case 14:
			result = 1;
			break;
		case 15:
			result = 20;
			break;
		case 16:
			result = 5;
			break;
		case 17:
			result = 12;
			break;
		case 18:
			result = 9;
			break;
		case 19:
			result = 14;
			break;
		default:
			result = 0;
		}
		return result * multiplier;
	}
}
