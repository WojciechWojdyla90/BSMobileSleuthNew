package com.Biosys.Adapters;

import com.Biosys.ListViewsDatas.UserListData;
import com.Biosys.bsmobilesleuth.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserListAdapter extends ArrayAdapter<UserListData> {

	private Context _context;
	private UserListData[] _objects;
	
	public UserListAdapter(Context context,
			int textViewResourceId, UserListData[] objects) {
		super(context , textViewResourceId, objects);
		_context = context;
		_objects = objects;
	}

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) _context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    
	    
	    View rowView = inflater.inflate(R.layout.user_list_row, parent, false);
	    TextView textViewTitle = (TextView) rowView.findViewById(R.id.thirdLine);
	    TextView textViewDescription = (TextView) rowView.findViewById(R.id.secondLine);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	    textViewTitle.setText(_objects[position].getTitle());
	    textViewDescription.setText(_objects[position].getDescription());
	    imageView.setImageResource(R.drawable.ic_launcher);
	    return rowView;
	  }
}
