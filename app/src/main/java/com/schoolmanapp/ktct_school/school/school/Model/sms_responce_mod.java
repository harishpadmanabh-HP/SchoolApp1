package com.schoolmanapp.ktct_school.school.school.Model;

public class sms_responce_mod {
    /**
     * status : true
     * msg : Successful
     */

    private boolean status;
    private String msg;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
