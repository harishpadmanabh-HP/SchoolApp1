package com.schoolmanapp.ktct_school.school.parent.model_class;

import java.util.List;

/**
 * Created by srishtiinnovative on 29/06/17.
 */

public class message_model {


    /**
     * status : true
     * msg : Success
     * UserData : [{"MessageId":33,"TeacherId":68,"ToMsgSentId":10032,"Subject":"haii","Description":"hello","MessageType":1,"MessageTypeString":"ForStudent","Filepath":"/Media/School/Message/Cake Maker!  .pdf","IsActive":true,"Timestamp":"2017-07-12T09:59:40.34","teacherName":"ff","teacherContact":"456"}]
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
         * MessageId : 33
         * TeacherId : 68
         * ToMsgSentId : 10032
         * Subject : haii
         * Description : hello
         * MessageType : 1
         * MessageTypeString : ForStudent
         * Filepath : /Media/School/Message/Cake Maker!  .pdf
         * IsActive : true
         * Timestamp : 2017-07-12T09:59:40.34
         * teacherName : ff
         * teacherContact : 456
         */

        private int MessageId;
        private int TeacherId;
        private int ToMsgSentId;
        private String Subject;
        private String Description;
        private int MessageType;
        private String MessageTypeString;
        private String Filepath;
        private boolean IsActive;
        private String Timestamp;
        private String teacherName;
        private String teacherContact;

        public int getMessageId() {
            return MessageId;
        }

        public void setMessageId(int MessageId) {
            this.MessageId = MessageId;
        }

        public int getTeacherId() {
            return TeacherId;
        }

        public void setTeacherId(int TeacherId) {
            this.TeacherId = TeacherId;
        }

        public int getToMsgSentId() {
            return ToMsgSentId;
        }

        public void setToMsgSentId(int ToMsgSentId) {
            this.ToMsgSentId = ToMsgSentId;
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

        public int getMessageType() {
            return MessageType;
        }

        public void setMessageType(int MessageType) {
            this.MessageType = MessageType;
        }

        public String getMessageTypeString() {
            return MessageTypeString;
        }

        public void setMessageTypeString(String MessageTypeString) {
            this.MessageTypeString = MessageTypeString;
        }

        public String getFilepath() {
            return Filepath;
        }

        public void setFilepath(String Filepath) {
            this.Filepath = Filepath;
        }

        public boolean isIsActive() {
            return IsActive;
        }

        public void setIsActive(boolean IsActive) {
            this.IsActive = IsActive;
        }

        public String getTimestamp() {
            return Timestamp;
        }

        public void setTimestamp(String Timestamp) {
            this.Timestamp = Timestamp;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getTeacherContact() {
            return teacherContact;
        }

        public void setTeacherContact(String teacherContact) {
            this.teacherContact = teacherContact;
        }
    }
}
