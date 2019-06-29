package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 16/02/18.
 */

public class event_model {

    /**
     * status : true
     * msg : Success
     * UserData : [{"Head":"Circular","CircularId":1,"SchoolId":10093,"CircularDate":"2018-03-03T00:00:00","Description":"Hello dear ","FilePath":"","TimeStamp":"2018-02-16T09:46:42.3313718Z","FromStatus":0},{"Head":"Circular","CircularId":6,"SchoolId":10093,"CircularDate":"2018-03-17T00:00:00","Description":"xxxxxx","FilePath":"","TimeStamp":"2018-02-16T09:46:42.3313718Z","FromStatus":0},{"Head":"Circular","CircularId":7,"SchoolId":10093,"CircularDate":"2018-03-10T00:00:00","Description":"sssss","FilePath":"","TimeStamp":"2018-02-16T09:46:42.3313718Z","FromStatus":0},{"Head":"Circular","CircularId":8,"SchoolId":10093,"CircularDate":"2018-03-31T00:00:00","Description":"13223","FilePath":"","TimeStamp":"2018-02-16T09:46:42.3313718Z","FromStatus":0},{"Head":"Circular","CircularId":9,"SchoolId":10093,"CircularDate":"2018-03-03T00:00:00","Description":"ffff","FilePath":"","TimeStamp":"2018-02-16T09:46:42.3313718Z","FromStatus":0},{"Head":"Circular","CircularId":12,"SchoolId":10093,"CircularDate":"2018-03-10T00:00:00","Description":"xxxx","FilePath":"","TimeStamp":"2018-02-16T09:46:42.3313718Z","FromStatus":0},{"Head":"Circular","CircularId":15,"SchoolId":10093,"CircularDate":"2018-03-10T00:00:00","Description":"xxxxxx3233322","FilePath":"","TimeStamp":"2018-02-16T09:46:42.3313718Z","FromStatus":0}]
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
         * Head : Circular
         * CircularId : 1
         * SchoolId : 10093
         * CircularDate : 2018-03-03T00:00:00
         * Description : Hello dear
         * FilePath :
         * TimeStamp : 2018-02-16T09:46:42.3313718Z
         * FromStatus : 0
         */

        private String Head;
        private int CircularId;
        private int SchoolId;
        private String CircularDate;
        private String Description;
        private String FilePath;
        private String TimeStamp;
        private int FromStatus;

        public String getHead() {
            return Head;
        }

        public void setHead(String Head) {
            this.Head = Head;
        }

        public int getCircularId() {
            return CircularId;
        }

        public void setCircularId(int CircularId) {
            this.CircularId = CircularId;
        }

        public int getSchoolId() {
            return SchoolId;
        }

        public void setSchoolId(int SchoolId) {
            this.SchoolId = SchoolId;
        }

        public String getCircularDate() {
            return CircularDate;
        }

        public void setCircularDate(String CircularDate) {
            this.CircularDate = CircularDate;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public String getTimeStamp() {
            return TimeStamp;
        }

        public void setTimeStamp(String TimeStamp) {
            this.TimeStamp = TimeStamp;
        }

        public int getFromStatus() {
            return FromStatus;
        }

        public void setFromStatus(int FromStatus) {
            this.FromStatus = FromStatus;
        }
    }
}
