package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_travel_history;
import com.schoolmanapp.ktct_school.school.school.common.BaseFragment;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 06/06/17.
 */

public class Fragment_customtravel extends Fragment {
    View view;
    ListView lv;

    ArrayList<String> place,time;
    String bid,status,tid,tdate,driver_name,driver_photo;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    AlertDialog pd;
    TextView tv_name;
    ImageView img_driver;

    @Bind(R.id.imgbackbt)
    ImageView back;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.trav_history, container, false);
        lv=(ListView)view.findViewById(R.id.travel_list);
        tv_name=(TextView)view.findViewById(R.id.name);
        img_driver=(ImageView)view.findViewById(R.id.imgdriver_photo);
        ButterKnife.bind(this,view);
        Home h = (Home) getActivity();
        h.getApp().getActivityComponent().inject(this);
        bid=  appPreferences.getData("bus_ID");
        status=appPreferences.getData("shift");
        tid=appPreferences.getData("tripid");
        tdate=appPreferences.getData("tripdate");
     place=new ArrayList<String>();
        time=new ArrayList<String>();
        pd = new SpotsDialog(getActivity());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment2(new HistoryFragment());

            }
        });



        try {
            pd.show();


            Call<Mod_travel_history> call = utils.getApi().history(bid, tid, tdate, status);

            call.enqueue(new Callback<Mod_travel_history>() {
                @Override
                public void onResponse(Call<Mod_travel_history> call, Response<Mod_travel_history> response) {
                    if (response.isSuccess()) {

                        Mod_travel_history.UserDataBean details = response.body().getUserData();
                        // utils.toToast(response.body().getMsg());
                        //Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        if (response.body().getMsg().equals("Success")) {
                            try {
                                if (response.body().getUserData() != null) {
                                    List<Mod_travel_history.UserDataBean.TravelBean> travel = details.getTravel();
                                    //  utils.toToast("list"+travel);
                                    driver_name = details.getDriverName().toString();
                                    driver_photo = details.getDriverProfile().toString();


                                    int l = travel.size();
                                    for (int i = 0; i < l; i++) {
                                        String p = travel.get(i).getPlace().toString();
                                        List<String> area = Arrays.asList(p.split(","));
                                        String location = "";
                                        if (area.size() < 2) {
                                            location = area.get(0);
                                        } else {
                                            location = area.get(0) + "," + area.get(1);
                                        }

//                                        List<String> area = Arrays.asList(p.split(","));
//                                        String location = area.get(0) + "," + area.get(1);
                                        place.add(location);
                                        String t = travel.get(i).getTimeStamp().toString();
                                        //  time.add(t);

                                        try {

                                            SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                                            formatdate.setTimeZone(TimeZone.getTimeZone("PST"));
                                            Date date = formatdate.parse(t);
                                            // Log.e("date",date.toString());
                                            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");

                                            String temp = formatter.format(date).toString();


                                            time.add(temp);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }


                                    }

                                    traveladapter adapter = new traveladapter(getActivity(), place, time);
                                    tv_name.setText(driver_name);
                                    if(driver_photo==null){
                                        Picasso.with(getActivity()).load(R.drawable.dummy).into(img_driver);
                                    }else {
                                        String path = "http://www.schoolman.in//" + driver_photo;
                                        Picasso.with(getActivity()).load(path).into(img_driver);
                                    }
                                    lv.setAdapter(adapter);
                                    pd.dismiss();

                                } else {
                                    if (pd.isShowing()) {

                                        pd.dismiss();
                                    }
                                    utils.toToast("No history available");
                                    //utils.toToast(response.body().getMsg());
                                }
                            }catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        } else {
                            if (pd.isShowing()) {

                                pd.dismiss();
                            }
                            loadFragment2(new HistoryFragment());
                            utils.toToast("No history available");
                            //utils.toToast(response.body().getMsg());
                        }
                    }
                    if (pd.isShowing()) {

                        pd.dismiss();
                        utils.toToast(response.body().getMsg());
                    }


                }


                @Override
                public void onFailure(Call<Mod_travel_history> call, Throwable t) {
                    //  utils.toToast("Network failure");
                    Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
                    if(pd.isShowing())
                    {
                        pd.dismiss();
                    }

                }

            });
        }catch(Exception e)
        {
            e.printStackTrace();
            if(pd.isShowing())
            {
                pd.dismiss();
            }

            Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
        }


        return view;


    }


    private  void loadFragment2(HistoryFragment fragment)
    {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        android.app.FragmentManager fm=getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_lay,fragment);
        fragmentTransaction.commit();
    }


}

