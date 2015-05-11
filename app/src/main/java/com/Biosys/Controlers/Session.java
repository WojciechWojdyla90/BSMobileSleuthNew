package com.Biosys.Controlers;

import java.util.ArrayList;

import com.Biosys.Naming.BSEvent;
import com.Biosys.Naming.BSTrip;
import com.Biosys.Naming.BSUser;
import com.Biosys.ReportLogic.EventPair;

public class Session {
	private static ArrayList<BSUser> _users;
	private static ArrayList<BSTrip> _trips;
	private static ArrayList<BSEvent> _events;
	private static ArrayList<EventPair> _pairs;
	
	public static void setUsers(ArrayList<BSUser> users){
		Session._users = users;
	}
	
	public static ArrayList<BSUser> getUsers(){
		return Session._users;
	}
	
	public static void setTrips(ArrayList<BSTrip> trips){
		Session._trips = trips;
	}
	
	public static ArrayList<BSEvent> getEvents(){
		return Session._events;
	}
	
	public static void setEvents(ArrayList<BSEvent> events){
		Session._events = events;
	}
	
	public static ArrayList<BSTrip> getTrips(){
		return Session._trips;
	}
	
	public static void setReport(ArrayList<EventPair> pairs){
		Session._pairs = pairs;
	}
	
	public static ArrayList<EventPair> getReport(){
		return Session._pairs;
	}
}
