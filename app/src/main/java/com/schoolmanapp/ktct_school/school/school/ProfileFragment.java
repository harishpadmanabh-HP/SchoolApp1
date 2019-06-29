package com.schoolmanapp.ktct_school.school.school;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 01/06/17.
 */

public class ProfileFragment extends Fragment {
    View view;
    ImageView iv;
    TextView name,phno,email,web,addr,scity;
    @Bind(R.id.prof_img)
    ImageView iv_prof;
    @Bind(R.id.school_name)
    TextView tv_name;
    @Bind(R.id.addr)
    TextView tv_addr;
    @Bind(R.id.city)
    TextView tv_city;
    @Bind(R.id.website)
    TextView tv_web;
    @Bind(R.id.email)
    TextView tv_email;
    @Bind(R.id.phno)
    TextView tv_phno;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    @Bind(R.id.iv_settings)ImageView setting;

    String location=new String();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.profile,container,false);
        Home h = (Home) getActivity();
        h.getApp().getActivityComponent().inject(this);
        ButterKnife.bind(this,view);
        int sid = appPreferences.getInt("scid");
        String  scid = Integer.toString(sid);
        String name=appPreferences.getData("name");
        String addr=appPreferences.getData("addr");
        String city=appPreferences.getData("city");
        String contact=appPreferences.getData("contact");
        String email=appPreferences.getData("email");
        String path=appPreferences.getData("path");
        String web=appPreferences.getData("web");

        List<String> area = Arrays.asList(addr.split(","));
        int l=area.size();
        for(int i=0;i<l;i++)

         location+= area.get(i) + " , ";


        tv_name.setText(name);
        tv_addr.setText(location);
        tv_city.setText(city);
        tv_email.setText(email);
        tv_phno.setText(contact);
        tv_web.setText(web);
        String imgpath = "http://www.schoolman.in//" +path;
        if(!(path.isEmpty()) ){
            Picasso.with(getActivity()).load(imgpath).into(iv_prof);
        }
        Home.isInHomePage = false;

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),SetttingsFragment.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
