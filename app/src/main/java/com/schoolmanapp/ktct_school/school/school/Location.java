package com.schoolmanapp.ktct_school.school.school;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.schoolmanapp.ktct_school.school.R;

/**
 * Created by srishtiinnovative on 31/05/17.
 */

//public class Location extends Activity {
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.location);
//    }
//}
public class Location extends Fragment {
    ListView lv;
    String studclass[]={"1","2"};
    String division[]={"A","B"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.classdivtab, container, false);

        lv=(ListView)V.findViewById(R.id.class_list);
      //  classadapter adapter=new classadapter(getActivity(),studclass,division);


    //    lv.setAdapter(adapter);


        return V;
    }


}