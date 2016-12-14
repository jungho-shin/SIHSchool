package com.sungil_i.user.sihschool.service;

import com.sungil_i.user.sihschool.datatype.SGalleryData;
import com.sungil_i.user.sihschool.datatype.SNoticeData;
import com.sungil_i.user.sihschool.datatype.SScheduleData;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by taos9938 on 2016. 12. 14..
 */

public class SConnector {

    public ArrayList<SNoticeData> getNotices() {

        ArrayList<SNoticeData> notices = new ArrayList<SNoticeData>();

        return notices;

    }

    public ArrayList<SScheduleData> getSchedules() {

        ArrayList<SScheduleData> schedules = new ArrayList<SScheduleData>();

        return schedules;

    }

    public ArrayList<SNoticeData> getFoods() {

        ArrayList<SNoticeData> foods = new ArrayList<SNoticeData>();

        return foods;

    }

    public void getJobs() {

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
