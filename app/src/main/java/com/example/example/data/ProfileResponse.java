package com.example.example.data;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userAge")
    private int userAge;

    @SerializedName("userCountry")
    private String userCountry;

    public int getCode() {
        return code;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public String getUserCountry() {
        return userCountry;
    }
}
