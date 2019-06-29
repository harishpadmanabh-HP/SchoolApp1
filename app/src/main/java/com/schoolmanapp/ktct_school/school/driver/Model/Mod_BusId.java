package com.schoolmanapp.ktct_school.school.driver.Model;

/**
 * Created by srishtiinnovative on 22/06/17.
 */

public class Mod_BusId {


    /**
     * status : true
     * msg : Success
     * UserData : {"BusId":49,"BusSpecialId":"BSTD82","TripNumber":"2","LocationStart":"sreekaryam","LocationEnd":"Thiruvananthapuram","BusGuid":"f3ebedd6-ab0c-43e8-9a0b-9e828875b4a0","IsActive":true,"BusType":"Van","BusName":"Bus16","SchoolId":10039}
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
         * BusId : 49
         * BusSpecialId : BSTD82
         * TripNumber : 2
         * LocationStart : sreekaryam
         * LocationEnd : Thiruvananthapuram
         * BusGuid : f3ebedd6-ab0c-43e8-9a0b-9e828875b4a0
         * IsActive : true
         * BusType : Van
         * BusName : Bus16
         * SchoolId : 10039
         */

        private int BusId;
        private String BusSpecialId;
        private String TripNumber;
        private String LocationStart;
        private String LocationEnd;
        private String BusGuid;
        private boolean IsActive;
        private String BusType;
        private String BusName;
        private int SchoolId;

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

        public String getTripNumber() {
            return TripNumber;
        }

        public void setTripNumber(String TripNumber) {
            this.TripNumber = TripNumber;
        }

        public String getLocationStart() {
            return LocationStart;
        }

        public void setLocationStart(String LocationStart) {
            this.LocationStart = LocationStart;
        }

        public String getLocationEnd() {
            return LocationEnd;
        }

        public void setLocationEnd(String LocationEnd) {
            this.LocationEnd = LocationEnd;
        }

        public String getBusGuid() {
            return BusGuid;
        }

        public void setBusGuid(String BusGuid) {
            this.BusGuid = BusGuid;
        }

        public boolean isIsActive() {
            return IsActive;
        }

        public void setIsActive(boolean IsActive) {
            this.IsActive = IsActive;
        }

        public String getBusType() {
            return BusType;
        }

        public void setBusType(String BusType) {
            this.BusType = BusType;
        }

        public String getBusName() {
            return BusName;
        }

        public void setBusName(String BusName) {
            this.BusName = BusName;
        }

        public int getSchoolId() {
            return SchoolId;
        }

        public void setSchoolId(int SchoolId) {
            this.SchoolId = SchoolId;
        }
    }
}
