package com.haymorg.drabc.api;

import com.haymorg.drabc.models.ConditionsResponse;
import com.haymorg.drabc.models.Treatment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {
//    @Headers("Content-Type: application/json")
    @GET("/dev/conditions")
    Call<ArrayList<ConditionsResponse>> getConditions();

    @GET("/dev/treatment")
    Call<Treatment> getTreatment(@Query("id") String id);


//    Call<LoginResponse> userLogin(
//            @Body LoginRequest login
//    );
//
//    @GET("api/v1/user/installation_locations")
//    Call<LocationListResponse> getLocations(
//            @Header("Authorization") String token
//    );


}