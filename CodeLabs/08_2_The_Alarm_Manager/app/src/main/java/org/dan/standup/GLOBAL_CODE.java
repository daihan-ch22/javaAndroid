package org.dan.standup;

public enum GLOBAL_CODE {

    PRIMARY("primary_notification_channel") ,
    SECONDARY("secondary_notification_channel"),
    NOTIFICATION_ID(0);

    GLOBAL_CODE(String code) {
        this.code = code;
    }

    GLOBAL_CODE(int id){
        this.id = id;
    }

    public String code;

    public int id;
}
