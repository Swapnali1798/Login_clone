package com.example.login_retrofit_api;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Interface_api {
    @POST("user.accounts/checkLogin")
    Call<String> checkLogin(@Header("Authorization")String authToken);


}
