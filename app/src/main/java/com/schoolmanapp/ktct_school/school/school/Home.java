package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 31/05/17.
 */
import android.Manifest;
import android.widget.Toast;

public class Home extends BaseActivity {
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    public static boolean isInHomePage = true;
    public static boolean isAddButtonPressed = false;
    public static boolean isclassdiv = false;
    // Fragment TabHost as mTabHost
    public FragmentTabHost mTabHost;
    ImageView iv_home, iv_loc, iv_history, iv_profile, iv_sms, setting;
    LinearLayout top_ll, bottom_ll;
    RelativeLayout ll_tabbot;
    RelativeLayout rl_homeimg, rl_locimg, rl_hisimg, rl_pimg, rl_sms;
    ConstraintLayout ll_head;
    String[] permissionsRequired = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    View tabview, tabview1, tabview2, tabview3, tabview4;
    int f = 0;
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10;
    @Inject
    Utils utils;
    private SharedPreferences permissionStatus;
    private boolean sentToSettings = false;

    private static View createTabView(final Context context, final String text) {

        View view = LayoutInflater.from(context).inflate(R.layout.tab_bg, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);

        tv.setText(text);

        return view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        permissionStatus = getSharedPreferences("permissionStatus", MODE_PRIVATE);
        permisoncheck();
        iv1 = (ImageView) findViewById(R.id.tab_home);
        iv2 = (ImageView) findViewById(R.id.tab_home_sel);
        iv3 = (ImageView) findViewById(R.id.tab_location);
        iv4 = (ImageView) findViewById(R.id.tab_loc_sel);
        iv5 = (ImageView) findViewById(R.id.tab_history);
        iv6 = (ImageView) findViewById(R.id.tab_history_sel);
        iv7 = (ImageView) findViewById(R.id.tab_profile);
        iv8 = (ImageView) findViewById(R.id.tab_profile_sel);
        iv9 = (ImageView) findViewById(R.id.tab_sms);
        iv10= (ImageView) findViewById(R.id.tab_set_sms);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        top_ll = (LinearLayout) findViewById(R.id.rl_home);
        bottom_ll = (LinearLayout) findViewById(R.id.tab2lay);
        ll_head = (ConstraintLayout) findViewById(R.id.head);
        ll_tabbot = (RelativeLayout) findViewById(R.id.under);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        setting = (ImageView) findViewById(R.id.head_settings);
        rl_homeimg = (RelativeLayout) findViewById(R.id.ll_home);
        rl_locimg = (RelativeLayout) findViewById(R.id.ll_loc);
        rl_hisimg = (RelativeLayout) findViewById(R.id.ll_history);
        rl_pimg = (RelativeLayout) findViewById(R.id.ll_profile);
        rl_sms = (RelativeLayout) findViewById(R.id.ll_sms);
        iv_home = (ImageView) findViewById(R.id.tab_home);
        iv_loc = (ImageView) findViewById(R.id.tab_location);
        iv_history = (ImageView) findViewById(R.id.tab_history);
        iv_profile = (ImageView) findViewById(R.id.tab_profile);
        iv_sms = (ImageView) findViewById(R.id.tab_sms);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetttingsFragment.class);
                startActivity(intent);
            }
        });
        top_ll.setVisibility(View.VISIBLE);
        keyBoardListner(getWindow().getDecorView().getRootView());
        isclssdivfunc();
        rl_homeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv1.setVisibility(v.GONE);
                iv2.setVisibility(v.VISIBLE);
                iv3.setVisibility(v.VISIBLE);
                iv4.setVisibility(v.GONE);
                iv5.setVisibility(v.VISIBLE);
                iv6.setVisibility(v.GONE);
                iv7.setVisibility(v.VISIBLE);
                iv8.setVisibility(v.GONE);
                iv9.setVisibility(v.VISIBLE);
                iv10.setVisibility(v.GONE);
                top_ll.setVisibility(v.VISIBLE);
                bottom_ll.setVisibility(v.GONE);
                loadFragment1(new HomeFragment());


            }
        });
        rl_locimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv1.setVisibility(v.VISIBLE);
                iv2.setVisibility(v.GONE);
                iv3.setVisibility(v.GONE);
                iv4.setVisibility(v.VISIBLE);
                iv5.setVisibility(v.VISIBLE);
                iv6.setVisibility(v.GONE);
                iv7.setVisibility(v.VISIBLE);
                iv8.setVisibility(v.GONE);
                iv9.setVisibility(v.VISIBLE);
                iv10.setVisibility(v.GONE);
                top_ll.setVisibility(v.GONE);
                bottom_ll.setVisibility(v.VISIBLE);
                loadFragment2(new LocationFragment());
            }
        });
        rl_hisimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv1.setVisibility(v.VISIBLE);
                iv2.setVisibility(v.GONE);
                iv3.setVisibility(v.VISIBLE);
                iv4.setVisibility(v.GONE);
                iv5.setVisibility(v.GONE);
                iv6.setVisibility(v.VISIBLE);
                iv7.setVisibility(v.VISIBLE);
                iv8.setVisibility(v.GONE);
                iv9.setVisibility(v.VISIBLE);
                iv10.setVisibility(v.GONE);
                top_ll.setVisibility(v.GONE);
                bottom_ll.setVisibility(v.VISIBLE);
                loadFragment3(new HistoryFragment());
            }
        });
        rl_pimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv1.setVisibility(v.VISIBLE);
                iv2.setVisibility(v.GONE);
                iv3.setVisibility(v.VISIBLE);
                iv4.setVisibility(v.GONE);
                iv5.setVisibility(v.VISIBLE);
                iv6.setVisibility(v.GONE);
                iv7.setVisibility(v.GONE);
                iv8.setVisibility(v.VISIBLE);
                iv9.setVisibility(v.VISIBLE);
                iv10.setVisibility(v.GONE);
                top_ll.setVisibility(v.GONE);
                bottom_ll.setVisibility(v.VISIBLE);
                loadFragment4(new ProfileFragment());


            }
        });
        rl_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                iv1.setVisibility(v.VISIBLE);
//                iv2.setVisibility(v.GONE);
//                iv3.setVisibility(v.VISIBLE);
//                iv4.setVisibility(v.GONE);
//                iv5.setVisibility(v.VISIBLE);
//                iv6.setVisibility(v.GONE);
//                iv7.setVisibility(v.VISIBLE);
//                iv8.setVisibility(v.GONE);
//                iv9.setVisibility(v.GONE);
//                iv10.setVisibility(v.VISIBLE);
//                top_ll.setVisibility(v.GONE);
//                bottom_ll.setVisibility(v.VISIBLE);
////              loadFragment5(new SetttingsFragment());
                Intent intent = new Intent(getApplicationContext(), Sms_school.class);
                startActivity(intent);

            }
        });

        tabview = createTabView(mTabHost.getContext(), "Class");

        mTabHost.addTab(mTabHost.newTabSpec("Class").setIndicator(tabview), Classdiv.class, null);

        tabview1 = createTabView(mTabHost.getContext(), "Teacher");

        mTabHost.addTab(mTabHost.newTabSpec("Teacher").setIndicator(tabview1), Teacher.class, null);
        tabview2 = createTabView(mTabHost.getContext(), "Bus");
        mTabHost.addTab(mTabHost.newTabSpec("Bus").setIndicator(tabview2),
                Bus.class, null);
        tabview3 = createTabView(mTabHost.getContext(), "Student");
        mTabHost.addTab(mTabHost.newTabSpec("Student").setIndicator(tabview3),
                Displayclass.class, null);
        tabview4 = createTabView(mTabHost.getContext(), "Driver");
        mTabHost.addTab(mTabHost.newTabSpec("Driver").setIndicator(tabview4),
                Driver.class, null);


    }

    public void isclssdivfunc() {
        if (isclassdiv) {
            mTabHost.setCurrentTab(3);
        }
    }

    private void loadFragment2(LocationFragment fragment) {

        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_lay, fragment);
        fragmentTransaction.commit();
    }

    private void loadFragment1(HomeFragment fragment) {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_lay, fragment);
        fragmentTransaction.commit();
    }

    private void loadFragment3(HistoryFragment fragment) {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_lay, fragment);
        fragmentTransaction.commit();
    }

//    private void loadFragment5(SetttingsFragment fragment) {
//        android.app.FragmentManager fm = getFragmentManager();
//        android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_lay, fragment);
//        fragmentTransaction.commit();
//    }

    private void loadFragment4(ProfileFragment fragment) {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_lay, fragment);
        fragmentTransaction.commit();
    }

    @Override

    public void onBackPressed() {

        if (isInHomePage) {

        } else {

            isInHomePage = true;
            if (isAddButtonPressed) {
                isAddButtonPressed = false;
                Classdiv.deleteInterface.onBackPressed();
            }
            iv1.setVisibility(View.GONE);
            iv2.setVisibility(View.VISIBLE);
            iv3.setVisibility(View.VISIBLE);
            iv4.setVisibility(View.GONE);
            iv5.setVisibility(View.VISIBLE);
            iv6.setVisibility(View.GONE);
            iv7.setVisibility(View.VISIBLE);
            iv8.setVisibility(View.GONE);
            iv9.setVisibility(View.VISIBLE);
            iv10.setVisibility(View.GONE);
            top_ll.setVisibility(View.VISIBLE);
            bottom_ll.setVisibility(View.GONE);
            mTabHost.setCurrentTab(0);
        }
    }

    private void keyBoardListner(final View contentView) {
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                contentView.getWindowVisibleDisplayFrame(r);
                int screenHeight = contentView.getRootView().getHeight();
                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;
                Log.d("HAHAHA", "keypadHeight = " + keypadHeight);
                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                    // keyboard is opened
                    ll_tabbot.setVisibility(View.GONE);
                } else {
                    // keyboard is closed
                    ll_tabbot.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void permisoncheck() {
        if (ActivityCompat.checkSelfPermission(Home.this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(Home.this, permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(Home.this, permissionsRequired[2]) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this, permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Home.this, permissionsRequired[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Home.this, permissionsRequired[2])) {
                //Show Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Contacts permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(Home.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else if (permissionStatus.getBoolean(permissionsRequired[0], false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Contacts permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sentToSettings = true;
                        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Toast.makeText(getBaseContext(), "Go to Permissions to Grant  Camera and Contacts", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                //just request the permission
                ActivityCompat.requestPermissions(Home.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
            }

            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(permissionsRequired[0], true);
            editor.commit();
        } else {
            //You already have the permission, just go ahead.
            proceedAfterPermission();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CALLBACK_CONSTANT) {
            //check if all permissions are granted
            boolean allgranted = false;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    allgranted = true;
                } else {
                    allgranted = false;
                    break;
                }
            }

            if (allgranted) {
                proceedAfterPermission();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this, permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Home.this, permissionsRequired[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Home.this, permissionsRequired[2])) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Location permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(Home.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                Toast.makeText(getBaseContext(), "Unable to get Permission", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void proceedAfterPermission() {
        //   Toast.makeText(getBaseContext(), "We got All Permissions", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (sentToSettings) {
            if (ActivityCompat.checkSelfPermission(Home.this, permissionsRequired[0]) == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                proceedAfterPermission();
            }
        }
    }

}

