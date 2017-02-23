package com.haptik.demo.webapi.core.response;


import android.util.Log;

import java.util.concurrent.CopyOnWriteArrayList;


public class ResponsePublisher implements IResponseSubscribe {

    private final CopyOnWriteArrayList<IResponseSubscribe> responseObservers;

    public ResponsePublisher() {
        responseObservers = new CopyOnWriteArrayList<>();
    }

    public void registerResponseSubscribe(IResponseSubscribe responseObserver) {
        if (!responseObservers.contains(responseObserver)) {
            responseObservers.add(responseObserver);
        }

    }

    public void unregisterResponseSubscribe(IResponseSubscribe responseObserver) {
        responseObservers.remove(responseObserver);
    }



    @Override
    public void onSuccess(ChatApiResponse response, String tag, int apiMethodName) {
        System.err.println("ResponsePublisher.onResponse() : API Method : " + apiMethodName);
        Log.e(getClass().getName() ,"ResponsePublisher.onResponse() : API Method "+ apiMethodName);

        for (IResponseSubscribe observer : responseObservers) {
            observer.onSuccess(response, tag,apiMethodName);
        }

    }

    @Override
    public void onError(Throwable t, int apiCode) {
        System.err.println("ResponsePublisher.onErrorResponse(): API Method : " + apiCode);
        Log.e(getClass().getName() ,"ResponsePublisher.onErrorResponse(): API Method :  "+ apiCode);
        for (IResponseSubscribe observer : responseObservers) {
            observer.onError(t,apiCode);
        }
    }


}
