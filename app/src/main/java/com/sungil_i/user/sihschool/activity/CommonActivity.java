package com.sungil_i.user.sihschool.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.widget.Menus;
import com.sungil_i.user.sihschool.widget.OnMenuClickListener;

/**
 * Created by user on 2016-11-08.
 */

public class CommonActivity extends Activity implements OnMenuClickListener {

    private Menus menus;
    private int currentMenu;

    @Override
    protected void onStart() {
        super.onStart();

        menus = (Menus) findViewById(R.id.menu);
        menus.setOnMenuClickListener(this);
    }

    @Override
    public void onMenuClicked(int menuType) {
        Log.d("TEST", ">>>>> menuType : " + menuType);

        if(currentMenu == menuType) {
            return;
        }

        Intent intent;
        switch (menuType) {
            case Menus.MENU_NOTICE:
                intent = new Intent(this, SNotice_1.class);
                startActivity(intent);
                break;
            case Menus.MENU_SCHEDULE:
                intent = new Intent(this, SSchedule_1.class);
                startActivity(intent);
                break;
            case Menus.MENU_FOOD:
                intent = new Intent(this, SFood_1.class);
                startActivity(intent);
                break;
            case Menus.MENU_HOME:
                intent = new Intent(this, SHomeMail.class);
                startActivity(intent);
                break;
            case Menus.MENU_JOB:
                intent = new Intent(this, SJob_1.class);
                startActivity(intent);
                break;
            case Menus.MENU_EMPLOYEE_NEWS:
                intent = new Intent(this, SEmployeesNews.class);
                startActivity(intent);
                break;
        }

        finish();

    }

    public void setCurrentMenu(int menuType) {
        this.currentMenu = menuType;
    }
}
