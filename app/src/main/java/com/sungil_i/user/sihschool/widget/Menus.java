package com.sungil_i.user.sihschool.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sungil_i.user.sihschool.R;

/**
 * Created by taos9938 on 2016. 12. 15..
 */

public class Menus extends LinearLayout implements View.OnClickListener {

    public static final int MENU_NOTICE = 1001;
    public static final int MENU_SCHEDULE = 1002;
    public static final int MENU_FOOD = 1003;
    public static final int MENU_HOME = 1004;
    public static final int MENU_JOB = 1005;
    public static final int MENU_EMPLOYEE_NEWS = 1006;

    private Button btn_notice;
    private Button btn_schedule;
    private Button btn_food;
    private Button btn_home;
    private Button btn_job;
    private Button btn_employee_news;

    private OnMenuClickListener onMenuClickListener;

    public Menus(Context context) {
        super(context);
        initView();
    }

    public Menus(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public Menus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.widget_menu, this, false);
        addView(v);

        btn_notice = (Button) v.findViewById(R.id.btn_notice);
        btn_notice.setOnClickListener(this);

        btn_schedule = (Button) v.findViewById(R.id.btn_schedule);
        btn_schedule.setOnClickListener(this);

        btn_food = (Button) v.findViewById(R.id.btn_food);
        btn_food.setOnClickListener(this);

        btn_home = (Button) v.findViewById(R.id.btn_home);
        btn_home.setOnClickListener(this);

        btn_job = (Button) v.findViewById(R.id.btn_job);
        btn_job.setOnClickListener(this);

        btn_employee_news = (Button) v.findViewById(R.id.btn_employee_news);
        btn_employee_news.setOnClickListener(this);
    }

    public void setOnMenuClickListener(OnMenuClickListener listener) {
        onMenuClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_notice:
                if(onMenuClickListener != null) {
                    onMenuClickListener.onMenuClicked(MENU_NOTICE);
                }
                break;
            case R.id.btn_schedule:
                if(onMenuClickListener != null) {
                    onMenuClickListener.onMenuClicked(MENU_SCHEDULE);
                }
                break;
            case R.id.btn_food:
                if(onMenuClickListener != null) {
                    onMenuClickListener.onMenuClicked(MENU_FOOD);
                }
                break;
            case R.id.btn_home:
                if(onMenuClickListener != null) {
                    onMenuClickListener.onMenuClicked(MENU_HOME);
                }
                break;
            case R.id.btn_job:
                if(onMenuClickListener != null) {
                    onMenuClickListener.onMenuClicked(MENU_JOB);
                }
                break;
            case R.id.btn_employee_news:
                if(onMenuClickListener != null) {
                    onMenuClickListener.onMenuClicked(MENU_EMPLOYEE_NEWS);
                }
                break;
        }
    }
}
