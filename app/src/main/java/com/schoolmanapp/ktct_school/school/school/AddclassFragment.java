package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Add_Classdiv;
import com.schoolmanapp.ktct_school.school.school.common.BaseFragment;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;


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
 * Created by srishtiinnovative on 01/06/17.
 */

public class AddclassFragment extends BaseFragment implements Validator.ValidationListener {
    View view;
    LinearLayout llhide, llshow;
    TextView iv;
    Validator validator;
    AlertDialog pd;

    //EditText et_class,et_div;
    String sclass, sdiv;


    @Bind(R.id.sp_class)
    Spinner sp_class;

    @Bind(R.id.imgback)
    ImageView iv_back;

    @NotEmpty
    @Bind(R.id.et_adddivision)
    EditText et_div;

    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    String scid,class_pos;
    ArrayAdapter<String> class_adapter;
    ArrayList<String> class_sp;
    Integer position_classspinner=0;
    String clss=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.addclass, container, false);
        ButterKnife.bind(this, view);
        validator = new Validator(this);
        validator.setValidationListener(this);
        llhide = (LinearLayout) view.findViewById(R.id.tabhidelay);
        llshow = (LinearLayout) view.findViewById(R.id.tabaddlay);
        iv = (TextView) view.findViewById(R.id.imgbtn_createclass);
       class_sp=new ArrayList<String>();
       class_sp.add("Select Class");
        class_sp.add("Pre-KG");
       class_sp.add("LKG");
        class_sp.add("UKG");
        class_sp.add("I");
        class_sp.add("II");
        class_sp.add("III");
        class_sp.add("IV");
        class_sp.add("V");
        class_sp.add("VI");
        class_sp.add("VII");
        class_sp.add("VIII");
        class_sp.add("IX");
        class_sp.add("X");
        class_sp.add("XI");
        class_sp.add("XII");
        class_sp.add("XI-CS1");
        class_sp.add("XI-BS");
        class_sp.add("XI-CC");
        class_sp.add("XII-CS");
        class_sp.add("XII-BS");
        class_sp.add(" XII-CC");
        final boolean[] ans = new boolean[1];

        Home h = (Home) getActivity();
        h.getApp().getActivityComponent().inject(this);
//        h.tabview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment3(new Classdiv());
//
//
//            }
//        });
        class_adapter= new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, class_sp);

        // Drop down layout style - list view with radio button
        class_adapter.setDropDownViewResource(R.layout.custom_dropdown);

        // attaching data adapter to spinner
//        try {
//            gender_adapter .notifyDataSetChanged();
//        } catch (Exception e) {
//            utils.toToast(e+"");
//        }
        try {
            sp_class.setAdapter(class_adapter);
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        class_adapter.notifyDataSetChanged();
        sp_class.setSelection(0, false);
        sp_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position>0) {
                    position_classspinner=position;
                    class_pos = class_sp.get(position);
//                    notrips = Integer.parseInt(tripnoofbus.get(position));
//                    trip(notrips);
                }
                else
                {
                    TextView errorText = (TextView)sp_class.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Please select Class");//changes the selected item text to this
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        int sid = appPreferences.getInt("scid");
        scid = Integer.toString(sid);
iv_back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        loadFragment3(new Classdiv());
    }
});

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        view=inflater.inflate(R.layout.addclass,container,false);

                validator.validate();
                ans[0] = spinner_validation();
            }
        });


        return view;
    }

    public void loadFragment3(Classdiv fragment) {
        try {
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            FragmentManager fm = getFragmentManager();


            FragmentTransaction fragmentTransaction = fm.beginTransaction();

            fragmentTransaction.replace(R.id.frameadd_lay, fragment);
            fragmentTransaction.commit();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void loadFragment2(Classdiv fragment) {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        FragmentManager fm = getFragmentManager();


        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.frameadd_lay, fragment);
        fragmentTransaction.commit();
        pd.dismiss();
    }





    @Override
    public void onValidationSucceeded() {
        spinner_validation();
//          llhide.setVisibility(v.GONE);
//          llshow.setVisibility(v.VISIBLE);


//        sclass = et_class.getText().toString();
        sdiv = et_div.getText().toString();
        pd = new SpotsDialog(getActivity());
        try {
            pd.show();
            switch (class_pos){
                case"Pre-KG":
                    clss ="1";
                    break;
                case"LKG":
                    clss ="2";
                    break;
                case"UKG":
                    clss ="3";
                    break;
                case"I":
                    clss ="4";
                    break;
                case"II":
                    clss ="5";
                    break;
                case"III":
                    clss ="6";
                    break;
                case"IV":
                    clss ="7";
                    break;
                case"V":
                    clss ="8";
                    break;
                case"VI":
                    clss ="9";
                    break;
                case"VII":
                    clss ="10";
                    break;
                case"VIII":
                    clss ="11";
                    break;
                case"IX":
                    clss ="12";
                    break;
                case"X":
                    clss ="13";
                    break;
                case"XI":
                    clss ="14";
                    break;
                case"XII":
                    clss ="15";
                    break;
                case"XI-CS1":
                    clss ="16";
                    break;
                case"XI-BS":
                    clss ="17";
                    break;
                case"XI-CC":
                    clss ="18";
                    break;
                case"XII-CS":
                    clss ="19";
                    break;
                case"XII-BS":
                    clss ="20";
                    break;
                case"XII-CC":
                    clss ="21";
                    break;
            }
            Call<Mod_Add_Classdiv> call = utils.getApi().addclassdivision(scid,clss, sdiv);


            call.enqueue(new Callback<Mod_Add_Classdiv>() {
                @Override
                public void onResponse(Call<Mod_Add_Classdiv> call, Response<Mod_Add_Classdiv> response) {
                    if (response.isSuccess()) {
                        //


                        String msg = response.body().getMsg();
                        if (msg.equals("Success")) {
                            //utils.toToast(response.body().getMsg());
                            utils.toToast("Class Added Successfully");
                            loadFragment2(new Classdiv());

                        } else {
                            if (pd.isShowing()) {
                                pd.dismiss();
                            }
                            // utils.toToast(response.body().getMsg());
                            utils.toToast("Adding class failed");
                            loadFragment2(new Classdiv());


                        }
                    } else {
                        if (pd.isShowing()) {
                            pd.dismiss();
                        }
                        // utils.toToast(response.body().getMsg());
                        utils.toToast("Adding class failed");
                        loadFragment2(new Classdiv());


                    }

                }

                @Override
                public void onFailure(Call<Mod_Add_Classdiv> call, Throwable t) {
                    if (pd.isShowing()) {
                        pd.dismiss();
                    }
                    utils.toToast("Network failure");
                }

            });
        }catch (Exception e)
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
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        }


    }
    public Boolean spinner_validation()

    {
//        if (spinbusid_pos > 0 && spintrip_pos > 0 && position_genderspinner>0 )
        if ( position_classspinner>0 ){

            return true;
        } else {
//            if (spinbusid_pos <= 0) {
//                TextView errorText = (TextView) spbus.getSelectedView();
//                errorText.setError("");
//                errorText.setTextColor(Color.RED);//just to highlight that this is an error
//                errorText.setText("Please select a Bus");//changes the selected item text to this
//            }
//            if (spintrip_pos <=0) {
//                TextView errorText = (TextView) sp_trip.getSelectedView();
//                errorText.setError("");
//                errorText.setTextColor(Color.RED);//just to highlight that this is an error
//                errorText.setText("Please select a trip");//changes the selected item text to this
//            }
            if (position_classspinner<=0) {
                TextView errorText = (TextView) sp_class.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Please select class");//changes the selected item text to this
            }


            return false;
        }


    }


}


