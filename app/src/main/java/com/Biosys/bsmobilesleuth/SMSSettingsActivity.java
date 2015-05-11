package com.Biosys.bsmobilesleuth;



import java.util.ArrayList;

import com.Biosys.Utils.Contact;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SMSSettingsActivity extends Activity implements OnItemSelectedListener{

	ArrayList<Contact> contactsList;
	Spinner spinner;
	Contact contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smssettings);
		
		contactsList = new ArrayList<Contact>();
		spinner = (Spinner) findViewById(R.id.spnPhones);
		
		Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
		while (phones.moveToNext())
		{
		  String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
		  String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
		  if(!name.equals("") && !phoneNumber.equals("")){
			  Contact con = new Contact();
			  con.setName(name);
			  con.setNumber(phoneNumber);
			  contactsList.add(con);
		  }
		}
		phones.close();
		
		ArrayList<String> toDisplay = new ArrayList<String>();
		for(Contact contact : contactsList){
			toDisplay.add(contact.getName());
    	}
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,toDisplay);
    	spinner.setAdapter(adapter);
    	
		spinner.setOnItemSelectedListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.smssettings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		contact = contactsList.get(position);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
	//onSaveSettings
	public void onSaveSettings(View v){
		EditText et = (EditText) findViewById(R.id.evSmsContent);
		SharedPreferences sp = getSharedPreferences(
				"com.biosys.mobilesleuth", Context.MODE_PRIVATE);
		SharedPreferences.Editor ed = sp.edit();

		ed.putString("string.phone", contact.getNumber());
		ed.putString("string.content",et.getText().toString());
		
		ed.commit();

		Toast.makeText(this, "Dane zosta³y zapisane", Toast.LENGTH_LONG).show();
	}
}
