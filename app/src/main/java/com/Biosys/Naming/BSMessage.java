package com.Biosys.Naming;


public class BSMessage {
	int id;
	int senderId;
	int reciverId;
	String content;
	String date;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getReciverId() {
		return reciverId;
	}

	public void setReciverId(int reciverId) {
		this.reciverId = reciverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	//public Date getDate() {
		//return date;
	//}

	//public void setDate(Date date) {
		//this.date = date;
	//}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BSMessage(){
		id = 0;
		senderId = 0;
		reciverId = 0;
		content = "";
		date = "";
	}
	
	public BSMessage(int _id, int _sender, int _reciver, String _content, String  _date){
		id = _id;
		senderId = _sender;
		reciverId = _reciver;
		content = _content;
		date = _date;
	}
}
