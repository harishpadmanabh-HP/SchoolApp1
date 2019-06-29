package com.schoolmanapp.ktct_school.school.school.Model;

/**
 * Created by srishtiinnovative on 09/06/17.
 */

public class Mod_Login {


    /**
     * status : true
     * msg : Success
     * UserData : {"SchoolId":10039,"SchoolName":"MGM","Address":" Near SFS apartment,Karimanal","City":"Thiruvananthapuram","Website":"www.mgmtvm.com","Contact":"9446172550","IsActive":true,"State":"Kerala","FilePath":"/Media/School/Profile/b4ca1bd9-131d-40c7-acc7-ab2a31decffd.jpeg","Latitude":"8.571032","Longitude":"76.866331","Login":{"UserId":18,"SchoolId":10039,"RoleId":1,"Name":"MGM","Username":"mgm@gmail.com","Password":"123456"}}
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
         * SchoolId : 10039
         * SchoolName : MGM
         * Address :  Near SFS apartment,Karimanal
         * City : Thiruvananthapuram
         * Website : www.mgmtvm.com
         * Contact : 9446172550
         * IsActive : true
         * State : Kerala
         * FilePath : /Media/School/Profile/b4ca1bd9-131d-40c7-acc7-ab2a31decffd.jpeg
         * Latitude : 8.571032
         * Longitude : 76.866331
         * Login : {"UserId":18,"SchoolId":10039,"RoleId":1,"Name":"MGM","Username":"mgm@gmail.com","Password":"123456"}
         */

        private int SchoolId;
        private String SchoolName;
        private String Address;
        private String City;
        private String Website;
        private String Contact;
        private boolean IsActive;
        private String State;
        private String FilePath;
        private String Latitude;
        private String Longitude;
        private LoginBean Login;

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

        public boolean isIsActive() {
            return IsActive;
        }

        public void setIsActive(boolean IsActive) {
            this.IsActive = IsActive;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
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

        public LoginBean getLogin() {
            return Login;
        }

        public void setLogin(LoginBean Login) {
            this.Login = Login;
        }

        public static class LoginBean {
            /**
             * UserId : 18
             * SchoolId : 10039
             * RoleId : 1
             * Name : MGM
             * Username : mgm@gmail.com
             * Password : 123456
             */

            private int UserId;
            private int SchoolId;
            private int RoleId;
            private String Name;
            private String Username;
            private String Password;

            public int getUserId() {
                return UserId;
            }

            public void setUserId(int UserId) {
                this.UserId = UserId;
            }

            public int getSchoolId() {
                return SchoolId;
            }

            public void setSchoolId(int SchoolId) {
                this.SchoolId = SchoolId;
            }

            public int getRoleId() {
                return RoleId;
            }

            public void setRoleId(int RoleId) {
                this.RoleId = RoleId;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getUsername() {
                return Username;
            }

            public void setUsername(String Username) {
                this.Username = Username;
            }

            public String getPassword() {
                return Password;
            }

            public void setPassword(String Password) {
                this.Password = Password;
            }
        }
    }
}
