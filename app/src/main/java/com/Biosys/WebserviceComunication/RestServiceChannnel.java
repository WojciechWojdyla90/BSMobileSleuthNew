package com.Biosys.WebserviceComunication;

import java.util.ArrayList;






import com.Biosys.Naming.BSEvent;
import com.Biosys.Naming.BSMessage;
import com.Biosys.Naming.BSTrip;
import com.Biosys.Naming.BSUser;

public class RestServiceChannnel implements IServiceChannel {

	public RestServiceChannnel(){
		
	}

	@Override
	public ArrayList<BSUser> GetBSUsersToRegister() throws Exception {
		RestAPI api = new RestAPI();
		JSONParser parser = new JSONParser();
		
		return parser.parseUser(api.GetUsersToRegistred());
	}

	@Override
	public Boolean RegisterBSUser(String id, String password) throws Exception {
		
		RestAPI api = new RestAPI();
		api.RegisterUser(Integer.parseInt(id), password);
		return true;
	}

	@Override
	public ArrayList<BSUser> GetAllBSUsers() throws Exception {
		RestAPI api = new RestAPI();
		JSONParser parser = new JSONParser();
		
		return parser.parseUser(api.GetAllUsers());
	}

	@Override
	public ArrayList<BSMessage> GetBSUserMessages(int userId) throws Exception {
		RestAPI api = new RestAPI();
		JSONParser parser = new JSONParser();
		return parser.parseMessages(api.GetAllUserMessage(userId));
	}

	@Override
	public Boolean SendMessage(int sender, int reciver, String content)
			throws Exception {
		RestAPI api = new RestAPI();
		api.SendMessage(sender, reciver, content);
		return true;
	}

	@Override
	public ArrayList<BSTrip> GetBSUserTrips(int userId) throws Exception {
		RestAPI api = new RestAPI();
		JSONParser parser = new JSONParser();
		return parser.parseTrip(api.GetAllUserTrips(userId));
	}

	@Override
	public Boolean SendEvent(String checktime, int checktype, int userId,
			String longitude, String latitude, int tripId) throws Exception {
		RestAPI api = new RestAPI();
		api.InsertEvent(checktime, checktype, userId, longitude, latitude, tripId);
		return true;
	}

	@Override
	public ArrayList<BSEvent> GetBSUserEvents(int userId) throws Exception {
		RestAPI api = new RestAPI();
		JSONParser parser = new JSONParser();
		return parser.parseEvent(api.GetAllUserEvents(userId));
	}
}




