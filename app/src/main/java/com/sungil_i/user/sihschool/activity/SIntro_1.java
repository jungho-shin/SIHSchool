package com.sungil_i.user.sihschool.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sungil_i.user.sihschool.MainActivity;
import com.sungil_i.user.sihschool.R;

/**
 * Created by user on 2016-11-08.
 */

public class SIntro_1 extends Activity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintro_1);
        handler= new Handler();
        handler.postDelayed(runnable, 2000);

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SIntro_1.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };






}
