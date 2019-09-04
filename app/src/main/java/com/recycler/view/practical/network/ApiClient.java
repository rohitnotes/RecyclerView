package com.recycler.view.practical.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient
{
    private static Retrofit retrofit = null;
    static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    static Retrofit getApiClient(String baseUrl)
    {
        if (retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
