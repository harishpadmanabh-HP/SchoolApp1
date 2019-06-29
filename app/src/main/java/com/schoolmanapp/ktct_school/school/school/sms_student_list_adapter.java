package com.schoolmanapp.ktct_school.school.school;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;

import java.util.ArrayList;

public class sms_student_list_adapter extends BaseAdapter {
    Context context;
    CheckBox select;
    ArrayList<String> classname, division;
    ArrayList<Integer> clsid, divid;
    ArrayList<Boolean> status;
    TextView tv_class, tv_division;
    LinearLayout ll;
    Integer s;
    ArrayList pos = new ArrayList();

    public sms_student_list_adapter(Application application, ArrayList<String> classname, ArrayList<String> division, ArrayList<Integer> cid, ArrayList<Integer> did, ArrayList<Boolean> status) {
        context = application;
        this.classname = classname;
        this.division = division;
        this.clsid = cid;
        this.divid = did;
        this.status = status;
    }

    @Override
    public int getCount() {
        return classname.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = vi.inflate(R.layout.custom_sms_std_list, null);
        tv_class = (TextView) convertView.findViewById(R.id.txt_class);
        tv_division = (TextView) convertView.findViewById(R.id.txt_div);
        select = (CheckBox) convertView.findViewById(R.id.checkBox_select);
        ll = (LinearLayout) convertView.findViewById(R.id.ll_classdiv);
        tv_class.setText(classname.get(position));
        tv_division.setText(division.get(position));
//         s =position;
//        for(int i =0;i<pos.size();i++) {
//            if (pos.get(i) == s) {
//                select.setChecked(true);
//            }
//        }
        if (status.get(position) == false) {
            select.setChecked(false);
        } else {
            select.setChecked(true);
        }


        select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    status.set(position, true);
                    Sms_school.onchecked.onchecked(clsid.get(position), divid.get(position), status, position);


                } else {
                    status.set(position, false);
                    Sms_school.onchecked.unchecked(clsid.get(position), divid.get(position), status, position);
//                    pos.remove(position);
//                    select.setChecked(false);

                }
            }
        });


        return convertView;
    }
}
