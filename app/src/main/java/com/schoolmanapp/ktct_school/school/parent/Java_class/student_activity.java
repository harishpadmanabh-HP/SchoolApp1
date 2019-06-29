package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.parent.model_class.remove_kid_model;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 05/06/17.
 */

public class student_activity extends Activity {
    ImageView iv_att, iv_track, iv_message;
    AppPreferences appPreferences;
    String student_name;
    int kid_id,busid;
    int parent_id;
    String file;
    String student_class_name;
    String division;
    String school_name;
    String teacher_name;
    String CONTACT_TEACHER;

    @Bind(R.id.kid_name)
    TextView tv_kid_name;

    @Bind(R.id.school_name)
    TextView tv_school_name;

    @Bind(R.id.class_division)
    TextView tv_class_division;

    @Bind(R.id.teacher_name)
    TextView tv_teacher_name;

    @Bind(R.id.teacher_contact_number)
    TextView tv_teacher_contact_number;

    @Bind(R.id.kid_image)
    CircleImageView kid_image;
//    @Bind(R.id.img_pic)
//    ImageView kid_image_bg;
    @Bind(R.id.stud_fee)
            ImageView iv_fees;
    @Bind(R.id.remove_child)
    LinearLayout remove_child;
    @Bind(R.id.iv_settings)
    ImageView iv_settings;
    @Bind(R.id.cimg_circular)
    ImageView circular;
    @Bind(R.id.c_img_event)
    ImageView event;

    AlertDialog loader;
    String school_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        ButterKnife.bind(this);
        iv_att = (ImageView) findViewById(R.id.attendence);
        iv_track = (ImageView) findViewById(R.id.tracking);
        iv_message = (ImageView) findViewById(R.id.message);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        loader = new SpotsDialog(this);

        student_name = appPreferences.getData("student_name");
        kid_id=appPreferences.getInt("kid_id");
        Log.e("KIdID",kid_id+"");
        parent_id=appPreferences.getInt("parent_id");
        school_id= String.valueOf(appPreferences.getInt("school_id"));
        Log.e("ParentID",parent_id+"");
        file = appPreferences.getData("file_path");
        student_class_name = appPreferences.getData("student_class_name");
        division = appPreferences.getData("division");
        school_name = appPreferences.getData("school_name");
        teacher_name = appPreferences.getData("teacher_name");
        busid=appPreferences.getInt("busid");
        //CONTACT_TEACHER = appPreferences.getData("CONTACT_TEACHER");

        tv_kid_name.setText(student_name);
        tv_class_division.setText(student_class_name + " " + division);
        tv_school_name.setText(school_name);
        tv_teacher_name.setText(teacher_name);
       // tv_teacher_contact_number.setText(CONTACT_TEACHER);
        if(file==null){
            Glide.with(this).load(R.drawable.dummy).into(kid_image);
        }
        else if (file.equals("NULL")) {
            Glide.with(this).load(R.drawable.dummy).into(kid_image);
        }else {
//        String path = "http://www.schoolman.in//" + file;
//      //  Picasso.with(this).load(path).into(kid_image_bg);
//        Picasso.with(this).load(path).into(kid_image);}

            Glide.with(this).load("http://www.schoolman.in//" + file)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(kid_image);

        }

        loader.dismiss();
        iv_att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loader.show();
                Intent i = new Intent(getApplicationContext(), attendence_activity.class);
                startActivity(i);
                loader.dismiss();

            }
        });
        circular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),activity_newevents.class);
                intent.putExtra("schoolid",school_id);
                startActivity(intent);
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),calender_events.class);
                intent.putExtra("schoolid",school_id);
                startActivity(intent);
            }
        });
        iv_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(busid==1){
                    Toast.makeText(student_activity.this, "Your child not assign any bus", Toast.LENGTH_SHORT).show();
                }else {
                    loader.show();
                    Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(i);

                    loader.dismiss();
                }

            }
        });
        iv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loader.show();
                Intent i = new Intent(getApplicationContext(), Activity_Message.class);
                startActivity(i);
                loader.dismiss();

            }
        });
        iv_fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loader.show();
                Intent i = new Intent(getApplicationContext(), activity_fees.class);
                startActivity(i);
               loader.dismiss();

            }
        });
        iv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loader.show();
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);

            }
        });
        remove_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(student_activity.this); // Context, this, etc.
                dialog.setContentView(R.layout.remove_kid_alert);
                Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
                Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
                dialog.setTitle("Warning!");
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
                        loader.show();
                        final API_interface api = Api_client.removekid().create(API_interface.class);
                        Call<remove_kid_model> call = api.removekid(parent_id,kid_id);
                        call.enqueue(new retrofit2.Callback<remove_kid_model>() {
                            @Override
                            public void onResponse(Call<remove_kid_model> call, Response<remove_kid_model> response) {
                                if (response.isSuccess()) {
                                    if (response.body().getMsg().equals("Successful")) {
                                        Intent intent =new Intent(getApplicationContext(),Home.class);
                                        startActivity(intent);
                                        finish();
                                        loader.dismiss();
                                        Toast.makeText(getApplicationContext(), "Kid Removed", Toast.LENGTH_SHORT).show();

                                    }
                                }
                                else {
                                    loader.dismiss();
                                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<remove_kid_model> call, Throwable t) {
                                loader.dismiss();
                                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    });

                    }

        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(student_activity.this, Home.class);
        startActivity(intent);

    }

    public void wakeup(View view) {
        Intent intenti = new Intent(student_activity.this, WakeUp .class);
        startActivity(intenti);
    }
}
