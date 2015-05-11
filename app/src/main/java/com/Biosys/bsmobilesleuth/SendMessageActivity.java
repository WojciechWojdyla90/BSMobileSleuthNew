package com.Biosys.bsmobilesleuth;


import java.util.ArrayList;

import org.json.JSONObject;

import com.Biosys.Controlers.Session;
import com.Biosys.Naming.BSUser;
import com.Biosys.WebserviceComunication.IServiceChannel;
import com.Biosys.WebserviceComunication.RestServiceChannnel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SendMessageActivity extends Activity implements OnItemSelectedListener {

	BSUser user;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_message);
		
		context = this;
		
		Spinner spinner =  (Spinner)findViewById(R.id.spnUsers);
		
    	ArrayList<String> list = new ArrayList<String>();
    	
    	for(BSUser user : Session.getUsers()){
    		String toDisplay = user.getName() + " " + user.getSurname();
    		list.add(toDisplay);
    	}
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
    	spinner.setAdapter(adapter);
    	
		spinner.setOnItemSelectedListener(this);
    	
    	EditText et1 = (EditText)findViewById(R.id.etContent);
		
		Button bt = (Button)findViewById(R.id.btnMessage);
		bt.setEnabled(false);
		
    	et1.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
	        	EditText et1 = (EditText)findViewById(R.id.etContent);
	    		Button bt = (Button)findViewById(R.id.btnMessage);
	    		
	    		if(et1.getText().equals(""))
	    			bt.setEnabled(false);
	    		else{
	        		bt.setEnabled(true);
	        	}
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
    	
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_message, menu);
		return true;
	}
	
	public void onSendMessage(View view){
		EditText et1 = (EditText)findViewById(R.id.etContent);
		SharedPreferences sp = getSharedPreferences("com.biosys.mobilesleuth", Context.MODE_PRIVATE);
	    String sender = Integer.toString(sp.getInt("string.userId", 1));
	    String reciver = Integer.toString(user.getId());
	    String content = et1.getText().toString();
		new AsyncSendMessage().execute(sender,reciver,content);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
			long arg3) {
		// TODO Auto-generated method stub
		int i = pos; 
		user = Session.getUsers().get(i);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private class AsyncSendMessage extends AsyncTask<String, JSONObject, Boolean> {
		ProgressDialog progDailog;
        //String userName=null;
		
		
		@Override
        protected void onPreExecute() {
            //super.onPreExecute();
        			progDailog = new ProgressDialog(context);
            		progDailog.setMessage(getResources().getString(R.string.message_sending));
            		progDailog.show();
            	}

        @Override
        protected void onPostExecute(Boolean result) {
       	
        	if(progDailog.isShowing())
        			progDailog.dismiss();
        	
        	Toast.makeText(context, getResources().getString(R.string.message_success), Toast.LENGTH_LONG).show();
		}
		
        @Override
        protected Boolean doInBackground(String... params) {
        	  IServiceChannel serviceChannel = new RestServiceChannnel();
        	 // boolean userAuth;
			try {
				//userAuth = true;//serviceChannel.UserAuthorize(params[0].toString(), params[1].toString());
				
				Boolean res = serviceChannel.SendMessage(Integer.parseInt(params[0].toString()),
						Integer.parseInt(params[1].toString()), params[2].toString());
	        	
	        	return res;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				String message = e.getMessage();
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
				return false;
			}
              
        }
        }

}
