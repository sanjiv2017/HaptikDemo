package com.haptik.demo.webapi.core;

import android.content.Context;

import com.haptik.demo.webapi.chat.ChatServiceFacade;
import com.haptik.demo.webapi.core.response.ResponsePublisher;

import java.util.WeakHashMap;

/**
 * Created by Sanjiv Patel on 22/02/2017.
 *
 */

public class IOCContainer {

    public static String BaseURL = "http://haptik.mobi/android/";

    private final static WeakHashMap<Integer, Object> objectContainer = new WeakHashMap<>();
    private static IOCContainer instance;
    public ResponsePublisher publisher;
    private Context context;


    private IOCContainer() {
        publisher = new ResponsePublisher();
    }

    public static IOCContainer getInstance() {

        if (instance == null) {
            instance = new IOCContainer();
        }
        return instance;
    }


    public Context getContext() {
        return context;
    }

    public void init(Context context) {
        this.context = context;
    }

    public IBaseFacade getObject(Integer name, String tag) {
        IBaseFacade object = (IBaseFacade) objectContainer.get(name);
        switch (name) {
            case ServiceName.CHAT_SERVICE:
                object = new ChatServiceFacade(publisher);
                break;

        }
        if (object != null) {
            object.setTag(tag);
        }
        return object;
    }
}
