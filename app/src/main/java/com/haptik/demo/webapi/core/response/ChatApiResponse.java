package com.haptik.demo.webapi.core.response;

import com.google.gson.annotations.Expose;
import com.haptik.demo.models.ChatMessage;

import java.util.ArrayList;

/**
 * Created by sanjiv on 22/02/17.
 */
public class ChatApiResponse {

    @Expose
    private int count;

    @Expose
    private ArrayList<ChatMessage> messages;

    public int getCount() {
        return count;
    }

    public ArrayList<ChatMessage> getMessages() {
        return messages;
    }
}
