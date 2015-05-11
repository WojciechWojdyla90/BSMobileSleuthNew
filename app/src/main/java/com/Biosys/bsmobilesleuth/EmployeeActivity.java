package com.Biosys.bsmobilesleuth;

import java.util.ArrayList;

import org.json.JSONObject;

import com.Biosys.Adapters.MessageListAdapter;
import com.Biosys.Controlers.Session;
import com.Biosys.Naming.BSMessage;
import com.Biosys.Naming.BSUser;
import com.Biosys.WebserviceComunication.IServiceChannel;
import com.Biosys.WebserviceComunication.RestServiceChannnel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class EmployeeActivity extends Activity {

	Context context; 
	ArrayList<BSMessage> messages;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee);
		context = this;
		new AsyncGetUserMessages().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		
		
		
		SharedPreferences sp = context.getSharedPreferences("com.biosys.mobilesleuth", Context.MODE_PRIVATE);
	    int position = sp.getInt("string.userPosition", 1);
		
	    if(position == 2){
	    	getMenuInflater().inflate(R.menu.employee_with, menu);
	    }else{
	    	getMenuInflater().inflate(R.menu.employee, menu);
	    }
	    
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
       	switch (item.getItemId()) {
    	case R.id.action_to_send_mail:
    		Intent i1 = new Intent("com.Biosys.bsmobilesleuth.SendMessageActivity");
    	    startActivity(i1);
            break;
    	case R.id.action_to_event_registration:
    		Intent i2 = new Intent("com.Biosys.bsmobilesleuth.EventRegistrationActivity");
    	    startActivity(i2);
            break;
    	case R.id.action_to_user_report:
    		Intent i3 = new Intent("com.Biosys.bsmobilesleuth.ReportActivity");
    	    startActivity(i3);
            break;
    	case R.id.action_to_trip_info:
    		Intent i4 = new Intent("com.Biosys.bsmobilesleuth.TripInfoActivity");
    	    startActivity(i4);
            break;
    	}
       	
       	
    	return super.onOptionsItemSelected(item);
    }
	
	private class AsyncGetUserMessages extends AsyncTask<String, JSONObject, ArrayList<BSMessage>> {
		ProgressDialog progDailog;
        //String userName=null;
        @Override
        protected ArrayList<BSMessage> doInBackground(String... params) {
        	  IServiceChannel serviceChannel = new RestServiceChannnel();
        	 // boolean userAuth;
			try {
				//userAuth = true;//serviceChannel.UserAuthorize(params[0].toString(), params[1].toString());
				SharedPreferences sp = context.getSharedPreferences("com.biosys.mobilesleuth", Context.MODE_PRIVATE);
			    int userId = sp.getInt("string.userId", 1);
				Session.setUsers(serviceChannel.GetAllBSUsers());
				Session.setTrips(serviceChannel.GetBSUserTrips(userId));
				Session.setEvents(serviceChannel.GetBSUserEvents(userId));
				return serviceChannel.GetBSUserMessages(userId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				String message = e.getMessage();
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
				return null;
			}
              
        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
        			progDailog = new ProgressDialog(context);
            		progDailog.setMessage(getResources().getString(R.string.data_loading));
            		progDailog.show();
            	}

        @Override
        protected void onPostExecute(ArrayList<BSMessage> result) {
        	
        	final ListView listview = (ListView) findViewById(R.id.messageListView);
        	messages = result;
        	
        	BSMessage[] msgs= new  BSMessage[messages.size()];
        	msgs = messages.toArray(msgs);
        	
        	final MessageListAdapter  adapter = new MessageListAdapter(context, R.layout.message_list_row, msgs);
        	
        	listview.setAdapter(adapter);
        	
        	if(progDailog.isShowing())
        			progDailog.dismiss();
		}

        }
}
