package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 13/07/17.
 */

public class no_webview extends Activity {

    AppPreferences appPreferences;
    String description, subject, filepath;
    @Bind(R.id.description)
    TextView tv_description;

    @Bind(R.id.subject)
    TextView tv_subject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowebview);
        ButterKnife.bind(this);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        description = appPreferences.getData("description");
        subject = appPreferences.getData("subject");
        filepath = appPreferences.getData("filepath");
        Log.e("filepath", filepath);
        tv_description.setText(description);
        tv_subject.setText(subject);




    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(student_message.this, message_activity.class);
//        startActivity(intent);
//    }
}


