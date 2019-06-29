package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.parent.API_interface.API_interface;
import com.schoolmanapp.ktct_school.school.parent.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.model_class.message_model;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_adapter;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 31/05/17.
 */

public class message_activity extends AppCompatActivity {


    private List<model_adapter> modelhomes;
    private CardViewDataAdapter_message contactAdapter;
    private Random random;
    int school_id, class_id;
    String kidid,name;
    String path;
    int pos = 0;
    AppPreferences appPreferences;
    final int length = 10;
    RecyclerView recyclerView;
    AlertDialog dialog;
    List<message_model.UserDataBean> student_details;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_parent);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        appPreferences.getInt("kid_id");
        school_id = appPreferences.getInt("school_id");
        class_id = appPreferences.getInt("class_id");
        //Log.e("kidid", " :" + kidid);
        dialog = new SpotsDialog(this);
        Log.e("pos", " :" + pos);
        Log.e("school_id", " :" + school_id);
        Log.e("school_id", " :" + school_id);
        Log.e("class_id", " :" + class_id);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);


//
//        kidid = getIntent().getStringExtra("kididsss");
//        path = getIntent().getStringExtra("file");
//        name=getIntent().getStringExtra("name");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(message_activity.this, message_tosend.class);
//                intent.putExtra("kidid",kidid);
//                intent.putExtra("file",path);
//                intent.putExtra("name",name);
                startActivity(intent);
                finish();
            }
        });

//        contacts = new ArrayList<>();
        modelhomes = new ArrayList<>();
        random = new Random();
        try {
            dialog.show();
            final API_interface api = Api_client.getClient().create(API_interface.class);
            Call<message_model> call = api.messageModelCall(appPreferences.getInt("kid_id"), pos, length, school_id, class_id);
            Log.e("response:", "");
            call.enqueue(new retrofit2.Callback<message_model>() {

                @Override
                public void onResponse(Call<message_model> call, Response<message_model> response) {
                    Log.e("response:", response.body().getMsg() + "");
                    if (response.isSuccess()) {
                        Log.e("response:", response.body().getMsg() + "");
                        if (response.body().getMsg().equals("Success")) {

                            student_details = response.body().getUserData();
                            Log.e("response", " :" + student_details);

                            for (int i = 0; i < student_details.size(); i++) {


                                String subject = student_details.get(i).getSubject();


                                String description = student_details.get(i).getDescription();

                                String filepath = student_details.get(i).getFilepath();
                                String time = student_details.get(i).getTimestamp();

                                model_adapter s = new model_adapter();
                                s.setsubject(subject);
                                s.setdescription(description);
                                s.setfilepath(filepath);
                                s.settime(time);
                                modelhomes.add(s);


                            }


                            recyclerView.setHasFixedSize(true);

                            recyclerView.setLayoutManager(new LinearLayoutManager(message_activity.this));
                            contactAdapter = new CardViewDataAdapter_message(recyclerView, modelhomes, message_activity.this);
                            recyclerView.setAdapter(contactAdapter);

                            contactAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                                @Override
                                public void onLoadMore() {

                                    if (modelhomes.size() > 0) {

                                        modelhomes.add(null);
                                        contactAdapter.notifyItemInserted(modelhomes.size() - 1);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {

                                                modelhomes.remove(modelhomes.size() - 1);
                                                Log.e("modelhomes.size() - 1", " :" + modelhomes);
                                                contactAdapter.notifyItemRemoved(modelhomes.size());
                                                Log.e("modelhomes.size()", " :" + modelhomes.size());
                                                //Generating more data
                                                int position = pos + 1;
                                                pos = position;
                                                Log.e("posw", " :" + position);
                                                final int end = modelhomes.size() + 10;
                                                Log.e("end", " :" + end);
                                                final API_interface api = Api_client.getClient().create(API_interface.class);
                                                Call<message_model> call = api.messageModelCall(appPreferences.getInt("kid_id"), pos, length, school_id, class_id);
                                                call.enqueue(new retrofit2.Callback<message_model>() {

                                                    @Override
                                                    public void onResponse(Call<message_model> call, Response<message_model> response) {
                                                        if (response.isSuccess()) {
                                                            Log.e("response:", response.body().getMsg() + "");

                                                            if (response.body().getMsg().equals("Success")) {
                                                                List<message_model.UserDataBean> student_details = response.body().getUserData();
                                                                Log.e("response", " :" + student_details);

                                                                for (int i = 0; i < student_details.size(); i++) {


                                                                    String subject = student_details.get(i).getSubject();


                                                                    String description = student_details.get(i).getDescription();

                                                                    String filepath = student_details.get(i).getFilepath();
                                                                    model_adapter s = new model_adapter();
                                                                    s.setsubject(subject);
                                                                    s.setdescription(description);
                                                                    s.setfilepath(filepath);
                                                                    modelhomes.add(s);


                                                                }
                                                                contactAdapter.notifyDataSetChanged();
                                                                contactAdapter.setLoaded();
                                                            }

                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<message_model> call, Throwable t) {

                                                        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                                                    }

                                                });

//
                                            }
                                        }, 5000);
                                    } else {
                                        Toast.makeText(message_activity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


//
                            dialog.dismiss();

                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show()
                            ;
                            dialog.dismiss();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<message_model> call, Throwable t) {
                    dialog.dismiss();
                    Log.e("response:", t.getMessage());
                    t.printStackTrace();
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                }

            });
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "No Value", Toast.LENGTH_SHORT).show();
        }

//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new ClickListener() {
//            @Override
//            public void onClick(View view, final int position) {
//                appPreferences.saveData("description",student_details.get(pos).getDescription());
//                appPreferences.saveData("subject",student_details.get(pos).getSubject());
//                appPreferences.saveData("filepath",student_details.get(pos).getFilepath());
//                if(!student_details.get(position).getFilepath().equals("")){
//                Intent intent= new Intent(message_activity.this,student_message.class);
//               startActivity(intent);}
//                else {
//                    Intent intent= new Intent(message_activity.this,no_webview.class);
//                    startActivity(intent);}
//
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//                Toast.makeText(message_activity.this, "Long press on position :"+position,
//                        Toast.LENGTH_LONG).show();
//            }
//        }));
    }


//    @Override
//    public void onBackPressed() {
////        Intent intent = new Intent(message_activity.this, student_activity.class);
////        startActivity(intent);
//
//    }

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

    public void onBackPressed() {
        finish();
        // your code.
    }
}