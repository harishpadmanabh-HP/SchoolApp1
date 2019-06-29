package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.teacher.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.teacher.Api_interface.API_interface;
import com.schoolmanapp.ktct_school.school.teacher.model_class.previous_attendence_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.student_list_model;
import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 22/06/17.
 */

public class previous_attaendence extends Activity {
    AppPreferences appPreferences;
    int teacher_id, division_id, class_id;
    String date, time;
    int shift_status;
    ArrayList studentid_previous = new ArrayList();
    ArrayList attendence_data = new ArrayList();
    customgridview adapter;
    ArrayList studentid = new ArrayList();
    ArrayList student_name = new ArrayList();;
    @Bind(R.id.attendence_grid)
    GridView grid;
    AlertDialog dialog ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_attendence);
        ButterKnife.bind(this);
        dialog = new SpotsDialog(previous_attaendence.this);

        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        teacher_id = appPreferences.getInt("teacher_id");
        division_id = appPreferences.getInt("division_id");
        class_id = appPreferences.getInt("class_id");
        date = appPreferences.getData("date");
        time = appPreferences.getData("time");
        Log.e("teacher_id" + teacher_id, "division_id :" + division_id + "class_id :" + class_id + "previous_date :" + date + "time : " + time);
        if (time.equals("Morning")) {
            shift_status = 0;
        } else {
            shift_status = 1;
        }

        try {
            dialog.show();
            final API_interface api = Api_client.getClient().create(API_interface.class);
            Call<previous_attendence_model> call = api.previous_attendence_listModelCall(teacher_id,
                    class_id,
                    division_id, date, shift_status);

            call.enqueue(new retrofit2.Callback<previous_attendence_model>() {

                @Override
                public void onResponse(Call<previous_attendence_model> call, Response<previous_attendence_model> response) {
                    if (response.isSuccess()) {
                        if (response.body().getMsg().equals("Success")) {
                            List<previous_attendence_model.UserDataBean> student_details = response.body().getUserData();
                            Log.e("response", " :" + student_details);
                            if (student_details.size() > 0) {
                                for (int i = 0; i < student_details.size(); i++) {

                                    String roll = String.valueOf(student_details.get(i).getStudentId());
                                    Log.e("response", " roll:" + roll);
                                    studentid_previous.add(roll);
                                    boolean attendence = student_details.get(i).isAttendanceData();
                                    Log.e("response", " name:" + attendence);
                                    attendence_data.add(attendence);
                                    dialog.dismiss();
                                    student_details();

                                }
//                                adapter = new customgridview(studentid, attendence_data);
//                                grid.setAdapter(adapter);
//                                Log.e("student id", ":" + studentid);
//                                Log.e("student name", ":" + attendence_data);
                            } else {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "No attendence data available", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), timing_previous.class);
                                startActivity(intent);
                                finish();

                            }
                        } else {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(),response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), timing_previous.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), timing_previous.class);
                        startActivity(intent);
                        finish();

                    }
                }

                @Override
                public void onFailure(Call<previous_attendence_model> call, Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "check your network connection", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.dismiss();
        } catch (NullPointerException e) {
            //Toast.makeText(getApplicationContext(), "No Value", Toast.LENGTH_SHORT).show();
        }




    }
    public void  student_details(){
        try {
            dialog.show();
            final API_interface api = Api_client.getClient().create(API_interface.class);
            Call<student_list_model> call = api.studentlistModelCall(teacher_id,class_id,division_id);

            call.enqueue(new retrofit2.Callback<student_list_model>() {

                @Override
                public void onResponse(Call<student_list_model> call, Response<student_list_model> response) {
                    if (response.isSuccess()) {
                        if (response.body().getMsg().equals("Success")) {
                            List<student_list_model.UserDataBean> student_details = response.body().getUserData();
                            Log.e("response", " :" + student_details);

                            for (int i = 0; i < student_details.size(); i++) {

                                String roll = String.valueOf(student_details.get(i).getStudentId());
                                Log.e("response", " roll:" + roll);
                                studentid.add(roll);
                                String name = student_details.get(i).getStundentName();
                                Log.e("response", " name:" + name);
                                student_name.add(name);

                            }
                            adapter = new customgridview(studentid, student_name, attendence_data, studentid_previous);
                            grid.setAdapter(adapter);
                            dialog.dismiss();
                            Log.e("student id", ":" + studentid);
                            Log.e("student name", ":" + student_name);

                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<student_list_model> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "check your network connection", Toast.LENGTH_SHORT).show();
                }

            });
        } catch (NullPointerException e) {
            //   Toast.makeText(getApplicationContext(), "No Value", Toast.LENGTH_SHORT).show();
        }


    }


    public class customgridview extends BaseAdapter {


        public ArrayList studentid, attendence_data,student_name,studentid_previous;
        int mSelectedItem;


        public customgridview(ArrayList studentid, ArrayList attendence_data) {

            this.studentid = studentid;
            this.attendence_data = attendence_data;
        }

        public customgridview(ArrayList studentid, ArrayList student_name, ArrayList attendence_data, ArrayList studentid_previous) {
            this.studentid = studentid;
            this.student_name = student_name;
            this.attendence_data=attendence_data;
            Log.e("attendence_data","attendence_data"+attendence_data);
            this.studentid_previous=studentid_previous;
            Log.e("studentid_previous","studentid_previous"+studentid_previous);


        }


        @Override
        public int getCount() {
            return attendence_data.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            Holder holder = new Holder();
            View rowView;


            LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = vi.inflate(R.layout.custom_student, null);
            holder.tv_roll = (TextView) rowView.findViewById(R.id.tvId);
            holder.tv_name = (TextView) rowView.findViewById(R.id.tvName);
            holder.l = (LinearLayout) rowView.findViewById(R.id.grid_bg);
            holder.tv_roll.setText(studentid.get(position).toString());
            holder.tv_name.setText(student_name.get(position).toString());
            holder.ab=(ImageView)rowView.findViewById(R.id.img_ab);
//            Boolean flag = Boolean.getBoolean(attendence_data.get(position).toString());
//            if (flag == true) {
//                holder.l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_selected));
//
//
//            } else {
//                holder.l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_attendence));
//
//
//            }



//                Log.e("studentid_previous","studentid_previous"+studentid_previous.get(i)+"//"+studentid.get(position));
            if((attendence_data.get(position).equals(true))){
                holder.l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_selected));
            }
            else {
                holder.ab.setVisibility(View.VISIBLE);
                holder.l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_attendence));
            }



            return rowView;
        }


    }

    public class Holder {
        TextView tv_name, tv_roll;
        LinearLayout l;
        ImageView ab;

    }
}
