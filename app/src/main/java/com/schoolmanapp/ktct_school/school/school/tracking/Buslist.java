package com.schoolmanapp.ktct_school.school.school.tracking;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.school.Home;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Bus;
import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Buslist extends android.app.Fragment {
    ListView lv;
    LinearLayout llhide, llshow;
    ImageView plus;
    ArrayList<String> busrgno;
    ArrayList<String> bid;
    ArrayList<String> btype;
    ArrayList<String> btripno;
    ArrayList<String> start;
    ArrayList<String> end;
    ArrayList<Integer> status;
    AlertDialog pd;
    String scid;
    Utils utils;
    AppPreferences appPreferences;
    BusListAdapter adapter;
    @Bind(R.id.displaymessage)
    TextView tv_msg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.buslist, container, false);
        Home h = (Home) getActivity();
        ButterKnife.bind(this, V);
        appPreferences = new AppPreferences(getActivity(), "Peekabo");
        utils = new Utils();
        busrgno = new ArrayList<String>();
        bid = new ArrayList<String>();
        btripno = new ArrayList<String>();
        btype = new ArrayList<String>();
        start = new ArrayList<String>();
        end = new ArrayList<String>();
        status = new ArrayList<Integer>();
        llhide = (LinearLayout) V.findViewById(R.id.tabhidelay);
        llshow = (LinearLayout) V.findViewById(R.id.tabaddlay);
        int sid = appPreferences.getInt("scid");
        scid = Integer.toString(sid);
        lv = (ListView) V.findViewById(R.id.bus_list);
        buslist();
        final Handler refreshHandler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // do updates for imageview

                buslist_refresh();
          refreshHandler.postDelayed(this, 10 * 1000);
            }
        };        refreshHandler.postDelayed(runnable, 10 * 1000);


//        BusListAdapter adapter = new BusListAdapter(getActivity(), busrgno, bid, btype, btripno, start, end);
//        lv.setAdapter(adapter);
        return V;
    }

//    public void buslist() {
//        pd = new SpotsDialog(getActivity());
//        try{
//        pd.show();
//        Call<Mod_cur_running_bus> call = utils.getApi().buslist_running(scid);
//        call.enqueue(new Callback<Mod_cur_running_bus>() {
//            @Override
//            public void onResponse(Call<Mod_cur_running_bus> call, Response<Mod_cur_running_bus> response) {
//                if (response.isSuccess()) {
//                    if (response.body().getMsg().equals("Success")) {
//
//                        List<Mod_cur_running_bus.UserDataBean> details = response.body().getUserData();
//                        //     Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                        int len = details.size();
//                        if (details.size() > 0) {
//                            int l = details.size();
//                            for (int i = 0; i < l; i++) {
//                                int busid = details.get(i).getBusId();
//                                String bus_id = Integer.toString(busid);
//                                String bus_regno = details.get(i).getBusSpecialId().toString();
//                                String bus_type = details.get(i).getBusType().toString();
//                                String no_of_trip = details.get(i).getTripNumber().toString();
//                                String loc_start = details.get(i).getLocationStart().toString();
//                                String loc_end = details.get(i).getLocationEnd().toString();
//                                bid.add(bus_id);
//                                busrgno.add(bus_regno);
//                                btype.add(bus_type);
//                                btripno.add(no_of_trip);
//                                start.add(loc_start);
//                                end.add(loc_end);
//                            }
//                          adapter = new BusListAdapter(getActivity(), busrgno, bid, btype, btripno, start, end);
//                           // adapter=new BuslistAdapter_new(getActivity(),details);
//                            lv.setAdapter(adapter);
//                        }
//                        else {
//                            tv_msg.setVisibility(View.VISIBLE);
//                            lv.setVisibility(View.GONE);
//                            Toast.makeText(getActivity(),"No buses running", Toast.LENGTH_SHORT).show();
//
//                            //utils.toToast("No buses running");
//                            if (pd.isShowing()) {
//                                pd.dismiss();
//                            }
//
//                            // utils.toToast(response.body().getMsg());
//                        }
//
//                    }
//                    else {
//                        if (pd.isShowing()) {
//                            pd.dismiss();
//                        }
//
//                        tv_msg.setVisibility(View.VISIBLE);
//                        lv.setVisibility(View.GONE);
//                        Toast.makeText(getActivity(),"No buses running", Toast.LENGTH_SHORT).show();
//                        //utils.toToast(response.body().getMsg());
//                    }
//
//                }
//                if (pd.isShowing()) {
//                    pd.dismiss();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Mod_cur_running_bus> call, Throwable t) {
//                //  utils.toToast("Network failure");
//                if (pd.isShowing()) {
//                    pd.dismiss();
//                }
//                Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//        }catch(Exception e)
//        {
//            e.printStackTrace();
//            if(pd.isShowing())
//            {
//                pd.dismiss();
//            }
//
//            Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
//        }
//
//    }
public void buslist() {

        try{
            pd = new SpotsDialog(getActivity());
        pd.show();
        Call<Mod_List_Bus> call = utils.getApi().buslist(scid);
        call.enqueue(new Callback<Mod_List_Bus>() {
            @Override
            public void onResponse(Call<Mod_List_Bus> call, Response<Mod_List_Bus> response) {
                if (response.isSuccess()) {
                    if (response.body().getMsg().equals("Success")) {

                        List<Mod_List_Bus.UserDataBean> details = response.body().getUserData();
                        //     Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        int len = details.size();
                        if (details.size() > 0) {
                            int l = details.size();
                            bid.clear();
                            busrgno.clear();
                            btype.clear();
                            btripno.clear();
                            start.clear();
                            end.clear();
                            status.clear();
                            for (int i = 0; i < l; i++) {
                                int busid = details.get(i).getBusId();
                                String bus_id = Integer.toString(busid);
                                String bus_regno = details.get(i).getBusSpecialId().toString();
                                String bus_type = details.get(i).getBusType().toString();
                                String no_of_trip = details.get(i).getTripNumber().toString();
                                String loc_start = details.get(i).getLocationStart().toString();
                                String loc_end = details.get(i).getLocationEnd().toString();
                                int st= details.get(i).getTravellingStatus();
                                bid.add(bus_id);
                                busrgno.add(bus_regno);
                                btype.add(bus_type);
                                btripno.add(no_of_trip);
                                start.add(loc_start);
                                end.add(loc_end);
                                status.add(st);
                            }
                          adapter = new BusListAdapter(getActivity(), busrgno, bid, btype, btripno, start, end,status);
                            adapter.notifyDataSetChanged();

                           // adapter=new BuslistAdapter_new(getActivity(),details);
                            lv.setAdapter(adapter);
                        }
                        else {
                            tv_msg.setVisibility(View.VISIBLE);
                            lv.setVisibility(View.GONE);
                            Toast.makeText(getActivity(),"No buses added", Toast.LENGTH_SHORT).show();

                            //utils.toToast("No buses running");
                            if (pd.isShowing()) {
                                pd.dismiss();
                            }

                            // utils.toToast(response.body().getMsg());
                        }

                    }
                    else {
                        if (pd.isShowing()) {
                            pd.dismiss();
                        }

                        tv_msg.setVisibility(View.VISIBLE);
                        lv.setVisibility(View.GONE);
                        Toast.makeText(getActivity(),"No buses added", Toast.LENGTH_SHORT).show();
                        //utils.toToast(response.body().getMsg());
                    }

                }
                if (pd.isShowing()) {
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Mod_List_Bus> call, Throwable t) {
                //  utils.toToast("Network failure");
                if (pd.isShowing()) {
                    pd.dismiss();
                }
                Toast.makeText(getContext(), "Network Failure", Toast.LENGTH_SHORT).show();
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

    }
    public void buslist_refresh() {

        try{

            Call<Mod_List_Bus> call = utils.getApi().buslist(scid);
            call.enqueue(new Callback<Mod_List_Bus>() {
                @Override
                public void onResponse(Call<Mod_List_Bus> call, Response<Mod_List_Bus> response) {
                    if (response.isSuccess()) {
                        if (response.body().getMsg().equals("Success")) {

                            List<Mod_List_Bus.UserDataBean> details = response.body().getUserData();
                            //     Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            int len = details.size();
                            if (details.size() > 0) {
                                int l = details.size();
                                bid.clear();
                                busrgno.clear();
                                btype.clear();
                                btripno.clear();
                                start.clear();
                                end.clear();
                                status.clear();
                                for (int i = 0; i < l; i++) {
                                    int busid = details.get(i).getBusId();
                                    String bus_id = Integer.toString(busid);
                                    String bus_regno = details.get(i).getBusSpecialId().toString();
                                    String bus_type = details.get(i).getBusType().toString();
                                    String no_of_trip = details.get(i).getTripNumber().toString();
                                    String loc_start = details.get(i).getLocationStart().toString();
                                    String loc_end = details.get(i).getLocationEnd().toString();
                                    int st= details.get(i).getTravellingStatus();
                                    bid.add(bus_id);
                                    busrgno.add(bus_regno);
                                    btype.add(bus_type);
                                    btripno.add(no_of_trip);
                                    start.add(loc_start);
                                    end.add(loc_end);
                                    status.add(st);
                                }
                                adapter = new BusListAdapter(getActivity(), busrgno, bid, btype, btripno, start, end,status);
                                adapter.notifyDataSetChanged();

                                // adapter=new BuslistAdapter_new(getActivity(),details);
                                lv.setAdapter(adapter);
                            }
                            else {
                                tv_msg.setVisibility(View.VISIBLE);
                                lv.setVisibility(View.GONE);
                          //      Toast.makeText(getActivity(),"No buses added", Toast.LENGTH_SHORT).show();

                                //utils.toToast("No buses running");


                                // utils.toToast(response.body().getMsg());
                            }

                        }
                        else {


                            tv_msg.setVisibility(View.VISIBLE);
                            lv.setVisibility(View.GONE);
                         //   Toast.makeText(getActivity(),"No buses added", Toast.LENGTH_SHORT).show();
                            //utils.toToast(response.body().getMsg());
                        }

                    }

                }

                @Override
                public void onFailure(Call<Mod_List_Bus> call, Throwable t) {
                    //  utils.toToast("Network failure");

//                    Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
                }

            });
        }catch(Exception e)
        {
            e.printStackTrace();
           // if(pd.isShowing())

            //Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
        }

    }

}
