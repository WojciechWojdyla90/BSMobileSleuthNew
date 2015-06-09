package com.Biosys.Naming;

import java.util.ArrayList;

/**
 * Created by wojdy_000 on 2015-05-12.
 */
public class ConversationFull {
    ArrayList<MessageFull> messages;
    ArrayList<MemberFull> members;
    BSConversation conversation;

    public BSConversation getConversation() {
        return conversation;
    }

    public void setConversation(BSConversation conversation) {
        this.conversation = conversation;
    }

    public ArrayList<MessageFull> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MessageFull> messages) {
        this.messages = messages;
    }

    public ArrayList<MemberFull> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<MemberFull> members) {
        this.members = members;
    }

    public ConversationFull(){}

    public ConversationFull(ArrayList<MessageFull> _messages,ArrayList<MemberFull> _members, BSConversation bsConversation){
        this.conversation = bsConversation;
        this.messages = _messages;
        this.members = _members;
    }
}
