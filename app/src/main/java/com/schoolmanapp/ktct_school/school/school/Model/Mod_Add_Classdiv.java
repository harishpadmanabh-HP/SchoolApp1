package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 13/06/17.
 */

public class Mod_Add_Classdiv {

    /**
     * status : true
     * msg : Success
     * UserData : {"SchoolId":1,"SchoolName":"Mery Matha Public School","Address":"Test","City":"Test","Email":"TestPassword","Website":"Test","Password":"Test","Contact":"Test","TimeStamp":"2017-05-30T09:32:28.59","SchoolGuidId":"1b6c67e9-45cd-4f19-8fff-78c18e7450fe","IsActive":true,"State":"Test","FilePath":"/Media/School/Profile/14798059-9b06-4b04-9f20-60fd2de24f7b.jpeg","Divisions":[{"DivisionId":7,"ClassId":5,"DivisionName":"A","TimeStamp":"2017-06-13T05:25:56.1548Z","DivisionGuid":"0762610e-a2d2-4f3f-a63b-3240b7b4ffa0","IsActive":true,"ClassName":"3"},{"DivisionId":3,"ClassId":2,"DivisionName":"A","TimeStamp":"2017-05-31T07:43:24.137","DivisionGuid":"de3ffe07-d350-4574-b701-217e7f9c2312","IsActive":true,"ClassName":"1"},{"DivisionId":5,"ClassId":4,"DivisionName":"A","TimeStamp":"2017-06-02T05:54:51.61","DivisionGuid":"c905cbde-c51b-4b18-a568-46e08a58d2ad","IsActive":true,"ClassName":"2"},{"DivisionId":6,"ClassId":4,"DivisionName":"H","TimeStamp":"2017-06-12T10:15:05.36","DivisionGuid":"392db4e6-f0a1-4156-b5da-bd87cdf38ef8","IsActive":true,"ClassName":"2"}]}
     */

    private boolean status;
    private String msg;
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
        /**
         * SchoolId : 1
         * SchoolName : Mery Matha Public School
         * Address : Test
         * City : Test
         * Email : TestPassword
         * Website : Test
         * Password : Test
         * Contact : Test
         * TimeStamp : 2017-05-30T09:32:28.59
         * SchoolGuidId : 1b6c67e9-45cd-4f19-8fff-78c18e7450fe
         * IsActive : true
         * State : Test
         * FilePath : /Media/School/Profile/14798059-9b06-4b04-9f20-60fd2de24f7b.jpeg
         * Divisions : [{"DivisionId":7,"ClassId":5,"DivisionName":"A","TimeStamp":"2017-06-13T05:25:56.1548Z","DivisionGuid":"0762610e-a2d2-4f3f-a63b-3240b7b4ffa0","IsActive":true,"ClassName":"3"},{"DivisionId":3,"ClassId":2,"DivisionName":"A","TimeStamp":"2017-05-31T07:43:24.137","DivisionGuid":"de3ffe07-d350-4574-b701-217e7f9c2312","IsActive":true,"ClassName":"1"},{"DivisionId":5,"ClassId":4,"DivisionName":"A","TimeStamp":"2017-06-02T05:54:51.61","DivisionGuid":"c905cbde-c51b-4b18-a568-46e08a58d2ad","IsActive":true,"ClassName":"2"},{"DivisionId":6,"ClassId":4,"DivisionName":"H","TimeStamp":"2017-06-12T10:15:05.36","DivisionGuid":"392db4e6-f0a1-4156-b5da-bd87cdf38ef8","IsActive":true,"ClassName":"2"}]
         */

        private int SchoolId;
        private String SchoolName;
        private String Address;
        private String City;
        private String Email;
        private String Website;
        private String Password;
        private String Contact;
        private String TimeStamp;
        private String SchoolGuidId;
        private boolean IsActive;
        private String State;
        private String FilePath;
        private List<DivisionsBean> Divisions;

        public int getSchoolId() {
            return SchoolId;
        }

        public void setSchoolId(int SchoolId) {
            this.SchoolId = SchoolId;
        }

        public String getSchoolName() {
            return SchoolName;
        }

        public void setSchoolName(String SchoolName) {
            this.SchoolName = SchoolName;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getWebsite() {
            return Website;
        }

        public void setWebsite(String Website) {
            this.Website = Website;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getContact() {
            return Contact;
        }

        public void setContact(String Contact) {
            this.Contact = Contact;
        }

        public String getTimeStamp() {
            return TimeStamp;
        }

        public void setTimeStamp(String TimeStamp) {
            this.TimeStamp = TimeStamp;
        }

        public String getSchoolGuidId() {
            return SchoolGuidId;
        }

        public void setSchoolGuidId(String SchoolGuidId) {
            this.SchoolGuidId = SchoolGuidId;
        }

        public boolean isIsActive() {
            return IsActive;
        }

        public void setIsActive(boolean IsActive) {
            this.IsActive = IsActive;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public List<DivisionsBean> getDivisions() {
            return Divisions;
        }

        public void setDivisions(List<DivisionsBean> Divisions) {
            this.Divisions = Divisions;
        }

        public static class DivisionsBean {
            /**
             * DivisionId : 7
             * ClassId : 5
             * DivisionName : A
             * TimeStamp : 2017-06-13T05:25:56.1548Z
             * DivisionGuid : 0762610e-a2d2-4f3f-a63b-3240b7b4ffa0
             * IsActive : true
             * ClassName : 3
             */

            private int DivisionId;
            private int ClassId;
            private String DivisionName;
            private String TimeStamp;
            private String DivisionGuid;
            private boolean IsActive;
            private String ClassName;

            public int getDivisionId() {
                return DivisionId;
            }

            public void setDivisionId(int DivisionId) {
                this.DivisionId = DivisionId;
            }

            public int getClassId() {
                return ClassId;
            }

            public void setClassId(int ClassId) {
                this.ClassId = ClassId;
            }

            public String getDivisionName() {
                return DivisionName;
            }

            public void setDivisionName(String DivisionName) {
                this.DivisionName = DivisionName;
            }

            public String getTimeStamp() {
                return TimeStamp;
            }

            public void setTimeStamp(String TimeStamp) {
                this.TimeStamp = TimeStamp;
            }

            public String getDivisionGuid() {
                return DivisionGuid;
            }

            public void setDivisionGuid(String DivisionGuid) {
                this.DivisionGuid = DivisionGuid;
            }

            public boolean isIsActive() {
                return IsActive;
            }

            public void setIsActive(boolean IsActive) {
                this.IsActive = IsActive;
            }

            public String getClassName() {
                return ClassName;
            }

            public void setClassName(String ClassName) {
                this.ClassName = ClassName;
            }
        }
    }
}
