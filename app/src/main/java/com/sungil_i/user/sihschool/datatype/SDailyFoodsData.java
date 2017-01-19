package com.sungil_i.user.sihschool.datatype;

import java.util.ArrayList;

/**
 * Created by taos9938 on 2017. 1. 19..
 */

public class SDailyFoodsData {

    String title;
    ArrayList<SFoodData> foods;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<SFoodData> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<SFoodData> foods) {
        this.foods = foods;
    }
}
