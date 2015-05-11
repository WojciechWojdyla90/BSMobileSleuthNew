package com.Biosys.DaoClasses;

import com.j256.ormlite.field.DatabaseField;

public class User{

	@DatabaseField(generatedId = true)
	int id;
	
	@DatabaseField
	String name;
	
	@DatabaseField
	String surrname;
	
	@DatabaseField
	String email;
	
	@DatabaseField
	String login;
	
	@DatabaseField
	String password;
	
	@DatabaseField
	int mainDataBaseId;
	
	@DatabaseField
	int positionGroupId;
	
	User(){
		// needed by ormlite
	}
	
	public User(String _name, String _surname, String _email,String _login ,int _mainDataBaseId,int _positionGroupId, String _password){
		this.surrname = _surname;
		this.email = _email;
		this.mainDataBaseId = _mainDataBaseId;
		this.name = _name;
		this.positionGroupId = _positionGroupId;
		this.password = _password;
		this.login = _login;
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

	public String getSurrname() {
		return surrname;
	}

	public void setSurrname(String surrname) {
		this.surrname = surrname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMainDataBaseId() {
		return mainDataBaseId;
	}

	public void setMainDataBaseId(int mainDataBaseId) {
		this.mainDataBaseId = mainDataBaseId;
	}

	public int getPositionGroupId() {
		return positionGroupId;
	}

	public void setPositionGroupId(int positionGroupId) {
		this.positionGroupId = positionGroupId;
	}

	

	
	

	
}
