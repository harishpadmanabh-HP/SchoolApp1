package com.schoolmanapp.ktct_school.school.school.Model;

/**
 * Created by srishtiinnovative on 27/06/17.
 */

public class Mod_track {

    /**
     * status : true
     * msg : success
     * UserData : {"TravelId":5,"TripId":10020,"Longitude":"76.8810702","Latitude":"8.5605516","Place":"Leela Infopark, Technopark Rd, Technopark Campus, Thiruvananthapuram, Kerala 695581, India","IsActive":true,"TimeStamp":"2017-06-27T09:16:27.483","TravelGuid":"26b90f98-5ea2-4978-a2ee-2090bb726f5a"}
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
         * TravelId : 5
         * TripId : 10020
         * Longitude : 76.8810702
         * Latitude : 8.5605516
         * Place : Leela Infopark, Technopark Rd, Technopark Campus, Thiruvananthapuram, Kerala 695581, India
         * IsActive : true
         * TimeStamp : 2017-06-27T09:16:27.483
         * TravelGuid : 26b90f98-5ea2-4978-a2ee-2090bb726f5a
         */

        private int TravelId;
        private int TripId;
        private String Longitude;
        private String Latitude;
        private String Place;
        private boolean IsActive;
        private String TimeStamp;
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

        public String getTravelGuid() {
            return TravelGuid;
        }

        public void setTravelGuid(String TravelGuid) {
            this.TravelGuid = TravelGuid;
        }
    }
}
