package com.schoolmanapp.ktct_school.school.parent.Java_class;

/**
 * Created by srishtiinnovative on 05/06/17.
 */


import android.app.AlertDialog;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * + * Decorate several days with two dots
 * +
 */
public class DoubleEventDecorator_calender implements DayViewDecorator {



    private int color;

    private HashSet<CalendarDay> dates;

    public DoubleEventDecorator_calender(int blue, Collection<CalendarDay> doubleEventDays) {
        color=blue;
        dates = new HashSet<>(doubleEventDays);
    }


    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }


    @Override
    public void decorate(DayViewFacade view) {

            view.addSpan(new DotSpan(5, color));
        Log.e("HEllooo","haiii");



    }
}