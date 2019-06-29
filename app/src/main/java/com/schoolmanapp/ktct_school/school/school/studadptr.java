package com.schoolmanapp.ktct_school.school.school;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by srishtiinnovative on 31/05/17.
 */

class studadptr extends BaseAdapter {
    Context context;
    ArrayList<String> sname;
    ArrayList<String> sphno;
    ArrayList<String> sparname;
    ArrayList<String> saddr;
    ArrayList<String> scity;
    ArrayList<String> sclass;
    ArrayList<String> sdiv;
    ArrayList<String> sfpath;
    ArrayList<String> stid;
    ArrayList<String> bid;

    public studadptr(Context st, ArrayList<String> sname, ArrayList<String> sphno, ArrayList<String> sparname, ArrayList<String> sclass, ArrayList<String> sdiv, ArrayList<String> saddr, ArrayList<String> scity, ArrayList<String> sfpath, ArrayList<String> stid, ArrayList<String> bid) {
        context = st;
        this.sname = sname;
        this.sphno = sphno;
        this.saddr = saddr;
        this.scity = scity;
        this.sclass = sclass;
        this.sparname = sparname;
        this.sdiv = sdiv;
        this.sfpath = sfpath;
        this.stid = stid;
        this.bid = bid;

    }


    @Override
    public int getCount() {
        return sname.size();
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
        TextView tv_class, tv_sid, tv_division, tv_name, tv_phone, tv_parent, tv_loc, tv_city, tv_busid,tv_dob,tv_gndr,tv_bldgrp;

        ImageView icon,img_del;
        // LinearLayout ll;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
    //    View rowView=convertView;
        View rowView;


       // if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = vi.inflate(R.layout.customstudent, null);
            holder.tv_name = (TextView) rowView.findViewById(R.id.sname);
            holder.tv_sid = (TextView) rowView.findViewById(R.id.sid);
//            holder.tv_class = (TextView) rowView.findViewById(R.id.sclass);
//            holder.tv_division = (TextView) rowView.findViewById(R.id.sdiv);

            holder.tv_phone = (TextView) rowView.findViewById(R.id.sphno);

            holder.tv_parent = (TextView) rowView.findViewById(R.id.sparname);
            holder.tv_loc = (TextView) rowView.findViewById(R.id.slocation);

            holder.tv_city = (TextView) rowView.findViewById(R.id.scity);
            holder.tv_busid = (TextView) rowView.findViewById(R.id.sbusid);
            holder.icon = (ImageView) rowView.findViewById(R.id.stud_img);
            holder.img_del=(ImageView)rowView.findViewById(R.id.img_del_stud);
            holder.tv_dob= (TextView) rowView.findViewById(R.id.sdob);
            holder.tv_gndr= (TextView) rowView.findViewById(R.id.sgendr);
            holder.tv_bldgrp= (TextView) rowView.findViewById(R.id.sblood);



            holder.tv_name.setText(sname.get(position).toString());
            holder.tv_sid.setText(stid.get(position).toString());
//            holder.tv_class.setText(sclass.get(position).toString());
//            holder.tv_division.setText(sdiv.get(position).toString());
            holder.tv_phone.setText(sphno.get(position).toString());
            holder.tv_parent.setText(sparname.get(position).toString());
        holder.tv_busid.setText(bid.get(position).toString());


            holder.tv_loc.setText(saddr.get(position).toString());
            holder.tv_city.setText(scity.get(position).toString());

            String path = "http://www.schoolman.in/api/" + sfpath.get(position);
            Picasso.with(context).load(path).into(holder.icon);
       // }

        holder.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Student.deleteInterface.onDelete(position);
            }
        });
        return rowView;


    }

}
