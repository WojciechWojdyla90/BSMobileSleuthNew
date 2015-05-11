package com.Biosys.ReportLogic;

import java.util.Date;

public class EventPair {
	REvent startEvent;
	REvent endEvent;
	
	Date date;
	
	public EventPair(Date _date){
		date = _date;
	}

	public REvent getStartEvent() {
		return startEvent;
	}

	public void setStartEvent(REvent startEvent) {
		this.startEvent = startEvent;
	}

	public REvent getEndEvent() {
		return endEvent;
	}

	public void setEndEvent(REvent endEvent) {
		this.endEvent = endEvent;
	}

	public Date getDate() {
		return date;
	}
	
}
