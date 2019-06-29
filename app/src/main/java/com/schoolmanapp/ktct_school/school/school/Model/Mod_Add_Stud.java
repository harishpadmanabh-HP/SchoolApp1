package com.schoolmanapp.ktct_school.school.school.Model;

/**
 * Created by srishtiinnovative on 19/06/17.
 */

public class Mod_Add_Stud {

    /**
     * status : true
     * msg : Success
     * UserData : 7895629
     */

    private boolean status;
    private String msg;
    private String UserData;

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

    public String getUserData() {
        return UserData;
    }

    public void setUserData(String UserData) {
        this.UserData = UserData;
    }
}
