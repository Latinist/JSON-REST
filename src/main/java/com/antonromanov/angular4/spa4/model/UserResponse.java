package com.antonromanov.angular4.spa4.model;


import java.util.List;

public class UserResponse {


    String msg;
    List<UserA> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<UserA> getResult() {
        return result;
    }

    public void setResult(UserA result) {
        this.result.add(result);
    }
}
