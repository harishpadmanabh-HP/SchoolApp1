package com.schoolmanapp.ktct_school.school.school.common;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import javax.inject.Inject;

/**
 * Created by srishtiinnovative on 06/01/16.
 */
public class BaseFragment extends Fragment {
    @Inject
    Utils utilsPref;


    public void addFragment(int fragment_container, Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(fragment_container, fragment);
        transaction.addToBackStack(addBackStack ? fragment.getClass().getName() : null);
        transaction.commit();
    }


    public void replaceFragment(int fragment_container, Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(fragment_container, fragment);
       // transaction.addToBackStack(addBackStack ? fragment.getClass().getName() : null);
        transaction.commit();
    }


}
