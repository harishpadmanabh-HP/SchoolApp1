package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 16/06/17.
 */

public class Mod_Del_driver {

    /**
     * status : true
     * msg : Success
     * UserData : [{"DriverId":4,"SchoolId":19,"DriverSpecialId":"DR3345","DriverName":"Test Driver","LicenseNumber":"012LKLI45","ContactNumber":"2356566525","Address":"Test address","FilePath":"/Media/Driver/Profile/300c76ca-677f-4cd6-9fd5-8fbdf581b921.jpeg","City":"TVM","State":"Kerala"},{"DriverId":5,"SchoolId":19,"DriverSpecialId":"DRUK62","DriverName":"Murukan","LicenseNumber":"012LKLI45","ContactNumber":"2356566525","Address":"Test address","FilePath":"/Media/Driver/Profile/455824c8-653d-4444-8b25-3f165b3ae38b.jpeg","City":"TVM","State":"Kerala"},{"DriverId":7,"SchoolId":19,"DriverSpecialId":"DR8U11","DriverName":"ff","LicenseNumber":"3d","ContactNumber":"356","Address":"dd","FilePath":"/Media/Driver/Profile/acaa8e79-c667-4848-8250-4291b7392646.jpeg","City":"dddx","State":"dd"},{"DriverId":8,"SchoolId":19,"DriverSpecialId":"DRMW44","DriverName":"v ","LicenseNumber":"c","ContactNumber":"456","Address":"f","FilePath":"/Media/Driver/Profile/450e50b7-14cf-49d2-a7bc-7604a5cfd83e.jpeg","City":"c","State":"c"},{"DriverId":9,"SchoolId":19,"DriverSpecialId":"DR2324","DriverName":"y","LicenseNumber":"f","ContactNumber":"677","Address":"g","FilePath":"/Media/Driver/Profile/f5349926-b9e8-401d-bc15-3af2caf8eb88.jpeg","City":"g","State":"g"}]
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
         * DriverId : 4
         * SchoolId : 19
         * DriverSpecialId : DR3345
         * DriverName : Test Driver
         * LicenseNumber : 012LKLI45
         * ContactNumber : 2356566525
         * Address : Test address
         * FilePath : /Media/Driver/Profile/300c76ca-677f-4cd6-9fd5-8fbdf581b921.jpeg
         * City : TVM
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
