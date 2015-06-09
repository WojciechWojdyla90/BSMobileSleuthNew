package com.Biosys.WebserviceComunication;

import java.util.ArrayList;


import com.Biosys.Naming.BSConversation;
import com.Biosys.Naming.BSConversationMember;
import com.Biosys.Naming.BSEvent;
import com.Biosys.Naming.BSMessage;
import com.Biosys.Naming.BSMobileMessage;
import com.Biosys.Naming.BSRodeInfo;
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
    public Boolean InsertMember(int convId, int userId) throws Exception {
        RestAPI api = new RestAPI();
        api.InsertMember(convId,userId);
        return true;
    }

    @Override
    public Boolean InsertRodeInfo(int userId, int messageId) throws Exception {
        RestAPI api = new RestAPI();
        api.InsertRodeInfo(userId,messageId);
        return true;
    }

    @Override
    public Boolean InsertConversation(String topic) throws Exception {
        RestAPI api = new RestAPI();
        api.InsertConversation(topic);
        return true;
    }

    @Override
    public ArrayList<BSConversation> GetLastConversation() throws Exception {
        RestAPI api = new RestAPI();
        JSONParser parser = new JSONParser();
        return parser.parseConversation(api.GetLastConversation());
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
    public ArrayList<BSMobileMessage> GetBSMobileMessages(int userId) throws Exception {
        RestAPI api = new RestAPI();
        JSONParser parser = new JSONParser();
        return parser.parseMobileMessage(api.GetUserMessages(userId));
    }

    @Override
    public ArrayList<BSRodeInfo> GetBSRodeInfos(int userId) throws Exception {
        RestAPI api = new RestAPI();
        JSONParser parser = new JSONParser();
        return parser.parseRodeInfo(api.GetRodeInfos(userId));
    }

    @Override
    public ArrayList<BSConversation> GetBSUserConversations(int userId) throws Exception {
        RestAPI api = new RestAPI();
        JSONParser parser = new JSONParser();
        return parser.parseConversation(api.GetUserConversations(userId));
    }

    @Override
    public ArrayList<BSConversationMember> GetBSConversationMembers(int userId) throws Exception {
        RestAPI api = new RestAPI();
        JSONParser parser = new JSONParser();
        return parser.parseConversationMembers(api.GetUserConversationMembers(userId));
    }

    @Override
    public Boolean SendMessage(int convId, int senderID, String content) throws Exception {
        RestAPI api = new RestAPI();
        api.SendMessage(convId,senderID,content);
        return true;
    }

    @Override
	public ArrayList<BSEvent> GetBSUserEvents(int userId) throws Exception {
		RestAPI api = new RestAPI();
		JSONParser parser = new JSONParser();
		return parser.parseEvent(api.GetAllUserEvents(userId));
	}
}




