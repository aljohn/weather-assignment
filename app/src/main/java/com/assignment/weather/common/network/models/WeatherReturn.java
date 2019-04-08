package com.assignment.weather.common.network.models;

import com.google.gson.annotations.SerializedName;

public class WeatherReturn {

    @SerializedName("icon")
    private String icon;
    @SerializedName("description")
    private String description;

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }
}
