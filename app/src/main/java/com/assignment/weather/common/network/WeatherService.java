package com.assignment.weather.common.network;

import com.assignment.weather.common.network.models.ForecastsReturn;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("forecast/daily")
    Call<ForecastsReturn> getForecasts(
            @Query("days") String days,
            @Query("lat") String latitude,
            @Query("lon") String longitude);
}
