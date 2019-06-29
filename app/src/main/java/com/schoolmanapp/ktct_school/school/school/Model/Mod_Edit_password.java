package com.schoolmanapp.ktct_school.school.school.Model;

/**
 * Created by srishtiinnovative on 20/06/17.
 */

public class Mod_Edit_password {

    /**
     * status : true
     * msg : Success
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
