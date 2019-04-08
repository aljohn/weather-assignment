package com.assignment.weather.common.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastsReturn {

    @SerializedName("data")
    private List<DayForecastReturn> forecasts;

    @SerializedName("city_name")
    private String cityName;

    @SerializedName("country_code")
    private String countryCode;

    public List<DayForecastReturn> getForecasts() {
        return forecasts;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
