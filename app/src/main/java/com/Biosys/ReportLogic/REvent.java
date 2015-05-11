package com.Biosys.ReportLogic;

import java.util.Date;

import com.Biosys.Naming.BSEvent;
import com.Biosys.Utils.DateUtil;

public class REvent {
	BSEvent event;
	Date date;
	
	public REvent(BSEvent _event){
		event = _event;
		date = DateUtil.parseDate(_event.getChecktime());
	}

	public BSEvent getEvent() {
		return event;
	}

	public Date getDate() {
		return date;
	}
}
