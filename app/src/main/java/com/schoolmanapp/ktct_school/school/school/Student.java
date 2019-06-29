package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Del_stud;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Students;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by srishtiinnovative on 29/05/17.
 */
public class Student extends Fragment implements DeleteInterface {
    ListView lv;
    ImageView plus;
    TextView clsdivshw;
    LinearLayout llhide, llshow;
    ArrayList<String> sname, sphno, sparname, saddr, scity, sclass, sdiv, sfpath, bid, stid,sdob,sgndr,sbldgrp;
    ArrayList<Integer> st_id;
    String scid;
    @Bind(R.id.imgback)
    ImageView iv_back;
    AlertDialog pd;
    Bundle b;
    Bundle args;
    int cid, did;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    public static DeleteInterface deleteInterface;
    String class_id, div_id,classid,divid;
    //studadptr adapter;
    studentadapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.studenttab, container, false);
        //  public static DeleteInterface deleteInterface;
        Home h = (Home) getActivity();

        h.getApp().getActivityComponent().inject(this);
        ButterKnife.bind(this, V);
        b = new Bundle();
        llhide = (LinearLayout) V.findViewById(R.id.tabhidelay);
        llshow = (LinearLayout) V.findViewById(R.id.tabaddlay);

        sname = new ArrayList<String>();
        sphno = new ArrayList<String>();
        sparname = new ArrayList<String>();
        sclass = new ArrayList<String>();
        sdiv = new ArrayList<String>();
        saddr = new ArrayList<String>();
        scity = new ArrayList<String>();
        stid = new ArrayList<String>();
        bid = new ArrayList<String>();
        sfpath = new ArrayList<String>();
        st_id = new ArrayList<Integer>();
        sbldgrp = new ArrayList<String>();
        sdob= new ArrayList<String>();
        sgndr = new ArrayList<String>();
        if (V != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(V.getWindowToken(), 0);
        }
//        h.tabview3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment3(new Displayclass());
//
//            }
//        });
        classid= appPreferences.getData("classtopass");
        divid= appPreferences.getData("divisiontopass");
        clsdivshw=(TextView) V.findViewById(R.id.clsdiv);
        clsdivshw.setText(classid+"-".concat(divid));


        plus = (ImageView) V.findViewById(R.id.img_addstudent);
        plus.setVisibility(View.INVISIBLE);


        args = this.getArguments();
        try {
            display();
        } catch (Exception e) {
            e.printStackTrace();
        }


        deleteInterface = this;
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Home.isclassdiv==true)
                {
                    loadFragment4(new Classdiv());
                }
                else {
                    loadFragment3(new Displayclass());
                }
            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llhide.setVisibility(v.GONE);
                llshow.setVisibility(v.VISIBLE);

                if (args != null) {
                    class_id = args.getString("classname");
                    div_id = args.getString("divname");
                    cid = args.getInt("classid");
                    did = args.getInt("divid");

                }
                b.putString("classname", class_id);
                b.putString("divname", div_id);
                b.putInt("classid", cid);
                b.putInt("divid", did);
                loadFragment2(new AddStudentFragment());

            }
        });
        lv = (ListView) V.findViewById(R.id.student_list);


        return V;
    }
    private void loadFragment4(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        fragment.setArguments(b);

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameadd_lay, fragment);
        fragmentTransaction.commit();

    }
    private void loadFragment2(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        fragment.setArguments(b);

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameadd_lay, fragment);
        fragmentTransaction.commit();

    }

    public void display()

    {

        Bundle args = this.getArguments();
        if (args != null) {
            //          try {

//            if(args.getInt("classid")!=0 && args.getInt("divid")!=0) {
            cid = args.getInt("classid");
            did = args.getInt("divid");
//            }
//            else
//           {
//                cid = args.getInt("sclid");
//                did = args.getInt("sdivid");
//            }
//            }catch (NullPointerException e)
//            {
//                e.printStackTrace();
//            }

//        }
//        else
//        {
//            cid=0;
//            did=0;
        } else {
            cid = appPreferences.getInt("cidtopass");
            did = appPreferences.getInt("didtopass");
        }
//        String c_id = Integer.toString(cid);
//        String d_id = Integer.toString(did);

        String c_id = appPreferences.getData("classid");
        String d_id = appPreferences.getData("divid");
        int sid = appPreferences.getInt("scid");
        scid = Integer.toString(sid);
//        Log.e("test",scid+" "+Integer.toString(cid)+" "+Integer.toString(did));
        pd = new SpotsDialog(getActivity());
        try {
            pd.show();

            Call<Mod_List_Students> call = utils.getApi().studentlist(scid, Integer.toString(cid), Integer.toString(did));

            call.enqueue(new Callback<Mod_List_Students>() {
                @Override
                public void onResponse(Call<Mod_List_Students> call, Response<Mod_List_Students> response) {
                    if (response.isSuccess()) {
                        if (response.body().getMsg().equals("Success")) {
                            List<Mod_List_Students.UserDataBean> details = response.body().getUserData();

                            int len = details.size();

                            //   utils.toToast(response.body().getMsg());
                            if (details.size() > 0) {
                                int l = details.size();
                                for (int i = 0; i < l; i++) {

                                    try {
                                        String stname = details.get(i).getStundentName().toString();
                                        String stphno = details.get(i).getContactNumber().toString();
                                        String stparname = details.get(i).getParentName().toString();
                                        String staddress = details.get(i).getAddress().toString();
                                        String st_city = details.get(i).getCity().toString();
                                        String st_dob=details.get(i).getDOBString().toString();
                                        String st_gender=details.get(i).getGender().toString();
                                        String st_blood=details.get(i).getBloodGroup().toString();
                                        try {
                                            String fpath = details.get(i).getFilePath().toString();
                                            sfpath.add(fpath);
                                        } catch (NullPointerException e) {
                                            //utils.toToast("No image set for students");
                                        }
                                        String divname = details.get(i).getDivision().getDivisionName().toString();
                                        String classname = details.get(i).getDivision().getClassName().toString();
                                        int stud_id = details.get(i).getStudentId();
                                        st_id.add(stud_id);
                                        int bus_id = details.get(i).getBusId();

                                        String b_id = details.get(i).getBusSpecialId().toString();
                                        String s_id = details.get(i).getStudentSpecialId();
                                        sname.add(stname);
                                        Log.e("studnames", "dfgfhf" + sname);
                                        sphno.add(stphno);
                                        Log.e("studnames", "dfgfhf" + stphno);
                                        sparname.add(stparname);
                                        Log.e("studnames", "dfgfhf" + sparname);
                                        sclass.add(classname);
                                        Log.e("studnames", "dfgfhf" + sclass);
                                        sdiv.add(divname);
                                        Log.e("studnames", "dfgfhf" + sdiv);
                                        saddr.add(staddress);
                                        Log.e("studnames", "dfgfhf" + saddr);
                                        scity.add(st_city);
                                        Log.e("studnames", "dfgfhf" + scity);
                                        sdob.add(st_dob);
                                        sbldgrp.add(st_blood);
                                        sgndr.add(st_gender);
                                        //Log.e("studnames","dfgfhf"+sfpath);
                                        stid.add(s_id);
                                        Log.e("studnames", "dfgfhf" + stid);
                                        bid.add(b_id);
                                        Log.e("studnames", "dfgfhf" + bid);
                                    } catch (NullPointerException e) {
                                        e.printStackTrace();
                                    }

                                }

//                            adapter = new studadptr(getActivity(), sname, sphno, sparname, sclass, sdiv, saddr, scity, sfpath, stid, bid);
                                adapter = new studentadapter(getActivity(), details);
                                lv.setAdapter(adapter);


                            } else {
                                if (pd.isShowing()) {
                                    plus.setVisibility(View.VISIBLE);
                                    pd.dismiss();
                                }
                                utils.toToast("No student added for the class");
                                //utils.toToast(response.body().getMsg());
                            }
                        } else {
                            if (pd.isShowing()) {
                                plus.setVisibility(View.VISIBLE);
                                pd.dismiss();
                            }
                            utils.toToast("No student added for the class");
                            //utils.toToast(response.body().getMsg());
                        }
                    }
                    if (pd.isShowing()) {
                        plus.setVisibility(View.VISIBLE);
                        pd.dismiss();
                    }


                }

                @Override
                public void onFailure(Call<Mod_List_Students> call, Throwable t) {
                    if (pd.isShowing()) {
                        pd.dismiss();
                    }


                    Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
            if (pd.isShowing()) {
                pd.dismiss();
            }

            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onDelete(final String pos) {
        //int id = st_id.get(pos);
        //String stid = Integer.toString(id);
        pd = new SpotsDialog(getActivity());
        pd.show();


        Call<Mod_Del_stud> call = utils.getApi().delstud(scid, pos);

        call.enqueue(new Callback<Mod_Del_stud>() {
            @Override
            public void onResponse(Call<Mod_Del_stud> call, Response<Mod_Del_stud> response) {
                if (response.isSuccess()) {
                    //utils.toToast(response.message());
                    if (response.body().getMsg().equals("Success")) {
                        pd.dismiss();
                        utils.toToast("Student deleted successfully");

                       // display();
                        loadFragment3(new Student());

                    } else {
                        if (pd.isShowing()) {

                            pd.dismiss();
                        }
                        utils.toToast(response.body().getMsg());
                    }


                } else {
                    if (pd.isShowing()) {

                        pd.dismiss();
                    }
                    utils.toToast(response.body().getMsg());
                }

            }

            @Override
            public void onFailure(Call<Mod_Del_stud> call, Throwable t) {
                //  utils.toToast("Network failure");
                Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
            }


        });

    }

    private void loadFragment3(Fragment fragment) {
        try {
            FragmentManager fm = getFragmentManager();


            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameadd_lay, fragment);
            fragmentTransaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

