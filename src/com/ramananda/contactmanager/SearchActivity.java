package com.ramananda.contactmanager;

import java.util.ArrayList;

import com.rama.bean.ContactValue;
import com.ramananda.adapter.ContactAdapter;
import com.ramananda.fragment.child.ContactsDetails;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class SearchActivity extends Activity {
	ListView searchName;
	ContactAdapter searchAdapter;
	ArrayList<ContactValue> searchList = new ArrayList<ContactValue>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		searchName = (ListView) findViewById(R.id.searchContactList);

		searchAdapter = new ContactAdapter(getApplicationContext(),
				ContactsDetails.contacts);
		searchName.setAdapter(searchAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
