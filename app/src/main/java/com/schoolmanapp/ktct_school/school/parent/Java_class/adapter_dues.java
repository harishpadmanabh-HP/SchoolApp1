package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_fees;

import java.util.List;

/**
 * Created by srishtiinnovative on 16/10/17.
 */

public class adapter_dues extends BaseAdapter {
    Context mContext;

    List<model_fees.DataBean.DueHistoryDataBean> list;
//    List<model_fees.DataBean.DueHistoryDataBean> dlist;
//    int flag;
//
    int amount;



    public adapter_dues(Activity activity,List<model_fees.DataBean.DueHistoryDataBean> details) {
        mContext = activity;
        this.list = details;
    }
//    public adapter_details(Activity activity,List<model_fees.DataBean.DueHistoryDataBean> details,int flag) {
//        mContext = activity;
//        this.dlist = details;
//        this.flag=flag;
//    }


    @Override
    public int getCount() {
//        if(flag==1)
//        return dlist.size();
//
//        else
        return list.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class Holder
    {
        TextView tv_slno,tv_particulars,tv_amt;
   TextView tv_details;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View v;

        if (convertView == null) {  // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.custom_feelist, parent, false);






        } else {
            v = (View) convertView;
        }


        holder.tv_slno=  (TextView) v.findViewById(R.id.slno);
        holder.tv_particulars=  (TextView) v.findViewById(R.id.particular);
        holder.tv_details=  (TextView) v.findViewById(R.id.fee_details);
        holder.tv_amt=  (TextView) v.findViewById(R.id.fee_amount);
//            holder.tv_slno.setText(position+1);
        String part=list.get(position).getParticulars();
        String details=list.get(position).getDetails();
        int amt=list.get(position).getAmount();
        holder.tv_slno.setText(position+1+"");
        holder.tv_particulars.setText(part);
        holder.tv_details.setVisibility(View.VISIBLE);
        holder.tv_details.setText(details);
        holder.tv_amt.setText(amt+"");






        return v;

    }


}
