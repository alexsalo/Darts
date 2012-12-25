package ru.saloasdev.darts.fragment;

import java.util.ArrayList;

import ru.saloasdev.darts.R;
import ru.saloasdev.darts.fragment.interfaces.FragmentsWithOnScoreChangeListener;
import ru.saloasdev.darts.fragment.interfaces.OnScoreChangedListener;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class DartBoardTextFragment extends Fragment implements FragmentsWithOnScoreChangeListener {

	protected static final String EXTRA_SCORE = "extra_score";
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 10;
	protected int currentScore = 0;
	protected OnScoreChangedListener onScoreChangedListener;
	protected ImageButton voiceButton;
	protected EditText scoreInput;

	public static DartBoardTextFragment newInstance(int score) {
		DartBoardTextFragment f = new DartBoardTextFragment();
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_SCORE, score);
		f.setArguments(args);
		return f;
	}

	public static DartBoardTextFragment newInstance(int score, OnScoreChangedListener listener) {
		DartBoardTextFragment f = new DartBoardTextFragment();
		f.setOnScoreChangedListener(listener);
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_SCORE, score);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		Bundle b = getArguments();
		if (b != null) {
			currentScore = b.getInt(EXTRA_SCORE, 0);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dartboard_text_fragment, container, false);
		voiceButton = (ImageButton) view.findViewById(R.id.dartboard_text_fragment_voicebutton);
		voiceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startVoiceRecognitionActivity();
			}
		});
		scoreInput = (EditText) view.findViewById(R.id.dartboard_text_fragment_editetext);
		scoreInput.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (onScoreChangedListener != null) {
					onScoreChangedListener.onScoreChanged(Integer.parseInt(s.toString()));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		return view;
	}

	@Override
	public void setOnScoreChangedListener(OnScoreChangedListener onScoreChangedListener) {
		this.onScoreChangedListener = onScoreChangedListener;
	}

	private void startVoiceRecognitionActivity() {
		try {
			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.dartboard_voice_header));
			startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);

		} catch (ActivityNotFoundException e) {
			Intent browserIntent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("https://market.android.com/details?id=com.google.android.voicesearch"));
			startActivity(browserIntent);

		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
			ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			for (String s : matches) {
				Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT);
			}

			super.onActivityResult(requestCode, resultCode, data);
		}

	}
}
