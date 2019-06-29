package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.util.List;

/**
 * Created by srishtiinnovative on 08/02/18.
 */

public class teachermessage_model {

    /**
     * status : true
     * msg : Success
     * UserData : [{"CurrentTime":"2018-02-13T11:02:40.0702683","MessageId":19,"SenderId":34,"StudentId":11761,"Subject":"Show","Description":"Tesy","FilePath":"/Media/Parent/Message/1518158867385.jpg","IsActive":true,"TimeStamp":"2018-02-09T06:48:41.31","parentNamr":"Anoop","studentName":"ALIYA FATHIMA","ReadStatus":false},{"CurrentTime":"2018-02-13T11:02:40.0702683","MessageId":18,"SenderId":34,"StudentId":11761,"Subject":"test","Description":"tessssttt","FilePath":"","IsActive":true,"TimeStamp":"2018-02-09T06:37:02.733","parentNamr":"Anoop","studentName":"ALIYA FATHIMA","ReadStatus":false},{"CurrentTime":"2018-02-13T11:02:40.0702683","MessageId":17,"SenderId":34,"StudentId":11761,"Subject":"hello","Description":"ffg","FilePath":"","IsActive":true,"TimeStamp":"2018-02-09T06:23:17.49","parentNamr":"Anoop","studentName":"ALIYA FATHIMA","ReadStatus":false},{"CurrentTime":"2018-02-13T11:02:40.0702683","MessageId":16,"SenderId":34,"StudentId":11761,"Subject":"test","Description":"test","FilePath":"","IsActive":true,"TimeStamp":"2018-02-09T05:02:44.193","parentNamr":"Anoop","studentName":"ALIYA FATHIMA","ReadStatus":false},{"CurrentTime":"2018-02-13T11:02:40.0702683","MessageId":15,"SenderId":34,"StudentId":11761,"Subject":"hi","Description":"hello","FilePath":"","IsActive":true,"TimeStamp":"2018-02-09T04:43:20.327","parentNamr":"Anoop","studentName":"ALIYA FATHIMA","ReadStatus":false},{"CurrentTime":"2018-02-13T11:02:40.0702683","MessageId":14,"SenderId":34,"StudentId":11761,"Subject":"return gift","Description":"1234444","FilePath":"","IsActive":true,"TimeStamp":"2018-02-08T13:39:09.417","parentNamr":"Anoop","studentName":"ALIYA FATHIMA","ReadStatus":false},{"CurrentTime":"2018-02-13T11:02:40.0702683","MessageId":13,"SenderId":34,"StudentId":11761,"Subject":"Return Gift","Description":"Mock up\n\nNelson","FilePath":"","IsActive":true,"TimeStamp":"2018-02-08T13:38:30.717","parentNamr":"Anoop","studentName":"ALIYA FATHIMA","ReadStatus":false},{"CurrentTime":"2018-02-13T11:02:40.0702683","MessageId":12,"SenderId":34,"StudentId":11761,"Subject":"got it","Description":"ok","FilePath":"/Media/Parent/Message/1518090129437.jpg","IsActive":true,"TimeStamp":"2018-02-08T11:43:02.653","parentNamr":"Anoop","studentName":"ALIYA FATHIMA","ReadStatus":true},{"CurrentTime":"2018-02-13T11:02:40.0702683","MessageId":10,"SenderId":34,"StudentId":11761,"Subject":"ghj","Description":"vbv","FilePath":"","IsActive":true,"TimeStamp":"2018-02-08T05:12:00.127","parentNamr":"Anoop","studentName":"ALIYA FATHIMA","ReadStatus":false},{"CurrentTime":"2018-02-13T11:02:40.0702683","MessageId":9,"SenderId":34,"StudentId":11761,"Subject":"Hi","Description":"Meeting","FilePath":"","IsActive":true,"TimeStamp":"2018-02-06T04:02:58.037","parentNamr":"Anoop","studentName":"ALIYA FATHIMA","ReadStatus":true}]
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
         * CurrentTime : 2018-02-13T11:02:40.0702683
         * MessageId : 19
         * SenderId : 34
         * StudentId : 11761
         * Subject : Show
         * Description : Tesy
         * FilePath : /Media/Parent/Message/1518158867385.jpg
         * IsActive : true
         * TimeStamp : 2018-02-09T06:48:41.31
         * parentNamr : Anoop
         * studentName : ALIYA FATHIMA
         * ReadStatus : false
         */

        private String CurrentTime;
        private int MessageId;
        private int SenderId;
        private int StudentId;
        private String Subject;
        private String Description;
        private String FilePath;
        private boolean IsActive;
        private String TimeStamp;
        private String parentNamr;
        private String studentName;
        private boolean ReadStatus;

        public String getCurrentTime() {
            return CurrentTime;
        }

        public void setCurrentTime(String CurrentTime) {
            this.CurrentTime = CurrentTime;
        }

        public int getMessageId() {
            return MessageId;
        }

        public void setMessageId(int MessageId) {
            this.MessageId = MessageId;
        }

        public int getSenderId() {
            return SenderId;
        }

        public void setSenderId(int SenderId) {
            this.SenderId = SenderId;
        }

        public int getStudentId() {
            return StudentId;
        }

        public void setStudentId(int StudentId) {
            this.StudentId = StudentId;
        }

        public String getSubject() {
            return Subject;
        }

        public void setSubject(String Subject) {
            this.Subject = Subject;
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

        public boolean isIsActive() {
            return IsActive;
        }

        public void setIsActive(boolean IsActive) {
            this.IsActive = IsActive;
        }

        public String getTimeStamp() {
            return TimeStamp;
        }

        public void setTimeStamp(String TimeStamp) {
            this.TimeStamp = TimeStamp;
        }

        public String getParentNamr() {
            return parentNamr;
        }

        public void setParentNamr(String parentNamr) {
            this.parentNamr = parentNamr;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public boolean isReadStatus() {
            return ReadStatus;
        }

        public void setReadStatus(boolean ReadStatus) {
            this.ReadStatus = ReadStatus;
        }
    }
}
