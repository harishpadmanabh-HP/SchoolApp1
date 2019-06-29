package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.util.List;

public class AllClassModel {

    /**
     * status : true
     * msg : Success
     * UserData : [{"ClassId":56,"ClassName":"1"},{"ClassId":57,"ClassName":"2"},{"ClassId":58,"ClassName":"3"},{"ClassId":59,"ClassName":"4"},{"ClassId":60,"ClassName":"5"},{"ClassId":61,"ClassName":"6"},{"ClassId":62,"ClassName":"7"}]
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
         * ClassId : 56
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
