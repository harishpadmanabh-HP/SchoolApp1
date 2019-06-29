package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.event_model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 14/02/18.
 */

public class actvity_calender extends AppCompatActivity {
    @Bind(R.id.list_calender)
    ListView list;

    @Bind(R.id.calendarView_calender)
    com.prolificinteractive.materialcalendarview.MaterialCalendarView widget1;
    List<event_model.UserDataBean> list_events;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_events);
        ButterKnife.bind(this);
        list_events = new ArrayList<>();

        widget1.setOnDateChangedListener(new OnDateSelectedListener() {
          @Override
          public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
              Log.e("checkdATE", date.getDate() + "");
              System.out.print(date.getDate()+"");
          }
      });
    }
}
