package com.Biosys.bsmobilesleuth;

import java.util.ArrayList;

import com.Biosys.Adapters.MessageListAdapter;
import com.Biosys.Adapters.RaportListAdapter;
import com.Biosys.Controlers.Session;
import com.Biosys.Naming.BSMessage;
import com.Biosys.ReportLogic.EventPair;
import com.Biosys.ReportLogic.ReportUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ReportActivity extends Activity {

	ArrayList<EventPair> pairs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);
		
		pairs = ReportUtil.GenerateReport(Session.getEvents());
		
		final ListView listview = (ListView) findViewById(R.id.reportListView);
    	
		EventPair[] msgs= new  EventPair[pairs.size()];
    	msgs = pairs.toArray(msgs);
    	
    	final RaportListAdapter  adapter = new RaportListAdapter(this, R.layout.report_list_row, msgs);
    	
    	listview.setAdapter(adapter);
    	listview.setClickable(true);
    	
    	Session.setReport(pairs);

    	listview.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			SharedPreferences sp = getSharedPreferences(
					"com.biosys.mobilesleuth", Context.MODE_PRIVATE);
			SharedPreferences.Editor ed = sp.edit();

			ed.putInt("string.reportPosition", position);
			ed.commit();
			
			Intent intentAdmin = new Intent(
					"com.Biosys.bsmobilesleuth.ReportDetailActivity");
			startActivity(intentAdmin);
			
		}
    });


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report, menu);
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
}
