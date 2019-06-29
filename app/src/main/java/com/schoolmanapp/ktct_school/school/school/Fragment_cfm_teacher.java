package com.schoolmanapp.ktct_school.school.school;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 02/06/17.
 */

public class Fragment_cfm_teacher extends Fragment {
    TextView iv;
    ProgressDialog pd;
    View view;
    @Inject
    Utils util;
    @Inject
    AppPreferences appPreferences;

    TextView tv_tid;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cfm_addteacher, container, false);
        ButterKnife.bind(this,view);
        tv_tid=(TextView)view.findViewById(R.id.cfm_tid);
        Home h=(Home)getActivity();
        h.getApp().getActivityComponent().inject(this);
        pd = ProgressDialog.show(getActivity(), "Please Wait...","",true, false);
        pd.show();
        if(pd.isShowing())
        {
            pd.dismiss();
        }
        Bundle args =this.getArguments();
        if (args != null) {
            Log.e("Teacherid",args.getString("tid"));
            tv_tid.setText(args.getString("tid"));
        }
        iv=(TextView) view.findViewById(R.id.cfm_addteacher_img);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         view=inflater.inflate(R.layout.addclass,container,false);
                loadFragment2(new Teacher());

            }
        });
        return view;
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        Bundle args = getArguments();
//        if (args != null) {
//            tv_tid.setText(args.getString("tid"));
//        }
//    }
    private  void loadFragment2(Teacher fragment)
    {
        FragmentManager fm=getFragmentManager();


        FragmentTransaction fragmentTransaction=fm.beginTransaction();

        fragmentTransaction.replace(R.id.frameadd_lay,fragment);
        fragmentTransaction.commit();
    }
}
