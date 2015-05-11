package com.Biosys.Naming;

public class BSPosition {
	int id;
	String name;
	
	public BSPosition(){
		id = 0;
		name = "";
	}
	
	public BSPosition(int _id,String _name){
		id = _id;
		name = _name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
