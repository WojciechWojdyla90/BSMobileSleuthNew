package com.Biosys.Controlers;

import com.Biosys.Naming.BSConversationMember;
import com.Biosys.Naming.ConversationFull;
import com.Biosys.Naming.BSMessage;
import com.Biosys.Naming.BSRodeInfo;
import com.Biosys.Naming.MemberFull;
import com.Biosys.Naming.MessageFull;
import com.Biosys.Naming.BSMobileMessage;
import com.Biosys.Naming.BSConversation;
import com.Biosys.Naming.BSUser;
import com.Biosys.Naming.BSRodeInfo;
import com.Biosys.Naming.BSMobileMessage;
import com.Biosys.Naming.BSConversation;
import java.util.ArrayList;

/**
 * Created by wojdy_000 on 2015-06-07.
 */
public class ConversationsControler {
    public int getUserId() {
        return userId;
    }

    public ArrayList<ConversationFull> getConversations() {
        return conversations;
    }

    private int userId;
    private ArrayList<ConversationFull> conversations;

    public ConversationsControler(int user){
        this.userId = user;
        this.conversations = new ArrayList<ConversationFull>();
    }

    public void createConversationsDatas(ArrayList<BSUser> users,ArrayList<BSConversation> bsConversations,ArrayList<BSMobileMessage> messages,
                                         ArrayList<BSConversationMember> members, ArrayList<BSRodeInfo>rodeInfos){
        ArrayList<ConversationFull> conversationFulls = new ArrayList<ConversationFull>();

        for(BSConversation conv : bsConversations){
            ConversationFull convFull = new ConversationFull();
            convFull.setConversation(conv);
            //set messages
            ArrayList<BSMobileMessage> messTmp = new ArrayList<BSMobileMessage>();

            for(BSMobileMessage mess :  messages){
                if(mess.getConversationId() == conv.getId()){
                    messTmp.add(mess);
                }
            }

            ArrayList<MessageFull> messageFulls = new ArrayList<MessageFull>();

            for(BSMobileMessage mess : messTmp){
                MessageFull messageFull = new MessageFull();
                messageFull.setMessage(mess);

                ArrayList<BSRodeInfo> bsRodeInfos = new ArrayList<BSRodeInfo>();
                for(BSRodeInfo info : rodeInfos){
                    if(mess.getId() == info.getMessageId()){
                        bsRodeInfos.add(info);
                    }
                }

                messageFull.setRodeInfos(bsRodeInfos);
                messageFulls.add(messageFull);
            }

            convFull.setMessages(messageFulls);

            //set members
            ArrayList<MemberFull> memberFulls = new ArrayList<MemberFull>();

            for(BSConversationMember bsConversationMember : members){
                if(bsConversationMember.getConversationID() == conv.getId()){
                MemberFull memberFull = new MemberFull();

                memberFull.setMember(bsConversationMember);

                for(BSUser user : users){
                    if(bsConversationMember.getUserId() == user.getId()){
                        memberFull.setUser(user);
                    }
                }

                memberFulls.add(memberFull);
                }
            }

            convFull.setMembers(memberFulls);

            conversationFulls.add(convFull);
        }

        this.conversations = conversationFulls;
    }
    //public

}
