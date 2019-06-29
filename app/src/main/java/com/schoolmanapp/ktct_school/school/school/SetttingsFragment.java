package com.schoolmanapp.ktct_school.school.school;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 01/06/17.
 */

public class SetttingsFragment extends BaseActivity {
    View view;
    TextView tv_logout;
    ImageView tv_un, tv_up;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        ButterKnife.bind(this);
//        Home h = (Home) getApplicationContext();
//        h.getApp().getActivityComponent().inject(this);
//        ButterKnife.bind(getApplicationContext(), view);
        getApp().getActivityComponent().inject(this);
        tv_logout = (TextView) findViewById(R.id.logout);
        tv_un = (ImageView) findViewById(R.id.txt_chg_usrnam);
        tv_up = (ImageView) findViewById(R.id.txt_chg_pass);
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SetttingsFragment.this);
                dialog.setContentView(R.layout.confirm_logout);
                Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
                Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
                dialog.setTitle("Do you want to Logout?");

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
                        appPreferences.saveDataBoolean("isLogin", false);
                        Intent i = new Intent(SetttingsFragment.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                });

            }
        });
        tv_un.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetttingsFragment.this, Change_username.class);
                startActivity(intent);
                finish();
//              loadFragment2(new Change_username());
                //  utils.toToast("Hi");
            }
        });

        tv_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetttingsFragment.this, Change_password.class);
                startActivity(intent);
                finish();
//                loadFragment2(new Change_password());
                //  utils.toToast("Hi");
            }
        });

        Home.isInHomePage = false;
    }

    private void loadFragment2(Fragment fragment) {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_lay, fragment);
        fragmentTransaction.commit();
    }
}