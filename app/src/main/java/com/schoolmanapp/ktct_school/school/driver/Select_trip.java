package com.schoolmanapp.ktct_school.school.driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 19/05/17.
 */

public class Select_trip extends BaseActivity {
    @Bind(R.id.txtbtn_fromschool)
    TextView tv_fromschool;
    @Bind(R.id.txtbtn_toschool)
    TextView tv_toschool;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_stop);
        ButterKnife.bind(this);
        getApp().getActivityComponent().inject(this);
        String  start= appPreferences.getData("locationstart");
        String  end= appPreferences.getData("locationend");
        appPreferences.saveDataBoolean("status",false);
        String dispfrom=start+" "+"->"+" "+end;
        String dispto=end+" "+"->"+" "+start;
        tv_fromschool.setText(dispfrom);
        tv_toschool.setText(dispto);
        tv_fromschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), Startstop.class);
                Bundle bundle = new Bundle();

                bundle.putString("flag","true");


                i.putExtras(bundle);
                startActivity(i);
            }
        });
        tv_toschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), Startstop.class);
                Bundle bundle = new Bundle();


                bundle.putString("flag","false");

                i.putExtras(bundle);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),Home.class);
        startActivity(intent);

    }
}
