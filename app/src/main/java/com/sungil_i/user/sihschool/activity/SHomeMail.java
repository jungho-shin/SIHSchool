package com.sungil_i.user.sihschool.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.adapter.SHomeMailAdapter;
import com.sungil_i.user.sihschool.datatype.SNoticeData;
import com.sungil_i.user.sihschool.service.SConnector;
import com.sungil_i.user.sihschool.widget.Menus;

import java.util.ArrayList;

/**
 * Created by phg54 on 2016-12-15.
 */

public class SHomeMail extends CommonActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        listView = (ListView) findViewById(R.id.listview);

        new HomeMailTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentMenu(Menus.MENU_HOME);
    }

    class HomeMailTask extends AsyncTask<Void, Void, ArrayList<SNoticeData>>{

        @Override
        protected ArrayList<SNoticeData> doInBackground(Void... params) {
            return new SConnector().getHomes();
        }

        @Override
        protected void onPostExecute(ArrayList<SNoticeData> sNoticeDatas) {
            SHomeMailAdapter adapter = new SHomeMailAdapter(sNoticeDatas);
            listView.setAdapter(adapter);
        }
    }
}
