package com.schoolmanapp.ktct_school.school.school;

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

import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 02/06/17.
 */

public class Fragment_cfm_stud extends Fragment {
    TextView iv;
    Bundle b;

    TextView tv_sid;
    int cid,did;
    String class_name,division_name;
    View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cfm_addstud, container, false);
        iv=(TextView)view.findViewById(R.id.cfm_addstud_img);
        ButterKnife.bind(this,view);
        b=new Bundle();
        tv_sid=(TextView)view.findViewById(R.id.sid);
        Bundle args =this.getArguments();
        if (args != null) {
            Log.e("studid",args.getString("sid"));
            cid=args.getInt("cl_id");
            did=args.getInt("d_id");
            class_name=args.getString("cl_name");
            division_name=args.getString("d_name");
            tv_sid.setText(args.getString("sid"));
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         view=inflater.inflate(R.layout.addclass,container,false);
                b.putInt("classid",cid);
                b.putInt("divid",did);
                b.putString("classname",class_name);
                b.putString("divname",division_name);
                loadFragment2(new Student());

            }
        });

        return view;
    }
    private  void loadFragment2(Student fragment)
    {
        fragment.setArguments(b);
             FragmentManager fm=getFragmentManager();


        FragmentTransaction fragmentTransaction=fm.beginTransaction();

        fragmentTransaction.replace(R.id.frameadd_lay,fragment);
        fragmentTransaction.commit();
    }
}
