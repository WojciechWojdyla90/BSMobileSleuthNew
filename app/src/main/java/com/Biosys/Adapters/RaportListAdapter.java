package com.Biosys.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.Biosys.ReportLogic.EventPair;
import com.Biosys.Utils.DateUtil;
import com.Biosys.bsmobilesleuth.R;

public class RaportListAdapter extends ArrayAdapter<EventPair>{
	private Context _context;
	private EventPair[] _objects;
	
	public RaportListAdapter(Context context,
			int textViewResourceId, EventPair[] objects) {
		super(context , textViewResourceId, objects);
		_context = context;
		_objects = objects;
	}

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		
		
		
	    LayoutInflater inflater = (LayoutInflater) _context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.report_list_row, parent, false);
	    TextView textViewDay = (TextView) rowView.findViewById(R.id.tvDay);
	    TextView textViewStart = (TextView) rowView.findViewById(R.id.tvStart);
	    TextView textViewEnd = (TextView) rowView.findViewById(R.id.tvEnd);
	    //textViewTitle.setText(DateUtil.parseDateToString(_objects[position].getDate()));
	    
	    textViewDay.setText(DateUtil.databaseFormat(_objects[position].getDate()).substring(0, 10));
	    
	    
	    String start = "?";
	    if(_objects[position].getStartEvent() != null)
	    	start = DateUtil.databaseFormat(_objects[position].getStartEvent().getDate()).substring(11, 19);
	    
	    String end = "?";
	    if(_objects[position].getEndEvent() != null)
	    	end = DateUtil.databaseFormat(_objects[position].getEndEvent().getDate()).substring(11, 19);
	    	
	    textViewStart.setText(start);
	    textViewEnd.setText(end);
	    
	    return rowView;
	  }
}
