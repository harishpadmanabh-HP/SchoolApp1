package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Teacher_List;
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

public class Teacher extends Fragment implements DeleteInterface {
    public static DeleteInterface deleteInterface;
    ListView lv;
    ImageView plus;
    LinearLayout llhide, llshow;
    ArrayList<Integer> tid_list;
    String scid;
    //teacheradptr  adapter;
    teacheradapter adapter;
    teacheradptr adapter_tr;
    ArrayList<String> tname, spid, classname, divname, phno, email, fpath;
    ArrayList<String> tname1, spid1, classname1, divname1, phno1, email1, fpath1;
    View V;
    AlertDialog pd;
    TextWatcher tv;

    @Bind(R.id.ed_search)
    EditText et_search;
    @Bind(R.id.img_search)
    ImageView iv_serach;

    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    int f = 0, flag = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        V = inflater.inflate(R.layout.teachertab, container, false);
        Home h = (Home) getActivity();
        h.getApp().getActivityComponent().inject(this);
        ButterKnife.bind(this, V);
        llhide = (LinearLayout) V.findViewById(R.id.tabhidelay);
        llshow = (LinearLayout) V.findViewById(R.id.tabaddlay);

        Home.isInHomePage = true;
        tname = new ArrayList<String>();
        spid = new ArrayList<String>();
        classname = new ArrayList<String>();
        divname = new ArrayList<String>();
        phno = new ArrayList<String>();
        email = new ArrayList<String>();
        fpath = new ArrayList<String>();
        tname1 = new ArrayList<String>();
        spid1 = new ArrayList<String>();
        classname1 = new ArrayList<String>();
        divname1 = new ArrayList<String>();
        phno1 = new ArrayList<String>();
        email1 = new ArrayList<String>();
        fpath1 = new ArrayList<String>();
        deleteInterface = this;
        if (V != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(V.getWindowToken(), 0);
        }


        plus = (ImageView) V.findViewById(R.id.img_addteacher);
        plus.setVisibility(View.INVISIBLE);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llhide.setVisibility(v.GONE);
                llshow.setVisibility(v.VISIBLE);
                loadFragment2(new AddTeacheFragment());

            }
        });


        et_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getActivity().getCurrentFocus() == et_search) {
                    String n = et_search.getText().toString();

                    displayteacher(n);
                    View view = getActivity().getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        iv_serach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_search.getText().toString();

                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                displayteacher(name);


            }
        });


        lv = (ListView) V.findViewById(R.id.teacher_list);


        int sid = appPreferences.getInt("scid");
        scid = Integer.toString(sid);
        try {
            display();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return V;


    }

    private void loadFragment2(AddTeacheFragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.addToBackStack("teacher");
        fragmentTransaction.replace(R.id.frameadd_lay, fragment);

        fragmentTransaction.commit();
    }

    @Override
    public void onDelete(final String tid) {
        pd = new SpotsDialog(getActivity());
        try {


            pd.show();

            Call<Mod_Del_treacher> call = utils.getApi().delteacher(tid, scid);

            call.enqueue(new Callback<Mod_Del_treacher>() {
                @Override
                public void onResponse(Call<Mod_Del_treacher> call, Response<Mod_Del_treacher> response) {
                    if (response.isSuccess()) {

                        if (response.body().getMsg().equals("Success")) {
                            //  utils.toToast(response.body().getMsg());
                            pd.dismiss();
                            utils.toToast("Teacher deleted successfully");
                            int c = lv.getCount();
                            if (c == 1)
                                lv.setAdapter(null);
                            display();
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
                public void onFailure(Call<Mod_Del_treacher> call, Throwable t) {
                    //  utils.toToast("Network failure");
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

    public void display() {
        try {
            pd = new SpotsDialog(getActivity());
            pd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Call<Mod_Teacher_List> call = utils.getApi().teacherlist(scid);

        call.enqueue(new Callback<Mod_Teacher_List>() {
            @Override
            public void onResponse(Call<Mod_Teacher_List> call, Response<Mod_Teacher_List> response) {
                if (response.isSuccess()) {
                    if (response.body().getMsg().equals("Success")) {

                        List<Mod_Teacher_List.UserDataBean> details = response.body().getUserData();
                        // utils.toToast(response.body().getMsg());
                        if (details.size() > 0) {
                            try {
                                for (int i = 0; i < details.size(); i++) {
                                    tname.add(details.get(i).getTeacherName());
                                    spid.add(details.get(i).getTeacherSpecialId());
                                    int l = details.get(i).getTeacherClass().size();
                                    if (l > 0) {
                                        classname.add(details.get(i).getTeacherClass().get(0).getClassName());
                                        divname.add(details.get(i).getTeacherClass().get(0).getDivisionName());
                                    } else {
                                        classname.add("No Class");
                                        divname.add("No Division");
                                    }
                                    phno.add(details.get(i).getContactNumber());
                                    email.add(details.get(i).getEmail());
                                    fpath.add(details.get(i).getFilePath());
                                }
                                adapter = new teacheradapter(getActivity(), details);

                                lv.setAdapter(adapter);
                                if (pd.isShowing()) {
                                    // utils.toToast("No teacher added for the school");
                                    pd.dismiss();
                                    plus.setVisibility(View.VISIBLE);
                                }
                            } catch (NullPointerException e) {

                            }

                        } else {
                            //utils.toToast(response.body().getMsg());
                            utils.toToast("No teacher added for the school");
                            if (pd.isShowing()) {
                                pd.dismiss();
                                plus.setVisibility(View.VISIBLE);
                            }
                        }
                    } else {
                        utils.toToast(response.body().getMsg());
                        //  utils.toToast("No teacher added for the school");
                        if (pd.isShowing()) {
                            pd.dismiss();
                            plus.setVisibility(View.VISIBLE);
                        }
                    }
                }
                if (pd.isShowing()) {
                    // utils.toToast("No teacher added for the school");
                    pd.dismiss();
                    plus.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFailure(Call<Mod_Teacher_List> call, Throwable t) {
                //  utils.toToast("Network failure");
                if (pd.isShowing()) {
                    pd.dismiss();
                    plus.setVisibility(View.VISIBLE);
                }
                Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
            }

        });


    }

    public void displayteacher(String n) {


        int l = tname.size();
        int len = n.length();

        tname1.clear();
        spid1.clear();
        classname1.clear();
        divname1.clear();
        phno1.clear();
        email1.clear();
        fpath1.clear();


        for (int i = 0; i < l; i++) {

            if (n.regionMatches(true, 0, tname.get(i).toString(), 0, len)) {

                try {


                    tname1.add(tname.get(i).toString());
                    spid1.add(spid.get(i).toString());
                    classname1.add(classname.get(i).toString());
                    divname1.add(divname.get(i).toString());
                    phno1.add(phno.get(i).toString());
                    email1.add(email.get(i).toString());
                    fpath1.add(fpath.get(i).toString());
                    f = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
        if (tname1.isEmpty() && f == 1)

        {
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            utils.toToast("No teacher available for the searched name");
            f = 0;
        }
        adapter_tr = new teacheradptr(getActivity(), tname1, spid1, classname1, divname1, phno1, email1, fpath1);
        lv.setAdapter(adapter_tr);


    }

}
