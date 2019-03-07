package com.example.adminapp.interfaces;

import com.example.adminapp.addPoint.AddPointResponse;
import com.example.adminapp.addPoint.AddPointBody;
import com.example.adminapp.authorization.RegistrationBody;
import com.example.adminapp.authorization.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @POST("/api/login")
    Call<RegistrationResponse> loginUser(@Body RegistrationBody registrationBody);


    @POST("/api/addPoint?token=access1de2f9162a8599d21604cfbdc27b134a")
    Call<AddPointResponse> addPoint(@Body AddPointBody addPointBody);
}
