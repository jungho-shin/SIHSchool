package com.sungil_i.user.sihschool.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.widget.Menus;

/**
 * Created by user on 2016-11-08.
 */

public class SSchedule extends CommonActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCurrentMenu(Menus.MENU_SCHEDULE);
        setContentView(R.layout.activity_schedule);

        calendarView = (CalendarView) findViewById(R.id.calendarView);


    }
}
