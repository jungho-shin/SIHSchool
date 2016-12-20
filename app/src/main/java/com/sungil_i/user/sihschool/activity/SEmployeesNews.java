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
        setContentView(R.layout.activity_notice);

        listView = (ListView) findViewById(R.id.listview);

        new EmployeesNewsTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentMenu(Menus.MENU_EMPLOYEE_NEWS);
    }

    class EmployeesNewsTask extends AsyncTask<Void, Void, ArrayList<SNoticeData>> {


        @Override
        protected ArrayList<SNoticeData> doInBackground(Void... params) {
            return new SConnector().getEmployeesNews();
        }

        @Override
        protected void onPostExecute(ArrayList<SNoticeData> datas) {

            SNoticeAdapter adapter = new SNoticeAdapter(datas);
            listView.setAdapter(adapter);

        }
    }
}
