package com.haptik.demo.models;

import java.util.ArrayList;

/**
 * Created by sanjiv on 22/02/17.
 */

public class FavConversation {

    private int getFavCount;
    private int msgCount;

    private ArrayList<ChatMessage> msgList;

    public int getGetFavCount() {
        return getFavCount;
    }

    public void setGetFavCount(int getFavCount) {
        this.getFavCount = getFavCount;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public ArrayList<ChatMessage> getMsgList() {
        return msgList;
    }

    public void setMsgList(ArrayList<ChatMessage> msgList) {
        this.msgList = msgList;
    }
}
