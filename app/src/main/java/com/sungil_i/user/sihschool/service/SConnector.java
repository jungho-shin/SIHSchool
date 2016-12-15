package com.sungil_i.user.sihschool.service;

import android.util.Log;

import com.sungil_i.user.sihschool.datatype.SGalleryData;
import com.sungil_i.user.sihschool.datatype.SNoticeData;
import com.sungil_i.user.sihschool.datatype.SScheduleData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by taos9938 on 2016. 12. 14..
 */

public class SConnector {

    private JSONArray request(String strUrl) {

        Log.d("TEST", "strUrl : " + strUrl);

        JSONArray jsonArray = null;

        try {

            URL url = new URL(strUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
//            urlConnection.setDoOutput(false);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setDefaultUseCaches(false);

            urlConnection.connect();

            InputStream is = urlConnection.getInputStream();

            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String line;
            while((line = reader.readLine()) != null) {
                builder.append(line + "\n");
                Log.d("TEST", line);
            }

            jsonArray = new JSONArray(builder.toString());

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (JSONException e) {

            e.printStackTrace();

        }

        return jsonArray;

    }

    /**
     *
     * 열린마당 > 공지사항 : 석성희.
     *
     * @return
     */
    public ArrayList<SNoticeData> getNotices() {

        ArrayList<SNoticeData> notices = new ArrayList<SNoticeData>();

        JSONArray datas = request("http://45.32.29.112:5000/notices");

        for(int i = 0; i < datas.length(); i++) {

            JSONObject data = null;

            try {

                data = (JSONObject) datas.get(i);

                SNoticeData notice = new SNoticeData();
                notice.setIndex(data.getString("번호"));
                notice.setAttachment(data.getString("첨부"));
                notice.setTitle(data.getString("제목"));
                notice.setName(data.getString("이름"));
                notice.setDate(data.getString("날짜"));
                notice.setHit(data.getInt("조회"));

                notices.add(notice);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return notices;

    }

    /**
     *
     * 학교생활/학사 > 학사일정 : 안용찬
     *
     * @return
     */
    public ArrayList<SScheduleData> getSchedules() {

        ArrayList<SScheduleData> schedules = new ArrayList<SScheduleData>();

        return schedules;

    }

    /**
     *
     * 학교생활/학사 > 급식 > 오늘의 식단 : 양희석
     *
     * @return
     */
    public ArrayList<SNoticeData> getFoods() {

        ArrayList<SNoticeData> foods = new ArrayList<SNoticeData>();

        for(int i = 0; i < 10; i++) {

            SNoticeData data = new SNoticeData();
            data.setIndex("" + i);
            data.setAttachment("test " + i);
            data.setTitle("Schedule title " + i);
            data.setName("신정호");
            data.setDate("2016.12.16");
            data.setHit(i);

            foods.add(data);

        }

        return foods;

    }

    /**
     *
     * 가정통신문 :
     *     학교생활/학사 > 가정통신문 : 박현기
     *
     * @return
     */
    public ArrayList<SNoticeData> getHomeMail() {

        ArrayList<SNoticeData> homeMails = new ArrayList<SNoticeData>();

        for(int i = 0; i < 10; i++) {

            SNoticeData data = new SNoticeData();
            data.setIndex("" + i);
            data.setAttachment("test " + i);
            data.setTitle("HomeMail title " + i);
            data.setName("신정호");
            data.setDate("2016.12.16");
            data.setHit(i);

            homeMails.add(data);

        }

        return homeMails;

    }

    /**
     *
     * 취업 : 취업 > 취업새소식 : 강찬
     *
     * @return
     */
    public ArrayList<SNoticeData> getJobs() {

        ArrayList<SNoticeData> jobs = new ArrayList<SNoticeData>();

        for(int i = 0; i < 10; i++) {

            SNoticeData data = new SNoticeData();
            data.setIndex("" + i);
            data.setAttachment("test " + i);
            data.setTitle("HomeMail title " + i);
            data.setName("신정호");
            data.setDate("2016.12.16");
            data.setHit(i);

            jobs.add(data);

        }

        return jobs;

    }

    public ArrayList<SNoticeData> getCompanies() {

        ArrayList<SNoticeData> companies = new ArrayList<SNoticeData>();

        return companies;

    }

    public ArrayList<SNoticeData> getQnas() {

        ArrayList<SNoticeData> qnas = new ArrayList<SNoticeData>();

        return qnas;

    }

    public ArrayList<SGalleryData> getPhotos() {

        ArrayList<SGalleryData> photos = new ArrayList<SGalleryData>();

        return photos;

    }

}
