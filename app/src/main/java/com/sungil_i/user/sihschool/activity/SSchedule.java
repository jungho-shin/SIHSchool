package com.sungil_i.user.sihschool.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Toast;

import com.mustafaferhan.MFCalendarView;
import com.mustafaferhan.Util;
import com.mustafaferhan.onMFCalendarViewListener;
import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.adapter.SNoticeAdapter;
import com.sungil_i.user.sihschool.datatype.SNoticeData;
import com.sungil_i.user.sihschool.datatype.SScheduleData;
import com.sungil_i.user.sihschool.service.SConnector;
import com.sungil_i.user.sihschool.widget.Menus;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-08.
 */

public class SSchedule extends CommonActivity {

    MFCalendarView mf;
    String yyyymm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        mf = (MFCalendarView) findViewById(R.id.mFCalendarView);
        mf.setOnCalendarViewListener(new onMFCalendarViewListener() {
            @Override
            public void onDateChanged(String date) {
                Toast.makeText(SSchedule.this, "onDateChanged : " + date, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDisplayedMonthChanged(int month, int year, String monthStr) {
                Toast.makeText(SSchedule.this, month + "." + year + "." + monthStr, Toast.LENGTH_LONG);
            }
        });

        yyyymm = mf.getSelectedDate().substring(0, 7);

        new ScheduleTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentMenu(Menus.MENU_SCHEDULE);
    }

    class ScheduleTask extends AsyncTask<Void, Void, ArrayList<SScheduleData>> {

        @Override
        protected ArrayList<SScheduleData> doInBackground(Void... params) {
            return new SConnector().getSchedules();
        }

        @Override
        protected void onPostExecute(ArrayList<SScheduleData> datas) {

            ArrayList<String> eventDays = new ArrayList<String>();

            for(int i = 0; i < datas.size(); i++) {
                if(!datas.get(i).getTitle().equals("")) {
                    eventDays.add(yyyymm + "-" + datas.get(i).getDate());
                }
            }

            mf.setEvents(eventDays);

        }
    }
}
