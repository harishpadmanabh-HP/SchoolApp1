package com.schoolmanapp.ktct_school.school.parent.Java_class;

/**
 * Created by srishtiinnovative on 05/06/17.
 */


import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * + * Decorate several days with two dots
 * +
 */
public class DoubleEventDecorator implements DayViewDecorator {

    private int colorLeft;
    private int color= Color.WHITE;
    private int colorRight;
    private HashSet<CalendarDay> dates;
    private int morning_shift, evening_shift,calender;




    public DoubleEventDecorator(int red, int green, Collection<CalendarDay> doubleEventDays, int morning_shift, int evening_shift) {

        this.colorLeft = red;
        this.colorRight = green;
        dates = new HashSet<>(doubleEventDays);
        this.morning_shift = morning_shift;
        this.evening_shift = evening_shift;


    }

//    public DoubleEventDecorator(int i, int red, int blue, Collection<CalendarDay> doubleEventDays, String morning_shift, String calender) {
//        color=blue;
//        dates = new HashSet<>(doubleEventDays);
//        this.calender=calender;
//        this.morning_shift = "";
//        this.evening_shift = "";
//    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }


    @Override
    public void decorate(DayViewFacade view) {
        if (morning_shift==2 && evening_shift==2) {
            view.addSpan(new DoubleDotSpan(5, colorLeft, colorLeft));
        } else if (morning_shift==1 && evening_shift==1) {
            view.addSpan(new DoubleDotSpan(5, colorRight, colorRight));
        }
        else if(morning_shift==2 && evening_shift==1){
            view.addSpan(new DoubleDotSpan(5, colorLeft, colorRight));
        }
        else if(morning_shift==1 && evening_shift==2){
            view.addSpan(new DoubleDotSpan(5, colorRight, colorLeft));
        }
        else if(morning_shift==0 && evening_shift==1){
            view.addSpan(new DoubleDotSpan(5, color, colorRight));
        }
        else if(morning_shift==0 && evening_shift==2){
            view.addSpan(new DoubleDotSpan(5, color, colorLeft));
        }
        else if(morning_shift==1 && evening_shift==0){
            view.addSpan(new DoubleDotSpan(5, colorRight, color));
        }
        else if(morning_shift==2 && evening_shift==0){
            view.addSpan(new DoubleDotSpan(5, colorLeft, color));
        }
        else if(morning_shift==0 && evening_shift==0){
            view.addSpan(new DoubleDotSpan(5, color, color));
        }
        else {
            view.addSpan(new DotSpan(5, color));
        }


    }
}