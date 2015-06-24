package com.ramananda.fragment.child;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rama.bean.ContactValue;
import com.ramananda.adapter.ContactAdapter;
import com.ramananda.contactmanager.R;

public class SearchFragment extends Fragment {

	ListView searchName;
	ContactAdapter searchAdapter;
	ArrayList<ContactValue> searchList = new ArrayList<ContactValue>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.search_fragment, container, false);

		searchName = (ListView) v.findViewById(R.id.searchContactList);

		searchAdapter = new ContactAdapter(getActivity(),
				ContactsDetails.contacts);
		searchName.setAdapter(searchAdapter);
		return v;
	}
}
