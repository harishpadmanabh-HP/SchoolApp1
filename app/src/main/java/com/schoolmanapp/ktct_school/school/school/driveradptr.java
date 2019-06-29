package com.schoolmanapp.ktct_school.school.school;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
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
 * Created by srishtiinnovative on 01/06/17.
 */

class driveradptr extends BaseAdapter {
    Context context;
    ArrayList<String> dname,dphone,fpath,dlcno,dspid,daddr,dcity;

    public driveradptr(FragmentActivity driver, ArrayList<String> dspid, ArrayList<String> dname, ArrayList<String> dphone, ArrayList<String> dlcno, ArrayList<String> daddr, ArrayList<String> dcity, ArrayList<String> fpath) {
        context=driver;
        this.dspid=dspid;
        this.dname=dname;
        this.dphone=dphone;
        this.dlcno=dlcno;
        this.daddr=daddr;
        this.dcity=dcity;
        this.fpath=fpath;
    }

    @Override
    public int getCount() {
        return dspid.size();
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
        TextView tv_name,tv_did,tv_phno,tv_lcno,tv_loc,tv_city;
          ImageView icon,img_del;
        // LinearLayout ll;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;



        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = vi.inflate(R.layout.customdriver, null);
        holder.tv_name=(TextView) rowView.findViewById(R.id.dname);
        holder.tv_did=(TextView) rowView.findViewById(R.id.did);
        holder.tv_phno=(TextView) rowView.findViewById(R.id.dphno);
        holder.tv_lcno=(TextView) rowView.findViewById(R.id.d_lcno);

        holder.tv_loc=(TextView) rowView.findViewById(R.id.location);
        holder.icon=(ImageView)rowView.findViewById(R.id.img_driver);
        holder.img_del=(ImageView)rowView.findViewById(R.id.del_dr_img);

        holder.tv_city=(TextView) rowView.findViewById(R.id.city);



        // holder.detail=(ImageView)rowView.findViewById(R.id.img_detail);
//        holder.ll=(LinearLayout)rowView.findViewById(R.id.ll_list);

        holder.tv_name.setText(dname.get(position));
        holder.tv_did.setText(dspid.get(position));
        holder.tv_phno.setText(dphone.get(position));
        holder.tv_lcno.setText(dlcno.get(position));
        holder.tv_loc.setText(daddr.get(position));
        holder.tv_city.setText(dcity.get(position));
        String path = "http://www.schoolman.in/api/"+fpath.get(position);
        Picasso.with(context).load(path).into(holder.icon);

        holder.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Driver.deleteInterface.onDelete(position);
            }
        });

        return rowView;
    }
}
