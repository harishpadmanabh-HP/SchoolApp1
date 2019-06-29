package com.schoolmanapp.ktct_school.school.school;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;

import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 02/06/17.
 */

public class Fragment_cfmDriver extends Fragment {
    TextView iv;
    View view;

    TextView tv_did;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cfm_adddriver, container, false);
        iv=(TextView)view.findViewById(R.id.cfm_adddriver_img);
        ButterKnife.bind(this,view);
        tv_did=(TextView)view.findViewById(R.id.did);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         view=inflater.inflate(R.layout.addclass,container,false);
                loadFragment2(new Driver());

            }
        });
        Bundle args =this.getArguments();
        if (args != null) {
            tv_did.setText(args.getString("did"));
        }
        return view;
    }
    private  void loadFragment2(Driver fragment)
    {
        FragmentManager fm=getFragmentManager();


        FragmentTransaction fragmentTransaction=fm.beginTransaction();

        fragmentTransaction.replace(R.id.frameadd_lay,fragment);
        fragmentTransaction.commit();
    }
}
