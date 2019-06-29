package com.schoolmanapp.ktct_school.school.parent.Java_class;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_adapter;
import com.schoolmanapp.ktct_school.school.parent.model_class.recevie_adapter;
import com.schoolmanapp.ktct_school.school.parent.utils.AppPreferences;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by srishtiinnovative on 31/05/17.
 */

public class CardViewDataAdapter_recevie extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {
    java.util.Date dates;
    private boolean isLoading;
    private Activity activity;
    private List<recevie_adapter> message;

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    AppPreferences appPreferences;
    String subdate = "";

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private OnLoadMoreListener onLoadMoreListener;


    public CardViewDataAdapter_recevie(RecyclerView recyclerView, List<recevie_adapter> modelhomes, FragmentActivity message_activity) {
        this.message = modelhomes;
        this.activity = message_activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });


    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }


    @Override
    public int getItemViewType(int position) {
        return message.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            appPreferences = AppPreferences.getInstance(activity, activity.getResources().getString(R.string.app_name));
            View view = LayoutInflater.from(activity).inflate(R.layout.custom_message_parent, parent, false);
            return new UserViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            final recevie_adapter contact = message.get(position);
            try {
                String date = message.get(position).gettime();
                int i = date.indexOf('T');
                subdate = date.substring(0, i);
                Log.e(date + " :", "!!!" + subdate);
            } catch (NullPointerException e) {

            }


            SimpleDateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat writeFormat = new SimpleDateFormat("MMM dd,yyyy");

            try {
                dates = readFormat.parse(subdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            Log.e("Changed date", "" + writeFormat.format(dates));

            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.subject.setText(contact.getsubject());
            userViewHolder.time.setText(writeFormat.format(dates));
            userViewHolder.descritpion.setText(contact.getdescription());
            String path = "http://schoolman.in//" + contact.getfilepath();
            if(contact.getfilepath().isEmpty()){
                userViewHolder.image.setVisibility(View.GONE);
            }else {
                userViewHolder.image.setVisibility(View.VISIBLE);
                Picasso.with(activity).load(path).into(userViewHolder.image);
            }

            userViewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appPreferences.saveData("description", contact.getdescription());
                    appPreferences.saveData("subject", contact.getsubject());
                    appPreferences.saveData("filepath", contact.getfilepath());
                    Intent intent = new Intent(activity,student_message.class);
                    activity.startActivity(intent);

                }
            });
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String date_time = sdf.format(contact.gettime());
//            userViewHolder.time.setText(date_time);
//            userViewHolder.message_layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    appPreferences.saveData("description", contact.getdescription());
//                    appPreferences.saveData("subject", contact.getsubject());
//                    appPreferences.saveData("filepath", contact.getfilepath());
//                    if (!contact.getfilepath().equals("")) {
//                        Intent intent = new Intent(activity, student_message.class);
//                        activity.startActivity(intent);
//                    } else {
//                        Intent intent = new Intent(activity, no_webview.class);
//                        activity.startActivity(intent);
//                    }
////
////            }
////
//
//                }
////            });
//            userViewHolder.ll.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(activity, message_tosend.class);
//                    activity.startActivity(i);
//
//
//                }
//            });


        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }


    }

    @Override
    public int getItemCount() {
        return message == null ? 0 : message.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }

    // "Normal item" ViewHolder
    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView subject;
        public TextView descritpion;
        public ImageView image;
        public TextView time;
        public RelativeLayout ll;



        public UserViewHolder(View view) {
            super(view);
            subject = (TextView) view.findViewById(R.id.tv_subject);
            image = (ImageView) view.findViewById(R.id.imageView);
            descritpion = (TextView) view.findViewById(R.id.description);
            time = (TextView) view.findViewById(R.id.time);
            ll=(RelativeLayout)view.findViewById(R.id.ll_tosend);
        }
    }

}