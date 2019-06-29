package com.schoolmanapp.ktct_school.school.driver.Model;

/**
 * Created by srishtiinnovative on 23/06/17.
 */

public class Mod_start_response {

    /**
     * status : true
     * msg : Success
     * UserData : 10303
     */

    private boolean status;
    private String msg;
    private int UserData;

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

    public int getUserData() {
        return UserData;
    }

    public void setUserData(int UserData) {
        this.UserData = UserData;
    }
}
