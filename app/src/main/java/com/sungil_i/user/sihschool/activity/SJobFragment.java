package com.sungil_i.user.sihschool.activity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.adapter.SNoticeAdapter;
import com.sungil_i.user.sihschool.datatype.SNoticeData;
import com.sungil_i.user.sihschool.service.SConnector;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SJobFragment extends Fragment {


    ListView listView;

    public SJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        JobTask jobTask = new JobTask();
        jobTask.execute();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    class JobTask extends AsyncTask<Void, Void, ArrayList<SNoticeData>> {


        @Override
        protected ArrayList<SNoticeData> doInBackground(Void... params) {
            return new SConnector().getJobs();
        }

        @Override
        protected void onPostExecute(ArrayList<SNoticeData> datas) {

            SNoticeAdapter adapter = new SNoticeAdapter(datas);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), SNoticeDetail.class);
                    intent.putExtra("index", position);
                    intent.putExtra("pages",4);
                    intent.putExtra("title",2);
                    startActivity(intent);

                }
            });

        }
    }

}