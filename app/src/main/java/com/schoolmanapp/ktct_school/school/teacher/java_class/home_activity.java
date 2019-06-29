package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 02/06/17.
 */

public class home_activity extends Activity {
    @Bind(R.id.attendence)
    ImageView attendence;
    @Bind(R.id.message)
    ImageView message;
    @Bind(R.id.logout)
    ImageView logout;
    @Bind(R.id.addmark)
    ImageView mark;


    AppPreferences appPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id=appPreferences.getInt("schoolid");
                appPreferences.saveInt("schoolid",id);
                Toast.makeText(home_activity.this, ""+id, Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(home_activity.this,AddMark.class);
                startActivity(intent);
            }
        });

        attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(home_activity.this,calender_activity.class);
                startActivity(intent);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(home_activity.this,message_activity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(home_activity.this); // Context, this, etc.
                dialog.setContentView(R.layout.confirm_logout);
                Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
                Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
                dialog.setTitle("Do you want to Logout?");


                dialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appPreferences.saveDataBoolean("isLogin",false);

                         Intent intent= new Intent(home_activity.this,Login.class);
                         startActivity(intent);
                    }
                });

            }
        });
    }
    public void onBackPressed() {
         // your code.
    }
}
