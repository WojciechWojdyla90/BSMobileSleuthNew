package com.Biosys.WebserviceComunication;

import java.util.ArrayList;

import com.Biosys.Naming.BSEvent;
import com.Biosys.Naming.BSMessage;
import com.Biosys.Naming.BSTrip;
import com.Biosys.Naming.BSUser;

public interface IServiceChannel {
	//boolean UserAuthorize(String login, String password) throws Exception;
	ArrayList<BSUser> GetBSUsersToRegister() throws Exception;
	ArrayList<BSUser> GetAllBSUsers() throws Exception;
	Boolean RegisterBSUser(String id,String password) throws Exception;
	ArrayList<BSMessage> GetBSUserMessages(int userId) throws Exception;
	Boolean SendMessage(int sender,int reciver,String content) throws Exception;
	ArrayList<BSTrip> GetBSUserTrips(int userId) throws Exception;
	ArrayList<BSEvent> GetBSUserEvents(int userId) throws Exception;
	Boolean SendEvent(String checktime, int checktype, int userId,String longitude,String latitude,int tripId ) throws Exception;
}
