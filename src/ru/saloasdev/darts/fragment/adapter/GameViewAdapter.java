package ru.saloasdev.darts.fragment.adapter;

import java.util.ArrayList;
import java.util.List;

import ru.saloasdev.darts.fragment.DartBoardGraphicalFragment;
import ru.saloasdev.darts.fragment.DartBoardTextFragment;
import ru.saloasdev.darts.fragment.interfaces.FragmentsWithOnScoreChangeListener;
import ru.saloasdev.darts.fragment.interfaces.OnScoreChangedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class GameViewAdapter extends FragmentPagerAdapter {

	static final int NUM_ITEMS = 2;
	private OnScoreChangedListener listener;
	private List<Integer> TABLE = new ArrayList<Integer>();

	public GameViewAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		TABLE.add(position);
		return createFragment(position);
	}

	private Fragment createFragment(int position) {
		if (position == 0) {
			if (listener != null) {
				return DartBoardGraphicalFragment.newInstance(0, listener);
			} else {
				return DartBoardGraphicalFragment.newInstance(0);
			}
		} else {
			if (listener != null) {
				return DartBoardTextFragment.newInstance(0, listener);
			} else {
				return DartBoardTextFragment.newInstance(0);
			}
		}
	}

	@Override
	public int getCount() {
		return NUM_ITEMS;
	}

	@Override
	public void destroyItem(android.view.ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
	}

	public void setOnScoreChangedListener(ViewPager viewPager, OnScoreChangedListener listener) {
		this.listener = listener;
		for (Integer i : TABLE) {
			Object objectobject = this.instantiateItem(viewPager, i);
			if (objectobject != null)
				try {
					((FragmentsWithOnScoreChangeListener) objectobject).setOnScoreChangedListener(listener);
				} catch (Exception e) {
					// Log.i(TAG, "no more Fragment in FragmentPagerAdapter");
				}
		}
	}
}
