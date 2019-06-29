package com.schoolmanapp.ktct_school.school.parent.Java_class;

import java.io.Serializable;

/**
 * Created by srishtiinnovative on 31/05/17.
 */

public class model_home implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String id;
    private String file_path;
    private String division_s;
    private String teacher_name;
    private String school_name;

    private String CONTACT_TEACHER;
    private int kid_id, bus_id, schoolid, class_id;

    private String driver_number;
    private String driver_name;
    private boolean isSelected;
    private String latitude;
    private String longitude;
private String time;
    public model_home() {

    }

    public model_home(String name) {

        this.name = name;


    }

    public model_home(String name, boolean isSelected) {

        this.name = name;

        this.isSelected = isSelected;
    }

    public model_home(String id, String name, String file_path, String division_s, String teacher_name, String school_name, String CONTACT_TEACHER, String schoolid, String class_id, boolean b) {


    }

    public model_home(int kid_id, String id, int bus_id, String name, String file_path, String division_s, String teacher_name, String school_name, String contact_teacher, String schoolid, String class_id, boolean b) {

    }

    public model_home(int kid_id, String id, int bus_id, String name, String file_path, String division_s, String teacher_name, String school_name, String contact_teacher, int schoolid, int class_id, boolean b) {


    }

    public model_home(int kid_id, String id, int bus_id, String name, String file_path, String division_s, String teacher_name, String school_name, String contact_teacher, int schoolid, int class_id, String driver_name, String driver_number, boolean b) {


    }

    public model_home(int kid_id, String latitude, String longitude, String id, int bus_id, String name, String file_path, String division_s, String teacher_name, String school_name, String contact_teacher, int schoolid, int class_id, String driver_name, String driver_number, boolean b) {

        this.kid_id = kid_id;
        this.name = name;
        this.id = id;
        this.file_path = file_path;
        this.division_s = division_s;
        this.teacher_name = teacher_name;
        this.school_name = school_name;
        this.CONTACT_TEACHER = contact_teacher;
        this.schoolid = schoolid;
        this.class_id = class_id;
        this.bus_id = bus_id;
        this.driver_name = driver_name;
        this.driver_number = driver_number;
        this.latitude = latitude;
        this.longitude = longitude;


    }

    public String getlatitude() {
        return latitude;
    }

    public void setlatitude(String latitude) {
        this.latitude = latitude;
    }


    public String getlongitude() {
        return longitude;
    }

    public void setlongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getkid_id() {
        return kid_id;
    }

    public void setkid_id(int kid_id) {
        this.kid_id = kid_id;
    }


    public String getCONTACT_TEACHER() {
        return CONTACT_TEACHER;
    }

    public void setCONTACT_TEACHER(String CONTACT_TEACHER) {
        this.CONTACT_TEACHER = CONTACT_TEACHER;
    }


    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }


    public String getfile_path() {
        return file_path;
    }

    public void setfile_path(String file_path) {
        this.file_path = file_path;
    }


    public String getdivision_s() {
        return division_s;
    }

    public void setdivision_s(String division_s) {
        this.division_s = division_s;
    }


    public String getteacher_name() {
        return teacher_name;
    }

    public void setteacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }


    public int getschoolid() {
        return schoolid;
    }

    public void setschoolid(int schoolid) {
        this.schoolid = schoolid;
    }


    public int getclass_id() {
        return class_id;
    }

    public void setclass_id(int class_id) {
        this.class_id = class_id;
    }


    public int getbus_id() {
        return bus_id;
    }

    public void setbus_id(int bus_id) {
        this.bus_id = bus_id;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }


    public String getschool_name() {
        return school_name;
    }

    public void setschool_name(String school_name) {
        this.school_name = school_name;
    }


    public String getdriver_name() {
        return driver_name;
    }

    public void setdriver_name(String driver_name) {
        this.driver_name = driver_name;
    }


    public String getdriver_number() {
        return driver_number;
    }

    public void setdriver_number(String driver_number) {
        this.driver_number = driver_number;
    }
}