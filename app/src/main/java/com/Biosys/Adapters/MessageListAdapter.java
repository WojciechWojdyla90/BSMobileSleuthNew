package com.Biosys.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Biosys.Controlers.Session;
import com.Biosys.Naming.BSMessage;
import com.Biosys.Naming.BSUser;
import com.Biosys.Utils.DateUtil;
import com.Biosys.bsmobilesleuth.R;

public class MessageListAdapter extends ArrayAdapter<BSMessage>{
	private Context _context;
	private BSMessage[] _objects;
	
	public MessageListAdapter(Context context,
			int textViewResourceId, BSMessage[] objects) {
		super(context , textViewResourceId, objects);
		_context = context;
		_objects = objects;
	}

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		
		//SharedPreferences sp = _context.getSharedPreferences("com.biosys.mobilesleuth", Context.MODE_PRIVATE);
	    //int userPosition = sp.getInt("string.userPosition", 1);
		int userPosition = 0;
		for(BSUser user : Session.getUsers()){
			if(user.getId() == _objects[position].getSenderId()){
				userPosition = user.getPosition().getId();
			}
		}
		
	    LayoutInflater inflater = (LayoutInflater) _context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.message_list_row, parent, false);
	    TextView textViewTitle = (TextView) rowView.findViewById(R.id.thirdLine);
	    TextView textViewDescription = (TextView) rowView.findViewById(R.id.secondLine);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	    textViewTitle.setText(DateUtil.parseDateToString(_objects[position].getDate()));
	    textViewDescription.setText(_objects[position].getContent());
	    if(userPosition == 3)
	    	imageView.setImageResource(R.drawable.manager);
	    else if(userPosition == 2)
	    	imageView.setImageResource(R.drawable.trader);
	    else
	    	imageView.setImageResource(R.drawable.employee);
	    
	    return rowView;
	  }
}
