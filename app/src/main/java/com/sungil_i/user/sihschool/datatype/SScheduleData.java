package com.sungil_i.user.sihschool.datatype;

/**
 * Created by taos9938 on 2016. 12. 14..
 */

public class SScheduleData {

    private String date;
    private String title;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if(date.length() == 1) {
            date = "0" + date;
        }
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
