package com.costmgn.costmgnsrv.utils;

public class WebApiResponse<T> {
    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = 1;
    private int code;
    private String error;
    private T data;


    public static <T> WebApiResponse<T> success(T data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(SUCCESS_CODE);
        response.setData(data);
        return response;
    }

    public static <T> WebApiResponse<T> error(String errorMessage) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(ERROR_CODE);
        response.setError(errorMessage);
        return response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
