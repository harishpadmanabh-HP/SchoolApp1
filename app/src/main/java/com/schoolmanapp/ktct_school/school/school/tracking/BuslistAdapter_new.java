package com.schoolmanapp.ktct_school.school.school.tracking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.school.Model.Mod_cur_running_bus;
import com.schoolmanapp.ktct_school.school.R;

import java.util.List;

/**
 * Created by srishtiinnovative on 13/07/17.
 */

public class BuslistAdapter_new extends ArrayAdapter<Mod_cur_running_bus.UserDataBean> {
    private LayoutInflater mInflater;
    private Context context;


    public BuslistAdapter_new(Context context, List<Mod_cur_running_bus.UserDataBean> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);

    }


    private static class ViewHolder {
        public final LinearLayout linearLayout;
        public final TextView tv_busrgno;
        public final TextView tv_bid;
        public final TextView tv_btripno;
        public final TextView tv_btype;
        public final TextView tv_start;
        public final TextView tv_end;


        public final ImageView icon;



        private ViewHolder(LinearLayout linearLayout, TextView tv_busrgno, TextView tv_bid, TextView tv_btripno, TextView tv_btype, TextView tv_start, TextView tv_end, ImageView icon) {
            this.linearLayout = linearLayout;
            this.tv_busrgno = tv_busrgno;
            this.tv_bid = tv_bid;
            this.tv_btripno = tv_btripno;
            this.tv_btype = tv_btype;
            this.tv_start = tv_start;

            this.tv_end = tv_end;
            this.icon=icon;

        }

        public static BuslistAdapter_new.ViewHolder create(LinearLayout linearLayout) {
            TextView tv_busrgno = (TextView) linearLayout.findViewById(R.id.bregno);
            TextView tv_bid = (TextView) linearLayout.findViewById(R.id.bid);

            TextView tv_btripno = (TextView) linearLayout.findViewById(R.id.btripno);
            TextView tv_btype = (TextView) linearLayout.findViewById(R.id.btype);
            TextView tv_start = (TextView) linearLayout.findViewById(R.id.location);
            TextView tv_end = (TextView) linearLayout.findViewById(R.id.city);
            ImageView icon = (ImageView) linearLayout.findViewById(R.id.img_del);


            return new BuslistAdapter_new.ViewHolder(linearLayout, tv_busrgno, tv_bid, tv_btripno, tv_btype, tv_start, tv_end, icon);
        }
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final BuslistAdapter_new.ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.custombus, parent, false);
            vh = BuslistAdapter_new.ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (BuslistAdapter_new.ViewHolder) convertView.getTag();
        }

        final Mod_cur_running_bus.UserDataBean tlist = getItem(position);
        final String busid = Integer.toString(tlist.getBusId());
        String busname=tlist.getBusName();

        vh.tv_busrgno.setText(busname);

        vh.tv_bid.setText(tlist.getBusSpecialId());
        vh.tv_btripno.setText(tlist.getTripNumber());

        vh.tv_btype.setText(tlist.getBusType());
        vh.tv_start.setText(tlist.getLocationStart());
        vh.tv_end.setText(tlist.getLocationEnd());


        vh.icon.setVisibility(View.GONE);
//        vh.llNoOfTrips.setVisibility(View.GONE);
//
//        vh.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, MapsActivity.class);
//                i.putExtra("BusId",bid.get(position));
//                context.startActivity(i);
//            }
//        });
        return vh.linearLayout;
    }
}
