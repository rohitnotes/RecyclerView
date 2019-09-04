package com.recycler.view.practical.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class FoodListModel {

    @SerializedName("FoodList")
    @Expose
    private ArrayList<Food> foodList = null;

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }
}
