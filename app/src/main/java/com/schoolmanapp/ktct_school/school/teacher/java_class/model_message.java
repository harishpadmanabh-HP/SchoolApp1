package com.schoolmanapp.ktct_school.school.teacher.java_class;

import java.io.Serializable;

/**
 * Created by srishtiinnovative on 31/05/17.
 */

public class model_message implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private String file_path;
    private String div;


    private int student_id;



    private boolean isSelected;

    public model_message() {

    }

    public model_message(String name) {

        this.name = name;


    }

    public model_message(String name, String file_path, boolean isSelected) {


    }

    public model_message(String name, String file_path, int id, boolean isSelected, String student) {
        this.name = name;
        this.file_path = file_path;
         student_id=id;
        this.isSelected = isSelected;
        div=student+"";
    }

    public int getId() {
        return student_id;
    }

    public void setId(int student_id) {
        this.student_id = student_id;
    }

    public String getdiv() {
        return div;
    }

    public void setdiv(String div) {
        this.div = div;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }



    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}