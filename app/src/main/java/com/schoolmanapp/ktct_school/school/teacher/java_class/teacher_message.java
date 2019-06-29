package com.schoolmanapp.ktct_school.school.teacher.java_class;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.Java_class.activity_fees;
import com.schoolmanapp.ktct_school.school.parent.Java_class.outstanding;
import com.schoolmanapp.ktct_school.school.parent.Java_class.paid_history;
import com.schoolmanapp.ktct_school.school.teacher.Api_client.Api_client;
import com.schoolmanapp.ktct_school.school.teacher.Api_interface.API_interface;
import com.schoolmanapp.ktct_school.school.teacher.model_class.teachermessage_model;
import com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cc.cloudist.acplibrary.ACProgressFlower;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 08/02/18.
 */

public class teacher_message  extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    public ViewPager viewPager;
    public TabLayout tabLayout;

//    @Bind(R.id.lists)
//    ListView list;

//    @Bind(R.id.largeimage_teacher)
//    ImageView image;
    @Bind(R.id.message_kidname_tv_teacher)
    TextView kidname;
    @Bind(R.id.student_images_tacher)
    ImageView student_image;
    @Bind(R.id.message_class_tv_teacher)
    TextView tv_cls;
    @Bind(R.id.message_div_tv_teacher)
    TextView tv_dvi;

String cls,div;
    int[] a = {0, 1, 0, 0, 0};
    AppPreferences appPreferences;
    message_Adapter adapter;
    List<teachermessage_model.UserDataBean> message_list;
    boolean onsrolled = false;
   // public static refreshlistener listener;
    android.app.AlertDialog dialog_alert;
    int student_id,teacher_id;
    String divid,kid,file_path;
    ACProgressFlower dialog;
    ProgressDialog progressDialog;
    private FloatingActionButton fab;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_message);
        ButterKnife.bind(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        progressDialog = new ProgressDialog(teacher_message.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        setupTabIcons();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        appPreferences = AppPreferences.getInstance(teacher_message.this, getResources().getString(R.string.app_name));
        dialog_alert = new SpotsDialog(this);
        message_list = new ArrayList<>();
        teacher_id = appPreferences.getInt("teacher_id");
        student_id = appPreferences.getInt("student_id");
        divid = appPreferences.getData("divisionid");
        kid = appPreferences.getData("student_name");
        appPreferences.saveData("kid_name",kid);
        cls=appPreferences.getData("class_name");
        div=appPreferences.getData("division_name");
        tv_cls.setText(cls);
        tv_dvi.setText(div);
        file_path = appPreferences.getData("file_path");
        kidname.setText(kid);
        if(file_path.equals("null"))
        {
            Glide.with(teacher_message.this).load(R.drawable.dummy).into(student_image);
        }else if(file_path.equals("")){
       Glide.with(teacher_message.this).load(R.drawable.dummy).into(student_image);
        } else{
            String path = "http://www.schoolman.in//" + file_path;
            Glide.with(getApplicationContext()).load(path).into(student_image);}
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(teacher_message.this, student_message.class);
//                intent.putExtra("kidid",kidid);
//                intent.putExtra("file",path);
//                intent.putExtra("name",name);
                startActivity(intent);
                finish();
            }
        });
     //   listener = this;
//        processapi(0);
//        onscroll(0);
    }
    public void setupTabIcons() {

        tabLayout.addTab(tabLayout.newTab().setText("Recevie"));
        tabLayout.addTab(tabLayout.newTab().setText("Send"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


    }

    public void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(this.getSupportFragmentManager());
        adapter.addFragment(new receive_message(), "Recevie");
        adapter.addFragment(new send_message(), "Send");



        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);


        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPager.setCurrentItem(tab.getPosition());
        // title.setText(tab.getText());


    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    static class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }



//    void processapi(final int index) {
//      //  dialog_alert.show();
//      //  progressDialog.show();
//        final API_interface api = Api_client.getClient().create(API_interface.class);
//        Call<teachermessage_model> call = api.message_list(teacher_id+"",divid,index+"","10",student_id+"");
//        call.enqueue(new retrofit2.Callback<teachermessage_model>() {
//
//            @Override
//            public void onResponse(Call<teachermessage_model> call, Response<teachermessage_model> response) {
//                if (response.isSuccess()) {
//                    if (response.body().isStatus()) {
//                       // progressDialog.dismiss();
//
//                      //  Picasso.with(getApplicationContext()).load(path).into(image);
//                        kidname.setText(kid);
//                        if (response.body().getMsg().equals("Success")) {
//                            //dialog_alert.dismiss();
//                         //   dialog.dismiss();
//                            message_list.addAll(response.body().getUserData());
//
//                            if (onsrolled == false) {
//
//                                adapter = new message_Adapter(getApplicationContext(), message_list);
//                                list.setAdapter(adapter);
//                                onsrolled = true;
//
//                            } else {
//                                adapter.notifyDataSetChanged();
//                            }
//
//
//                        }
//                        else {
//                          //  Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                        }
//                      //  dialog.dismiss();
//                        //dialog_alert.dismiss();
//                    } else
//
//                    {  //Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                       // dialog.dismiss();
//                        //dialog_alert.dismiss();
//                        if (message_list.size() > 0) {
//                            adapter.notifyDataSetChanged();
//                        } else {
//                           // dialog.dismiss();
//                         //   dialog_alert.dismiss();
//
//                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                        }
//
//                        // dialog_alert.dismiss();
//                    }
//                } else {
//                   // dialog.dismiss();
//                  //  dialog_alert.dismiss();
//                    // dialog_alert.dismiss();
//
//                     Toast.makeText(getApplicationContext(), "server error.Please check your network", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<teachermessage_model> call, Throwable t) {
//              //  dialog_alert.dismiss();
//                //progressDialog.dismiss();
//              Toast.makeText(getApplicationContext(), "server error.Please check your network", Toast.LENGTH_SHORT).show();
//                Log.e("ERROR!!", t.getMessage());
//
//            }
//
//        });
//
//    }


//    private void onscroll(final int i) {
//
////        list.setOnScrollListener(new AbsListView.OnScrollListener() {
//
//            private int currentVisibleItemCount;
//            private int currentScrollState;
//            private int currentFirstVisibleItem;
//            private int totalItem;
//            private LinearLayout lBelow;
//            int pageindex = i;
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                this.currentScrollState = scrollState;
//                //this.isScrollCompleted();
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                this.currentFirstVisibleItem = firstVisibleItem;
//                this.currentVisibleItemCount = visibleItemCount;
//                this.totalItem = totalItemCount;
//                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
//
//
//                    pageindex = pageindex + 1;
//                    int pos = pageindex;
//
////                    processapi(pos);/** To do code here*/
//
//
//                } else if (firstVisibleItem + visibleItemCount == 0) {
//                    // Toast.makeText(getActivity(), "end of it", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//
//
//        });


    }

//    @Override
//    public void refresh() {
//
//        int c = list.getCount();
//        list_jobs.clear();
//        if (c == 1) {
//
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    list.setAdapter(null);
//                    processapi(0);
//
//                }
//            });
//
//        } else {
//            processapi(0);
//
//        }
//
//    }
//
//    @Override
//    public void refresh(String name) {
//
//    }
//
//    @Override
//    public void refresh(String name, String email, String address, String image) {
//
//    }
//
//    @Override
//    public void refresh(String name, String image) {
//
//    }
//
//    @Override
//    public void refresh(login_model.UserDataBean object) {
//
//    }


