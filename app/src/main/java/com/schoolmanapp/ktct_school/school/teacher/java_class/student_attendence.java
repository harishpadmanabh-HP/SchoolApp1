package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.teacher.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.teacher.Api_interface.API_interface;
import com.schoolmanapp.ktct_school.school.teacher.model_class.attendence_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.previous_attendence_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.response_attendence_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.student_list_model;
import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 04/08/17.
 */

public class student_attendence extends Activity {

    @Bind(R.id.button_submit)
    TextView submit;
    @Bind(R.id.attendence_grid)
    GridView grid;
    @Bind(R.id.time)
    TextView time;



    AlertDialog dialog;
    SharedPreferences shared;
    ArrayList array_position;
    int shift_status;
    int checkin_status = 1;
    AppPreferences appPreferences;
    int teacher_id, class_id, division_id;
    String attendence_time;
    String json;
    RequestBody AccessTokenValue = null;
    ArrayList studentid = new ArrayList();
    ArrayList student_name = new ArrayList();
    String DATE;
    ArrayList attendence_data, studentid_previous;


    private List<student_list_model.UserDataBean> allStudentsDetails;
    public static ArrayList<String> present = new ArrayList();
    private StudentAttendenceGridAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        ButterKnife.bind(this);
        shared = getSharedPreferences("app", MODE_PRIVATE);
        String time_text = shared.getString("time", null);
        attendence_data = new ArrayList();
        studentid_previous = new ArrayList();
        dialog = new SpotsDialog(this);
        array_position = new ArrayList();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        DATE = appPreferences.getData("date");
        teacher_id = appPreferences.getInt("teacher_id");
        class_id = appPreferences.getInt("class_id");
        division_id = appPreferences.getInt("division_id");
        attendence_time = appPreferences.getData("current_date_time");
        if (time_text.equals("Morning")) {
            shift_status = 0;
        } else if (time_text.equals("Afternoon")) {
            shift_status = 1;

        }
        present.clear();
        //dialog.show();
        getStudentList();
        time.setText(time_text);
        Log.e("student id", ":" + studentid);

    }

    @OnClick(R.id.button_submit)
    public void button_submitClick() {
        final Dialog dialog = new Dialog(student_attendence.this); // Context, this, etc.
        dialog.setContentView(R.layout.upadate_attendence_dialogue);
        Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
        Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
        TextView total_std=(TextView) dialog.findViewById(R.id.tv_total_std);
        TextView total_prsnt=(TextView) dialog.findViewById(R.id.tv_total_prst);
        TextView total_ab=(TextView) dialog.findViewById(R.id.tv_total_ab);
        int ab=allStudentsDetails.size() - present.size();
        total_std.setText(allStudentsDetails.size()+"");
        total_prsnt.setText(present.size()+"");
        total_ab.setText(ab+"");
        dialog.setTitle("Attendence Updating!");
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
                dialog.dismiss();
                callSubmitApi();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Timing.class);
        startActivity(intent);
    }

    private void getStudentList() {
        try {
            dialog.show();
            final API_interface api = Api_client.getClient().create(API_interface.class);
            Call<student_list_model> call2 = api.studentlistModelCall(teacher_id, class_id, division_id);
            call2.enqueue(new retrofit2.Callback<student_list_model>() {
                @Override
                public void onResponse(Call<student_list_model> call, Response<student_list_model> response) {
                    if (response.isSuccess()) {
                        if (response.body().getMsg().equals("Success")) {
                            allStudentsDetails = response.body().getUserData();
                            getPervAttendance();
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
                public void onFailure(Call<student_list_model> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            });
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "No Value", Toast.LENGTH_SHORT).show();
        }
    }

    private void getPervAttendance() {
        try {
            final API_interface api = Api_client.getClient().create(API_interface.class);
            Call<previous_attendence_model> call1 = api.previous_attendence_listModelCall(teacher_id, class_id, division_id, DATE, shift_status);
            call1.enqueue(new retrofit2.Callback<previous_attendence_model>() {
                @Override
                public void onResponse(Call<previous_attendence_model> call, Response<previous_attendence_model> response) {
                    if (response.isSuccess()) {
                        if (response.body().getMsg().equals("Success")) {
                            List<previous_attendence_model.UserDataBean> previous_attendence_model = response.body().getUserData();
                            if (previous_attendence_model.size() > 0) {
                                for (int i = 0; i < previous_attendence_model.size(); i++) {
                                    if(previous_attendence_model.get(i).isAttendanceData()==true){
                                        present.add(previous_attendence_model.get(i).getStudentId() + "");}
                                }
                                adapter = new StudentAttendenceGridAdapter(getApplicationContext(), allStudentsDetails);
                                grid.setAdapter(adapter);
                            }

                        } else {
                            for (int i = 0; i < allStudentsDetails.size(); i++) {
                                present.add(allStudentsDetails.get(i).getStudentId() + "");
                            }
                            adapter = new StudentAttendenceGridAdapter(getApplicationContext(), allStudentsDetails);
                            grid.setAdapter(adapter);
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<previous_attendence_model> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                }

            });
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "No Value", Toast.LENGTH_SHORT).show();
        }
    }

    private void callSubmitApi() {
        dialog.show();
        attendence_model att_model_object = null;
        List<attendence_model.StudentListBean> student_list = null;
        attendence_model.StudentListBean student_listbean;
        try {
            att_model_object = new attendence_model();
            att_model_object.teacherId = String.valueOf(teacher_id);
            att_model_object.attendanceDateTime = attendence_time;
            att_model_object.classId = String.valueOf(class_id);
            att_model_object.divisionId = String.valueOf(division_id);
            att_model_object.shiftstatus = String.valueOf(shift_status);

            student_list = new ArrayList<attendence_model.StudentListBean>();
            for (int i = 0; i < present.size(); i++) {
                student_listbean = new attendence_model.StudentListBean();
                try {
                    student_listbean.studentId = present.get(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    student_listbean.attendaneStatus = String.valueOf(checkin_status);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                student_list.add(student_listbean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Gson gson = new Gson();
            att_model_object.studentList = student_list;
            json = gson.toJson(att_model_object).trim();
            AccessTokenValue = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            AccessTokenValue = RequestBody.create(MediaType.parse("application/json"), json.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.e("AccessTokenValue:", "value" + json);
        Log.e("AccessTokenValue:", "value" + AccessTokenValue);
        final API_interface api = Api_client.getClient().create(API_interface.class);
        Call<response_attendence_model> call = api.update_attendence(AccessTokenValue);
        call.enqueue(new retrofit2.Callback<response_attendence_model>() {
            @Override
            public void onResponse(Call<response_attendence_model> call, Response<response_attendence_model> response) {
                if (response.isSuccess()) {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Attendence status updated" + "", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), home_activity.class);
                    startActivity(intent);
                } else {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Error in status updation:check your network", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<response_attendence_model> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error in status updation:check your network", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
