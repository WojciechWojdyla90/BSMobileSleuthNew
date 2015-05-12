package com.Biosys.Naming;

/**
 * Created by wojdy_000 on 2015-05-12.
 */
public class BSConversationMember {
    int id;
    int userId;
    int conversationID;

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

    public int getConversationID() {
        return conversationID;
    }

    public void setConversationID(int conversationID) {
        this.conversationID = conversationID;
    }

    public BSConversationMember(){

    }

    public BSConversationMember(int _id,int _userId,int _conversationId){
        this.conversationID = _conversationId;
        this.id = _id;
        this.userId = _userId;
    }
}
