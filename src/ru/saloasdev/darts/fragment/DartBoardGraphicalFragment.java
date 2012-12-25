package ru.saloasdev.darts.fragment;

import ru.saloasdev.darts.fragment.interfaces.FragmentsWithOnScoreChangeListener;
import ru.saloasdev.darts.fragment.interfaces.OnScoreChangedListener;
import ru.saloasdev.darts.view.DartBoard;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DartBoardGraphicalFragment extends Fragment implements FragmentsWithOnScoreChangeListener {

	protected static final String EXTRA_SCORE = "extra_score";
	protected int currentScore = 0;
	protected OnScoreChangedListener onScoreChangedListener;

	public static DartBoardGraphicalFragment newInstance(int score) {
		DartBoardGraphicalFragment f = new DartBoardGraphicalFragment();
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_SCORE, score);
		f.setArguments(args);
		return f;
	}

	public static DartBoardGraphicalFragment newInstance(int score, OnScoreChangedListener listener) {
		DartBoardGraphicalFragment f = new DartBoardGraphicalFragment();
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
		DartBoard view = new DartBoard(getActivity());
		view.setOnScoreChangedListener(onScoreChangedListener);
		return view;
	}

	@Override
	public void setOnScoreChangedListener(OnScoreChangedListener onScoreChangedListener) {
		this.onScoreChangedListener = onScoreChangedListener;
	}

}
