package com.Biosys.bsmobilesleuth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.Biosys.Controlers.Session;
import com.Biosys.DaoClasses.Event;
import com.Biosys.LocalDatabaseComunication.DatabaseHelper;
import com.Biosys.Naming.BSTrip;
import com.Biosys.Naming.BSUser;
import com.Biosys.Utils.DateUtil;
import com.Biosys.WebserviceComunication.IServiceChannel;
import com.Biosys.WebserviceComunication.RestServiceChannnel;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class EventRegistrationActivity extends OrmLiteBaseActivity<DatabaseHelper> implements OnItemSelectedListener{

	TextView tvLocation;// = (TextView)findViewById(R.id.tvEventPlace);
	TextView tvTime;
	TextView tvCHecktype;
	Spinner spinner;
	List<Event>charges;
	double longitiude;
	double latitiude;
	int tripId;
	int userPosition;
	int userId;
	
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_registration);
		
		SharedPreferences sp = this.getSharedPreferences("com.biosys.mobilesleuth", Context.MODE_PRIVATE);
	     userPosition = sp.getInt("string.userPosition", 1);
	     userId = sp.getInt("string.userId", 1);
	     
	    tvLocation = (TextView)findViewById(R.id.tvEventPlace);
	    spinner = (Spinner) findViewById(R.id.spTrip);
	    tvTime = (TextView)findViewById(R.id.tvEventTime);
	    tvCHecktype = (TextView)findViewById(R.id.tvEventType);
	    
	    longitiude = 0;
	    latitiude = 0;
	    
	    context = this;
	    
	    tripId = 0;
	    
	    if(userPosition != 2){
	    	tvLocation.setVisibility(View.INVISIBLE);
	    	spinner.setVisibility(View.INVISIBLE);
	    }
	    
	    if(userPosition == 2){
	    	LocationManager locationManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);
	    	LocationListener myListener = new myListener();
	    	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,myListener);
	    	
	    	ArrayList<String> list = new ArrayList<String>();
	    	
	    	for(BSTrip trip : Session.getTrips()){
	    		String toDisplay = trip.getId()+" "+trip.getName();
	    		list.add(toDisplay);
	    	}
	    	
	    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
	    	spinner.setAdapter(adapter);
	    	
			spinner.setOnItemSelectedListener(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_registration, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
       	switch (item.getItemId()) {
    	case R.id.action_send_event:
    		sendEvent();
            break;
    	}
       	return super.onOptionsItemSelected(item);
       	}
	
	public void onRegisterStart(View v) {
		Event toRegister = createEvent(true);
		
		DatabaseHelper helper = DatabaseHelper.getDatabaseHelperInstance();
		RuntimeExceptionDao<Event, Integer> chargeDao = helper.getEventDataDao();
		
		chargeDao.create(toRegister);
		
		tvCHecktype.setText("Praca start");
		
		if(userPosition == 2){
			tvLocation.setText(Double.toString(longitiude) + " "+Double.toString(latitiude));
		}
	}

	public void onRegisterEnd(View v) {
		Event toRegister = createEvent(false);
		
		DatabaseHelper helper = DatabaseHelper.getDatabaseHelperInstance();
		RuntimeExceptionDao<Event, Integer> chargeDao = helper.getEventDataDao();
		
		chargeDao.create(toRegister);
		
		tvCHecktype.setText("Praca koniec");
		
		if(userPosition == 2){
			tvLocation.setText(Double.toString(longitiude) + " "+Double.toString(latitiude));
		}
	}
	
	private Event createEvent(Boolean isStart){
		
		int checkType = 0;
		
		if(!isStart)
			checkType = 1;
		
		tvTime.setText(DateUtil.databaseFormat(new Date()));
		
		return new Event(userId,checkType,DateUtil.databaseFormat(new Date()),longitiude,latitiude,tripId);
	}
	
	private void sendEvent(){
		DatabaseHelper helper = DatabaseHelper.getDatabaseHelperInstance();
		RuntimeExceptionDao<Event, Integer> chargeDao = helper.getEventDataDao();
		
		charges = chargeDao.queryForAll();
		
		if(charges.size() > 0){
		Event first = charges.get(0);
		
		new AsyncSendEvent().execute(first.getChecktime(),Integer.toString(first.getChecktype()),Integer.toString(first.getUserId()),
				Double.toString(first.getLongitiude()),Double.toString(first.getLatitude()),Integer.toString(first.getChecktype()));
		}
		else{
			Toast.makeText(context, "Wszystkie zdarzenia wys³ano", Toast.LENGTH_LONG).show();
		}
	}
	
	class myListener implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
			if(location != null){
				String str = Double.toString(location.getLatitude()) + " "+Double.toString(location.getLongitude()); 
				tvLocation.setText(str);
				longitiude = location.getLatitude();
			    latitiude = location.getLongitude();
			}else{
				tvLocation.setText("lipa");
			}
				
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
	}

	
	private class AsyncSendEvent extends AsyncTask<String, JSONObject, Boolean> {
		ProgressDialog progDailog;
        //String userName=null;
		
		
		@Override
        protected void onPreExecute() {
            //super.onPreExecute();
        			progDailog = new ProgressDialog(context);
            		progDailog.setMessage("Trwa wysy³anie zdarzeñ ...");
            		progDailog.show();
            	}

        @Override
        protected void onPostExecute(Boolean result) {
       	
        	DatabaseHelper helper = DatabaseHelper.getDatabaseHelperInstance();
    		RuntimeExceptionDao<Event, Integer> chargeDao = helper.getEventDataDao();
    		
    		chargeDao.delete(charges);
    		
        	if(progDailog.isShowing())
        			progDailog.dismiss();
        	
        	Toast.makeText(context, "Poprawnie wys³ano zdarzenie", Toast.LENGTH_LONG).show();
		}
		
        @Override
        protected Boolean doInBackground(String... params) {
        	  IServiceChannel serviceChannel = new RestServiceChannnel();
        	 // boolean userAuth;
			try {
				//userAuth = true;//serviceChannel.UserAuthorize(params[0].toString(), params[1].toString());
				
	        	
				
				Boolean res = serviceChannel.SendEvent(params[0].toString(), Integer.parseInt(params[1].toString()), 
						Integer.parseInt(params[2].toString()), params[3].toString(), params[4].toString(),
								Integer.parseInt(params[5].toString()));
				
	        	return res;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				String message = e.getMessage();
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
				return false;
			}
              
        }
        }


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		this.tripId = Session.getTrips().get(position).getId();
		
		if(tripId <2)
			this.tripId = 2;
		
		longitiude = 50.060451;
	    latitiude = 19.959456;
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
}
