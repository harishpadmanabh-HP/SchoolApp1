package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 26/06/17.
 */

public class Mod_travel_history {


    /**
     * status : true
     * msg : Success
     * UserData : {"TripId":10081,"DriverId":10020,"SchoolId":10039,"TripNumber":"1","LocationStart":"Sreekaryam","LocationEnd":"Thiruvananthapuram","StartTime":"2017-07-06T09:52:09.41","ReachTime":"2017-07-06T09:52:09.41","BusId":44,"DriverName":"Mahesh","DriverNumber":"963852741","DriverProfile":"/Media/Driver/Profile/e4788083-99a9-473b-a8a1-a9b1b748f6bb.jpeg","TravellingStatus":1,"Travel":[{"TravelId":361,"TripId":10081,"Longitude":"76.8812279","Latitude":"8.56062","Place":"Leela Infopark, Technopark Rd, Technopark Campus, Thiruvananthapuram, Kerala 695581, India","IsActive":true,"TimeStamp":"2017-07-06T04:22:10.043","TimeStampString":"04:22:10 AM","TravelGuid":"0334dcd7-03cd-4da5-a559-506794b8e455"},{"TravelId":367,"TripId":10081,"Longitude":"76.8810928","Latitude":"8.5605944","Place":"Leela Infopark, Technopark Rd, Technopark Campus, Thiruvananthapuram, Kerala 695581, India","IsActive":true,"TimeStamp":"2017-07-06T04:46:25.913","TimeStampString":"04:46:25 AM","TravelGuid":"3799ab19-4e6c-4c34-859a-73fbde58dcdf"}]}
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
         * TripId : 10081
         * DriverId : 10020
         * SchoolId : 10039
         * TripNumber : 1
         * LocationStart : Sreekaryam
         * LocationEnd : Thiruvananthapuram
         * StartTime : 2017-07-06T09:52:09.41
         * ReachTime : 2017-07-06T09:52:09.41
         * BusId : 44
         * DriverName : Mahesh
         * DriverNumber : 963852741
         * DriverProfile : /Media/Driver/Profile/e4788083-99a9-473b-a8a1-a9b1b748f6bb.jpeg
         * TravellingStatus : 1
         * Travel : [{"TravelId":361,"TripId":10081,"Longitude":"76.8812279","Latitude":"8.56062","Place":"Leela Infopark, Technopark Rd, Technopark Campus, Thiruvananthapuram, Kerala 695581, India","IsActive":true,"TimeStamp":"2017-07-06T04:22:10.043","TimeStampString":"04:22:10 AM","TravelGuid":"0334dcd7-03cd-4da5-a559-506794b8e455"},{"TravelId":367,"TripId":10081,"Longitude":"76.8810928","Latitude":"8.5605944","Place":"Leela Infopark, Technopark Rd, Technopark Campus, Thiruvananthapuram, Kerala 695581, India","IsActive":true,"TimeStamp":"2017-07-06T04:46:25.913","TimeStampString":"04:46:25 AM","TravelGuid":"3799ab19-4e6c-4c34-859a-73fbde58dcdf"}]
         */

        private int TripId;
        private int DriverId;
        private int SchoolId;
        private String TripNumber;
        private String LocationStart;
        private String LocationEnd;
        private String StartTime;
        private String ReachTime;
        private int BusId;
        private String DriverName;
        private String DriverNumber;
        private String DriverProfile;
        private int TravellingStatus;
        private List<TravelBean> Travel;

        public int getTripId() {
            return TripId;
        }

        public void setTripId(int TripId) {
            this.TripId = TripId;
        }

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

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getReachTime() {
            return ReachTime;
        }

        public void setReachTime(String ReachTime) {
            this.ReachTime = ReachTime;
        }

        public int getBusId() {
            return BusId;
        }

        public void setBusId(int BusId) {
            this.BusId = BusId;
        }

        public String getDriverName() {
            return DriverName;
        }

        public void setDriverName(String DriverName) {
            this.DriverName = DriverName;
        }

        public String getDriverNumber() {
            return DriverNumber;
        }

        public void setDriverNumber(String DriverNumber) {
            this.DriverNumber = DriverNumber;
        }

        public String getDriverProfile() {
            return DriverProfile;
        }

        public void setDriverProfile(String DriverProfile) {
            this.DriverProfile = DriverProfile;
        }

        public int getTravellingStatus() {
            return TravellingStatus;
        }

        public void setTravellingStatus(int TravellingStatus) {
            this.TravellingStatus = TravellingStatus;
        }

        public List<TravelBean> getTravel() {
            return Travel;
        }

        public void setTravel(List<TravelBean> Travel) {
            this.Travel = Travel;
        }

        public static class TravelBean {
            /**
             * TravelId : 361
             * TripId : 10081
             * Longitude : 76.8812279
             * Latitude : 8.56062
             * Place : Leela Infopark, Technopark Rd, Technopark Campus, Thiruvananthapuram, Kerala 695581, India
             * IsActive : true
             * TimeStamp : 2017-07-06T04:22:10.043
             * TimeStampString : 04:22:10 AM
             * TravelGuid : 0334dcd7-03cd-4da5-a559-506794b8e455
             */

            private int TravelId;
            private int TripId;
            private String Longitude;
            private String Latitude;
            private String Place;
            private boolean IsActive;
            private String TimeStamp;
            private String TimeStampString;
            private String TravelGuid;

            public int getTravelId() {
                return TravelId;
            }

            public void setTravelId(int TravelId) {
                this.TravelId = TravelId;
            }

            public int getTripId() {
                return TripId;
            }

            public void setTripId(int TripId) {
                this.TripId = TripId;
            }

            public String getLongitude() {
                return Longitude;
            }

            public void setLongitude(String Longitude) {
                this.Longitude = Longitude;
            }

            public String getLatitude() {
                return Latitude;
            }

            public void setLatitude(String Latitude) {
                this.Latitude = Latitude;
            }

            public String getPlace() {
                return Place;
            }

            public void setPlace(String Place) {
                this.Place = Place;
            }

            public boolean isIsActive() {
                return IsActive;
            }

            public void setIsActive(boolean IsActive) {
                this.IsActive = IsActive;
            }

            public String getTimeStamp() {
                return TimeStamp;
            }

            public void setTimeStamp(String TimeStamp) {
                this.TimeStamp = TimeStamp;
            }

            public String getTimeStampString() {
                return TimeStampString;
            }

            public void setTimeStampString(String TimeStampString) {
                this.TimeStampString = TimeStampString;
            }

            public String getTravelGuid() {
                return TravelGuid;
            }

            public void setTravelGuid(String TravelGuid) {
                this.TravelGuid = TravelGuid;
            }
        }
    }
}
