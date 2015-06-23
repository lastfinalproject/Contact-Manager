package com.ramananda.contactmanager;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.ramananda.adapter.FragmentPageAdapter;

public class MainActivity extends FragmentActivity implements TabListener {
	ActionBar actionBar;
	ViewPager viewPager;
	FragmentPageAdapter mSectionsPagerAdapter;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		viewPager = (ViewPager) findViewById(R.id.mainpager);
		// ft = new FragmentPageAdapter(v.getSupportFragmentManager());
		mSectionsPagerAdapter = new FragmentPageAdapter(
				getSupportFragmentManager());
		actionBar = getActionBar();
		viewPager.setAdapter(mSectionsPagerAdapter);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText("Speed Dial")
				.setTabListener(this));
		// set tab
		ActionBar.Tab contacts = actionBar.newTab();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#00968C")));
		
		contacts.setText("Contacts").setTabListener(this);
		actionBar.addTab(contacts);
		actionBar.addTab(actionBar.newTab().setText("Recents")
				.setTabListener(this));
		actionBar.selectTab(contacts);

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int item) {
				actionBar.setSelectedNavigationItem(item);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
