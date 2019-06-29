package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.util.List;

/**
 * Created by srishtiinnovative on 08/06/17.
 */

public class login_model {


    /**
     * status : true
     * msg : Success
     * UserData : {"TeacherId":21,"TeacherSpecialId":"TR1C16","TeacherName":"Test Teacher","SchoolId":19,"ContactNumber":"2545652365","Email":"rp@gmail.com","TimeStamp":"2017-06-14T06:17:04.027","TeacherGuid":"e305a118-7f2f-4d5f-8ecf-8f0f1bf5f557","IsActive":true,"FilePath":"/Media/Teacher/Profile/6cacc6bf-8312-4926-8d88-a5af65674baa.jpeg","SchoolData":{"SchoolId":19,"SchoolName":"gss","Address":"Aakkulam","City":"tvm","Email":"gss@gmail.com","Website":"gss.tvm.com","Contact":"98765433","FilePath":"/Media/School/Profile/3cd74f70-edc7-4c11-bdcd-1c386f52de85.jpeg","State":"kerala"},"TeacherClass":[{"TeacherClassId":8,"TeacherId":21,"ClassId":13,"DivisionId":23,"ClassName":"2 ","DivisionName":"A"}]}
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
         * TeacherId : 21
         * TeacherSpecialId : TR1C16
         * TeacherName : Test Teacher
         * SchoolId : 19
         * ContactNumber : 2545652365
         * Email : rp@gmail.com
         * TimeStamp : 2017-06-14T06:17:04.027
         * TeacherGuid : e305a118-7f2f-4d5f-8ecf-8f0f1bf5f557
         * IsActive : true
         * FilePath : /Media/Teacher/Profile/6cacc6bf-8312-4926-8d88-a5af65674baa.jpeg
         * SchoolData : {"SchoolId":19,"SchoolName":"gss","Address":"Aakkulam","City":"tvm","Email":"gss@gmail.com","Website":"gss.tvm.com","Contact":"98765433","FilePath":"/Media/School/Profile/3cd74f70-edc7-4c11-bdcd-1c386f52de85.jpeg","State":"kerala"}
         * TeacherClass : [{"TeacherClassId":8,"TeacherId":21,"ClassId":13,"DivisionId":23,"ClassName":"2 ","DivisionName":"A"}]
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
        private SchoolDataBean SchoolData;
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

        public SchoolDataBean getSchoolData() {
            return SchoolData;
        }

        public void setSchoolData(SchoolDataBean SchoolData) {
            this.SchoolData = SchoolData;
        }

        public List<TeacherClassBean> getTeacherClass() {
            return TeacherClass;
        }

        public void setTeacherClass(List<TeacherClassBean> TeacherClass) {
            this.TeacherClass = TeacherClass;
        }

        public static class SchoolDataBean {
            /**
             * SchoolId : 19
             * SchoolName : gss
             * Address : Aakkulam
             * City : tvm
             * Email : gss@gmail.com
             * Website : gss.tvm.com
             * Contact : 98765433
             * FilePath : /Media/School/Profile/3cd74f70-edc7-4c11-bdcd-1c386f52de85.jpeg
             * State : kerala
             */

            private int SchoolId;
            private String SchoolName;
            private String Address;
            private String City;
            private String Email;
            private String Website;
            private String Contact;
            private String FilePath;
            private String State;

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

            public String getContact() {
                return Contact;
            }

            public void setContact(String Contact) {
                this.Contact = Contact;
            }

            public String getFilePath() {
                return FilePath;
            }

            public void setFilePath(String FilePath) {
                this.FilePath = FilePath;
            }

            public String getState() {
                return State;
            }

            public void setState(String State) {
                this.State = State;
            }
        }

        public static class TeacherClassBean {
            /**
             * TeacherClassId : 8
             * TeacherId : 21
             * ClassId : 13
             * DivisionId : 23
             * ClassName : 2
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
