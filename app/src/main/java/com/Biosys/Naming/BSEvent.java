package com.Biosys.Naming;

public class BSEvent {
	int id;
	int userId;
	int checktype;
	String checktime;
	double longitiude;
	double latitude;
	int tripId;
	
	public BSEvent(){
		id = 0;
		userId = 0;
		checktype = -1;
		checktime = "";
		longitiude = -1000;
		latitude = -1000;
		tripId = 0;
	}
	
	public BSEvent(int _id,int _useId,int _chectype,String _checktime, double _longitiude, 
			double _latitiude, int _tripid){
		id = _id;
		userId = _useId;
		checktype = _chectype;
		checktime = _checktime;
		longitiude = _longitiude;
		latitude = _latitiude;
		tripId = _tripid;
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
