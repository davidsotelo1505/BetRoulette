package com.masivian.roulettebets;

import java.io.Serializable;

public class GeneralResponse<T> implements Serializable {

   
    private static final long serialVersionUID = 1;


    private T data;

 
    private boolean success;


    private ApiError apiError;

    public GeneralResponse() {
	super();
    }

    public T getData() {
        return data;
    }

   
    public boolean isSuccess() {
        return success;
    }


    public void setData(T data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }

}

