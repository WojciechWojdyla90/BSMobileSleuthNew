package com.Biosys.bsmobilesleuth;

import java.util.ArrayList;

import com.Biosys.Adapters.MessageListAdapter;
import com.Biosys.Adapters.TripListAdapter;
import com.Biosys.Controlers.Session;
import com.Biosys.Naming.BSEvent;
import com.Biosys.Naming.BSMessage;
import com.Biosys.Naming.BSTrip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class TripInfoActivity extends Activity implements OnItemSelectedListener{

	Spinner spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trip_info);
		
		ArrayList<String> list = new ArrayList<String>();
    	
		spinner = (Spinner) findViewById(R.id.spnTrip);
		
    	for(BSTrip trip : Session.getTrips()){
    		String toDisplay = trip.getId()+" "+trip.getName();
    		list.add(toDisplay);
    	}
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
    	spinner.setAdapter(adapter);
    	
		spinner.setOnItemSelectedListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trip_info, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i1 = new Intent("com.Biosys.bsmobilesleuth.SMSSettingsActivity");
    	    startActivity(i1);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		BSTrip trip = Session.getTrips().get(position);
		
		ArrayList<BSEvent> events = new ArrayList<BSEvent>();
		
		ArrayList<BSEvent> tmp = Session.getEvents();
		
		for(BSEvent ev : Session.getEvents()){
			if(trip.getId() == ev.getTripId()){
				events.add(ev);
			}
		}
		
		final ListView listview = (ListView) findViewById(R.id.tripListView);
    	
		BSEvent[] msgs= new  BSEvent[events.size()];
    	msgs = events.toArray(msgs);
    	
    	TripListAdapter  adapter = new TripListAdapter(this, R.layout.event_trip_user, msgs);
    	
    	listview.setAdapter(adapter);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
	public void onSendSMS(View v){
		SharedPreferences sp = getSharedPreferences("com.biosys.mobilesleuth", Context.MODE_PRIVATE);
	    String phone = sp.getString("string.phone", "");
	    String content = sp.getString("string.content", "");
	    
	    if(phone.equals("") || content.equals(""))
	    	Toast.makeText(this, "Najpierw ustaw dane do sms-a", Toast.LENGTH_LONG).show();
	    
	    SmsManager smsManager =     SmsManager.getDefault();
	    smsManager.sendTextMessage(phone, null, content, null, null);
	    
	    Toast.makeText(this, "Wys³ano smsa", Toast.LENGTH_LONG).show();
	}
}
