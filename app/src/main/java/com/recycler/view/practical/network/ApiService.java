package com.recycler.view.practical.network;

import com.recycler.view.practical.model.FoodListModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("foodList.json")
    Call<FoodListModel> getMyJSON();
}
