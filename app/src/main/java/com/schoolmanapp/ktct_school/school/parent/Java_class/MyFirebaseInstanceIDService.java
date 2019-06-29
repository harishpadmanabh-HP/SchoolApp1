package com.schoolmanapp.ktct_school.school.parent.Java_class;

/*
 * Created by sics on 1/24/2017.
 */

import android.util.Log;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


//Class extending FirebaseInstanceIdService
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    AppPreferences appPreferences;

    @Override
    public void onTokenRefresh() {

        Log.d("inTocken", "ontocvdskv");
        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Displaying token on logcat
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        appPreferences.saveData("refreshedToken",refreshedToken);
        System.out.println("refreshedToken" + refreshedToken);
        Log.e(TAG, "Refreshed token: " + refreshedToken);

    }

    private void sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }
}
