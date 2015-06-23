package com.ramananda.fragment.child;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rama.bean.ContactValue;
import com.ramananda.adapter.ContactAdapter;
import com.ramananda.contactmanager.R;

public class ContactsDetails extends Fragment {

	ContactAdapter adapter;
	// ArrayList<ContactValue> contactList;
	ListView lv;
	ArrayList<ContactValue> conts = new ArrayList<ContactValue>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.contacts_fragment, container, false);
		// contactList = new ArrayList<ContactValue>();

		// contactList = readContact();
		// Adapter to set data in the listview

		lv = (ListView) v.findViewById(R.id.contactList);
		// adapter = new ContactAdapter(getActivity(), contactList);
		// lv.setAdapter(adapter);
		LoadContactsAyscn ld = new LoadContactsAyscn();
		ld.execute();
		return v;
	}

	//

	// public ArrayList<ContactValue> readContact() {
	// ProgressDialog pd;
	// Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
	//
	// // Querying the table ContactsContract.Contacts to retrieve all the
	// // contacts
	// Cursor contactsCursor = getActivity().getContentResolver().query(
	// contactsUri, null, null, null,
	// ContactsContract.Contacts.DISPLAY_NAME + " ASC ");
	//
	// if (contactsCursor.moveToFirst()) {
	// do {
	// long contactId = contactsCursor.getLong(contactsCursor
	// .getColumnIndex("_ID"));
	//
	// Uri dataUri = ContactsContract.Data.CONTENT_URI;
	//
	// // Querying the table ContactsContract.Data to retrieve
	// // individual items like
	// // home phone, mobile phone, work email etc corresponding to
	// // each contact
	// Cursor dataCursor = getActivity().getContentResolver().query(
	// dataUri, null,
	// ContactsContract.Data.CONTACT_ID + "=" + contactId,
	// null, null);
	//
	// String displayName = "";
	// String nickName = "";
	// String homePhone = "";
	// String mobilePhone = "";
	// String workPhone = "";
	// String photoPath = "" + R.drawable.blank;
	// byte[] photoByte = null;
	// String homeEmail = "";
	// String workEmail = "";
	// String companyName = "";
	// String title = "";
	//
	// if (dataCursor.moveToFirst()) {
	// // Getting Display Name
	// displayName = dataCursor
	// .getString(dataCursor
	// .getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
	// do {
	//
	// // Getting NickName
	// if (dataCursor
	// .getString(
	// dataCursor.getColumnIndex("mimetype"))
	// .equals(ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE))
	// nickName = dataCursor.getString(dataCursor
	// .getColumnIndex("data1"));
	//
	// // Getting Phone numbers
	// if (dataCursor
	// .getString(
	// dataCursor.getColumnIndex("mimetype"))
	// .equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
	// switch (dataCursor.getInt(dataCursor
	// .getColumnIndex("data2"))) {
	// case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
	// homePhone = dataCursor.getString(dataCursor
	// .getColumnIndex("data1"));
	// break;
	// case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
	// mobilePhone = dataCursor.getString(dataCursor
	// .getColumnIndex("data1"));
	// break;
	// case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
	// workPhone = dataCursor.getString(dataCursor
	// .getColumnIndex("data1"));
	// break;
	// }
	// }
	//
	// // Getting EMails
	// if (dataCursor
	// .getString(
	// dataCursor.getColumnIndex("mimetype"))
	// .equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {
	// switch (dataCursor.getInt(dataCursor
	// .getColumnIndex("data2"))) {
	// case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
	// homeEmail = dataCursor.getString(dataCursor
	// .getColumnIndex("data1"));
	// break;
	// case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
	// workEmail = dataCursor.getString(dataCursor
	// .getColumnIndex("data1"));
	// break;
	// }
	// }
	//
	// // Getting Organization details
	// if (dataCursor
	// .getString(
	// dataCursor.getColumnIndex("mimetype"))
	// .equals(ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE))
	// {
	// companyName = dataCursor.getString(dataCursor
	// .getColumnIndex("data1"));
	// title = dataCursor.getString(dataCursor
	// .getColumnIndex("data4"));
	// }
	//
	// // Getting Photo
	// if (dataCursor
	// .getString(
	// dataCursor.getColumnIndex("mimetype"))
	// .equals(ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)) {
	// photoByte = dataCursor.getBlob(dataCursor
	// .getColumnIndex("data15"));
	//
	// if (photoByte != null) {
	// Bitmap bitmap = BitmapFactory.decodeByteArray(
	// photoByte, 0, photoByte.length);
	//
	// // Getting Caching directory
	// File cacheDirectory = getActivity()
	// .getBaseContext().getCacheDir();
	//
	// // Temporary file to store the contact image
	// File tmpFile = new File(
	// cacheDirectory.getPath() + "/wpta_"
	// + contactId + ".png");
	//
	// // The FileOutputStream to the temporary
	// // file
	// try {
	// FileOutputStream fOutStream = new FileOutputStream(
	// tmpFile);
	//
	// // Writing the bitmap to the temporary
	// // file as png file
	// bitmap.compress(Bitmap.CompressFormat.PNG,
	// 100, fOutStream);
	//
	// // Flush the FileOutputStream
	// fOutStream.flush();
	//
	// // Close the FileOutputStream
	// fOutStream.close();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// photoPath = tmpFile.getPath();
	// }
	//
	// }
	//
	// } while (dataCursor.moveToNext());
	//
	// String details = "";
	//
	// // Concatenating various information to single string
	// if (homePhone != null && !homePhone.equals(""))
	// details = "HomePhone : " + homePhone + "\n";
	// if (mobilePhone != null && !mobilePhone.equals(""))
	// details += "MobilePhone : " + mobilePhone + "\n";
	// if (workPhone != null && !workPhone.equals(""))
	// details += "WorkPhone : " + workPhone + "\n";
	// if (nickName != null && !nickName.equals(""))
	// details += "NickName : " + nickName + "\n";
	// if (homeEmail != null && !homeEmail.equals(""))
	// details += "HomeEmail : " + homeEmail + "\n";
	// if (workEmail != null && !workEmail.equals(""))
	// details += "WorkEmail : " + workEmail + "\n";
	// if (companyName != null && !companyName.equals(""))
	// details += "CompanyName : " + companyName + "\n";
	// if (title != null && !title.equals(""))
	// details += "Title : " + title + "\n";
	//
	// // Adding id, display name, path to photo and other
	// // details to cursor
	// // mMatrixCursor.addRow(new Object[] {
	// // Long.toString(contactId), displayName,
	// // photoPath, details });
	//
	// ContactValue values = new ContactValue(displayName, details);
	// conts.add(values);
	// }
	//
	// } while (contactsCursor.moveToNext());
	// }
	// return conts;
	// }

	class LoadContactsAyscn extends
			AsyncTask<Void, Void, ArrayList<ContactValue>> {
		ProgressDialog pd;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			pd = ProgressDialog.show(getActivity(), "Loading Contacts",
					"Please Wait");
		}

		@Override
		protected ArrayList<ContactValue> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			ArrayList<ContactValue> contacts = new ArrayList<ContactValue>();

			Cursor c = getActivity().getContentResolver().query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					null, null, " ASC ");
			while (c.moveToNext()) {

				String contactName = c
						.getString(c
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				String phNumber = c
						.getString(c
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				String email = c
						.getString(c
								.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE));

				contacts.add(new ContactValue(contactName, email, phNumber));

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
