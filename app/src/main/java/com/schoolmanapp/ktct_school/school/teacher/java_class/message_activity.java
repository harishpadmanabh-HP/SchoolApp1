package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.teacher.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.teacher.Api_interface.API_interface;
import com.schoolmanapp.ktct_school.school.R;
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
 * Created by srishtiinnovative on 31/05/17.
 */

public class message_activity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<model_message> studentList;
    List<student_list_model.UserDataBean> student_details;
    ArrayList rollno;
    AppPreferences appPreferences;

    int teacher_id, class_id, division_id;

    //    @Bind(R.id.toolbar)
//    Toolbar toolbar;
//
    @Bind(R.id.message_to_all)
    LinearLayout message_to_all;


    @Bind(R.id.checkBox)
    CheckBox checkBox;

    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_newmessage);
        ButterKnife.bind(this);
        dialog = new SpotsDialog(this);

        studentList = new ArrayList<model_message>();
        rollno = new ArrayList();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        teacher_id = appPreferences.getInt("teacher_id");
        class_id = appPreferences.getInt("class_id");
        division_id = appPreferences.getInt("division_id");
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


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
                        Log.e("RESPONSE:", response.body().getMsg());
                        if (response.body().getMsg().equals("Success")) {
                            student_details = response.body().getUserData();
                            Log.e("response", " :" + student_details);

                            for (int i = 0; i < student_details.size(); i++) {


                                int id = student_details.get(i).getStudentId();
                                rollno.add(id);
                                String name = student_details.get(i).getStundentName();
                                String file_path = student_details.get(i).getFilePath();
                                model_message s = new model_message(name, file_path, id, false, student_details.get(i).getDivisionId()
                                        + "");
                                Log.e("response", " name:" + name);
                                studentList.add(s);


                            }
                            Log.e("student_list", "student" + studentList);
//                            if (toolbar != null) {
//                                setSupportActionBar(toolbar);
//                                getSupportActionBar().setTitle("Android Students");
//
//                            }


                            // use this setting to improve performance if you know that changes
                            // in content do not change the layout size of the RecyclerView
                            mRecyclerView.setHasFixedSize(true);

                            // use a linear layout manager
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                            // create an Object for Adapter
                            mAdapter = new CardViewDataAdapter(message_activity.this, studentList);

                            // set the adapter object to the Recyclerview
                            mRecyclerView.setAdapter(mAdapter);
                            dialog.dismiss();
//
//                            mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new ClickListener() {
//                                @Override
//                                public void onClick(View view, final int position) {
//                                    //Values are passing to activity & to fragment as well
//                                    Toast.makeText(message_activity.this, "Single Click on position        :"+position,
//                                            Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onLongClick(View view, int position) {
//                                    Toast.makeText(message_activity.this, "Long press on position :"+position,
//                                            Toast.LENGTH_LONG).show();
//                                }
//                            }));


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

                    Toast.makeText(getApplicationContext(), "check your network connection", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            });
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "No Value", Toast.LENGTH_SHORT).show();
        }

//        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new ClickListener() {
//            @Override
//            public void onClick(View view, final int position) {
//
//                String student_name = student_details.get(position).getStundentName();
//                appPreferences.saveData("student_name", student_name);
//                String file = student_details.get(position).getFilePath();
//                Log.e("filepath@@@@", file);
//                appPreferences.saveData("file_path", file);
//                int student_id = student_details.get(position).getStudentId();
//                appPreferences.saveInt("student_id", student_id);
//                dialog.show();
//                Intent intent = new Intent(getApplicationContext(), student_message.class);
//                startActivity(intent);  //Values are passing to activity & to fragment as well
//
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//                Toast.makeText(message_activity.this, "Long press on position :" + position,
//                        Toast.LENGTH_LONG).show();
//            }
//        }));


        message_to_all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (checkBox.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), student_messagetoall.class);
                    startActivity(intent);
                    intent.putParcelableArrayListExtra("id",rollno);
                    startActivity(intent);
                } else {
                    Toast.makeText(message_activity.this, "Select the checkbox to continue",
                            Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), home_activity.class);
        startActivity(intent);
        finish();
    }


    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}