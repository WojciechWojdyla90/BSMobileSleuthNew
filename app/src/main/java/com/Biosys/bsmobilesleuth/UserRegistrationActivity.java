package com.Biosys.bsmobilesleuth;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.json.JSONObject;

import com.Biosys.Controlers.PositionsDaoControler;
import com.Biosys.DaoClasses.Positions;
import com.Biosys.DaoClasses.User;
import com.Biosys.LocalDatabaseComunication.DatabaseHelper;
import com.Biosys.Naming.BSUser;
import com.Biosys.Utils.SecurityUtil;
import com.Biosys.WebserviceComunication.IServiceChannel;
import com.Biosys.WebserviceComunication.RestServiceChannnel;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegistrationActivity extends OrmLiteBaseActivity<DatabaseHelper> implements OnItemSelectedListener {

	Context context;
	ArrayList<BSUser> users;
	String id;
	BSUser bsuser;
	RuntimeExceptionDao<User, Integer> userDao;
	private SecurityUtil util;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_registration);
		context = this;
		
		
		
		new AsyncGetUsers().execute();
		Spinner spinner =  (Spinner)findViewById(R.id.spTrip);
		spinner.setOnItemSelectedListener(this);
		
		EditText et1 = (EditText)findViewById(R.id.editText1);
		EditText et2 = (EditText)findViewById(R.id.editText2);
		
		Button bt = (Button)findViewById(R.id.btnStart);
		bt.setEnabled(false);
		
		
		
		et1.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	EditText et1 = (EditText)findViewById(R.id.editText1);
	    		EditText et2 = (EditText)findViewById(R.id.editText2);
	    		Button bt = (Button)findViewById(R.id.btnStart);
	    		
	    		String str1 = et1.getText().toString();
	    		String str2 = et2.getText().toString();
	    		
	    		if(str1.equals("") || str2.equals(""))
	    			bt.setEnabled(false);
	    		else{
	        	if(!str1.endsWith(str2)){
	        		bt.setEnabled(false);
	        	}
	        	else
	        		bt.setEnabled(true);
	        	}
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
		
		et2.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
	        	EditText et1 = (EditText)findViewById(R.id.editText1);
	    		EditText et2 = (EditText)findViewById(R.id.editText2);
	    		Button bt = (Button)findViewById(R.id.btnStart);
	    		
	    		String str1 = et1.getText().toString();
	    		String str2 = et2.getText().toString();
	    		
	    		if(str1.equals("") || str2.equals(""))
	    			bt.setEnabled(false);
	    		else{
	        	if(!str1.endsWith(str2)){
	        		bt.setEnabled(false);
	        	}
	        	else
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
		getMenuInflater().inflate(R.menu.user_registration, menu);
		return true;
	}

	
	public void onButtonClick(View view){
		EditText et1 = (EditText)findViewById(R.id.editText1);
		new AsyncRegisterUser().execute(id,et1.getText().toString());
	}
	
	private class AsyncGetUsers extends AsyncTask<String, JSONObject, ArrayList<BSUser>> {
		ProgressDialog progDailog;
        //String userName=null;
        @Override
        protected ArrayList<BSUser> doInBackground(String... params) {
        	  IServiceChannel serviceChannel = new RestServiceChannnel();
        	 // boolean userAuth;
			try {
				//userAuth = true;//serviceChannel.UserAuthorize(params[0].toString(), params[1].toString());
				return serviceChannel.GetBSUsersToRegister();
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
        protected void onPostExecute(ArrayList<BSUser> result) {
        	
        	users = result;
        	Spinner spinner =  (Spinner)findViewById(R.id.spTrip);
        		
        	ArrayList<String> list = new ArrayList<String>();
        	
        	for(BSUser user : result){
        		list.add(Integer.toString(user.getId())+" " + user.getName() + " " + user.getSurname());
        	}
        	
        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,list);
        	spinner.setAdapter(adapter);
        	
        	//spinner.setOnItemSelectedListener(context);
        	
        	if(progDailog.isShowing())
        			progDailog.dismiss();
		}

        }

	
	private class AsyncRegisterUser extends AsyncTask<String, JSONObject, Boolean> {
		ProgressDialog progDailog;
        //String userName=null;
        @Override
        protected Boolean doInBackground(String... params) {
        	  IServiceChannel serviceChannel = new RestServiceChannnel();
        	 // boolean userAuth;
			try {
				//userAuth = true;//serviceChannel.UserAuthorize(params[0].toString(), params[1].toString());
				
				try {
					util = new SecurityUtil("MD5");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Boolean res = serviceChannel.RegisterBSUser(params[0].toString(), util.EncryptString(params[1].toString()));
	        	userDao = getHelper().getUserDataDao();
	        	User user = new User(bsuser.getName(),bsuser.getSurname(),"",params[0].toString(),bsuser.getId(),
	        			bsuser.getPosition().getId(),util.EncryptString(params[1].toString()));
	        	userDao.create(user);
	        	return res;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				String message = e.getMessage();
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
				return false;
			}
              
        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
        			progDailog = new ProgressDialog(context);
            		progDailog.setMessage(getResources().getString(R.string.data_register));
            		progDailog.show();
            	}

        @Override
        protected void onPostExecute(Boolean result) {
       	
        	if(progDailog.isShowing())
        			progDailog.dismiss();
        	
        	
        	//User userPosition = new User("root", "root", "","admin" , -1, 1, util.EncryptString("admin"));
        	//userDao.create(userPosition);
        	
        	
        	Toast.makeText(context, getResources().getString(R.string.datas_complete), Toast.LENGTH_LONG).show();
		}

        }
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// TODO Auto-generated method stub
		//TextView tv1 =  (TextView)findViewById(R.id.tvEventType);
		//TextView tv2 =  (TextView)findViewById(R.id.tvEventTime);
		TextView tv3 =  (TextView)findViewById(R.id.tvEventPlace);
		
		//int Integer.parseInt(parent.getItemAtPosition(pos).toString());
		
		//Toast.makeText(parent.getContext(), text, duration)
		
		//tv1.setText(this.users.get(pos).getName());
		//tv2.setText(this.users.get(pos).getSurname());
		
		this.id = Integer.toString(this.users.get(pos).getId());
		this.bsuser = this.users.get(pos);
		
		RuntimeExceptionDao<Positions, Integer> positionsDao = getHelper().getPositioneDataDao();
		String str =  PositionsDaoControler.GetPositionName(this.users.get(pos).getPosition().getId(), positionsDao);
		
		tv3.setText(str);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}


	
}
