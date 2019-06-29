package com.schoolmanapp.ktct_school.school.school.common;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.schoolmanapp.ktct_school.school.school.peekaboApplication;

import javax.inject.Inject;


/**
 * Created by srishtiinnovative on 28/12/15.
 */
public class BaseActivity extends AppCompatActivity {
    @Inject
    Utils utilsPref;

    //@Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    public peekaboApplication getApp() {
        return (peekaboApplication) getApplicationContext();
    }

    public void addFragment(int fragment_container, Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(fragment_container, fragment);
        transaction.addToBackStack(addBackStack ? fragment.getClass().getName() : null);
        transaction.commit();
    }

    public void replaceFragment(int fragment_container, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(fragment_container, fragment);
        transaction.commit();
    }
}
