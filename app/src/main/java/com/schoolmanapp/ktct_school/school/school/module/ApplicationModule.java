package com.schoolmanapp.ktct_school.school.school.module;

import android.content.Context;


import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.schoolmanapp.ktct_school.school.school.peekaboApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by sics on 1/4/2016.
 */

@Module
public class ApplicationModule {

    private final peekaboApplication mApp;
    private String PreferenceName="Peekabo";

    public ApplicationModule(peekaboApplication app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public Context appContext() {
        return mApp;
    }

    @Provides
    @Singleton
    public Utils appUtilsPref() {
        return new Utils(mApp.getActivityComponent());
    }

    @Provides
    @Singleton
    public AppPreferences appPref() {
        return new AppPreferences(mApp.getApplicationContext(),PreferenceName);
    }


}


