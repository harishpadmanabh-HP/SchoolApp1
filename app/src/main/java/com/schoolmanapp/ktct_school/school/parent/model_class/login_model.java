package com.schoolmanapp.ktct_school.school.parent.model_class;

/**
 * Created by srishtiinnovative on 08/06/17.
 */

public class login_model {

    /**
     * status : true
     * msg : Success
     * UserData : {"ParentId":2,"ParentName":"Test  ParentName","Address":"Test Address","City":"Test City","Email":"Test Email@gmail.com","ContactNumber":"Test ContactNumber","ParentGuid":"741a7cb3-d295-4c64-b91c-c04f7dad97db","IsActive":true,"State":"Test State","FilePath":"/Media/Parent/Profile/f8bd05aa-b241-4614-92cd-58fee4684b0c.jpeg"}
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
         * ParentId : 2
         * ParentName : Test  ParentName
         * Address : Test Address
         * City : Test City
         * Email : Test Email@gmail.com
         * ContactNumber : Test ContactNumber
         * ParentGuid : 741a7cb3-d295-4c64-b91c-c04f7dad97db
         * IsActive : true
         * State : Test State
         * FilePath : /Media/Parent/Profile/f8bd05aa-b241-4614-92cd-58fee4684b0c.jpeg
         */

        private int ParentId;
        private String ParentName;
        private String Address;
        private String City;
        private String Email;
        private String ContactNumber;
        private String ParentGuid;
        private boolean IsActive;
        private String State;
        private String FilePath;

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
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

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getContactNumber() {
            return ContactNumber;
        }

        public void setContactNumber(String ContactNumber) {
            this.ContactNumber = ContactNumber;
        }

        public String getParentGuid() {
            return ParentGuid;
        }

        public void setParentGuid(String ParentGuid) {
            this.ParentGuid = ParentGuid;
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
    }
}
