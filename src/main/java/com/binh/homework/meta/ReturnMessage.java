package com.binh.homework.meta;

/**
 * Created by binh on 2017/4/10.
 */
public class ReturnMessage {
    private int code;
    private String message;
    private boolean result;

    public ReturnMessage(int code, String message, boolean result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
