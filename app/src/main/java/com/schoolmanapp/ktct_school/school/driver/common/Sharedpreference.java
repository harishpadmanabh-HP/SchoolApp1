package com.schoolmanapp.ktct_school.school.driver.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;

/**
 * Created by srishtiinnovative on 27/06/17.
 */

public class Sharedpreference {
    private static AppPreferences instance;
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;


    public Sharedpreference(Context context, String Preferncename) {
        this.appSharedPrefs = context.getSharedPreferences(Preferncename, Activity.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }



    public String getData(String key) {
        return appSharedPrefs.getString(key, "");
    }


    public String getData(String key, String defValue) {
        return appSharedPrefs.getString(key, defValue);
    }

    public int getInt(String key) {
        return appSharedPrefs.getInt(key, 0);
    }

    public int getInt(String key, int def) {
        return appSharedPrefs.getInt(key, def);
    }

    public boolean getDataBoolean(String key) {
        return appSharedPrefs.getBoolean(key, false);
    }

    public boolean getDataBoolean(String key, Boolean def) {
        return appSharedPrefs.getBoolean(key, def);
    }

    public Boolean contains(String Tag) {
        boolean aa = appSharedPrefs.contains(Tag);
        prefsEditor.commit();
        return aa;
    }

    public void saveData(String Tag, String text) {
        prefsEditor.putString(Tag, text);
        prefsEditor.commit();
    }

    public void saveInt(String Tag, int text) {
        prefsEditor.putInt(Tag, text);
        prefsEditor.commit();
    }

    public void clearData() {
        prefsEditor.clear();
        prefsEditor.commit();
    }

    public void removeKey(String Tag) {
        prefsEditor.remove(Tag);
        prefsEditor.commit();
    }

    public void saveDataBoolean(String Tag, Boolean text) {
        prefsEditor.putBoolean(Tag, text);
        prefsEditor.commit();
    }

}
