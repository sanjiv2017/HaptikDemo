package com.haptik.demo.webapi.core.request;


import com.haptik.demo.webapi.core.IOCContainer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetroRequestBuilder {



    private static Retrofit retrofit = null;


    protected  Retrofit getClient() {

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(IOCContainer.BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }




}
