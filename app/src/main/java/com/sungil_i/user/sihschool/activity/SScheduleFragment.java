package com.sungil_i.user.sihschool.activity;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mustafaferhan.MFCalendarView;
import com.mustafaferhan.onMFCalendarViewListener;
import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.datatype.SScheduleData;
import com.sungil_i.user.sihschool.service.SConnector;
import com.sungil_i.user.sihschool.widget.Menus;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SScheduleFragment extends Fragment {
    MFCalendarView mf;
    TextView tv_content;

    String[] dates;
    ArrayList<SScheduleData> schedules;

    public SScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_schedule, container, false);
        mf = (MFCalendarView)view.findViewById(R.id.mFCalendarView);
        mf.setOnCalendarViewListener(new onMFCalendarViewListener() {
            @Override
            public void onDateChanged(String date) {
                dates = date.split("-");
                showSchedule();
            }

            @Override
            public void onDisplayedMonthChanged(int month, int year, String monthStr) {
                dates[0] = String.valueOf(year);
                if(month < 10) {
                    dates[1] = "0" + String.valueOf(month);
                } else {
                    dates[1] = String.valueOf(month);
                }
                ScheduleTask scheduleTask = new ScheduleTask();
                scheduleTask.execute();
            }
        });
        tv_content = (TextView)view.findViewById(R.id.tv_content);
        dates = mf.getSelectedDate().split("-");
        ScheduleTask scheduleTask = new ScheduleTask();
        scheduleTask.execute();
        return view;

        }

    public void onResume() {
        super.onResume();

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
            return new SConnector().getSchedules(dates[0], dates[1]);
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








