package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
 * Created by srishtiinnovative on 01/06/17.
 */

public class Timing extends Activity {
    @Bind(R.id.morning_img)
    TextView img_morning;
    @Bind(R.id.evening_img)
   TextView img_evening;
//    @Bind(R.id.img_back)
//    ImageView img_back;
    @Bind(R.id.date)
    TextView date;
    SharedPreferences shared;
    SharedPreferences.Editor edit;
    String time;
    AppPreferences appPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);
        shared = getSharedPreferences("app", MODE_PRIVATE);
        edit=shared.edit();
        String date_text=shared.getString("current date",null);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        ButterKnife.bind(this);
        date.setText(date_text);
        img_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="Morning";
                appPreferences.saveData("time",time);
                edit.putString("time",time);
                edit.commit();
                Intent intent= new Intent(Timing.this,student_attendence.class);
                startActivity(intent);
            }
        });
        img_evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="Afternoon";
                edit.putString("time",time);
                edit.commit();
                Intent intent= new Intent(Timing.this,student_attendence.class);
                startActivity(intent);

            }
        });

//        img_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(Timing.this,calender_activity.class);
//                startActivity(intent);
//            }
//        });
    }
    @Override
    public void onBackPressed() {
        Intent intent= new Intent(getApplicationContext(),home_activity.class);
        startActivity(intent);
    }
}
