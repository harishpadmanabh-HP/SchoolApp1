package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 14/06/17.
 */

public class Mod_Teacher_List {


    /**
     * status : true
     * msg : Success
     * UserData : [{"TeacherId":18,"TeacherSpecialId":"TR4V98","TeacherName":"Test Teacher","SchoolId":19,"ContactNumber":"2545652365","Email":"gsst@gmail.com","TimeStamp":"2017-06-14T04:58:37.35","TeacherGuid":"7e34ff61-bbd1-4fed-97b2-8179daa2b07c","IsActive":true,"FilePath":"/Media/Teacher/Profile/feb28599-0f38-4231-b6c4-4c5f47007e84.jpeg","TeacherClass":[{"TeacherClassId":5,"TeacherId":18,"ClassId":12,"DivisionId":20,"ClassName":"1","DivisionName":"A"}]}]
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
         * TeacherId : 18
         * TeacherSpecialId : TR4V98
         * TeacherName : Test Teacher
         * SchoolId : 19
         * ContactNumber : 2545652365
         * Email : gsst@gmail.com
         * TimeStamp : 2017-06-14T04:58:37.35
         * TeacherGuid : 7e34ff61-bbd1-4fed-97b2-8179daa2b07c
         * IsActive : true
         * FilePath : /Media/Teacher/Profile/feb28599-0f38-4231-b6c4-4c5f47007e84.jpeg
         * TeacherClass : [{"TeacherClassId":5,"TeacherId":18,"ClassId":12,"DivisionId":20,"ClassName":"1","DivisionName":"A"}]
         */

        private int TeacherId;
        private String TeacherSpecialId;
        private String TeacherName;
        private int SchoolId;
        private String ContactNumber;
        private String Email;
        private String TimeStamp;
        private String TeacherGuid;
        private boolean IsActive;
        private String FilePath;
        private List<TeacherClassBean> TeacherClass;

        public int getTeacherId() {
            return TeacherId;
        }

        public void setTeacherId(int TeacherId) {
            this.TeacherId = TeacherId;
        }

        public String getTeacherSpecialId() {
            return TeacherSpecialId;
        }

        public void setTeacherSpecialId(String TeacherSpecialId) {
            this.TeacherSpecialId = TeacherSpecialId;
        }

        public String getTeacherName() {
            return TeacherName;
        }

        public void setTeacherName(String TeacherName) {
            this.TeacherName = TeacherName;
        }

        public int getSchoolId() {
            return SchoolId;
        }

        public void setSchoolId(int SchoolId) {
            this.SchoolId = SchoolId;
        }

        public String getContactNumber() {
            return ContactNumber;
        }

        public void setContactNumber(String ContactNumber) {
            this.ContactNumber = ContactNumber;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getTimeStamp() {
            return TimeStamp;
        }

        public void setTimeStamp(String TimeStamp) {
            this.TimeStamp = TimeStamp;
        }

        public String getTeacherGuid() {
            return TeacherGuid;
        }

        public void setTeacherGuid(String TeacherGuid) {
            this.TeacherGuid = TeacherGuid;
        }

        public boolean isIsActive() {
            return IsActive;
        }

        public void setIsActive(boolean IsActive) {
            this.IsActive = IsActive;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public List<TeacherClassBean> getTeacherClass() {
            return TeacherClass;
        }

        public void setTeacherClass(List<TeacherClassBean> TeacherClass) {
            this.TeacherClass = TeacherClass;
        }

        public static class TeacherClassBean {
            /**
             * TeacherClassId : 5
             * TeacherId : 18
             * ClassId : 12
             * DivisionId : 20
             * ClassName : 1
             * DivisionName : A
             */

            private int TeacherClassId;
            private int TeacherId;
            private int ClassId;
            private int DivisionId;
            private String ClassName;
            private String DivisionName;

            public int getTeacherClassId() {
                return TeacherClassId;
            }

            public void setTeacherClassId(int TeacherClassId) {
                this.TeacherClassId = TeacherClassId;
            }

            public int getTeacherId() {
                return TeacherId;
            }

            public void setTeacherId(int TeacherId) {
                this.TeacherId = TeacherId;
            }

            public int getClassId() {
                return ClassId;
            }

            public void setClassId(int ClassId) {
                this.ClassId = ClassId;
            }

            public int getDivisionId() {
                return DivisionId;
            }

            public void setDivisionId(int DivisionId) {
                this.DivisionId = DivisionId;
            }

            public String getClassName() {
                return ClassName;
            }

            public void setClassName(String ClassName) {
                this.ClassName = ClassName;
            }

            public String getDivisionName() {
                return DivisionName;
            }

            public void setDivisionName(String DivisionName) {
                this.DivisionName = DivisionName;
            }
        }
    }
}
