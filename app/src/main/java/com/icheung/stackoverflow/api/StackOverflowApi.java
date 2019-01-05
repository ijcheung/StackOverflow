package com.icheung.stackoverflow.api;

import com.icheung.stackoverflow.model.Wrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StackOverflowApi {
    @GET("users?site=stackoverflow")
    Call<Wrapper> getUsers();
}