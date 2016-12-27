package com.sungil_i.user.sihschool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.activity.SFood;
import com.sungil_i.user.sihschool.datatype.SFoodData;
import com.sungil_i.user.sihschool.datatype.SNoticeData;

import java.util.ArrayList;

/**
 * Created by taos9938 on 2016. 12. 15..
 */

public class SFoodAdapter extends BaseAdapter {

    private ArrayList<SFoodData> datas = new ArrayList<SFoodData>();

    public SFoodAdapter(ArrayList<SFoodData> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public SFoodData getItem(int position) {
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
            convertView = inflater.inflate(R.layout.list_item_food, parent, false);

        }

        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        ImageView photo = (ImageView) convertView.findViewById(R.id.iv_photo);
        TextView menu = (TextView) convertView.findViewById(R.id.tv_menu);

        SFoodData data = getItem(position);

        title.setText(data.getTitle());
        // TODO_TAOS - set photo
        menu.setText(data.getMenu());

        return convertView;
    }
}
