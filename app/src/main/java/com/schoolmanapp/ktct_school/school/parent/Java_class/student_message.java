package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsibbold.zoomage.ZoomageView;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 06/06/17.
 */

public class student_message extends Activity {

    AppPreferences appPreferences;
    String description, subject, filepath,kidname;
    @Bind(R.id.description)
    TextView tv_description;

    @Bind(R.id.subject)
    TextView tv_subject;
    @Bind(R.id.studentname)
    TextView tv_studentname;
    @Bind(R.id.webView1)
    ImageView image;
    @Bind(R.id.main)RelativeLayout msg;
    LinearLayout content;
    RelativeLayout rl_des;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_message_parent);
        ButterKnife.bind(this);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        description = appPreferences.getData("description");
        subject = appPreferences.getData("subject");
        filepath = appPreferences.getData("filepath");
        kidname=appPreferences.getData("kid_name");
        Log.e("filepath", filepath);
        content=(LinearLayout)findViewById(R.id.content);
        rl_des=(RelativeLayout)findViewById(R.id.rl_ds);
        tv_description.setText(description);
        tv_studentname.setText(kidname);
        tv_subject.setText(subject);
        tv_description.setMovementMethod(new ScrollingMovementMethod());
//        web.getSettings().setJavaScriptEnabled(true);
        String path = "http://www.schoolman.in//" + filepath;
        if(filepath.isEmpty()){
            image.setVisibility(View.GONE);
        }else {
           image.setVisibility(View.VISIBLE);
            Picasso.with(getApplicationContext()).load(path).into(image);
        }
        content.setVisibility(View.VISIBLE);
        tv_subject.setVisibility(View.VISIBLE);
        tv_description.setVisibility(View.VISIBLE);
//        image.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(tv_description.getVisibility()==View.VISIBLE && tv_subject.getVisibility()==View.VISIBLE){
//                    tv_subject.setVisibility(View.GONE);
//                    tv_description.setVisibility(View.GONE);
//                    content.setVisibility(View.GONE);
//                }
//                else{
//                    tv_subject.setVisibility(View.VISIBLE);
//                    tv_description.setVisibility(View.VISIBLE);
//                    content.setVisibility(View.VISIBLE);
//                }
//                return true;
//            }
//        });
        rl_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_description.getVisibility()==View.VISIBLE && tv_subject.getVisibility()==View.VISIBLE){
                    tv_subject.setVisibility(View.GONE);
                    tv_description.setVisibility(View.GONE);
                    content.setVisibility(View.GONE);
                }
                else{
                    tv_subject.setVisibility(View.VISIBLE);
                    tv_description.setVisibility(View.VISIBLE);
                    content.setVisibility(View.VISIBLE);
                }
            }
        });


    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(student_message.this, message_activity.class);
//        startActivity(intent);
//    }
}
