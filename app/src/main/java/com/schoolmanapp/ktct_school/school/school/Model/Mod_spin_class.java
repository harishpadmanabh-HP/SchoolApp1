package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 15/06/17.
 */

public class Mod_spin_class {

    /**
     * status : true
     * msg : Success
     * UserData : [{"ClassId":12,"ClassName":"1"},{"ClassId":13,"ClassName":"2 "},{"ClassId":14,"ClassName":"3"},{"ClassId":15,"ClassName":"10"},{"ClassId":16,"ClassName":"12"},{"ClassId":17,"ClassName":"4"}]
     */

    private boolean status;
    private String msg;
    private List<UserDataBean> UserData;

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

    public List<UserDataBean> getUserData() {
        return UserData;
    }

    public void setUserData(List<UserDataBean> UserData) {
        this.UserData = UserData;
    }

    public static class UserDataBean {
        /**
         * ClassId : 12
         * ClassName : 1
         */

        private int ClassId;
        private String ClassName;

        public int getClassId() {
            return ClassId;
        }

        public void setClassId(int ClassId) {
            this.ClassId = ClassId;
        }

        public String getClassName() {
            return ClassName;
        }

        public void setClassName(String ClassName) {
            this.ClassName = ClassName;
        }
    }
}
