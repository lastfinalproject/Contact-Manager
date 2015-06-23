package com.ramananda.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ramananda.fragment.child.ContactsDetails;
import com.ramananda.fragment.child.RecentDetails;
import com.ramananda.fragment.child.SpeedDialDetails;

public class FragmentPageAdapter extends FragmentPagerAdapter {
	public FragmentPageAdapter(FragmentManager fm) {
		super(fm);
		
	}

	@Override
	public Fragment getItem(int id) {
		switch (id) {
		case 0:
			return new SpeedDialDetails();
		case 1:
			return new ContactsDetails();
		case 2:
			return new RecentDetails();
		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}

}
