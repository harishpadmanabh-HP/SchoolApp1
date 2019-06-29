package com.schoolmanapp.ktct_school.school.parent.model_class;

/**
 * Created by srishtiinnovative on 08/06/17.
 */

public class register_model {


    /**
     * status : true
     * msg : Success
     * UserData : {"ParentId":4,"ParentName":"Test  ParentName","Address":"Test Address","City":"Test City","Email":"Test Email1@gmail.com","ContactNumber":"Test ContactNumber","ParentGuid":"96381d2b-1838-40f6-a02c-44ac2d69d9e9","IsActive":true,"State":"Test State"}
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
         * ParentId : 4
         * ParentName : Test  ParentName
         * Address : Test Address
         * City : Test City
         * Email : Test Email1@gmail.com
         * ContactNumber : Test ContactNumber
         * ParentGuid : 96381d2b-1838-40f6-a02c-44ac2d69d9e9
         * IsActive : true
         * State : Test State
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
    }
}
