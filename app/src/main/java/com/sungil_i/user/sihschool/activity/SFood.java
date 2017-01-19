package com.sungil_i.user.sihschool.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.mustafaferhan.MFCalendarView;
import com.mustafaferhan.onMFCalendarViewListener;
import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.adapter.SFoodAdapter;
import com.sungil_i.user.sihschool.datatype.SDailyFoodsData;
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

    String[] dates;
    ArrayList<SFoodData> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mf = (MFCalendarView) findViewById(R.id.mFCalendarView);
        mf.setOnCalendarViewListener(new onMFCalendarViewListener() {
            @Override
            public void onDateChanged(String date) {
                dates = date.split("-");
                new FoodTask().execute();
            }

            @Override
            public void onDisplayedMonthChanged(int month, int year, String monthStr) {
                dates[0] = String.valueOf(year);
                if(month < 10) {
                    dates[1] = "0" + String.valueOf(month);
                } else {
                    dates[1] = String.valueOf(month);
                }
                mf.setDate(dates[0] + "-" + dates[1] + "-" + dates[2]);
                new FoodTask().execute();
            }
        });

        listView = (ListView) findViewById(R.id.listview);

        dates = mf.getSelectedDate().split("-");

        new FoodTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentMenu(Menus.MENU_FOOD);
    }

    class FoodTask extends AsyncTask<Void, Void, SDailyFoodsData> {

        @Override
        protected SDailyFoodsData doInBackground(Void... params) {
            return new SConnector().getFood(dates[0], dates[1], dates[2]);
        }

        @Override
        protected void onPostExecute(SDailyFoodsData data) {

            SFoodAdapter adapter = new SFoodAdapter(data.getFoods());
            listView.setAdapter(adapter);
        }
    }
}
