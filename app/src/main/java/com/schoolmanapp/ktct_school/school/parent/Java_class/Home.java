package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;

import com.schoolmanapp.ktct_school.school.parent.model_class.kid_list_model;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

public class Home extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String driver_name, driver_number;
    private List<model_home> studentList;
    ImageView iv_addstud, iv_settings;
    AppPreferences appPreferences;
    int parent_id;
    ArrayList school, kid_name, class_array, division, teacher, contact, Image;
    AlertDialog dialog;
     String CONTACT_TEACHER;
      String teacher_name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_parent);
        ButterKnife.bind(this);
        iv_addstud = (ImageView) findViewById(R.id.img_addstudent);
        iv_settings = (ImageView) findViewById(R.id.iv_settings);
        studentList = new ArrayList<model_home>();
        class_array = new ArrayList();
        division = new ArrayList();
        teacher = new ArrayList();
        contact = new ArrayList();
        Image = new ArrayList();
        school = new ArrayList();
        kid_name = new ArrayList();
        dialog = new SpotsDialog(this);
        dialog.dismiss();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        parent_id = appPreferences.getInt("parent_id");
        Log.e("parent_id", "id" + parent_id);

        try {
            dialog.show();
            final API_interface api = Api_client.getClient().create(API_interface.class);
            Call<kid_list_model> call = api.kidlistModelCall(parent_id);

            call.enqueue(new retrofit2.Callback<kid_list_model>() {

                @Override
                public void onResponse(Call<kid_list_model> call, Response<kid_list_model> response) {

                    if (response.isSuccess()) {
                        Log.e("response:", response.body().getMsg() + "");
                        if (response.body().getMsg().equals("success")) {
                            List<kid_list_model.UserDataBean> student_details = response.body().getUserData();
                            Log.e("§", " :" + student_details);

                            for (int i = 0; i < student_details.size(); i++) {

                                int kid_id = student_details.get(i).getStudentId();

                                appPreferences.saveInt("kid_id",kid_id);
                                Log.e("kid_id", " :" + kid_id);
                                String id = student_details.get(i).getClassName();
                                class_array.add(id);
                                int schoolid = student_details.get(i).getSchoolId();
                                int class_id = student_details.get(i).getClassId();
                                String name = student_details.get(i).getStundentName();
                                kid_name.add(name);
                                Log.e("name", "kid" + kid_name);

                                String file_path = student_details.get(i).getFilePath();
                                Image.add(file_path);

                                String division_s = student_details.get(i).getDivisionName();
                                division.add(division_s);
                                     String teacherss = student_details.get(i).getTeacher() + "";
                                  if (!teacherss.equals("null")) {
                                      teacher_name = student_details.get(i).getTeacher().getTeacherName();
                                      teacher.add(teacher_name);
                                     CONTACT_TEACHER = student_details.get(i).getTeacher().getContactNumber();
                                  }

                                int bus_id = student_details.get(i).getBusId();
                                String latitude = student_details.get(i).getLatitude();
                                String longitude = student_details.get(i).getLongitude();
                                String school_name = student_details.get(i).getSchoolName();
                                school.add(school_name);

                                String driver = student_details.get(i).getDriver() + "";

                                if (driver.equals("null")) {
                                    driver_name = "No driver found";
                                    appPreferences.saveData("driver_name", driver_name);
                                    driver_number = "No driver";
                                    appPreferences.saveData("driver_number", driver_number);
                                } else {
                                    driver_name = student_details.get(i).getDriver().getDriverName();
                                    driver_number = student_details.get(i).getDriver().getContactNumber();
                                    appPreferences.saveData("driver_name", driver_name);
                                    appPreferences.saveData("driver_number", driver_number);
                                }
                                model_home s = new model_home(kid_id, latitude, longitude, id, bus_id, name, file_path, division_s, teacher_name, school_name, CONTACT_TEACHER, schoolid, class_id, driver_name, driver_number, false);
                                Log.e("response", " name:" + name);
                                studentList.add(s);


                            }
                            Log.e("student_list", "student" + studentList);
//                            if (toolbar != null) {
//
////                                setSupportActionBar(toolbar);
////                                getSupportActionBar().setTitle("Android Students");
//
//
//                            }

                            mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

                            // use this setting to improve performance if you know that changes
                            // in content do not change the layout size of the RecyclerView
                            mRecyclerView.setHasFixedSize(true);

                            // use a linear layout manager
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                            // create an Object for Adapter
                            mAdapter = new CardViewDataAdapter(Home.this, studentList);

                            // set the adapter object to the Recyclerview
                            mRecyclerView.setAdapter(mAdapter);

                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<kid_list_model> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            });
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "No Value", Toast.LENGTH_SHORT).show();
        }


        iv_addstud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Add_student.class);
                startActivity(intent);
            }
        });
        iv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}

