package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 01/06/17.
 */

public class calender_activity extends Activity implements OnDateSelectedListener {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat FORMATTER_new = new SimpleDateFormat("dd/MM/yyyy");


    MaterialCalendarView calender;
    CalendarDay day;
    SharedPreferences shared;
    SharedPreferences.Editor edit;
    @Bind(R.id.class_name)
    TextView et_class_name;
    @Bind(R.id.division_name)
    TextView et_division_name;
    Calendar c;
    AppPreferences appPreferences;
    MaterialCalendarView calendarView;
    String DATE, class_name, division_name;
    String newformat_date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        ButterKnife.bind(this);


        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangedListener(this);
        Calendar calendar = Calendar.getInstance();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        class_name = appPreferences.getData("class_name");
        division_name = appPreferences.getData("division_name");

        calendarView.setDateSelected(calendar.getTime(), true);
        calender = (MaterialCalendarView) findViewById(R.id.calendarView);
        et_class_name.setText(class_name);
        et_division_name.setText(division_name);
        c = Calendar.getInstance();
        day = calender.getCurrentDate();

        int b=calendarView.getTitleAnimationOrientation();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date_time = sdf.format(c.getTime());
        appPreferences.saveData("current_date_time", date_time);
        Log.e("date time:::", date_time);


        calendarView.setOnDateChangedListener(this);
        DATE = getSelectedDatesString();
        newformat_date=getSelectedDatesStringNEW();
        Log.e("date:///////", DATE);
        shared = getSharedPreferences("app", MODE_PRIVATE);
        edit = shared.edit();
        edit.putString("current date", newformat_date);
        edit.commit();

//        int myColor = R.color.colorAccent;
//        calender.addDecorator(new BookingDecorator(myColor, day, calender_activity.this));

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), home_activity.class);
        startActivity(intent);
    }

    private String getSelectedDatesString() {
        CalendarDay date = calendarView.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    private String getSelectedDatesStringNEW() {
        CalendarDay date = calendarView.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER_new.format(date.getDate());
    }
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        boolean b = false;
        String a = getSelectedDatesString();
        String newdate=getSelectedDatesStringNEW();
        Log.e("selected_Date", a);
        Log.e("current_date",DATE);
        try {
            if(FORMATTER.parse(a).before(FORMATTER.parse(DATE)))
            {
                b = true;//If start date is before end date
            }
            else
            {
                b = false; //If start date is after the end date
            }
        }  catch (ParseException e) {
            e.printStackTrace();
        }

        if (a.equals(DATE)) {
            appPreferences.saveData("date", a);
            Intent intent = new Intent(getApplicationContext(), Timing.class);
            startActivity(intent);
        } else if(b==true){
            Log.e("formatted_date://", a);
            appPreferences.saveData("date", a);
            appPreferences.saveData("date_timing",newdate);
            Intent intent = new Intent(getApplicationContext(), timing_previous.class);
            startActivity(intent);
        }


    }
}