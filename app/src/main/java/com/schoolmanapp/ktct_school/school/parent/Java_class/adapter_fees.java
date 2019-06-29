package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_fees;

import java.util.List;

/**
 * Created by srishtiinnovative on 16/10/17.
 */

public class adapter_fees extends BaseAdapter {
    Context mContext;
    String paid_date,newd;
            int paid_billno,amount=0;

    Bundle b;
    List<model_fees.DataBean.PaidHistoryDataBean> list;




    public adapter_fees(Context context, List<model_fees.DataBean.PaidHistoryDataBean> details) {
        this.mContext = context;
        this.list = details;
    }

    @Override
    public int getCount() {
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
        TextView tv_date,tv_amt;
        LinearLayout ll;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View v;

        if (convertView == null) {  // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.custom_fees, parent, false);

        } else {
            v = (View) convertView;
        }

        holder.tv_date=  (TextView) v.findViewById(R.id.date);
        holder.tv_amt=  (TextView) v.findViewById(R.id.paidfee);
        holder.ll=(LinearLayout) v.findViewById(R.id.ll);
        paid_date=list.get(position).getBilldate();

        paid_billno=list.get(position).getBillNo();

        String d=list.get(position).getBilldate();
        newd=d.substring(0,10);

        holder.tv_date.setText(newd);
        List<model_fees.DataBean.PaidHistoryDataBean.PaidBillsDataBean>  l = list.get(position).getPaidBillsData();
        int length=l.size();
        for(int j=0;j<length;j++)
        {
            amount+=l.get(j).getAmount();
        }

        // int a=l.get(0).getAmount();
        holder.tv_amt.setText(amount+"");
amount=0;




       holder.ll.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

//               b=new Bundle();
               Intent intent= new Intent(mContext,paidhistory_Details.class);
               intent.putExtra("position",position+"");
//               b.putString("pdate",newd);
//               b.putInt("pbillno",paid_billno);
//               b.putInt("amount",amount);
//               b.putInt("position",position);
//               intent.putExtras(b);
               mContext.startActivity(intent);
//               paid_history.deleteInterface.onDelete(position);


           }
       });

        return v;

    }


}
