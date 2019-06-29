package com.schoolmanapp.ktct_school.school.parent.model_class;

import java.util.List;

public class new_attendence_model {
    /**
     * status : true
     * msg : Success
     * UserData : [{"attendanceDate":"6/2/2018","mornignShift":1,"eveningShift":0},{"attendanceDate":"6/5/2018","mornignShift":1,"eveningShift":1},{"attendanceDate":"6/12/2018","mornignShift":2,"eveningShift":0},{"attendanceDate":"6/14/2018","mornignShift":1,"eveningShift":2}]
     */

    private boolean status;
    private String msg;
    /**
     * attendanceDate : 6/2/2018
     * mornignShift : 1
     * eveningShift : 0
     */

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
        private String attendanceDate;
        private int mornignShift;
        private int eveningShift;

        public String getAttendanceDate() {
            return attendanceDate;
        }

        public void setAttendanceDate(String attendanceDate) {
            this.attendanceDate = attendanceDate;
        }

        public int getMornignShift() {
            return mornignShift;
        }

        public void setMornignShift(int mornignShift) {
            this.mornignShift = mornignShift;
        }

        public int getEveningShift() {
            return eveningShift;
        }

        public void setEveningShift(int eveningShift) {
            this.eveningShift = eveningShift;
        }
    }
}
