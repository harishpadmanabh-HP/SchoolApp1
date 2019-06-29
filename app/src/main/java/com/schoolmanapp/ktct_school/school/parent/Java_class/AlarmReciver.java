package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // We're creating a new intent that's going to start the MainActivity
        Intent in = new Intent(context, WakeUp.class);
        // This boolean just makes it easier to check if the Activity has been started from
        // this class
        in.putExtra("lock", true);
        // You need to add this to your intent if you want to start an Activity fromm a class
        // that is not an Activity itself
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Now we just start the Activity
        context.startActivity(in);



    }
}