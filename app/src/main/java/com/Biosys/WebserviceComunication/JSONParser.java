package com.Biosys.WebserviceComunication;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.Biosys.Naming.BSEvent;
import com.Biosys.Naming.BSMessage;
import com.Biosys.Naming.BSPosition;
import com.Biosys.Naming.BSTrip;
import com.Biosys.Naming.BSUser;

public class JSONParser {
	
	public JSONParser(){
		super();
    }
	
	public ArrayList<BSUser> parseUser(JSONObject object)
	{
		ArrayList<BSUser> result = new ArrayList<BSUser>();
		
		try{
			JSONArray jsArray = object.getJSONArray("Value");
			JSONObject jsObject = null;
			
			for(int i = 0; i < jsArray.length();i++)
			{
				jsObject = jsArray.getJSONObject(i);
				
				BSUser toAdd = new BSUser();
				toAdd.setId(jsObject.getInt("ID"));
				toAdd.setName(jsObject.getString("NAME"));
				toAdd.setSurname(jsObject.getString("SURNAME"));
				try{
					toAdd.setPassword(jsObject.getString("MOBILE_PASSWORD"));
				}catch(Exception e){
					Log.d("JSONParser -> parseUser", e.getMessage());
				}
				BSPosition pos = new BSPosition();
				pos.setId(jsObject.getInt("POSITION"));
				toAdd.setPosition(pos);
				result.add(toAdd);
			}
		}catch(JSONException e){
			Log.d("JSONParser -> parseUser", e.getMessage());
		}
		
		return result;
	}
	
	public ArrayList<BSMessage> parseMessages(JSONObject object)
	{
		ArrayList<BSMessage> result = new ArrayList<BSMessage>();
		
		try{
			JSONArray jsArray = object.getJSONArray("Value");
			JSONObject jsObject = null;
			
			for(int i = 0; i < jsArray.length();i++)
			{
				jsObject = jsArray.getJSONObject(i);
				
				BSMessage toAdd = new BSMessage();
				toAdd.setId(jsObject.getInt("ID"));
				toAdd.setSenderId(jsObject.getInt("SENDER_ID"));
				toAdd.setReciverId(jsObject.getInt("RECIVER_ID"));
				toAdd.setContent(jsObject.getString("CONTENT"));
				toAdd.setDate(jsObject.getString("DATE"));
				result.add(toAdd);
			}
		}catch(JSONException e){
			Log.d("JSONParser -> parseDate", e.getMessage());
		}
		
		return result;
	}

	public ArrayList<BSTrip> parseTrip(JSONObject object)
	{
		ArrayList<BSTrip> result = new ArrayList<BSTrip>();
		
		try{
			JSONArray jsArray = object.getJSONArray("Value");
			JSONObject jsObject = null;
			
			for(int i = 0; i < jsArray.length();i++)
			{
				jsObject = jsArray.getJSONObject(i);
				
				BSTrip toAdd = new BSTrip();
				toAdd.setId(jsObject.getInt("ID"));
				toAdd.setName(jsObject.getString("NAME"));
				toAdd.setUserId(jsObject.getInt("USER_ID"));
				result.add(toAdd);
			}
		}catch(JSONException e){
			Log.d("JSONParser -> parseTrip", e.getMessage());
		}
		
		return result;
	}
	
	public ArrayList<BSEvent> parseEvent(JSONObject object)
	{
		ArrayList<BSEvent> result = new ArrayList<BSEvent>();
		
		try{
			JSONArray jsArray = object.getJSONArray("Value");
			JSONObject jsObject = null;
			
			for(int i = 0; i < jsArray.length();i++)
			{
				jsObject = jsArray.getJSONObject(i);
				
				BSEvent toAdd = new BSEvent();
				toAdd.setId(jsObject.getInt("ID"));
				toAdd.setUserId(jsObject.getInt("USER_ID"));
				toAdd.setChecktype(jsObject.getInt("CHECKTYPE"));
				toAdd.setChecktime(jsObject.getString("CHECKTIME"));
				toAdd.setLongitiude(jsObject.getDouble("LONGITUDE"));
				toAdd.setLatitude(jsObject.getDouble("LATITUDE"));
				toAdd.setTripId(jsObject.getInt("TRIP_ID"));
				result.add(toAdd);
				Log.d("JSONParser -> parseEvent", "i");
				
			}
		}catch(JSONException e){
			Log.d("JSONParser -> parseEvent", e.getMessage());
		}
		
		return result;
	}
}
