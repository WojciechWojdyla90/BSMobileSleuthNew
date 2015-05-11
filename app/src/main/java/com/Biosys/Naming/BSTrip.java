package com.Biosys.Naming;

public class BSTrip {
	int id;
	int userId;
	String name;
	
	public BSTrip(){
		id = 0;
		userId = 0;
		name = "";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BSTrip(int _id, int _userid, String _name){
		id = _id;
		userId = _userid;
		name = _name;
	}
}
