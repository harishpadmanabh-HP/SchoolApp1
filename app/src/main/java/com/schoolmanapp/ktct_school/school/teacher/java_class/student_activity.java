package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.teacher.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.teacher.Api_interface.API_interface;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.teacher.model_class.attendence_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.previous_attendence_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.response_attendence_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.student_list_model;
import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 02/06/17.
 */

public class student_activity extends Activity {
    @Bind(R.id.button_submit)
    TextView submit;
    @Bind(R.id.attendence_grid)
    GridView grid;
    @Bind(R.id.time)
    TextView time;
    ArrayList<String> present = new ArrayList();
    AlertDialog dialog ;
    Button btn_add, btn_mark, btn_msg;

    GridView gv;
    CheckBox cb;
    LinearLayout l;
    SharedPreferences shared;
    customgridview adapter;
    int mSelectedItem;
    Boolean flag = false;
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


        try {
            final API_interface api = Api_client.getClient().create(API_interface.class);
            Call<previous_attendence_model> call = api.previous_attendence_listModelCall(teacher_id,
                    class_id,
                    division_id, DATE, shift_status);

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


                                }

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<previous_attendence_model> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "check your network connection", Toast.LENGTH_SHORT).show();
                }

            });
        } catch (NullPointerException e) {
         //   Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
        }


        Log.e("teacher_id:", "//" + teacher_id);
        Log.e("class_id:", "//" + class_id);
        Log.e("division_id:", "//" + division_id);
        Log.e("attendence_time:", "//" + attendence_time);
        try {
            dialog.show();
            final API_interface api = Api_client.getClient().create(API_interface.class);
            Call<student_list_model> call = api.studentlistModelCall(teacher_id,
                    class_id,
                    division_id);

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
                            dialog.dismiss();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<student_list_model> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "check your network connection", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            });
        } catch (NullPointerException e) {
            //Toast.makeText(getApplicationContext(), "No Value", Toast.LENGTH_SHORT).show();
        }


        time.setText(time_text);
        Log.e("student id", ":" + studentid);
        Log.e("student name", ":" + student_name);
        present.clear();
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout l = (LinearLayout) view;
                String student = studentid.get(position).toString();

                if (flag == false) {
                    if(studentid_previous.contains(studentid.get(position))){
                        l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_attendence));
                        studentid_previous.remove(studentid.get(position));
                        flag=true;
                    }
                    else {
                        array_position.add(position + "");
                        present.add(studentid.get(position).toString());
                        l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_selected));
                        Log.e("flag", "  false :" + array_position);
                        flag = true;
                    }
                } else if ((flag == true) && (array_position.contains(position + ""))) {
                    l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_attendence));
                    array_position.remove(position + "");
                    present.remove(studentid.get(position));
                    Log.e("flag :true", " studentid.get(position).toString() :" + studentid.contains(studentid.get(position).toString()));
                    Log.e("flag :true", " array_position.contains(position) :" + array_position);
                } else if ((flag == true) && (!array_position.contains(position + ""))) {
                    if(studentid_previous.contains(studentid.get(position))){
                        l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_attendence));
                        studentid_previous.remove(studentid.get(position));
                        flag=true;
                    }else {
                        l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_selected));
                        array_position.add(position + "");
                        present.add(studentid.get(position).toString());
                        Log.e("flag :true", "  :" + array_position);
                    }
                }
                Log.e("present:", "list" + present);

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                for (int i = 0; i < studentid_previous.size(); i++) {
                    present.add(studentid_previous.get(i) + "");
                }
                Log.e("present:", "list" + present);
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

                // callRetrofitUpdateProfile(AccessTokenValue);
                final API_interface api = Api_client.getClient().create(API_interface.class);
                Call<response_attendence_model> call = api.update_attendence(AccessTokenValue);
                call.enqueue(new retrofit2.Callback<response_attendence_model>() {

                    @Override
                    public void onResponse(Call<response_attendence_model> call, Response<response_attendence_model> response) {
                        if (response.isSuccess()) {

                            //Toast.makeText(getApplicationContext(), response.body().getMsg() + "", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Attendence status updated" + "", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), home_activity.class);
                            startActivity(intent);

                        } else {
                            dialog.dismiss();

                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<response_attendence_model> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "check your network connection", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                });


            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), Timing.class);
        startActivity(intent);
    }

    public class customgridview extends BaseAdapter {


        public ArrayList studentid, student_name, attendence_data, studentid_previous;
        int mSelectedItem;


        public customgridview(ArrayList studentid, ArrayList student_name) {

            this.studentid = studentid;
            this.student_name = student_name;
        }

        public customgridview(ArrayList studentid, ArrayList student_name, ArrayList attendence_data, ArrayList studentid_previous) {

            this.studentid = studentid;
            this.student_name = student_name;
            this.attendence_data = attendence_data;
            Log.e("attendence_data", "attendence_data" + attendence_data);
            this.studentid_previous = studentid_previous;
            Log.e("studentid_previous", "studentid_previous" + studentid_previous);

        }


        @Override
        public int getCount() {
            return studentid.size();
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
            Log.e("student_id", "student" + studentid.get(position));

            for (int i = 0; i < attendence_data.size(); i++) {
                Log.e("studentid_previous", "studentid_previous" + studentid_previous.get(i) + "//" + studentid.get(position));
                if ((studentid_previous.get(i).equals(studentid.get(position)))) {
                    holder.l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_selected));
                } else {
                    //holder.l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_attendence));
                }
            }
//            String id=studentid_previous.get(position).toString();
//            String attendence=attendence_data.get(position).toString();
//            if((studentid_previous.contains(studentid.get(position).toString()))&&(attendence.equals("false"))){
//                holder.l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_selected));
//            }
//            else {
//                holder.l.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.shape_attendence));
//            }


            return rowView;
        }

        public class Holder {
            TextView tv_name, tv_roll;
            LinearLayout l;

        }
    }
}
