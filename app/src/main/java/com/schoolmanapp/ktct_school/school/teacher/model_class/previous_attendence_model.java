package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.util.List;

/**
 * Created by srishtiinnovative on 22/06/17.
 */

public class previous_attendence_model {


    /**
     * status : true
     * msg : Success
     * UserData : [{"AttendanceId":69,"AttendanceData":false,"StudentId":26,"ShiftStatus":0},{"AttendanceId":70,"AttendanceData":false,"StudentId":27,"ShiftStatus":0}]
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
         * AttendanceId : 69
         * AttendanceData : false
         * StudentId : 26
         * ShiftStatus : 0
         */

        private int AttendanceId;
        private boolean AttendanceData;
        private int StudentId;
        private int ShiftStatus;

        public int getAttendanceId() {
            return AttendanceId;
        }

        public void setAttendanceId(int AttendanceId) {
            this.AttendanceId = AttendanceId;
        }

        public boolean isAttendanceData() {
            return AttendanceData;
        }

        public void setAttendanceData(boolean AttendanceData) {
            this.AttendanceData = AttendanceData;
        }

        public int getStudentId() {
            return StudentId;
        }

        public void setStudentId(int StudentId) {
            this.StudentId = StudentId;
        }

        public int getShiftStatus() {
            return ShiftStatus;
        }

        public void setShiftStatus(int ShiftStatus) {
            this.ShiftStatus = ShiftStatus;
        }
    }
}
