package com.sungil_i.user.sihschool.service;

import android.util.Log;

import com.sungil_i.user.sihschool.common.Const;
import com.sungil_i.user.sihschool.datatype.SFoodData;
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

    private String request(String strUrl) {

        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream is = null;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;

        try {

            url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setDefaultUseCaches(false);
            urlConnection.connect();

            is = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String line;
            while((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return builder.toString();

    }

    private SScheduleData convertStrToScheduleData(String str) {
        SScheduleData data = null;

        if(str.length() > 0) {

            String[] splits = str.split("\\r\\n\\t\\t\\t\\t\\r\\n");

            data = new SScheduleData();
            if(splits.length == 1) {
                data.setDate(splits[0]);
                data.setTitle("");
            } else if(splits.length == 2) {
                data.setDate(splits[0]);
                data.setTitle(splits[1]);
            }

        }

        return data;
    }

    private ArrayList<SFoodData> parseFood(JSONArray datas) {

        ArrayList<SFoodData> foods = new ArrayList<SFoodData>();

        if(datas != null) {

            for(int i = 0; i < datas.length(); i++) {

                JSONObject data = null;

                try {
                    data = (JSONObject) datas.get(i);

                    SFoodData food = new SFoodData();
                    food.setTitle(data.getString("title"));
                    food.setPhotoUrl(data.getString("photoUrl"));
                    food.setMenu(data.getString("menu"));

                    foods.add(food);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }

        return foods;

    }

    private ArrayList<SNoticeData> parseNotices(JSONArray datas) {

        ArrayList<SNoticeData> notices = new ArrayList<SNoticeData>();

        if(datas != null) {

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
        }

        return notices;

    }

    private SNoticeData parseNotice(JSONObject data) {

        SNoticeData notice = new SNoticeData();

        if(data != null) {
            try {
                notice.setTitle(data.getString("제목"));
                notice.setName(data.getString("이름"));
                notice.setHit(data.getInt("조회수"));
                notice.setDate(data.getString("등록일"));
                notice.setContent(data.getString("내용"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return notice;

    }

    /**
     *
     * 공지목록 : 열린마당 > 공지사항
     *
     * @manager : 석성희
     * @return
     */
    public ArrayList<SNoticeData> getNotices() {
        JSONArray data = null;
        try {
            data = new JSONArray(request(Const.API_DOMAIN + Const.API_PORT + "/notices"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseNotices(data);
    }

    /**
     *
     * 공지상세 : 열린마당 > 공지사항 > 공지상세
     *
     * @manager : 석성희
     * @return
     */
    public SNoticeData getNotice(int id) {
        JSONObject data = null;
        try {
            data = new JSONObject(request(Const.API_DOMAIN + Const.API_PORT + "/notice/" + id));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseNotice(data);
    }
    /**
     *
     * 일정목록 : 학교생활/학사 > 학사일정
     *
     * @manager : 안용찬
     * @return
     */
    public ArrayList<SScheduleData> getSchedules() {

        ArrayList<SScheduleData> schedules = new ArrayList<SScheduleData>();

        JSONArray datas = null;
        try {
            datas = new JSONArray(request(Const.API_DOMAIN + Const.API_PORT + "/schedules"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(datas != null) {

            for(int i = 0; i < datas.length(); i++) {

                JSONObject data = null;

                try {

                    data = (JSONObject) datas.get(i);

                    if(data.length() <= 0) {
                        continue;
                    }

                    String content = data.getString("일요일");
                    SScheduleData sunday = convertStrToScheduleData(content);
                    if(sunday != null) {
                        schedules.add(sunday);
                    }

                    content = data.getString("월요일");
                    SScheduleData monday = convertStrToScheduleData(content);
                    if(monday != null) {
                        schedules.add(monday);
                    }

                    content = data.getString("화요일");
                    SScheduleData tuesday = convertStrToScheduleData(content);
                    if(tuesday != null) {
                        schedules.add(tuesday);
                    }

                    content = data.getString("수요일");
                    SScheduleData wednesday = convertStrToScheduleData(content);
                    if(wednesday != null) {
                        schedules.add(wednesday);
                    }

                    content = data.getString("목요일");
                    SScheduleData thursday = convertStrToScheduleData(content);
                    if(thursday != null) {
                        schedules.add(thursday);
                    }

                    content = data.getString("금요일");
                    SScheduleData friday = convertStrToScheduleData(content);
                    if(friday != null) {
                        schedules.add(friday);
                    }

                    content = data.getString("토요일");
                    SScheduleData saturday = convertStrToScheduleData(content);
                    if(saturday != null) {
                        schedules.add(saturday);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }

        return schedules;

    }

    /**
     *
     * 급식목록 : 학교생활/학사 > 급식 > 오늘의 식단
     *
     * @manager : 양희석
     * @return
     */
    public ArrayList<SFoodData> getFood() {
        JSONArray data = null;
        try {
            data = new JSONArray(request(Const.API_DOMAIN + Const.API_PORT + "/food"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseFood(data);
    }


    /**
     *
     * 가정통신문목록 : 학교생활/학사 > 가정통신문
     *
     * @manager : 박현기
     * @return
     */
    public ArrayList<SNoticeData> getHomes() {
        JSONArray data = null;
        try {
            data = new JSONArray(request(Const.API_DOMAIN + Const.API_PORT + "/homes"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseNotices(data);
    }

    /**
     *
     * 가정통신문상세 : 학교생활/학사 > 가정통신문 > 가정통신문상세
     *
     * @manager : 박현기
     * @return
     */
    public SNoticeData getHome(int id) {
        JSONObject data = null;
        try {
            data = new JSONObject(request(Const.API_DOMAIN + Const.API_PORT + "/home/" + id));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseNotice(data);
    }


    /**
     *
     * 취업목록 : 취업 > 취업새소식
     *
     * @manager : 강찬
     * @return
     */
    public ArrayList<SNoticeData> getJobs() {
        JSONArray data = null;
        try {
            data = new JSONArray(request(Const.API_DOMAIN + Const.API_PORT + "/jobs"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseNotices(data);
    }

    /**
     *
     * 취업상세 : 취업 > 취업새소식 > 취업상세
     *
     * @manager : 강찬
     * @return
     */
    public SNoticeData getJob(int id) {
        JSONObject data = null;
        try {
            data = new JSONObject(request(Const.API_DOMAIN + Const.API_PORT + "/job/" + id));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseNotice(data);
    }


    /**
     *
     * 취업자목록 : 취업 > 합격자안내
     *
     * @manager : 신정호
     * @return
     */
    public ArrayList<SNoticeData> getEmployeesNews() {
        JSONArray data = null;
        try {
            data = new JSONArray(request(Const.API_DOMAIN + Const.API_PORT + "/employees"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseNotices(data);
    }

    /**
     *
     * 취업자상세 : 취업 > 합격자안내 > 취업자상세
     *
     * @manager : 신정호
     * @return
     */
    public SNoticeData getEmployeeNews(int id) {
        JSONObject data = null;
        try {
            data = new JSONObject(request(Const.API_DOMAIN + Const.API_PORT + "/employee/" + id));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseNotice(data);
    }

}
