package com.sungil_i.user.sihschool.activity;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mustafaferhan.MFCalendarView;
import com.mustafaferhan.onMFCalendarViewListener;
import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.adapter.SFoodAdapter;
import com.sungil_i.user.sihschool.datatype.SDailyFoodsData;
import com.sungil_i.user.sihschool.datatype.SFoodData;
import com.sungil_i.user.sihschool.service.SConnector;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SFoodFragment extends Fragment {
    MFCalendarView mf;
    ListView listView;
    String[] dates;
    ArrayList<SFoodData> foods;

    public SFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_food, container, false);
        mf = (MFCalendarView)view.findViewById(R.id.mFCalendarView);
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

        listView = (ListView)view.findViewById(R.id.listview);

        dates = mf.getSelectedDate().split("-");

        new FoodTask().execute();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

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
