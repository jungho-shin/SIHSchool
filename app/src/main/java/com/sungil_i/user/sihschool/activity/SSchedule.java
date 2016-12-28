package com.sungil_i.user.sihschool.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.mustafaferhan.MFCalendarView;
import com.mustafaferhan.onMFCalendarViewListener;
import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.datatype.SScheduleData;
import com.sungil_i.user.sihschool.service.SConnector;
import com.sungil_i.user.sihschool.widget.Menus;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-08.
 */

public class SSchedule extends CommonActivity {

    MFCalendarView mf;
    TextView tv_content;

    String[] dates;
    ArrayList<SScheduleData> schedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        mf = (MFCalendarView) findViewById(R.id.mFCalendarView);
        mf.setOnCalendarViewListener(new onMFCalendarViewListener() {
            @Override
            public void onDateChanged(String date) {
                Toast.makeText(SSchedule.this, "onDateChanged : " + date, Toast.LENGTH_LONG).show();
                dates = date.split("-");
                showSchedule();
            }

            @Override
            public void onDisplayedMonthChanged(int month, int year, String monthStr) {
                Toast.makeText(SSchedule.this, month + "." + year + "." + monthStr, Toast.LENGTH_LONG);
            }
        });

        tv_content = (TextView) findViewById(R.id.tv_content);

        dates = mf.getSelectedDate().split("-");

        new ScheduleTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentMenu(Menus.MENU_SCHEDULE);
    }

    private void showSchedule() {

        for(int i = 0; i < schedules.size(); i++) {

            SScheduleData schedule = schedules.get(i);

            if(schedule.getDate().equals(dates[2])) {
                if(schedule.getTitle().equals("")) {
                    tv_content.setText("등록된 일정이 없습니다.");
                } else {
                    tv_content.setText(schedule.getTitle());
                }
                break;
            }

        }

    }

    class ScheduleTask extends AsyncTask<Void, Void, ArrayList<SScheduleData>> {

        @Override
        protected ArrayList<SScheduleData> doInBackground(Void... params) {
            return new SConnector().getSchedules();
        }

        @Override
        protected void onPostExecute(ArrayList<SScheduleData> datas) {

            schedules = datas;
            ArrayList<String> eventDays = new ArrayList<String>();

            for(int i = 0; i < datas.size(); i++) {
                if(!datas.get(i).getTitle().equals("")) {
                    eventDays.add(dates[0] + "-" + dates[1] + "-" + datas.get(i).getDate());
                }
            }

            mf.setEvents(eventDays);

            showSchedule();

        }
    }
}
