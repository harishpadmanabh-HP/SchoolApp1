package com.schoolmanapp.ktct_school.school.parent.Java_class;

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


    private boolean isSelected;

    public model_message() {

    }

    public model_message(String name) {

        this.name = name;


    }

    public model_message(String name, boolean isSelected) {

        this.name = name;

        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;

    }
}
