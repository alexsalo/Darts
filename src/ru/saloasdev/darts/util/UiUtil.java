package ru.saloasdev.darts.util;

import android.content.Context;
import android.util.TypedValue;
import android.widget.Toast;



public class UiUtil {

	public static void showToast(Context context, int id) {
		showToast(context, id, false);
	}

	public static void showToast(Context context, CharSequence text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(Context context, CharSequence text, boolean longToast) {
		Toast.makeText(context, text, longToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
	}

	public static void showToast(Context context, int id, boolean longToast) {
		Toast.makeText(context, id, longToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
	}

	public static void showFormattedToast(Context context, int id, Object... args) {
		Toast.makeText(context, String.format(context.getText(id).toString(), args), Toast.LENGTH_LONG).show();
	}

	public static float pxToDp(Context context, int value) {
		return TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
	}

}