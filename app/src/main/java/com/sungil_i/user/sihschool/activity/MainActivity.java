package com.sungil_i.user.sihschool.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sungil_i.user.sihschool.R;
import com.viewpagerindicator.IconPageIndicator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment[] arrFragment = new Fragment[6];
        arrFragment[0] = new SNoticeFragment();
        arrFragment[1] = new SScheduleFragment();
        arrFragment[2] = new SFoodFragment();
        arrFragment[5] = new SEmployeesNewsFragment();
        arrFragment[4] = new SJobFragment();
        arrFragment[3] = new HomeMailFrament();
        ViewPager viewPager = (ViewPager)findViewById(R.id.vp);
        IconPageIndicator iconPageIndicator = (IconPageIndicator) findViewById(R.id.indicator);
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(),arrFragment);
        viewPager.setAdapter(vpAdapter);



    }
    protected class VpAdapter extends FragmentPagerAdapter {
        Fragment[] arrFragment;
        public VpAdapter(FragmentManager fm, Fragment[] arrFragment) {
            super(fm);
            this.arrFragment=arrFragment;
        }

        @Override
        public Fragment getItem(int position) {
            return arrFragment[position];
        }

        @Override
        public int getCount() {
            return arrFragment.length;
        }
    }
}
