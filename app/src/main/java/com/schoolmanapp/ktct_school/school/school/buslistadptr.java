package com.schoolmanapp.ktct_school.school.school;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;

/**
 * Created by srishtiinnovative on 05/06/17.
 */

public class buslistadptr extends BaseAdapter {

    Context context;
    String busrgno[],btype[],btripno[],loc[],city[];
    public buslistadptr(Context bus, String[] busrgno, String[] btype, String[] btripno, String[] loc, String[] city) {
        context=bus;
        this.busrgno=busrgno;
        this.btype=btype;
        this.btripno=btripno;
        this.loc=loc;
        this.city=city;
    }

    @Override
    public int getCount() {
        return busrgno.length;
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
        TextView tv_brgno,tv_btype,tv_btripno,loc,city;
        //  ImageView icon,detail;
         LinearLayout ll;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;



        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = vi.inflate(R.layout.customlistbus, null);
        holder.ll=(LinearLayout)rowView.findViewById(R.id.ll_list);
        holder.tv_brgno=(TextView) rowView.findViewById(R.id.bregno);
        holder.tv_btype=(TextView) rowView.findViewById(R.id.btype);
        holder.tv_btripno=(TextView) rowView.findViewById(R.id.btripno);

        holder.loc=(TextView) rowView.findViewById(R.id.location);

        holder.city=(TextView) rowView.findViewById(R.id.city);
//holder.ll.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        loadFragment(new Fargmenttrack());
//    }
//});


        // holder.detail=(ImageView)rowView.findViewById(R.id.img_detail);
//        holder.ll=(LinearLayout)rowView.findViewById(R.id.ll_list);

        holder.tv_brgno.setText(busrgno[position]);
        holder.tv_btype.setText(btype[position]);
        holder.tv_btripno.setText(btripno[position]);
        holder.loc.setText(loc[position]);
        holder.city.setText(city[position]);
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
        });
        return rowView;
    }
//
}
