package com.schoolmanapp.ktct_school.school.school;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;


import java.util.ArrayList;

/**
 * Created by srishtiinnovative on 06/06/17.
 */

class traveladapter extends BaseAdapter {
    Context context;
    ArrayList<String> place,time;
    String name,img;
    public traveladapter(Context travel, ArrayList<String> place,  ArrayList<String> time) {
        context=travel;
        this.place=place;
        this.time=time;
        this.name=name;
        this.img=img;

    }

    @Override
    public int getCount() {
        return place.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv_place,tv_time,tv_name;
        ImageView img_driver;



    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;


        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = vi.inflate(R.layout.custom_travel, null);
        holder.tv_place=(TextView) rowView.findViewById(R.id.loc);
        holder.tv_time=(TextView) rowView.findViewById(R.id.time);
try {


    holder.tv_place.setText(place.get(position));
    holder.tv_time.setText(time.get(position));
}catch(Exception e)
{
    e.printStackTrace();
}


        return rowView;
    }

}
