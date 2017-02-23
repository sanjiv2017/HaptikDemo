package com.haptik.demo.webapi.core.response;

import com.google.gson.annotations.Expose;

/**
 * Created by sanjiv on 13/09/16.
 */
public class APIError {

    @Expose
    private int error_code;

    @Expose
    private String message;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
