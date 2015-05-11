package com.Biosys.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.Biosys.Naming.BSEvent;
import com.Biosys.Utils.DateUtil;
import com.Biosys.bsmobilesleuth.R;

public class TripListAdapter extends ArrayAdapter<BSEvent>{
	private Context _context;
	private BSEvent[] _objects;
	
	public TripListAdapter(Context context,
			int textViewResourceId, BSEvent[] objects) {
		super(context , textViewResourceId, objects);
		_context = context;
		_objects = objects;
	}

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		
		
		
	    LayoutInflater inflater = (LayoutInflater) _context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.event_trip_user, parent, false);
	    TextView textViewType = (TextView) rowView.findViewById(R.id.tvDay);
	    TextView textViewLongitiude = (TextView) rowView.findViewById(R.id.tvStart);
	    TextView textViewLatitiude = (TextView) rowView.findViewById(R.id.tvEnd);
	    TextView textViewDate = (TextView) rowView.findViewById(R.id.tvTime);
	    
	    BSEvent ev = this._objects[position];
	    
	    if(ev.getChecktype() == 0){
	    	textViewType.setText("Pr pocz¹tek");
	    	textViewType.setTextColor(Color.GREEN);
	    }else{
	    	textViewType.setText("Pr koniec");
	    	textViewType.setTextColor(Color.RED);
	    }
	    
	    textViewLongitiude.setText(Double.toString(ev.getLongitiude()));
	    textViewLatitiude.setText(Double.toString(ev.getLatitude()));
	    
	    textViewDate.setText(DateUtil.databaseFormat(DateUtil.parseDate(ev.getChecktime())));
	    //textViewTitle.setText(DateUtil.parseDateToString(_objects[position].getDate()));
	    
	    //textViewDay.setText(DateUtil.databaseFormat(_objects[position].getDate()).substring(0, 10));
	    
	    
	    //String start = "?";
	    //if(_objects[position].getStartEvent() != null)
	    	//start = DateUtil.databaseFormat(_objects[position].getStartEvent().getDate()).substring(11, 19);
	    
	    //String end = "?";
	    //if(_objects[position].getEndEvent() != null)
	    	//end = DateUtil.databaseFormat(_objects[position].getEndEvent().getDate()).substring(11, 19);
	    	
	    //textViewStart.setText(start);
	    //textViewEnd.setText(end);
	    
	    return rowView;
	  }
}
