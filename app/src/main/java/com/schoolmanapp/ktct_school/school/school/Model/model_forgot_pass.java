package com.schoolmanapp.ktct_school.school.school.Model;

/**
 * Created by srishtiinnovative on 27/07/17.
 */

public class model_forgot_pass {


    /**
     * status : false
     * msg : Email Not Exists
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
