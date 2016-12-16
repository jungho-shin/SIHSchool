package com.sungil_i.user.sihschool.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sungil_i.user.sihschool.R;

/**
 * Created by user on 2016-11-08.
 */

public class SIntro extends Activity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintro_1);
        handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SIntro.this, SNotice.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }

    };

