package com.sungil_i.user.sihschool.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.widget.Menus;
import com.sungil_i.user.sihschool.widget.OnMenuClickListener;

/**
 * Created by user on 2016-11-08.
 */

public class CommonActivity extends AppCompatActivity implements OnMenuClickListener {

    private Menus menus;
    private static int currentMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        menus = (Menus) findViewById(R.id.menu);
        menus.setOnMenuClickListener(this);
    }

    @Override
    public void onMenuClicked(int menuType) {

        // 현재와 동일한 메뉴를 클릭한 경우 아무 작업을 하지
        // 않고 리턴.
        if(currentMenu == menuType) {
            return;
        }

        Intent intent = null;
        switch (menuType) {
            case Menus.MENU_NOTICE:
                intent = new Intent(this, SNotice.class);
                break;
            case Menus.MENU_SCHEDULE:
                intent = new Intent(this, SSchedule.class);
                break;
            case Menus.MENU_FOOD:
                intent = new Intent(this, SFood.class);
                break;
            case Menus.MENU_HOME:
                intent = new Intent(this, SHomeMail.class);
                break;
            case Menus.MENU_JOB:
                intent = new Intent(this, SJob.class);
                break;
            case Menus.MENU_EMPLOYEE_NEWS:
                intent = new Intent(this, SEmployeesNews.class);
                break;
        }

        startActivity(intent);
        finish();

    }

    public void setCurrentMenu(int menuType) {
        currentMenu = menuType;
        menus.setFocusableInTouchMode(menuType);
    }

    public int getCurrentMenu() {
        return currentMenu;
    }
}
