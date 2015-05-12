package com.Biosys.Naming;

/**
 * Created by wojdy_000 on 2015-05-12.
 */
public class MemberFull {
    BSConversationMember member;
    BSUser user;

    public BSConversationMember getMember() {
        return member;
    }

    public void setMember(BSConversationMember member) {
        this.member = member;
    }

    public BSUser getUser() {
        return user;
    }

    public void setUser(BSUser user) {
        this.user = user;
    }

    public MemberFull(){}

    public MemberFull(BSConversationMember _member, BSUser _user){
        member = _member;
        user = _user;
    }
}
