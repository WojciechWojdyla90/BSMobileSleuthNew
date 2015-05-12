package com.Biosys.Naming;

/**
 * Created by wojdy_000 on 2015-05-12.
 */
public class BSRodeInfo {
    int id;
    int userId;
    int messageId;

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

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public BSRodeInfo(){

    }

    public BSRodeInfo(int _id, int _userId,int _messageId){
        this.id = _id;
        this.userId = _userId;
        this.messageId = _messageId;
    }
}
