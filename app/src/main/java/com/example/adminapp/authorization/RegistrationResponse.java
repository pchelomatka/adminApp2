package com.example.adminapp.authorization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {

    @SerializedName("token")
    @Expose
    public String token;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("response")
    @Expose
    public Object response;


}
