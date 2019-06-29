package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 13/07/17.
 */

public class Mod_cur_running_bus {

    /**
     * status : true
     * msg : Success
     * UserData : [{"BusId":26,"BusSpecialId":"BSZ04","TripNumber":"5","LocationStart":"xc","LocationEnd":"tvm","BusGuid":"5b0764d8-d5e2-4ec0-b6f0-b17f337c0c39","BusType":"dd","BusName":"cc","SchoolId":19}]
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
         * BusId : 26
         * BusSpecialId : BSZ04
         * TripNumber : 5
         * LocationStart : xc
         * LocationEnd : tvm
         * BusGuid : 5b0764d8-d5e2-4ec0-b6f0-b17f337c0c39
         * BusType : dd
         * BusName : cc
         * SchoolId : 19
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
    }
}
