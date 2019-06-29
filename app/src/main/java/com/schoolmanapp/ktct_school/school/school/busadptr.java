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
 * Created by srishtiinnovative on 31/05/17.
 */

class busadptr extends BaseAdapter {
    ArrayList<String> busrgno;
    ArrayList<String> bid;
    ArrayList<String> btype;
    ArrayList<String> btripno;
    ArrayList<String> start;
    ArrayList<String> end;

    Context context;

    public busadptr(Context bus,  ArrayList<String> busrgno, ArrayList<String>bid,ArrayList<String> btype,ArrayList<String> btripno, ArrayList<String> start , ArrayList<String> end) {
        context=bus;
        this.busrgno=busrgno;
        this.bid=bid;
        this.btype=btype;
        this.btripno=btripno;
        this.start=start;
        this.end=end;
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
        TextView tv_brgno,tv_bid,tv_btype,tv_btripno,loc,city;
        //  ImageView icon,detail;
        // LinearLayout ll;
        ImageView del_img;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;



        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = vi.inflate(R.layout.custombus, null);
        holder.tv_brgno=(TextView) rowView.findViewById(R.id.bregno);
        holder.tv_bid=(TextView) rowView.findViewById(R.id.bid);
        holder.tv_btype=(TextView) rowView.findViewById(R.id.btype);
        holder.tv_btripno=(TextView) rowView.findViewById(R.id.btripno);
        holder.del_img = (ImageView) rowView.findViewById(R.id.img_del);
        holder.loc=(TextView) rowView.findViewById(R.id.location);

        holder.city=(TextView) rowView.findViewById(R.id.city);


        holder.tv_brgno.setText(busrgno.get(position));
        holder.tv_bid.setText(bid.get(position));
        holder.tv_btype.setText(btype.get(position));
        holder.tv_btripno.setText(btripno.get(position));
        holder.loc.setText(start.get(position));
      //  holder.city.setText(end.get(position));
        holder.del_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Bus.deleteInterface.onDelete(position);

            }
        });
        return rowView;
    }
}
