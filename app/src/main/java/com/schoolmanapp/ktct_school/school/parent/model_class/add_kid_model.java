package com.schoolmanapp.ktct_school.school.parent.model_class;

/**
 * Created by srishtiinnovative on 23/06/17.
 */

public class add_kid_model {

    /**
     * status : false
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
