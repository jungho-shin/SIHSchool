package com.sungil_i.user.sihschool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.activity.SHomeMail;
import com.sungil_i.user.sihschool.datatype.SNoticeData;

import java.util.ArrayList;

/**
 * Created by phg54 on 2016-12-15.
 */

public class SHomeMailAdapter extends BaseAdapter {

    private ArrayList<SNoticeData> sNoticeDatas = new ArrayList<SNoticeData>();

    public SHomeMailAdapter(ArrayList<SNoticeData> sNoticeDatas) {
        this.sNoticeDatas = sNoticeDatas;
    }

    @Override
    public int getCount() {
        return sNoticeDatas.size();
    }

    @Override
    public SNoticeData getItem(int position) {
        return sNoticeDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_notice,parent,false);
        }

        TextView index = (TextView) convertView.findViewById(R.id.tv_index);
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView date = (TextView) convertView.findViewById(R.id.tv_date);
        TextView hits = (TextView) convertView.findViewById(R.id.tv_hits);

        SNoticeData data = getItem(position);

        index.setText(data.getIndex());
        title.setText(data.getTitle());
        name.setText(data.getName());
        date.setText(data.getDate());
        hits.setText("" + data.getHit());

        return convertView;
    }
}
