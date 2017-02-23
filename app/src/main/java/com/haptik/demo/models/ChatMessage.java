package com.haptik.demo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sanjiv on 22/02/17.
 */

public class ChatMessage {

    @Expose
    private String body;

    @Expose
    private String username;

    @Expose
    @SerializedName("image-url")
    private String imageUrl;

    @Expose
    @SerializedName("message-time")
    private String messageTime;

    private boolean isFavorite;


    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getBody() {
        return body;
    }

    public String getUsername() {
        return username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMessageTime() {
        return messageTime;
    }
}
