package com.beniezsche.finintask.retrofit2;

import com.beniezsche.finintask.model.Page;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("users")
    Call<Page> getPage(@Query("page") int page);


}
