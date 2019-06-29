package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 07/08/17.
 */

public class Mod_unassigned {


    /**
     * status : true
     * msg : Success
     * UserData : [{"ClassName":"10","ClassId":84,"Division":"G","DivisionId":171}]
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
         * ClassName : 10
         * ClassId : 84
         * Division : G
         * DivisionId : 171
         */

        private String ClassName;
        private int ClassId;
        private String Division;
        private int DivisionId;

        public String getClassName() {
            return ClassName;
        }

        public void setClassName(String ClassName) {
            this.ClassName = ClassName;
        }

        public int getClassId() {
            return ClassId;
        }

        public void setClassId(int ClassId) {
            this.ClassId = ClassId;
        }

        public String getDivision() {
            return Division;
        }

        public void setDivision(String Division) {
            this.Division = Division;
        }

        public int getDivisionId() {
            return DivisionId;
        }

        public void setDivisionId(int DivisionId) {
            this.DivisionId = DivisionId;
        }
    }
}
