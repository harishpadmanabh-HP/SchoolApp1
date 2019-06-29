package com.schoolmanapp.ktct_school.school.parent.Java_class;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.schoolmanapp.ktct_school.school.school.FileDisplay;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    AppPreferences appPreferences;
    PendingIntent pendingIntent;
    Intent intent;
    private static final String TAG = "MyFirebaseMsgService";
    int flag;
    String sclid,html;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

        Log.e(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        Log.e(TAG, "" + remoteMessage.getData());


//        try{
//
//            JSONObject response = new JSONObject(remoteMessage.getData());
//            String role= response.getString("Role");
//            String type=response.getString("Function");
//            if(role.equals("Teacher") && type.equals("Message")) {
//
//                String classid = response.getString("ClassId");
//                String schoolid = response.getString("SchoolId");
//                String kidid = response.getString("KidId");
//                int cid = Integer.parseInt(classid);
//                int sid = Integer.parseInt(schoolid);
//                int kid = Integer.parseInt(kidid);
//                flag = 0;
//
//                appPreferences.saveInt("class_id", cid);
//                appPreferences.saveInt("school_id", sid);
//                appPreferences.saveInt("kid_id", kid);
//            }
//            else
//                if(role.equals("Teacher") && type.equals("AttendanceData"))
//
//                {
//                    flag=1;
//                    String kidid = response.getString("KidId");
//                    appPreferences.saveInt("kid_id",Integer.parseInt(kidid));
//                }
//                else
//                if(role.equals("Driver") && type.equals("Bus Start"))
//                {
//                    String lat = response.getString("Latitude");
//                    String did = response.getString("DriverId");
//                    String bid = response.getString("BusId");
//                    String kid = response.getString("KidId");
//                    String longt = response.getString("Longitude");
//                    appPreferences.saveData("latitude", lat);
//                    appPreferences.saveInt("busid", Integer.parseInt(bid));
//                    appPreferences.saveInt("kid_id", Integer.parseInt(kid));
//                    appPreferences.saveData("longitude", longt);
//                    flag=2;
//                }
//
//        } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//








       // sendNotification(remoteMessage.getNotification().getBody());
    }
    //This method is only generating push notification
    //It is same as we did in earlier posts

    @Override
    public void handleIntent(Intent intent) {
        // super.handleIntent(intent);
        Bundle mBundle = intent.getExtras();
//        String ReceiverId = mBundle.getString("ReceiverId");
//        String JobId = mBundle.getString("JobId");
//        String Action = mBundle.getString("Action");
        String body = mBundle.getString("gcm.notification.body");
        Log.e(TAG, "notification body " + body);
//      String kidid= String.valueOf(Integer.parseInt(mBundle.getString("KidId")));


        String role= mBundle.getString("Role");
        String type=mBundle.getString("Function");
        Log.e("Role",role);
        Log.e("Message",type);
        if(role.equals("Teacher") && type.equals("Message")) {

            flag = 1;
            Random r = new Random();
            int i1 = r.nextInt(80 - 65) + 65;
            Log.e("random!!!", i1 + "");
            String kid = mBundle.getString("KidId");
            appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
            appPreferences.saveInt("kid_id", Integer.parseInt(kid));
//            appPreferences.saveInt("kid_id",kidid);

//            String classid = mBundle.getString("ClassId");
//            String schoolid = mBundle.getString("SchoolId");
//            String kidid = mBundle.getString("KidId");
            sendNotification(i1, 1,body);


        }
        else
        if(role.equals("Teacher") && type.equals("AttendanceData"))

        {
            flag = 2;
            Random r = new Random();
            int i1 = r.nextInt(80 - 65) + 65;
            Log.e("random!!!", i1 + "");
//            String kidid = mBundle.getString("KidId");

//            appPreferences.saveInt("kid_id",Integer.parseInt(kidid));
            sendNotification(i1, 2,body);
        }
        else
        if(role.equals("Driver") && type.equals("Bus Start"))
        {
            flag = 3;
            Random r = new Random();
            int i1 = r.nextInt(80 - 65) + 65;
            Log.e("random!!!", i1 + "");
            String lat = mBundle.getString("Latitude");
            String did = mBundle.getString("DriverId");
            String bid = mBundle.getString("BusId");
            String kid = mBundle.getString("KidId");
            String longt = mBundle.getString("Longitude");

            appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

            appPreferences.saveData("latitude", lat);
            appPreferences.saveInt("busid", Integer.parseInt(bid));
            appPreferences.saveInt("kid_id", Integer.parseInt(kid));
            appPreferences.saveData("longitude", longt);
            sendNotification(i1, 3,body);
        }
        else
        if(role.equals("Teacher") && type.equals("Events"))
        {
            flag = 4;
            Random r = new Random();
            int i1 = r.nextInt(80 - 65) + 65;
            Log.e("random!!!", i1 + "");
            sclid=mBundle.getString("schoolId");

//            sendNotification(i1, 4, "","","","","","",body);
            sendNotification(i1, 4,body);
        }
        else
        if(role.equals("Teacher") && type.equals("Circular"))
        {
            flag = 5;
            Random r = new Random();
            int i1 = r.nextInt(80 - 65) + 65;
            Log.e("random!!!", i1 + "");
            sclid=mBundle.getString("schoolId");

//            sendNotification(i1, 4, "","","","","","",body);
            sendNotification(i1, 5,body);
        }
        else if(role.equals("Parent") && type.equals("ProgressCard"))
        {
            flag = 6;
            Random r = new Random();
            int i1 = r.nextInt(80 - 65) + 65;
            Log.e("random!!!", i1 + "");
            html=mBundle.getString("HtmlView ");

//            sendNotification(i1, 4, "","","","","","",body);
            sendNotification(i1, 6,body);

        }








    }
//    private void sendNotification(int i1, int i, String classid,String schoolid,String kidid,
//                                  String latitude,String longitude,String busid,String messageBody) {}

    private void sendNotification(int i1, int i, String messageBody) {

        if(i==1) {
            intent = new Intent(this, Activity_Message.class);
        }
        else if(i==2)
            {
                intent = new Intent(this, attendence_activity.class);
            }
        else if(i==3)
         {
             intent = new Intent(this, MapsActivity.class);
         }
        else if(i==4)
        {
            intent = new Intent(this,calender_events .class);
            intent.putExtra("schoolid",sclid);
        }
        else if(i==5)
        {
            intent = new Intent(this,activity_newevents.class);
            intent.putExtra("schoolid",sclid);
        }
        else if(i==6)
        {
            intent = new Intent(this,ResultWebView.class);
            intent.putExtra("html",html);

        }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

             pendingIntent= PendingIntent.getActivity(this, i1, intent,
                    PendingIntent.FLAG_ONE_SHOT);


            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.app_icon)
                    .setContentTitle("School Man")
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0, notificationBuilder.build());

    }

    //above changed by Saima
    //below changed by Remya
//    AppPreferences appPreferences;
//    PendingIntent pendingIntent;
//    Intent intent;
//    private static final String TAG = "MyFirebaseMsgService";
//    int flag;
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//
//        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
//
//        Log.e(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
//        Log.e(TAG, "" + remoteMessage.getData());
//
//
//        try{
//
//            JSONObject response = new JSONObject(remoteMessage.getData());
//            String role= response.getString("Role");
//            String type=response.getString("Function");
//            if(role.equals("Teacher") && type.equals("Message")) {
//                String classid = response.getString("ClassId");
//                String schoolid = response.getString("SchoolId");
//                String kidid = response.getString("KidId");
//                int cid = Integer.parseInt(classid);
//                int sid = Integer.parseInt(schoolid);
//                int kid = Integer.parseInt(kidid);
//                flag = 0;
//
//                appPreferences.saveInt("class_id", cid);
//                appPreferences.saveInt("school_id", sid);
//                appPreferences.saveInt("kid_id", kid);
//            }
//            else if(role.equals("Teacher") && type.equals("AttendanceData"))
//            {
//                flag=1;
//                String kidid = response.getString("KidId");
//                appPreferences.saveInt("kid_id",Integer.parseInt(kidid));
//            }
//            else if(role.equals("Driver") && type.equals("Bus Start"))
//            {
//                String lat = response.getString("Latitude");
//                String did = response.getString("DriverId");
//                String bid = response.getString("BusId");
//                String kid = response.getString("KidId");
//                String longt = response.getString("Longitude");
//                appPreferences.saveData("latitude", lat);
//                appPreferences.saveInt("busid", Integer.parseInt(bid));
//                appPreferences.saveInt("kid_id", Integer.parseInt(kid));
//                appPreferences.saveData("longitude", longt);
//                flag=2;
//            }
//            else if(role.equals("Teacher")&& type.equals("Events")){
//                flag =3;
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//
//
//
//
//        sendNotification(remoteMessage.getNotification().getBody());
//    }
//    //This method is only generating push notification
//    //It is same as we did in earlier posts
//    private void sendNotification(String messageBody) {
//
//        if(flag==0) {
//
//            intent = new Intent(this, message_activity.class);
//        }
//        else
//        if(flag==1)
//        {
//            intent = new Intent(this, attendence_activity.class);
//        }
//        else if(flag==2) {
//            intent = new Intent(this, MapsActivity.class);
//        }
//        else if(flag==3){
//            intent = new Intent(this, activity_newevents.class);
//        }
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        pendingIntent= PendingIntent.getActivity(this, 0, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.school_icon)
//                .setContentTitle("School Man")
//                .setContentText(messageBody)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0, notificationBuilder.build());
//
//    }

}