package com.sungil_i.user.sihschool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.datatype.SNoticeData;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by taos9938 on 2016. 12. 15..
 */

public class SNoticeAdapter extends BaseAdapter {

    private ArrayList<SNoticeData> datas = new ArrayList<SNoticeData>();

    public SNoticeAdapter(ArrayList<SNoticeData> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public SNoticeData getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_notice, parent, false);

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
