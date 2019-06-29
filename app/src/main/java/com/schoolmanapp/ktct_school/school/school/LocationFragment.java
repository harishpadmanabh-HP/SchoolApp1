package com.schoolmanapp.ktct_school.school.school;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.schoolmanapp.ktct_school.school.R;

import com.schoolmanapp.ktct_school.school.school.tracking.Buslist;

import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import java.util.ArrayList;

/**
 * Created by srishtiinnovative on 01/06/17.
 */

public class LocationFragment extends android.app.Fragment {
    View view;
    ArrayList<String> busrgno;
    ArrayList<String> bid;
    ArrayList<String> btype;
    ArrayList<String> btripno;
    ArrayList<String> start;
    ArrayList<String> end;
    ImageView iv;
    Utils utils;
    String scid;
    AppPreferences appPreferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.buslist, container, false);
//        iv = (ImageView) view.findViewById(R.id.btn_track);
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
        appPreferences = new AppPreferences(getActivity(), "Peekabo");
        utils = new Utils();
        busrgno = new ArrayList<String>();
        bid = new ArrayList<String>();
        btripno = new ArrayList<String>();
        btype = new ArrayList<String>();
        start = new ArrayList<String>();
        end = new ArrayList<String>();
        Home.isInHomePage = false;
        loadFragment2(new Buslist());


        return view;
    }

    private  void loadFragment2(Buslist fragment)
    {



        android.app.FragmentManager fm=getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_lay,fragment);
        fragmentTransaction.commit();
    }
//    public void buslist() {
//
//        try{
//            Call<Mod_cur_running_bus> call = utils.getApi().buslist_running(scid);
//            call.enqueue(new Callback<Mod_cur_running_bus>() {
//                @Override
//                public void onResponse(Call<Mod_cur_running_bus> call, Response<Mod_cur_running_bus> response) {
//                    if (response.isSuccess()) {
//                        if (response.body().getMsg().equals("Success")) {
//
//                            List<Mod_cur_running_bus.UserDataBean> details = response.body().getUserData();
//                            //     Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                            int len = details.size();
//                            if (details.size() > 0) {
//                                int l = details.size();
//                                for (int i = 0; i < l; i++) {
//                                    int busid = details.get(i).getBusId();
//                                    String bus_id = Integer.toString(busid);
//                                    String bus_regno = details.get(i).getBusSpecialId().toString();
//                                    String bus_type = details.get(i).getBusType().toString();
//                                    String no_of_trip = details.get(i).getTripNumber().toString();
//                                    String loc_start = details.get(i).getLocationStart().toString();
//                                    String loc_end = details.get(i).getLocationEnd().toString();
//                                    bid.add(bus_id);
//                                    busrgno.add(bus_regno);
//                                    btype.add(bus_type);
//                                    btripno.add(no_of_trip);
//                                    start.add(loc_start);
//                                    end.add(loc_end);
//                                }
//
//                            }
//
//
//                                // utils.toToast(response.body().getMsg());
//                            }
//
//                        }
//
//
//                    }
//
//
//
//                @Override
//                public void onFailure(Call<Mod_cur_running_bus> call, Throwable t) {
//                    //  utils.toToast("Network failure");
//
//                    Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
//                }
//
//            });
//        }catch(Exception e)
//        {
//            e.printStackTrace();
//
//            Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
//        }
//
//    }
}
