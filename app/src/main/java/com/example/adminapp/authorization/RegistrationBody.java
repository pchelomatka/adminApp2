package com.example.adminapp.authorization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationBody {


    @SerializedName("login")
    @Expose
    public String login;

    @SerializedName("password")
    @Expose
    public String password;


    public RegistrationBody(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
