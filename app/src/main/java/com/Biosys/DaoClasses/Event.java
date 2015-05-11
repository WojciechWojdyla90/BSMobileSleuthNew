package com.Biosys.DaoClasses;

import com.j256.ormlite.field.DatabaseField;

public class Event {
	@DatabaseField(generatedId = true)
	int id;
	
	@DatabaseField
	int userId;
	
	@DatabaseField
	int checktype;
	
	@DatabaseField
	String checktime;
	
	@DatabaseField
	double longitiude;

	@DatabaseField
	double latitude;
	
	@DatabaseField
	int tripId;
	
	Event(){
		// needed by ormlite
	}
	
	public Event(int _userId,int _checktype, String _checkTime,double _longitiude, double _latitiude, int _tripId){
		checktype = _checktype;
		userId = _userId;
		checktime = _checkTime;
		longitiude = _longitiude;
		latitude = _latitiude;
		tripId = _tripId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getChecktype() {
		return checktype;
	}

	public void setChecktype(int checktype) {
		this.checktype = checktype;
	}

	public String getChecktime() {
		return checktime;
	}

	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}

	public double getLongitiude() {
		return longitiude;
	}

	public void setLongitiude(double longitiude) {
		this.longitiude = longitiude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	
}
