package com.sungil_i.user.sihschool.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sungil_i.user.sihschool.R;
import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    String[] CONTENT = new String[] { "공지", "일정", "급식", "가정"+"\n"+"통신문", "취업", "취업자"+"\n"+"소식" };

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
        TabPageIndicator tabPageIndicator = (TabPageIndicator)findViewById(R.id.indi);
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(),arrFragment);
        viewPager.setAdapter(vpAdapter);
        tabPageIndicator.setViewPager(viewPager);
        viewPager.setOnPageChangeListener(this);

        setTitle(getString(R.string.menu_notice));

        ;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0: {
                setTitle(getString(R.string.menu_notice));
                break;
            }
            case 1: {
                setTitle(getString(R.string.menu_schedule));
                break;
            }
            case 2: {
                setTitle(getString(R.string.menu_food));
                break;
            }
            case 3: {
                setTitle(getString(R.string.menu_home));
                break;
            }
            case 4: {
                setTitle(getString(R.string.menu_job));
                break;
            }
            case 5: {
                setTitle(getString(R.string.menu_employee_news));
                break;
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position];
        }
    }
}
