package com.schoolmanapp.ktct_school.school.school;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;

import javax.inject.Inject;

/**
 * Created by srishtiinnovative on 17/05/17.
 */

public class Splashscreen extends BaseActivity {
    @Inject
    AppPreferences appPreferences;
    @Inject
    Context mContext;
    com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences pref_parent;
    com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences pref_teacher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        getApp().getActivityComponent().inject(this);
        pref_parent = com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        pref_teacher = com.schoolmanapp.ktct_school.school.teacher.utils.AppPreferences.getInstance(this, getResources().getString(R.string.app_name));


    final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

                                @Override
                                public void run() {


                                    if (appPreferences.getDataBoolean("isLogin")) {
                                        String user = appPreferences.getData("login_name");
                                        switch (user) {
                                            case "School": {
                                                Intent intent;
                                                intent = new Intent(mContext, Home.class);
                                                startActivity(intent);

                                                //loadFragment2(new Classdiv());

                                                break;

                                            }
                                            case "Driver": {
                                                Intent intent;
                                                Boolean val=appPreferences.getDataBoolean("Bustravelstatus");
                                                if(val)
                                                {
                                                    intent = new Intent(mContext, com.schoolmanapp.ktct_school.school.driver.MainActivity.class);

                                                    startActivity(intent);
                                                }
                                                else {
                                                    intent = new Intent(mContext, com.schoolmanapp.ktct_school.school.driver.Home.class);

                                                    startActivity(intent);
                                                }
                                                break;
                                            }
                                            default:
                                                {
                                                    Intent intent;
                                                    intent = new Intent(mContext, FourInOne.class);
                                                    startActivity(intent);

                                                }


                                        }
                                    }
                                    else if(pref_parent.getDataBoolean("isLogin")){
                                        String user = pref_parent.getData("login_name");
                                        switch (user) {
                                            case "Parent": {
                                                Intent intent;
                                                intent = new Intent(mContext, com.schoolmanapp.ktct_school.school.parent.Java_class.Home.class);
                                                startActivity(intent);
                                                break;
                                            }
                                            case "Teacher": {
                                                Intent intent;
                                                intent = new Intent(mContext, com.schoolmanapp.ktct_school.school.teacher.java_class.home_activity.class);
                                                startActivity(intent);
                                                break;
                                            }
                                            default:
                                            {
                                                Intent intent;
                                                intent = new Intent(mContext, FourInOne.class);
                                                startActivity(intent);

                                            }

                                        }

                                    }
//                                    else if(pref_teacher.getDataBoolean("isLogin")){
//                                        String user = pref_teacher.getData("login_name");
//                                        switch (user) {
//                                            case "Teacher": {
//                                                Intent intent;
//                                                intent = new Intent(mContext, com.schoolmanapp.srishtiinnovative.school.teacher.java_class.home_activity.class);
//                                                startActivity(intent);
//                                                break;
//                                            }
//                                            default:
//                                            {
//                                                Intent intent;
//                                                intent = new Intent(mContext, FourInOne.class);
//                                                startActivity(intent);
//
//                                            }
//
//                                        }
//
//                                    }

                                    else {
                                        Intent intent;
                                        intent = new Intent(mContext, FourInOne.class);
                                        startActivity(intent);


                                    }
                                    finish();
                                }


                            }

                , 2000);

}

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
//    public void loadFragment2(Classdiv fragment) {
//
//       FragmentManager fm = getFragmentManager();
//
//
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//
//        fragmentTransaction.replace(R.id.frameadd_lay, fragment);
//        fragmentTransaction.commit();
//       // pd.dismiss();
//    }
//

}
