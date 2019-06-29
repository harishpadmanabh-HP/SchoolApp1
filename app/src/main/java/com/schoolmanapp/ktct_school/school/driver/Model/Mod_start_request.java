package com.schoolmanapp.ktct_school.school.driver.Model;

/**
 * Created by srishtiinnovative on 23/06/17.
 */

public class Mod_start_request {


    /**
     * driverId : 10021
     * schoolId : 10039
     * tripDate : 2017-08-09 12:14:53.483
     * busId : 73
     * startPlace : xc
     * endPlace : tvm
     * tripNo : 1
     * longitude : 91.933594
     * latitude : 49.317961
     * shiftStatus : 0
     */

    private String driverId;
    private String schoolId;
    private String tripDate;
    private String busId;
    private String startPlace;
    private String endPlace;
    private String tripNo;
    private String longitude;
    private String latitude;
    private String shiftStatus;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public String getTripNo() {
        return tripNo;
    }

    public void setTripNo(String tripNo) {
        this.tripNo = tripNo;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getShiftStatus() {
        return shiftStatus;
    }

    public void setShiftStatus(String shiftStatus) {
        this.shiftStatus = shiftStatus;
    }
}
