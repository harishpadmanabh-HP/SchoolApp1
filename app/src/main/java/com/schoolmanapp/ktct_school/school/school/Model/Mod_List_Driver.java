package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 16/06/17.
 */

public class Mod_List_Driver {

    /**
     * status : true
     * msg : Success
     * UserData : [{"DriverId":2,"SchoolId":4,"DriverSpecialId":"DR4T60","DriverName":"Test Driver","LicenseNumber":"012LKLI45","ContactNumber":"2356566525","Address":"Test address","FilePath":"/Media/Driver/Profile/05d0ea50-ce5d-46b9-a0df-76a34808281d.jpeg","City":"Kazhakoottam","State":"Kerala"},{"DriverId":3,"SchoolId":4,"DriverSpecialId":"DRKB47","DriverName":"Test Driver","LicenseNumber":"012LKLI45","ContactNumber":"2356566525","Address":"Test address","FilePath":"/Media/Driver/Profile/6c11e58f-05f3-447d-a80b-99d119ff9466.jpeg","City":"TVM","State":"Kerala"}]
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
         * DriverId : 2
         * SchoolId : 4
         * DriverSpecialId : DR4T60
         * DriverName : Test Driver
         * LicenseNumber : 012LKLI45
         * ContactNumber : 2356566525
         * Address : Test address
         * FilePath : /Media/Driver/Profile/05d0ea50-ce5d-46b9-a0df-76a34808281d.jpeg
         * City : Kazhakoottam
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
