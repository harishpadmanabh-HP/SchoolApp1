package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 20/06/17.
 */

public class Mod_Del_stud {

    /**
     * status : true
     * msg : Success
     * UserData : [{"StudentId":5,"SchoolId":5,"StudentSpecialId":"STC636","StundentName":"Test Student 2","ParentName":"Parent Test","Address":"Address Test","City":"City State","ContactNumber":"645654564","ClasssNumber":"21","ClassId":4,"DivisionId":1,"BusId":1,"TripNo":"2","FilePath":"/Media/School/Profile/e5c52866-c2e0-4728-a7c0-433a996d2544.jpeg","StudentGuid":"4a4d11a6-0e95-464e-87b9-694b406728dd","State":"State Test","Division":{"DivisionId":1,"ClassId":1,"DivisionName":"A","TimeStamp":"2017-05-31T06:35:29.743","DivisionGuid":"d9c31e9d-901e-493f-acc5-13948c7db4cb","IsActive":false,"ClassName":"1"}}]
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
         * StudentId : 5
         * SchoolId : 5
         * StudentSpecialId : STC636
         * StundentName : Test Student 2
         * ParentName : Parent Test
         * Address : Address Test
         * City : City State
         * ContactNumber : 645654564
         * ClasssNumber : 21
         * ClassId : 4
         * DivisionId : 1
         * BusId : 1
         * TripNo : 2
         * FilePath : /Media/School/Profile/e5c52866-c2e0-4728-a7c0-433a996d2544.jpeg
         * StudentGuid : 4a4d11a6-0e95-464e-87b9-694b406728dd
         * State : State Test
         * Division : {"DivisionId":1,"ClassId":1,"DivisionName":"A","TimeStamp":"2017-05-31T06:35:29.743","DivisionGuid":"d9c31e9d-901e-493f-acc5-13948c7db4cb","IsActive":false,"ClassName":"1"}
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
        private String StudentGuid;
        private String State;
        private DivisionBean Division;

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

        public String getStudentGuid() {
            return StudentGuid;
        }

        public void setStudentGuid(String StudentGuid) {
            this.StudentGuid = StudentGuid;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public DivisionBean getDivision() {
            return Division;
        }

        public void setDivision(DivisionBean Division) {
            this.Division = Division;
        }

        public static class DivisionBean {
            /**
             * DivisionId : 1
             * ClassId : 1
             * DivisionName : A
             * TimeStamp : 2017-05-31T06:35:29.743
             * DivisionGuid : d9c31e9d-901e-493f-acc5-13948c7db4cb
             * IsActive : false
             * ClassName : 1
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
