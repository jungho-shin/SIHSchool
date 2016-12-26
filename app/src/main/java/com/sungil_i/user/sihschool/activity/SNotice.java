package com.sungil_i.user.sihschool.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.adapter.SNoticeAdapter;
import com.sungil_i.user.sihschool.datatype.SNoticeData;
import com.sungil_i.user.sihschool.service.SConnector;
import com.sungil_i.user.sihschool.widget.Menus;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-08.
 */

public class SNotice extends CommonActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        listView = (ListView) findViewById(R.id.listview);

        new NoticeTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentMenu(Menus.MENU_NOTICE);
    }

    class NoticeTask extends AsyncTask<Void, Void, ArrayList<SNoticeData>> {


        @Override
        protected ArrayList<SNoticeData> doInBackground(Void... params) {
            return new SConnector().getNotices();
        }

        @Override
        protected void onPostExecute(ArrayList<SNoticeData> datas) {

            SNoticeAdapter adapter = new SNoticeAdapter(datas);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(SNotice.this, SNoticeDetail.class);
                    intent.putExtra("index", position);
                    startActivity(intent);
                }
            });

        }
    }
}
