package com.ramananda.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.rama.bean.ContactValue;
import com.ramananda.contactmanager.R;

public class ContactAdapter extends BaseAdapter implements Filterable {
	LayoutInflater inflater;
	private Context mcontext;
	private List<ContactValue> singleName = null;
	private ArrayList<ContactValue> allContacts = new ArrayList<ContactValue>();

	public ContactAdapter(Context context, ArrayList<ContactValue> names) {
		mcontext = context;
		this.singleName = names;
		inflater = LayoutInflater.from(mcontext);
		this.allContacts.addAll(names);
	}

	public class ViewHolder {
		TextView name;
		TextView phone;
	}

	@Override
	public int getCount() {
		return singleName.size();
	}

	@Override
	public ContactValue getItem(int position) {
		return singleName.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.lv_layout, null);
			holder.name = (TextView) convertView.findViewById(R.id.tv_name);
			// holder.photo = (ImageView)
			// convertView.findViewById(R.id.iv_photo);
			holder.phone = (TextView) convertView
					.findViewById(R.id.tv_phone);

			// String str = ((TextView) convertView.findViewById(R.id.txt_eng))
			// .getText().toString();

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText(singleName.get(position).getName());
		holder.phone.setText(singleName.get(position).getPhone());
		return convertView;
	}

	// Filter method
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		singleName.clear();
		if (charText.length() == 0) {
			singleName.addAll(allContacts);
		} else {
			for (ContactValue cts : allContacts) {
				if (cts.getName().toLowerCase(Locale.getDefault())
						.contains(charText)) {
					singleName.add(cts);
				}
			}
		}
		notifyDataSetChanged();
	}

	@Override
	public android.widget.Filter getFilter() {
		return null;
	}

}
