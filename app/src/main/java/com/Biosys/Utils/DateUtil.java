package com.Biosys.Utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String databaseFormat(Date date){
		String h,m,mon,day,year;
		if(date.getHours() > 9)
			h = ""+date.getHours();
		else
			h = "0"+date.getHours();
		
		if(date.getMinutes() > 9)
			m = ""+date.getMinutes();
		else
			m = "0"+date.getMinutes();
		
		int dd = date.getDate();
		if(dd > 9)
			day = ""+dd;
		else
			day = "0"+dd;
		
		int mo = date.getMonth() + 1;
		if(mo > 9)
			mon = ""+mo;
		else
			mon = "0"+mo;
		
		if(date.getYear() < 2000){
			int ye = date.getYear() + 1900;
			year = "" + ye;
		}else
			year = ""+date.getYear();
		
		String resul = year+"-"+mon+"-"+day+" "+h+":"+m+":00";
		return resul;
	}

	public static String parseDateToString(String string){
		String str = string.substring(0, 10)+" "+string.substring(11, 19);
		return str;
	}
	
	public static Date parseDate(String string){
		String str = string.substring(0, 10)+" "+string.substring(11, 19);
		int year = Integer.parseInt((String) str.subSequence(0, 4));
		int month = Integer.parseInt((String) str.subSequence(5, 7));
		int day = Integer.parseInt((String) str.subSequence(8, 10));
		
		int hour = Integer.parseInt((String) str.subSequence(11, 13));
		int minute = Integer.parseInt((String) str.subSequence(14, 16));
		Date result = new Date(year - 1900,month - 1,day,hour,minute);
		return result;
	}
	
	 public static Date addDays(Date date, int days)
	    {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, days); //minus number would decrement the days
	        return cal.getTime();
	    }
	 
	 
	 public static Boolean isSame(Date date1, Date date2)
	    {
	        if(date1.getDate() == date2.getDate()) return true;
	        
	        return false;
	    }
	
}
