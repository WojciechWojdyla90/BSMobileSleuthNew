package com.Biosys.Naming;

/**
 * Created by wojdy_000 on 2015-05-12.
 */
public class BSMobileMessage {
    int id;
    int conversationId;
    int senderId;
    String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BSMobileMessage(){}

    public BSMobileMessage(int _id,int _conversationId, int _senderId, String _content){
        this.id = _id;
        this.conversationId = _conversationId;
        this.content = _content;
        this.senderId = _senderId;
    }
}
