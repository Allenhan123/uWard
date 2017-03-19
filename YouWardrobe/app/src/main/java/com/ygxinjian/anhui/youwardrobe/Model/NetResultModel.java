package com.ygxinjian.anhui.youwardrobe.Model;

/**
 * Created by ${Ua} on 2017/3/19.
 */

public class NetResultModel extends BaseModel {
    public static final  int RESULT_CODE_SUCCESS=400;

    private int code;
    private String message;

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
}
