package com.sungil_i.user.sihschool.activity;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sungil_i.user.sihschool.R;
import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends AppCompatActivity {
    String[] CONTENT = new String[]{"공지", "일정", "급식", "가정" + "\n" + "통신문", "취업", "취업자" + "\n" + "소식"};
    private DrawerLayout drawerLayout ;
    private ListView listView;
    private ActionBarDrawerToggle actionBarDrawerToggle ;
    ViewPager viewPager;


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
        viewPager = (ViewPager) findViewById(R.id.vp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), arrFragment);
        viewPager.setAdapter(vpAdapter);


        listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, CONTENT);
        listView.setAdapter(adapter);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_drawer, R.string.close_drawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        viewPager.setCurrentItem(position);
                        break;

                    case 1:
                        viewPager.setCurrentItem(position);
                        break;

                    case 2:
                        viewPager.setCurrentItem(position);
                        break;

                    case 3:
                        viewPager.setCurrentItem(position);
                        break;

                    case 4:
                        viewPager.setCurrentItem(position);
                        break;

                    case 5:
                        viewPager.setCurrentItem(position);
                        break;


                }
                drawerLayout.closeDrawer(listView);
            }
        });




        setTitle(getString(R.string.menu_notice));
        ;

    }


    protected class VpAdapter extends FragmentPagerAdapter {
        Fragment[] arrFragment;

        public VpAdapter(FragmentManager fm, Fragment[] arrFragment) {
            super(fm);
            this.arrFragment = arrFragment;
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


};

