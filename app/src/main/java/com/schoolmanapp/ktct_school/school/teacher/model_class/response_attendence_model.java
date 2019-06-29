package com.schoolmanapp.ktct_school.school.teacher.model_class;

/**
 * Created by srishtiinnovative on 19/06/17.
 */

public class response_attendence_model {

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
