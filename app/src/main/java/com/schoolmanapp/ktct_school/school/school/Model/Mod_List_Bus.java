package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 15/06/17.
 */

public class Mod_List_Bus {

    /**
     * status : true
     * msg : Success
     * UserData : [{"BusId":73,"BusSpecialId":"BSF760","TripNumber":"2","LocationStart":"Sreekaryam","LocationEnd":"Trivandrum","BusGuid":"4c310a75-fc1c-433e-9a8e-12f73bf4fc1e","BusType":"Minibus","BusName":"Bus01","SchoolId":10058,"TravellingStatus":0},{"BusId":75,"BusSpecialId":"BS1Q97","TripNumber":"2","LocationStart":"Kesavadasapuram","LocationEnd":"Trivandrum","BusGuid":"c350fd53-fec3-4d49-882f-9132ad81250e","BusType":"Van","BusName":"Bus02","SchoolId":10058,"TravellingStatus":0},{"BusId":76,"BusSpecialId":"BS9Z23","TripNumber":"2","LocationStart":"Ulloor","LocationEnd":"Trivandrum","BusGuid":"b878bc8b-65e9-4452-9418-7d49b3bf47ff","BusType":"Van","BusName":"Bus03","SchoolId":10058,"TravellingStatus":0},{"BusId":77,"BusSpecialId":"BS1K37","TripNumber":"2","LocationStart":"Peroorkkada","LocationEnd":"Trivandrum","BusGuid":"a3172c25-75f6-4e0a-861b-429ff01e2da0","BusType":"Minibus","BusName":"Buso4","SchoolId":10058,"TravellingStatus":0},{"BusId":78,"BusSpecialId":"BS9684","TripNumber":"1","LocationStart":"Palayam","LocationEnd":"Trivandrum","BusGuid":"3dd42e80-614c-43a2-8e48-41b8987a0fac","BusType":"Van","BusName":"Bus05","SchoolId":10058,"TravellingStatus":0},{"BusId":79,"BusSpecialId":"BSXH41","TripNumber":"2","LocationStart":"Attingal","LocationEnd":"Trivandrum","BusGuid":"d8b52113-2d26-4bfa-97f4-806ec4ce3052","BusType":"Van","BusName":"Bus05","SchoolId":10058,"TravellingStatus":0},{"BusId":83,"BusSpecialId":"BSAN55","TripNumber":"1","LocationStart":"Karamana","LocationEnd":"Trivandrum","BusGuid":"2d26fa9e-5333-4df6-8687-e513d915a360","BusType":"van","BusName":"Bus07","SchoolId":10058,"TravellingStatus":0}]
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
         * BusId : 73
         * BusSpecialId : BSF760
         * TripNumber : 2
         * LocationStart : Sreekaryam
         * LocationEnd : Trivandrum
         * BusGuid : 4c310a75-fc1c-433e-9a8e-12f73bf4fc1e
         * BusType : Minibus
         * BusName : Bus01
         * SchoolId : 10058
         * TravellingStatus : 0
         */

        private int BusId;
        private String BusSpecialId;
        private String TripNumber;
        private String LocationStart;
        private String LocationEnd;
        private String BusGuid;
        private String BusType;
        private String BusName;
        private int SchoolId;
        private int TravellingStatus;

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

        public int getTravellingStatus() {
            return TravellingStatus;
        }

        public void setTravellingStatus(int TravellingStatus) {
            this.TravellingStatus = TravellingStatus;
        }
    }
}
