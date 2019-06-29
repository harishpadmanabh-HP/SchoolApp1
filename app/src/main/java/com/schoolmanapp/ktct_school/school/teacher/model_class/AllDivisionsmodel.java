package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.util.List;

public class AllDivisionsmodel {

    /**
     * status : true
     * msg : Success
     * UserData : [{"DivisionId":123,"ClassId":61,"ClassName":"6","DivisionName":"A"},{"DivisionId":124,"ClassId":61,"ClassName":"6","DivisionName":"B"},{"DivisionId":125,"ClassId":61,"ClassName":"6","DivisionName":"C"}]
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
         * DivisionId : 123
         * ClassId : 61
         * ClassName : 6
         * DivisionName : A
         */

        private int DivisionId;
        private int ClassId;
        private String ClassName;
        private String DivisionName;

        public int getDivisionId() {
            return DivisionId;
        }

        public void setDivisionId(int DivisionId) {
            this.DivisionId = DivisionId;
        }

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

        public String getDivisionName() {
            return DivisionName;
        }

        public void setDivisionName(String DivisionName) {
            this.DivisionName = DivisionName;
        }
    }
}
