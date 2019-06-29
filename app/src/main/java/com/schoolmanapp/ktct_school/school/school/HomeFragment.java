package com.schoolmanapp.ktct_school.school.school;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.schoolmanapp.ktct_school.school.R;

/**
 * Created by srishtiinnovative on 01/06/17.
 */

public class HomeFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_main,container,false);

       // loadFragment2(new Classdiv());
        return view;
    }

//    private  void loadFragment2(Classdiv fragment)
//    {
//
//
//
//        android.app.FragmentManager fm=getFragmentManager();
//        android.app.FragmentTransaction fragmentTransaction=fm.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_lay,fragment);
//        fragmentTransaction.commit();
//    }
}
