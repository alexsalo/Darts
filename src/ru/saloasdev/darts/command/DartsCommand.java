package ru.saloasdev.darts.command;

import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.util.Assert;
import org.springframework.web.util.UriTemplate;

import ru.saloasdev.darts.exception.BusinessException;
import ru.saloasdev.darts.exception.TransportException;
import ru.saloasdev.darts.parser.JSONParser;
import ru.saloasdev.darts.service.HttpCommand;
import android.content.Context;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;


@SuppressWarnings("serial")
public class DartsCommand implements HttpCommand {

	private static final String TAG = DartsCommand.class.getSimpleName();

	/** Request URL */
	protected String requestUrl;

	/** Request params */
	protected Map<String, Object> params;

	/** Response parser */
	protected JSONParser<? extends Serializable> parser;

	public DartsCommand(String requestUrl) {
		this.requestUrl = requestUrl;
		this.params = new HashMap<String, Object>();
	}

	@Override
	public Serializable execute(Context context) throws BusinessException, TransportException {
		Assert.notNull(context, "Context must not be null.");
		UriTemplate template = new UriTemplate(requestUrl);
		URI uri = template.expand(params);

		JSONObject jo = null;
		AjaxStatus status = null;
		AQuery aq = new AQuery(context);

		AjaxCallback<JSONObject> cb = new AjaxCallback<JSONObject>();
		cb.url(uri.toString()).type(JSONObject.class);
		aq.sync(cb);

		jo = cb.getResult();
		status = cb.getStatus();
		if (status.getCode() != 200 || jo == null) {
			Log.d(TAG, "status.getCode(): " + status.getCode());
			Log.d(TAG, "jo == null: " + String.valueOf(jo == null));
			throw new TransportException("Request problems.");
		}
		Log.d(TAG, "RESPONSE<<<<:" + jo.toString());
		return parser != null ? parser.parse(jo) : null;
	}
}
