package com.haptik.demo.webapi.chat;

import com.haptik.demo.webapi.core.BaseFacade;
import com.haptik.demo.webapi.core.ServiceName;
import com.haptik.demo.webapi.core.response.ChatApiResponse;
import com.haptik.demo.webapi.core.response.IResponseSubscribe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sanjiv Patel on 22/02/17.
 * This class handles to contain all the methods for the chat service api.
 * This class call the web service over http request get the response and passed to the respinse
 * subscriber.
 *
 */
public class ChatServiceFacade extends BaseFacade implements IChatService,IResponseSubscribe {
    private final ChatServiceRequestBuilder myServiceRequestBuilder;

    public ChatServiceFacade(IResponseSubscribe responseSubscribe) {
        super(responseSubscribe);
        this.myServiceRequestBuilder = new ChatServiceRequestBuilder();

    }

    @Override
    public void getAllChats() {
        Call<ChatApiResponse> call = myServiceRequestBuilder.getService().getAllChatList();
        call.enqueue(new Callback<ChatApiResponse>() {
            @Override
            public void onResponse(Call<ChatApiResponse> call, Response<ChatApiResponse> response) {
                onSuccess(response.body(),TAG, ServiceName.CHAT_SERVICE);
            }

            @Override
            public void onFailure(Call<ChatApiResponse> call, Throwable t) {
                onError(t,ServiceName.CHAT_SERVICE);
            }
        });
    }

    @Override
    public void onSuccess(ChatApiResponse response, String tag, int apiCode) {
        mResponseSubscribe.onSuccess(response, TAG,apiCode);
    }

    @Override
    public void onError(Throwable t, int apiCode) {
        mResponseSubscribe.onError(t,apiCode);
    }


}
