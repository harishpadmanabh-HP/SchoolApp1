package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.driver.common.BaseFragment;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Del_driver;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Driver;
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

public class Driver extends BaseFragment implements DeleteInterface,EditInterface {
    ListView lv;
    ImageView plus;
    LinearLayout llhide,llshow;
    ArrayList<String> dname,dphone,fpath,dlcno,dspid,daddr,dcity;
    public static DeleteInterface deleteInterface;
    public static EditInterface editInterface;



    ArrayList<Integer>did;
    String scid;
    //driveradptr adapter;
    driveradapter adapter;
    AlertDialog pd;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    List<Mod_List_Driver.UserDataBean> details;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.drivertab, container, false);
        Home h=(Home)getActivity();
        h.getApp().getActivityComponent().inject(this);
        ButterKnife.bind(this,V);
        deleteInterface=this;
        editInterface=this;
        llhide=(LinearLayout) V.findViewById(R.id.tabhidelay);
        llshow=(LinearLayout)V.findViewById(R.id.tabaddlay);
        Home.isInHomePage = true;
        dname=new ArrayList<String>();
        dphone=new ArrayList<String>();
        fpath=new ArrayList<String>();
        dlcno=new ArrayList<String>();
        dspid=new ArrayList<String>();
        daddr=new ArrayList<String>();
        dcity=new ArrayList<String>();

        did=new ArrayList<Integer>();
        if (V != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(V.getWindowToken(), 0);
        }


        plus=(ImageView)V.findViewById(R.id.img_adddriver);
        plus.setVisibility(View.INVISIBLE);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llhide.setVisibility(v.GONE);
                llshow.setVisibility(v.VISIBLE);
                loadFragment2(new AddDriverFragment());

            }
        });


        int sid=appPreferences.getInt("scid");
        scid=Integer.toString(sid);


        lv=(ListView)V.findViewById(R.id.driver_list);
       // simpleProgressBar.setVisibility(V.VISIBLE);
        try {
            display();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return V;
    }

    public void display() {
        pd = new SpotsDialog(getContext());
        try
        {


        pd.show();

        Call<Mod_List_Driver> call = utils.getApi().driverlist(scid);

        call.enqueue(new Callback<Mod_List_Driver>() {
            @Override
            public void onResponse(Call<Mod_List_Driver> call, Response<Mod_List_Driver> response) {
                if (response.isSuccess()) {

                    if (response.body().getMsg().equals("Success")) {
                        details = response.body().getUserData();
                        // utils.toToast(response.body().getMsg());

                        if (details.size() > 0) {

                            adapter = new driveradapter(getActivity(), details);

                            lv.setAdapter(adapter);


                        } else {

                            if (pd.isShowing()) {
                                plus.setVisibility(View.VISIBLE);
                                pd.dismiss();
                            }

                            //  utils.toToast(response.body().getMsg());
                            utils.toToast("No driver added for the school");
                        }
                    } else {
                        utils.toToast(response.body().getMsg());
                        //utils.toToast("No driver added for the school");

                        plus.setVisibility(View.VISIBLE);
                        pd.dismiss();
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
            public void onFailure(Call<Mod_List_Driver> call, Throwable t) {
                //  utils.toToast("Network failure");
                if(pd.isShowing())
                {
                    pd.dismiss();
                }
                Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
            }

        });
        }catch(NullPointerException e)
        {
            e.printStackTrace();
            if(pd.isShowing())
            {
                pd.dismiss();
            }

            Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
        }

    }

    private void loadFragment2(AddDriverFragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.frameadd_lay, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDelete(final String pos) {

        pd = new SpotsDialog(getActivity());
           pd.show();

        Call<Mod_Del_driver> call = utils.getApi().deldriver(scid,pos);

        call.enqueue(new Callback<Mod_Del_driver>() {
            @Override
            public void onResponse(Call<Mod_Del_driver> call, Response<Mod_Del_driver> response) {
                if (response.isSuccess()) {
                   // utils.toToast(response.message());
                    if (response.body().getMsg().equals("Success")) {
                   //   utils.toToast(response.body().getMsg());
                        pd.dismiss();
                        utils.toToast("Driver deleted successfully");
                        int c=lv.getCount();
                        if(c==1)
                            lv.setAdapter(null);
                        display();


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
            public void onFailure (Call <Mod_Del_driver> call, Throwable t){
                //  utils.toToast("Network failure");
                Toast.makeText(getActivity(),"Network Failure",Toast.LENGTH_SHORT).show();
            }

        });


    }

    @Override
    public void onEdit(String position) {
        llhide.setVisibility(View.VISIBLE);
        llshow.setVisibility(View.VISIBLE);
        Fragment fragment=new EditDriverFragment();
        Integer pos=Integer.valueOf(position);
        Bundle b =new Bundle();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        b.putString("Address",details.get(pos).getAddress());
        b.putString("Name",details.get(pos).getDriverName());
        b.putString("City",details.get(pos).getCity());
        b.putString("Licenceno",details.get(pos).getLicenseNumber());
        b.putString("State",details.get(pos).getState());
        b.putString("Contactno",details.get(pos).getContactNumber());
        b.putInt("drid",details.get(pos).getDriverId());
        b.putString("photopath",details.get(pos).getFilePath());
        b.putString("photo",details.get(pos).getFilePath());


        fragment.setArguments(b);
//        replaceFragment(R.id.frameadd_lay,fragment);

        fragmentTransaction.replace(R.id.frameadd_lay, fragment);
        fragmentTransaction.commit();

    }
}