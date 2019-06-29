package com.schoolmanapp.ktct_school.school.school;

import android.app.Application;
import android.content.Context;

import com.schoolmanapp.ktct_school.school.school.component.ActivityComponent;
import com.schoolmanapp.ktct_school.school.school.component.AppComponent;

import com.schoolmanapp.ktct_school.school.school.component.DaggerActivityComponent;
import com.schoolmanapp.ktct_school.school.school.component.DaggerAppComponent;
import com.schoolmanapp.ktct_school.school.school.module.ApplicationModule;

/**
 * Created by srishtiinnovative on 08/06/17.
 */

public class peekaboApplication extends Application {
    private AppComponent mAppComponent;
    private ActivityComponent activityComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(mAppComponent).build();
       /* CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/olivier_demo.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());*/
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

}

