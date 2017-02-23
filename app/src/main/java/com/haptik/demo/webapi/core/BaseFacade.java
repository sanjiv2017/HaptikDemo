package com.haptik.demo.webapi.core;


import com.haptik.demo.webapi.core.response.IResponseSubscribe;

import java.util.HashMap;


/**
 * Created by Intelliswift on 22/02/2017.
 */

public class BaseFacade implements IBaseFacade {

    protected final IResponseSubscribe mResponseSubscribe;
   // protected final IApplicationFacade mApplicationFacade;
    protected String TAG;

    public BaseFacade(IResponseSubscribe responseSubscribe) {
        this.mResponseSubscribe = responseSubscribe;
        //this.mApplicationFacade = applicationFacade;
    }


    @Override
    public void setTag(String tag) {
        TAG = tag;
    }

    private HashMap<String, Object> getBodyParameterMap(String methodName) {
        HashMap<String, Object> bodyParameter = new HashMap<>();
        bodyParameter.put("token", TOKEN);
        bodyParameter.put("method", methodName);

        return bodyParameter;
    }

    protected HashMap<String, Object> getRequestBody(String methodName) {
       // UserResponse userResponse = mApplicationFacade.getUserResponse();
        HashMap<String, Object> bodyParameter = getBodyParameterMap(methodName);
        /*if (userResponse != null) {
            if(userResponse.getData() != null) {
                String customerID = userResponse.getData().getId();
                bodyParameter.put("cust_id", customerID);
            }
        }*/
        return bodyParameter;
    }
}
