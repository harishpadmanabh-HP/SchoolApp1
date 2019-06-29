package com.schoolmanapp.ktct_school.school.driver.Model;

/**
 * Created by srishtiinnovative on 21/06/17.
 */

public class Mod_login {

    /**
     * status : true
     * msg : Success
     * UserData : {"DriverId":7,"SchoolId":19,"DriverSpecialId":"DR8U11","DriverName":"ff","LicenseNumber":"3d","ContactNumber":"356","Address":"dd","DriverGuid":"0b3b5a68-9eb5-4df5-bdab-4a7a9ebe5074","FilePath":"/Media/Driver/Profile/acaa8e79-c667-4848-8250-4291b7392646.jpeg","City":"dddx","State":"dd","School":{"SchoolId":19,"SchoolName":"gss","Address":"Aakkulam","City":"tvm","Email":"gss@gmail.com","Website":"gss.tvm.com","Contact":"98765433","FilePath":"/Media/School/Profile/3cd74f70-edc7-4c11-bdcd-1c386f52de85.jpeg","State":"kerala"}}
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
         * DriverId : 7
         * SchoolId : 19
         * DriverSpecialId : DR8U11
         * DriverName : ff
         * LicenseNumber : 3d
         * ContactNumber : 356
         * Address : dd
         * DriverGuid : 0b3b5a68-9eb5-4df5-bdab-4a7a9ebe5074
         * FilePath : /Media/Driver/Profile/acaa8e79-c667-4848-8250-4291b7392646.jpeg
         * City : dddx
         * State : dd
         * School : {"SchoolId":19,"SchoolName":"gss","Address":"Aakkulam","City":"tvm","Email":"gss@gmail.com","Website":"gss.tvm.com","Contact":"98765433","FilePath":"/Media/School/Profile/3cd74f70-edc7-4c11-bdcd-1c386f52de85.jpeg","State":"kerala"}
         */

        private int DriverId;
        private int SchoolId;
        private String DriverSpecialId;
        private String DriverName;
        private String LicenseNumber;
        private String ContactNumber;
        private String Address;
        private String DriverGuid;
        private String FilePath;
        private String City;
        private String State;
        private SchoolBean School;

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

        public String getDriverGuid() {
            return DriverGuid;
        }

        public void setDriverGuid(String DriverGuid) {
            this.DriverGuid = DriverGuid;
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

        public SchoolBean getSchool() {
            return School;
        }

        public void setSchool(SchoolBean School) {
            this.School = School;
        }

        public static class SchoolBean {
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
    }
}
