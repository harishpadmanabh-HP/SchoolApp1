package com.schoolmanapp.ktct_school.school.school.tracking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;

import java.util.ArrayList;

/**
 * Created by srishtiinnovative on 31/05/17.
 */

class BusListAdapter extends BaseAdapter {
    ArrayList<String> busrgno;
    ArrayList<String> bid;
    ArrayList<String> btype;
    ArrayList<String> btripno;
    ArrayList<String> start;
    ArrayList<String> end;
    ArrayList<Integer> status;

    Context context;

    public BusListAdapter(Context bus, ArrayList<String> busrgno, ArrayList<String>bid, ArrayList<String> btype, ArrayList<String> btripno, ArrayList<String> start , ArrayList<String> end,ArrayList<Integer> status) {
        context=bus;
        this.busrgno=busrgno;
        this.bid=bid;
        this.btype=btype;
        this.btripno=btripno;
        this.start=start;
        this.end=end;
        this.status=status;
    }

    @Override
    public int getCount() {
        return busrgno.size();
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
        RelativeLayout mainLayout;
                LinearLayout llNoOfTrips;
        TextView tv_brgno,tv_bid,tv_btype,tv_btripno,loc,city;
        ImageView del_img,st_rest,st_run;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = vi.inflate(R.layout.custom_bustlist_status, null);
//        holder.llNoOfTrips=(LinearLayout) rowView.findViewById(R.id.llNoOfTrips);
        holder.mainLayout=(RelativeLayout) rowView.findViewById(R.id.mainLayout);
        holder.tv_brgno=(TextView) rowView.findViewById(R.id.bregno);
//        holder.tv_bid=(TextView) rowView.findViewById(R.id.bid);
//        holder.tv_btype=(TextView) rowView.findViewById(R.id.btype);
//        holder.tv_btripno=(TextView) rowView.findViewById(R.id.btripno);
//        holder.del_img = (ImageView) rowView.findViewById(R.id.img_del);
                holder.st_rest = (ImageView) rowView.findViewById(R.id.bus_stop);
        holder.st_run = (ImageView) rowView.findViewById(R.id.bus_run);
        holder.loc=(TextView) rowView.findViewById(R.id.location);
//        holder.city=(TextView) rowView.findViewById(R.id.city);
       holder.tv_brgno.setText(busrgno.get(position));
//        holder.tv_bid.setText(bid.get(position));
//        holder.tv_btype.setText(btype.get(position));
//        holder.tv_btripno.setText(btripno.get(position));
        holder.loc.setText(start.get(position));
//        holder.city.setText(end.get(position));
//        holder.del_img.setVisibility(View.GONE);
        int val=status.get(position);
        if(val==2  || val==3) {
            holder.st_run.setVisibility(View.GONE);
            holder.st_rest.setVisibility(View.VISIBLE);
        }
        else if(val==1 || val==0 ||val==3)
        {
            holder.st_run.setVisibility(View.VISIBLE);
            holder.st_rest.setVisibility(View.GONE);
        }
//        holder.llNoOfTrips.setVisibility(View.GONE);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, MapsActivity.class);
                i.putExtra("BusId",bid.get(position));
                i.putExtra("status",status.get(position));



                context.startActivity(i);
            }
        });
        return rowView;
    }
}
