package com.haymorg.drabc.api;

import com.haymorg.drabc.models.ConditionsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {
//    @Headers("Content-Type: application/json")
    @GET("/dev/conditions")
    Call<ArrayList<ConditionsResponse>> getConditions();


//    Call<LoginResponse> userLogin(
//            @Body LoginRequest login
//    );
//
//    @GET("api/v1/user/installation_locations")
//    Call<LocationListResponse> getLocations(
//            @Header("Authorization") String token
//    );


}