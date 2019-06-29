package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;

import com.schoolmanapp.ktct_school.school.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

/**
 * Created by srishtiinnovative on 01/06/17.
 */

public class BookingDecorator implements DayViewDecorator {
    private int mColor;
    private CalendarDay mCalendarDayCollection;
    Context context;
    public BookingDecorator(int myColor, CalendarDay day, calender_activity home) {
        mColor = myColor;
        mCalendarDayCollection = day;
        context=home;

    }




    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return mCalendarDayCollection.equals(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(mColor));
        //view.addSpan(new BackgroundColorSpan(Color.BLUE));
        view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circle));
        view.setSelectionDrawable(ContextCompat.getDrawable(context,R.drawable.circle));

    }
}
