package com.antonromanov.angular4.spa4.model;

import java.util.List;

// Class for response from Server to Client
public class RequestList {

    String msg;
    List<Request> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Request> getResult() {
        return result;
    }

    public void setResult(List<Request> result) {
        this.result = result;
    }
}
