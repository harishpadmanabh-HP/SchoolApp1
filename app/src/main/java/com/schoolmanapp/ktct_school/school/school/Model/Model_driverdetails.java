package com.schoolmanapp.ktct_school.school.school.Model;

/**
 * Created by srishtiinnovative on 22/08/17.
 */

public class Model_driverdetails {

    /**
     * status : true
     * msg : Success
     * UserData : {"DriverId":10088,"SchoolId":10062,"DriverSpecialId":"DR5Y47","DriverName":"akshay1","LicenseNumber":"she/😊772*","ContactNumber":"9899595959","Address":"shnsnns","FilePath":"/Media/Driver/Profile/557ef2d7-6c8f-45dc-8f81-d4be1331a032.jpeg","City":"sbbs","State":"Kerala"}
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
         * DriverId : 10088
         * SchoolId : 10062
         * DriverSpecialId : DR5Y47
         * DriverName : akshay1
         * LicenseNumber : she/😊772*
         * ContactNumber : 9899595959
         * Address : shnsnns
         * FilePath : /Media/Driver/Profile/557ef2d7-6c8f-45dc-8f81-d4be1331a032.jpeg
         * City : sbbs
         * State : Kerala
         */

        private int DriverId;
        private int SchoolId;
        private String DriverSpecialId;
        private String DriverName;
        private String LicenseNumber;
        private String ContactNumber;
        private String Address;
        private String FilePath;
        private String City;
        private String State;

        public int getDriverId() {
            return DriverId;
        }

        public void setDriverId(int DriverId) {
            this.DriverId = DriverId;
        }

        public int getSchoolId() {
            return SchoolId;
        }

        public void setSchoolId(int SchoolId) {
            this.SchoolId = SchoolId;
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

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }
    }
}
