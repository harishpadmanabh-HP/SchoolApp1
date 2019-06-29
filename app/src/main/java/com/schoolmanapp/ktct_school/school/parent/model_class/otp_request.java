package com.schoolmanapp.ktct_school.school.parent.model_class;

public class otp_request {
    /**
     * status : true
     * msg : Send an OTP in your contact number!
     * UserData : {"StudentId":16054,"Name":"Test Auto","ClassDetails":"X/A"}
     */

    private boolean status;
    private String msg;
    /**
     * StudentId : 16054
     * Name : Test Auto
     * ClassDetails : X/A
     */

    private UserDataBean UserData;

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

    public UserDataBean getUserData() {
        return UserData;
    }

    public void setUserData(UserDataBean UserData) {
        this.UserData = UserData;
    }

    public static class UserDataBean {
        private int StudentId;
        private String Name;
        private String ClassDetails;

        public int getStudentId() {
            return StudentId;
        }

        public void setStudentId(int StudentId) {
            this.StudentId = StudentId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getClassDetails() {
            return ClassDetails;
        }

        public void setClassDetails(String ClassDetails) {
            this.ClassDetails = ClassDetails;
        }
    }
}
