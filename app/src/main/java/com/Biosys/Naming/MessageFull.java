package com.Biosys.Naming;

import java.util.ArrayList;

/**
 * Created by wojdy_000 on 2015-05-12.
 */
public class MessageFull {
    BSMobileMessage message;
    ArrayList<BSRodeInfo> rodeInfos;

    public BSMobileMessage getMessage() {
        return message;
    }

    public void setMessage(BSMobileMessage message) {
        this.message = message;
    }

    public ArrayList<BSRodeInfo> getRodeInfos() {
        return rodeInfos;
    }

    public void setRodeInfos(ArrayList<BSRodeInfo> rodeInfos) {
        this.rodeInfos = rodeInfos;
    }

    public MessageFull(){}

    public MessageFull(BSMobileMessage msg,ArrayList<BSRodeInfo> informations)
    {
        this.message = msg;
        this.rodeInfos = informations;
    }
}
