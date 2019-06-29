package com.schoolmanapp.ktct_school.school.parent.model_class;

import java.util.List;

/**
 * Created by srishtiinnovative on 30/06/17.
 */

public class calender_model {


    /**
     * status : true
     * msg : Success
     * UserData : [{"attendanceDate":"6/24/2017","mornignShift":false,"eveningShift":true},{"attendanceDate":"6/28/2017","mornignShift":true,"eveningShift":false},{"attendanceDate":"6/30/2017","mornignShift":false,"eveningShift":false}]
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
         * attendanceDate : 6/24/2017
         * mornignShift : false
         * eveningShift : true
         */

        private String attendanceDate;
        private boolean mornignShift;
        private boolean eveningShift;

        public String getAttendanceDate() {
            return attendanceDate;
        }

        public void setAttendanceDate(String attendanceDate) {
            this.attendanceDate = attendanceDate;
        }

        public boolean isMornignShift() {
            return mornignShift;
        }

        public void setMornignShift(boolean mornignShift) {
            this.mornignShift = mornignShift;
        }

        public boolean isEveningShift() {
            return eveningShift;
        }

        public void setEveningShift(boolean eveningShift) {
            this.eveningShift = eveningShift;
        }
    }
}
