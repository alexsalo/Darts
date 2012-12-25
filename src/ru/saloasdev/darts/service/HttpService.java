package ru.saloasdev.darts.service;

import java.io.Serializable;

import ru.saloasdev.darts.util.ConnectivityUtil;

import junit.framework.Assert;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class HttpService extends IntentService {

	private static final String TAG = HttpService.class.getSimpleName();

	public static final String EXTRA_STATUS_RECEIVER = "EXTRA_STATUS_RECEIVER";
	public static final String EXTRA_HTTP_COMMAND = "EXTRA_HTTP_COMMAND";
	public static final String EXTRA_SHOWTOAST = "EXTRA_SHOWTOAST";
	public static final String EXTRA_RESULT = "EXTRA_RESULT";
	public static final String EXTRA_ERROR_CODE = "EXTRA_ERROR_CODE";
	public static final String EXTRA_STATUS_DATA = "EXTRA_STATUS_DATA";
	public static final String EXTRA_ERROR_MESSAGE = "EXTRA_ERROR_MESSAGE";

	public static final int STATUS_RUNNING = 0x0;
	public static final int STATUS_ERROR = 0x1;
	public static final int STATUS_FINISHED = 0x2;

	public static final int ERROR_CODE_TRANSPORT = 0x1;
	public static final int ERROR_CODE_BUSINESS = 0x2;

	private boolean stoped;

	public HttpService() {
		super(TAG);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		stoped = false;
	}

	public static void request(Context context, HttpCommand restCommand, ResultReceiver receiver) {
		Intent intent = new Intent(context, HttpService.class);
		intent.putExtra(HttpService.EXTRA_STATUS_RECEIVER, receiver);
		intent.putExtra(HttpService.EXTRA_HTTP_COMMAND, restCommand);
		context.startService(intent);
	}

	public static void request(Context context, HttpCommand restCommand, ResultReceiver receiver, boolean showToast) {
		Intent intent = new Intent(context, HttpService.class);
		intent.putExtra(HttpService.EXTRA_STATUS_RECEIVER, receiver);
		intent.putExtra(HttpService.EXTRA_HTTP_COMMAND, restCommand);
		intent.putExtra(HttpService.EXTRA_SHOWTOAST, showToast);
		context.startService(intent);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		stoped = false;
		ResultReceiver receiver = intent.getParcelableExtra(EXTRA_STATUS_RECEIVER);
		HttpCommand restCommand = (HttpCommand) intent.getSerializableExtra(EXTRA_HTTP_COMMAND);
		Assert.assertNotNull(restCommand);
		int status = STATUS_RUNNING;
		if (receiver != null)
			receiver.send(status, Bundle.EMPTY);
		Bundle resultBundle = new Bundle();
		try {
			boolean showToast = (intent.getBooleanExtra(EXTRA_SHOWTOAST, true));
			ConnectivityUtil.checkNetwork(getApplicationContext(),showToast);
			long startRemote = System.currentTimeMillis();
			Serializable result = restCommand.execute(getApplicationContext());
			resultBundle.putSerializable(EXTRA_RESULT, result);
			status = STATUS_FINISHED;
		} catch (Exception e) {
			resultBundle.putString(EXTRA_ERROR_MESSAGE, e.getMessage());
			resultBundle.putInt(EXTRA_ERROR_CODE, ERROR_CODE_TRANSPORT);
			status = STATUS_ERROR;
		}
		if (!stoped && receiver != null)
			receiver.send(status, resultBundle);
	}

	@Override
	public void onDestroy() {
		stoped = true;
		super.onDestroy();
	}
}
