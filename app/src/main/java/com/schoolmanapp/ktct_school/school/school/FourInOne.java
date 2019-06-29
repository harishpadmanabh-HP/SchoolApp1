package com.schoolmanapp.ktct_school.school.school;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;


import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.Java_class.actvity_calender;
import com.schoolmanapp.ktct_school.school.parent.Java_class.calender_events;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by srishtiinnovative on 21/07/17.
 */

public class FourInOne extends Activity {


@Bind(R.id.web)
ImageView iv_link;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_in_one);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.layout)
    public void txt_link_schoolClick() {
        Intent intent = new Intent(FourInOne.this, Login.class);
        startActivity(intent);
    }

    @OnClick(R.id.txt_link_teacher)
    public void txt_link_teacherClick() {
        Intent intent = new Intent(FourInOne.this, com.schoolmanapp.ktct_school.school.teacher.java_class.Login.class);
        startActivity(intent);
    }

    @OnClick(R.id.txt_link_parent)
    public void txt_link_parentClick() {
        Intent intent = new Intent(FourInOne.this, com.schoolmanapp.ktct_school.school.parent.Java_class.Login.class);
        startActivity(intent);
    }

    @OnClick(R.id.txt_link_driver)
    public void txt_link_driverClick() {
        Intent intent = new Intent(FourInOne.this, com.schoolmanapp.ktct_school.school.driver.Login.class);
        startActivity(intent);
    }
    @OnClick(R.id.bt_calender)
    public void txt_link_calender() {
        Intent intent = new Intent(FourInOne.this, calender_events.class);
        startActivity(intent);
    }
    @OnClick(R.id.bt_events)
    public void txt_link_events() {
        Intent intent = new Intent(FourInOne.this, com.schoolmanapp.ktct_school.school.parent.Java_class.activity_newevents.class);
        startActivity(intent);
    }
    @OnClick(R.id.web)
    public void wv_link() {
        Intent intent = new Intent(FourInOne.this, com.schoolmanapp.ktct_school.school.parent.Java_class.webview.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finishAffinity();
    }
}
