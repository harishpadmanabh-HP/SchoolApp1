package com.schoolmanapp.ktct_school.school.parent.model_class;

import java.util.List;

/**
 * Created by srishtiinnovative on 27/06/17.
 */

public class kid_list_model {


    /**
     * status : true
     * msg : success
     * UserData : [{"StudentId":10032,"SchoolId":19,"StudentSpecialId":"STNC3","StundentName":"t","ParentName":"f","Address":"t","City":"f","ContactNumber":"58","ClasssNumber":"2","ClassId":12,"DivisionId":32,"BusId":26,"TripNo":"2","FilePath":"/Media/Student/Profile/1d934d53-f6b3-4f79-b78a-be09b29d5838.jpeg","TimeStamp":"2017-06-26T12:45:06.477","StudentGuid":"52c03b50-71f1-4d7b-b555-681bb612a37e","IsActive":true,"ParentId":10,"State":"f","ClassName":"1","SchoolName":"gss","Latitude":"8.571032","Longitude":"76.866331","DivisionName":"A","Teacher":{"TeacherId":55,"TeacherSpecialId":"TRH840","TeacherName":"devi","ContactNumber":"67743","Email":"devi@gmail.com","FilePath":"/Media/Teacher/Profile/254cd421-5d6b-4864-9278-78fe7a156a4f.jpeg"},"Driver":{"DriverId":13,"DriverSpecialId":"DRPM24","DriverName":"tt","LicenseNumber":"f","ContactNumber":"67","Address":"r"}}]
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
         * StudentId : 10032
         * SchoolId : 19
         * StudentSpecialId : STNC3
         * StundentName : t
         * ParentName : f
         * Address : t
         * City : f
         * ContactNumber : 58
         * ClasssNumber : 2
         * ClassId : 12
         * DivisionId : 32
         * BusId : 26
         * TripNo : 2
         * FilePath : /Media/Student/Profile/1d934d53-f6b3-4f79-b78a-be09b29d5838.jpeg
         * TimeStamp : 2017-06-26T12:45:06.477
         * StudentGuid : 52c03b50-71f1-4d7b-b555-681bb612a37e
         * IsActive : true
         * ParentId : 10
         * State : f
         * ClassName : 1
         * SchoolName : gss
         * Latitude : 8.571032
         * Longitude : 76.866331
         * DivisionName : A
         * Teacher : {"TeacherId":55,"TeacherSpecialId":"TRH840","TeacherName":"devi","ContactNumber":"67743","Email":"devi@gmail.com","FilePath":"/Media/Teacher/Profile/254cd421-5d6b-4864-9278-78fe7a156a4f.jpeg"}
         * Driver : {"DriverId":13,"DriverSpecialId":"DRPM24","DriverName":"tt","LicenseNumber":"f","ContactNumber":"67","Address":"r"}
         */

        private int StudentId;
        private int SchoolId;
        private String StudentSpecialId;
        private String StundentName;
        private String ParentName;
        private String Address;
        private String City;
        private String ContactNumber;
        private String ClasssNumber;
        private int ClassId;
        private int DivisionId;
        private int BusId;
        private String TripNo;
        private String FilePath;
        private String TimeStamp;
        private String StudentGuid;
        private boolean IsActive;
        private int ParentId;
        private String State;
        private String ClassName;
        private String SchoolName;
        private String Latitude;
        private String Longitude;
        private String DivisionName;
        private TeacherBean Teacher;
        private DriverBean Driver;

        public int getStudentId() {
            return StudentId;
        }

        public void setStudentId(int StudentId) {
            this.StudentId = StudentId;
        }

        public int getSchoolId() {
            return SchoolId;
        }

        public void setSchoolId(int SchoolId) {
            this.SchoolId = SchoolId;
        }

        public String getStudentSpecialId() {
            return StudentSpecialId;
        }

        public void setStudentSpecialId(String StudentSpecialId) {
            this.StudentSpecialId = StudentSpecialId;
        }

        public String getStundentName() {
            return StundentName;
        }

        public void setStundentName(String StundentName) {
            this.StundentName = StundentName;
        }

        public String getParentName() {
            return ParentName;
        }

        public void setParentName(String ParentName) {
            this.ParentName = ParentName;
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

        public String getContactNumber() {
            return ContactNumber;
        }

        public void setContactNumber(String ContactNumber) {
            this.ContactNumber = ContactNumber;
        }

        public String getClasssNumber() {
            return ClasssNumber;
        }

        public void setClasssNumber(String ClasssNumber) {
            this.ClasssNumber = ClasssNumber;
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

        public int getBusId() {
            return BusId;
        }

        public void setBusId(int BusId) {
            this.BusId = BusId;
        }

        public String getTripNo() {
            return TripNo;
        }

        public void setTripNo(String TripNo) {
            this.TripNo = TripNo;
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

        public String getStudentGuid() {
            return StudentGuid;
        }

        public void setStudentGuid(String StudentGuid) {
            this.StudentGuid = StudentGuid;
        }

        public boolean isIsActive() {
            return IsActive;
        }

        public void setIsActive(boolean IsActive) {
            this.IsActive = IsActive;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getClassName() {
            return ClassName;
        }

        public void setClassName(String ClassName) {
            this.ClassName = ClassName;
        }

        public String getSchoolName() {
            return SchoolName;
        }

        public void setSchoolName(String SchoolName) {
            this.SchoolName = SchoolName;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String Latitude) {
            this.Latitude = Latitude;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }

        public String getDivisionName() {
            return DivisionName;
        }

        public void setDivisionName(String DivisionName) {
            this.DivisionName = DivisionName;
        }

        public TeacherBean getTeacher() {
            return Teacher;
        }

        public void setTeacher(TeacherBean Teacher) {
            this.Teacher = Teacher;
        }

        public DriverBean getDriver() {
            return Driver;
        }

        public void setDriver(DriverBean Driver) {
            this.Driver = Driver;
        }

        public static class TeacherBean {
            /**
             * TeacherId : 55
             * TeacherSpecialId : TRH840
             * TeacherName : devi
             * ContactNumber : 67743
             * Email : devi@gmail.com
             * FilePath : /Media/Teacher/Profile/254cd421-5d6b-4864-9278-78fe7a156a4f.jpeg
             */

            private int TeacherId;
            private String TeacherSpecialId;
            private String TeacherName;
            private String ContactNumber;
            private String Email;
            private String FilePath;

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

            public String getFilePath() {
                return FilePath;
            }

            public void setFilePath(String FilePath) {
                this.FilePath = FilePath;
            }
        }

        public static class DriverBean {
            /**
             * DriverId : 13
             * DriverSpecialId : DRPM24
             * DriverName : tt
             * LicenseNumber : f
             * ContactNumber : 67
             * Address : r
             */

            private int DriverId;
            private String DriverSpecialId;
            private String DriverName;
            private String LicenseNumber;
            private String ContactNumber;
            private String Address;

            public int getDriverId() {
                return DriverId;
            }

            public void setDriverId(int DriverId) {
                this.DriverId = DriverId;
            }

            public String getDriverSpecialId() {
                return DriverSpecialId;
            }

            public void setDriverSpecialId(String DriverSpecialId) {
                this.DriverSpecialId = DriverSpecialId;
            }

            public String getDriverName() {
                return DriverName;
            }

            public void setDriverName(String DriverName) {
                this.DriverName = DriverName;
            }

            public String getLicenseNumber() {
                return LicenseNumber;
            }

            public void setLicenseNumber(String LicenseNumber) {
                this.LicenseNumber = LicenseNumber;
            }

            public String getContactNumber() {
                return ContactNumber;
            }

            public void setContactNumber(String ContactNumber) {
                this.ContactNumber = ContactNumber;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }
        }
    }
}