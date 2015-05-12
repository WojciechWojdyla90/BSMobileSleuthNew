package com.Biosys.Naming;

/**
 * Created by wojdy_000 on 2015-05-12.
 */
public class BSConversation {

    int id;
    String topic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public BSConversation(){
    }

    public BSConversation(int _id,String _topic){
        this.id = _id;
        this.topic = _topic;
    }
}
