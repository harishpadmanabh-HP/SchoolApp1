package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.schoolmanapp.ktct_school.school.R;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srishtiinnovative on 11/01/18.
 */

public class activity_fees extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    public ViewPager viewPager;
    public TabLayout tabLayout;
    @Bind(R.id.iv_settings)
    ImageView iv_settings;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fees_layout);
        ButterKnife.bind(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        setupTabIcons();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        iv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // dialog.show();
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);

            }
        });

    }


    public void setupTabIcons() {

        tabLayout.addTab(tabLayout.newTab().setText("Paid History"));
        tabLayout.addTab(tabLayout.newTab().setText("Outstanding"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


    }

    public void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(this.getSupportFragmentManager());

        adapter.addFragment(new paid_history(), "Paid History");
        adapter.addFragment(new outstanding(), "Outstanding");


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


}

