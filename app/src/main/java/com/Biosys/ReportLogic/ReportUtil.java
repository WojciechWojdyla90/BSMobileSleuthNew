package com.Biosys.ReportLogic;

import java.util.ArrayList;
import java.util.Date;

import com.Biosys.Naming.BSEvent;
import com.Biosys.Utils.DateUtil;

public class ReportUtil {
	public static ArrayList<EventPair> GenerateReport(ArrayList<BSEvent> events){
		ArrayList<EventPair> result = new ArrayList<EventPair>();
		
		ArrayList<REvent> tmp = new ArrayList<REvent>();
		
		for(BSEvent event : events){
			tmp.add(new REvent(event));
		}
		
		Date date = new Date();
		date = DateUtil.addDays(date, -16);
		
		while(date.getDate() <= 28 && date.getMonth() < 2){
			
			EventPair ep = new EventPair(date);
			
			for(REvent ev : tmp){
				if(DateUtil.isSame(ev.date, ep.date)){
					if(ev.getEvent().getChecktype() == 0){
						ep.setStartEvent(ev);
					}else{
						ep.setEndEvent(ev);
					}
				}
			}
			
			result.add(ep);
			
			date = DateUtil.addDays(date, 1);
		}
		
		return result;
	}
}
