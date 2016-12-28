package com.sungil_i.user.sihschool.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.datatype.SNoticeData;
import com.sungil_i.user.sihschool.service.SConnector;
import com.sungil_i.user.sihschool.widget.Menus;

/**
 * Created by user on 2016-11-08.
 */

public class SNoticeDetail extends CommonActivity {

    TextView tv_title;
    TextView tv_name;
    TextView tv_hits;
    TextView tv_date;
    TextView tv_content;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_hits = (TextView) findViewById(R.id.tv_hits);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_content = (TextView) findViewById(R.id.tv_content);

        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);

        new NoticeTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentMenu(getCurrentMenu());
    }

    class NoticeTask extends AsyncTask<Void, Void, SNoticeData> {


        @Override
        protected SNoticeData doInBackground(Void... params) {
            switch (getCurrentMenu()) {
                case Menus.MENU_NOTICE:
                    return new SConnector().getNotice(index);
                case Menus.MENU_HOME:
                    return new SConnector().getHome(index);
                case Menus.MENU_JOB:
                    return new SConnector().getJob(index);
                case Menus.MENU_EMPLOYEE_NEWS:
                    return new SConnector().getEmployeeNews(index);
                default:
                    return new SConnector().getNotice(index);
            }
        }

        @Override
        protected void onPostExecute(SNoticeData data) {

            tv_title.setText(data.getTitle());
            tv_name.setText(data.getName());
            tv_hits.setText(String.valueOf(data.getHit()));
            tv_date.setText(data.getDate());
            tv_content.setText(data.getContent());

        }
    }
}
