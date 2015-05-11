package com.Biosys.bsmobilesleuth;

import com.Biosys.Controlers.Session;
import com.Biosys.ReportLogic.EventPair;
import com.Biosys.Utils.DateUtil;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ReportDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_detail);
		
		SharedPreferences sp = getSharedPreferences("com.biosys.mobilesleuth", Context.MODE_PRIVATE);
	    int position = sp.getInt("string.reportPosition", 1);
	    
	    EventPair pair = Session.getReport().get(position);
	    
	    TextView tv1 = (TextView) findViewById(R.id.tvDate);
	    TextView tv2 = (TextView) findViewById(R.id.tvstartTime);
	    TextView tv3 = (TextView) findViewById(R.id.tvstartLocation);
	    TextView tv4 = (TextView) findViewById(R.id.tvstartTrip);
	    TextView tv5 = (TextView) findViewById(R.id.tvendTime);
	    TextView tv6 = (TextView) findViewById(R.id.tvendLocation);
	    TextView tv7 = (TextView) findViewById(R.id.tvendTrip);
	    
	    tv1.setText(DateUtil.databaseFormat(pair.getDate()).substring(0, 10));
	    
	    if(pair.getStartEvent() != null)
	    	tv2.setText(DateUtil.databaseFormat(pair.getStartEvent().getDate()).substring(11, 19));
	    else
	    	tv2.setText("?");
	    
	    if(pair.getStartEvent() != null)
	    	tv3.setText(Double.toString(pair.getStartEvent().getEvent().getLongitiude()));
	    else
	    	tv3.setText("?");
	    
	    if(pair.getStartEvent() != null)
	    	tv4.setText(Double.toString(pair.getStartEvent().getEvent().getLatitude()));
	    else
	    	tv4.setText("?");
	    
	    
	    if(pair.getEndEvent() != null)
	    	tv5.setText(DateUtil.databaseFormat(pair.getEndEvent().getDate()).substring(11, 19));
	    else
	    	tv5.setText("?");
	    
	    if(pair.getEndEvent() != null)
	    	tv6.setText(Double.toString(pair.getEndEvent().getEvent().getLongitiude()));
	    else
	    	tv6.setText("?");
	    
	    if(pair.getEndEvent() != null)
	    	tv7.setText(Double.toString(pair.getEndEvent().getEvent().getLatitude()));
	    else
	    	tv7.setText("?");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report_detail, menu);
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
