package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

public class Mod_Message {

    /**
     * status : true
     * msg : Successful
     * UserData : [{"MessageId":10065,"SenderId":10107,"StudentId":29441,"Subject":"Hi","Description":"Ok Ma'am , I will ","FilePath":"","TimeStamp":"2018-05-28T12:34:06.08","Role":"Parent","Status":0},{"MessageId":20213,"SenderId":30448,"StudentId":29441,"Subject":"Hello","Description":"Dear , We published your students mark list , please visit our site .","FilePath":"","TimeStamp":"2018-05-28T12:31:29.283","Role":"Teacher","Status":1},{"MessageId":20212,"SenderId":30448,"StudentId":29441,"Subject":"Hello","Description":"Good evening ","FilePath":"","TimeStamp":"2018-05-28T12:29:32.597","Role":"Teacher","Status":1},{"MessageId":10064,"SenderId":10107,"StudentId":29441,"Subject":"Hi","Description":"Good evening teacher ","FilePath":"","TimeStamp":"2018-05-28T12:26:28.733","Role":"Parent","Status":0}]
     */

    private boolean status;
    private String msg;
    /**
     * MessageId : 10065
     * SenderId : 10107
     * StudentId : 29441
     * Subject : Hi
     * Description : Ok Ma'am , I will
     * FilePath :
     * TimeStamp : 2018-05-28T12:34:06.08
     * Role : Parent
     * Status : 0
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
        private int MessageId;
        private int SenderId;
        private int StudentId;
        private String Subject;
        private String Description;
        private String FilePath;
        private String TimeStamp;
        private String Role;
        private int Status;

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

        public String getTimeStamp() {
            return TimeStamp;
        }

        public void setTimeStamp(String TimeStamp) {
            this.TimeStamp = TimeStamp;
        }

        public String getRole() {
            return Role;
        }

        public void setRole(String Role) {
            this.Role = Role;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }
    }
}
