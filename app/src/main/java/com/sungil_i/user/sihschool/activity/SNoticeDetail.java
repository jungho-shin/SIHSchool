package com.sungil_i.user.sihschool.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sungil_i.user.sihschool.R;
import com.sungil_i.user.sihschool.datatype.SNoticeData;
import com.sungil_i.user.sihschool.service.SConnector;
import com.sungil_i.user.sihschool.widget.Menus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by user on 2016-11-08.
 */

public class SNoticeDetail extends MainActivity {




    TextView tv_title;
    TextView tv_name;
    TextView tv_hits;
    TextView tv_date;
    TextView tv_content;

    LinearLayout ll_attach1;
    TextView tv_attachfile1;
    LinearLayout ll_attach2;
    TextView tv_attachfile2;



    int index = 0;
    int pages = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_hits = (TextView) findViewById(R.id.tv_hits);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_content = (TextView) findViewById(R.id.tv_content);

        ll_attach1 = (LinearLayout) findViewById(R.id.ll_attach1);
        tv_attachfile1 = (TextView) findViewById(R.id.tv_attachfile1);
        ll_attach2 = (LinearLayout) findViewById(R.id.ll_attach2);
        tv_attachfile2 = (TextView) findViewById(R.id.tv_attachfile2);

        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);




        new NoticeTask().execute();
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    Html.ImageGetter imageGetter = new Html.ImageGetter() {
        @Override
        public Drawable getDrawable(String source) {
            LevelListDrawable d = new LevelListDrawable();
            Drawable empty = getResources().getDrawable(R.drawable.ic_launcher);
            d.addLevel(0, 0, empty);
            d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

            new LoadImage().execute(source, d);

            return d;
        }
    };

    class LoadImage extends AsyncTask<Object, Void, Bitmap> {
        private final String TAG = LoadImage.class.getSimpleName();

        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            Log.d(TAG, "doInBackground " + source);
            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d(TAG, "onPostExecute drawable " + mDrawable);
            Log.d(TAG, "onPostExecute bitmap " + bitmap);
            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = tv_content.getText();
                tv_content.setText(t);
            }
        }
    }

    class NoticeTask extends AsyncTask<Void, Void, SNoticeData> {

        

        @Override
        protected SNoticeData doInBackground(Void... params) {
            Intent intent = getIntent();

            pages = intent.getIntExtra("pages",0);

            if (pages==0){
                return new SConnector().getNotice(index);
            }else if (pages==3){
                return new SConnector().getHome(index);
            }else if (pages==4){
                return new SConnector().getJob(index);
            }else if (pages==5){
                return new SConnector().getEmployeeNews(index);
            }else{
                return new SConnector().getNotice(index);
            }


        }

        @Override
        protected void onPostExecute(SNoticeData data) {

            Log.d("TEST", data.getContent());

            tv_title.setText(data.getTitle());
            tv_name.setText(data.getName());
            tv_hits.setText(String.valueOf(data.getHit()));
            tv_date.setText(data.getDate());
            tv_content.setText(Html.fromHtml(data.getContent(), imageGetter, null));
            tv_content.setMovementMethod(LinkMovementMethod.getInstance());

            if(data.getAttachFile1() != null) {
                ll_attach1.setVisibility(View.VISIBLE);
                tv_attachfile1.setText(Html.fromHtml("<a href=\"" + data.getAttachFile1().getUrl() + "\">" + data.getAttachFile1().getFileName() + "</a>"));
                tv_attachfile1.setMovementMethod(LinkMovementMethod.getInstance());
            }

            if(data.getAttachFile2() != null) {
                ll_attach2.setVisibility(View.VISIBLE);
                tv_attachfile2.setText(Html.fromHtml("<a href=\"" + data.getAttachFile2().getUrl() + "\">" + data.getAttachFile2().getFileName() + "</a>"));
                tv_attachfile2.setMovementMethod(LinkMovementMethod.getInstance());
            }

        }
    }
}
