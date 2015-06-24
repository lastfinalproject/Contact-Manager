package com.ramananda.fragment.child;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.rama.bean.ContactValue;
import com.ramananda.adapter.ContactAdapter;
import com.ramananda.contactmanager.R;

public class ContactsDetails extends Fragment {

	ContactAdapter adapter;
	// ArrayList<ContactValue> contactList;
	ListView lv;
	public static ArrayList<ContactValue> contacts = new ArrayList<ContactValue>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.contacts_fragment, container, false);

		getActivity().getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#00968C")));

		// Adapter to set data in the listview

		lv = (ListView) v.findViewById(R.id.contactList);
		// adapter = new ContactAdapter(getActivity(), contactList);
		// lv.setAdapter(adapter);
		LoadContactsAyscn ld = new LoadContactsAyscn();
		ld.execute();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				String phone = contacts.get(position).getPhone();
				try {
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse("tel:" + phone));
					startActivity(callIntent);
				} catch (Exception e) {
					Toast.makeText(getActivity(), e.getMessage(),
							Toast.LENGTH_LONG).show();
				}
			}
		});

		return v;
	}

	class LoadContactsAyscn extends
			AsyncTask<Void, Void, ArrayList<ContactValue>> {
		ProgressDialog pd;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pd = ProgressDialog.show(getActivity(), "Loading Contacts",
					"Please Wait");
		}

		@Override
		protected ArrayList<ContactValue> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Cursor c = getActivity().getContentResolver().query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					null, null,
					ContactsContract.Contacts.DISPLAY_NAME + " ASC ");
			while (c.moveToNext()) {

				String contactName = c
						.getString(c
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				String phNumber = c
						.getString(c
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

				contacts.add(new ContactValue(contactName, phNumber));

			}
			c.close();

			return contacts;
		}

		@Override
		protected void onPostExecute(ArrayList<ContactValue> contacts) {
			super.onPostExecute(contacts);
			pd.cancel();

			adapter = new ContactAdapter(getActivity(), contacts);

			lv.setAdapter(adapter);

		}

	}

}
