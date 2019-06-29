package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Del_Bus;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Bus;
import com.schoolmanapp.ktct_school.school.school.common.BaseFragment;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by srishtiinnovative on 29/05/17.
 */

public class Bus extends BaseFragment implements DeleteInterface {
    ListView lv;
    LinearLayout llhide, llshow;
    ImageView plus;
    ArrayList<String> busrgno;
    ArrayList<String> bid;
    ArrayList<String> btype;
    ArrayList<String> btripno;
    ArrayList<String> start;
    ArrayList<String> end;
    AlertDialog pd;
    String scid;
    public static DeleteInterface deleteInterface;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
  //  busadptr adapter;
    busadapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.bustab, container, false);
        Home h = (Home) getActivity();
        h.getApp().getActivityComponent().inject(this);
        ButterKnife.bind(this, V);
        deleteInterface = this;
        busrgno = new ArrayList<String>();
        bid = new ArrayList<String>();
        btripno = new ArrayList<String>();
        btype = new ArrayList<String>();
        start = new ArrayList<String>();
        end = new ArrayList<String>();
        Home.isInHomePage = true;
        llhide = (LinearLayout) V.findViewById(R.id.tabhidelay);
        llshow = (LinearLayout) V.findViewById(R.id.tabaddlay);

        plus = (ImageView) V.findViewById(R.id.img_addbus);
        plus.setVisibility(View.INVISIBLE);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llhide.setVisibility(v.GONE);
                llshow.setVisibility(v.VISIBLE);
                loadFragment2(new AddBusFragment());

            }
        });
        int sid = appPreferences.getInt("scid");
        scid = Integer.toString(sid);
        //    simpleProgressBar.setVisibility(V.VISIBLE);
        try {
            buslist();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        lv = (ListView) V.findViewById(R.id.bus_list);
        busadptr adapter = new busadptr(getActivity(), busrgno, bid, btype, btripno, start, end);


        lv.setAdapter(adapter);

        return V;
    }


    private void loadFragment2(AddBusFragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.frameadd_lay, fragment);
        fragmentTransaction.commit();
    }

    public void buslist() {
        pd = new SpotsDialog(getActivity());
        try
        {
        pd.show();

        Call<Mod_List_Bus> call = utils.getApi().buslist(scid);

        call.enqueue(new Callback<Mod_List_Bus>() {
            @Override
            public void onResponse(Call<Mod_List_Bus> call, Response<Mod_List_Bus> response) {
                if (response.isSuccess()) {


                    String msg = response.body().getMsg();
                    if (msg.equals("Success")) {

                        List<Mod_List_Bus.UserDataBean> details = response.body().getUserData();
                       if (details.size() > 0) {

                        adapter =new busadapter(getActivity(),details);

                            lv.setAdapter(adapter);



                        }
                        else
                        {
                            if (pd.isShowing()) {
                                plus.setVisibility(View.VISIBLE);
                                pd.dismiss();
                            }
                            utils.toToast("No buses added for this school");
                        }
                    }
                    else
                    {
                        if (pd.isShowing()) {
                            plus.setVisibility(View.VISIBLE);
                            pd.dismiss();
                        }
                        utils.toToast(msg);
                    }
                }
                if(pd.isShowing())
                {
                    // utils.toToast("No teacher added for the school");
                    pd.dismiss();
                    plus.setVisibility(View.VISIBLE);
                }


            }


            @Override
            public void onFailure(Call<Mod_List_Bus> call, Throwable t) {
                //  utils.toToast("Network failure");
                if (pd.isShowing()) {
                    pd.dismiss();
                }
                Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onDelete(String bid) {
       // String busid =Integer.toString(position);
        pd = new SpotsDialog(getActivity());
        pd.show();
        Call<Mod_Del_Bus> call = utils.getApi().delbus(scid, bid);
        call.enqueue(new Callback<Mod_Del_Bus>() {
            @Override
            public void onResponse(Call<Mod_Del_Bus> call, Response<Mod_Del_Bus> response) {
                if (response.isSuccess()) {
                    if (response.body().getMsg().equals("Success")) {
                    //    utils.toToast(response.body().getMsg());
                        pd.dismiss();
                        utils.toToast("Bus deleted successfully ");
                        int c=lv.getCount();
                        if(c==1)
                            lv.setAdapter(null);
                        buslist();
                    }
                    else
                    {
                        if (pd.isShowing()) {
                            pd.dismiss();
                        }
                        utils.toToast(response.body().getMsg());
                    }
                }
                else
                {
                    if (pd.isShowing()) {
                        pd.dismiss();
                    }
                    utils.toToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<Mod_Del_Bus> call, Throwable t) {
                  utils.toToast("Network failure");
                //Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
            }


        });

    }
}