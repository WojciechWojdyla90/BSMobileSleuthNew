package com.Biosys.ReportLogic;

import java.util.ArrayList;
import java.util.Date;

import com.Biosys.Naming.BSEvent;

public class DayEvents {
	Date date;
	ArrayList<EventPair> pairs;
	
	BSEvent start;
	BSEvent end;
	
	public BSEvent getStart() {
		return start;
	}

	public BSEvent getEnd() {
		return end;
	}

	public DayEvents(Date _date){
		date = _date;
		pairs = new ArrayList<EventPair>();
	}

	public Date getDate() {
		return date;
	}

	public ArrayList<EventPair> getPairs() {
		return pairs;
	}
	
	
}
