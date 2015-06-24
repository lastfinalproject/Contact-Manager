package com.ramananda.contactmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.rama.bean.ContactValue;
import com.ramananda.adapter.ContactAdapter;
import com.ramananda.fragment.child.ContactsDetails;

public class SearchActivity extends Activity {
	ListView searchName;
	ContactAdapter searchAdapter;
	ArrayList<ContactValue> searchContacts = new ArrayList<ContactValue>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayUseLogoEnabled(false);

		// remove action bar icon
		getActionBar().setIcon(android.R.color.transparent);
		getActionBar().setTitle("");

		// set background
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#00968C")));
		searchName = (ListView) findViewById(R.id.searchContactList);

		searchAdapter = new ContactAdapter(getApplicationContext(),
				ContactsDetails.contacts);
		searchName.setAdapter(searchAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.search, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setSubmitButtonEnabled(true);

		SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				searchAdapter.filter(query);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				searchAdapter.filter(newText);
				return true;
			}
		};
		searchView.setOnQueryTextListener(textChangeListener);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(SearchActivity.this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
