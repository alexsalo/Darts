package ru.saloasdev.darts;

import ru.saloasdev.darts.fragment.adapter.GameViewAdapter;
import ru.saloasdev.darts.fragment.interfaces.OnScoreChangedListener;
import ru.saloasdev.darts.util.DartsConstants;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class GameActivity extends FragmentActivity {

	protected ViewPager viewPager;
	protected GameViewAdapter fragmentAdapter;
	protected OnDartsScoreChangedListener onScoreChangedListener;

	protected RadioGroup radioGroup;
	protected Button addButton;
	protected TextView scoreTextView;

	protected int rawScore;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_activity);
		initViewPager();
		initViews();
	}

	private void initViewPager() {
		onScoreChangedListener = new OnDartsScoreChangedListener();
		fragmentAdapter = new GameViewAdapter(getSupportFragmentManager());
		viewPager = (ViewPager) findViewById(R.id.game_activity_viewpager);
		viewPager.setAdapter(fragmentAdapter);
		fragmentAdapter.setOnScoreChangedListener(viewPager, onScoreChangedListener);
	}

	private void initViews() {
		radioGroup = (RadioGroup) findViewById(R.id.game_activity_radiogroup);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				updateScoreTextView();
			}
		});
		addButton = (Button) findViewById(R.id.game_activity_add_button);
		scoreTextView = (TextView) findViewById(R.id.game_activity_points_textview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void updateScoreTextView() {
		int currentScore = rawScore;
		int multiplier = getMultiplier(radioGroup.getCheckedRadioButtonId());
		switch (multiplier) {
		case DartsConstants.SCORE_BULL:
			currentScore = DartsConstants.SCORE_BULL;
			break;
		case DartsConstants.SCORE_BULLSEYE:
			currentScore = DartsConstants.SCORE_BULLSEYE;
			break;
		default:
			currentScore *= multiplier;
			break;
		}
		scoreTextView.setText(createPointsString(currentScore));
	}

	private int getMultiplier(int radiobuttonId) {
		switch (radiobuttonId) {
		case R.id.game_activity_radio_one:
			return 1;
		case R.id.game_activity_radio_two:
			return 2;
		case R.id.game_activity_radio_three:
			return 3;
		case R.id.game_activity_radio_bull:
			return DartsConstants.SCORE_BULL;
		case R.id.game_activity_radio_bullseye:
			return DartsConstants.SCORE_BULLSEYE;
		default:
			return 1;
		}
	}

	private String createPointsString(int points) {
		String result;
		result = String.valueOf(points);
		result = result.concat(" ").concat(getString(R.string.dartboard_points));
		return result;

	}

	private class OnDartsScoreChangedListener implements OnScoreChangedListener {

		@Override
		public void onScoreChanged(int score) {
			rawScore = score;
			updateScoreTextView();
		}
	}

}
