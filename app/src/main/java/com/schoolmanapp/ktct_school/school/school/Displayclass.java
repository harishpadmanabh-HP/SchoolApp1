package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Classdiv;
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
 * Created by srishtiinnovative on 02/06/17.
 */

public class Displayclass extends android.support.v4.app.Fragment implements Listclasses {
    LinearLayout ll;
    LinearLayout llhide,llshow;
    int sid;
    String scid;
    Bundle bundle;
    public static Displayclass listclasses;
    AlertDialog pd;

    ArrayList<String> classname, division;
    ArrayList<Integer> did,cid;
    ListView lv;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    public classviewadapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.displayclasses, container, false);
        Home h = (Home) getActivity();
        h.getApp().getActivityComponent().inject(this);

        ButterKnife.bind(getActivity());
        bundle=new Bundle();
        classname = new ArrayList<String>();
        division = new ArrayList<String>();
        did = new ArrayList<Integer>();
        cid = new ArrayList<Integer>();
        Home.isInHomePage = true;
        listclasses=this;
        llhide=(LinearLayout) V.findViewById(R.id.tabhidelay);
        llshow=(LinearLayout)V.findViewById(R.id.tabaddlay);
        sid = appPreferences.getInt("scid");
        scid = Integer.toString(sid);
        display();
        lv = (ListView) V.findViewById(R.id.view_class_list);


        return V;
    }
    private  void loadFragment2(Fragment fragment)
    {
        llhide.setVisibility(View.GONE);
        llshow.setVisibility(View.VISIBLE);

        FragmentManager fm=getFragmentManager();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameadd_lay,fragment);
        fragmentTransaction.commit();

    }
    public  void display()
    {

        pd = new SpotsDialog(getActivity());
        try {
            pd.show();

            Call<Mod_List_Classdiv> call = utils.getApi().classlist(scid);

            call.enqueue(new Callback<Mod_List_Classdiv>() {
                @Override
                public void onResponse(Call<Mod_List_Classdiv> call, Response<Mod_List_Classdiv> response) {
                    if (response.isSuccess()) {

                        List<Mod_List_Classdiv.UserDataBean> details = response.body().getUserData();
                        // utils.toToast(response.body().getMsg());
                        //  Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        //appPreferences.saveData(Constants.currentUser, details.get(0).getUserId());
                        //  appPreferences.saveDataBoolean(Constants.isLogin, true);
                        if (response.body().getMsg().equals("Success")) {
                            if (details.size() > 0) {

                                int l = details.size();
                                for (int i = 0; i < l; i++) {
                                    String c = details.get(i).getClassName().toString();
                                    int id = details.get(i).getClassId();

                                    // classname.add(c);
                                    List<Mod_List_Classdiv.UserDataBean.DivisionBean> d = details.get(i).getDivision();
                                    int len = d.size();
                                    for (int j = 0; j < len; j++) {
                                        try {


                                        String div = d.get(j).getDivisionName().toString();
                                        int divid = d.get(j).getDivisionId();
                                        classname.add(c);
                                        division.add(div);
                                        did.add(divid);
                                        cid.add(id);
                                        }catch (NullPointerException e){

                                        }
                                    }

                                }

                                adapter = new classviewadapter(getActivity(), classname, division);
                                lv.setAdapter(adapter);


                            } else {
                                if (pd.isShowing()) {
                                    pd.dismiss();
                                }
                                utils.toToast("No class added for the school");
                                // utils.toToast(response.body().getMsg());
                            }


                        } else {
                            utils.toToast(response.body().getMsg());
                            if (pd.isShowing()) {
                                pd.dismiss();
                            }
                        }

                    }

                    if (pd.isShowing()) {
                        pd.dismiss();
                    }
                }


                @Override
                public void onFailure(Call<Mod_List_Classdiv> call, Throwable t) {
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
    public void viewclass(final int pos) {
        int classid=cid.get(pos);
        int divid=did.get(pos);
        bundle.putInt("classid",classid);
        bundle.putInt("divid",divid);
        String cname=classname.get(pos);
        String dname=division.get(pos);
        bundle.putString("classname",cname);
        bundle.putString("divname",dname);
        Home.isclassdiv=false;
        appPreferences.saveData("classid",Integer.toString(classid));
        appPreferences.saveData("divid",Integer.toString(divid));
        appPreferences.saveInt("cidtopass",classid);
        appPreferences.saveInt("didtopass",divid);
        appPreferences.saveData("classtopass",cname);
        appPreferences.saveData("divisiontopass",dname);
        loadFragment2(new Student());


    }


}

