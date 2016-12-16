package com.sungil_i.user.sihschool.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.adapter.SNoticeAdapter;
import com.sungil_i.user.sihschool.datatype.SNoticeData;
import com.sungil_i.user.sihschool.service.SConnector;
import com.sungil_i.user.sihschool.widget.Menus;

import java.util.ArrayList;

/**
 * Created by taos9938 on 2016. 12. 15..
 */

public class SEmployeesNews extends CommonActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCurrentMenu(Menus.MENU_EMPLOYEE_NEWS);
        setContentView(R.layout.activity_employees_news);

        listView = (ListView) findViewById(R.id.listview);

        new EmployeesNewsTask().execute();
    }

    class EmployeesNewsTask extends AsyncTask<Void, Void, ArrayList<SNoticeData>> {


        @Override
        protected ArrayList<SNoticeData> doInBackground(Void... params) {
            return new SConnector().getNotices();
        }

        @Override
        protected void onPostExecute(ArrayList<SNoticeData> datas) {

            SNoticeAdapter adapter = new SNoticeAdapter(datas);
            listView.setAdapter(adapter);

        }
    }
}
