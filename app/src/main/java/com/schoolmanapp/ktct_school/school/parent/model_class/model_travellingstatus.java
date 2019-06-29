package com.schoolmanapp.ktct_school.school.parent.model_class;

/**
 * Created by srishtiinnovative on 04/08/17.
 */

public class model_travellingstatus {


    /**
     * status : true
     * msg : Trip Start
     * UserData : {"DriverName":"Vijay","LicenseNumber":"1","ContactNumber":"9638521476","Address":"test address","FilePath":"/Media/Driver/Profile/8caa12ce-266b-4dde-b501-2c43e3c2e2c3.jpeg","City":"Kazhakkoottam","State":"Kerala"}
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
         * DriverName : Vijay
         * LicenseNumber : 1
         * ContactNumber : 9638521476
         * Address : test address
         * FilePath : /Media/Driver/Profile/8caa12ce-266b-4dde-b501-2c43e3c2e2c3.jpeg
         * City : Kazhakkoottam
         * State : Kerala
         */

        private String DriverName;
        private String LicenseNumber;
        private String ContactNumber;
        private String Address;
        private String FilePath;
        private String City;
        private String State;

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
