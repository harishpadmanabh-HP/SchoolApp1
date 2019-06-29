package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 22/06/17.
 */

public class timing_previous extends Activity {
    AppPreferences appPreferences;
    String date;
    @Bind(R.id.date_previous)
    TextView tv_date;
    @Bind(R.id.morning_img)
    TextView img_morning;
    @Bind(R.id.evening_img)
    TextView img_evening;
    String time;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing_previous);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        ButterKnife.bind(this);
       // date=appPreferences.getData("previous_date");
        date=appPreferences.getData("date_timing");
        tv_date.setText(date);
        img_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="Morning";
                appPreferences.saveData("time",time);
                Intent intent= new Intent(timing_previous.this,previous_attaendence.class);
                startActivity(intent);
            }
        });
        img_evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="Afternoon";
                appPreferences.saveData("time",time);
                Intent intent= new Intent(timing_previous.this,previous_attaendence.class);
                startActivity(intent);

            }
        });




    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(getApplicationContext(),home_activity.class);
        startActivity(intent);
    }
}
