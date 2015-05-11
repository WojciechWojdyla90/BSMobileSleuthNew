package com.Biosys.Naming;

public class BSUser {
	int id;
	String name;
	String surname;
	String password;
	BSPosition position;
	
	public BSUser(){
		id = 0;
		name = "";
		surname = "";
		password = "";
		position = new BSPosition();
	}
	
	public BSUser(int _id, String _name, String _surname,String _password ,BSPosition _position){
		id = _id;
		name = _name;
		surname = _surname;
		password = _password;
		position = _position;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BSPosition getPosition() {
		return position;
	}
	public void setPosition(BSPosition position) {
		this.position = position;
	} 
	
	
}
