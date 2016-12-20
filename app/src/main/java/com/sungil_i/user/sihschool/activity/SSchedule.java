package com.sungil_i.user.sihschool.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CalendarView;

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

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

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


        }
    }
}
