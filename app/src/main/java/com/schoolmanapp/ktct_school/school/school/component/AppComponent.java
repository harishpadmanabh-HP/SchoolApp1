package com.schoolmanapp.ktct_school.school.school.component;

import android.content.Context;

import com.schoolmanapp.ktct_school.school.school.module.ApplicationModule;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by sics on 1/4/2016.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface AppComponent {
    Context appContext();

    Utils appUtilsPref();

   AppPreferences appPref();


}
