package ru.saloasdev.darts.util;

import ru.saloasdev.darts.R;
import ru.saloasdev.darts.exception.TransportException;
import android.content.Context;
import android.net.ConnectivityManager;

public class ConnectivityUtil {

	/** Check network availability */
	public static void checkNetwork(Context context, boolean showToast) throws TransportException {
		if (context == null) {
			return;
		}
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm.getActiveNetworkInfo() == null || !cm.getActiveNetworkInfo().isAvailable()
				|| !cm.getActiveNetworkInfo().isConnectedOrConnecting()) {
			if (showToast) {
				UiUtil.showToast(context, context.getString(R.string.toast_no_internet));
			}
			throw new TransportException("Network is unavailable.");
		}
	}

	public static boolean hasNetwork(Context context) {
		if (context == null) {
			return false;
		}
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm.getActiveNetworkInfo() == null || !cm.getActiveNetworkInfo().isAvailable()
				|| !cm.getActiveNetworkInfo().isConnectedOrConnecting()) {
			return false;
		}
		return true;
	}
}
