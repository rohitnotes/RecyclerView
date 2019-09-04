package com.recycler.view.practical.network;

public class ApiUtils
{
    private static final String API_BASE_URL ="https://hellomajorproject.000webhostapp.com/";

    public static ApiService getAPIService()
    {
        return ApiClient.getApiClient(API_BASE_URL).create(ApiService.class);
    }
}
