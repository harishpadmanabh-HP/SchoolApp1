package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 16/06/17.
 */

public class Mod_List_Students {


    /**
     * status : true
     * msg : Success
     * UserData : [{"StudentId":10102,"SchoolId":10039,"StudentSpecialId":"admno102","StundentName":"Anna","ParentName":"Rajeev","Address":"Ragam","City":"Thiruvananthapuram","ContactNumber":"9638527410","ClasssNumber":"2","ClassId":49,"DivisionId":93,"BusId":1,"BusSpecialId":"BS1S99","TripNo":null,"FilePath":"/Media/Student/Profile/93dc7752-522a-4d9c-97ea-3ae4ef37689f.jpeg","StudentGuid":"95ef3f20-ce1c-43fb-a21b-bcb6ee7707a8","State":"Kerala","Division":{"DivisionId":93,"ClassId":49,"DivisionName":"D","TimeStamp":"2017-07-24T06:49:27.7","DivisionGuid":"3fa65afc-a721-4462-b40b-6722ae70411d","IsActive":true,"ClassName":"6"},"Gender":"","BloodGroup":null,"DOB":null,"DOBString":""},{"StudentId":10098,"SchoolId":10039,"StudentSpecialId":"admno101","StundentName":"Anu","ParentName":"Rajeev","Address":"Ragam","City":"Thiruvananthapuram","ContactNumber":"9865327417","ClasssNumber":"2","ClassId":49,"DivisionId":93,"BusId":1,"BusSpecialId":"BS1S99","TripNo":null,"FilePath":"/Media/Student/Profile/a51c2313-4286-42e7-966e-37b20efaa178.jpeg","StudentGuid":"d04af7d4-bdac-4162-b923-71c434dc2086","State":"Kerala","Division":{"DivisionId":93,"ClassId":49,"DivisionName":"D","TimeStamp":"2017-07-24T06:49:27.7","DivisionGuid":"3fa65afc-a721-4462-b40b-6722ae70411d","IsActive":true,"ClassName":"6"},"Gender":"","BloodGroup":null,"DOB":null,"DOBString":""},{"StudentId":10158,"SchoolId":10039,"StudentSpecialId":"ff","StundentName":"dhjkk","ParentName":"rguuooo","Address":"dddfhj","City":"dd","ContactNumber":"1234567890","ClasssNumber":"2","ClassId":49,"DivisionId":93,"BusId":82,"BusSpecialId":"BSEG97","TripNo":null,"FilePath":"/Media/Student/Profile/77637954-46aa-4423-9ab6-52716fa9330d.jpeg","StudentGuid":"f5adeca9-659f-4e08-8149-01c68d233316","State":"vv","Division":{"DivisionId":93,"ClassId":49,"DivisionName":"D","TimeStamp":"2017-07-24T06:49:27.7","DivisionGuid":"3fa65afc-a721-4462-b40b-6722ae70411d","IsActive":true,"ClassName":"6"},"Gender":"","BloodGroup":null,"DOB":null,"DOBString":""},{"StudentId":10161,"SchoolId":10039,"StudentSpecialId":"kkk","StundentName":"y","ParentName":"yy","Address":"hhh","City":"ghjjkk","ContactNumber":"9638527410","ClasssNumber":"2","ClassId":49,"DivisionId":93,"BusId":81,"BusSpecialId":"BSZZ18","TripNo":"2","FilePath":"/Media/Student/Profile/332017ca-dc72-4dbb-bae7-27b14754492a.jpeg","StudentGuid":"62e11aa0-3350-429a-8902-4b216b31dd6b","State":"yhji","Division":{"DivisionId":93,"ClassId":49,"DivisionName":"D","TimeStamp":"2017-07-24T06:49:27.7","DivisionGuid":"3fa65afc-a721-4462-b40b-6722ae70411d","IsActive":true,"ClassName":"6"},"Gender":"","BloodGroup":null,"DOB":null,"DOBString":""}]
     */

    private boolean status;
    private String msg;
    /**
     * StudentId : 10102
     * SchoolId : 10039
     * StudentSpecialId : admno102
     * StundentName : Anna
     * ParentName : Rajeev
     * Address : Ragam
     * City : Thiruvananthapuram
     * ContactNumber : 9638527410
     * ClasssNumber : 2
     * ClassId : 49
     * DivisionId : 93
     * BusId : 1
     * BusSpecialId : BS1S99
     * TripNo : null
     * FilePath : /Media/Student/Profile/93dc7752-522a-4d9c-97ea-3ae4ef37689f.jpeg
     * StudentGuid : 95ef3f20-ce1c-43fb-a21b-bcb6ee7707a8
     * State : Kerala
     * Division : {"DivisionId":93,"ClassId":49,"DivisionName":"D","TimeStamp":"2017-07-24T06:49:27.7","DivisionGuid":"3fa65afc-a721-4462-b40b-6722ae70411d","IsActive":true,"ClassName":"6"}
     * Gender :
     * BloodGroup : null
     * DOB : null
     * DOBString :
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
        private String BusSpecialId;
        private Object TripNo;
        private String FilePath;
        private String StudentGuid;
        private String State;
        /**
         * DivisionId : 93
         * ClassId : 49
         * DivisionName : D
         * TimeStamp : 2017-07-24T06:49:27.7
         * DivisionGuid : 3fa65afc-a721-4462-b40b-6722ae70411d
         * IsActive : true
         * ClassName : 6
         */

        private DivisionBean Division;
        private String Gender;
        private String BloodGroup;
        private Object DOB;
        private String DOBString;

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

        public String getBusSpecialId() {
            return BusSpecialId;
        }

        public void setBusSpecialId(String BusSpecialId) {
            this.BusSpecialId = BusSpecialId;
        }

        public Object getTripNo() {
            return TripNo;
        }

        public void setTripNo(Object TripNo) {
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

        public String getGender() {
            return Gender;
        }

        public void setGender(String Gender) {
            this.Gender = Gender;
        }

        public String getBloodGroup() {
            return BloodGroup;
        }

        public void setBloodGroup(String BloodGroup) {
            this.BloodGroup = BloodGroup;
        }

        public Object getDOB() {
            return DOB;
        }

        public void setDOB(Object DOB) {
            this.DOB = DOB;
        }

        public String getDOBString() {
            return DOBString;
        }

        public void setDOBString(String DOBString) {
            this.DOBString = DOBString;
        }

        public static class DivisionBean {
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
