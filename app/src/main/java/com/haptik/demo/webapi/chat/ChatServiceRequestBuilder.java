package com.haptik.demo.webapi.chat;



import com.haptik.demo.webapi.core.request.RetroRequestBuilder;
import com.haptik.demo.webapi.core.response.ChatApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Sanjiv Patel on 13/02/17.
 *
 * This class handles to create  Retrofit Client.
 */
public class ChatServiceRequestBuilder extends RetroRequestBuilder {

    public MyService getService() {
        return super.getClient().create(MyService.class);
    }

    /**
     * This interface contains all the methods of the Web apis end points.
     */
    public interface MyService {

        @POST("test_data")
        Call<ChatApiResponse> getAllChatList();

    }
}
