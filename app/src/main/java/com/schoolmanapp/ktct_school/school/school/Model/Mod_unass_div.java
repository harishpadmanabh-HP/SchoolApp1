package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 09/08/17.
 */

public class Mod_unass_div {

    /**
     * status : true
     * msg : Success
     * UserData : [{"Division":"G","DivisionId":171}]
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
         * Division : G
         * DivisionId : 171
         */

        private String Division;
        private int DivisionId;

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
