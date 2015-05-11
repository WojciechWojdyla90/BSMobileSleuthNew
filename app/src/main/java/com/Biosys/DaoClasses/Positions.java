package com.Biosys.DaoClasses;

import com.j256.ormlite.field.DatabaseField;

public class Positions {

	@DatabaseField(generatedId = true)
	int id;
	
	@DatabaseField
	String positionName;
	
	Positions() {
		
	}
	
	public Positions(String _positionName){
		//this.id = _id;
		this.positionName = _positionName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	
}
