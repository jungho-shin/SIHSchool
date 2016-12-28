package com.sungil_i.user.sihschool.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.mustafaferhan.MFCalendarView;
import com.mustafaferhan.onMFCalendarViewListener;
import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.adapter.SFoodAdapter;
import com.sungil_i.user.sihschool.datatype.SFoodData;
import com.sungil_i.user.sihschool.service.SConnector;
import com.sungil_i.user.sihschool.widget.Menus;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-08.
 */

public class SFood extends CommonActivity {

    MFCalendarView mf;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mf = (MFCalendarView) findViewById(R.id.mFCalendarView);
        mf.setOnCalendarViewListener(new onMFCalendarViewListener() {
            @Override
            public void onDateChanged(String date) {
                Toast.makeText(SFood.this, "onDateChanged : " + date, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDisplayedMonthChanged(int month, int year, String monthStr) {
                Toast.makeText(SFood.this, month + "." + year + "." + monthStr, Toast.LENGTH_LONG);
            }
        });

        listView = (ListView) findViewById(R.id.listview);

        new FoodTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentMenu(Menus.MENU_FOOD);
    }

    class FoodTask extends AsyncTask<Void, Void, ArrayList<SFoodData>> {

        String[] date;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            date = mf.getSelectedDate().split("-");
        }

        @Override
        protected ArrayList<SFoodData> doInBackground(Void... params) {
            return new SConnector().getFood();
        }

        @Override
        protected void onPostExecute(ArrayList<SFoodData> datas) {

            SFoodAdapter adapter = new SFoodAdapter(datas);
            listView.setAdapter(adapter);
        }
    }
}
