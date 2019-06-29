package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Activity_Message extends BaseActivity implements TabLayout.OnTabSelectedListener{
    public ViewPager viewPager;
    public TabLayout tabLayout;
    @Bind(R.id.iv_settings)
    ImageView iv_settings;
    Integer l=0;
    private FloatingActionButton fab;
    Adapter adapter = new Adapter(this.getSupportFragmentManager());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__message);
        ButterKnife.bind(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), message_tosend.class);
//                intent.putExtra("kidid",kidid);
//                intent.putExtra("file",path);
//                intent.putExtra("name",name);
                startActivity(intent);
                finish();

            }
        });
        setupTabIcons();
//    l=adapter.mFragments.size();
//        if(l>0)
//        {
//            adapter.mFragments.clear();
//            adapter.mFragmentTitles.clear();
//        }
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);



        iv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);

            }
        });

    }


    public void setupTabIcons() {

        tabLayout.addTab(tabLayout.newTab().setText("RECEIVE"));
        tabLayout.addTab(tabLayout.newTab().setText("SEND"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


    }


    public void setupViewPager(ViewPager viewPager) {

        adapter.addFragment(new receive_message(), "RECEIVE");
        adapter.addFragment(new send_message(), "SEND");



        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);


        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPager.setCurrentItem(tab.getPosition());
        // title.setText(tab.getText());


    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    static class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
           // mFragments.clear();
         //   mFragmentTitles.clear();
            mFragments.add(fragment);

            mFragmentTitles.add(title);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
    @Override
    public void onBackPressed() {
super.onBackPressed();

        finish();
    }
}
