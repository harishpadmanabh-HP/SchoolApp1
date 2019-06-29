package com.schoolmanapp.ktct_school.school.school;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;

import java.util.ArrayList;

/**
 * Created by srishtiinnovative on 16/06/17.
 */

class classviewadapter extends BaseAdapter {
    Context context;

    ArrayList<String> classname, division;

    public classviewadapter(Context classview, ArrayList<String> studclass, ArrayList<String> division) {

        context = classview;
        this.classname = studclass;
        this.division = division;

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

    public class Holder {
        TextView tv_class, tv_division;
        LinearLayout ll;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder = new Holder();
        View rowView;
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = vi.inflate(R.layout.customdisplayclass, null);
        holder.tv_class = (TextView) rowView.findViewById(R.id.txt_class);
        holder.tv_division = (TextView) rowView.findViewById(R.id.txt_div);
        holder.ll = (LinearLayout) rowView.findViewById(R.id.ll_class);
        try {


            holder.tv_class.setText(classname.get(position));
            holder.tv_division.setText(division.get(position));
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Displayclass.listclasses.viewclass(position);
                }
            });
        } catch (NullPointerException e) {

        }
        return rowView;
    }

}
