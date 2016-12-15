package com.sungil_i.user.sihschool.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.sungil_i.user.sihschool.R;

/**
 * Created by taos9938 on 2016. 12. 15..
 */

public class Menus extends LinearLayout {

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
    }
}
