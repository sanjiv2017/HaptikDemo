package com.haptik.demo.webapi.core.response;


public interface IResponseSubscribe {

    void onSuccess(ChatApiResponse response, String tag, int apiCode);

    void onError(Throwable t, int apiCode);


}
