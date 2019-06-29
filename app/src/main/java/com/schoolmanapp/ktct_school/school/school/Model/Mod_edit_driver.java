package com.schoolmanapp.ktct_school.school.school.Model;

public class Mod_edit_driver {
    /**
     * status : true
     * msg : Success
     * UserData : {"DriverId":"10116","DriverName":"Vinu Moh ","LicenseNumber":"1111111101111","ContactNumber":"1234567890","Address":"Kaniyapuram1","FilePath":"/Media/Driver/Profile/dc7bdbdf-e844-4c35-baa7-3e766c5c3517.jpeg","image":null,"City":"ekm","State":"Kerala","DriverSpecialId":"DR9S67"}
     */

    private boolean status;
    private String msg;
    /**
     * DriverId : 10116
     * DriverName : Vinu Moh
     * LicenseNumber : 1111111101111
     * ContactNumber : 1234567890
     * Address : Kaniyapuram1
     * FilePath : /Media/Driver/Profile/dc7bdbdf-e844-4c35-baa7-3e766c5c3517.jpeg
     * image : null
     * City : ekm
     * State : Kerala
     * DriverSpecialId : DR9S67
     */

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
        private String DriverId;
        private String DriverName;
        private String LicenseNumber;
        private String ContactNumber;
        private String Address;
        private String FilePath;
        private Object image;
        private String City;
        private String State;
        private String DriverSpecialId;

        public String getDriverId() {
            return DriverId;
        }

        public void setDriverId(String DriverId) {
            this.DriverId = DriverId;
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

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
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

        public String getDriverSpecialId() {
            return DriverSpecialId;
        }

        public void setDriverSpecialId(String DriverSpecialId) {
            this.DriverSpecialId = DriverSpecialId;
        }
    }
}
