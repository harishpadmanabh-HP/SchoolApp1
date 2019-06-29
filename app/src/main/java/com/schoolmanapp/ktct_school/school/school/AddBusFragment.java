package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Add_Bus;
import com.schoolmanapp.ktct_school.school.school.common.BaseFragment;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

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

public class AddBusFragment extends BaseFragment implements Validator.ValidationListener{


    View view;
    LinearLayout llhide,llshow;
    TextView iv;
    Validator validator;
    Bundle bundle;
    AlertDialog pd;
    @NotEmpty
   @Bind(R.id.bustype)
   EditText type;
    @NotEmpty
    @Bind(R.id.bustripno)
    EditText btrpno;
    @Bind(R.id.busloc)
    @NotEmpty
    EditText bloc;
    @NotEmpty
    @Bind(R.id.busname)
    EditText bname;
    @Bind(R.id.imgback)
    ImageView iv_back;
String b_name,b_tpno,b_loc,b_type;
    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    String scid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.addbus,container,false);
        ButterKnife.bind(this,view);
        validator = new Validator(this);
        validator.setValidationListener(this);
        bundle=new Bundle();
        llhide=(LinearLayout)view.findViewById(R.id.tabhidelay);
        llshow=(LinearLayout)view.findViewById(R.id.tabaddlay);
        Home h=(Home) getActivity();
        h.getApp().getActivityComponent().inject(this);
//        h.tabview2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment3(new Bus());
//
//            }
//        });

        int sid = appPreferences.getInt("scid");
        scid = Integer.toString(sid);

        iv=(TextView)view.findViewById(R.id.addbus_img);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         view=inflater.inflate(R.layout.addclass,container,false);
                validator.validate();
//                llhide.setVisibility(v.GONE);
//                llshow.setVisibility(v.VISIBLE);

            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment3(new Bus());
            }
        });
        return view;

    }

    private  void loadFragment3(Fragment fragment)
    {
try {
    if (view != null) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    FragmentManager fm = getFragmentManager();


    FragmentTransaction fragmentTransaction = fm.beginTransaction();

    fragmentTransaction.replace(R.id.frameadd_lay, fragment);
    fragmentTransaction.commit();
}catch (Exception e)
{
    e.printStackTrace();
}

    }


    private  void loadFragment2(Fragment fragment)
    {
        fragment.setArguments(bundle);

        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        FragmentManager fm=getFragmentManager();


        FragmentTransaction fragmentTransaction=fm.beginTransaction();

        fragmentTransaction.replace(R.id.frameadd_lay,fragment);
        fragmentTransaction.commit();

        pd.dismiss();
    }


    @Override
    public void onValidationSucceeded() {
        b_loc = bloc.getText().toString();
        b_name = bname.getText().toString();
        b_tpno = btrpno.getText().toString();
        b_type = type.getText().toString();
        pd = new SpotsDialog(getActivity());
        try {
            pd.show();

            Call<Mod_Add_Bus> call = utils.getApi().addbus(scid, b_name, b_tpno, b_loc, b_type);


            call.enqueue(new Callback<Mod_Add_Bus>() {

                @Override
                public void onResponse(Call<Mod_Add_Bus> call, Response<Mod_Add_Bus> response) {
                    if (response.isSuccess()) {
                        //   utils.toToast(response.body().getMsg());


                        String msg = response.body().getMsg();
                        if (msg.equals("Success")) {
                            String tid = response.body().getUserData();
                            //   Log.e("Busid",tid);

                            bundle.putString("bid", tid);
                            utils.toToast("Bus Added Successfully");

                            loadFragment2(new Fragment_cfmBus());


                        } else {
                            if (pd.isShowing()) {
                                pd.dismiss();
                            }

                            utils.toToast(response.body().getMsg());
                            // loadFragment2(new Fragment_cfmBus());


                        }
                    } else {
                        if (pd.isShowing()) {
                            pd.dismiss();
                        }

                        utils.toToast(response.body().getMsg());
                        // loadFragment2(new Fragment_cfmBus());


                    }


                }

                @Override
                public void onFailure(Call<Mod_Add_Bus> call, Throwable t) {
                    if (pd.isShowing()) {
                        pd.dismiss();
                    }

                    utils.toToast(t.getMessage());
                }

            });
        }
        catch(Exception e)
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





}